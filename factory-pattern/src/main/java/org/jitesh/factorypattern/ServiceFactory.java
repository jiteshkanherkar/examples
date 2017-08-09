package org.jitesh.factorypattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jitesh.factorypattern.service.AbstractService;

public class ServiceFactory {

	private static ServiceFactory serviceFactory;

	private ServiceFactory() {
		super();
	}

	public static ServiceFactory getInstance() {
		if (serviceFactory == null) {
			synchronized (ServiceFactory.class) {
				if (serviceFactory == null) {
					System.out.println("ServiceFactory instanciated by :" + Thread.currentThread().getName());
					serviceFactory = new ServiceFactory();
				}
			}
		}
		return serviceFactory;
	}

	public AbstractService getServiceInstance(String clsName) {
		AbstractService service = null;
		service = ServiceFactoryImpl.getImplInstance().getInstance(clsName);
		return service;
	}

	private static class ServiceFactoryImpl {
		private final Map<String, AbstractService> serviceMap;
		private static ServiceFactoryImpl implInstance;

		private ServiceFactoryImpl() {
			serviceMap = new ConcurrentHashMap<String, AbstractService>();
		}

		public static ServiceFactoryImpl getImplInstance() {
			if (implInstance == null) {
				synchronized (ServiceFactoryImpl.class) {
					if (implInstance == null) {
						System.out.println("ServiceFactoryImpl instanciated by :" + Thread.currentThread().getName());
						implInstance = new ServiceFactoryImpl();
					}
				}
			}
			return implInstance;
		}

		protected AbstractService getInstance(String clsName) {
			AbstractService service = serviceMap.get(clsName);
			try {
				if (service == null) {
					synchronized (this) {
						if (service == null) {
							service = (AbstractService) Class.forName(clsName).newInstance();
							serviceMap.putIfAbsent(clsName, service);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return service;
		}
	}
}
