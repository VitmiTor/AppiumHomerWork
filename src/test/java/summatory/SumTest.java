package summatory;

import base.BaseTest;
import data.DataGiver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import page.MainPage;

public class SumTest extends BaseTest {

    @Test
    public void sumNumber() {
        final var numbers = DataGiver.getSumNumbers();
        mainPage.sumNumbers(numbers.getNumber1(), numbers.getNumber2(), numbers.getResult());
    }

    @Test
    public void multiplyingNumbers() {
        mainPage.anotherMultiplier(123.45, 26.4);
    }

    @Test
    public void verifyingNumber() {
        mainPage.verifyingRandomNumber();
    }

    @Override
    public void initPages(AndroidDriver driver) {
        mainPage = new MainPage(driver);
    }
}
