// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EstimationApprovalRoute.java

package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.List;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;

// Referenced classes of package jp.co.ricoh.cotos.commonlib.entity.estimation:
//            Estimation

public class EstimationApprovalRoute extends EntityBase
{

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof EstimationApprovalRoute))
            return false;
        EstimationApprovalRoute other = (EstimationApprovalRoute)o;
        if(!other.canEqual(this))
            return false;
        if(!super.equals(o))
            return false;
        if(getId() != other.getId())
            return false;
        Object this$estimation = getEstimation();
        Object other$estimation = other.getEstimation();
        if(this$estimation != null ? !this$estimation.equals(other$estimation) : other$estimation != null)
            return false;
        Object this$approvalRequesterEmpId = getApprovalRequesterEmpId();
        Object other$approvalRequesterEmpId = other.getApprovalRequesterEmpId();
        if(this$approvalRequesterEmpId != null ? !this$approvalRequesterEmpId.equals(other$approvalRequesterEmpId) : other$approvalRequesterEmpId != null)
            return false;
        Object this$approvalRequesterName = getApprovalRequesterName();
        Object other$approvalRequesterName = other.getApprovalRequesterName();
        if(this$approvalRequesterName != null ? !this$approvalRequesterName.equals(other$approvalRequesterName) : other$approvalRequesterName != null)
            return false;
        Object this$approvalRequesterOrgName = getApprovalRequesterOrgName();
        Object other$approvalRequesterOrgName = other.getApprovalRequesterOrgName();
        if(this$approvalRequesterOrgName != null ? !this$approvalRequesterOrgName.equals(other$approvalRequesterOrgName) : other$approvalRequesterOrgName != null)
            return false;
        if(getSpecialPriceApprovalFlg() != other.getSpecialPriceApprovalFlg())
            return false;
        Object this$estimationApprovalResultList = getEstimationApprovalResultList();
        Object other$estimationApprovalResultList = other.getEstimationApprovalResultList();
        if(this$estimationApprovalResultList != null ? !this$estimationApprovalResultList.equals(other$estimationApprovalResultList) : other$estimationApprovalResultList != null)
            return false;
        Object this$estimationApprovalRouteNodeList = getEstimationApprovalRouteNodeList();
        Object other$estimationApprovalRouteNodeList = other.getEstimationApprovalRouteNodeList();
        return this$estimationApprovalRouteNodeList != null ? this$estimationApprovalRouteNodeList.equals(other$estimationApprovalRouteNodeList) : other$estimationApprovalRouteNodeList == null;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof EstimationApprovalRoute;
    }

    public int hashCode()
    {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + super.hashCode();
        long $id = getId();
        result = result * 59 + (int)($id ^ $id >>> 32);
        Object $estimation = getEstimation();
        result = result * 59 + ($estimation != null ? $estimation.hashCode() : 43);
        Object $approvalRequesterEmpId = getApprovalRequesterEmpId();
        result = result * 59 + ($approvalRequesterEmpId != null ? $approvalRequesterEmpId.hashCode() : 43);
        Object $approvalRequesterName = getApprovalRequesterName();
        result = result * 59 + ($approvalRequesterName != null ? $approvalRequesterName.hashCode() : 43);
        Object $approvalRequesterOrgName = getApprovalRequesterOrgName();
        result = result * 59 + ($approvalRequesterOrgName != null ? $approvalRequesterOrgName.hashCode() : 43);
        result = result * 59 + getSpecialPriceApprovalFlg();
        Object $estimationApprovalResultList = getEstimationApprovalResultList();
        result = result * 59 + ($estimationApprovalResultList != null ? $estimationApprovalResultList.hashCode() : 43);
        Object $estimationApprovalRouteNodeList = getEstimationApprovalRouteNodeList();
        result = result * 59 + ($estimationApprovalRouteNodeList != null ? $estimationApprovalRouteNodeList.hashCode() : 43);
        return result;
    }

    public long getId()
    {
        return id;
    }

    public Estimation getEstimation()
    {
        return estimation;
    }

    public String getApprovalRequesterEmpId()
    {
        return approvalRequesterEmpId;
    }

    public String getApprovalRequesterName()
    {
        return approvalRequesterName;
    }

    public String getApprovalRequesterOrgName()
    {
        return approvalRequesterOrgName;
    }

    public int getSpecialPriceApprovalFlg()
    {
        return specialPriceApprovalFlg;
    }

    public List getEstimationApprovalResultList()
    {
        return estimationApprovalResultList;
    }

    public List getEstimationApprovalRouteNodeList()
    {
        return estimationApprovalRouteNodeList;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setEstimation(Estimation estimation)
    {
        this.estimation = estimation;
    }

    public void setApprovalRequesterEmpId(String approvalRequesterEmpId)
    {
        this.approvalRequesterEmpId = approvalRequesterEmpId;
    }

    public void setApprovalRequesterName(String approvalRequesterName)
    {
        this.approvalRequesterName = approvalRequesterName;
    }

    public void setApprovalRequesterOrgName(String approvalRequesterOrgName)
    {
        this.approvalRequesterOrgName = approvalRequesterOrgName;
    }

    public void setSpecialPriceApprovalFlg(int specialPriceApprovalFlg)
    {
        this.specialPriceApprovalFlg = specialPriceApprovalFlg;
    }

    public void setEstimationApprovalResultList(List estimationApprovalResultList)
    {
        this.estimationApprovalResultList = estimationApprovalResultList;
    }

    public void setEstimationApprovalRouteNodeList(List estimationApprovalRouteNodeList)
    {
        this.estimationApprovalRouteNodeList = estimationApprovalRouteNodeList;
    }

    public String toString()
    {
        return (new StringBuilder("EstimationApprovalRoute(id=")).append(getId()).append(", estimation=").append(getEstimation()).append(", approvalRequesterEmpId=").append(getApprovalRequesterEmpId()).append(", approvalRequesterName=").append(getApprovalRequesterName()).append(", approvalRequesterOrgName=").append(getApprovalRequesterOrgName()).append(", specialPriceApprovalFlg=").append(getSpecialPriceApprovalFlg()).append(", estimationApprovalResultList=").append(getEstimationApprovalResultList()).append(", estimationApprovalRouteNodeList=").append(getEstimationApprovalRouteNodeList()).append(")").toString();
    }

    public EstimationApprovalRoute()
    {
    }

    private long id;
    private Estimation estimation;
    private String approvalRequesterEmpId;
    private String approvalRequesterName;
    private String approvalRequesterOrgName;
    private int specialPriceApprovalFlg;
    private List estimationApprovalResultList;
    private List estimationApprovalRouteNodeList;
}
