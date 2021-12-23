package jp.co.ricoh.cotos.commonlib.repository;

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
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseAccount;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfoOperationLog;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseKeyInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseAccountRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoOperationLogRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseKeyInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseProcessRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseRemainingNumberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestLicense {

	@Autowired
	LicenseDetailRepository licenseDetailRepository;

	@Autowired
	LicenseInfoOperationLogRepository licenseInfoOperationLogRepository;

	@Autowired
	LicenseInfoRepository licenseInfoRepository;

	@Autowired
	LicenseProcessRepository licenseProcessRepository;

	@Autowired
	LicenseRemainingNumberRepository licenseRemainingNumberRepository;

	@Autowired
	LicenseAccountRepository licenseAccountRepository;

	@Autowired
	LicenseKeyInfoRepository licenseKeyInfoRepository;

	@Autowired
	TestTools testTool;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/license.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void LicenseDetailRepositoryのテスト() throws Exception {

		LicenseDetail found = licenseDetailRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseInfoOperationLogRepositoryのテスト() throws Exception {

		LicenseInfoOperationLog found = licenseInfoOperationLogRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseInfoRepositoryのテスト() throws Exception {

		LicenseInfo found = licenseInfoRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseProcessRepositoryのテスト() throws Exception {

		LicenseProcess found = licenseProcessRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseRemainingNumberのテスト() throws Exception {

		LicenseRemainingNumber found = licenseRemainingNumberRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseRemainingNumberのみテスト() throws Exception {

		LicenseRemainingNumber found = licenseRemainingNumberRepository.findOne(2L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void LicenseAccountRepositoryのテスト() throws Exception {

		LicenseAccount found = licenseAccountRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseKeyInfoRepositoryのテスト() throws Exception {

		LicenseKeyInfo found = licenseKeyInfoRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}
}
