package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DefectReport {

    public DefectReport (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath="//input[1]")
    public WebElement dateField;

    @FindBy (xpath="//textarea[1]")
    public WebElement descField;

    @FindBy (xpath="//textarea[2]")
    public WebElement stepsField;

    @FindBy (xpath="//input[2]")
    public WebElement severityField;

    @FindBy (xpath="//input[3]")
    public WebElement priorityField;

    @FindBy (xpath="//button[text()='Report']")
    public WebElement reportButton;

    @FindBy (xpath="//div[@class='ReactModalPortal']//h4[contains(text(), 'Defect')]")
    public List<WebElement> modalDefectID;

    @FindBy(xpath="//button[text()='Close']")
    public WebElement closeButton;
}
