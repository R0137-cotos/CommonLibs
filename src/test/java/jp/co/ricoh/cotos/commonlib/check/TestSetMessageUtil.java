package jp.co.ricoh.cotos.commonlib.check;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;

/**
 * フォーマット変換確認メソッドのテストクラス
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSetMessageUtil {

	@Value("${spring.messages.basename}")
	String basename;
	@Value("${spring.messages.encoding}")
	String encoding;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.stop();
		}
	}

	@Test
	public void エラーが発生しないこと() {
		try {
			CheckUtil checkUtil = new CheckUtil();
			checkUtil.setMessageUtil(basename, encoding);
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
