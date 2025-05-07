package jp.co.ricoh.cotos.commonlib.logic.fileupdownload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CreateZipParameter;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.logic.eim.EimConnectionHelper;
import jp.co.ricoh.cotos.commonlib.repository.common.AttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.util.AppProperties;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

@Component
public class FileUpDownload {

	@Autowired
	AttachedFileRepository attachedFileRepository;

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	AppProperties appProperties;

	@Autowired
	EimConnectionHelper eimConnectionHelper;

	private int compressionMethod = Zip4jConstants.COMP_DEFLATE;
	private int compressionLevel = Zip4jConstants.DEFLATE_LEVEL_NORMAL;
	private int encryptionMethod = Zip4jConstants.ENC_METHOD_STANDARD;
	private int aesKeyStrength = Zip4jConstants.AES_STRENGTH_256;

	/**
	 * ファイルアップロード
	 *
	 * @param file
	 *            ファイル情報
	 * @return 添付ファイル情報
	 * @throws IOException
	 */
	public AttachedFile fileUpload(MultipartFile file) throws ErrorCheckException, IOException {
		// チェック処理
		if (null == file) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileInfoNotFoundError"));
		}

		String fileName = file.getOriginalFilename();
		int lastUnixPos = fileName.lastIndexOf('/');
		int lastWindowsPos = fileName.lastIndexOf('\\');
		int index = Math.max(lastUnixPos, lastWindowsPos);
		if (index > 0) {
			fileName = fileName.substring(index + 1);
		}

