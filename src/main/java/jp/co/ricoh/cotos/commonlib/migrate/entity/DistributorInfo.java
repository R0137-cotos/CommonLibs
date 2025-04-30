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
 * 販売店情報を表すエンティティ
 * 
 */

@Entity
@Table(name = "distributor_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class DistributorInfo extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "distributor_info_seq")
	@SequenceGenerator(name = "distributor_info_seq", sequenceName = "distributor_info_seq", allocationSize = 1)
	private long id;

	/**基本情報*/
	@ManyToOne(optional = false)
	@JoinColumn(name = "basic_contents_id", referencedColumnName = "id")
	@JsonIgnore
	private BasicContents basicContents;

	/**販売店コード*/
	private String distributorCd;

	/**販売店名*/
	private String distributorNm;

	/**OE届け先コード*/
	private String oeDeliveryCd;

	/**販売店区分*/
	private String distributorCustomerType;

	/**販売店担当営業*/
	private String distributorEmployeeNm;

	/**販売店担当営業メールアドレス*/
	private String distributorEmployeeMailAddress;

	/**Rings得意先コード*/
	private String ringsCustomerCd;

	/**販売店郵便番号*/
	private String distributorZipCd;

	/**販売店住所*/
	private String distributorAddress;

	/**販売店電話番号*/
	private String distributorTel;

	/**販売店FAX番号*/
	private String distributorFax;

	/**取引先コード（手数料用）*/
	private String distributorRtcCd;

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
