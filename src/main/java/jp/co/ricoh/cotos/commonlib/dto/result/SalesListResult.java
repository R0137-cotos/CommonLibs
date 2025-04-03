package jp.co.ricoh.cotos.commonlib.dto.result;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "連番", required = true, position = 1)
	private long seqNo;

	/**
	 * 売上年月
	 */
	@ApiModelProperty(value = "売上年月", required = true, position = 2)
	private String salesMonth;

	/**
	 * 注文日
	 */
	@ApiModelProperty(value = "注文日", required = false, position = 3)
	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationDate;

	/**
	 * 支店コード
	 */
	@ApiModelProperty(value = "支店コード", required = true, position = 4)
	private String salesCompanyCode;

	/**
	 * 支店名
	 */
	@ApiModelProperty(value = "支店名", required = true, position = 5)
	private String salesCompanyName;

	/**
	 * 担当SA所属名
	 */
	@ApiModelProperty(value = "担当SA所属名", required = true, position = 6)
	private String salesDepartmentName;

	/**
	 * 担当SA名
	 */
	@ApiModelProperty(value = "担当SA名", required = true, position = 7)
	private String employeeName;

	/**
	 * E/U：企業ID
	 */
	@ApiModelProperty(value = "E/U：企業ID", required = true, position = 8)
	private String companyId;

	/**
	 * E/U：企事部ID
	 */
	@ApiModelProperty(value = "E/U：企事部ID", required = true, position = 9)
	private String momCustId;

	/**
	 * E/U：上流システムコード
	 */
	@ApiModelProperty(value = "E/U：上流システムコード", required = true, position = 10)
	private String salesCompanyCustomerCode;

	/**
	 * E/U：お客様名
	 */
	@ApiModelProperty(value = "E/U：お客様名", required = true, position = 11)
	private String companyName;

	/**
	 * ベンダ名
	 */
	@ApiModelProperty(value = "ベンダ名", required = false, position = 12)
	private String vendorName;

	/**
	 * サービス名
	 */
	@ApiModelProperty(value = "サービス名", required = true, position = 13)
	private String productContractName;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別", required = true, allowableValues = "初期費(\"1\"), 月額定額(\"2\"), 年額(\"3\"), 月額従量(\"4\")", example = "1", position = 14)
	private CostType cotosCostType;

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 15)
	private String rjManageNumber;

	/**
	 * ベンダ契約番号
	 */
	@ApiModelProperty(value = "ベンダ契約番号", required = false, position = 16)
	private String vendorManageNumber;

	/**
	 * 注文番号
	 */
	@ApiModelProperty(value = "注文番号", required = true, position = 17)
	private String contractNumber;

	/**
	 * 品種コード
	 */
	@ApiModelProperty(value = "品種コード", required = true, position = 18)
	private String itemCode;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称", required = true, position = 19)
	private String itemName;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量", required = true, position = 20)
	private Integer quantity;

	/**
	 * 売価単価
	 */
	@ApiModelProperty(value = "売価単価", required = true, position = 21)
	private BigDecimal salesCompanySalesPrice;

	/**
	 * 金額
	 */
	@ApiModelProperty(value = "金額", required = true, position = 22)
	private BigDecimal salesCompanySalesAmount;

	/**
	 * 販社仕切単価
	 */
	@ApiModelProperty(value = "販社仕切単価", required = false, position = 23)
	private BigDecimal ricohSalesPrice;

	/**
	 * リコー仕切単価
	 */
	@ApiModelProperty(value = "リコー仕切単価", required = false, position = 24)
	private BigDecimal ricohPartitionPrice;

	/**
	 * サービス開始日
	 */
	@ApiModelProperty(value = "サービス開始日", required = true, position = 25)
	@Temporal(TemporalType.TIMESTAMP)
	private Date serviceTermStart;

	/**
	 * 課金開始日
	 */
	@ApiModelProperty(value = "課金開始日", required = false, position = 26)
	@Temporal(TemporalType.TIMESTAMP)
	private Date billingStartDate;

	/**
	 * Web受付番号
	 */
	@ApiModelProperty(value = "Web受付番号", required = false, position = 27)
	private String webOrderNumber;

	/**
	 * COTOS商流区分
	 */
	@ApiModelProperty(value = "COTOS商流区分", required = true, allowableValues = "1:直売  2:代売_接点店  3:代売_母店_接点店", example = "1", position = 28)
	private String cotosDealerFlow;

	/**
	 * 接点店：企事部ID
	 */
	@ApiModelProperty(value = "接点店：企事部ID", required = false, position = 29)
	private String contactStoreMomCustId;

	/**
	 * 接点店名
	 */
	@ApiModelProperty(value = "接点店名", required = false, position = 30)
	private String contactStoreDealerName;

	/**
	 * 接点店：取引先コード（手数料用）
	 */
	@ApiModelProperty(value = "接点店：取引先コード（手数料用）", required = false, position = 31)
	private String contactStoreDistributorRtcCd;

	/**
	 * 接点店：販売店担当営業名
	 */
	@ApiModelProperty(value = "接点店：販売店担当営業名", required = false, position = 32)
	private String contactStorePicName;

	/**
	 * 接点店担当営業メールアドレス
	 */
	@ApiModelProperty(value = "接点店担当営業メールアドレス", required = false, position = 33)
	private String contactStoreDistributorEmployeeMailAddress;

	/**
	 * 接点店担当営業電話番号
	 */
	@ApiModelProperty(value = "接点店担当営業電話番号", required = false, position = 34)
	private String contactStorePicPhoneNumber;

	/**
	 * 接点店住所
	 */
	@ApiModelProperty(value = "接点店住所", required = false, position = 35)
	private String contactStoreAddress;

	/**
	 * 接点店手数料金額
	 */
	@ApiModelProperty(value = "接点店手数料金額", required = false, position = 36)
	private BigDecimal contactStoreCommissionAmount;

	/**
	 * 母店：企事部ID
	 */
	@ApiModelProperty(value = "母店：企事部ID", required = false, position = 37)
	private String motherStoreMomCustId;

	/**
	 * 母店名
	 */
	@ApiModelProperty(value = "母店名", required = false, position = 38)
	private String motherStoreDealerName;

	/**
	 * 母店：取引先コード（手数料用）
	 */
	@ApiModelProperty(value = "母店：取引先コード（手数料用）", required = false, position = 39)
	private String motherStoreDistributorRtcCd;

	/**
	 * 母店手数料金額
	 */
	@ApiModelProperty(value = "母店手数料金額", required = false, position = 40)
	private BigDecimal motherStoreCommissionAmount;

	/**
	 * 商品区分
	 */
	@ApiModelProperty(value = "商品区分", required = false, position = 41)
	private String productClassDiv;
}