		if (!existsMatchExtension(fileName)) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileExtensionNotMatchError"));
		}

		if (!existsFileNmSizeMaxOver(fileName)) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileSizeOverError", new String[] { "ファイル名サイズ" }));
		}

		File baseDir = new File(appProperties.getFileProperties().getUploadFileDir());
		Files.createDirectories(baseDir.toPath());

		// 添付ファイル情報登録
		AttachedFile attachedFile = createAttachedFile(file, fileName);
		attachedFileRepository.save(attachedFile);
		attachedFile.setFilePhysicsName(attachedFile.getId() + "_" + fileName);
		attachedFile.setSavedPath(baseDir.getPath() + "/" + attachedFile.getFilePhysicsName());
		attachedFileRepository.save(attachedFile);

		// ファイル保存
		File uploadFile = new File(baseDir, attachedFile.getFilePhysicsName());
		try (OutputStream out = Files.newOutputStream(uploadFile.toPath())) {
			StreamUtils.copy(file.getInputStream(), out);
		} catch (IOException e) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileUploadError", new String[] { uploadFile.getAbsolutePath() }));
		}

		return attachedFile;
	}

	/**
	 * ファイルダウンロード
	 *
	 * <pre>
	 * ・引数のダウンロードファイル名ファイル名が指定されていない場合は、添付ファイル情報の物理ファイル名をダウンロード時のファイル名にする。
	 * </pre>
	 *
	 * @param attachedFileId
	 *            添付ファイルID
	 * @param downloadFileNm
	 *            ダウンロードファイル名
	 * @return ファイル情報
	 * @throws IOException
	 */
	public ResponseEntity<InputStream> downloadFile(Long attachedFileId, String downloadFileNm) throws IOException {
		AttachedFile attachedFile = attachedFileRepository.findById(attachedFileId).orElse(null);
		if (null == attachedFile) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileAttachedFileNotFoundError", new String[] { "ダウンロード" }));
		}

		File file = new File(attachedFile.getSavedPath());
		if (!file.exists()) {
			if (EimLinkedStatus.連携済 == attachedFile.getEimLinkedStatus()) {
				byte[] eimFile = eimConnectionHelper.getFile(attachedFile.getEimFileId());
				if (null == eimFile || eimFile.length == 0) {
					throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileNotFoundError", new String[] { file.getAbsolutePath() }));
				}
				InputStream eimStream = new ByteArrayInputStream(eimFile);
				return new ResponseEntity<>(eimStream, HttpStatus.OK);
			}
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileNotFoundError", new String[] { file.getAbsolutePath() }));
		}

		// ファイルダウンロード
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", attachedFile.getContentType());
		header.setContentLength(attachedFile.getFileSize());
		String fileNm = downloadFileNm == null ? attachedFile.getFilePhysicsName() : downloadFileNm;
		header.setContentDispositionFormData("filename", fileNm);

		InputStream stream;
		try {
			stream = new FileInputStream(file);
		} catch (IOException e) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileDownloadError", new String[] { file.getAbsolutePath() }));
		}

		return new ResponseEntity<>(stream, header, HttpStatus.OK);
	}

	/**
	 * ファイル削除
	 *
	 * @param attachedFileId
	 *            添付ファイルID
	 * @throws IOException
	 */
	public void deleteFile(Long attachedFileId) throws ErrorCheckException, IOException {
		AttachedFile attachedFile = attachedFileRepository.findById(attachedFileId).orElse(null);
		if (null == attachedFile) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileAttachedFileNotFoundError", new String[] { "削除" }));
		}

		File file = new File(appProperties.getFileProperties().getUploadFileDir() + "/" + attachedFile.getFilePhysicsName());
		if (!file.exists()) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileNotFoundError", new String[] { file.getAbsolutePath() }));
		}

		attachedFileRepository.delete(attachedFile);

		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "FileDeleteError", new String[] { file.getAbsolutePath() }));
		}
	}

	/**
	 * 拡張子が設定可能なものか確認
	 *
	 * @param fileName
	 *            ファイル名
	 * @return チェック結果
	 */
	private boolean existsMatchExtension(String fileName) {
		List<String> extensionList = appProperties.getFileProperties().getExtension();
		if (extensionList.isEmpty()) {
			return true;
		}

		String fileExtension = findExtension(fileName);
		return extensionList.stream().filter(ext -> ext.equals(fileExtension)).collect(Collectors.toList()).size() > 0;
	}

	/**
	 * ファイル名サイズが最大を超えていないか確認
	 *
	 * @param fileName
	 *            ファイル名
	 * @return チェック結果
	 */
	private boolean existsFileNmSizeMaxOver(String fileName) {
		return !(fileName.length() > appProperties.getFileProperties().getFileNmMaxSize());
	}

	/**
	 * ファイル拡張子取得
	 *
	 * @param fileName
	 *            ファイル名
	 * @return 拡張子
	 */
	private String findExtension(String fileName) {
		if (null != fileName) {
			int point = fileName.lastIndexOf(".");
			if (point != -1) {
				return fileName.substring(point + 1);
			}
		}
		return null;
	}

	/**
	 * 添付ファイル情報生成
	 *
	 * @param multipartFile
	 *            ファイル情報
	 * @return 添付ファイル情報
	 */
	private AttachedFile createAttachedFile(MultipartFile multipartFile, String fileName) {
		AttachedFile attachedFile = new AttachedFile();
		attachedFile.setId(0);
		attachedFile.setFilePhysicsName(fileName);
		attachedFile.setFileSize(multipartFile.getSize());
		attachedFile.setContentType(multipartFile.getContentType());
		attachedFile.setSavedPath(fileName);
		return attachedFile;
	}

	/**
	 * Zipファイルを作成し、バイト配列を取得後、作成したZipファイルを削除します。
	 * @param parameter
	 * @return
	 * @throws ZipException
	 * @throws IOException
	 */
	public byte[] createZipAndDelete(CreateZipParameter parameter) {
		ZipFile zip = null;
		byte[] result = null;
		try {
			zip = createZip(parameter);
			result = Files.readAllBytes(zip.getFile().toPath());
		} catch (ZipException | IOException e) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ZipCreatingError", new String[] {}));
		} finally {
			Optional.ofNullable(zip).ifPresent(z -> z.getFile().delete());
		}

		return result;
	}

	/**
	 * Zipファイルを作成します。パスワードを渡された場合、暗号化Zipを作成します。
	 *
	 * @param parameter
	 * @throws ZipException
	 * @throws IOException
	 * @see jp.co.ricoh.cotos.arrangement.util.AttachedUtil#zip
	 */
	private ZipFile createZip(CreateZipParameter parameter) throws ZipException, IOException {
		// パラメータ存在チェック
		Optional.ofNullable(parameter).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "EntityCheckNotNullError", new String[] { "Zipファイル作成パラメータ" })));

		// パラメータ内の各Objectに関する存在チェック
		if (CollectionUtils.isEmpty(parameter.getInputPathList())) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "圧縮対象ファイル/ディレクトリ" }));
		}
		Optional.ofNullable(parameter.getOutputPath()).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "圧縮先ファイル" })));

		Optional.ofNullable(parameter.getPassword()).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "パスワード" })));

		Optional.ofNullable(parameter.getFileNameCharset()).orElseThrow(() -> new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "ParameterEmptyError", new String[] { "ファイル名文字コード" })));

		// パラメータで指定しているパスに関する存在チェック
		for (String path : parameter.getInputPathList()) {
			if (!new File(path).exists()) {
				throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "CannotIdentify", new String[] { "圧縮対象ファイル/ディレクトリ" }));
			}
		}

		if (!Paths.get(parameter.getOutputPath()).toAbsolutePath().getParent().toFile().exists()) {
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "CannotIdentify", new String[] { "圧縮先ディレクトリ" }));
		}

		ZipFile zipFile = new ZipFile(parameter.getOutputPath());

		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(compressionMethod);
		parameters.setCompressionLevel(compressionLevel);

		if (parameter.getPassword().isEmpty()) {
			parameters.setEncryptFiles(false);
		} else {
			parameters.setEncryptFiles(true);
			parameters.setPassword(parameter.getPassword());
			parameters.setEncryptionMethod(encryptionMethod);
			parameters.setAesKeyStrength(aesKeyStrength);
		}

		zipFile.setFileNameCharset(parameter.getFileNameCharset());

		for (String eachInput : parameter.getInputPathList()) {
			File inputFile = new File(eachInput);

			if (inputFile.isDirectory()) {
				zipFile.addFolder(inputFile, parameters);
				if (parameter.isInputFileDeleteFlg()) {
					// ディレクトリの場合、ディレクトリ配下のファイルを削除する
					List<File> fileList = Arrays.asList(inputFile.listFiles());
					fileList.stream().forEach(file -> file.delete());
				}
			} else {
				zipFile.addFile(inputFile, parameters);
			}

			// ディレクトリ、または、ファイルを削除する
			if (parameter.isInputFileDeleteFlg()) {
				inputFile.delete();
			}
		}
		return zipFile;
	}
}
