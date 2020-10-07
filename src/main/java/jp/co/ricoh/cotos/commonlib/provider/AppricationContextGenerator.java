package jp.co.ricoh.cotos.commonlib.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import jp.co.ricoh.cotos.commonlib.security.AccessLogOutputFilter;

/**
 *
 * AppricationContextを生成する
 *
 */
public class AppricationContextGenerator extends AbstractXmlApplicationContextEx {

	private static final Log log = LogFactory.getLog(AccessLogOutputFilter.class);

	private Resource[] configResources;

	/**
	 * AppricationContextの生成
	 * @param configLocation resource location
	 * @throws BeansException if context creation failed
	 */
	public AppricationContextGenerator(String configLocation) throws BeansException {
		this(new String[] {configLocation}, true, null);
		log.info("調査用：AppricationContextGenerator constructor(param count 1) call");
	}

	public AppricationContextGenerator(String[] configLocations, boolean refresh, ApplicationContext parent) throws BeansException {
		super(parent);

		log.info("調査用：AppricationContextGenerator constructor(param count 3) start");

		setConfigLocations(configLocations);
		if (refresh) {
			refresh();
		}

		log.info("調査用：AppricationContextGenerator constructor(param count 3) end");
	}

	@Override
	protected Resource[] getConfigResources() {
		log.info("調査用：AppricationContextGenerator.getConfigResources call");
		return this.configResources;
	}

}