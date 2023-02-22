package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.Gestures;

import java.time.Duration;

public abstract class BasePage {
    protected AndroidDriver driver;
    public static final int DEFAULT_TIMEOUT = 5;
    protected WebDriverWait wait;
    protected SoftAssert softAssert;
    protected int timeout;
    protected Gestures gestures;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        timeout = DEFAULT_TIMEOUT;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        softAssert = new SoftAssert();
        gestures = new Gestures(driver);
    }

    public BasePage(AndroidDriver driver, int timeout) {
        this(driver);
        this.timeout = timeout;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public abstract void waitPageToLoad();

    public abstract void verifyPage();
}
