package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.reports")
public class ReportsProperties {

	/**
	 * 帳票テンプレートディレクトリ
	 */
	private String excelTemplateDir;

	/**
	 * 帳票出力一時ディレクトリ
	 */
	private String temporaryDir;

	/**
	 * CreateForm帳票出力ワークディレクトリ
	 */
	private String createFormWorkDir;
}
