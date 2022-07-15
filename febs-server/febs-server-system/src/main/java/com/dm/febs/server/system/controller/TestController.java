package com.dm.febs.server.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {
    @GetMapping("info")
    public String test(){
      return "febs-server-system";
    }
    @GetMapping("user")
    public Principal currentUser(Principal principal){
        return principal;
    }
}
