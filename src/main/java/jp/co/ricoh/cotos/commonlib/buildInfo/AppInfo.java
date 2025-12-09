package jp.co.ricoh.cotos.commonlib.buildInfo;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import org.springframework.boot.info.BuildProperties;

/**
 * ビルド情報生成用クラス(App関連)
 */
class AppInfo {

	@SuppressWarnings("unused")
	final private String name;
	@SuppressWarnings("unused")
	final private String buildTime;

	AppInfo(BuildProperties buildProperties) {
		String timeDate = buildProperties.get("time");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
		Instant buildTimeDate = ZonedDateTime.parse(timeDate, formatter).toInstant();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		name = buildProperties.getName();
		buildTime = (Objects.isNull(buildTimeDate)) ? "" : sdf.format(Date.from(buildTimeDate));
	}
}
