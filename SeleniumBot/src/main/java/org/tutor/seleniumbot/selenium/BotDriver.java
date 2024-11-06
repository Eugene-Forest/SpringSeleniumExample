package org.tutor.seleniumbot.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

/**
 * 自动测试驱动器
 */
public class BotDriver {

    /**
     * 谷歌浏览器测试驱动对象
     */
    private static SeleniumBot seleniumBot;

    /**
     * 初始化所有驱动器
     * @return boolean
     */
    public static boolean initDriver() {
        seleniumBot = new SeleniumBot();
        return true;
    }

    /**
     * 谷歌浏览器驱动器
     * @return driver
     */
    public static ChromeDriver getDriver(){
        return seleniumBot.driver;
    }

    public static List<LogEntry> logEntryList(){
        return seleniumBot.driverLogEntries;
    }

    public static List<LogEntry> browerLogEntryList(){
        return seleniumBot.browserLogEntries;
    }
}
