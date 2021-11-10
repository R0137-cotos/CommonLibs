package jp.co.ricoh.cotos.commonlib.util;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestRsiProperties {

	static ConfigurableApplicationContext context;

	@Autowired
	RsiProperties rsiProperties;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void RSIのプロパティが取得できること() {
		Assert.assertEquals("RSI 契約管理APIのベースURLが取得できること", rsiProperties.getContractUrl(), "dummy_rsi_contract_url");
		Assert.assertEquals("RSI 契約管理APIのユーザー認証用URLが取得できること", rsiProperties.getAuthUrl(), "dummy_rsi_auth_url");
		Assert.assertEquals("RSI 契約管理APIのユーザー認証用ユーザーIDが取得できること", rsiProperties.getUserId(), "dummy_rsi_user_id");
		Assert.assertEquals("RSI 契約管理APIのユーザー認証用パスワードが取得できること", rsiProperties.getUserPassword(), "dummy_rsi_user_password");
		Assert.assertEquals("RSI 契約管理APIのユーザー認証用テナントIDが取得できること", rsiProperties.getUserTenantId(), "dummy_rsi_user_tenantId");
		Assert.assertEquals("RSI 契約管理APIのアプリケーション認証用ユーザーIDが取得できること", rsiProperties.getAppUserId(), "dummy_rsi_app_user_id");
		Assert.assertEquals("RSI 契約管理APIのアプリケーション認証用パスワードが取得できること", rsiProperties.getAppUserPassword(), "dummy_rsi_app_user_password");
	}
}
