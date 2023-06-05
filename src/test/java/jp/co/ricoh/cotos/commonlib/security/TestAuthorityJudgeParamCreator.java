package jp.co.ricoh.cotos.commonlib.security;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.AuthorityJudgeParameter;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ApprovalProcessCategory;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkApprovalRouteNode;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAddedEditorEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalResult;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalRouteNode;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicSaEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.CustomerContract;
import jp.co.ricoh.cotos.commonlib.entity.estimation.CustomerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationAddedEditorEmp;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalRouteNode;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationPicSaEmp;
import jp.co.ricoh.cotos.commonlib.entity.master.ApprovalRouteNodeMaster.ApproverDeriveMethodDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AccessType;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;
import jp.co.ricoh.cotos.commonlib.security.util.AuthorityJudgeParamCreator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestAuthorityJudgeParamCreator {

	@Autowired
	AuthorityJudgeParamCreator authorityJudgeParamCreator;

	@Autowired
	MvEmployeeMasterRepository mvEmployeeMasterRepository;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void 正常_権限判定用パラメーター取得_見積_参照() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 見積
		Estimation estimation = new Estimation();

		// 承認ルート
		estimation.setEstimationApprovalRoute(new EstimationApprovalRoute());
		estimation.getEstimationApprovalRoute().setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<EstimationApprovalRouteNode> estimationApprovalRouteNodeList = new ArrayList<>();
		EstimationApprovalRouteNode estimationApprovalRouteNode = new EstimationApprovalRouteNode();
		estimationApprovalRouteNode.setApproverEmpId("00231268");
		estimationApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		estimationApprovalRouteNodeList.add(estimationApprovalRouteNode);
		estimation.getEstimationApprovalRoute().setEstimationApprovalRouteNodeList(estimationApprovalRouteNodeList);

		// 担当SA
		estimation.setEstimationPicSaEmp(new EstimationPicSaEmp());
		estimation.getEstimationPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		estimation.setEstimationAddedEditorEmpList(new ArrayList<EstimationAddedEditorEmp>());
		estimation.getEstimationAddedEditorEmpList().add(new EstimationAddedEditorEmp());
		estimation.getEstimationAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		estimation.setCustomerEstimation(new CustomerEstimation());
		estimation.getCustomerEstimation().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromEstimation(estimation, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_見積_参照_グループ承認() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 見積
		Estimation estimation = new Estimation();

		// 承認ルート
		EstimationApprovalRoute estimationApprovalRoute = new EstimationApprovalRoute();
		estimationApprovalRoute.setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<EstimationApprovalRouteNode> estimationApprovalRouteNodeList = new ArrayList<>();
		EstimationApprovalRouteNode estimationApprovalRouteNode = new EstimationApprovalRouteNode();
		estimationApprovalRouteNode.setApproverEmpId("TORUNO001");
		estimationApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		estimationApprovalRouteNodeList.add(estimationApprovalRouteNode);
		estimationApprovalRoute.setEstimationApprovalRouteNodeList(estimationApprovalRouteNodeList);
		estimation.setEstimationApprovalRoute(estimationApprovalRoute);

		// 担当SA
		EstimationPicSaEmp estimationPicSaEmp = new EstimationPicSaEmp();
		estimationPicSaEmp.setMomEmployeeId("00500784");
		estimation.setEstimationPicSaEmp(estimationPicSaEmp);

		// 追加編集者
		List<EstimationAddedEditorEmp> estimationAddedEditorEmpList = new ArrayList<>();
		EstimationAddedEditorEmp estimationAddedEditorEmp = new EstimationAddedEditorEmp();
		estimationAddedEditorEmp.setMomEmployeeId("00500784");
		estimationAddedEditorEmpList.add(estimationAddedEditorEmp);
		estimation.setEstimationAddedEditorEmpList(estimationAddedEditorEmpList);

		// 顧客
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId("000000003985825");
		estimation.setCustomerEstimation(customerEstimation);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromEstimation(estimation, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_見積_参照_社員非存在() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 見積
		Estimation estimation = new Estimation();

		// 承認ルート
		estimation.setEstimationApprovalRoute(new EstimationApprovalRoute());
		estimation.getEstimationApprovalRoute().setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<EstimationApprovalRouteNode> estimationApprovalRouteNodeList = new ArrayList<>();
		EstimationApprovalRouteNode estimationApprovalRouteNode = new EstimationApprovalRouteNode();
		estimationApprovalRouteNode.setApproverEmpId("00231268A");
		estimationApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		estimationApprovalRouteNodeList.add(estimationApprovalRouteNode);
		estimation.getEstimationApprovalRoute().setEstimationApprovalRouteNodeList(estimationApprovalRouteNodeList);

		// 担当SA
		estimation.setEstimationPicSaEmp(new EstimationPicSaEmp());
		estimation.getEstimationPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		estimation.setEstimationAddedEditorEmpList(new ArrayList<EstimationAddedEditorEmp>());
		estimation.getEstimationAddedEditorEmpList().add(new EstimationAddedEditorEmp());
		estimation.getEstimationAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		estimation.setCustomerEstimation(new CustomerEstimation());
		estimation.getCustomerEstimation().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromEstimation(estimation, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertTrue("承認者の社員情報が作成されていないこと", authParam.getApproverMvEmployeeMasterList().isEmpty());
		Assert.assertNull("次回承認者の社員情報が作成されていないこと", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_見積_参照_グループ承認_社員非存在() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator_社員非存在.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 見積
		Estimation estimation = new Estimation();

		// 承認ルート
		EstimationApprovalRoute estimationApprovalRoute = new EstimationApprovalRoute();
		estimationApprovalRoute.setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<EstimationApprovalRouteNode> estimationApprovalRouteNodeList = new ArrayList<>();
		EstimationApprovalRouteNode estimationApprovalRouteNode = new EstimationApprovalRouteNode();
		estimationApprovalRouteNode.setApproverEmpId("TORUNO001");
		estimationApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		estimationApprovalRouteNodeList.add(estimationApprovalRouteNode);
		estimationApprovalRoute.setEstimationApprovalRouteNodeList(estimationApprovalRouteNodeList);
		estimation.setEstimationApprovalRoute(estimationApprovalRoute);

		// 担当SA
		EstimationPicSaEmp estimationPicSaEmp = new EstimationPicSaEmp();
		estimationPicSaEmp.setMomEmployeeId("00500784");
		estimation.setEstimationPicSaEmp(estimationPicSaEmp);

		// 追加編集者
		List<EstimationAddedEditorEmp> estimationAddedEditorEmpList = new ArrayList<>();
		EstimationAddedEditorEmp estimationAddedEditorEmp = new EstimationAddedEditorEmp();
		estimationAddedEditorEmp.setMomEmployeeId("00500784");
		estimationAddedEditorEmpList.add(estimationAddedEditorEmp);
		estimation.setEstimationAddedEditorEmpList(estimationAddedEditorEmpList);

		// 顧客
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId("000000003985825");
		estimation.setCustomerEstimation(customerEstimation);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromEstimation(estimation, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertTrue("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList().size() == 3);
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_見積_承認() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 見積
		Estimation estimation = new Estimation();

		// 承認ルート
		estimation.setEstimationApprovalRoute(new EstimationApprovalRoute());
		estimation.getEstimationApprovalRoute().setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<EstimationApprovalRouteNode> estimationApprovalRouteNodeList = new ArrayList<>();
		EstimationApprovalRouteNode estimationApprovalRouteNode = new EstimationApprovalRouteNode();
		estimationApprovalRouteNode.setApproverEmpId("00231268");
		estimationApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		estimationApprovalRouteNodeList.add(estimationApprovalRouteNode);
		estimation.getEstimationApprovalRoute().setEstimationApprovalRouteNodeList(estimationApprovalRouteNodeList);

		// 担当SA
		estimation.setEstimationPicSaEmp(new EstimationPicSaEmp());
		estimation.getEstimationPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		estimation.setEstimationAddedEditorEmpList(new ArrayList<EstimationAddedEditorEmp>());
		estimation.getEstimationAddedEditorEmpList().add(new EstimationAddedEditorEmp());
		estimation.getEstimationAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		estimation.setCustomerEstimation(new CustomerEstimation());
		estimation.getCustomerEstimation().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromEstimation(estimation, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_見積_承認_ユーザー直接指定() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 見積
		Estimation estimation = new Estimation();

		// 承認ルート
		estimation.setEstimationApprovalRoute(new EstimationApprovalRoute());
		estimation.getEstimationApprovalRoute().setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<EstimationApprovalRouteNode> estimationApprovalRouteNodeList = new ArrayList<>();
		EstimationApprovalRouteNode estimationApprovalRouteNode = new EstimationApprovalRouteNode();
		estimationApprovalRouteNode.setApproverEmpId("00500784");
		estimationApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.ユーザー直接指定);
		estimationApprovalRouteNodeList.add(estimationApprovalRouteNode);
		estimation.getEstimationApprovalRoute().setEstimationApprovalRouteNodeList(estimationApprovalRouteNodeList);

		// 担当SA
		estimation.setEstimationPicSaEmp(new EstimationPicSaEmp());
		estimation.getEstimationPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		estimation.setEstimationAddedEditorEmpList(new ArrayList<EstimationAddedEditorEmp>());
		estimation.getEstimationAddedEditorEmpList().add(new EstimationAddedEditorEmp());
		estimation.getEstimationAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		estimation.setCustomerEstimation(new CustomerEstimation());
		estimation.getCustomerEstimation().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromEstimation(estimation, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertTrue("ユーザー直接指定であること", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_見積_承認_グループ承認() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 見積
		Estimation estimation = new Estimation();

		// 承認ルート
		EstimationApprovalRoute estimationApprovalRoute = new EstimationApprovalRoute();
		estimationApprovalRoute.setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<EstimationApprovalRouteNode> estimationApprovalRouteNodeList = new ArrayList<>();
		EstimationApprovalRouteNode estimationApprovalRouteNode = new EstimationApprovalRouteNode();
		estimationApprovalRouteNode.setApproverEmpId("TORUNO001");
		estimationApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		estimationApprovalRouteNodeList.add(estimationApprovalRouteNode);
		estimationApprovalRoute.setEstimationApprovalRouteNodeList(estimationApprovalRouteNodeList);
		estimation.setEstimationApprovalRoute(estimationApprovalRoute);

		// 担当SA
		EstimationPicSaEmp estimationPicSaEmp = new EstimationPicSaEmp();
		estimationPicSaEmp.setMomEmployeeId("00500784");
		estimation.setEstimationPicSaEmp(estimationPicSaEmp);

		// 追加編集者
		List<EstimationAddedEditorEmp> estimationAddedEditorEmpList = new ArrayList<>();
		EstimationAddedEditorEmp estimationAddedEditorEmp = new EstimationAddedEditorEmp();
		estimationAddedEditorEmp.setMomEmployeeId("00500784");
		estimationAddedEditorEmpList.add(estimationAddedEditorEmp);
		estimation.setEstimationAddedEditorEmpList(estimationAddedEditorEmpList);

		// 顧客
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId("000000003985825");
		estimation.setCustomerEstimation(customerEstimation);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromEstimation(estimation, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
		Assert.assertTrue("グループ承認であること", authParam.isGroupApproval());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		contract.setContractApprovalRouteList(new ArrayList<>());
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("00231268");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contract.getContractApprovalRouteList().add(contractApprovalRoute);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照_グループ承認かつ最終承認() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成完了);

		// 担当SA
		ContractPicSaEmp contractPicSaEmp = new ContractPicSaEmp();
		contractPicSaEmp.setMomEmployeeId("00500784");
		contract.setContractPicSaEmp(contractPicSaEmp);

		// 顧客
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000003985825");
		contract.setCustomerContract(customerContract);

		// 承認ルート
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		List<ContractApprovalRoute> contractApprovalRouteList = new ArrayList<>();

		// 承認ルートノード1(通常承認)
		ContractApprovalRouteNode contractApprovalRouteNode1 = new ContractApprovalRouteNode();
		contractApprovalRouteNode1.setId(1L);
		contractApprovalRouteNode1.setApproverEmpId("00231268");
		contractApprovalRouteNode1.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		contractApprovalRouteNode1.setApprovalOrder(1);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode1);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);

		// 承認ルートノード2(グループ承認)
		ContractApprovalRouteNode contractApprovalRouteNode2 = new ContractApprovalRouteNode();
		contractApprovalRouteNode2.setId(2L);
		contractApprovalRouteNode2.setApproverEmpId("TORUNO001");
		contractApprovalRouteNode2.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		contractApprovalRouteNode2.setApprovalOrder(2);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode2);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);

		List<ContractApprovalResult> contractApprovalResultList = new ArrayList<>();

		// 承認実績1(通常承認)
		ContractApprovalResult contractApprovalResult1 = new ContractApprovalResult();
		contractApprovalResult1.setApprovalProcessCategory(ApprovalProcessCategory.承認);
		contractApprovalResult1.setContractApprovalRouteNodeId(1L);

		// 承認実績2(グループ承認)
		ContractApprovalResult contractApprovalResult2 = new ContractApprovalResult();
		contractApprovalResult2.setApprovalProcessCategory(ApprovalProcessCategory.承認);
		contractApprovalResult2.setContractApprovalRouteNodeId(2L);

		// 承認実績は先頭にくるほど新しい実績のためグループ承認が最終の承認実績となるように設定する
		contractApprovalResultList.add(contractApprovalResult2);
		contractApprovalResultList.add(contractApprovalResult1);

		contractApprovalRoute.setContractApprovalResultList(contractApprovalResultList);
		contractApprovalRouteList.add(contractApprovalRoute);
		contract.setContractApprovalRouteList(contractApprovalRouteList);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.編集);

		Assert.assertEquals("グループ承認フラグがONになっていること", true, authParam.isGroupApproval());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照_ライフサイクル状態_作成完了_対象ライフサイクル状態_作成中() {
		契約ライフサイクル状態と対象ライフサイクル状態が異なる場合(LifecycleStatus.作成完了, LifecycleStatus.作成中);
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照_ライフサイクル状態_キャンセル手続き中_対象ライフサイクル状態_キャンセル手続き中() {
		// 対象ライフサイクル状態がキャンセル手続き中になるのは契約のライクサイクル状態がキャンセル手続き中のみのため、キャンセル手続き中でテストを行う
		契約ライフサイクル状態と対象ライフサイクル状態が異なる場合(LifecycleStatus.キャンセル手続き中, LifecycleStatus.キャンセル手続き中);
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照_ライフサイクル状態_解約予定日待ち_対象ライフサイクル状態_解約手続き中() {
		契約ライフサイクル状態と対象ライフサイクル状態が異なる場合(LifecycleStatus.解約予定日待ち, LifecycleStatus.解約手続き中);
	}

	private void 契約ライフサイクル状態と対象ライフサイクル状態が異なる場合(LifecycleStatus contractLifecycleStatus, LifecycleStatus targetLifecycleStatus) {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(contractLifecycleStatus);

		// 承認ルート
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(targetLifecycleStatus);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		List<ContractApprovalRoute> contractApprovalRouteList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("00231268");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contractApprovalRouteList.add(contractApprovalRoute);
		contract.setContractApprovalRouteList(contractApprovalRouteList);

		// 担当SA
		ContractPicSaEmp contractPicSaEmp = new ContractPicSaEmp();
		contractPicSaEmp.setMomEmployeeId("00500784");
		contract.setContractPicSaEmp(contractPicSaEmp);

		// 追加編集者
		List<ContractAddedEditorEmp> contractAddedEditorEmpList = new ArrayList<>();
		ContractAddedEditorEmp contractAddedEditorEmp = new ContractAddedEditorEmp();
		contractAddedEditorEmp.setMomEmployeeId("00500784");
		contractAddedEditorEmpList.add(contractAddedEditorEmp);
		contract.setContractAddedEditorEmpList(contractAddedEditorEmpList);

		// 顧客
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000003985825");
		contract.setCustomerContract(customerContract);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.編集);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照_グループ承認() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		List<ContractApprovalRoute> contractApprovalRouteList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("TORUNO001");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contractApprovalRouteList.add(contractApprovalRoute);
		contract.setContractApprovalRouteList(contractApprovalRouteList);

		// 担当SA
		ContractPicSaEmp contractPicSaEmp = new ContractPicSaEmp();
		contractPicSaEmp.setMomEmployeeId("00500784");
		contract.setContractPicSaEmp(contractPicSaEmp);

		// 追加編集者
		List<ContractAddedEditorEmp> contractAddedEditorEmpList = new ArrayList<>();
		ContractAddedEditorEmp contractAddedEditorEmp = new ContractAddedEditorEmp();
		contractAddedEditorEmp.setMomEmployeeId("00500784");
		contractAddedEditorEmpList.add(contractAddedEditorEmp);
		contract.setContractAddedEditorEmpList(contractAddedEditorEmpList);

		// 顧客
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000003985825");
		contract.setCustomerContract(customerContract);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
		Assert.assertFalse("受付担当CE指定でないこと", authParam.isPicAccCeApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照_承認ルート無し() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成完了);

		// 承認ルート
		contract.setContractApprovalRouteList(new ArrayList<>());
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.キャンセル手続き中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("00231268");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contract.getContractApprovalRouteList().add(contractApprovalRoute);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNull("正常に承認者の社員情報が作成されていないこと", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNull("正常に次回承認者の社員情報が作成されていないこと", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照_社員非存在() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		contract.setContractApprovalRouteList(new ArrayList<>());
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("00231268A");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contract.getContractApprovalRouteList().add(contractApprovalRoute);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertTrue("承認者の社員情報が作成されていないこと", authParam.getApproverMvEmployeeMasterList().isEmpty());
		Assert.assertNull("次回承認者の社員情報が作成されていないこと", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_参照_グループ承認_社員非存在() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator_社員非存在.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		List<ContractApprovalRoute> contractApprovalRouteList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("TORUNO001");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contractApprovalRouteList.add(contractApprovalRoute);
		contract.setContractApprovalRouteList(contractApprovalRouteList);

		// 担当SA
		ContractPicSaEmp contractPicSaEmp = new ContractPicSaEmp();
		contractPicSaEmp.setMomEmployeeId("00500784");
		contract.setContractPicSaEmp(contractPicSaEmp);

		// 追加編集者
		List<ContractAddedEditorEmp> contractAddedEditorEmpList = new ArrayList<>();
		ContractAddedEditorEmp contractAddedEditorEmp = new ContractAddedEditorEmp();
		contractAddedEditorEmp.setMomEmployeeId("00500784");
		contractAddedEditorEmpList.add(contractAddedEditorEmp);
		contract.setContractAddedEditorEmpList(contractAddedEditorEmpList);

		// 顧客
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000003985825");
		contract.setCustomerContract(customerContract);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertTrue("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList().size() == 3);
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
		Assert.assertFalse("受付担当CE指定でないこと", authParam.isPicAccCeApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_承認() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		contract.setContractApprovalRouteList(new ArrayList<>());
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("00231268");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contract.getContractApprovalRouteList().add(contractApprovalRoute);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_承認_ユーザー直接指定() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		contract.setContractApprovalRouteList(new ArrayList<>());
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("00500784");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.ユーザー直接指定);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contract.getContractApprovalRouteList().add(contractApprovalRoute);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertTrue("ユーザー直接指定であること", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_承認_自己承認() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		contract.setContractApprovalRouteList(new ArrayList<>());
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("00500784");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.自己承認);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contract.getContractApprovalRouteList().add(contractApprovalRoute);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定であること", authParam.isManualApprover());
		Assert.assertTrue("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_承認_受付担当CE() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		contract.setContractApprovalRouteList(new ArrayList<>());
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("00500784");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.受付担当CE指定);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contract.getContractApprovalRouteList().add(contractApprovalRoute);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
		Assert.assertTrue("受付担当CE指定であること", authParam.isPicAccCeApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_契約_承認_グループ承認() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルート
		ContractApprovalRoute contractApprovalRoute = new ContractApprovalRoute();
		contractApprovalRoute.setApprovalRequesterEmpId("00500784");
		contractApprovalRoute.setTargetLifecycleStatus(LifecycleStatus.作成中);

		// 承認ルートノード
		List<ContractApprovalRouteNode> contractApprovalRouteNodeList = new ArrayList<>();
		List<ContractApprovalRoute> contractApprovalRouteList = new ArrayList<>();
		ContractApprovalRouteNode contractApprovalRouteNode = new ContractApprovalRouteNode();
		contractApprovalRouteNode.setApproverEmpId("TORUNO001");
		contractApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		contractApprovalRouteNodeList.add(contractApprovalRouteNode);
		contractApprovalRoute.setContractApprovalRouteNodeList(contractApprovalRouteNodeList);
		contractApprovalRouteList.add(contractApprovalRoute);
		contract.setContractApprovalRouteList(contractApprovalRouteList);

		// 担当SA
		ContractPicSaEmp contractPicSaEmp = new ContractPicSaEmp();
		contractPicSaEmp.setMomEmployeeId("00500784");
		contract.setContractPicSaEmp(contractPicSaEmp);

		// 追加編集者
		List<ContractAddedEditorEmp> contractAddedEditorEmpList = new ArrayList<>();
		ContractAddedEditorEmp contractAddedEditorEmp = new ContractAddedEditorEmp();
		contractAddedEditorEmp.setMomEmployeeId("00500784");
		contractAddedEditorEmpList.add(contractAddedEditorEmp);
		contract.setContractAddedEditorEmpList(contractAddedEditorEmpList);

		// 顧客
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000003985825");
		contract.setCustomerContract(customerContract);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromContract(contract, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
		Assert.assertFalse("受付担当CE指定でないこと", authParam.isPicAccCeApprover());
		Assert.assertTrue("グループ承認であること", authParam.isGroupApproval());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_手配_参照() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 手配業務
		ArrangementWork arrangementWork = new ArrangementWork();

		// 承認ルート
		arrangementWork.setArrangementWorkApprovalRoute(new ArrangementWorkApprovalRoute());
		arrangementWork.getArrangementWorkApprovalRoute().setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<ArrangementWorkApprovalRouteNode> arrangementWorkApprovalRouteNodeList = new ArrayList<>();
		ArrangementWorkApprovalRouteNode arrangementWorkApprovalRouteNode = new ArrangementWorkApprovalRouteNode();
		arrangementWorkApprovalRouteNode.setApproverEmpId("00231268");
		arrangementWorkApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		arrangementWorkApprovalRouteNodeList.add(arrangementWorkApprovalRouteNode);
		arrangementWork.getArrangementWorkApprovalRoute().setArrangementWorkApprovalRouteNodeList(arrangementWorkApprovalRouteNodeList);

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromArrangementWork(arrangementWork, contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_手配_参照_グループ承認() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 手配業務
		ArrangementWork arrangementWork = new ArrangementWork();

		// 承認ルート
		ArrangementWorkApprovalRoute arrangementWorkApprovalRoute = new ArrangementWorkApprovalRoute();
		arrangementWorkApprovalRoute.setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<ArrangementWorkApprovalRouteNode> arrangementWorkApprovalRouteNodeList = new ArrayList<>();
		ArrangementWorkApprovalRouteNode arrangementWorkApprovalRouteNode = new ArrangementWorkApprovalRouteNode();
		arrangementWorkApprovalRouteNode.setApproverEmpId("TORUNO001");
		arrangementWorkApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		arrangementWorkApprovalRouteNodeList.add(arrangementWorkApprovalRouteNode);
		arrangementWorkApprovalRoute.setArrangementWorkApprovalRouteNodeList(arrangementWorkApprovalRouteNodeList);
		arrangementWork.setArrangementWorkApprovalRoute(arrangementWorkApprovalRoute);

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 担当SA
		ContractPicSaEmp contractPicSaEmp = new ContractPicSaEmp();
		contractPicSaEmp.setMomEmployeeId("00500784");
		contract.setContractPicSaEmp(contractPicSaEmp);

		// 追加編集者
		List<ContractAddedEditorEmp> contractAddedEditorEmpList = new ArrayList<>();
		ContractAddedEditorEmp contractAddedEditorEmp = new ContractAddedEditorEmp();
		contractAddedEditorEmp.setMomEmployeeId("00500784");
		contractAddedEditorEmpList.add(contractAddedEditorEmp);
		contract.setContractAddedEditorEmpList(contractAddedEditorEmpList);

		// 顧客
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000003985825");
		contract.setCustomerContract(customerContract);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromArrangementWork(arrangementWork, contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_手配_参照_社員非存在() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 手配業務
		ArrangementWork arrangementWork = new ArrangementWork();

		// 承認ルート
		arrangementWork.setArrangementWorkApprovalRoute(new ArrangementWorkApprovalRoute());
		arrangementWork.getArrangementWorkApprovalRoute().setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<ArrangementWorkApprovalRouteNode> arrangementWorkApprovalRouteNodeList = new ArrayList<>();
		ArrangementWorkApprovalRouteNode arrangementWorkApprovalRouteNode = new ArrangementWorkApprovalRouteNode();
		arrangementWorkApprovalRouteNode.setApproverEmpId("00231268A");
		arrangementWorkApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		arrangementWorkApprovalRouteNodeList.add(arrangementWorkApprovalRouteNode);
		arrangementWork.getArrangementWorkApprovalRoute().setArrangementWorkApprovalRouteNodeList(arrangementWorkApprovalRouteNodeList);

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromArrangementWork(arrangementWork, contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertTrue("承認者の社員情報が作成されていないこと", authParam.getApproverMvEmployeeMasterList().isEmpty());
		Assert.assertNull("次回承認者の社員情報が作成されていないこと", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_手配_参照_グループ承認_社員非存在() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator_社員非存在.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 手配業務
		ArrangementWork arrangementWork = new ArrangementWork();

		// 承認ルート
		ArrangementWorkApprovalRoute arrangementWorkApprovalRoute = new ArrangementWorkApprovalRoute();
		arrangementWorkApprovalRoute.setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<ArrangementWorkApprovalRouteNode> arrangementWorkApprovalRouteNodeList = new ArrayList<>();
		ArrangementWorkApprovalRouteNode arrangementWorkApprovalRouteNode = new ArrangementWorkApprovalRouteNode();
		arrangementWorkApprovalRouteNode.setApproverEmpId("TORUNO001");
		arrangementWorkApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		arrangementWorkApprovalRouteNodeList.add(arrangementWorkApprovalRouteNode);
		arrangementWorkApprovalRoute.setArrangementWorkApprovalRouteNodeList(arrangementWorkApprovalRouteNodeList);
		arrangementWork.setArrangementWorkApprovalRoute(arrangementWorkApprovalRoute);

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 担当SA
		ContractPicSaEmp contractPicSaEmp = new ContractPicSaEmp();
		contractPicSaEmp.setMomEmployeeId("00500784");
		contract.setContractPicSaEmp(contractPicSaEmp);

		// 追加編集者
		List<ContractAddedEditorEmp> contractAddedEditorEmpList = new ArrayList<>();
		ContractAddedEditorEmp contractAddedEditorEmp = new ContractAddedEditorEmp();
		contractAddedEditorEmp.setMomEmployeeId("00500784");
		contractAddedEditorEmpList.add(contractAddedEditorEmp);
		contract.setContractAddedEditorEmpList(contractAddedEditorEmpList);

		// 顧客
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000003985825");
		contract.setCustomerContract(customerContract);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromArrangementWork(arrangementWork, contract, actor, AccessType.参照);

		Assert.assertEquals("正常に社員情報が作成されていること", 2, authParam.getEmployeeTransactionForAuthDtoList().size());
		Assert.assertNotNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertTrue("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList().size() == 3);
		Assert.assertNull("承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_手配_承認() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 手配業務
		ArrangementWork arrangementWork = new ArrangementWork();

		// 承認ルート
		arrangementWork.setArrangementWorkApprovalRoute(new ArrangementWorkApprovalRoute());
		arrangementWork.getArrangementWorkApprovalRoute().setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<ArrangementWorkApprovalRouteNode> arrangementWorkApprovalRouteNodeList = new ArrayList<>();
		ArrangementWorkApprovalRouteNode arrangementWorkApprovalRouteNode = new ArrangementWorkApprovalRouteNode();
		arrangementWorkApprovalRouteNode.setApproverEmpId("00231268");
		arrangementWorkApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.直属上司指定);
		arrangementWorkApprovalRouteNodeList.add(arrangementWorkApprovalRouteNode);
		arrangementWork.getArrangementWorkApprovalRoute().setArrangementWorkApprovalRouteNodeList(arrangementWorkApprovalRouteNodeList);

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromArrangementWork(arrangementWork, contract, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に会社情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_手配_直接_ユーザー直接指定() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 手配業務
		ArrangementWork arrangementWork = new ArrangementWork();

		// 承認ルート
		arrangementWork.setArrangementWorkApprovalRoute(new ArrangementWorkApprovalRoute());
		arrangementWork.getArrangementWorkApprovalRoute().setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<ArrangementWorkApprovalRouteNode> arrangementWorkApprovalRouteNodeList = new ArrayList<>();
		ArrangementWorkApprovalRouteNode arrangementWorkApprovalRouteNode = new ArrangementWorkApprovalRouteNode();
		arrangementWorkApprovalRouteNode.setApproverEmpId("00500784");
		arrangementWorkApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.ユーザー直接指定);
		arrangementWorkApprovalRouteNodeList.add(arrangementWorkApprovalRouteNode);
		arrangementWork.getArrangementWorkApprovalRoute().setArrangementWorkApprovalRouteNodeList(arrangementWorkApprovalRouteNodeList);

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromArrangementWork(arrangementWork, contract, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertTrue("ユーザー直接指定であること", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_手配_直接_グループ承認() {

		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("sql/security/testAuthorityJudgeParamCreator.sql");

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 手配業務
		ArrangementWork arrangementWork = new ArrangementWork();

		// 承認ルート
		ArrangementWorkApprovalRoute arrangementWorkApprovalRoute = new ArrangementWorkApprovalRoute();
		arrangementWorkApprovalRoute.setApprovalRequesterEmpId("00500784");

		// 承認ルートノード
		List<ArrangementWorkApprovalRouteNode> arrangementWorkApprovalRouteNodeList = new ArrayList<>();
		ArrangementWorkApprovalRouteNode arrangementWorkApprovalRouteNode = new ArrangementWorkApprovalRouteNode();
		arrangementWorkApprovalRouteNode.setApproverEmpId("TORUNO001");
		arrangementWorkApprovalRouteNode.setApproverDeriveMethodDiv(ApproverDeriveMethodDiv.グループ承認);
		arrangementWorkApprovalRouteNodeList.add(arrangementWorkApprovalRouteNode);
		arrangementWorkApprovalRoute.setArrangementWorkApprovalRouteNodeList(arrangementWorkApprovalRouteNodeList);
		arrangementWork.setArrangementWorkApprovalRoute(arrangementWorkApprovalRoute);

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 担当SA
		ContractPicSaEmp contractPicSaEmp = new ContractPicSaEmp();
		contractPicSaEmp.setMomEmployeeId("00500784");
		contract.setContractPicSaEmp(contractPicSaEmp);

		// 追加編集者
		List<ContractAddedEditorEmp> contractAddedEditorEmpList = new ArrayList<>();
		ContractAddedEditorEmp contractAddedEditorEmp = new ContractAddedEditorEmp();
		contractAddedEditorEmp.setMomEmployeeId("00500784");
		contractAddedEditorEmpList.add(contractAddedEditorEmp);
		contract.setContractAddedEditorEmpList(contractAddedEditorEmpList);

		// 顧客
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000003985825");
		contract.setCustomerContract(customerContract);

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromArrangementWork(arrangementWork, contract, actor, AccessType.承認);

		Assert.assertNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNotNull("正常に承認者の社員情報が作成されていること", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNotNull("正常に次回承認者の社員情報が作成されていること", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNotNull("正常に承認依頼者の社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
		Assert.assertTrue("グループ承認であること", authParam.isGroupApproval());
	}

	@Test
	public void 正常_権限判定用パラメーター取得_手配_手配無し() {

		// ログインユーザー
		MvEmployeeMaster actor = mvEmployeeMasterRepository.findByMomEmployeeId("00500784");

		// 契約
		Contract contract = new Contract();
		contract.setLifecycleStatus(LifecycleStatus.作成中);

		// 担当SA
		contract.setContractPicSaEmp(new ContractPicSaEmp());
		contract.getContractPicSaEmp().setMomEmployeeId("00500784");

		// 追加編集者
		contract.setContractAddedEditorEmpList(new ArrayList<>());
		contract.getContractAddedEditorEmpList().add(new ContractAddedEditorEmp());
		contract.getContractAddedEditorEmpList().get(0).setMomEmployeeId("00500784");

		// 顧客
		contract.setCustomerContract(new CustomerContract());
		contract.getCustomerContract().setMomKjbSystemId("000000003985825");

		AuthorityJudgeParameter authParam = authorityJudgeParamCreator.createFromArrangementWork(null, contract, actor, AccessType.参照);

		Assert.assertNotNull("正常に社員情報が作成されていること", authParam.getEmployeeTransactionForAuthDtoList());
		Assert.assertNotNull("正常に顧客情報が作成されていること", authParam.getVKjbMaster());
		Assert.assertNotNull("正常にログインユーザー情報が作成されていること", authParam.getActorMvEmployeeMaster());
		Assert.assertNull("正常に承認者の社員情報が作成されていないこと", authParam.getApproverMvEmployeeMasterList());
		Assert.assertNull("正常に次回承認者の社員情報が作成されていないこと", authParam.getNextApproverMvEmployeeMaster());
		Assert.assertNull("正常に承認依頼者の社員情報が作成されていないこと", authParam.getRequesterMvEmployeeMaster());
		Assert.assertFalse("ユーザー直接指定でないこと", authParam.isManualApprover());
		Assert.assertFalse("自己承認でないこと", authParam.isSelfApprover());
	}

}
