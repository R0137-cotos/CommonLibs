package jp.co.ricoh.cotos.commonlib.dto.parameter.communication;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContactDto extends DtoBase {

	/**
	 * 見積ID
	 */
	@Min(0)
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long estimationId;

	/**
	 * 子問い合わせリスト
	 */
	@Valid
	@Schema(description = "子問い合わせリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContactDto> children;

	/**
	 * 送信者MoM社員ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "送信者MoM社員ID (作成時不要)", allowableValues = "range[0,255]", readOnly = true)
	private String contactFromEmpId;

	/**
	 * サービスカテゴリ
	 */
	@Schema(description = "サービスカテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1")
	private ServiceCategory serviceCategory;

	/**
	 * タイトル
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "タイトル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String title;

	/**
	 * 内容
	 */
	@NotNull
	@Schema(description = "内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String content;

	/**
	 * 送信日時
	 */
	@Schema(description = "送信日時", requiredMode = Schema.RequiredMode.REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendAt;

	/**
	 * 送信者氏名
	 */
	@Size(max = 255)
	@Schema(description = "送信者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactFromEmpName;

	/**
	 * 宛先
	 */
	@NotNull
	@Valid
	@Schema(description = "宛先", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ContactToDto> contactToList;
}