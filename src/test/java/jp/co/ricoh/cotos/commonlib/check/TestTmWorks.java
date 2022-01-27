package jp.co.ricoh.cotos.commonlib.check;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.TestTools.ParameterErrorIds;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmLinkManagement;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserResponseWork;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmCreateCustomerRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmCreateCustomerResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmCreateSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmCreateSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmLinkManagementRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmSuspendSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmSuspendSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmUpdateCustomerRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmUpdateCustomerResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmUpdateSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmUpdateSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmUpdateUserRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.cas.tm.TmUpdateUserResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.security.TestSecurityController;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;
import lombok.val;

/**
 * パラメータチェック（トレンドマイクロ連携各WORKテーブル）のテストクラス

 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestTmWorks {

	private static final String STR_256 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	private static final int INT_MINUS_1 = -1;

	static ConfigurableApplicationContext context;

	@Autowired
	HeadersProperties headersProperties;

	@Autowired
	TmLinkManagementRepository tmLinkManagementRepository;

	@Autowired
	TmCreateCustomerRequestWorkRepository tmCreateCustomerRequestWorkRepository;

	@Autowired
	TmCreateCustomerResponseWorkRepository tmCreateCustomerResponseWorkRepository;

	@Autowired
	TmCreateSubscriptionRequestWorkRepository tmCreateSubscriptionRequestWorkRepository;

	@Autowired
	TmCreateSubscriptionResponseWorkRepository tmCreateSubscriptionResponseWorkRepository;

	@Autowired
	TmSuspendSubscriptionRequestWorkRepository tmSuspendSubscriptionRequestWorkRepository;

	@Autowired
	TmSuspendSubscriptionResponseWorkRepository tmSuspendSubscriptionResponseWorkRepository;

	@Autowired
	TmUpdateCustomerRequestWorkRepository tmUpdateCustomerRequestWorkRepository;

	@Autowired
	TmUpdateCustomerResponseWorkRepository tmUpdateCustomerResponseWorkRepository;

	@Autowired
	TmUpdateSubscriptionRequestWorkRepository tmUpdateSubscriptionRequestWorkRepository;

	@Autowired
	TmUpdateSubscriptionResponseWorkRepository tmUpdateSubscriptionResponseWorkRepository;

	@Autowired
	TmUpdateUserRequestWorkRepository tmUpdateUserRequestWorkRepository;

	@Autowired
	TmUpdateUserResponseWorkRepository tmUpdateUserResponseWorkRepository;

	@Autowired
	TestTools testTool;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/license/cas/tm/tmWorks.sql");
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

	/**
	 * 個別にテストを流すとデータのInsert/Deleteに時間がかかるので、
	 * 一つのテストメソッドで実施する。
	 */
	@Test
	public void 各Repositoryのテスト() {
		TmLinkManagementRepositoryのテスト();
		TmCreateCustomerRequestWorkRepositoryのテスト();
		TmCreateCustomerResponseWorkRepositoryのテスト();
		TmCreateSubscriptionRequestWorkRepositoryのテスト();
		TmCreateSubscriptionResponseWorkRepositoryのテスト();
		TmSuspendSubscriptionRequestWorkRepositoryのテスト();
		TmSuspendSubscriptionResponseWorkRepositoryのテスト();
		TmUpdateCustomerRequestWorkRepositoryのテスト();
		TmUpdateCustomerResponseWorkRepositoryのテスト();
		TmUpdateSubscriptionRequestWorkRepositoryのテスト();
		TmUpdateSubscriptionResponseWorkRepositoryのテスト();
		TmUpdateUserRequestWorkRepositoryのテスト();
		TmUpdateUserResponseWorkRepositoryのテスト();
	}

	private void TmUpdateUserResponseWorkRepositoryのテスト() {
		val entity = tmUpdateUserResponseWorkRepository.findOne(10L);
		val testTarget = new TmUpdateUserResponseWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setUserId(STR_256);
		testTarget.setFirstName(STR_256);
		testTarget.setLastName(STR_256);
		testTarget.setEmail(STR_256);
		testTarget.setTimeZone(STR_256);
		testTarget.setLanguage(STR_256);
		testTarget.setPhoneAreaCode(STR_256);
		testTarget.setPhoneNumber(STR_256);
		testTarget.setPhoneExtension(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 9);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "担当者名は最大文字数（255）を超えています。"));

	}

	private void TmUpdateSubscriptionResponseWorkRepositoryのテスト() {

		val entity = tmUpdateSubscriptionResponseWorkRepository.findOne(10L);
		val testTarget = new TmUpdateSubscriptionResponseWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setSubscriptionId(STR_256);
		testTarget.setServiceUrl(STR_256);
		testTarget.setProductId(STR_256);
		testTarget.setAcCode(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "サブスクリプションIDは最大文字数（255）を超えています。"));
	}

	private void TmUpdateUserRequestWorkRepositoryのテスト() {

		val entity = tmUpdateUserRequestWorkRepository.findOne(10L);
		val testTarget = new TmUpdateUserRequestWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setEmail(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "メールアドレスは最大文字数（255）を超えています。"));

	}

	private void TmUpdateSubscriptionRequestWorkRepositoryのテスト() {

		val entity = tmUpdateSubscriptionRequestWorkRepository.findOne(10L);
		val testTarget = new TmUpdateSubscriptionRequestWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setCustomerId(STR_256);
		testTarget.setSubscriptionId(STR_256);
		testTarget.setUnitsPerLicense(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "サブスクリプションIDは最大文字数（255）を超えています。"));

	}

	private void TmUpdateCustomerRequestWorkRepositoryのテスト() {

		val entity = tmUpdateCustomerRequestWorkRepository.findOne(10L);
		val testTarget = new TmUpdateCustomerRequestWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setName(STR_256);
		testTarget.setState(STR_256);
		testTarget.setCountry(STR_256);
		testTarget.setCity(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "市区町村は最大文字数（255）を超えています。"));

	}

	private void TmUpdateCustomerResponseWorkRepositoryのテスト() {

		val entity = tmUpdateCustomerResponseWorkRepository.findOne(10L);
		val testTarget = new TmUpdateCustomerResponseWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setName(STR_256);
		testTarget.setState(STR_256);
		testTarget.setCountry(STR_256);
		testTarget.setCity(STR_256);
		testTarget.setAddress(STR_256);
		testTarget.setPostalCode(STR_256);
		testTarget.setNote(STR_256);
		testTarget.setEmergencyEmail(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 8);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "国は最大文字数（255）を超えています。"));

	}

	private void TmSuspendSubscriptionResponseWorkRepositoryのテスト() {

		val entity = tmSuspendSubscriptionResponseWorkRepository.findOne(10L);
		val testTarget = new TmSuspendSubscriptionResponseWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setHttpStatus(STR_256);
		testTarget.setErrorMessage(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "HTTPステータスは最大文字数（255）を超えています。"));

	}

	private void TmSuspendSubscriptionRequestWorkRepositoryのテスト() {

		val entity = tmSuspendSubscriptionRequestWorkRepository.findOne(10L);
		val testTarget = new TmSuspendSubscriptionRequestWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setCustomerId(STR_256);
		testTarget.setSubscriptionId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "サブスクリプションIDは最大文字数（255）を超えています。"));

	}

	private void TmCreateSubscriptionRequestWorkRepositoryのテスト() {

		val entity = tmCreateSubscriptionRequestWorkRepository.findOne(10L);
		val testTarget = new TmCreateSubscriptionRequestWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setCustomerId(STR_256);
		testTarget.setServicePlanId(STR_256);
		testTarget.setUnitsPerLicense(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ユニット数は最大文字数（255）を超えています。"));

	}

	private void TmCreateSubscriptionResponseWorkRepositoryのテスト() {

		val entity = tmCreateSubscriptionResponseWorkRepository.findOne(10L);
		val testTarget = new TmCreateSubscriptionResponseWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setSubscriptionId(STR_256);
		testTarget.setProductName(STR_256);
		testTarget.setServiceUrl(STR_256);
		testTarget.setProductId(STR_256);
		testTarget.setVersionlicenceVersion(STR_256);
		testTarget.setUnits(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 6);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "シート数は最大文字数（255）を超えています。"));

	}

	private void TmCreateCustomerResponseWorkRepositoryのテスト() {

		val entity = tmCreateCustomerResponseWorkRepository.findOne(10L);
		val testTarget = new TmCreateCustomerResponseWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setCompanyId(STR_256);
		testTarget.setUserId(STR_256);
		testTarget.setUserLoginName(STR_256);
		testTarget.setUserPassword(STR_256);
		testTarget.setUserResetpasswordurl(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ログインアカウントは最大文字数（255）を超えています。"));

	}

	private void TmCreateCustomerRequestWorkRepositoryのテスト() {

		val entity = tmCreateCustomerRequestWorkRepository.findOne(10L);
		val testTarget = new TmCreateCustomerRequestWork();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setCompanyName(STR_256);
		testTarget.setCompanyState(STR_256);
		testTarget.setCompanyCountry(STR_256);
		testTarget.setUserLoginName(STR_256);
		testTarget.setUserFirstName(STR_256);
		testTarget.setUserLastName(STR_256);
		testTarget.setUserEmail(STR_256);
		testTarget.setUserTimeZone(STR_256);
		testTarget.setUserLanguage(STR_256);
		testTarget.setCompanyCity(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 10);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "市区町村は最大文字数（255）を超えています。"));

	}

	private void TmLinkManagementRepositoryのテスト() {

		val entity = tmLinkManagementRepository.findOne(10L);
		val testTarget = new TmLinkManagement();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMvbAccount(STR_256);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setMomCompanyId(STR_256);
		testTarget.setMomCustId(STR_256);
		testTarget.setCompanyNameKana(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MVBアカウントは最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setArrangementWorkId(INT_MINUS_1);
		testTarget.setContractId(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約IDは最小値（0）を下回っています。"));

	}
}
