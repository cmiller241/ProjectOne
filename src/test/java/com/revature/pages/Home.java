package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Home {

    public Home(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath="//body/div[@id='root']/nav[1]/p[1]")
    public WebElement greeting;

    @FindBy (xpath="//a[text()='Report a Defect']")
    public WebElement linkToDefect;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> pendingDefects;

    @FindBy(xpath="//button[text()='Select']")
    public WebElement selectButton;

    @FindBy(xpath="//h4")
    public WebElement defectInBold;

    @FindBy(xpath="//input[@list='employees']")
    public WebElement inputList;

    @FindBy(css = "datalist#employees option:nth-child(1)")
    public WebElement sDataList;

    @FindBy(xpath="//button[text()='Assign']")
    public WebElement assignButton;

    @FindBy(xpath="//li")
    public List<WebElement> allDefects;

    @FindBy(xpath="//p[contains(text(), 'Assigned To:')]")
    public List<WebElement> defectsAssigned;

    @FindBy(xpath="//button[text()='Change Status']")
    public WebElement statusButton;

    @FindBy(xpath="//button[text()='Accepted']")
    public WebElement acceptedButton;

    @FindBy(xpath="//div[@class='Collapsible']//b[2]")
    public WebElement defectStatus;

    @FindBy(xpath="//button[text()='Create A new Requirements Matrix']")
    public WebElement matrixButton;

    @FindBy(xpath="//fieldset")
    public List<WebElement> fieldSet;

    @FindBy(xpath="//input[1]")
    public WebElement titleField;

    @FindBy(xpath="//input[@placeholder='User Story']")
    public WebElement userStoryField;

    @FindBy(xpath="//input[@placeholder='Note']")
    public WebElement noteField;

    @FindBy(xpath="//button[text()='Add Requirement']")
    public WebElement addRequirementField;

    @FindBy(xpath="//button[text()='Create Matrix']")
    public WebElement createMatrixButton;

    @FindBy(xpath="//a[text()='Matrices']")
    public WebElement linkToMatrices;

    @FindBy(xpath="//a[text()='Test Cases']")
    public WebElement linkToTestCases;

    @FindBy(xpath="//a[text()='Defect Overview']")
    public WebElement linktoOverview;
}