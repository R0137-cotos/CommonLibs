package jp.co.ricoh.cotos.commonlib.logic.trendmicro;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmGetWfbssDomainsResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPostWfbssReportRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPostWfbssReportRequestDto.TmPostWfbssReportRequestDtoSetting;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPostWfbssReportRequestDto.TmPostWfbssReportRequestDtoSetting.TmPostWfbssReportRequestDtoParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPostWfbssReportRequestDto.TmPostWfbssReportRequestDtoSetting.TmPostWfbssReportRequestDtoParameter.TmPostWfbssReportRequestDtoContents;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPostWfbssReportResponseDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoAntispyware;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoAntispyware.TmPutWfbssNotifSettingsRequestDtoAntispywareThreshold;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoAntivirus;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoAntivirus.TmPutWfbssNotifSettingsRequestDtoAntivirusThreshold;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoring;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoring.TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoringThreshold;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoLicense;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoNetworkVirus;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoNetworkVirus.TmPutWfbssNotifSettingsRequestDtoNetworkVirusThreshold;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoOutbreak;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoSmartScan;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoUpdate;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoUrlFiltering;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoUrlFiltering.TmPutWfbssNotifSettingsRequestDtoUrlFilteringThreshold;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoWtp;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDto.TmPutWfbssNotifSettingsRequestDtoNotifSetting.TmPutWfbssNotifSettingsRequestDtoWtp.TmPutWfbssNotifSettingsRequestDtoWtpThreshold;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm.TmPutWfbssNotifSettingsRequestDtoThreshold0;
import jp.co.ricoh.cotos.commonlib.log.LogUtil;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.rest.ExternalClientHttpRequestInterceptor;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogRequestProperties;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogResponseProperties;
import lombok.extern.log4j.Log4j;

