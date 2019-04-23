package org.jitesh.appstatistics.serverstats.service;

import java.util.List;

import org.jitesh.appstatistics.serverstats.model.Process;
import org.jitesh.appstatistics.serverstats.model.Server;

import com.jcraft.jsch.Session;

public interface IProcessMonitorService {

	List<Process> getAvailableProcessDetails(Server dummyServer, Session session);

}
