package com.zizimee.api.pimanager.common.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkageWebDriver {

    private final WebDriver driver;
    private WebElement element;

    private static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static String CHROMEDRIVER_PATH = "C:\\Selenium\\chromedriver.exe";
    public static String ENTERPRISE_URL = "http://localhost:8080";

    public LinkageWebDriver() {
        System.setProperty(WEB_DRIVER_ID, CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
    }

    public boolean login(String id, String password) {

        String user;

        try {
            driver.get(ENTERPRISE_URL + "/login");

            element = driver.findElement(By.id("username"));
            Thread.sleep(500);
            element.sendKeys(id);

            element = driver.findElement(By.id("password"));
            Thread.sleep(500);
            element.sendKeys(password);

            element = driver.findElement(By.className("btn_primary"));
            element.submit();

            Thread.sleep(10000);

            driver.get(ENTERPRISE_URL + "/main");

            user = driver.findElement(By.tagName("h1")).getText();

            return user.equals("Users");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
        return false;
    }

}
