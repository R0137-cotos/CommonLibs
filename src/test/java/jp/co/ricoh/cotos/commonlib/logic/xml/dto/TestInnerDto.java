package jp.co.ricoh.cotos.commonlib.logic.xml.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name = "inner2")
@Data
@XmlAccessorType(XmlAccessType.FIELD) // @Dataと同時に使用するため必要
public class TestInnerDto {

	private int id;

	private String text;
}
