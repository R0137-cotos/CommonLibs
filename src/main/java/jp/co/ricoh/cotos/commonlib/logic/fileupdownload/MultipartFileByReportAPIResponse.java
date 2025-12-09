package jp.co.ricoh.cotos.commonlib.logic.fileupdownload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 帳票出力APIのレスポンスをfileUploadするためのMultipartFile実装
 * @author z00se03039
 *
 */
public class MultipartFileByReportAPIResponse implements MultipartFile {

	private ResponseEntity<byte[]> responseEntity;

	public MultipartFileByReportAPIResponse(ResponseEntity<byte[]> responseEntity) {
		this.responseEntity = responseEntity;
	}

	@Override
	public String getName() {
		return this.getOriginalFilename();
	}

	@Override
	public String getOriginalFilename() {
		try {
			return URLDecoder.decode(responseEntity.getHeaders().getFirst("filename"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			return responseEntity.getHeaders().getFirst("filename");
		}
	}

	@Override
	public String getContentType() {
		return responseEntity.getHeaders().getContentType().toString();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public long getSize() {
		return responseEntity.getBody().length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return responseEntity.getBody();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(this.getBytes());
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		FileCopyUtils.copy(responseEntity.getBody(), dest);
	}

}
