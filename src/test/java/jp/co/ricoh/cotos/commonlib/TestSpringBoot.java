package jp.co.ricoh.cotos.commonlib;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
public class TestSpringBoot {

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
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/estimation/estimation_all.sql");

	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			//context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void SpringBootと非SpringBootの列挙値比較() {
		Estimation expected = estimationRepository.findOne(4L);

		ApplicationContext context2 = ApplicationContextProvider.getApplicationContext();
		EstimationRepository repository = context2.getBean(EstimationRepository.class);
		Estimation actual = repository.findOne(4L);

		Assert.assertEquals("ライフサイクル状態の値が1であること", "1", expected.getLifecycleStatus().toString());
		Assert.assertEquals("ライフサイクル状態が一致すること", expected.getLifecycleStatus(), actual.getLifecycleStatus());
	}

	@Test
	public void Repository検証_SpringBoot() {
		Estimation estimation = estimationRepository.findOne(4L);

		Assert.assertEquals("ライフサイクル状態の値が1であること", "1", estimation.getLifecycleStatus().toString());

		estimation.setLifecycleStatus(LifecycleStatus.作成完了);
		estimation.setCreatedUserId("aaaaa");
		estimationRepository.save(estimation);

		Estimation estimation2 = estimationRepository.findOne(4L);
		Assert.assertEquals("ライフサイクル状態の値が2であること", "2", estimation2.getLifecycleStatus().toString());
	}

	@Test
	public void Repository検証_非SpringBoot() {

		// コンテキスト取得
		ApplicationContext context2 = ApplicationContextProvider.getApplicationContext();

		// トランザクション開始
		JpaTransactionManager transactionManager = context2.getBean(JpaTransactionManager.class);
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		// JPAレポジトリ取得
		EstimationRepository repository = context2.getBean(EstimationRepository.class);

		try {
			Estimation actual = repository.findOne(4L);
			actual.setLifecycleStatus(LifecycleStatus.作成完了);
			actual.setCreatedUserId("aaaaa");
			repository.save(actual);
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			Assert.fail();
		}

		Estimation actual2 = repository.findOne(4L);
		Assert.assertEquals("ワークフローの値が2であること", "2", actual2.getLifecycleStatus().toString());
	}
}
