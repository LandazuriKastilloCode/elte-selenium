import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BlogPage extends PageBase {

    By headerXPath = By.xpath("//div[@class='row section-row']//div[@class='col-sm-12']/h1");
    By homeIcon = By.xpath("//div[@class='navbar-header']/a[@class='navbar-brand']");

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    // Get project detail page header text using XPath
    public String getHeader() {
        WebElement header = waitAndReturnElement(headerXPath);
        return header.getText();
    }

    public ProjectPage clickHomeIcon() {
        WebElement homeIconElement = waitAndReturnElement(homeIcon);
        homeIconElement.click();
        return new ProjectPage(this.driver);
    }

}
