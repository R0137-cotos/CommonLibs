package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.FileOperationRelationProductMaster.CategoryType;

@Converter(autoApply = true)
public class CategoryTypeConverter implements AttributeConverter<CategoryType, String> {

	@Override
	public String convertToDatabaseColumn(CategoryType categoryType) {
		if (categoryType == null)
			return null;
		return categoryType.toString();
	}

	@Override
	public CategoryType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CategoryType.fromString(value); //IllegalArgumentExceptionはCategoryType.fromString側で投げている
	}
}