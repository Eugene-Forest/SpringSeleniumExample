package org.tutor.seleniumbot.selenium;

import org.openqa.selenium.WebDriver;

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
    public static WebDriver getDriver(){
        return seleniumBot.driver;
    }
}
