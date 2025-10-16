package jp.co.ricoh.cotos.commonlib.dto.csv.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jp.co.ricoh.cotos.commonlib.logic.csv.EmptyToNullDeserializer;
import lombok.Data;

@Data
@JsonPropertyOrder({ "商品コード", "品種コード", "品種名", "数量", "単価（E/U売価）", "システム用項目１", "システム用項目２", "ver" })
public class ProductStackingFileDto {
	/**
	 * 商品コード
	 */
	@JsonDeserialize(using = EmptyToNullDeserializer.class)
	@JsonProperty("商品コード")
	private String productMasterId;

	/**
	 * 品種コード
	 */
	@JsonDeserialize(using = EmptyToNullDeserializer.class)
	@JsonProperty("品種コード")
	private String ricohItemCode;

	/**
	 * 品種名
	 */
	@JsonDeserialize(using = EmptyToNullDeserializer.class)
	@JsonProperty("品種名")
	private String itemName;

	/**
	 * 数量
	 */
	@JsonDeserialize(using = EmptyToNullDeserializer.class)
	@JsonProperty("数量")
	private String quantity;

	/**
	 * 単価（E/U売価）
	 */
	@JsonDeserialize(using = EmptyToNullDeserializer.class)
	@JsonProperty("単価（E/U売価）")
	private String unitPrice;

	/**
	 * システム用項目１（単価（母店仕切））
	 */
	@JsonDeserialize(using = EmptyToNullDeserializer.class)
	@JsonProperty("システム用項目１")
	private String rjDividingPrice;

	/**
	 * システム用項目２（単価（接点店仕切））
	 */
	@JsonDeserialize(using = EmptyToNullDeserializer.class)
	@JsonProperty("システム用項目２")
	private String motherStorePrice;

	/**
	 * ファイルバージョン
	 */
	@JsonDeserialize(using = EmptyToNullDeserializer.class)
	@JsonProperty("ver")
	private String importFileVersion;
}
