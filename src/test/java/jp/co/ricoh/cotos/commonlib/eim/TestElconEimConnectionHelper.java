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
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.PreparationFileUploadResponseHeader;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.ElconDocumentRegistrationParameter;
import jp.co.ricoh.cotos.commonlib.logic.eim.ElconEimConnectionHelper;
import jp.co.ricoh.cotos.commonlib.util.EimConnectionProperties;
import jp.co.ricoh.cotos.commonlib.util.ElconEimConnectionProperties;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestElconEimConnectionHelper {

	@SpyBean
	ElconEimConnectionHelper elconEimConnectionHelper;

	@SpyBean
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
	@Ignore
	public void 正常系_文書取得API() throws Exception {

		String documentId = "2eb22b56796e4b1a8de0abc5a2033bd0";

		try {
			DocumentGetResponse response = elconEimConnectionHelper.getDocument(documentId);
			assertNotNull("StatusCodeがNULLでないこと", response.getDocument().getProperties().getStatusCode());
			assertNotNull("StatusNameがNULLでないこと", response.getDocument().getProperties().getStatusName());
			// 処理が1回のみ行われていること
			verify(elconEimConnectionHelper, times(1)).getDocument(Mockito.anyString());
		} catch (RestClientException e) {
			log.info(e);
			Assert.fail("異常終了");
		}
	}

	@Test
	@Ignore
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

	@Test
	@Ignore
	public void 正常系_文書更新_論理削除API() throws Exception {

		String documentId = "6741c01392734341be6a7a6486417369";
		try {
			DocumentDeleteResponse response = elconEimConnectionHelper.deleteDocument(documentId);
			assertNotNull("documentIdがNULLでないこと", response.getSystem().getDocumentId());
			assertNotNull("documentKeyがNULLでないこと", response.getSystem().getDocumentKey());
			// 処理が1回のみ行われていること
			verify(elconEimConnectionHelper, times(1)).deleteDocument(Mockito.anyString());
		} catch (RestClientException e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	@Ignore
	public void 異常系_文書更新_論理削除API() throws Exception {

		try {
			elconEimConnectionHelper.deleteDocument(null);
			Assert.fail("正常終了してしまった");
		} catch (RestClientException e) {
			// ログが正しく出力されること
			Logger mockLog = Mockito.mock(Logger.class);
			mockLog.error("【APIエラー】電子契約EIMの文書更新（論理削除）API実行に失敗しました。[ドキュメントID:9999999999999]");
			verify(mockLog).error(eq("【APIエラー】電子契約EIMの文書更新（論理削除）API実行に失敗しました。[ドキュメントID:9999999999999]"));
			// 処理がリトライ回数だけ行われていること
			verify(elconEimConnectionHelper, times(5)).deleteDocument(Mockito.anyString());
		}
	}

	@Test
	@Ignore
	public void 正常系_ファイルアップロード準備API() {

		try {
			RestTemplate restForEim = new RestTemplate();
			// アプリケーション認証API
			ApiAuthResponse apResponse = elconEimConnectionHelper.apiAuth(restForEim, null);

			// パラメータ設定
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setFileName("elconEim_test01.pdf");
			paramDto.setVupContractNo("RJ管理番号1");

			// 実行
			PreparationFileUploadResponse response = elconEimConnectionHelper.preparationFilesUpload(restForEim, apResponse, paramDto);
			assertNotNull("x-ms-blob-content-dispositionがNULLでないこと", response.getHeader().getX_ms_blob_content_disposition());
			assertNotNull("x-ms-blob-content-typeがNULLでないこと", response.getHeader().getX_ms_blob_content_type());
			assertNotNull("x-ms-blob-typeがNULLでないこと", response.getHeader().getX_ms_blob_type());

		} catch (RestClientException e) {
			Assert.fail("エラーが発生した");
		} catch (Exception e) {
			Assert.fail("予期せぬエラーが発生した");
		}
	}

	@Test
	@Ignore
	public void 異常系_ファイルアップロード準備API() {

		try {
			RestTemplate restForEim = new RestTemplate();
			// アプリケーション認証API
			ApiAuthResponse apResponse = elconEimConnectionHelper.apiAuth(restForEim, null);

			// パラメータ設定
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setFileName(" ");
			paramDto.setVupContractNo("RJ管理番号1");

			// 実行
			elconEimConnectionHelper.preparationFilesUpload(restForEim, apResponse, paramDto);
			Assert.fail("正常終了してしまった");

		} catch (RestClientException e) {
			// ログが正しく出力されること
			Logger mockLog = Mockito.mock(Logger.class);
			mockLog.error("電子契約EIMのファイルアップロード準備API実行に失敗しました。[RJ管理番号:RJ管理番号1]");
			verify(mockLog).error(eq("電子契約EIMのファイルアップロード準備API実行に失敗しました。[RJ管理番号:RJ管理番号1]"));
			// 処理がリトライ回数だけ行われていること
			verify(elconEimConnectionHelper, times(5)).preparationFilesUpload(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());

		} catch (Exception e) {
			Assert.fail("予期せぬエラーが発生した");
		}
	}

	@Test
	@Ignore
	public void 正常系_ファイルアップロードAPI() {

		try {
			RestTemplate restForEim = new RestTemplate();
			// アプリケーション認証API
			ApiAuthResponse apResponse = elconEimConnectionHelper.apiAuth(restForEim, null);

			// パラメータ設定
			PreparationFileUploadResponseHeader headerResponse = new PreparationFileUploadResponseHeader();
			headerResponse.setX_ms_blob_content_disposition("attachment; filename*=UTF-8''elconEim%5Ftest01%2Epdf");
			headerResponse.setX_ms_blob_content_type("binary/octet-stream");
			headerResponse.setX_ms_blob_type("BlockBlob");
			PreparationFileUploadResponse preResponse = new PreparationFileUploadResponse();
			preResponse.setHeader(headerResponse);
			preResponse.setUrl("https://dev01blob.blob.core.windows.net/412e9d2e34884529bf1d0b53ef7f11e4/911fd1d779fb48c99f00e3afd26e10c5/file?sig=KMEWO3hO0KZlWTKfqNncBP9kzkImiVo3xIm4NWdk%2BrI%3D&se=2024-10-24T17%3A20%3A02Z&sv=2017-04-17&sp=w&sr=b");

			byte[] fileBody = Files.readAllBytes(Paths.get("src/test/resources/emi/elconEim_test01.pdf"));
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setTargetPdf(fileBody);
			paramDto.setVupContractNo("RJ管理番号1");

			// 実行
			elconEimConnectionHelper.fileUpload(restForEim, apResponse, preResponse, paramDto);

		} catch (RestClientException e) {
			Assert.fail("エラーが発生した");
		} catch (Exception e) {
			Assert.fail("予期せぬエラーが発生した");
		}
	}

	@Test
	@Ignore
	public void 異常系_ファイルアップロードAPI() {

		try {
			RestTemplate restForEim = new RestTemplate();
			// アプリケーション認証API
			ApiAuthResponse apResponse = elconEimConnectionHelper.apiAuth(restForEim, null);

			// パラメータ設定
			PreparationFileUploadResponseHeader headerResponse = new PreparationFileUploadResponseHeader();
			headerResponse.setX_ms_blob_content_disposition("attachment; filename*=UTF-8");
			headerResponse.setX_ms_blob_content_type("binary/octet-stream");
			headerResponse.setX_ms_blob_type("BlockBlob");
			PreparationFileUploadResponse preResponse = new PreparationFileUploadResponse();
			preResponse.setHeader(headerResponse);
			// ダミーURL設定
			preResponse.setUrl("https://dev01blob.blob.core.windows.net/412e9d2e34884529bf1d0b53ef7f11e4/dummydummydummy/file?sig=ehtD0GlwyJzSi1hriWaNQtd1yXzm62ovQkXU9tMuqow%3D&se=2024-10-24T14%3A33%3A03Z&sv=2017-04-17&sp=w&sr=b");

			byte[] fileBody = Files.readAllBytes(Paths.get("src/test/resources/emi/elconEim_test01.pdf"));
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setTargetPdf(fileBody);
			paramDto.setVupContractNo("RJ管理番号1");

			// 実行
			elconEimConnectionHelper.fileUpload(restForEim, apResponse, preResponse, paramDto);
			Assert.fail("正常終了してしまった");

		} catch (RestClientException e) {
			// ログが正しく出力されること
			Logger mockLog = Mockito.mock(Logger.class);
			mockLog.error("電子契約EIMのファイルアップロードAPI実行に失敗しました。[RJ管理番号:RJ管理番号1]");
			verify(mockLog).error(eq("電子契約EIMのファイルアップロードAPI実行に失敗しました。[RJ管理番号:RJ管理番号1]"));
			// 処理がリトライ回数だけ行われていること
			verify(elconEimConnectionHelper, times(5)).fileUpload(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());

		} catch (Exception e) {
			Assert.fail("予期せぬエラーが発生した");
		}
	}

	@Test
	@Ignore
	public void 正常系_文書登録_COTOS申込書API() {

		try {
			RestTemplate restForEim = new RestTemplate();
			// アプリケーション認証API
			ApiAuthResponse apResponse = elconEimConnectionHelper.apiAuth(restForEim, null);

			// パラメータ設定
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setVupContractNo("RJ管理番号1");
			paramDto.setVupEstimatesNo("vup見積No");
			paramDto.setAnknMi("案件名");
			paramDto.setHanshaCd("091");
			paramDto.setSaId("00229746");
			paramDto.setCustomerName("企業名");
			paramDto.setImfrSdInsertId("2000000001");
			paramDto.setImfrSdRowNo("2");
			paramDto.setStartDatePrintFlag("1");
			paramDto.setCustomerPrintFlag("1");
			String documentUniqueID = "911fd1d779fb48c99f00e3afd26e10c5";

			// 実行
			PostCotosDocumentResponse response = elconEimConnectionHelper.postCotosDocument(restForEim, apResponse, documentUniqueID, paramDto);
			assertNotNull("documentIdがNULLでないこと", response.getSystem().getDocumentId());
			assertNotNull("documentKeyがNULLでないこと", response.getSystem().getDocumentKey());

		} catch (RestClientException e) {
			Assert.fail("エラーが発生した");
		} catch (Exception e) {
			Assert.fail("予期せぬエラーが発生した");
		}
	}

	@Test
	@Ignore
	public void 異常系_文書登録_COTOS申込書API() {
		try {
			// モック化
			Mockito.doReturn("test").when(elconEimConnectionProperties).getModelIdCotos();

			RestTemplate restForEim = new RestTemplate();
			// アプリケーション認証API
			ApiAuthResponse apResponse = elconEimConnectionHelper.apiAuth(restForEim, null);

			// パラメータ設定
			ElconDocumentRegistrationParameter paramDto = new ElconDocumentRegistrationParameter();
			paramDto.setVupContractNo("RJ管理番号1");
			String documentUniqueID = "911fd1d779fb48c99f00e3afd26e10c5";

			// 実行
			elconEimConnectionHelper.postCotosDocument(restForEim, apResponse, documentUniqueID, paramDto);
			Assert.fail("正常終了してしまった");

		} catch (RestClientException e) {
			// ログが正しく出力されること
			Logger mockLog = Mockito.mock(Logger.class);
			mockLog.error("電子契約EIMの文書登録（COTOS申込書）API実行に失敗しました。[RJ管理番号:RJ管理番号1]");
			verify(mockLog).error(eq("電子契約EIMの文書登録（COTOS申込書）API実行に失敗しました。[RJ管理番号:RJ管理番号1]"));
			// 処理がリトライ回数だけ行われていること
			verify(elconEimConnectionHelper, times(5)).postCotosDocument(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyString(), Mockito.anyObject());

		} catch (Exception e) {
			Assert.fail("予期せぬエラーが発生した");
		}
	}

	@Test
	public void properties取得() {
		try {
			Method sysMethod = ElconEimConnectionHelper.class.getDeclaredMethod("getProperties");
			sysMethod.setAccessible(true);
			EimConnectionProperties properties = (EimConnectionProperties) sysMethod.invoke(elconEimConnectionHelper);

			assertEquals("hostNameが想定通りであること", "app-dev28", properties.getHostName());
			assertEquals("domainNameが想定通りであること", "ope.azure.ricoh-eim.com", properties.getDomainName());
			assertNull("systemAuthPathが想定通りであること", properties.getSystemAuthPath());
			assertEquals("apiAuthPathが想定通りであること", "api/v1/auth", properties.getApiAuthPath());
			assertEquals("loginUserNameが想定通りであること", "test02", properties.getLoginUserName());
			assertEquals("loginPasswordが想定通りであること", "P@ssw0rd", properties.getLoginPassword());
			assertEquals("fileUploadPathが想定通りであること", "services/v1/files/upload", properties.getFileUploadPath());
			assertEquals("appIdが想定通りであること", "RFG_RJ_AGREE_01", properties.getAppId());
			assertNull("appIdMonthDBが想定通りであること", properties.getAppIdMonthDB());
			assertEquals("resourcesPathが想定通りであること", "resources/v3/apps/", properties.getResourcesPath());
			assertEquals("documentsPathが想定通りであること", "documents", properties.getDocumentsPath());
			assertNull("fileDownloadPathが想定通りであること", properties.getFileDownloadPath());
			assertEquals("modelIdが想定通りであること", "DM_FmCloudsignLink", properties.getModelId());
			assertNull("systemNameが想定通りであること", properties.getSystemName());
		} catch (Exception e) {
			Assert.fail("エラーが発生した");
		}
	}

}
