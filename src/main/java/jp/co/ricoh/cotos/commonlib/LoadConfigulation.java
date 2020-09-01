package jp.co.ricoh.cotos.commonlib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import jp.co.ricoh.cotos.commonlib.util.BatchMomInfoProperties;
import jp.co.ricoh.cotos.commonlib.util.DatasourceProperties;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;
import jp.co.ricoh.jmo.util.StringUtil;

/**
 *
 * application.ymlから設定情報を取得する
 *
 */
@SuppressWarnings("unchecked")
public class LoadConfigulation {


	private static LinkedHashMap<String, Object> configOrg;
	private static LinkedHashMap<String, Object> configActive;

	static {

		String ymlName = "config/application.yml";
		try (InputStreamReader in = new InputStreamReader(LoadConfigulation.class.getClassLoader().getResourceAsStream(ymlName))) {

			Yaml yaml = new Yaml();
			configOrg = (LinkedHashMap<String, Object>) yaml.load(in);
		} catch (IOException e) {

		}

		// 環境変数から読む込むymlファイルを指定する
		Map<String, String> env;
		env = System.getenv();
		String activeProfile = env.get("SPRING_PROFILES_ACTIVE");

		if (!StringUtil.isEmpty(activeProfile)) {
			String activeYmlName = "config/application-" + activeProfile + ".yml";
			try (InputStreamReader in = new InputStreamReader(LoadConfigulation.class.getClassLoader().getResourceAsStream(activeYmlName))) {
				Yaml yaml = new Yaml();
				configActive = (LinkedHashMap<String, Object>) yaml.load(in);
			} catch (IOException e) {

			}
		}
	}

	/**
	 * cotos.mom.system情報を取得
	 * @return cotos.mom.system情報
	 */
	public static BatchMomInfoProperties getBatchMomInfoProperties() {
		BatchMomInfoProperties properties = new BatchMomInfoProperties();

		try {
			LinkedHashMap<String, Object> cotos = (LinkedHashMap<String, Object>) configActive.get("cotos");
			LinkedHashMap<String, Object> mom = (LinkedHashMap<String, Object>) cotos.get("mom");
			LinkedHashMap<String, String> system = (LinkedHashMap<String, String>) mom.get("system");
			properties.setMomEmpId(system.get("momEmpId"));
			properties.setOperatorName(system.get("operatorName"));
			properties.setOperatorOrgName(system.get("operatorOrgName"));
		} catch(Exception e) {
			LinkedHashMap<String, Object> cotos = (LinkedHashMap<String, Object>) configOrg.get("cotos");
			LinkedHashMap<String, Object> mom = (LinkedHashMap<String, Object>) cotos.get("mom");
			LinkedHashMap<String, String> system = (LinkedHashMap<String, String>) mom.get("system");
			properties.setMomEmpId(system.get("momEmpId"));
			properties.setOperatorName(system.get("operatorName"));
			properties.setOperatorOrgName(system.get("operatorOrgName"));
		}

		return properties;
	}

	/**
	 * cotos.auth.headers情報を取得
	 * @return cotos.auth.headers情報
	 */
	public static HeadersProperties getHeadersProperties() {
		HeadersProperties properties = new HeadersProperties();

		try {
			LinkedHashMap<String, Object> cotos = (LinkedHashMap<String, Object>) configActive.get("cotos");
			LinkedHashMap<String, Object> auth = (LinkedHashMap<String, Object>) cotos.get("auth");
			LinkedHashMap<String, String> headers = (LinkedHashMap<String, String>) auth.get("headers");
			properties.setAuthorization(headers.get("authorization"));
			properties.setRequireDispAuthorize(headers.get("requireDispAuthorize"));
			properties.setDispAuthorization(headers.get("dispAuthorization"));
			properties.setContentType(headers.get("contentType"));
			properties.setFilename(headers.get("filename"));
		} catch (Exception e) {
			LinkedHashMap<String, Object> cotos = (LinkedHashMap<String, Object>) configOrg.get("cotos");
			LinkedHashMap<String, Object> auth = (LinkedHashMap<String, Object>) cotos.get("auth");
			LinkedHashMap<String, String> headers = (LinkedHashMap<String, String>) auth.get("headers");
			properties.setAuthorization(headers.get("authorization"));
			properties.setRequireDispAuthorize(headers.get("requireDispAuthorize"));
			properties.setDispAuthorization(headers.get("dispAuthorization"));
			properties.setContentType(headers.get("contentType"));
			properties.setFilename(headers.get("filename"));
		}

		return properties;
	}

	/**
	 * cotos.authenticationを取得
	 * @return cotos.authentication
	 */
	public static String getAuthenticationUrl() {

		String authenticationUrl = "";
		try {
			LinkedHashMap<String, Object> cotos = (LinkedHashMap<String, Object>) configActive.get("cotos");
			authenticationUrl = (String) cotos.get("authentication");
		} catch (Exception e) {
		}
		if (StringUtil.isEmpty(authenticationUrl)) {
			LinkedHashMap<String, Object> cotos = (LinkedHashMap<String, Object>) configOrg.get("cotos");
			authenticationUrl = (String) cotos.get("authentication");
		}

		return authenticationUrl;
	}

