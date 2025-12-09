package jp.co.ricoh.cotos.commonlib.dto.result;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoDealerFlow;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import lombok.Data;

/**
 * 売上一覧をリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class SalesListResult {

	/**
	 * 連番
	 */
	@Id
	@Schema(description = "連番", required = true)
	private long seqNo;

	/**
	 * 売上年月
	 */
	@Schema(description = "売上年月", required = true)
	private String salesMonth;

	/**
	 * 注文日
	 */
	@Schema(description = "注文日", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationDate;

	/**
	 * 支店コード
	 */
	@Schema(description = "支店コード", required = true)
	private String salesCompanyCode;

	/**
	 * 支店名
	 */
	@Schema(description = "支店名", required = true)
	private String salesCompanyName;

	/**
	 * 担当SA所属名
	 */
	@Schema(description = "担当SA所属名", required = true)
	private String salesDepartmentName;

	/**
	 * 担当SA名
	 */
	@Schema(description = "担当SA名", required = true)
	private String employeeName;

	/**
	 * E/U：企業ID
	 */
	@Schema(description = "E/U：企業ID", required = true)
	private String companyId;

	/**
	 * E/U：企事部ID
	 */
	@Schema(description = "E/U：企事部ID", required = true)
	private String momCustId;

	/**
	 * E/U：上流システムコード
	 */
	@Schema(description = "E/U：上流システムコード", required = true)
	private String salesCompanyCustomerCode;

	/**
	 * E/U：お客様名
	 */
	@Schema(description = "E/U：お客様名", required = true)
	private String companyName;

	/**
	 * ベンダ名
	 */
	@Schema(description = "ベンダ名", required = false)
	private String vendorName;

	/**
	 * サービス名
	 */
	@Schema(description = "サービス名", required = true)
	private String productContractName;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別", required = true, allowableValues = "初期費(\"1\"), 月額定額(\"2\"), 年額(\"3\"), 月額従量(\"4\")", example = "1")
	private CostType cotosCostType;

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", required = false)
	private String rjManageNumber;

	/**
	 * ベンダ契約番号
	 */
	@Schema(description = "ベンダ契約番号", required = false)
	private String vendorManageNumber;

	/**
	 * 注文番号
	 */
	@Schema(description = "注文番号", required = true)
	private String contractNumber;

	/**
	 * 品種コード
	 */
	@Schema(description = "品種コード", required = true)
	private String itemCode;

	/**
	 * 商品名称
	 */
	@Schema(description = "商品名称", required = true)
	private String itemName;

	/**
	 * 数量
	 */
	@Schema(description = "数量", required = true)
	private Integer quantity;

	/**
	 * 売価単価
	 */
	@Schema(description = "売価単価", required = true)
	private BigDecimal salesCompanySalesPrice;

	/**
	 * 金額
	 */
	@Schema(description = "金額", required = true)
	private BigDecimal salesCompanySalesAmount;

	/**
	 * 販社仕切単価
	 */
	@Schema(description = "販社仕切単価", required = false)
	private BigDecimal ricohSalesPrice;

	/**
	 * リコー仕切単価
	 */
	@Schema(description = "リコー仕切単価", required = false)
	private BigDecimal ricohPartitionPrice;

	/**
	 * サービス開始日
	 */
	@Schema(description = "サービス開始日", required = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date serviceTermStart;

	/**
	 * 課金開始日
	 */
	@Schema(description = "課金開始日", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date billingStartDate;

	/**
	 * Web受付番号
	 */
	@Schema(description = "Web受付番号", required = false)
	private String webOrderNumber;

	/**
	 * COTOS商流区分
	 */
	@Schema(description = "COTOS商流区分", required = true, allowableValues = "1:直売  2:代売_接点店  3:代売_母店_接点店", example = "1")
	private String cotosDealerFlow;

	/**
	 * 接点店：企事部ID
	 */
	@Schema(description = "接点店：企事部ID", required = false)
	private String contactStoreMomCustId;

	/**
	 * 接点店名
	 */
	@Schema(description = "接点店名", required = false)
	private String contactStoreDealerName;

	/**
	 * 接点店：取引先コード（手数料用）
	 */
	@Schema(description = "接点店：取引先コード（手数料用）", required = false)
	private String contactStoreDistributorRtcCd;

	/**
	 * 接点店：販売店担当営業名
	 */
	@Schema(description = "接点店：販売店担当営業名", required = false)
	private String contactStorePicName;

	/**
	 * 接点店担当営業メールアドレス
	 */
	@Schema(description = "接点店担当営業メールアドレス", required = false)
	private String contactStoreDistributorEmployeeMailAddress;

	/**
	 * 接点店担当営業電話番号
	 */
	@Schema(description = "接点店担当営業電話番号", required = false)
	private String contactStorePicPhoneNumber;

	/**
	 * 接点店住所
	 */
	@Schema(description = "接点店住所", required = false)
	private String contactStoreAddress;

	/**
	 * 接点店手数料金額
	 */
	@Schema(description = "接点店手数料金額", required = false)
	private BigDecimal contactStoreCommissionAmount;

	/**
	 * 母店：企事部ID
	 */
	@Schema(description = "母店：企事部ID", required = false)
	private String motherStoreMomCustId;

	/**
	 * 母店名
	 */
	@Schema(description = "母店名", required = false)
	private String motherStoreDealerName;

	/**
	 * 母店：取引先コード（手数料用）
	 */
	@Schema(description = "母店：取引先コード（手数料用）", required = false)
	private String motherStoreDistributorRtcCd;

	/**
	 * 母店手数料金額
	 */
	@Schema(description = "母店手数料金額", required = false)
	private BigDecimal motherStoreCommissionAmount;

	/**
	 * 商品区分
	 */
	@Schema(description = "商品区分", required = false)
	private String productClassDiv;
}
