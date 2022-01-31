package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseDetailDto extends DtoBase {

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "取込フラグ", required = false, position = 3, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "取込日時", required = false, position = 4)
	private Date captureAt;

	/**
	 * リクエスト作成状態
	 */
	@ApiModelProperty(value = "リクエスト作成状態", required = false, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")", position = 5)
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "リクエスト作成日時", required = false, position = 6)
	private Date requestCreateDate;

}
