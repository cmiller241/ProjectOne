package com.revature.runners;

import com.revature.pages.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

    @CucumberOptions(features="classpath:features", glue="com.revature.stepimplementations")
    public class BasicRunner extends AbstractTestNGCucumberTests {

        public static WebDriver driver;
        public static Login loginPage;
        public static Home homePage;
        public static TestCases testPage;
        public static Matrices matrixPage;
        public static DefectReport defectReport;

        public static String webURL = "https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=3";


        @BeforeMethod   //This method will run before each Cucumber scenario
        public void setup() throws InterruptedException {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            loginPage = new Login(driver);
            testPage = new TestCases(driver);
            homePage = new Home(driver);
            defectReport = new DefectReport(driver);
            matrixPage = new Matrices(driver);
        }

        @AfterMethod //This method will run after all scenarios
        public void cleanup() {
            driver.quit();
        }
    }

