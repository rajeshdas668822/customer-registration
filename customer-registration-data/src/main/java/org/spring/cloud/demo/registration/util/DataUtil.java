package org.spring.cloud.demo.registration.util;

import java.util.StringJoiner;

public class DataUtil {

    public static String getRefByType(String type){
        long time = System.nanoTime();
        return new StringJoiner("").add(type+time).toString();
    }
}
