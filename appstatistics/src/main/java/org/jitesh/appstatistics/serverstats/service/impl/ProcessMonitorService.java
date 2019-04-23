package org.jitesh.appstatistics.serverstats.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jitesh.appstatistics.boot.AppConfig;
import org.jitesh.appstatistics.serverstats.model.Process;
import org.jitesh.appstatistics.serverstats.model.Server;
import org.jitesh.appstatistics.serverstats.model.Process.Status;
import org.jitesh.appstatistics.serverstats.service.IProcessMonitorService;
import org.jitesh.appstatistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service
public class ProcessMonitorService implements IProcessMonitorService {

	private static final Logger LOGGER = LogManager.getLogger(ProcessMonitorService.class);

	@Autowired
	private AppConfig appConfig;

	@Override
	public List<Process> getAvailableProcessDetails(Server dummyServer, Session session) {
		Server server = appConfig.getServerMap().get(dummyServer.getHost());
		List<Process> processes = server.getProcesses();
		processes.forEach(process -> getProcessDetails(process, session));
		return processes;
	}

	/**
	 * 
	 * @param process
	 * @param session PS command is used to get process details o/p is like cpu
	 *                usage, mem usage, PID, start date, group, process running
	 *                time, user etc
	 */
	private void getProcessDetails(Process process, Session session) {
		try {
			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(
					"ps -e -o \"%cpu\" -o \"%mem\" -o \"|%p|\" -o\"lstart\" -o \"|%g|\" -o \"etime\" -o \"|%u|\" -o \"cmd\" | grep "
							+ process.getSignature() + " | grep -v grep");
			channel.setInputStream(null);
			channel.connect(1000);
			LOGGER.info("Executing command");

			try (InputStream in = channel.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] split = line.split("\\|");
					// cpu & mem usage
					String[] usage = split[0].trim().split(" ");
					LOGGER.info(usage[0]);
					process.setCpuUsage(Double.parseDouble(usage[0].trim()));
					LOGGER.info(usage[1]);
					process.setMemUsage(Double.parseDouble(usage[usage.length-1].trim()));
					// PID of process
					process.setPid(split[1].trim());
					// start date time
					Date startDate = DateUtil.convertDate("EEE MMM dd HH:mm:ss yyyy", split[2].trim());
					process.setStartDate(startDate);
					// group
					process.setGroup(split[3]);
					// elapsed time
					process.setElapsedTime(DateUtil.convertDate("HH:mm:ss", split[4].trim()));
					// user
					process.setUser(split[5]);
					process.setStatus(Status.OK.getState());
				}
			} catch (IOException e) {

			}
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}
}
