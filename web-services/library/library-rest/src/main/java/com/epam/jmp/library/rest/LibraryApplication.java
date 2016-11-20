package com.epam.jmp.library.rest;

import com.epam.jmp.library.rest.resource.impl.LibraryResourceImpl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//@ApplicationPath("/api")
public class LibraryApplication extends Application {

    public LibraryApplication() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/library-rest/api");
        beanConfig.setResourcePackage("com.epam.jmp.library.rest.resource");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        // library
        classes.add(LibraryResourceImpl.class);
        classes.add(JacksonJsonProvider.class);
        // swagger
        classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return classes;
    }

}
