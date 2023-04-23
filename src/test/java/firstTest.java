import base.BaseTest;
import elements.$;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import page.HistoryPage;
import page.MainPage;
import page.ThemePage;

public class firstTest extends BaseTest {

    private MainPage mainPage;
    private ThemePage themePage;
    private HistoryPage historyPage;

    @Test
    public void test1() {
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
    public void test2() {
        mainPage.waitPageToLoad();
        mainPage.changeToRAD();
    }

    @Test
    public void test3() {
        mainPage.waitPageToLoad();
        mainPage.sumNumbers(1, 2, 4, 5, "57");
    }


    @Test
    public void test4() throws InterruptedException {
        mainPage.waitPageToLoad();
        //mainPage.multiplyNumber();
        mainPage.anotherMultiplier();
    }

    @Test
    public void test5() {
        mainPage.waitPageToLoad();
        mainPage.historyButton();

        historyPage.waitPageToLoad();
        historyPage.clickOnBack();
        mainPage.waitPageToLoad();
    }

    @Test
    public void test6() {
        mainPage.waitPageToLoad();
        mainPage.swipe(15, 70, $.Orientation.VERTICAL);
        historyPage.waitPageToLoad();
        mainPage.swipe(70, 15, $.Orientation.VERTICAL);
        mainPage.waitPageToLoad();
    }

    @Test
    public void test7() {
        mainPage.waitPageToLoad();
        mainPage.verifyingRandomNumber();
    }

    @Override
    public void initPages(AndroidDriver driver) {
        mainPage = new MainPage(driver);
        themePage = new ThemePage(driver);
        historyPage = new HistoryPage(driver);
    }
}
