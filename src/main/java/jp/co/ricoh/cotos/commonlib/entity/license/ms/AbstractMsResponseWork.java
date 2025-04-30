package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import java.util.Arrays;

import jakarta.persistence.MappedSuperclass;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MSレスポンスWORK共通項目用抽象クラス
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public abstract class AbstractMsResponseWork extends EntityBase {

	/**
	 * MSレスポンス反映状態
	 */
	@Description(value = "レスポンス反映状態(MSレスポンスWORK)")
	public enum MsResponseMappedStatus {

		未反映("0"), 反映済("1"), 反映不要("2");

		private final String text;

		private MsResponseMappedStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MsResponseMappedStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
}
