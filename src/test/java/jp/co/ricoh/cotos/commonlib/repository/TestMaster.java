package jp.co.ricoh.cotos.commonlib.repository;

import static org.hamcrest.CoreMatchers.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.master.*;
import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkOrderMaster.CheckTimingType;
import jp.co.ricoh.cotos.commonlib.entity.master.MvWjmoc080DealerInfo.Id;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.Domain;
import jp.co.ricoh.cotos.commonlib.repository.master.*;
import lombok.val;

/**
 * Repository（マスタドメイン）のテストクラス
 *
 * @author hideto.yamanaka
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestMaster {

	static ConfigurableApplicationContext context;

	@Autowired
	private ApprovalRouteGrpMasterRepository approvalRouteGrpMasterRepository;
	@Autowired
	private ApprovalRouteMasterRepository approvalRouteMasterRepository;
	@Autowired
	private ApprovalRouteNodeMasterRepository approvalRouteNodeMasterRepository;
	@Autowired
	private ArrangementChecklistCompMasterRepository arrangementChecklistCompMasterRepository;
	@Autowired
	private ArrangementWorkCompMasterRepository arrangementWorkCompMasterRepository;
	@Autowired
	private ArrangementWorkTypeMasterRepository arrangementWorkTypeMasterRepository;
	@Autowired
	private AuthPatternMasterRepository authPatternMasterRepository;
	@Autowired
	private BusinessCalendarRepository businessCalendarRepository;
	@Autowired
	private CommonMasterDetailRepository commonMasterDetailRepository;
	@Autowired
	private CommonMasterRepository commonMasterRepository;
	@Autowired
	private ContractChecklistCompMasterRepository contractChecklistCompMasterRepository;
	@Autowired
	private CheckByItemMasterRepository checkByItemMasterRepository;
	@Autowired
	private DispUrlAuthMasterRepository dispUrlAuthMasterRepository;
	@Autowired
	private EstimationChecklistCompMasterRepository estimationChecklistCompMasterRepository;
	@Autowired
	private GpCheckMatterMasterRepository gpCheckMatterMasterRepository;
	@Autowired
	private ItemMasterRepository itemMasterRepository;
	@Autowired
	private SystemMasterRepository systemMasterRepository;
	@Autowired
	private AppMasterRepository appMasterRepository;
	@Autowired
	private MailTemplateMasterRepository mailTemplateMasterRepository;
	@Autowired
	private MvEmployeeMasterRepository mvEmployeeMasterRepository;
	@Autowired
	private MvTJmci101MasterRepository mvTJmci101MasterRepository;
	@Autowired
	private MvTJmci105Repository mvTJmci105Repository;
	@Autowired
	private MvTjmmb010UtlItemRepository mvTjmmb010UtlItemRepository;
	@Autowired
	private MvTjmmb020UtlCdRepository mvTjmmb020UtlCdRepository;
	@Autowired
	private ProductCompMasterRepository productCompMasterRepository;
	@Autowired
	private ProductExtendsParameterMasterRepository productExtendsParameterMasterRepository;
	@Autowired
	private ProductGrpMasterRepository productGrpMasterRepository;
	@Autowired
	private ProductGrpIdentifierMasterRepository productGrpIdentifierMasterRepository;
	@Autowired
	private ProductMasterRepository productMasterRepository;
	@Autowired
	private RecordDecomposeCompMasterRepository recordDecomposeCompMasterRepository;
	@Autowired
	private RecordDecomposeMasterRepository recordDecomposeMasterRepository;
	@Autowired
	private SuperUserMasterRepository superUserMasterRepository;
	@Autowired
	private UrlAuthMasterRepository urlAuthMasterRepository;
	@Autowired
	private VKjbMasterRepository vKjbMasterRepository;
	@Autowired
	private VPicAffiliateMasterRepository vPicAffiliateMasterRepository;
	@Autowired
	private VPicAffiliateMasterFullRepository vPicAffiliateMasterFullRepository;
	@Autowired
	private MailControlMasterRepository mailControlMasterRepository;
	@Autowired
	private MailConvertValueMasterRepository mailConvertValueMasterRepository;
	@Autowired
	private DummyUserMasterRepository dummyUserMasterRepository;
	@Autowired
	private JsonSchemaMasterRepository jsonSchemaMasterRepository;
	@Autowired
	private ExtendsParameterCorrelationCheckMasterRepository extendsParameterCorrelationCheckMasterRepository;
	@Autowired
	private ContractAutoUpdateMasterRepository contractAutoUpdateMasterRepository;
	@Autowired
	private LedgerMasterRepository ledgerMasterRepository;
	@Autowired
	private MailProductMasterRepository mailProductMasterRepository;
	@Autowired
	private CeMasterRepository ceMasterRepository;
	@Autowired
	private ModelAbbreviationMasterRepository modelAbbreviationMasterRepository;
	@Autowired
	private EquipmentCompMasterRepository equipmentCompMasterRepository;
	@Autowired
	private ItemTransCompMasterRepository itemTransCompMasterRepository;
	@Autowired
	private ReportTemplateMasterRepository reportTemplateMasterRepository;
	@Autowired
	private ReportPageMasterRepository reportPageMasterRepository;
	@Autowired
	private MvTJmcj005MasterRepository mvTJmcj005MasterRepository;
	@Autowired
	private VDirectDeliveryDealerInfoMasterRepository vDirectDeliveryDealerInfoMasterRepository;
	@Autowired
	private MvWjmoco40EmpAllInfoComRepository mvWjmoco40EmpAllInfoComRepository;
	@Autowired
	private MvWjmoc080DealerInfoRepository mvWjmoc080DealerInfoRepository;
	@Autowired
	private MvTJmci102MasterRepository mvTJmci102MasterRepository;
	@Autowired
	private MvWjmoc020OrgAllInfoComRepository mvWjmoc020OrgAllInfoComRepository;
	@Autowired
	private IfsCsvMasterRepository ifsCsvMasterRepository;
	@Autowired
	private NonBusinessDayCalendarMasterRepository nonBusinessDayCalendarMasterRepository;
	@Autowired
	private MvTJmci108MasterRepository mvTJmci108MasterRepository;
	@Autowired
	private MvTjmcc020HnbitnMasterRepository mvTjmcc020HnbitnMasterRepository;
	@Autowired
	private MvVjmcb010MomKgyMasterRepository mvVjmcb010MomKgyMasterRepository;
	@Autowired
	private CheckAlertMasterRepository checkAlertMasterRepository;
	@Autowired
	private CheckAlertTargetMasterRepository checkAlertTargetMasterRepository;
	@Autowired
	private ProductPicMasterRepository productPicMasterRepository;
	@Autowired
	private VendorMasterRepository vendorMasterRepository;
	@Autowired
	private VendorProductMasterRepository vendorProductMasterRepository;
	@Autowired
	private AttachedFileLinkageRepository attachedFileLinkageRepository;
	@Autowired
	private AttachedFileProductClassCheckMasterRepository attachedFileProductClassCheckMasterRepository;
	@Autowired
	private AttachedFileProductGrpCheckMasterRepository attachedFileProductGrpCheckMasterRepository;
	@Autowired
	private ArrangementWorkOrderMasterRepository arrangementWorkOrderMasterRepository;
	@Autowired
	private ArrangementWorkTypeForSearchMasterRepository arrangementWorkTypeForSearchMasterRepository;
	@Autowired
	private MenuManagementMasterRepository menuManagementMasterRepository;
	@Autowired
	private MenuDetailsManagementMasterRepository menuDetailsManagementMasterRepository;
	@Autowired
	private ProductMasterDtoRepository productMasterDtoRepository;
	@Autowired
	private EmpGrpManagementMasterRepository empGrpManagementMasterRepository;

	@Autowired
	private FileKindManagementMasterRepository fileKindManagementMasterRepository;
	@Autowired
	private FileOperationRelationProductMasterRepository fileOperationRelationProductMasterRepository;
	@Autowired
	private ShippingThingMasterRepository shippingThingMasterRepository;
	@Autowired
	private ShippingPostNumberMasterRepository shippingPostNumberMasterRepository;
	@Autowired
	private DateCalcPatternMasterRepository dateCalcPatternMasterRepository;
	@Autowired
	private ItemDecomposeMasterRepository itemDecomposeMasterRepository;
	@Autowired
	private ContractChangeSpanMasterRepository contractChangeSpanMasterRepository;
	@Autowired
	private LicenseProcessControlMasterRepository licenseProcessControlMasterRepository;
	@Autowired
	private LicenseProcessMasterRepository licenseProcessMasterRepository;
	@Autowired
	private LicenseProcessPatternMasterRepository licenseProcessPatternMasterRepository;
	@Autowired
	private LicenseDivMasterRepository licenseDivMasterRepository;
	@Autowired
	private LicenseDivCompMasterRepository licenseDivCompMasterRepository;
	@Autowired
	private LicenseAccountDivMasterRepository licenseAccountDivMasterRepository;
	@Autowired
	private LicenseAccountDivCompMasterRepository licenseAccountDivCompMasterRepository;
	@Autowired
	private LicenseServiceMasterRepository licenseServiceMasterRepository;
	@Autowired
	private LicenseServiceCompMasterRepository licenseServiceCompMasterRepository;
	@Autowired
	private LicenseArrangementMasterRepository licenseArrangementMasterRepository;
	@Autowired
	private ItemLicenseSettingMasterRepository itemLicenseSettingMasterRepository;
	@Autowired
	private MailMasterRepository mailMasterRepository;
	@Autowired
	private MailAddressMasterRepository mailAddressMasterRepository;
	@Autowired
	private CsvFileSettingMasterRepository csvFileSettingMasterRepository;
	@Autowired
	private MvTjmob260OrgServiceMasterRepository mvTjmob260OrgServiceMasterRepository;
	@Autowired
	private MvRjShohinInfoMasterRepository mvRjShohinInfoMasterRepository;
	@Autowired
	private BatchRunDateManagementMasterRepository batchRunDateManagementMasterRepository;
	@Autowired
	private ArrangementWorkAuthControlMasterRepository arrangementWorkAuthControlMasterRepository;
	@Autowired
	private EnumDefinitionMasterRepository enumDefinitionMasterRepository;
	@Autowired
	private MvTjmoc290SsMasterRepository mvTjmoc290SsMasterRepository;

	@Autowired
	TestTools testTool = null;

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
	public void CommonMasterRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMasterDetail.sql");

		Long id = 1L;
		CommonMaster found = commonMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getCommonMasterDetailList() == null || found.getCommonMasterDetailList().size() == 0)
			Assert.assertTrue(false);
	}

	@Test
	public void SystemMasterRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/systemMaster.sql");

		String id = "cotos";
		SystemMaster found = systemMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void AppMasterRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/systemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/appMaster.sql");

		String id = "cotos_test";
		AppMaster found = appMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void CommonMasterDetailRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMasterDetail.sql");

		Long id = 1L;
		CommonMasterDetail found = commonMasterDetailRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目が値が null ではないことを確認
		if (found.getCommonMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void CommonMasterDetailRepository_CommonMasterDetailRepositoryのテスト() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMasterDetail.sql");
		CommonMasterDetail found = commonMasterDetailRepository.findByCommonMasterColumnNameAndAvailablePeriodBetween("sales_tax_rate", "20190101");
		Assert.assertEquals("取得データの税率が8(%)であること", found.getCodeValue(), "8");
	}

	@Test
	public void MailTemplateMasterRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailTemplateMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailConvertValueMaster.sql");
		Long id = 1L;
		MailTemplateMaster found = mailTemplateMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		MailTemplateMaster foundByServiceCategoryAndProcessCategoryAndProductGrpMasterId = mailTemplateMasterRepository.findByServiceCategoryAndProcessCategoryAndProductGrpMasterId("1", "3", 10L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundByServiceCategoryAndProcessCategoryAndProductGrpMasterId);

		// 狙ったマスタが取得できていること
		Assert.assertEquals("メールテンプレートマスタが取得できていること", 3, foundByServiceCategoryAndProcessCategoryAndProductGrpMasterId.getId());

	}

	@Test
	public void BusinessCalendarRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/businessCalendar.sql");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date id = sdf.parse("2018-09-20");

		BusinessCalendar found = businessCalendarRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		Assert.assertTrue(found.getBusinessDay() != null && found.getCalendarDate() != null);

	}

	@Test
	public void SuperUserMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/superUserMaster.sql");

		// エンティティの取得
		Long id = 1L;
		SuperUserMaster found = superUserMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// エンティティ取得
		found = superUserMasterRepository.findByUserId("MOM_EMPLOYEE_ID");

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void UrlAuthMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/urlAuthMaster.sql");

		// エンティティの取得
		UrlAuthMaster.Id id = new UrlAuthMaster.Id();
		id.setUrlPattern("/api/estimation$");
		id.setMethod(HttpMethod.GET);
		id.setDomain(Domain.estimation);
		UrlAuthMaster found = urlAuthMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void AuthPatternMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dispUrlAuthMaster.sql");

		// エンティティの取得
		Long id = 1L;
		AuthPatternMaster found = authPatternMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getAuthPatternMasterList() == null || found.getAuthPatternMasterList().size() == 0)
			Assert.assertTrue(false);
	}

	@Test
	public void DispUrlAuthMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dispUrlAuthMaster.sql");

		// エンティティの取得
		DispUrlAuthMaster.Id id = new DispUrlAuthMaster.Id();
		id.setSystemDomain("cotos.ricoh.co.jp");
		id.setUrlPattern("api/estimation/[0-9]+");
		id.setActionId("test_action_001");
		DispUrlAuthMaster found = dispUrlAuthMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getAuthPatternMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void ArrangementChecklistCompMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/gpCheckMatterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ArrangementChecklistCompMaster found = arrangementChecklistCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getArrangementWorkTypeMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void GpCheckMatterMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/gpCheckMatterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/estimationChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		GpCheckMatterMaster found = gpCheckMatterMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getArrangementChecklistCompMasterList() == null || found.getArrangementChecklistCompMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getEstimationChecklistCompMasterList() == null || found.getEstimationChecklistCompMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getContractChecklistCompMasterList() == null || found.getContractChecklistCompMasterList().size() == 0)
			Assert.assertTrue(false);
	}

	@Test
	public void GpCheckMatterMasterRepository_findOneByCheckMatterCode() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/gpCheckMatterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/estimationChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		String checkMatterCode = "TEST_1";
		GpCheckMatterMaster found = gpCheckMatterMasterRepository.findOneByCheckMatterCode(checkMatterCode);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getArrangementChecklistCompMasterList() == null || found.getArrangementChecklistCompMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getEstimationChecklistCompMasterList() == null || found.getEstimationChecklistCompMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getContractChecklistCompMasterList() == null || found.getContractChecklistCompMasterList().size() == 0)
			Assert.assertTrue(false);

	}

	@Test
	public void ArrangementWorkTypeMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ArrangementWorkTypeMaster found = arrangementWorkTypeMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getApprovalRouteGrpMaster() == null)
			Assert.assertTrue(false);
		if (found.getArrangementWorkCompMasterList() == null || found.getArrangementWorkCompMasterList() == null)
			Assert.assertTrue(false);
		if (found.getArrangementChecklistCompMasterList() == null || found.getArrangementChecklistCompMasterList() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void EstimationChecklistCompMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/estimationChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/gpCheckMatterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");

		// エンティティの取得
		Long id = 1L;
		EstimationChecklistCompMaster found = estimationChecklistCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getProductMaster() == null)
			Assert.assertTrue(false);
		if (found.getGpCheckMatterMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void ContractChecklistCompMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/gpCheckMatterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ContractChecklistCompMaster found = contractChecklistCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getProductMaster() == null)
			Assert.assertTrue(false);
		if (found.getGpCheckMatterMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void ArrangementWorkCompMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ArrangementWorkCompMaster found = arrangementWorkCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getItemMaster() == null)
			Assert.assertTrue(false);
		if (found.getArrangementWorkTypeMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void ApprovalRouteNodeMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteNodeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ApprovalRouteNodeMaster found = approvalRouteNodeMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getApprovalRouteMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void ApprovalRouteMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteNodeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ApprovalRouteMaster found = approvalRouteMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getApprovalRouteGrpMaster() == null)
			Assert.assertTrue(false);
		if (found.getApprovalRouteNodeMasterList() == null || found.getApprovalRouteNodeMasterList().size() == 0)
			Assert.assertTrue(false);
	}

	@Test
	public void ApprovalRouteGrpMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteNodeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ApprovalRouteGrpMaster found = approvalRouteGrpMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getApprovalRouteMasterList() == null || found.getApprovalRouteMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getArrangementWorkTypeMasterList() == null || found.getArrangementWorkTypeMasterList().size() == 0)
			Assert.assertTrue(false);

		// 承認ルートマスタが条件判定順の昇順で取得できていること
		for (int i = 0; i < found.getApprovalRouteMasterList().size() - 1; i = i + 1) {
			Assert.assertTrue(found.getApprovalRouteMasterList().get(i).getCondDetermineOrder() < found.getApprovalRouteMasterList().get(i + 1).getCondDetermineOrder());
		}
	}

	@Test
	public void ProductCompMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpIdentifierMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ProductCompMaster found = productCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getProductGrpMaster() == null)
			Assert.assertTrue(false);
		if (found.getProductMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void ProductGrpMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteNodeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpIdentifierMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ProductGrpMaster found = productGrpMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getContractApprovalRouteGrpMaster() == null)
			Assert.assertTrue(false);
		if (found.getEstimationApprovalRouteGrpMaster() == null)
			Assert.assertTrue(false);
		if (found.getProductGrpIdentifierMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void ProductGrpIdentifierMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpIdentifierMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ProductGrpIdentifierMaster found = productGrpIdentifierMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ProductMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/estimationChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/gpCheckMatterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/extendsParameterCorrelationCheckMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ifsCsvMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorProductMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productPicMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ProductMaster found = productMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getContractChecklistCompMasterList() == null || found.getContractChecklistCompMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getEstimationChecklistCompMasterList() == null || found.getEstimationChecklistCompMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getItemMasterList() == null || found.getItemMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getExtendsParameterCorrelationCheckMasterList() == null || found.getExtendsParameterCorrelationCheckMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getJsonSchemaMasterId() == null)
			Assert.assertTrue(false);
		if (found.getAttachedFileLinkageList() == null || found.getAttachedFileLinkageList().size() == 0)
			Assert.assertTrue(false);
		if (found.getVendorProductMaster() == null)
			Assert.assertTrue(false);
		if (found.getProductPicMasterList() == null || found.getProductPicMasterList().size() == 0)
			Assert.assertTrue(false);

		id = 2L;
		found = productMasterRepository.findOne(id);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
		if (found.getJsonSchemaMasterId() != null)
			Assert.assertTrue(false);
	}

	@Test
	public void ItemMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ItemMaster found = itemMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getArrangementWorkCompMasterList() == null || found.getArrangementWorkCompMasterList().size() == 0)
			Assert.assertTrue(false);
		if (found.getProductMaster() == null)
			Assert.assertTrue(false);
		if (found.getRecordDecomposeCompMasterList() == null || found.getRecordDecomposeCompMasterList().size() == 0)
			Assert.assertTrue(false);

		String ricohItemCode = "CP6573";
		ItemMaster found2 = itemMasterRepository.findByProductMasterIdAndRicohItemCode(id, ricohItemCode);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found2);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found2);

		// 結合するテーブルの外部KEYがNULLの場合、エンティティが取得されること
		id = 3L;
		found = itemMasterRepository.findOne(id);
		Assert.assertNotNull(found);
	}

	@Test
	public void RecordDecomposeCompMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		RecordDecomposeCompMaster found = recordDecomposeCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getItemMaster() == null)
			Assert.assertTrue(false);
		if (found.getRecordDecomposeMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void RecordDecomposeMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		RecordDecomposeMaster found = recordDecomposeMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getRecordDecomposeCompMasterList() == null || found.getRecordDecomposeCompMasterList().size() == 0)
			Assert.assertTrue(false);
	}

	@Test
	public void MvTJmci105Masterのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "4930594";
		MvTJmci105Master found = mvTJmci105Repository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void MvTJmci101Masterのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "1";
		MvTJmci101Master found = mvTJmci101MasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void MvTJmci102Masterのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "19197";
		MvTJmci102Master found = mvTJmci102MasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		String customerSiteNumber = "19197";
		MvTJmci102Master foundByCustomerSiteNumber = mvTJmci102MasterRepository.findOne(customerSiteNumber);

		Assert.assertNotNull(foundByCustomerSiteNumber);
	}

	@Test
	public void MvTjmmb010UtlItemのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "JMM_PH23_RELEASE";
		MvTjmmb010UtlItem found = mvTjmmb010UtlItemRepository.findByItemId(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void MvTjmmb020UtlItemのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		MvTjmmb020UtlCd.Id id = new MvTjmmb020UtlCd.Id();
		id.setItemId("JFCDM660");
		id.setCdVal("subject");
		MvTjmmb020UtlCd found = mvTjmmb020UtlCdRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test

	public void MvEmployeeMasterのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "00150194";
		MvEmployeeMaster found = mvEmployeeMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void VPicAffiliateMasterのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "0263975";
		VPicAffiliateMaster found = vPicAffiliateMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void VPicAffiliateMasterFullのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "0263975";
		VPicAffiliateMasterFull found = vPicAffiliateMasterFullRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void VKjbMasterのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "000000003985825";
		VKjbMaster found = vKjbMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void MailControlMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailTemplateMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailConvertValueMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		MailControlMaster found = mailControlMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// エンティティの取得 処理実行日計算パターンマスタIDがnull
		id = 2L;
		found = mailControlMasterRepository.findOne(id);
		// Entity が null ではないことを確認
		Assert.assertNotNull("Entityがnullではないことを確認", found);
	}

	@Test
	public void MailConvertValueMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailTemplateMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailConvertValueMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		MailConvertValueMaster found = mailConvertValueMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getMailControlMaster() == null)
			Assert.assertTrue(false);

		// メール制御マスタからデータ取得
		MailControlMaster found2 = mailControlMasterRepository.findOne(id);
		List<MailConvertValueMaster> result = mailConvertValueMasterRepository.findByMailControlMaster(found2);
		// Entity が null ではないことを確認
		Assert.assertNotNull(result);
	}

	@Test
	public void ProductMasterRepositoryの条件テスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/estimationChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/gpCheckMatterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/extendsParameterCorrelationCheckMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ifsCsvMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorProductMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productPicMaster.sql");

		List<String> appId = Arrays.asList("electric");
		List<ProductMaster> list = productMasterRepository.findByAppIdNotInOrderByIdAsc(appId);
		Assert.assertNotEquals(0, list.size());
		List<Long> id = Arrays.asList(new Long[] { 1L, 2L });
		list = productMasterRepository.findByIdInAndAppIdNotInOrderByIdAsc(id, appId);
		Assert.assertEquals(2, list.size());
		appId = Arrays.asList("cotos_dev");
		list = productMasterRepository.findByAppIdInOrderByIdAsc(appId);
		Assert.assertNotEquals(0, list.size());
		list = productMasterRepository.findByIdInAndAppIdInOrderByIdAsc(id, appId);
		Assert.assertEquals(2, list.size());
		list = productMasterRepository.findByProductClassDiv("RSI");
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void ProductMasterDtoRepositoryの条件テスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/estimationChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/gpCheckMatterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/extendsParameterCorrelationCheckMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ifsCsvMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorProductMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productPicMaster.sql");

		List<String> appId = Arrays.asList("electric");
		List<ProductMasterDto> list = productMasterDtoRepository.findByAppIdNotInOrderByIdAsc(appId);
		Assert.assertNotEquals(0, list.size());
		List<Long> id = Arrays.asList(new Long[] { 1L, 2L });
		list = productMasterDtoRepository.findByIdInAndAppIdNotInOrderByIdAsc(id, appId);
		Assert.assertEquals(2, list.size());
		appId = Arrays.asList("cotos_dev");
		list = productMasterDtoRepository.findByAppIdInOrderByIdAsc(appId);
		Assert.assertNotEquals(0, list.size());
		list = productMasterDtoRepository.findByIdInAndAppIdInOrderByIdAsc(id, appId);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void ArrangementWorkTypeMasterRepositoryの条件テスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementChecklistCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		List<String> appId = Arrays.asList("electric");
		List<ArrangementWorkTypeMaster> list = arrangementWorkTypeMasterRepository.findByAppIdNotInOrderByIdAsc(appId);
		Assert.assertNotEquals(0, list.size());
		List<Long> id = Arrays.asList(new Long[] { 1L, 2L });
		list = arrangementWorkTypeMasterRepository.findByIdInAndAppIdNotInOrderByIdAsc(id, appId);
		Assert.assertEquals(2, list.size());
		appId = Arrays.asList("cotos_dev");
		list = arrangementWorkTypeMasterRepository.findByAppIdInOrderByIdAsc(appId);
		Assert.assertNotEquals(0, list.size());
		list = arrangementWorkTypeMasterRepository.findByArrangementWorkTypeDiv("1");
		Assert.assertNotEquals(0, list.size());
		list = arrangementWorkTypeMasterRepository.findByIdInAndAppIdInOrderByIdAsc(id, appId);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void DummyUserMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dummyUserMaster.sql");

		// エンティティの取得
		Long id = 1L;
		DummyUserMaster found = dummyUserMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// エンティティ取得
		found = dummyUserMasterRepository.findByUserId("COTOS_BATCH_USER");

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void JsonSchemaMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");

		// エンティティの取得
		Long id = 1L;
		JsonSchemaMaster found = jsonSchemaMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		if (found.getProductExtendsParameterMasterList() == null || found.getProductExtendsParameterMasterList().size() == 0)
			Assert.assertTrue(false);
	}

	@Test
	public void ExtendsParameterCorrelationCheckMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/extendsParameterCorrelationCheckMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");

		// エンティティの取得
		ExtendsParameterCorrelationCheckMaster found = extendsParameterCorrelationCheckMasterRepository.findByProductMasterIdAndDomain(1L, "1");

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ContractAutoUpdateMasterRepositoryのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractAutoUpdateMaster.sql");

		// エンティティの取得
		ContractAutoUpdateMaster found = contractAutoUpdateMasterRepository.findByItemMasterId(1003L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ProductExtendsParameterMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ProductExtendsParameterMaster found = productExtendsParameterMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		if (found.getProductMaster() == null)
			Assert.assertTrue(false);
		if (found.getJsonSchemaMaster() == null)
			if (found.getProductMaster() == null)
				Assert.assertTrue(false);
	}

	@Test
	public void ModelAbbreviationMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/modelAbbreviationMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ModelAbbreviationMaster found = modelAbbreviationMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void EquipmentCompMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/equipmentCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		EquipmentCompMaster found = equipmentCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ItemTransCompMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemTransCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ItemTransCompMaster found = itemTransCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ReportTemplateMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/reportTemplateMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ReportTemplateMaster found = reportTemplateMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ReportPageMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/reportTemplateMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/reportPageMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ReportPageMaster found = reportPageMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void MvWjmoc020OrgAllInfoComのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date formatDate = sdf.parse("2019/01/01");
		MvWjmoc020OrgAllInfoCom found = mvWjmoc020OrgAllInfoComRepository.findByOrgId("0913849", formatDate);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void CommonMaster_findByColumnNameAndServiceCategoryAndDetailCodeValuesのテスト() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMasterDetail.sql");

		List<CommonMaster> foundList = commonMasterRepository.findByColumnNameAndServiceCategoryAndDetailCodeValues("issue_format", "1", Arrays.asList("1"));
		// データが取得できていることを確認
		Assert.assertTrue(foundList.size() > 0);

		// Entity の各項目の値が null ではないことを確認
		try {
			testTool.assertColumnsNotNull(foundList.get(0));
		} catch (Exception e) {
			Assert.fail("throw Exception :" + e.getMessage());
		}
	}

	@Test
	public void ReportTemplateMasterRepository_findByReportListParameterのテスト() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/reportTemplateMaster.sql");
		List<ReportTemplateMaster> foundList = reportTemplateMasterRepository.findByReportListParameter("1", "1", "1", "1", 1L, "1", "2");
		// データが取得できていることを確認
		Assert.assertTrue(foundList.size() > 0);

		// Entity の各項目の値が null ではないことを確認
		try {
			testTool.assertColumnsNotNull(foundList.get(0));
		} catch (Exception e) {
			Assert.fail("throw Exception :" + e.getMessage());
		}
	}

	@Test
	public void MvTJmcj005Masterのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String id = "000000004015129";
		MvTJmcj005Master found = mvTJmcj005MasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
		// 判定に使用するカラムを確認
		Assert.assertEquals("支社コードが一致すること", "702", found.getHanshCd());
		Assert.assertEquals("RINGS得意先コードが一致すること", "10027811", found.getRingsTkiskCd());
		Assert.assertEquals("RINGS届先コードが一致すること", "001", found.getRingsTodokesakiCd());
	}

	@Test
	public void MvTJmcj005Master_キーで取得するテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		MvTJmcj005Master found = mvTJmcj005MasterRepository.findByHanshCdAndRingsTkiskCdAndRingsTodokesakiCd("702", "10027811", "001");

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
		// 判定に使用するカラムを確認
		Assert.assertEquals("支社コードが一致すること", "702", found.getHanshCd());
		Assert.assertEquals("RINGS得意先コードが一致すること", "10027811", found.getRingsTkiskCd());
		Assert.assertEquals("RINGS届先コードが一致すること", "001", found.getRingsTodokesakiCd());

		// キー不一致
		found = mvTJmcj005MasterRepository.findByHanshCdAndRingsTkiskCdAndRingsTodokesakiCd("702", "10027811", "002");
		// Entity が null であることを確認
		Assert.assertNull(found);
	}

	@Test
	public void MvTJmcj005Master_販社コードと得意先コードで取得するテスト() throws Exception {

		// エンティティの取得
		List<MvTJmcj005Master> found = mvTJmcj005MasterRepository.findByHanshCdAndRingsTkiskCd("702", "10027811");

		// Entity が 空 ではないことを確認
		Assert.assertFalse(CollectionUtils.isEmpty(found));

		found.stream().forEach(e -> {
			// 判定に使用するカラムを確認
			Assert.assertEquals("支社コードが一致すること", "702", e.getHanshCd());
			Assert.assertEquals("RINGS得意先コードが一致すること", "10027811", e.getRingsTkiskCd());
		});

		// キー不一致
		found = mvTJmcj005MasterRepository.findByHanshCdAndRingsTkiskCd("702", "10027812");
		// Entity が 空 であることを確認
		Assert.assertTrue(CollectionUtils.isEmpty(found));

		// キー不一致
		found = mvTJmcj005MasterRepository.findByHanshCdAndRingsTkiskCd("701", "10027811");
		// Entity が 空 であることを確認
		Assert.assertTrue(CollectionUtils.isEmpty(found));
	}

	@Test
	public void MvTJmcj005Master_OE届け先コードで取得するテスト() throws Exception {

		// エンティティの取得
		List<MvTJmcj005Master> found = mvTJmcj005MasterRepository.findByOeTodokesakiCd("86045030000");

		// Entity が 空 ではないことを確認
		Assert.assertFalse(CollectionUtils.isEmpty(found));

		found.stream().forEach(e -> {
			// 判定に使用するカラムを確認
			Assert.assertEquals("支社コードが一致すること", "101", e.getHanshCd());
			Assert.assertEquals("RINGS得意先コードが一致すること", "10005470", e.getRingsTkiskCd());
		});

		// キー不一致
		found = mvTJmcj005MasterRepository.findByOeTodokesakiCd("86045030001");
		// Entity が 空 であることを確認
		Assert.assertTrue(CollectionUtils.isEmpty(found));

	}

	@Test
	public void MvTJmcj005Master_OriginalSystemCodeで取得するテスト() throws Exception {

		// エンティティの取得
		List<MvTJmcj005Master> found = mvTJmcj005MasterRepository.findByOriginalSystemCode("10110005470");
		// Entity が 空 ではないことを確認
		Assert.assertFalse(CollectionUtils.isEmpty(found));

		// キー不一致
		found = mvTJmcj005MasterRepository.findByOriginalSystemCode("99999999999");
		// Entity が 空 であることを確認
		Assert.assertTrue(CollectionUtils.isEmpty(found));

	}

	@Test
	public void VDirectDeliveryDealerInfoMasterRepositoryのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		VDirectDeliveryDealerInfoMaster.Id id = new VDirectDeliveryDealerInfoMaster.Id("83013", "00");
		VDirectDeliveryDealerInfoMaster found = vDirectDeliveryDealerInfoMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
		// 判定に使用するカラムを確認
		Assert.assertNotNull("売上情報送信年月日がnullでないこと", found.getAdSalesSendDate());
	}

	@Test
	public void MvWjmoco40EmpAllInfoComRepositoryのテスト() {
		final String empId = "00150194";
		MvWjmoco40EmpAllInfoCom found = mvWjmoco40EmpAllInfoComRepository.findOne(empId);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		Assert.assertEquals("Mom会社IDが一致すること", "000010", found.getCorpId());
		Assert.assertEquals("SingleUserIdが一致すること", "u033014739", found.getSingleuserId());
	}

	@Test
	public void MvWjmoco40EmpAllInfoComRepository_findByRingsHanshCdAndRingsEmpCdのテスト() {
		final String ringsHanshCd = "601";
		final String ringsEmpCd = "24396";
		List<MvWjmoco40EmpAllInfoCom> found = mvWjmoco40EmpAllInfoComRepository.findByRingsHanshCdAndRingsEmpCd(ringsHanshCd, ringsEmpCd);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		Assert.assertTrue("Mom会社IDが一致すること", found.stream().anyMatch(f -> f.getEmpId().equals("00150194")));
		Assert.assertTrue("SingleUserIdが一致すること", found.stream().anyMatch(f -> f.getSingleuserId().equals("u033014739")));
	}

	@Test
	public void MvWjmoco40EmpAllInfoComRepository_findByEmailのテスト() {
		final String email = "zpg_vo3is_mom_support_st@nts.ricoh.co.jp";
		List<MvWjmoco40EmpAllInfoCom> found = mvWjmoco40EmpAllInfoComRepository.findByEmail(email);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		Assert.assertTrue("Mom会社IDが一致すること", found.stream().anyMatch(f -> f.getEmpId().equals("00150194")));
		Assert.assertTrue("SingleUserIdが一致すること", found.stream().anyMatch(f -> f.getSingleuserId().equals("u033014739")));
	}

	@Test
	public void VWjmoc080DealerInfoRepositoryのテスト() {
		Id id = new Id();
		id.setDealerDiscrimCd("00006");
		id.setDpCd("00");
		MvWjmoc080DealerInfo found = mvWjmoc080DealerInfoRepository.findOne(id);
		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void ProductGrpMasterRepository_findByProductGroupCdのテスト() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpIdentifierMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		List<String> foundTestString = Arrays.asList("CPG00001");
		List<ProductGrpMaster> foundList = productGrpMasterRepository.findByProductGroupCdIn(foundTestString);

		// データが取得できていることを確認
		Assert.assertTrue(foundList.size() > 0);

		// Entity の各項目の値が null ではないことを確認
		try {
			testTool.assertColumnsNotNull(foundList.get(0));
		} catch (Exception e) {
			Assert.fail("throw Exception :" + e.getMessage());
		}

	}

	@Test
	public void IfsCsvMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ifsCsvMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");

		// エンティティの取得
		Long id = 1L;
		IfsCsvMaster found = ifsCsvMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void MvTJmci108Master_findByCustomerSiteNumberのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String customerSiteNumber = "2526642";
		MvTJmci108Master found = mvTJmci108MasterRepository.findByCustomerSiteNumber(customerSiteNumber);

		// Entity が null ではないことを確認(実装時に販社別届先情報.RINGS届先コードがnullでないデータが存在しなかったため)
		Assert.assertNotNull(found);
	}

	@Test
	public void MvTjmcc020HnbitnMaster_findByMomKgyIdAndHanshCdAndNendoのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String momKgyId = "000000000012704";
		String hanshCd = "408";
		String nendo = "2011";
		MvTjmcc020HnbitnMaster found = mvTjmcc020HnbitnMasterRepository.findByMomKgyIdAndHanshCdAndNendo(momKgyId, hanshCd, nendo);

		// Entity が null ではないことを確認(実装時に販社別届先情報.RINGS届先コードがnullでないデータが存在しなかったため)
		Assert.assertNotNull(found);
	}

	@Test
	public void MvVjmcb010MomKgyMaster_findByMomKishIdのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String momKishId = "000001";
		MvVjmcb010MomKgyMaster found = mvVjmcb010MomKgyMasterRepository.findByMomKishId(momKishId);

		// Entity が null ではないことを確認(実装時に販社別届先情報.RINGS届先コードがnullでないデータが存在しなかったため)
		Assert.assertNotNull(found);

		found = mvVjmcb010MomKgyMasterRepository.findByMomKishIdAndCuicMcdbDltFlg(momKishId, "0");

		// Entity が null ではないことを確認(実装時に販社別届先情報.RINGS届先コードがnullでないデータが存在しなかったため)
		Assert.assertNotNull(found);
	}

	@Test
	public void ProductGrpMaster_findByProductGroupCdのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteNodeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpIdentifierMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");

		// エンティティの取得
		String productGroupCd = "CPG00001";
		ProductGrpMaster found = productGrpMasterRepository.findByProductGroupCd(productGroupCd);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getContractApprovalRouteGrpMaster() == null)
			Assert.assertTrue(false);
		if (found.getEstimationApprovalRouteGrpMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void VKjbMaster_findFirstByMclMomKjbIdAndMclDltFlgOrderByMclUpdateDtのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		String mclMomKjbId = "000000002773772";
		String mclDltFlg = "0";
		List<VKjbMaster> found = vKjbMasterRepository.findByMclMomKjbIdAndMclDltFlgAndMaxMclUpdateDt(mclMomKjbId, mclDltFlg);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void NonBusinessDayCalendarMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/nonBusinessDayCalendarMaster.sql");

		// エンティティの取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = sdf.parse("2019/01/01");
		NonBusinessDayCalendarMaster found = nonBusinessDayCalendarMasterRepository.findOne(date);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LedgerMasterRepositoryのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ledgerMaster.sql");

		// エンティティの取得
		List<LedgerMaster> found = ledgerMasterRepository.findByProductMasterId(1001L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// 2件取得できることを確認
		Assert.assertEquals(found.size(), 2);

		// Entity の各項目の値が null ではないことを確認
		found.stream().forEach(foundOne -> {
			try {
				testTool.assertColumnsNotNull(foundOne);
			} catch (Exception e) {
				Assert.fail("例外が発生した場合、エラー");
			}
		});
	}

	@Test
	public void MailProductMasterRepositoryのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailProductMasterRepository.sql");

		// エンティティの取得
		MailProductMaster found = mailProductMasterRepository.findOne(1001L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void CeMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ceMaster.sql");

		// エンティティの取得
		Long id = 1L;
		CeMaster found = ceMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void AttachedFileLinkageのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");

		// エンティティの取得
		Long id = 1L;
		AttachedFileLinkage found = attachedFileLinkageRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void VendorProductMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorProductMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");

		// エンティティの取得
		Long id = 1L;
		VendorProductMaster found = vendorProductMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void VendorMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorMaster.sql");

		// エンティティの取得
		Long id = 1L;
		VendorMaster found = vendorMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ProductPicMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productPicMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ProductPicMaster found = productPicMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void CheckAlertTargetMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkAlertMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkAlertTargetMaster.sql");

		// エンティティの取得
		Long id = 1L;
		CheckAlertTargetMaster found = checkAlertTargetMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void CheckAlertMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkAlertMaster.sql");

		// エンティティの取得
		Long id = 1L;
		CheckAlertMaster found = checkAlertMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void CheckAlertMaster_findByDomainAndTargetMasterIdのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkAlertMaster_findByDomainAndTargetMasterId.sql");

		// エンティティの取得
		String serviceCategory = "1"; //見積
		Long targetMasterId = 300L; //
		List<CheckAlertMaster> found = checkAlertMasterRepository.findByDomainAndTargetMasterId(serviceCategory, targetMasterId);

		// Entity が null ではないことを確認
		// 2件取得されることを確認
		Assert.assertEquals(2, found.size());

		List<CheckAlertMaster> foundSorted = found.stream().sorted(Comparator.comparing(CheckAlertMaster::getId)).collect(Collectors.toList());

		CheckAlertMaster common = foundSorted.get(0); //1件目：共通

		testTool.assertColumnsNotNull(common);
		Assert.assertEquals(1L, common.getId());

		CheckAlertMaster byProduct = foundSorted.get(1); //2件目：商品毎

		testTool.assertColumnsNotNull(byProduct);
		Assert.assertEquals(3L, byProduct.getId());

		// 対象アラートマスタが1件取得されていることを確認
		List<CheckAlertTargetMaster> list = byProduct.getCheckAlertTargetMasterList();
		Assert.assertEquals(1, list.size());

		val targetRecord = list.get(0);
		testTool.assertColumnsNotNull(targetRecord);
		// Entity の各項目の値が null ではないことを確認

	}

	@Test
	public void CheckByItemMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		CheckByItemMaster found = checkByItemMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void CommonMasterDetail_findByCommonMasterIdAndAvailablePeriodBetweenのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMasterDetail.sql");

		List<CommonMasterDetail> foundList = commonMasterDetailRepository.findByCommonMasterIdAndAvailablePeriodBetween(11L, "20200101");
		// データが取得できていることを確認
		Assert.assertTrue(foundList.size() > 0);

		// Entity の各項目の値が null ではないことを確認
		try {
			testTool.assertColumnsNotNull(foundList.get(0));
		} catch (Exception e) {
			Assert.fail("throw Exception :" + e.getMessage());
		}
	}

	@Test
	public void ArrangementWorkOrderMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkOrderMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ArrangementWorkOrderMaster found = arrangementWorkOrderMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の エンティティクラスの項目の値が null ではないことを確認
		if (found.getProductMaster() == null)
			Assert.assertTrue(false);
		if (found.getArrangementWorkTypeMaster() == null)
			Assert.assertTrue(false);

		// 追加したSelect文を使用したエンティティの取得
		found = null;
		List<ArrangementWorkOrderMaster> list = arrangementWorkOrderMasterRepository.findByProductMasterIdAndContractTypeAndDisengagementFlgAndArrangementWorkTypeMasterIdAndCheckTimingType(2L, ContractType.情報変更.toString(), 1, 1L, CheckTimingType.業務完了時.toString());
		found = list.get(0);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の エンティティクラスの項目の値が null ではないことを確認
		if (found.getProductMaster() == null)
			Assert.assertTrue(false);
		if (found.getArrangementWorkTypeMaster() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void AttachedFileProductClassCheckMaster_findAttachedFileProductClassCheckListのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileProductClassCheckMaster.sql");

		// エンティティの取得
		List<AttachedFileProductClassCheckMaster> foundList = attachedFileProductClassCheckMasterRepository.findAttachedFileProductClassCheckList("CSP", "1", "1", "7").get();

		// データが4件取得できていることを確認
		Assert.assertEquals(4, foundList.size());
		// 取得したデータの内容が正しいことを確認
		for (AttachedFileProductClassCheckMaster found : foundList) {
			Assert.assertEquals("CSP", found.getProductClassDiv());
			Assert.assertEquals("1", found.getDomain());
			Assert.assertThat(found.getEstimationContractType(), anyOf(nullValue(), is("1")));
			Assert.assertThat(found.getLifecycleStatus(), anyOf(nullValue(), is("7")));
			Assert.assertThat(found.getExcludeProductGrpMasterId(), anyOf(nullValue(), is("1008,1009")));
			Assert.assertThat(found.getArrangementWorkTypeMasterId(), anyOf(nullValue(), is(1001L), is(1002L)));
			Assert.assertEquals("xlsx", found.getExtension());
		}

	}

	@Test
	public void AttachedFileProductClassCheckMaster_findAttachedFileProductClassCheckListByArrangementWorkTypeMasterIdのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileProductClassCheckMaster.sql");

		// エンティティの取得
		List<AttachedFileProductClassCheckMaster> foundList = attachedFileProductClassCheckMasterRepository.findAttachedFileProductClassCheckListByArrangementWorkTypeMasterId("CSP", "1", "1", "7", 1001L).get();

		// データが2件取得できていることを確認
		Assert.assertEquals(2, foundList.size());
		// 取得したデータの内容が正しいことを確認
		for (AttachedFileProductClassCheckMaster found : foundList) {
			Assert.assertEquals("CSP", found.getProductClassDiv());
			Assert.assertEquals("1", found.getDomain());
			Assert.assertThat(found.getEstimationContractType(), anyOf(nullValue(), is("1")));
			Assert.assertThat(found.getLifecycleStatus(), anyOf(nullValue(), is("7")));
			Assert.assertThat(found.getExcludeProductGrpMasterId(), anyOf(nullValue(), is("1008,1009")));
			Assert.assertThat(found.getArrangementWorkTypeMasterId(), anyOf(nullValue(), is(1001L)));
			Assert.assertEquals("xlsx", found.getExtension());
		}

	}

	@Test
	public void AttachedFileProductGrpCheckMaster_findAttachedFileProductGrpCheckListのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileProductGrpCheckMaster.sql");

		// エンティティの取得
		List<AttachedFileProductGrpCheckMaster> foundList = attachedFileProductGrpCheckMasterRepository.findAttachedFileProductGrpCheckList(200L, "2", "1", "7", Arrays.asList(1L, 2L)).get();

		// データが4件取得できていることを確認
		Assert.assertEquals(4, foundList.size());
		// 取得したデータの内容が正しいことを確認
		for (AttachedFileProductGrpCheckMaster found : foundList) {
			Assert.assertEquals(Long.valueOf(200), found.getProductGrpMasterId());
			Assert.assertEquals("2", found.getDomain());
			Assert.assertThat(found.getEstimationContractType(), anyOf(nullValue(), is("1")));
			Assert.assertThat(found.getLifecycleStatus(), anyOf(nullValue(), is("7")));
			Assert.assertNotNull(found.getFileKind());
			Assert.assertThat(found.getItemMasterId(), anyOf(nullValue(), is(1L), is(2L)));
			Assert.assertThat(found.getArrangementWorkTypeMasterId(), anyOf(nullValue(), is(1001L), is(1002L)));
			Assert.assertEquals("xlsx", found.getExtension());
		}
	}

	@Test
	public void AttachedFileProductGrpCheckMaster_findAttachedFileProductGrpCheckListByArrangementWorkTypeMasterIdのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileProductGrpCheckMaster.sql");

		// エンティティの取得
		List<AttachedFileProductGrpCheckMaster> foundList = attachedFileProductGrpCheckMasterRepository.findAttachedFileProductGrpCheckListByArrangementWorkTypeMasterId(200L, "2", "1", "7", Arrays.asList(1L, 2L), 1001L).get();

		// データが3件取得できていることを確認
		Assert.assertEquals(3, foundList.size());
		// 取得したデータの内容が正しいことを確認
		for (AttachedFileProductGrpCheckMaster found : foundList) {
			Assert.assertEquals(Long.valueOf(200), found.getProductGrpMasterId());
			Assert.assertEquals("2", found.getDomain());
			Assert.assertThat(found.getEstimationContractType(), anyOf(nullValue(), is("1")));
			Assert.assertThat(found.getLifecycleStatus(), anyOf(nullValue(), is("7")));
			Assert.assertNotNull(found.getFileKind());
			Assert.assertThat(found.getItemMasterId(), anyOf(nullValue(), is(1L), is(2L)));
			Assert.assertThat(found.getArrangementWorkTypeMasterId(), anyOf(nullValue(), is(1001L)));
			Assert.assertEquals("xlsx", found.getExtension());
		}
	}

	@Test
	public void FileKindManagementMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/fileKindManagementMaster.sql");

		// エンティティの取得
		Long id = 1L;
		FileKindManagementMaster found = fileKindManagementMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// ファイル種別タイプ区分
		found = fileKindManagementMasterRepository.findByFileKindTypeDiv("test_file_kind_type_div").get(0);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ArrangementWorkTypeForSearchMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeForSearchMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ArrangementWorkTypeForSearchMaster found = arrangementWorkTypeForSearchMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void FileOperationRelationProductMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/fileOperationRelationProductMaster.sql");

		// エンティティの取得
		Long id = 1L;
		FileOperationRelationProductMaster found = fileOperationRelationProductMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ShippingThingMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/shippingThingMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ifsCsvMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorProductMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ShippingThingMaster found = shippingThingMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ShippingPostNumberMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/shippingPostNumberMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ShippingPostNumberMaster found = shippingPostNumberMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void MenuManagementMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/menuManagementMaster.sql");

		// エンティティの取得
		Long id = 1L;
		MenuManagementMaster found = menuManagementMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void DateCalcPatternMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		DateCalcPatternMaster found = dateCalcPatternMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ItemDecomposeMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemDecomposeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ifsCsvMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorProductMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ItemDecomposeMaster found = itemDecomposeMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ContractChangeSpanMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/contractChangeSpanMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ContractChangeSpanMaster found = contractChangeSpanMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// 結合するテーブルの外部KEYがNULLの場合、エンティティが取得されること
		id = 2L;
		found = contractChangeSpanMasterRepository.findOne(id);
		Assert.assertNotNull(found);
	}

	@Test
	public void LicenseProcessControlMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseProcessControlMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseProcessControlMaster found = licenseProcessControlMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseProcessMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseProcessMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseProcessMaster found = licenseProcessMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		List<LicenseProcessMaster> foundList = licenseProcessMasterRepository.findByLicenseDivMasterIdOrderById(3);
		// Entityが指定件数取得できていることを確認する
		Assert.assertEquals(foundList.size(), 2);
		// OrderBy句の確認
		Assert.assertEquals(foundList.get(0).getId(), 10);
		Assert.assertEquals(foundList.get(1).getId(), 11);
	}

	@Test
	public void LicenseProcessPatternMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseProcessPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseProcessPatternMaster found = licenseProcessPatternMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseDivMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseDivMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseDivMaster found = licenseDivMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseDivCompMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseDivCompMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseDivCompMaster found = licenseDivCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseAccountDivMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseAccountDivMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseAccountDivMaster found = licenseAccountDivMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseAccountDivCompMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseAccountDivCompMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseAccountDivCompMaster found = licenseAccountDivCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseServiceMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseServiceMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseServiceMaster found = licenseServiceMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseServiceCompMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseServiceCompMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseServiceCompMaster found = licenseServiceCompMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void MailMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailMaster.sql");

		// エンティティの取得
		Long id = 1L;
		MailMaster found = mailMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void MailAddressMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/mailAddressMaster.sql");

		// エンティティの取得
		Long id = 2L;
		MailAddressMaster found = mailAddressMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void LicenseArrangementMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/licenseArrangementMaster.sql");

		// エンティティの取得
		Long id = 1L;
		LicenseArrangementMaster found = licenseArrangementMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ItemLicenseSettingMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemLicenseSettingMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productExtendsParameterMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/recordDecomposeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/ifsCsvMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/vendorProductMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ItemLicenseSettingMaster found = itemLicenseSettingMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void CsvFileSettingMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/csvFileSettingMaster.sql");

		// エンティティの取得
		Long id = 1L;
		CsvFileSettingMaster found = csvFileSettingMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void MenuDetailsManagementMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/menuManagementMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/menuDetailsManagementMaster.sql");

		// エンティティの取得
		Long id = 1L;
		MenuDetailsManagementMaster found = menuDetailsManagementMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ItemTransCompMaster_findByItemMasterIdのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemTransCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/approvalRouteGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/checkByItemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/dateCalcPatternMaster.sql");

		// エンティティの取得
		List<ItemTransCompMaster> foundList = itemTransCompMasterRepository.findByItemMasterId(1L);

		// データが1件取得できていることを確認
		Assert.assertEquals(1, foundList.size());
	}

	@Test
	public void MvTjmob260OrgServiceMasterのテスト() throws Exception {

		// MoMから作成したMViewのためテストデータはなし

		// エンティティの取得
		String id = "4080772";
		List<MvTjmob260OrgServiceMaster> found = mvTjmob260OrgServiceMasterRepository.findByIdOrsCubicOrgId(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	@Ignore
	public void MvRjShohinInfoMasterのテスト() throws Exception {

		// MoMから作成したMViewのためテストデータはなし

		// エンティティの取得
		String id = "312982";
		MvRjShohinInfoMaster found = mvRjShohinInfoMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void BatchRunDateManagementMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/batchRunDateManagementMaster.sql");

		// エンティティの取得
		Long id = 1L;
		BatchRunDateManagementMaster found = batchRunDateManagementMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void EmpGrpManagementMasterのテスト() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/empGrpManagementMaster.sql");

		// エンティティの取得
		Long id = 1L;
		EmpGrpManagementMaster found = empGrpManagementMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void EmpGrpManagementMaster_findByGroupCodeのテスト() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/empGrpManagementMaster.sql");

		List<EmpGrpManagementMaster> foundList = empGrpManagementMasterRepository.findByGroupCode("A0001");
		// データが取得できていることを確認
		Assert.assertTrue(foundList.size() > 0);

		// Entity の各項目の値が null ではないことを確認
		try {
			testTool.assertColumnsNotNull(foundList.get(0));
		} catch (Exception e) {
			Assert.fail("throw Exception :" + e.getMessage());
		}
	}

	@Test
	public void ArrangementWorkAuthControlMasterのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkTypeMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/arrangementWorkAuthControlMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/authPatternMaster.sql");

		// エンティティの取得
		Long id = 1L;
		ArrangementWorkAuthControlMaster found = arrangementWorkAuthControlMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の リストとエンティティクラスの項目の値が null ではないことを確認
		if (found.getArrangementWorkTypeMasterList() == null)
			Assert.assertTrue(false);
	}

	@Test
	public void EnumDefinitionMasterRepositoryのテスト() throws Exception {

		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/enumDefinitionMaster.sql");

		//エンティティの取得
		Long id = 1L;
		EnumDefinitionMaster found = enumDefinitionMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// 取得したデータの内容が正しいことを確認
		Assert.assertEquals("Contract", found.getClassName());
		Assert.assertEquals("text", found.getFieldName());
		Assert.assertEquals("contract", found.getTableName());
		Assert.assertEquals("text", found.getColumnName());
		Assert.assertEquals("契約種別", found.getEnumName());
		Assert.assertEquals("新規", found.getEnumValueName());
		Assert.assertEquals("1", found.getEnumValueCode());
		Assert.assertEquals("ContractType", found.getEnumClassName());
	}

	@Test
	public void MvTjmoc290SsMasterのテスト() throws Exception {

		// テストデータはなし

		// エンティティの取得
		long id = 1L;
		MvTjmoc290SsMaster found = mvTjmoc290SsMasterRepository.findOne(id);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}
}
