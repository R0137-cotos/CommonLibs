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
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CheckResultUpdateParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CustomerAbstractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DealerAbstractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.EmployeeAbstractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.FileImportManagementParameter;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.common.CustomerAbstractEntity;
import jp.co.ricoh.cotos.commonlib.entity.common.DealerAbstractEntity;
import jp.co.ricoh.cotos.commonlib.entity.common.EmployeeAbstractEntity;
import jp.co.ricoh.cotos.commonlib.repository.common.AttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.security.TestSecurityController;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestCommonDto {

	private static final String STR_256 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	private static final int INT_MINUS_1 = -1;

	static ConfigurableApplicationContext context;

	@Autowired
	HeadersProperties headersProperties;

	@Autowired
	TestTools testTool;

	@Autowired
	AttachedFileRepository attachedFileRepository;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
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
	public void CheckResultUpdateParameterのテスト() throws Exception {
		CheckResultUpdateParameter dto = new CheckResultUpdateParameter();
		dto.setCheckResultId(11L);
		dto.setUpdateStatus(true);

		CheckResultUpdateParameter testTarget = new CheckResultUpdateParameter();

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setCheckResultId(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "チェック結果IDは最小値（0）を下回っています。"));
	}

	@Test
	public void DtoBaseのテスト() {
		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(EntityBase.class, DtoBase.class, "id");
	}

	@Test
	public void CustomerAbstractDtoのテスト() {
		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(CustomerAbstractEntity.class, CustomerAbstractDto.class);
	}

	@Test
	public void DealerAbstractDtoのテスト() {
		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(DealerAbstractEntity.class, DealerAbstractDto.class);
	}

	@Test
	public void EmployeeAbstractDtoのテスト() {
		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(EmployeeAbstractEntity.class, EmployeeAbstractDto.class);
	}

	@Test
	public void FileImportManagementParameterのテスト() throws Exception {
		AttachedFile entity = attachedFileRepository.findById(1L).get();
		FileImportManagementParameter testTarget = new FileImportManagementParameter();
		FileImportManagementParameter dto = new FileImportManagementParameter();
		dto.setFileName("dummy_file_name");
		dto.setAttachedFile(entity);
		dto.setImportUser("dummy_import_user");

		// 正常系
		BeanUtils.copyProperties(testTarget, dto);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setFileName(null);
		testTarget.setAttachedFile(null);
		testTarget.setImportUser(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ファイル名が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setFileName(STR_256);
		testTarget.setImportUser(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ファイル名は最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, dto);
		testTarget.setFileKindManagementMasterId(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ファイル種別管理マスタIDは最小値（0）を下回っています。"));
	}

}
