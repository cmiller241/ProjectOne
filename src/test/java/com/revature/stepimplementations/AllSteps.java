package com.revature.stepimplementations;

import com.revature.runners.BasicRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.revature.runners.BasicRunner.driver;


public class AllSteps {

    final String manUsername = "g8tor";
    final String manPassword = "chomp!";
    final String tester1Username = "ryeGuy";
    final String tester1Password = "coolbeans";
    private String tempTester;
    private String tempDefect;
    private String tempTest;
    private String tempId;
    private String tempTitle = "bob51332";
    private String tempDescription;
    private String tempSteps;
    private String tempPerformedBy;
    private String tempTestResult;
    private String tempSummary;
    private String newDefectList;
    private String newTestList;

    @Given("The manager is logged in as a manager")
    public void the_manager_is_logged_in_as_a_manager() throws InterruptedException {
        BasicRunner.driver.get(BasicRunner.webURL);
        BasicRunner.loginPage.loginToSite("g8tor","chomp!");
        Thread.sleep(500);
    }

    @Given("The manager is on the home page")
    public void the_manager_is_on_the_home_page() throws InterruptedException {
        Assert.assertEquals(driver.getCurrentUrl(), "https://bugcatcher-jasdhir.coe.revaturelabs.com/managerhome", "Error: Manager is not on the homepage!");
    }

    @Then("The manager should see pending defects")
    public void the_manager_should_see_pending_defects() {
        Boolean isPresent = BasicRunner.homePage.pendingDefects.size()>0;
        Assert.assertEquals(isPresent, true, "Error: No pending defects found");
    }

    @When("The manager clicks on the select button for a defect")
    public void the_manager_clicks_on_the_select_button_for_a_defect() throws InterruptedException {
        BasicRunner.homePage.selectButton.click();
        Thread.sleep(300);
    }

    @Then("The defect description should appear in bold")
    public void the_defect_description_should_appear_in_bold() throws InterruptedException {
        tempDefect = BasicRunner.homePage.defectInBold.getText();
        String fontWeight = BasicRunner.homePage.defectInBold.getCssValue("font-weight");
        boolean isBold = "bold".equals(fontWeight) || "bolder".equals(fontWeight) || Integer.parseInt(fontWeight) >= 700;
        Assert.assertTrue(isBold,"Error: The defect description does not appear in bold");
    }

    @When("The manager selects a tester from the drop down")
    public void the_manager_selects_a_tester_from_the_drop_down() throws InterruptedException {
        tempTester = BasicRunner.homePage.sDataList.getAttribute("value");
        BasicRunner.homePage.inputList.sendKeys(tempTester);
    }

    @When("The manager clicks assign")
    public void the_manager_clicks_assign() throws InterruptedException {
        BasicRunner.homePage.assignButton.click();
        Thread.sleep(300);
    }

    @Then("The defect should disappear from the list")
    public void the_defect_should_disappear_from_the_list() throws InterruptedException {
        Boolean isPresent = driver.findElements(By.xpath("//td[contains(text(), '" + tempDefect + "')]")).size() > 0;
        Assert.assertEquals(isPresent, false);
    }

    @Given("The assigned tester is on their home page")
    public void the_assigned_tester_is_on_their_home_page() throws InterruptedException {
        BasicRunner.driver.get(BasicRunner.webURL);
        BasicRunner.loginPage.loginToSite(tester1Username,tester1Password);
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://bugcatcher-jasdhir.coe.revaturelabs.com/testerhome");
    }

