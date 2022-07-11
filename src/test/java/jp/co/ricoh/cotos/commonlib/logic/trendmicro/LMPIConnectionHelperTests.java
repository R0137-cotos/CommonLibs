package jp.co.ricoh.cotos.commonlib.logic.trendmicro;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
import lombok.extern.log4j.Log4j;

/**
 * TrendMicro LMPI連携 ヘルパーテストクラス。
 * 外部環境にデータが登録されるため、テストは常にIgnoreする。
 * @author z00se03039
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Log4j
@Ignore
public class LMPIConnectionHelperTests {

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

	// ローカルでのテスト時にURL、requestBody、responseをログに出力したい場合は、
	// LMPIConnectionHelper.javaのcallServiceメソッドに以下を記述すること
	// コミット時は削除すること
	// log.info("LMPI call : " + url);
	// log.info("LMPI requestBody : " + body);

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
		Date date = df.parse("202200801");

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
		requestDto.setCustomerId("bf46415e-5649-448b-a7cb-3f465508be5e");
		requestDto.setSubscriptionId("bffe523f-3da7-4f87-9dd4-2606b0a527ae");

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
		Date date = df.parse("20220731");

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
		requestWork.setCustomerId("6198979e-dcf7-446e-949e-9b562574b125");
		requestWork.setSubscriptionId("de7b56ec-bd34-4fc7-a35b-46131cbdef1d");
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

	/**
	 *  APIリトライテスト
	 */
	@Test
	@WithMockCustomUser
	@Ignore
	public void callApiRetryTest() {
		try {
			getHelper().postCustomers(new TmCreateCustomerRequestWork());
			fail("正常終了しました。");
		} catch (RestClientException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
		} catch (Exception e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			fail("想定外のエラーが発生しました。");
		}
	}
}
