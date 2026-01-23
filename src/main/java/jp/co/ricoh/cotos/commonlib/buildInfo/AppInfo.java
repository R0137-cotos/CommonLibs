package jp.co.ricoh.cotos.commonlib.buildInfo;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.info.BuildProperties;

/**
 * ビルド情報生成用クラス(App関連)
 */
class AppInfo {

	@SuppressWarnings("unused")
	final private String name;
	@SuppressWarnings("unused")
	final private String buildTime;

	private static final ZoneId JST = ZoneId.of("Asia/Tokyo");

	private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX").withZone(JST);

	AppInfo(BuildProperties buildProperties) {
		Instant instant = null;
		try {
			instant = buildProperties.getTime();
		} catch (Exception ignored) {
			// build-info.properties が無い/値不正などのケースで落とさない
		}
		name = buildProperties.getName();
		buildTime = (instant == null) ? "" : DISPLAY_FORMAT.format(instant);
	}
}
