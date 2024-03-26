package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.BillingMethod;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.ContractYears;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（MITS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueMitsDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * 請求方法
	 */
	private BillingMethod billingMethod;

	/**
	 * 契約年数
	 */
	private ContractYears contractYears;

	/**
	 * サービス利用希望日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date conclusionPreferredDate;

}