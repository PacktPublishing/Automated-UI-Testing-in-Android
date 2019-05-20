package com.sample.tests.controls;

import org.openqa.selenium.By;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.pages.DestinationLookupPage;

public class LocationLookupEdit extends Edit {

    public LocationLookupEdit(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    @Override
    public void setText(String value) throws Exception {
        this.click();
        if (Configuration.platform().isWeb()) {
            this.element().clear();
            this.element().sendKeys(value);
            Control lookupItem = new Control(this.getParent(),
                    By.xpath("(//li[contains(@class, 'autocomplete__item')])[1]"));
            lookupItem.click();
        } else {
        		DestinationLookupPage search = PageFactory.init(this.getDriver(), DestinationLookupPage.class);
            search.editDestinationInput.setText(value);
            Thread.sleep(3000);
            search.itemDestinationResult.element(0).click();
        }
    }

    
}
