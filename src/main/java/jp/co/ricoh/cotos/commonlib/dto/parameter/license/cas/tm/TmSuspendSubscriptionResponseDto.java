package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション解約レスポンスDTO
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmSuspendSubscriptionResponseDto extends AbstractTmResponseDto {

}
