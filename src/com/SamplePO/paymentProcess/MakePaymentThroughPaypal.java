package com.edureka.paymentProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.Mailnator;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.PayPalPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class MakePaymentThroughPaypal extends DriverTestCase{
	private DashboardPage dashboardPage;
	private UserHomePage userHomePage;
	private SelectedCoursePage selectedCoursePage;
	private OrderSummaryPage orderSummaryPage;
	private SignInModalPage signInModalPage;
	private PayPalPage payPalPage;
	private MyProfilePage myProfilePage;
	private Mailnator mailnator;
	
	static String name = "";
    static String course_price = "";
    static String email = "";
	@Test
	public void test_004MakePaymentThroughPaypal() throws Exception {

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
			name = signInModalPage.RuntimeUserName();
			 email = signInModalPage.RuntimeUserEmail();
			String phone = signInModalPage.RuntimeUserPhNum();

			// Verify User Home Page
			addLog("Verify User Home Page");
			userHomePage=userHomePage.verifyRandUserLoggedIn(name);

			// Select Course
			String courseName = propertyReader.readTestData("BigData&Hadoop");
			addLog("Select Course");
			selectedCoursePage=dashboardPage.selectCourse(courseName);

			// Verify Selected course should be open.
			addLog("Verify Selected course should be open");
			selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

			
			// Click on Enroll Button
			addLog("Click on Enroll Button");
			orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

			// Verify Order Summary Page
			addLog("Verify Order Summary Page");
			orderSummaryPage=orderSummaryPage.verifyPage();
			 

			String currency= propertyReader.readTestData("USDCurrency");
			orderSummaryPage=orderSummaryPage.changeCurrency(currency);

			// Verify total Amount for USD is equal to Net price
			addLog("Verify total Amount for USD is equal to Net price");
			//orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

			// Verify USD Currency Exclusive Of Service Tax
			addLog("Verify USD Currency Exclusive Of Service Tax");
			orderSummaryPage=orderSummaryPage.verifyUSDCurrency();
			course_price = selectedCoursePage.getCourse_originalPrice();			
            System.out.println("COURSE PRICE : "+course_price);

			// Click on Paypal link
			addLog("Click on Paypal link");
			payPalPage= orderSummaryPage.clickOnPaypalLink_New();

			// Verify Paypal Page
			addLog("Verify Paypal Page");
			payPalPage=payPalPage.verifyPage();

			// Enter Email, Password and Login paypal account
			addLog("Enter Email, Password and Login paypal account");
			payPalPage=payPalPage.enterEmailAndPassword();


			
			// Make payment by clicking on Continue button
			addLog("Make payment by clicking on Continue button");
			myProfilePage=payPalPage.clickOnContinueButton();

			
			// Switch to Parent Window and verify my Profile page
			addLog("Switch to Parent Window and verify my Profile page");
			myProfilePage= myProfilePage.verifyPage();

			// Verify Payment success message
			addLog("Verify payment success message 'Thank you for making payment !'");
			myProfilePage= myProfilePage.verifySuccessPaymentMessage();

//Course ID URL 			
			// Verify Data in User table
			addLog("Verify Data in User table");
			myProfilePage=myProfilePage.dataVerificationInUserTable("3",email,name,phone);

			// Verify Data in Pre-Order table
			String course_Id= propertyReader.readTestData("Course_ID_BigData");
			//String course_Title= propertyReader.readTestData("BigData_Hadoop_Title");
			String getUserID = selectedCoursePage.getUserID(email);
			addLog("Verify Data in Pre-Order table");
			myProfilePage=myProfilePage.dataVerificationInUser_PreOrderTableForUSD(course_Id,course_price,getUserID);

			// Verify Data in User Course Table
			String course_Group = propertyReader.readTestData("Course_Group_BigData");
			String isPaidValue= propertyReader.readTestData("Bigdata_Is_Paid_Value");
			addLog("Verify Data in User Course Table");
			myProfilePage=myProfilePage.dataVerificationInUser_CoursedTable(course_Id, isPaidValue, course_Group,getUserID);

			// Database verification in Post order table
			String paymentGateway = propertyReader.readTestData("PaymentGatewayPaypal");
			addLog("Database verification in Post order table");
			myProfilePage=myProfilePage.dataVerificationInUser_PostOrderTable(course_Id,paymentGateway,currency,getUserID);

			//Verify the Url  on my profile page after payment
			myProfilePage=myProfilePage.verifyCourseIdInUrl(course_Id);

			//Verify Goto Course and LMS Page
			addLog("Verify Go to course and LMS Page");
			myProfilePage= myProfilePage.verifyGotoCourseAndLMSPage();
        }
		catch (final Error e) {
			captureScreenshot("test_004MakePaymentThroughPaypal");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_004MakePaymentThroughPaypal");
			throw e;
		}
	}
	@Test(dependsOnMethods={"test_004MakePaymentThroughPaypal"})
	public void test_022ValidateMyOrdersDetailsOnMyProfilePage() throws Exception {

		try {  
            
			// Click on Profile Dropdown
			myProfilePage= myProfilePage.clickOnProfileDropdown(name);

			// Select My Profile
			addLog("Select My Profile");
			myProfilePage=myProfilePage.clickOnMyProfile();

			//Click on My Orders
			myProfilePage=myProfilePage.clickOnMyOrders();

			//Verify course details on My orders
			String course_Title= propertyReader.readTestData("Bigdata_course_name");
			myProfilePage=myProfilePage.validateCourseDetailsOnMyOrdersPage(course_Title);

			//Verify View Invoice
			myProfilePage=myProfilePage.verifyViewInvoice(course_Title,email);

		
			//Verify Print Invoice
	        String referralEmail= randomString(6)+"@mailinator.com";
			propertyReader.updatePropertyTestData("RefferralEmail", referralEmail);
	        myProfilePage=myProfilePage.verifyEmailAndPrintInvoice(referralEmail);

			// Navigatw to the mailnator
			addLog("Navigatw to the mailnator");
			mailnator=myProfilePage.navigateToMailnator();

			// Enter email
			addLog("Enter email "+referralEmail);
			mailnator = mailnator.enterEmail(referralEmail);

			// open Email
			addLog("open Email");
			mailnator = mailnator.openInvoiceMail();

			//Verify Invoice email elements
			mailnator=mailnator.verifyInvoiceEmailData();


		}
		catch (final Error e) {
			captureScreenshot("test_022ValidateMyOrdersDetailsOnMyProfilePage");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_022ValidateMyOrdersDetailsOnMyProfilePage");
			throw e;
		}
	}

}