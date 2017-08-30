package org.jitesh.examples;

import org.jitesh.examples.annotations.DbColName;
import org.jitesh.examples.annotations.DbTableName;
import org.jitesh.examples.annotations.IgnoreField;

@DbTableName(table="feed_data")
public class Feed {

	@DbColName(name="feed_id")
	private long feedId;
	
	@DbColName(name="entity")
	private String entity;

	@DbColName(name="cpty_id")
	private String cpty;

	@IgnoreField
	private String temp;

	public String getEntity() {
		return entity;
	}

	
	public Feed() {
		super();
	}


	public Feed(long feedId, String entity, String cpty) {
		super();
		this.feedId = feedId;
		this.entity = entity;
		this.cpty = cpty;
	}


	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getCpty() {
		return cpty;
	}

	public void setCpty(String cpty) {
		this.cpty = cpty;
	}

	public long getFeedId() {
		return feedId;
	}

	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}

	@Override
	public String toString() {
		return "Feed [feedId=" + feedId + ", entity=" + entity + ", cpty=" + cpty + "]";
	}
	
}
