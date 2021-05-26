package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * ヤマト便作業対象を検索するためのキー項目クラスを表します。
 */
@Data
public class YamatoSearchParameter {

	/**
	 * 商品マスタID
	 */
	@ApiParam(value = "商品マスタID", required = false)
	@ApiModelProperty(value = "商品マスタID", required = false)
	private Long productMasterId;

	/**
	 * お問い合わせ番号
	 */
	@ApiParam(value = "お問い合わせ番号", required = false)
	@ApiModelProperty(value = "お問い合わせ番号", required = false, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * 契約番号
	 */
	@ApiParam(value = "契約番号", required = false)
	@ApiModelProperty(value = "契約番号", required = false, allowableValues = "range[0,15]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@ApiParam(value = "契約番号枝番", required = false)
	@ApiModelProperty(value = "契約番号枝番", required = false, allowableValues = "range[0,2]")
	private String contractBranchNumber;

	/**
	 * 業務受付日時
	 */
	@ApiParam(value = "業務受付日時", required = false)
	@ApiModelProperty(value = "業務受付日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date businessAcceptDateTime;

	/**
	 * アプリケーションIDリスト
	 */
	@ApiParam(value = "アプリケーションIDリスト", required = false)
	@ApiModelProperty(value = "アプリケーションIDリスト", required = false)
	private List<String> appId;

	/**
	 * 他システムデータ排他フラグ
	 */
	@ApiParam(value = "他システムデータ排他フラグ", required = false)
	@ApiModelProperty(value = "他システムデータ排他フラグ", required = false)
	private int otherSysDataExcludeFlg;

	/**
	 * パラメータをMapにする。
	 */
	public Map<String, Object> createParamaterMap() {
		Map<String, Object> retMap = new HashMap<>();

		FieldUtils.getAllFieldsList(this.getClass()).stream().filter(putField -> !putField.getName().startsWith("$")).forEach(field -> {
			try {
				retMap.put(field.getName(), field.get(this));
			} catch (IllegalAccessException e) {
				retMap.put(field.getName(), null);
			}
		});

		return retMap;
	}

}