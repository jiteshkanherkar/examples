package org.jitesh.examples.repository;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jitesh.examples.annotations.DbColName;
import org.jitesh.examples.annotations.IgnoreField;
import org.springframework.jdbc.core.RowMapper;

public class CustomRowMapper<T> implements RowMapper<T> {

	private Class _class;

	public CustomRowMapper(Class _class) {
		super();
		this._class = _class;
	}

	public T mapRow(ResultSet resultSet, int arg1) throws SQLException {
		T t = null;
		try {
			t = (T) _class.newInstance();
			Field[] declaredFields = _class.getDeclaredFields();
			for (Field field : declaredFields) {
				if (field.getAnnotation(IgnoreField.class) != null) {
					continue;
				}
				DbColName annotation = field.getAnnotation(DbColName.class);
				if (annotation == null) {
					continue;
				}
				field.setAccessible(true);
				Object extractValue = extractValue(resultSet, annotation.name(), field.getType().getName());
				field.set(t, extractValue);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return t;
	}

	private <E> E extractValue(ResultSet resultSet, String columnName, String FieldType) throws SQLException {
		E e = null;
		switch (FieldType) {
		case "String":
			e = (E) resultSet.getString(columnName);
			break;
		case "integer":
			e = (E) Integer.valueOf(resultSet.getInt(columnName));
			break;
		case "Long":
			e = (E) Long.valueOf(resultSet.getLong(columnName));
			break;
		case "Double":
			e = (E) Double.valueOf(resultSet.getDouble(columnName));
			break;
		case "Date":
			e = (E) resultSet.getDate(columnName);
			break;

		default:
			break;
		}

		return e;
	}
}
