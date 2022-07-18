package com.example;

import java.lang.management.ManagementFactory;

import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        var procs = Runtime.getRuntime().availableProcessors();
        System.out.printf("Available processors: %s \n", procs);

        System.out.printf("UseParallelGC: %s\n", getHotSpotVmOptionValue("UseParallelGC"));
        System.out.printf("UseSerialGC: %s\n", getHotSpotVmOptionValue("UseSerialGC"));
        System.out.printf("UseG1GC: %s\n", getHotSpotVmOptionValue("UseG1GC"));
    }

    private static String getHotSpotVmOptionValue(String name) {
        try {
            var server = ManagementFactory.getPlatformMBeanServer();
            var beanName = ObjectName.getInstance("com.sun.management:type=HotSpotDiagnostic");
            var vmOption = server.invoke(beanName, "getVMOption", new Object[] {name}, new String[] {"java.lang.String"});
            return (String)((CompositeData)vmOption).get("value");
        } catch (Throwable t) {
            return null;
        }
    }
}
