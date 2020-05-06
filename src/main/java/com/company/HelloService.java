package com.company;

import org.springframework.security.access.annotation.Secured;
//Any One Implement interface by default taking this configuration
public interface HelloService {
    //Framework check you have User role or not by this annotation if not disply Exceptionssss
    @Secured(value = {"ROLE_USER"})
    public String sayHello(String name);
}