package com.sample.test.demo.tests;

import com.sample.test.demo.config.Configuration;
import com.sample.test.demo.pageobject.SamplePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.fail;

public class BaseTest {

    public Logger log = Logger.getRootLogger();
    private Configuration config;
    protected WebDriver driver;
    protected String url;
    protected SamplePage samplePage;

    @BeforeClass(alwaysRun = true)
    public void init() {
        config = new Configuration();
        url = config.getUrl();
        initializeDriver();
        driver.manage().window().maximize();
        navigateToSite();
    }

    private void navigateToSite() {
        driver.get(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            log.error("Unexpected error while closing driver.");
        }
    }

    private void initializeDriver() {
        if (config.getBrowser().equalsIgnoreCase("chrome")) {
            if (config.getPlatform().equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver",
                        "src/test/resources/chromedriver/windows/chromedriver.exe");
            }
            driver = new ChromeDriver();
        } else {
            fail("Unsupported browser " + config.getBrowser());
        }
    }


}
