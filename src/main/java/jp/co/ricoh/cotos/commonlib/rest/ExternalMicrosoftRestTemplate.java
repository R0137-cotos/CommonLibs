package jp.co.ricoh.cotos.commonlib.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExternalMicrosoftRestTemplate extends ExternalRestTemplate {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Autowired
	ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor;

	/**
	 * JSONで通信を行う際、一般的な設定をした{@link RestTemplate}を返却する<br>
	 * MicrosoftのREST API実行用の設定
	 * @param interceptor インターセプトを行う場合は設定する。不要時はnull
	 * @return
	 */
	public RestTemplate createRestTemplateForJson(ClientHttpRequestInterceptor interceptor) {
		RestTemplate rest = super.createRestTemplateForJson(interceptor);

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setReadTimeout(180000);
		rest.setRequestFactory(requestFactory);
		return rest;
	}
}
