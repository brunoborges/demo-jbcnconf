package com.example;

import java.lang.management.ManagementFactory;
import java.util.Arrays;

import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        // Processor
        var procs = Runtime.getRuntime().availableProcessors();
        System.out.printf("JVM Processor Count: %s \n", procs);

        // Memory
        final int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.printf("JVM Heap Size: " + runtime.maxMemory() / mb + "MB \n");

        // Garbage Collector (ergonomics only)
        Arrays.asList("UseSerialGC", "UseG1GC").forEach(this::printVmOption);
    }

    public void printVmOption(String name) {
        System.out.printf("%s: %s\n", name, getHotSpotVmOptionValue(name));
    }

    private static String getHotSpotVmOptionValue(String name) {
        try {
            var server = ManagementFactory.getPlatformMBeanServer();
            var beanName = ObjectName.getInstance("com.sun.management:type=HotSpotDiagnostic");
            var vmOption = server.invoke(beanName, "getVMOption", new Object[] { name },
                    new String[] { "java.lang.String" });
            return (String) ((CompositeData) vmOption).get("value");
        } catch (Throwable t) {
            return null;
        }
    }

}
