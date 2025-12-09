package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.vsim")
public class VsimProperties {

	/**
	 * 承諾番号取得手配業務タイプ区分
	 */
	String mnpNumberingTypeDiv;
}
