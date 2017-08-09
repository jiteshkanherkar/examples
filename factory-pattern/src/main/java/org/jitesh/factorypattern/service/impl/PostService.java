package org.jitesh.factorypattern.service.impl;

import org.jitesh.factorypattern.service.IPostService;

public class PostService implements IPostService {

	public String getClsName() {
		return "Class name : " + PostService.class.getName();
	}

}
