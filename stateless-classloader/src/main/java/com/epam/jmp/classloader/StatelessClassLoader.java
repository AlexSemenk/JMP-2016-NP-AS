package com.epam.jmp.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class StatelessClassLoader extends URLClassLoader {
	
	public StatelessClassLoader(URL jarUrl, ClassLoader parent) {
		super(new URL[] {jarUrl}, parent);
	}
	
	public StatelessClassLoader(URL jar) {
		super(new URL[] {jar});
	}

	protected Class<?> findClass(String className) throws ClassNotFoundException {
		Class<?> clazz = super.findClass(className);
		if (ClassUtils.isStateless(clazz)) {
			return clazz;
		} else {
			throw new ClassNotFoundException("class " + className + " isn't stateless");
		}
	}

}
