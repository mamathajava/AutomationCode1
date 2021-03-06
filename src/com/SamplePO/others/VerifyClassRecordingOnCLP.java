package com.edureka.others;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyClassRecordingOnCLP extends DriverTestCase{
	private DashboardPage dashboardPage;
	private AllCoursesPage allCoursesPage;
	private SignInModalPage signInModalPage;
	private UserHomePage userHomePage;
	private SelectedCoursePage selectedCoursePage;

	private static String allCourse;
	@Test
	public void test_005VerifyClassRecordingOnCLP() throws Exception {

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

			//  Select AllCourse Tab from Courses dropdown
			allCourse = propertyReader.readTestData("AllCourse");
			addLog("Select "+allCourse+" Tab from Courses dropdown");
			allCoursesPage= userHomePage.selectCourseOption(AllCoursesPage.class, allCourse);

			// Verify All Courses page
			addLog("Verify All Courses page");
			allCoursesPage= allCoursesPage.verifyAllCoursesPage();

			// Select Course
			String courseName = propertyReader.readTestData("DataScienceCourse");
			addLog("Select Course "+courseName);
			selectedCoursePage=allCoursesPage.selectCourseFromGridView(courseName);

			// Verify Selected course should be open.
			addLog("Verify "+courseName+" course should be open");
			selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

			// Click on Play Button of Video
			addLog("Click on Play Button of Video");
			selectedCoursePage=selectedCoursePage.playClassRecording(SelectedCoursePage.class);

			// Select All CoursesTab from Courses dropdown
			addLog("Select "+allCourse+" Tab from Courses dropdown");
			allCoursesPage= selectedCoursePage.selectCourseOption(AllCoursesPage.class, allCourse);

			// Verify All Courses page
			addLog("Verify All Courses page");
			allCoursesPage= allCoursesPage.verifyAllCoursesPage();

			// Select Course
			courseName = propertyReader.readTestData("BigData&Hadoop");
			addLog("Select Course "+courseName);
			selectedCoursePage=allCoursesPage.selectCourseFromGridView(courseName);

			// Verify Selected course should be open.
			addLog("Verify "+courseName+" course should be open");
			selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

			// Click on Play Button of Video
			addLog("Click on Play Button of Video");
			selectedCoursePage=selectedCoursePage.playClassRecording(SelectedCoursePage.class);

			// Select All CoursesTab from Courses dropdown
			addLog("Select "+allCourse+" Tab from Courses dropdown");
			allCoursesPage= selectedCoursePage.selectCourseOption(AllCoursesPage.class, allCourse);

			// Verify All Courses page
			addLog("Verify All Courses page");
			allCoursesPage= allCoursesPage.verifyAllCoursesPage();

			// Select Course
			courseName = propertyReader.readTestData("CoursesJava");
			addLog("Select Course "+courseName);
			selectedCoursePage=allCoursesPage.selectCourseFromGridView(courseName);

			// Verify Selected course should be open.
			addLog("Verify "+courseName+" course should be open");
			selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

			// Click on Play Button of Video
			addLog("Click on Play Button of Video");
			selectedCoursePage=selectedCoursePage.playClassRecording(SelectedCoursePage.class);


			// Verify Data in User Table
			addLog("Verify Data in User Table");
			//selectedCoursePage=selectedCoursePage.dataVerificationInUserTable("1");



		}   catch (final Error e) {
			captureScreenshot("test_005VerifyClassRecordingOnCLP");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_005VerifyClassRecordingOnCLP");
			throw e;
		}
	}
}
