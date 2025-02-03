package org.apache.openjpa.lib.util;

import java.util.ArrayList;
import java.util.List;

public class MyOptionsMethodTracker {
    public static List<String> methodCallList = new ArrayList<>();

    public static void addMethod(String newString) {
        methodCallList.add(newString);
    }

    public static boolean verify(String methodName) {
        return methodCallList.contains(methodName);
    }
}
