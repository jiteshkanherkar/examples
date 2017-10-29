package org.jitesh.examples.restapispringboot.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class LoggingFilter implements Filter {
	private static final String REQUEST_PREFIX = " <-----------------Request:-----------------\n ";
	private static final String RESPONSE_PREFIX = " <-----------------Response:-----------------\n ";

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		request = new RequestWrapper(1l, "", request);
		response = new ResponseWrapper(1l, response);
		try {
			filterChain.doFilter(request, response);
			response.flushBuffer();
		} catch (Exception e) {
		} finally {

			HashMap<String, String> reqParams = logRequest(request);
			HashMap<String, String> resParams = logResponse((ResponseWrapper) response);

			System.out.println("reqParams :: " + reqParams);
			System.out.println("resParams :: " + resParams);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private HashMap<String, String> logRequest(final ServletRequest request) {
		HashMap<String, String> params = new HashMap<String, String>();

		StringBuilder msg = new StringBuilder();
		try {
			msg.append(REQUEST_PREFIX);
			if (request instanceof RequestWrapper) {
				msg.append("request id=").append(((RequestWrapper) request).getId()).append(";\n ");

				String requestDate = ((RequestWrapper) request).getRequestDate();

				msg.append("request Date time = ").append(requestDate).append(";\n ");

			}

			if (request.getContentType() != null) {
				msg.append("content type=").append(request.getContentType()).append(";\n ");
			}

			String requestURL = ((HttpServletRequest) request).getRequestURL().toString();

			msg.append("URL =").append(requestURL).append(";\n ");

			if (request instanceof RequestWrapper && !isMultipart(request)) {
				RequestWrapper requestWrapper = (RequestWrapper) request;
				try {
					String charEncoding = requestWrapper.getCharacterEncoding() != null
							? requestWrapper.getCharacterEncoding()
							: "UTF-8";

					String payload = new String(requestWrapper.toByteArray(), charEncoding);

					msg.append("payload=").append(payload).append(";\n ");

				} catch (UnsupportedEncodingException e) {
				}
			}

			System.out.println("Request Data :: " + msg.toString());
		} catch (Exception e) {
		}
		return params;
	}

	private boolean isMultipart(final ServletRequest request) {
		return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
	}

	private HashMap<String, String> logResponse(final ResponseWrapper response) {
		HashMap<String, String> params = new HashMap<String, String>();
		StringBuilder msg = new StringBuilder();
		try {
			msg.append(RESPONSE_PREFIX);
			msg.append("request id=").append(1).append(";\n ");

			msg.append("Response Date =").append(";\n ");
			try {
				String payload = new String(response.toByteArray(), response.getCharacterEncoding());

				msg.append("payload=").append(payload).append(";\n ");

			} catch (UnsupportedEncodingException e) {
			}
		} catch (Exception e) {
		}
		System.out.println("response Data :: " + msg.toString());
		return params;
	}

}
