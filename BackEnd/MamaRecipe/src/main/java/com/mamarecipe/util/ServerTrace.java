package com.mamarecipe.util;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class ServerTrace {
    private ServerTrace(){}
    public static void log(String className, String tag, String info){
        System.out.println("Class: " + className);
        System.out.println("["+tag+"]"+" : "+info);
    }
}
