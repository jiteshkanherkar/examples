package org.jitesh.factorypattern;

import org.jitesh.factorypattern.service.AbstractService;
import org.jitesh.factorypattern.service.impl.PostService;
import org.jitesh.factorypattern.service.impl.SendService;

public class FactoryRunnable implements Runnable {

	public void run() {
		System.out.println("Thread name " + Thread.currentThread().getName());
		ServiceFactory instance = ServiceFactory.getInstance();
		AbstractService sendInstance = instance.getServiceInstance(SendService.class.getName());
		System.out.println(Thread.currentThread().getName() + "--" + sendInstance.getClsName());
		try {
			System.out.println(Thread.currentThread().getName() + " sleeping..." + Thread.currentThread().getName());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " wake up :)" + Thread.currentThread().getName());
		AbstractService postInstance = instance.getServiceInstance(PostService.class.getName());
		System.out.println(Thread.currentThread().getName() + "--" + postInstance.getClsName());
	}

}
