package org.jitesh.appstatistics.boot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jitesh.appstatistics.serverstats.model.Server;
import org.jitesh.appstatistics.utils.JsonUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jcraft.jsch.Session;

@Configuration
public class AppConfig {

	private List<Server> availableServers;
	private Map<String, Server> serverMap = new ConcurrentHashMap<String, Server>();
	private Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();
	private static final Logger LOG = LogManager.getLogger(AppConfig.class);

	@PostConstruct
	public void loadAvailableServers() throws IOException {
		CollectionLikeType type = TypeFactory.defaultInstance().constructCollectionLikeType(List.class, Server.class);
		try {
			byte[] bytes = Files.readAllBytes(ResourceUtils.getFile("classpath:server-config.json").toPath());
			availableServers = JsonUtils.convertJsonToObject(type, new ByteArrayInputStream(bytes));
			LOG.info("Available servers for configuration are " + availableServers);
			availableServers.forEach(server -> serverMap.put(server.getHost(), server));
		} catch (IOException e) {
			LOG.error(e.getStackTrace());
			throw e;
		}
	}

	public List<Server> getAvailableServers() {
		return availableServers;
	}

	public Map<String, Server> getServerMap() {
		return serverMap;
	}
	public Map<String, Session> getSessionMap() {
		return sessionMap;
	}
}
