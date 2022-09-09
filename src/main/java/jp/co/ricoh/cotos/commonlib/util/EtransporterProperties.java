package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.etransporter")
public class EtransporterProperties {

	String url;

	String authkey;

	int count;

	int days;

	boolean sendMail;

	boolean fileIsEnabled;

	boolean fileAutoPassword;

	boolean downloadIsEnabled;

	boolean downloadAutoPassword;

	String mailTo;
}
