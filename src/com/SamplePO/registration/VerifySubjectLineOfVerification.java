package com.edureka.registration;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.Mailnator;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifySubjectLineOfVerification extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private Mailnator mailnator;
    private UserHomePage userHomePage;

    static String email;
    static String verificaitonCode ;
    static String password;
    static String errorMessage;
    String runtimeUser;
    String runtimeEmail;
    String runtimePassword;

    @Test
    public void test_025VerifySubjectLineOfVerification() throws Exception {

        try {

         // Navigate to app url]
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Sign up user
            String domainName= propertyReader.readApplicationFile("MailnatorDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class,domainName);
             runtimeUser = signInModalPage.RuntimeUserName();
             runtimeEmail = signInModalPage.RuntimeUserEmail();
             runtimePassword = signInModalPage.RuntimeUserPassword();
            
            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyRandUserLoggedIn(runtimeUser);

            // Click on Profile dropdown
            addLog("Click on Profile dropdown");
            userHomePage= userHomePage.clickOnProfileDropdown();

            // Logout Application
            addLog("Logout Application");
            dashboardPage=userHomePage.logout();
            
            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Verify Enter email
            addLog("Verify Enter email");
            signInModalPage = signInModalPage.enterMailnatorAccount(runtimeEmail);

            // Click on forgot password
            addLog("Click on forgot password");
            signInModalPage = signInModalPage.clickOnForgotPassword();

            // Verify Message 'Verification code sent to email'
            email = propertyReader.readRunTimeData("Email_Id");
            addLog("Verify Message Verification code sent to email " +email);
            signInModalPage = signInModalPage.verifySuccessMsgForCode(email);

            // Navigate to mailnator
            addLog("Navigate to mailnator");
            mailnator = signInModalPage.navigateToMailnator();

            // Enter email
            String email = propertyReader.readRunTimeData("Email_Id");
            addLog("Enter email " +email);
            mailnator = mailnator.enterEmail(runtimeEmail);

            // open Email
            addLog("open Email");
            mailnator = mailnator.openMail();

            // Verify Mail subject is correct
            String subject = propertyReader.readTestData("VerificationSubject");
            addLog("Verify Mail subject is correct");
            mailnator = mailnator.verifyMailSubject(subject);

        }
        catch (final Error e) {
            captureScreenshot("test_025VerifySubjectLineOfVerification");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_025VerifySubjectLineOfVerification");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_025VerifySubjectLineOfVerification"})
    public void test_027VerifyErrorMsgForIncorrectVeirficationCode() throws Exception {
        try {

            // Get Verification Code
            addLog("Get Verification Code");
            verificaitonCode = mailnator.getVerificationCode();

            // Navigate to Edureka 
            addLog("Navigate to Edureka");
            signInModalPage=mailnator.navigateToEdureka();

            // Verify Message 'Verification code sent to email'
            //email = propertyReader.readRunTimeData("Email_Id");
            addLog("Verify Message 'Verification code sent to email " +email);
            signInModalPage = signInModalPage.verifySuccessMsgForCode(runtimeEmail);

            // Enter Incorrect Verification code, enter new password and click on Password reset button
            String wrongVerificationCode = verificaitonCode + "00";
            password = propertyReader.readTestData("Password");
            addLog("Enter Incorrect Verification code, enter new password and click on Password reset button");
            signInModalPage = signInModalPage.enterVerificationCodeAndNewPassword(wrongVerificationCode, runtimePassword);

            // Verify Error message for incorrect verification code 
            errorMessage = propertyReader.readTestData("WrongVerificationCodeMsg");
            addLog("Verify Error message for incorrect verification code ");
            signInModalPage = signInModalPage.verify_IncorrectVerificationCode_errorMessage();
        }
        catch (final Error e) {
            captureScreenshot("test_027VerifyErrorMsgForIncorrectVeirficationCode");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_027VerifyErrorMsgForIncorrectVeirficationCode");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_027VerifyErrorMsgForIncorrectVeirficationCode"})
    public void test_029VerifyNewPasswordShouldNotBeLessThan8Characters() throws Exception {
        try {

            // Enter Verification code, enter new password and click on Password reset button
            password = "12345";
            addLog("Enter Verification code, enter new password and click on Password reset button");
            signInModalPage = signInModalPage.enterVerificationCodeAndNewPassword(verificaitonCode,password);

            // Verify Error message when new password is less than 8 characters
            addLog("Verify Error message when new password is less than 8 characters");
            signInModalPage= signInModalPage.verifyMessage("Please enter 8 digit password");
 
        }
        catch (final Error e) {
            captureScreenshot("test_029VerifyNewPasswordShouldNotBeLessThan8Characters");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_029VerifyNewPasswordShouldNotBeLessThan8Characters");
            throw e;
        }
    }

    @Test(dependsOnMethods={"test_029VerifyNewPasswordShouldNotBeLessThan8Characters"})
    public void test_026VerifyCorrectVerificationCode() throws Exception {
        try {

            // Enter Verification code, enter new password and click on Password reset button
            addLog("Enter Verification code, enter new password and click on Password reset button");
            signInModalPage = signInModalPage.enterVerificationCodeAndNewPassword(verificaitonCode, runtimePassword);

            // Verify message password has reset
            String successMessage = propertyReader.readTestData("PasswordResetSuccessMsg");
            addLog("Verify message password has reset");
            signInModalPage= signInModalPage.verifyMessage(successMessage);
            
            // Navigate to mailnator
            addLog("Navigate to mailnator");
            mailnator = signInModalPage.navigateToMailnator();

            // Enter email
            String email = propertyReader.readRunTimeData("Email_Id");
            addLog("Enter email" +email);
            mailnator = mailnator.enterEmail(runtimeEmail);

            // open Email
            addLog("open Email");
            mailnator = mailnator.openMail();

            // Verify Mail subject is correct
            String subject = propertyReader.readTestData("VerificationSubject");
            addLog("Verify Mail subject is correct");
            mailnator = mailnator.verifyMailSubject(subject);

           // Navigate to Edureka 
            addLog("Navigate to Edureka");
            signInModalPage=mailnator.navigateToForgetPasswordLink();
            
            // Verify error message for expired link
            String errorMessageForExpiredLink = propertyReader.readTestData("ErrorMessageForExpiredLink");
            addLog("Verify error message "+errorMessageForExpiredLink);
            signInModalPage= signInModalPage.verifyMessage(errorMessageForExpiredLink);

        }
        catch (final Error e) {
            captureScreenshot("test_026VerifyCorrectVerificationCode");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_026VerifyCorrectVerificationCode");
            throw e;
        }
    }
}
