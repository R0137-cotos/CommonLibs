package jp.co.ricoh.cotos.commonlib.entity;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationDto;
import jp.co.ricoh.cotos.commonlib.entity.estimation.CustomerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationPicSaEmp;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ItemEstimation;
import jp.co.ricoh.cotos.commonlib.repository.estimation.CustomerEstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationApprovalRouteRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationAttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationPicSaEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.ItemEstimationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestEstimationHashCode {

	static ConfigurableApplicationContext context;

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	CustomerEstimationRepository customerEstimationRepository;

	@Autowired
	EstimationApprovalRouteRepository estimationApprovalRouteRepository;

	@Autowired
	EstimationAttachedFileRepository estimationAttachedFileRepository;

	@Autowired
	EstimationDetailRepository estimationDetailRepository;

	@Autowired
	EstimationPicSaEmpRepository estimationPicSaEmpRepository;

	@Autowired
	ItemEstimationRepository itemEstimationRepository;

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
	public void Estimationのテスト() throws Exception {

		try {
			Estimation found = estimationRepository.findById(4L).get();
			found.hashCode();
		} catch (StackOverflowError e) {
			Assert.fail("エラー発生");
		}
	}

	@Test
	public void CustomerEstimationのテスト() throws Exception {

		try {
			CustomerEstimation found = customerEstimationRepository.findById(401L).get();
			found.hashCode();
		} catch (StackOverflowError e) {
			Assert.fail("エラー発生");
		}
	}

	@Test
	public void EstimationApprovalRouteのテスト() throws Exception {

		try {
			EstimationApprovalRoute found = estimationApprovalRouteRepository.findById(401L).get();
			found.hashCode();
		} catch (StackOverflowError e) {
			Assert.fail("エラー発生");
		}
	}

	@Test
	public void EstimationAttachedFileのテスト() throws Exception {

		try {
			EstimationAttachedFile found = estimationAttachedFileRepository.findById(401L).get();
			found.hashCode();
		} catch (StackOverflowError e) {
			Assert.fail("エラー発生");
		}
	}

	@Test
	public void EstimationDetailのテスト() throws Exception {

		try {
			EstimationDetail found = estimationDetailRepository.findById(401L).get();
			found.hashCode();
		} catch (StackOverflowError e) {
			Assert.fail("エラー発生");
		}
	}

	@Test
	public void EstimationPicSaEmpのテスト() throws Exception {

		try {
			EstimationPicSaEmp found = estimationPicSaEmpRepository.findById(401L).get();
			found.hashCode();
		} catch (StackOverflowError e) {
			Assert.fail("エラー発生");
		}
	}

	@Test
	public void ItemEstimationのテスト() throws Exception {

		try {
			ItemEstimation found = itemEstimationRepository.findById(401L).get();
			found.hashCode();
		} catch (StackOverflowError e) {
			Assert.fail("エラー発生");
		}
	}

	@Test
	public void EstimationDtoのテスト() throws Exception {

		try {
			Estimation found1 = estimationRepository.findById(4L).get();
			EstimationDto found2 = new EstimationDto();
			BeanUtils.copyProperties(found1, found2, "estimationAttachedFileList", "estimationAddedEditorEmpList", "dealerEstimationList", "estimationCheckResultList", "estimationDetailList", "productEstimationList", "penaltyDetailEstimationList");
			found2.hashCode();
		} catch (StackOverflowError e) {
			Assert.fail("エラー発生");
		}
	}
}
