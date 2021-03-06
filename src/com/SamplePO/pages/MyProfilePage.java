package com.edureka.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;

public class MyProfilePage extends DriverHelper{

    private final LocatorReader myProfile;
    private final DatabaseVerifications databaseVerification;
    public MyProfilePage(WebDriver driver) {
        super(driver);
        myProfile = new LocatorReader("MyProfile.xml");
        databaseVerification= new DatabaseVerifications(getWebDriver());
    }

    /**
     *  Verify My Profile Page
     * @return
     */
    public MyProfilePage verifyPage(){
    	_waitForJStoLoad();
        String pageHeader = myProfile.getLocator("PageHeader");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(pageHeader));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify success payment message
     * @return
     */
    public MyProfilePage verifySuccessPaymentMessage(){
        String msgPaymentSuccess = myProfile.getLocator("MessagePaymentSuccess");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(msgPaymentSuccess));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    /**
     *  verifyGotoCourseAndLMSPage
     * @return
     */
    public MyProfilePage verifyGotoCourseAndLMSPage(){

        // Select My Courses from Courses Menu drop down
       /* String tabCourses =myProfile.getLocator("TabCourses");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(tabCourses));
        clickOn(tabCourses);
       */ 
    	_waitForJStoLoad();
        String myCourseTab = propertyReader.readTestData("MyCourses");
        selectCourseOption(MyCourses.class, myCourseTab);

        //Verify Goto Course and click on
        String tabGotoCourse = myProfile.getLocator("TabGoToCourse");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(tabGotoCourse));
        clickOn(tabGotoCourse);

        //Verify LMS tab
        String tabLMS=myProfile.getLocator("TabLMS");
        Assert.assertTrue(isElementPresent(tabLMS));

        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    /**
     *  verifyCourseIdInUrl
     * @return
     */
    public MyProfilePage verifyCourseIdInUrl(String courseId){
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains(courseId));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Select Community
     * @param className
     * @param option
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T selectOptionFromCommunity(Class className,String option) {
        String ddCommunity = myProfile.getLocator("DDCommunity");
        WaitForElementPresent(ddCommunity, 30);
        clickOn(ddCommunity);

        String communityOption = "//ul[@class='dropdown-menu']//li/a[contains(text(),'"+option+"')]";
        WaitForElementPresent(communityOption, getTimeOut());
        clickOn(communityOption);
        return (T) PageFactory.initElements(driver, className);
    }


