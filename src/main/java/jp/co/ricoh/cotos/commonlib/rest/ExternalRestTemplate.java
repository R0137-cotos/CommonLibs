package jp.co.ricoh.cotos.commonlib.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 外部システム連携用RestTemplate
 * タイムアウト時間が適合しない場合は、当クラスを継承しcreateRequestFactoryのタイムアウト時間を調整すること
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExternalRestTemplate {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Autowired
	ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor;

	public RestTemplate loadRestTemplate() {
		return createRestTemplate();
	}

	public RestTemplate loadRestTemplate(ClientHttpRequestInterceptor interceptor) {
		return createRestTemplate(interceptor);
	}

	private RestTemplate createRestTemplate() {
		RestTemplate rest = restTemplateBuilder.build();
		rest.setInterceptors(Stream.concat(rest.getInterceptors().stream(), Arrays.asList(externalClientHttpRequestInterceptor).stream()).collect(Collectors.toList()));
		rest.setRequestFactory(createRequestFactory());
		return rest;
	}

	private RestTemplate createRestTemplate(ClientHttpRequestInterceptor interceptor) {
		RestTemplate rest = restTemplateBuilder.build();
		rest.setInterceptors(Stream.concat(rest.getInterceptors().stream(), Arrays.asList(interceptor, externalClientHttpRequestInterceptor).stream()).collect(Collectors.toList()));
		rest.setRequestFactory(createRequestFactory());
		return rest;
	}

	/**
	 * JSONで通信を行う際、一般的な設定をした{@link RestTemplate}を返却する<br>
	 * Intra-martとTrendMicroの処理で同様の内容を実施している
	 * @param interceptor インターセプトを行う場合は設定する。不要時はnull
	 * @return
	 * @see jp.co.ricoh.cotos.commonlib.logic.trendmicro.LMPIConnectionHelper#init(ApplicationContext, ExternalRestTemplate)
	 */
	public RestTemplate createRestTemplateForJson(ClientHttpRequestInterceptor interceptor) {
		RestTemplate rest;
		if (interceptor == null) {
			rest = createRestTemplate();
		} else {
			rest = createRestTemplate(interceptor);
		}
		rest.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				// 何も書かないことで、サーバーエラーとクライアントエラーが起きても例外を発生させずにBodyにエラーメッセージを返す。
			}
		});
		// HTTPのBODY（Json形式）をJAVAのDTOに変換するためのコンバーター
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
		rest.getMessageConverters().add(mappingJackson2HttpMessageConverter);

		SimpleClientHttpRequestFactory requestFactory = createRequestFactory();
		rest.setRequestFactory(requestFactory);

		return rest;
	}

	/**
	 * JSONで通信を行う際、<b>インターセプトなし</b>の一般的な設定をした{@link RestTemplate}を返却する
	 * @return
	 */
	public RestTemplate createRestTemplateForJson() {
		return createRestTemplateForJson(null);
	}

	/**
	 * SimpleClientHttpRequestFactory生成
	 * @return SimpleClientHttpRequestFactory
	 */
	protected SimpleClientHttpRequestFactory createRequestFactory() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setReadTimeout(30000);

		return requestFactory;
	}
}
