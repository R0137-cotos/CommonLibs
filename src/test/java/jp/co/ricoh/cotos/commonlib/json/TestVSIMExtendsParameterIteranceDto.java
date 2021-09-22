package jp.co.ricoh.cotos.commonlib.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.json.VSIMExtendsParameterIteranceDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestVSIMExtendsParameterIteranceDto {

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void コンバートされること() throws IOException {
		URL url = resourceLoader.getResource("classpath:json/vsimExtendsParameterIterance.json").getURL();
		StringBuffer requestbodyBuffer = new StringBuffer();
		Reader reader = new InputStreamReader(url.openStream());
		BufferedReader bufferedReader = new BufferedReader(reader);
		bufferedReader.lines().forEach(line -> requestbodyBuffer.append(line + System.getProperty("line.separator")));
		String json = requestbodyBuffer.toString();
		JsonNode node = mapper.readTree(json);
		JsonNode extendsParameterList = node.get("extendsParameterList");
		List<VSIMExtendsParameterIteranceDto> result = mapper.convertValue(extendsParameterList, new TypeReference<List<VSIMExtendsParameterIteranceDto>>() {
		});
		Assert.assertEquals("オブジェクトがコンバートされること", 2, result.size());
		result.stream().forEach(e -> {
			Assert.assertEquals("商品コードがコンバートされること", "productCode_" + e.getId(), e.getProductCode());
			Assert.assertEquals("商品名コンバートされること", "productName_" + e.getId(), e.getProductName());
			Assert.assertEquals("MNPコンバートされること", "mnpType_" + e.getId(), e.getMnpType());
			Assert.assertEquals("回線番号コンバートされること", "lineNumber_" + e.getId(), e.getLineNumber());
			Assert.assertEquals("ICCID(シリアル番号)コンバートされること", "serialNumber_" + e.getId(), e.getSerialNumber());
			Assert.assertEquals("IMEI (携帯シリアル番号)コンバートされること", "imeiNumber_" + e.getId(), e.getImeiNumber());
			Assert.assertEquals("送り状番号コンバートされること", "invoiceNumber_" + e.getId(), e.getInvoiceNumber());
			Assert.assertEquals("解約日コンバートされること", "cancelDate_" + e.getId(), e.getCancelDate());
			Assert.assertEquals("承諾番号コンバートされること", "mnpNumber_" + e.getId(), e.getMnpNumber());
		});
	}

}
