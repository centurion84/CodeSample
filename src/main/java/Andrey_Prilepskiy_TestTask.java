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
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        Assert.assertTrue(findElement("div[id='users_models_RegisterForm_name_em_']").isDisplayed());
    }
    @Test (dependsOnMethods = {"NameFieldEmpty"})
    public static void NameFieldValue () {
        findAndWrite("input[id='users_models_RegisterForm_name']", "TestName");
        EmptyClick("input[id='users_models_RegisterForm_name']");
        Assert.assertTrue (findElement("div[id='users_models_RegisterForm_name_em_']").isDisplayed());
    }
    // Tests for Nick (login)

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
//      // Close browser after tests are ended
//    @AfterTest
//    public static void closeBrowser() {
//        browser.quit();
//    }
}