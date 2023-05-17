package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.MigrationDiv;
import lombok.Data;

/**
 * 商品（見積用）拡張項目移行用項目DTO（標準）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEstimationMigrationParameterDto {
	/**
	 * 移行区分
	 */
	private MigrationDiv migrationDiv;

	/**
	 * PC台数
	 */
	private Integer pcAmount ;
}
