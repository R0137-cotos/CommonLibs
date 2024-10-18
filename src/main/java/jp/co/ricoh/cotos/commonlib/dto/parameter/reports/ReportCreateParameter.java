package jp.co.ricoh.cotos.commonlib.dto.parameter.reports;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "出力ファイル名", required = true, allowableValues = "range[0,255]", position = 1)
	private String outputFileName;

	/**
	 * 帳票テンプレート特定結果
	 */
	@Valid
	@ApiModelProperty(value = "帳票テンプレート特定結果", required = true, position = 2)
	private ReportSpecificResult reportSpecificResult;

	/**
	 * 帳票データ部マッピングリスト（ページ毎）
	 */
	@NotNull
	@ApiModelProperty(value = "帳票データ部マッピングリスト", required = true, position = 3)
	private List<Map<String, List<Object>>> dataMapList;

	/**
	 * CreateFormワークディレクトリ(絶対パス指定)
	 * 設定例）/sharestorage/reports/template/CreateForm/EstimationMediation
	 *
	 * 帳票テンプレート管理マスタの出力形式=2:Excel、かつExcel帳票出力区分=1:CreateFormの場合のみ設定必須
	 * 呼び出し元のymlに設定しておき、帳票出力APIコール時に本パラメーターに設定すること
	 */
	@ApiModelProperty(value = "CreateFormワークディレクトリ", required = false, position = 4)
	private String workAbsoluteDir;

	/**
	 * PDF出力フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "PDF出力フラグ", required = false, position = 5)
	private Integer pdfOutputFlg;
}
