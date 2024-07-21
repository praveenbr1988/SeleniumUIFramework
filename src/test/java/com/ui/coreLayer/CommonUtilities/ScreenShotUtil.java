package com.ui.coreLayer.CommonUtilities;

import com.ui.orchestrationLayer.Generics.TestMembersFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ScreenShotUtil {

    private static WebDriver driver= TestMembersFactory.getDriver();

    public static String getBase64Screenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return Base64.getEncoder().encodeToString(ts.getScreenshotAs(OutputType.BYTES));
    }

    public static byte[] getBase64ScreenshotinBytes() {

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
            return fileContent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String saveScreenShot() {

        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        Date date =new Date();
        String actualDate = format.format(date);
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + actualDate + ".png";
        File dest = new File(screenshotPath);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return screenshotPath;

    }


}
