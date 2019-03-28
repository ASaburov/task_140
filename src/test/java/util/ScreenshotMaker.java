package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotMaker {

    public File takeScreenshot(WebDriver driver){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    public void saveTheScreenhotTo(File file, String filePath, String imageNameWithExtension){
        try {
            FileUtils.copyFile(file, new File(filePath + imageNameWithExtension));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
