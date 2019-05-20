package com.sample.tests.testng;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.utils.SystemUtils;
import com.sample.tests.pages.DestinationLookupPage;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

public class SampleTestNGTest {
    public SearchPage searchPage;
    
    @BeforeClass(alwaysRun = true)
    public void beforeSuite() throws IOException {
        Configuration.load();
        if (Configuration.platform().isAndroidNative()) {
            SystemUtils.uninstallApp(Configuration.get("appPackage"));
        }
    }
    
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
	    Configuration.load();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, Configuration.get("browser"));
        cap.setCapability("platformVersion", Configuration.get("platformVersion"));
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", new File(Configuration.get("app_path")).getAbsolutePath());
        //cap.setCapability("udid", Configuration.get("udid"));
        cap.setCapability("deviceName", Configuration.get("deviceName"));
        cap.setCapability("commandTimeout", Configuration.get("commandTimeout"));
        cap.setCapability("appActivity", Configuration.get("appActivity"));
        cap.setCapability("appPackage", Configuration.get("appPackage"));
        cap.setCapability("appWaitActivity", Configuration.get("appActivity"));
        cap.setCapability("appWaitPackage", Configuration.get("appPackage"));
        cap.setCapability("noReset", true);
        cap.setCapability("fullReset", false);
        cap.setCapability("dontStopAppOnReset", true);
        if (Configuration.platform().isAndroidNative()) {
            SystemUtils.setSystemTime();
            SystemUtils.resetAppData();
        }
        WebDriver driver = Driver.init(Configuration.get("driver_url"), Configuration.platform(), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.add(driver);
        if (Configuration.platform().isWeb()) {
                Driver.current().get(Configuration.get("url"));
        }
        if (Configuration.platform().isWeb()) {
            searchPage = PageFactory.init(Driver.current(), SearchPage.class);
        } else {
            LandingPage landingPage = PageFactory.init(Driver.current(), LandingPage.class);
            searchPage = landingPage.buttonStartSearch.click(SearchPage.class);
        }
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		Driver.current().quit();
	}

	@DataProvider(name = "source")
	public static Object[][] getParameters() {
        return new Object[][] {
        		{"London", true },
        		{"Manchester", false},
        };
    }
	
	@Test(dataProvider="source")
	public void testSample(String destination, boolean isBusiness) throws Exception {
	       
        Assert.assertTrue(searchPage.allElementsExist(
                new Control[] {
                        searchPage.editDestination,
                        searchPage.dateCheckin,
                        searchPage.dateCheckout,
                        searchPage.buttonSearch
                }));
        Assert.assertTrue(searchPage.anyOfElementsExist(
                new Control[] {
                        searchPage.editDestination,
                        searchPage.dateCheckin,
                        searchPage.dateCheckout,
                        searchPage.buttonSearch
                }));
        searchPage.editDestination.setText(destination);
        
        searchPage.buttonTodaysDate.click();

        long checkin = Long.parseLong(searchPage.dateCheckin.getValue());
        long checkout = Long.parseLong(searchPage.dateCheckout.getValue());
        Assert.assertEquals(checkout - checkin, 24 * 60 * 60 * 1000);
        if (isBusiness) {
            searchPage.radioBusiness.click();
        } else {
            searchPage.radioLeisure.click();
        }
        SearchResultsPage searchResultsPage = searchPage.buttonSearch.click(SearchResultsPage.class);
        String actualTitle = searchResultsPage.textSubTitle.getText();
        Assert.assertEquals(actualTitle, destination);
        //searchResultsPage.scrollTo(scrollToText);
        //searchResultsPage.textParkPlaza.click();
		searchResultsPage.captureScreenShot("./build/sample-" + destination + "-testng.png");
	}
}
