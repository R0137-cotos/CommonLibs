package jp.co.ricoh.cotos.commonlib.logic.trendmicro;

import org.springframework.context.ApplicationContext;

import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateCustomerRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateCustomerResponseWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateSubscriptionRequestWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.tm.TmCreateSubscriptionResponseWorkRepository;
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
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.LMPIPropertiesNewProduct;

/**
 * TrendMicro LMPI連携 ヘルパークラス（新商品用）
 * BatchesLightTemplateでも使用することを想定するため、コンポーネント化しない
 *
 */
public class LMPIConnectionHelperNewProduct extends LMPIConnectionHelper {

	protected LMPIConnectionHelperNewProduct() {
	}

	public static void init(ApplicationContext context, ExternalRestTemplate externalRestTemplate) {
		init(//
				context.getBean(LMPIPropertiesNewProduct.class), //
				context.getBean(TmCreateCustomerRequestWorkRepository.class), //
				context.getBean(TmCreateCustomerResponseWorkRepository.class), //
				context.getBean(TmUpdateCustomerRequestWorkRepository.class), //
				context.getBean(TmUpdateCustomerResponseWorkRepository.class), //
				context.getBean(TmUpdateUserRequestWorkRepository.class), //
				context.getBean(TmUpdateUserResponseWorkRepository.class), //
				context.getBean(TmCreateSubscriptionRequestWorkRepository.class), //
				context.getBean(TmCreateSubscriptionResponseWorkRepository.class), //
				context.getBean(TmUpdateSubscriptionRequestWorkRepository.class), //
				context.getBean(TmUpdateSubscriptionResponseWorkRepository.class), //
				context.getBean(TmSuspendSubscriptionRequestWorkRepository.class), //
				context.getBean(TmSuspendSubscriptionResponseWorkRepository.class), //
				context.getBean(TmTransitionSubscriptionRequestWorkRepository.class), //
				context.getBean(TmTransitionSubscriptionResponseWorkRepository.class), //
				context.getBean(TrendMicroUtil.class), //
				externalRestTemplate); //
	}
}
