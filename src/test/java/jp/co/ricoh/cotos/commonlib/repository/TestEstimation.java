package jp.co.ricoh.cotos.commonlib.repository;

import java.util.Arrays;
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
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.db.DBUtil;
import jp.co.ricoh.cotos.commonlib.entity.estimation.CustomerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.DealerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ElectronicContractInfo;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationAddedEditorEmp;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalResult;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalRouteNode;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationCheckResult;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationPicSaEmp;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ItemEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.OperationLog;
import jp.co.ricoh.cotos.commonlib.entity.estimation.PenaltyDetailEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ProductEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.SeOperationHistory;
import jp.co.ricoh.cotos.commonlib.entity.estimation.VupCaseWork;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateCalcPatternUtil;
import jp.co.ricoh.cotos.commonlib.repository.estimation.CustomerEstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.DealerEstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.ElectronicContractInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationAddedEditorEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationApprovalResultRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationApprovalRouteNodeRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationApprovalRouteRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationAttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationCheckResultRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationPicSaEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.ItemEstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.OperationLogRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.PenaltyDetailEstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.ProductEstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.SeOperationHistoryRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.VupCaseWorkRepository;

/**
 * Repository（見積ドメイン）のテストクラス
 *
 * @author kentaro.kakuhana
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestEstimation {

	static ConfigurableApplicationContext context;

	@Autowired
	TestTools testTool;

	@Autowired
	EstimationAttachedFileRepository estimationAttachedFileRepository;

	@Autowired
	OperationLogRepository operationLogRepository;

	@Autowired
	EstimationAddedEditorEmpRepository estimationAddedEditorEmpRepository;

	@Autowired
	DealerEstimationRepository dealerEstimationRepository;

	@Autowired
	EstimationCheckResultRepository estimationCheckResultRepository;

	@Autowired
	EstimationDetailRepository estimationDetailRepository;

	@Autowired
	ProductEstimationRepository productEstimationRepository;

	@Autowired
	EstimationApprovalResultRepository estimationApprovalResultRepository;

	@Autowired
	EstimationApprovalRouteNodeRepository estimationApprovalRouteNodeRepository;

	@Autowired
	CustomerEstimationRepository customerEstimationRepository;

	@Autowired
	EstimationPicSaEmpRepository estimationPicSaEmpRepository;

	@Autowired
	EstimationApprovalRouteRepository estimationApprovalRouteRepository;

	@Autowired
	ItemEstimationRepository itemEstimationRepository;

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	PenaltyDetailEstimationRepository penaltyDetailEstimationRepository;

	@Autowired
	VupCaseWorkRepository vupCaseWorkRepository;

	@Autowired
	ElectronicContractInfoRepository electronicContractInfoRepository;

	@Autowired
	SeOperationHistoryRepository seOperationHistoryRepository;

	@Autowired
	private DateCalcPatternUtil dateCalcPatternUtil;

	@Autowired
	DBUtil dbutil;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/estimation/estimation_all.sql");
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

		EstimationAttachedFile found = estimationAttachedFileRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		List<EstimationAttachedFile> foundList = estimationAttachedFileRepository.findByEstimationId(4L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

		// Entity 1件以上取得できていることを確認
		Assert.assertNotEquals(foundList.size(), 0);
	}

	@Test
	public void OperationLogRepositoryのテスト() throws Exception {

		OperationLog found = operationLogRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void EstimationAddedEditorEmpRepositoryのテスト() throws Exception {

		EstimationAddedEditorEmp found = estimationAddedEditorEmpRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void DealerEstimationRepositoryのテスト() throws Exception {

		DealerEstimation found = dealerEstimationRepository.findOne(402L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void EstimationCheckResultRepositoryのテスト() throws Exception {

		EstimationCheckResult found = estimationCheckResultRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void EstimationDetailRepositoryのテスト() throws Exception {

		EstimationDetail found = estimationDetailRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void ProductEstimationRepositoryのテスト() throws Exception {

		ProductEstimation found = productEstimationRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void EstimationApprovalResultRepositoryのテスト() throws Exception {

		EstimationApprovalResult found = estimationApprovalResultRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void EstimationApprovalRouteNodeRepositoryのテスト() throws Exception {

		EstimationApprovalRouteNode found = estimationApprovalRouteNodeRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		found = estimationApprovalRouteNodeRepository.findByEstimationApprovalRouteIdAndApprovalOrderAndApproverEmpId(401L, 1, "00808347");
		Assert.assertNotNull(found);

	}

	@Test
	public void CustomerEstimationRepositoryのテスト() throws Exception {

		CustomerEstimation found = customerEstimationRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void EstimationPicSaEmpRepositoryのテスト() throws Exception {

		EstimationPicSaEmp found = estimationPicSaEmpRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void EstimationApprovalRouteRepositoryのテスト() throws Exception {

		EstimationApprovalRoute found = estimationApprovalRouteRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		found = estimationApprovalRouteRepository.findByEstimationId(4L);
		Assert.assertNotNull(found);
	}

	@Test
	public void ItemEstimationRepositoryのテスト() throws Exception {

		ItemEstimation found = itemEstimationRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void EstimationRepositoryのテスト() throws Exception {

		Estimation found = estimationRepository.findOne(4L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

	}

	@Test
	public void EstimationRepositoryの条件テスト() throws Exception {

		List<String> appId = Arrays.asList("electric");
		Estimation found = estimationRepository.findByIdAndAppIdNotIn(4L, appId);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		appId = Arrays.asList("cotos_dev");
		found = estimationRepository.findByIdAndAppIdIn(4L, appId);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void PenaltyDetailEstimationRepositoryのテスト() throws Exception {

		PenaltyDetailEstimation found = penaltyDetailEstimationRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void VupCaseWorkRepositoryのテスト() throws Exception {

		VupCaseWork found = vupCaseWorkRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void ElectronicContractInfoRepositoryのテスト() throws Exception {

		ElectronicContractInfo found = electronicContractInfoRepository.findOne(401L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		found = electronicContractInfoRepository.findByEstimationId(4L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);
	}

	@Test
	public void DBUtilのメソッドテスト() throws Exception {

		String sql = "SELECT * FROM ESTIMATION";
		List<Estimation> foundList = dbutil.executeSelectWithSQL(sql);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundList);

		// Entity 1件以上取得できていることを確認
		Assert.assertNotEquals(foundList.size(), 0);

	}

	@Test
	@WithMockCustomUser
	@Transactional
	public void EstimationRepositorySaveのテスト() throws Exception {

		Estimation found = estimationRepository.findOne(4L);
		found.setEstimationTitle("テスト見積タイトル");
		estimationRepository.save(found);
		Estimation foundUpd = estimationRepository.findOne(4L);
		Assert.assertEquals("正しく更新されていること", "テスト見積タイトル", foundUpd.getEstimationTitle());

	}

	@Test
	@WithMockCustomUser
	@Transactional
	public void EstimationPicSaEmpRepositorySaveのテスト() throws Exception {

		EstimationPicSaEmp found = estimationPicSaEmpRepository.findOne(401L);
		found.setAddress("テスト住所");
		estimationPicSaEmpRepository.save(found);
		EstimationPicSaEmp foundUpd = estimationPicSaEmpRepository.findOne(401L);
		Assert.assertEquals("正しく更新されていること", "テスト住所", foundUpd.getAddress());

	}

	@Test
	@WithMockCustomUser
	@Transactional
	public void CustomerEstimationRepositorySaveのテスト() throws Exception {

		CustomerEstimation found = customerEstimationRepository.findOne(401L);
		found.setAddress("テスト住所");
		customerEstimationRepository.save(found);
		CustomerEstimation foundUpd = customerEstimationRepository.findOne(401L);
		Assert.assertEquals("正しく更新されていること", "テスト住所", foundUpd.getAddress());

	}

	@Test
	@WithMockCustomUser
	@Transactional
	public void EstimationApprovalRouteRepositorySaveのテスト() throws Exception {

		EstimationApprovalRoute found = estimationApprovalRouteRepository.findOne(401L);
		found.setSpecialPriceApprovalFlg(2);
		estimationApprovalRouteRepository.save(found);
		EstimationApprovalRoute foundUpd = estimationApprovalRouteRepository.findOne(401L);
		Assert.assertEquals("正しく更新されていること", 2, foundUpd.getSpecialPriceApprovalFlg());

	}

	@Test
	@WithMockCustomUser
	@Transactional
	public void EstimationApprovalRouteRepositorySaveのテスト_削除() throws Exception {

		EstimationApprovalRoute found = estimationApprovalRouteRepository.findOne(401L);
		estimationApprovalRouteRepository.delete(found);
		EstimationApprovalRoute foundDel = estimationApprovalRouteRepository.findOne(401L);
		Assert.assertNull("削除されていること", foundDel);

	}

	@Test
	@WithMockCustomUser
	@Transactional
	public void ItemEstimationRepositorySaveのテスト() throws Exception {

		ItemEstimation found = itemEstimationRepository.findOne(401L);
		found.setMakerItemCode("テストコード");
		itemEstimationRepository.save(found);
		ItemEstimation foundUpd = itemEstimationRepository.findOne(401L);
		Assert.assertEquals("正しく更新されていること", "テストコード", foundUpd.getMakerItemCode());

	}

	@Test
	public void SeOperationHistoryRepositoryのテスト() throws Exception {

		SeOperationHistory found = seOperationHistoryRepository.findOne(1234L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		// Entity の各項目の値が期待値と一致しているか確認
		// 期待値をDate型に変換
		String testDate = "2018-09-19 12:09:10.0";
		java.util.Date parsedTestDate = dateCalcPatternUtil.stringToDateConverter(testDate, "yyyy-MM-dd HH:mm:ss.S");

		Assert.assertEquals(2, found.getEstimationId());
		Assert.assertEquals("1", found.getDomain().toString());
		Assert.assertEquals("1", found.getProcessingCategory().toString());
		Assert.assertEquals("見積番号更新(test→TEST)", found.getProcessingDetails());
		Assert.assertEquals(parsedTestDate, found.getExpirationFrom());
		Assert.assertEquals(parsedTestDate, found.getExpirationTo());

	}

}
