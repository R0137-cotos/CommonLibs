package jp.co.ricoh.cotos.commonlib.eim;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.ApiAuthResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentDeleteResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentGetResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.PostCotosDocumentResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.PreparationFileUploadResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.SystemAuthResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.ElconDocumentRegistrationParameter;
import jp.co.ricoh.cotos.commonlib.logic.eim.EimConnectionHelper;
import jp.co.ricoh.cotos.commonlib.logic.eim.ElconEimConnectionHelper;
import jp.co.ricoh.cotos.commonlib.util.ElconEimConnectionProperties;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Log4j
public class TestElconEimConnectionHelper {

	//	@Mock
	//	RestTemplate restTemplate;

	@SpyBean
	ElconEimConnectionHelper elconEimConnectionHelper;

	@Autowired
	EimConnectionHelper eimConnectionHelper;

	@Autowired
	ElconEimConnectionProperties elconEimConnectionProperties;

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
	public void 正常系_文書取得API() throws Exception {

		String documentId = "7bc0ca432c4e4a2d84192a3355c6dd02";
		try {
			DocumentGetResponse response = elconEimConnectionHelper.getDocument(documentId);
			assertNotNull("StatusCodeがNULLでないこと", response.getDocument().getProperties().getStatusCode());
			assertNotNull("StatusNameがNULLでないこと", response.getDocument().getProperties().getStatusName());

		} catch (RestClientException e) {
			log.info(e);
			Assert.fail("異常終了");
		}
	}

