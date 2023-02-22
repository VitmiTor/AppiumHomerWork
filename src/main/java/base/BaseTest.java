package base;

import io.appium.java_client.android.AndroidDriver;
import listeners.InvokeMethodListeners;
import listeners.SuiteListeners;
import listeners.TestListeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utilities.DriverManager;
import utilities.Logs;

@Listeners({TestListeners.class, SuiteListeners.class, InvokeMethodListeners.class})
public abstract class BaseTest {
    private final boolean runOnServer = System.getenv("JOB_NAME") != null;
    protected final String regression = "Regression";
    protected final String smoke = "Smoke";
    protected final String setup = "Setup";
    private AndroidDriver driver;
    //protected DataGiver dataGiver = new DataGiver();

    @BeforeMethod(alwaysRun = true, description = "Master setup")
    public void setupDriver() {
        final var driverManager = new DriverManager();
        if (runOnServer) {
            driver = driverManager.buildRemoteDriver();
        } else {
            driver = driverManager.builLocalDriver();
        }
        initPages(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Master TearDown")
    public void tearDownDriver() {
        Logs.debug("killing the driver");
        driver.quit();
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    protected void triggerDeepLink(String url) {
        driver.get(url);
    }

    public abstract void initPages(AndroidDriver driver);
}