    /**
     *  Verify Data in user Table
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUserTable(String custID,String email,String username,String phone) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInUserTable(MyProfilePage.class,custID,email,username,phone);
        return myProfile;
    }
    /**
     *  Verify Data in user Table
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUserTable(String emailId,String userName,String phone) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInUserTable(MyProfilePage.class,emailId,userName,phone);
        return myProfile;
    }

    /**
     *  Database verification in Pre_Order table When currency is INR
     * @param course_Id
     * @param courseTitle
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUser_PreOrderTableForINR(String course_id,String course_price,String course_servicetax,String userID) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInPreOrderTableForINR(MyProfilePage.class, course_id,course_price,course_servicetax,userID);
        return myProfile;
    }

    /**
     *  Database verification in Pre_Order table When currency is USD
     * @param course_Id
     * @param courseTitle
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUser_PreOrderTableForUSD(String course_id, String course_price,String user_Id1) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInPreOrderTableForUSD(MyProfilePage.class, course_id, course_price,user_Id1);
        return myProfile;
    }

    /**
     *  Verify Data in User Courses Table
     * @param courseId
     * @param isPaidValue
     * @param courseGroup
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUser_CoursedTable(String courseId, String isPaidValue, String courseGroup,String getUserID) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInUser_Course_Table(MyProfilePage.class, courseId, isPaidValue, courseGroup,getUserID);
        return myProfile;
    }

    /**
     * Select Course option from course Dropdown
     * @param className
     * @param courseOption : My Course
     *                       All Course
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T selectCourseOption(@SuppressWarnings("rawtypes") Class className, String courseOption) {
        String tabCourses =myProfile.getLocator("TabCourses");
        WaitForElementPresent(tabCourses, getTimeOut());
        Assert.assertTrue(isElementPresent(tabCourses));
        clickOn(tabCourses);

        String option ="//ul[@class='dropdown-menu']//li/a[contains(text(),'"+courseOption+"')]";
        WaitForElementPresent(option, getTimeOut());
        Assert.assertTrue(isElementPresent(option));
        clickOn(option);
        _waitForJStoLoad();
        return (T) PageFactory.initElements(driver, className);
    }

    /**
     *  Click on Refer friend link
     * @return
     */
    public MyProfilePage clickOnReferFriendButton(){
        String btnReferFriend = myProfile.getLocator("RefferralProcess.BTNReferFriend");
        _waitForJStoLoad();
        mouseOver(btnReferFriend);
        clickOn(btnReferFriend);
        _waitForJStoLoad();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Copy Referral Link
     * @return
     */
    public String copyReferralLink(){
        String referralLink = myProfile.getLocator("RefferralProcess.ReferralLink");
        _waitForJStoLoad();
        String referralLinkText = getText(referralLink);
        propertyReader.updatePropertyTestData("ReferralLink",referralLinkText);
        return referralLinkText;
    }

    /**
     *  Verify Refferal Form
     * @return
     */
    public MyProfilePage verifyRefferralForm(){
        String refferralForm = myProfile.getLocator("RefferralProcess.BTNReferFriend");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(refferralForm));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Refer Friend
     * @param email
     * @return
     */
    public MyProfilePage referFriend(String email) throws InterruptedException{
        String tbEmail = myProfile.getLocator("RefferralProcess.TBEmail");
        WaitForElementPresent(tbEmail, getTimeOut());
        sendKeys(tbEmail, email);

        Thread.sleep(3000);
        String btnSendInvitation = myProfile.getLocator("RefferralProcess.BTNSendInvitation");
        _waitForJStoLoad();
        clickOn(btnSendInvitation);
        clickOn(btnSendInvitation);      
        String msgSuccess = myProfile.getLocator("RefferralProcess.SuccessMessage");
        if(!isElementPresent(msgSuccess)){
            clickOn(btnSendInvitation);      
        }
        String alertMessage = myProfile.getLocator("RefferralProcess.AlertMessage");
        if (isElementPresent(alertMessage)){
            sendKeys(tbEmail, email);
            WaitForElementPresent(btnSendInvitation, getTimeOut());
            clickOn(btnSendInvitation);
            clickOn(btnSendInvitation);    
        }
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify Success Referral Mail Send message
     * @return
     */
    public MyProfilePage VerifySuccessRefferralMailSentMsg(){
        String msgSuccess = myProfile.getLocator("RefferralProcess.SuccessMessage");
        //_waitForJStoLoad();
        WaitForElementPresent(msgSuccess,getTimeOut());
        WaitForElementVisible(msgSuccess, getTimeOut());
        Assert.assertTrue(isElementPresent(msgSuccess));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  close Refer Pop up 
     * @return
     */
    public MyProfilePage closeReferPopUp(){
        String btnClose = myProfile.getLocator("RefferralProcess.BTNClose");
        _waitForJStoLoad();
        clickOn(btnClose);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Click on Profile Dropdown
     * @param userName
     * @return
     */
    public MyProfilePage clickOnProfileDropdown(String userName){
        String dropdown ="//span[contains(text(),'"+userName+"')]";
        WaitForElementPresent(dropdown, getTimeOut());
        mouseOver(dropdown);
        clickOn(dropdown);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Logout application
     * @return
     */
    public DashboardPage logout(){
        String logout=myProfile.getLocator("LTLogout");;
        WaitForElementPresent(logout, getTimeOut());
        mouseOver(logout);
        clickOn(logout);
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     *  Click on Referral Tab
     * @return
     */
    public MyProfilePage clickOnReferralTab(){
        String tabReferral = myProfile.getLocator("RefferralProcess.TabReferral");
        _waitForJStoLoad();
        clickOn(tabReferral);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify Earning Credit Points
     * @return
     */
    public MyProfilePage verifyEarningCreditPoints(){
        String earningCredit = myProfile.getLocator("RefferralProcess.EarnCredit");
        _waitForJStoLoad();
        String earningCreditPointsText= getText(earningCredit);
        String earningCreditPointsText_1 = earningCreditPointsText.trim();
        propertyReader.updatePropertyTestData("EarnedCredits", earningCreditPointsText_1);
        int earningCreditPoints = Integer.parseInt(earningCreditPointsText_1);
        Assert.assertTrue(earningCreditPoints>0);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Click on Redemption button
     * @return
     */
    public AllCoursesPage clickOnRedeemButton(){
        String btnRedeem = myProfile.getLocator("RefferralProcess.BTNRedeem");
        _waitForJStoLoad();
        clickOn(btnRedeem);
        return PageFactory.initElements(driver, AllCoursesPage.class);
    }

    /**
     *  Verify Redeemption Earning Credit Points
     * @return
     */
    public MyProfilePage verifyRedeemptionCreditPoints(){
        String redeemptionCredit = myProfile.getLocator("RefferralProcess.RedeemptionCredit");
        WaitForElementPresent(redeemptionCredit, getTimeOut());
        String redeemptionCreditPointsText= getText(redeemptionCredit);
        String redeemptionCreditPointsText_1 = redeemptionCreditPointsText.trim();
        int redeemptionCreditPoints= Integer.parseInt(redeemptionCreditPointsText_1);
        Assert.assertTrue(redeemptionCreditPoints>0);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     * Database verification for Referral Process
     * @param email
     * @param fName
     * @param referralLink
     * @param levelId
     * @param welcomemail
     * @param ambassador_Campaign_Id
     * @param ambassador_Level_Id
     * @param source
     * @param page_Url
     * @param campaign_id
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationFor_ReferralProcess(String email,String referralLink,String levelId, String welcomemail, String ambassador_Campaign_Id, String ambassador_Level_Id,String source, String page_Url, String campaign_id) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationForReferralProcess(MyProfilePage.class,email, referralLink, levelId,welcomemail, ambassador_Campaign_Id,ambassador_Level_Id,source,page_Url,campaign_id);
        return myProfile;
    }

    /**
     *  Verify Referral Link in Ambassador Table
     * @param email
     * @param referralLink
     * @return
     * @throws Exception
     */
    public MyProfilePage veirfyReferralLinkInAmassadorTable(String email, String referralLink) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.verifyReferralLinkIn_AmbassdorTable(MyProfilePage.class,email,referralLink);
        return myProfile;
    }


    /**
     *  Click on Refer button
     * @return
     * @throws InterruptedException 
     */
    public MyProfilePage clickOnReferButton() throws InterruptedException{
        Thread.sleep(3000);
        String btnRefer = myProfile.getLocator("RefferralProcess.BTNReferFriend");
        WaitForElementPresent(btnRefer, getTimeOut());
        mouseOver(btnRefer);
        clickOn(btnRefer);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *   Database verification in Post_Order table
     * @param course_Id
     * @param courseTitle
     * @param paymentGatway
     * @param currency
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUser_PostOrderTable(String course_Id,String paymentGatway, String currency,String UserID) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInPostOrderTable(MyProfilePage.class, course_Id, paymentGatway, currency,UserID);
        return myProfile;
    }

    /**
     *  Validate the image(refer friend,earn credits,redeen credits) present inside referral tab on myprofile page
     * @return
     */
    public MyProfilePage verifyImageInsideReferralTab(){
        String imgReferral = myProfile.getLocator("RefferralProcess.ImgReferral");
        WaitForElementPresent(imgReferral, getTimeOut());
        Assert.assertTrue(isElementPresent(imgReferral));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     * Get Credit Points
     * @return
     */
    public String getCreditPoints(){
        String earningCredit = myProfile.getLocator("RefferralProcess.EarnCredit");
        _waitForJStoLoad();
        String earningCreditPointsText= getText(earningCredit);
        String earningCreditPointsText_1 = earningCreditPointsText.trim();
        return earningCreditPointsText_1;
    }

    /**
     *  Verify Credit Points in Ambassadors table
     * @param email
     * @param points
     * @return
     * @throws Exception
     */
/*    public MyProfilePage VerificationCreditPointsInAmbassadorsTableReferralProcess(String email, String points) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification._VerificationCreditPointsInAmbassadorsTableReferralProcess(MyProfilePage.class,email, points);
        return myProfile;
    }
*/
    /**
     *  Get Redeemption Earning Credit Points
     * @return
     */
    public String getRedeemptionCreditPoints(){
        String redeemptionCredit = myProfile.getLocator("RefferralProcess.RedeemptionCredit");
        _waitForJStoLoad();
        String redeemptionCreditPointsText= getText(redeemptionCredit);
        String redeemptionCreditPointsText_1 = redeemptionCreditPointsText.trim();
        return redeemptionCreditPointsText_1;
    }

    /**
     *  Verify Referred Friends points
     * @return
     */
    public MyProfilePage verifyReferredFriend(){
        String referFriendPoints = myProfile.getLocator("RefferralProcess.ReferFriendCount");
        _waitForJStoLoad();
        String referFriendPointsText= getText(referFriendPoints);
        String referFriendPointsText_1 = referFriendPointsText.trim();
        propertyReader.updatePropertyTestData("ReferredFriendCount",referFriendPointsText_1);
        int referFriendPointsValue= Integer.parseInt(referFriendPointsText_1);
        Assert.assertTrue(referFriendPointsValue>0);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify value of Enrolments number Inside referral Tab
     * @return
     */
    public MyProfilePage verifyEnorlNumbers(){
        String enrolmentNumbers = myProfile.getLocator("RefferralProcess.EnrolmentsNumbers");
        _waitForJStoLoad();
        String enrolmentNumbersText= getText(enrolmentNumbers);
        String enrolmentNumbersText_1 = enrolmentNumbersText.trim();
        propertyReader.updatePropertyTestData("Enroll_Count",enrolmentNumbersText_1);
        int enrolmentNumbersValue= Integer.parseInt(enrolmentNumbersText_1);
        Assert.assertTrue(enrolmentNumbersValue>0);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     * Click on View Details button
     * @return
     * @throws InterruptedException 
     */
    public MyProfilePage clickOnViewDetails() throws InterruptedException{
        String btnViewDetails = myProfile.getLocator("RefferralProcess.BtnViewDetails");
        WaitForElementPresent(btnViewDetails, getTimeOut());
        clickOn(btnViewDetails);
        Thread.sleep(5000);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify Records in Referral Table
     * @return
     * @throws InterruptedException
     */
    public MyProfilePage verifyRecordsInReferralTable() throws InterruptedException{
        String referralTableName= myProfile.getLocator("RefferralProcess.ReferralTableName");
        WaitForElementPresent(referralTableName, getTimeOut());
        Assert.assertTrue(isTextPresent(referralTableName, "My Referrals"));

        String referralTable = myProfile.getLocator("RefferralProcess.ReferralTableRecords");
        WaitForElementPresent(referralTable, getTimeOut());
        List<WebElement> referralRecords= driver.findElements(By.xpath(referralTable));
        int totalRecords= referralRecords.size();
        Assert.assertTrue(totalRecords>0);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify Referral Link
     * @return
     */
    public String verifyReferralLink(){
        String linkReferral = myProfile.getLocator("RefferralProcess.LinkReferral");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(linkReferral));

        String link = getText(linkReferral);
        return link;
    }

    /**
     *  Verify Enrolled count in Ambassador Transaction table
     * @param email
     * @param enorlled_Count
     * @return
     * @throws Exception
     */
    public MyProfilePage verifyDataIn_AmbassadorTransactionTable(String email, String enorlled_Count) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.verifyDataIn_AmbassadorTransactionTable(MyProfilePage.class,email, enorlled_Count);
        return myProfile;
    }

    /**
     *  Verify Data in Referral Redemption table-+
     * @param email
     * @param redeemPoint
     * @return
     * @throws Exception
     */
    public MyProfilePage verifyDataIn_Referral_redemptionsTable(String email, String redeemPoint) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.verifyDataIn_Referral_redemptionsTable(MyProfilePage.class,email, redeemPoint);
        return myProfile;
    }

    /**
     *  Verify Refer Source in Ambassador Activity Table
     * @param email
     * @param source
     * @return
     * @throws Exception
     */
    public MyProfilePage verifyReferSourceIn_Ambassador_activitiesTable(String email, String source) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.verifyReferSourceIn_Ambassador_activitiesTable(MyProfilePage.class,email, source);
        return myProfile;
    }

    /**
     *  Verify Referred Date in Ambassador Activities Table
     * @param email
     * @return
     * @throws Exception
     */
    public MyProfilePage verifyReferDateIn_Ambassador_activitiesTable(String email) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.verifyReferDateIn_Ambassador_activitiesTable(MyProfilePage.class,email);
        return myProfile;
    }

    /**
     *  Click on Remind button
     * @return
     * @throws InterruptedException 
     */
    public MyProfilePage clickOnRemindButton() throws InterruptedException{
        String btnRemind=myProfile.getLocator("RefferralProcess.BtnRemind");
        _waitForJStoLoad();
        clickOn(btnRemind);
        Thread.sleep(5000);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Navigate to Mailnaor
     * @return
     */
    public Mailnator navigateToMailnator() {
        getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        String mailnatorurl = propertyReader.readApplicationFile("Mailnator-URL");
        getWebDriver().navigate().to(mailnatorurl);
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     *  Verify Refresh Browser
     * @return
     */
    public MyProfilePage refreshBrowser(){
        getWebDriver().navigate().refresh();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify The course name which one is bought by user B. If not yet bought then "-" (hyphon) symbol will be there.
     * @return
     */
    public MyProfilePage verifyHyphon(){
        String btnRemind=myProfile.getLocator("RefferralProcess.BtnHyphen");
        WaitForElementPresent(btnRemind, getTimeOut());
        clickOn(btnRemind);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify User is registered or not in website 
     * @param email
     * @return
     * @throws Exception
     */
    public MyProfilePage verifyUserRegisteredOrNot(String email) throws Exception{
        String referralTable = myProfile.getLocator("RefferralProcess.ReferralTable");
        List<WebElement>  referralTableList = driver.findElements(By.xpath(referralTable));
        int listSize = referralTableList.size();
        String elementeName="";
        for (int i=1;i<=listSize;i++){
            String refferralTableElements= "//div[@id='user-referral']/table/tbody/tr["+i+"]/td[1]";
            WaitForElementPresent(refferralTableElements, getTimeOut());
            elementeName = elementeName+getText(refferralTableElements)+"#";
        }
        String value = databaseVerification.checkUserIsRegistered(email);
        if (value.equals(null) || value.equals("")){
            Assert.assertTrue(elementeName.contains(email));
            System.out.println("User is not registered");
        }
        else {
            System.out.println("User is registered");
        }
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Get page url
     * @return
     */
    public String getUrl(){
        String page_Url=getWebDriver().getCurrentUrl();
        return page_Url; 
    }

    /**
     *  Database verification for referred user
     * @param email
     * @param levelId
     * @param welcomemail
     * @param ambassador_Campaign_Id
     * @param ambassador_Level_Id
     * @param source
     * @param campaign_id
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationForReferredUser(String email, String levelId, String welcomemail, String ambassador_Campaign_Id, String ambassador_Level_Id, String source, String campaign_id) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationForReferredUser(MyProfilePage.class,email, levelId,welcomemail, ambassador_Campaign_Id,ambassador_Level_Id,source,campaign_id);
        return myProfile;
    }

    /**
     *  Database verification in Pre_Order table
     * @param course_Id
     * @param courseTitle
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUser_PreOrderTable(String course_Id, String courseTitle,String gateway,String currency,String testCaseName,String excelSheetName,String Discount,String TotalAmount,String price,String serviceTax,String email) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInPreOrderTable(MyProfilePage.class, course_Id, courseTitle,gateway,currency,testCaseName,excelSheetName,Discount,TotalAmount,price,serviceTax,email);
        return myProfile;
    }
    /**
     *  Database verification in Pre_Order table
     * @param course_Id
     * @param courseTitle
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUser_QAUserFavoritesTable(String course_Id,String courseGroup,String entityType,String UserId) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInUser_QAUserFavoritesTable(MyProfilePage.class,courseGroup, course_Id,entityType,UserId);
        return myProfile;
    }
    /**
     *  Database verification in Pre_Order table
     * @param course_Id
     * @param courseTitle
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationInUser_Events_Table_PaymentType(String eventType,String getUserID) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInUser_Events_Table_PaymentType(MyProfilePage.class,eventType,getUserID);
        return myProfile;
    }

    /**
     *  Click on My Profile Link
     * @return
     */
    public MyProfilePage clickOnMyProfile(){
        String lTMyProfile=myProfile.getLocator("LTProfile");
        WaitForElementPresent(lTMyProfile, getTimeOut());
        clickOn(lTMyProfile);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    /**
     *  Click on My Profile Link
     * @return
     */
    public MyProfilePage clickOnMyOrders(){
        String lTMyOrders=myProfile.getLocator("MyOrdersTab");
        WaitForElementPresent(lTMyOrders, getTimeOut());
        clickOn(lTMyOrders);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    /**
     *  Validate course details on My orders Page
     * @return
     */
    public MyProfilePage validateCourseDetailsOnMyOrdersPage(String courseTitle){
        String courseImage=myProfile.getLocator("MyOrdersTab");
        Assert.assertTrue(isElementPresent(courseImage));
        String title=myProfile.getLocator("CourseTitle");
        String crsTitle=getText(title);
        Assert.assertTrue(crsTitle.contains(courseTitle));
        String orderId=myProfile.getLocator("OrderId");
        Assert.assertTrue(!(getText(orderId).equals(null)));
       // String price=propertyReader.readRunTimeData("PriceValue");
        String priceLocator=myProfile.getLocator("Price");
        String priceVal=getText(priceLocator);
        System.out.println("priceVal "+priceVal);
       // System.out.println("price"+price);
       // Assert.assertTrue(priceVal.trim().contains(price.trim()));
        String paymentDate=myProfile.getLocator("PaymentDate");
        Assert.assertTrue(isElementPresent(paymentDate)); 
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    /**
     *  Click on My Profile Link
     * @return
     */
    public MyProfilePage verifyViewInvoice(String courseTitle,String email){
        //View Invoice
        String viewInvice=myProfile.getLocator("ViewInvice");
        Assert.assertTrue(isElementPresent(viewInvice));
        clickOn(viewInvice);

        //Retail Invoice title
        String retailInvoiceTitle=myProfile.getLocator("RetailInvoiceText");
        waitForWebElementPresent(driver.findElement(By.xpath(retailInvoiceTitle)));
        Assert.assertTrue(isElementPresent(retailInvoiceTitle));
        String orderId=myProfile.getLocator("InvoiceOrderId");
        Assert.assertTrue(!(getText(orderId).equals(null)));

        //UserName
        String user=propertyReader.readRunTimeData("UserName");
        String userName=myProfile.getLocator("UserName");
        Assert.assertTrue(getText(userName.trim()).contains(user.trim()));

        //Email Id
       // String emailId=propertyReader.readRunTimeData("Email_Id");
        String invoicemail=myProfile.getLocator("EmailId");
        String emaildID = getText(invoicemail);
        System.out.println("EmailId from Invoice Mail: "+emaildID);
        Assert.assertTrue(emaildID.contains(email.trim()));

        //Course Name
        String courseName=myProfile.getLocator("CourseName");
        Assert.assertTrue(getText(courseName).contains(courseTitle));

/*        //total Value
        String price=propertyReader.readRunTimeData("PriceValue");
        String totalPrice=myProfile.getLocator("TotalPrice");
        Assert.assertTrue(getText(totalPrice).contains(price.trim())); 
*/
        //click on close Button
        String closeButton=myProfile.getLocator("CloseButton");
        clickOn(closeButton);

        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    /**
     *  Click on My Profile Link
     * @return
     * @throws InterruptedException 
     */
    public MyProfilePage verifyEmailAndPrintInvoice(String email) throws InterruptedException{
        //Email Invoice
        String emailInvice=myProfile.getLocator("EmailInvoice");
        waitForElement();
        Assert.assertTrue(isElementPresent(emailInvice));
        clickOn(emailInvice);

        //Email Id
        String emailId=myProfile.getLocator("InvoiceEmailId");
        Assert.assertTrue(isElementPresent(emailId));
        sendKeys(emailId, email);
        //Send Button
        String sendButton=myProfile.getLocator("SendButton");
        Assert.assertTrue(isElementPresent(sendButton));
        clickOn(sendButton);

        //Print Invoice
        String printInvice=myProfile.getLocator("PrintInvoice");
        Assert.assertTrue(isElementPresent(printInvice));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify AMBASSADOR_TRANSACTIONS User_id refferer_id level_id and campaign_id (taken from AMBASSADOR) , order_id(taken fron post_orders), revenue, value
     * @param email
     * @return
     * @throws Exception
     */
    public MyProfilePage verifyData_InAmassador_Transaction_ForReferral(String email,String  course_Id) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.verifyData_InAmassador_Transaction_ForReferral(MyProfilePage.class,email, course_Id);
        return myProfile;
    }

    /**
     *  Redemption of credit points
     * @param email
     * @param course_Id
     * @return
     * @throws Exception
     */
    public MyProfilePage verifyData_For_redemptions_In_ReferralProcess(String email, String  course_Id) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.verifyData_For_redemptions_In_ReferralProcess(MyProfilePage.class,email, course_Id);
        return myProfile;
    }

    /**
     *  Verify referral pop up messages
     * @param twitter_referral_Link
     * @param linkdln_Referral_Link
     * @return
     */
    public MyProfilePage verifyReferralPop(String twitter_referral_Link, String linkdln_Referral_Link){
        String headerMessage = myProfile.getLocator("RefferralProcess.HeaderMessage");
        WaitForElementPresent(headerMessage, getTimeOut());
        String msgText = getText(headerMessage);
        System.out.println("headerMessage:::: " +msgText);
        Assert.assertTrue(msgText.contains("If your friend buys using this link, you get 10% cashback and your friend gets 10% discount."));

        String ltCopy =  myProfile.getLocator("RefferralProcess.LTCopy");
        WaitForElementPresent(headerMessage, getTimeOut());
        Assert.assertTrue(isElementPresent(ltCopy));

        String import_Gmail =  myProfile.getLocator("RefferralProcess.ImportGmail");
        WaitForElementPresent(import_Gmail, getTimeOut());
        Assert.assertTrue(isElementPresent(import_Gmail));

        String import_OutLook =  myProfile.getLocator("RefferralProcess.ImportOutLook");
        WaitForElementPresent(import_OutLook, getTimeOut());
        Assert.assertTrue(isElementPresent(import_OutLook));

        String subjectEmail = myProfile.getLocator("RefferralProcess.EmailSubject");
        WaitForElementPresent(subjectEmail, getTimeOut());
        String subjectEmail_text = getAttribute(subjectEmail, "value");
        Assert.assertTrue(subjectEmail_text.contains("has invited you to learn on Edureka and get 10% discount"));

        String messageBottom =  myProfile.getLocator("RefferralProcess.BottomMessage");
        WaitForElementPresent(messageBottom, getTimeOut());
        Assert.assertTrue(isElementPresent(messageBottom));

        String twitter =  myProfile.getLocator("RefferralProcess.Twitter");
        WaitForElementPresent(twitter, getTimeOut());
        clickOn(twitter);

        String twitterInsideMessage =  "//textarea[contains(text(),'Have you checked out the live online courses by www.edureka.co? Use my referral link "+twitter_referral_Link+" to get a 10% discount!')]";
        WaitForElementPresent(twitterInsideMessage, getTimeOut());
        Assert.assertTrue(isElementPresent(twitterInsideMessage));

        String btnPostTwitter =  myProfile.getLocator("RefferralProcess.BTNPostOnTwitter");
        WaitForElementPresent(btnPostTwitter, getTimeOut());
        Assert.assertTrue(isElementPresent(btnPostTwitter));

        String linkdln =  myProfile.getLocator("RefferralProcess.linkdln");
        WaitForElementPresent(linkdln, getTimeOut());
        clickOn(linkdln);

        String linkdlnInsideMessage =  "//textarea[contains(text(),'Have you checked out the live online courses by www.edureka.co? The classes are conducted by a well-versed industry expert and you also get 24X7 post-session support! Use my referral link "+linkdln_Referral_Link+" to get a 10% discount.')]";
        WaitForElementPresent(linkdlnInsideMessage, getTimeOut());
        Assert.assertTrue(isElementPresent(linkdlnInsideMessage));

        String btnPostLinkdln =  myProfile.getLocator("RefferralProcess.btnShareinLinkedIn");
        WaitForElementPresent(btnPostLinkdln, getTimeOut());
        Assert.assertTrue(isElementPresent(btnPostLinkdln));

        String linkdlnBottomMessage =  myProfile.getLocator("RefferralProcess.LinkdlnBottomMessage");
        WaitForElementPresent(linkdlnBottomMessage, getTimeOut());
        Assert.assertTrue(isElementPresent(linkdlnBottomMessage));

        String linkdlnBottomTitle =  myProfile.getLocator("RefferralProcess.LinkdlnBottomTitle");
        WaitForElementPresent(linkdlnBottomTitle, getTimeOut());
        Assert.assertTrue(isElementPresent(linkdlnBottomTitle));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Database Verification for Referral pop
     * @param className
     * @param email
     * @return
     * @throws Exception
     */
    public MyProfilePage datbaseVerificationFor_ReferralPop(String email) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.datbaseVerificationFor_ReferralPop(MyProfilePage.class,email);
        return myProfile;
    }
    /**
     *   Database verification in Complete Post_Order table on the basis of preorder table
     * @param course_Id
     * @param courseTitle
     * @param paymentGatway
     * @param currency
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationIn_PostOrderOnTheBasisOfPreOrderTable(String course_Id, String courseTitle, String paymentGatway, String currency,String emailId) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInPostOrderOnTheBasisOfPreOrderTable(MyProfilePage.class, course_Id, courseTitle, paymentGatway, currency, emailId);
        return myProfile;
    }
    /**
     *   Database verification in Customer records table on the basis of preorder table
     * @param course_Id
     * @param courseTitle
     * @param paymentGatway
     * @param currency
     * @return
     * @throws Exception
     */
    public MyProfilePage dataVerificationIn_CustomerRecordsTable(String course_Id, String courseName, String paymentGatway, String currency,String emailId) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.dataVerificationInCustomerRecordsTable(MyProfilePage.class, course_Id, courseName, paymentGatway, currency,emailId);
        return myProfile;
    }   
    /**
     *  Verify campaign and level Assignment
     * @param campaignId
     * @param levelId
     * @return 
     */
    public MyProfilePage verifyCampaignAndLevelAssignment(String campaignId, String levelId){
        String inviteFriendText= myProfile.getLocator("RefferralProcess.LevelAndCampaignAssignment");
        String inviteFriendText_2= "//h5[contains(text(),'Test')]";
        if(campaignId.contains("3") && (levelId.contains("5"))){
            Assert.assertTrue(isElementPresent(inviteFriendText));
        }
        else {
            Assert.assertTrue(isElementPresent(inviteFriendText_2));
        }

        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Get Campaign and Level Id From Ambassador table   
     * @param email
     * @return
     * @throws Exception
     */
    public MyProfilePage getCampaignAndLevelIdFromAmbassadorTable(String email) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.getCampaignAndLevelIdFromAmbassadorTable(MyProfilePage.class,email);
        return myProfile;
    }

    /**
     *  Verify Remind Button
     * @return
     */
    public MyProfilePage verifyRemindButton(String email){
        String btnRemind="//td[contains(text(),'"+email+"')]/following::td[4]/a[contains(text(),'Remind')]";
        WaitForElementPresent(btnRemind, getTimeOut());
        Assert.assertTrue(isElementPresent(btnRemind));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify Existing Customer
     * @param name
     * @return
     */
    public MyProfilePage verifyExistingCustomer(String name){
        String existingCustomer="//td[contains(text(),'"+name+"')]/following::td[contains(text(),'Existing Customer')]";
        WaitForElementPresent(existingCustomer, getTimeOut());
        Assert.assertTrue(isElementPresent(existingCustomer ));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     * Database Verification For Existing Customer, Remind and Earned Credit in Referral
     * @param email
     * @param referral_Eamil
     * @param points
     * @return
     * @throws Exception
     */
    public MyProfilePage databaseVerificationForExisting_Customer_Remind_Earned_CreditInReferral(String email, String referral_Eamil, String points) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.databaseVerificationForExisting_Customer_Remind_Earned_CreditInReferral(MyProfilePage.class,email, referral_Eamil, points);
        return myProfile;
    }

    /**
     *  Verify Referral Count
     * @param email
     * @param referral_Count
     * @return
     * @throws Exception
     */
    public MyProfilePage verifyReferralCountForRefferalProcess(String email, String referral_Count) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.verifyReferralCountForRefferalProcess(MyProfilePage.class,email, referral_Count);
        return myProfile;
    }

    /**
     *  Verify Credit Points are same 
     * @param creditPoint_1
     * @return
     */
    public MyProfilePage verifyCreditPointsAreSame(String creditPoint_1){
        String earningCredit = myProfile.getLocator("RefferralProcess.EarnCredit");
        _waitForJStoLoad();
        String earningCreditPointsText= getText(earningCredit);
        String earningCreditPointsText_1 = earningCreditPointsText.trim();
        Assert.assertTrue(earningCreditPointsText_1.contains(creditPoint_1));
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Compare earning points
     * @param email
     * @param referral_Count
     * @return
     * @throws Exception
     */
    public MyProfilePage compareEarnedPoints(String email, String referral_Count) throws Exception{
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.compareCreditPoints(MyProfilePage.class,email, referral_Count);
        return myProfile;
    }
    /**
     *  Get User A and User B Available Points
     * @param email
     * @param referral_Email
     * @return
     * @throws Exception
     */
    public MyProfilePage getAvailablePointsOfUserAndUserB(String email, String referral_Email) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.getAvailablePointsOfUserAndUserB(MyProfilePage.class, email, referral_Email);
        return myProfile;
    }

    /**
     * Compare Credit points after referred Existing Customer   
     * @param email
     * @return
     * @throws Exception
     */
    public MyProfilePage compareCreditPointAfterReferExistingCustomer(String email) throws Exception {
        MyProfilePage myProfile;
        myProfile = (MyProfilePage)databaseVerification.compareCreditPointAfterReferExistingCustomer(MyProfilePage.class, email);
        return myProfile;
    }
}