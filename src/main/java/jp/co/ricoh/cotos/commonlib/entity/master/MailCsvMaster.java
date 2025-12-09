package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "CSVメール制御マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * メール件名
	 */
	@Size(max = 255)
	@Schema(description = "メール件名", required = false, allowableValues = "range[0,255]")
	private String mailSubject;

	/**
	 * メール本文
	 */
	@Lob
	@Schema(description = "メール本文", required = false)
	private String mailBody;

	/**
	 * CSVファイル名
	 */
	@Size(max = 255)
	@Schema(description = "CSVファイル名", required = false, allowableValues = "range[0,255]")
	private String csvFileName;

	/**
	 * fromメールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "fromメールアドレス", required = false, allowableValues = "range[0,255]")
	private String fromMailAddress;

	/**
	 * TO
	 */
	@Size(max = 255)
	@Schema(description = "TO", required = false, allowableValues = "range[0,255]")
	private String toMailAddress;

	/**
	 * CC
	 */
	@Size(max = 255)
	@Schema(description = "CC", required = false, allowableValues = "range[0,255]")
	private String ccMailAddress;

	/**
	 * BCC
	 */
	@Size(max = 255)
	@Schema(description = "BCC", required = false, allowableValues = "range[0,255]")
	private String bccMailAddress;

	/**
	 * CSVメール制御タイプ区分
	 */
	@Size(max = 255)
	@Schema(description = "CSVメール制御タイプ区分", required = false, allowableValues = "range[0,255]")
	private String csvMailTypeDiv;

}
