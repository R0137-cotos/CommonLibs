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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "メールマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メールテンプレートマスタID
	 */
	@Min(0)
	@Schema(description = "メールテンプレートマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long mailTemplateMasterId;

	/**
	 * メールタイプ区分
	 */
	@Size(max = 255)
	@Schema(description = "メールタイプ区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mailTypeDiv;

	/**
	 * メールアドレスマスタリスト
	 */
	@Valid
	@OneToMany(mappedBy = "mailMaster")
	@OrderBy("id ASC")
	@Schema(description = "メールアドレスマスタリスト", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<MailAddressMaster> mailAddressMasterList;

	/**
	 * サブメールマスタID
	 */
	@Min(0)
	@Schema(description = "サブメールマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long subMailMasterId;

	/**
	 * サブメール送信条件区分
	 */
	@Size(max = 255)
	@Schema(description = "サブメール送信条件区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subMailTypeDiv;

	/**
	 * サブメール送信条件値
	 */
	@Size(max = 255)
	@Schema(description = "サブメール送信条件値", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subMailCondition;
}
