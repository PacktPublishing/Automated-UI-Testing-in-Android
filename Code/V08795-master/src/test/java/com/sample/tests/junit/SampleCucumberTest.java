package com.sample.tests.junit;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.sample.framework.utils.SystemUtils;
import com.sample.tests.helpers.AppHelper;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "./src/test/java/com/sample/tests/features" },
        glue = { "com/sample/tests/steps" },
        plugin = {
        "json:build/cucumber.json", "html:build/cucumber-html-report",
        "pretty:build/cucumber-pretty.txt",
        "usage:build/cucumber-usage.json",
        "junit:build/cucumber-junit-results.xml" }, tags = {}
)
public class SampleCucumberTest {
    private static Process process;

    @BeforeClass
    public static void beforeSuite() throws Exception {
        AppHelper.uninstallApp();
        process = SystemUtils.startProcessMetricsCommand(new File("output.txt"));
    }
    @AfterClass
    public static void afterSuite() throws Exception {
        process.destroy();
    }
}
