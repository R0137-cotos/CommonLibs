package jp.co.ricoh.cotos.commonlib.db.dto;

import javax.persistence.ParameterMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputStoreProcedureDto {
	private String name;

	private Class<?> clazz;

	private Object value;

	private ParameterMode mode;
}
