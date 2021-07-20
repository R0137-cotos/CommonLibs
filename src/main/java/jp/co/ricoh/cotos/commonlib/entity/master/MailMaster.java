package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

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
}
