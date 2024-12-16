package jp.co.ricoh.cotos.commonlib.eim;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentUploadProperties;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentUploadRequest;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests.DocumentUploadSystem;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentUploadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.FileUploadResponse;
import jp.co.ricoh.cotos.commonlib.logic.eim.EimConnectionHelper;
import jp.co.ricoh.cotos.commonlib.util.EimConnectionProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestEimConnectionHelper {

	@Autowired
	EimConnectionHelper eimConnectionHelper;

	@Autowired
	EimConnectionProperties eimConnectionProperties;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.stop();
		}
	}

	@Test
	@Ignore
	public void 添付ファイルアップロード要求_動作確認_PDF() throws Exception {
		try {
			byte[] fileBody = Files.readAllBytes(Paths.get("src/test/resources/emi/test01.pdf"));
			MediaType contentType = MediaType.APPLICATION_PDF;
			ResponseEntity<FileUploadResponse> responseEntity = eimConnectionHelper.postFile("test01.pdf", fileBody, contentType);
			Assert.assertEquals("200", responseEntity.getStatusCode().toString());
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	@Ignore
	public void 添付ファイルアップロード要求_動作確認_contentTypeがnull() {
		try {
			byte[] fileBody = Files.readAllBytes(Paths.get("src/test/resources/emi/test01.pdf"));
			ResponseEntity<FileUploadResponse> responseEntity = eimConnectionHelper.postFile("test01.pdf", fileBody, null);
			Assert.assertEquals("200", responseEntity.getStatusCode().toString());
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	@Ignore
	public void アプリに文書を登録_動作確認() {
		try {
			DocumentUploadRequest request = new DocumentUploadRequest();
			DocumentUploadSystem system = new DocumentUploadSystem();
			system.setAppId(eimConnectionProperties.getAppId());
			system.setModelId(eimConnectionProperties.getModelId());
			request.setSystem(system);

			DocumentUploadProperties properties = new DocumentUploadProperties();
			properties.setSystemName(eimConnectionProperties.getSystemName());
			properties.setDocumentUniqueID("1");
			List<String> fileBody = Arrays.asList("7e38acc2c46543c7a9393ec495f67272");
			properties.setFileBody(fileBody);
			request.setProperties(properties);
			ResponseEntity<DocumentUploadResponse> responseEntity = eimConnectionHelper.postDocument(request);
			Assert.assertEquals("201", responseEntity.getStatusCode().toString());
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	@Ignore
	public void 添付ファイルのダウンロード要求_動作確認() {
		try {
			String fileId = "7e38acc2c46543c7a9393ec495f67272";
			eimConnectionHelper.getFile(fileId);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	@Ignore
	public void 文書を文書キーで保存_動作確認() {
		try {
			DocumentUploadRequest request = new DocumentUploadRequest();
			DocumentUploadProperties properties = new DocumentUploadProperties();
			properties.setSystemName(eimConnectionProperties.getSystemName());
			properties.setDocumentUniqueID("T010000025");
			properties.setDocumentId("7bc0ca432c4e4a2d84192a3355c6dd02");
			properties.setDocumentKey("7bc0ca432c4e4a2d84192a3355c6dd02");
			properties.setTitle("#6125_CSPテスト");
			properties.setKeiyakNo("T010000025");
			properties.setHnsyCd("091");
			properties.setSyainCd("00229746");
			properties.setSyainNm("【PUTテスト】一戸満");
			properties.setKssCd("0913430");
			properties.setCshnsyCd("091");
			properties.setCssyainCd("00185523");
			properties.setCssyainNm("林真由●");
			properties.setCskssCd("0913992");
			properties.setCskssName("販売事業本部　千葉支社　－　業務２グループ　");
			properties.setEkigyoId("000000006439751");
			properties.setEjgsId("000000006168076");
			properties.setEkijibId("000000009508821");
			properties.setEkigyoNm("Ｉ＊佐＊アリコジャパン損保ジャパンアフラック代理店");
			properties.setEjgsNm("＊＊＊");
			properties.setUkigyoId("000000002658981");
			properties.setUjgsId("000000005676909");
			properties.setUkijibId("000000008956005");
			properties.setUkigyoNm("アライ株式＊＊");
			properties.setUbmnNm("＊＊");
			properties.setTokuCd("10111610185");
			properties.setTokuNm("アライ株式＊＊");
			properties.setKisyuKiban("955S#CIC202009030022 956A#CIC202009030022 956C#test");
			properties.setCsrhnsyCd("091");
			properties.setCsrsyainCd("00185523");
			properties.setCsrsyainNm("林真由●");
			properties.setCsrkssCd("0913992");
			properties.setCsrkssName("販売事業本部　千葉支社　－　業務２グループ　");
			properties.setCsihnsyCd("091");
			properties.setCsisyainCd("00185523");
			properties.setCsisyainNm("林真由●");
			properties.setCsikssCd("0913992");
			properties.setCsikssName("販売事業本部　千葉支社　－　業務２グループ　");
			properties.setKssName("販売事業本部　北海道支社　札幌南西営業部　南西第一営業所　");
			properties.setUjgsNm("本＊＊");
			properties.setFileCount(3);
			properties.setDelFlg(0);
			properties.setHnsyNm("北海道支社");
			properties.setBmnName1("販売事業本部");
			properties.setBmnName2("北海道支社");
			properties.setBmnName3("札幌南西営業部");
			properties.setBmnName4("小樽営業所");
			properties.setCshnsyNm("東京支社");
			properties.setCsbmnName1("販売事業本部");
			properties.setCsbmnName2("東京支社");
			properties.setCsbmnName3("城北Ｓ＆Ｓ部");
			properties.setCsbmnName4("豊島サービスステーション");
			properties.setCsrhnsyNm("東京支社");
			properties.setCsrbmnName1("販売事業本部");
			properties.setCsrbmnName2("東京支社");
			properties.setCsrbmnName3("城北Ｓ＆Ｓ部");
			properties.setCsrbmnName4("豊島サービスステーション");
			properties.setCsihnsyNm("東京支社");
			properties.setCsibmnName1("販売事業本部");
			properties.setCsibmnName2("東京支社");
			properties.setCsibmnName3("城北Ｓ＆Ｓ部");
			properties.setCsibmnName4("豊島サービスステーション");
			properties.setSiskThNm("正式帳票名");
			properties.setSkbtKbn("1");
			properties.setKiykKngk("2000");
			properties.setKiykTiktHi("2024-12-11T15:00:00.000Z");
			properties.setKeiyakType("2");
			properties.setFfmHttiawsNo("ffm001");
			properties.setVupMtmriNo("V2000162420");
			properties.setVupAnknNo("100032719");
			properties.setSerKisHi("2024-12-12T15:00:00.000Z");
			properties.setSerRyuKbuHi("2024-12-13T15:00:00.000Z");

			List<String> fileBody = Arrays.asList("e4a7c6194562473aa1f42f7262a8a612", "55262ce4c21648cbabaf98488666cb70", "18c4d845a89f414bb8263cb1dfd369b6");
			properties.setFileBody(fileBody);
			request.setProperties(properties);

			eimConnectionHelper.putDocument(request);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void properties取得() {
		try {
			Method sysMethod = EimConnectionHelper.class.getDeclaredMethod("getProperties");
			sysMethod.setAccessible(true);
			EimConnectionProperties properties = (EimConnectionProperties) sysMethod.invoke(eimConnectionHelper);

			assertEquals("hostNameが想定通りであること", "app-dev28", properties.getHostName());
			assertEquals("domainNameが想定通りであること", "ope.azure.ricoh-eim.com", properties.getDomainName());
			assertEquals("systemAuthPathが想定通りであること", "services/v1/system/auth", properties.getSystemAuthPath());
			assertEquals("apiAuthPathが想定通りであること", "api/v1/auth", properties.getApiAuthPath());
			assertEquals("loginUserNameが想定通りであること", "test02", properties.getLoginUserName());
			assertEquals("loginPasswordが想定通りであること", "P@ssw0rd", properties.getLoginPassword());
			assertEquals("fileUploadPathが想定通りであること", "services/v1/files/upload", properties.getFileUploadPath());
			assertEquals("appIdが想定通りであること", "RFG_CTS_CNT_02", properties.getAppId());
			assertEquals("appIdMonthDBが想定通りであること", "RFG_CTS_CNT_01", properties.getAppIdMonthDB());
			assertEquals("resourcesPathが想定通りであること", "resources/v3/apps/", properties.getResourcesPath());
			assertEquals("documentsPathが想定通りであること", "documents", properties.getDocumentsPath());
			assertEquals("fileDownloadPathが想定通りであること", "services/v1/files/download", properties.getFileDownloadPath());
			assertEquals("modelIdが想定通りであること", "DetailForm^CotosContractModel", properties.getModelId());
			assertEquals("systemNameが想定通りであること", "CTSCont02", properties.getSystemName());
		} catch (Exception e) {
			Assert.fail("エラーが発生した");
		}
	}
}
