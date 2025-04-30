package jp.co.ricoh.cotos.commonlib.check;

import java.util.ArrayList;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.TestTools.ParameterErrorIds;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.CasLicenseBasicInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.CasLicenseDetailInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.CasLicenseManagementInfoDto;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.CasLicenseStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseBasicInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseDetailInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseManagementInfo;
import jp.co.ricoh.cotos.commonlib.security.TestSecurityController;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;

/**
 * TestArrangementDtoのテストクラス
 *
 * @author kentaro.kakuhana
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestCasLicenseDto {

	private static final String STR_256 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	private static final int INT_MINUS_1 = -1;
	private static final int INT_100 = 100;
	private static final int INT_100000 = 100000;

	static ConfigurableApplicationContext context;

	@Autowired
	HeadersProperties headersProperties;

	@Autowired
	TestTools testTool;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
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
	public void CasLicenseBasicInfoDtoのテスト() throws Exception {

		CasLicenseBasicInfoDto dto = CasLicenseBasicInfoDto_full();
		CasLicenseBasicInfoDto testTarget = new CasLicenseBasicInfoDto();

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setCustomerId(STR_256);
		testTarget.setTrendUserId(STR_256);
		testTarget.setMvbAccount(STR_256);
		testTarget.setMomCompanyId(STR_256);
		testTarget.setPicNameSei(STR_256);
		testTarget.setPicNameMei(STR_256);
		testTarget.setPicMailAddress(STR_256);
		testTarget.setCompanyName(STR_256);
		testTarget.setPrefectures(STR_256);
		testTarget.setMunicipality(STR_256);
		testTarget.setInitialPassword(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 11);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "カスタマーIDは最大文字数（255）を超えています。"));

		// 異常系（@Valid：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getCasLicenseManagementInfoList().get(0).setRjManageNumber(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "RJ管理番号は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(CasLicenseBasicInfo.class, CasLicenseBasicInfoDto.class);
	}

	@Test
	public void CasLicenseManagementInfoDtoのテスト() throws Exception {

		CasLicenseManagementInfoDto dto = casLicenseManagementInfoDto_full();
		CasLicenseManagementInfoDto testTarget = new CasLicenseManagementInfoDto();

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setContractNumber(STR_256);
		testTarget.setMvbAccount(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "RJ管理番号は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setContractBranchNumber(INT_100);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "文書番号枝番は最大値（99）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setContractId(INT_MINUS_1);
		testTarget.setContractBranchNumber(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約IDは最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(CasLicenseManagementInfo.class, CasLicenseManagementInfoDto.class, "mvbAccount");
	}

	@Test
	public void CasLicenseDetailInfoDtoのテスト() throws Exception {

		CasLicenseDetailInfoDto dto = casLicenseDetailInfoDto_full();
		CasLicenseDetailInfoDto testTarget = new CasLicenseDetailInfoDto();

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setSubscriptionId(STR_256);
		testTarget.setServicePlanId(STR_256);
		testTarget.setMvbAccount(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "サブスクリプションIDは最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setQuantity(INT_100000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最大値（99999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setQuantity(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(CasLicenseDetailInfo.class, CasLicenseDetailInfoDto.class, "mvbAccount");
	}

	private CasLicenseBasicInfoDto CasLicenseBasicInfoDto_full() {

		CasLicenseBasicInfoDto ret = new CasLicenseBasicInfoDto();

		ret.setCustomerId("customerId");
		ret.setTrendUserId("trendUserId");
		ret.setMvbAccount("mvbAccount");
		ret.setLicenseStatus(CasLicenseStatus.未確定);
		ret.setMomCompanyId("momCompanyId");
		ret.setPicNameSei("picNameSei");
		ret.setPicNameMei("picNameMei");
		ret.setPicMailAddress("picMailAddress");
		ret.setCompanyName("companyName");
		ret.setPrefectures("prefectures");
		ret.setMunicipality("municipality");
		ret.setInitialPassword("initialPassword");
		ret.setCasLicenseManagementInfoList(new ArrayList<>());
		ret.getCasLicenseManagementInfoList().add(this.casLicenseManagementInfoDto_full());

		return ret;
	}

	private CasLicenseManagementInfoDto casLicenseManagementInfoDto_full() {

		CasLicenseManagementInfoDto ret = new CasLicenseManagementInfoDto();

		ret.setContractId(1L);
		ret.setRjManageNumber("rjManageNumber");
		ret.setContractNumber("contractNumber");
		ret.setContractBranchNumber(1);
		ret.setLastContractSyncDate(new Date());
		ret.setMvbAccount("mvbAccount");

		return ret;
	}

	private CasLicenseDetailInfoDto casLicenseDetailInfoDto_full() {

		CasLicenseDetailInfoDto ret = new CasLicenseDetailInfoDto();

		ret.setSubscriptionId("subscriptionId");
		ret.setServicePlanId("servicePlanId");
		ret.setLicenseStatus(CasLicenseStatus.未確定);
		ret.setLicenseTermStart(new Date());
		ret.setLicenseTermEnd(new Date());
		ret.setQuantity(1);
		ret.setMvbAccount("mvbAccount");

		return ret;
	}

}
