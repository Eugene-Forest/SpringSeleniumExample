package org.tutor.seleniumbot.service.impl;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import org.tutor.seleniumbot.selenium.BotDriver;
import org.tutor.seleniumbot.service.DemoService;

import java.time.Duration;

@Service
public class DomeServiceImpl implements DemoService {
    @Override
    public String openBing() {
        BotDriver.initDriver();
        WebDriver driver = BotDriver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 启动需要打开的网页
        driver.get("https://cn.bing.com/");
        String title = driver.getTitle();
        driver.quit();
        return title;
    }
}
