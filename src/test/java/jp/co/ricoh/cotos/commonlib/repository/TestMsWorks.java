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
import jp.co.ricoh.cotos.commonlib.entity.license.ms.AbstractMsResponseWork.MsResponseMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsCustomerRegisterRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsCustomerRegisterResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsSubscriptionRegisterRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsSubscriptionRegisterResponseWork;
import jp.co.ricoh.cotos.commonlib.repository.license.ms.MsCustomerRegisterRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.ms.MsCustomerRegisterResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.ms.MsSubscriptionRegisterRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.ms.MsSubscriptionRegisterResponseWorkRepository;

/**
 * MS連携WORK
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestMsWorks {

	static ConfigurableApplicationContext context;

	@Autowired
	TestTools testTool;

	@Autowired
	MsCustomerRegisterRequestWorkRepository msCustomerRegisterRequestWorkRepository;

	@Autowired
	MsCustomerRegisterResponseWorkRepository msCustomerRegisterResponseWorkRepository;

	@Autowired
	MsSubscriptionRegisterRequestWorkRepository msSubscriptionRegisterRequestWorkRepository;

	@Autowired
	MsSubscriptionRegisterResponseWorkRepository msSubscriptionRegisterResponseWorkRepository;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/license/ms/msWorks.sql");
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
		MsCustomerRegisterRequestWorkRepositoryのテスト();
		MsCustomerRegisterResponseWorkRepositoryのテスト();
		MsSubscriptionRegisterRequestWorkRepositoryのテスト();
		MsSubscriptionRegisterResponseWorkRepositoryのテスト();
	}

	private void MsCustomerRegisterRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(msCustomerRegisterRequestWorkRepository, 10L);

		// 契約IDから取得できること
		MsCustomerRegisterRequestWork entity = msCustomerRegisterRequestWorkRepository.findByContractId(1L);
		Assert.assertNotNull(entity);
		// 全てのカラムがNullではないことを確認
		this.assertColumnsNotNull(entity);
	}

	private void MsCustomerRegisterResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(msCustomerRegisterResponseWorkRepository, 10L);

		//レスポンス反映状態から取得できること
		List<MsCustomerRegisterResponseWork> entity = msCustomerRegisterResponseWorkRepository.findByProcessStatus(MsResponseMappedStatus.反映済);

		Assert.assertEquals("2件取得できていること", 2, entity.size());

		entity.stream().forEach(data -> {
			Assert.assertNotNull(data);
			// 全てのカラムがNullではないことを確認
			this.assertColumnsNotNull(data);
		});

		// 契約IDから取得できること
		MsCustomerRegisterResponseWork entity2 = msCustomerRegisterResponseWorkRepository.findByContractId(1L);
		Assert.assertNotNull(entity2);
		// 全てのカラムがNullではないことを確認
		this.assertColumnsNotNull(entity2);
	}

	private void MsSubscriptionRegisterRequestWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(msSubscriptionRegisterRequestWorkRepository, 10L);

		// ライセンスIDから取得できること
		List<MsSubscriptionRegisterRequestWork> entity = msSubscriptionRegisterRequestWorkRepository.findByLicenseInfoId(1L);
		// 取得件数チェック
		Assert.assertEquals("2件取得できていること", 2, entity.size());
		// 全てのカラムがNullでないことを確認する
		entity.stream().forEach(data -> {
			Assert.assertNotNull(data);
			// 全てのカラムがNullではないことを確認
			this.assertColumnsNotNull(data);
		});

		// 顧客テナントID・製品ID・ライセンスNOから取得できること
		MsSubscriptionRegisterRequestWork entity2 = msSubscriptionRegisterRequestWorkRepository.findByCustomerIdAndOfferIdAndLicenseNo("1", "1", "1");
		Assert.assertNotNull(entity2);
		// 全てのカラムがNullではないことを確認
		this.assertColumnsNotNull(entity2);
	}

	private void MsSubscriptionRegisterResponseWorkRepositoryのテスト() {
		this.全てのカラムがNullではないことを確認_共通(msSubscriptionRegisterResponseWorkRepository, 10L);

		//レスポンス反映状態から取得できること
		List<MsSubscriptionRegisterResponseWork> entity = msSubscriptionRegisterResponseWorkRepository.findByProcessStatus(MsResponseMappedStatus.反映済);

		Assert.assertEquals("2件取得できていること", 2, entity.size());

		entity.stream().forEach(data -> {
			Assert.assertNotNull(data);
			// 全てのカラムがNullではないことを確認
			this.assertColumnsNotNull(data);
		});

		// ライセンスNOから取得できること
		MsSubscriptionRegisterResponseWork entity2 = msSubscriptionRegisterResponseWorkRepository.findByLicenseNo("1");
		Assert.assertNotNull(entity2);
		// 全てのカラムがNullではないことを確認
		this.assertColumnsNotNull(entity2);
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
