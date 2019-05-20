package com.sample.framework.ui.controls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

import com.sample.framework.ui.Page;

public class DateLabel extends Control {

    public DateLabel(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    @Override
    public String getValue() {
        String val = super.getText();

        SimpleDateFormat format = new SimpleDateFormat(getFormat());

        Date date;
        try {
            date = format.parse(val);
        } catch (ParseException e) {
            return "0";
        }
        return String.valueOf(date.getTime());
    }
}