/**
 * TrendMicro SMPI連携 ヘルパーテストクラス。
 * 外部環境にデータが登録されるため、テストは常にIgnoreする。
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Log4j
//@Ignore
public class SMPIConnectionHelperTests {

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		context.stop();
	}

	private SMPIConnectionHelper getHelper() {
		// ヘルパー初期化
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor = new ExternalClientHttpRequestInterceptor(new MessageUtil(), new LogUtil(), new ExternalLogRequestProperties(), new ExternalLogResponseProperties(), formatter);
		ExternalRestTemplate externalRestTemplate = new ExternalRestTemplate(new RestTemplateBuilder(), externalClientHttpRequestInterceptor);
		SMPIConnectionHelper.init(context, externalRestTemplate);
		return SMPIConnectionHelper.getInstance();
	}

	// ローカルでのテスト時にURL、requestBody、responseをログに出力したい場合は、
	// SMPIConnectionHelper.javaのcallServiceメソッドに以下を記述すること
	// コミット時は削除すること
	// log.info("SMPI call : " + url);
	// log.info("SMPI requestBody : " + body);

	/**
	 *  [GET] サブスクリプション取得API
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void getSubscriptionsTest() throws ParseException {

		String customerId = "5118f657-9f7d-407d-97ab-ca434c6dc936";

		try {
			TmGetWfbssDomainsResponseDto responceDto = getHelper().getWfbssDomains(customerId);
			System.out.println("★★取得結果：" + responceDto);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [POST] WFBSS初期化API
	 * @throws ParseException
	 */
	@Test
	@WithMockCustomUser
	public void postWfbssInitializeTest() throws ParseException {
		String customerId = "5118f657-9f7d-407d-97ab-ca434c6dc936";

		try {
			getHelper().postWfbssInitialize(customerId);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [POST] レポート作成API
	 * @throws ParseException
	 * @throws JsonProcessingException
	 */
	@Test
	@WithMockCustomUser
	public void postWfbssReportTest() throws ParseException, JsonProcessingException {

		String customerId = "5118f657-9f7d-407d-97ab-ca434c6dc936";

		List<TmPostWfbssReportRequestDtoContents> contensList = new ArrayList<TmPostWfbssReportRequestDtoContents>();
		TmPostWfbssReportRequestDtoContents contens1 = new TmPostWfbssReportRequestDtoContents();
		contens1.setTblType(100);
		contensList.add(contens1);
		TmPostWfbssReportRequestDtoContents contens2 = new TmPostWfbssReportRequestDtoContents();
		contens2.setTblType(101);
		contensList.add(contens2);
		TmPostWfbssReportRequestDtoContents contens3 = new TmPostWfbssReportRequestDtoContents();
		contens3.setTblType(102);
		contensList.add(contens3);
		TmPostWfbssReportRequestDtoContents contens4 = new TmPostWfbssReportRequestDtoContents();
		contens4.setTblType(200);
		contensList.add(contens4);
		TmPostWfbssReportRequestDtoContents contens5 = new TmPostWfbssReportRequestDtoContents();
		contens5.setTblType(201);
		contensList.add(contens5);
		TmPostWfbssReportRequestDtoContents contens6 = new TmPostWfbssReportRequestDtoContents();
		contens6.setTblType(202);
		contensList.add(contens6);
		TmPostWfbssReportRequestDtoContents contens7 = new TmPostWfbssReportRequestDtoContents();
		contens7.setTblType(300);
		contensList.add(contens7);
		TmPostWfbssReportRequestDtoContents contens8 = new TmPostWfbssReportRequestDtoContents();
		contens8.setTblType(301);
		contensList.add(contens8);
		TmPostWfbssReportRequestDtoContents contens9 = new TmPostWfbssReportRequestDtoContents();
		contens9.setTblType(400);
		contensList.add(contens9);
		TmPostWfbssReportRequestDtoContents contens10 = new TmPostWfbssReportRequestDtoContents();
		contens10.setTblType(500);
		contensList.add(contens10);
		TmPostWfbssReportRequestDtoContents contens11 = new TmPostWfbssReportRequestDtoContents();
		contens11.setTblType(501);
		contensList.add(contens11);
		TmPostWfbssReportRequestDtoContents contens12 = new TmPostWfbssReportRequestDtoContents();
		contens12.setTblType(600);
		contensList.add(contens12);
		TmPostWfbssReportRequestDtoContents contens13 = new TmPostWfbssReportRequestDtoContents();
		contens13.setTblType(601);
		contensList.add(contens13);

		TmPostWfbssReportRequestDtoParameter parameter = new TmPostWfbssReportRequestDtoParameter();
		parameter.setTime(0);
		parameter.setContents(contensList.toArray(new TmPostWfbssReportRequestDtoContents[contensList.size()]));
		parameter.setWeekday(4);
		parameter.setEmail(1);
		List<String> addressList = new ArrayList<String>();
		addressList.add("test@aaa.bbb");
		parameter.setAddress(addressList.toArray(new String[addressList.size()]));

		TmPostWfbssReportRequestDtoSetting setting = new TmPostWfbssReportRequestDtoSetting();
		setting.setEnabled(1);
		setting.setName("【クラウドサービス　ｆｏｒ　ＭＶＢ】テストレポート");
		setting.setParameter(parameter);

		TmPostWfbssReportRequestDto requestDto = new TmPostWfbssReportRequestDto();
		requestDto.setSetting(setting);

		try {
			TmPostWfbssReportResponseDto responceDto = getHelper().postWfbssReport(customerId, requestDto);
			System.out.println("★★取得結果：" + responceDto);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  [PUT] 通知設定作成API
	 * @throws ParseException
	 * @throws JsonProcessingException
	 */
	@Test
	@WithMockCustomUser
	public void putWfbssNotifSettingsTest() throws ParseException, JsonProcessingException {

		String customerId = "5118f657-9f7d-407d-97ab-ca434c6dc936";

		TmPutWfbssNotifSettingsRequestDto requestDto = new TmPutWfbssNotifSettingsRequestDto();
		TmPutWfbssNotifSettingsRequestDtoNotifSetting setting = new TmPutWfbssNotifSettingsRequestDtoNotifSetting();
		List<String> recipientsList = new ArrayList<String>();
		recipientsList.add("test@aaa.bbb");
		setting.setSubjectPreface("【クラウドサービス　ｆｏｒ　ＭＶＢテスト】");
		setting.setRecipients(recipientsList.toArray(new String[recipientsList.size()]));

		TmPutWfbssNotifSettingsRequestDtoThreshold0 wtpThreshold0 = new TmPutWfbssNotifSettingsRequestDtoThreshold0();
		wtpThreshold0.setThreshold(200);
		wtpThreshold0.setTimeInterval(3600);
		TmPutWfbssNotifSettingsRequestDtoWtpThreshold wtpThreshold = new TmPutWfbssNotifSettingsRequestDtoWtpThreshold();
		wtpThreshold.setReplaceTarget0(wtpThreshold0);
		TmPutWfbssNotifSettingsRequestDtoWtp wtp = new TmPutWfbssNotifSettingsRequestDtoWtp();
		wtp.setNotifyIncidentExceed(0);
		wtp.setThreshold(wtpThreshold);
		setting.setWtp(wtp);

		TmPutWfbssNotifSettingsRequestDtoLicense license = new TmPutWfbssNotifSettingsRequestDtoLicense();
		license.setNotifyLicenseExpireWarn(0);
		license.setNotifyLicenseExpired(0);
		license.setNotifySeatCountExceedWarn(0);
		license.setNotifySeatCountExceedCrit(0);
		setting.setLicense(license);

		TmPutWfbssNotifSettingsRequestDtoUpdate update = new TmPutWfbssNotifSettingsRequestDtoUpdate();
		update.setNotifyDeploymentRateWarn(0);
		update.setNotifyDeploymentRateCrit(0);
		setting.setUpdate(update);

		TmPutWfbssNotifSettingsRequestDtoSmartScan smartScan = new TmPutWfbssNotifSettingsRequestDtoSmartScan();
		smartScan.setNotifyCloudscanConnect(0);
		setting.setSmartScan(smartScan);

		TmPutWfbssNotifSettingsRequestDtoThreshold0 behaviorMonitoringThreshold0 = new TmPutWfbssNotifSettingsRequestDtoThreshold0();
		behaviorMonitoringThreshold0.setThreshold(20);
		behaviorMonitoringThreshold0.setTimeInterval(3600);
		TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoringThreshold behaviorMonitoringThreshold = new TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoringThreshold();
		behaviorMonitoringThreshold.setReplaceTarget0(behaviorMonitoringThreshold0);
		TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoring behaviorMonitoring = new TmPutWfbssNotifSettingsRequestDtoBehaviorMonitoring();
		behaviorMonitoring.setNotifyIncidentExceed(0);
		behaviorMonitoring.setThreshold(behaviorMonitoringThreshold);
		setting.setBehaviorMonitoring(behaviorMonitoring);

		TmPutWfbssNotifSettingsRequestDtoThreshold0 urlFilteringThreshold0 = new TmPutWfbssNotifSettingsRequestDtoThreshold0();
		urlFilteringThreshold0.setThreshold(300);
		urlFilteringThreshold0.setTimeInterval(3600);
		TmPutWfbssNotifSettingsRequestDtoUrlFilteringThreshold urlFilteringThreshold = new TmPutWfbssNotifSettingsRequestDtoUrlFilteringThreshold();
		urlFilteringThreshold.setReplaceTarget0(urlFilteringThreshold0);
		TmPutWfbssNotifSettingsRequestDtoUrlFiltering urlFiltering = new TmPutWfbssNotifSettingsRequestDtoUrlFiltering();
		urlFiltering.setNotifyIncidentExceed(0);
		urlFiltering.setThreshold(urlFilteringThreshold);
		setting.setUrlFiltering(urlFiltering);

		TmPutWfbssNotifSettingsRequestDtoThreshold0 antispywareThreshold0 = new TmPutWfbssNotifSettingsRequestDtoThreshold0();
		antispywareThreshold0.setThreshold(15);
		antispywareThreshold0.setTimeInterval(3600);
		TmPutWfbssNotifSettingsRequestDtoAntispywareThreshold antispywareThreshold = new TmPutWfbssNotifSettingsRequestDtoAntispywareThreshold();
		antispywareThreshold.setReplaceTarget0(antispywareThreshold0);
		TmPutWfbssNotifSettingsRequestDtoAntispyware antispyware = new TmPutWfbssNotifSettingsRequestDtoAntispyware();
		antispyware.setNotifyNeedRestart(0);
		antispyware.setNotifyIncidentExceed(0);
		antispyware.setThreshold(antispywareThreshold);
		setting.setAntispyware(antispyware);

		TmPutWfbssNotifSettingsRequestDtoThreshold0 antivirusThreshold0 = new TmPutWfbssNotifSettingsRequestDtoThreshold0();
		antivirusThreshold0.setThreshold(5);
		antivirusThreshold0.setTimeInterval(3600);
		TmPutWfbssNotifSettingsRequestDtoAntivirusThreshold antivirusThreshold = new TmPutWfbssNotifSettingsRequestDtoAntivirusThreshold();
		antivirusThreshold.setReplaceTarget0(antivirusThreshold0);
		TmPutWfbssNotifSettingsRequestDtoAntivirus antivirus = new TmPutWfbssNotifSettingsRequestDtoAntivirus();
		antivirus.setNotifyActionFailure(1);
		antivirus.setNotifyIncidentExceed(0);
		antivirus.setNotifyRealtimeScanDisabled(1);
		antivirus.setThreshold(antivirusThreshold);
		setting.setAntivirus(antivirus);

		TmPutWfbssNotifSettingsRequestDtoOutbreak outbreak = new TmPutWfbssNotifSettingsRequestDtoOutbreak();
		outbreak.setNotifyRedAlert(0);
		outbreak.setNotifyYellowAlert(0);
		setting.setOutbreak(outbreak);

		TmPutWfbssNotifSettingsRequestDtoThreshold0 networkVirusThreshold0 = new TmPutWfbssNotifSettingsRequestDtoThreshold0();
		networkVirusThreshold0.setThreshold(10);
		networkVirusThreshold0.setTimeInterval(3600);
		TmPutWfbssNotifSettingsRequestDtoNetworkVirusThreshold networkVirusThreshold = new TmPutWfbssNotifSettingsRequestDtoNetworkVirusThreshold();
		networkVirusThreshold.setReplaceTarget0(networkVirusThreshold0);
		TmPutWfbssNotifSettingsRequestDtoNetworkVirus networkVirus = new TmPutWfbssNotifSettingsRequestDtoNetworkVirus();
		networkVirus.setNotifyIncidentExceed(0);
		networkVirus.setThreshold(networkVirusThreshold);
		setting.setNetworkVirus(networkVirus);
		requestDto.setNotifSetting(setting);

		try {
			getHelper().putWfbssNotifSettings(customerId, requestDto);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  TrendMicroAPIリトライテスト
	 */
	@Test
	@WithMockCustomUser
	public void callApiRetryTest() {
		// TrendMicroに存在しないカスタマーID
		String customerId = "5118f657-9f7d-407d-97ab-ca434c6dc936-11111";

		try {
			TmGetWfbssDomainsResponseDto responceDto = getHelper().getWfbssDomains(customerId);
			assertNotNull("結果が返ってくること。", responceDto);
		} catch (RestClientException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			fail("想定外のエラーが発生しました。");
		} catch (Exception e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			fail("想定外のエラーが発生しました。");
		}
	}
}
