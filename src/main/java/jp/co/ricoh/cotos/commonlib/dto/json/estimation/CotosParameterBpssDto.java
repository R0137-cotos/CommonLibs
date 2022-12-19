package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（見積用）拡張項目COTOS商品固有項目DTO（BPN・BPS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CotosParameterBpssDto {

	/**
	 * 追加メール削除フラグ
	 */
	//private AddMailDeleteFlg addMailDeleteFlg;

	/**
	 * 追加メール残数
	 */
	private String addMailRemainingNumber;

	/**
	 * 追加メールリスト
	 */
	@JsonProperty("addMail")
	private List<ParameterEstimationAddMailDto> addMailList;
}
