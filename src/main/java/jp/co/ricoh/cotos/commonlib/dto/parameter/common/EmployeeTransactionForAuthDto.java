package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import lombok.Data;

/**
 * AuthorityJudgeParameterに用いる
 * 権限判定用社員トランザクションリストに使うDTO
 */
@Data
public class EmployeeTransactionForAuthDto {

	/**
	 * MOM社員ID
	 */
	private String momEmployeeId;

	/**
	 * MOM組織ID
	 */
	private String momOrgId;

	/**
	 * RINGS販社コード
	 */
	private String hanshCd;
}
