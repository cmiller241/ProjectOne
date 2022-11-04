package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TestCases {

    public TestCases(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//h1")
    public WebElement header;

    @FindBy(xpath="//textarea[1]")
    public WebElement descBox;

    @FindBy(xpath="//textarea[2]")
    public WebElement stepsBox;

    @FindBy(xpath="//button[text()=' Submit']")
    public WebElement submitButton;

    @FindBy(xpath="//tr[last()]/td[3]")
    public WebElement testCaseResult;

    @FindBy(xpath="//tr[last()]/td[last()]/button")
    public WebElement lastCaseDetails;

    @FindBy(xpath="//tr[last()]/td")
    public WebElement testID;

    @FindBy(xpath="//div[@class='ReactModalPortal']//h3")
    public WebElement modalGreeting;

    @FindBy(xpath="//div[@class='ReactModalPortal']//p[last()]")
    public WebElement performBy;

    @FindBy(xpath="//div[@class='ReactModalPortal']//p[last()]")
    public List<WebElement> performBys;

    @FindBy(xpath="//fieldset[1]/p[2]")
    public WebElement h6performBy;

    @FindBy(xpath="//button[text()='Close']")
    public WebElement closeButton;

    @FindBy(xpath="//a[text()='Edit']")
    public WebElement linkToEdit;

    @FindBy(xpath="//textarea")
    public List<WebElement> allFields;

    @FindBy(xpath="//fieldset[2]/p")
    public WebElement testResult;

    @FindBy(xpath="//button[text()='Edit']")
    public WebElement editButton;

    @FindBy(xpath="//input[@type='checkbox']")
    public WebElement checkBox;

    @FindBy(xpath="//fieldset[2]/textarea")
    public WebElement summaryBox;

    @FindBy(xpath="//button[2]")
    public WebElement saveButton;

    @FindBy(xpath="//button[1]")
    public WebElement resetButton;
}
