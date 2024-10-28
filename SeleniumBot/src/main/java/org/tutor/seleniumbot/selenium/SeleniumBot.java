package org.tutor.seleniumbot.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Browser;

/**
 * Selenium 自动化测试基础类
 */
public class SeleniumBot {

    /**
     * 驱动对象
     */
    protected WebDriver driver;

    public SeleniumBot() {
        //指定驱动路径

        System.setProperty("webdriver.chrome.driver","E://Driver/129/chromedriver-win64/chromedriver.exe");
        // 谷歌驱动
        ChromeOptions options = new ChromeOptions();
        // 允许所有请求
        options.addArguments("--remote-allow-origins=*");
        options.setBinary("E://Driver/129/chrome-win64/chrome.exe");
        // 设置为 headless 模式 （必须）
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
}
