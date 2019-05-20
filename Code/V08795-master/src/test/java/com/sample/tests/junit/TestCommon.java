package com.sample.tests.junit;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.utils.SystemUtils;
import com.sample.tests.helpers.AppHelper;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;

public class TestCommon {
    public SearchPage searchPage;
    public static Process process;

	public TestCommon() {
		// TODO Auto-generated constructor stub
	}
    @BeforeClass
    public static void beforeSuite() throws Exception {
        AppHelper.uninstallApp();
        process = SystemUtils.startProcessMetricsCommand(new File("output.txt"));
    }
    public void setUp(boolean reset) throws Exception {
        this.searchPage = AppHelper.startApp(reset);
    }
	@Before
	public void setUp() throws Exception {
		setUp(true);
	}
	@After
	public void tearDown() {
		AppHelper.stopApp();
		TestCommon.process.destroy();
	}

}
