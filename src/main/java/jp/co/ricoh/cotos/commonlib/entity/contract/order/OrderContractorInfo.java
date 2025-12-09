package jp.co.ricoh.cotos.commonlib.entity.contract.order;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文顧客情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_contractor_info")
public class OrderContractorInfo extends EntityBase {

/**
 * ID
 */
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_contractor_info_seq")
@SequenceGenerator(name = "order_contractor_info_seq", sequenceName = "order_contractor_info_seq", allocationSize = 1)
@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
private long id;

/**
 * 注文基本情報ID
 */
@OneToOne(optional = false)
@JoinColumn(name = "order_basic_info_id", referencedColumnName = "id")
@JsonIgnore
private OrderBasicInfo orderBasicInfo;

/**
 * 企業ＩＤ
 */
@Column
@Schema(description = "企業ＩＤ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String companyId;

/**
 * 企事部ＩＤ
 */
@Column
@Schema(description = "企事部ＩＤ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String kjbId;

/**
 * 会員基本ID
 */
@Column
@Schema(description = "会員基本ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String netricohAccount;

/**
 * 得意先コード
 */
@Column
@Schema(description = "得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String customerCd;

/**
 * 会社名
 */
@Column
@Schema(description = "会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String contractorCompanyName;

/**
 * 担当者漢字（姓＋名）
 */
@Column
@Schema(description = "担当者漢字（姓＋名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String contractorNameKanji;

/**
 * 担当者カナ（姓＋名）
 */
@Column
@Schema(description = "担当者カナ（姓＋名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String contractorNameKana;

/**
 * 担当者メールアドレス
 */
@Column
@Schema(description = "担当者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String contractorMailAddress;

/**
 * 郵便番号
 */
@Column
@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String contractorPostNumber;

/**
 * 事業所名
 */
@Column
@Schema(description = "事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String contractorOfficeName;

/**
 * 住所
 */
@Column
@Schema(description = "住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String contractorAddress;

/**
 * 電話番号
 */
@Column
@Schema(description = "電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
private String contractorPhoneNumber;

/**
 * 利用登録権限(NetRICOH)
 */
@Column
@Schema(description = "利用登録権限(NetRICOH)", allowableValues = "range[0,]")
private String authorityForNetricoh;

/**
 * サービス開始希望日
 */
@Column
@Temporal(TemporalType.DATE)
@Schema(description = "サービス開始希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
private Date desiredServiceStartDate;

}

