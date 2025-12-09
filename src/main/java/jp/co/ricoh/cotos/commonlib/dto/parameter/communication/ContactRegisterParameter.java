package jp.co.ricoh.cotos.commonlib.dto.parameter.communication;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 問い合わせ登録時のパラメーターを表します。
 */
@Data
public class ContactRegisterParameter {

	/**
	 * 問い合わせエンティティ
	 */
	@Valid
	@Parameter(description = "問い合わせエンティティ")
	private ContactDto contact;

	/**
	 * 親問い合わせエンティティ
	 */
	@Parameter(description = "親問い合わせエンティティ")
	private ContactDto parentContact;

	/**
	 * メール件名置換リスト
	 */
	@NotNull
	@Parameter(description = "メール件名置換リスト")
	private List<String> mailSubjectRepalceValueList;

	/**
	 * メール本文置換リスト
	 */
	@NotNull
	@Parameter(description = "メール本文置換リスト")
	private List<String> mailTextRepalceValueList;
}
