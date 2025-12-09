package jp.co.ricoh.cotos.commonlib.buildInfo;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import org.springframework.boot.info.GitProperties;

/**
 * ビルド情報生成用クラス(Git関連)
 */
class GitInfo {
	@SuppressWarnings("unused")
	final private String commitTime;
	@SuppressWarnings("unused")
	final private String commitId;
	@SuppressWarnings("unused")
	final private String tags;
	@SuppressWarnings("unused")
	final private String branch;

	GitInfo(GitProperties gitProperties) {
		Instant commitTimeDate = gitProperties.getCommitTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		commitTime = (Objects.isNull(commitTimeDate)) ? "" : sdf.format(Date.from(commitTimeDate));
		commitId = gitProperties.getCommitId();
		tags = gitProperties.get("tags");
		branch = gitProperties.getBranch();
	}
}
