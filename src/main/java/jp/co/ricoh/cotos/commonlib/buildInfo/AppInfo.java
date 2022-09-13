package jp.co.ricoh.cotos.commonlib.buildInfo;

import java.text.SimpleDateFormat;
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
		Date buildTimeDate = buildProperties.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		name = buildProperties.getName();
		buildTime = (Objects.isNull(buildTimeDate)) ? "" : sdf.format(buildTimeDate);
	}
}
