package jp.co.ricoh.cotos.commonlib.db.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SynonymNameDto {

	/**
	 * 連番
	 */
	@Id
	private Integer rownum;

	private String tableName;
}
