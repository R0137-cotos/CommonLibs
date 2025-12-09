package jp.co.ricoh.cotos.commonlib.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogResponseProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * ログ共通クラス
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LogUtil {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	ExternalLogResponseProperties externalLogResponseProperties;

	private static final Log log = LogFactory.getLog(LogUtil.class);

	/**
	 * リクエストボディーをログ出力か否か
	 * @param obj
	 * @return
	 */
	public boolean isOutputBody(Object obj) {
		if (obj instanceof ResponseEntity) {
			ResponseEntity<?> entity = ResponseEntity.class.cast(obj);
			Object bodyObject = entity.getBody();

			// ResponseEntity<InputStreamResource>の場合は以下例外対策のためヘッダー情報のみログ出力する
			// 例外：java.lang.IllegalStateException: InputStream has already been read - do not use InputStreamResource if a stream needs to be read multiple times
			if (bodyObject instanceof InputStreamSource) {
				return false;
			}

			// 帳票はバイト配列であり、ログが大量に出力されるためヘッダー情報のみログ出力する
			if (bodyObject instanceof byte[]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ログ出力
	 * ※JSON変換できる場合はJSONで出力、変換不可の場合はtoStringで出力
	 * ※MultipartFileの場合はファイル名やサイズのみ出力
	 *
	 * @param obj
	 * @throws JsonProcessingException
	 */
	public String outputLog(Object obj) {
		if (null == obj) {
			return null;
		}

		if (obj instanceof MultipartFile) {
			MultipartFile file = (MultipartFile) obj;
			return String.format("[MultipartFile] name=%s, originalFilename=%s, size=%d bytes", file.getName(), file.getOriginalFilename(), file.getSize());
		}

		String log = null;
		try {
			log = String.format("[JSON] %s", mapper.writeValueAsString(obj));
		} catch (Exception e) {
			log = String.format("[TEXT] %s", obj.toString());
		}
		return log;
	}

	/**
	 * レスポンスサイズ判定
	 * @param response
	 * @throws IOException
	 */
	public void checkLogSize(ClientHttpResponse response) {
		// レスポンスサイズ超過の場合はエラーとする
		if (Optional.ofNullable(externalLogResponseProperties.getOutputLogSizeLimit()).map(s -> {
			try {
				return s < response.getBody().available();
			} catch (IOException e) {
				log.warn("想定外のエラーによりレスポンスサイズを取得できませんでした。");
				return false;
			}
		}).orElse(false)) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "NumberOfContractChangesLimitError", new String[] { "ログ出力" }));
		}
	}
}
