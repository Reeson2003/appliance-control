package ru.reeson2003.applianceControl.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Gavrilov
 */
@RestController
@RequestMapping("/")
public class DemoController {
    @RequestMapping
    public String demo() {
        return "Hello";
    }
}
