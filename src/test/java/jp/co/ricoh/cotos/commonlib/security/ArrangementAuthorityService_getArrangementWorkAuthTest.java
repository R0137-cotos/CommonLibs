package jp.co.ricoh.cotos.commonlib.security;

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
import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkAuthControlMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.ActionDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AuthDiv;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService.AuthLevel;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArrangementAuthorityService_getArrangementWorkAuthTest {

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
	public void 正常系_2200しか持たない場合() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/マスタ関連.sql");

		List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList = arrangementAuthorityService.getArrangementWorkAuth();

		Assert.assertEquals("取得結果数が等しいこと", 1, arrangementWorkAuthControlMasterList.size());
		Assert.assertEquals("取得IDが等しいこと", 1, arrangementWorkAuthControlMasterList.get(0).getId());
		Assert.assertNull("権限パターンマスタの情報がnullであること", arrangementWorkAuthControlMasterList.get(0).getAuthPatternMaster());
	}

	@Test
	@WithMockCustomUserAuthArray(actionDiv = ActionDiv.更新, auth = { @SubWithMockCustomUserAuth(authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.マネージド, authLevel = AuthLevel.すべて) })
	public void 正常系_2200と2250を持つ場合() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/マスタ関連.sql");

		List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList = arrangementAuthorityService.getArrangementWorkAuth();

		Assert.assertEquals("取得結果数が等しいこと", 1, arrangementWorkAuthControlMasterList.size());
		Assert.assertEquals("取得IDが等しいこと", 3, arrangementWorkAuthControlMasterList.get(0).getId());
		Assert.assertNotNull("権限パターンマスタの情報がnullでないこと", arrangementWorkAuthControlMasterList.get(0).getAuthPatternMaster());
	}

	@Test
	@WithMockCustomUserAuthArray(actionDiv = ActionDiv.更新, auth = { @SubWithMockCustomUserAuth(authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.マネージド, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.リペア, authLevel = AuthLevel.すべて) })
	public void 正常系_2200と2250と2260を持つ場合() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/マスタ関連.sql");

		List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList = arrangementAuthorityService.getArrangementWorkAuth();

		Assert.assertEquals("取得結果数が等しいこと", 2, arrangementWorkAuthControlMasterList.size());
		Assert.assertNotNull("マネージドのIDが取得できていること", arrangementWorkAuthControlMasterList.stream().filter(a -> a.getId() == 3).findFirst().orElseGet(null));
		Assert.assertNotNull("マネージドの権限パターンマスタの情報がnullでないこと", arrangementWorkAuthControlMasterList.stream().filter(a -> a.getId() == 3).findFirst().get().getAuthPatternMaster());
		Assert.assertNotNull("リペアのIDが取得できていること", arrangementWorkAuthControlMasterList.stream().filter(a -> a.getId() == 4).findFirst().orElseGet(null));
		Assert.assertNotNull("リペアの権限パターンマスタの情報がnullでないこと", arrangementWorkAuthControlMasterList.stream().filter(a -> a.getId() == 4).findFirst().get().getAuthPatternMaster());
	}

	@Test
	@WithMockCustomUserAuthArray(actionDiv = ActionDiv.更新, auth = { @SubWithMockCustomUserAuth(authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.システム管理, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.リペア, authLevel = AuthLevel.すべて) })
	public void 作業不要パターン_システム管理者を含む_2200と2220と2260を持つ場合() {

		List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList = arrangementAuthorityService.getArrangementWorkAuth();

		Assert.assertNull("取得結果がnullであること", arrangementWorkAuthControlMasterList);
	}

	@Test
	@WithMockCustomUserAuthArray(momEmployeeId = "COTOS_BATCH_USER", actionDiv = ActionDiv.更新, auth = { @SubWithMockCustomUserAuth(authDiv = AuthDiv.見積_契約_手配, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.マネージド, authLevel = AuthLevel.すべて), @SubWithMockCustomUserAuth(authDiv = AuthDiv.リペア, authLevel = AuthLevel.すべて) })
	public void 作業不要パターン_スーパーユーザー且つ2200と2250と2260の場合() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/スーパーユーザー.sql");

		List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList = arrangementAuthorityService.getArrangementWorkAuth();

		Assert.assertNull("取得結果がnullであること", arrangementWorkAuthControlMasterList);
	}

	@Test
	@WithMockCustomUser(momEmployeeId = "COTOS_BATCH_USER")
	public void 作業不要パターン_スーパーユーザーの場合() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("sql/arrangementAuthorityService/スーパーユーザー.sql");

		List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList = arrangementAuthorityService.getArrangementWorkAuth();

		Assert.assertNull("取得結果がnullであること", arrangementWorkAuthControlMasterList);
	}
}
