import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage extends PageBase {
    // Locators
    private final By emailInput = By.xpath("//input[@id='email']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//button[@type='submit' and contains(., 'Log in')]");
    private final By headerXPath = By.xpath("//main//h1");

    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.overleaf.com/login");
    }

    // Get login page header text using XPath
    public String getLoginHeader() {
        WebElement loginHeader = waitAndReturnElement(headerXPath);
        return loginHeader.getText();
    }

    // Fill email field
    public void enterEmail(String email) {
        WebElement emailField = waitAndReturnElement(emailInput);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Fill password field
    public void enterPassword(String password) {
        WebElement passwordField = waitAndReturnElement(passwordInput);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Click on the login button to submit the form
    public ProjectPage submitLogin() {
        WebElement submitButton = waitAndReturnElement(loginButton);
        submitButton.click();
        return new ProjectPage(this.driver);
    }

    // Convenience method to perform full login
    public ProjectPage loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return submitLogin();
    }
}
