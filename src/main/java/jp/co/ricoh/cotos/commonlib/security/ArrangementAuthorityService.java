package jp.co.ricoh.cotos.commonlib.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkAuthControlMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkTypeMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.AuthPatternMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.ActionDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AuthDiv;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.arrangement.ArrangementWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.ArrangementWorkAuthControlMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.ArrangementWorkTypeMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.AuthPatternMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.SuperUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService.AuthLevel;

@Component
public class ArrangementAuthorityService {

	/** 権限判定対象となるアクション区分 */
	private static final ActionDiv TARGET_ACTION_DIV = ActionDiv.更新;
	/** 権限判定対象となる権限レベル */
	private static final AuthLevel TARGET_AUTH_LEVEL = AuthLevel.すべて;
	/** 権限判定対象外となる権限区分 */
	private static final AuthDiv WITHOUT_TARGET_AUTH_DIV = AuthDiv.見積_契約_手配;

	@Autowired
	MomAuthorityService momAuthorityService;

	@Autowired
	SuperUserMasterRepository superUserMasterRepository;

	@Autowired
	AuthPatternMasterRepository authPatternMasterRepository;

	@Autowired
	ArrangementWorkAuthControlMasterRepository arrangementWorkAuthControlMasterRepository;

	@Autowired
	ArrangementWorkRepository arrangementWorkRepository;

	@Autowired
	ArrangementWorkTypeMasterRepository arrangementWorkTypeMasterRepository;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	CheckUtil checkUtil;

	/**
	 * ログインユーザが持つ権限から手配業務権限制御マスタリストを取得する
	 * @return nullの場合処理不要（全権限持ち）。nullでない場合、そのユーザの権限と紐づく手配業務権限制御マスタリスト
	 */
	public List<ArrangementWorkAuthControlMaster> getArrangementWorkAuth() {
		// ユーザー情報の取得
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// 権限レベルを取得
		Map<ActionDiv, Map<AuthDiv, AuthLevel>> allMomAuthorities = userInfo.getMomAuthorities();

		if (isSystemAdministrator(allMomAuthorities) || isSuperUser(userInfo)) {
			// 処理不要であるため、returnする
			return null;
		}

		// 自身の持つ権限パターンマスタ情報を取得する
		List<AuthPatternMaster> authPatternMasterList = authPatternMasterRepository.findByActionDivAndAuthDivIn(TARGET_ACTION_DIV, createUserAuthDivList(allMomAuthorities));

		// 手配業務権限制御マスタの情報から作業可能な手配業務タイプマスタの情報を取得する
		List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList;
		if (CollectionUtils.isEmpty(authPatternMasterList)) {
			// 権限パターンマスタがnullの場合、2200のみが設定されているものとしてnullで検索を行う
			arrangementWorkAuthControlMasterList = arrangementWorkAuthControlMasterRepository.findByAuthPatternMasterIsNull();
		} else {
			arrangementWorkAuthControlMasterList = arrangementWorkAuthControlMasterRepository.findByAuthPatternMasterIn(authPatternMasterList);
		}

		return arrangementWorkAuthControlMasterList;
	}

