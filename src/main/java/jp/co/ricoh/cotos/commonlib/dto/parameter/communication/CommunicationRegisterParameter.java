package jp.co.ricoh.cotos.commonlib.dto.parameter.communication;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.Parameter;
import jp.co.ricoh.cotos.commonlib.entity.communication.Communication;
import lombok.Data;

/**
 * コミュニケーション登録時のパラメーターを表します。
 */
@Data
public class CommunicationRegisterParameter {

	/**
	 * コミュニケーションエンティティ
	 */
	@NotNull
	@Parameter(description = "コミュニケーションエンティティ", required = true)
	private Communication communication;

	/**
	 * メールCC送付先MoM社員IDリスト
	 */
	@Parameter(description = "メールCC送付先MoM社員IDリスト", required = false)
	private List<String> momEmpList;

	/**
	 * メール件名置換リスト
	 */
	@NotNull
	@Parameter(description = "メール件名置換リスト", required = true)
	private List<String> mailSubjectRepalceValueList;

	/**
	 * メール本文置換リスト
	 */
	@NotNull
	@Parameter(description = "メール本文置換リスト", required = true)
	private List<String> mailTextRepalceValueList;

	/**
	 * メール本文リスト置換リスト
	 */
	@Parameter(description = "メール本文リスト置換リスト", required = false)
	List<List<String>> mailTextRepalceListValues;
	
	/**
	 * グループ承認フラグ
	 */
	@Parameter(description = "グループ承認フラグ", required = true)
	private boolean isGroupApproval = false;
}
