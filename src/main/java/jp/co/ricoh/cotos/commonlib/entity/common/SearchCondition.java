package jp.co.ricoh.cotos.commonlib.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 検索条件を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "search_condition")
public class SearchCondition extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "search_condition_seq")
	@SequenceGenerator(name = "search_condition_seq", sequenceName = "search_condition_seq", allocationSize = 1)
	@Schema(description = "検索条件ID (作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * MoM社員ID
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "MoM社員ID", required = true, allowableValues = "range[0,255]")
	private String momEmployeeId;

	/**
	 * ドメイン
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "ドメイン", required = true, allowableValues = "range[0,255]")
	private String domain;

	/**
	 * タイトル
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "タイトル", required = true, allowableValues = "range[0,255]")
	private String title;

	/**
	 * 検索条件
	 */
	@Column(nullable = false)
	@NotNull
	@Lob
	@Schema(description = "検索条件", required = true)
	private String searchCondition;

}
