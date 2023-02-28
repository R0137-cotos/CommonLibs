package jp.co.ricoh.cotos.commonlib.logic.xml;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.xml.dto.TestInnerDto;
import jp.co.ricoh.cotos.commonlib.logic.xml.dto.TestXmlDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class XmlUtilTest {

	@Autowired
	private XmlUtil xmlUtil;

	@Test
	public void 正常系_xml文字() {
		String xml = "<?xml version=\"1.0\"?>" //
				+ "<testDto>" //
				+ "  <id>20</id>" //
				+ "  <estimationTitle>タイトル</estimationTitle>" //
				+ "  <lifecycle_status>1</lifecycle_status>" //
				+ "  <workflowStatus>2</workflowStatus>" //
				+ "  <contract_id>3</contract_id>" //
				+ "  <Type>type</Type>" //
				+ "  <inner>" //
				+ "    <id>100</id>" //
				+ "    <text>text1</text>" //
				+ "  </inner>" //
				+ "  <inner>" //
				+ "    <id>200</id>" //
				+ "    <text>text2</text>" //
				+ "  </inner>" //
				+ "</testDto>"; //
		TestXmlDto dto = xmlUtil.convertToDto(xml, TestXmlDto.class);
		Assert.assertEquals("idが想定通りであること", 20, dto.getId().longValue());
		Assert.assertEquals("estimationTitleが想定通りであること", "タイトル", dto.getEstimationTitle());
		Assert.assertEquals("lifecycle_statusが文字列で取得できること", "1", dto.getLifecycleStatus());
		Assert.assertNull("workflowStatusが取得できていないこと", dto.getWorkflowStatus()); // キャメルケースの自動変換はなし
		Assert.assertEquals("contract_idが数字で取得できること", 3, dto.getContractId().longValue());
		Assert.assertNull("Typeが取得できていないこと", dto.getType()); // クラス名は最初小文字で認識するが、配下は大文字小文字の区別あり
		Assert.assertNull("emptyが取得できていないこと", dto.getEmpty());
		Assert.assertEquals("innerの数が想定通りであること", 2, dto.getInnerList().size()); //インナークラスの場合「@XmlRootElement」で設定している値ではなく「@XmlElement」で指定している値優先
		Assert.assertEquals("【1件目】idが想定通りであること", 100, dto.getInnerList().get(0).getId());
		Assert.assertEquals("【1件目】textが想定通りであること", "text1", dto.getInnerList().get(0).getText());
		Assert.assertEquals("【2件目】idが想定通りであること", 200, dto.getInnerList().get(1).getId());
		Assert.assertEquals("【2件目】textが想定通りであること", "text2", dto.getInnerList().get(1).getText());
	}

	@Test
	public void 正常系_xml文字2() {
		String xml = "<?xml version=\"1.0\"?>" //
				+ "  <inner2>" //
				+ "    <id>200</id>" //
				+ "    <text>text2</text>" //
				+ "  </inner2>"; //
		TestInnerDto dto = xmlUtil.convertToDto(xml, TestInnerDto.class);
		Assert.assertEquals("idが想定通りであること", 200, dto.getId());
		Assert.assertEquals("textが想定通りであること", "text2", dto.getText());
	}

	@Test
	public void 正常系_xmlファイル() {
		Path path = Paths.get("src/test/resources/logic/xml/shift-jis.xml");
		try {
			TestXmlDto dto = xmlUtil.convertToDto(path, TestXmlDto.class, Charset.forName("Shift_JIS"));
			Assert.assertEquals("idが想定通りであること", 20, dto.getId().longValue());
			Assert.assertEquals("estimationTitleが想定通りであること", "タイトル", dto.getEstimationTitle());
			Assert.assertEquals("lifecycle_statusが文字列で取得できること", "1", dto.getLifecycleStatus());
			Assert.assertNull("workflowStatusが取得できていないこと", dto.getWorkflowStatus()); // キャメルケースの変換は実施しない
			Assert.assertEquals("contract_idが数字で取得できること", 3, dto.getContractId().longValue());
			Assert.assertNull("Typeが取得できていないこと", dto.getType()); // クラス名は最初小文字で認識するが、配下は大文字小文字の区別あり
			Assert.assertNull("emptyが取得できていないこと", dto.getEmpty());
			Assert.assertEquals("innerの数が想定通りであること", 2, dto.getInnerList().size()); //インナークラスの場合「@XmlRootElement」で設定している値ではなく「@XmlElement」で指定している値優先
			Assert.assertEquals("【1件目】idが想定通りであること", 100, dto.getInnerList().get(0).getId());
			Assert.assertEquals("【1件目】textが想定通りであること", "text1", dto.getInnerList().get(0).getText());
			Assert.assertEquals("【2件目】idが想定通りであること", 200, dto.getInnerList().get(1).getId());
			Assert.assertEquals("【2件目】textが想定通りであること", "text2", dto.getInnerList().get(1).getText());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("想定外のエラー");
		}
	}

	@Test
	public void 異常系_文字列がnull() {
		try {
			xmlUtil.convertToDto(null, TestXmlDto.class);
			Assert.fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			// エラーメッセージ取得
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals("ROT00013", messageInfo.get(0).getErrorId());
			Assert.assertEquals("XML文字列が設定されていません。", messageInfo.get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラー");
		}
	}

	@Test
	public void 異常系_変換対象クラスがnull() {
		try {
			xmlUtil.convertToDto("aaa", null);
			Assert.fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			// エラーメッセージ取得
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals("ROT00013", messageInfo.get(0).getErrorId());
			Assert.assertEquals("変換対象クラスが設定されていません。", messageInfo.get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラー");
		}
	}

	@Test
	public void 異常系_存在しないファイル() {
		Path path = Paths.get("src/test/resources/logic/xml/no-file");
		try {
			xmlUtil.convertToDto(path, TestXmlDto.class, Charset.forName("Shift_JIS"));
			Assert.fail("異常系のテストで正常終了した");
		} catch (IOException e) {
			// エラーメッセージ取得
			Assert.assertTrue("対象の例外が発生", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラー");
		}
	}

	@Test
	public void 異常系_文字コードがnull() {
		Path path = Paths.get("src/test/resources/logic/xml/shift-jis.xml");
		try {
			xmlUtil.convertToDto(path, TestXmlDto.class, null);
			Assert.fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			// エラーメッセージ取得
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals("ROT00013", messageInfo.get(0).getErrorId());
			Assert.assertEquals("文字コードが設定されていません。", messageInfo.get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラー");
		}
	}
}
