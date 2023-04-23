package page;

import base.BasePage;
import com.github.javafaker.Faker;
import elements.$;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import utilities.Logs;

import static elements.$.LocatorType.*;

public class MainPage extends BasePage {

    private final $ optionsInput = $(ACCESIBILITY_ID, "More options");
    private final $ chooseButton = $(UIAUTOMATOR2, "text(\"Choose theme\")");
    private final $ degOption = $(ACCESIBILITY_ID, "degree mode");
    private final $ radOption = $(ACCESIBILITY_ID, "radian mode");
    private final $ equals = $(ID, "com.google.android.calculator:id/eq");
    private final $ multiply = $(ID, "com.google.android.calculator:id/op_mul");
    private final $ numeric = $(ID, "com.google.android.calculator:id/formula");
    private final $ results = $(ID, "com.google.android.calculator:id/result_final");
    private final $ point = $(ID, "com.google.android.calculator:id/dec_point");
    private final $ historyOption = $(UIAUTOMATOR2, "text(\"History\")");
    private final $ formula = $(ID, "com.google.android.calculator:id/formula");

    private final $ numbers(int a) {
        return $(ID, String.format("com.google.android.calculator:id/digit_%d", a));
    }

    private final $ numbers(String a) {
        return $(ID, String.format("com.google.android.calculator:id/digit_%s", a));
    }

    private final $ addSymbol = $(ID, "com.google.android.calculator:id/op_add");

    public MainPage(AndroidDriver driver) {
        super(driver);
    }

    public MainPage(AndroidDriver driver, int timeout) {
        super(driver, timeout);
    }

    @Override
    public void waitPageToLoad() {
        waitPage(optionsInput, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {

    }

    public void clickOnOptions() {
        optionsInput.click();
    }

    public void clickOnThemeButton() {
        chooseButton.waitforVisibility();
        chooseButton.click();
    }

    public void changeToRAD() {
        Logs.info("Changing to radiant");
        degOption.click();
        Logs.info("Verifying if it changes To rad");
        Assert.assertEquals(radOption.getText(), "RAD");
    }

    public void changeToDEG() {
        Logs.info("Changing to DEGREES");
        radOption.click();
        Logs.info("Verifying if it changes To degrees");
        Assert.assertEquals(degOption.getText(), "DEG");
    }

    public void sumNumbers(int digitA, int digitB, int digitC, int digitD, String result) {
        numbers(digitA).click();
        numbers(digitB).click();
        addSymbol.click();
        numbers(digitC).click();
        numbers(digitD).click();
        equals.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException interruptedException) {
            interruptedException.getLocalizedMessage();
        }
        Assert.assertEquals(results.getText(), result);
    }

    public void multiplyNumber() throws InterruptedException {
        var faker = new Faker();
        var a = faker.number().randomDouble(2, 50, 325);
        Logs.debug(String.valueOf(a));
        var b = faker.number().randomDouble(2, 50, 325);
        Logs.debug(String.valueOf(b));


        numeric.sendKeys(a + "*" + b);
        equals.findElement();
        equals.waitforVisibility(5).click();
        Thread.sleep(1000);
        var c = a * b;
        Logs.debug(String.valueOf(c));
        Logs.debug(numeric.getText());
        Thread.sleep(1000);
        Assert.assertEquals(numeric.getText(), String.valueOf(c));
    }

    public void anotherMultiplier() {
        numbers(4).click();
        numbers(5).click();
        point.click();
        numbers(2).click();
        numbers(2).click();
        multiply.click();
        numbers(7).click();
        numbers(2).click();
        equals.click();
    }

    public void historyButton() {
        optionsInput.click();
        historyOption.click();
    }

    public void verifyingRandomNumber() {
        var faker = new Faker();

        var number = faker.number().numberBetween(3000, 25000);
        var stringNumber = String.valueOf(number);

        char[] eachNumber = stringNumber.toCharArray();
        Logs.debug(String.valueOf(eachNumber));
        for (char i : eachNumber) {
            Logs.debug(String.valueOf(i));
            numbers(String.valueOf(i)).click();
        }
        Logs.debug("getting text" + formula.getText());
        Assert.assertEquals(formula.getText(), stringNumber);
    }
}
