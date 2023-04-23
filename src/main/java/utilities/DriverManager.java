package utilities;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static AndroidDriver driver;

    public AndroidDriver buildRemoteDriver() {
        Logs.debug("Building Remote driver");
        //TO-Do
        return null;
    }

    public AndroidDriver builLocalDriver() {
        try {
            Logs.debug("Building local driver");
            final var fileAPK = new File("src/main/resources/apk/calculator.apk");
            //final var fileAPK = new File("src/main/resources/apk/saucelabs.apk");
            final var appiumUrl = "http://localhost:4723/wd/hub";

            final var desiredCapabilities = new DesiredCapabilities();

            desiredCapabilities.setCapability("appium:ChromeOptions", ImmutableMap.of("w3c", false));
            desiredCapabilities.setCapability("autoGrantPermissions", true);
            desiredCapabilities.setCapability("appWaitActivity", "com.google.android.calculator,com.android.calculator2.Calculator");
            //desiredCapabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "mobile_emulator");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, fileAPK.getAbsolutePath());

            final var driver = new AndroidDriver(new URL(appiumUrl), desiredCapabilities);
            DriverManager.driver = driver;
            return driver;
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
            Logs.error("failed building local driver");
            return null;
        }
    }
}
