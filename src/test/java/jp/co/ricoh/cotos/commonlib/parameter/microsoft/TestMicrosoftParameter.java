package jp.co.ricoh.cotos.commonlib.parameter.microsoft;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft.CustomerBillingProfileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft.CustomerCompanyProfileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft.CustomerDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft.DefaultAddressDto;

public class TestMicrosoftParameter {

	@Test
	public void 非nullの項目だけでJSONが作成されること() throws JsonProcessingException {

		CustomerDto customerDto = new CustomerDto();
		CustomerCompanyProfileDto companyProfileDto = new CustomerCompanyProfileDto();
		companyProfileDto.setDomain("domain");
		customerDto.setCompanyProfile(companyProfileDto);
		CustomerBillingProfileDto billingProfileDto = new CustomerBillingProfileDto();
		billingProfileDto.setEmail("email");
		billingProfileDto.setCulture("culture");
		billingProfileDto.setLanguage("language");
		billingProfileDto.setCompanyName("companyName");
		DefaultAddressDto defaultAddressDto = new DefaultAddressDto();
		defaultAddressDto.setCountry("country");
		defaultAddressDto.setCity("city");
		defaultAddressDto.setState("state");
		defaultAddressDto.setAddressLine1("addressLine1");
		defaultAddressDto.setAddressLine2("addressLine2");
		defaultAddressDto.setPostalCode("postalCode");
		defaultAddressDto.setFirstName("firstName");
		defaultAddressDto.setLastName("lastName");
		defaultAddressDto.setPhoneNumber("phoneNumber");
		billingProfileDto.setDefaultAddress(defaultAddressDto);
		customerDto.setBillingProfile(billingProfileDto);

		// JavaオブジェクトをJSONに変換
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(customerDto);
		} catch (JsonProcessingException e) {
			Assert.fail("例外が発生した");
		}

		String expected = "{\"companyProfile\":{\"domain\":\"domain\"},\"billingProfile\":{\"email\":\"email\",\"culture\":\"culture\",\"language\":\"language\",\"companyName\":\"companyName\",\"defaultAddress\":{\"country\":\"country\",\"city\":\"city\",\"state\":\"state\",\"addressLine1\":\"addressLine1\",\"addressLine2\":\"addressLine2\",\"postalCode\":\"postalCode\",\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"phoneNumber\":\"phoneNumber\"}}}";
		Assert.assertEquals("値を設定した項目だけでjson文字列が作成されること", expected, json);
	}

	@Test
	public void JSON文字列をDTOにマッピングできること_多重継承あり() throws JsonProcessingException {

		String json = "{\"id\":\"6ebdeebe-daf5-468f-9c10-762cb382d3da\",\"commerceId\":\"ec5d089a-a823-4e4b-b0df-8269082deb18\",\"companyProfile\":{\"tenantId\":\"6ebdeebe-daf5-468f-9c10-762cb382d3da\",\"domain\":\"utf8test001002.onmicrosoft.com\",\"companyName\":\"株式会社ＲＩＴＯＳ\",\"links\":{\"self\":{\"uri\":\"/customers/6ebdeebe-daf5-468f-9c10-762cb382d3da/profiles/company\",\"method\":\"GET\",\"headers\":[]}},\"attributes\":{\"objectType\":\"CustomerCompanyProfile\"}},\"billingProfile\":{\"id\":\"83e2aa9c-1967-5851-2199-72abf62fd4f5\",\"email\":\"qqqq@itos.com\",\"culture\":\"ja-JP\",\"language\":\"ja\",\"companyName\":\"株式会社ＲＩＴＯＳ\",\"defaultAddress\":{\"country\":\"JP\",\"region\":\"東京都\",\"city\":\"大田区\",\"state\":\"東京都\",\"addressLine1\":\"東京都大田区大森西３丁目８－１１\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"postalCode\":\"1430015\",\"firstName\":\"０1\",\"lastName\":\"テスト\",\"phoneNumber\":\"0123456789\"},\"links\":{\"self\":{\"uri\":\"/customers/6ebdeebe-daf5-468f-9c10-762cb382d3da/profiles/billing\",\"method\":\"GET\",\"headers\":[]}},\"attributes\":{\"etag\":\"3205030145379371967\",\"objectType\":\"CustomerBillingProfile\"}},\"relationshipToPartner\":\"reseller\",\"tags\":[],\"links\":{\"self\":{\"uri\":\"/customers/6ebdeebe-daf5-468f-9c10-762cb382d3da\",\"method\":\"GET\",\"headers\":[]}},\"attributes\":{\"objectType\":\"Customer\"},\"userCredentials\":{\"userName\":\"ユーザ\",\"password\":\"w8HSsQh3\"}}";

		CustomerDto customerDto = new CustomerDto();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			customerDto = mapper.readValue(json, CustomerDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertEquals("テナントIDが取得できること", customerDto.getId(), "6ebdeebe-daf5-468f-9c10-762cb382d3da");
		Assert.assertEquals("attributes.objectTypeが取得できること", customerDto.getAttributes().getObjectType(), "Customer");
		Assert.assertEquals("テナントIDが取得できること", customerDto.getCompanyProfile().getTenantId(), "6ebdeebe-daf5-468f-9c10-762cb382d3da");
		Assert.assertEquals("メールアドレスが取得できること", customerDto.getBillingProfile().getEmail(), "qqqq@itos.com");
		Assert.assertEquals("国名コードが取得できること", customerDto.getBillingProfile().getDefaultAddress().getCountry(), "JP");
		Assert.assertEquals("ユーザ名が取得できること", customerDto.getUserCredentials().getUserName(), "ユーザ");
		Assert.assertNull("HTTPステータスが設定されていないこと", customerDto.getHttpStatus());
		Assert.assertNull("エラーメッセージが設定されていないこと", customerDto.getHttpStatus());
	}
}
