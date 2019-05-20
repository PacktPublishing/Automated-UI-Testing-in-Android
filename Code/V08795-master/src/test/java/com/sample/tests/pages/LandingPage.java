package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

public class LandingPage extends Page {

	@FindBy(locator = "com.booking:id/btn_start_search")
	public Control buttonStartSearch;
	
	public LandingPage(WebDriver driverValue) {
		super(driverValue);
	}

}
