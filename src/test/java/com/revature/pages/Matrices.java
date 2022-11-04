package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Matrices {

    public Matrices(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//button[text()='Show']")
    public WebElement firstShowButton;

    @FindBy(xpath="//td[3]")
    public WebElement firstTestCaseList;
    @FindBy(xpath="//td[4]")
    public WebElement firstDefectList;

    @FindBy(xpath="//button[text()='Edit']")
    public WebElement firstEditButton;

    @FindBy(xpath="//input[@list='defectlist']")
    public WebElement defectList;

    @FindBy(xpath="//input[@list='testlist']")
    public WebElement testList;

    @FindBy(css="datalist#defectlist option:nth-child(1)")
    public WebElement sDataList;

    @FindBy(css="datalist#testlist option:nth-child(1)")
    public WebElement tDataList;

    @FindBy(xpath="(//button[text()='Add'])[2]")
    public WebElement defectAddButton;

    @FindBy(xpath="//ul[2]//button[text()='Remove']")
    public WebElement defectRemoveButton;

    @FindBy(xpath="(//button[text()='Add'])[1]")
    public WebElement testCaseAddButton;

    @FindBy(xpath="//button[text()='Save Requirements']")
    public WebElement saveRequirementsButton;


}
