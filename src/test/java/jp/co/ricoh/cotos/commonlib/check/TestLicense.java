package jp.co.ricoh.cotos.commonlib.check;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.TestTools.ParameterErrorIds;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfoOperationLog;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoOperationLogRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseProcessRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseRemainingNumberRepository;
import jp.co.ricoh.cotos.commonlib.security.TestSecurityController;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;

/**
 * パラメータチェック（ライセンスドメイン）のテストクラス
 *
 * @author kentaro.kakuhana
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestLicense {

	private static final String STR_256 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	private static final int INT_MINUS_100000 = -100000;
	private static final int INT_MINUS_1 = -1;
	private static final int INT_10 = 10;
	private static final int INT_100 = 100;
	private static final int INT_1000 = 1000;
	private static final int INT_100000 = 100000;

	static ConfigurableApplicationContext context;

	@Autowired
	HeadersProperties headersProperties;

	@Autowired
	LicenseInfoRepository licenseInfoRepository;

	@Autowired
	LicenseRemainingNumberRepository licenseRemainingNumberRepository;

	@Autowired
	LicenseInfoOperationLogRepository licenseInfoOperationLogRepository;

	@Autowired
	LicenseProcessRepository licenseProcessRepository;

	@Autowired
	LicenseDetailRepository licenseDetailRepository;

	@Autowired
	TestTools testTool;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/license.sql");
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
	public void LicenseInfoのテスト() throws Exception {
		LicenseInfo entity = licenseInfoRepository.findById(1L).get();
		LicenseInfo testTarget = new LicenseInfo();

		// 正常系
		BeanUtils.copyProperties(testTarget, entity);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setImmutableContIdentNumber(STR_256);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setContractNumber(STR_256);
		testTarget.setContractTypeDetail(STR_256);
		testTarget.setMailAddress(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約番号は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setContractBranchNumber(INT_100);
		testTarget.setDisengagementFlg(INT_10);
		testTarget.setCompleteProcessOrder(INT_1000);
		testTarget.setWorkingProcessOrder(INT_1000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "文書番号枝番は最大値（99）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setContractId((long) INT_MINUS_1);
		testTarget.setContractBranchNumber(INT_MINUS_1);
		testTarget.setDisengagementFlg(INT_MINUS_1);
		testTarget.setLicenseDivMasterId((long) INT_MINUS_1);
		testTarget.setCompleteProcessOrder(INT_MINUS_1);
		testTarget.setCompleteProcessMasterId((long) INT_MINUS_1);
		testTarget.setCompleteArrangementWorkId((long) INT_MINUS_1);
		testTarget.setWorkingProcessOrder(INT_MINUS_1);
		testTarget.setWorkingProcessMasterId((long) INT_MINUS_1);
		testTarget.setWorkingArrangementWorkId((long) INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 10);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約IDは最小値（0）を下回っています。"));
	}

	@Test
	public void LicenseRemainingNumberのテスト() throws Exception {
		LicenseRemainingNumber entity = licenseRemainingNumberRepository.findById(1L).get();
		LicenseRemainingNumber testTarget = new LicenseRemainingNumber();

		// 正常系
		BeanUtils.copyProperties(testTarget, entity);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setLicenseKey(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ライセンスキーが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setLicenseKey(STR_256);
		testTarget.setImmutableContIdentNumber(STR_256);
		testTarget.setRjManageNumber(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ライセンスキーは最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setLicenseDivMasterId((long) INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ライセンス区分マスタIDは最小値（0）を下回っています。"));
	}

	@Test
	public void LicenseInfoOperationLogのテスト() throws Exception {
		LicenseInfoOperationLog entity = licenseInfoOperationLogRepository.findById(1L).get();
		LicenseInfoOperationLog testTarget = new LicenseInfoOperationLog();

		// 正常系
		BeanUtils.copyProperties(testTarget, entity);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setOperatorEmpId(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "操作者MoM社員IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setOperatorEmpId(STR_256);
		testTarget.setOperatorName(STR_256);
		testTarget.setOperatorOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "操作者氏名は最大文字数（255）を超えています。"));
	}

	@Test
	public void LicenseProcessのテスト() throws Exception {
		LicenseProcess entity = licenseProcessRepository.findById(1L).get();
		LicenseProcess testTarget = new LicenseProcess();

		// 正常系
		BeanUtils.copyProperties(testTarget, entity);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setOperationDiv(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "操作区分が設定されていません。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setProcessOrder(INT_1000);
		testTarget.setMailArrivalCheckFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "工程順は最大値（999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setProcessOrder(INT_MINUS_1);
		testTarget.setProcessMasterId((long) INT_MINUS_1);
		testTarget.setArrangementWorkId((long) INT_MINUS_1);
		testTarget.setMailArrivalCheckFlg(INT_MINUS_1);
		testTarget.setMailMasterId((long) INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "工程順は最小値（0）を下回っています。"));
	}

	@Test
	public void LicenseDetailのテスト() throws Exception {
		LicenseDetail entity = licenseDetailRepository.findById(1L).get();
		LicenseDetail testTarget = new LicenseDetail();

		// 正常系
		BeanUtils.copyProperties(testTarget, entity);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setEquipmentCode(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "機種コードは最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setQuantity(INT_100000);
		testTarget.setCaptureFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最大値（99999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setLicenseDivMasterId((long) INT_MINUS_1);
		testTarget.setSeqNumber((long) INT_MINUS_1);
		testTarget.setItemMasterId((long) INT_MINUS_1);
		testTarget.setProductMasterId((long) INT_MINUS_1);
		testTarget.setCaptureFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ライセンス区分マスタIDは最小値（0）を下回っています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setQuantity(INT_MINUS_100000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最小値（-99999）を下回っています。"));

	}
}
