package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.Alias;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.ScrollTo;
import com.sample.framework.ui.controls.Control;

@Alias("Search Results")
public class SearchResultsPage extends Page {

    @Alias("Title")
	@FindBy(locator = "com.booking:id/subtitle_layout_text")
	public Control textSubTitle;
	@FindBy(locator = "//*[contains(@text, 'Park Plaza')]",
	        scrollTo = "Park Plaza",
	        scrollDirection = ScrollTo.TOP_BOTTOM,
	        excludeFromSearch = true)
	public Control textParkPlaza;
	public SearchResultsPage(WebDriver driverValue) {
		super(driverValue);
	}

}
