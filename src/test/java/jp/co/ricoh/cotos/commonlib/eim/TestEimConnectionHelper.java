package jp.co.ricoh.cotos.commonlib.eim;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
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
	//@Ignore
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
	//@Ignore
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
	//@Ignore
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
	//@Ignore
	public void 添付ファイルのダウンロード要求_動作確認() {
		try {
			String fileId = "7e38acc2c46543c7a9393ec495f67272";
			eimConnectionHelper.getFile(fileId);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	//@Ignore
	public void 文書を文書キーで保存_動作確認() {
		try {
			DocumentUploadRequest request = new DocumentUploadRequest();
			DocumentUploadProperties properties = new DocumentUploadProperties();
			properties.setSystemName(eimConnectionProperties.getSystemName());
			properties.setDocumentUniqueID("e8fb3bef191c463eaceb1a1b88ae9815");
			properties.setDocumentKey("e8fb3bef191c463eaceb1a1b88ae9815");
			List<String> fileBody = Arrays.asList("7e38acc2c46543c7a9393ec495f67272");
			properties.setFileBody(fileBody);
			request.setProperties(properties);

			eimConnectionHelper.putDocument(request);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}
}
