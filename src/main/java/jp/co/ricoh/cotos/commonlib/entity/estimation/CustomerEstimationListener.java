package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.dto.parameter.common.MomCommonMasterSearchParameter;
import jp.co.ricoh.cotos.commonlib.dto.result.CommonMasterResult;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DummyCodeValue;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster.DepartmentDiv;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.logic.findcommonmaster.FindCommonMaster;
import jp.co.ricoh.cotos.commonlib.provider.ApplicationContextProvider;
import jp.co.ricoh.cotos.commonlib.provider.UtilProvider;
import jp.co.ricoh.cotos.commonlib.repository.master.VKjbMasterRepository;

@Component
public class CustomerEstimationListener {

	private static String HJN_KAKU_ITEM_CD = "JMC-HJN_KAKU_CD";

	private static VKjbMasterRepository vKjbMasterRepository;
	private static CheckUtil checkUtil;
	private static FindCommonMaster findCommonMaster;

	/**
	 * 顧客マスタ情報を顧客(見積用)トランザクションに紐づけます。
	 *
	 * @param customerEstimation
	 */
	@PrePersist
	@Transactional
	public void appendsCustomerEstimationFields(CustomerEstimation customerEstimation) {
		// Beanの取得
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		vKjbMasterRepository = context.getBean(VKjbMasterRepository.class);
		checkUtil = UtilProvider.getCheckUtil();
		CustomerEstimationListener.findCommonMaster = new FindCommonMaster();

		if (DummyCodeValue.Dummy_Mcl_MoM_Rel_Id.toString().equals(customerEstimation.getMomKjbSystemId())) {
			return;
		}

		VKjbMaster vKjbMaster = vKjbMasterRepository.findByMclMomRelId(customerEstimation.getMomKjbSystemId());
		if (vKjbMaster == null) {
			String[] regexList = { "顧客（見積用）" };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistKjbMaster", regexList));
		}

		// 結合して表示するものを設定
		// 値が設定されていない場合のみ補完する
		if (StringUtils.isBlank(customerEstimation.getCompanyName())) // 画面からは法人格付きの会社名が送られてくる
			customerEstimation.setCompanyName(this.convertJoinedCompanyName(vKjbMaster));

		if (StringUtils.isBlank(customerEstimation.getCustomerName()))
			customerEstimation.setCustomerName(this.convertJoinedCustomerName(vKjbMaster, customerEstimation));
		if (StringUtils.isBlank(customerEstimation.getAddress()))
			customerEstimation.setAddress(this.convertJoinedAddress(vKjbMaster));

		if (StringUtils.isBlank(customerEstimation.getPhoneNumber()))
			customerEstimation.setPhoneNumber(vKjbMaster.getJgsJgsTelNum());
		if (StringUtils.isBlank(customerEstimation.getFaxNumber()))
			customerEstimation.setFaxNumber(vKjbMaster.getJgsJgsFaxNum());

		if (null == customerEstimation.getDepartmentDiv())
			customerEstimation.setDepartmentDiv(vKjbMaster.getPrflKjbSetKbn());
		if (StringUtils.isBlank(customerEstimation.getCompanyId()))
			customerEstimation.setCompanyId(vKjbMaster.getPrflMomKgyId());
		if (StringUtils.isBlank(customerEstimation.getOfficeId()))
			customerEstimation.setOfficeId(vKjbMaster.getPrflMomJgsId());
		if (StringUtils.isBlank(customerEstimation.getOfficeName()))
			customerEstimation.setOfficeName(vKjbMaster.getJgsJgsNmKnji());
		if (StringUtils.isBlank(customerEstimation.getMomCustId()))
			customerEstimation.setMomCustId(vKjbMaster.getMclMomKjbId());
		if (StringUtils.isBlank(customerEstimation.getPostNumber()))
			customerEstimation.setPostNumber(vKjbMaster.getJgsJgsPostNum());
		if (StringUtils.isBlank(customerEstimation.getCompanyName()))
			customerEstimation.setCompanyName(vKjbMaster.getKgyKgyNmKnji());
		if (StringUtils.isBlank(customerEstimation.getDepartmentName()))
			customerEstimation.setDepartmentName(vKjbMaster.getBmnBmnNmKnji());
	}

	/**
	 * 顧客マスタ情報を顧客(見積用)トランザクションに紐づけます。
	 *
	 * @param customerEstimation
	 */
	@PreUpdate
	@Transactional
	public void updateCustomerEstimationFields(CustomerEstimation customerEstimation) {
		customerEstimation.setCustomerName(this.convertJoinedCustomerNameUpdate(customerEstimation));
	}

	private String convertJoinedCompanyName(VKjbMaster kjbMaster) {
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getKgyKgyNmKnji(), StringUtils.EMPTY));

		MomCommonMasterSearchParameter parameter = new MomCommonMasterSearchParameter();
		parameter.setCommonArticleCdList(Arrays.asList(HJN_KAKU_ITEM_CD));
		List<CommonMasterResult> commonMasterList = findCommonMaster.findMomCommonMaster(parameter);
		commonMasterList.stream().forEach(commonMasterResult -> {
			commonMasterResult.getCommonMasterDetailResultList().stream().forEach(commonMasterDetailResult -> {
				if (commonMasterDetailResult.getCodeValue().equals(kjbMaster.getKgyHjnKakuCd())) {
					if ("1".equals(kjbMaster.getKgyHjnKakuZengoCd())) {
						sb.setLength(0);
						sb.append(StringUtils.defaultIfEmpty(commonMasterDetailResult.getDataArea1(), StringUtils.EMPTY));
						sb.append(StringUtils.defaultIfEmpty(kjbMaster.getKgyKgyNmKnji(), StringUtils.EMPTY));
					} else if ("2".equals(kjbMaster.getKgyHjnKakuZengoCd())) {
						sb.setLength(0);
						sb.append(StringUtils.defaultIfEmpty(kjbMaster.getKgyKgyNmKnji(), StringUtils.EMPTY));
						sb.append(StringUtils.defaultIfEmpty(commonMasterDetailResult.getDataArea1(), StringUtils.EMPTY));
					}
				}
			});
		});

		return sb.toString();
	}

	private String convertJoinedCustomerName(VKjbMaster kjbMaster, CustomerEstimation customerEstimation) {

		StringBuilder sb = new StringBuilder();

		sb.append(StringUtils.defaultIfEmpty(customerEstimation.getCompanyName(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(kjbMaster.getJgsJgsNmKnji(), StringUtils.EMPTY));

		if (DepartmentDiv.企事部.equals(kjbMaster.getPrflKjbSetKbn())) {
			sb.append(StringUtils.defaultIfEmpty(kjbMaster.getBmnBmnNmKnji(), StringUtils.EMPTY));
		}

		return sb.toString();
	}

	private String convertJoinedCustomerNameUpdate(CustomerEstimation customerEstimation) {

		StringBuilder sb = new StringBuilder();

		sb.append(StringUtils.defaultIfEmpty(customerEstimation.getCompanyName(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(customerEstimation.getOfficeName(), StringUtils.EMPTY));

		if (DepartmentDiv.企事部.equals(customerEstimation.getDepartmentDiv())) {
			sb.append(StringUtils.defaultIfEmpty(customerEstimation.getDepartmentName(), StringUtils.EMPTY));
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
