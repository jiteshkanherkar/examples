package org.jitesh.appstatistics.serverstats.model;

import java.util.List;

public class Server {

	private String host;
	private String ipAddress;
	private String status;
	private String userName;
	private String password;
	private List<Process> processes;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public List<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(List<Process> processes) {
		this.processes = processes;
	}

	@Override
	public String toString() {
		return "Server [host=" + host + ", ipAddress=" + ipAddress + ", status=" + status + ", userName=" + userName
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Server) {
			Server server = (Server) obj;
			return this.getHost().equals(server.getHost()) && this.getIpAddress().equals(server.getIpAddress());
		}
		return false;
	}

}
