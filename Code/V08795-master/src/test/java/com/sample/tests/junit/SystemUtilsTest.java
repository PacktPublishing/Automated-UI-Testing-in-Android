package com.sample.tests.junit;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.sample.framework.utils.SystemUtils;

public class SystemUtilsTest {

    //@Test
    public void testErrorLog() throws Exception {
        File output = File.createTempFile("test", "errorlog");
        output.deleteOnExit();
        SystemUtils.dumpErrorLog(output);
        System.out.println(FileUtils.readFileToString(output));
    }
    @Test
    public void testDisplayActivities() throws Exception {
        List<String> lines = SystemUtils.getDisplayActivities();
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
