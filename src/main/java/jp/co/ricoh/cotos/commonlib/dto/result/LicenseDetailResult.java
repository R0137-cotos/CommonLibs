package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.HardSoftDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.InfoDiv;
import lombok.Data;

@Entity
@Data
public class LicenseDetailResult {

	/**
	 * ライセンス明細ID
	 */
	@Id
	@ApiModelProperty(value = "ライセンス明細ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * シーケンスNo
	 */
	@Min(0)
	@ApiModelProperty(value = "シーケンスNo", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long seqNumber;

	/**
	 * 情報区分
	 */
	@ApiModelProperty(value = "情報区分", required = false, allowableValues = "ハード(\"1\"), 減数(\"2\")", position = 4)
	private InfoDiv infoDiv;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = false, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "商品マスタID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * ハード・ソフト区分
	 */
	@ApiModelProperty(value = "ハード・ソフト区分", required = false, allowableValues = "ハード(\"1\"), ライセンス(\"2\")", position = 7)
	private HardSoftDiv hardSoftDiv;

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "機種コード", required = false, position = 8, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 9, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "取込フラグ", required = false, position = 10, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "取込日時", required = false, position = 11)
	private Date captureAt;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 12)
	private String extendsParameter;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種名", required = false, position = 13, allowableValues = "range[0,255]")
	private String itemName;
}
