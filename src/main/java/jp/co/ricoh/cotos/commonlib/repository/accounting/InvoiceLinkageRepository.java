package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.BatchCommonStatus;
import jp.co.ricoh.cotos.commonlib.entity.accounting.InvoiceLinkage;

public interface InvoiceLinkageRepository extends CrudRepository<InvoiceLinkage, Long> {

	/**
	 * 連携月、請求受信ステータスから取得
	 * インボイス契約情報ファイル作成バッチで未受信データを取得するために使用する。
	 * @param createYm 連携月
	 * @param receiveStatus 請求受信ステータス
	 * @return
	 */
	public List<InvoiceLinkage> findByCreateYmAndReceiveStatus(String createYm, BatchCommonStatus receiveStatus);

	/**
	 * 契約送信ステータスから取得
	 * インボイス契約情報ファイル作成バッチで未送信データを取得するために使用する。
	 * @param sendStatus 契約送信ステータス
	 * @return
	 */
	public List<InvoiceLinkage> findBySendStatusOrderById(BatchCommonStatus sendStatus);

	/**
	 * 契約No.、商品コード、シリアル番号、連携月、契約送信ステータス、請求受信ステータスから取得
	 * インボイス請求データ取込バッチで対象データを取得するために使用する。
	 * @param contractId 契約No.、
	 * @param ricohItemCode 商品コード
	 * @param serialNumber シリアル番号
	 * @param createYm 連携月
	 * @param sendStatus 契約送信ステータス
	 * @param receiveStatus 請求受信ステータス
	 * @return
	 */
	public InvoiceLinkage findByContractIdAndRicohItemCodeAndSerialNumberAndCreateYmAndSendStatusAndReceiveStatus(String contractId, String ricohItemCode, String serialNumber, String createYm, BatchCommonStatus sendStatus, BatchCommonStatus receiveStatus);

	/**
	 * 請求受信ステータスから取得
	 * インボイス請求データ取込バッチで未受信データを取得するために使用する。
	 * @param receiveStatus 請求受信ステータス
	 * @return
	 */
	public List<InvoiceLinkage> findByReceiveStatus(BatchCommonStatus receiveStatus);

	/**
	 * RJ管理番号、連携月から取得
	 * インボイス請求データ取込バッチで、エラー明細と同一契約の明細を全て取得してエラーとするために使用する。
	 * @param rjManageNumber RJ管理番号
	 * @param createYm 連携月
	 * @return
	 */
	/**
	 * 契約送信ステータス、請求受信ステータスから取得
	 * インボイス請求データ取込バッチで対象データを取得するために使用する。
	 * @param sendStatus 契約送信ステータス
	 * @param receiveStatus 請求受信ステータス
	 * @return 
	 */
	public List<InvoiceLinkage> findBySendStatusAndReceiveStatus(BatchCommonStatus sendStatus, BatchCommonStatus receiveStatus);

}
