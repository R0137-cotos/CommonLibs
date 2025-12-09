package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JsonSchemaMasterParameter {

	/**
	 * 商品マスタID
	 */
	@Schema(description = "商品マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long productMasterId;

	/**
	 * 見積種別
	 */
	@Size(max = 255)
	@Schema(description = "見積種別:契約種別未設定時のみ設定可能", required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\")")
	private String estimationType;

	/**
	 * 契約種別
	 */
	@Size(max = 255)
	@Schema(description = "契約種別:見積種別未設定時のみ設定可能", required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private String contractType;

	/**
	 * ライフサイクル状態
	 */
	@Size(max = 255)
	@Schema(description = "ライフサイクル状態", required = false, allowableValues = "見積種別有：作成中(\"1\"), 作成完了(\"2\"), 受注(\"3\"), 失注(\"4\"), 破棄(\"5\")、契約種別有：作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\"), 締結待ち(\"11\")")
	private String lifecycleStatus;
}