package org.tutor.botweb.demos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoControl {

    @Resource
    private DemoService domeService;

    @RequestMapping("/getMessage")
    public String getMessage(){
        return domeService.openBing();
    }
}
