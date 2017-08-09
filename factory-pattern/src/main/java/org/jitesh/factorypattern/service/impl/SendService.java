package org.jitesh.factorypattern.service.impl;

import org.jitesh.factorypattern.service.ISendService;

public class SendService implements ISendService {

	public String getClsName() {
		return "Class name : " + SendService.class.getName();
	}

}
