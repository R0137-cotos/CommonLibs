package jp.co.ricoh.cotos.commonlib.repository;

import java.io.Serializable;
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
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmResponseWork.TmLicenceMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmLinkManagement;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmTransitionSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserResponseWork;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateCustomerRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateCustomerResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmLinkManagementRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmSuspendSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmSuspendSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmTransitionSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmTransitionSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateCustomerRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateCustomerResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateSubscriptionResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateUserRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmUpdateUserResponseWorkRepository;

/**
 * トレンドマイクロ連携WORK
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestTmWorks {

	static ConfigurableApplicationContext context;

	@Autowired
	TestTools testTool;

	@Autowired
	TmLinkManagementRepository tmLinkManagementRepository;

	@Autowired
	TmCreateCustomerRequestWorkRepository tmCreateCustomerRequestWorkRepository;

	@Autowired
	TmCreateCustomerResponseWorkRepository tmCreateCustomerResponseWorkRepository;

	@Autowired
	TmCreateSubscriptionRequestWorkRepository tmCreateSubscriptionRequestWorkRepository;

	@Autowired
	TmCreateSubscriptionResponseWorkRepository tmCreateSubscriptionResponseWorkRepository;

	@Autowired
	TmSuspendSubscriptionRequestWorkRepository tmSuspendSubscriptionRequestWorkRepository;

	@Autowired
	TmSuspendSubscriptionResponseWorkRepository tmSuspendSubscriptionResponseWorkRepository;

	@Autowired
	TmUpdateCustomerRequestWorkRepository tmUpdateCustomerRequestWorkRepository;

	@Autowired
	TmUpdateCustomerResponseWorkRepository tmUpdateCustomerResponseWorkRepository;

	@Autowired
	TmUpdateSubscriptionRequestWorkRepository tmUpdateSubscriptionRequestWorkRepository;

	@Autowired
	TmUpdateSubscriptionResponseWorkRepository tmUpdateSubscriptionResponseWorkRepository;

	@Autowired
	TmUpdateUserRequestWorkRepository tmUpdateUserRequestWorkRepository;

	@Autowired
	TmUpdateUserResponseWorkRepository tmUpdateUserResponseWorkRepository;

	@Autowired
	TmTransitionSubscriptionRequestWorkRepository tmTransitionSubscriptionRequestWorkRepository;

	@Autowired
	TmTransitionSubscriptionResponseWorkRepository tmTransitionSubscriptionResponseWorkRepository;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/license/cas/tm/tmWorks.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	/**
	 * 個別にテストを流すとデータのInsert/Deleteに時間がかかるので、
	 * 一つのテストメソッドで実施する。
	 */
	@Test
	public void 各Repositoryのテスト() {
		TmLinkManagementRepositoryのテスト();
		TmCreateCustomerRequestWorkRepositoryのテスト();
		TmCreateCustomerResponseWorkRepositoryのテスト();
		TmCreateSubscriptionRequestWorkRepositoryのテスト();
		TmCreateSubscriptionResponseWorkRepositoryのテスト();
		TmSuspendSubscriptionRequestWorkRepositoryのテスト();
		TmSuspendSubscriptionResponseWorkRepositoryのテスト();
		TmUpdateCustomerRequestWorkRepositoryのテスト();
		TmUpdateCustomerResponseWorkRepositoryのテスト();
		TmUpdateSubscriptionRequestWorkRepositoryのテスト();
		TmUpdateSubscriptionResponseWorkRepositoryのテスト();
		TmUpdateUserRequestWorkRepositoryのテスト();
		TmUpdateUserResponseWorkRepositoryのテスト();
		TmTransitionSubscriptionRequestWorkRepositoryのテスト();
		TmTransitionSubscriptionResponseWorkRepositoryのテスト();
	}

	private void TmLinkManagementRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmLinkManagementRepository, 10L);
		// ID
		List<TmLinkManagement> found = tmLinkManagementRepository.findByIdBetween(10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmLinkManagementRepository.findByIdBetween(11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmLinkManagementRepository.findByIdBetween(10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
		TmUpdateCustomerRequestWork tmUpdateCustomerRequestWork = tmUpdateCustomerRequestWorkRepository.findOne(10L);
		found = tmLinkManagementRepository.findByTmUpdateCustomerRequestWork(tmUpdateCustomerRequestWork);
		Assert.assertNotNull(found);
		Assert.assertEquals("トレンドマイクロ会社情報更新リクエストWORKをキーにレコードが取得されること ", 1, found.size());
		TmCreateCustomerRequestWork tmCreateCustomerRequestWork = tmCreateCustomerRequestWorkRepository.findOne(10L);
		found = tmLinkManagementRepository.findByTmCreateCustomerRequestWork(tmCreateCustomerRequestWork);
		Assert.assertNotNull(found);
		Assert.assertEquals("トレンドマイクロ顧客情報作成リクエストWORKをキーにレコードが取得されること ", 1, found.size());
		TmCreateSubscriptionRequestWork tmCreateSubscriptionRequestWork = tmCreateSubscriptionRequestWorkRepository.findOne(10L);
		found = tmLinkManagementRepository.findByTmCreateSubscriptionRequestWork(tmCreateSubscriptionRequestWork);
		Assert.assertNotNull(found);
		Assert.assertEquals("トレンドマイクロサブスクリプション作成リクエストWORKをキーにレコードが取得されること ", 1, found.size());
		TmUpdateSubscriptionRequestWork tmUpdateSubscriptionRequestWork = tmUpdateSubscriptionRequestWorkRepository.findOne(10L);
		found = tmLinkManagementRepository.findByTmUpdateSubscriptionRequestWork(tmUpdateSubscriptionRequestWork);
		Assert.assertNotNull(found);
		Assert.assertEquals("トレンドマイクロサブスクリプション更新リクエストWORKをキーにレコードが取得されること ", 1, found.size());
		TmSuspendSubscriptionRequestWork tmSuspendSubscriptionRequestWork = tmSuspendSubscriptionRequestWorkRepository.findOne(10L);
		found = tmLinkManagementRepository.findByTmSuspendSubscriptionRequestWork(tmSuspendSubscriptionRequestWork);
		Assert.assertNotNull(found);
		Assert.assertEquals("トレンドマイクロサブスクリプション解約リクエストWORKをキーにレコードが取得されること ", 1, found.size());
		TmTransitionSubscriptionRequestWork tmTransitionSubscriptionRequestWork = tmTransitionSubscriptionRequestWorkRepository.findOne(10L);
		found = tmLinkManagementRepository.findByTmTransitionSubscriptionRequestWork(tmTransitionSubscriptionRequestWork);
		Assert.assertNotNull(found);
		Assert.assertEquals("トレンドマイクロサブスクリプション乗換リクエストWORKをキーにレコードが取得されること ", 1, found.size());
	}

	private void TmCreateCustomerRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmCreateCustomerRequestWorkRepository, 10L);
		// 送信状態
		List<TmCreateCustomerRequestWork> found = tmCreateCustomerRequestWorkRepository.findByRequestStatus(TmRequestStatus.未連携);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmCreateCustomerRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmCreateCustomerRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmCreateCustomerRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmCreateCustomerResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmCreateCustomerResponseWorkRepository, 10L);
		// 送信状態
		List<TmCreateCustomerResponseWork> found = tmCreateCustomerResponseWorkRepository.findByLicenceMappedStatus(TmLicenceMappedStatus.未反映);
		Assert.assertNotNull(found);
		Assert.assertEquals("未反映のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmCreateCustomerResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmCreateCustomerResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmCreateCustomerResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmCreateSubscriptionRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmCreateSubscriptionRequestWorkRepository, 10L);
		// 送信状態
		List<TmCreateSubscriptionRequestWork> found = tmCreateSubscriptionRequestWorkRepository.findByRequestStatus(TmRequestStatus.未連携);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmCreateSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmCreateSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmCreateSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmCreateSubscriptionResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmCreateSubscriptionResponseWorkRepository, 10L);
		// 送信状態
		List<TmCreateSubscriptionResponseWork> found = tmCreateSubscriptionResponseWorkRepository.findByLicenceMappedStatus(TmLicenceMappedStatus.未反映);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmCreateSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmCreateSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmCreateSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmSuspendSubscriptionRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmSuspendSubscriptionRequestWorkRepository, 10L);
		// 送信状態
		List<TmSuspendSubscriptionRequestWork> found = tmSuspendSubscriptionRequestWorkRepository.findByRequestStatus(TmRequestStatus.未連携);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmSuspendSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmSuspendSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmSuspendSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmSuspendSubscriptionResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmSuspendSubscriptionResponseWorkRepository, 10L);
		// 送信状態
		List<TmSuspendSubscriptionResponseWork> found = tmSuspendSubscriptionResponseWorkRepository.findByLicenceMappedStatus(TmLicenceMappedStatus.未反映);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmSuspendSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmSuspendSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmSuspendSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmUpdateCustomerRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmUpdateCustomerRequestWorkRepository, 10L);
		// 送信状態
		List<TmUpdateCustomerRequestWork> found = tmUpdateCustomerRequestWorkRepository.findByRequestStatus(TmRequestStatus.未連携);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmUpdateCustomerRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmUpdateCustomerRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmUpdateCustomerRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmUpdateCustomerResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmUpdateCustomerResponseWorkRepository, 10L);
		// 送信状態
		List<TmUpdateCustomerResponseWork> found = tmUpdateCustomerResponseWorkRepository.findByLicenceMappedStatus(TmLicenceMappedStatus.未反映);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmUpdateCustomerResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmUpdateCustomerResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmUpdateCustomerResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmUpdateSubscriptionRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmUpdateSubscriptionRequestWorkRepository, 10L);
		// 送信状態
		List<TmUpdateSubscriptionRequestWork> found = tmUpdateSubscriptionRequestWorkRepository.findByRequestStatus(TmRequestStatus.未連携);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmUpdateSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmUpdateSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmUpdateSubscriptionRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmUpdateSubscriptionResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmUpdateSubscriptionResponseWorkRepository, 10L);
		// 送信状態
		List<TmUpdateSubscriptionResponseWork> found = tmUpdateSubscriptionResponseWorkRepository.findByLicenceMappedStatus(TmLicenceMappedStatus.未反映);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmUpdateSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmUpdateSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmUpdateSubscriptionResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmUpdateUserRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmUpdateUserRequestWorkRepository, 10L);
		// 送信状態
		List<TmUpdateUserRequestWork> found = tmUpdateUserRequestWorkRepository.findByRequestStatus(TmRequestStatus.未連携);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmUpdateUserRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmUpdateUserRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmUpdateUserRequestWorkRepository.findByRequestStatusAndIdBetween(TmRequestStatus.未連携, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmUpdateUserResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmUpdateUserResponseWorkRepository, 10L);
		// 送信状態
		List<TmUpdateUserResponseWork> found = tmUpdateUserResponseWorkRepository.findByLicenceMappedStatus(TmLicenceMappedStatus.未反映);
		Assert.assertNotNull(found);
		Assert.assertEquals("未連携のレコードが取得されていること", 2, found.size());
		// 送信状態 + ID
		found = tmUpdateUserResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること 境界値と等しい", 2, found.size());
		found = tmUpdateUserResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 11L, 20L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること from境界値 ", 1, found.size());
		found = tmUpdateUserResponseWorkRepository.findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus.未反映, 10L, 19L);
		Assert.assertNotNull(found);
		Assert.assertEquals("from~toのレコードが取得されていること to境界値 ", 1, found.size());
	}

	private void TmTransitionSubscriptionRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmTransitionSubscriptionRequestWorkRepository, 10L);
	}

	private void TmTransitionSubscriptionResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(tmTransitionSubscriptionResponseWorkRepository, 10L);
	}

	private <T extends EntityBase, ID extends Serializable> void 全てのカラムがNullではないことを確認_共通(CrudRepository<T, ID> repository, @SuppressWarnings("unchecked") ID... ids) {
		// テストデータ登録

		List<ID> idList = Arrays.asList(ids);

		idList.stream().forEach(id -> {
			// データが取得できることを確認
			T found = repository.findOne(id);
			Assert.assertNotNull(found);
			// 全てのカラムがNullではないことを確認
			this.assertColumnsNotNull(found);
		});
	}

	/**
	 * 全てのカラムがNullではないことを確認
	 * @param <T>
	 * @param entity
	 */
	private <T extends EntityBase> void assertColumnsNotNull(T entity) {
		try {
			testTool.assertColumnsNotNull(entity);
		} catch (Exception e1) {
			Assert.fail("例外が発生した場合、エラー");
		}
	}
}
