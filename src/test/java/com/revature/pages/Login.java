package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[1]")
    public WebElement username;

    @FindBy(xpath ="//input[2]")
    public WebElement password;

    @FindBy(xpath="//button[text()='Login']")
    public WebElement submit;

    public void loginToSite(String uName, String pWord) throws InterruptedException {
        username.sendKeys(uName);
        password.sendKeys(pWord);
        submit.click();
    }
}
