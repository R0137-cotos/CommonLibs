package jp.co.ricoh.cotos.commonlib.repository;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.WorkflowType;
import jp.co.ricoh.cotos.commonlib.entity.communication.Communication;
import jp.co.ricoh.cotos.commonlib.entity.communication.Contact;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.provider.ApplicationContextProvider;
import jp.co.ricoh.cotos.commonlib.repository.communication.CommunicationHistoryRepository;
import jp.co.ricoh.cotos.commonlib.repository.communication.CommunicationRepository;
import jp.co.ricoh.cotos.commonlib.repository.communication.ContactRepository;
import jp.co.ricoh.cotos.commonlib.repository.communication.ContactToRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestCommunication {

	@Autowired
	CommunicationRepository communicationRepository;

	@Autowired
	CommunicationHistoryRepository communicationHistoryRepository;

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	ContactToRepository contactToRepository;

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	TestTools testTools;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/communication.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			//context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void 全てのカラムがNullではないことを確認_コミュニケーション() {
		全てのカラムがNullではないことを確認_共通(communicationRepository, 1L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_コミュニケーション履歴() {
		全てのカラムがNullではないことを確認_共通(communicationHistoryRepository, 1L);
	}

	@Test
	public void 全てのカラムがNullではないことを確認_問い合わせ() {
		全てのカラムがNullではないことを確認_共通(contactRepository, 5L);
	}

	@Test
	public void 検証() {
		Communication expected = communicationRepository.findOne(4L);

		ApplicationContext context2 = ApplicationContextProvider.getApplicationContext();
		CommunicationRepository repository = context2.getBean(CommunicationRepository.class);
		Communication actual = repository.findOne(4L);

		Assert.assertEquals("ワークフローの値が1であること", "1", expected.getWorkflowType().toString());
		Assert.assertEquals("ワークフローが一致すること", expected.getWorkflowType(), actual.getWorkflowType());
	}

	@Test
	@Transactional
	public void 検証_SpringBoot() {
		Communication expected = communicationRepository.findOne(4L);

		Assert.assertEquals("ワークフローの値が1であること", "1", expected.getWorkflowType().toString());

		expected.setWorkflowType(WorkflowType.タスクフロー);
		expected.setCreatedUserId("aaaaa");
		communicationRepository.save(expected);

		Communication expected2 = communicationRepository.findOne(4L);
		Assert.assertEquals("ワークフローの値が2であること", "2", expected2.getWorkflowType().toString());
	}

	@Test
	@WithMockCustomUser
	public void CustomerEstimationListenerのテスト() throws Exception {

		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/estimation/estimation_all.sql");

		Estimation estimation = estimationRepository.findOne(4L);
		estimation.setLifecycleStatus(LifecycleStatus.作成完了);
		estimationRepository.save(estimation);

	}

	@Test
	public void 検証_非SpringBoot() {

		// コンテキスト取得
		ApplicationContext context2 = ApplicationContextProvider.getApplicationContext();

		// トランザクション開始
		JpaTransactionManager transactionManager = context2.getBean(JpaTransactionManager.class);
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		// JPAレポジトリ取得
		CommunicationRepository repository = context2.getBean(CommunicationRepository.class);

		try {
			Communication actual = repository.findOne(4L);
			actual.setWorkflowType(WorkflowType.タスクフロー);
			actual.setCreatedUserId("aaaaa");
			repository.save(actual);
			//transactionManager.commit(status);
		} catch (Exception e) {
			//transactionManager.rollback(status);
		}

		Communication actual2 = repository.findOne(4L);
		Assert.assertEquals("ワークフローの値が2であること", "2", actual2.getWorkflowType().toString());
	}

	@Test
	public void CommunicationRepositoryの条件テスト() {
		context.getBean(DBConfig.class).initTargetTestData("repository/communication.sql");
		List<String> appId = Arrays.asList("electric");
		List<Communication> list = communicationRepository.findByProcessCategoryAndLoginUserMomEmployeeIdAndAppIdNotIn("1", "dummy_request_to_id_1", appId);
		Assert.assertNotEquals(0, list.size());
		appId = Arrays.asList("cotos_dev");
		list = communicationRepository.findByProcessCategoryAndLoginUserMomEmployeeIdAndAppIdIn("1", "dummy_request_to_id_1", appId);
		Assert.assertNotEquals(0, list.size());
		list = communicationRepository.findByProcessCategoryAndLoginUserMomEmployeeId("1", "dummy_request_to_id_1");
		Assert.assertEquals(1, list.size());
	}

	@Test
	public void ContactRepositoryの条件テスト() {
		context.getBean(DBConfig.class).initTargetTestData("repository/communication.sql");
		List<String> appId = Arrays.asList("electric");
		List<Contact> list = contactRepository.findByEstimationIdAndServiceCategoryAndParentIdIsNullAndAppIdNotInOrderByIdDesc(4L, "1", appId);
		Assert.assertNotEquals(0, list.size());
		appId = Arrays.asList("cotos_dev");
		list = contactRepository.findByEstimationIdAndServiceCategoryAndParentIdIsNullAndAppIdInOrderByIdDesc(4L, "1", appId);
		Assert.assertNotEquals(0, list.size());
		list = contactRepository.findByEstimationIdAndServiceCategoryAndParentIdIsNullOrderByIdDesc(4L, "1");
		Assert.assertEquals(1, list.size());
	}

	@Test
	public void 全てのカラムがNullではないことを確認_問い合わせ宛先() {
		全てのカラムがNullではないことを確認_共通(contactToRepository, 1L);
	}

	@Test
	public void 親がいない場合には親がnullになることを確認_問い合わせ() {
		context.getBean(DBConfig.class).initTargetTestData("repository/communication.sql");
		Contact child = contactRepository.findOne(4L);
		Assert.assertNull(child.getParent());
	}

	@Transactional
	private <T extends EntityBase, ID extends Serializable> void 全てのカラムがNullではないことを確認_共通(CrudRepository<T, ID> repository, @SuppressWarnings("unchecked") ID... ids) {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/communication.sql");

		List<ID> idList = Arrays.asList(ids);

		idList.stream().forEach(id -> {
			// データが取得できることを確認
			T found = repository.findOne(id);

			Assert.assertNotNull(found);
			// 全てのカラムがNullではないことを確認
			try {
				testTools.assertColumnsNotNull(found);
			} catch (Exception e1) {
				Assert.fail("例外が発生した場合、エラー");
			}
		});
	}
}
