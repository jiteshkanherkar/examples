package org.jitesh.appstatistics.serverstats.controller;

import java.util.List;

import org.jitesh.appstatistics.boot.AppConfig;
import org.jitesh.appstatistics.serverstats.exception.ServerNotFoundException;
import org.jitesh.appstatistics.serverstats.model.Process;
import org.jitesh.appstatistics.serverstats.model.Server;
import org.jitesh.appstatistics.serverstats.service.IProcessMonitorService;
import org.jitesh.appstatistics.serverstats.service.IServerMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.Session;

@RestController
@RequestMapping("/api/server")
public class ServerMonitorController {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private IServerMonitorService serverMonitorService;
	
	@Autowired
	private IProcessMonitorService processMonitorService;

	@GetMapping("list")
	public ResponseEntity<?> getAvailableServerList() {
		List<Server> availableServers = appConfig.getAvailableServers();
		return new ResponseEntity<List<Server>>(availableServers, HttpStatus.OK);
	}

	@GetMapping("pingStatus/{hostAddress}")
	public ResponseEntity<Boolean> getPingStatusOfServer(@PathVariable("hostAddress") String hostAddress)
			throws ServerNotFoundException {
		boolean pingStatus = serverMonitorService.checkServerAvailable(hostAddress);
		return new ResponseEntity<Boolean>(pingStatus, HttpStatus.FOUND);
	}

	@PostMapping("connect")
	public ResponseEntity<?> connectServer(@RequestBody Server server) throws ServerNotFoundException {
		Session session = serverMonitorService.connectServer(server);
		List<Process> processes = processMonitorService.getAvailableProcessDetails(server, session);
		
		return new ResponseEntity<List<Process>>(processes, HttpStatus.OK);
	}
	

}