	/**
	 * spring.messages情報を取得
	 * @return spring.messages情報
	 */
	public static Map<String, String> getMessageProperties() {
		Map<String, String> messageProperties = new HashMap<>();

		try {
			LinkedHashMap<String, Object> spring = (LinkedHashMap<String, Object>) configActive.get("spring");
			LinkedHashMap<String, String> messages = (LinkedHashMap<String, String>) spring.get("messages");
			messageProperties.put("basename", messages.get("basename"));
			messageProperties.put("defaultEncoding", messages.get("defaultEncoding"));
		} catch (Exception e) {
			LinkedHashMap<String, Object> spring = (LinkedHashMap<String, Object>) configOrg.get("spring");
			LinkedHashMap<String, String> messages = (LinkedHashMap<String, String>) spring.get("messages");
			messageProperties.put("basename", messages.get("basename"));
			messageProperties.put("defaultEncoding", messages.get("defaultEncoding"));
		}

		return messageProperties;
	}

	/**
	 * spring.datasource情報を取得
	 * @return spring.datasource情報
	 */
	public static DatasourceProperties getDatasourceProperties() {
		DatasourceProperties properties = new DatasourceProperties();

		try {
			LinkedHashMap<String, Object> spring = (LinkedHashMap<String, Object>) configActive.get("spring");
			LinkedHashMap<String, String> datasource = (LinkedHashMap<String, String>) spring.get("datasource");
			properties.setUrl(datasource.get("url"));
			properties.setUsername(datasource.get("username"));
			properties.setPassword(datasource.get("password"));
			properties.setDriverClassName(datasource.get("driverClassName"));
		} catch (Exception e) {
			LinkedHashMap<String, Object> spring = (LinkedHashMap<String, Object>) configOrg.get("spring");
			LinkedHashMap<String, String> datasource = (LinkedHashMap<String, String>) spring.get("datasource");
			properties.setUrl(datasource.get("url"));
			properties.setUsername(datasource.get("username"));
			properties.setPassword(datasource.get("password"));
			properties.setDriverClassName(datasource.get("driverClassName"));
		}

		return properties;
	}

	/**
	 * cotos.master.urlを取得
	 * @return cotos.master.url
	 */
	public static String getMasterUrl() {

		String masterUrl = "";
		try {
			LinkedHashMap<String, Object> cotos = (LinkedHashMap<String, Object>) configActive.get("cotos");
			LinkedHashMap<String, String> master = (LinkedHashMap<String, String>) cotos.get("master");
			masterUrl = (String) master.get("url");
		} catch (Exception e) {
		}
		if (StringUtil.isEmpty(masterUrl)) {
			LinkedHashMap<String, Object> cotos = (LinkedHashMap<String, Object>) configOrg.get("cotos");
			LinkedHashMap<String, String> master = (LinkedHashMap<String, String>) cotos.get("master");
			masterUrl = (String) master.get("url");
		}

		return masterUrl;
	}

	/**
	 * spring.jpa.properties.hibernate情報を取得
	 * @return spring.jpa.properties.hibernate情報
	 */
	public static Map<String, String> getHibernateProperties() {
		Map<String, String> hibernateProperties = new HashMap<>();

		try {
			LinkedHashMap<String, Object> spring = (LinkedHashMap<String, Object>) configActive.get("spring");
			LinkedHashMap<String, Object> jpa = (LinkedHashMap<String, Object>) spring.get("jpa");
			LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) jpa.get("properties");
			LinkedHashMap<String, Object> hibernate = (LinkedHashMap<String, Object>) properties.get("hibernate");
			hibernateProperties.put("show_sql", hibernate.get("show_sql").toString());
			hibernateProperties.put("use_sql_comments", hibernate.get("use_sql_comments").toString());
			hibernateProperties.put("format_sql", hibernate.get("format_sql").toString());
			hibernateProperties.put("default_schema", hibernate.get("default_schema").toString());
		} catch (Exception e) {
			LinkedHashMap<String, Object> spring = (LinkedHashMap<String, Object>) configOrg.get("spring");
			LinkedHashMap<String, Object> jpa = (LinkedHashMap<String, Object>) spring.get("jpa");
			LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) jpa.get("properties");
			LinkedHashMap<String, Object> hibernate = (LinkedHashMap<String, Object>) properties.get("hibernate");
			hibernateProperties.put("show_sql", hibernate.get("show_sql").toString());
			hibernateProperties.put("use_sql_comments", hibernate.get("use_sql_comments").toString());
			hibernateProperties.put("format_sql", hibernate.get("format_sql").toString());
			hibernateProperties.put("default_schema", hibernate.get("default_schema").toString());
		}

		return hibernateProperties;
	}
}
