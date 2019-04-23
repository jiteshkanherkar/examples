package org.jitesh.appstatistics.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JschClientBuilder {

	private JSch jSch;
	Session session ;
	private JschClientBuilder() {

	}
	public static JschClientBuilder getInstance() {
		return new JschClientBuilder();
	}

	public JschClientBuilder buildJschClient(String hostAddress, String user, String password) throws JSchException {
		jSch = new JSch();
		session = jSch.getSession(user, hostAddress);
		session.setPassword(password);
		return this;
	}

	public JschClientBuilder addConfigProperties(String key, String value) {
		session.setConfig(key, value);
		return this;
	}
	
	public Session getSession() {
		return session;
	}

	public Session connect() throws JSchException {
		session.connect();
		return session;
	}
}
