package jp.co.ricoh.cotos.commonlib.rest;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExternalClientHttpRequestInterceptorTest {

	static ConfigurableApplicationContext context;

	@Autowired
	ExternalClientHttpRequestInterceptor externalClientHttpRequestInterceptor;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		context.stop();
	}

	@Test
	public void outputLog_メモリサイズ超過() {
		HttpRequest request = mock(HttpRequest.class);
		byte[] body = new byte[] {};
		try {
			byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/rest/レスポンス.txt"));
			ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(bytes, HttpStatus.OK);
			ClientHttpRequestExecution execution = mock(ClientHttpRequestExecution.class);
			Mockito.doReturn(clientHttpResponse).when(execution).execute(any(), any());
			externalClientHttpRequestInterceptor.intercept(request, body, execution);
			Assert.fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00071");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "CPQデータの取得に失敗しました。SE区へご連絡ください。");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生した");
		}
	}

	@Test
	public void outputLog_メモリサイズ範囲内() {
		HttpRequest request = mock(HttpRequest.class);
		byte[] body = new byte[] {};
		try {
			byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/rest/レスポンス_軽.txt"));
			ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(bytes, HttpStatus.OK);
			ClientHttpRequestExecution execution = mock(ClientHttpRequestExecution.class);
			Mockito.doReturn(clientHttpResponse).when(execution).execute(any(), any());
			externalClientHttpRequestInterceptor.intercept(request, body, execution);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生した");
		}
	}

	@Test
	public void outputLog_CPQ以外() {
		HttpRequest request = mock(HttpRequest.class);
		byte[] body = new byte[] {};
		String resstr = "1234567890";
		try {
			ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(resstr.getBytes(), HttpStatus.OK);
		ClientHttpRequestExecution execution = mock(ClientHttpRequestExecution.class);
			Mockito.doReturn(clientHttpResponse).when(execution).execute(any(), any());
			externalClientHttpRequestInterceptor.intercept(request, body, execution);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生した");
		}
	}

}
