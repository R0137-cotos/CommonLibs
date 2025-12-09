package jp.co.ricoh.cotos.commonlib.entity.util;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.common.CustomerAbstractEntity;
import jp.co.ricoh.cotos.commonlib.entity.common.DealerAbstractEntity;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.master.VKjbMasterRepository;

/**
 * 企事部マスタ用共通処理
 */
@Component
public class VKjbMasterUtil {

	private static VKjbMasterRepository vKjbMasterRepository;

	private static CheckUtil checkUtil;

	@Autowired
	public void setVkjbMasterRepository(VKjbMasterRepository vKjbMasterRepository) {
		VKjbMasterUtil.vKjbMasterRepository = vKjbMasterRepository;
	}

	@Autowired
	public void setCheckUtil(CheckUtil checkUtil) {
		VKjbMasterUtil.checkUtil = checkUtil;
	}

	/**
	 *
	 * @param customerEntity - 顧客エンティティ
	 * @param entityLogicalName - エンティティ論理名
	 * @return
	 */
	public VKjbMaster specifyVKjbMaster(CustomerAbstractEntity customerEntity, String entityLogicalName) throws ErrorCheckException {
		return specifyVKjbMaster(customerEntity.getMomKjbSystemId(), customerEntity.getMomCustId(), entityLogicalName);
	}

	/**
	 *
	 * @param dealerEntity - 販売店エンティティ
	 * @param entityLogicalName - エンティティ論理名
	 * @return
	 */
	public VKjbMaster specifyVKjbMaster(DealerAbstractEntity dealerEntity, String entityLogicalName) throws ErrorCheckException {
		return specifyVKjbMaster(dealerEntity.getMomKjbSystemId(), dealerEntity.getMomCustId(), entityLogicalName);
	}

	private VKjbMaster specifyVKjbMaster(String momKjbSystemId, String momCustId, String entityLogicalName) throws ErrorCheckException {
		VKjbMaster vKjbMaster = vKjbMasterRepository.findByMclMomRelId(momKjbSystemId);
		// MoMシステム連携IDで検索して企事部マスタが見つかった場合はそれを返却する
		if (Objects.nonNull(vKjbMaster)) {
			return vKjbMaster;
		}

		// Momシステム連携IDで検索して企事部マスタが見つからない場合、
		// 企事部IDで検索し、MoMシステム連携IDが一番若い番号のレコードを検索する
		Optional<VKjbMaster> vKjbMasterAlternative = vKjbMasterRepository.findByMclMomKjbIdOrderByMclMomRelId(momCustId).stream().findFirst();
		if (vKjbMasterAlternative.isPresent()) {
			return vKjbMasterAlternative.get();
		} else {
			String[] regexList = { entityLogicalName };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistKjbMaster", regexList));
		}
	}
}
