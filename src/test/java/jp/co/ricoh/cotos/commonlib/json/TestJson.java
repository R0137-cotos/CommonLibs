package jp.co.ricoh.cotos.commonlib.json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.json.TestJsonDto.TestEstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.logic.json.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJson {

	@Autowired
	JsonUtil jsonUtil;

	private String JSON_TEXT = "{\"estimationTypeDetails\":\"2\",\"organizationId\":\"ORG0000001\",\"vpn\":\"2\",\"rmaContractNumber\":\"RMA0000001\",\"rmaContractStart\":\"2020/11/01\",\"rmaContractEnd\":\"2020/10/31\",\"testObject\":{\"testKey1\":\"testValue1\",\"testKey2\":\"testValue2\",\"testArray\":[{\"arrayKey1\":\"arrayValue1\",\"arrayKey2\":\"arrayValue2\"},{\"arrayKey1\":\"arrayValue3\",\"arrayKey2\":\"arrayValue4\"}]}}";

	private String JSON_TEXT_ERROR = "{\"estimationTypeDetails\":\"2\",\"organizationId1\":\"ORG0000001\",\"vpn1\":\"2\",\"rmaContractNumber1\":\"RMA0000001\",\"rmaContractStart1\":\"2020/11/01\",\"rmaContractEnd1\":\"2020/10/31\",\"testObject\":{\"testKey1\":\"testValue1\",\"testKey2\":\"testValue2\",\"testArray\":[{\"arrayKey1\":\"arrayValue1\",\"arrayKey2\":\"arrayValue2\"},{\"arrayKey1\":\"arrayValue1\",\"arrayKey2\":\"arrayValue2\"}]}}";

	@Test
	public void 正常系_JSON文字列からオブジェクトに変換() {

		// DTO
		TestJsonDto dto = new TestJsonDto();

		try {
			// JSON文字列からオブジェクトに変換
			dto = jsonUtil.convertToDto(JSON_TEXT, TestJsonDto.class);
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}

		// チェック
		assertNotNull(dto);
		assertEquals(TestEstimationTypeDetails.契約更新, dto.getEstimationTypeDetails());
		assertEquals("ORG0000001", dto.getOrganizationId());
		assertEquals("2", dto.getVpn());
		assertEquals("RMA0000001", dto.getRmaContractNumber());
		assertEquals("2020/11/01", dto.getRmaContractStart());
		assertEquals("2020/10/31", dto.getRmaContractEnd());

		assertNotNull(dto.getTestJsonDetailDto());
		assertEquals("testValue1", dto.getTestJsonDetailDto().getTestKey1());
		assertEquals("testValue2", dto.getTestJsonDetailDto().getTestKey2());

		assertEquals(2, dto.getTestJsonDetailDto().getTestJsonArrayList().size());
		assertEquals("arrayValue1", dto.getTestJsonDetailDto().getTestJsonArrayList().get(0).getArrayKey1());
		assertEquals("arrayValue2", dto.getTestJsonDetailDto().getTestJsonArrayList().get(0).getArrayKey2());
		assertEquals("arrayValue3", dto.getTestJsonDetailDto().getTestJsonArrayList().get(1).getArrayKey1());
		assertEquals("arrayValue4", dto.getTestJsonDetailDto().getTestJsonArrayList().get(1).getArrayKey2());
	}

	@Test
	public void 正常系_JSON文字列からDTOに変換_JSON文字列_項目不一致() {

		try {
			// JSON文字列からオブジェクトに変換
			TestJsonDto dto = jsonUtil.convertToDto(JSON_TEXT_ERROR, TestJsonDto.class);

			// チェック
			assertNotNull(dto);
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}
	}

	@Test
	public void 異常系_JSON文字列からオブジェクトに変換_JSON文字列なし() {

		try {
			// JSON文字列からオブジェクトに変換
			jsonUtil.convertToDto(null, TestJsonDto.class);

			fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00013", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("JSON文字列が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_JSON文字列からオブジェクトに変換_オブジェクトクラスなし() {

		try {
			// JSON文字列からオブジェクトに変換
			jsonUtil.convertToDto(JSON_TEXT, null);

			fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00013", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("Objectが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_JSON文字列からDTOに変換_JSON文字列_JSON以外() {

		try {
			// JSON文字列からオブジェクトに変換
			jsonUtil.convertToDto("no_json_text", TestJsonDto.class);

			fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00045", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("JSON文字列からObjectの変換に失敗しました。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 正常系_オブジェクトからJSON文字列に変換() {

		// DTO
		TestJsonDto dto = new TestJsonDto();

		dto.setEstimationTypeDetails(TestEstimationTypeDetails.契約更新);
		dto.setOrganizationId("ORG0000001");
		dto.setVpn("2");
		dto.setRmaContractNumber("RMA0000001");
		dto.setRmaContractStart("2020/11/01");
		dto.setRmaContractEnd("2020/10/31");

		// DTO Detail
		TestJsonDetailDto dtoDetail = new TestJsonDetailDto();

		dtoDetail.setTestKey1("testValue1");
		dtoDetail.setTestKey2("testValue2");

		// ArrayList
		List<TestJsonArrayDto> arrayDtoList = new ArrayList<>();

		// DTO Array1
		TestJsonArrayDto arrayDto1 = new TestJsonArrayDto();

		arrayDto1.setArrayKey1("arrayValue1");
		arrayDto1.setArrayKey2("arrayValue2");

		arrayDtoList.add(arrayDto1);

		// DTO Array2
		TestJsonArrayDto arrayDto2 = new TestJsonArrayDto();

		arrayDto2.setArrayKey1("arrayValue3");
		arrayDto2.setArrayKey2("arrayValue4");

		arrayDtoList.add(arrayDto2);

		dtoDetail.setTestJsonArrayList(arrayDtoList);

		dto.setTestJsonDetailDto(dtoDetail);

		try {
			// オブジェクトからJSON文字列に変換
			String jsonText = jsonUtil.convertToStr(dto);

			// チェック
			assertNotNull(jsonText);
			assertEquals(JSON_TEXT, jsonText);
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}
	}

	@Test
	public void 正常系_オブジェクトからJSON文字列に変換_オブジェクト以外() {

		try {
			// オブジェクトからJSON文字列に変換
			String jsonText = jsonUtil.convertToStr("text");

			// チェック
			assertNotNull(jsonText);
			assertEquals("\"text\"", jsonText);
		} catch (ErrorCheckException e) {
			fail("エラーが発生した");
		}
	}

	@Test
	public void 異常系_オブジェクトからJSON文字列に変換_オブジェクトなし() {

		try {
			// オブジェクトからJSON文字列に変換
			jsonUtil.convertToStr(null);

			fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			Assert.assertEquals(1, errorList.size());
			Assert.assertEquals("ROT00013", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("Objectが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}
}
