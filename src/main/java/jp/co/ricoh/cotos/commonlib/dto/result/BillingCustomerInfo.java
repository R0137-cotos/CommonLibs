package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BillingCustomerInfo {

	@Schema(description = "得意先コード", required = true, allowableValues = "range[0,60]")
	private String originalSystemCode;

	@Schema(description = "得意先名称", required = true, allowableValues = "range[0,1326")
	private String customerName;

	@Schema(description = "サイト番号", required = true, allowableValues = "range[0,1326")
	private String customerSiteNumber;

	@Schema(description = "販社コード", required = true, allowableValues = "range[0,1326")
	private String hanshCd;

	@Schema(description = "住所1", required = true, allowableValues = "range[0,1326")
	private String address1;

	@Schema(description = "住所2", required = true, allowableValues = "range[0,1326")
	private String address2;

	@Schema(description = "郵便番号", required = true, allowableValues = "range[0,1326")
	private String postalCode;

	@Schema(description = "回収条件", required = true, allowableValues = "range[0,1326")
	private String standardTerms;

	@Schema(description = "回収方法", required = true, allowableValues = "range[0,1326")
	private String collectMethodName;

	@Schema(description = "バーチャル口座使用区分", required = true, allowableValues = "range[0,1326")
	private String virtualBnkAccountUseKbn;

	@Schema(description = "バーチャル銀行コード", required = true, allowableValues = "range[0,1326")
	private String virtualBnkCd;

	@Schema(description = "バーチャル本支店コード", required = true, allowableValues = "range[0,1326")
	private String virtualBnkBranchCd;

	@Schema(description = "バーチャル口座種別", required = true, allowableValues = "range[0,1326")
	private String virtualBankAccountType;

	@Schema(description = "バーチャル口座番号", required = true, allowableValues = "range[0,1326")
	private String virtualBankAccountNum;

	@Schema(description = "振込先自社口座_銀行コード(1)", required = true, allowableValues = "range[0,1326")
	private String bnkCd1;

	@Schema(description = "振込先自社口座_本支店コード(1)", required = true, allowableValues = "range[0,1326")
	private String bnkBranchCd1;

	@Schema(description = "振込先自社口座_口座番号(1)", required = true, allowableValues = "range[0,1326")
	private String bnkAccountCd1;

	@Schema(description = "振込先自社口座_銀行コード(2)", required = true, allowableValues = "range[0,1326")
	private String bnkCd2;

	@Schema(description = "振込先自社口座_支店コード(2)", required = true, allowableValues = "range[0,1326")
	private String bnkBranchCd2;

	@Schema(description = "振込先自社口座_口座番号(2)", required = true, allowableValues = "range[0,1326")
	private String bnkAccountCd2;

	@Schema(description = "振込先自社口座_口座種別(1)", required = true, allowableValues = "range[0,1326")
	private String bankAccountCls1;

	@Schema(description = "振込先自社口座_口座種別(2)", required = true, allowableValues = "range[0,1326")
	private String bankAccountCls2;

	@Schema(description = "売上課所コード", required = true, allowableValues = "range[0,1326")
	private String customerPersonSectionCode;

	@Schema(description = "自振口座_口座番号", required = true, allowableValues = "range[0,1326")
	private String bankAccountNum;

	@Schema(description = "自振口座_銀行番号", required = true, allowableValues = "range[0,1326")
	private String bankNumber;

	@Schema(description = "自振口座_銀行名", required = true, allowableValues = "range[0,1326")
	private String bankName;

	@Schema(description = "自振口座_支店番号", required = true, allowableValues = "range[0,1326")
	private String bankNum;

	@Schema(description = "自振口座_支店名", required = true, allowableValues = "range[0,1326")
	private String bankBranchName;

	@Schema(description = "自振口座_口座名義人カナ名", required = true, allowableValues = "range[0,1326")
	private String accountHolderNameAlt;

	@Schema(description = "事業所正式名称", required = true, allowableValues = "range[0,1326")
	private String ebsBusinessPlaceName;

	@Schema(description = "請求先事業所", required = true, allowableValues = "range[0,1326")
	private String billingCustomerAddress;

	@Schema(description = "自振引落日", required = true, allowableValues = "range[0,1326")
	private String autoTransferDay;

	@Schema(description = "請求先分類", required = true, allowableValues = "range[2,2]")
	private String susBnri;

}
