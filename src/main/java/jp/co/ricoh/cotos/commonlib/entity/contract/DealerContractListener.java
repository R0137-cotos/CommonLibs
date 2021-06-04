package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Arrays;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.dto.parameter.common.MomCommonMasterSearchParameter;
import jp.co.ricoh.cotos.commonlib.dto.result.CommonMasterResult;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster;
import jp.co.ricoh.cotos.commonlib.entity.util.VKjbMasterUtil;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.logic.findcommonmaster.FindCommonMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.VKjbMasterRepository;

@Component
public class DealerContractListener {

	private static String HJN_KAKU_ITEM_CD = "JMC-HJN_KAKU_CD";

	private static CheckUtil checkUtil;
	private static VKjbMasterUtil vKjbMasterUtil;
	private static FindCommonMaster findCommonMaster;

	private static VKjbMasterRepository vKjbMasterRepository;

	@Autowired
	public void setVkjbMasterRepository(VKjbMasterRepository vKjbMasterRepository) {
		DealerContractListener.vKjbMasterRepository = vKjbMasterRepository;
	}

	@Autowired
	public void setCheckUtil(CheckUtil checkUtil) {
		DealerContractListener.checkUtil = checkUtil;
	}

	@Autowired
	public void setVKjbMasterUtil(VKjbMasterUtil vKjbMasterUtil) {
		DealerContractListener.vKjbMasterUtil = vKjbMasterUtil;
	}

	@Autowired
	public void setFindCommonMaster(FindCommonMaster findCommonMaster) {
		DealerContractListener.findCommonMaster = findCommonMaster;
	}

	@PrePersist
	@Transactional
	public void appendsDealerContractFields(DealerContract dealerContract) {

		if (StringUtils.isNotBlank(dealerContract.getMomKjbSystemId())) {
			VKjbMaster vKjbMaster = vKjbMasterUtil.specifyVKjbMaster(dealerContract, "販売店（契約用）");
			// 企業IDで企事部マスタが特定された場合、システム連携IDを企事部マスタに合わせて変更する
			if (!StringUtils.equals(dealerContract.getMomKjbSystemId(), vKjbMaster.getMclMomRelId())) {
				dealerContract.setMomKjbSystemId(vKjbMaster.getMclMomRelId());
			}

			// 結合して表示するものを設定
			// 値が設定されていない場合のみ補完する
			if (StringUtils.isBlank(dealerContract.getDealerName()))
				dealerContract.setDealerName(this.convertJoinedDealerName(vKjbMaster));
			if (StringUtils.isBlank(dealerContract.getAddress()))
				dealerContract.setAddress(this.convertJoinedAddress(vKjbMaster));

			if (StringUtils.isBlank(dealerContract.getPostNumber()))
				dealerContract.setPostNumber(vKjbMaster.getJgsJgsPostNum());
			if (StringUtils.isBlank(dealerContract.getOrgPhoneNumber()))
				dealerContract.setOrgPhoneNumber(vKjbMaster.getJgsJgsTelNum());

			if (StringUtils.isBlank(dealerContract.getDistributorMomCmpId()))
				dealerContract.setDistributorMomCmpId("999999");
		}
	}

	@PreUpdate
	@Transactional
	public void updateDealerContractFields(DealerContract dealerContract) {
		if (StringUtils.isNotBlank(dealerContract.getMomKjbSystemId())) {
			if (StringUtils.isBlank(dealerContract.getDistributorMomCmpId()))
				dealerContract.setDistributorMomCmpId("999999");
		}
	}

	private String convertJoinedDealerName(VKjbMaster kjbMaster) {

		StringBuilder sb = new StringBuilder();

		MomCommonMasterSearchParameter parameter = new MomCommonMasterSearchParameter();
		parameter.setCommonArticleCdList(Arrays.asList(HJN_KAKU_ITEM_CD));
		List<CommonMasterResult> commonMasterList = findCommonMaster.findMomCommonMaster(parameter);
		commonMasterList.stream().forEach(commonMasterResult -> {
			commonMasterResult.getCommonMasterDetailResultList().stream().forEach(commonMasterDetailResult -> {
				if (commonMasterDetailResult.getCodeValue().equals(kjbMaster.getKgyHjnKakuCd())) {
					if ("1".equals(kjbMaster.getKgyHjnKakuZengoCd())) {
						sb.append(StringUtils.defaultIfEmpty(commonMasterDetailResult.getDataArea1(), StringUtils.EMPTY));
						sb.append(StringUtils.defaultIfEmpty(kjbMaster.getKgyKgyNmKnji(), StringUtils.EMPTY));
					} else if ("2".equals(kjbMaster.getKgyHjnKakuZengoCd())) {
						sb.append(StringUtils.defaultIfEmpty(kjbMaster.getKgyKgyNmKnji(), StringUtils.EMPTY));
						sb.append(StringUtils.defaultIfEmpty(commonMasterDetailResult.getDataArea1(), StringUtils.EMPTY));
					}
				}
			});
		});

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
