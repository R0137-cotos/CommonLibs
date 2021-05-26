package jp.co.ricoh.cotos.commonlib.penalty;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import jp.co.ricoh.cotos.commonlib.dto.result.PenaltyCheckResultDTO;
import jp.co.ricoh.cotos.commonlib.dto.result.PenaltyInfoDto;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateCalcPatternUtil;
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
	DateCalcPatternUtil dateCalcPatternUtil;

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
		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200101", null);

		PenaltyCheckResultDTO actual = penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), penalyStartingDate);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	@Test
	public void 正常_違約金発生無し_契約_起算日区分1_違約金有無フラグ有りテスト() throws Exception {
		final long contractId = 1L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);
		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200101", null);

		PenaltyCheckResultDTO actual = penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), penalyStartingDate);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	@Test
	public void 正常_違約金発生無し_契約_起算日区分1_違約金有無フラグNULLテスト() throws Exception {
		final long contractId = 1L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Contract contract = contractRepository.findOne(contractId);
		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200101", null);

		PenaltyCheckResultDTO actual = penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), penalyStartingDate);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分1_違約金有無フラグ無しテスト() throws Exception {
		final long contractId = 2L;
		final long itemMasterId = 16125L; // 違約金有無フラグ=「0：無し」
		Contract contract = contractRepository.findOne(contractId);
		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200201", null);

		PenaltyCheckResultDTO actual = penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), penalyStartingDate);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分1_違約金有無フラグ有りテスト() throws Exception {
		final long contractId = 2L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);
		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200201", null);

		PenaltyCheckResultDTO actual = penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), penalyStartingDate);

		Assert.assertNotNull("違約金チェック結果がNULLでないこと", actual);
		Assert.assertEquals("違約金チェック結果の品種マスタIDを確認", itemMasterId, actual.getItemMaster().getId());
	}

	@Test
	public void 正常_違約金発生有り_契約_起算日区分1_違約金有無フラグNULLテスト() throws Exception {
		final long contractId = 2L;
		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
		Contract contract = contractRepository.findOne(contractId);
		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200201", null);

		PenaltyCheckResultDTO actual = penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), penalyStartingDate);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);
	}

