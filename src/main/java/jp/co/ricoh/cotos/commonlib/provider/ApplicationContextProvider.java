package jp.co.ricoh.cotos.commonlib.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import jp.co.ricoh.cotos.commonlib.security.AccessLogOutputFilter;

/**
 *
 * ApplicationContextのインスタンスを生成、返却する
 *
 */
public class ApplicationContextProvider {

	private static final Log log = LogFactory.getLog(AccessLogOutputFilter.class);

	private static ApplicationContext context;

	private ApplicationContextProvider() {
	}

	/**
	 * ApplicationContextを取得
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		log.info("調査用：ApplicationContextProvider.getApplicationContext() start");

		if (context == null) {
			context = createApplicationContext();
		}

		log.info("調査用：ApplicationContextProvider.getApplicationContext() end");
		return context;
	}

	/**
	 * ApplicationContextを生成
	 * @return ApplicationContext
	 */
	private static ApplicationContext createApplicationContext() {
		log.info("調査用：ApplicationContextProvider.createApplicationContext() start");

		ApplicationContext context = new AppricationContextGenerator("bean.xml");

		log.info("調査用：ApplicationContextProvider.createApplicationContext() end");
		return context;
	}
}