    @Then("The tester should see the pending defect")
    public void the_tester_should_see_the_pending_defect() {
        List<WebElement> theDefects= BasicRunner.homePage.allDefects;
        Boolean isPresent = false;
        for (WebElement elem:theDefects){
            if (elem.getText().contains(tempDefect)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertEquals(isPresent, true, "Error: The following defect cannot be found: " + tempDefect);
    }

    @Given("The tester is on the Home Page")
    public void the_tester_is_on_the_home_page() throws InterruptedException {
        driver.get(BasicRunner.webURL);
        BasicRunner.loginPage.loginToSite(tester1Username,tester1Password);
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "https://bugcatcher-jasdhir.coe.revaturelabs.com/testerhome", "Error: Not on tester homepage");
    }
    @Then("The tester can can see only defects assigned to them")
    public void the_tester_can_can_see_only_defects_assigned_to_them() throws InterruptedException {
        Actions actions = new Actions(driver);
        for (WebElement elem: BasicRunner.homePage.allDefects) {
            actions.moveToElement(elem).click().perform();
            Thread.sleep(500);  //needs to be 500
        }
        Boolean allPresent = true;
        for (WebElement elem:BasicRunner.homePage.defectsAssigned) {
            if (!elem.getText().equals("Assigned To: "+ tester1Username)) allPresent=false;
        }
        Assert.assertEquals(allPresent, true, "Error: All defects are not assigned to current user");
    }
    @When("The tester changes a defect to any status")
    public void the_tester_changes_a_defect_to_any_status() throws InterruptedException {
        BasicRunner.homePage.statusButton.click();
        Thread.sleep(300);
        BasicRunner.homePage.acceptedButton.click();
    }
    @Then("The tester should see the defect has a different status")
    public void the_tester_should_see_the_defect_has_a_different_status() {
        Assert.assertEquals(BasicRunner.homePage.defectStatus.getText().contains("Accepted"), true, "Error: Defect status was not changed");
    }
    @Given("The employee is on the Defect Reporter Page")
    public void the_employee_is_on_the_defect_reporter_page() throws InterruptedException {
        driver.get(BasicRunner.webURL);
        BasicRunner.loginPage.loginToSite(manUsername, manPassword);
        Thread.sleep(500);
        BasicRunner.homePage.linkToDefect.click();
        Thread.sleep(500);
    }
    @When("The employee selects todays date")
    public void the_employee_selects_todays_date() throws InterruptedException {
        BasicRunner.defectReport.dateField.clear();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        BasicRunner.defectReport.dateField.sendKeys(dtf.format(LocalDateTime.now()));
    }
    @When("The employee types in {string} with")
    public void the_employee_types_in_with(String string, String docString) throws InterruptedException {
        if (string.equals( "Description")) BasicRunner.defectReport.descField.sendKeys(docString);
        if (string.equals("Steps")) BasicRunner.defectReport.stepsField.sendKeys(docString);
    }
    @When("The employee selects high priority")
    public void the_employee_selects_high_priority() {
        for (int i=0;i<3;i++) BasicRunner.defectReport.priorityField.sendKeys(Keys.RIGHT);
    }
    @When("The employee selects low severity")
    public void the_employee_selects_low_severity() throws InterruptedException {
        for (int i=0;i<3;i++) BasicRunner.defectReport.severityField.sendKeys(Keys.LEFT);
    }
    @When("The employee clicks the report button")
    public void the_employee_clicks_the_report_button() throws InterruptedException {
        BasicRunner.defectReport.reportButton.click();
        Thread.sleep(500);
    }
    @Then("No confirmation dialog appears")
    public void no_confirmation_dialog_appears() throws InterruptedException {
        Boolean isAlert=true;
        try {
            Alert alert = driver.switchTo().alert();
            String AlertText = alert.getText();
        } catch (Exception e) {
            isAlert=false;
        }
        Assert.assertEquals(isAlert, false, "Error: No confirmation dialog appears");
    }
    @Then("There should be a confirmation box")
    public void there_should_be_a_confirmation_box() {
        Boolean isAlert=true;
        try {
            Alert alert = driver.switchTo().alert();
            String AlertText = alert.getText();
        } catch (Exception e) {
            isAlert=false;
        }
        Assert.assertEquals(isAlert, true);
    }
    @When("The employee clicks Ok")
    public void the_employee_clicks_ok() throws InterruptedException {
        driver.switchTo().alert().accept();
        Thread.sleep(300);
    }
    @Then("A modal should appear with a Defect ID")
    public void a_modal_should_appear_with_a_defect_id() throws InterruptedException {
        Boolean isPresent = BasicRunner.defectReport.modalDefectID.size() > 0;
        Assert.assertEquals(isPresent, true, "Error: Modal did not appear with a Defect ID");
    }
    @When("The employee clicks close")
    public void the_employee_clicks_close() throws InterruptedException {
        BasicRunner.defectReport.closeButton.click();
        Thread.sleep(300);
    }
    @Then("The modal should disappear")
    public void the_modal_should_disappear() throws InterruptedException {
        Boolean isPresent = BasicRunner.defectReport.modalDefectID.size() > 0;
        Assert.assertEquals(isPresent, false, "Error: The modal should have disappeared");
    }
    @Given("The employee is on the login page")
    public void the_employee_is_on_the_login_page() {
        driver.get(BasicRunner.webURL);
    }

    @When("The employee clicks on the login button")
    public void the_employee_clicks_on_the_login_button() throws InterruptedException {
        BasicRunner.loginPage.submit.click();
        Thread.sleep(500);
    }
    @Then("The employee should see an alert saying they have the wrong password")
    public void the_employee_should_see_an_alert_saying_they_have_the_wrong_password() throws InterruptedException {
        Boolean isAlert=true;
        try {
            Alert alert = driver.switchTo().alert();
            if (!alert.getText().equals("Wrong password for user")) isAlert = false;
            //Should equal "Wrong password for user"
        } catch (Exception e) {
            isAlert=false;
        }
        Assert.assertEquals(isAlert, true, "Error: User does not see an alert about a wrong password!");
    }
    @Then("The employee should see an alert saying no user with that username found")
    public void the_employee_should_see_an_alert_saying_no_user_with_that_username_found() throws InterruptedException {
        Boolean isAlert=true;
        try {
            Alert alert = driver.switchTo().alert();
            if (!alert.getText().equals("Username not found")) isAlert = false;
        } catch (Exception e) {
            isAlert=false;
        }
        Assert.assertEquals(isAlert, true, "Error: No alert warning about non-existent username found occurs!");
    }

    @When("The employee types {string} into username input")
    public void the_employee_types_into_username_input(String string) {
        BasicRunner.loginPage.username.sendKeys(string);
    }
    @When("The employee types {string} into password input")
    public void the_employee_types_into_password_input(String string) {
        BasicRunner.loginPage.password.sendKeys(string);
    }

    @Then("the employee should be on the {string} page")
    public void the_employee_should_be_on_the_page(String string) throws InterruptedException {
        Boolean isPresent = driver.findElements(By.xpath("//h1[contains(text(), '" + string + "')]")).size() > 0;
        Assert.assertEquals(isPresent, true);
    }
    @Then("The employee should see their name {string} {string} on the home page")
    public void the_employee_should_see_their_name_on_the_home_page(String string, String string2) throws InterruptedException {
        String nameGreeting = BasicRunner.homePage.greeting.getText();
        Assert.assertEquals(nameGreeting.contains(string), true, "Error: First name is not in greeting!");
        Assert.assertEquals(nameGreeting.contains(string2), true, "Error: Last name is not in greeting!");
    }

    @Then("A manager can pull up a form to make a new matrix")
    public void a_manager_can_pull_up_a_form_to_make_a_new_matrix() throws InterruptedException {
        BasicRunner.homePage.matrixButton.click();
        Thread.sleep(300);
        Boolean isPresent = BasicRunner.homePage.fieldSet.size() > 0;
        Assert.assertEquals(isPresent, true, "Error: New matrix fieldset does not open!");
    }
    @When("A manager creates a title for a matrix")
    public void a_manager_creates_a_title_for_a_matrix() {
        tempTitle = RandomStringUtils.randomAlphabetic((int) (Math.random()*50));
        BasicRunner.homePage.titleField.sendKeys(tempTitle);
    }
    @When("A manager adds requirements to a matrix")
    public void a_manager_adds_requirements_to_a_matrix() {
        BasicRunner.homePage.userStoryField.sendKeys(RandomStringUtils.randomAlphabetic((int) (Math.random()*50)));
        BasicRunner.homePage.noteField.sendKeys(RandomStringUtils.randomAlphabetic((int) (Math.random()*50)));
        BasicRunner.homePage.addRequirementField.click();
    }
    @When("A manager saves a matrix")
    public void a_manager_saves_a_matrix() throws InterruptedException {
        BasicRunner.homePage.createMatrixButton.click();
    }
    @Then("The matrix should be visible for all testers and managers")
    public void the_matrix_should_be_visible_for_all_testers_and_managers() throws InterruptedException {
        BasicRunner.homePage.linkToMatrices.click();
        Thread.sleep(500);
        Boolean isPresent = driver.findElements(By.xpath("//li[contains(text(), '" + tempTitle + "')]")).size() > 0;
        Assert.assertEquals(isPresent, true, "Error: Matrices don't exist!");
    }

    @Given("A manager or tester has selected a matrix")
    public void a_manager_or_tester_has_selected_a_matrix() throws InterruptedException {
        BasicRunner.matrixPage.firstShowButton.click();
        Thread.sleep(300);
    }

    @When("A manager or tester adds defects")
    public void a_manager_or_tester_adds_defects() throws InterruptedException {
        String defectList = BasicRunner.matrixPage.firstDefectList.getText();
        BasicRunner.matrixPage.firstEditButton.click();
        Thread.sleep(300);
        String optionValue = BasicRunner.matrixPage.sDataList.getAttribute("value");
        if (defectList.equals("None")) newDefectList = optionValue;              //Checks that the defectList is added.
        else newDefectList = defectList + ", " + optionValue;
        BasicRunner.matrixPage.defectList.sendKeys(optionValue);
        BasicRunner.matrixPage.defectAddButton.click();
        Thread.sleep(300);
    }

    @When("A manager or tester removes defects")
    public void a_manager_or_tester_removes_defects() throws InterruptedException {
        String defectList = BasicRunner.matrixPage.firstDefectList.getText();
        BasicRunner.matrixPage.firstEditButton.click();
        Thread.sleep(300);
        BasicRunner.matrixPage.defectRemoveButton.click();
        Boolean isChanged = false;
        if (!defectList.equals(BasicRunner.matrixPage.firstDefectList.getText())) isChanged = true;
        Assert.assertEquals(isChanged, true, "Error: Defect did not appear to be deleted!");
    }

    @When("A manager or tester confirms their changes")
    public void a_manager_or_tester_confirms_their_changes() {
        Boolean hasChanged = false;
        String defectList = BasicRunner.matrixPage.firstDefectList.getText();
        String testList = BasicRunner.matrixPage.firstTestCaseList.getText();
        if ((!defectList.isEmpty()) && (defectList.equals(newDefectList))) hasChanged = true;
        if ((!testList.isEmpty()) && (testList.equals(newTestList))) hasChanged = true;
        Assert.assertEquals(hasChanged, true, "Error: Test cases or defects have not changed!");
    }
    @Then("Then the matrix should saved")
    public void then_the_matrix_should_saved() {
        BasicRunner.matrixPage.saveRequirementsButton.click();
    }

    @Given("The employee clicks on Matrices")
    public void the_employee_clicks_on_matrices() throws InterruptedException {
        BasicRunner.homePage.linkToMatrices.click();
        Thread.sleep(500);
    }

    @When("A manager or tester adds or removes Test Cases")
    public void a_manager_or_tester_adds_or_removes_test_cases() throws InterruptedException {
        String testList = BasicRunner.matrixPage.firstTestCaseList.getText();
        BasicRunner.matrixPage.firstEditButton.click();
        Thread.sleep(300);
        String optionValue = BasicRunner.matrixPage.tDataList.getAttribute("value");
        if (testList.isEmpty()) newTestList = optionValue;              //Checks that the defectList is added.
        else newTestList = testList + ", " + optionValue;
        BasicRunner.matrixPage.testList.sendKeys(optionValue);
        BasicRunner.matrixPage.testCaseAddButton.click();
        Thread.sleep(300);
    }

    @Then("The manager should see links for Matrices, Test Cases, Defect Reporting and Defect Overview")
    public void the_manager_should_see_links_for_matrices_test_cases_defect_reporting_and_defect_overview() {
        Boolean isPresent = BasicRunner.homePage.linkToMatrices.isDisplayed();
        Assert.assertEquals(isPresent, true, "Error: Matrix Link not available!");
        isPresent = BasicRunner.homePage.linkToTestCases.isDisplayed();
        Assert.assertEquals(isPresent, true, "Error: Test Case link not available!");
        isPresent = BasicRunner.homePage.linkToDefect.isDisplayed();
        Assert.assertEquals(isPresent, true);
        isPresent = BasicRunner.homePage.linktoOverview.isDisplayed();
        Assert.assertEquals(isPresent, true);
    }

    @When("The manager clicks on {string}")
    public void the_manager_clicks_on(String string) throws InterruptedException {
        WebElement linkToClick = driver.findElement(By.xpath("//a[text()='" + string +"']"));
        linkToClick.click();
        Thread.sleep(500);
    }
    @Then("The title of page should be {string}")
    public void the_title_of_page_should_be(String string) {
        Boolean isComparable = false;
        WebElement pageTitle = driver.findElement(By.xpath("//h1"));
        System.out.println(pageTitle.getText());
        System.out.println(string);
        if(pageTitle.getText().equals(string)) isComparable = true;
        Assert.assertEquals(isComparable, true, "Error: The page title is not the same!");
    }
    @When("The manager clicks the browser back button")
    public void the_manager_clicks_the_browser_back_button() {
        driver.navigate().back();
    }
    @Then("The manager should be on the home page and the title of page is Home")
    public void the_manager_should_be_on_the_home_page_and_the_title_of_page_is_home() {
        Boolean isComparable = false;
        WebElement pageTitle = driver.findElement(By.xpath("//h1"));
        if(pageTitle.getText().equals("Manager Home")) isComparable = true;
        Assert.assertEquals(isComparable, true, "Error: The manager is not on the homepage!");
    }

    @Given("The tester is on the test case dashboard")
    public void the_tester_is_on_the_test_case_dashboard() throws InterruptedException {
        driver.get(BasicRunner.webURL);
        BasicRunner.loginPage.loginToSite(tester1Username, tester1Password);
        Thread.sleep(500);
        BasicRunner.homePage.linkToTestCases.click();
        Thread.sleep(500);
    }
    @When("The tester types {string} into input with")
    public void the_tester_types_into_input_with(String string, String docString) {
        if (string.equals( "Description")) {
            BasicRunner.testPage.descBox.sendKeys(docString);
            tempTest = docString;
        }
        if (string.equals("Steps")){
            BasicRunner.testPage.stepsBox.sendKeys(docString);
        }
    }
    @When("The tester presses the submit button")
    public void the_tester_presses_the_submit_button() throws InterruptedException {
        BasicRunner.testPage.submitButton.click();
        Thread.sleep(300);
    }
    @Then("The test case should appear at the bottom of the table")
    public void the_test_case_should_appear_at_the_bottom_of_the_table() {
        Boolean isPresent = driver.findElements(By.xpath("//tr[last()]/td[contains(text(),'" + tempTest + "')]")).size() > 0;
        Assert.assertEquals(isPresent, true);
    }
    @Then("The test case result should say UNEXECUTED")
    public void the_test_case_result_should_say_unexecuted() throws InterruptedException {
        WebElement testStatus = BasicRunner.testPage.testCaseResult;
        Boolean isComparable = false;
        if(testStatus.getText().equals("UNEXECUTED")) isComparable = true;
        Assert.assertEquals(isComparable, true, "Error: Test case result does not read UNEXECUTED despite just being made" );
    }
    @When("The tester presses on details")
    public void the_tester_presses_on_details() throws InterruptedException {
        BasicRunner.testPage.lastCaseDetails.click();
        Thread.sleep(300);
    }
    @Then("A test case modal should appear showing the case ID")
    public void a_test_case_modal_should_appear_showing_the_case_id() {
        String modalId = BasicRunner.testPage.modalGreeting.getText();
        Boolean isPresent = false;
        if (modalId.contains(BasicRunner.testPage.testID.getText())) isPresent = true;
        Assert.assertEquals(isPresent, true, "Error: Test Case Info includes wrong Test ID");
    }
    @Then("The performed by field should say No One")
    public void the_performed_by_field_should_say_no_one() {
        Boolean isPresent = false;
        if (BasicRunner.testPage.performBy.getText().equals("No One")) isPresent = true;
        Assert.assertEquals(isPresent, true, "Error: Test Case should be performed by 'No One'");
    }
    @When("The tester presses the close buttton")
    public void the_tester_presses_the_close_buttton() throws InterruptedException {
        BasicRunner.testPage.closeButton.click();
        Thread.sleep(300);
    }
    @Then("The Modal Should be closed")
    public void the_modal_should_be_closed() {
        Boolean checkId = BasicRunner.testPage.performBys.size()>0;
        Assert.assertEquals(checkId, false, "Error: The modal should be closed!");
    }

    @When("The Tester clicks on edit within the modal")
    public void the_tester_clicks_on_edit_within_the_modal() throws InterruptedException {
        tempId = BasicRunner.testPage.testID.getText();
        BasicRunner.testPage.linkToEdit.click();
        Thread.sleep(300);
    }
    @Then("The Tester should be on the case editor for that case")
    public void the_tester_should_be_on_the_case_editor_for_that_case() throws InterruptedException {
        Boolean isCaseId = false;
        if (BasicRunner.testPage.header.getText().contains(tempId)) isCaseId = true;
        Assert.assertEquals(isCaseId, true, "Error: You are not editing test case you selected");
    }
    @Then("The fields should be uneditable")
    public void the_fields_should_be_uneditable() throws InterruptedException {
        Boolean isEditable = false;
        for (WebElement elem:BasicRunner.testPage.allFields) {
            if (elem.isEnabled()) isEditable = true;
        }
        Thread.sleep(300);
        Assert.assertEquals(isEditable, false, "Error: One or more of the fields is editable!");
    }
    @When("The tester clicks on the edit button")
    public void the_tester_clicks_on_the_edit_button() throws InterruptedException {
        tempTestResult = BasicRunner.testPage.testResult.getText();
        BasicRunner.testPage.editButton.click();
        Thread.sleep(300);
    }
    @Then("The test case fields should be editable")
    public void the_test_case_fields_should_be_editable() {
        Boolean isEditable = true;
        for (WebElement elem:BasicRunner.testPage.allFields) {
            if (!elem.isEnabled()) isEditable = false;
        }
        Assert.assertEquals(isEditable, true, "Error: One or more text fields cannot be edited");
    }
    @When("The tester types in a new description into the description text area")
    public void the_tester_types_in_a_new_description_into_the_description_text_area() {
        tempDescription = BasicRunner.testPage.descBox.getText();
        String randDescription = RandomStringUtils.randomAlphabetic((int) (Math.random()*50));
        BasicRunner.testPage.descBox.clear();
        BasicRunner.testPage.descBox.sendKeys(randDescription);
    }
    @When("The tester types in a new steps into the steps text area")
    public void the_tester_types_in_a_new_steps_into_the_steps_text_area() {
        tempSteps = BasicRunner.testPage.stepsBox.getText();
        String randDescription = RandomStringUtils.randomAlphabetic((int) (Math.random()*50));
        BasicRunner.testPage.stepsBox.clear();
        BasicRunner.testPage.stepsBox.sendKeys(randDescription);
    }
    @When("The tester clicks on the automated check mark")
    public void the_tester_clicks_on_the_automated_check_mark() {
        BasicRunner.testPage.checkBox.click();
    }
    @When("The tester selects ryeGuy for performed from drop down")
    public void the_tester_selects_rye_guy_for_performed_from_drop_down() throws InterruptedException {
        Select selectBox = new Select(driver.findElement(By.xpath("//select[1]")));
        tempPerformedBy = selectBox.getFirstSelectedOption().getText();
        selectBox.selectByVisibleText(tester1Username);
    }
    @When("The tester selects FAIL for test result from drop down")
    public void the_tester_selects_fail_for_test_result_from_drop_down() throws InterruptedException {
        Select selectBox = new Select(driver.findElement(By.xpath("//fieldset[2]/select")));
        selectBox.selectByVisibleText("FAIL");
    }
    @When("The tester types in a new summary into the summary text area")
    public void the_tester_types_in_a_new_summary_into_the_summary_text_area() {
        tempSummary = BasicRunner.testPage.summaryBox.getText();
        BasicRunner.testPage.summaryBox.clear();
        String randDescription = RandomStringUtils.randomAlphabetic((int) (Math.random()*50));
        BasicRunner.testPage.summaryBox.sendKeys(randDescription);
    }
    @When("The tester clicks save on test case page")
    public void the_tester_clicks_save_on_test_case_page() throws InterruptedException {
        BasicRunner.testPage.saveButton.click();
        Thread.sleep(300);
    }

    @Then("An alert says the test case has been saved")
    public void an_alert_says_the_test_case_has_been_saved() throws InterruptedException {
        Boolean isAlert=true;
        try {
            Alert alert = driver.switchTo().alert();
            if (!alert.getText().equals("Test Case has been Saved")) isAlert = false;
            alert.accept();
            //Should equal "Wrong password for user"
        } catch (Exception e) {
            isAlert=false;
        }
        Assert.assertEquals(isAlert, true, "Error: Alert does not appear saying test case has been saved!");
    }


    @When("The tester clicks on the reset button")
    public void the_tester_clicks_on_the_reset_button() {
        BasicRunner.testPage.resetButton.click();
    }
    @Then("The fields should be populated to their old values")
    public void the_fields_should_be_populated_to_their_old_values() throws InterruptedException {
        String testDescription = BasicRunner.testPage.descBox.getText();
        Assert.assertEquals(testDescription.equals(tempDescription), true, "Test description did not revert");

        String testSteps = BasicRunner.testPage.stepsBox.getText();
        Assert.assertEquals(testSteps.equals(tempSteps), true, "Test steps did not revert");

        String testPerformedBy = BasicRunner.testPage.h6performBy.getText();
        Assert.assertEquals(testPerformedBy.equals(testPerformedBy), true, "Test performer did not revert");

        String testResult = BasicRunner.testPage.testResult.getText();
        Assert.assertEquals(testResult.equals(tempTestResult), true, "Test result did not revert");

        String testSummary = BasicRunner.testPage.summaryBox.getText();
        Assert.assertEquals(testSummary.equals(tempSummary), true, "Summary textarea did not revert");
    }

}