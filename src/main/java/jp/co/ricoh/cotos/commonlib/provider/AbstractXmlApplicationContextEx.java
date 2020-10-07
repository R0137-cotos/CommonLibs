package jp.co.ricoh.cotos.commonlib.provider;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;

import jp.co.ricoh.cotos.commonlib.security.AccessLogOutputFilter;

public abstract class AbstractXmlApplicationContextEx extends AbstractXmlApplicationContext {

	private static final Log log = LogFactory.getLog(AccessLogOutputFilter.class);

	public AbstractXmlApplicationContextEx(ApplicationContext parent) {
		super(parent);

		log.info("調査用：AbstractXmlApplicationContextEx constructor call");
	}

	@Override
	protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
		log.info("調査用：AbstractXmlApplicationContextEx.loadBeanDefinitions() start");

		// Create a new XmlBeanDefinitionReader for the given BeanFactory.
		XmlBeanDefinitionReaderEx beanDefinitionReader = new XmlBeanDefinitionReaderEx(beanFactory);

		// Configure the bean definition reader with this context's
		// resource loading environment.
		beanDefinitionReader.setEnvironment(this.getEnvironment());
		beanDefinitionReader.setResourceLoader(this);
		beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this));

		// Allow a subclass to provide custom initialization of the reader,
		// then proceed with actually loading the bean definitions.
		initBeanDefinitionReader(beanDefinitionReader);
		loadBeanDefinitions(beanDefinitionReader);

		log.info("調査用：AbstractXmlApplicationContextEx.loadBeanDefinitions() end");
	}
}