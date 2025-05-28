package jp.co.ricoh.cotos.commonlib.check;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.TestTools.ParameterErrorIds;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.AttachedFileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractAddedEditorEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractApprovalRouteDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractApprovalRouteNodeDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractAssignmentAttachedFileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractAssignmentDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractAttachedFileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractAttachedFileHistoryDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractAttachedFileLinkageDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractCheckResultDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractDetailDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractEquipmentAdditionInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractEquipmentDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractEquipmentNoIsysoneDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractInstallationLocationDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicAccCeEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicAccSsOrgDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicIntCeEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicIntSsOrgDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicMntCeEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicMntSsOrgDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicSaEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.CustomerContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.DealerContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ItemContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ItemDetailContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ManagedEstimationDetailDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.PenaltyDetailContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ProductContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ShippingAddressDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ShippingAddressSsOrgDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.ContractForFindAllDetailsDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.ProductContractForFindAllDetailsDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats.ContractDetailForFindAllDetailsBplatsDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats.ContractForFindAllDetailsBplatsDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats.ContractListDetailInfoGetBplatsDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats.ItemContractDetailForFindAllDetailsBplatsDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats.PagingDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats.ProductContractForFindAllDetailsBplatsDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.external.ContractExtCancelDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.external.ContractExtChangeDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.external.ContractExtCreateDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.external.ProductContractExtCreateDto;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAddedEditorEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalRouteNode;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAssignment;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAssignmentAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFileHistory;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFileLinkage;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractCheckResult;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentNoIsysone;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractInstallationLocation;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicAccCeEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicAccSsOrg;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicIntCeEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicIntSsOrg;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicMntCeEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicMntSsOrg;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicSaEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.CustomerContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.DealerContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ItemContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ItemDetailContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ManagedEstimationDetail;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ProductContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ShippingAddress;
import jp.co.ricoh.cotos.commonlib.entity.contract.ShippingAddressSsOrg;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAddedEditorEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractApprovalResultRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractApprovalRouteNodeRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractApprovalRouteRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAssignmentAttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAssignmentRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAttachedFileHistoryRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAttachedFileLinkageRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractAttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractCheckResultRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractEquipmentAdditionInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractEquipmentNoIsysoneRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractEquipmentRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractInstallationLocationRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractOperationLogRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicAccCeEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicAccSsOrgRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicIntCeEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicIntSsOrgRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicMntCeEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicMntSsOrgRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractPicSaEmpRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.CustomerContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.DealerContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ItemContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ItemDetailContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ManagedEstimationDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.PenaltyDetailContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ProductContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ShippingAddressRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ShippingAddressSsOrgRepository;
import jp.co.ricoh.cotos.commonlib.security.TestSecurityController;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;