// RITOS移管Aカテゴリでは部分解約(見積での違約金表示)は対応不要となった。Bカテゴリ以降で対応する可能性あるためコメント化。以降のコメント箇所も同様。
//	// 見積テスト
//	@Test
//	public void 正常_違約金発生無し_見積_起算日区分1_違約金有無フラグ無しテスト() throws Exception {
//		final long estimationId = 1L;
//		final long itemMasterId = 16125L; // 違約金有無フラグ=「0：無し」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//	}
//
//	@Test
//	public void 正常_違約金発生無し_見積_起算日区分1_違約金有無フラグ有りテスト() throws Exception {
//		final long estimationId = 1L;
//		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}
//
//	@Test
//	public void 正常_違約金発生無し_見積_起算日区分1_違約金有無フラグNULLテスト() throws Exception {
//		final long estimationId = 1L;
//		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}
//
//	@Test
//	public void 正常_違約金発生有り_見積_起算日区分1_違約金有無フラグ無しテスト() throws Exception {
//		final long estimationId = 2L;
//		final long itemMasterId = 16125L; // 違約金有無フラグ=「0：無し」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}
//
//	@Test
//	public void 正常_違約金発生有り_見積_起算日区分1_違約金有無フラグ有りテスト() throws Exception {
//		final long estimationId = 2L;
//		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNotNull("違約金チェック結果がNULLでないこと", actual);
//		Assert.assertEquals("違約金チェック結果の品種マスタIDを確認", itemMasterId, actual.getId());
//
//	}
//
//	@Test
//	public void 正常_違約金発生有り_見積_起算日区分1_違約金有無フラグNULLテスト() throws Exception {
//		final long estimationId = 2L;
//		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}
//
//	@Test
//	public void 正常_違約金発生無し_見積_起算日区分2_違約金有無フラグ無しテスト() throws Exception {
//		final long estimationId = 3L;
//		final long itemMasterId = 16147L; // 違約金有無フラグ=「0：無し」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}
//
//	@Test
//	public void 正常_違約金発生無し_見積_起算日区分2_違約金有無フラグ有りテスト() throws Exception {
//		final long estimationId = 3L;
//		final long itemMasterId = 16148L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}
//
//	@Test
//	public void 正常_違約金発生無し_見積_起算日区分2_違約金有無フラグNULLテスト() throws Exception {
//		final long estimationId = 3L;
//		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}
//
//	@Test
//	public void 正常_違約金発生有り_見積_起算日区分2_違約金有無フラグ無しテスト() throws Exception {
//		final long estimationId = 4L;
//		final long itemMasterId = 16147L; // 違約金有無フラグ=「0：無し」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}
//
//	@Test
//	public void 正常_違約金発生有り_見積_起算日区分2_違約金有無フラグ有りテスト() throws Exception {
//		final long estimationId = 4L;
//		final long itemMasterId = 16148L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNotNull("違約金チェック結果がNULLでないこと", actual);
//		Assert.assertEquals("違約金チェック結果の品種マスタIDを確認", itemMasterId, actual.getId());
//
//	}
//
//	@Test
//	public void 正常_違約金発生有り_見積_起算日区分2_違約金有無フラグNULLテスト() throws Exception {
//		final long estimationId = 4L;
//		final long itemMasterId = 16220L; // 違約金有無フラグ=「NULL（設定無し）」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		ItemMaster actual = penaltyUtil.penaltyCheck(estimation, itemMasterId);
//
//		Assert.assertNull("違約金チェック結果がNULLであること", actual);
//
//	}

	@Test
	public void 正常_解約日がNULL() throws Exception {
		final long contractId = 5L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);
		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200101", null);

		PenaltyCheckResultDTO actual = penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), penalyStartingDate);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 正常_課金開始日がNULL() throws Exception {
		final long contractId = 6L;
		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);

		PenaltyCheckResultDTO actual = penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), null);

		Assert.assertNull("違約金チェック結果がNULLであること", actual);

	}

	@Test
	public void 異常系_品種マスタ取得できない_契約() throws Exception {
		final long contractId = 1L;
		final long itemMasterId = 99999L; // 違約金有無フラグ=「1：有り」
		Contract contract = contractRepository.findOne(contractId);
		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200101", null);

		try {
			penaltyUtil.penaltyCheck(itemMasterId, contract.getCancelScheduledDate(), penalyStartingDate);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した品種マスタIDが存在しません。", errorList.get(0).getErrorMessage());
		}
	}

