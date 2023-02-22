package listeners;

import base.BaseListeners;
import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Logs;

public class TestListeners extends BaseListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Logs.testSteps();
        setDriver(result);
    }

    private WebDriver getDriverFromResult(ITestResult result) {
        var currentClass = result.getInstance();
        return ((BaseTest) currentClass).getDriver();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.endTest("Success");
        printSuccess(result.getInstanceName(), result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.endTest("Fail");
        fileManager.getScreenshot(driver, result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.endTest("Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}