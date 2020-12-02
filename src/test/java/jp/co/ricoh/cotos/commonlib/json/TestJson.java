package jp.co.ricoh.cotos.commonlib.json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.logic.json.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJson {

	@Autowired
	JsonUtil jsonUtil;

	private String JSON_TEXT = "{\"organizationId\":\"ORG0000001\",\"vpn\":\"2\",\"rmaContractNumber\":\"RMA0000001\",\"rmaContractStart\":\"2020/11/01\",\"rmaContractEnd\":\"2020/10/31\",\"testObject\":{\"testKey1\":\"testValue1\",\"testKey2\":\"testValue2\",\"testArray\":[{\"arrayKey1\":\"arrayValue1\",\"arrayKey2\":\"arrayValue2\"},{\"arrayKey1\":\"arrayValue3\",\"arrayKey2\":\"arrayValue4\"}]}}";

	private String JSON_TEXT_ERROR = "{\"organizationId1\":\"ORG0000001\",\"vpn1\":\"2\",\"rmaContractNumber1\":\"RMA0000001\",\"rmaContractStart1\":\"2020/11/01\",\"rmaContractEnd1\":\"2020/10/31\",\"testObject\":{\"testKey1\":\"testValue1\",\"testKey2\":\"testValue2\",\"testArray\":[{\"arrayKey1\":\"arrayValue1\",\"arrayKey2\":\"arrayValue2\"},{\"arrayKey1\":\"arrayValue1\",\"arrayKey2\":\"arrayValue2\"}]}}";

	@Test
	public void 正常系_JSONテキストからオブジェクトに変換() {

		// JSONテキストからオブジェクトに変換
		TestJsonDto dto = jsonUtil.convertToDto(JSON_TEXT, TestJsonDto.class);

		// チェック
		assertNotNull(dto);
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
	public void 異常系_JSONテキストからオブジェクトに変換_JSONテキストなし() {

		// JSONテキストからオブジェクトに変換
		TestJsonDto dto = jsonUtil.convertToDto(null, TestJsonDto.class);

		// チェック
		assertNull(dto);
	}

	@Test
	public void 異常系_JSONテキストからオブジェクトに変換_クラスなし() {

		// JSONテキストからオブジェクトに変換
		TestJsonDto dto = jsonUtil.convertToDto(JSON_TEXT, null);

		// チェック
		assertNull(dto);
	}

	@Test
	public void 異常系_JSONテキストからDTOに変換_JSONテキスト_項目不一致() {

		// JSONテキストからオブジェクトに変換
		TestJsonDto dto = jsonUtil.convertToDto(JSON_TEXT_ERROR, null);

		// チェック
		assertNull(dto);
	}

	@Test
	public void 異常系_JSONテキストからDTOに変換_JSONテキスト_JSON以外() {

		// JSONテキストからオブジェクトに変換
		TestJsonDto dto = jsonUtil.convertToDto("no_json_text", null);

		// チェック
		assertNull(dto);
	}

	@Test
	public void 正常系_オブジェクトからJSONテキストに変換() {

		TestJsonDto dto = new TestJsonDto();

		dto.setOrganizationId("ORG0000001");
		dto.setVpn("2");
		dto.setRmaContractNumber("RMA0000001");
		dto.setRmaContractStart("2020/11/01");
		dto.setRmaContractEnd("2020/10/31");

		TestJsonDetailDto dtoDetail = new TestJsonDetailDto();

		dtoDetail.setTestKey1("testValue1");
		dtoDetail.setTestKey2("testValue2");

		List<TestJsonArrayDto> arrayDtoList = new ArrayList<>();

		TestJsonArrayDto arrayDto1 = new TestJsonArrayDto();

		arrayDto1.setArrayKey1("arrayValue1");
		arrayDto1.setArrayKey2("arrayValue2");

		arrayDtoList.add(arrayDto1);

		TestJsonArrayDto arrayDto2 = new TestJsonArrayDto();

		arrayDto2.setArrayKey1("arrayValue3");
		arrayDto2.setArrayKey2("arrayValue4");

		arrayDtoList.add(arrayDto2);

		dtoDetail.setTestJsonArrayList(arrayDtoList);

		dto.setTestJsonDetailDto(dtoDetail);

		// オブジェクトからJSONテキストに変換
		String jsonText = jsonUtil.convertToStr(dto);

		// チェック
		assertNotNull(jsonText);
		assertEquals(JSON_TEXT, jsonText);
	}

	@Test
	public void 正常系_オブジェクトからJSONテキストに変換_オブジェクト以外() {

		// オブジェクトからJSONテキストに変換
		String jsonText = jsonUtil.convertToStr("text");

		// チェック
		assertNotNull(jsonText);
		assertEquals("\"text\"", jsonText);
	}

	@Test
	public void 異常系_オブジェクトからJSONテキストに変換_オブジェクトなし() {

		// オブジェクトからJSONテキストに変換
		String jsonText = jsonUtil.convertToStr(null);

		// チェック
		assertNull(jsonText);
	}
}
