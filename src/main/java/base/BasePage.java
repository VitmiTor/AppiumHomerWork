package base;

import elements.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utilities.Gestures;
import utilities.Logs;

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

    protected void waitPage($ element, String pageName) {
        Logs.info("Waiting  %s", pageName);
        element.waitforVisibility(timeout);
        Logs.info("%s Loaded", pageName);
    }

    protected $ $($.LocatorType locatorType, String locator) {
        return new $(driver, locator, locatorType, timeout);
    }

    @Step("Scrolling up")
    public void scrollTopTop() {
        Logs.info("Scrolling to top");
        gestures.scrollToTop();
    }

    @Step("Pressing Back")
    public void pressBackButton() {
        Logs.info("Pressing Back button");
        gestures.pressBack();
    }

    @Step("Pressing Home")
    public void pressHome() {
        Logs.info("Pressing Home");
        gestures.pressHome();
    }

    @Step("Swipe x1:{0} y1:{1} x2:{2} y2:{3}")
    public void swipe(int x1, int y1, int x2, int y2) {
        Logs.info("Swiping using x1: %d y1: %d x2: %d y2: %d", x1, y1, x2, y2);
        gestures.generalSwipeByPercentages(x1, y1, x2, y2);
    }

    @Step("Swipe init:{0} end:{1}")
    public void swipe(int init, int end, $.Orientation orientation) {
        Logs.info("Swipe using init: %d end: %d", init, end);
        gestures.generalSwipeByPercentages(init, end, orientation);
    }

    @Step("Swipe init:{0} end:{1} aux:{2}")
    public void swipe(int init, int end, int aux, $.Orientation orientation) {
        Logs.info("Swipe using x1: %d x2: %d aux: %d", init, end, aux);
        gestures.generalSwipeByPercentages(init, end, aux, orientation);
    }

    public abstract void waitPageToLoad();

    public abstract void verifyPage();
}
