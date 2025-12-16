package jp.co.ricoh.cotos.commonlib.security;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCotosUserDetsilsService {

	@Autowired
	CotosUserDetailsService cotosUserDetailsService;

	static ConfigurableApplicationContext context;

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
	@WithMockCustomUser(momEmployeeId = "COTOS_BATCH_USER")
	public void 正常系_バッチユーザーを指定() {

		boolean result = cotosUserDetailsService.isBatchUser();

		Assert.assertTrue("trueが返ってくること", result);
	}

	@Test
	@WithMockCustomUser(momEmployeeId = "00500784")
	public void 正常系_バッチユーザー以外を指定() {

		boolean result = cotosUserDetailsService.isBatchUser();

		Assert.assertFalse("falseが返ってくること", result);
	}
}
