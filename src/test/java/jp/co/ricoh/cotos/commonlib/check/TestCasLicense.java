package jp.co.ricoh.cotos.commonlib.check;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.TestTools.ParameterErrorIds;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseBasicInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseDetailInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseManagementInfo;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.CasLicenseBasicInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.CasLicenseDetailInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.CasLicenseManagementInfoRepository;
import jp.co.ricoh.cotos.commonlib.security.TestSecurityController;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;

/**
 * パラメータチェック（ライセンスドメイン:CAS）のテストクラス

 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "test.context.id = TestCasLicense")
public class TestCasLicense {

	private static final String STR_19 = "1234567890123456789";
	private static final String STR_101 = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";
	private static final String STR_151 = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";
	private static final String STR_256 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	private static final String STR_301 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
	private static final String STR_754 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234";
	private static final int INT_MINUS_1 = -1;
	private static final int INT_100 = 100;
	private static final int INT_100000 = 100000;

	static ConfigurableApplicationContext context;

	@Autowired
	HeadersProperties headersProperties;

	@Autowired
	CasLicenseBasicInfoRepository casLicenseBasicInfoRepository;

	@Autowired
	CasLicenseManagementInfoRepository casLicenseManagementInfoRepository;

	@Autowired
	CasLicenseDetailInfoRepository casLicenseDetailInfoRepository;

	@Autowired
	TestTools testTool;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/license/cas/casLicense.sql");
		//		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		//		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		//		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		//		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		//		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		//		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		//		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");
	}

	@Autowired
	TestSecurityController testSecurityController;

	@LocalServerPort
	private int localServerPort;

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void CasLicenseBasicInfoRepositoryのテスト() throws Exception {

		CasLicenseBasicInfo entity = casLicenseBasicInfoRepository.findById(9999L).get();
		CasLicenseBasicInfo testTarget = new CasLicenseBasicInfo();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setCustomerId(STR_256);
		testTarget.setTrendUserId(STR_256);
		testTarget.setMvbAccount(STR_19);
		testTarget.setMomCompanyId(STR_256);
		testTarget.setPicNameSei(STR_301);
		testTarget.setPicNameMei(STR_301);
		testTarget.setPicMailAddress(STR_101);
		testTarget.setCompanyName(STR_754);
		testTarget.setPrefectures(STR_151);
		testTarget.setMunicipality(STR_151);
		testTarget.setInitialPassword(STR_151);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 11);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "カスタマーIDは最大文字数（255）を超えています。"));

		// 異常系（@Valid：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.getCasLicenseManagementInfoList().get(0).setRjManageNumber(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "RJ管理番号は最大文字数（255）を超えています。"));

	}

	@Test
	public void CasLicenseManagementInfoのテスト() throws Exception {

		CasLicenseManagementInfo entity = casLicenseManagementInfoRepository.findById(9999L).get();
		CasLicenseManagementInfo testTarget = new CasLicenseManagementInfo();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setContractNumber(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "RJ管理番号は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setContractBranchNumber(INT_100);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "文書番号枝番は最大値（99）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setContractId(INT_MINUS_1);
		testTarget.setContractBranchNumber(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約IDは最小値（0）を下回っています。"));

	}

	@Test
	public void CasLicenseDetailInfoのテスト() throws Exception {

		CasLicenseDetailInfo entity = casLicenseDetailInfoRepository.findById(9999L).get();
		CasLicenseDetailInfo testTarget = new CasLicenseDetailInfo();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setSubscriptionId(STR_256);
		testTarget.setServicePlanId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "サブスクリプションIDは最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setQuantity(INT_100000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最大値（99999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setQuantity(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最小値（0）を下回っています。"));

	}

}
