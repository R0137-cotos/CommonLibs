package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import java.util.Arrays;

import javax.persistence.MappedSuperclass;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MSリクエストWORK共通項目用抽象クラス
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public abstract class AbstractMsRequestWork extends EntityBase {

	/**
	 * MS送信状態
	 */

	@Description(value = "送信状態(MSリクエストWORK)")
	public enum MsRequestStatus {

		未処理("0"), 出力済("1"), 連携対象外("2");

		private final String text;

		private MsRequestStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MsRequestStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

}