//	@Test
//	public void 異常系_品種マスタ取得できない_見積() throws Exception {
//		final long estimationId = 1L;
//		final long itemMasterId = 99999L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//		Date penalyStartingDate = dateCalcPatternUtil.stringToDateConverter("20200101", null);
//
//		try {
//			penaltyUtil.penaltyCheck(estimation, itemMasterId);
//			fail("エラーなし");
//		} catch (ErrorCheckException e) {
//			List<ErrorInfo> errorList = e.getErrorInfoList();
//			Assert.assertEquals(1, errorList.size());
//			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
//			Assert.assertEquals("指定した品種マスタIDが存在しません。", errorList.get(0).getErrorMessage());
//		}
//	}
//	@Test
//	public void 異常系_見積情報取得できない() throws Exception {
//
//		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = null;
//
//		try {
//			penaltyUtil.penaltyCheck(estimation, itemMasterId);
//			fail("エラーなし");
//		} catch (ErrorCheckException e) {
//			List<ErrorInfo> errorList = e.getErrorInfoList();
//			Assert.assertEquals(1, errorList.size());
//			Assert.assertEquals("RES00001", errorList.get(0).getErrorId());
//			Assert.assertEquals("指定した見積が存在しません。", errorList.get(0).getErrorMessage());
//		}
//	}
//
//	@Test
//	public void 異常系_変更元契約情報取得できない_見積種別が新規() throws Exception {
//
//		final long estimationId = 5L;
//		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		try {
//			penaltyUtil.penaltyCheck(estimation, itemMasterId);
//			fail("エラーなし");
//		} catch (ErrorCheckException e) {
//			List<ErrorInfo> errorList = e.getErrorInfoList();
//			Assert.assertEquals(1, errorList.size());
//			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
//			Assert.assertEquals("指定した変更元契約が存在しません。", errorList.get(0).getErrorMessage());
//		}
//	}
//
//	@Test
//	public void 異常系_変更元契約情報取得できない_変更元契約IDがNULL() throws Exception {
//
//		final long estimationId = 6L;
//		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		try {
//			penaltyUtil.penaltyCheck(estimation, itemMasterId);
//			fail("エラーなし");
//		} catch (ErrorCheckException e) {
//			List<ErrorInfo> errorList = e.getErrorInfoList();
//			Assert.assertEquals(1, errorList.size());
//			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
//			Assert.assertEquals("指定した変更元契約が存在しません。", errorList.get(0).getErrorMessage());
//		}
//	}
//
//	@Test
//	public void 異常系_変更元契約情報取得できない_変更元契約が取得できない() throws Exception {
//
//		final long estimationId = 7L;
//		final long itemMasterId = 16126L; // 違約金有無フラグ=「1：有り」
//		Estimation estimation = estimationRepository.findOne(estimationId);
//
//		try {
//			penaltyUtil.penaltyCheck(estimation, itemMasterId);
//			fail("エラーなし");
//		} catch (ErrorCheckException e) {
//			List<ErrorInfo> errorList = e.getErrorInfoList();
//			Assert.assertEquals(1, errorList.size());
//			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
//			Assert.assertEquals("指定した変更元契約が存在しません。", errorList.get(0).getErrorMessage());
//		}
//	}
//
//	@Test
//	public void 異常系_違約金情報取得_見積ID未設定() throws Exception {
//
//		try {
//			penaltyUtil.getPenaltyInfo(null);
//			fail("エラーなし");
//		} catch (ErrorCheckException e) {
//			List<ErrorInfo> errorList = e.getErrorInfoList();
//			Assert.assertEquals(1, errorList.size());
//			Assert.assertEquals("ROT00001", errorList.get(0).getErrorId());
//			Assert.assertEquals("パラメータ「見積ID」が設定されていません。", errorList.get(0).getErrorMessage());
//		}
//	}
//
//	@Test
//	public void 異常系_違約金情報取得_見積情報が取得できない() throws Exception {
//
//		try {
//			penaltyUtil.getPenaltyInfo(12345L);
//			fail("エラーなし");
//		} catch (ErrorCheckException e) {
//			List<ErrorInfo> errorList = e.getErrorInfoList();
//			Assert.assertEquals(1, errorList.size());
//			Assert.assertEquals("RES00001", errorList.get(0).getErrorId());
//			Assert.assertEquals("指定した見積が存在しません。", errorList.get(0).getErrorMessage());
//		}
//	}
//
//	@Test
//	public void 正常_違約金情報取得_見積_違約金情報なし() throws Exception {
//
//		Long estimatinoId = 1L;
//		List<PenaltyInfoDto> resultList = penaltyUtil.getPenaltyInfo(estimatinoId);
//		Assert.assertEquals("違約金が発生しない場合、空のリストが返却されること", 0, resultList.size());
//	}
//
//	@Test
//	public void 正常_違約金情報取得_見積_違約金情報あり() throws Exception {
//
//		Long estimatinoId = 2L;
//		List<PenaltyInfoDto> resultList = penaltyUtil.getPenaltyInfo(estimatinoId);
//		Assert.assertEquals("違約金情報が1件返却されること", 1, resultList.size());
//	}

	@Test
	public void 異常系_違約金情報取得_契約ID未設定() throws Exception {

		try {
			penaltyUtil.getPenaltyInfo(null, new Date());
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00001", errorList.get(0).getErrorId());
			Assert.assertEquals("パラメータ「契約ID」が設定されていません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_違約金情報取得_契約情報が取得できない() throws Exception {

		try {
			penaltyUtil.getPenaltyInfo(12345L, new Date());
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した契約が存在しません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_違約金情報取得_解約予定日未設定() throws Exception {

		try {
			penaltyUtil.getPenaltyInfo(20L, null);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00001", errorList.get(0).getErrorId());
			Assert.assertEquals("パラメータ「解約予定日」が設定されていません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 正常_違約金情報取得_契約_違約金情報なし() throws Exception {

		Long contractId = 1L;
		Date cancelScheduledDate = dateCalcPatternUtil.stringToDateConverter("2020/12/31", "yyyy/MM/dd");
		List<PenaltyInfoDto> resultList = penaltyUtil.getPenaltyInfo(contractId, cancelScheduledDate);
		Assert.assertEquals("違約金が発生しない場合、空のリストが返却されること", 0, resultList.size());
	}

	@Test
	public void 正常_違約金情報取得_契約_違約金情報あり_全解約() throws Exception {

		Long contractId = 20L;
		Date cancelScheduledDate = dateCalcPatternUtil.stringToDateConverter("2020/12/31", "yyyy/MM/dd");
		List<PenaltyInfoDto> resultList = penaltyUtil.getPenaltyInfo(contractId, cancelScheduledDate);
		Assert.assertEquals("違約金情報が2件返却されること", 2, resultList.size());
	}

//	@Test
//	public void 異常系_違約金情報取得_契約_違約金情報あり_部分解約_契約に紐づく見積情報なし() throws Exception {
//
//		Long contractId = 21L;
//		Date cancelScheduledDate = dateCalcPatternUtil.stringToDateConverter("2020/12/31", "yyyy/MM/dd");
//		try {
//			penaltyUtil.getPenaltyInfo(contractId, cancelScheduledDate);
//			fail("エラーなし");
//		} catch (ErrorCheckException e) {
//			List<ErrorInfo> errorList = e.getErrorInfoList();
//			Assert.assertEquals(1, errorList.size());
//			Assert.assertEquals("RES00001", errorList.get(0).getErrorId());
//			Assert.assertEquals("指定した見積が存在しません。", errorList.get(0).getErrorMessage());
//		}
//	}
//
//	@Test
//	public void 違約金情報取得_契約_違約金情報あり_部分解約() throws Exception {
//
//		Long contractId = 22L;
//		Date cancelScheduledDate = dateCalcPatternUtil.stringToDateConverter("2020/12/01", "yyyy/MM/dd");
//		List<PenaltyInfoDto> resultList = penaltyUtil.getPenaltyInfo(contractId, cancelScheduledDate);
//		Assert.assertEquals("違約金情報が1件返却されること", 1, resultList.size());
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void 減数あり品種数量リスト取得_対象データなし() throws Exception {
//
//		Method method = PenaltyUtil.class.getDeclaredMethod("getDecreaseItemList", Estimation.class);
//		method.setAccessible(true);
//
//		Estimation estimation = null;
//		Map<Long, Integer> resultMap = (Map<Long, Integer>)method.invoke(penaltyUtil, estimation);
//		Assert.assertEquals("見積がnull場合、nullが返却されること", null, resultMap);
//
//		estimation = new Estimation();
//		resultMap = (Map<Long, Integer>)method.invoke(penaltyUtil, estimation);
//		Assert.assertEquals("見積明細がnullの場合、nullが返却されること", null, resultMap);
//
//		estimation = new Estimation();
//		estimation.setEstimationDetailList(new ArrayList<EstimationDetail>());
//		resultMap = (Map<Long, Integer>)method.invoke(penaltyUtil, estimation);
//		Assert.assertEquals("見積明細が空の場合、nullが返却されること", null, resultMap);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void 減数あり品種数量リスト取得_対象データあり() throws Exception {
//
//		Method method = PenaltyUtil.class.getDeclaredMethod("getDecreaseItemList", Estimation.class);
//		method.setAccessible(true);
//
//		final long estimationId = 20L;
//		Estimation estimation = estimationRepository.findOne(estimationId);
//		Map<Long, Integer> resultMap = (Map<Long, Integer>)method.invoke(penaltyUtil, estimation);
//
//		Assert.assertEquals("変更前数量が未設定または減数されていない、数量が0の場合、返却されないこと", 3, resultMap.size());
//		Assert.assertEquals("減数がある場合、返却されること", "1", String.valueOf(resultMap.get(2009L)));
//		Assert.assertEquals("状態が削除の場合、返却されること", "2", String.valueOf(resultMap.get(2011L)));
//		Assert.assertEquals("状態が削除の場合、変更前数量が設定されている場合、変更前数量が設定されること", "10", String.valueOf(resultMap.get(2012L)));
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void 異常系_違約金情報作成() throws Exception {
//
//		Method method = PenaltyUtil.class.getDeclaredMethod("createPenaltyInfoList", Map.class, Date.class, Contract.class);
//		method.setAccessible(true);
//
//		// 減数品種マップがnullの場合
//		Map<Long, Integer> decreaseItemMap = null;
//		Estimation estimation = new Estimation();
//		Date checkTrgetDate = new Date();
//
//		List<PenaltyInfoDto> resultList = (List<PenaltyInfoDto>)method.invoke(penaltyUtil, decreaseItemMap, checkTrgetDate, estimation);
//		Assert.assertEquals("減数品種マップがnullの場合、空のリストが返却されること", 0, resultList.size());
//
//		decreaseItemMap = new HashMap<Long, Integer>();
//		estimation = new Estimation();
//		resultList = (List<PenaltyInfoDto>)method.invoke(penaltyUtil, decreaseItemMap, checkTrgetDate, estimation);
//		Assert.assertEquals("減数品種マップのサイズが0の場合、空のリストが返却されること", 0, resultList.size());
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void 違約金情報作成_見積() throws Exception {
//
//		Method method = PenaltyUtil.class.getDeclaredMethod("createPenaltyInfoList", Map.class, Date.class, Contract.class);
//		method.setAccessible(true);
//
//		// 違約金発生なし
//		Map<Long, Integer> decreaseItemMap = new HashMap<Long, Integer>();
//		Long estimationId = 3L;
//		Estimation estimation = estimationRepository.findOne(estimationId);
//		Long itemMasterId = 16126L;
//		decreaseItemMap.put(itemMasterId, 2);
//		Date checkTrgetDate = new Date();
//		List<PenaltyInfoDto> resultList = (List<PenaltyInfoDto>)method.invoke(penaltyUtil, decreaseItemMap, checkTrgetDate, estimation);
//		Assert.assertEquals("違約金が発生しない場合、戻り値リストが空であること", 0, resultList.size());
//
//		// 違約金発生あり
//		decreaseItemMap = new HashMap<Long, Integer>();
//		estimationId = 2L;
//		estimation = estimationRepository.findOne(estimationId);
//		itemMasterId = 16127L;
//		decreaseItemMap.put(itemMasterId, 2);
//		resultList = (List<PenaltyInfoDto>)method.invoke(penaltyUtil, decreaseItemMap, checkTrgetDate, estimation);
//		Assert.assertEquals("違約金情報リストに1件設定されていること", 1, resultList.size());
//		PenaltyInfoDto penaltyInfoDto = resultList.get(0);
//		Assert.assertEquals("違約金品種マスタIDが正しく設定されていること", String.valueOf(20), String.valueOf(penaltyInfoDto.getPenaltyItemMasterId()));
//		Assert.assertEquals("違約金品種名が正しく設定されていること", "ライトモデル20違約金", penaltyInfoDto.getPenaltyItemName());
//		Assert.assertEquals("違約金リコー品種コードが正しく設定されていること", "11111", penaltyInfoDto.getPenaltyRicohItemCode());
//		Assert.assertEquals("違約金品種区分が正しく設定されていること", ItemType.オプション, penaltyInfoDto.getPenaltyItemType());
//		Assert.assertEquals("元品種マスタIDが正しく設定されていること", String.valueOf(16127), String.valueOf(penaltyInfoDto.getOriginItemMasterId()));
//		Assert.assertEquals("違約金単価が正しく設定されていること", BigDecimal.valueOf(100), penaltyInfoDto.getPenaltyUnitPrice());
//		Assert.assertEquals("数量が正しく設定されていること", 2, penaltyInfoDto.getQuantity());
//		Assert.assertEquals("違約金額が正しく設定されていること", BigDecimal.valueOf(200), penaltyInfoDto.getPenaltyAmountSummary());
//		// 違約金発生最終解約日にはシステム日付の月の最終日が設定されているのが正
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//		cal.set(Calendar.HOUR_OF_DAY, 00);
//		cal.set(Calendar.MINUTE, 00);
//		cal.set(Calendar.SECOND, 00);
//		String comparison = dateCalcPatternUtil.dateToStringConverter(cal.getTime(), "yyyyMMdd HH:mm:ss");
//		Assert.assertEquals("違約金発生最終解約日が正しく設定されていること", comparison, dateCalcPatternUtil.dateToStringConverter(penaltyInfoDto.getPenaltyOccurCacnlLastDate(), "yyyyMMdd HH:mm:ss"));
//	}

	@SuppressWarnings("unchecked")
	@Test
	public void 違約金情報作成_契約() throws Exception {

		Method method = PenaltyUtil.class.getDeclaredMethod("createPenaltyInfoList", Map.class, Date.class, Contract.class);
		method.setAccessible(true);

		// 違約金発生なし
		Map<Long, Integer> decreaseItemMap = new HashMap<Long, Integer>();
		Long contractId = 3L;
		Contract contract = contractRepository.findOne(contractId);
		decreaseItemMap.put(16147L, 3);
		decreaseItemMap.put(16148L, 2);
		decreaseItemMap.put(16220L, 1);
		Date checkTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201231", null);
		List<PenaltyInfoDto> resultList = (List<PenaltyInfoDto>)method.invoke(penaltyUtil, decreaseItemMap, checkTrgetDate, contract);
		Assert.assertEquals("違約金が発生しない場合、戻り値リストが空であること", 0, resultList.size());

		// 違約金発生あり
		decreaseItemMap = new HashMap<Long, Integer>();
		contractId = 20L;
		contract = contractRepository.findOne(contractId);
		decreaseItemMap.put(16147L, 1);
		decreaseItemMap.put(16127L, 2);
		decreaseItemMap.put(16220L, 3);
		decreaseItemMap.put(16128L, 4);

		resultList = (List<PenaltyInfoDto>)method.invoke(penaltyUtil, decreaseItemMap, checkTrgetDate, contract);
		Assert.assertEquals("違約金情報リストに2件設定されていること", 2, resultList.size());
		resultList.stream().forEach(penaltyInfoDto -> {
			if(20L == penaltyInfoDto.getPenaltyItemMasterId()) {
				Assert.assertEquals("違約金品種マスタIDが正しく設定されていること", String.valueOf(20), String.valueOf(penaltyInfoDto.getPenaltyItemMasterId()));
				Assert.assertEquals("違約金品種名が正しく設定されていること", "ライトモデル20違約金", penaltyInfoDto.getPenaltyItemName());
				Assert.assertEquals("違約金リコー品種コードが正しく設定されていること", "11111", penaltyInfoDto.getPenaltyRicohItemCode());
				Assert.assertEquals("違約金品種区分が正しく設定されていること", ItemType.オプション, penaltyInfoDto.getPenaltyItemType());
				Assert.assertEquals("元品種マスタIDが正しく設定されていること", String.valueOf(16127), String.valueOf(penaltyInfoDto.getOriginItemMasterId()));
				Assert.assertEquals("違約金単価が正しく設定されていること", BigDecimal.valueOf(100), penaltyInfoDto.getPenaltyUnitPrice());
				Assert.assertEquals("数量が正しく設定されていること", 2, penaltyInfoDto.getQuantity());
				Assert.assertEquals("違約金額が正しく設定されていること", BigDecimal.valueOf(200), penaltyInfoDto.getPenaltyAmountSummary());
				Assert.assertEquals("違約金発生最終解約日が正しく設定されていること", "20201231 00:00:00", dateCalcPatternUtil.dateToStringConverter(penaltyInfoDto.getPenaltyOccurCacnlLastDate(), "yyyyMMdd HH:mm:ss"));
			} else if(30L == penaltyInfoDto.getPenaltyItemMasterId()) {
				Assert.assertEquals("違約金品種マスタIDが正しく設定されていること", String.valueOf(30), String.valueOf(penaltyInfoDto.getPenaltyItemMasterId()));
				Assert.assertEquals("違約金品種名が正しく設定されていること", "スタンダードモデル67違約金", penaltyInfoDto.getPenaltyItemName());
				Assert.assertEquals("違約金リコー品種コードが正しく設定されていること", "22222", penaltyInfoDto.getPenaltyRicohItemCode());
				Assert.assertEquals("違約金品種区分が正しく設定されていること", ItemType.基本, penaltyInfoDto.getPenaltyItemType());
				Assert.assertEquals("元品種マスタIDが正しく設定されていること", String.valueOf(16128), String.valueOf(penaltyInfoDto.getOriginItemMasterId()));
				Assert.assertEquals("違約金単価が正しく設定されていること", BigDecimal.valueOf(200), penaltyInfoDto.getPenaltyUnitPrice());
				Assert.assertEquals("数量が正しく設定されていること", 4, penaltyInfoDto.getQuantity());
				Assert.assertEquals("違約金額が正しく設定されていること", BigDecimal.valueOf(800), penaltyInfoDto.getPenaltyAmountSummary());
				Assert.assertEquals("違約金発生最終解約日が正しく設定されていること", "20201231 00:00:00", dateCalcPatternUtil.dateToStringConverter(penaltyInfoDto.getPenaltyOccurCacnlLastDate(), "yyyyMMdd HH:mm:ss"));
			} else {
				Assert.fail();
			}
		});
	}

	@Test
	public void 違約金計算() throws Exception {

		Method method = PenaltyUtil.class.getDeclaredMethod("calcPenaltyAmount", ItemMaster.class, Integer.class);
		method.setAccessible(true);

		// 標準単価：39840
		Long itemMasterId = 16125L;
		ItemMaster itemMaster = itemMasterRepository.findOne(itemMasterId);
		Integer quantity = 1;
		BigDecimal rslut = (BigDecimal)method.invoke(penaltyUtil, itemMaster, quantity);
		Assert.assertEquals("数量1_標準単価 * 1の結果が設定されること", BigDecimal.valueOf(39840), rslut);

		// 標準単価：8000
		itemMasterId = 16220L;
		itemMaster = itemMasterRepository.findOne(itemMasterId);
		quantity = 2;
		rslut = (BigDecimal)method.invoke(penaltyUtil, itemMaster, quantity);
		Assert.assertEquals("数量2_標準単価 * 2の結果が設定されること", BigDecimal.valueOf(16000), rslut);
	}

	@Test
	public void 違約金発生解約日の最終日取得() throws Exception {

		Method method = PenaltyUtil.class.getDeclaredMethod("penaltyOccurCacnlLastDate", ItemMaster.class, Date.class);
		method.setAccessible(true);

		ItemMaster item = new ItemMaster();
		Date billingStartDate = new Date();
		Date result = (Date)method.invoke(penaltyUtil, item, billingStartDate);
		Assert.assertEquals("違約金起算日区分が未設定の場合、nullが返却されること", null, result);

		// 違約金起算日区分：課金開始日 最低契約月数：12
		Long itemMasterId = 16148L;
		billingStartDate = dateCalcPatternUtil.stringToDateConverter("20200115", null);
		item = itemMasterRepository.findOne(itemMasterId);
		result = (Date)method.invoke(penaltyUtil, item, billingStartDate);
		Assert.assertEquals("12か月後(指定月含む)の月の最終日が設定されていること", "20201130 23:59:59", dateCalcPatternUtil.dateToStringConverter(result, "yyyyMMdd HH:mm:ss"));
	}

	@Test
	public void 違約金起算日取得() throws Exception {

		Method method = PenaltyUtil.class.getDeclaredMethod("getPenalyStartingDate", Contract.class);
		method.setAccessible(true);

		long contractId = 1L;
		Contract contract = contractRepository.findOne(contractId);
		Date result = (Date)method.invoke(penaltyUtil, contract);
		Assert.assertEquals("契約が新規の場合、当契約の課金開始日が取得されること", contract.getBillingStartDate(), result);

		contractId = 23L;
		contract = contractRepository.findOne(contractId);
		result = (Date)method.invoke(penaltyUtil, contract);
		Assert.assertEquals("契約が変更の場合、最初の契約の課金開始日が取得されること", "20180101 00:00:00", dateCalcPatternUtil.dateToStringConverter(result, "yyyyMMdd HH:mm:ss"));
	}

	@Test
	public void 異常系_違約金起算日取得_元契約なし() throws Exception {

		long contractId = 26L;

		try {
			penaltyUtil.getPenaltyInfo(contractId, new Date());
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した変更元契約が存在しません。", errorList.get(0).getErrorMessage());
		}
	}
}
