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
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.common.FileImportErrorDetails;
import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement;
import jp.co.ricoh.cotos.commonlib.entity.common.SearchCondition;
import jp.co.ricoh.cotos.commonlib.repository.common.AttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.EimDocumentInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.FileImportErrorDetailsRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.FileImportManagementRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.SearchConditionRepository;
import jp.co.ricoh.cotos.commonlib.security.TestSecurityController;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestCommon {

	private static final String STR_256 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	private static final String STR_1001 = "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
	private static final int INT_MINUS_1 = -1;
	private static final int INT_100000 = 100000;


	static ConfigurableApplicationContext context;

	@Autowired
	HeadersProperties headersProperties;

	@Autowired
	TestTools testTool;

	@Autowired
	AttachedFileRepository attachedFileRepository;

	@Autowired
	FileImportManagementRepository fileImportManagementRepository;

	@Autowired
	FileImportErrorDetailsRepository fileImportErrorDetailsRepository;

	@Autowired
	EimDocumentInfoRepository eimDocumentInfoRepository;

	@Autowired
	SearchConditionRepository searchConditionRepository;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/fileImportErrorDetails.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/fileImportManagement.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/searchCondition.sql");
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
	public void AttachedFileのテスト() throws Exception {
		AttachedFile entity = attachedFileRepository.findById(1L).get();
		AttachedFile testTarget = new AttachedFile();
		BeanUtils.copyProperties(testTarget, entity);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setFilePhysicsName(null);
		testTarget.setContentType(null);
		testTarget.setSavedPath(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "コンテンツタイプが設定されていません。"));

		// 異常系（@Size(max)）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setFilePhysicsName(STR_256);
		testTarget.setContentType(STR_256);
		testTarget.setSavedPath(STR_1001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "サーバーパスは最大文字数（1000）を超えています。"));
	}

	@Test
	public void SearchConditionのテスト() throws Exception {
		SearchCondition entity = searchConditionRepository.findById(1L).get();
		SearchCondition testTarget = new SearchCondition();
		BeanUtils.copyProperties(testTarget, entity);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setMomEmployeeId(null);
		testTarget.setDomain(null);
		testTarget.setTitle(null);
		testTarget.setSearchCondition(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDが設定されていません。"));

		// 異常系（@Size(max)）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setMomEmployeeId(STR_256);
		testTarget.setDomain(STR_256);
		testTarget.setTitle(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDは最大文字数（255）を超えています。"));
	}

	@Test
	public void FileImportManagementのテスト() throws Exception {
		FileImportManagement entity = fileImportManagementRepository.findById(1L).get();
		FileImportManagement testTarget = new FileImportManagement();

		// 正常系
		BeanUtils.copyProperties(testTarget, entity);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setAttachmentFile(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "添付ファイルIDが設定されていません。"));

		// 異常系（@Size(max)）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setFileName(STR_256);
		testTarget.setImportUser(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ファイル名は最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setFileKindManagementMasterId((long) INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ファイル種別管理マスタIDは最小値（0）を下回っています。"));
	}

	@Test
	public void FileImportErrorDetailsのテスト() throws Exception {
		FileImportErrorDetails entity = fileImportErrorDetailsRepository.findById(1L).get();
		FileImportErrorDetails testTarget = new FileImportErrorDetails();

		// 正常系
		BeanUtils.copyProperties(testTarget, entity);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max)）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setMessage(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "エラー内容は最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setLineNumber((long) INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "行番号は最小値（0）を下回っています。"));
	}
}
