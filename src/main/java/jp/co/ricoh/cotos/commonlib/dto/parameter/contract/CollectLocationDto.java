package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.CollectLocation.LinkageState;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CollectLocationDto extends DtoBase {

	/**
	 * 希望回収日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "希望回収日", required = false, position = 2)
	private Date collectPreferredDate;

	/**
	 * 担当者氏名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名", required = false, position = 3, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "郵便番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "住所", required = false, position = 5, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 6, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "事業所名", required = false, position = 7, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 担当者部署
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者部署", required = false, position = 8, allowableValues = "range[0,255]")
	private String picDeptName;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "電話番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 回収不要フラグ
	 * 【商品種類区分がBPSMの場合】保存時に契約情報更新APIで値が自動設定される
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "回収不要フラグ", required = false, position = 10, allowableValues = "range[0,9]")
	private Integer collectUnnecessaryFlg;

	/**
	 * アップグレードRJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アップグレードRJ管理番号", required = false, position = 11, allowableValues = "range[0,255]")
	private String upgradeRjManageNumber;

	/**
	 * 連携状態
	 */
	@ApiModelProperty(value = "連携状態", required = false, position = 12, allowableValues = "AP削除連携済(\"1\"), 解約連携済(\"2\")")
	private LinkageState linkageState;

}
