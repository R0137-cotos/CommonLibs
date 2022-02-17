package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロWfbssレポート作成レスポンスDTO
 * 
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmPostWfbssReportResponseDto extends AbstractTmResponseDto {

	private TmPostWfbssReportResponseDtoCustomers[] customers;

	@Data
	public static class TmPostWfbssReportResponseDtoCustomers {

		/**
		 * ID(String型であることに注意)
		 */
		private String id;

		/**
		 * eID
		 */
		private String eid;

		/**
		 * レポートID
		 */
		private Integer rtid;

		/**
		 * 実行結果
		 *  Done = 成功した場合
		 *  LimitError = レポート数が制限である10を超えた場合
		 *  SpecError = レポートテンプレートの仕様に合わない場合
		 *  InternalError = その他のエラーが発生した場合
		 *  Exception = 予期せぬ例外が発生した場合
		 */
		private String result;

		/**
		 * 実行結果のメッセージ
		 *  Doneの場合：Null
		 *  LimitErrorの場合：“over the report limit”
		 *  SpecErrorの場合：“report template spec error”
		 *  InternalErrorと Exceptionの場合：エラー詳細が表示
		 */
		private String message;
	}
}
