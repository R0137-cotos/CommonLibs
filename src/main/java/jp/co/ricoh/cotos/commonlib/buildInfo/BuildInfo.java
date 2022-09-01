package jp.co.ricoh.cotos.commonlib.buildInfo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ビルド情報生成用クラス
 */
public class BuildInfo {
	@SuppressWarnings("unused")
	final private GitInfo git;
	@SuppressWarnings("unused")
	final private AppInfo app;

	public BuildInfo(BuildProperties buildProperties, GitProperties gitProperties) {
		app = new AppInfo(buildProperties);
		git = new GitInfo(gitProperties);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		return objectMapper.convertValue(this, HashMap.class);
	}
}
