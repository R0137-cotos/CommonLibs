package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロWfbss通知設定更新リクエストDTO内部ネスト
 * 
 */
@Data
public class TmPutWfbssNotifSettingsRequestDtoThreshold0 implements AbstractTmRequestDto {

	/**
	 * 警告しきい値とする件数
	 *  0以上
	 */
	private Integer threshold;

	/**
	 * 警告しきい値をはかる期間（秒）
	 *  秒単位で指定
	 */
	@JsonProperty("time_interval")
	private Integer timeInterval;

}
