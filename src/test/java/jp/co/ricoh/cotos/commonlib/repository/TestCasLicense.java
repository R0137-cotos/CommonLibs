package jp.co.ricoh.cotos.commonlib.repository;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.CasLicenseDetailInfoRepository;

/**
 * トレンドマイクロ連携WORK
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCasLicense {

	static ConfigurableApplicationContext context;

	@Autowired
	TestTools testTool;

	@Autowired
	CasLicenseDetailInfoRepository casLicenseDetailInfoRepository;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/license/cas/casLicense.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void CasLicenseDetailInfoRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(casLicenseDetailInfoRepository.findBySubscriptionId("subscription_id").get(0));
	}

	/**
	 * 全てのカラムがNullではないことを確認
	 * @param <T>
	 * @param entity
	 */
	private <T extends EntityBase> void 全てのカラムがNullではないことを確認_共通(T entity) {
		try {
			testTool.assertColumnsNotNull(entity);
		} catch (Exception e1) {
			Assert.fail("例外が発生した場合、エラー");
		}
	}
}
