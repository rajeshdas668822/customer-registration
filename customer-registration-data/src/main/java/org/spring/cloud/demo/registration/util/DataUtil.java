package org.spring.cloud.demo.registration.util;

import java.util.StringJoiner;

public class DataUtil {

    public static String getRefByType(String type){
        return new StringJoiner("").add(type+System.currentTimeMillis()).toString();
    }
}
