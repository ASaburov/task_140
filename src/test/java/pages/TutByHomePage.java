package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TutByHomePage extends PageObject {

    private static final By ENTER_POPUP = By.className("enter");
    private static final By LOGIN_INPUT = By.name("login");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By ENTER_SUBMIT_BUTTON = By.xpath("//input[@class='button auth__enter']");
    private static final By USERNAME_LINK = By.className("uname");
    private static final By EXIT_BUTTON = By.xpath("//*[@class='button wide auth__reg']");

    public TutByHomePage(WebDriver driver) {
        super(driver);
    }

    public void expandEnterPopup() {
        this.driver.findElement(ENTER_POPUP).click();
    }

    public void login(String login, String password) {
        this.driver.findElement(LOGIN_INPUT).sendKeys(login);
        this.driver.findElement(PASSWORD_INPUT).sendKeys(password);
        this.driver.findElement(ENTER_SUBMIT_BUTTON).click();
    }

    public void logout() {
        this.driver.findElement(EXIT_BUTTON).click();
    }

    public String getCurrentUserName() {
        return this.driver.findElement(USERNAME_LINK).getText();
    }


}
