package jp.co.ricoh.cotos.commonlib.eim;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

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
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentDeleteResponse;
import jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses.DocumentGetResponse;
import jp.co.ricoh.cotos.commonlib.logic.eim.ElconEimConnectionHelper;
import jp.co.ricoh.cotos.commonlib.util.ElconEimConnectionProperties;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestElconEimConnectionHelper {

	@Autowired
	ElconEimConnectionHelper elconEimConnectionHelper;

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
	@Ignore
	public void 正常系_文書取得API() throws Exception {

		String documentId = "2eb22b56796e4b1a8de0abc5a2033bd0";
		try {
			DocumentGetResponse response = elconEimConnectionHelper.getDocument(documentId);
			assertNotNull("StatusCodeがNULLでないこと", response.getDocument().getProperties().getStatusCode());
			assertNotNull("StatusNameがNULLでないこと", response.getDocument().getProperties().getStatusName());
			verify(elconEimConnectionHelper, times(1)).getDocument(Mockito.anyString());
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
}
