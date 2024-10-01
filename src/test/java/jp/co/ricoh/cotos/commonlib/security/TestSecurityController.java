package jp.co.ricoh.cotos.commonlib.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement.ArrangementDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement.ArrangementPicWorkerEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement.ArrangementWorkApprovalRouteDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement.ArrangementWorkApprovalRouteNodeDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement.ArrangementWorkAttachedFileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement.ArrangementWorkAttachedFileLinkageDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement.ArrangementWorkCheckResultDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement.ArrangementWorkDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation.ArrangementResultDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.AttachedFileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CheckResultUpdateParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.communication.BounceMailHeaderDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.communication.CommunicationRegisterParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.communication.ContactDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.communication.ContactRegisterParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.communication.ContactToDto;
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
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderBasicContentsDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderBasicInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderBranchCustomerInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderContractorInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderDistributorInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderListDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderProductGroupInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderProductInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderServiceInnerInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrderSetupInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order.OrdererInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.CustomerEstimationDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.DealerEstimationDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.ElectronicContractInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationAddedEditorEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationApprovalRouteDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationApprovalRouteNodeDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationAttachedFileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationCancelParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationCheckResultDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationDetailDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationDetailRegisterParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationPicSaEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.EstimationRegisterParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.ItemEstimationDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.PenaltyDetailEstimationDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.ProductEstimationDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.external.EstimationInitialCostDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.FileImportManagementParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.LicenseInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.CasLicenseBasicInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.CasLicenseDetailInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.CasLicenseManagementInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.master.AttachedFileProductClassCheckMasterSearchParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.master.AttachedFileProductGrpCheckMasterSearchParameter;
import jp.co.ricoh.cotos.commonlib.dto.parameter.master.JsonSchemaMasterParameter;
import jp.co.ricoh.cotos.commonlib.entity.accounting.Accounting;
import jp.co.ricoh.cotos.commonlib.entity.accounting.CommissionData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.InvoiceLinkage;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestDetailData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestDetailPlanData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestPlanData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoResultsData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoResultsPlanData;
import jp.co.ricoh.cotos.commonlib.entity.accounting.UsageQuantity;
import jp.co.ricoh.cotos.commonlib.entity.accounting.UsageQuantityRelatedManagement;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.Arrangement;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementPicWorkerEmp;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkApprovalResult;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkApprovalRouteNode;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkAttachedFileLinkage;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkCheckResult;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkErrorLog;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkOperationLog;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.common.FileImportErrorDetails;
import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement;
import jp.co.ricoh.cotos.commonlib.entity.common.SearchCondition;
import jp.co.ricoh.cotos.commonlib.entity.communication.BounceMailDestination;
import jp.co.ricoh.cotos.commonlib.entity.communication.BounceMailRecord;
import jp.co.ricoh.cotos.commonlib.entity.communication.Communication;
import jp.co.ricoh.cotos.commonlib.entity.communication.CommunicationHistory;
import jp.co.ricoh.cotos.commonlib.entity.communication.Contact;
import jp.co.ricoh.cotos.commonlib.entity.communication.ContactTo;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAddedEditorEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalResult;
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
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentItemLink;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentNoIsysone;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractInstallationLocation;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractOperationLog;
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
import jp.co.ricoh.cotos.commonlib.entity.contract.ManagedContractEquipmentStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ManagedEstimationDetail;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailTrans;
import jp.co.ricoh.cotos.commonlib.entity.contract.ProductContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ShippingAddress;
import jp.co.ricoh.cotos.commonlib.entity.contract.ShippingAddressSsOrg;
import jp.co.ricoh.cotos.commonlib.entity.contract.ShippingThingDetail;
import jp.co.ricoh.cotos.commonlib.entity.estimation.CustomerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.DealerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ElectronicContractInfo;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationAddedEditorEmp;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalResult;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalRouteNode;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationCheckResult;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationPicSaEmp;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ItemEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.OperationLog;
import jp.co.ricoh.cotos.commonlib.entity.estimation.PenaltyDetailEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ProductEstimation;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfoOperationLog;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseBasicInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseDetailInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseManagementInfo;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmLinkManagement;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserResponseWork;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.security.bean.ParamterCheckResult;
import jp.co.ricoh.cotos.commonlib.util.HeadersProperties;
import lombok.Data;

