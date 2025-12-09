package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 固定帳票情報マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ledger_master")
public class LedgerMaster extends EntityBaseMaster {

	/**
	 * 固定帳票情報マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ledger_master_seq")
	@SequenceGenerator(name = "ledger_master_seq", sequenceName = "ledger_master_seq", allocationSize = 1)
	@Schema(description = "固定帳票情報マスタID", required = true)
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@Schema(description = "商品マスタID", required = true)
	private long productMasterId;

	/**
	 * ファイル名
	 */
	@Column(nullable = false)
	@Schema(description = "ファイル名", required = true, allowableValues = "range[0,]")
	private String fileName;

	/**
	 * ファイルパス
	 */
	@Column(nullable = false)
	@Schema(description = "ファイルパス", required = true, allowableValues = "range[0,]")
	private String filePath;

}
