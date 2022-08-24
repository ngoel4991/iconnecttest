package com.blecua84.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller example.
 *
 * @author blecua84
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hola desde Spring Boot!!";
    }
}
