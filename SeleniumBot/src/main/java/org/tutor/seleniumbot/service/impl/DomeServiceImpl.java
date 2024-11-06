package org.tutor.seleniumbot.service.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.tutor.seleniumbot.selenium.BotDriver;
import org.tutor.seleniumbot.service.DemoService;

import java.time.Duration;

@Service
public class DomeServiceImpl implements DemoService {
    @Override
    public String openBing() {
        BotDriver.initDriver();
        ChromeDriver driver = BotDriver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 启动需要打开的网页
        driver.get("https://cn.bing.com/");
        String title = driver.getTitle();
        driver.quit();
        return title;
    }

    @Override
    public String computeTime4RequestUrl() {
        BotDriver.initDriver();
        ChromeDriver driver = BotDriver.getDriver();
        // 启动需要打开的网页
        driver.get("http://localhost:5500/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("canvas")));
//        JavascriptExecutor driver_js = (JavascriptExecutor) driver;
//        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);

//        DevTools devTools = driver.getDevTools();

//        devTools.addListener(Network.responseReceived(), responseReceived -> {
//            RequestId requestId = responseReceived.getRequestId();
//            Network.GetResponseBodyResponse response = devTools.send(Network.getResponseBody(requestId));
//            String body = response.getBody();
//            System.out.println(body);
//        });
        // 监听网络请求
//        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

//        devTools.createSession();


        try {

            for (int i = 0; i < 4; i++) {

                boolean isMergeApi = i % 2 == 0;
                System.out.println(i);
                if(i>0){
                    driver.executeScript("location.reload()");
                }
                Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(10));

                if (isMergeApi) {
                    driver.executeScript("app.testInitDoorDesignERP('mctool')");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));
                    driver.executeScript("app.setSeriesMergeAPI(true)");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));

                    driver.executeScript("app.testApplyFactorySeries2(30754,'02.02.014','Web推拉2',60)");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));
                    System.out.println("dot");
                    driver.executeScript("app.testApplyFactorySeries2(30754,'02.02.014','Web推拉2',60)");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));
                    driver.executeScript("'aaaa '");

                    driver.executeScript("app.testApplyFactorySeries2(42816,'02.02.014','Web推拉2',60)");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(10));

                } else {
                    driver.executeScript("app.testInitDoorDesignERP('mctool')");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));

                    driver.executeScript("app.setSeriesMergeAPI(false)");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));

                    driver.executeScript("app.testApplyFactorySeries2(30754,'02.02.014','Web推拉2',60)");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));
                    System.out.println("dot 2");

                    driver.executeScript("app.testApplyFactorySeries2(30754,'02.02.014','Web推拉2',60)");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));

                    driver.executeScript("app.testApplyFactorySeries2(42816,'02.02.014','Web推拉2',60)");
                    Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(10));

                }
                BotDriver.logEntryList().forEach(System.out::println);
                BotDriver.browerLogEntryList().forEach(e->{
                    System.out.println(e);
                });
            }

//            System.out.println("logs"+ logs.getAll().toArray().length);
//            for(LogEntry entry : logs) {
//                System.out.println("chrome.console===="+" "+entry.getLevel() +" "+ entry.getMessage());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            driver.quit();
        }
        return "";
    }
}
