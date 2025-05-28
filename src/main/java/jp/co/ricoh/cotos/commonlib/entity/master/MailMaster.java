package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "mail_master")
public class MailMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_master_seq")
	@SequenceGenerator(name = "mail_master_seq", sequenceName = "mail_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "メールマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メールテンプレートマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "メールテンプレートマスタID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long mailTemplateMasterId;

	/**
	 * メールタイプ区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールタイプ区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String mailTypeDiv;

	/**
	 * メールアドレスマスタリスト
	 */
	@Valid
	@OneToMany(mappedBy = "mailMaster")
	@OrderBy("id ASC")
	@ApiModelProperty(value = "メールアドレスマスタリスト", required = true, position = 4)
	private List<MailAddressMaster> mailAddressMasterList;

	/**
	 * サブメールマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "サブメールマスタID", required = false, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long subMailMasterId;

	/**
	 * サブメール送信条件区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブメール送信条件区分", required = false, position = 6, allowableValues = "range[0,255]")
	private String subMailTypeDiv;

	/**
	 * サブメール送信条件値
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブメール送信条件値", required = false, position = 7, allowableValues = "range[0,255]")
	private String subMailCondition;
}
