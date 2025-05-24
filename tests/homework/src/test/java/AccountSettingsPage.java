import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.interactions.Actions;

public class AccountSettingsPage extends PageBase {
    // Locators
    By firstNameInput = By.xpath("//input[@id='first-name-input']");
    By lastNameInput = By.xpath("//input[@id='last-name-input']");
    By updateButton = By.xpath("//form[@id='account-info-form']//button[@type='submit']");
    By headerXPath = By.xpath("//div[@class='page-header']/h1");
    By successMessageLocator = By.xpath("//div[@class='notification-content-and-cta']/div[@class='notification-content']");
    By contactUsLink = By.xpath("//div[contains(@class,'footer-section')]//h2[text()='Help']/following-sibling::ul//a[@href='/contact' and text()='Contact us']");

    public AccountSettingsPage(WebDriver driver) {
        super(driver);
    }

    // Fill first and last name
    public void fillAccountInfo(String firstName, String lastName) {
        WebElement firstNameField = waitAndReturnElement(firstNameInput);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = waitAndReturnElement(lastNameInput);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    // Click the Update button
    public void clickUpdate() {
        WebElement updateBtn = waitAndReturnElement(updateButton);
        updateBtn.click();
    }

    // Convenience method to fill form and submit
    public void updateAccountInfo(String firstName, String lastName) {
        fillAccountInfo(firstName, lastName);
        clickUpdate();
    }

    // Get settings page header text using XPath
    public String getAccountSettingsHeader() {
        WebElement accountSettignsHeader = waitAndReturnElement(headerXPath);
        return accountSettignsHeader.getText();
    }

    // Get success text using XPath
    public String getSuccessMessage() {
        WebElement successMessage = waitAndReturnElement(successMessageLocator);
        return successMessage.getText();
    }

    // Navigate to contact page
    public ContactPage clickContactUsLink() {
        WebElement contactUs = waitUntilClickable(contactUsLink);
        new Actions(this.driver).moveToElement(contactUs).perform();  // Scroll to element using Actions
        contactUs.click();
        return new ContactPage(this.driver);
    }
}
