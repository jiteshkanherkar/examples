package org.jitesh.appstatistics.serverstats.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jitesh.appstatistics.boot.AppConfig;
import org.jitesh.appstatistics.serverstats.exception.ServerNotFoundException;
import org.jitesh.appstatistics.serverstats.model.Server;
import org.jitesh.appstatistics.serverstats.service.IServerMonitorService;
import org.jitesh.appstatistics.utils.JschClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service
public class ServerMonitorService implements IServerMonitorService {

	private static final Logger LOGGER = LogManager.getLogger(ServerMonitorService.class);

	@Autowired
	private AppConfig appConfig;

	public boolean checkServerAvailable(String ipAddress) throws ServerNotFoundException {
		return sendPingRequest(ipAddress);
	}

	private boolean sendPingRequest(String ipAddress) throws ServerNotFoundException {
		try {
			InetAddress address = InetAddress.getByName(ipAddress);
			LOGGER.info("Sending Ping Request to " + ipAddress);
			if (address.isReachable(5000)) {
				LOGGER.info("Host is reachable");
				return true;
			}
			LOGGER.info("Host is unreachable");
			throw new ServerNotFoundException("Host is unreachable " + ipAddress);
		} catch (IOException e) {
			LOGGER.error("Exception Server Not reachable ", e);
			throw new ServerNotFoundException("Exception in ping request for Host : " + ipAddress);
		}
	}

	@Override
	public Session connectServer(Server _server) throws ServerNotFoundException {
		// get Server from application config
		LOGGER.info("Connecting to server " + _server.getHost());
		Server server = appConfig.getServerMap().get(_server.getHost());

		JschClientBuilder builder = JschClientBuilder.getInstance();
		// TODO password must be encrypted in json file
		try {
			Session session = null;
			Map<String, Session> sessionMap = appConfig.getSessionMap();
			if (sessionMap.containsKey(server.getHost())) {
				LOGGER.info("Fetching existing session " + server.getHost());
				session = sessionMap.get(server.getHost());
			} else {
				LOGGER.info("Logging in server  " + server.getHost());
				session = builder.buildJschClient(server.getIpAddress(), server.getUserName(), server.getPassword())
						.addConfigProperties("StrictHostKeyChecking", "no").connect();
				sessionMap.put(server.getHost(), session);
			}
			return session;
		} catch (JSchException e) {
			throw new ServerNotFoundException("Unable to Logon to Host " + server.getHost());
		}
	}

	@Override
	public void disconnectServer(Session session) {
		LOGGER.info("DisConnecting from server ");
		session.disconnect();
	}

	@PreDestroy
	public void disconnectServers() {
		LOGGER.info("Disconnecting from all servers");
		appConfig.getSessionMap().forEach((host, session) -> disconnectServer(session));
	}

}
