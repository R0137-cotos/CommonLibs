package jp.co.ricoh.cotos.commonlib.parameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft.CustomerCompanyProfileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft.CustomerDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft.Link;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft.StandardResorceLinks;

public class TestParameter {

	@Test
	public void 非nullの項目だけでJSONが作成されること() throws JsonProcessingException {

		CustomerDto customerDto = new CustomerDto();

		customerDto.setId(null);
		customerDto.setCommerceId(null);
		CustomerCompanyProfileDto companyProfileDto = new CustomerCompanyProfileDto();
		companyProfileDto.setDomain("domain");
		customerDto.setCompanyProfile(companyProfileDto);
		customerDto.setBillingProfile(null);
		customerDto.setRelationshipToPartner(null);
		customerDto.setAllowDelegatedAccess(true);
		customerDto.setUserCredentials(null);
		List<String> customDomains = new ArrayList<String>();
		customDomains.add("test1");
		customDomains.add("test2");
		customDomains.add("test3");
		customerDto.setCustomDomains(customDomains);
		customerDto.setAssociatedPartnerId(null);
		StandardResorceLinks standardResorceLinks = new StandardResorceLinks();
		Link link = new Link();
		link.setUri("uri");
		standardResorceLinks.setSelf(link);
		customerDto.setLinks(standardResorceLinks);

		// JavaオブジェクトをJSONに変換
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(customerDto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String expected = "{\"links\":{\"self\":{\"uri\":\"uri\"}},\"companyProfile\":{\"domain\":\"domain\"},\"allowDelegatedAccess\":true,\"customDomains\":[\"test1\",\"test2\",\"test3\"]}";
		Assert.assertEquals(expected, json);
	}

	@Test
	public void JSON文字列をDTOにマッピングできること_多重継承あり() throws JsonProcessingException {

		String json = "{\"id\":\"6ebdeebe-daf5-468f-9c10-762cb382d3da\",\"commerceId\":\"ec5d089a-a823-4e4b-b0df-8269082deb18\",\"companyProfile\":{\"tenantId\":\"6ebdeebe-daf5-468f-9c10-762cb382d3da\",\"domain\":\"utf8test001002.onmicrosoft.com\",\"companyName\":\"株式会社ＲＩＴＯＳ\",\"links\":{\"self\":{\"uri\":\"/customers/6ebdeebe-daf5-468f-9c10-762cb382d3da/profiles/company\",\"method\":\"GET\",\"headers\":[]}},\"attributes\":{\"objectType\":\"CustomerCompanyProfile\"}},\"billingProfile\":{\"id\":\"83e2aa9c-1967-5851-2199-72abf62fd4f5\",\"email\":\"qqqq@itos.com\",\"culture\":\"ja-JP\",\"language\":\"ja\",\"companyName\":\"株式会社ＲＩＴＯＳ\",\"defaultAddress\":{\"country\":\"JP\",\"region\":\"東京都\",\"city\":\"大田区\",\"state\":\"東京都\",\"addressLine1\":\"東京都大田区大森西３丁目８－１１\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"postalCode\":\"1430015\",\"firstName\":\"０1\",\"lastName\":\"テスト\",\"phoneNumber\":\"0123456789\"},\"links\":{\"self\":{\"uri\":\"/customers/6ebdeebe-daf5-468f-9c10-762cb382d3da/profiles/billing\",\"method\":\"GET\",\"headers\":[]}},\"attributes\":{\"etag\":\"3205030145379371967\",\"objectType\":\"CustomerBillingProfile\"}},\"relationshipToPartner\":\"reseller\",\"tags\":[],\"links\":{\"self\":{\"uri\":\"/customers/6ebdeebe-daf5-468f-9c10-762cb382d3da\",\"method\":\"GET\",\"headers\":[]}},\"attributes\":{\"objectType\":\"Customer\"}}";

		CustomerDto customerDto = new CustomerDto();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			customerDto = mapper.readValue(json, CustomerDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(customerDto.getId(), "6ebdeebe-daf5-468f-9c10-762cb382d3da");
	}
}
