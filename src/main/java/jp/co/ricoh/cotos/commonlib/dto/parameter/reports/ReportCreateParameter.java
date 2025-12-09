package jp.co.ricoh.cotos.commonlib.dto.parameter.reports;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.result.ReportSpecificResult;
import lombok.Data;

/**
 * 帳票作成パラメーター
 */
@Data
public class ReportCreateParameter {

	/**
	 * 出力ファイル名
	 */
	@Size(max = 255)
	@Schema(description = "出力ファイル名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String outputFileName;

	/**
	 * 帳票テンプレート特定結果
	 */
	@Valid
	@Schema(description = "帳票テンプレート特定結果", requiredMode = Schema.RequiredMode.REQUIRED)
	private ReportSpecificResult reportSpecificResult;

	/**
	 * 帳票データ部マッピングリスト（ページ毎）
	 */
	@NotNull
	@Schema(description = "帳票データ部マッピングリスト", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<Map<String, List<Object>>> dataMapList;

	/**
	 * CreateFormワークディレクトリ(絶対パス指定)
	 * 設定例）/sharestorage/reports/template/CreateForm/EstimationMediation
	 *
	 * 帳票テンプレート管理マスタの出力形式=2:Excel、かつExcel帳票出力区分=1:CreateFormの場合のみ設定必須
	 * 呼び出し元のymlに設定しておき、帳票出力APIコール時に本パラメーターに設定すること
	 */
	@Schema(description = "CreateFormワークディレクトリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String workAbsoluteDir;

	/**
	 * PDF出力フラグ
	 *
	 * 帳票テンプレート管理マスタの出力形式=2:Excel の場合のみ有効
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "PDF出力フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer pdfOutputFlg;
}
