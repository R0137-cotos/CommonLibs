package jp.co.ricoh.cotos.commonlib.repository;

import java.util.List;

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
import jp.co.ricoh.cotos.commonlib.entity.license.CloudEdgeAccountInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseAccount;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetailRefreshHis;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfoOperationLog;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfoRefreshHis;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseKeyInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber;
import jp.co.ricoh.cotos.commonlib.repository.license.CloudEdgeAccountInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseAccountRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseDetailRefreshHisRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoOperationLogRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoRefreshHisRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseKeyInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseProcessRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseRemainingNumberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
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
	LicenseInfoRefreshHisRepository licenseInfoRefreshHisRepository;

	@Autowired
	LicenseDetailRefreshHisRepository licenseDetailRefreshHisRepository;

	@Autowired
	CloudEdgeAccountInfoRepository cloudEdgeAccountInfoRepository;

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

		LicenseDetail found = licenseDetailRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseInfoOperationLogRepositoryのテスト() throws Exception {

		LicenseInfoOperationLog found = licenseInfoOperationLogRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseInfoRepositoryのテスト() throws Exception {

		LicenseInfo found = licenseInfoRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseProcessRepositoryのテスト() throws Exception {

		LicenseProcess found = licenseProcessRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseRemainingNumberのテスト() throws Exception {

		LicenseRemainingNumber found = licenseRemainingNumberRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseRemainingNumberのみテスト() throws Exception {

		LicenseRemainingNumber found = licenseRemainingNumberRepository.findById(2L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void LicenseAccountRepositoryのテスト() throws Exception {

		LicenseAccount found = licenseAccountRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseKeyInfoRepositoryのテスト() throws Exception {

		LicenseKeyInfo found = licenseKeyInfoRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		List<LicenseKeyInfo> list = licenseKeyInfoRepository.findByLicenseAccountId(1L);
		Assert.assertTrue(list.size() != 0);
	}

	@Test
	public void LicenseInfoRefreshHisRepositoryのテスト() throws Exception {

		LicenseInfoRefreshHis found = licenseInfoRefreshHisRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void licenseDetailRefreshHisRepositoryのテスト() throws Exception {

		LicenseDetailRefreshHis found = licenseDetailRefreshHisRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void CloudEdgeAccountInfoRepositoryのテスト() throws Exception {

		CloudEdgeAccountInfo found = cloudEdgeAccountInfoRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

}
