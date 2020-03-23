package com.demo.Utils;

public class TimeUtil {
    public static long getRunTime(long start){
        long end = System.currentTimeMillis();
        long time = end-start;
        return time;
    }
}
