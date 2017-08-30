package org.jitesh.examples.repository;

import javax.annotation.PostConstruct;

import org.jitesh.examples.CustomException;
import org.jitesh.examples.Feed;
import org.jitesh.examples.JdbcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FeedRepository {

	@Autowired
	private RepositoryHelper repositoryHelper;

	// @Autowired
	private JdbcUtils jdbcUtils;

	@Autowired
	private ApplicationContext applicationContext;

	private String feedInsertQuery;

	@PostConstruct
	public void init() {
		System.out.println("Feed Repo init");
	}

	@Autowired
	public void createFeedInsertQuery() throws CustomException {
		System.out.println("preparing insert stmt");
		jdbcUtils = applicationContext.getBean(JdbcUtils.class);
		this.feedInsertQuery = jdbcUtils.prepareInsertQuery(Feed.class);
	}

	public void insertFeed(Feed feed) throws CustomException {

		NamedParameterJdbcTemplate jdbcTemplate = repositoryHelper.getNamedParameterJdbcTemplate();
		MapSqlParameterSource sqlParameterSource = jdbcUtils.mapFieldsValue(feed);
		System.out.println("Inserting " + feedInsertQuery + " with values " + sqlParameterSource.getValues());
		jdbcTemplate.update(feedInsertQuery, sqlParameterSource);
	}

	public Feed loadFeed(String feedId) {
		NamedParameterJdbcTemplate jdbcTemplate = repositoryHelper.getNamedParameterJdbcTemplate();
		String query = "select * from feed_data where feed_id=:feedId";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(":feedId", feedId);
		return jdbcTemplate.queryForObject(query, parameterSource, new CustomRowMapper<Feed>(Feed.class));
	}
}
