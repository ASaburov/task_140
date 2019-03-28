package junit;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.TutByHomePage;
import util.ScreenshotMaker;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TutByLoginLogoutTests {

    private static final String URL = "https://tut.by";
    private static final String LOGIN = "seleniumtests@tut.by";
    private static final String PASSWORD = "123456789zxcvbn";
    private static final String LOGIN_EXPECTED_RESULT = "Selenium Test";
    private static final String SCREENSHOT_FILE_PATH = "src/main/screenshots";
    private static final String SCREENSHOT_FILE_NAME = "logoutImage.png";




    private WebDriver driver;

//    String sauceUserName = "ASaburov";
//    String sauceAccessKey = "302a274a-e008-46d4-85de-0fb707d92397";
//    String sauceURL = "http://ondemand.saucelabs.com:80/wd/hub";


    @BeforeEach
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
//        DesiredCapabilities caps = DesiredCapabilities.firefox();
//        caps.setCapability("username", sauceUserName);
//        caps.setCapability("accessKey", sauceAccessKey);
//        caps.setCapability("platform", "Windows 8.1");
//        caps.setCapability("version", "39.0");
//        driver = new RemoteWebDriver(new URL(sauceURL), caps);
        driver.get(URL);
    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }

    //test logs in the app and check whether username appeared in the corresponding field
    @Test
    public void loginProcessTest() {
        TutByHomePage homePage = new TutByHomePage(driver);
        homePage.expandEnterPopup();
        homePage.login(LOGIN, PASSWORD);
        assertEquals(LOGIN_EXPECTED_RESULT, homePage.getCurrentUserName());
    }

    //test logs out the app and check whether username is not displayed
    @Test
    public void logoutProcessTest() {
        TutByHomePage homePage = new TutByHomePage(driver);
        homePage.expandEnterPopup();
        homePage.login(LOGIN, PASSWORD);
        homePage.expandEnterPopup();
        homePage.logout();
        //taking a screenshot and saving it to the file
        ScreenshotMaker screenshotMaker = new ScreenshotMaker();
        File file = screenshotMaker.takeScreenshot(driver);
        screenshotMaker.saveTheScreenhotTo(file, SCREENSHOT_FILE_PATH, SCREENSHOT_FILE_NAME);
        assertThrows(NoSuchElementException.class, () ->
                homePage.getCurrentUserName());
    }


}