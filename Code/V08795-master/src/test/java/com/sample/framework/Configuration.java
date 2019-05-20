package com.sample.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public final class Configuration {

	private Configuration() {
	}

	private static Properties properties;
	public static void load() throws IOException {
		Configuration config = new Configuration();
		properties = new Properties();
		Enumeration<URL> enumerator = config.getClass().getClassLoader().getResources("/");
		while(enumerator.hasMoreElements()) {
			System.out.println(enumerator.nextElement());
		}
    	InputStream is = new FileInputStream(new File("config.properties"));
    	BufferedReader reader = new BufferedReader(
    	        new InputStreamReader(
    	                is,
    	                StandardCharsets.UTF_8));
    	try {
        	properties.load(reader);
    	} finally {
    		is.close();
    	    reader.close();
    	}
    	for (Entry<Object, Object> entry : System.getProperties().entrySet()) {
    	    properties.put((String) entry.getKey(), (String) entry.getValue());
        }
        for (Entry<String, String> entry : System.getenv().entrySet()) {
            properties.put((String) entry.getKey(), (String) entry.getValue());
        }
	}
	public static String get(String option) {
	    if (properties == null) {
	        try {
                load();
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
	    String prefix = "";
	    String value = "";
	    if (properties.containsKey("prefix")) {
	        prefix = (String) properties.get("prefix");
	    }
	    if (StringUtils.isNotBlank(prefix)) {
	        value = properties.getProperty(prefix + "_" + option);
	        if (value != null) {
	            return value;
	        }
	    }
	    value = properties.getProperty(option);
		if (value == null) {
			return "";
		}
		return value;
	}
	public static void print() {
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
		}
	}
	public static long timeout() {
	    String value = Configuration.get("timeout");
	    if (value == null || value.trim().equals("")) {
	        return 60L;
	    }
	    return Long.parseLong(value.trim());
	}
	public static Platform platform() {
	    return Platform.fromString(get("platform"));
	}
}
