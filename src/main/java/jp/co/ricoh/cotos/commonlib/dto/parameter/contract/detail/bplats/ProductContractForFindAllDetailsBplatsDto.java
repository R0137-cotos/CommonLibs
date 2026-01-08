package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品（契約用）を表す契約一覧情報詳細取得API用DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ProductContractForFindAllDetailsBplatsDto extends EntityBase {

	/**
	 * ID
	 */
	@Min(0)
	@Schema(description = "契約ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 商品名
	 */
	@Column(nullable = false)
	@Schema(description = "商品名(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String productContractName;

	/**
	 * 代表品種マスタID
	 */
	@Min(0)
	@Schema(description = "代表品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long repItemMasterId;

	/**
	 * サービス識別番号
	 */
	@Column(nullable = false)
	@Schema(description = "サービス識別番号(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String serviceIdentNumber;

	/**
	 * Bplats連携対象外
	 */
	@Schema(description = "Bplats連携対象外", requiredMode = Schema.RequiredMode.NOT_REQUIRED, readOnly = true)
	private Boolean extendBplatsLink;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * ベンダー管理番号名称
	 */
	@Size(max = 255)
	@Schema(description = "ベンダー管理番号名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorManageNumberName;

	/**
	 * サービス提供会社名
	 */
	@Size(max = 255)
	@Schema(description = "サービス提供会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceProviderCompanyName;

}
