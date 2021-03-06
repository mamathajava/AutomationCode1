package com.edureka.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;

public class QuestionPage extends DriverHelper{

    private LocatorReader questionLocator;

    public QuestionPage(WebDriver driver) {
        super(driver);

        questionLocator = new LocatorReader("Question.xml");
    }

    /**
     *  Verify Question page
     * @return
     */
    public QuestionPage verifyPage(){
        String pageHeader = questionLocator.getLocator("PageHeader");
        WaitForElementPresent(pageHeader, getTimeOut());
        Assert.assertTrue(isElementPresent(pageHeader));
        return PageFactory.initElements(driver, QuestionPage.class);
    }

    /**
     *  Verify Selected Course Dropdown shows only Enroll Course
     * @param course
     * @return
     */
    public QuestionPage verifySelectedCourseShowEnrollCourse(String course){
        String ddSelectCourse = questionLocator.getLocator("PostQuestion.DDSelectCourse");
        WaitForElementPresent(ddSelectCourse, getTimeOut());
        clickOn(ddSelectCourse);

        String selectedCourse= questionLocator.getLocator("PostQuestion.ListSelectedCourse");
        List <WebElement> selectedCoursList = getWebDriver().findElements(By.xpath(selectedCourse));
        int course_Count= selectedCoursList.size();
        Assert.assertEquals(course_Count,2);
        for (int i=2; i<=course_Count;i++){
            String enrolledCourse="//div[@id='community_chosen']/div/ul/li["+i+"]";
            String enrolledCourseName = getText(enrolledCourse);
            if(enrolledCourseName.contains(course)){
                Assert.assertTrue(enrolledCourseName.contains(course));
                String courseName= "//li[text()='"+course+"']";
                WaitForElementPresent(courseName, getTimeOut());
                clickOn(courseName);
                break;
            }
        }
        return PageFactory.initElements(driver, QuestionPage.class);
    }

    /**
     *  Post Question
     * @param courseName
     * @param categoryName
     * @param questionTitle
     * @param question
     * @return
     * @throws InterruptedException 
     */
    public ForumsPage postQuestion(String courseName, String categoryName, String questionTitle, String question) throws InterruptedException{
        String ddSelectCourse = questionLocator.getLocator("PostQuestion.DDSelectCourse");
        WaitForElementPresent(ddSelectCourse, getTimeOut());
        clickOn(ddSelectCourse);

        String course= "//li[text()='"+courseName+"']";
        WaitForElementPresent(course, getTimeOut());
        clickOn(course);

        String ddSelectCategory= questionLocator.getLocator("PostQuestion.DDSelectCategory");
        WaitForElementPresent(ddSelectCategory, getTimeOut());
        clickOn(ddSelectCategory);

        String category= "//li[text()='"+categoryName+"']";
        WaitForElementPresent(category, getTimeOut());
        clickOn(category);

        String tbQuestionTitle= questionLocator.getLocator("PostQuestion.TBQuestionTitle");
        WaitForElementPresent(tbQuestionTitle, getTimeOut());
        sendKeys(tbQuestionTitle, questionTitle);

        String tbtextAreaQuestion= questionLocator.getLocator("PostQuestion.TextAreaQuestion");
        WaitForElementPresent(tbtextAreaQuestion, getTimeOut());
        sendKeys(tbtextAreaQuestion, question);

        String btnSubmit = questionLocator.getLocator("PostQuestion.BTNSubmit");
        WaitForElementPresent(btnSubmit, getTimeOut());
        clickOn(btnSubmit);
        pageLoading();
        return PageFactory.initElements(driver, ForumsPage.class);
    }

    /**
     *  Verify Error Message for Blank field
     * @param message
     * @return
     */
    public QuestionPage VerifyErrorForBlankField(String message) throws InterruptedException{
        String errorMessage= "//span[contains(text(),'"+message+"')]";
        WaitForElementPresent(errorMessage, getTimeOut());
        Assert.assertTrue(isElementPresent(errorMessage));
        return PageFactory.initElements(driver, QuestionPage.class);
    }

    /**
     * Select Category from Category dropdown
     * @param categoryName
     * @return
     */
    public QuestionPage selectCategory(String categoryName){
        String ddSelectCategory= questionLocator.getLocator("PostQuestion.DDSelectCategory");
        WaitForElementPresent(ddSelectCategory, getTimeOut());
        clickOn(ddSelectCategory);

        String category= "//li[text()='"+categoryName+"']";
        WaitForElementPresent(category, getTimeOut());
        clickOn(category);
        return PageFactory.initElements(driver, QuestionPage.class);
    }

    /**
     *  Enter Question Title 
     * @param questionTitle
     * @return
     */
    public QuestionPage enterQuestionTitle(String questionTitle){
        String tbQuestionTitle= questionLocator.getLocator("PostQuestion.TBQuestionTitle");
        WaitForElementPresent(tbQuestionTitle, getTimeOut());
        sendKeys(tbQuestionTitle, questionTitle);
        return PageFactory.initElements(driver, QuestionPage.class);
    }

    /**
     *  Click on Submit button
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> T clickOnSubmitButton(Class className){
        String btnSubmit = questionLocator.getLocator("PostQuestion.BTNSubmit");
        WaitForElementPresent(btnSubmit, getTimeOut());
        clickOn(btnSubmit);
        return (T) PageFactory.initElements(driver, className);
    }

    /**
     *  Enter Question description
     * @param question
     * @return
     */
    public QuestionPage enterQuestionDescription(String question){
        String tbtextAreaQuestion= questionLocator.getLocator("PostQuestion.TextAreaQuestion");
        WaitForElementPresent(tbtextAreaQuestion, getTimeOut());
        sendKeys(tbtextAreaQuestion, question);
        return PageFactory.initElements(driver, QuestionPage.class);
    }

    /**
     *  Error For Wrong question Title
     * @return
     */
    public QuestionPage verifyErrorForWrongTitle(){
        String errorWrongQuestionTitle= questionLocator.getLocator("PostQuestion.WrongQuestionTitleClass");
        WaitForElementPresent(errorWrongQuestionTitle, getTimeOut());
        Assert.assertTrue(isElementPresent(errorWrongQuestionTitle));
        return PageFactory.initElements(driver, QuestionPage.class);
    }

    /**
     *  Verify Attached File link 
     * @return
     */
    public QuestionPage verifyAttachedFileLink(){
        String ltAttachedFile = questionLocator.getLocator("LTAttachedFile");
        WaitForElementPresent(ltAttachedFile, getTimeOut());
        Assert.assertTrue(isElementPresent(ltAttachedFile));
        return PageFactory.initElements(driver, QuestionPage.class);
    }
}