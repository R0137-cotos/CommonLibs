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
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商材固有セクションマスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "specific_section_master")
public class SpecificSectionMaster extends EntityBase {

	/**
	 * 商材固有セクションマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specific_section_master_seq")
	@SequenceGenerator(name = "specific_section_master_seq", sequenceName = "specific_section_master_seq", allocationSize = 1)
	@Schema(description = "商材固有セクションマスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商材固有セクション名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "商材固有セクション名", required = true, allowableValues = "range[0,255]")
	private String specificSectionName;

	/**
	 * 商材固有セクション区分
	 */
	@Size(max = 255)
	@Schema(description = "商材固有セクション区分", required = false, allowableValues = "range[0,255]")
	private String specificSectionDiv;

	/**
	 * 商材固有項目マスタリスト
	 */
	@OneToMany(mappedBy = "specificSectionMaster")
	@JsonIgnore
	@Schema(description = "商材固有項目マスタ", required = true)
	private List<SpecificControlMaster> specificControlMaster;

}
