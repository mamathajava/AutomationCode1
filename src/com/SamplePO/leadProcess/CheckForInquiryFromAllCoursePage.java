package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class CheckForInquiryFromAllCoursePage extends DriverTestCase{
	private DashboardPage dashboardPage;
	private AllCoursesPage allCoursesPage;
	private SignInModalPage signInModalPage;
	private UserHomePage userHomePage;

	@Test
	public void test_019CheckForInquiryFromAllCoursePage() throws Exception {

		try {
			// Navigate to app url
			addLog("Navigate to the Edureka application url");
			dashboardPage = applicationSetupForLead();

			// Verify Edureka Dashboard Page
			addLog("Verify Edureka Dashboard Page");
			dashboardPage=  dashboardPage.verifyDashboard();

			signInModalPage = dashboardPage.clickSignInHeader();
			
			// Verify Login is selected as default
		    addLog("Verify Login is selected as default");
			signInModalPage = signInModalPage.verifyLoginIsDefault();

			// click on Sign up link
			addLog("click on Sign up link");
			signInModalPage=signInModalPage.clickSignUp();

			// Sign up user
			String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
			addLog("Sign up user");
			userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);
            String Email = signInModalPage.RuntimeUserEmail();
            String UserName = signInModalPage.RuntimeUserName();
            String PhNumber = signInModalPage.RuntimeUserPhNum();
        
			// Click on Course Tab
			String allCourse = propertyReader.readTestData("AllCourse");
			addLog("Click on Course Tab");
			Thread.sleep(10000);
			allCoursesPage = userHomePage.selectCourseOption(AllCoursesPage.class, allCourse);

            // Verify All Courses page
			addLog("Verify All Courses page");
			allCoursesPage= allCoursesPage.verifyAllCoursesPage();

			// Click on Query Box
			addLog("Click on Query Box");
			allCoursesPage=allCoursesPage.clickOnQueryBox();

			// Submit inquiry in drop query box
			addLog("Submit inquiry in drop query box");
			allCoursesPage=allCoursesPage.sendQuery(UserName,Email,PhNumber);
			// Verify Inquiry has been submitted
			addLog("Verify Inquiry has been submitted");
			allCoursesPage=allCoursesPage.verifySumbitInquiry();

			// Verify Data for Inquiry
	        String course__Id = propertyReader.readTestData("HomePage_Signup_Course_ID");
			String webSiteAction = propertyReader.readTestData("HomePage_Signup_WebSite_Action");
			String event = propertyReader.readTestData("Inquiry_Event");
			String campaign_Values= propertyReader.readTestData("Event_UTM_campaign");
	        String course_Group = propertyReader.readTestData("HomePage_Signup_Course_Group");
            String event_Type=propertyReader.readTestData("CountryIndia");
            String level_id = propertyReader.readTestData("HomePage_Signup_level_id");
			addLog("Verify Data for Inquiry");
			allCoursesPage=allCoursesPage.verifyDataForInquiry(course__Id,webSiteAction,event,campaign_Values,course_Group,event_Type,level_id,Email,UserName);

		}   catch (final Error e) {
			captureScreenshot("test_019CheckForInquiryFromAllCoursePage");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_019CheckForInquiryFromAllCoursePage");
			throw e;
		}
	}
}
