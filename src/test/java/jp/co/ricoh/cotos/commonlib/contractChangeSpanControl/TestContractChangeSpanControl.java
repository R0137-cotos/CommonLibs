package jp.co.ricoh.cotos.commonlib.contractChangeSpanControl;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
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
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.CheckPatternType;
import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.ContractChangeSpanTargetDateType;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcStndType;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.contractChangeSpanControl.ContractChangeSpanControl;
import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateCalcPatternUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestContractChangeSpanControl {

	@Autowired
	ContractChangeSpanControl contractChangeSpanControl;

	@Autowired
	DateCalcPatternUtil dateCalcPatternUtil;

	static ConfigurableApplicationContext context;

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
	public void 異常系_引数チェック() {

		try {
			contractChangeSpanControl.contractChangeSpanCheck(null, 1L, null, ContractType.契約変更, ContractTypeDetails.新規, "1", "1", 1L);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "パラメータ「対象ドメイン」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		try {
			contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, null, null, ContractType.契約変更, ContractTypeDetails.新規, "1", "1", 1L);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "パラメータ「商品マスタID」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		try {
			contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 1L, null, ContractType.新規, null, null, "1", 1L);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "パラメータ「ライフサイクル状態」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		try {
			contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 1L, null, ContractType.新規, null, "1", null, 1L);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "パラメータ「ワークフロー状態」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		try {
			contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 1L, null, ContractType.新規, null, "1", "1", null);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "パラメータ「対象トランザクションテーブルID」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		try {
			contractChangeSpanControl.contractChangeSpanCheck(null, 1L, null, ContractType.新規, null, "1", "1", null);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("2行エラー情報が設定されていること", 2, e.getErrorInfoList().size());
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "パラメータ「対象ドメイン」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00001", e.getErrorInfoList().get(1).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "パラメータ「対象トランザクションテーブルID」が設定されていません。", e.getErrorInfoList().get(1).getErrorMessage());
		}
	}

	@Test
	public void 異常系_契約情報なし() {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterInsert.sql");

		try {
			contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 7L, null, ContractType.契約更新, ContractTypeDetails.新規, "1", "1", 20L);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "RCO00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "指定した契約が存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		// 変更元契約ID未設定
		try {
			contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 7L, null, ContractType.契約更新, ContractTypeDetails.新規, "1", "1", 6L);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00013", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "変更元契約IDが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		// 変更元契約情報なし
		try {
			contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 7L, null, ContractType.契約変更, null, "3", "4", 7L);
			Assert.fail("正常終了した");
		} catch(ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "RCO00001", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "指定した変更元契約が存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		// 変更元契約ID未設定_契約種別が新規の場合、エラーとならないこと
		List<ErrorInfo> errors = contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 7L, null, ContractType.新規, null, "1", "1", 6L);
		Assert.assertEquals("エラー情報リストが空であること", 0, errors.size());
	}

	@Test
	public void エラー情報リストが空であること() {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterInsert.sql");

		List<ErrorInfo> errorInfoList = contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.見積, 5L, null, ContractType.契約変更, null, "2", "3", 5L);
		Assert.assertEquals("エラー情報リストが空であること(契約変更期間管理マスタに該当レコードなし)", 0, errorInfoList.size());

		errorInfoList = contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 5L, null, ContractType.情報変更, ContractTypeDetails.新規, "1", "1", 5L);
		Assert.assertEquals("エラー情報リストが空であること(契約品種に紐づく契約変更期間管理マスタレコードなし)", 0, errorInfoList.size());

		errorInfoList = contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 5L, 100L, ContractType.契約変更, null, "2", "3", 5L);
		Assert.assertEquals("エラー情報リストが空であること(品種マスタID指定時契約変更期間管理マスタレコードなし)", 0, errorInfoList.size());

		errorInfoList = contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 4L, null, ContractType.契約更新, null, "2", "3", 4L);
		Assert.assertEquals("エラー情報リストが空であること(契約情報が追加条件式に該当しない)", 0, errorInfoList.size());

		errorInfoList = contractChangeSpanControl.contractChangeSpanCheck(ServiceCategory.契約, 7L, null, ContractType.契約変更, null, "3", "4", 4L);
		Assert.assertEquals("エラー情報リストが空であること(変更可能期間内)", 0, errorInfoList.size());
	}
	@Test
	public void 契約変更可能期間外判定テスト_期間固定_開始() throws NoSuchMethodException, SecurityException {

		Method method = ContractChangeSpanControl.class.getDeclaredMethod("isNotContractChangeSpan", ContractChangeSpanMaster.class, Date.class, Date.class, Date.class, Date.class);
		method.setAccessible(true);
		try {

			Date fromCheckDate = null;
			Date toCheckDate = null;
			Date fromCheckTrgetDate = null;
			Date toCheckTrgetDate = null;

			// 契約変更期間管理マスタ
			ContractChangeSpanMaster spanMaster = new ContractChangeSpanMaster();
			spanMaster.setCheckPatternType(CheckPatternType.期間固定);

			boolean result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("変更可能期間開始が未設定の場合、期間内と判定されること", false, false);

			fromCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);
			fromCheckTrgetDate = null;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("チェック対象日(開始)が未設定の場合、期間外と判定されること", true, result);

			fromCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);
			fromCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("変更可能期間開始、チェック対象日(開始)が同日の場合、期間内と判定されること", false, result);

			fromCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207 08:50", "yyyyMMdd HH:mm");
			fromCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201207 07:00", "yyyyMMdd HH:mm");;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("チェック対象日(開始)が変更可能期間開始より前(時間)の場合、期間外と判定されること", true, result);

			fromCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);
			fromCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201206", null);;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("チェック対象日(開始)が変更可能期間開始より前の場合、期間外と判定されること", true, result);

			fromCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);
			fromCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201208", null);;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("チェック対象日(開始)が変更可能期間開始より後の場合、期間内と判定されること", false, result);

		} catch(Exception e) {
			e.printStackTrace();
			fail("例外が発生している。");
		}
	}

	@Test
	public void 契約変更可能期間外判定テスト_期間固定_終了() throws NoSuchMethodException, SecurityException {

		Method method = ContractChangeSpanControl.class.getDeclaredMethod("isNotContractChangeSpan", ContractChangeSpanMaster.class, Date.class, Date.class, Date.class, Date.class);
		method.setAccessible(true);
		try {

			Date fromCheckDate = null;
			Date toCheckDate = null;
			Date fromCheckTrgetDate = null;
			Date toCheckTrgetDate = null;

			// 契約変更期間管理マスタ
			ContractChangeSpanMaster spanMaster = new ContractChangeSpanMaster();
			spanMaster.setCheckPatternType(CheckPatternType.期間固定);

			boolean result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("変更可能期間終了が未設定の場合、期間内と判定されること", false, false);

			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);
			toCheckTrgetDate = null;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("チェック対象日(終了)が未設定の場合、期間外と判定されること", true, result);

			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("変更可能期間終了、チェック対象日(終了)が同日の場合、期間内と判定されること", false, result);

			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207 08:50", "yyyyMMdd HH:mm");
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201207 21:00", "yyyyMMdd HH:mm");;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("チェック対象日(終了)が変更可能期間終了より後(時間)の場合、期間外と判定されること", true, result);

			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201208", null);;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("チェック対象日(終了)が変更可能期間終了より後の場合、期間外と判定されること", true, result);

			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20201207", null);
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201206", null);;
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("チェック対象日(終了)が変更可能期間終了より前の場合、期間内と判定されること", false, result);

		} catch(Exception e) {
			e.printStackTrace();
			fail("例外が発生している。");
		}
	}

	@Test
	public void 契約変更可能期間外判定テスト_期間可変() throws NoSuchMethodException, SecurityException {

		Method method = ContractChangeSpanControl.class.getDeclaredMethod("isNotContractChangeSpan", ContractChangeSpanMaster.class, Date.class, Date.class, Date.class, Date.class);
		method.setAccessible(true);
		try {

			Date fromCheckDate = null;
			Date toCheckDate = null;
			Date fromCheckTrgetDate = null;
			Date toCheckTrgetDate = null;

			// 契約変更期間管理マスタ
			ContractChangeSpanMaster spanMaster = new ContractChangeSpanMaster();
			spanMaster.setCheckPatternType(CheckPatternType.期間可変);

			fromCheckDate = dateCalcPatternUtil.stringToDateConverter("20201201", null);
			fromCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201210", null);
			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20210131", null);
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20210131", null);
			boolean result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("開始一致、変更可能期限終了、チェック対象日(終了)が同日の場合、期間内と判定されること", false, result);

			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20210131", null);
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("202102011", null);
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("開始一致、チェック対象日(終了)が変更可能期限終了より後の場合、期間外と判定されること", true, result);

			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20210131", null);
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20210201", null);
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("開始一致、チェック対象日(終了)が変更可能期限終了より後の場合、期間外と判定されること", true, result);

			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20210131", null);
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20210130", null);
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("開始一致、チェック対象日(終了)が変更可能期限終了より後の場合、期間内と判定されること", false, result);

			fromCheckDate = dateCalcPatternUtil.stringToDateConverter("20210101", null);
			fromCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20201210", null);
			toCheckDate = dateCalcPatternUtil.stringToDateConverter("20210131", null);
			toCheckTrgetDate = dateCalcPatternUtil.stringToDateConverter("20210201", null);
			result = (Boolean)method.invoke(contractChangeSpanControl, spanMaster, fromCheckDate, toCheckDate, fromCheckTrgetDate, toCheckTrgetDate);
			Assert.assertEquals("開始不一致、チェック対象日(終了)が変更可能期限終了より後の場合、期間外と判定されること", false, result);


		} catch(Exception e) {
			e.printStackTrace();
			fail("例外が発生している。");
		}
	}

	@Test
	public void チェック対象日取得テスト() throws NoSuchMethodException, SecurityException {

		Contract contrat = new Contract();
		// サービス利用希望日
		contrat.setConclusionPreferredDate(dateCalcPatternUtil.stringToDateConverter("20201205", null));
		// 解約希望日
		contrat.setCancelScheduledDate(dateCalcPatternUtil.stringToDateConverter("20210115", null));

		Method method = ContractChangeSpanControl.class.getDeclaredMethod("getCheckTargetDate", EntityBase.class, ContractChangeSpanTargetDateType.class);
		method.setAccessible(true);
		try {
			Date rsltDate = (Date)method.invoke(contractChangeSpanControl, contrat, ContractChangeSpanTargetDateType.契約承認依頼日);
			Assert.assertEquals("システム日付が設定されていること", dateCalcPatternUtil.dateToStringConverter(new Date(), null), dateCalcPatternUtil.dateToStringConverter(rsltDate, null));

			rsltDate = (Date)method.invoke(contractChangeSpanControl, contrat, ContractChangeSpanTargetDateType.サービス利用希望日);
			Assert.assertEquals("サービス利用希望日が設定されていること", "20201205", dateCalcPatternUtil.dateToStringConverter(rsltDate, null));

			rsltDate = (Date)method.invoke(contractChangeSpanControl, contrat, ContractChangeSpanTargetDateType.契約承認依頼日);
			Assert.assertEquals("システム日付が設定されていること", dateCalcPatternUtil.dateToStringConverter(new Date(), null), dateCalcPatternUtil.dateToStringConverter(rsltDate, null));

			rsltDate = (Date)method.invoke(contractChangeSpanControl, contrat, ContractChangeSpanTargetDateType.解約希望日);
			Assert.assertEquals("解約希望日が設定されていること", "20210115", dateCalcPatternUtil.dateToStringConverter(rsltDate, null));

		} catch(Exception e) {
			e.printStackTrace();
			fail("例外が発生している。");
		}
	}

	@Test
	public void チェック対象期間計算基準日取得() throws NoSuchMethodException, SecurityException {

		// 現契約
		Contract targetEntity = new Contract();
		// 現契約.サービス開始日
		targetEntity.setServiceTermStart(dateCalcPatternUtil.stringToDateConverter("20190401", null));
		// 現契約.サービス終了日
		targetEntity.setServiceTermEnd(dateCalcPatternUtil.stringToDateConverter("20211231", null));

		// 元契約
		Contract originContract = new Contract();
		// 元契約.サービス開始日
		originContract.setServiceTermStart(dateCalcPatternUtil.stringToDateConverter("201810011", null));
		// 元契約.サービス終了日
		originContract.setServiceTermEnd(dateCalcPatternUtil.stringToDateConverter("20201205", null));

		DateCalcPatternMaster dateCalcPatternMaster = new DateCalcPatternMaster();

		Method method = ContractChangeSpanControl.class.getDeclaredMethod("getCheckTermReferenceDate", DateCalcPatternMaster.class, EntityBase.class, Contract.class);
		method.setAccessible(true);
		try {
			dateCalcPatternMaster.setDateCalcStndType(DateCalcStndType.サービス終了日);
			Date rsltDate = (Date)method.invoke(contractChangeSpanControl, dateCalcPatternMaster, targetEntity, null);
			Assert.assertEquals("現契約のサービス終了日が設定されること", "20211231", dateCalcPatternUtil.dateToStringConverter(rsltDate, null));

			rsltDate = (Date)method.invoke(contractChangeSpanControl, dateCalcPatternMaster, targetEntity, originContract);
			Assert.assertEquals("元契約のサービス終了日が設定されること", "20201205", dateCalcPatternUtil.dateToStringConverter(rsltDate, null));

			dateCalcPatternMaster.setDateCalcStndType(DateCalcStndType.契約開始日);
			rsltDate = (Date)method.invoke(contractChangeSpanControl, dateCalcPatternMaster, targetEntity, originContract);
			Assert.assertEquals("現契約のサービス開始日が設定されること", "20190401", dateCalcPatternUtil.dateToStringConverter(rsltDate, null));

			dateCalcPatternMaster.setDateCalcStndType(DateCalcStndType.システム日付);
			rsltDate = (Date)method.invoke(contractChangeSpanControl, dateCalcPatternMaster, targetEntity, originContract);
			Assert.assertEquals("システム日付が設定されること", dateCalcPatternUtil.dateToStringConverter(new Date(), null), dateCalcPatternUtil.dateToStringConverter(rsltDate, null));

		} catch(Exception e) {
			e.printStackTrace();
			fail("例外が発生している。");
		}
	}

	@Test
	public void 契約変更期間判定_条件にNULLが設定されたレコードが取得されること() throws NoSuchMethodException, SecurityException {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterNullDataInsert.sql");

		ServiceCategory serviceCategory = ServiceCategory.契約;
		Long productMasterId = 1L;
		Long itemMasterId = null;
		ContractType contractType = ContractType.契約更新;
		ContractTypeDetails contractTypeDetail = ContractTypeDetails.アップグレード;
		String lifecycleStatus = "5";
		String workflowStatus = "6";
		Long transactionTableId = 4L;

		List<ErrorInfo> errors = contractChangeSpanControl.contractChangeSpanCheck(serviceCategory, productMasterId, itemMasterId, contractType, contractTypeDetail, lifecycleStatus, workflowStatus, transactionTableId);
		Assert.assertEquals("エラーリストの件数が１件であること", 1, errors.size());
		errors.stream().forEach(err -> {
			Assert.assertEquals("エラーIDが正しく設定されること", "RCO00045", err.getErrorId());
			Assert.assertEquals("契約更新可能期間外のため契約の承認依頼は行えません。（契約更新可能期間：aから、bまで）",  err.getErrorMessage());
		});
	}

	@Test
	public void 契約変更期間判定_条件に一致するレコードが取得されること() throws NoSuchMethodException, SecurityException {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterInsert.sql");

		ServiceCategory serviceCategory = ServiceCategory.契約;
		Long productMasterId = 1L;
		Long itemMasterId = 363L;
		ContractType contractType = ContractType.契約更新;
		ContractTypeDetails contractTypeDetail = ContractTypeDetails.新規;
		String lifecycleStatus = "2";
		String workflowStatus = "3";
		Long transactionTableId = 4L;

		List<ErrorInfo> errors = contractChangeSpanControl.contractChangeSpanCheck(serviceCategory, productMasterId, itemMasterId, contractType, contractTypeDetail, lifecycleStatus, workflowStatus, transactionTableId);
		Assert.assertEquals("エラーリストの件数が１件であること", 1, errors.size());
		errors.stream().forEach(err -> {
			Assert.assertEquals("エラーIDが正しく設定されること", "RCO00034", err.getErrorId());
			Assert.assertEquals("aを経過しているためbの反映はできません。サービス利用希望日にc以降の日付を指定してください。",  err.getErrorMessage());
		});
	}

	@Test
	public void 契約変更期間判定_追加条件式あり_該当なし() throws NoSuchMethodException, SecurityException {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterInsert.sql");

		ServiceCategory serviceCategory = ServiceCategory.契約;
		Long productMasterId = 4L;
		Long itemMasterId = null;
		ContractType contractType = ContractType.契約更新;
		ContractTypeDetails contractTypeDetail = ContractTypeDetails.新規;
		String lifecycleStatus = "2";
		String workflowStatus = "3";
		Long transactionTableId = 4L;

		List<ErrorInfo> errors = contractChangeSpanControl.contractChangeSpanCheck(serviceCategory, productMasterId, itemMasterId, contractType, contractTypeDetail, lifecycleStatus, workflowStatus, transactionTableId);
		Assert.assertEquals("エラーリストの件数が0件であること", 0, errors.size());;
	}

	@Test
	public void 契約変更期間判定_追加条件式あり_該当あり() throws NoSuchMethodException, SecurityException {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterInsert.sql");

		ServiceCategory serviceCategory = ServiceCategory.契約;
		Long productMasterId = 5L;
		Long itemMasterId = null;
		ContractType contractType = ContractType.契約更新;
		ContractTypeDetails contractTypeDetail = ContractTypeDetails.新規;
		String lifecycleStatus = "2";
		String workflowStatus = "3";
		Long transactionTableId = 5L;

		List<ErrorInfo> errors = contractChangeSpanControl.contractChangeSpanCheck(serviceCategory, productMasterId, itemMasterId, contractType, contractTypeDetail, lifecycleStatus, workflowStatus, transactionTableId);
		Assert.assertEquals("エラーリストの件数が１件であること", 1, errors.size());
		errors.stream().forEach(err -> {
			Assert.assertEquals("エラーIDが正しく設定されること", "RCO00034", err.getErrorId());
			Assert.assertEquals("aを経過しているためbの反映はできません。サービス利用希望日にc以降の日付を指定してください。",  err.getErrorMessage());
		});
	}

	@Test
	public void 契約変更期間判定_開始終了範囲外() throws NoSuchMethodException, SecurityException {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterInsert.sql");

		ServiceCategory serviceCategory = ServiceCategory.契約;
		Long productMasterId = 6L;
		Long itemMasterId = null;
		ContractType contractType = ContractType.契約更新;
		ContractTypeDetails contractTypeDetail = ContractTypeDetails.新規;
		String lifecycleStatus = "2";
		String workflowStatus = "3";
		Long transactionTableId = 5L;

		List<ErrorInfo> errors = contractChangeSpanControl.contractChangeSpanCheck(serviceCategory, productMasterId, itemMasterId, contractType, contractTypeDetail, lifecycleStatus, workflowStatus, transactionTableId);
		Assert.assertEquals("エラーリストの件数が１件であること", 1, errors.size());
		errors.stream().forEach(err -> {
			Assert.assertEquals("エラーIDが正しく設定されること", "RCO00037", err.getErrorId());
			Assert.assertEquals("解約予定日はaからbの範囲で指定してください。",  err.getErrorMessage());
		});
	}

	@Test
	public void 契約変更期間判定_全解約() throws NoSuchMethodException, SecurityException {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterInsert.sql");

		ServiceCategory serviceCategory = ServiceCategory.契約;
		Long productMasterId = 10L;
		Long itemMasterId = null;
		ContractType contractType = null;
		ContractTypeDetails contractTypeDetail = null;
		String lifecycleStatus = "2";
		String workflowStatus = "3";
		Long transactionTableId = 5L;

		List<ErrorInfo> errors = contractChangeSpanControl.contractChangeSpanCheck(serviceCategory, productMasterId, itemMasterId, contractType, contractTypeDetail, lifecycleStatus, workflowStatus, transactionTableId);
		Assert.assertEquals("エラーリストの件数が１件であること", 1, errors.size());
		errors.stream().forEach(err -> {
			Assert.assertEquals("エラーIDが正しく設定されること", "RCO00037", err.getErrorId());
			Assert.assertEquals("解約予定日はaからbの範囲で指定してください。",  err.getErrorMessage());
		});
	}

	@Test
	public void 契約変更期間判定_契約変更_当月_エラーフィールドの追加() throws NoSuchMethodException, SecurityException {

		テストデータ作成();
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testContractChangeSpanMasterInsert.sql");

		ServiceCategory serviceCategory = ServiceCategory.契約;
		Long productMasterId = 11L;
		Long itemMasterId = null;
		ContractType contractType = ContractType.契約変更;
		;
		ContractTypeDetails contractTypeDetail = ContractTypeDetails.プラン_オプション_減数_削除;
		String lifecycleStatus = "1";
		String workflowStatus = "2";
		Long transactionTableId = 5L;

		List<ErrorInfo> errors = contractChangeSpanControl.contractChangeSpanCheck(serviceCategory, productMasterId, itemMasterId, contractType, contractTypeDetail, lifecycleStatus, workflowStatus, transactionTableId);
		Assert.assertEquals("エラーリストの件数が１件であること", 1, errors.size());
		errors.stream().forEach(err -> {
			Assert.assertEquals("エラーIDが正しく設定されること", "RCO00048", err.getErrorId());
			Assert.assertEquals("サービス利用希望日が当月の契約変更は行えません。", err.getErrorMessage());
			Assert.assertEquals("conclusionPreferredDate", err.getErrorField());
		});
	}
	private void テストデータ作成() {
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/testDateCalcPatternMasterInsert.sql");
		context.getBean(DBConfig.class).initTargetTestData("sql/contractChangeSpanControl/contract.sql");
	}
}