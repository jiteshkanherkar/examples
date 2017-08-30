package org.jitesh.examples.service;

import javax.annotation.PostConstruct;

import org.jitesh.examples.CustomException;
import org.jitesh.examples.Feed;
import org.jitesh.examples.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

	@Autowired
	private FeedRepository feedRepository;

	@PostConstruct
	public void init() {
		System.out.println("Feed Service init");
	}
	public int insertFeed(Feed feed) {
		try {
			feedRepository.insertFeed(feed);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
