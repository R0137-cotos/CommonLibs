package jp.co.ricoh.cotos.commonlib.logic.intramart;

import static org.junit.Assert.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterInfoDto.KhsData;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterInfoDto.MarkerData;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterInfoDto.SofuData;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.SAndSWebRequestDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.SAndSWebResponseDto;
import jp.co.ricoh.cotos.commonlib.log.LogUtil;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;
import jp.co.ricoh.cotos.commonlib.rest.ExternalClientHttpRequestInterceptor;
import jp.co.ricoh.cotos.commonlib.rest.ExternalRestTemplate;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogRequestProperties;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogResponseProperties;
import lombok.extern.log4j.Log4j;

/**
 * Intra-mart RestAPI連携 ヘルパーテストクラス
 * 外部環境にデータが登録されるため、テストは常にIgnoreする。
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Log4j
@Ignore
public class IntramartConnectionHelperTests {

	static ConfigurableApplicationContext context;

	@SpyBean
	private IntraMartConnectionHelper intraMartConnectionHelper;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		context.stop();
	}

	private IntraMartConnectionHelper getHelper() {
		// ヘルパー初期化
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor = new ExternalClientHttpRequestInterceptor(new MessageUtil(), new LogUtil(), new ExternalLogRequestProperties(), new ExternalLogResponseProperties(), formatter);
		ExternalRestTemplate externalRestTemplate = new ExternalRestTemplate(new RestTemplateBuilder(), externalClientHttpRequestInterceptor);
		intraMartConnectionHelper.init(context, externalRestTemplate);
		return intraMartConnectionHelper;
	}

	@Test
	public void 正常系_postIntraMartTest() {

		List<KhsData> khsDataList = new ArrayList<>();
		KhsData khsData1 = new KhsData();
		khsData1.setKhsMail("ryohsuke_tanigawa@jp.ricoh.com");
		khsDataList.add(khsData1);

		List<MarkerData> markerDataList = new ArrayList<>();
		MarkerData markerData1 = new MarkerData();
		markerData1.setMarkerDetailNm("クラウドサービス for MVB");
		markerData1.setMarkerDetailDaisu(1);
		markerDataList.add(markerData1);

		List<SofuData> sofuDataList1 = new ArrayList<>();
		SofuData sofuData1 = new SofuData();
		sofuData1.setSofuDetailWhat("");
		sofuData1.setSofuDetailWhen("");
		sofuData1.setSofuDetailTo("");
		sofuData1.setSofuDetailFrom("");
		sofuData1.setSofuComment("");
		sofuData1.setSofuDeliDate("");
		sofuData1.setSofuKakuninDate("");
		sofuDataList1.add(sofuData1);

		List<RegisterInfoDto> registerInfoDtoList = new ArrayList<RegisterInfoDto>();
		RegisterInfoDto registerInfo1 = new RegisterInfoDto();
		registerInfo1.setCreateUserCd("作成者1");
		registerInfo1.setKihyousha("michino.kanako.W@jp.ricoh.com");
		registerInfo1.setShouninsha("承認者1");
		registerInfo1.setDocumentId("CIC1");
		registerInfo1.setAnkenNm("クラウドサービス for MVB インストール代行オプション");
		registerInfo1.setAnkenOkyakuNm("設置先会社名1");
		registerInfo1.setAnkenJigyosyoNm("設置先事業者1");
		registerInfo1.setAnkenjouhouAdd("設置先住所1");
		registerInfo1.setAnkenTel("設置先TEL1");
		registerInfo1.setBushomei("部署1");
		registerInfo1.setAnkenTantoUserNm("設置先担当者1");
		registerInfo1.setIraiBusyoCode("000226");
		registerInfo1.setIraiBushoAll("");
		registerInfo1.setSagyoDate("2022/08/01");
		registerInfo1.setSagyoIrai("01");
		registerInfo1.setKikiJoho("04");
		registerInfo1.setCeRenrakuNoki("2022/08/01");
		registerInfo1.setSagyoJikanKbn("01");
		registerInfo1.setSagyouJikan2("1");
		registerInfo1.setTejunsyoKbn("01");
		registerInfo1.setBackupKbn("2");
		registerInfo1.setJikogu("2");
		registerInfo1.setSofubutu("2");
		registerInfo1.setHenkyakuButu("2");
		registerInfo1.setHenkyakuKanri("1");
		registerInfo1.setReportTeisyutu("2");
		registerInfo1.setSysoneKikiReg("01");
		registerInfo1.setSysoneCeReg("2");
		registerInfo1.setSyaroJoho("2");
		registerInfo1.setHosyuKeiyakuIrai("2");
		registerInfo1.setKanryoHokoku("1");
		registerInfo1.setTotyakuRenraku("2");
		registerInfo1.setSagyoStartRenraku("2");
		registerInfo1.setSagyoKanryoRenraku("2");
		registerInfo1.setTaisyutuRenraku("2");
		registerInfo1.setFfmRenkei("1");
		registerInfo1.setTokusoku("2");
		registerInfo1.setSagyoinHokoku("2");
		registerInfo1.setHakoumotoRenraku("2022/08/01");
		registerInfo1.setToiawseNo("TeMS受注No1");
		registerInfo1.setInYakusokuTime("12:00");
		registerInfo1.setSagyoStartTime("12:00");
		registerInfo1.setKousokuJikan(120);
		registerInfo1.setCeAssignSu(1);
		registerInfo1.setSagyoKingaku(0);
		registerInfo1.setKobetuNaiyo("");
		registerInfo1.setRitosKinkyuUser("Web1");
		registerInfo1.setShouninDate("2022/08/01");
		registerInfo1.setRitosLink("");
		registerInfo1.setKhsData(khsDataList.toArray(new KhsData[khsDataList.size()]));
		registerInfo1.setMarkerData(markerDataList.toArray(new MarkerData[markerDataList.size()]));
		registerInfo1.setSofuData(sofuDataList1.toArray(new SofuData[sofuDataList1.size()]));
		registerInfoDtoList.add(registerInfo1);

		RegisterInfoDto registerInfo2 = new RegisterInfoDto();
		registerInfo2.setCreateUserCd("作成者2");
		registerInfo2.setKihyousha("michino.kanako.W@jp.ricoh.com");
		registerInfo2.setShouninsha("承認者2");
		registerInfo2.setDocumentId("CIC2");
		registerInfo2.setAnkenNm("クラウドサービス for MVB インストール代行オプション");
		registerInfo2.setAnkenOkyakuNm("設置先会社名2");
		registerInfo2.setAnkenJigyosyoNm("設置先事業者2");
		registerInfo2.setAnkenjouhouAdd("設置先住所2");
		registerInfo2.setAnkenTel("設置先TEL2");
		registerInfo2.setBushomei("部署2");
		registerInfo2.setAnkenTantoUserNm("設置先担当者2");
		registerInfo2.setIraiBusyoCode("000226");
		registerInfo2.setIraiBushoAll("");
		registerInfo2.setSagyoDate("2022/08/02");
		registerInfo2.setSagyoIrai("01");
		registerInfo2.setKikiJoho("04");
		registerInfo2.setCeRenrakuNoki("2022/08/02");
		registerInfo2.setSagyoJikanKbn("01");
		registerInfo2.setSagyouJikan2("1");
		registerInfo2.setSofubutu("2");
		registerInfoDtoList.add(registerInfo2);

		SAndSWebRequestDto requestDto = new SAndSWebRequestDto();
		requestDto.setRegisterInfoDtoList(registerInfoDtoList);

		try {
			SAndSWebResponseDto responceDto = getHelper().postIntraMart(requestDto);
			System.out.println("★★取得結果：" + responceDto);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void 異常系_postIntraMartTestエラー() throws JsonProcessingException {
		List<RegisterInfoDto> registerInfoDtoList = new ArrayList<RegisterInfoDto>();
		SAndSWebRequestDto requestDto = new SAndSWebRequestDto();
		requestDto.setRegisterInfoDtoList(registerInfoDtoList);
		IntraMartConnectionHelper helper = getHelper();
		try {
			Mockito.doReturn("test").when(intraMartConnectionHelper).createBody(Mockito.anyObject());
			helper.postIntraMart(requestDto);
			fail("正常終了しました。");
		} catch (RuntimeException e) {
			log.error(e);
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			// チェック
			assertEquals("エラーメッセージが一致すること", "Intra-mart S&S作業依頼APIで想定外のエラーが発生しました。; nested exception is java.lang.RuntimeException: org.springframework.web.client.HttpClientErrorException: 400 Bad Request", e.getMessage());
		} catch (Exception e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			fail("想定外のエラーが発生しました。");
		}
	}

	@Test
	public void 異常系_postIntraMartTest_正常エラーデータ混在() {

		List<RegisterInfoDto> registerInfoDtoList = new ArrayList<RegisterInfoDto>();
		RegisterInfoDto registerInfo1 = new RegisterInfoDto();
		registerInfo1.setCreateUserCd("作成者1");
		registerInfo1.setKihyousha("michino.kanako.W@jp.ricoh.com");
		registerInfo1.setShouninsha("承認者1");
		registerInfo1.setDocumentId("CIC1");
		registerInfo1.setAnkenNm("クラウドサービス for MVB インストール代行オプション");
		registerInfo1.setAnkenOkyakuNm("設置先会社名1");
		registerInfo1.setAnkenJigyosyoNm("設置先事業者1");
		registerInfo1.setAnkenjouhouAdd("設置先住所1");
		registerInfo1.setAnkenTel("設置先TEL1");
		registerInfo1.setBushomei("部署1");
		registerInfo1.setAnkenTantoUserNm("設置先担当者1");
		registerInfo1.setIraiBusyoCode("000226");
		registerInfo1.setIraiBushoAll("");
		registerInfo1.setSagyoDate("2022/08/01");
		registerInfo1.setSagyoIrai("01");
		registerInfo1.setKikiJoho("04");
		registerInfo1.setCeRenrakuNoki("2022/08/01");
		registerInfo1.setSagyoJikanKbn("01");
		registerInfo1.setSagyouJikan2("1");
		registerInfo1.setSofubutu("2");
		registerInfoDtoList.add(registerInfo1);

		RegisterInfoDto registerInfo2 = new RegisterInfoDto();
		registerInfo2.setCreateUserCd("作成者2");
		registerInfo2.setKihyousha("michino.kanako.W@jp.ricoh.com");
		registerInfo2.setShouninsha("承認者2");
		registerInfo2.setDocumentId("CIC2");
		registerInfo2.setAnkenNm("クラウドサービス for MVB インストール代行オプション");
		registerInfo2.setAnkenOkyakuNm("設置先会社名2");
		registerInfo2.setAnkenJigyosyoNm("設置先事業者2");
		registerInfo2.setAnkenjouhouAdd("設置先住所2");
		registerInfo2.setAnkenTel("設置先TEL2");
		registerInfo2.setBushomei("部署2");
		registerInfo2.setAnkenTantoUserNm("設置先担当者2");
		registerInfo2.setIraiBusyoCode("000226");
		registerInfo2.setIraiBushoAll("");
		registerInfo2.setSagyoDate("20220802");
		registerInfo2.setSagyoIrai("");
		registerInfo2.setKikiJoho("04");
		registerInfo2.setCeRenrakuNoki("2022/08/02");
		registerInfo2.setSagyoJikanKbn("01");
		registerInfo2.setSagyouJikan2("1");
		registerInfo2.setSofubutu("2");
		registerInfoDtoList.add(registerInfo2);

		RegisterInfoDto registerInfo3 = new RegisterInfoDto();
		registerInfo3.setCreateUserCd("作成者3");
		registerInfo3.setKihyousha("michino.kanako.W@jp.ricoh.com");
		registerInfo3.setShouninsha("承認者3");
		registerInfo3.setDocumentId("CIC3");
		registerInfo3.setAnkenNm("クラウドサービス for MVB インストール代行オプション");
		registerInfo3.setAnkenOkyakuNm("設置先会社名3");
		registerInfo3.setAnkenJigyosyoNm("設置先事業者3");
		registerInfo3.setAnkenjouhouAdd("設置先住所3");
		registerInfo3.setAnkenTel("設置先TEL3");
		registerInfo3.setBushomei("部署3");
		registerInfo3.setAnkenTantoUserNm("設置先担当者3");
		registerInfo3.setIraiBusyoCode("000226");
		registerInfo3.setIraiBushoAll("");
		registerInfo3.setSagyoDate("2022/08/03");
		registerInfo3.setSagyoIrai("00001");
		registerInfo3.setKikiJoho("04");
		registerInfo3.setCeRenrakuNoki("2022/08/03");
		registerInfo3.setSagyoJikanKbn("01");
		registerInfo3.setSagyouJikan2("1");
		registerInfo3.setSofubutu("2");
		registerInfoDtoList.add(registerInfo3);

		SAndSWebRequestDto requestDto = new SAndSWebRequestDto();
		requestDto.setRegisterInfoDtoList(registerInfoDtoList);

		IntraMartConnectionHelper helper = getHelper();

		try {
			helper.postIntraMart(requestDto);
			fail("正常終了しました。");
		} catch (RuntimeException e) {
			log.error(e);
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			// チェック
			assertEquals("登録件数が一致すること", "1", helper.getResponseDto().getRegisterResultInfoDto().getRegistrationCount());
			assertEquals("エラー件数が一致すること", "2", helper.getResponseDto().getRegisterResultInfoDto().getErrorInfo().getErrorCount());
			assertEquals("エラー文書IDが一致すること", "CIC2", helper.getResponseDto().getRegisterResultInfoDto().getErrorInfo().getErrorContent()[0].getErrorDocumentid());
			assertEquals("エラー内容が一致すること", "必須項目に入力してください。(対象項目: 作業依頼項目)\n" //
					+ "日付形式で入力してください。(対象項目: 依頼作業日)", helper.getResponseDto().getRegisterResultInfoDto().getErrorInfo().getErrorContent()[0].getErrorMsg());
			assertEquals("エラー文書IDが一致すること", "CIC3", helper.getResponseDto().getRegisterResultInfoDto().getErrorInfo().getErrorContent()[1].getErrorDocumentid());
			assertEquals("エラー内容が一致すること", "最大サイズを超えました。(対象項目: 作業依頼項目)", helper.getResponseDto().getRegisterResultInfoDto().getErrorInfo().getErrorContent()[1].getErrorMsg());
			assertEquals("エラーメッセージが一致すること", "Intra-mart S&S作業依頼APIで想定外のエラーが発生しました。; nested exception is java.lang.RuntimeException: Intra-mart S&S作業依頼APIでエラーが発生しました。\n" //
					+ "契約番号：CIC2、エラー内容：必須項目に入力してください。(対象項目: 作業依頼項目)\n" //
					+ "日付形式で入力してください。(対象項目: 依頼作業日)\n" //
					+ "契約番号：CIC3、エラー内容：最大サイズを超えました。(対象項目: 作業依頼項目)" //
					, e.getMessage());
		} catch (Exception e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			fail("想定外のエラーが発生しました。");
		}
	}
}
