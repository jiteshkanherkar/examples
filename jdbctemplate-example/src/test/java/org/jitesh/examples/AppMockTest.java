package org.jitesh.examples;

import javax.sql.DataSource;

import org.jitesh.examples.config.ApplicationConfig;
import org.jitesh.examples.repository.RepositoryHelper;
import org.jitesh.examples.service.FeedService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class AppMockTest {

	@Mock
	FeedService feedService;

	@Before
	public void initMock() {
		DataSource dataSource=Mockito.mock(DataSource.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		RepositoryHelper helper = context.getBean(RepositoryHelper.class);
		helper.createNamedParameterJdbcTemplate(dataSource);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFeed() {
		Feed feed = new Feed(1, "Feed1", "JItesh");
		Mockito.when(feedService.insertFeed(feed)).thenReturn(1);

	}
}
