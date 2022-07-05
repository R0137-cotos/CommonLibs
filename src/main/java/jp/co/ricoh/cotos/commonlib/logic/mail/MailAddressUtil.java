package jp.co.ricoh.cotos.commonlib.logic.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.dto.result.MailInfoDto;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo;
import jp.co.ricoh.cotos.commonlib.entity.master.MailAddressMaster.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.master.MailMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.arrangement.ArrangementWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MailMasterRepository;

/**
 * メールアドレス共通クラス
 */
@Component
public class MailAddressUtil {

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	MailMasterRepository mailMasterRepository;

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	ArrangementWorkRepository arrangementWorkRepository;

	@Autowired
	LicenseInfoRepository licenseInfoRepository;

	public final static String AUDIT_TRAIL_MAIL_ADDRESS = "customer_send_history@cotos.ricoh.co.jp";

	/**
	 * メール情報DTOを取得します。
	 *
	 * <pre>
	 * targetIdMapのキーにはサービスカテゴリ（Enum）、値には各ドメインの対象テーブルのIDを設定してください。
	 * ※キーに設定できるサービスカテゴリは１つまでです。重複して設定はできませんのでご注意ください。
	 *
	 * [各ドメインの対象テーブル]
	 * 見積：Estimation
	 * 契約：Contract
	 * 手配業務：ArrangementWork
	 * ライセンス：LicenseInfo
	 * </pre>
	 *
	 * @param targetIdMap HashMap<ServiceCategory, Long> 各ドメインの対象テーブルIDマップ
	 * @param mailMasterId long メールマスタID
	 * @return MailInfoDto
	 */
	public MailInfoDto getMailInfo(HashMap<ServiceCategory, Long> targetIdMap, long mailMasterId) {
		// 引数チェック
		if (MapUtils.isEmpty(targetIdMap)) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "各ドメインの対象テーブルIDマップ" }));
		}
		// メールマスタを取得
		MailMaster mailMaster = mailMasterRepository.findOne(mailMasterId);
		// メールマスタが存在しない場合
		if (null == mailMaster) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExist", new String[] { "メールマスタ" }));
		}
		// メールアドレスマスタが存在しない場合
		if (CollectionUtils.isEmpty(mailMaster.getMailAddressMasterList())) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExist", new String[] { "メールアドレスマスタ" }));
		}
		// MailInfoDto
		MailInfoDto mailInfoDto = new MailInfoDto();
		// TOリスト
		List<String> toList = new ArrayList<>();
		// CCリスト
		List<String> ccList = new ArrayList<>();
		// BCCリスト
		List<String> bccList = new ArrayList<>();
		// メールアドレス取得
		mailMaster.getMailAddressMasterList().stream().forEach(mailAddressMaster -> {//
			// メールアドレス
			String mailAddress = null;
			// メールアドレス取得
			switch (mailAddressMaster.getServiceCategory()) {
			case 見積:
				// 見積IDが設定されていない場合
				if (null == targetIdMap.get(ServiceCategory.見積)) {
					break;
				}
				// 見積を取得
				Estimation estimation = estimationRepository.findOne(targetIdMap.get(ServiceCategory.見積));
				// メールアドレスを取得
				mailAddress = this.getMailAddress(estimation, mailAddressMaster.getTargetEntityName(), mailAddressMaster.getTargetFieldName());
				break;
			case 契約:
				// 契約IDが設定されていない場合
				if (null == targetIdMap.get(ServiceCategory.契約)) {
					break;
				}
				// 契約を取得
				Contract contract = contractRepository.findOne(targetIdMap.get(ServiceCategory.契約));
				// メールアドレスを取得
				mailAddress = this.getMailAddress(contract, mailAddressMaster.getTargetEntityName(), mailAddressMaster.getTargetFieldName());
				break;
			case 手配:
				// 手配IDが設定されていない場合
				if (null == targetIdMap.get(ServiceCategory.手配)) {
					break;
				}
				// 手配を取得
				ArrangementWork arrangementWork = arrangementWorkRepository.findOne(targetIdMap.get(ServiceCategory.手配));
				// メールアドレスを取得
				mailAddress = this.getMailAddress(arrangementWork, mailAddressMaster.getTargetEntityName(), mailAddressMaster.getTargetFieldName());
				break;
			case ライセンス:
				// ライセンス情報IDが設定されていない場合
				if (null == targetIdMap.get(ServiceCategory.ライセンス)) {
					break;
				}
				// ライセンス情報を取得
				LicenseInfo licenseInfo = licenseInfoRepository.findOne(targetIdMap.get(ServiceCategory.ライセンス));
				// メールアドレスを取得
				mailAddress = this.getMailAddress(licenseInfo, mailAddressMaster.getTargetEntityName(), mailAddressMaster.getTargetFieldName());
				break;
			default:
				break;
			}
			// メールアドレスが設定されている場合、メールアドレスリストに追加する。
			if (!StringUtils.isBlank(mailAddress)) {
				// メールアドレス区分を元にメールアドレスリストに追加
				switch (mailAddressMaster.getMailAddressDiv()) {
				case TO:
					// メールアドレスをTOリストに追加
					toList.add(mailAddress);
					break;
				case CC:
					// メールアドレスをCCリストに追加
					ccList.add(mailAddress);
					break;
				case BCC:
					// メールアドレスをBCCリストに追加
					bccList.add(mailAddress);
					break;
				default:
					break;
				}
			}
		});
		bccList.add(AUDIT_TRAIL_MAIL_ADDRESS);
		// 値を設定
		mailInfoDto.setMailTemplateMasterId(mailMaster.getMailTemplateMasterId());
		mailInfoDto.setMailTypeDiv(mailMaster.getMailTypeDiv());
		mailInfoDto.setToMailAddressList(toList);
		mailInfoDto.setCcMailAddressList(ccList);
		mailInfoDto.setBccMailAddressList(bccList);

		return mailInfoDto;
	}

	/**
	 * メールアドレスを取得します。
	 *
	 * @param targetEntity EntityBase 親エンティティ
	 * @param entityName String 子エンティティ名
	 * @param fieldName String フィールド名
	 * @return String
	 */
	private String getMailAddress(EntityBase targetEntity, String entityName, String fieldName) {
		// 親エンティティが存在しない場合
		if (null == targetEntity) {
			return null;
		}
		// エンティティ名が設定されていない場合
		if (StringUtils.isBlank(entityName)) {
			// 親エンティティから取得
			return this.callMethod(targetEntity, fieldName);
		}
		// 子のエンティティリストを取得
		EntityBase targetChild = this.getAllChildrenEntity(targetEntity).stream().filter(e -> e.getClass().getSimpleName().equals(entityName)).findFirst().orElse(null);
		// 子のエンティティが存在しない場合
		if (targetChild == null) {
			return null;
		}
		// 子エンティティから取得
		return this.callMethod(targetChild, fieldName);
	}

	/**
	 * リフレクションを使用し指定したオブジェクトの値を取得します。
	 */
	private String callMethod(Object obj, String fieldName) {
		try {
			Object ret = obj.getClass().getMethod("get" + fieldName).invoke(obj);
			if (null == ret) {
				return null;
			}
			return ret.toString();
		} catch (Exception neverOccur) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "CannotIdentify", new String[] { "対象フィールド" }));
		}
	}

	/**
	 * 子エンティティ取得
	 *
	 * @param parent 親エンティティ
	 * @return 子エンティティリスト
	 */
	private List<EntityBase> getAllChildrenEntity(EntityBase parent) {
		return getAllChildrenEntity(parent, null);
	}

	/**
	 * 子エンティティ取得
	 *
	 * @param parent 親エンティティ
	 * @param entityList エンティティリスト
	 * @return　子エンティティリスト
	 */
	private List<EntityBase> getAllChildrenEntity(EntityBase parent, List<EntityBase> entityList) {
		//事前設定
		List<EntityBase> retList;
		if (entityList == null) {
			retList = new ArrayList<>();
		} else {
			retList = entityList;
		}
		//相互参照による循環の回避
		if (retList.contains(parent))
			return retList;
		// EntityBaseの抽出
		Arrays.asList(parent.getClass().getDeclaredFields()).stream().forEach(field -> {
			field.setAccessible(true);
			System.out.println("Class:" + parent.getClass().getSimpleName() + ", Field:" + field.getName());
			try {
				//1件のものはそのまま使用する。
				Object ob = field.get(parent);
				if (ob instanceof EntityBase) {
					retList.add((EntityBase) ob);
					this.getAllChildrenEntity((EntityBase) ob, retList);
					return;
				}
				//リストのものは1件目を使用する。
				if (ob instanceof List<?>) {
					List<?> list = ((List<?>) ob);
					if (!CollectionUtils.isEmpty(list)) {
						Object firstOb = ((List<?>) ob).get(0);
						if (firstOb instanceof EntityBase) {
							retList.add((EntityBase) firstOb);
							this.getAllChildrenEntity((EntityBase) firstOb, retList);
							return;
						}
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return;
			}
		});
		return retList;
	}
}
