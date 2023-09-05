package jp.co.ricoh.cotos.commonlib.log;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.util.ExternalLogResponseProperties;

/**
 * ログ共通クラス
 */
@Component
public class LogUtil {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	ExternalLogResponseProperties externalLogResponseProperties;

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
	 *
	 * @param obj
	 * @throws JsonProcessingException
	 */
	public String outputLog(Object obj) {
		if (null == obj) {
			return null;
		}
		if (obj instanceof String) {
			String str = (String) obj;
			// CPQデータでかつ契約更新を繰り返し増大したレスポンスデータの場合はエラーとする
			if (str.contains("_config_attr_info")) {
				if (externalLogResponseProperties.getOutputLogSizeLimit() != null && externalLogResponseProperties.getOutputLogSizeLimit() < str.length()) {
					throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "NumberOfContractChangesError", new String[] {}));
				}
			}
		}

		String log = null;
		try {
			log = String.format("[JSON] %s", mapper.writeValueAsString(obj));
		} catch (Exception e) {
			log = String.format("[TEXT] %s", obj.toString());
		}
		return log;
	}
}
