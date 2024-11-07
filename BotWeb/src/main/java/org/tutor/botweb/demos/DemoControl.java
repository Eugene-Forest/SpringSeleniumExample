package org.tutor.botweb.demos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutor.seleniumbot.service.DemoService;


@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoControl {

    @Autowired
    private DemoService domeService;

    @RequestMapping("/getMessage")
    public String getMessage(){
        return domeService.openBing();
    }

    @RequestMapping("/hello")
    public String getHelloWorld(){
        log.debug("Debug message");
        log.info("Info message");
        log.warn("Warning message");
        log.error("Error message");
        return "helloworld";
    }
}
