package com.andrew.demo;

import com.andrew.demo.controller.BookController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig{
    public JerseyConfig() {
        register(BookController.class);
        register(GenericExceptionMapper.class);
    }
}
