package jp.co.ricoh.cotos.commonlib.db.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AnalyzedDateDto {
	/**
	 * 連番
	 */
	@Id
	private Integer rownum;

	private Date lastAnalyzed;
}
