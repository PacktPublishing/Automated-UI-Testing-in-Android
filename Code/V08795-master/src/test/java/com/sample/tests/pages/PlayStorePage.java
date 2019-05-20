package com.sample.tests.pages;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Configuration;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.utils.SystemUtils;


public class PlayStorePage extends Page {

    public PlayStorePage(WebDriver driverValue) {
        super(driverValue);
        // TODO Auto-generated constructor stub
    }

    @FindBy(locator = "//android.widget.ImageView[contains(@resource-id, 'id/title_thumbnail')]")
    public Control iconTitle;

    @FindBy(locator = "//*[contains(@text, 'INSTALL')]", excludeFromSearch = true)
    public Control buttonInstall;

    @FindBy(locator = "//*[contains(@text, 'UNINSTALL')]", excludeFromSearch = true)
    public Control buttonUninstall;

    @FindBy(locator = "//*[@text='ACCEPT']", excludeFromSearch = true)
    public Control buttonAccept;

    @FindBy(locator = "//*[@text='OPEN']", excludeFromSearch = true)
    public Control buttonOpen;

    @Override
    public Page navigate() {
        SystemUtils.openDeepLink("market://details?id=" + Configuration.get("appPackage"));
        return this;
    }
    public LandingPage installApp() throws Exception {
        this.navigate();
        Control ok = this.getTextControl("OK");
        if (ok.exists(3)) {
            ok.click();
        }
        this.buttonInstall.click();
        if (buttonAccept.exists(1)) {
            this.buttonAccept.click();
        }
        Assert.assertTrue("Installation didn't complete within specified timeout",
                this.buttonOpen.exists(Configuration.timeout() * 2));
        LandingPage landingPage = this.buttonOpen.click(LandingPage.class);
        return landingPage;
    }
}
