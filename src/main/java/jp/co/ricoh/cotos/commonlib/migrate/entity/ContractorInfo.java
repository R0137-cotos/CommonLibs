package jp.co.ricoh.cotos.commonlib.migrate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 契約者情報を表すエンティティ
 * 
 */

@Entity
@Table(name = "contractor_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractorInfo extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contractor_info_seq")
	@SequenceGenerator(name = "contractor_info_seq", sequenceName = "contractor_info_seq", allocationSize = 1)
	private long id;

	/**基本情報*/
	@ManyToOne(optional = false)
	@JoinColumn(name = "basic_contents_id", referencedColumnName = "id")
	@JsonIgnore
	private BasicContents basicContents;

	/**企業ID*/
	private String corporationId;

	/**企事部ID*/
	private String corpId;

	/**得意先コード*/
	private String customerCd;

	/**会社名*/
	private String contractorCorpNm;

	/**契約者漢字（姓＋名）*/
	private String contractorNameKanji;

	/**契約者カナ（姓＋名）*/
	private String contractorNameKana;

	/**メールアドレス*/
	private String contractorMail;

	/**郵便番号*/
	private String contractorPostCd;

	/**事業所名*/
	private String contractorOfficeNm;

	/**住所*/
	private String contractorAddr;

	/**電話番号*/
	private String contractorTel;

}
