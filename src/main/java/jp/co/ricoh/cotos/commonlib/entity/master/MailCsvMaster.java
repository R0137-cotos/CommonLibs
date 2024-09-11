package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
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
	@ApiModelProperty(value = "CSVメール制御マスタID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * メール件名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メール件名", required = false, position = 2, allowableValues = "range[0,255]")
	private String mailSubject;

	/**
	 * メール本文
	 */
	@Lob
	@ApiModelProperty(value = "メール本文", required = false, position = 3)
	private String mailBody;

	/**
	 * CSVファイル名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "CSVファイル名", required = false, position = 4, allowableValues = "range[0,255]")
	private String csvFileName;

	/**
	 * fromメールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "fromメールアドレス", required = false, position = 5, allowableValues = "range[0,255]")
	private String fromMailAddress;

	/**
	 * TO
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "TO", required = false, position = 6, allowableValues = "range[0,255]")
	private String toMailAddress;

	/**
	 * CC
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "CC", required = false, position = 7, allowableValues = "range[0,255]")
	private String ccMailAddress;

	/**
	 * BCC
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "BCC", required = false, position = 8, allowableValues = "range[0,255]")
	private String bccMailAddress;

	/**
	 * CSVメール制御タイプ区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "CSVメール制御タイプ区分", required = false, position = 9, allowableValues = "range[0,255]")
	private String csvMailTypeDiv;

}
