package jp.co.ricoh.cotos.commonlib.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;

@Component
public class ExternalClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Log log = LogFactory.getLog(ExternalClientHttpRequestInterceptor.class);

	@Autowired
	MessageUtil messageUtil;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		logRequest(request, body);
		ClientHttpResponse response = new BufferingClientHttpResponseWrapper(execution.execute(request, body));
		logResponse(response);
		return response;
	}

	public void logRequest(HttpRequest request, byte[] body) {
		log.info(messageUtil.createMessageInfo("ExternalApiRequestLogInfo", Arrays.asList(request.getMethod(), request.getURI(), request.getHeaders(), new String(body), formatter.format(LocalDateTime.now())).toArray(new String[0])).getMsg());
	}

	public void logResponse(ClientHttpResponse response) throws IOException {
		log.info(messageUtil.createMessageInfo("ExternalApiResponseLogInfo", Arrays.asList(response.getStatusCode().value(), response.getHeaders(), StreamUtils.copyToString(response.getBody(), Charset.defaultCharset())).toArray(new String[0])).getMsg());
	}

	public static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

		private final ClientHttpResponse response;

		private byte[] body;

		BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
			this.response = response;
		}

		@Override
		public HttpStatus getStatusCode() throws IOException {
			return this.response.getStatusCode();
		}

		@Override
		public int getRawStatusCode() throws IOException {
			return this.response.getRawStatusCode();
		}

		@Override
		public String getStatusText() throws IOException {
			return this.response.getStatusText();
		}

		@Override
		public HttpHeaders getHeaders() {
			return this.response.getHeaders();
		}

		@Override
		public InputStream getBody() throws IOException {
			if (this.body == null) {
				this.body = StreamUtils.copyToByteArray(this.response.getBody());
			}
			return new ByteArrayInputStream(this.body);
		}

		@Override
		public void close() {
			this.response.close();
		}

	}
}
