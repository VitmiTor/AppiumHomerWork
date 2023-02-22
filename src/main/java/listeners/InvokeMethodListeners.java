package listeners;

import base.BaseListeners;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import utilities.Logs;

public class InvokeMethodListeners extends BaseListeners implements IInvokedMethodListener {
    private final String preConditionsMethodName = "setupDriver";
    private final String postConditionMethodName = "tearDownDriver";

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.getTestResult().getName().equals(preConditionsMethodName)) {
            Logs.startTest(testResult.getInstanceName());
            Logs.preconditionStart();
        }
        if (method.getTestResult().getName().equals(postConditionMethodName)) {
            Logs.postConditionStart();
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.getTestResult().getName().equals(postConditionMethodName)) {
            Logs.postConditionFinish();
        }
    }
}
