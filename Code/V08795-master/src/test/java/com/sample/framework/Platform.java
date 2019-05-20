package com.sample.framework;

import io.appium.java_client.service.local.flags.AndroidServerFlag;

import java.util.HashSet;

public enum Platform {
    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("ie"),
    SAFARI("safari"),
    OPERA("opera"),
    ANDROID_NATIVE("android_native"),
    ANDROID_WEB("android_web"),
    IOS_NATIVE("ios_native"),
    ANY("any");
    private String value;
    
    Platform(String param) {
        this.value = param;
    }
    public String getValue() {
        return value;
    }
    public boolean isAndroidNative() {
        return this.equals(ANDROID_NATIVE);
    }
    public boolean isIOSNative() {
        return this.equals(IOS_NATIVE);
    }
    public boolean isMobile() {
        return new HashSet<Platform>() {
            private static final long serialVersionUID = 1L;

            {
                add(ANDROID_NATIVE);
                add(ANDROID_WEB);
                add(IOS_NATIVE);
            }
        }.contains(this);
    }
    public boolean isWeb() {
        return new HashSet<Platform>() {
            private static final long serialVersionUID = 1L;

            {
                add(CHROME);
                add(FIREFOX);
                add(IE);
                add(SAFARI);
                add(OPERA);
                add(ANDROID_WEB);
                add(ANY);
            }
        }.contains(this);
    }
    public static Platform fromString(String input) {
        for (Platform platform : Platform.values()) {
            if (platform.getValue().equals(input)) {
                return platform;
            }
        }
        return null;
    }
}
