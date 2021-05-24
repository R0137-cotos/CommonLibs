package jp.co.ricoh.cotos.commonlib.logic.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import lombok.Data;

@Data
public class MailText {
	// 置換文字列
	private String replaceValue1;
	private String replaceValue2;
	private String replaceValue3;
	private String replaceValue4;
	private String replaceValue5;
	private String replaceValue6;
	private String replaceValue7;
	private String replaceValue8;
	private String replaceValue9;
	private String replaceValue10;
	private String replaceValue11;
	private String replaceValue12;
	private String replaceValue13;
	private String replaceValue14;
	private String replaceValue15;
	// 置換リスト
	private List<String> replaceList1 = new ArrayList<String>();
	private List<String> replaceList2 = new ArrayList<String>();
	private List<String> replaceList3 = new ArrayList<String>();

	public MailText(List<String> replaceValueList) {
		// 置換文字列
		this.replaceValue1 = replaceValueList.size() > 0 ? replaceValueList.get(0) : "";
		this.replaceValue2 = replaceValueList.size() > 1 ? replaceValueList.get(1) : "";
		this.replaceValue3 = replaceValueList.size() > 2 ? replaceValueList.get(2) : "";
		this.replaceValue4 = replaceValueList.size() > 3 ? replaceValueList.get(3) : "";
		this.replaceValue5 = replaceValueList.size() > 4 ? replaceValueList.get(4) : "";
		this.replaceValue6 = replaceValueList.size() > 5 ? replaceValueList.get(5) : "";
		this.replaceValue7 = replaceValueList.size() > 6 ? replaceValueList.get(6) : "";
		this.replaceValue8 = replaceValueList.size() > 7 ? replaceValueList.get(7) : "";
		this.replaceValue9 = replaceValueList.size() > 8 ? replaceValueList.get(8) : "";
		this.replaceValue10 = replaceValueList.size() > 9 ? replaceValueList.get(9) : "";
		this.replaceValue11 = replaceValueList.size() > 10 ? replaceValueList.get(10) : "";
		this.replaceValue12 = replaceValueList.size() > 11 ? replaceValueList.get(11) : "";
		this.replaceValue13 = replaceValueList.size() > 12 ? replaceValueList.get(12) : "";
		this.replaceValue14 = replaceValueList.size() > 13 ? replaceValueList.get(13) : "";
		this.replaceValue15 = replaceValueList.size() > 14 ? replaceValueList.get(14) : "";
	}

	public MailText(List<String> replaceValueList, List<List<String>> replaceListValues) {
		// 置換文字列
		this.replaceValue1 = replaceValueList.size() > 0 ? replaceValueList.get(0) : "";
		this.replaceValue2 = replaceValueList.size() > 1 ? replaceValueList.get(1) : "";
		this.replaceValue3 = replaceValueList.size() > 2 ? replaceValueList.get(2) : "";
		this.replaceValue4 = replaceValueList.size() > 3 ? replaceValueList.get(3) : "";
		this.replaceValue5 = replaceValueList.size() > 4 ? replaceValueList.get(4) : "";
		this.replaceValue6 = replaceValueList.size() > 5 ? replaceValueList.get(5) : "";
		this.replaceValue7 = replaceValueList.size() > 6 ? replaceValueList.get(6) : "";
		this.replaceValue8 = replaceValueList.size() > 7 ? replaceValueList.get(7) : "";
		this.replaceValue9 = replaceValueList.size() > 8 ? replaceValueList.get(8) : "";
		this.replaceValue10 = replaceValueList.size() > 9 ? replaceValueList.get(9) : "";
		this.replaceValue11 = replaceValueList.size() > 10 ? replaceValueList.get(10) : "";
		this.replaceValue12 = replaceValueList.size() > 11 ? replaceValueList.get(11) : "";
		this.replaceValue13 = replaceValueList.size() > 12 ? replaceValueList.get(12) : "";
		this.replaceValue14 = replaceValueList.size() > 13 ? replaceValueList.get(13) : "";
		this.replaceValue15 = replaceValueList.size() > 14 ? replaceValueList.get(14) : "";
		// 置換リスト
		this.replaceList1 = getReplaceList(replaceListValues, 0);
		this.replaceList2 = getReplaceList(replaceListValues, 1);
		this.replaceList3 = getReplaceList(replaceListValues, 2);
	}

	/**
	 * 置換リスト取得
	 * @param replaceListValues
	 * @param index
	 * @return
	 */
	private List<String> getReplaceList(List<List<String>> replaceListValues, int index) {
		List<String> list = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(replaceListValues) && replaceListValues.size() > index && CollectionUtils.isNotEmpty(replaceListValues.get(index)) && replaceListValues.get(index).size() > index) {
			list = replaceListValues.get(index);
			return list;
		} else {
			list.add("");
			return list;
		}
	}
}
