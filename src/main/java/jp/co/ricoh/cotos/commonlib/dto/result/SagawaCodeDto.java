package jp.co.ricoh.cotos.commonlib.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 佐川コードのDTO
 */
@Data
@JsonPropertyOrder({ "郵便番号７桁", "都道府県名", "市区町村名", "町域名", "JIS５桁", "HP記載", "最終便", })
public class SagawaCodeDto {

	/**
	 * 郵便番号７桁
	 */
	@JsonProperty("郵便番号７桁")
	@Schema(description = "郵便番号７桁", required = true, example = "郵便番号７桁")
	private String postNumber;

	/**
	 * 都道府県名
	 */
	@JsonProperty("都道府県名")
	@Schema(description = "都道府県名", required = true, example = "都道府県名")
	private String prefecturesName;

	/**
	 * 市区町村名
	 */
	@JsonProperty("市区町村名")
	@Schema(description = "市区町村名", required = true, example = "市区町村名")
	private String cityName;

	/**
	 * 町域名
	 */
	@JsonProperty("町域名")
	@Schema(description = "町域名", required = true, example = "町域名")
	private String townAreaName;

	/**
	 * JIS５桁
	 */
	@JsonProperty("JIS５桁")
	@Schema(description = "JIS５桁", required = true, example = "JIS５桁")
	private String jis;

	/**
	 * HP記載
	 */
	@JsonProperty("HP記載")
	@Schema(description = "HP記載", required = true, example = "HP記載")
	private String hp;

	/**
	 * 最終便
	 */
	@JsonProperty("最終便")
	@Schema(description = "最終便", required = true, example = "最終便")
	private String lastFlight;
}
