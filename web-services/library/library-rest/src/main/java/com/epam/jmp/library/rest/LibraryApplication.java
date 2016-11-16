package com.epam.jmp.library.rest;

import com.epam.jmp.library.rest.resource.LibraryResource;
import com.epam.jmp.library.rest.resource.impl.LibraryResourceImpl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class LibraryApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(LibraryResourceImpl.class);
        classes.add(JacksonJsonProvider.class);
        return classes;
    }

}
