package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Formatter;

public class Logs {
    public static Logger logs = LogManager.getLogger("Automation");

    private static final String bigSeparator =
            "------------------------------------------------------------------------------------------";
    private static final String smallSeparator = "**********************************************************";


    private static void printTestSeparator() {
        logs.info(bigSeparator);
    }

    public static void printSeparator() {
        logs.info(smallSeparator);
    }

    private static void printSeparatorDebug() {
        logs.debug(smallSeparator);
    }

    private static void printNewLine() {
        logs.info("");
    }

    public static void startTest(String testName) {
        printTestSeparator();
        logs.info("Test: " + testName);
        printTestSeparator();
    }

    public static void endTest(String status) {
        printTestSeparator();
        logs.info(status);
        printTestSeparator();
        printNewLine();
        printNewLine();
    }

    public static void startSuite(String suiteName) {
        printNewLine();
        printTestSeparator();
        printTestSeparator();
        logs.info("Suite: " + suiteName);
        printTestSeparator();
        printTestSeparator();
        printNewLine();
    }

    public static void preconditionStart() {
        printSeparator();
        logs.info("Preconditions");
        printSeparator();
    }

    public static void postConditionStart() {
        printSeparatorDebug();
        logs.debug("Post conditions");
        printSeparatorDebug();
    }

    public static void postConditionFinish() {
        printSeparatorDebug();
    }

    public static void testSteps() {
        printSeparator();
        logs.info("Test steps:");
        printSeparator();
    }

    public static void info(String message) {
        logs.info(message);
    }

    public static void warn(String message) {
        logs.warn(message);
    }

    public static void error(String message) {
        logs.error(message);
    }

    public static void debug(String message) {
        logs.debug(message);
    }

    public static void info(String format, Object... args) {
        logs.info(new Formatter().format(format, args).toString());
    }

    public static void warn(String format, Object... args) {
        logs.warn(new Formatter().format(format, args).toString());
    }

    public static void error(String format, Object... args) {
        logs.error(new Formatter().format(format, args).toString());
    }

    public static void debug(String format, Object... args) {
        logs.debug(new Formatter().format(format, args).toString());
    }
}
