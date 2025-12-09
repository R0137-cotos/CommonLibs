package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 汎用マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "common_master")
public class CommonMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_master_seq")
	@SequenceGenerator(name = "common_master_seq", sequenceName = "common_master_seq", allocationSize = 1)
	@Schema(description = "汎用マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * マスタ名称
	 */
	@Column(nullable = false)
	@Schema(description = "マスタ名称", required = true, allowableValues = "range[0,255]")
	private String articleName;

	/**
	 * カラム名
	 */
	@Column(nullable = false)
	@Schema(description = "カラム名", required = true, allowableValues = "range[0,255]")
	private String columnName;

	/**
	 * サービスカテゴリ
	 */
	@Column(nullable = false)
	@Schema(description = "サービスカテゴリ", required = true, allowableValues = "共通(\"0\"), 見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1")
	private ServiceCategory serviceCategory;

	/**
	 * マスタ説明
	 */
	@Schema(description = "マスタ説明", required = false, allowableValues = "range[0,255]")
	private String description;

	/**
	 * 汎用マスタ明細リスト
	 */
	@OneToMany(mappedBy = "commonMaster")
	@Schema(description = "汎用マスタ明細", required = false)
	private List<CommonMasterDetail> commonMasterDetailList;

	/**
	 * 削除フラグ
	 */
	@Column(nullable = false)
	@Schema(description = "削除フラグ", required = true, allowableValues = "range[0,1]")
	private String deleteFlg;
}
