package jp.co.ricoh.cotos.commonlib.check;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ProductContract;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ProductEstimation;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;

/**
 * 月ずれチェックメソッドのテストクラス
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestMigrationDataCheck {

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	ContractRepository contractRepository;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/check/testMigrationDataCheck.sql");
	}

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
	public void 正常系_見積_標準データ() {

		Estimation estimation = estimationRepository.findOne(4L);
		boolean result = checkUtil.migrationDataCheck(estimation);
		Assert.assertFalse(result);
	}

	@Test
	public void 正常系_見積_RITOS移行データ() {

		Estimation estimation = estimationRepository.findOne(5L);
		boolean result = checkUtil.migrationDataCheck(estimation);
		Assert.assertTrue(result);
	}

	@Test
	public void 正常系_契約_標準データ() {

		Contract contract = contractRepository.findOne(4L);
		boolean result = checkUtil.migrationDataCheck(contract);
		Assert.assertFalse(result);
	}

	@Test
	public void 正常系_契約_RITOS移行データ() {

		Contract contract = contractRepository.findOne(5L);
		boolean result = checkUtil.migrationDataCheck(contract);
		Assert.assertTrue(result);
	}


	@Test
	public void 異常系_見積_見積がNULL() {
		Estimation estimation = null;
		try {
			checkUtil.migrationDataCheck(estimation);
			fail("正常終了");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00001", errorList.get(0).getErrorId());
			Assert.assertEquals("パラメータ「見積」が設定されていません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_見積_商品見積用がNULL() {
		Estimation estimation = new Estimation();
		estimation.setProductEstimationList(new ArrayList<ProductEstimation>());
		try {
			checkUtil.migrationDataCheck(estimation);
			fail("正常終了");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00001", errorList.get(0).getErrorId());
			Assert.assertEquals("パラメータ「商品（見積用）」が設定されていません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_契約_契約がNULL() {
		Contract contract = null;
		try {
			checkUtil.migrationDataCheck(contract);
			fail("正常終了");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00001", errorList.get(0).getErrorId());
			Assert.assertEquals("パラメータ「契約」が設定されていません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_契約_商品契約用がNULL() {
		Contract contract = new Contract();
		contract.setProductContractList(new ArrayList<ProductContract>());
		try {
			checkUtil.migrationDataCheck(contract);
			fail("正常終了");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00001", errorList.get(0).getErrorId());
			Assert.assertEquals("パラメータ「商品（契約用）」が設定されていません。", errorList.get(0).getErrorMessage());
		}
	}
}
