package org.jitesh.appstatistics.serverstats.service;

import org.jitesh.appstatistics.serverstats.exception.ServerNotFoundException;
import org.jitesh.appstatistics.serverstats.model.Server;

import com.jcraft.jsch.Session;

public interface IServerMonitorService {

	public boolean checkServerAvailable(String ipAddress) throws ServerNotFoundException;

	Session connectServer(Server _server) throws ServerNotFoundException;

	void disconnectServer(Session session);
}
