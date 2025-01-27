package org.apache.openjpa.lib.util;

public class MyStringUtilConfigurer {

    public static final String SUBSTRING_1 = "one";
    public static final String SUBSTRING_2 = "two";
    public static final String SUBSTRING_3 = "three";

    public static void setup(MyStringUtilTest.TestState testState) {

        /* Set up token */
        String token;
        switch (testState.tokenState) {
            case NULL:
                token = null;
                break;
            case LENGTH_EQUAL_AS_ZERO:
                token = "";
                break;
            case LENGTH_GREATER_THAN_ZERO:
                token = ".";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + testState.tokenState);
        }
        testState.token = token;

        /* Set up max */
        int max;
        switch (testState.maxState) {
            case LESS_THAN_ZERO:
                max = -1;
                break;
            case EQUAL_AS_ZERO:
                max = 0;
                break;
            case GREATER_THAN_ZERO:
                max = 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + testState.maxState);
        }
        testState.max = max;

        /* Set up str */
        String str;
        switch (testState.strState) {
            case NULL:
                str = null;
                break;
            case LENGTH_EQUAL_AS_ZERO:
                str = "";
                break;
            case WITHOUT_TOKEN:
                str = SUBSTRING_1;
                break;
            case LAST_TOKEN_IN_BETWEEN_LESS_EQUAL_THAN_MAX_BL:
                str = SUBSTRING_1 + token + SUBSTRING_2;
                break;
            case LAST_TOKEN_AT_END_LESS_EQUAL_THAN_MAX_EL:
                str = SUBSTRING_1 + token;
                break;
            case LAST_TOKEN_IN_BETWEEN_GREATER_THAN_MAX_BG:
                str = SUBSTRING_1 + token + SUBSTRING_2 + token + SUBSTRING_3;
                break;
            case LAST_TOKEN_AT_END_GREATER_THAN_MAX_EG:
                str = SUBSTRING_1 + token + SUBSTRING_2 + token;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + testState.strState);
        }
        testState.str = str;

        /* Set up expectedArray */
        String[] expectedArray;
        if (str == null || str.isEmpty()) {
            expectedArray = new String[0];
        } else {
            // #TODO
            throw new IllegalStateException("expectedArray NOT IMPLEMENTED YET!");
        }
        testState.expectedArray = expectedArray;
    }


}
