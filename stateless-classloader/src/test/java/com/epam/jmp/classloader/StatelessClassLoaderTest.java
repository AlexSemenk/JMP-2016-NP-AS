package com.epam.jmp.classloader;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StatelessClassLoaderTest {
	
	private static StatelessClassLoader classloader;
	
	@BeforeClass
	public static void initClassloader() throws MalformedURLException {
		URL jarUrl = new URL("jar:file:src/test/resources/stateless-classloader-test-data-0.1.0.jar!/");
		classloader = new StatelessClassLoader(jarUrl);
	}
	
	@AfterClass
	public static void closeClassloader() throws IOException {
		classloader.close();
	}
	
	@Test
	public void testLoadsMoneyClass() throws ClassNotFoundException {
		assertNotNull(classloader.loadClass("com.epam.jmp.classloader.test.Money"));
	}
	
	@Test
	public void testLoadsStaticCounterClass() throws ClassNotFoundException {
		assertNotNull(classloader.loadClass("com.epam.jmp.classloader.test.StaticCounter"));
	}
	
	@Test
	public void testLoadsMathConstantsClass() throws ClassNotFoundException {
		assertNotNull(classloader.loadClass("com.epam.jmp.classloader.test.MathConstants"));
	}
	
	@Test(expected = ClassNotFoundException.class)
	public void testDoesNotLoadMutableIntegerClass() throws ClassNotFoundException {
		assertNotNull(classloader.loadClass("com.epam.jmp.classloader.test.MutableInteger"));
	}
	
}
