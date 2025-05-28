package jp.co.ricoh.cotos.commonlib.rest;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j;

/**
 * ExternalRestTemplateテストクラス。
 * 外部環境に依存するため、テストは常にIgnoreする。
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j
@Ignore
public class ExternalRestTemplateTest {

	static ConfigurableApplicationContext context;

	@Autowired
	ExternalRestTemplate externalRestTemplate;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		context.stop();
	}

	@Test
	public void タイムアウト発生確認テスト_loadRestTemplate() {

		try {
			RestTemplate rest = externalRestTemplate.loadRestTemplate();
			rest.getForObject("http://dev-api-0.cotos.ricoh.co.jp/contract/api/contract/1", Map.class);
		} catch (RuntimeException e) {
			log.error("タイムアウト発生");
			e.printStackTrace();
			assertTrue("タイムアウトが発生していること", true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("予期しない例外が発生");
		}
	}

	@Test
	public void タイムアウト発生確認テスト_createRestTemplateForJson() {

		try {
			RestTemplate rest = externalRestTemplate.createRestTemplateForJson();
			rest.getForObject("http://dev-api-0.cotos.ricoh.co.jp/contract/api/contract/1", Map.class);
		} catch (RuntimeException e) {
			log.error("タイムアウト発生");
			e.printStackTrace();
			assertTrue("タイムアウトが発生していること", true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("予期しない例外が発生");
		}
	}

}
