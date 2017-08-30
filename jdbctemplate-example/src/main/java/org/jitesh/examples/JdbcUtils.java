package org.jitesh.examples;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jitesh.examples.annotations.DbColName;
import org.jitesh.examples.annotations.DbTableName;
import org.jitesh.examples.annotations.IgnoreField;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

//@Component
public class JdbcUtils {

	
	public JdbcUtils() {
		super();
		System.out.println("JdbcUtils initialized");
	}

	public <T> String prepareInsertQuery(Class<T> _class) throws CustomException {

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("insert into ");
		DbTableName annotation = _class.getAnnotation(DbTableName.class);

		if (annotation == null) {
			throw new CustomException("Class level annotation missing");
		}
		queryBuilder.append(annotation.table());
		Field[] declaredFields = _class.getDeclaredFields();
		List<String> colName = new ArrayList<String>();
		List<String> fieldName = new ArrayList<String>();

		for (Field field : declaredFields) {
			IgnoreField ignore = field.getAnnotation(IgnoreField.class);
			if (ignore != null) {
				continue;
			}
			DbColName fieldAnnotation = field.getAnnotation(DbColName.class);
			if (fieldAnnotation == null) {
				throw new CustomException("Field annotation missing : " + field.getName());
			}
			colName.add(fieldAnnotation.name());
			fieldName.add(":" + field.getName());
		}
		queryBuilder.append(colName.toString().replace("[", "(").replace("]", ")"));
		queryBuilder.append(" values ");
		queryBuilder.append(fieldName.toString().replace("[", "(").replace("]", ")"));
		colName = null;
		fieldName = null;
		return queryBuilder.toString();
	}

	public MapSqlParameterSource mapFieldsValue(Object object) throws CustomException {
		MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
		Field[] declaredFields = object.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			try {
				Object value = field.get(object);
				if (value == null) {
					System.out.println("Field value is null : " + field.getName());
					continue;
				}
				sqlParameterSource.addValue(":" + field.getName(), value);
			} catch (IllegalArgumentException e) {
				throw new CustomException(e);
			} catch (IllegalAccessException e) {
				throw new CustomException(e);
			}
		}
		return sqlParameterSource;
	}
}