/**
 *
 * テスト実施用コントローラクラス
 *
 */
@Data
@RestController
@RequestMapping("/test/api")
public class TestSecurityController {

	@Autowired
	CheckUtil checkUtil;

	private String swaggerBody = "swagger";

	@RequestMapping(method = RequestMethod.GET, path = "/test/{id}")
	@Transactional
	public String get() {
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return userInfo.getSingleUserId() + "," + userInfo.getMomEmployeeId() + "," + userInfo.getOrigin() + "," + userInfo.getApplicationId() + "," + userInfo.getJwt() + "," + userInfo.isSuperUser() + "," + Boolean.toString(userInfo.getMomAuthorities() != null);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/test")
	@Transactional
	public String post() {
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return userInfo.getSingleUserId() + "," + userInfo.getMomEmployeeId() + "," + userInfo.getOrigin() + "," + userInfo.getApplicationId() + "," + userInfo.getJwt() + "," + userInfo.isSuperUser() + "," + Boolean.toString(userInfo.getMomAuthorities() != null);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/test")
	@Transactional
	public String put() {
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return userInfo.getSingleUserId() + "," + userInfo.getMomEmployeeId() + "," + userInfo.getOrigin() + "," + userInfo.getApplicationId() + "," + userInfo.getJwt() + "," + userInfo.isSuperUser() + "," + Boolean.toString(userInfo.getMomAuthorities() != null);
	}

	@GetMapping(path = "/swagger-ui.html")
	public String swagger() {
		return swaggerBody;
	}

	private ParamterCheckResult createParameterCheckResult(BindingResult result) {
		ParamterCheckResult paramterCheckResult = new ParamterCheckResult();
		if (result == null)
			return paramterCheckResult;
		try {
			checkUtil.checkEntity(result);
		} catch (ErrorCheckException ex) {
			paramterCheckResult.setErrorInfoList(ex.getErrorInfoList());
			return paramterCheckResult;
		}
		return paramterCheckResult;
	}

	private String loadTopURL(int localServerPort) {
		return "http://localhost:" + localServerPort + "/";
	}

	private RestTemplate initRest(final String header, final HeadersProperties headersProperties) {
		RestTemplate rest = new RestTemplate();
		if (null != header) {
			rest.setInterceptors(Stream.concat(rest.getInterceptors().stream(), Arrays.asList(new ClientHttpRequestInterceptor() {
				@Override
				public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
					System.out.println("initRest Start");
					System.out.println(request.getURI());
					System.out.println(request.getMethod());
					request.getHeaders().add(headersProperties.getAuthorization(), "Bearer " + header);
					System.out.println(request.getHeaders());
					System.out.println("initRest End");
					return execution.execute(request, body);
				}
			}).stream()).collect(Collectors.toList()));
		}
		return rest;
	}

	/**
	 * パラメータチェックの URL を取得
	 *
	 * @param entity
	 *            パラメータチェック対象のエンティティ
	 * @param localServerPort
	 *            ポート番号
	 * @return String パラメータチェックの URL
	 */
	public String getParamterCheckUrl(Object entity, int localServerPort) {
		final String API_ROOT_HEAD = "test/api/ParameterCheck/";
		final String API_ROOT_END = "?isSuccess=true&hasBody=false";
		String entityName = entity.getClass().getSimpleName().replaceAll(".java", "");
		return loadTopURL(localServerPort) + API_ROOT_HEAD + entityName + API_ROOT_END;
	}

	public ParamterCheckResult callParameterCheck(Object entity, HeadersProperties headersProperties, int localServerPort) {
		String WITHIN_PERIOD_JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtb21BdXRoIjoie1wiMDNcIjp7XCIyMjEwXCI6XCIwMFwiLFwiMjIyMFwiOlwiMDBcIixcIjIyMzBcIjpcIjEwXCIsXCIyMjAwXCI6XCI1MFwifSxcIjA1XCI6e1wiMjIxMFwiOlwiMDBcIixcIjIyMjBcIjpcIjAwXCIsXCIyMjMwXCI6XCIxMFwiLFwiMjIwMFwiOlwiNTBcIn0sXCIwN1wiOntcIjIyMTBcIjpcIjAwXCIsXCIyMjIwXCI6XCIwMFwiLFwiMjIzMFwiOlwiMTBcIixcIjIyMDBcIjpcIjUwXCJ9LFwiMDFcIjp7XCIyMjEwXCI6XCIwMFwiLFwiMjIyMFwiOlwiMDBcIixcIjIyMzBcIjpcIjEwXCIsXCIyMjAwXCI6XCI1MFwifSxcIjA0XCI6e1wiMjIxMFwiOlwiMDBcIixcIjIyMjBcIjpcIjAwXCIsXCIyMjMwXCI6XCIxMFwiLFwiMjIwMFwiOlwiNTBcIn0sXCIwNlwiOntcIjIyMTBcIjpcIjAwXCIsXCIyMjIwXCI6XCIwMFwiLFwiMjIzMFwiOlwiMTBcIixcIjIyMDBcIjpcIjUwXCJ9LFwiMDJcIjp7XCIyMjEwXCI6XCIwMFwiLFwiMjIyMFwiOlwiMDBcIixcIjIyMzBcIjpcIjEwXCIsXCIyMjAwXCI6XCI1MFwifX0iLCJvcmlnaW4iOiJodHRwczovL2Rldi5jb3Rvcy5yaWNvaC5jby5qcCIsInNpbmdsZVVzZXJJZCI6InUwMjAxMTI1IiwibW9tRW1wSWQiOiIwMDIyOTc0NiIsImV4cCI6MjUzNDAyMjY4Mzk5LCJhcHBsaWNhdGlvbklkIjoiY290b3NfZGV2In0.DVREQfy-8H2hOAX44ktBfi8IVKB45I43dinEN_a8I5E";
		RestTemplate rest = initRest(WITHIN_PERIOD_JWT, headersProperties);
		ResponseEntity<ParamterCheckResult> parameterCheckResult = rest.postForEntity(getParamterCheckUrl(entity, localServerPort), entity, ParamterCheckResult.class);
		return parameterCheckResult.getBody();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/Arrangement")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated Arrangement entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementPicWorkerEmp")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementPicWorkerEmp entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkApprovalResult")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkApprovalResult entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkApprovalRoute")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkApprovalRoute entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkApprovalRouteNode")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkApprovalRouteNode entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkAttachedFile")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkAttachedFile entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkCheckResult")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkCheckResult entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkOperationLog")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkOperationLog entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkErrorLog")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkErrorLog entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkApprovalRouteDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkApprovalRouteDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkApprovalRouteNodeDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkApprovalRouteNodeDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkAttachedFileDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkAttachedFileDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkCheckResultDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkCheckResultDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementPicWorkerEmpDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementPicWorkerEmpDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	// 以下、communication パッケージの callParamterCheck
	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/Communication")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated Communication entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CommunicationHistory")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CommunicationHistory entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/Contact")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated Contact entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContactTo")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContactTo entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/BounceMailRecord")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated BounceMailRecord entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/BounceMailDestination")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated BounceMailDestination entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContactDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContactDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContactToDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContactToDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/BounceMailHeaderDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated BounceMailHeaderDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	// 以下、contact パッケージの callParamterCheck
	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/Contract")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated Contract entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAddedEditorEmp")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAddedEditorEmp entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAddedEditorEmpDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAddedEditorEmpDto Dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractApprovalResult")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractApprovalResult entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractApprovalRoute")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractApprovalRoute entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractApprovalRouteDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractApprovalRouteDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractApprovalRouteNode")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractApprovalRouteNode entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractApprovalRouteNodeDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractApprovalRouteNodeDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAttachedFile")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAttachedFile entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAttachedFileDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAttachedFileDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAttachedFileHistory")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAttachedFileHistory entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAttachedFileHistoryDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAttachedFileHistoryDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractCheckResult")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractCheckResult entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractCheckResultDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractCheckResultDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractDetail")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractDetail entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractDetailDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractDetailDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractEquipment")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractEquipment entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractEquipmentDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractEquipmentDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractOperationLog")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractOperationLog entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicSaEmp")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicSaEmp entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicSaEmpDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicSaEmpDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicMntCeEmpDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicMntCeEmpDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	// TODO
	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicIntCeEmpDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicIntCeEmpDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicAccCeEmpDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicAccCeEmpDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CustomerContract")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CustomerContract entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CustomerContractDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CustomerContractDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractInstallationLocation")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractInstallationLocation entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractInstallationLocationDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractInstallationLocationDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/DealerContract")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated DealerContract entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/DealerContractDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated DealerContractDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ItemContract")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ItemContract entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ItemContractDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ItemContractDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ItemDetailContract")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ItemDetailContract entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ItemDetailContractDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ItemDetailContractDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ProductContract")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ProductContract entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ProductContractDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ProductContractDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	// 以下、estimation パッケージの callParamterCheck
	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CustomerEstimation")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CustomerEstimation entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CustomerEstimationDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CustomerEstimationDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/DealerEstimation")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated DealerEstimation entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/DealerEstimationDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated DealerEstimationDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/Estimation")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated Estimation entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationAddedEditorEmp")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationAddedEditorEmp entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationAddedEditorEmpDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationAddedEditorEmpDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationApprovalResult")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationApprovalResult entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationApprovalRoute")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationApprovalRoute entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationApprovalRouteDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationApprovalRouteDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationApprovalRouteNode")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationApprovalRouteNode entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationApprovalRouteNodeDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationApprovalRouteNodeDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationAttachedFile")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationAttachedFile entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationAttachedFileDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationAttachedFileDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationCheckResult")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationCheckResult entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationCheckResultDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationCheckResultDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationDetail")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationDetail entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationDetailDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationDetailDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationPicSaEmp")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationPicSaEmp entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationPicSaEmpDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationPicSaEmpDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ItemEstimation")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ItemEstimation entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ItemEstimationDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ItemEstimationDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OperationLog")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OperationLog entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ProductEstimation")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ProductEstimation entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ProductEstimationDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ProductEstimationDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/AttachedFile")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated AttachedFile entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/AttachedFileDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated AttachedFileDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/SearchCondition")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated SearchCondition entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/Accounting")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated Accounting entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicMntCeEmp")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicMntCeEmp entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicIntCeEmp")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicIntCeEmp entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicAccCeEmp")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicAccCeEmp entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicMntSsOrg")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicMntSsOrg entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicMntSsOrgDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicMntSsOrgDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicAccSsOrg")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicAccSsOrg entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicAccSsOrgDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicAccSsOrgDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicIntSsOrg")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicIntSsOrg entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractPicIntSsOrgDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractPicIntSsOrgDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationRegisterParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationRegisterParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationDetailRegisterParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationDetailRegisterParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationCancelParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationCancelParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CheckResultUpdateParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CheckResultUpdateParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CommunicationRegisterParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CommunicationRegisterParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContactRegisterParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContactRegisterParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractExtCreateDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractExtCreateDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractExtChangeDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractExtChangeDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractExtCancelDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractExtCancelDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ProductContractExtCreateDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ProductContractExtCreateDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractForFindAllDetailsDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractForFindAllDetailsDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ProductContractForFindAllDetailsDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ProductContractForFindAllDetailsDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ManagedEstimationDetail")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ManagedEstimationDetail entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ManagedEstimationDetailDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ManagedEstimationDetailDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderSetupInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderSetupInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderServiceInnerInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderServiceInnerInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderProductInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderProductInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderProductGroupInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderProductGroupInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrdererInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrdererInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderDistributorInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderDistributorInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderContractorInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderContractorInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderBranchCustomerInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderBranchCustomerInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderBasicInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderBasicInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderListDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderListDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementResultDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementResultDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OrderBasicContentsDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OrderBasicContentsDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/EstimationInitialCostDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated EstimationInitialCostDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/JsonSchemaMasterParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated JsonSchemaMasterParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/AttachedFileProductClassCheckMasterSearchParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated AttachedFileProductClassCheckMasterSearchParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/AttachedFileProductGrpCheckMasterSearchParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated AttachedFileProductGrpCheckMasterSearchParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkAttachedFileLinkage")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkAttachedFileLinkage entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ArrangementWorkAttachedFileLinkageDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ArrangementWorkAttachedFileLinkageDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAttachedFileLinkage")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAttachedFileLinkage entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAttachedFileLinkageDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAttachedFileLinkageDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAssignment")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAssignment entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAssignmentDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAssignmentDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAssignmentAttachedFile")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAssignmentAttachedFile entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractAssignmentAttachedFileDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractAssignmentAttachedFileDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CommissionData")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CommissionData entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OsoRequestData")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OsoRequestData entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OsoRequestDetailData")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OsoRequestDetailData entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OsoRequestDetailPlanData")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OsoRequestDetailPlanData entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OsoRequestPlanData")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OsoRequestPlanData entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OsoResultsData")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OsoResultsData entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/OsoResultsPlanData")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated OsoResultsPlanData entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/UsageQuantity")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated UsageQuantity entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/UsageQuantityRelatedManagement")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated UsageQuantityRelatedManagement entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/InvoiceLinkage")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated InvoiceLinkage entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractForFindAllDetailsBplatsDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractForFindAllDetailsBplatsDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ProductContractForFindAllDetailsBplatsDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ProductContractForFindAllDetailsBplatsDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractDetailForFindAllDetailsBplatsDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractDetailForFindAllDetailsBplatsDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ItemContractDetailForFindAllDetailsBplatsDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ItemContractDetailForFindAllDetailsBplatsDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/PagingDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated PagingDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractListDetailInfoGetBplatsDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractListDetailInfoGetBplatsDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CasLicenseBasicInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CasLicenseBasicInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CasLicenseManagementInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CasLicenseManagementInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CasLicenseDetailInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CasLicenseDetailInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CasLicenseBasicInfo")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CasLicenseBasicInfo entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CasLicenseManagementInfo")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CasLicenseManagementInfo entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/CasLicenseDetailInfo")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated CasLicenseDetailInfo entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmLinkManagement")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmLinkManagement entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmCreateCustomerRequestWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmCreateCustomerRequestWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmCreateCustomerResponseWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmCreateCustomerResponseWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmCreateSubscriptionRequestWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmCreateSubscriptionRequestWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmCreateSubscriptionResponseWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmCreateSubscriptionResponseWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmSuspendSubscriptionRequestWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmSuspendSubscriptionRequestWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmSuspendSubscriptionResponseWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmSuspendSubscriptionResponseWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmUpdateCustomerRequestWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmUpdateCustomerRequestWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmUpdateCustomerResponseWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmUpdateCustomerResponseWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmUpdateSubscriptionRequestWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmUpdateSubscriptionRequestWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmUpdateSubscriptionResponseWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmUpdateSubscriptionResponseWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmUpdateUserRequestWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmUpdateUserRequestWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/TmUpdateUserResponseWork")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated TmUpdateUserResponseWork entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ShippingAddress")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ShippingAddress entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ShippingAddressSsOrg")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ShippingAddressSsOrg entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractEquipmentItemLink")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractEquipmentItemLink entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractEquipmentNoIsysone")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractEquipmentNoIsysone entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ShippingThingDetail")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ShippingThingDetail entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ManagedContractEquipmentStatus")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ManagedContractEquipmentStatus entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/PenaltyDetailTrans")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated PenaltyDetailTrans entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/LicenseInfo")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated LicenseInfo entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/LicenseRemainingNumber")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated LicenseRemainingNumber entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/LicenseInfoOperationLog")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated LicenseInfoOperationLog entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/LicenseProcess")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated LicenseProcess entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/LicenseDetail")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated LicenseDetail entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/FileImportManagement")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated FileImportManagement entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/FileImportErrorDetails")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated FileImportErrorDetails entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/PenaltyDetailEstimation")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated PenaltyDetailEstimation entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/PenaltyDetailEstimationDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated PenaltyDetailEstimationDto entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/PenaltyDetailContract")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated PenaltyDetailContract entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ContractEquipmentNoIsysoneDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ContractEquipmentNoIsysoneDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ShippingAddressDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ShippingAddressDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ShippingAddressSsOrgDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ShippingAddressSsOrgDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/LicenseInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated LicenseInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/FileImportManagementParameter")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated FileImportManagementParameter dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/PenaltyDetailContractDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated PenaltyDetailContractDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ElectronicContractInfo")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ElectronicContractInfo entity, BindingResult result) {
		return createParameterCheckResult(result);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ParameterCheck/ElectronicContractInfoDto")
	public ParamterCheckResult callParamterCheck(@RequestBody @Validated ElectronicContractInfoDto dto, BindingResult result) {
		return createParameterCheckResult(result);
	}
}
