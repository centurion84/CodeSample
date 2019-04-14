import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Andrey_Prilepskiy_TestTask {

    static WebDriver browser;

    //helpers
    public static WebElement findElement(String cssSelector) {
        return browser.findElement(By.cssSelector(cssSelector));
    }
    public static void findAndClick(String cssSelector) {
        browser.findElement(By.cssSelector(cssSelector)).click();
    }
    public static void findAndWrite(String cssSelector, String text) {
        WebElement element = browser.findElement(By.cssSelector(cssSelector));
        element.clear();
        element.sendKeys(text);
    }
    public static boolean elementExists(String cssSelector) {
        return browser.findElements(By.cssSelector(cssSelector)).size() != 0;
    }
    public static boolean elementDontExist(String cssSelector) {
        return browser.findElements(By.cssSelector(cssSelector)).isEmpty();
    }
    public static void EmptyClick (String cssSelector) {
        Actions clicker = new Actions(browser);
        clicker.moveToElement(browser.findElement(By.cssSelector(cssSelector)), 0, 50).click().build().perform();
    }

    // Open Reg form on web site
    @BeforeTest
    public static void openRegForm() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        browser.get("http://odpublic.net");
        findAndClick("a[id='btnRegOpen']");
    }
    // Tests for Name
    @Test()
    public static void NameFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_name']");
        Assert.assertTrue(findElement("label[for='field_ur_name']").isDisplayed());
    }
    @Test (dependsOnMethods = {"NameFieldClick"})
    public void NameFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_name']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_name_em_']"))).isDisplayed());
        //Assert.assertTrue(findElement("div[id='users_models_RegisterForm_name_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"NameFieldEmpty"})
    public static void NameFieldValue () {
        findAndWrite("input[id='users_models_RegisterForm_name']", "TestName");
        EmptyClick("input[id='users_models_RegisterForm_name']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_name_em_']").isDisplayed());
    }
    // Tests for Surname
    @Test(dependsOnMethods = {"NameFieldValue"})
    public static void SurnameFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_family']");
        Assert.assertTrue(findElement("label[for='field_ur_surname']").isDisplayed());
    }
    @Test (dependsOnMethods = {"SurnameFieldClick"})
    public void SurnameFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_family']");
        WebDriverWait wait=new WebDriverWait(browser, 5);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_family_em_']"))).isDisplayed());
        //Assert.assertTrue(findElement("div[id='users_models_RegisterForm_family_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"SurnameFieldEmpty"})
    public static void SurnameFieldValue () {
        findAndWrite("input[id='users_models_RegisterForm_family']", "TestSurname");
        EmptyClick("input[id='users_models_RegisterForm_family']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_family_em_']").isDisplayed());
    }
    // Tests for Nick (login)
    @Test (dependsOnMethods = {"SurnameFieldValue"})
    public static void NickFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_login']");
        Assert.assertTrue(findElement("label[for='field_ur_login']").isDisplayed());
    }
    @Test (dependsOnMethods = {"NickFieldClick"})
    public void NickFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_login']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_login_em_']"))).isDisplayed());
        //Assert.assertTrue(findElement("div[id='users_models_RegisterForm_login_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"NickFieldEmpty"})
    public static void NickFieldRusValue () {
        findAndWrite("input[id='users_models_RegisterForm_login']", "Логин");
        EmptyClick("input[id='users_models_RegisterForm_login']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_login_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"NickFieldRusValue"})
    public static void NickFieldDuplicatedValue () {
        findAndWrite("input[id='users_models_RegisterForm_login']", "Testlogin");
        EmptyClick("input[id='users_models_RegisterForm_login']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_login_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"NickFieldDuplicatedValue"})
    public static void NickFieldUnduplicatedValue () {
        findAndWrite("input[id='users_models_RegisterForm_login']", "TestNick7");
        EmptyClick("input[id='users_models_RegisterForm_login']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_login_em_']").isDisplayed());
    }
    // Tests for E-mail
    @Test (dependsOnMethods = {"NickFieldUnduplicatedValue"})
    public static void EmailFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_email']");
        Assert.assertTrue(findElement("label[for='field_ur_email']").isDisplayed());
    }
    @Test (dependsOnMethods = {"EmailFieldClick"})
    public void EmailFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_email']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_email_em_']"))).isDisplayed());
       // Assert.assertTrue(findElement("div[id='users_models_RegisterForm_email_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"EmailFieldEmpty"})
    public static void EmailFieldInvalidFormat () {
        findAndWrite("input[id='users_models_RegisterForm_email']", "test.test.ua");
        EmptyClick("input[id='users_models_RegisterForm_email']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_email_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"EmailFieldInvalidFormat"})
    public static void EmailFieldDuplicatedValue () {
        findAndWrite("input[id='users_models_RegisterForm_email']", "testmail@test.ua");
        EmptyClick("input[id='users_models_RegisterForm_email']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_email_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"EmailFieldDuplicatedValue"})
    public static void EmailFieldUnduplicatedValue () {
        findAndWrite("input[id='users_models_RegisterForm_email']", "testemail8@test.ua");
        EmptyClick("input[id='users_models_RegisterForm_email']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_email_em_']").isDisplayed());
    }
    // Tests for Password
    @Test (dependsOnMethods = {"EmailFieldUnduplicatedValue"})
    public static void PasswordFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_password']");
        Assert.assertTrue(findElement("label[for='field_ur_password']").isDisplayed());
    }
    @Test (dependsOnMethods = {"PasswordFieldClick"})
    public void PasswordFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_password']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_password_em_']"))).isDisplayed());
        //Assert.assertTrue(findElement("div[id='users_models_RegisterForm_password_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"PasswordFieldEmpty"})
    public static void PasswordFieldLess6CharsValue () {
        findAndWrite("input[id='users_models_RegisterForm_password']", "12345");
        EmptyClick("input[id='users_models_RegisterForm_password']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_password_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"PasswordFieldLess6CharsValue"})
    public static void PasswordField6CharsValue () {
        findAndWrite("input[id='users_models_RegisterForm_password']", "123456");
        EmptyClick("input[id='users_models_RegisterForm_password']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_password_em_']").isDisplayed());
    }
    // Tests for Password Confirmation
    @Test (dependsOnMethods = {"PasswordField6CharsValue"})
    public static void PasswordRepeatFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_password_repeat']");
        Assert.assertTrue(findElement("label[for='field_ur_pass_again']").isDisplayed());
    }
    @Test (dependsOnMethods = {"PasswordRepeatFieldClick"})
    public void PasswordRepeatFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_password_repeat']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_password_repeat_em_']"))).isDisplayed());
       // Assert.assertTrue(findElement("div[id='users_models_RegisterForm_password_repeat_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"PasswordRepeatFieldEmpty"})
    public static void PasswordRepeatFieldUnequalValue () {
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", "111111");
        EmptyClick("input[id='users_models_RegisterForm_password_repeat']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_password_repeat_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"PasswordRepeatFieldUnequalValue"})
    public static void PasswordRepeatFieldEqualValue () {
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", "123456");
        EmptyClick("input[id='users_models_RegisterForm_password_repeat']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_password_repeat_em_']").isDisplayed());
    }
    // Tests for Submitting user data for registration
    @Test (dependsOnMethods = {"PasswordRepeatFieldEqualValue"})
    public static void SubmitAllRequiredData()  {
        findAndClick("input[id='submitRegistration']");
        WebDriverWait wait = new WebDriverWait(browser, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        //Assert.assertTrue(browser.switchTo().alert().getText() == "Для подтверждения регистрации, проверьте почту");
        Assert.assertEquals("Для подтверждения регистрации, проверьте почту",browser.switchTo().alert().getText());
        browser.switchTo().alert().accept();
    }
//        findAndWrite("input[name='os_username']", "aprilepskiy");
//        findAndWrite("input[name='os_password']", "Cent90308122");
//        findAndClick("input[id='login']");
//        Assert.assertTrue(elementExists("a[data-username]"));
//    }
//    @Test (dependsOnMethods = {"jiraLogin"})
//    public static void createIssue () throws InterruptedException {
//        findAndClick("a[id='browse_link']");
//        findAndClick("a[id='admin_main_proj_link_lnk']");
//        findAndClick("a[id='create_link']");
//        findAndWrite("input[class='text long-field']", newIssueSummary);
//        findAndWrite("input[id='assignee-field']", "Robert");
//        findAndClick("input[id='create-issue-submit']");
//        findElement("a.issue-created-key");
//        Assert.assertTrue(elementExists("a.issue-created-key"));
//        newIssueURL = findElement("a.issue-created-key").getAttribute("href");
//    }
//    @Test(dependsOnMethods = {"createIssue"})
//    public static void viewIssue () {
//        browser.get(newIssueURL);
//        Assert.assertEquals(newIssueSummary, findElement("h1#summary-val").getText());
//    }
//    @Test(dependsOnMethods = {"createIssue"})
//    public static void createIssueAttachment() {
//        browser.get(newIssueURL);
//        findElement("input[class='issue-drop-zone__file ignore-inline-attach']").sendKeys(attachmentPath);
//        findElement("span[style='width: 100%;']");
//        browser.get(newIssueURL);
//        Assert.assertEquals(attachmentName, findElement("a[class='attachment-title']").getText());
//    }
//    @Test (dependsOnMethods = {"createIssueAttachment"})
//    public static void downloadIssueAttachment() throws InterruptedException, IOException, NoSuchAlgorithmException {
//        browser.get(newIssueURL);
//        findAndClick("div[class='attachment-thumb']");
//        Thread.sleep(5000);
//        findAndClick("a[id='cp-control-panel-download']");
//        Thread.sleep(5000);
//        Assert.assertTrue(isFileDownloaded(attachmentName),"File names don't match of file downloading failed!");
//        Assert.assertTrue(Objects.equals(getMD5(uploadPath + "/" + attachmentName), getMD5(attachmentPath)),"MD5 is incorrect!");
//        new File(uploadPath + "/" + attachmentName).delete();
//    }
      // Close browser after tests are ended
    @AfterTest
    public static void closeBrowser() {
        browser.quit();
    }
}