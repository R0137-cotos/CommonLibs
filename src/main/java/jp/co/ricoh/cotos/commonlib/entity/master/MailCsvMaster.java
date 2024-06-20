package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CSVメール制御マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "mail_csv_master")
public class MailCsvMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_csv_master_seq")
	@SequenceGenerator(name = "mail_csv_master_seq", sequenceName = "mail_csv_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "CSVメール制御マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メール件名
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "メール件名", required = true, position = 2, allowableValues = "range[0,255]")
	private String mailSubject;

	/**
	 * メール本文
	 */
	@Column(nullable = false)
	@Lob
	@ApiModelProperty(value = "メール本文", required = true, position = 3)
	private String mailBody;

	/**
	 * CSVファイル名
	 */
	@Column(nullable = false)
	@Lob
	@ApiModelProperty(value = "CSVファイル名", required = true, position = 4)
	private String csvFileName;

	/**
	 * fromメールアドレス
	 */
	@Column
	@ApiModelProperty(value = "fromメールアドレス", required = true, position = 5)
	private String fromMailAddress;

	/**
	 * TO
	 */
	@Column
	@ApiModelProperty(value = "TO", required = true, position = 6)
	private String to;

	/**
	 * CC
	 */
	@Column
	@ApiModelProperty(value = "CC", required = true, position = 7)
	private String cc;

	/**
	 * BCC
	 */
	@Column
	@ApiModelProperty(value = "BCC", required = true, position = 8)
	private String bcc;

	/**
	 * CSVメール制御タイプ区分
	 */
	@Column
	@ApiModelProperty(value = "CSVメール制御タイプ区分", required = true, position = 9)
	private String csvMailTypeDiv;

}
