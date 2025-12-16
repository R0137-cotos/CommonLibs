package jp.co.ricoh.cotos.commonlib.dateCalcPattern;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ProductContract;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateCalcPatternUtil;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateContractUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
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

		Contract contract = contractRepository.findById(4L).get();

		String testDate = "20200101";
		Date resultDate = dateContractUtil.getServiceTermEndFromItem(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), true);
		Assert.assertEquals("サービス終了日が正しく取得されること", "20241231", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 月加算_終了日() throws Exception {

		Contract contract = contractRepository.findById(4L).get();

		String testDate = "20200110";
		Date resultDate = dateContractUtil.getServiceTermEndFromItem(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertEquals("サービス終了日が正しく取得されること", "20250109", dateCalcPatternUtil.dateToStringConverter(resultDate, null));
	}

	@Test
	public void 異常系_契約期間なし() throws Exception {
		try {
			Contract contract = contractRepository.findById(5L).get();
			dateContractUtil.getServiceTermEndFromItem(contract, null, true);
			Assert.fail("正常終了した");
		} catch (ErrorCheckException e) {
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

		Contract contract = contractRepository.findById(8L).get();

		Contract resultContract = dateContractUtil.getFirstContract(contract);
		Assert.assertEquals("最初の契約が正しく取得されること", 6, resultContract.getId());
	}

	@Test
	public void 異常系_変更元契約なし() throws Exception {

		Contract contract = contractRepository.findById(8L).get();
		contract.setOriginContractId(99L);
		try {
			dateContractUtil.getFirstContract(contract);
			fail("エラーなし");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("RCO00001", errorList.get(0).getErrorId());
			Assert.assertEquals("指定した変更元契約が存在しません。", errorList.get(0).getErrorMessage());
		}
	}

	@Test
	public void 契約更新可能判定_最長契約月数なし() throws Exception {

		Contract contract = contractRepository.findById(9L).get();

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, null, false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新可能() throws Exception {

		Contract contract = contractRepository.findById(10L).get();
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新不可() throws Exception {

		Contract contract = contractRepository.findById(10L).get();
		String testDate = "20231101";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertFalse("契約更新不可であること", result);
	}

	@Test
	public void 契約更新可能判定_更新可能_拡張項目Null() throws Exception {

		Contract contract = contractRepository.findById(11L).get();
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新可能_migrationParameter_Null() throws Exception {

		Contract contract = contractRepository.findById(12L).get();
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新可能_migrationDivがRITOS移管以外() throws Exception {

		Contract contract = contractRepository.findById(13L).get();
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertTrue("契約更新可能であること", result);
	}

	@Test
	public void 契約更新可能判定_更新不可_正常な移行データ() throws Exception {

		Contract contract = contractRepository.findById(14L).get();
		String testDate = "20231031";

		boolean result = dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
		Assert.assertFalse("契約更新不可であること", result);
	}

	@Test
	public void 異常系_契約更新可能判定_Json変換例外() throws Exception {

		Contract contract = contractRepository.findById(15L).get();
		String testDate = "20231031";
		try {
			dateContractUtil.contractUpdatePossibleCheck(contract, dateCalcPatternUtil.stringToDateConverter(testDate, null), false);
			Assert.fail("正常終了した");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00045", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "JSON文字列からObjectの変換に失敗しました。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void サービス終了最大延長日_60か月() throws Exception {
		Contract contract = new Contract();
		ProductContract productContract = new ProductContract();
		productContract.setProductMasterId(6002);
		List<ProductContract> list = new ArrayList<>();
		list.add(productContract);
		contract.setProductContractList(list);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		Date result = dateContractUtil.getServiceTermMaxEndFromProduct(contract, sf.parse("2022/08/01"), true);
		Assert.assertEquals("60か月後の日付が返ること", "2027/07/31", sf.format(result));
		result = dateContractUtil.getServiceTermMaxEndFromProduct(contract, sf.parse("2023/03/01"), true);
		Assert.assertEquals("うるう年の場合には2/29が返ること", "2028/02/29", sf.format(result));
	}

	@Test
	public void 延長可能契約月数() throws Exception {

		Contract contract = new Contract();
		Assert.assertNull("サービス終了日・サービス終了最大延長日が設定されていない場合にはnullが返ること", dateContractUtil.getMaxExtensionContractMonths(contract));
		contract.setServiceTermEnd(new Date());
		Assert.assertNull("サービス終了最大延長日が設定されていない場合にはnullが返ること", dateContractUtil.getMaxExtensionContractMonths(contract));
		contract.setServiceTermEnd(null);
		contract.setServiceTermMaxEnd(new Date());
		Assert.assertNull("サービス終了日が設定されていない場合にはnullが返ること", dateContractUtil.getMaxExtensionContractMonths(contract));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");

		// java.util.Date型のテスト
		// 1年間のテスト
		contract.setServiceTermEnd(sf.parse("2022/01/31"));
		contract.setServiceTermMaxEnd(sf.parse("2023/1/31"));
		Assert.assertEquals("1年間で12が返されること", Long.valueOf(12), dateContractUtil.getMaxExtensionContractMonths(contract));
		contract.setServiceTermEnd(sf.parse("2024/02/29"));
		contract.setServiceTermMaxEnd(sf.parse("2025/2/28"));
		Assert.assertEquals("うるう年からの1年間で12が返されること", Long.valueOf(12), dateContractUtil.getMaxExtensionContractMonths(contract));
		contract.setServiceTermEnd(sf.parse("2023/02/28"));
		contract.setServiceTermMaxEnd(sf.parse("2024/2/29"));
		Assert.assertEquals("うるう年までの1年間で12が返されること", Long.valueOf(12), dateContractUtil.getMaxExtensionContractMonths(contract));
		// 4年間のテスト
		contract.setServiceTermEnd(sf.parse("2022/01/31"));
		contract.setServiceTermMaxEnd(sf.parse("2026/1/31"));
		Assert.assertEquals("4年間で48が返されること", Long.valueOf(48), dateContractUtil.getMaxExtensionContractMonths(contract));
		contract.setServiceTermEnd(sf.parse("2024/02/29"));
		contract.setServiceTermMaxEnd(sf.parse("2028/2/29"));
		Assert.assertEquals("うるう年からの4年間で48が返されること", Long.valueOf(48), dateContractUtil.getMaxExtensionContractMonths(contract));

		// java.sql.Date型のテスト
		contract = contractRepository.findById(16L).get();
		// 2024/02/29～2026/2/28
		Assert.assertEquals("うるう年からの2年間で24が返されること", Long.valueOf(24), dateContractUtil.getMaxExtensionContractMonths(contract));

		// サービス終了日：java.util.Date サービス終了最大延長日：java.sql.Date の場合のテスト(契約変更時にコールされることを想定)
		contract.setServiceTermEnd(sf.parse("2025/02/28"));
		Assert.assertEquals("1年間で12が返されること", Long.valueOf(12), dateContractUtil.getMaxExtensionContractMonths(contract));
	}

	@Test
	public void ランニング売上計上処理日_区分なし() throws Exception {
		long contractId = 17L;
		Contract contract = contractRepository.findById(contractId).get();
		dateContractUtil.setRunningAccountSalesDate(contract);
		contract = contractRepository.findById(contractId).get();
		contract.getContractDetailList().stream().filter(s -> s.getItemContract().getCostType() == CostType.月額_定額).forEach(detail -> {
			Assert.assertNull("ランニング売上計上処理日にNULLが設定されること", detail.getRunningAccountSalesDate());
		});
	}

	@Test
	public void ランニング売上計上処理日_サービス開始日() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("sql/dateCalcPattern/updateItemMaster_001.sql");
		long contractId = 18L;
		Contract contract = contractRepository.findById(contractId).get();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		dateContractUtil.setRunningAccountSalesDate(contract);
		contract.getContractDetailList().stream().forEach(detail -> {
			if (detail.getItemContract().getCostType() == CostType.月額_定額) {
				Assert.assertEquals("サービス開始日の月初日が設定されること", "2024/07/01", sf.format(detail.getRunningAccountSalesDate()));
			} else {
				Assert.assertNull("ライニング計上処理日が未設定であること", detail.getRunningAccountSalesDate());
			}
		});
	}

	@Test
	public void ランニング売上計上処理日_課金開始日() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("sql/dateCalcPattern/updateItemMaster_002.sql");
		long contractId = 19L;
		Contract contract = contractRepository.findById(contractId).get();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		dateContractUtil.setRunningAccountSalesDate(contract);
		contract.getContractDetailList().stream().forEach(detail -> {
			if (detail.getItemContract().getCostType() == CostType.月額_定額) {
				Assert.assertEquals("課金開始日の月初日が設定されること", "2024/08/01", sf.format(detail.getRunningAccountSalesDate()));
			} else {
				Assert.assertNull("ライニング計上処理日が未設定であること", detail.getRunningAccountSalesDate());
			}
		});
	}

	private void テストデータ作成() {
		context.getBean(DBConfig.class).initTargetTestData("sql/dateCalcPattern/testDateContractUtil.sql");
	}
}
