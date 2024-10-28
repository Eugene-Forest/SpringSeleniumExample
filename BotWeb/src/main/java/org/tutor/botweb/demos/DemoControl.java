package org.tutor.botweb.demos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutor.seleniumbot.service.DemoService;


@RestController
@RequestMapping("/demo")
public class DemoControl {

    @Autowired
    private DemoService domeService;

    @RequestMapping("/getMessage")
    public String getMessage(){
        return domeService.openBing();
    }
}
