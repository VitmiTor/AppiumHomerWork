package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Gestures {
    private final AndroidDriver driver;
    private final Actions actions;

    public Gestures(AndroidDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public WebElement verticalScrollInto(String locatorString) {
        Logs.debug(String.format("Vertical Scrolling into %s", locatorString));
        final var uiAutomatorText =
                String.format("UiScrollable(scrollable(true)).setAsVerticalList().scrollIntoView(%s)", locatorString);
        return driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }

    public WebElement horizontalScrollInto(String locatorString) {
        Logs.debug(String.format("Vertical Scrolling into %s", locatorString));
        final var uiAutomatorText =
                String.format("UiScrollable(scrollable(true)).setAsHorizontalList().scrollIntoView(%s)", locatorString);
        return driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }
}
