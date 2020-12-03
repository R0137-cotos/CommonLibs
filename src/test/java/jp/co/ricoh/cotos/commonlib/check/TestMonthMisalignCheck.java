package jp.co.ricoh.cotos.commonlib.check;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;

/**
 * 月ずれチェックメソッドのテストクラス
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestMonthMisalignCheck {

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	ContractRepository contractRepository;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/check/testMonthMisalignCheck.sql");
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
	public void 正常系_指定した品種が存在しない() {

		final long contractId = 1L;
		final long itemMasterId = 2000L; // 存在しない
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01";

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 1), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_一致() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/11/01"; // 月数:10

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 1), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_月数が大きい場合NG() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2019/12/01";
		final String strDateTo = "2020/12/01"; // 月数:12

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 2), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_積上げ数量が大きい場合NG() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/02/01";
		final String strDateTo = "2020/12/01"; // 月数:10

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 3), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_積上げ数量が月数or月数プラス1と一致しない場合1() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/03/01";
		final String strDateTo = "2020/12/01"; // 月数:9

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 4), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_積上げ数量が月数or月数プラス1と一致しない場合2() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2019/12/01";
		final String strDateTo = "2020/12/01"; // 月数:12

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 4), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_積上げ数量が月数マイナス1or月数と一致しない場合NG1() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/02/01";
		final String strDateTo = "2020/12/01"; // 月数:10

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 5), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_積上げ数量が月数マイナス1or月数と一致しない場合NG2() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2019/11/01";
		final String strDateTo = "2020/12/01"; // 月数:13

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 5), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_積上げ数量が月数マイナス1or月数or月数プラス1と一致しない場合NG1() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/03/01";
		final String strDateTo = "2020/12/01"; // 月数:9

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 6), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生_積上げ数量が月数マイナス1or月数or月数プラス1と一致しない場合NG2() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2019/11/01";
		final String strDateTo = "2020/12/01"; // 月数:13

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がTrueか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 6), true);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分なし() {

		final long contractId = 1L;
		final long itemMasterId = 2004L; // 数量:0
		final String strDateFrom = "2020/12/01";
		final String strDateTo = "2020/12/31"; // 月数:0

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 3), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_年跨ぎ無し() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/15"; // 月数:11

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 1), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_年跨ぎ有り() {

		final long contractId = 1L;
		final long itemMasterId = 2007L; // 数量:15
		final String strDateFrom = "2019/09/01";
		final String strDateTo = "2020/12/01"; // 月数:15

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 1), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_積上げ数量が大きい() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01"; // 月数:11

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 2), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_月数が大きい() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01"; // 月数:11

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 3), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_積上げ数量が月数or月数プラス1と一致しない場合1() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01"; // 月数:11

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 4), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_積上げ数量が月数or月数プラス1と一致しない場合2() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/02/01";
		final String strDateTo = "2020/12/01"; // 月数:10

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 4), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_積上げ数量が月数マイナス1or月数と一致しない場合1() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01"; // 月数:11

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 5), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_積上げ数量が月数マイナス1or月数と一致しない場合2() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2019/12/01";
		final String strDateTo = "2020/12/01"; // 月数:12

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 5), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_積上げ数量が月数マイナス1or月数or月数プラス1と一致しない場合1() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01"; // 月数:11

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 6), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_積上げ数量が月数マイナス1or月数or月数プラス1と一致しない場合2() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/02/01";
		final String strDateTo = "2020/12/01"; // 月数:10

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 6), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 正常系_指定した品種の月ずれが発生しない_差分あり_積上げ数量が月数マイナス1or月数or月数プラス1と一致しない場合3() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2019/12/01";
		final String strDateTo = "2020/12/01"; // 月数:12

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			Assert.assertEquals("月ずれチェック結果がFalseか確認", checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 6), false);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (Exception e) {
			fail("想定外のエラー発生");
		}
	}

	@Test
	public void 異常系_契約情報が存在しない() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01";

		Contract contract = null;

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 3);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "RAR00001");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "指定した契約が存在しません。");
		}
	}

	@Test
	public void 異常系_チェック日付Fromが不正() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01";

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			checkUtil.monthMisalignCheck(contract, itemMasterId, null, checkDateTo, 0);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "RCO00011");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "パラメーターに不正な項目が設定されています。");
		}
	}

	@Test
	public void 異常系_チェック日付Toが不正() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01";

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, null, 0);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "RCO00011");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "パラメーターに不正な項目が設定されています。");
		}
	}

	@Test
	public void 異常系_許容値区分が不正() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/01/01";
		final String strDateTo = "2020/12/01";

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 0);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "RCO00011");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "パラメーターに不正な項目が設定されています。");
		}
	}

	@Test
	public void 異常系_FromとToが逆() {

		final long contractId = 1L;
		final long itemMasterId = 2006L; // 数量:11
		final String strDateFrom = "2020/12/01";
		final String strDateTo = "2020/01/01";

		Contract contract = contractRepository.findOne(contractId);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date checkDateFrom;
		Date checkDateTo;
		try {
			checkDateFrom = sdFormat.parse(strDateFrom);
			checkDateTo = sdFormat.parse(strDateTo);
			checkUtil.monthMisalignCheck(contract, itemMasterId, checkDateFrom, checkDateTo, 0);

		} catch (ParseException e) {
			fail("日付変換でエラー");
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "RCO00011");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "パラメーターに不正な項目が設定されています。");
		}
	}
}
