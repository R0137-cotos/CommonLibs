package jp.co.ricoh.cotos.commonlib.rest;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExternalRestTemplate {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Autowired
	ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor;

	public void setRestTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplateBuilder = restTemplateBuilder;
	}

	public void setExternalClientHttpRequestInterceptor(ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor) {
		this.externalClientHttpRequestInterceptor = externalClientHttpRequestInterceptor;
	}

	public RestTemplate loadRestTemplate() {
		return createRestTemplate();
	}

	public RestTemplate loadRestTemplate(ClientHttpRequestInterceptor interceptor) {
		return createRestTemplate(interceptor);
	}

	private RestTemplate createRestTemplate() {
		RestTemplate rest = restTemplateBuilder.build();
		rest.setInterceptors(Stream.concat(rest.getInterceptors().stream(), Arrays.asList(externalClientHttpRequestInterceptor).stream()).collect(Collectors.toList()));
		return rest;
	}

	private RestTemplate createRestTemplate(ClientHttpRequestInterceptor interceptor) {
		RestTemplate rest = restTemplateBuilder.build();
		rest.setInterceptors(Stream.concat(rest.getInterceptors().stream(), Arrays.asList(interceptor, externalClientHttpRequestInterceptor).stream()).collect(Collectors.toList()));
		return rest;
	}
}
