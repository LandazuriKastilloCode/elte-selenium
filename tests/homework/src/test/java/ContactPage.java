import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.Select;

public class ContactPage extends PageBase {
    By headerXPath = By.xpath("//h1[span[@class='eyebrow-text' and contains(., 'contact us')]]");
    By subSubjectDropdown = By.xpath("//select[@name='subSubject' and contains(@class, 'form-control')]");
    By homeIcon = By.xpath("//div[@class='navbar-header']/a[@class='navbar-brand']");
    By messageTextarea = By.xpath("//textarea[@name='message' and contains(@class, 'form-control')]");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    // Get contact page header text using XPath
    public String getHeader() {
        WebElement accountSettignsHeader = waitAndReturnElement(headerXPath);
        return accountSettignsHeader.getText();
    }

    // Click dropdown and return array of all option texts
    public String[] getSubSubjectOptions() {
        WebElement dropdownElement = waitAndReturnElement(subSubjectDropdown);

        // Optional: click the dropdown to open it
        dropdownElement.click();

        Select select = new Select(dropdownElement);

        // Get all options text as a List<String>
        List<String> optionsText = select.getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // Convert to array and return
        return optionsText.toArray(new String[0]);
    }

    // Write text into the message textarea
    public void enterMessage(String message) {
        WebElement textarea = waitAndReturnElement(messageTextarea);
        textarea.clear();
        textarea.sendKeys(message);
    }

    // Read the current value of the message textarea
    public String getMessageText() {
        WebElement textarea = waitAndReturnElement(messageTextarea);
        return textarea.getAttribute("value");
    }

    public ProjectPage clickHomeIcon() {
        WebElement homeIconElement = waitAndReturnElement(homeIcon);
        homeIconElement.click();
        return new ProjectPage(this.driver);
    }
}
