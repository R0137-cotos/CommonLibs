package jp.co.ricoh.cotos.commonlib.provider;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import jp.co.ricoh.cotos.commonlib.security.AccessLogOutputFilter;

/**
 *
 * EntityManagerのインスタンスを生成、返却する
 *
 */
public class EntityManagerProvider {

	private static final Log log = LogFactory.getLog(AccessLogOutputFilter.class);

	private static EntityManager entityManager;

	private EntityManagerProvider() {
	}

	/**
	 * EntityManagerを取得
	 * @return EntityManager
	 */
	public static EntityManager getEntityManager() {
		log.info("調査用：EntityManagerProvider.getEntityManager() start");

		if (entityManager == null) {
			entityManager = createEntityManager();
		}

		log.info("調査用：EntityManagerProvider.getEntityManager() end");
		return entityManager;
	}

	/**
	 * EntityManagerを生成
	 * @return EntityManager
	 */
	private static EntityManager createEntityManager() {
		log.info("調査用：EntityManagerProvider.createEntityManager() start");

		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		LocalContainerEntityManagerFactoryBean factory = context.getBean(LocalContainerEntityManagerFactoryBean.class);
		EntityManager entityManager = factory.getNativeEntityManagerFactory().createEntityManager();

		log.info("調査用：EntityManagerProvider.createEntityManager() end");

		return entityManager;
	}
}