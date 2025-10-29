package jp.co.ricoh.cotos.commonlib.serializer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.dto.result.ArrangementListInfo;
import jp.co.ricoh.cotos.commonlib.dto.result.ContractListInfo;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalResult;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFileHistory;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractCheckResult;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractOperationLog;
import jp.co.ricoh.cotos.commonlib.entity.contract.NextUpdateDetailInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnixTimestampDateSerializerTest {

	@Autowired
	ObjectMapper mapper;

	@SuppressWarnings("unchecked")
	@Test
	public void UNIXタイムスタンプにシリアライズされること() throws Exception {
		LocalDate localDate = LocalDate.of(2025, 10, 16);
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		ArrangementListInfo arrangementListInfo = new ArrangementListInfo();
		arrangementListInfo.setDesiredDeliveryDate(date);
		arrangementListInfo.setBusinessAcceptanceDateTime(date);
		arrangementListInfo.setServiceTermStart(date);
		arrangementListInfo.setServiceTermEnd(date);
		arrangementListInfo.setCreatedAt(date);
		arrangementListInfo.setUpdatedAt(date);
		arrangementListInfo.setWorkCompletedAt(date);
		arrangementListInfo.setApprovalDate(date);
		Map<String, Object> map = mapper.readValue(mapper.writeValueAsString(arrangementListInfo), Map.class);
		Assert.assertEquals("desiredDeliveryDate_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("desiredDeliveryDate"));
		Assert.assertEquals("businessAcceptanceDateTime_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("businessAcceptanceDateTime"));
		Assert.assertEquals("serviceTermStart_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("serviceTermStart"));
		Assert.assertEquals("serviceTermEnd_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("serviceTermEnd"));
		Assert.assertEquals("createdAt_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("createdAt"));
		Assert.assertEquals("updatedAt_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("updatedAt"));
		Assert.assertEquals("workCompletedAt_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("workCompletedAt"));
		Assert.assertEquals("approvalDate_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("approvalDate"));
		
		ContractListInfo contractListInfo = new ContractListInfo();
		contractListInfo.setCreatedAt(date);
		contractListInfo.setUpdatedAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contractListInfo), Map.class);
		Assert.assertEquals("createdAt_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("createdAt"));
		Assert.assertEquals("updatedAt_UNIXタイムスタンプにシリアライズされること", 1760540400000L, map.get("updatedAt"));
		
		Contract contract = new Contract();
		contract.setCreatedAt(date);
		contract.setUpdatedAt(date);
		contract.setArcsPeriodSaleMntOriginLinkAt(date);
		contract.setArcsPeriodSaleMntOriginLinkAt(date);
		contract.setRpaLinkageCpqUpdateCsvCreateAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contract), Map.class);
		Assert.assertEquals("createdAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("createdAt"));
		Assert.assertEquals("updatedAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("updatedAt"));
		Assert.assertEquals("arcsPeriodSaleMntOriginLinkAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("arcsPeriodSaleMntOriginLinkAt"));
		Assert.assertEquals("rpaLinkageCpqUpdateCsvCreateAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("rpaLinkageCpqUpdateCsvCreateAt"));
		
		ContractEquipment contractEquipment = new ContractEquipment();
		contractEquipment.setIsysoneLinkageAt(date);
		contractEquipment.setIsysoneMaintereportLinkageAt(date);
		contractEquipment.setArcsPeriodSaleMainteLinkageAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contractEquipment), Map.class);
		Assert.assertEquals("isysoneLinkageAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("isysoneLinkageAt"));
		Assert.assertEquals("isysoneMaintereportLinkageAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("isysoneMaintereportLinkageAt"));
		Assert.assertEquals("arcsPeriodSaleMainteLinkageAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("arcsPeriodSaleMainteLinkageAt"));

		ContractCheckResult contractCheckResult = new ContractCheckResult();
		contractCheckResult.setCheckedAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contractCheckResult), Map.class);
		Assert.assertEquals("checkedAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("checkedAt"));
		
		ContractApprovalResult contractApprovalResult = new ContractApprovalResult();
		contractApprovalResult.setProcessedAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contractApprovalResult), Map.class);
		Assert.assertEquals("processedAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("processedAt"));
		
		ContractAttachedFile contractAttachedFile = new ContractAttachedFile();
		contractAttachedFile.setAttachedAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contractAttachedFile), Map.class);
		Assert.assertEquals("attachedAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("attachedAt"));

		ContractOperationLog contractOperationLog = new ContractOperationLog ();
		contractOperationLog.setOperatedAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contractOperationLog), Map.class);
		Assert.assertEquals("operatedAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("operatedAt"));
		
		ContractAttachedFileHistory contractAttachedFileHistory = new ContractAttachedFileHistory ();
		contractAttachedFileHistory.setAttachedAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contractAttachedFileHistory), Map.class);
		Assert.assertEquals("attachedAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("attachedAt"));
		
		ContractEquipmentAdditionInfo contractEquipmentAdditionInfo = new ContractEquipmentAdditionInfo();
		contractEquipmentAdditionInfo.setIsysoneLinkageAt(date);
		contractEquipmentAdditionInfo.setArcsPeriodSaleMainteLinkageAt(date);
		map = mapper.readValue(mapper.writeValueAsString(contractEquipmentAdditionInfo), Map.class);
		Assert.assertEquals("isysoneLinkageAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("isysoneLinkageAt"));
		Assert.assertEquals("arcsPeriodSaleMainteLinkageAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("arcsPeriodSaleMainteLinkageAt"));
		
		NextUpdateDetailInfo nextUpdateDetailInfo = new NextUpdateDetailInfo();
		nextUpdateDetailInfo.setVendorLinkageAt(date);
		map = mapper.readValue(mapper.writeValueAsString(nextUpdateDetailInfo ), Map.class);
		Assert.assertEquals("vendorLinkageAt_UNIXタイムスタンプにシリアライされること", 1760540400000L, map.get("vendorLinkageAt"));
		
		// nullでもエラーにならないことの検証
		contract = new Contract();
		contract.setCreatedAt(null);
		contract.setUpdatedAt(null);
		contract.setArcsPeriodSaleMntOriginLinkAt(null);
		contract.setRpaLinkageCpqUpdateCsvCreateAt(null);
		map = mapper.readValue(mapper.writeValueAsString(contract), Map.class);
		Assert.assertNull("createdAt_NULLでもエラーにならないこと", map.get("arcsPeriodSaleMntOriginLinkAt"));
		Assert.assertNull("updatedAt_NULLでもエラーにならないこと", map.get("arcsPeriodSaleMntOriginLinkAt"));
		Assert.assertNull("arcsPeriodSaleMntOriginLinkAt_NULLでもエラーにならないこと", map.get("arcsPeriodSaleMntOriginLinkAt"));
		Assert.assertNull("rpaLinkageCpqUpdateCsvCreateAt_NULLでもエラーにならないこと", map.get("rpaLinkageCpqUpdateCsvCreateAt"));
	}
}