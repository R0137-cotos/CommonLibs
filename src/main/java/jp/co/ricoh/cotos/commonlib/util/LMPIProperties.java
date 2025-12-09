package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * トレンドマイクロ連携設定
 * @author z00se03039
 *
 */
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.lmpi")
public class LMPIProperties extends LMPIPropertiesBase {
}
