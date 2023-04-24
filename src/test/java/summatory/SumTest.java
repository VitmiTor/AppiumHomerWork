package summatory;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import page.MainPage;

public class SumTest extends BaseTest {

    MainPage mainPage;

    @Test
    public void sumNumber() {
        mainPage.waitPageToLoad();
        mainPage.sumNumbers(233, 44, "277");
    }

    @Test
    public void multiplyingNumbers() {
        mainPage.waitPageToLoad();
        mainPage.anotherMultiplier(122.34, 23.1);
    }

    @Test
    public void verifyingNumber() {
        mainPage.waitPageToLoad();
        mainPage.verifyingRandomNumber();
    }

    @Override
    public void initPages(AndroidDriver driver) {
        mainPage = new MainPage(driver);
    }
}
