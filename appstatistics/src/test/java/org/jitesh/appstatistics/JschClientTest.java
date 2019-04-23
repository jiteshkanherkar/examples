package org.jitesh.appstatistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JschClientTest {

	public static void main(String[] args) throws IOException {
		Session session = null;
		Channel channel = null;
		try {
			JSch ssh = new JSch();
			session = ssh.getSession("jitesh", "127.0.0.1", 22);
			session.setConfig("StrictHostKeyChecking", "no");
			
			session.setPassword("mh16bn4983");
			session.connect();
			System.out.println("server connected");

			ChannelExec exec = (ChannelExec) session.openChannel("exec");
			exec.setCommand("ps -ef | grep java");
			exec.setInputStream(null);
			exec.setErrStream(System.err);
			InputStream in = exec.getInputStream();
			exec.connect(1000);

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			int index = 0;

			while ((line = reader.readLine()) != null) {
				System.out.println(++index + " : " + line);
			}
			System.out.println("Connection completed");

		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}
	}
}
