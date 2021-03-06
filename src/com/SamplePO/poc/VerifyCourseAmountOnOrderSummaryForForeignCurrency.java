package com.edureka.poc;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyCourseAmountOnOrderSummaryForForeignCurrency extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;

    @Test
    public void test_07VerifyCourseAmountOnOrderSummaryForForeignCurrency() throws Exception {

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

            // Login Application
            addLog(" Login Application");
            userHomePage= signInModalPage.loginApp();

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
            String courseName = propertyReader.readTestData("DataScienceCourse");
            addLog("Select Course");
            selectedCoursePage=dashboardPage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

            // Select Foreign currency
            String currency= propertyReader.readTestData("USDCurrency");
            addLog("Select Foreign currency");
            selectedCoursePage=selectedCoursePage.selectCurrency(currency);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();
            
            // Verify total Amount for USD is equal to Net price
            addLog("Verify total Amount for USD is equal to Net price");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);
            
            // Verify USD Currency Exclusive Of Service Tax
            addLog("Verify USD Currency Exclusive Of Service Tax");
            orderSummaryPage=orderSummaryPage.verifyUSDCurrency();
            
        }
        catch (final Error e) {
            captureScreenshot("test_07VerifyCourseAmountOnOrderSummaryForForeignCurrency");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_07VerifyCourseAmountOnOrderSummaryForForeignCurrency");
            throw e;
        }
    }

}

