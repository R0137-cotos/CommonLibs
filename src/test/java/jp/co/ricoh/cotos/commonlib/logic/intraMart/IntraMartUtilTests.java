package jp.co.ricoh.cotos.commonlib.logic.intraMart;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Ignore
public class IntraMartUtilTests {

	static ConfigurableApplicationContext context;

	@Autowired
	IntraMartUtil intraMartUtil;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		context.stop();
	}

	/**
	 *  [GET] 設定情報PDF参照API
	 */
	@Test
	public void intraMartAPI接続確認() {

		String url = "https://sld.imp.ricoh.co.jp/imsl/logic/api/ss_000172_fd204?rjManageNumber=rjManageNumber&contractNumber=contractNumber&contractBranchNumber=contractBranchNumber&whole_status=1";

		// 通信ができることのみ確認する。ステータスコードは確認しない
		try {
			intraMartUtil.callService(HttpMethod.GET, null, url, null);
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail("予期しない例外が発生");
		}
	}
}
