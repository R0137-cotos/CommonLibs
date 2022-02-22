package jp.co.ricoh.cotos.commonlib.logic.trendmicro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmGetSubscriptionRequestDto;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserRequestWork;
import jp.co.ricoh.cotos.commonlib.log.LogUtil;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.rest.ExternalClientHttpRequestInterceptor;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogRequestProperties;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogResponseProperties;

/**
 * TrendMicro LMPI連携 ヘルパーテストクラス。
 * 外部環境にデータが登録されるため、テストは常にIgnoreする。
 * @author z00se03039
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Ignore
public class LMPIConnectionHelperTests {

	@Autowired
	ExternalLogRequestProperties externalLogRequestProperties;

	@Autowired
	ExternalLogResponseProperties externalLogResponseProperties;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		context.stop();
	}

	private LMPIConnectionHelper getHelper() {
		// ヘルパー初期化
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor = new ExternalClientHttpRequestInterceptor(new MessageUtil(), new LogUtil(), new ExternalLogRequestProperties(), new ExternalLogResponseProperties(), formatter);
		ExternalRestTemplate externalRestTemplate = new ExternalRestTemplate(new RestTemplateBuilder(), externalClientHttpRequestInterceptor);
		LMPIConnectionHelper.init(context, externalRestTemplate);
		return LMPIConnectionHelper.getInstance();
	}

	/**
	 *  [POST] 顧客作成API
	 */
	@Test
	@WithMockCustomUser
	public void postCustomersTest() {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = df.format(date);

		TmCreateCustomerRequestWork requestWork = new TmCreateCustomerRequestWork();
		// abstractWork
		requestWork.setRequestStatus(TmRequestStatus.未連携);
		requestWork.setRequestTime(new Date());
		requestWork.setUrl("setUrl");
		requestWork.setHttpHeader("setHttpHeader");
		requestWork.setHttpBody("setHttpBody");
		// entityBase
		requestWork.setCreatedAt(new Date());
		requestWork.setCreatedUserId("setCreatedUserId");
		requestWork.setUpdatedAt(new Date());
		requestWork.setUpdatedUserId("setUpdatedUserId");
		requestWork.setVersion(0);
		// requestWork
		requestWork.setId(0);
		requestWork.setCompanyName("cas-cotos-companyName");
		requestWork.setCompanyState("cas-cotos-companyState");
		requestWork.setCompanyCountry("JP");
		requestWork.setUserLoginName("cotosLN" + dateString);
		requestWork.setUserFirstName("cas-cotos-userFirstName");
		requestWork.setUserLastName("cas-userLastName");
		requestWork.setUserEmail("cas-userEmail@gmail.com");
		requestWork.setUserTimeZone("Tokyo Standard Time");
		requestWork.setUserLanguage("ja-JP");
		requestWork.setCompanyCity("cas-companyCity");

		try {
			getHelper().postCustomers(requestWork);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [PUT] 会社情報更新API
	 */
	@Test
	@WithMockCustomUser
	public void putCustomersTest() {

		TmUpdateCustomerRequestWork requestWork = new TmUpdateCustomerRequestWork();
		// abstractWork
		requestWork.setRequestStatus(TmRequestStatus.未連携);
		requestWork.setRequestTime(new Date());
		requestWork.setUrl("setUrl");
		requestWork.setHttpHeader("setHttpHeader");
		requestWork.setHttpBody("setHttpBody");
		// entityBase
		requestWork.setCreatedAt(new Date());
		requestWork.setCreatedUserId("setCreatedUserId");
		requestWork.setUpdatedAt(new Date());
		requestWork.setUpdatedUserId("setUpdatedUserId");
		requestWork.setVersion(0);
		// requestWork
		requestWork.setId(0);
		requestWork.setName("updatedName");
		requestWork.setState("updatedState");
		requestWork.setCountry("JP");
		requestWork.setCity("updatedCity");
		requestWork.setCustomerId("bda49589-4aa6-4c9d-9f44-33bc8e266412");

		try {
			getHelper().putCustomers(requestWork);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [PUT] ユーザーアカウント更新API
	 */
	@Test
	@WithMockCustomUser
	public void putUsersTest() {

		TmUpdateUserRequestWork requestWork = new TmUpdateUserRequestWork();
		// abstractWork
		requestWork.setRequestStatus(TmRequestStatus.未連携);
		requestWork.setRequestTime(new Date());
		requestWork.setUrl("setUrl");
		requestWork.setHttpHeader("setHttpHeader");
		requestWork.setHttpBody("setHttpBody");
		// entityBase
		requestWork.setCreatedAt(new Date());
		requestWork.setCreatedUserId("setCreatedUserId");
		requestWork.setUpdatedAt(new Date());
		requestWork.setUpdatedUserId("setUpdatedUserId");
		requestWork.setVersion(0);
		// requestWork
		requestWork.setId(0);
		requestWork.setEmail("updatedEmail@gmail.com");
		requestWork.setCustomerId("bda49589-4aa6-4c9d-9f44-33bc8e266412");
		requestWork.setUserId("33d9bb6b-11c4-444c-a5da-8f76cbd7fb92");

		try {
			getHelper().putUsers(requestWork);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [POST] サブスクリプション作成API
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void postSubscriptionsTest() throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date = df.parse("20220216");

		TmCreateSubscriptionRequestWork requestWork = new TmCreateSubscriptionRequestWork();
		// abstractWork
		requestWork.setRequestStatus(TmRequestStatus.未連携);
		requestWork.setRequestTime(new Date());
		requestWork.setUrl("setUrl");
		requestWork.setHttpHeader("setHttpHeader");
		requestWork.setHttpBody("setHttpBody");
		// entityBase
		requestWork.setCreatedAt(new Date());
		requestWork.setCreatedUserId("setCreatedUserId");
		requestWork.setUpdatedAt(new Date());
		requestWork.setUpdatedUserId("setUpdatedUserId");
		requestWork.setVersion(0);
		// requestWork
		requestWork.setId(0);
		requestWork.setCustomerId("5118f657-9f7d-407d-97ab-ca434c6dc936");
		requestWork.setServicePlanId("4c011d2a-3df4-48aa-bc2a-e632e6d58adf");
		requestWork.setUnitsPerLicense("10");
		requestWork.setLicenseStartDate(date);

		try {
			getHelper().postSubscriptions(requestWork);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [PUT] サブスクリプション更新API
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void putSubscriptionsTest() throws ParseException {

		TmUpdateSubscriptionRequestWork requestWork = new TmUpdateSubscriptionRequestWork();
		// abstractWork
		requestWork.setRequestStatus(TmRequestStatus.未連携);
		requestWork.setRequestTime(new Date());
		requestWork.setUrl("setUrl");
		requestWork.setHttpHeader("setHttpHeader");
		requestWork.setHttpBody("setHttpBody");
		// entityBase
		requestWork.setCreatedAt(new Date());
		requestWork.setCreatedUserId("setCreatedUserId");
		requestWork.setUpdatedAt(new Date());
		requestWork.setUpdatedUserId("setUpdatedUserId");
		requestWork.setVersion(0);
		// requestWork
		requestWork.setId(0);
		requestWork.setCustomerId("9842f3d0-0993-4eea-a61f-0ca33b3f7c3e");
		requestWork.setSubscriptionId("7ef15198-607a-4eb3-9907-077468585172");
		requestWork.setUnitsPerLicense("50");

		try {
			getHelper().putSubscriptions(requestWork);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [GET] サブスクリプション取得API
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void getSubscriptionsTest() throws ParseException {

		TmGetSubscriptionRequestDto requestDto = new TmGetSubscriptionRequestDto();

		// requestDto
		requestDto.setCustomerId("5118f657-9f7d-407d-97ab-ca434c6dc936");
		requestDto.setSubscriptionId("8cf1739a-b7f6-49f3-9bea-2c1ea0a7c05a");

		try {
			getHelper().getSubscriptions(requestDto);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [PUT] サブスクリプション解約API
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void putSuspendTest() throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date = df.parse("20201130");

		TmSuspendSubscriptionRequestWork requestWork = new TmSuspendSubscriptionRequestWork();
		// abstractWork
		requestWork.setRequestStatus(TmRequestStatus.未連携);
		requestWork.setRequestTime(new Date());
		requestWork.setUrl("setUrl");
		requestWork.setHttpHeader("setHttpHeader");
		requestWork.setHttpBody("setHttpBody");
		// entityBase
		requestWork.setCreatedAt(new Date());
		requestWork.setCreatedUserId("setCreatedUserId");
		requestWork.setUpdatedAt(new Date());
		requestWork.setUpdatedUserId("setUpdatedUserId");
		requestWork.setVersion(0);
		// requestWork
		requestWork.setId(0);
		requestWork.setCustomerId("1e6749fe-8c89-4b23-8787-b5a258d2f6b6");
		requestWork.setSubscriptionId("be5564c5-92d4-4ed5-a99c-86f5aa3b4bd9");
		requestWork.setLicenseExpirationDate(date);

		try {
			getHelper().putSuspend(requestWork);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [GET] 更新ユーザー取得API
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void getCustomersTest() throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date startDate = df.parse("20201113");
		Date endDate = df.parse("20201114");

		try {
			getHelper().getCustomers(startDate, endDate);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [GET] サービスプランID取得
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void getServicePlanId() throws ParseException {

		try {
			getHelper().getServicePlanId();
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
