package jp.co.ricoh.cotos.commonlib.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestJsonDetailDto {

	private String testKey1;

	private String testKey2;

	@JsonProperty("testArray")
	private List<TestJsonArrayDto> testJsonArrayList;

}
