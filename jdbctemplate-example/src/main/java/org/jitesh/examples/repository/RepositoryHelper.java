package org.jitesh.examples.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RepositoryHelper {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
//	@Autowired
	public void createNamedParameterJdbcTemplate(DataSource dataSource) {
		System.out.println("init jdbc template");
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

}
