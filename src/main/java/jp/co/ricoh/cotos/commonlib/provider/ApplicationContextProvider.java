package jp.co.ricoh.cotos.commonlib.provider;

import org.springframework.context.ApplicationContext;


/**
 *
 * ApplicationContextのインスタンスを生成、返却する
 *
 */
public class ApplicationContextProvider {

	private static ApplicationContext context;

	private ApplicationContextProvider() {
	}

	/**
	 * ApplicationContextを取得
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			context = createApplicationContext();
		}
		return context;
	}

	/**
	 * ApplicationContextを生成
	 * @return ApplicationContext
	 */
	private static ApplicationContext createApplicationContext() {

		ApplicationContext context = new jp.co.ricoh.cotos.commonlib.config.AppricationContextGenerator("bean.xml");
		return context;
	}
}
