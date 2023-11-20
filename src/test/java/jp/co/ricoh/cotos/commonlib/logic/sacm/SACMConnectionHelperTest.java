package jp.co.ricoh.cotos.commonlib.logic.sacm;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.log.LogUtil;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.rest.ExternalClientHttpRequestInterceptor;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogRequestProperties;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogResponseProperties;
import jp.co.ricoh.cotos.commonlib.util.SACMProperties;
import lombok.extern.log4j.Log4j;

/**
 * SACM連携 ヘルパーテストクラス。
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Log4j
@Ignore
public class SACMConnectionHelperTest {

	static ConfigurableApplicationContext context;

	@Autowired
	private SACMProperties properties;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		context.stop();
	}

	private SACMConnectionHelper getHelper() {
		// ヘルパー初期化
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor = new ExternalClientHttpRequestInterceptor(new MessageUtil(), new LogUtil(), new ExternalLogRequestProperties(), new ExternalLogResponseProperties(), formatter);
		ExternalRestTemplate externalRestTemplate = new ExternalRestTemplate(new RestTemplateBuilder(), externalClientHttpRequestInterceptor);
		SACMConnectionHelper.init(context, externalRestTemplate);
		return SACMConnectionHelper.getInstance();
	}

	/**
	 *  [PUT] SACMサービスアダプタ情報更新API
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void putUpdateServiceAdapterInfoTest() throws ParseException {

		String userCode = "userCode";
		String saCode = "saCode";
		String name = "name";
		String distributionId = "distributionId";

		try {
			getHelper().putUpdateServiceAdapterInfo(userCode, saCode, name, distributionId);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@WithMockCustomUser
	public void タイムアウト発生確認テスト() {

		// 起動していないサービスのURLで実行しタイムアウトを発生させる
		String defaultUrl = properties.getApiUrl();
		String url = "http://dev-api-0.cotos.ricoh.co.jp/contract/api/contract/1";
		properties.setApiUrl(url);

		String userCode = "userCode";
		String saCode = "saCode";
		String name = "name";
		String distributionId = "distributionId";

		// 設定したタイムアウト発生秒数後にタイムアウトが発生することを確認する
		try {
			getHelper().putUpdateServiceAdapterInfo(userCode, saCode, name, distributionId);
			fail("タイムアウトが発生していない");
		} catch (RuntimeException e) {
			e.printStackTrace();
			assertTrue("タイムアウトが発生していること", true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("予期しない例外が発生");
		} finally {
			properties.setApiUrl(defaultUrl);
		}
	}

}
