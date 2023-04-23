package elements;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Gestures;

import java.time.Duration;

public class $ {
    private WebElement webElement;
    private final AndroidDriver driver;
    private final By locator;
    private String locatorString;

    private final LocatorType locatorType;
    private final int timeout;
    private WebDriverWait wait;
    private final Gestures gestures;

    public $(AndroidDriver driver, String locator, LocatorType locatorType) {
        this(driver, locator, locatorType, BasePage.DEFAULT_TIMEOUT);
    }

    public $(AndroidDriver driver, String locator, LocatorType locatorType, int timeout) {
        this.driver = driver;
        this.locatorType = locatorType;
        this.timeout = timeout;
        this.gestures = new Gestures(driver);
        this.locator = buildLocator(locator);
    }

    private By buildLocator(String locator) {
        switch (locatorType) {
            case ID:
                this.locatorString = String.format("resourceId(\"%s\")", locator);
                return By.id(locator);
            case ACCESIBILITY_ID:
                this.locatorString = String.format("description(\"%s\")", locator);
                return AppiumBy.accessibilityId(locator);
            case CLASSNAME:
                this.locatorString = String.format("className(\"%s\")", locator);
                return By.className(locator);
            case UIAUTOMATOR2:
                this.locatorString = locator;
                return AppiumBy.androidUIAutomator(locator);
        }
        return null;
    }

    public $ click() {
        findElement();
        webElement.click();
        return this;
    }

    public $ sendKeys(String text) {
        findElement();
        webElement.sendKeys(text);
        return this;
    }

    public boolean isDisplayed() {
        findElement();
        return webElement.isDisplayed();
    }

    public boolean isEnable() {
        findElement();
        return webElement.isEnabled();
    }

    public $ dragTo($ element) {
        findElement();
        gestures.dragOneItemToAnother(this.webElement, element.webElement);
        return this;
    }

    public $ doubleClick() {
        findElement();
        gestures.doubleClick(webElement);
        return this;
    }

    public $ longTap(int duration) {
        findElement();
        gestures.longTap(webElement, duration);
        return this;

    }

    public String getText() {
        findElement();
        return webElement.getText();
    }

    public $ scrollTo(Orientation orientation) {
        switch (orientation) {
            case VERTICAL:
                gestures.verticalScrollInto(locatorString);
                return this;
            case HORIZONTAL:
                gestures.horizontalScrollInto(locatorString);
                return this;
            default:
                return this;
        }
    }

    public $ waitforVisibility(int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this;
    }

    public $ waitforVisibility() {
        return waitforVisibility(timeout);
    }

    public enum LocatorType {
        ID,
        ACCESIBILITY_ID,
        CLASSNAME,
        UIAUTOMATOR2
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    //28:00
    public void findElement() {
        webElement = driver.findElement(locator);
    }

    public WebElement getWebElement() {
        findElement();
        return webElement;
    }
}
