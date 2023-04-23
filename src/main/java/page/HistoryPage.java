package page;

import base.BasePage;
import elements.$;
import io.appium.java_client.android.AndroidDriver;

import static elements.$.LocatorType.ACCESIBILITY_ID;
import static elements.$.LocatorType.ID;

public class HistoryPage extends BasePage {

    private $ historyPage = $(ID, "com.google.android.calculator:id/history_recycler_view");
    private $ backButton = $(ACCESIBILITY_ID, "Navigate up");

    public HistoryPage(AndroidDriver driver) {
        super(driver);
    }

    public HistoryPage(AndroidDriver driver, int timeout) {
        super(driver, timeout);
    }

    @Override
    public void waitPageToLoad() {
        waitPage(historyPage, this.getClass().getName());
    }

    @Override
    public void verifyPage() {
        softAssert.assertTrue(historyPage.isDisplayed());
        softAssert.assertAll();
    }

    public void clickOnBack() {
        backButton.click();
    }
}