/**
 * パラメータチェック（見積ドメイン）のテストクラス
 *
 * @author kentaro.kakuhana
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "test.context.id = TestContractDto")
public class TestContractDto {

	private static final String STR_256 = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	private static final String STR_1001 = "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
	private static final String STR_1334 = "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123";
	private static final int INT_MINUS_1 = -1;
	private static final int INT_MINUS_100000 = -100000;
	private static final Long LONG_MINUS_1 = -1L;
	private static final int INT_10 = 10;
	private static final int INT_100 = 100;
	private static final int INT_1000 = 1000;
	private static final int INT_100000 = 100000;
	private static final BigDecimal DECIMAL_MINUS_001 = new BigDecimal("-0.01");
	private static final BigDecimal DECIMAL_0001 = new BigDecimal("0.001");
	private static final BigDecimal DECIMAL_MINUS_1000000000000000000001 = new BigDecimal("-10000000000000000000.01");
	private static final String STR_19 = "01234567890123456789";

	static ConfigurableApplicationContext context;

	@Autowired
	HeadersProperties headersProperties;

	@Autowired
	ContractAddedEditorEmpRepository contractAddedEditorEmpRepository;

	@Autowired
	ContractApprovalResultRepository contractApprovalResultRepository;

	@Autowired
	ContractApprovalRouteNodeRepository contractApprovalRouteNodeRepository;

	@Autowired
	ContractApprovalRouteRepository contractApprovalRouteRepository;

	@Autowired
	ContractAttachedFileRepository contractAttachedFileRepository;

	@Autowired
	ContractCheckResultRepository contractCheckResultRepository;

	@Autowired
	ContractDetailRepository contractDetailRepository;

	@Autowired
	ContractOperationLogRepository contractOperationLogRepository;

	@Autowired
	ContractPicSaEmpRepository contractPicSaEmpRepository;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	CustomerContractRepository customerContractRepository;

	@Autowired
	ContractInstallationLocationRepository contractInstallationLocationRepository;

	@Autowired
	DealerContractRepository dealerContractRepository;

	@Autowired
	ItemContractRepository itemContractRepository;

	@Autowired
	ItemDetailContractRepository itemDetailContractRepository;

	@Autowired
	ProductContractRepository productContractRepository;

	@Autowired
	ContractEquipmentRepository contractEquipmentRepository;

	@Autowired
	ContractPicMntSsOrgRepository contractPicMntSsOrgRepository;

	@Autowired
	ContractPicAccSsOrgRepository contractPicAccSsOrgRepository;

	@Autowired
	ContractPicIntSsOrgRepository contractPicIntSsOrgRepository;

	@Autowired
	ContractPicMntCeEmpRepository contractPicMntCeEmpRepository;

	@Autowired
	ContractPicIntCeEmpRepository contractPicIntCeEmpRepository;

	@Autowired
	ContractPicAccCeEmpRepository contractPicAccCeEmpRepository;

	@Autowired
	ManagedEstimationDetailRepository managedEstimationDetailRepository;

	@Autowired
	ContractAttachedFileHistoryRepository contractAttachedFileHistoryRepository;

	@Autowired
	ContractAttachedFileLinkageRepository contractAttachedFileLinkageRepository;

	@Autowired
	ContractAssignmentRepository contractAssignmentRepository;

	@Autowired
	ContractAssignmentAttachedFileRepository contractAssignmentAttachedFileRepository;

	@Autowired
	ContractEquipmentNoIsysoneRepository contractEquipmentNoIsysoneRepository;

	@Autowired
	ShippingAddressRepository shippingAddressRepository;

	@Autowired
	ShippingAddressSsOrgRepository shippingAddressSsOrgRepository;

	@Autowired
	PenaltyDetailContractRepository penaltyDetailContractRepository;

	@Autowired
	ContractEquipmentAdditionInfoRepository contractEquipmentAdditionInfoRepository;

	@Autowired
	TestTools testTool;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/attachedFile.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/itemMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productCompMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/productGrpMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/jsonMaster.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/master/attachedFileLinkage.sql");
		context.getBean(DBConfig.class).initTargetTestData("repository/contract.sql");
	}

	@Autowired
	TestSecurityController testSecurityController;

	@LocalServerPort
	private int localServerPort;

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void CustomerContractDtoのテスト() throws Exception {
		CustomerContract entity = customerContractRepository.findById(401L).get();
		CustomerContractDto testTarget = new CustomerContractDto();
		BeanUtils.copyProperties(entity, testTarget);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomKjbSystemId(null);
		testTarget.setMomCustId(null);
		testTarget.setCompanyId(null);
		testTarget.setOfficeId(null);
		testTarget.setDepartmentDiv(null);
		testTarget.setCustomerName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 6);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM企事部システム連携IDが設定されていません。"));

		// 異常系（@Size(max))
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomKjbSystemId(STR_256);
		testTarget.setMomCustId(STR_256);
		testTarget.setCompanyId(STR_256);
		testTarget.setOfficeId(STR_256);
		testTarget.setCustomerName(STR_256);
		testTarget.setCompanyName(STR_256);
		testTarget.setCompanyNameKana(STR_256);
		testTarget.setOfficeName(STR_256);
		testTarget.setDepartmentName(STR_256);
		testTarget.setPostNumber(STR_256);
		testTarget.setAddress(STR_1001);
		testTarget.setPhoneNumber(STR_256);
		testTarget.setFaxNumber(STR_256);
		testTarget.setCompanyRepresentativeName(STR_256);
		testTarget.setCompanyRepresentativeNameKana(STR_256);
		testTarget.setPicName(STR_256);
		testTarget.setPicNameKana(STR_256);
		testTarget.setPicDeptName(STR_256);
		testTarget.setPicPhoneNumber(STR_256);
		testTarget.setPicFaxNumber(STR_256);
		testTarget.setPicMailAddress(STR_256);
		testTarget.setNetricohAccount(STR_256);
		testTarget.setSetupCorpNm(STR_256);
		testTarget.setSetupPostCd(STR_256);
		testTarget.setSetupAddr(STR_256);
		testTarget.setSetupTel(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 26);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "住所は最大文字数（1000）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(CustomerContract.class, CustomerContractDto.class);

	}

	@Test
	public void ContractInstallationLocationDtoのテスト() throws Exception {
		ContractInstallationLocation entity = contractInstallationLocationRepository.findById(401L).get();
		ContractInstallationLocationDto testTarget = new ContractInstallationLocationDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setCompanyRepresentativeNameKana(STR_256);
		testTarget.setPicName(STR_256);
		testTarget.setPicNameKana(STR_256);
		testTarget.setPicDeptName(STR_256);
		testTarget.setPicPhoneNumber(STR_256);
		testTarget.setPicFaxNumber(STR_256);
		testTarget.setPicMailAddress(STR_256);
		testTarget.setInputPostNumber(STR_256);
		testTarget.setInputAddress(STR_1001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 9);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM非連携_住所(手入力)は最大文字数（1000）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractInstallationLocation.class, ContractInstallationLocationDto.class);

	}

	@Test
	public void DealerContractDtoのテスト() throws Exception {
		DealerContract entity = dealerContractRepository.findById(401L).get();
		DealerContractDto testTarget = new DealerContractDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setDealerFlowOrder(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "販売店商流順が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomKjbSystemId(STR_256);
		testTarget.setDealerName(STR_256);
		testTarget.setPostNumber(STR_256);
		testTarget.setAddress(STR_1001);
		testTarget.setOrgPhoneNumber(STR_256);
		testTarget.setPicName(STR_256);
		testTarget.setPicDeptName(STR_256);
		testTarget.setPicPhoneNumber(STR_256);
		testTarget.setPicFaxNumber(STR_256);
		testTarget.setDistributorCd(STR_256);
		testTarget.setOeDeliveryCd(STR_256);
		testTarget.setDistributorEmployeeMailAddress(STR_256);
		testTarget.setRingsCustomerCd(STR_256);
		testTarget.setDistributorRtcCd(STR_256);
		testTarget.setDistributorMomCmpId(STR_256);
		testTarget.setDistributorMomShikiCd(STR_256);
		testTarget.setDistributorMomSoshikiId(STR_256);
		testTarget.setDistributorMomDepoCd(STR_256);
		testTarget.setOrbSendSiteId(STR_256);
		testTarget.setPicNameKana(STR_256);
		testTarget.setDealerNameKana(STR_256);
		testTarget.setCompanyRepresentativeName(STR_256);
		testTarget.setCompanyRepresentativeNameKana(STR_256);
		testTarget.setMomCustId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 24);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "住所は最大文字数（1000）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setSendUpdateMailFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "更新案内メール送信フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setSendUpdateMailFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "更新案内メール送信フラグは最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(DealerContract.class, DealerContractDto.class);
	}

	@Test
	public void ContractDtoのテスト() throws Exception {
		Contract entity = contractRepository.findById(4L).get();
		ContractDto dto = new ContractDto();
		ContractDto testTarget = new ContractDto();

		BeanUtils.copyProperties(entity, dto);

		// 契約明細
		ContractDetailDto detail = new ContractDetailDto();
		BeanUtils.copyProperties(entity.getContractDetailList().get(0), detail);
		ItemContractDto item = new ItemContractDto();
		BeanUtils.copyProperties(entity.getContractDetailList().get(0).getItemContract(), item);
		detail.setItemContract(item);
		dto.setContractDetailList(Arrays.asList(detail));

		// 見積明細管理
		ManagedEstimationDetailDto estimationDetail = new ManagedEstimationDetailDto();
		BeanUtils.copyProperties(entity.getManagedEstimationDetailList().get(0), estimationDetail);
		dto.setManagedEstimationDetailList(Arrays.asList(estimationDetail));

		// 契約担当SA社員
		ContractPicSaEmpDto sa = new ContractPicSaEmpDto();
		BeanUtils.copyProperties(entity.getContractPicSaEmp(), sa);
		dto.setContractPicSaEmp(sa);

		// 顧客（契約用）
		CustomerContractDto customer = new CustomerContractDto();
		BeanUtils.copyProperties(entity.getCustomerContract(), customer);
		dto.setCustomerContract(customer);

		// 販売店（契約用）
		DealerContractDto dealer = new DealerContractDto();
		BeanUtils.copyProperties(entity.getDealerContractList().get(0), dealer);
		dto.setDealerContractList(Arrays.asList(dealer));

		// 契約追加編集者社員
		ContractAddedEditorEmpDto added = new ContractAddedEditorEmpDto();
		BeanUtils.copyProperties(entity.getContractAddedEditorEmpList().get(0), added);
		dto.setContractAddedEditorEmpList(Arrays.asList(added));

		// 契約添付ファイル
		ContractAttachedFileDto esAttached = new ContractAttachedFileDto();
		BeanUtils.copyProperties(entity.getContractAttachedFileList().get(0), esAttached);
		AttachedFileDto attached = new AttachedFileDto();
		BeanUtils.copyProperties(entity.getContractAttachedFileList().get(0).getAttachedFile(), attached);
		esAttached.setAttachedFile(attached);
		dto.setContractAttachedFileList(Arrays.asList(esAttached));

		// 契約添付ファイル履歴
		ContractAttachedFileHistoryDto attachedFileHistory = new ContractAttachedFileHistoryDto();
		BeanUtils.copyProperties(entity.getContractAttachedFileHistoryList().get(0), attachedFileHistory);
		AttachedFileDto attachedHistory = new AttachedFileDto();
		BeanUtils.copyProperties(entity.getContractAttachedFileHistoryList().get(0).getAttachedFile(), attachedHistory);
		attachedFileHistory.setAttachedFile(attachedHistory);
		dto.setContractAttachedFileHistoryList(Arrays.asList(attachedFileHistory));

		// 商品（契約用）
		ProductContractDto product = new ProductContractDto();
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));

		// 契約承認ルート
		ContractApprovalRouteDto route = new ContractApprovalRouteDto();
		BeanUtils.copyProperties(entity.getContractApprovalRouteList().get(0), route);
		ContractApprovalRouteNodeDto approvalNode = new ContractApprovalRouteNodeDto();
		BeanUtils.copyProperties(entity.getContractApprovalRouteList().get(0).getContractApprovalRouteNodeList().get(0), approvalNode);
		route.setContractApprovalRouteNodeList(Arrays.asList(approvalNode));
		dto.setContractApprovalRouteList(Arrays.asList(route));

		// 見積チェック結果
		ContractCheckResultDto check = new ContractCheckResultDto();
		BeanUtils.copyProperties(entity.getContractCheckResultList().get(0), check);
		dto.setContractCheckResultList(Arrays.asList(check));

		// 契約受付担当CE社員
		ContractPicAccCeEmpDto picAccCeEmp = new ContractPicAccCeEmpDto();
		BeanUtils.copyProperties(entity.getContractPicAccCeEmp(), picAccCeEmp);
		dto.setContractPicAccCeEmp(picAccCeEmp);

		// 契約保守担当CE社員
		ContractPicMntCeEmpDto picMntCeEmp = new ContractPicMntCeEmpDto();
		BeanUtils.copyProperties(entity.getContractPicMntCeEmp(), picMntCeEmp);
		dto.setContractPicMntCeEmp(picMntCeEmp);

		// 契約導入担当SS組織
		ContractPicIntCeEmpDto picIntCeEmp = new ContractPicIntCeEmpDto();
		BeanUtils.copyProperties(entity.getContractPicIntCeEmp(), picIntCeEmp);
		dto.setContractPicIntCeEmp(picIntCeEmp);

		// 契約受付担当SS組織
		ContractPicAccSsOrgDto picAccSsOrg = new ContractPicAccSsOrgDto();
		BeanUtils.copyProperties(entity.getContractPicAccSsOrg(), picAccSsOrg);
		dto.setContractPicAccSsOrg(picAccSsOrg);

		// 契約保守担当SS組織
		ContractPicMntSsOrgDto picMntSsOrg = new ContractPicMntSsOrgDto();
		BeanUtils.copyProperties(entity.getContractPicMntSsOrg(), picMntSsOrg);
		dto.setContractPicMntSsOrg(picMntSsOrg);

		// 契約導入担当SS組織
		ContractPicIntSsOrgDto picIntSsOrg = new ContractPicIntSsOrgDto();
		BeanUtils.copyProperties(entity.getContractPicIntSsOrg(), picIntSsOrg);
		dto.setContractPicIntSsOrg(picIntSsOrg);

		// 契約機種
		dto.setContractEquipmentList(new ArrayList<ContractEquipmentDto>());
		entity.getContractEquipmentList().forEach(s -> {
			ContractEquipmentDto node = new ContractEquipmentDto();
			BeanUtils.copyProperties(s, node);
			dto.getContractEquipmentList().add(node);
		});

		// 設置先(契約用)
		ContractInstallationLocationDto installationLocation = new ContractInstallationLocationDto();
		BeanUtils.copyProperties(entity.getContractInstallationLocation(), installationLocation);
		dto.setContractInstallationLocation(installationLocation);

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setLifecycleStatus(null);
		testTarget.setContractType(null);
		testTarget.setWorkflowStatus(null);
		testTarget.setContractNumber(null);
		testTarget.setContractPicSaEmp(null);
		testTarget.setCustomerContract(null);
		testTarget.setContractDetailList(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 7);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約担当SA社員が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setImmutableContIdentNumber(STR_256);
		testTarget.setCaseNumber(STR_256);
		testTarget.setCaseTitle(STR_256);
		testTarget.setContractNumber(STR_256);
		testTarget.setContractTitle(STR_256);
		testTarget.setOriginContractNumber(STR_256);
		testTarget.setEstimationNumber(STR_256);
		testTarget.setEstimationTitle(STR_256);
		testTarget.setCommercialFlowDiv(STR_256);
		testTarget.setIssueFormat(STR_256);
		testTarget.setBillingCustomerSpCode(STR_256);
		testTarget.setBillingCustomerSpName(STR_256);
		testTarget.setPaymentTerms(STR_256);
		testTarget.setPaymentMethod(STR_256);
		testTarget.setCancelReason(STR_256);
		testTarget.setCancelReasonEtc(STR_1001);
		testTarget.setWebOrderNumber(STR_256);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setCancelOrderNo(STR_256);
		testTarget.setContactNo(STR_256);
		testTarget.setIssueTaxCodeValue(STR_256);
		testTarget.setInstallDeliverySiteId(STR_256);
		testTarget.setVendorManageNumber(STR_256);
		testTarget.setPenaltyFfmOrderContactNo(STR_256);
		testTarget.setToVendorComment(STR_1334);
		testTarget.setPurchaseManageNumber(STR_256);
		testTarget.setMvbAccount(STR_19);
		testTarget.setNttCustomerId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 28);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "得意先宛先名は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setEstimationBranchNumber(INT_100);
		testTarget.setOriginContractBranchNumber(INT_100);
		testTarget.setAccountSalesFlg(INT_10);
		testTarget.setManualUpdateFlg(INT_10);
		testTarget.setSsWorkRequestCreateFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "売上計上フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setProductGrpMasterId(INT_MINUS_1);
		testTarget.setContractBranchNumber(INT_MINUS_1);
		testTarget.setOriginContractBranchNumber(INT_MINUS_1);
		testTarget.setOriginContractId((long) INT_MINUS_1);
		testTarget.setAccountSalesFlg(INT_MINUS_1);
		testTarget.setEstimationBranchNumber(INT_MINUS_1);
		testTarget.setEstimationId(LONG_MINUS_1);
		testTarget.setManualUpdateFlg(INT_MINUS_1);
		testTarget.setSsWorkRequestCreateFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 9);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品グループマスタIDは最小値（0）を下回っています。"));

		// 異常系（@Valid：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getContractDetailList().get(0).setDetailAbstract(STR_256);
		testTarget.getManagedEstimationDetailList().get(0).setDetailAbstract(STR_256);
		testTarget.getContractPicSaEmp().setPostNumber(STR_256);
		testTarget.getCustomerContract().setCustomerName(STR_256);
		testTarget.getDealerContractList().get(0).setDealerName(STR_256);
		testTarget.getContractAddedEditorEmpList().get(0).setOrgName(STR_256);
		testTarget.getContractAttachedFileList().get(0).setAttachedOrgName(STR_256);
		testTarget.getProductContractList().get(0).setProductContractName(STR_256);
		testTarget.getContractApprovalRouteList().get(0).setApprovalRequesterName(STR_256);
		testTarget.getContractCheckResultList().get(0).setCheckedUserName(STR_256);
		testTarget.getContractPicMntSsOrg().setServiceOrgName(STR_256);
		testTarget.getContractPicMntCeEmp().setFaxNumber(STR_256);
		testTarget.getContractPicAccCeEmp().setMomEmployeeId(STR_256);
		testTarget.getContractPicIntCeEmp().setMomEmployeeId(STR_256);
		testTarget.getContractPicMntCeEmp().setMomEmployeeId(STR_256);
		testTarget.getContractPicAccSsOrg().setMomOrgId(STR_256);
		testTarget.getContractPicIntSsOrg().setMomOrgId(STR_256);
		testTarget.getContractPicMntSsOrg().setMomOrgId(STR_256);
		testTarget.getContractEquipmentList().get(0).setEquipmentCode(STR_256);
		testTarget.getContractInstallationLocation().setMomKjbSystemId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 20);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品名は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(Contract.class, ContractDto.class);
	}

	@Test
	public void ItemDetailContractDtoのテスト() throws Exception {
		ItemDetailContract entity = itemDetailContractRepository.findById(401L).get();
		ItemDetailContractDto testTarget = new ItemDetailContractDto();
		BeanUtils.copyProperties(entity, testTarget);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setTransToServiceOrgCode(STR_256);
		testTarget.setTransToServiceOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "振替先課所コードは最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setBatchImportFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "一括登録フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setBatchImportFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "一括登録フラグは最小値（0）を下回っています。"));

		// 異常系（@DecimalMin ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setPrice(DECIMAL_MINUS_1000000000000000000001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesOne(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "原価は最小値（-9999999999999999999.99）を下回っています。"));
		Assert.assertTrue(testTool.errorIdMatchesOne(result.getErrorInfoList(), ParameterErrorIds.ROT00028));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "原価は小数点以下2桁を超えています。"));

		// 異常系（@Digits ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setPrice(DECIMAL_0001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00028));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "原価は小数点以下2桁を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ItemDetailContract.class, ItemDetailContractDto.class);
	}

	@Test
	public void ContractEquipmentのテスト() throws Exception {
		ContractEquipment entity = contractEquipmentRepository.findById(401L).get();
		ContractEquipmentDto testTarget = new ContractEquipmentDto();
		BeanUtils.copyProperties(entity, testTarget);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：)
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setEquipmentCode(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "機種コードが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setEquipmentCode(STR_256);
		testTarget.setEquipmentNo(STR_256);
		testTarget.setPurchaseForm(STR_256);
		testTarget.setDeliveryForm(STR_256);
		testTarget.setMaintenanceForm(STR_256);
		testTarget.setDeliveryMachineDiv(STR_256);
		testTarget.setMaintenanceNoteKana(STR_1001);
		testTarget.setInspectionMonth(STR_256);
		testTarget.setInspectionMonthYearWorth(STR_256);
		testTarget.setIsysoneLinkagedEquipmentNo(STR_256);
		testTarget.setIsysoneLinkagedEquipmentCode(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 11);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "納入機器区分は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractEquipment.class, ContractEquipmentDto.class);
	}

	@Test
	public void ContractForFindAllDetailsDtoのテスト() throws Exception {
		Contract entity = contractRepository.findById(4L).get();
		ContractForFindAllDetailsDto testTarget = new ContractForFindAllDetailsDto();
		ContractForFindAllDetailsDto dto = new ContractForFindAllDetailsDto();

		BeanUtils.copyProperties(entity, dto, "productContractList");

		// 商品（契約用）
		ProductContractForFindAllDetailsDto product = new ProductContractForFindAllDetailsDto();
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setDealerContractList(null);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setLifecycleStatus(null);
		testTarget.setContractType(null);
		testTarget.setWorkflowStatus(null);
		testTarget.setContractNumber(null);
		testTarget.setContractPicSaEmp(null);
		testTarget.setCustomerContract(null);
		testTarget.setContractDetailList(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約種別が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setCaseNumber(STR_256);
		testTarget.setCaseTitle(STR_256);
		testTarget.setContractTitle(STR_256);
		testTarget.setOriginContractNumber(STR_256);
		testTarget.setEstimationNumber(STR_256);
		testTarget.setEstimationTitle(STR_256);
		testTarget.setCommercialFlowDiv(STR_256);
		testTarget.setIssueFormat(STR_256);
		testTarget.setBillingCustomerSpCode(STR_256);
		testTarget.setBillingCustomerSpName(STR_256);
		testTarget.setPaymentTerms(STR_256);
		testTarget.setPaymentMethod(STR_256);
		testTarget.setCancelReason(STR_256);
		testTarget.setCancelReasonEtc(STR_1001);
		testTarget.setWebOrderNumber(STR_256);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setCancelOrderNo(STR_256);
		testTarget.setContactNo(STR_256);
		testTarget.setIssueTaxCodeValue(STR_256);
		testTarget.setInstallDeliverySiteId(STR_256);
		testTarget.setVendorManageNumber(STR_256);
		testTarget.setProductGroupCd(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 22);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "変更元文書番号は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setEstimationBranchNumber(INT_100);
		testTarget.setOriginContractBranchNumber(INT_100);
		testTarget.setManualUpdateFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "見積番号枝番は最大値（99）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setProductGrpMasterId(INT_MINUS_1);
		testTarget.setOriginContractBranchNumber(INT_MINUS_1);
		testTarget.setOriginContractId((long) INT_MINUS_1);
		testTarget.setEstimationBranchNumber(INT_MINUS_1);
		testTarget.setEstimationId(LONG_MINUS_1);
		testTarget.setManualUpdateFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 6);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "見積IDは最小値（0）を下回っています。"));

		// 異常系（@Valid：契約明細）
		entity = contractRepository.findById(4L).get();
		BeanUtils.copyProperties(entity, dto, "productContractList");
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getContractDetailList().get(0).setState(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "状態が設定されていません。"));

		// 異常系（@Valid：契約担当SA社員）
		entity = contractRepository.findById(4L).get();
		BeanUtils.copyProperties(entity, dto, "productContractList");
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getContractPicSaEmp().setMomEmployeeId(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDが設定されていません。"));

		// 異常系（@Valid：販売店(契約用)）
		entity = contractRepository.findById(4L).get();
		BeanUtils.copyProperties(entity, dto, "productContractList");
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getDealerContractList().get(0).setDistributorCd(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "販売店コードは最大文字数（255）を超えています。"));

		// 異常系（@Valid：顧客(契約用)）
		entity = contractRepository.findById(4L).get();
		BeanUtils.copyProperties(entity, dto, "productContractList");
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getCustomerContract().setCompanyRepresentativeNameKana(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM非連携_企業代表者名(カナ)は最大文字数（255）を超えています。"));

		// 異常系（@Valid：商品(契約用)）
		entity = contractRepository.findById(4L).get();
		BeanUtils.copyProperties(entity, dto, "productContractList");
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getProductContractList().get(0).setProductMasterId(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品マスタIDは最小値（0）を下回っています。"));

		// 異常系（@Valid：見積明細管理）
		entity = contractRepository.findById(4L).get();
		BeanUtils.copyProperties(entity, dto, "productContractList");
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getManagedEstimationDetailList().get(0).setState(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "状態が設定されていません。"));
	}

	@Test
	public void ProductContractForFindAllDetailsDtoのテスト() throws Exception {
		ProductContract entity = productContractRepository.findById(401L).get();
		ProductContractForFindAllDetailsDto testTarget = new ProductContractForFindAllDetailsDto();
		BeanUtils.copyProperties(entity, testTarget);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setVendorManageNumberName(STR_256);
		testTarget.setServiceProviderCompanyName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ベンダー管理番号名称は最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setProductMasterId(INT_MINUS_1);
		testTarget.setRepItemMasterId(-1L);
		testTarget.setVendorManageNumberName(null);
		testTarget.setServiceProviderCompanyName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "代表品種マスタIDは最小値（0）を下回っています。"));
	}

	@Test
	public void ContractAddedEditorEmpDtoのテスト() throws Exception {
		ContractAddedEditorEmp entity = contractAddedEditorEmpRepository.findById(401L).get();
		ContractAddedEditorEmpDto testTarget = new ContractAddedEditorEmpDto();
		BeanUtils.copyProperties(entity, testTarget);

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(null);
		testTarget.setEmployeeName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(STR_256);
		testTarget.setMomOrgId(STR_256);
		testTarget.setOrgName(STR_256);
		testTarget.setSalesCompanyName(STR_256);
		testTarget.setOrgPhoneNumber(STR_256);
		testTarget.setEmployeeName(STR_256);
		testTarget.setSalesDepartmentName(STR_256);
		testTarget.setPostNumber(STR_256);
		testTarget.setAddress(STR_1001);
		testTarget.setPhoneNumber(STR_256);
		testTarget.setFaxNumber(STR_256);
		testTarget.setMailAddress(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 12);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDは最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setOrgHierarchyLevel(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織階層レベルは最小値（0）を下回っています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setOrgHierarchyLevel(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織階層レベルは最大値（9）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractAddedEditorEmp.class, ContractAddedEditorEmpDto.class);
	}

	@Test
	public void ContractApprovalRouteDtoのテスト() throws Exception {
		ContractApprovalRoute entity = contractApprovalRouteRepository.findById(401L).get();
		ContractApprovalRouteDto dto = new ContractApprovalRouteDto();
		ContractApprovalRouteDto testTarget = new ContractApprovalRouteDto();

		BeanUtils.copyProperties(entity, dto);
		dto.setContractApprovalRouteNodeList(new ArrayList<ContractApprovalRouteNodeDto>());
		entity.getContractApprovalRouteNodeList().forEach(s -> {
			ContractApprovalRouteNodeDto node = new ContractApprovalRouteNodeDto();
			BeanUtils.copyProperties(s, node);
			dto.getContractApprovalRouteNodeList().add(node);
		});

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull、@NotEmptyの null チェック：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setContractApprovalRouteNodeList(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約承認ルートノードが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setApprovalRequesterEmpId(STR_256);
		testTarget.setApprovalRequesterName(STR_256);
		testTarget.setApprovalRequesterOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "承認依頼者組織名は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setSpecialPriceApprovalFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "特価承認対象フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setSpecialPriceApprovalFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "特価承認対象フラグは最小値（0）を下回っています。"));

		// 異常系（@Valid：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getContractApprovalRouteNodeList().get(0).setApproverName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "承認者氏名は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractApprovalRoute.class, ContractApprovalRouteDto.class);
	}

	@Test
	public void ContractApprovalRouteNodeDtoのテスト() throws Exception {
		ContractApprovalRouteNode entity = contractApprovalRouteNodeRepository.findById(401L).get();
		ContractApprovalRouteNodeDto testTarget = new ContractApprovalRouteNodeDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull、@NotEmptyの null チェック：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setApproverEmpId(null);
		testTarget.setApproverName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "承認者氏名が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setApproverEmpId(STR_256);
		testTarget.setApproverName(STR_256);
		testTarget.setApproverOrgName(STR_256);
		testTarget.setSubApproverEmpId(STR_256);
		testTarget.setSubApproverName(STR_256);
		testTarget.setSubApproverOrgName(STR_256);
		testTarget.setGroupName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 7);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "代理承認者組織名は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setApprovalOrder(INT_1000);
		testTarget.setApproverOrgLevel(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "承認順は最大値（999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setApprovalOrder(INT_MINUS_1);
		testTarget.setApproverOrgLevel(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "承認者組織階層レベルは最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractApprovalRouteNode.class, ContractApprovalRouteNodeDto.class);
	}

	@Test
	public void ContractAttachedFileDtoのテスト() throws Exception {

		ContractAttachedFile entity = contractAttachedFileRepository.findById(401L).get();
		ContractAttachedFileDto testTarget = new ContractAttachedFileDto();
		AttachedFileDto attachedFileDto = new AttachedFileDto();
		BeanUtils.copyProperties(entity.getAttachedFile(), attachedFileDto);

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedFile(attachedFileDto);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setFileName(null);
		testTarget.setAttachedFile(null);
		testTarget.setAttachedEmpId(null);
		testTarget.setAttachedEmpName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "添付者氏名が設定されていません。"));

		// 異常系（@Size(max)
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedFile(attachedFileDto);
		testTarget.setFileName(STR_256);
		testTarget.setFileKind(STR_256);
		testTarget.setAttachedComment(STR_1001);
		testTarget.setAttachedEmpId(STR_256);
		testTarget.setAttachedEmpName(STR_256);
		testTarget.setAttachedOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 6);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "添付者MoM社員IDは最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedRequiredFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "添付必須フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedRequiredFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "添付必須フラグは最小値（0）を下回っています。"));
		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractAttachedFile.class, ContractAttachedFileDto.class);
	}

	@Test
	public void ContractCheckResultDtoのテスト() throws Exception {
		ContractCheckResult entity = contractCheckResultRepository.findById(401L).get();
		ContractCheckResultDto testTarget = new ContractCheckResultDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull、@NotEmptyの null チェック：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setTargetLifecycleStatus(null);
		testTarget.setCheckMatterCode(null);
		testTarget.setCheckMatterText(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "チェック事項文面が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setCheckMatterCode(STR_256);
		testTarget.setCheckMatterText(STR_256);
		testTarget.setCheckedUserId(STR_256);
		testTarget.setCheckedUserName(STR_256);
		testTarget.setCheckedOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "チェック事項文面は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setDisplayOrder(INT_1000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "表示順は最大値（999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setDisplayOrder(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "表示順は最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractCheckResult.class, ContractCheckResultDto.class);
	}

	@Test
	public void ContractDetailDtoのテスト() throws Exception {
		ContractDetail entity = contractDetailRepository.findById(401L).get();
		ContractDetailDto dto = new ContractDetailDto();
		ContractDetailDto testTarget = new ContractDetailDto();
		BeanUtils.copyProperties(entity, dto);

		ItemContractDto item = new ItemContractDto();
		BeanUtils.copyProperties(entity.getItemContract(), item);
		dto.setItemContract(item);

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setState(null);
		testTarget.setUnitPrice(null);
		testTarget.setAmountSummary(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "状態が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setDetailAbstract(STR_256);
		testTarget.setOrderNo(STR_256);
		testTarget.setContractSpan(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "摘要は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setQuantity(INT_100000);
		testTarget.setBeforeQuantity(INT_100000);
		testTarget.setItemAddFlg(INT_10);
		testTarget.setContractAmount(INT_100000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最大値（99999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setQuantity(INT_MINUS_100000);
		testTarget.setBeforeQuantity(INT_MINUS_100000);
		testTarget.setItemAddFlg(INT_MINUS_1);
		testTarget.setContractAmount(INT_MINUS_100000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最小値（-99999）を下回っています。"));

		// 異常系（@DecimalMin：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setUnitPrice(DECIMAL_MINUS_001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "単価は最小値（0.00）を下回っています。"));

		// 異常系（@Digits：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setAmountSummary(DECIMAL_0001);
		testTarget.setUnitPrice(DECIMAL_0001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00028));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "金額は小数点以下2桁を超えています。"));

		// 異常系（@Valid：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getItemContract().setRicohItemCode(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "リコー品種コードは最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractDetail.class, ContractDetailDto.class);
	}

	@Test
	public void ContractPicSaEmpDtoのテスト() throws Exception {
		ContractPicSaEmp entity = contractPicSaEmpRepository.findById(401L).get();
		ContractPicSaEmpDto testTarget = new ContractPicSaEmpDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(null);
		testTarget.setEmployeeName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(STR_256);
		testTarget.setMomOrgId(STR_256);
		testTarget.setOrgName(STR_256);
		testTarget.setSalesCompanyName(STR_256);
		testTarget.setOrgPhoneNumber(STR_256);
		testTarget.setEmployeeName(STR_256);
		testTarget.setSalesDepartmentName(STR_256);
		testTarget.setPostNumber(STR_256);
		testTarget.setAddress(STR_1001);
		testTarget.setPhoneNumber(STR_256);
		testTarget.setFaxNumber(STR_256);
		testTarget.setMailAddress(STR_256);
		testTarget.setMomKjbSystemId(STR_256);
		testTarget.setMomCustId(STR_256);
		testTarget.setSalesCompanyNameKana(STR_256);
		testTarget.setCompanyRepresentativeName(STR_256);
		testTarget.setCompanyRepresentativeNameKana(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 17);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDは最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setOrgHierarchyLevel(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織階層レベルは最小値（0）を下回っています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setOrgHierarchyLevel(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織階層レベルは最大値（9）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractPicSaEmp.class, ContractPicSaEmpDto.class);
	}

	@Test
	public void ContractPicMntCeEmpDtoのテスト() throws Exception {
		ContractPicMntCeEmp entity = contractPicMntCeEmpRepository.findById(401L).get();
		ContractPicMntCeEmpDto testTarget = new ContractPicMntCeEmpDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(null);
		testTarget.setEmployeeName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(STR_256);
		testTarget.setMomOrgId(STR_256);
		testTarget.setOrgName(STR_256);
		testTarget.setSalesCompanyName(STR_256);
		testTarget.setOrgPhoneNumber(STR_256);
		testTarget.setEmployeeName(STR_256);
		testTarget.setSalesDepartmentName(STR_256);
		testTarget.setPostNumber(STR_256);
		testTarget.setAddress(STR_1001);
		testTarget.setPhoneNumber(STR_256);
		testTarget.setFaxNumber(STR_256);
		testTarget.setMailAddress(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 12);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDは最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setOrgHierarchyLevel(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織階層レベルは最小値（0）を下回っています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setOrgHierarchyLevel(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織階層レベルは最大値（9）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractPicMntCeEmp.class, ContractPicMntCeEmpDto.class);
	}

	@Test
	public void ItemContractDtoのテスト() throws Exception {
		ItemContract entity = itemContractRepository.findById(401L).get();
		ItemContractDto testTarget = new ItemContractDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setItemType(null);
		testTarget.setCostType(null);
		testTarget.setPartitionPrice(null);
		testTarget.setItemContractName(null);
		testTarget.setRicohItemCode(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "品種名が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setItemContractName(STR_256);
		testTarget.setRicohItemCode(STR_256);
		testTarget.setBpCd(STR_256);
		testTarget.setTaxFlag(STR_256);
		testTarget.setMakerItemCode(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "リコー品種コードは最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setItemMasterId(INT_MINUS_1);
		testTarget.setProductMasterId(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "品種マスタIDは最小値（0）を下回っています。"));

		// 異常系（@DecimalMin：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setPartitionPrice(DECIMAL_MINUS_001);
		testTarget.setRCost(DECIMAL_MINUS_001);
		testTarget.setRjPurchasePrice(DECIMAL_MINUS_001);
		testTarget.setRjDividingPrice(DECIMAL_MINUS_001);
		testTarget.setMotherStorePrice(DECIMAL_MINUS_001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "仕切価格は最小値（0.00）を下回っています。"));

		// 異常系（@Digits：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setPartitionPrice(DECIMAL_0001);
		testTarget.setRCost(DECIMAL_0001);
		testTarget.setRjPurchasePrice(DECIMAL_0001);
		testTarget.setRjDividingPrice(DECIMAL_0001);
		testTarget.setMotherStorePrice(DECIMAL_0001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00028));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "仕切価格は小数点以下2桁を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ItemContract.class, ItemContractDto.class);
	}

	@Test
	public void ProductContractDtoのテスト() throws Exception {
		ProductContract entity = productContractRepository.findById(401L).get();
		ProductContractDto testTarget = new ProductContractDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setProductContractName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品名が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setProductContractName(STR_256);
		testTarget.setServiceIdentNumber(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "サービス識別番号は最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setProductMasterId(INT_MINUS_1);
		testTarget.setRepItemMasterId(LONG_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "代表品種マスタIDは最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ProductContract.class, ProductContractDto.class);
	}

	@Test
	public void ContractPicIntCeEmpDtoのテスト() throws Exception {
		ContractPicIntCeEmp entity = contractPicIntCeEmpRepository.findById(401L).get();
		ContractPicIntCeEmpDto testTarget = new ContractPicIntCeEmpDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDは最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractPicIntCeEmp.class, ContractPicIntCeEmpDto.class);

	}

	@Test
	public void ContractPicAccCeEmpDtoのテスト() throws Exception {
		ContractPicAccCeEmp entity = contractPicAccCeEmpRepository.findById(401L).get();
		ContractPicAccCeEmpDto testTarget = new ContractPicAccCeEmpDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDは最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractPicAccCeEmp.class, ContractPicAccCeEmpDto.class);

	}

	@Test
	public void ContractPicMntSsOrgDtoのテスト() throws Exception {
		ContractPicMntSsOrg entity = contractPicMntSsOrgRepository.findById(401L).get();
		ContractPicMntSsOrgDto testTarget = new ContractPicMntSsOrgDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomOrgId(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織MoM組織IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomOrgId(STR_256);
		testTarget.setServiceOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "課所名は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractPicMntSsOrg.class, ContractPicMntSsOrgDto.class);
	}

	@Test
	public void ContractPicAccSsOrgDtoのテスト() throws Exception {
		ContractPicAccSsOrg entity = contractPicAccSsOrgRepository.findById(401L).get();
		ContractPicAccSsOrgDto testTarget = new ContractPicAccSsOrgDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomOrgId(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織MoM組織IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomOrgId(STR_256);
		testTarget.setServiceOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "課所名は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractPicAccSsOrg.class, ContractPicAccSsOrgDto.class);
	}

	@Test
	public void ContractPicIntSsOrgDtoのテスト() throws Exception {
		ContractPicIntSsOrg entity = contractPicIntSsOrgRepository.findById(401L).get();
		ContractPicIntSsOrgDto testTarget = new ContractPicIntSsOrgDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setMomOrgId(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織MoM組織IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(testTarget, entity);
		testTarget.setMomOrgId(STR_256);
		testTarget.setServiceOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "課所名は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractPicIntSsOrg.class, ContractPicIntSsOrgDto.class);
	}

	@Test
	public void ContractExtCreateDtoのテスト() throws Exception {
		Contract entity = contractRepository.findById(4L).get();
		ContractExtCreateDto dto = new ContractExtCreateDto();
		ContractExtCreateDto testTarget = new ContractExtCreateDto();

		BeanUtils.copyProperties(entity, dto);

		// 契約明細
		ContractDetailDto detail = new ContractDetailDto();
		BeanUtils.copyProperties(entity.getContractDetailList().get(0), detail);
		ItemContractDto item = new ItemContractDto();
		BeanUtils.copyProperties(entity.getContractDetailList().get(0).getItemContract(), item);
		detail.setItemContract(item);
		dto.setContractDetailList(Arrays.asList(detail));

		// 見積明細管理
		ManagedEstimationDetailDto estimationDetail = new ManagedEstimationDetailDto();
		BeanUtils.copyProperties(entity.getManagedEstimationDetailList().get(0), estimationDetail);
		dto.setManagedEstimationDetailList(Arrays.asList(estimationDetail));

		// 契約担当SA社員
		ContractPicSaEmpDto sa = new ContractPicSaEmpDto();
		BeanUtils.copyProperties(entity.getContractPicSaEmp(), sa);
		dto.setContractPicSaEmp(sa);

		// 顧客（契約用）
		CustomerContractDto customer = new CustomerContractDto();
		BeanUtils.copyProperties(entity.getCustomerContract(), customer);
		dto.setCustomerContract(customer);

		// 販売店（契約用）
		DealerContractDto dealer = new DealerContractDto();
		BeanUtils.copyProperties(entity.getDealerContractList().get(0), dealer);
		dto.setDealerContractList(Arrays.asList(dealer));

		// 商品（契約用）
		ProductContractExtCreateDto product = new ProductContractExtCreateDto();
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));

		// 追加編集者
		ContractAddedEditorEmpDto editor = new ContractAddedEditorEmpDto();
		BeanUtils.copyProperties(entity.getContractAddedEditorEmpList().get(0), editor);
		dto.setContractAddedEditorEmpList(Arrays.asList(editor));

		// 契約機種付加情報
		ContractEquipmentAdditionInfoDto equipmentAdd = new ContractEquipmentAdditionInfoDto();
		BeanUtils.copyProperties(entity.getContractEquipmentAdditionInfoList().get(0), equipmentAdd);
		dto.setContractEquipmentAdditionInfoList(Arrays.asList(equipmentAdd));

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setLifecycleStatus(null);
		testTarget.setWorkflowStatus(null);
		testTarget.setWebOrderNumber(null);
		testTarget.setContractPicSaEmp(null);
		testTarget.setCustomerContract(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約担当SA社員が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setCaseNumber(STR_256);
		testTarget.setCaseTitle(STR_256);
		testTarget.setContractNumber(STR_256);
		testTarget.setContractTitle(STR_256);
		testTarget.setOriginContractNumber(STR_256);
		testTarget.setEstimationNumber(STR_256);
		testTarget.setEstimationTitle(STR_256);
		testTarget.setCommercialFlowDiv(STR_256);
		testTarget.setIssueFormat(STR_256);
		testTarget.setBillingCustomerSpCode(STR_256);
		testTarget.setBillingCustomerSpName(STR_256);
		testTarget.setPaymentTerms(STR_256);
		testTarget.setPaymentMethod(STR_256);
		testTarget.setCancelReason(STR_256);
		testTarget.setCancelReasonEtc(STR_1001);
		testTarget.setWebOrderNumber(STR_256);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setCancelOrderNo(STR_256);
		testTarget.setInstallDeliverySiteId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 19);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "得意先宛先名は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setEstimationBranchNumber(INT_100);
		testTarget.setOriginContractBranchNumber(INT_100);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "見積番号枝番は最大値（99）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setProductGrpMasterId(INT_MINUS_1);
		testTarget.setOriginContractBranchNumber(INT_MINUS_1);
		testTarget.setOriginContractId((long) INT_MINUS_1);
		testTarget.setEstimationBranchNumber(INT_MINUS_1);
		testTarget.setEstimationId(LONG_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品グループマスタIDは最小値（0）を下回っています。"));

		// 異常系（@Valid：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getContractDetailList().get(0).setDetailAbstract(STR_256);
		testTarget.getManagedEstimationDetailList().get(0).setDetailAbstract(STR_256);
		testTarget.getContractPicSaEmp().setPostNumber(STR_256);
		testTarget.getCustomerContract().setCustomerName(STR_256);
		testTarget.getDealerContractList().get(0).setDealerName(STR_256);
		testTarget.getProductContractList().get(0).setProductContractName(STR_256);
		testTarget.getContractEquipmentAdditionInfoList().get(0).setCompanyName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 7);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品名は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(Contract.class, ContractExtCreateDto.class);
	}

	@Test
	public void ContractExtChangeDtoのテスト() throws Exception {
		Contract entity = contractRepository.findById(4L).get();
		ContractExtChangeDto dto = new ContractExtChangeDto();
		ContractExtChangeDto testTarget = new ContractExtChangeDto();

		BeanUtils.copyProperties(entity, dto);

		// 契約明細
		ContractDetailDto detail = new ContractDetailDto();
		BeanUtils.copyProperties(entity.getContractDetailList().get(0), detail);
		ItemContractDto item = new ItemContractDto();
		BeanUtils.copyProperties(entity.getContractDetailList().get(0).getItemContract(), item);
		detail.setItemContract(item);
		dto.setContractDetailList(Arrays.asList(detail));

		// 見積明細管理
		ManagedEstimationDetailDto estimationDetail = new ManagedEstimationDetailDto();
		BeanUtils.copyProperties(entity.getManagedEstimationDetailList().get(0), estimationDetail);
		dto.setManagedEstimationDetailList(Arrays.asList(estimationDetail));

		// 契約担当SA社員
		ContractPicSaEmpDto sa = new ContractPicSaEmpDto();
		BeanUtils.copyProperties(entity.getContractPicSaEmp(), sa);
		dto.setContractPicSaEmp(sa);

		// 顧客（契約用）
		CustomerContractDto customer = new CustomerContractDto();
		BeanUtils.copyProperties(entity.getCustomerContract(), customer);
		dto.setCustomerContract(customer);

		// 販売店（契約用）
		DealerContractDto dealer = new DealerContractDto();
		BeanUtils.copyProperties(entity.getDealerContractList().get(0), dealer);
		dto.setDealerContractList(Arrays.asList(dealer));

		// 商品（契約用）
		ProductContractExtCreateDto product = new ProductContractExtCreateDto();
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));

		// 追加編集者
		ContractAddedEditorEmpDto editor = new ContractAddedEditorEmpDto();
		BeanUtils.copyProperties(entity.getContractAddedEditorEmpList().get(0), editor);
		dto.setContractAddedEditorEmpList(Arrays.asList(editor));

		// 契約機種付加情報
		ContractEquipmentAdditionInfoDto equipmentAdd = new ContractEquipmentAdditionInfoDto();
		BeanUtils.copyProperties(entity.getContractEquipmentAdditionInfoList().get(0), equipmentAdd);
		dto.setContractEquipmentAdditionInfoList(Arrays.asList(equipmentAdd));

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setLifecycleStatus(null);
		testTarget.setWorkflowStatus(null);
		testTarget.setContractPicSaEmp(null);
		testTarget.setCustomerContract(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約担当SA社員が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setCaseNumber(STR_256);
		testTarget.setCaseTitle(STR_256);
		testTarget.setContractNumber(STR_256);
		testTarget.setContractTitle(STR_256);
		testTarget.setEstimationNumber(STR_256);
		testTarget.setEstimationTitle(STR_256);
		testTarget.setCommercialFlowDiv(STR_256);
		testTarget.setIssueFormat(STR_256);
		testTarget.setBillingCustomerSpCode(STR_256);
		testTarget.setBillingCustomerSpName(STR_256);
		testTarget.setPaymentTerms(STR_256);
		testTarget.setPaymentMethod(STR_256);
		testTarget.setCancelReason(STR_256);
		testTarget.setCancelReasonEtc(STR_1001);
		testTarget.setWebOrderNumber(STR_256);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setCancelOrderNo(STR_256);
		testTarget.setInstallDeliverySiteId(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 18);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "得意先宛先名は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setEstimationBranchNumber(INT_100);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "見積番号枝番は最大値（99）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setProductGrpMasterId(INT_MINUS_1);
		testTarget.setEstimationBranchNumber(INT_MINUS_1);
		testTarget.setEstimationId(LONG_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品グループマスタIDは最小値（0）を下回っています。"));

		// 異常系（@Valid：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.getContractDetailList().get(0).setDetailAbstract(STR_256);
		testTarget.getManagedEstimationDetailList().get(0).setDetailAbstract(STR_256);
		testTarget.getContractPicSaEmp().setPostNumber(STR_256);
		testTarget.getCustomerContract().setCustomerName(STR_256);
		testTarget.getDealerContractList().get(0).setDealerName(STR_256);
		testTarget.getProductContractList().get(0).setProductContractName(STR_256);
		testTarget.getContractEquipmentAdditionInfoList().get(0).setCompanyName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 7);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品名は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(Contract.class, ContractExtChangeDto.class);
	}

	@Test
	public void ContractExtCancelDtoのテスト() throws Exception {
		ContractExtCancelDto entity = new ContractExtCancelDto();
		ContractExtCancelDto testTarget = new ContractExtCancelDto();
		entity.setContractId(INT_10);
		entity.setRjManageNumber("ROX000001");
		entity.setLifecycleStatus(LifecycleStatus.作成中);
		entity.setWorkflowStatus(WorkflowStatus.作成中);
		entity.setCancelScheduledDate(new Date());
		entity.setCancelReason("cancel");
		entity.setCancelReasonEtc(STR_256);
		entity.setCancelOrderNo("cancelNo");
		entity.setPenaltyFfmOrderContactNo("cancel");

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setRjManageNumber(null);
		testTarget.setLifecycleStatus(null);
		testTarget.setWorkflowStatus(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "RJ管理番号が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setRjManageNumber(STR_256);
		testTarget.setCancelReason(STR_256);
		testTarget.setCancelReasonEtc(STR_1001);
		testTarget.setCancelOrderNo(STR_256);
		testTarget.setPenaltyFfmOrderContactNo(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "RJ管理番号は最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setContractId(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "契約IDは最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(Contract.class, ContractExtCancelDto.class, "contractId");
	}

	@Test
	public void ProductContractExtCreateDtoのテスト() throws Exception {
		ProductContract entity = productContractRepository.findById(401L).get();
		ProductContractExtCreateDto dto = new ProductContractExtCreateDto();
		ProductContractExtCreateDto testTarget = new ProductContractExtCreateDto();
		BeanUtils.copyProperties(entity, dto);

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setProductContractName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "商品名が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setProductContractName(STR_256);
		testTarget.setServiceIdentNumber(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "サービス識別番号は最大文字数（255）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setProductMasterId(INT_MINUS_1);
		testTarget.setRepItemMasterId(LONG_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "代表品種マスタIDは最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ProductContract.class, ProductContractExtCreateDto.class);
	}

	@Test
	public void ManagedEstimationDetailDtoのテスト() throws Exception {
		ManagedEstimationDetail entity = managedEstimationDetailRepository.findById(401L).get();
		ManagedEstimationDetailDto dto = new ManagedEstimationDetailDto();
		ManagedEstimationDetailDto testTarget = new ManagedEstimationDetailDto();
		BeanUtils.copyProperties(entity, dto);

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setState(null);
		testTarget.setEstimationUnitPrice(null);
		testTarget.setEstimationAmountSummary(null);
		testTarget.setRicohItemCode(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "状態が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setDetailAbstract(STR_256);
		testTarget.setMakerItemCode(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "摘要は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setQuantity(INT_100000);
		testTarget.setBeforeQuantity(INT_100000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最大値（99999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setQuantity(INT_MINUS_100000);
		testTarget.setBeforeQuantity(INT_MINUS_100000);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最小値（-99999）を下回っています。"));

		// 異常系（@DecimalMin：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setEstimationUnitPrice(DECIMAL_MINUS_001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "見積単価は最小値（0.00）を下回っています。"));

		// 異常系（@Digits：）
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setEstimationAmountSummary(DECIMAL_0001);
		testTarget.setEstimationUnitPrice(DECIMAL_0001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00028));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "見積金額は小数点以下2桁を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ManagedEstimationDetail.class, ManagedEstimationDetailDto.class);

	}

	@Test
	public void ContractAttachedFileHistoryDtoのテスト() throws Exception {

		ContractAttachedFileHistory entity = contractAttachedFileHistoryRepository.findById(401L).get();
		ContractAttachedFileHistoryDto dto = new ContractAttachedFileHistoryDto();
		ContractAttachedFileHistoryDto testTarget = new ContractAttachedFileHistoryDto();

		BeanUtils.copyProperties(entity, dto);
		AttachedFileDto attachedFile = new AttachedFileDto();
		BeanUtils.copyProperties(entity.getAttachedFile(), attachedFile);
		dto.setAttachedFile(attachedFile);

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setFileName(null);
		testTarget.setAttachedFile(null);
		testTarget.setAttachedEmpId(null);
		testTarget.setAttachedEmpName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "添付者氏名が設定されていません。"));

		// 異常系（@Size(max)
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setFileName(STR_256);
		testTarget.setFileKind(STR_256);
		testTarget.setAttachedComment(STR_1001);
		testTarget.setAttachedEmpId(STR_256);
		testTarget.setAttachedEmpName(STR_256);
		testTarget.setAttachedOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 6);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "添付者MoM社員IDは最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractAttachedFileHistory.class, ContractAttachedFileHistoryDto.class);
	}

	@Test
	public void ContractAttachedFileLinkageDtoのテスト() throws Exception {
		ContractAttachedFileLinkage entity = contractAttachedFileLinkageRepository.findById(401L).get();
		ContractAttachedFileLinkageDto testTarget = new ContractAttachedFileLinkageDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedFileLinkageName(null);
		testTarget.setLinkageStatus(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ファイル連携先が設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedFileLinkageName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ファイル連携先は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setDisengagementFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "解約フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setDisengagementFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "解約フラグは最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractAttachedFileLinkage.class, ContractAttachedFileLinkageDto.class);
	}

	@Test
	public void ContractAssignmentDtoのテスト() throws Exception {
		ContractAssignment entity = contractAssignmentRepository.findById(401L).get();
		ContractAssignmentDto testTarget = new ContractAssignmentDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 正常系（@Size 1001：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMemo(STR_1001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setUpdateFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "更新フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setUpdateFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "更新フラグは最小値（0）を下回っています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractAssignment.class, ContractAssignmentDto.class);
	}

	@Test
	public void ContractAssignmentAttachedFileDtoのテスト() throws Exception {
		ContractAssignmentAttachedFile entity = contractAssignmentAttachedFileRepository.findById(401L).get();
		ContractAssignmentAttachedFileDto testTarget = new ContractAssignmentAttachedFileDto();
		AttachedFileDto attachedFileDto = new AttachedFileDto();
		BeanUtils.copyProperties(entity.getAttachedFile(), attachedFileDto);

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedFile(attachedFileDto);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedFile(null);
		testTarget.setFileName(null);
		testTarget.setAttachedEmpId(null);
		testTarget.setAttachedEmpName(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "添付者MoM社員IDが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAttachedFile(attachedFileDto);
		testTarget.setFileName(STR_256);
		testTarget.setFileKind(STR_256);
		testTarget.setAttachedEmpId(STR_256);
		testTarget.setAttachedEmpName(STR_256);
		testTarget.setAttachedOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 5);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "ファイル名は最大文字数（255）を超えています。"));

		// dto-エンティティ整合性チェック※DTOクラスでは必須
		testTool.checkConsistency(ContractAssignmentAttachedFile.class, ContractAssignmentAttachedFileDto.class);
	}

	@Test
	public void ContractForFindAllDetailsBplatsDtoのテスト() throws Exception {
		Contract entity = contractRepository.findById(4L).get();
		ContractForFindAllDetailsBplatsDto testTarget = new ContractForFindAllDetailsBplatsDto();
		ContractForFindAllDetailsBplatsDto dto = new ContractForFindAllDetailsBplatsDto();

		BeanUtils.copyProperties(entity, dto, "productContractList", "contractDetailList");

		// 商品（契約用）
		ProductContractForFindAllDetailsBplatsDto product = new ProductContractForFindAllDetailsBplatsDto();
		BeanUtils.copyProperties(entity.getProductContractList().get(0), product);
		dto.setProductContractList(Arrays.asList(product));

		// 契約明細
		List<ContractDetailForFindAllDetailsBplatsDto> contractDetailList = new ArrayList<>();
		entity.getContractDetailList().stream().forEach(e -> {
			ContractDetailForFindAllDetailsBplatsDto contractDetail = new ContractDetailForFindAllDetailsBplatsDto();
			BeanUtils.copyProperties(e, dto, "itemContract");
			contractDetailList.add(contractDetail);
		});
		dto.setProductContractList(Arrays.asList(product));

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		testTarget.setDealerContractList(null);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

	}

	@Test
	public void ProductContractForFindAllDetailsBplatsDtoのテスト() throws Exception {
		ProductContract entity = productContractRepository.findById(401L).get();
		ProductContractForFindAllDetailsBplatsDto testTarget = new ProductContractForFindAllDetailsBplatsDto();
		BeanUtils.copyProperties(entity, testTarget);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

	}

	@Test
	public void ContractDetailForFindAllDetailsBplatsDtoのテスト() throws Exception {
		ContractDetail entity = contractDetailRepository.findById(401L).get();
		ContractDetailForFindAllDetailsBplatsDto dto = new ContractDetailForFindAllDetailsBplatsDto();
		ContractDetailForFindAllDetailsBplatsDto testTarget = new ContractDetailForFindAllDetailsBplatsDto();
		BeanUtils.copyProperties(entity, dto);

		ItemContractDetailForFindAllDetailsBplatsDto item = new ItemContractDetailForFindAllDetailsBplatsDto();
		BeanUtils.copyProperties(entity.getItemContract(), item);
		dto.setItemContract(item);

		// 正常系
		BeanUtils.copyProperties(dto, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);
	}

	@Test
	public void ItemContractDetailForFindAllDetailsBplatsDtoのテスト() throws Exception {
		ItemContract entity = itemContractRepository.findById(401L).get();
		ItemContractDetailForFindAllDetailsBplatsDto testTarget = new ItemContractDetailForFindAllDetailsBplatsDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);
	}

	@Test
	public void PagingDtoのテスト() throws Exception {

		PagingDto testTarget = new PagingDto();

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);
	}

	@Test
	public void ContractListDetailInfoGetBplatsDtoのテスト() throws Exception {

		ContractListDetailInfoGetBplatsDto testTarget = new ContractListDetailInfoGetBplatsDto();

		PagingDto pagingDto = new PagingDto();
		pagingDto.setTotalNum(0);
		pagingDto.setStartLine(0);
		pagingDto.setOffset(0);
		testTarget.setPagingDto(pagingDto);

		// 正常系
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);
	}

	@Test
	public void ContractEquipmentNoIsysoneDtoのテスト() throws Exception {
		ContractEquipmentNoIsysone entity = contractEquipmentNoIsysoneRepository.findById(1L).get();
		ContractEquipmentNoIsysoneDto testTarget = new ContractEquipmentNoIsysoneDto();

		//正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		//異常系(@Size(max):)
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setItemNo(STR_256);
		testTarget.setGoodsName(STR_256);
		testTarget.setEquipmentNo(STR_256);
		testTarget.setOptionName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 4);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "製品番号は最大文字数（255）を超えています。"));
	}

	@Test
	public void ShippingAddressDtoのテスト() throws Exception {
		ShippingAddress entity = shippingAddressRepository.findById(4L).get();
		ShippingAddressDto testTarget = new ShippingAddressDto();

		//正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		//異常系(@Size(max):)
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomEmployeeId(STR_256);
		testTarget.setMomOrgId(STR_256);
		testTarget.setOrgName(STR_256);
		testTarget.setSalesCompanyName(STR_256);
		testTarget.setOrgPhoneNumber(STR_1001);
		testTarget.setEmployeeName(STR_256);
		testTarget.setSalesDepartmentName(STR_256);
		testTarget.setPostNumber(STR_256);
		testTarget.setPrefectures(STR_256);
		testTarget.setCityStreet(STR_1001);
		testTarget.setPhoneNumber(STR_256);
		testTarget.setYamatoSlipNumber(STR_256);
		testTarget.setBuildingName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 13);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM社員IDは最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setOrgHierarchyLevel(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織階層レベルは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setOrgHierarchyLevel(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織階層レベルは最小値（0）を下回っています。"));
	}

	@Test
	public void ShippingAddressSsOrgDtoのテスト() throws Exception {
		ShippingAddressSsOrg entity = shippingAddressSsOrgRepository.findById(1L).get();
		ShippingAddressSsOrgDto testTarget = new ShippingAddressSsOrgDto();

		//正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNull）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomOrgId(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織MoM組織IDが設定されていません。"));

		//異常系(@Size(max):)
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomOrgId(STR_256);
		testTarget.setServiceOrgName(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "所属組織MoM組織IDは最大文字数（255）を超えています。"));
	}

	@Test
	public void PenaltyDetailContractDtoのテスト() throws Exception {
		PenaltyDetailContract entity = penaltyDetailContractRepository.findById(4L).get();
		PenaltyDetailContractDto testTarget = new PenaltyDetailContractDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@NotNullの null チェック：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setRicohItemCode(null);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 1);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00013));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "リコー品種コードが設定されていません。"));

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setItemName(STR_256);
		testTarget.setRicohItemCode(STR_256);
		testTarget.setItemType(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "品種名は最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setQuantity(INT_100000);
		testTarget.setDeleteFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "数量は最大値（99999）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setItemMasterId(INT_MINUS_1);
		testTarget.setQuantity(INT_MINUS_1);
		testTarget.setDeleteFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "品種マスタIDは最小値（0）を下回っています。"));

		// 異常系（@DecimalMin ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setPenaltyUnitPrice(DECIMAL_MINUS_001);
		testTarget.setPenaltyAmountSummary(DECIMAL_MINUS_001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "違約金単価は最小値（0.00）を下回っています。"));

		// 異常系（@Digits ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setPenaltyUnitPrice(DECIMAL_0001);
		testTarget.setPenaltyAmountSummary(DECIMAL_0001);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00028));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "違約金単価は小数点以下2桁を超えています。"));
	}

	@Test
	public void ContractEquipmentAdditionInfoDtoのテスト() throws Exception {
		ContractEquipmentAdditionInfo entity = contractEquipmentAdditionInfoRepository.findById(401L).get();
		ContractEquipmentAdditionInfoDto testTarget = new ContractEquipmentAdditionInfoDto();

		// 正常系
		BeanUtils.copyProperties(entity, testTarget);
		ParamterCheckResult result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		testTool.assertValidationOk(result);

		// 異常系（@Size(max) ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setMomCustId(STR_256);
		testTarget.setCompanyName(STR_256);
		testTarget.setOfficeName(STR_256);
		testTarget.setCompanyNameKana(STR_256);
		testTarget.setOfficeNameKana(STR_256);
		testTarget.setPostNumber(STR_256);
		testTarget.setAddress(STR_256);
		testTarget.setStreetBunch(STR_256);
		testTarget.setGoumeiName(STR_256);
		testTarget.setBuildingName(STR_256);
		testTarget.setFloor(STR_256);
		testTarget.setCrpStatusFrontRestKbn(STR_256);
		testTarget.setAddrCd(STR_256);
		testTarget.setPhoneNumber(STR_256);
		testTarget.setCrpStatusKbn(STR_256);
		testTarget.setDepartmentName(STR_256);
		testTarget.setPicName(STR_256);
		testTarget.setDepartmentNameKana(STR_256);
		testTarget.setPicNameKana(STR_256);
		testTarget.setCustomerNumber(STR_256);
		testTarget.setApplicantUserNumber(STR_256);
		testTarget.setPicMntMomOrgId(STR_256);
		testTarget.setMntOrgCd(STR_256);
		testTarget.setPicMntCeCd(STR_256);
		testTarget.setPicMntMomEmployeeId(STR_256);
		testTarget.setPicMntSalesDepartmentName(STR_256);
		testTarget.setPicMntEmployeeName(STR_256);
		testTarget.setPicMntSalesDepartmentNameKana(STR_256);
		testTarget.setPicMntEmployeeNameKana(STR_256);
		testTarget.setPicIntMomOrgId(STR_256);
		testTarget.setPicIntCeCd(STR_256);
		testTarget.setPicIntMomEmployeeId(STR_256);
		testTarget.setPicIntSalesDepartmentName(STR_256);
		testTarget.setPicIntEmployeeName(STR_256);
		testTarget.setPicIntSalesDepartmentNameKana(STR_256);
		testTarget.setPicIntEmployeeNameKana(STR_256);
		testTarget.setEquipmentContactNo(STR_256);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 37);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00014));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "MoM企事部IDは最大文字数（255）を超えています。"));

		// 異常系（@Max ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setRepresentationInstallationFlg(INT_10);
		testTarget.setCaptureFlg(INT_10);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 2);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00015));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "代表設置先フラグは最大値（9）を超えています。"));

		// 異常系（@Min ：）
		BeanUtils.copyProperties(entity, testTarget);
		testTarget.setAccumulationContractEquipmentId(INT_MINUS_1);
		testTarget.setRepresentationInstallationFlg(INT_MINUS_1);
		testTarget.setCaptureFlg(INT_MINUS_1);
		result = testSecurityController.callParameterCheck(testTarget, headersProperties, localServerPort);
		Assert.assertTrue(result.getErrorInfoList().size() == 3);
		Assert.assertTrue(testTool.errorIdMatchesAll(result.getErrorInfoList(), ParameterErrorIds.ROT00027));
		Assert.assertTrue(testTool.errorMessageMatchesOne(result.getErrorInfoList(), "累積契約機種IDは最小値（0）を下回っています。"));

	}
}
