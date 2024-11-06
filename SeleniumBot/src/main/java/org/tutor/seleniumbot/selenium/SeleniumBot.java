package org.tutor.seleniumbot.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;

/**
 * Selenium 自动化测试基础类
 */
public class SeleniumBot {

    /**
     * 驱动对象
     */
    protected ChromeDriver driver;

    protected List<LogEntry> browserLogEntries;

    protected List<LogEntry> driverLogEntries;

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
        LoggingPreferences logger = new LoggingPreferences();
        logger.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logger);
        // 打印性能日志
//        options.setLogLevel(ChromeDriverLogLevel.ALL);
        driver = new ChromeDriver(options);

        List<LogEntry> list = driver.manage().logs().get(LogType.PERFORMANCE).toJson();
        List<LogEntry> browserList = driver.manage().logs().get(LogType.BROWSER).toJson();
        driverLogEntries = list;
        browserLogEntries = browserList;
    }
}
