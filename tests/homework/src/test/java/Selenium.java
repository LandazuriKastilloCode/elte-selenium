import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import java.net.MalformedURLException;
import java.net.URL;

public class Selenium {
    private WebDriver driver;

    // Constants
    private static final String SELENIUM_GRID_URL = "http://selenium:4444/wd/hub";

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        this.driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
        this.driver.manage().window().maximize();
        this.driver.get("https://www.overleaf.com/login");
        driver.manage().addCookie(SessionCookies.OA);
        driver.manage().addCookie(SessionCookies.OVERLEAF_SESSION2);
        driver.navigate().refresh();
    }

    @Test
    public void testSelenium() throws InterruptedException {
        List<Map<String, String>> pages = Arrays.asList(
                new HashMap<String, String>() {{
                    put("url", "https://www.overleaf.com/about");
                    put("title", "The home of scientific and technical writing");
                }},
                new HashMap<String, String>() {{
                    put("url", "https://www.overleaf.com/about/values");
                    put("title", "Our philosophy");
                }}
        );
        for (Map<String, String> pageData : pages) {
            String url = pageData.get("url");
            String expectedTitle = pageData.get("title");
            driver.get(url);
            PageBase page = new PageBase(driver);
            Assert.assertTrue(
                    "Title does not match for: " + url,
                    page.getH1Text().contains(expectedTitle)
            );
        }
        this.driver.get("https://www.overleaf.com/login");
        ProjectPage projectPage = new ProjectPage(driver);
        projectPage.hoverOverFreePlanLabel();
        Assert.assertTrue(projectPage.getFreePlanTooltip().isDisplayed());
        AccountSettingsPage accountSettingsPage = projectPage.navigateToAccountSettings();
        // Sending Form
        accountSettingsPage.updateAccountInfo("Antonio", "Kastillo");
        Assert.assertTrue(
                "AccountSettingsPage header does not contain expected text",
                accountSettingsPage.getAccountSettingsHeader().toLowerCase().contains("account settings".toLowerCase())
        );
        Assert.assertTrue("Success message does not contain expected text", accountSettingsPage.getSuccessMessage().contains(" your settings have been updated."));
        // Contact Page
        ContactPage contactPage = accountSettingsPage.clickContactUsLink();
        Assert.assertTrue(
                "Contact Page header does not contain expected text",
                contactPage.getHeader().toLowerCase().contains("get in touch".toLowerCase())
        );
        String[] options = contactPage.getSubSubjectOptions();
        boolean containsLatex = Arrays.asList(options).contains("Using LaTeX");
        Assert.assertTrue("Dropdown should contain 'Using LaTeX'", containsLatex);
        contactPage.enterMessage("Just a message");
        Assert.assertTrue(
                "Contact Page textarea does not contain expected text",
                contactPage.getMessageText().contains("Just a message")
        );

        projectPage = contactPage.clickHomeIcon();
        // Blog Page
        BlogPage blogPage = projectPage.clickBlockLink();
        Assert.assertTrue(
                "Blog Page header does not contain expected text",
                blogPage.getHeader().toLowerCase().contains("Blog".toLowerCase())
        );
        projectPage = blogPage.clickHomeIcon();
        // Navigation test
        driver.navigate().back();
        Assert.assertTrue(
                "Current URL is not as expected after navigating back",
                driver.getCurrentUrl().equals("https://www.overleaf.com/blog")
        );
        driver.navigate().forward();
        // Log out
        SignInPage signInPage = projectPage.logoutUser();
        Assert.assertTrue(
                "Sign In Page header does not contain expected text",
                signInPage.getLoginHeader().toLowerCase().contains("Log in to Overleaf".toLowerCase())
        );
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
