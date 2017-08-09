package org.jitesh.factorypattern;

public class FactoryTest {

	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println("Thread " + i);
			FactoryRunnable factoryRunnable = new FactoryRunnable();
			new Thread(factoryRunnable).start();
		}
	}

}
