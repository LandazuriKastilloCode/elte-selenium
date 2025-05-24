import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProjectPage extends PageBase {
    // Locators
    By accountButton = By.xpath("//div[@role='menu' and contains(@class, 'ds-nav-icon-dropdown')]//button[@aria-label='Account']");
    By accountSettingsLink = By.xpath("//ul[@role='menu']//a[contains(@href, '/user/settings') and contains(text(), 'Account')]");
    By blogLink = By.xpath("//div[@class='footer-section']//a[@href='/blog' and text()='Blog']");
    By freePlanLabel = By.xpath("//a[contains(@class, 'current-plan-label')]");
    By freePlanTooltip = By.xpath("//*[@id='free-plan-tooltip']");
    By logoutButton = By.xpath("//button[@type='submit' and @form='logOutForm' and @role='menuitem' and .//span[text()='Log Out']]");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    // Click on the account button
    public AccountSettingsPage navigateToAccountSettings() {
        WebElement accountButtonElement = waitAndReturnElement(accountButton);
        accountButtonElement.click();

        WebElement accountSettingsElement = waitAndReturnElement(accountSettingsLink);
        accountSettingsElement.click();
        return new AccountSettingsPage(this.driver);
    }

    // Navigate to blog page
    public BlogPage clickBlockLink() {
        WebElement blog = waitUntilClickable(blogLink);
        new Actions(this.driver).moveToElement(blog).perform();
        blog.click();
        return new BlogPage(this.driver);
    }

    // Hover over the free plan label
    public void hoverOverFreePlanLabel() {
        WebElement planLabel = waitAndReturnElement(freePlanLabel);
        new Actions(this.driver).moveToElement(planLabel).perform();
    }

    // Return the tooltip element
    public WebElement getFreePlanTooltip() {
        return waitAndReturnElement(freePlanTooltip);
    }

    // Logout
    public SignInPage logoutUser() {
        WebElement accountButtonElement = waitAndReturnElement(accountButton);
        accountButtonElement.click();

        WebElement logoutElement = waitAndReturnElement(logoutButton);
        logoutElement.click();
        return new SignInPage(this.driver);
    }

}
