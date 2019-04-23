package org.jitesh.appstatistics.serverstats.model;

import java.util.Date;

public class Process {

	private String pid;
	private String pName;
	private String signature;
	private String group;
	private String user;
	private Date startDate;
	private Date elapsedTime;
	private double memUsage;
	private double cpuUsage;
	private String status;
	
	public Process() {
		this.status=Status.NOK.getState();
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Date elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public double getMemUsage() {
		return memUsage;
	}
	public void setMemUsage(double memUsage) {
		this.memUsage = memUsage;
	}
	public double getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Process [pid=" + pid + ", pName=" + pName + ", signature=" + signature + ", group=" + group + ", user="
				+ user + ", startDate=" + startDate + ", elapsedTime=" + elapsedTime + ", memUsage=" + memUsage
				+ ", cpuUsage=" + cpuUsage + ", status=" + status + "]";
	}	
	
	public enum Status{
		OK("OK"),
		NOK("NOK");
		
		private Status(String state) {
			this.state = state;
		}

		private String state;

		public String getState() {
			return state;
		}
		
	}
}
