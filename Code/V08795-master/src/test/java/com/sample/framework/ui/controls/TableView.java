package com.sample.framework.ui.controls;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.By;

import com.sample.framework.ui.Page;
import com.sample.framework.ui.SubItem;

public class TableView extends Control {

    public TableView(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    protected String getFullItemLocator() {
        return String.format("%s%s", this.getLocatorText(), this.getItemLocatorText());
    }
    public int getItemsCount() {
        return this.getDriver().findElements(By.xpath(getFullItemLocator())).size();
    }
    public Control getItem(int index) {
        String locator = String.format("(%s)[%d]", this.getFullItemLocator(), index + 1);
        return new Control(this.getParent(), By.xpath(locator));
    }
    public boolean isNotEmpty(long timeout) {
        return this.getItem(0).exists(timeout);
    }
    public boolean isNotEmpty() {
        return isNotEmpty(TIMEOUT);
    }
    public By getSubItemLocator(String name, int index) {
        SubItem item = this.getSubItemsMap().get(name);
        String locator = String.format("(%s)[%d]%s", this.getFullItemLocator(), index + 1, item.locator());
        return By.xpath(locator);
    }
    public <T extends Control> T getSubItem(String name, int index, Class<T> itemType) throws Exception {
        T element = itemType.getConstructor(Page.class, By.class).
                newInstance(this.getParent(), getSubItemLocator(name, index));
        return element;
    }
    public Control getSubItem(String name, int index) throws Exception {
        SubItem item = this.getSubItemsMap().get(name);
        return getSubItem(name, index, item.controlType());
    }
}