	@Test
	@Ignore
	public void 正常系_文書更新_論理削除API() throws Exception {

		String documentId = "7bc0ca432c4e4a2d84192a3355c6dd02";
		try {
			DocumentDeleteResponse response = elconEimConnectionHelper.deleteDocument(documentId);
			assertNotNull("documentKeyがNULLでないこと", response.getSystem().getDocumentKey());

		} catch (RestClientException e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	//@Ignore
	public void 正常系_ファイルアップロード準備API() throws Exception {

		try {
			RestTemplate restForEim = new RestTemplate();
			// EIMシステム認証API
			Method sysMethod = EimConnectionHelper.class.getDeclaredMethod("systemAuth", RestTemplate.class);
			sysMethod.setAccessible(true);
			SystemAuthResponse sysActual = (SystemAuthResponse) sysMethod.invoke(eimConnectionHelper, restForEim);

			// アプリケーション認証API
			Method apMethod = EimConnectionHelper.class.getDeclaredMethod("apiAuth", RestTemplate.class, SystemAuthResponse.class);
			apMethod.setAccessible(true);
			ApiAuthResponse apActual = (ApiAuthResponse) apMethod.invoke(eimConnectionHelper, restForEim, sysActual);

			// パラメータ設定
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setFileName("ファイルアップロード準備APIテスト");
			paramDto.setVupContractNo("RJ管理番号1");

			// 実行
			PreparationFileUploadResponse response = elconEimConnectionHelper.preparationFilesUpload(restForEim, apActual, paramDto);
			assertNotNull("x-ms-blob-content-dispositionがNULLでないこと", response.getHeader().getX_ms_blob_content_disposition());
			assertNotNull("x-ms-blob-content-typeがNULLでないこと", response.getHeader().getX_ms_blob_content_type());
			assertNotNull("x-ms-blob-typeがNULLでないこと", response.getHeader().getX_ms_blob_type());

		} catch (RestClientException e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	//@Ignore
	public void 正常系_ファイルアップロードAPI() throws Exception {

		try {
			RestTemplate restForEim = new RestTemplate();
			// EIMシステム認証API
			Method sysMethod = EimConnectionHelper.class.getDeclaredMethod("systemAuth", RestTemplate.class);
			sysMethod.setAccessible(true);
			SystemAuthResponse sysActual = (SystemAuthResponse) sysMethod.invoke(eimConnectionHelper, restForEim);

			// アプリケーション認証API
			Method apMethod = EimConnectionHelper.class.getDeclaredMethod("apiAuth", RestTemplate.class, SystemAuthResponse.class);
			apMethod.setAccessible(true);
			ApiAuthResponse apActual = (ApiAuthResponse) apMethod.invoke(eimConnectionHelper, restForEim, sysActual);

			byte[] fileBody = Files.readAllBytes(Paths.get("src/test/resources/emi/test01.pdf"));

			// パラメータ設定
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setFileName("ファイルアップロード準備APIテスト");
			paramDto.setVupContractNo("RJ管理番号1");
			paramDto.setTargetPdf(fileBody);
			paramDto.setVupContractNo("vup契約No");
			paramDto.setVupEstimatesNo("vup見積No");
			paramDto.setAnknMi("案件名");
			paramDto.setHanshaCd("091");
			paramDto.setSaId("00229746");
			paramDto.setCustomerName("企業名");
			paramDto.setImfrSdInsertId("2000000001");
			paramDto.setImfrSdRowNo("2");
			paramDto.setStartDatePrintFlag("1");
			paramDto.setCustomerPrintFlag("1");

			PreparationFileUploadResponse preparationRes = new PreparationFileUploadResponse();
			preparationRes.setId("027d4859c2534fc0a836135b04a8654c");

			// ファイルアップロード準備API
			//PreparationFileUploadResponse preparationRes = elconEimConnectionHelper.preparationFilesUpload(restForEim, apActual, paramDto);

			// ファイルアップロードAPI
			//elconEimConnectionHelper.filesUpload(restForEim, apActual, preparationRes, paramDto);

			// 文書登録（COTOS申込書）
			PostCotosDocumentResponse result = elconEimConnectionHelper.postCotosDocument(restForEim, apActual, preparationRes, paramDto);
			assertNotNull("documentIdがNULLでないこと", result.getSystem().getDocumentId());
			assertNotNull("documentKeyがNULLでないこと", result.getSystem().getDocumentKey());

		} catch (RestClientException e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	//@Ignore
	public void 異常系_文書取得API() throws Exception {

		String documentId = "9999999999999";
		try {
			elconEimConnectionHelper.getDocument(documentId);
			Assert.fail("正常終了してしまった");
		} catch (RestClientException e) {
			// ログが正しく出力されること
			Logger mockLog = Mockito.mock(Logger.class);
			mockLog.error("【APIエラー】電子契約EIMの文書取得API実行に失敗しました。[ドキュメントID:9999999999999]");
			verify(mockLog).error(eq("【APIエラー】電子契約EIMの文書取得API実行に失敗しました。[ドキュメントID:9999999999999]"));
			// 処理がリトライ回数だけ行われていること
			verify(elconEimConnectionHelper, times(5)).getDocument(Mockito.anyString());
		}
	}

	//@Test
	//@Ignore
	//	public void 異常系_文書取得API_200以外() throws Exception {
	//		HttpEntity<?> requestEntity = new HttpEntity<>(new HttpHeaders());
	//		DocumentGetResponse dto = new DocumentGetResponse();
	//		ResponseEntity<DocumentGetResponse> responseEntity = new ResponseEntity<DocumentGetResponse>(dto, HttpStatus.ACCEPTED);
	//		Mockito.doReturn(responseEntity).when(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.eq(requestEntity), Mockito.eq(DocumentGetResponse.class));
	//
	//		String documentId = "7bc0ca432c4e4a2d84192a3355c6dd02";
	//		try {
	//			elconEimConnectionHelper.getDocument(documentId);
	//			Assert.fail("正常終了してしまった");
	//		} catch (RestClientException e) {
	//			Logger mockLog = Mockito.mock(Logger.class);
	//			// ログが正しく出力されること
	//			mockLog.error("【APIエラー】電子契約EIMの文書取得API実行に失敗しました。[ドキュメントID:9999999999999]");
	//			verify(mockLog).error(eq("【APIエラー】電子契約EIMの文書取得API実行に失敗しました。[ドキュメントID:9999999999999]"));
	//			// 処理がリトライ回数だけ行われていること
	//			verify(elconEimConnectionHelper, times(5)).getDocument(Mockito.anyString());
	//		}
	//	}

	@Test
	@Ignore
	public void 異常系_文書更新_論理削除API() throws Exception {

		String documentId = "9999999999999";
		try {
			elconEimConnectionHelper.deleteDocument(documentId);
			Assert.fail("正常終了してしまった");
		} catch (RestClientException e) {
			Logger mockLog = Mockito.mock(Logger.class);
			// ログが正しく出力されること
			mockLog.error("【APIエラー】電子契約EIMの文書更新(論理削除)API実行に失敗しました。[ドキュメントID:9999999999999]");
			verify(mockLog).error(eq("【APIエラー】電子契約EIMの文書更新(論理削除)API実行に失敗しました。[ドキュメントID:9999999999999]"));
			// 処理がリトライ回数だけ行われていること
			verify(elconEimConnectionHelper, times(5)).deleteDocument(Mockito.anyString());
		}
	}

	@Test
	//@Ignore
	public void 異常系_ファイルアップロード準備API() throws Exception {

		try {
			RestTemplate restForEim = new RestTemplate();
			// EIMシステム認証API
			Method sysMethod = EimConnectionHelper.class.getDeclaredMethod("systemAuth", RestTemplate.class);
			sysMethod.setAccessible(true);
			SystemAuthResponse sysActual = (SystemAuthResponse) sysMethod.invoke(eimConnectionHelper, restForEim);

			// アプリケーション認証API
			Method apMethod = EimConnectionHelper.class.getDeclaredMethod("apiAuth", RestTemplate.class, SystemAuthResponse.class);
			apMethod.setAccessible(true);
			ApiAuthResponse apActual = (ApiAuthResponse) apMethod.invoke(eimConnectionHelper, restForEim, sysActual);

			// パラメータ設定
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setFileName(null);
			// 実行
			PreparationFileUploadResponse response = elconEimConnectionHelper.preparationFilesUpload(restForEim, apActual, paramDto);
			assertNotNull("x-ms-blob-content-dispositionがNULLでないこと", response.getHeader().getX_ms_blob_content_disposition());
			assertNotNull("x-ms-blob-content-typeがNULLでないこと", response.getHeader().getX_ms_blob_content_type());
			assertNotNull("x-ms-blob-typeがNULLでないこと", response.getHeader().getX_ms_blob_type());

		} catch (RestClientException e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	@Ignore
	public void 異常系_文書更新_論理削除API_202以外() throws Exception {

		String documentId = "9999999999999";
		try {
			elconEimConnectionHelper.deleteDocument(documentId);
			Assert.fail("正常終了してしまった");
		} catch (RestClientException e) {
			Logger mockLog = Mockito.mock(Logger.class);
			// ログが正しく出力されること
			mockLog.error("【APIエラー】電子契約EIMの文書更新(論理削除)API実行に失敗しました。[ドキュメントID:9999999999999]");
			verify(mockLog).error(eq("【APIエラー】電子契約EIMの文書更新(論理削除)API実行に失敗しました。[ドキュメントID:9999999999999]"));
			// 処理がリトライ回数だけ行われていること
			verify(elconEimConnectionHelper, times(5)).deleteDocument(Mockito.anyString());
		}
	}
}
