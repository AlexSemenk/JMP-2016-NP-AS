package com.epam.jmp.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ClassUtils {
	
	public static boolean isStateless(Class<?> clazz) {
		for(Field field : clazz.getDeclaredFields()) {
			int fieldModifiers = field.getModifiers();
			boolean isFinal = (fieldModifiers & Modifier.FINAL) != 0;
			boolean isStatic = (fieldModifiers & Modifier.STATIC) != 0;
			if(!isStatic && !isFinal) {
				return false;
			}
		}
		return true;
	}
	
}
