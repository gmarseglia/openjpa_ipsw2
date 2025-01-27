package org.apache.openjpa.lib.util;

public class MyStringUtilConfigurer {

    public void setup (MyStringUtilTest.TestState testState){

        String token;
        switch (testState.tokenState){
            case NULL:
                token = null;
                break;
            case LENGTH_EQUAL_AS_ZERO:
                token = "";
                break;
            case LENGTH_GREATER_THAN_ZERO:
                token = ".";
                break;
                
        }
    }
}
