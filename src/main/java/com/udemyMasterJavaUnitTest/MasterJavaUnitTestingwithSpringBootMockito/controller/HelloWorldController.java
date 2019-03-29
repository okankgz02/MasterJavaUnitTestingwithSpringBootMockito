package com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public  String main(){
        return "Hello World";
    }

}


