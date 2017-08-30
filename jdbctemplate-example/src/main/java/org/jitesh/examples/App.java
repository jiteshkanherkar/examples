package org.jitesh.examples;

import org.jitesh.examples.config.ApplicationConfig;
import org.jitesh.examples.service.FeedService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		App app = new App();
		try {

			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
			FeedService feedService = context.getBean(FeedService.class);
			
			Feed feed=new Feed(1,"Feed1","JItesh");
			
			feedService.insertFeed(feed);
/*			JdbcUtils jdbcUtils = new JdbcUtils();
			System.out.println(jdbcUtils.prepareInsertQuery(Feed.class));
			MapSqlParameterSource mapFields = jdbcUtils.mapFieldsValue(feed);
			System.out.println(mapFields.getValues());
*/		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
