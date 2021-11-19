package jp.co.ricoh.cotos.commonlib.check;
/**
 * Merakiルータ連携契約ID関連チェックメソッドのテストクラス
 */

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
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestMssLinkageRjManageNumberCheck {

	@Autowired
	CheckUtil checkUtil;

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
	public void TestMssLinkageRjManageNumberCheck_エラーなし() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMasterDetail.sql");
		context.getBean(DBConfig.class).initTargetTestData("check/testMssLinkageRjManageNumberCheck_エラーなし.sql");
		List<ErrorInfo> errorInfo = checkUtil.mssLinkageRjManageNumberCheck("1234567890", "000000002033411");
		Assert.assertEquals("エラーが発生しないこと", 0, errorInfo.size());
	}

	@Test
	public void TestMssLinkageRjManageNumberCheck_締結中データなし() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("check/testMssLinkageRjManageNumberCheck_締結中データなし.sql");
		try {
			checkUtil.mssLinkageRjManageNumberCheck("1234567890", "000000002033411");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals("エラーが発生すること", 1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00050");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "MSS連携契約IDに入力された契約IDに該当する契約が存在しない、または該当契約が条件(締結中,企業IDが一致,ルーター/UTMプランあり)を満たしていません。");
		}
	}

	@Test
	public void TestMssLinkageRjManageNumberCheck_MoM企業ID不一致() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("check/testMssLinkageRjManageNumberCheck_エラーなし.sql");
		try {
			checkUtil.mssLinkageRjManageNumberCheck("1234567890", "000000002033410");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals("エラーが発生すること", 1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00050");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "MSS連携契約IDに入力された契約IDに該当する契約が存在しない、または該当契約が条件(締結中,企業IDが一致,ルーター/UTMプランあり)を満たしていません。");
		}
	}

	@Test
	public void TestMssLinkageRjManageNumberCheck_MSSの月額品種が積み上がっていない() throws Exception {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/commonMasterDetail.sql");
		context.getBean(DBConfig.class).initTargetTestData("check/testMssLinkageRjManageNumberCheck_月額品種なし.sql");
		try {
			checkUtil.mssLinkageRjManageNumberCheck("1234567890", "000000002033411");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals("エラーが発生すること", 1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00050");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "MSS連携契約IDに入力された契約IDに該当する契約が存在しない、または該当契約が条件(締結中,企業IDが一致,ルーター/UTMプランあり)を満たしていません。");
		}
	}
}
