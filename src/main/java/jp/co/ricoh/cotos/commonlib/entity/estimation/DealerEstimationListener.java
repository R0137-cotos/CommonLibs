package jp.co.ricoh.cotos.commonlib.entity.estimation;

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
public class DealerEstimationListener {

	private static String HJN_KAKU_ITEM_CD = "JMC-HJN_KAKU_CD";

	private static VKjbMasterRepository vKjbMasterRepository;
	private static CheckUtil checkUtil;
	private static FindCommonMaster findCommonMaster;

	@Autowired
	private VKjbMasterUtil vKjbMasterUtil;

	@Autowired
	public void setKjbMasterRepository(VKjbMasterRepository kjbMasterRepository) {
		DealerEstimationListener.vKjbMasterRepository = kjbMasterRepository;
	}

	@Autowired
	public void setCheckUtil(CheckUtil checkUtil) {
		DealerEstimationListener.checkUtil = checkUtil;
	}

	@Autowired
	public void setFindCommonMaster(FindCommonMaster findCommonMaster) {
		DealerEstimationListener.findCommonMaster = findCommonMaster;
	}

	/**
	 * 企事部マスタ情報を販売店（見積用）トランザクションに紐づけます。
	 *
	 * @param dealerEstimation
	 */
	@PrePersist
	@Transactional
	public void appendsEstimationDealerFields(DealerEstimation dealerEstimation) {
		if (StringUtils.isNotBlank(dealerEstimation.getMomKjbSystemId())) {
			VKjbMaster vKjbMaster = vKjbMasterUtil.specifyVKjbMaster(dealerEstimation, "販売店（見積用）");
			// 企業IDで企事部マスタが特定された場合、システム連携IDを企事部マスタに合わせて変更する
			if (StringUtils.equals(dealerEstimation.getMomKjbSystemId(), vKjbMaster.getMclMomRelId())) {
				dealerEstimation.setMomKjbSystemId(vKjbMaster.getMclMomRelId());
			}

			// 結合して表示するものを設定
			// 値が設定されていない場合のみ補完する
			if (StringUtils.isBlank(dealerEstimation.getDealerName()))
				dealerEstimation.setDealerName(this.convertJoinedDealerName(vKjbMaster));
			if (StringUtils.isBlank(dealerEstimation.getAddress()))
				dealerEstimation.setAddress(this.convertJoinedAddress(vKjbMaster));

			if (StringUtils.isBlank(dealerEstimation.getPostNumber()))
				dealerEstimation.setPostNumber(vKjbMaster.getJgsJgsPostNum());
			if (StringUtils.isBlank(dealerEstimation.getOrgPhoneNumber()))
				dealerEstimation.setOrgPhoneNumber(vKjbMaster.getJgsJgsTelNum());

			if (StringUtils.isBlank(dealerEstimation.getDistributorMomCmpId()))
				dealerEstimation.setDistributorMomCmpId("999999");

		}
	}

	/**
	 * 企事部マスタ情報を販売店（見積用）トランザクションに紐づけます。
	 *
	 * @param dealerEstimation
	 */
	@PreUpdate
	@Transactional
	public void updateEstimationDealerFields(DealerEstimation dealerEstimation) {
		if (StringUtils.isNotBlank(dealerEstimation.getMomKjbSystemId())) {
			if (StringUtils.isBlank(dealerEstimation.getDistributorMomCmpId()))
				dealerEstimation.setDistributorMomCmpId("999999");
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
