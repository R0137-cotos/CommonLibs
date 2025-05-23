package jp.co.ricoh.cotos.commonlib.check;

import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.TestTools.ParameterErrorIds;
import jp.co.ricoh.cotos.commonlib.dto.parameter.master.AttachedFileProductClassCheckMasterSearchParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.master.AttachedFileProductGrpCheckMasterSearchParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.master.JsonSchemaMasterParameter;
import jp.co.ricoh.cotos.commonlib.security.TestSecurityController;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "test.context.id = TestMasterDto")
public class TestMasterDto {

	private static final String STR_256 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	private static final String STR_1001 = "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
	private static final int INT_MINUS_1 = -1;
	private static final int INT_10 = 10;

	@Autowired
	HeadersProperties headersProperties;

	@Autowired
	TestSecurityController testSecurityController;

	@Autowired
	TestTools testTool;

	@LocalServerPort
	private int localServerPort;

	@Test
	public void JsonSchemaMasterParameterのテスト() {

		JsonSchemaMasterParameter testTarget = new JsonSchemaMasterParameter();

		// 正常系
		testTarget.setProductMasterId(1L);
		testTarget.setEstimationType("1");
		testTarget.setContractType("2");
		testTarget.setLifecycleStatus("1");

		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系
		testTarget.setEstimationType(STR_256);
		testTarget.setContractType(STR_256);
		testTarget.setLifecycleStatus(STR_256);

		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);

	}

	@Test
	public void AttachedFileProductClassCheckMasterSearchParameterのテスト() throws Exception {

		AttachedFileProductClassCheckMasterSearchParameter dto = new AttachedFileProductClassCheckMasterSearchParameter();

		dto.setProductGrpMasterId(1L);
		dto.setProductClassDiv("csp");
		dto.setDomain("1");
		dto.setEstimationContractType("1");
		dto.setLifecycleStatus("1");
		dto.setArrangementWorkTypeMasterId(1L);
		dto.setVupLinkageCheckExcludeFlg(1);

		AttachedFileProductClassCheckMasterSearchParameter testTarget = new AttachedFileProductClassCheckMasterSearchParameter();
		BeanUtils.copyProperties(testTarget, dto);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setProductGrpMasterId(null);
		testTarget.setProductClassDiv(null);
		testTarget.setDomain(null);
		testTarget.setEstimationContractType(null);
		testTarget.setLifecycleStatus(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品グループマスタIDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setProductClassDiv(STR_256);
		testTarget.setDomain(STR_256);
		testTarget.setEstimationContractType(STR_256);
		testTarget.setLifecycleStatus(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品種類区分は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setVupLinkageCheckExcludeFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "V-UP連携チェック除外フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setVupLinkageCheckExcludeFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "V-UP連携チェック除外フラグは最小値（0）を下回っています。"));

	}

	@Test
	public void AttachedFileProductGrpCheckMasterSearchParameterのテスト() throws Exception {

		AttachedFileProductGrpCheckMasterSearchParameter dto = new AttachedFileProductGrpCheckMasterSearchParameter();

		dto.setProductGrpMasterId(1L);
		dto.setDomain("1");
		dto.setEstimationContractType("1");
		dto.setLifecycleStatus("1");
		dto.setItemMasterIdList(Arrays.asList(1L, 2L));
		dto.setArrangementWorkTypeMasterId(1L);
		dto.setVupLinkageCheckExcludeFlg(1);

		AttachedFileProductGrpCheckMasterSearchParameter testTarget = new AttachedFileProductGrpCheckMasterSearchParameter();
		BeanUtils.copyProperties(testTarget, dto);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setProductGrpMasterId(null);
		testTarget.setDomain(null);
		testTarget.setEstimationContractType(null);
		testTarget.setLifecycleStatus(null);
		testTarget.setItemMasterIdList(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品グループマスタIDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setDomain(STR_256);
		testTarget.setEstimationContractType(STR_256);
		testTarget.setLifecycleStatus(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ドメインは最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setVupLinkageCheckExcludeFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "V-UP連携チェック除外フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setVupLinkageCheckExcludeFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "V-UP連携チェック除外フラグは最小値（0）を下回っています。"));

	}
}