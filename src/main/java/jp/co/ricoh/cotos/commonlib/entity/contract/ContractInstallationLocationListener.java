package jp.co.ricoh.cotos.commonlib.entity.contract;

import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.DummyCodeValue;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster.DepartmentDiv;
import jp.co.ricoh.cotos.commonlib.entity.util.VKjbMasterUtil;

@Component
public class ContractInstallationLocationListener {

	private static VKjbMasterUtil vKjbMasterUtil;

	@Autowired
	public void setVKjbMasterUtil(VKjbMasterUtil vKjbMasterUtil) {
		ContractInstallationLocationListener.vKjbMasterUtil = vKjbMasterUtil;
	}

	/**
	 * 顧客マスタ情報を設置先(契約用)トランザクションに紐づけます。
	 *
	 * @param contractInstallationLocation
	 */
	@PrePersist
	@Transactional
	public void appendsCustomerEstimationFields(ContractInstallationLocation contractInstallationLocation) {

		if (StringUtils.equals(DummyCodeValue.Dummy_Mcl_MoM_Rel_Id.toString(), contractInstallationLocation.getMomKjbSystemId())) {
			return;
		}

		VKjbMaster vKjbMaster = vKjbMasterUtil.specifyVKjbMaster(contractInstallationLocation, "設置先(契約用)");
		// 企業IDで企事部マスタが特定された場合、システム連携IDを企事部マスタに合わせて変更する
		if (!StringUtils.equals(contractInstallationLocation.getMomKjbSystemId(), vKjbMaster.getMclMomRelId())) {
			contractInstallationLocation.setMomKjbSystemId(vKjbMaster.getMclMomRelId());
		}

		// 結合して表示するものを設定
		// 値が設定されていない場合のみ補完する
		if (StringUtils.isBlank(contractInstallationLocation.getCompanyName()))
			contractInstallationLocation.setCompanyName(vKjbMaster.getKgyKgyNmKnji()); // 画面からは法人格付きの会社名が送られてくる

		if (StringUtils.isBlank(contractInstallationLocation.getCustomerName()))
			contractInstallationLocation.setCustomerName(this.convertJoinedCustomerName(vKjbMaster, contractInstallationLocation));

		if (StringUtils.isBlank(contractInstallationLocation.getAddress()))
			contractInstallationLocation.setAddress(this.convertJoinedAddress(vKjbMaster));

		// 企事部設定区分により設定値を振り分け
		if (DepartmentDiv.企事部.equals(vKjbMaster.getPrflKjbSetKbn())) {
			if (StringUtils.isBlank(contractInstallationLocation.getPhoneNumber()))
				contractInstallationLocation.setPhoneNumber(vKjbMaster.getBmnBmnTelNum());
			if (StringUtils.isBlank(contractInstallationLocation.getFaxNumber()))
				contractInstallationLocation.setFaxNumber(vKjbMaster.getBmnBmnFaxNum());
		} else {
			if (StringUtils.isBlank(contractInstallationLocation.getPhoneNumber()))
				contractInstallationLocation.setPhoneNumber(vKjbMaster.getJgsJgsTelNum());
			if (StringUtils.isBlank(contractInstallationLocation.getFaxNumber()))
				contractInstallationLocation.setFaxNumber(vKjbMaster.getJgsJgsFaxNum());
		}

		if (null == contractInstallationLocation.getDepartmentDiv())
			contractInstallationLocation.setDepartmentDiv(vKjbMaster.getPrflKjbSetKbn());
		if (StringUtils.isBlank(contractInstallationLocation.getCompanyId()))
			contractInstallationLocation.setCompanyId(vKjbMaster.getPrflMomKgyId());
		if (StringUtils.isBlank(contractInstallationLocation.getOfficeId()))
			contractInstallationLocation.setOfficeId(vKjbMaster.getPrflMomJgsId());
		if (StringUtils.isBlank(contractInstallationLocation.getOfficeName()))
			contractInstallationLocation.setOfficeName(vKjbMaster.getJgsJgsNmKnji());
		if (StringUtils.isBlank(contractInstallationLocation.getMomCustId()))
			contractInstallationLocation.setMomCustId(vKjbMaster.getMclMomKjbId());
		if (StringUtils.isBlank(contractInstallationLocation.getPostNumber()))
			contractInstallationLocation.setPostNumber(vKjbMaster.getJgsJgsPostNum());
		if (StringUtils.isBlank(contractInstallationLocation.getCompanyName()))
			contractInstallationLocation.setCompanyName(vKjbMaster.getKgyKgyNmKnji());
		if (StringUtils.isBlank(contractInstallationLocation.getDepartmentName()))
			contractInstallationLocation.setDepartmentName(vKjbMaster.getBmnBmnNmKnji());
	}

	private String convertJoinedCustomerName(VKjbMaster kjbMaster, ContractInstallationLocation contractInstallationLocation) {

		StringBuilder sb = new StringBuilder();

		sb.append(StringUtils.defaultIfEmpty(contractInstallationLocation.getCompanyName(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getJgsJgsNmKnji(), StringUtils.EMPTY));

		if (DepartmentDiv.企事部.equals(kjbMaster.getPrflKjbSetKbn())) {
			sb.append(StringUtils.defaultIfEmpty(kjbMaster.getBmnBmnNmKnji(), StringUtils.EMPTY));
		}

		return sb.toString();
	}

	private String convertJoinedAddress(VKjbMaster kjbMaster) {
		StringBuilder sb = new StringBuilder();

		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getAdsJtdhknNmKnji(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getAdsJskugnchosnKnji(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getAdsJowaTusyoKnji(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getAdsJkowChomeKnji(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getJgsJgsAdsAzatusyoNm(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getJgsJgsAdsBantiNm(), StringUtils.EMPTY));
		if (!StringUtils.isEmpty(kjbMaster.getJgsJgsAdsGoNm())) {
			sb.append("－" + kjbMaster.getJgsJgsAdsGoNm());
		}
		if (!StringUtils.isEmpty(kjbMaster.getJgsJgsAdsBldgNm())) {
			sb.append("　" + kjbMaster.getJgsJgsAdsBldgNm());
		}
		if (!StringUtils.isEmpty(kjbMaster.getJgsJgsAdsFlorNm())) {
			sb.append("　" + kjbMaster.getJgsJgsAdsFlorNm());
		}

		return sb.toString();
	}
}
