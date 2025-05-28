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
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 母店情報を表すエンティティ
 * 
 */

@Entity
@Table(name = "parent_distributor_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParentDistributorInfo extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_distributor_info_seq")
	@SequenceGenerator(name = "parent_distributor_info_seq", sequenceName = "parent_distributor_info_seq", allocationSize = 1)
	private long id;

	/**基本情報*/
	@ManyToOne(optional = false)
	@JoinColumn(name = "basic_contents_id", referencedColumnName = "id")
	@JsonIgnore
	private BasicContents basicContents;

	/**母店販売店コード*/
	@JsonProperty("distributorCd")
	private String parentDistributorCd;

	/**母店名*/
	@JsonProperty("distributorNm")
	private String parentDistributorNm;

	/**OE届け先コード*/
	@JsonProperty("oeDeliveryCd")
	private String parentOeDeliveryCd;

	/**Rings得意先コード*/
	@JsonProperty("ringsCustomerCd")
	private String parentRingsCustomerCd;

	/**取引先コード（手数料用）*/
	@JsonProperty("distributorRtcCd")
	private String parentDistributorRtcCd;

	/**MoM会社ID*/
	@JsonProperty("distributorMoMCmpId")
	private String distributorMomCmpId;

	/**MoM販売店識別コード*/
	@JsonProperty("distributorMoMShikiCd")
	private String distributorMomShikiCd;

	/**MoM組織ID*/
	@JsonProperty("distributorMoMSoshikiId")
	private String distributorMomSoshikiId;

	/**MoMデポコード*/
	@JsonProperty("distributorMoMDepoCd")
	private String distributorMomDepoCd;

}
