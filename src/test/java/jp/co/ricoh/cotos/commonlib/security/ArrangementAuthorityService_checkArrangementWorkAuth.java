package jp.co.ricoh.cotos.commonlib.security;

import static org.junit.Assert.*;

import java.util.Arrays;
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
import jp.co.ricoh.cotos.commonlib.SubWithMockCustomUserAuth;
import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.WithMockCustomUserAuthArray;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.ActionDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AuthDiv;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService.AuthLevel;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArrangementAuthorityService_checkArrangementWorkAuth {

	@Autowired
	ArrangementAuthorityService arrangementAuthorityService;

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
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて)
	public void 正常系_2200_対象手配業務のみを指定() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/マスタ関連.sql");
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/手配業務.sql");

		List<ErrorInfo> errorList = arrangementAuthorityService.checkArrangementWorkAuth(Arrays.asList(401L));

		Assert.assertNotNull("チェック結果がnullでないこと", errorList);
		Assert.assertEquals("空のリストが作成されていること", 0, errorList.size());
	}

	@Test
	@WithMockCustomUserAuthArray(actionDiv = ActionDiv.更新, auth = { @SubWithMockCustomUserAuth(authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.リペア, authLevel = AuthLevel.すべて) })
	public void 正常系_2200と2260_対象手配業務のみを指定() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/マスタ関連.sql");
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/手配業務.sql");

		List<ErrorInfo> errorList = arrangementAuthorityService.checkArrangementWorkAuth(Arrays.asList(501L));

		Assert.assertNotNull("チェック結果がnullでないこと", errorList);
		Assert.assertEquals("空のリストが作成されていること", 0, errorList.size());
	}

	@Test
	@WithMockCustomUserAuthArray(momEmployeeId = "COTOS_BATCH_USER", actionDiv = ActionDiv.更新, auth = { @SubWithMockCustomUserAuth(authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.マネージド, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.リペア, authLevel = AuthLevel.すべて) })
	public void 作業不要パターン_スーパーユーザー且つ2200と2250と2260の場合() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/スーパーユーザー.sql");

		List<ErrorInfo> errorList = arrangementAuthorityService.checkArrangementWorkAuth(Arrays.asList(1L, 2L));

		Assert.assertNull("チェック結果がnullであること", errorList);
	}

	@Test
	public void 異常系_引数がnull() {

		try {
			arrangementAuthorityService.checkArrangementWorkAuth(null);
			Assert.fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals("エラー数が等しいこと", 1, errorList.size());
			assertEquals("エラーIDが正しく設定されること", "ROT00001", errorList.get(0).getErrorId());
			assertEquals("エラーメッセージが正しく設定されること", "パラメータ「手配業務IDリスト」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			Assert.fail("想定外のエラーが発生");
		}
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて)
	public void 異常系_存在しない手配業務IDを指定() {
		try {
			arrangementAuthorityService.checkArrangementWorkAuth(Arrays.asList(999L));
			Assert.fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals("エラー数が等しいこと", 1, errorList.size());
			assertEquals("エラーIDが正しく設定されること", "RAR00001", errorList.get(0).getErrorId());
			assertEquals("エラーメッセージが正しく設定されること", "指定した手配業務が存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			Assert.fail("想定外のエラーが発生");
		}
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて)
	public void 異常系_2200_手配業務に作業不可のIDを指定() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/マスタ関連.sql");
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/手配業務.sql");

		List<ErrorInfo> errorList = arrangementAuthorityService.checkArrangementWorkAuth(Arrays.asList(401L, 501L));

		Assert.assertNotNull("チェック結果がnullでないこと", errorList);
		Assert.assertEquals("リストのサイズが想定通りであること", 1, errorList.size());
		assertEquals("エラーIDが正しく設定されること", "RAR00031", errorList.get(0).getErrorId());
		assertEquals("エラーメッセージが正しく設定されること", "文書番号「CC2018091900501」の手配業務「政策在庫手配」に対する実行権限がありません。", errorList.get(0).getErrorMessage());
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて)
	public void 異常系_2200_手配業務に手配業務タイプマスタにない情報を紐づけ() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/マスタ関連.sql");
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/手配業務.sql");

		try {
			arrangementAuthorityService.checkArrangementWorkAuth(Arrays.asList(402L));
			Assert.fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals("エラー数が等しいこと", 1, errorList.size());
			assertEquals("エラーIDが正しく設定されること", "RAR00001", errorList.get(0).getErrorId());
			assertEquals("エラーメッセージが正しく設定されること", "指定した手配業務が存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			Assert.fail("想定外のエラーが発生");
		}
	}

	@Test
	@WithMockCustomUserAuthArray(actionDiv = ActionDiv.更新, auth = { @SubWithMockCustomUserAuth(authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.リペア, authLevel = AuthLevel.すべて) })
	public void 異常系_2200と2260_手配業務に作業不可のIDを指定() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/マスタ関連.sql");
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/手配業務.sql");

		List<ErrorInfo> errorList = arrangementAuthorityService.checkArrangementWorkAuth(Arrays.asList(401L, 501L));

		Assert.assertNotNull("チェック結果がnullでないこと", errorList);
		Assert.assertEquals("リストのサイズが想定通りであること", 1, errorList.size());
		assertEquals("エラーIDが正しく設定されること", "RAR00031", errorList.get(0).getErrorId());
		assertEquals("エラーメッセージが正しく設定されること", "文書番号「CC2018091900401」の手配業務「CE手配」に対する実行権限がありません。", errorList.get(0).getErrorMessage());
	}
}
