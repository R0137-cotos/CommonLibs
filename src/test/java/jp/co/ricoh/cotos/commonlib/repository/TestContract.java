package jp.co.ricoh.cotos.commonlib.repository;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalRouteNode;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAssignment;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractCheckResult;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractInstallationLocation;
import jp.co.ricoh.cotos.commonlib.entity.contract.DealerContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ManagedContractEquipmentStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.VValidContractPeriodHistory;
import jp.co.ricoh.cotos.commonlib.repository.contract.CollectLocationRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAddedEditorEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractApprovalResultRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractApprovalRouteNodeRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractApprovalRouteRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAssignmentAttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAssignmentRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAttachedFileHistoryRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAttachedFileLinkageRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractCheckResultRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractEquipmentAdditionInfoRefreshHisRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractEquipmentAdditionInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractEquipmentItemLinkRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractEquipmentNoIsysoneRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractEquipmentRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractInstallationLocationRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractOperationLogRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicAccCeEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicAccSsOrgRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicIntCeEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicIntSsOrgRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicMntCeEmpRefreshHisRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicMntCeEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicMntSsOrgRefreshHisRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicMntSsOrgRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicSaEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.CustomerContractRefreshHisRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.CustomerContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.DealerContractRefreshHisRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.DealerContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ItemContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ItemDetailContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ManagedContractEquipmentStatusRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ManagedEstimationDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.PenaltyDetailContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.PenaltyDetailTransRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.PriceRewriteExclusionContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.PriceRewriteItemInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ProductContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ShippingAddressRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ShippingAddressSsOrgRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ShippingThingDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.VValidContractPeriodHistoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestContract {

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	ContractAddedEditorEmpRepository contractAddedEditorEmpRepository;

	@Autowired
	ContractApprovalResultRepository contractApprovalResultRepository;

	@Autowired
	ContractApprovalRouteRepository contractApprovalRouteRepository;

	@Autowired
	ContractApprovalRouteNodeRepository contractApprovalRouteNodeRepository;

	@Autowired
	ContractAttachedFileRepository contractAttachedFileRepository;

	@Autowired
	ContractCheckResultRepository contractCheckResultRepository;

	@Autowired
	ContractDetailRepository contractDetailRepository;

	@Autowired
	ContractEquipmentRepository contractEquipmentRepository;

	@Autowired
	ContractOperationLogRepository contractOperationLogRepository;

	@Autowired
	ContractPicSaEmpRepository contractPicSaEmpRepository;

	@Autowired
	CustomerContractRepository customerContractRepository;

	@Autowired
	DealerContractRepository dealerContractRepository;

	@Autowired
	ItemContractRepository itemContractRepository;

	@Autowired
	ItemDetailContractRepository itemDetailContractRepository;

	@Autowired
	ProductContractRepository productContractRepository;

	@Autowired
	ContractPicMntCeEmpRepository contractPicMntCeEmpRepository;

	@Autowired
	ContractPicIntCeEmpRepository contractPicIntCeEmpRepository;

	@Autowired
	ContractPicAccCeEmpRepository contractPicAccCeEmpRepository;

	@Autowired
	ContractPicMntSsOrgRepository contractPicMntSsOrgRepository;

	@Autowired
	ContractPicAccSsOrgRepository contractPicAccSsOrgRepository;

	@Autowired
	ContractPicIntSsOrgRepository contractPicIntSsOrgRepository;

	@Autowired
	ContractInstallationLocationRepository contractInstallationLocationRepository;

	@Autowired
	ContractAttachedFileHistoryRepository contractAttachedFileHistoryRepository;

	@Autowired
	ManagedEstimationDetailRepository managedEstimationDetailRepository;

	@Autowired
	ContractAssignmentRepository contractAssignmentRepository;

	@Autowired
	ContractAssignmentAttachedFileRepository contractAssignmentAttachedFileRepository;

	@Autowired
	ContractAttachedFileLinkageRepository contractAttachedFileLinkageRepository;

	@Autowired
	VValidContractPeriodHistoryRepository vValidContractPeriodHistoryRepository;

	@Autowired
	PenaltyDetailTransRepository penaltyDetailTransRepository;

	@Autowired
	ShippingAddressSsOrgRepository shippingAddressSsOrgRepository;

	@Autowired
	ShippingThingDetailRepository shippingThingDetailRepository;

	@Autowired
	ContractEquipmentNoIsysoneRepository contractEquipmentNoIsysoneRepository;

	@Autowired
	ManagedContractEquipmentStatusRepository managedContractEquipmentStatusRepository;

	@Autowired
	PenaltyDetailContractRepository penaltyDetailContractRepository;

	@Autowired
	ShippingAddressRepository shippingAddressRepository;

	@Autowired
	ContractEquipmentItemLinkRepository contractEquipmentItemLinkRepository;

	@Autowired
	CustomerContractRefreshHisRepository customerContractRefreshHisRepository;

	@Autowired
	DealerContractRefreshHisRepository dealerContractRefreshHisRepository;

	@Autowired
	ContractPicMntSsOrgRefreshHisRepository contractPicMntSsOrgRefreshHisRepository;

	@Autowired
	ContractPicMntCeEmpRefreshHisRepository contractPicMntCeEmpRefreshHisRepository;

	@Autowired
	TestTools testTools;

	@Autowired
	CollectLocationRepository collectLocationRepository;

	@Autowired
	ContractEquipmentAdditionInfoRepository contractEquipmentAdditionInfoRepository;

	@Autowired
	ContractEquipmentAdditionInfoRefreshHisRepository contractEquipmentAdditionInfoRefreshHisRepository;

	@Autowired
	PriceRewriteItemInfoRepository priceRewriteItemInfoRepository;

	@Autowired
	PriceRewriteExclusionContractRepository priceRewriteExclusionContractRepository;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約() {
		全てのカラムがNullではないことを確認_共通(contractRepository, 4L, 5L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約追加編集者社員() {
		全てのカラムがNullではないことを確認_共通(contractAddedEditorEmpRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約承認実績() {
		全てのカラムがNullではないことを確認_共通(contractApprovalResultRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約承認ルート() {
		全てのカラムがNullではないことを確認_共通(contractApprovalRouteRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約承認ルートノード() {
		全てのカラムがNullではないことを確認_共通(contractApprovalRouteNodeRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約添付ファイル() {
		全てのカラムがNullではないことを確認_共通(contractAttachedFileRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約チェック結果() {
		全てのカラムがNullではないことを確認_共通(contractCheckResultRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約明細() {
		全てのカラムがNullではないことを確認_共通(contractDetailRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約機種() {
		全てのカラムがNullではないことを確認_共通(contractEquipmentRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約操作履歴() {
		全てのカラムがNullではないことを確認_共通(contractOperationLogRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約担当SA社員() {
		全てのカラムがNullではないことを確認_共通(contractPicSaEmpRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_顧客_契約用() {
		全てのカラムがNullではないことを確認_共通(customerContractRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_販売店_契約用() {
		全てのカラムがNullではないことを確認_共通(dealerContractRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_品種_契約用() {
		全てのカラムがNullではないことを確認_共通(itemContractRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_品種明細_契約用() {
		全てのカラムがNullではないことを確認_共通(itemDetailContractRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_商品_契約用() {
		全てのカラムがNullではないことを確認_共通(productContractRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約担当CE社員() {
		全てのカラムがNullではないことを確認_共通(contractPicMntCeEmpRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約導入担当CE社員() {
		全てのカラムがNullではないことを確認_共通(contractPicIntCeEmpRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約受付担当CE社員() {
		全てのカラムがNullではないことを確認_共通(contractPicAccCeEmpRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約保守担当SS組織() {
		全てのカラムがNullではないことを確認_共通(contractPicMntSsOrgRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約導入担当SS組織() {
		全てのカラムがNullではないことを確認_共通(contractPicIntSsOrgRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_設置先_契約用() {
		全てのカラムがNullではないことを確認_共通(contractInstallationLocationRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約受付担当SS組織() {
		全てのカラムがNullではないことを確認_共通(contractPicAccSsOrgRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約添付ファイル履歴() {
		全てのカラムがNullではないことを確認_共通(contractAttachedFileHistoryRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_見積明細管理() {
		全てのカラムがNullではないことを確認_共通(managedEstimationDetailRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約業務情報() {
		全てのカラムがNullではないことを確認_共通(contractAssignmentRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約業務添付ファイル() {
		全てのカラムがNullではないことを確認_共通(contractAssignmentAttachedFileRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約添付ファイル連携先() {
		全てのカラムがNullではないことを確認_共通(contractAttachedFileLinkageRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_違約金明細振替() {
		全てのカラムがNullではないことを確認_共通(penaltyDetailTransRepository, 4L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_配送先SS組織() {
		全てのカラムがNullではないことを確認_共通(shippingAddressSsOrgRepository, 1L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_発送物あり明細() {
		全てのカラムがNullではないことを確認_共通(shippingThingDetailRepository, 401L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約機種_Isys_Oneへの連携なし() {
		全てのカラムがNullではないことを確認_共通(contractEquipmentNoIsysoneRepository, 1L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約機種状態管理() {
		全てのカラムがNullではないことを確認_共通(managedContractEquipmentStatusRepository, 1L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_違約金明細_契約用() {
		全てのカラムがNullではないことを確認_共通(penaltyDetailContractRepository, 4L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約機種_配送先() {
		全てのカラムがNullではないことを確認_共通(shippingAddressRepository, 4L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約機種品種紐づけ() {
		全てのカラムがNullではないことを確認_共通(contractEquipmentItemLinkRepository, 1L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_顧客_契約用_洗い替え履歴() {
		全てのカラムがNullではないことを確認_共通(customerContractRefreshHisRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_販売店_契約用_洗い替え履歴() {
		全てのカラムがNullではないことを確認_共通(dealerContractRefreshHisRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_回収先() {
		全てのカラムがNullではないことを確認_共通(collectLocationRepository, 502L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約保守担当SS組織洗い替え履歴() {
		全てのカラムがNullではないことを確認_共通(contractPicMntSsOrgRefreshHisRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約担当CE社員洗い替え履歴() {
		全てのカラムがNullではないことを確認_共通(contractPicMntCeEmpRefreshHisRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約機種付加情報() {
		全てのカラムがNullではないことを確認_共通(contractEquipmentAdditionInfoRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_契約機種付加情報洗い替え履歴() {
		全てのカラムがNullではないことを確認_共通(contractEquipmentAdditionInfoRefreshHisRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_価格書換品種情報() {
		全てのカラムがNullではないことを確認_共通(contractEquipmentAdditionInfoRefreshHisRepository, 401L, 501L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_価格書換除外契約() {
		全てのカラムがNullではないことを確認_共通(priceRewriteExclusionContractRepository, 401L, 501L);
	}

	@Test
	public void 契約承認ルート条件取得確認() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		ContractApprovalRoute found = contractApprovalRouteRepository.findByContractIdAndTargetLifecycleStatus(4L, "5");
		Assert.assertNotNull(found);
	}

	@Test
	public void 契約承認ルートノード条件取得確認() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		ContractApprovalRouteNode found = contractApprovalRouteNodeRepository.findByContractApprovalRouteIdAndApprovalOrderAndApproverEmpId(401L, 1, "00808347");
		Assert.assertNotNull(found);
	}

	@Test
	public void 契約条件取得確認() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = format.parse("2019/12/01 01:23:45");
		List<Contract> listChangePreferred = contractRepository.findByContractTypeAndChangePreferredDate(date);
		Assert.assertTrue(listChangePreferred.size() != 0);

		List<Contract> listServiceStart = contractRepository.findByContractTypeAndServiceStartDate(date);
		Assert.assertTrue(listServiceStart.size() != 0);

		List<Contract> listChangePreferredAndServiceStart = contractRepository.findByContractTypeAndChangePreferredDateAndServiceStartDate(date);
		Assert.assertTrue(listChangePreferredAndServiceStart.size() != 0);
		// ライフサイクルが締結待ちで契約種別＝契約更新のデータを取得できているか確認
		Assert.assertTrue(listChangePreferredAndServiceStart.stream().filter(contract -> contract.getContractType().equals(ContractType.契約更新) && contract.getLifecycleStatus().equals(LifecycleStatus.締結待ち)).count() != 0);

		// 解約予定日<指定日付
		List<Contract> cancelScheduledDate = contractRepository.findByLifecycleAndCancelScheduledDateOrCancelDecisionDate("2020/07/01");
		Assert.assertTrue(cancelScheduledDate.size() > 0);

		// 解約確定日<指定日付
		List<Contract> cancelDecisionDate = contractRepository.findByLifecycleAndCancelScheduledDateOrCancelDecisionDate("2020/07/01");
		Assert.assertTrue(cancelDecisionDate.size() > 0);

		// 契約IDリスト指定
		List<Long> contractIdList = Arrays.asList(8L, 10L,12L);
		List<Contract> contractList = contractRepository.findByIdIn(contractIdList);
		Assert.assertTrue(contractList.size() == 3);
	}

	@Test
	public void ContractRepositoryの条件テスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		List<String> appId = Arrays.asList("electric");
		Contract found = contractRepository.findByIdAndAppIdNotIn(4L, appId);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTools.assertColumnsNotNull(found);

		appId = Arrays.asList("cotos_dev");
		found = contractRepository.findByIdAndAppIdIn(4L, appId);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTools.assertColumnsNotNull(found);
	}

	@Test
	public void ContractAssignmentRepositoryの条件テスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		ContractAssignment found = contractAssignmentRepository.findByContractId(4L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Transactional
	private <T extends EntityBase, ID extends Serializable> void 全てのカラムがNullではないことを確認_共通(CrudRepository<T, ID> repository, @SuppressWarnings("unchecked") ID... ids) {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		List<ID> idList = Arrays.asList(ids);

		idList.stream().forEach(id -> {
			// データが取得できることを確認
			T found = repository.findOne(id);
			Assert.assertNotNull(found);
			// 全てのカラムがNullではないことを確認
			try {
				testTools.assertColumnsNotNull(found);
			} catch (Exception e1) {
				Assert.fail("例外が発生した場合、エラー");
			}
		});
	}

	@Test
	public void 契約自動更新情報取得() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractAutoUpdateMaster.sql");

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = format.parse("2020/12/31 01:59:59");
		List<Contract> list = contractRepository.findByAutoUpdaterecord(date);
		Assert.assertTrue(list.size() != 0);
	}

	@Test
	public void ContractRepository_findByProductGrpMasterIdのテスト() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");
		List<Long> productGrpMasterIdList = Arrays.asList(7L, 8L, 9L);
		List<Contract> foundList = contractRepository.findByProductGrpMasterId(productGrpMasterIdList);
		// Entity の各項目の値が null ではないことを確認
		Assert.assertNotNull(foundList);
		// データが取得できていることを確認
		Assert.assertTrue(foundList.size() > 0);
		// 契約種別＝契約更新が取得できていることを確認
		Assert.assertTrue(foundList.stream().filter(contract -> contract.getContractType().equals(ContractType.契約更新)).count() > 0);

	}

	@Test
	public void VValidContractPeriodHistoryRepositoryのテスト() throws ParseException {

		context.getBean(DBConfig.class).initTargetTestData("repository/vValidContractPeriodHistory.sql");

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date date = df.parse("2019/12/01");

		//商品種類区分In指定
		List<String> productClassDivIn = Arrays.asList("RSI", "ROC");
		List<VValidContractPeriodHistory> foundByProductClassDivIn = vValidContractPeriodHistoryRepository.findByProductClassDivInAndDate(productClassDivIn, date);

		// データが取得できていることを確認
		Assert.assertEquals(foundByProductClassDivIn.size(), 4); //RSIが2件 ROCが2件
		// Entity の各項目の値が null ではないことを確認
		foundByProductClassDivIn.forEach(found -> {
			try {
				Assert.assertNull(testTools.findNullProperties(found));
			} catch (Exception e) {
				Assert.fail("例外が発生した場合、エラー");
			}
		});

		//RJ管理番号指定
		List<String> rjManageNumberIn = Arrays.asList("RJXXX0000000014", "RJXXX0000000015");
		List<VValidContractPeriodHistory> foundByRjManageNumberIn = vValidContractPeriodHistoryRepository.findByRjManageNumberInAndDate(rjManageNumberIn, date);

		// データが取得できていることを確認
		Assert.assertEquals(foundByRjManageNumberIn.size(), 2);
		// Entity の各項目の値が null ではないことを確認
		foundByRjManageNumberIn.forEach(found -> {
			try {
				Assert.assertNull(testTools.findNullProperties(found));
			} catch (Exception e) {
				Assert.fail("例外が発生した場合、エラー");
			}
		});
	}

	@Test
	public void AttachedFileRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		List<ContractAttachedFile> foundList = contractAttachedFileRepository.findByContractId(4L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

		// Entity 1件以上取得できていることを確認
		Assert.assertNotEquals(foundList.size(), 0);
	}

	@Test
	public void 契約チェック結果条件取得確認() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		List<ContractCheckResult> found = contractCheckResultRepository.findByContractIdAndCheckMatterCode(5L, "TEST_12");
		Assert.assertEquals("契約チェック結果が1件であること", 1, found.size());
	}

	@Test
	public void ContractInstallationLocationRepositoryのテスト() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		ContractInstallationLocation found = contractInstallationLocationRepository.findByContractId(4L);

		// 全てのカラムがNullではないことを確認
		try {
			testTools.assertColumnsNotNull(found);
		} catch (Exception e1) {
			Assert.fail("例外が発生した場合、エラー");
		}
	}

	@Test
	public void DealerContractRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		List<DealerContract> foundList = dealerContractRepository.findByContractId(4L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

		// Entity 2件レコードが取得できていることを確認
		Assert.assertEquals(foundList.size(), 2);
	}

	@Test
	public void ManagedContractEquipmentStatusRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");

		List<ManagedContractEquipmentStatus> foundList = managedContractEquipmentStatusRepository.findByContractId(4L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

		// Entity 1件レコードが取得できていることを確認
		Assert.assertEquals(foundList.size(), 1);
	}
}
