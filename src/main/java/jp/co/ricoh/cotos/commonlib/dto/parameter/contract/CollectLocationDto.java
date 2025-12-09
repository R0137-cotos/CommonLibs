package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "希望回収日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date collectPreferredDate;

	/**
	 * 担当者氏名
	 */
	@Size(max = 255)
	@Schema(description = "担当者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@Schema(description = "住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 担当者部署
	 */
	@Size(max = 255)
	@Schema(description = "担当者部署", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picDeptName;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 回収不要フラグ
	 * 【商品種類区分がBPSMの場合】保存時に契約情報更新APIで値が自動設定される
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "回収不要フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer collectUnnecessaryFlg;

	/**
	 * アップグレードRJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "アップグレードRJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String upgradeRjManageNumber;

	/**
	 * 連携状態
	 */
	@Schema(description = "連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "AP削除連携済(\"1\"), 解約連携済(\"2\")")
	private LinkageState linkageState;

}
