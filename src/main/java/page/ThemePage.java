package page;

import base.BasePage;
import elements.$;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import utilities.Logs;

import static elements.$.LocatorType.ID;
import static elements.$.LocatorType.UIAUTOMATOR2;

public class ThemePage extends BasePage {

    private final $ darkThemeBtn = $(UIAUTOMATOR2, "text(\"Dark\")");
    private final $ okButton = $(ID, "android:id/button1");

    public ThemePage(AndroidDriver driver) {
        super(driver);
    }

    public ThemePage(AndroidDriver driver, int timeout) {
        super(driver, timeout);
    }

    @Override
    public void waitPageToLoad() {
        waitPage(darkThemeBtn, this.getClass().getName());
    }

    @Override
    public void verifyPage() {

    }

    public void clickOnDarkTheme() {
        Logs.info("Clicking on darkMode theme");
        darkThemeBtn.click();
        Logs.info("clicking in ok button");
        okButton.click();
    }

    public void verifyIsSelected() {
        Logs.info("verigying if it is selected");
        Assert.assertTrue(darkThemeBtn.isEnable());
    }
}
