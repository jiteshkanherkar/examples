package org.jitesh.examples.restapispringboot.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.input.TeeInputStream;

public class RequestWrapper extends HttpServletRequestWrapper{
	private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
	private long id;
	private String requestDate;

	public RequestWrapper(Long requestId, String requestDate, ServletRequest request) {    	
        super((HttpServletRequest) request);
        this.id = requestId;
        this.requestDate = requestDate;
    }

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new ServletInputStream() {
			private TeeInputStream tee = new TeeInputStream(RequestWrapper.super.getInputStream(), bos);

			@Override
			public int read() throws IOException {
				return tee.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub

			}
		};
	}

	public byte[] toByteArray() {
		return bos.toByteArray();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequestDate() {
		return requestDate;
	}


}