	/**
	 * ログインユーザが持つ権限で引数の手配業務タイプが実行可能かをチェックする
	 * @param arrangementWorkIdList
	 * @return nullの場合処理不要（全権限持ち）。正常時は空のリスト、エラー時はエラー情報を返す
	 */
	public List<ErrorInfo> checkArrangementWorkAuth(List<Long> arrangementWorkIdList) {
		if (CollectionUtils.isEmpty(arrangementWorkIdList)) {
			// 通常は発生しない
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "手配業務IDリスト" }));
		}

		List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList = getArrangementWorkAuth();
		if (arrangementWorkAuthControlMasterList == null) {
			// nullの場合は、処理不要であるため、チェックしない
			return null;
		}

		// 引数から手配業務タイプの情報を取得する
		List<ArrangementWork> arrangementWorkList = arrangementWorkRepository.findByIdIn(arrangementWorkIdList);
		if (CollectionUtils.isEmpty(arrangementWorkList)) {
			// 通常は発生しない
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistArrangement", new String[] { "手配業務" }));
		}

		List<ErrorInfo> errorInfoList = new ArrayList<ErrorInfo>();
		arrangementWorkList.stream().forEach(work -> {
			boolean errorFlg = true;

			for (ArrangementWorkAuthControlMaster authControlMaster : arrangementWorkAuthControlMasterList) {
				ArrangementWorkTypeMaster arrangementWorkTypeMaster = arrangementWorkTypeMasterRepository.findOne(work.getArrangementWorkTypeMasterId());
				if (arrangementWorkTypeMaster == null) {
					// 通常は発生しない
					throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityDoesNotExistArrangement", new String[] { "手配業務" }));
				}

				if (CollectionUtils.isEmpty(authControlMaster.getArrangementWorkTypeMasterList())) {
					// 2200の場合は手配業務タイプマスタと紐づく手配業務権限制御マスタの情報から判定する
					if (arrangementWorkTypeMaster.getArrangementWorkAuthControlMaster() == null) {
						errorFlg = false;
						break;
					}
				}
				// 実行権限のある手配業務権限制御マスタと紐づく手配業務タイプマスタと引数が等しいかをチェックする
				if (authControlMaster.getArrangementWorkTypeMasterList().stream().anyMatch(authWorkType -> authWorkType.getId() == work.getArrangementWorkTypeMasterId())) {
					errorFlg = false;
					break;
				}
			}

			if (errorFlg) {
				// 引数の手配業務タイプマスタに対して権限が存在しない場合、エラー情報を追加する
				ArrangementWorkTypeMaster arrangementWorkTypeMaster = arrangementWorkTypeMasterRepository.findOne(work.getArrangementWorkTypeMasterId());
				Contract contract = contractRepository.findOne(work.getArrangement().getContractId());
				checkUtil.addErrorInfo(errorInfoList, "NoArrangementWorkAuthoritiesError", new String[] { contract.getContractNumber(), arrangementWorkTypeMaster.getArrangementWorkName() });
			}
		});

		return errorInfoList;
	}

	/**
	 * システム管理者の権限を持っているかを判定する
	 * @param allMomAuthorities ユーザの持つ全権限情報
	 * @return trueの場合、システム管理者権限を持つ
	 */
	private boolean isSystemAdministrator(Map<ActionDiv, Map<AuthDiv, AuthLevel>> allMomAuthorities) {
		AuthLevel authLevel = allMomAuthorities.get(TARGET_ACTION_DIV).get(AuthDiv.システム管理);

		return (TARGET_AUTH_LEVEL == authLevel);
	}

	/**
	 * スーパーユーザーかどうかを判定する
	 * @param userInfo ユーザ情報
	 * @return trueの場合、スーパーユーザー
	 */
	private boolean isSuperUser(CotosAuthenticationDetails userInfo) {
		return superUserMasterRepository.existsByUserId(userInfo.getMomEmployeeId());
	}

	/**
	 * ユーザの持つ権限情報から必要な情報のみを持つリストを作成する
	 * @param allMomAuthorities
	 * @return
	 */
	private List<AuthDiv> createUserAuthDivList(Map<ActionDiv, Map<AuthDiv, AuthLevel>> allMomAuthorities) {
		List<AuthDiv> resultList = allMomAuthorities.get(TARGET_ACTION_DIV).entrySet().stream().filter(e -> hasEditAuthority(e.getValue()) && !Objects.equals(e.getKey(), WITHOUT_TARGET_AUTH_DIV)).map(Map.Entry::getKey).collect(Collectors.toList());

		return resultList;
	}

	/**
	 * 参照・編集権限が存在するか判定する
	 */
	protected boolean hasEditAuthority(AuthLevel authLevel) {

		// 権限レベルによる認可処理を実施
		switch (authLevel) {
		case すべて:
			return true;
		default:
			return false;
		}
	}
}
