package com.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {        
        var procs = Runtime.getRuntime().availableProcessors();
        System.out.printf("Available processors: %s \n", procs);
    }
}
