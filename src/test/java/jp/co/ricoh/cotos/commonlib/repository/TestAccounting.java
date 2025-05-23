package jp.co.ricoh.cotos.commonlib.repository;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import jp.co.ricoh.cotos.commonlib.entity.EnumType.BatchCommonStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import jp.co.ricoh.cotos.commonlib.entity.accounting.AccountedCancellationData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.Accounting;
import jp.co.ricoh.cotos.commonlib.entity.accounting.AccountingPeriodDetail;
import jp.co.ricoh.cotos.commonlib.entity.accounting.CommissionData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.InvoiceLinkage;
import jp.co.ricoh.cotos.commonlib.entity.accounting.InvoiceLinkage.InvoiceTaxType;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestDetailData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestDetailPlanData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestPlanData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoResultsData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoResultsPlanData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.UsageQuantity;
import jp.co.ricoh.cotos.commonlib.entity.accounting.UsageQuantityRelatedManagement;
import jp.co.ricoh.cotos.commonlib.entity.accounting.Wjcmj301KiykSikyuCtsWk;
import jp.co.ricoh.cotos.commonlib.entity.accounting.Wjcmj302SikyuMisiCtsWk;
import jp.co.ricoh.cotos.commonlib.entity.accounting.Wjcmj303GnkHrkeCtsWk;
import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDataAbstractEntity.DataDiv;
import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDetailDataAbstractEntity.ProcessingDiv;
import jp.co.ricoh.cotos.commonlib.repository.accounting.AccountedCancellationDataRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.AccountingPeriodDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.AccountingRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.CommissionDataRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.InvoiceLinkageRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.OsoRequestDataRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.OsoRequestDetailDataRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.OsoRequestDetailPlanDataRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.OsoRequestPlanDataRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.OsoResultsDataRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.OsoResultsPlanDataRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.UsageQuantityRelatedManagementRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.UsageQuantityRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.Wjcmj301KiykSikyuCtsWkRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.Wjcmj302SikyuMisiCtsWkRepository;
import jp.co.ricoh.cotos.commonlib.repository.accounting.Wjcmj303GnkHrkeCtsWkRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAccounting {

	static ConfigurableApplicationContext context;

	@Autowired
	TestTools testTool;

	@Autowired
	AccountingRepository accountingRespository;

	@Autowired
	Wjcmj301KiykSikyuCtsWkRepository wjcmj301KiykSikyuCtsWkRepository;

	@Autowired
	Wjcmj302SikyuMisiCtsWkRepository wjcmj302SikyuMisiCtsWkRepository;

	@Autowired
	Wjcmj303GnkHrkeCtsWkRepository wjcmj303GnkHrkeCtsWkRepository;

	@Autowired
	CommissionDataRepository commissionDataRepository;

	@Autowired
	OsoRequestDataRepository osoRequestDataRepository;

	@Autowired
	OsoRequestDetailDataRepository osoRequestDetailDataRepository;

	@Autowired
	OsoRequestDetailPlanDataRepository osoRequestDetailPlanDataRepository;

	@Autowired
	OsoRequestPlanDataRepository osoRequestPlanDataRepository;

	@Autowired
	OsoResultsDataRepository osoResultsDataRepository;

	@Autowired
	OsoResultsPlanDataRepository osoResultsPlanDataRepository;

	@Autowired
	UsageQuantityRepository usageQuantityRepository;

	@Autowired
	UsageQuantityRelatedManagementRepository usageQuantityRelatedManagementRepository;

	@Autowired
	InvoiceLinkageRepository invoiceLinkageRepository;

	@Autowired
	AccountingPeriodDetailRepository accountingPeriodDetailRepository;

	@Autowired
	AccountedCancellationDataRepository accountedCancellationDataRepository;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/accounting.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void AccountingRepositoryのテスト() throws Exception {

		Accounting found = accountingRespository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void Wjcmj301KiykSikyuCtsWkRepositoryのテスト() throws Exception {

		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/wjcmj301KiykSikyuCtsWk.sql");

		Wjcmj301KiykSikyuCtsWk found = wjcmj301KiykSikyuCtsWkRepository.findById("00011878000001000000").get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void Wjcmj302SikyuMisiCtsWkRepositoryのテスト() throws Exception {

		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/wjcmj302SikyuMisiCtsWk.sql");

		Wjcmj302SikyuMisiCtsWk found = wjcmj302SikyuMisiCtsWkRepository.findById("00011878000001000000").get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void Wjcmj303GnkHrkeCtsWkRepositoryのテスト() throws Exception {

		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/wjcmj303GnkHrkeCtsWk.sql");

		Wjcmj303GnkHrkeCtsWk found = wjcmj303GnkHrkeCtsWkRepository.findById("00011878000001001000").get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void CommissionDataRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/commissionData.sql");

		CommissionData found = commissionDataRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void OsoRequestDataRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoRequestData.sql");

		OsoRequestData found = osoRequestDataRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void OsoRequestPlanDataRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoRequestPlanData.sql");

		OsoRequestPlanData found = osoRequestPlanDataRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void OsoRequestDetailDataRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoRequestDetailData.sql");

		OsoRequestDetailData found = osoRequestDetailDataRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void OsoRequestDetailPlanDataRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoRequestDetailPlanData.sql");

		OsoRequestDetailPlanData found = osoRequestDetailPlanDataRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void OsoResultsDataRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoResultsData.sql");

		OsoResultsData found = osoResultsDataRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void OsoResultsDataRepositoryの条件テスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoResultsData.sql");

		List<OsoResultsData> list = osoResultsDataRepository.findByOsoManageNumberAndItemCodeAndProcessingStatusOrderByIdDesc("oso_manage_number", "item_code", OsoProcessingStatus.処理済);

		// Entity が null ではないことを確認
		Assert.assertEquals(3L, list.size());
		Assert.assertNotNull(list.get(0));
		Assert.assertEquals(3L, list.get(0).getId());
	}

	@Test
	public void OsoResultsPlanDataRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoResultsPlanData.sql");

		OsoResultsPlanData found = osoResultsPlanDataRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void UsageQuantityRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/usageQuantity.sql");

		UsageQuantity found = usageQuantityRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		List<UsageQuantity> founds = usageQuantityRepository.findByContractIdAndContractDetailId(1L, 1L);

		// Entity が null または 空 ではないことを確認
		Assert.assertTrue(!CollectionUtils.isEmpty(founds));

		founds = usageQuantityRepository.findByRjManageNumberAndItemCode("rj_manage_number", "item_code");

		// Entity が null または 空 ではないことを確認
		Assert.assertTrue(!CollectionUtils.isEmpty(founds));

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		founds = usageQuantityRepository.findByUsageDate(df.parse("2019/01/01"));

		// Entity が null または 空 ではないことを確認
		Assert.assertTrue(!CollectionUtils.isEmpty(founds));
	}

	@Test
	public void UsageQuantityRelatedManagementRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/usageQuantityRelatedManagement.sql");

		UsageQuantityRelatedManagement found = usageQuantityRelatedManagementRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void OsoRequestDataRepositoryの条件テスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoRequestData.sql");

		List<OsoRequestData> list = osoRequestDataRepository.findByDataDivAndRequestManageNumberAndProcessingStatus(DataDiv.新規.toString(), "request_manage_number", OsoProcessingStatus.処理済.toString());

		// Entity が null ではないことを確認
		Assert.assertEquals(1L, list.size());
		Assert.assertNotNull(list.get(0));
	}

	@Test
	public void OsoRequestDetailDataRepositoryの条件テスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/osoRequestDetailData.sql");

		List<OsoRequestDetailData> list = osoRequestDetailDataRepository.findByProcessingDivAndOsoManageNumberAndItemCodeAndProcessingStatus(ProcessingDiv.追加, "oso_manage_number", "item_code", OsoProcessingStatus.処理済);

		// Entity が null ではないことを確認
		Assert.assertEquals(1L, list.size());
		Assert.assertNotNull(list.get(0));

		list = osoRequestDetailDataRepository.findByOsoManageNumberAndItemCodeAndProcessingStatusOrderByIdDesc("oso_manage_number", "item_code", OsoProcessingStatus.処理済);

		// Entity が null ではないことを確認
		Assert.assertEquals(3L, list.size());
		Assert.assertNotNull(list.get(0));
		Assert.assertEquals(3L, list.get(0).getId());

		// 独自メソッド
		list = osoRequestDetailDataRepository.findByContractDetailId(1L);

		Assert.assertEquals(3L, list.size());
		Assert.assertNotNull(list.get(0));
	}

	@Test
	public void InvoiceLinkageRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/invoiceLinkage.sql");

		InvoiceLinkage found = invoiceLinkageRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
		Assert.assertEquals(InvoiceTaxType.免税, found.getSalesTaxType());
	}

	@Test
	public void InvoiceLinkageRepositoryの条件テスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/invoiceLinkage.sql");

		List<InvoiceLinkage> list = invoiceLinkageRepository.findByCreateYmAndReceiveStatus("202107", BatchCommonStatus.未処理);

		// Entity が null ではないことを確認
		Assert.assertEquals(2, list.size());

		list = invoiceLinkageRepository.findBySendStatusOrderById(BatchCommonStatus.未処理);

		// Entity が null ではないことを確認
		Assert.assertEquals(2, list.size());
		// ID順にソートされていることを確認
		Assert.assertEquals(2L, list.get(0).getId());
		Assert.assertEquals(3L, list.get(1).getId());

		InvoiceLinkage found = invoiceLinkageRepository.findByContractIdAndRicohItemCodeAndSerialNumberAndCreateYmAndSendStatusAndReceiveStatus("contract_id_1", "ricoh_item_code_1", "serial_number_1", "202107", BatchCommonStatus.処理済, BatchCommonStatus.未処理);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		list = invoiceLinkageRepository.findByReceiveStatus(BatchCommonStatus.処理対象外);

		// Entity が null ではないことを確認
		Assert.assertEquals(1, list.size());

		list = invoiceLinkageRepository.findBySendStatusAndReceiveStatus(BatchCommonStatus.処理済, BatchCommonStatus.未処理);

		// Entity が null ではないことを確認
		Assert.assertEquals(2, list.size());

		found = invoiceLinkageRepository.findByContractIdAndRicohItemCodeAndSerialNumberAndCreateYm("contract_id_1", "ricoh_item_code_1", "serial_number_1", "202107");

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

	}

	@Test
	public void AccountingPeriodDetailRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/accountingPeriodDetail.sql");

		AccountingPeriodDetail found = accountingPeriodDetailRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void AccountedCancellationDataRepositoryのテスト() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("repository/accounting/accountedCancellationData.sql");

		AccountedCancellationData found = accountedCancellationDataRepository.findById(1L).get();

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}
}
