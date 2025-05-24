import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private final By bodyLocator = By.xpath("//body");
    private final By h1Locator = By.xpath("//h1");

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    protected WebElement waitAndReturnElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected String getBodyText() {
        return waitAndReturnElement(bodyLocator).getText();
    }

    public String getH1Text() {
        return waitAndReturnElement(h1Locator).getText();
    }
}
