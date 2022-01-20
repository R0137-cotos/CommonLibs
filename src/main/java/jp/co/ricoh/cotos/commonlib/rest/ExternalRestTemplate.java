package jp.co.ricoh.cotos.commonlib.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalRestTemplate {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Autowired
	ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor;

	public RestTemplate loadRestTemplate() {
		return createRestTemplate();
	}

	private RestTemplate createRestTemplate() {
		RestTemplate rest = restTemplateBuilder.build();
		List<ClientHttpRequestInterceptor> interceptors = rest.getInterceptors();
		addInterceptor(interceptors, externalClientHttpRequestInterceptor);
		return rest;
	}

	private List<ClientHttpRequestInterceptor> addInterceptor(List<ClientHttpRequestInterceptor> interceptors, ClientHttpRequestInterceptor interceptor) {
		interceptors.add(interceptor);
		return interceptors;
	}
}
