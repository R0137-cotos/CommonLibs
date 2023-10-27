package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * トレンドマイクロ連携設定（新商品）
 *
 */
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.lmpiNewProduct")
public class LMPIPropertiesNewProduct extends LMPIPropertiesBase {
}
