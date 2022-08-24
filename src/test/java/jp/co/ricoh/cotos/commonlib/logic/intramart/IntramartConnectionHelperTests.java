package jp.co.ricoh.cotos.commonlib.logic.intramart;

import static org.junit.Assert.*;

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

import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterInfoDto.KhsData;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterInfoDto.MarkerData;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterInfoDto.SofuData;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.RegisterResultInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart.SAndSWebRequestDto;
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
public class IntramartConnectionHelperTests {

	static ConfigurableApplicationContext context;

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
		IntraMartConnectionHelper.init(context, externalRestTemplate);
		return IntraMartConnectionHelper.getInstance();
	}

	/**
	 *  [POST] S&S作業依頼API テスト
	 */
	@Test
	public void 正常系_postIntraMartTest() {

		List<KhsData> khsDataList = new ArrayList<>();
		KhsData khsData1 = new KhsData();
		khsData1.setKhsMail("共同編集者1");
		KhsData khsData2 = new KhsData();
		khsData2.setKhsMail("共同編集者2");
		KhsData khsData3 = new KhsData();
		khsData3.setKhsMail("共同編集者3");
		KhsData khsData4 = new KhsData();
		khsData4.setKhsMail("共同編集者4");
		khsDataList.add(khsData1);
		khsDataList.add(khsData2);
		khsDataList.add(khsData3);
		khsDataList.add(khsData4);

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
		registerInfo1.setCreateUserCd("作成者");
		registerInfo1.setKihyousha("起票者");
		registerInfo1.setShouninsha("承認者");
		registerInfo1.setDocumentId("文書番号");
		registerInfo1.setAnkenNm("クラウドサービス for MVB インストール代行オプション");
		registerInfo1.setAnkenOkyakuNm("設置先会社名");
		registerInfo1.setAnkenJigyosyoNm("設置先事業者");
		registerInfo1.setAnkenjouhouAdd("設置先住所");
		registerInfo1.setAnkenTel("設置先TEL");
		registerInfo1.setBushomei("部署");
		registerInfo1.setAnkenTantoUserNm("設置先担当者");
		registerInfo1.setIraiBusyoCode("課所コード");
		registerInfo1.setIraiBushoAll("");
		registerInfo1.setSagyoDate("2022/08/24");
		registerInfo1.setSagyoIrai("01");
		registerInfo1.setKikiJoho("04");
		registerInfo1.setCeRenrakuNoki("");
		registerInfo1.setSagyoJikanKbn("01");
		registerInfo1.setSagyouJikan2("1");
		registerInfo1.setTejunsyoKbn("");
		registerInfo1.setBackupKbn("");
		registerInfo1.setJikogu("");
		registerInfo1.setSofubutu("2");
		registerInfo1.setHenkyakuButu("");
		registerInfo1.setHenkyakuKanri("");
		registerInfo1.setReportTeisyutu("");
		registerInfo1.setSysoneKikiReg("");
		registerInfo1.setSysoneCeReg("");
		registerInfo1.setSyaroJoho("");
		registerInfo1.setHosyuKeiyakuIrai("");
		registerInfo1.setKanryoHokoku("");
		registerInfo1.setTotyakuRenraku("");
		registerInfo1.setSagyoStartRenraku("");
		registerInfo1.setSagyoKanryoRenraku("");
		registerInfo1.setTaisyutuRenraku("");
		registerInfo1.setFfmRenkei("");
		registerInfo1.setTokusoku("");
		registerInfo1.setSagyoinHokoku("");
		registerInfo1.setHakoumotoRenraku("2022/08/22");
		registerInfo1.setToiawseNo("TeMS受注No");
		registerInfo1.setInYakusokuTime("");
		registerInfo1.setSagyoStartTime("");
		registerInfo1.setKousokuJikan(120);
		registerInfo1.setCeAssignSu(1);
		registerInfo1.setSagyoKingaku(0);
		registerInfo1.setKobetuNaiyo("");
		registerInfo1.setRitosKinkyuUser("Web");
		registerInfo1.setShouninDate("");
		registerInfo1.setRitosLink("");
		registerInfo1.setKhsData(khsDataList.toArray(new KhsData[khsDataList.size()]));
		registerInfo1.setMarkerData(markerDataList.toArray(new MarkerData[markerDataList.size()]));
		registerInfo1.setSofuData(sofuDataList1.toArray(new SofuData[sofuDataList1.size()]));
		registerInfoDtoList.add(registerInfo1);

		SAndSWebRequestDto requestDto = new SAndSWebRequestDto();
		requestDto.setRegisterInfoDtoList(registerInfoDtoList);

		try {
			RegisterResultInfoDto responceDto = getHelper().postIntraMart(requestDto);
			System.out.println("★★取得結果：" + responceDto);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	/**
	 *  [POST] S&S作業依頼API エラーテスト
	 */
	@Test
	public void 異常系_postIntraMartTest() {
		try {
			SAndSWebRequestDto requestDto = null;
			getHelper().postIntraMart(requestDto);
			fail("正常終了しました。");
		} catch (RuntimeException e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			// チェック
			assertEquals("エラーメッセージが一致すること", "Intra-martAPIでエラーが発生しました。ステータスコード： 400、エラー内容：{\"code\": \"400 Bad Request\", \"message\": \"リクエストの内容が不正です。Content-Type が application/json だが JSON 文字列でない 等が考えられます。\"}", e.getMessage());
		} catch (Exception e) {
			log.error(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(s -> log.error(s));
			fail("想定外のエラーが発生しました。");
		}
	}
}
