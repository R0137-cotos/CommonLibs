package jp.co.ricoh.cotos.commonlib.penalty;

import static org.junit.Assert.*;

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
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.logic.penalty.PenaltyUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.ItemMasterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestPenaltyCheck {

	static ConfigurableApplicationContext context;

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	PenaltyUtil penaltyUtil;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	ItemMasterRepository itemMasterRepository;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("penalty/penalty.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void 正常_違約金発生無し_契約_起算日区分1_違約金有無フラグ無しテスト() throws Exception {
		final long contractId = 1L;
		final long itemMasterId = 16125L; // 違約金有無フラグ=「0：無し」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生無し_契約_起算日区分1_違約金有無フラグ有りテスト() throws Exception {
		final long contractId = 1L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生無し_契約_起算日区分1_違約金有無フラグNULLテスト() throws Exception {
		final long contractId = 1L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分1_違約金有無フラグ無しテスト() throws Exception {
		final long contractId = 2L;
		final long itemMasterId = 16125L; // 違約金有無フラグ=「0：無し」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分1_違約金有無フラグ有りテスト() throws Exception {
		final long contractId = 2L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNotNull("違約金チェック結果がNULLでないこと", actual);
		Assert.assertEquals("違約金チェック結果の品種マスタIDを確認", itemMasterId, actual.getId());

	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分1_違約金有無フラグNULLテスト() throws Exception {
		final long contractId = 2L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生無し_契約_起算日区分2_違約金有無フラグ無しテスト() throws Exception {
		final long contractId = 3L;
		final long itemMasterId = 16147L; // 違約金有無フラグ=「0：無し」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	@Test
	public void 正常_違約金発生無し_契約_起算日区分2_違約金有無フラグ有りテスト() throws Exception {
		final long contractId = 3L;
		final long itemMasterId = 16148L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	@Test
	public void 正常_違約金発生無し_契約_起算日区分2_違約金有無フラグNULLテスト() throws Exception {
		final long contractId = 3L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分2_違約金有無フラグ無しテスト() throws Exception {
		final long contractId = 4L;
		final long itemMasterId = 16147L; // 違約金有無フラグ=「0：無し」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分2_違約金有無フラグ有りテスト() throws Exception {
		final long contractId = 4L;
		final long itemMasterId = 16148L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNotNull("違約金チェック結果がNULLでないこと", actual);
		Assert.assertEquals("違約金チェック結果の品種マスタIDを確認", itemMasterId, actual.getId());
	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分2_違約金有無フラグNULLテスト() throws Exception {
		final long contractId = 4L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	// 見積テスト
	@Test
	public void 正常_違約金発生無し_見積_起算日区分1_違約金有無フラグ無しテスト() throws Exception {
		final long estimationId = 1L;
		final long itemMasterId = 16125L; // 違約金有無フラグ=「0：無し」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生無し_見積_起算日区分1_違約金有無フラグ有りテスト() throws Exception {
		final long estimationId = 1L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生無し_見積_起算日区分1_違約金有無フラグNULLテスト() throws Exception {
		final long estimationId = 1L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生有り_見積_起算日区分1_違約金有無フラグ無しテスト() throws Exception {
		final long estimationId = 2L;
		final long itemMasterId = 16125L; // 違約金有無フラグ=「0：無し」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生有り_見積_起算日区分1_違約金有無フラグ有りテスト() throws Exception {
		final long estimationId = 2L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNotNull("違約金チェック結果がNULLでないこと", actual);
		Assert.assertEquals("違約金チェック結果の品種マスタIDを確認", itemMasterId, actual.getId());

	}

	@Test
	public void 正常_違約金発生有り_見積_起算日区分1_違約金有無フラグNULLテスト() throws Exception {
		final long estimationId = 2L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生無し_見積_起算日区分2_違約金有無フラグ無しテスト() throws Exception {
		final long estimationId = 3L;
		final long itemMasterId = 16147L; // 違約金有無フラグ=「0：無し」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生無し_見積_起算日区分2_違約金有無フラグ有りテスト() throws Exception {
		final long estimationId = 3L;
		final long itemMasterId = 16148L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生無し_見積_起算日区分2_違約金有無フラグNULLテスト() throws Exception {
		final long estimationId = 3L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生有り_見積_起算日区分2_違約金有無フラグ無しテスト() throws Exception {
		final long estimationId = 4L;
		final long itemMasterId = 16147L; // 違約金有無フラグ=「0：無し」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_違約金発生有り_見積_起算日区分2_違約金有無フラグ有りテスト() throws Exception {
		final long estimationId = 4L;
		final long itemMasterId = 16148L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNotNull("違約金チェック結果がNULLでないこと", actual);
		Assert.assertEquals("違約金チェック結果の品種マスタIDを確認", itemMasterId, actual.getId());

	}

	@Test
	public void 正常_違約金発生有り_見積_起算日区分2_違約金有無フラグNULLテスト() throws Exception {
		final long estimationId = 4L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Estimation estimation = estimationRepository.findOne(estimationId);

		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_解約日がNULL() throws Exception {
		final long contractId = 5L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_サービス開始日がNULL() throws Exception {
		final long contractId = 6L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);

		ItemMaster actual = penaltyUtil.penaltyCheck(contract, itemMasterId);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 異常系_品種マスタ取得できない_契約() throws Exception {
		final long contractId = 1L;
		final long itemMasterId = 99999L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);

		try {
			penaltyUtil.penaltyCheck(contract, itemMasterId);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した品種マスタIDが存在しません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_品種マスタ取得できない_見積() throws Exception {
		final long estimationId = 1L;
		final long itemMasterId = 99999L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = estimationRepository.findOne(estimationId);

		try {
			penaltyUtil.penaltyCheck(estimation, itemMasterId);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した品種マスタIDが存在しません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_契約情報取得できない() throws Exception {

		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = null;

		try {
			penaltyUtil.penaltyCheck(contract, itemMasterId);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した契約が存在しません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_見積情報取得できない() throws Exception {

		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = null;

		try {
			penaltyUtil.penaltyCheck(estimation, itemMasterId);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RES00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した見積が存在しません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_変更元契約情報取得できない_見積種別が新規() throws Exception {

		final long estimationId = 5L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = estimationRepository.findOne(estimationId);

		try {
			penaltyUtil.penaltyCheck(estimation, itemMasterId);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した変更元契約が存在しません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_変更元契約情報取得できない_変更元契約IDがNULL() throws Exception {

		final long estimationId = 6L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = estimationRepository.findOne(estimationId);

		try {
			penaltyUtil.penaltyCheck(estimation, itemMasterId);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した変更元契約が存在しません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_変更元契約情報取得できない_変更元契約が取得できない() throws Exception {

		final long estimationId = 7L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Estimation estimation = estimationRepository.findOne(estimationId);

		try {
			penaltyUtil.penaltyCheck(estimation, itemMasterId);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した変更元契約が存在しません。", errorList.get(0).getErrorMessage());
		}
	}


}
