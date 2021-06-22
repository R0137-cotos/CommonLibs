package jp.co.ricoh.cotos.commonlib.dateCalcPattern;

import java.util.Date;

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
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateCalcPatternUtil;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateContractUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestDateContractUtil {

	@Autowired
	private DateContractUtil dateContractUtil;

	@Autowired
	private DateCalcPatternUtil dateCalcPatternUtil;

	@Autowired
	private ContractRepository contractRepository;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		テストデータ作成();
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void 月加算_終了日月末() throws Exception {

		Contract contract = contractRepository.findOne(4L);

		String testDate = "20200101";
		Date resultDate = dateContractUtil.getServiceTermEndFromItem(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), true);
		Assert.assertEquals("サービス終了日が正しく取得されること", "20241231", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 月加算_終了日() throws Exception {

		Contract contract = contractRepository.findOne(4L);

		String testDate = "20200110";
		Date resultDate = dateContractUtil.getServiceTermEndFromItem(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertEquals("サービス終了日が正しく取得されること", "20250109", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 異常系_契約期間なし() throws Exception {
		try {
			Contract contract = contractRepository.findOne(5L);
			dateContractUtil.getServiceTermEndFromItem(contract, null, true);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00013", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "契約期間月数が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 次回更新時契約終了日取得_月末フラグFalse() throws Exception {

		String testDate = "20200110";
		Date resultDate = dateContractUtil.nextUpdateServiceTermEnd(dateCalcPatternUtil.stringToDateConverter(testDate, null), 12, false);
		Assert.assertEquals("サービス終了日が正しく取得されること", "20210110", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 次回更新時契約終了日取得_月末フラグTrue() throws Exception {

		String testDate = "20200110";
		Date resultDate = dateContractUtil.nextUpdateServiceTermEnd(dateCalcPatternUtil.stringToDateConverter(testDate, null), 12, true);
		Assert.assertEquals("サービス終了日が正しく取得されること", "20201231", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 最初の契約取得確認() throws Exception {

		Contract contract = contractRepository.findOne(8L);

		Contract resultContract = dateContractUtil.getFirstContract(contract);
		Assert.assertEquals("最初の契約が正しく取得されること", 6, resultContract.getId());
	}

	@Test
	public void 契約更新可能判定_最長契約月数なし() throws Exception {

		Contract contract = contractRepository.findOne(9L);

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, null, false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新可能() throws Exception {

		Contract contract = contractRepository.findOne(10L);
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新不可() throws Exception {

		Contract contract = contractRepository.findOne(10L);
		String testDate = "20231101";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertFalse("契約更新不可であること", result);
	}

	@Test
	public void 契約更新可能判定_更新可能_拡張項目Null() throws Exception {

		Contract contract = contractRepository.findOne(11L);
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新可能_migrationParameter_Null() throws Exception {

		Contract contract = contractRepository.findOne(12L);
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新可能_migrationDivがRITOS移管以外() throws Exception {

		Contract contract = contractRepository.findOne(13L);
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新不可_正常な移行データ() throws Exception {

		Contract contract = contractRepository.findOne(14L);
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertFalse("契約更新不可であること", result);
	}

	@Test
	public void 異常系_契約更新可能判定_Json変換例外() throws Exception {

		Contract contract = contractRepository.findOne(15L);
		String testDate = "20231031";
		try {
			dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
			Assert.fail("正常終了した");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00045", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "JSON文字列からObjectの変換に失敗しました。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	private void テストデータ作成() {
		context.getBean(DBConfig.class).initTargetTestData("sql/dateCalcPattern/testDateContractUtil.sql");
	}
}
