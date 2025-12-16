package jp.co.ricoh.cotos.commonlib.util;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRsiForJpdcProperties {

	static ConfigurableApplicationContext context;

	@Autowired
	RsiForJpdcProperties rsiForJpdcProperties;

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
	public void RsiForJpdcのプロパティが取得できること() {
		Assert.assertEquals("RSI 契約管理APIのベースURLが取得できること", rsiForJpdcProperties.getContractUrl(), "dummy_rsi_contract_url_for_jpdc");
		Assert.assertEquals("RSI 契約管理APIのユーザー認証用URLが取得できること", rsiForJpdcProperties.getAuthUrl(), "dummy_rsi_auth_url_for_jpdc");
		Assert.assertEquals("RSI 基本ユーザー情報APIのベースURLが取得できること", rsiForJpdcProperties.getBasicUserInfoUrl(), "dummy_rsi_basic_user_info_url_for_jpdc");
		Assert.assertEquals("RSI 契約管理APIのユーザー認証用ユーザーIDが取得できること", rsiForJpdcProperties.getUserId(), "dummy_rsi_user_id_for_jpdc");
		Assert.assertEquals("RSI 契約管理APIのユーザー認証用パスワードが取得できること", rsiForJpdcProperties.getUserPassword(), "dummy_rsi_user_password_for_jpdc");
		Assert.assertEquals("RSI 契約管理APIのユーザー認証用テナントIDが取得できること", rsiForJpdcProperties.getUserTenantId(), "dummy_rsi_user_tenantId_for_jpdc");
		Assert.assertEquals("RSI 契約管理APIのアプリケーション認証用ユーザーIDが取得できること", rsiForJpdcProperties.getAppUserId(), "dummy_rsi_app_user_id_for_jpdc");
		Assert.assertEquals("RSI 契約管理APIのアプリケーション認証用パスワードが取得できること", rsiForJpdcProperties.getAppUserPassword(), "dummy_rsi_app_user_password_for_jpdc");
	}
}
