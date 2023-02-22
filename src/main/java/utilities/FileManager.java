package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class FileManager {
    public static WebDriver staticDriver;
    private final String screenShotPath = "src/test/resources/screenshots";
    private final String allureReportsPath = "target/allure-results";

    public FileManager redirectStdErr() {
        Logs.debug("Redirecting stderr");
        var file = new File("src/test/resources/logs/stderr.log");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PrintStream ps = new PrintStream(fos);
        System.setErr(ps);
        return this;
    }

    public void getScreenshot(WebDriver driver, String screenshotName) {
        Logs.debug("Taking screenshohts");

        var screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        var path = String.format("%s/%s.png", screenShotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Path no existe");
            Logs.error(ioException.getLocalizedMessage());
        }
    }

    public FileManager deleteAllureResults() {
        try {
            Logs.debug("Deleting previous allure results Directory");
            FileUtils.deleteDirectory(new File(allureReportsPath));
        } catch (IOException ioException) {
            Logs.error("Failed deleting Folder");
            Logs.error(ioException.getLocalizedMessage());
        }
        return this;
    }

    public void deleteScreenShotDirectory() {
        try {
            Logs.debug("Cleaning screenShot Directory");
            FileUtils.deleteDirectory(new File(screenShotPath));
        } catch (IOException ioException) {
            Logs.error("El path no existe");
            Logs.error(ioException.getLocalizedMessage());
        }
    }

    @Attachment(value = "Screenshot failure", type = "image/png")
    public static byte[] getAllureScreenShot() {
        return ((TakesScreenshot) staticDriver).getScreenshotAs(OutputType.BYTES);
    }
}
