package com.edureka.community;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.ForumsPage;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.QuestionPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyOnlyEnrolledCourseIsShowInSelectedCourseDropdown  extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private RazorPayPage razorPayPage;
    private MyProfilePage myProfilePage;
    private ForumsPage fourmsPage;
    private QuestionPage questionPage;

    static  String courseName;

    @Test
    public void test_003VerifyOnlyEnrolledCourseIsShowInSelectedCourseDropdown() throws Exception {

        try {


            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Sign up User
            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);
            

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course" +courseName);
            selectedCoursePage=dashboardPage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            String currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            addLog("Verify Razor page");
         //   razorPayPage=razorPayPage.verifyPage();

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            String bankName = propertyReader.readTestData("Bank");
            addLog("Select Bank" +bankName +" from bank table ");
            razorPayPage=razorPayPage.selectBank(bankName);

            // Click on pay button
            addLog("Click on pay button");
            razorPayPage=razorPayPage.clickOnPayButton();

            // Get Parent window Id
            addLog("Get Parent Window ID");
            String parentWidnow = getParentWindowHandle();

            // Switch Child Window and Click on Succss button
            addLog("Switch Child Window and Click on Succss button");
            switchPreviewWindow();
            myProfilePage=razorPayPage.clickOnSuccessButton();

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            switchParentWindow(parentWidnow);
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Select Forum from Community dropdown
            String forum = propertyReader.readTestData("Forum");
            addLog("Select "+forum+ " from Community dropdown");
            fourmsPage= myProfilePage.selectOptionFromCommunity(ForumsPage.class, forum);

            // Verify  Forums page
            addLog("Verify Forums page");
            fourmsPage= fourmsPage.verifyPage();

            // Verify and Click on Post Question button
            addLog("Verify and Click on Post Question button");
            questionPage= fourmsPage.verifyAndClickOnPostQuestionButton();

            // Verify Question page
            addLog("Verify Question page");
            questionPage =questionPage.verifyPage();

            // Verify only Enroll course show in Selected Course Dropdown
            addLog("Verify only Enroll course show in Selected Course Dropdown");
            questionPage=questionPage.verifySelectedCourseShowEnrollCourse(courseName);

        }
        catch (Error e) {
            captureScreenshot("test_003VerifyOnlyEnrolledCourseIsShowInSelectedCourseDropdown");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_003VerifyOnlyEnrolledCourseIsShowInSelectedCourseDropdown");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_003VerifyOnlyEnrolledCourseIsShowInSelectedCourseDropdown"})
    public void test_007VerifyErrorMessageForBlankField() throws Exception {

        try {

            // Select Category from Category dropdown
            String category=propertyReader.readTestData("Category");
            addLog("Select Category "+category+ " from Category Dropdown");
            questionPage=questionPage.selectCategory(category);

            // Enter question Title
            String questionTitle=propertyReader.readTestData("Question_Title_Forum_Creation");
            addLog("Enter Question Title" +questionTitle);
            questionPage=questionPage.enterQuestionTitle(questionTitle);

            // Click on Submit button
            addLog("Click on Submit button");
            questionPage=questionPage.clickOnSubmitButton(QuestionPage.class);

            // Verify that user is unable to post a question if any of the related field is empty
            String errorMessage = propertyReader.readTestData("ErrorMessageForBlankField");
            addLog("Verify that user is unable to post a question if any of the related field is empty");
            questionPage= questionPage.VerifyErrorForBlankField(errorMessage);

        }
        catch (Error e) {
            captureScreenshot("test_007VerifyErrorMessageForBlankField");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_007VerifyErrorMessageForBlankField");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_007VerifyErrorMessageForBlankField"})
    public void test_004VerifyQuestionTitle() throws Exception {

        try {

            // Enter wrong question Title
            String questionTitle=propertyReader.readTestData("Question_Wrong_Title");
            addLog("Enter wrong Question Title" +questionTitle);
            questionPage=questionPage.enterQuestionTitle(questionTitle);

            String question=propertyReader.readTestData("Question");
            questionPage=questionPage.enterQuestionDescription(question);

            // Click on Submit button
            addLog("Click on Submit button");
            questionPage=questionPage.clickOnSubmitButton(QuestionPage.class);

            // Verify Error Wrong Question Title
            addLog("Verify Error Wrong Question Title");
            questionPage=questionPage.verifyErrorForWrongTitle();

        }
        catch (Error e) {
            captureScreenshot("test_004VerifyQuestionTitle");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_004VerifyQuestionTitle");
            throw e;
        }
    }
}
