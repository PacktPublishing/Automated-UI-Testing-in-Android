package com.sample.framework.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sample.framework.Platform;

@Target(ElementType.FIELD)
@Repeatable(FindByList.class)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FindBy {
    String locator();
    Platform platform() default Platform.ANY;
    String itemLocator() default "";
    String scrollTo() default "";
    ScrollTo scrollDirection() default ScrollTo.TOP_BOTTOM;
    String format() default "";
    boolean excludeFromSearch() default false;
}
