package com.epam.jmp.classloader;

public class Run {

	private static final String JAR_V1 = "src/test/resources/stateless-classloader-test-data-0.1.0.jar";
	private static final String JAR_V2 = "src/test/resources/stateless-classloader-test-data-0.2.0.jar";
	private static final String CLASS = "com.epam.jmp.classloader.test.HelloWorld";
	
	public static void main(String[] args) throws Exception {
		StatelessClassLoader statelessClassLoader1 = new StatelessClassLoader(JAR_V1);
		Class<?> helloWorld = statelessClassLoader1.loadClass(CLASS);
		helloWorld.getMethod("println").invoke(Void.class);
		
		StatelessClassLoader statelessClassLoader2 = new StatelessClassLoader(JAR_V2);
		helloWorld = statelessClassLoader2.loadClass(CLASS);
		helloWorld.getMethod("println").invoke(Void.class);
	}
	
}
