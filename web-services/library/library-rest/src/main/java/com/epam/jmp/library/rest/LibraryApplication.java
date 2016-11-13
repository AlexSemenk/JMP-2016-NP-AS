package com.epam.jmp.library.rest;

import com.epam.jmp.library.rest.resource.LibrariesResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 13.11.2016.
 */
public class LibraryApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(LibrariesResource.class);
        classes.add(JacksonJsonProvider.class);
        return classes;
    }

}
