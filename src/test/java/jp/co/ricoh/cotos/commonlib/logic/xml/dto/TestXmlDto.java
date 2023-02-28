package jp.co.ricoh.cotos.commonlib.logic.xml.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD) // @Dataと同時に使用するため必要
public class TestXmlDto {

	private Long id;

	private String estimationTitle;

	@XmlElement(name = "lifecycle_status")
	private String lifecycleStatus;

	@XmlElement(name = "workflow_status")
	private String workflowStatus;

	@XmlElement(name = "contract_id")
	private Long contractId;

	private String type;

	private String empty;

	@XmlElement(name = "inner")
	private List<TestInnerDto> innerList;
}
