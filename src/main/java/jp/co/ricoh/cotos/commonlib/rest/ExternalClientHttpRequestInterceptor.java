package jp.co.ricoh.cotos.commonlib.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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

import jp.co.ricoh.cotos.commonlib.log.LogUtil;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogRequestProperties;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogResponseProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExternalClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Log log = LogFactory.getLog(ExternalClientHttpRequestInterceptor.class);

	@Autowired
	MessageUtil messageUtil;

	@Autowired
	LogUtil logUtil;

	@Autowired
	ExternalLogRequestProperties externalLogRequestProperties;

	@Autowired
	ExternalLogResponseProperties externalLogResponseProperties;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		if (externalLogRequestProperties.isOutputLog()) {
			logRequest(request, body);
		}
		ClientHttpResponse response = new BufferingClientHttpResponseWrapper(execution.execute(request, body));
		if (externalLogResponseProperties.isOutputLog()) {
			logResponse(response);
		}
		return response;
	}

	private void logRequest(HttpRequest request, byte[] body) {
		String bodyStr = "";
		if (body.length > 0) {
			bodyStr = logUtil.outputLog(body);
		}
		List<Object> regexList = Arrays.asList(request.getMethod().toString(), request.getURI().toString(), request.getHeaders().toString(), bodyStr, formatter.format(LocalDateTime.now()));
		log.info(messageUtil.createMessageInfo("ExternalApiRequestLogInfo", regexList.toArray(new String[0])).getMsg());
	}

	private void logResponse(ClientHttpResponse response) throws IOException {
		String body = "";
		if (logUtil.isOutputBody(response)) {
			try {
				log.info(response.getBody().available());
				body = logUtil.outputLog(StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
			} catch (IOException e) {
				log.warn("想定外のエラーによりResponseBodyを取得できませんでした。");
				return;
			}
		}
		List<Object> regexList = Arrays.asList(String.valueOf(response.getStatusCode().value()), response.getHeaders().toString(), body, formatter.format(LocalDateTime.now()));
		log.info(messageUtil.createMessageInfo("ExternalApiResponseLogInfo", regexList.toArray(new String[0])).getMsg());
	}

	private static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

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
