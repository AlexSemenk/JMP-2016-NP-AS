package com.epam.jmp.classloader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Alexander Semenkevich
 * 
 *         Task: Write your own StatelessClassLoader which will load only
 *         stateless classes (without fields) from .jar file specified as input
 *         parameter (). Don’t forget provide test code.
 */
public class StatelessClassLoader extends ClassLoader {

	private String jarPath;
	
	public StatelessClassLoader(String jarPath, ClassLoader parent) {
		super(parent);
		this.jarPath = jarPath;
	}
	
	public StatelessClassLoader(String jarPath) {
		this.jarPath = jarPath;
	}

	protected Class<?> findClass(String className) throws ClassNotFoundException {
		byte[] bytes;
		try (JarFile jar = new JarFile(jarPath)) {
			String fileName = className.replace('.', '/').concat(".class");
			JarEntry entry = jar.getJarEntry(fileName);
			InputStream input = jar.getInputStream(entry);
			DataInputStream data = new DataInputStream(input);
			bytes = new byte[(int) entry.getSize()];
			data.readFully(bytes);
		} catch (IOException e) {
			throw new ClassNotFoundException(className, e);
		}
		Class<?> clazz = defineClass(className, bytes);
		if (ClassUtils.isStateless(clazz)) {
			return clazz;
		} else {
			throw new ClassNotFoundException("class " + className + " isn't stateless");
		}
	}

	protected final Class<?> defineClass(String name, byte[] b) {
		return defineClass(name, b, 0, b.length);
	}

}
