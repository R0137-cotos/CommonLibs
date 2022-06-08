package jp.co.ricoh.cotos.commonlib.repository;

import java.util.List;

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
import jp.co.ricoh.cotos.commonlib.db.RefreshMaterializedViewUtil;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.common.EimDocumentInfo;
import jp.co.ricoh.cotos.commonlib.entity.common.FileImportErrorDetails;
import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement;
import jp.co.ricoh.cotos.commonlib.entity.common.MailSendHistory;
import jp.co.ricoh.cotos.commonlib.entity.common.MailSendHistory.MailSendType;
import jp.co.ricoh.cotos.commonlib.entity.common.SearchCondition;
import jp.co.ricoh.cotos.commonlib.entity.common.TransactionDiscardingHistory;
import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressArrangementList;
import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressContractList;
import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressEstimationList;
import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressList;
import jp.co.ricoh.cotos.commonlib.entity.master.MailControlMaster;
import jp.co.ricoh.cotos.commonlib.repository.common.AttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.EimDocumentInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.FileImportErrorDetailsRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.FileImportManagementRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.MailSendHistoryRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.SearchConditionRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.TransactionDiscardingHistoryRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.VMailAddressArrangementListRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.VMailAddressContractListRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.VMailAddressEstimationListRepository;
import jp.co.ricoh.cotos.commonlib.repository.common.VMailAddressListRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MailControlMasterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestCommon {

	/** 添付ファイル */
	@Autowired
	AttachedFileRepository attachedFileRepository;

	/** メール送信履歴 */
	@Autowired
	MailSendHistoryRepository mailSendHistoryRepository;

	/**
	 * メールアドレス一覧
	 */
	@Autowired
	VMailAddressListRepository vMailAddressListRepository;

	/**
	 * メール制御マスタ
	 */
	@Autowired
	MailControlMasterRepository mailControlMasterRepository;

	/**
	 * EIM書誌情報
	 */
	@Autowired
	EimDocumentInfoRepository eimDocumentInfoRepository;

	/**
	 * ファイル取込エラー詳細
	 */
	@Autowired
	FileImportErrorDetailsRepository fileImportErrorDetailsRepository;

	/**
	 * ファイル取込管理
	 */
	@Autowired
	FileImportManagementRepository fileImportManagementRepository;

	/**
	 * メールアドレス一覧(見積)
	 */
	@Autowired
	VMailAddressEstimationListRepository vMailAddressEstimationListRepository;

	/**
	 * メールアドレス一覧(契約)
	 */
	@Autowired
	VMailAddressContractListRepository vMailAddressContractListRepository;

	/**
	 * メールアドレス一覧(手配)
	 */
	@Autowired
	VMailAddressArrangementListRepository vMailAddressArrangementListRepository;

	/**
	 * 検索条件
	 */
	@Autowired
	SearchConditionRepository searchConditionRepository;

	/**
	 * 案件破棄履歴
	 */
	@Autowired
	TransactionDiscardingHistoryRepository transactionDiscardingHistoryRepository;

	@Autowired
	TestTools testTool;

	@Autowired
	RefreshMaterializedViewUtil refreshMaterializedViewUtil;

	static ConfigurableApplicationContext context;

	private static final String SYNONYM_NAME = "V_VALID_LICENSE_ACCOUNT_INFO";

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		refreshMaterializedViewUtil.initStoredProcedure(context);
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailTemplateMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailConvertValueMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/mailSendHistory.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/estimation/estimation_all.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/eimDocumentInfo.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/fileImportErrorDetails.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/fileImportManagement.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/searchCondition.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/arrangement.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/transactionDiscardingHistory.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void AttachedFileRepositoryのテスト() throws Exception {

		AttachedFile found = attachedFileRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void MailSendHistoryRepositoryのテスト() throws Exception {

		MailSendHistory found = mailSendHistoryRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		MailControlMaster mailControlMaster = mailControlMasterRepository.findOne(1L);
		MailSendHistory found2 = mailSendHistoryRepository.findByTargetDataIdAndMailControlMasterAndMailSendType(1L, mailControlMaster, MailSendType.完了);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found2);

		List<MailSendHistory> found3 = mailSendHistoryRepository.findByMailControlMasterAndMailSendType(mailControlMaster, MailSendType.完了);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found3);
	}

	@Test
	public void VMailAddressListRepositoryのテスト() throws Exception {

		VMailAddressList found = vMailAddressListRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		List<String> foundList = vMailAddressListRepository.findByDomainAndTableAndTranId("1", "1", 4);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

	}

	@Test
	public void EimDocumentInfoRepositoryのテスト() throws Exception {
		EimDocumentInfo found = eimDocumentInfoRepository.findOne(1L);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		List<EimDocumentInfo> foundList = eimDocumentInfoRepository.findByKeiyakNoAndEimLinkedStatusAndOldDocumentFlg("3", EimLinkedStatus.連携済, false);
		// データが取得できていることを確認
		Assert.assertEquals(1, foundList.size());

	}

	@Test
	public void SearchConditionRepositoryのテスト() throws Exception {
		SearchCondition found = searchConditionRepository.findOne(1L);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		List<SearchCondition> foundList = searchConditionRepository.findByMomEmployeeIdAndDomain("cotos", "1");
		// データが取得できていることを確認
		Assert.assertEquals("データ数が正しいこと", 1, foundList.size());
		Assert.assertEquals("IDが正しいこと", 1L, foundList.get(0).getId());
	}

	@Test
	public void FileImportErrorDetailsRepositoryのテスト() throws Exception {
		FileImportErrorDetails found = fileImportErrorDetailsRepository.findOne(1L);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		List<FileImportErrorDetails> foundList = fileImportErrorDetailsRepository.findByFileImportManagementOrderById(10L);
		// Entityが指定件数取得できていることを確認する
		Assert.assertEquals(foundList.size(), 2);
		// OrderBy句の確認
		Assert.assertEquals(foundList.get(0).getId(), 2);
		Assert.assertEquals(foundList.get(1).getId(), 10);

	}

	@Test
	public void FileImportManagementRepositoryのテスト() throws Exception {
		FileImportManagement found = fileImportManagementRepository.findOne(1L);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void VMailAddressListEstimationRepositoryのテスト() throws Exception {

		VMailAddressEstimationList found = vMailAddressEstimationListRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		List<VMailAddressEstimationList> foundList = vMailAddressEstimationListRepository.findByTranId(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

	}

	@Test
	public void VMailAddressListContractRepositoryのテスト() throws Exception {

		// マテビューリフレッシュ
		refreshMaterializedViewUtil.refreshMViewAndSwitchOfLicenseAccountInfo(SYNONYM_NAME);

		VMailAddressContractList found = vMailAddressContractListRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		List<VMailAddressContractList> foundList = vMailAddressContractListRepository.findByTranId(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

	}

	@Test
	public void VMailAddressListArrangementRepositoryのテスト() throws Exception {

		VMailAddressArrangementList found = vMailAddressArrangementListRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		List<VMailAddressArrangementList> foundList = vMailAddressArrangementListRepository.findByTranId(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

	}

	@Test
	public void TransactionDiscardingHistoryRepositoryのテスト() throws Exception {

		TransactionDiscardingHistory found = transactionDiscardingHistoryRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}
}