package options;

import base.BaseTest;
import elements.$;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import page.HistoryPage;
import page.MainPage;
import page.ThemePage;

public class OptionsTest extends BaseTest {

    private MainPage mainPage;
    private ThemePage themePage;
    private HistoryPage historyPage;

    @Test
    public void changeToDarkTheme() {
        mainPage.waitPageToLoad();
        mainPage.clickOnOptions();
        mainPage.clickOnThemeButton();

        themePage.waitPageToLoad();
        themePage.clickOnDarkTheme();

        mainPage.waitPageToLoad();
        mainPage.clickOnOptions();
        mainPage.clickOnThemeButton();

        themePage.waitPageToLoad();
        themePage.verifyIsSelected();
    }

    @Test
    public void changeToRad() {
        mainPage.waitPageToLoad();
        mainPage.changeToRAD();
    }

    @Test
    public void goToHistory() {
        mainPage.waitPageToLoad();
        mainPage.historyButton();

        historyPage.waitPageToLoad();
        historyPage.clickOnBack();
        mainPage.waitPageToLoad();
    }

    @Test
    public void swipeToHistory() {
        mainPage.waitPageToLoad();
        mainPage.swipe(15, 70, $.Orientation.VERTICAL);
        historyPage.waitPageToLoad();
        mainPage.swipe(70, 15, $.Orientation.VERTICAL);
        mainPage.waitPageToLoad();
    }

    @Override
    public void initPages(AndroidDriver driver) {
        mainPage = new MainPage(driver);
        themePage = new ThemePage(driver);
        historyPage = new HistoryPage(driver);
    }
}
