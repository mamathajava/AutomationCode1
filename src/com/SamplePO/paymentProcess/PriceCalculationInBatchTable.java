package com.edureka.paymentProcess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class PriceCalculationInBatchTable extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;

    @Test
    public void test_002PriceCalculationInBatchTable() throws Exception {

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

            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);
            String name = signInModalPage.RuntimeUserName();
            String email = signInModalPage.RuntimeUserEmail();
            String phone = signInModalPage.RuntimeUserPhNum();

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyRandUserLoggedIn(name);

            // Select Course
            String courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course");
            selectedCoursePage=dashboardPage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open.");
            selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);
            String slug = dashboardPage.getSlug();

            // Select INR currency
            String currency= propertyReader.readTestData("INRCurrency");
            addLog("Select INR currency");
            selectedCoursePage=selectedCoursePage.selectCurrency(currency);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Verify Timezone
            addLog("Verify Timezone");
            orderSummaryPage=orderSummaryPage.veriyTimeZone();

            // Verify Payment modes
            addLog("Verify Payment modes");
            orderSummaryPage=orderSummaryPage.verifyPaymentModes();

/*    // Verify total Amount for INR is equal to Net price + Service Tax 
    addLog("Verify total Amount for INR is equal to Net price + Service Tax");
    orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);
*/
            // Verify INR Currency Inclusive Of Service Tax
            addLog("Verify INR Currency Inclusive Of Service Tax");
            orderSummaryPage=orderSummaryPage.verifyINRCurrency();

            // Verify Data in User table
            addLog("Verify Data in User table");
            orderSummaryPage=orderSummaryPage.dataVerificationInUserTable("1",email,name,phone);

            // Verify Data in Pre-Order table
    /*String course_Id= propertyReader.readTestData("Course_ID_BigData");
    String course_Title= propertyReader.readTestData("BigData_Hadoop_Title");
    */      
            //////////////////////////////
            String course_id = selectedCoursePage.getCourseID(slug);
            String course_price = selectedCoursePage.getCourse_originalPrice();
            String course_servicetax = selectedCoursePage.getCourse_ServiceTax();
            String getUserID = signInModalPage.getUserID(email);
            addLog("Verify Data in Pre-Order table");
            orderSummaryPage=orderSummaryPage.dataVerificationInUser_PreOrderTableForINR(course_id,course_price,course_servicetax,getUserID);

            // Verify Data in User Course Table
            String course_Group = propertyReader.readTestData("Course_Group_BigData");
            String isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            addLog("Verify Data in User Course Table");
            orderSummaryPage=orderSummaryPage.dataVerificationInUser_CoursedTable(course_id, isPaidValue, course_Group,getUserID);

            // Change Country
            String countryName= propertyReader.readTestData("CountryName");
            String currencySign = propertyReader.readTestData("AustralianCurrencySign");
            addLog("Change Country from country dropdown");
            orderSummaryPage=orderSummaryPage.changeCountry(countryName,currencySign);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Verify Total Price for Australian dollor
            // currency= propertyReader.readTestData("USDCurrency");
            addLog("Verify Total Price for Australian dollor");
            String amount = orderSummaryPage.getCourseTotalCost();
            System.out.println("Amount is : " + amount);
            orderSummaryPage.verifyCoursePrice(course_id,"5",amount);
  //orderSummaryPage=orderSummaryPage.verifyTotalAmount(amount);

        }
        catch (final Error e) {
            captureScreenshot("test_002PriceCalculationInBatchTable");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_002PriceCalculationInBatchTable");
            throw e;
        }
    }

}
