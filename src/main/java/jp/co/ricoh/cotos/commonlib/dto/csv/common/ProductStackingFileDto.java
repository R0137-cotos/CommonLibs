package jp.co.ricoh.cotos.commonlib.dto.csv.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "品種コード", "品種名", "数量", "単価（E/U売価）", "システム用項目１", "システム用項目２", "ver" })
public class ProductStackingFileDto {
	/**
	 * 品種コード
	 */
	@JsonProperty("品種コード")
	private String ricohItemCode;

	/**
	 * 品種名
	 */
	@JsonProperty("品種名")
	private String itemName;

	/**
	 * 数量
	 */
	@JsonProperty("数量")
	private String quantity;

	/**
	 * 単価（E/U売価）
	 */
	@JsonProperty("単価（E/U売価）")
	private String unitPrice;

	/**
	 * システム用項目１（単価（母店仕切））
	 */
	@JsonProperty("システム用項目１")
	private String rjDividingPrice;

	/**
	 * システム用項目２（単価（接点店仕切））
	 */
	@JsonProperty("システム用項目２")
	private String motherStorePrice;

	/**
	 * ファイルバージョン
	 */
	@JsonProperty("ver")
	private String importFileVersion;
}
