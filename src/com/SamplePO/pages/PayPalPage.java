package com.edureka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;

public class PayPalPage extends DriverHelper {

    private LocatorReader payPal;

    public PayPalPage(WebDriver driver) {
        super(driver);
        payPal = new LocatorReader("PayPal.xml");
    }

    /**
     *  Verify Paypal Page
     * @return
     * @throws InterruptedException 
     */
    public PayPalPage verifyPage() throws InterruptedException{
        String payPalHeader =payPal.getLocator("PageHeader");
        waitForLoading();
        Thread.sleep(10000);
        WaitForElementPresent(payPalHeader, 30);
        Assert.assertTrue(isElementPresent(payPalHeader));
        return PageFactory.initElements(driver, PayPalPage.class);
    }

    /**
     *  Login Paypal 
     * @return
     * @throws InterruptedException 
     */
    public PayPalPage enterEmailAndPassword() throws InterruptedException{
    	waitForLoading();
         
    	String paypalEmail = propertyReader.readApplicationFile("PaypalEmail");
        String payPalPassword = propertyReader.readApplicationFile("PaypasPassword");
        String frame = payPal.getLocator("Login.Frame");
        WebElement iFrame = getWebDriver().findElement(By.xpath(frame));
        getWebDriver().switchTo().frame(iFrame);

        String tbEmail =payPal.getLocator("Login.TBEmail");
        WaitForElementPresent(tbEmail, getTimeOut());
        sendKeys(tbEmail, paypalEmail);

        String tbPassword =payPal.getLocator("Login.TBPassword");
        WaitForElementPresent(tbPassword, getTimeOut());
        sendKeys(tbPassword, payPalPassword);

        String btnLogin =payPal.getLocator("Login.BTNLogin");
        WaitForElementPresent(btnLogin, getTimeOut());
        clickOn(btnLogin);
        return PageFactory.initElements(driver, PayPalPage.class);
    }
    
    /**
     *  Click on Continue button
     * @return
     * @throws InterruptedException 
     */
    public MyProfilePage clickOnContinueButton() throws InterruptedException{
        String btnContinue =payPal.getLocator("Login.BTNContinue");
        waitForClassLoading();
        getWebDriver().switchTo().defaultContent();
        WaitForElementPresent(btnContinue, 30);
        clickOn(btnContinue);
        Thread.sleep(3000);
        waitForClassLoading();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }


}