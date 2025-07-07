package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import java.math.BigDecimal;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductMaster;
import lombok.Data;

/**
 * 商品積上げ中間Dto
 */
@Data
public class ProductStackingMiddleDto {

	/**
	 * 商品コード
	 */
	private String productMasterId;

	/**
	 * 品種コード
	 */
	private String ricohItemCode;

	/**
	 * 品種名
	 */
	private String ricohItemName;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 単価（E/U売価）
	 */
	private BigDecimal unitPrice;

	/**
	 * 単価（母店仕切）
	 */
	private BigDecimal rjDividingPrice;

	/**
	 * 単価（接点店仕切）
	 */
	private BigDecimal motherStorePrice;

	/**
	 * ファイルバージョン
	 */
	private Long importFileVersion;

	/**
	 * 商品マスタ
	 */
	private ProductMaster productMaster;

	/**
	 * 品種マスタ
	 */
	private ItemMaster itemMaster;

}
