package utilities;

import elements.$;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

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

    public void scrollToTop() {
        final var uiAutomatorText = "new UiScrollable(scrollable(true)).scrollToBeginning(10)";
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }

    public void generalSwipeByPercentages(int x1, int y1, int x2, int y2) {
        swipe(
                getPointUsingPercentages(x1, y1),
                getPointUsingPercentages(x2, y2),
                0,
                1500
        );
    }

    public void generalSwipeByPercentages(int init, int end, $.Orientation orientation) {
        switch (orientation) {
            case HORIZONTAL:
                generalSwipeByPercentages(init, 50, end, 50);
                break;
            case VERTICAL:
                generalSwipeByPercentages(50, init, 50, end);
                break;
        }
    }

    public void generalSwipeByPercentages(int init, int end, int extra, $.Orientation orientation) {
        switch (orientation) {
            case HORIZONTAL:
                generalSwipeByPercentages(init, extra, end, extra);
                break;
            case VERTICAL:
                generalSwipeByPercentages(extra, init, extra, end);
                break;
        }
    }

    public void dragOneItemToAnother(WebElement originElement, WebElement destinyElement) {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final var sequence = new Sequence(finger, 1);

        //move the finger down to the element or starting position
        sequence.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.fromElement(originElement), 0, 0));

        //tap the element
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        //some pause
        sequence.addAction(new Pause(finger, Duration.ofMillis(800)));

        //move to the element
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(800),
                PointerInput.Origin.fromElement(destinyElement), 0, 0));

        //move the finger up of the screen
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));
    }

    public void pressBack() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void pressHome() {
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    public void doubleClick(WebElement element) {
        actions.moveToElement(element);
        actions.click();
        actions.pause(Duration.ofMillis(150));
        actions.click();
        actions.perform();
        //1:51:33
    }

    public void longTap(WebElement element, int duration) {
        actions.moveToElement(element);
        actions.clickAndHold(element);
        actions.pause(Duration.ofSeconds(duration));
        actions.release();
        actions.perform();
    }

    public void tap(WebElement element) {
        actions.clickAndHold(element);
        actions.pause(Duration.ofMillis(500));
        actions.release();
        actions.perform();
    }

    private void swipe(Point originPoint, Point destinyPoint, int secondsPauseMillis, int secondsMoveMillis) {
        Logs.debug("Swiping from %s to %s", originPoint, destinyPoint);

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final var sequence = new Sequence(finger, 1);

        //move the finger down to the element or starting position
        sequence.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), originPoint));

        //tap the element
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        if (secondsPauseMillis != 0) {
            //wait to make it long press
            sequence.addAction(new Pause(finger, Duration.ofMillis(secondsPauseMillis)));
        }

        //move to the element
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(secondsMoveMillis),
                PointerInput.Origin.viewport(), destinyPoint));

        //move the finger up of the screen
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));
    }

    private Point getPointUsingPercentages(int percentageX, int percentageY) {
        final var size = driver.manage().window().getSize();

        final var x = (int) (size.width * (percentageX / 100.0)); //transform from % to number according to the width
        final var y = (int) (size.height * (percentageY / 100.0)); //transform from % to number according to the height

        return new Point(x, y);
    }
}
