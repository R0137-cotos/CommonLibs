package jp.co.ricoh.cotos.commonlib.eim;

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
@SpringBootTest
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

			List<String> fileBody = Arrays.asList("e4a7c6194562473aa1f42f7262a8a612", "55262ce4c21648cbabaf98488666cb70", "18c4d845a89f414bb8263cb1dfd369b6");
			properties.setFileBody(fileBody);
			request.setProperties(properties);

			eimConnectionHelper.putDocument(request);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}
}
