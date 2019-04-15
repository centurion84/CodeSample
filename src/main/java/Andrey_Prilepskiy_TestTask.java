import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Andrey_Prilepskiy_TestTask {

    static WebDriver browser;

    //Helpers
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
    public static void EmptyClick (String cssSelector) {
        Actions clicker = new Actions(browser);
        clicker.moveToElement(browser.findElement(By.cssSelector(cssSelector)),
                0, 50).click().build().perform();
    }
    // Test Data
    static String TestName="TestName";
    static String TestSurname = "TestSurname";
    static String NickRus = "Логин";
    static String DuplNick = "Test";
    static String UnduplNick = "TestNick";
    static String InvFormatEmail = "test.test.ua";
    static String DuplEmail = "testmail@test.ua";
    static String UnduplEmail = "testemail@test.ua";
    static String Less6CharsPassword = "12345";
    static String Chars6Password = "123456";
    static String UnequalPasswordConfirm = "111111";

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
    @Test (groups = { "Name field" }, priority = 1)
    public static void NameFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_name']");
        Assert.assertEquals("имя *" , findElement("label[for='field_ur_name']").getText());
    }
    @Test (groups = { "Name field" }, priority = 1)
    public void NameFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_name']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Необходимо заполнить поле «Имя»." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_name_em_']"))).getText());
    }
    @Test (groups = { "Name field" }, priority = 1)
    public static void NameFieldValue () {
        findAndWrite("input[id='users_models_RegisterForm_name']", TestName);
        EmptyClick("input[id='users_models_RegisterForm_name']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementWithText(
                By.cssSelector("div[id='users_models_RegisterForm_name_em_']"),
                "Необходимо заполнить поле «Имя».")));
    }
    // Tests for Surname
    @Test(groups = { "Surname field" }, priority = 2)
    public static void SurnameFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_family']");
        Assert.assertEquals("фамилия *" , findElement("label[for='field_ur_surname']").getText());
    }
    @Test (groups = { "Surname field" }, priority = 2)
    public void SurnameFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_family']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Необходимо заполнить поле «Фамилия»." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_family_em_']"))).getText());
    }
    @Test (groups = { "Surname field" }, priority = 1)
    public static void SurnameFieldValue () {
        findAndWrite("input[id='users_models_RegisterForm_family']", TestSurname);
        EmptyClick("input[id='users_models_RegisterForm_family']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementWithText(
                By.cssSelector("div[id='users_models_RegisterForm_family_em_']"),
                "Необходимо заполнить поле «Фамилия».")));
    }
    // Tests for Nick (login)
    @Test (groups = { "Login field" }, priority = 3)
    public static void NickFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_login']");
        Assert.assertEquals("ник *" , findElement("label[for='field_ur_login']").getText());
    }
    @Test (groups = { "Login field" }, priority = 3)
    public void NickFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_login']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Необходимо заполнить поле «Логин»." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_login_em_']"))).getText());
    }
    @Test (groups = { "Login field" }, priority = 3)
    public static void NickFieldRusValue () {
        findAndWrite("input[id='users_models_RegisterForm_login']", NickRus);
        EmptyClick("input[id='users_models_RegisterForm_login']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("НЕ используйте русские буквы" ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_login_em_']"))).getText());
    }
    @Test (groups = { "Login field" }, priority = 3)
    public static void NickFieldDuplicatedValue () {
        findAndWrite("input[id='users_models_RegisterForm_login']", DuplNick);
        EmptyClick("input[id='users_models_RegisterForm_login']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Логин " +"\""+ DuplNick + "\""+" уже занят." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_login_em_']"))).getText());
    }
    @Test (groups = { "Login field" }, priority = 3)
    public static void NickFieldUnduplicatedValue () {
        findAndWrite("input[id='users_models_RegisterForm_login']", UnduplNick);
        EmptyClick("input[id='users_models_RegisterForm_login']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementWithText(
                By.cssSelector("div[id='users_models_RegisterForm_login_em_']"),
                "Необходимо заполнить поле «Логин».")));
    }
    // Tests for E-mail
    @Test (groups = { "E-mail field" }, priority = 4)
    public static void EmailFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_email']");
        Assert.assertEquals("e-mail *" , findElement("label[for='field_ur_email']").getText());
    }
    @Test (groups = { "E-mail field" }, priority = 4)
    public void EmailFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_email']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Необходимо заполнить поле «E-mail»." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_email_em_']"))).getText());
    }
    @Test (groups = { "E-mail field" }, priority = 4)
    public static void EmailFieldInvalidFormat () {
        findAndWrite("input[id='users_models_RegisterForm_email']", InvFormatEmail);
        EmptyClick("input[id='users_models_RegisterForm_email']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("E-mail не является правильным E-Mail адресом." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_email_em_']"))).getText());
    }
    @Test (groups = { "E-mail field" }, priority = 4)
    public static void EmailFieldDuplicatedValue () throws InterruptedException {
        findAndWrite("input[id='users_models_RegisterForm_email']", DuplEmail);
        EmptyClick("input[id='users_models_RegisterForm_email']");
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals(("E-mail " +"\""+ DuplEmail + "\""+" уже занят.") ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_email_em_']"))).getText());
    }
    @Test (groups = { "E-mail field" }, priority = 4)
    public static void EmailFieldUnduplicatedValue () {
        findAndWrite("input[id='users_models_RegisterForm_email']", UnduplEmail);
        EmptyClick("input[id='users_models_RegisterForm_email']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementWithText(
                By.cssSelector("div[id='users_models_RegisterForm_email_em_']"),
                "Необходимо заполнить поле «E-mail».")));
    }
    // Tests for Password
    @Test (groups = { "Password field" }, priority = 5)
    public static void PasswordFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_password']");
        Assert.assertEquals("пароль *" , findElement("label[for='field_ur_password']").getText());
    }
    @Test (groups = { "Password field" }, priority = 5)
    public void PasswordFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_password']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Необходимо заполнить поле «Пароль»." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_password_em_']"))).getText());
    }
    @Test (groups = { "Password field" }, priority = 5)
    public static void PasswordFieldLess6CharsValue () {
        findAndWrite("input[id='users_models_RegisterForm_password']", Less6CharsPassword);
        EmptyClick("input[id='users_models_RegisterForm_password']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Пароль слишком короткий (Минимум: 6 симв.)." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_password_em_']"))).getText());
    }
    @Test (groups = { "Password field" }, priority = 5)
    public static void PasswordField6CharsValue () {
        findAndWrite("input[id='users_models_RegisterForm_password']", Chars6Password);
        EmptyClick("input[id='users_models_RegisterForm_password']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementWithText(
                By.cssSelector("div[id='users_models_RegisterForm_password_em_']"),
                "Необходимо заполнить поле «Пароль».")));
    }
    // Tests for Password Confirmation
    @Test (groups = { "Password repeat field" }, priority = 6)
    public static void PasswordRepeatFieldClick() {
        findAndClick("input[id='users_models_RegisterForm_password_repeat']");
        Assert.assertEquals("пароль еще раз *" , findElement("label[for='field_ur_pass_again']").getText());
    }
    @Test (groups = { "Password repeat field" }, priority = 6)
    public void PasswordRepeatFieldEmpty() {
        EmptyClick("input[id='users_models_RegisterForm_password_repeat']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Необходимо заполнить поле «Повторите пароль»." ,
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_password_repeat_em_']"))).getText());
    }
    @Test (groups = { "Password repeat field" }, priority = 6)
    public static void PasswordRepeatFieldUnequalValue () throws InterruptedException {
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", UnequalPasswordConfirm);
        EmptyClick("input[id='users_models_RegisterForm_password_repeat']");
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertEquals("Повторите пароль" , wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='users_models_RegisterForm_password_repeat_em_']"))).getText());
    }
    @Test (groups = { "Password repeat field" }, priority = 6)
    public static void PasswordRepeatFieldEqualValue () {
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", Chars6Password);
        EmptyClick("input[id='users_models_RegisterForm_password_repeat']");
        WebDriverWait wait=new WebDriverWait(browser, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementWithText(
                By.cssSelector("div[id='users_models_RegisterForm_password_repeat_em_']"),
                "Необходимо заполнить поле «Повторите пароль».")));
    }
    // Tests for Submitting user data for registration
    @Test (groups = { "Submit to register" },
            dependsOnMethods = {"EmailFieldUnduplicatedValue","NickFieldUnduplicatedValue"},
            priority = 7)
    public static void SubmitAllRequiredData()  {
        findAndClick("input[id='submitRegistration']");
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("Для подтверждения регистрации, проверьте почту",browser.switchTo().alert().getText());
        browser.switchTo().alert().accept();
    }
    // Tests for Submitting user data without required data
    @Test (groups = { "Submit without data" }, priority = 8)
    public static void SubmitWithoutName() {
        browser.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(browser, 2);
        wait.until(ExpectedConditions.elementToBeClickable(findElement("a[id='btnRegOpen']"))).click();
        findAndWrite("input[id='users_models_RegisterForm_family']", TestSurname);
        findAndWrite("input[id='users_models_RegisterForm_login']", UnduplNick);
        findAndWrite("input[id='users_models_RegisterForm_email']", UnduplEmail);
        findAndWrite("input[id='users_models_RegisterForm_password']", Chars6Password);
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", Chars6Password);
        wait.until(ExpectedConditions.elementToBeClickable(
                findElement("input[id='submitRegistration']"))).click();
        Assert.assertEquals("Необходимо заполнить поле «Имя».", wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[id='users_models_RegisterForm_name_em_']"))).getText());
    }
    @Test (groups = { "Submit without data" }, priority = 8)
    public static void SubmitWithoutSurname(){
        browser.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(browser, 2);
        wait.until(ExpectedConditions.elementToBeClickable(findElement("a[id='btnRegOpen']"))).click();
        findAndWrite("input[id='users_models_RegisterForm_name']", TestName);
        findAndWrite("input[id='users_models_RegisterForm_login']", UnduplNick);
        findAndWrite("input[id='users_models_RegisterForm_email']", UnduplEmail);
        findAndWrite("input[id='users_models_RegisterForm_password']", Chars6Password);
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", Chars6Password);
        wait.until(ExpectedConditions.elementToBeClickable(
                findElement("input[id='submitRegistration']"))).click();
        Assert.assertEquals("Необходимо заполнить поле «Фамилия».", wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[id='users_models_RegisterForm_family_em_']"))).getText());
    }
    @Test (groups = { "Submit without data" }, priority = 8)
    public static void SubmitWithoutLogin(){
        browser.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(browser, 2);
        wait.until(ExpectedConditions.elementToBeClickable(findElement("a[id='btnRegOpen']"))).click();
        findAndWrite("input[id='users_models_RegisterForm_name']", TestName);
        findAndWrite("input[id='users_models_RegisterForm_family']", TestSurname);
        findAndWrite("input[id='users_models_RegisterForm_email']", UnduplEmail);
        findAndWrite("input[id='users_models_RegisterForm_password']", Chars6Password);
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", Chars6Password);
        wait.until(ExpectedConditions.elementToBeClickable(
                findElement("input[id='submitRegistration']"))).click();
        Assert.assertEquals("Необходимо заполнить поле «Логин».", wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[id='users_models_RegisterForm_login_em_']"))).getText());
    }
    @Test (groups = { "Submit without data" }, priority = 8)
    public static void SubmitWithoutEmail() {
        browser.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(browser, 2);
        wait.until(ExpectedConditions.elementToBeClickable(findElement("a[id='btnRegOpen']"))).click();
        findAndWrite("input[id='users_models_RegisterForm_name']", TestName);
        findAndWrite("input[id='users_models_RegisterForm_family']", TestSurname);
        findAndWrite("input[id='users_models_RegisterForm_login']", UnduplNick);
        findAndWrite("input[id='users_models_RegisterForm_password']", Chars6Password);
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", Chars6Password);
        wait.until(ExpectedConditions.elementToBeClickable(
                findElement("input[id='submitRegistration']"))).click();
        Assert.assertEquals("Необходимо заполнить поле «E-mail».", wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[id='users_models_RegisterForm_email_em_']"))).getText());
    }
    @Test (groups = { "Submit without data" }, priority = 8)
    public static void SubmitWithoutPassword() {
        browser.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(browser, 2);
        wait.until(ExpectedConditions.elementToBeClickable(findElement("a[id='btnRegOpen']"))).click();
        findAndWrite("input[id='users_models_RegisterForm_name']", TestName);
        findAndWrite("input[id='users_models_RegisterForm_family']", TestSurname);
        findAndWrite("input[id='users_models_RegisterForm_login']", UnduplNick);
        findAndWrite("input[id='users_models_RegisterForm_email']", UnduplEmail);
        findAndWrite("input[id='users_models_RegisterForm_password_repeat']", Chars6Password);
        wait.until(ExpectedConditions.elementToBeClickable(
                findElement("input[id='submitRegistration']"))).click();
        Assert.assertEquals("Необходимо заполнить поле «Пароль».", wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[id='users_models_RegisterForm_password_em_']"))).getText());
    }
    @Test (groups = { "Submit without data" }, priority = 8)
    public static void SubmitWithoutPasswordRepeat() {
        browser.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(browser, 2);
        wait.until(ExpectedConditions.elementToBeClickable(findElement("a[id='btnRegOpen']"))).click();
        findAndWrite("input[id='users_models_RegisterForm_name']", TestName);
        findAndWrite("input[id='users_models_RegisterForm_family']", TestSurname);
        findAndWrite("input[id='users_models_RegisterForm_login']", UnduplNick);
        findAndWrite("input[id='users_models_RegisterForm_email']", UnduplEmail);
        findAndWrite("input[id='users_models_RegisterForm_password']", Chars6Password);
        wait.until(ExpectedConditions.elementToBeClickable(
                findElement("input[id='submitRegistration']"))).click();
        Assert.assertEquals("Необходимо заполнить поле «Повторите пароль».", wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[id='users_models_RegisterForm_password_repeat_em_']"))).getText());
    }
      // Close browser after tests are ended
    @AfterTest
    public static void closeBrowser() {
        browser.quit();
    }
}