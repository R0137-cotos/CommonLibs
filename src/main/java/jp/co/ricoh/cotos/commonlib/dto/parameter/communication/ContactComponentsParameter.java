package jp.co.ricoh.cotos.commonlib.dto.parameter.communication;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.TargetDirectionType;
import jp.co.ricoh.cotos.commonlib.entity.communication.Contact;
import lombok.Data;

/**
 * 問い合わせ情報を作成するためのパラメーターを表します。
 */
@Data
public class ContactComponentsParameter {

	/**
	 * 親問い合わせエンティティ
	 */
	@Parameter(description = "親問い合わせエンティティ")
	private Contact parentContact;

	/**
	 * 問い合わせタイトル
	 */
	@NotNull
	@Size(max = 255)
	@Parameter(description = "問い合わせタイトル", schema = @Schema(allowableValues = "range[0,255]"))
	private String contactTitle;

	/**
	 * 問い合わせ内容
	 */
	@NotNull
	@Parameter(description = "問い合わせ内容", schema = @Schema(allowableValues = "range[0,255]"))
	private String contactMessage;

	/**
	 * 宛先種別
	 */
	@NotNull
	@Parameter(description = "宛先種別", schema = @Schema(allowableValues = "担当CE(\"1\"), 担当SA(\"2\"), 全担当者(\"98\"), その他(\"99\");"))
	private TargetDirectionType targetDirectionType;

	/**
	 * メールTO送付先MoM社員IDリスト
	 */
	@Parameter(description = "メールTO送付先MoM社員IDリスト")
	private List<String> momEmpIdSendToList;

	/**
	 * メールCC送付先MoM社員IDリスト
	 */
	@Parameter(description = "メールCC送付先MoM社員IDリスト")
	private List<String> momEmpIdSendCcList;
}
