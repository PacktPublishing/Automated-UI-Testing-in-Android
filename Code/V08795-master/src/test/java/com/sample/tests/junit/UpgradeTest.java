package com.sample.tests.junit;

import org.junit.Test;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.utils.SystemUtils;
import com.sample.tests.pages.PlayStorePage;

public class UpgradeTest extends TestCommon {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        SystemUtils.uninstallApp(Configuration.get("appPackage"));
        PlayStorePage playStore = PageFactory.init(Driver.current(), PlayStorePage.class);
        playStore.installApp();
    }

    @Test
    public void testUpgradedStart() throws Exception {
        Driver.current().quit();
        SystemUtils.updateApp(Configuration.get("appPackage"));
        super.setUp(false);
        searchPage.editDestination.setText("Leeds");
    }
}
