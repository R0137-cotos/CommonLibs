package jp.co.ricoh.cotos.commonlib.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestJsonArrayDto {

	private String arrayKey1;

	private String arrayKey2;
}
