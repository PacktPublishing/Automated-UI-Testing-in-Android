package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Driver;
import com.sample.framework.Platform;
import com.sample.framework.ui.Alias;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.DateLabel;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.controls.LocationLookupEdit;

@Alias("Search")
public class SearchPage extends Page {
    @Alias("Destination")
	@FindBy(locator = "com.booking:id/search_searchInput", platform = Platform.ANDROID_NATIVE)
	@FindBy(locator = "ss")
	public LocationLookupEdit editDestination;
	@FindBy(locator = "com.booking:id/search_checkinDate", platform = Platform.ANDROID_NATIVE, format = "EEE, MMMM dd")
	public DateLabel dateCheckin;
    @FindBy(locator = "com.booking:id/search_checkoutDate", platform = Platform.ANDROID_NATIVE, format = "EEE, MMMM dd")
    public DateLabel dateCheckout;
    @Alias("Today's Date")
	@FindBy(locator = "//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]")
	@FindBy(locator = "xpath=(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]",
		platform = Platform.ANDROID_NATIVE, excludeFromSearch = true)
	public Control buttonTodaysDate;
    @Alias("Business")
	@FindBy(locator = "com.booking:id/business_purpose_business", platform = Platform.ANDROID_NATIVE)
	@FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[2]")
	public Control radioBusiness;
    @Alias("Leisure")
	@FindBy(locator = "com.booking:id/business_purpose_leisure", platform = Platform.ANDROID_NATIVE)
	@FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[1]")
	public Control radioLeisure;
    @Alias("Search")
    @FindBy(locator = "com.booking:id/search_search", platform = Platform.ANDROID_NATIVE)
	@FindBy(locator = "//button[@type='submit']")
	public Control buttonSearch;
	public SearchPage(WebDriver driverValue) {
		super(driverValue);
	}
}
