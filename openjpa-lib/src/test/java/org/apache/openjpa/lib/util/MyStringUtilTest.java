package org.apache.openjpa.lib.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class MyStringUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(MyStringUtilTest.class);
    private static final String envFlag = System.getenv("flag");

    private static Stream<Arguments> splitTestArguments() {
        logger.info(String.format("env: %s", envFlag));
        List<TestState> availableTestState = new ArrayList<>();
        List<Arguments> activeArguments = new ArrayList<>();

        availableTestState.add(new TestState(
                "#01: token without regex special characters",
                StrState.LAST_TOKEN_IN_BETWEEN_LESS_EQUAL_THAN_MAX_BL,
                TokenState.WITHOUT_REGEX_SPECIAL_CHARS,
                MaxState.LESS_THAN_ZERO,
                ExpectedState.OPERATION,
                true
        ));


        availableTestState.add(new TestState(
                "#02: token is null",
                StrState.LAST_TOKEN_IN_BETWEEN_LESS_EQUAL_THAN_MAX_BL,
                TokenState.NULL,
                MaxState.LESS_THAN_ZERO,
                ExpectedState.ILLEGAL_ARGUMENT_EXCEPTION,
                true
        ));

        availableTestState.add(new TestState(
                "#03: token is empty",
                StrState.LAST_TOKEN_IN_BETWEEN_LESS_EQUAL_THAN_MAX_BL,
                TokenState.LENGTH_EQUAL_AS_ZERO,
                MaxState.LESS_THAN_ZERO,
                ExpectedState.ILLEGAL_ARGUMENT_EXCEPTION,
                true
        ));

        availableTestState.add(new TestState(
                "#04: max is negative and token at the end",
                StrState.LAST_TOKEN_AT_END_GREATER_THAN_MAX_EG,
                TokenState.WITH_REGEX_SPECIAL_CHARS,
                MaxState.LESS_THAN_ZERO,
                ExpectedState.OPERATION,
                true
        ));

        availableTestState.add(new TestState(
                "#05: max is 0 and token at the end",
                StrState.LAST_TOKEN_AT_END_GREATER_THAN_MAX_EG,
                TokenState.WITH_REGEX_SPECIAL_CHARS,
                MaxState.EQUAL_AS_ZERO,
                ExpectedState.OPERATION,
                true
        ));

        availableTestState.add(new TestState(
                "#06: segment are more than positive max and token at the end",
                StrState.LAST_TOKEN_AT_END_GREATER_THAN_MAX_EG,
                TokenState.WITH_REGEX_SPECIAL_CHARS,
                MaxState.GREATER_THAN_ZERO,
                ExpectedState.OPERATION,
                true
        ));

        availableTestState.add(new TestState(
                "#07: segment are less than positive max and token at the end",
                StrState.LAST_TOKEN_AT_END_LESS_EQUAL_THAN_MAX_EL,
                TokenState.WITH_REGEX_SPECIAL_CHARS,
                MaxState.GREATER_THAN_ZERO,
                ExpectedState.OPERATION,
                true
        ));

        availableTestState.add(new TestState(
                "#08: segment are more than positive max and token in between",
                StrState.LAST_TOKEN_IN_BETWEEN_GREATER_THAN_MAX_BG,
                TokenState.WITH_REGEX_SPECIAL_CHARS,
                MaxState.GREATER_THAN_ZERO,
                ExpectedState.OPERATION,
                true
        ));

        availableTestState.add(new TestState(
                "#09: str is null",
                StrState.NULL,
                TokenState.WITH_REGEX_SPECIAL_CHARS,
                MaxState.LESS_THAN_ZERO,
                ExpectedState.OPERATION,
                true
        ));

        availableTestState.add(new TestState(
                "#10: str is empty",
                StrState.LENGTH_EQUAL_AS_ZERO,
                TokenState.WITH_REGEX_SPECIAL_CHARS,
                MaxState.LESS_THAN_ZERO,
                ExpectedState.OPERATION,
                true
        ));

        availableTestState.add(new TestState(
                "#11: str does not contain token",
                StrState.NULL,
                TokenState.WITH_REGEX_SPECIAL_CHARS,
                MaxState.LESS_THAN_ZERO,
                ExpectedState.OPERATION,
                true
        ));


        for (TestState state : availableTestState) {
            if (!state.successful)
                if (("pitest".equals(envFlag) || "onlySuccess".equals(envFlag)))
                    continue;
            activeArguments.add(Arguments.of(state));
        }

        return activeArguments.stream();
    }

    @ParameterizedTest
    @MethodSource("splitTestArguments")
    @Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void splitTest(TestState testState) {
        logger.info(testState.description);

        MyStringUtilConfigurer.setup(testState);

        String debugMsg = String.format(
                "str: %s, token: %s, max: %d, expected: %s",
                testState.str == null ? "null" : testState.str,
                testState.token == null ? "null" : testState.str,
                testState.max,
                testState.expectedArray == null ? "null" : Arrays.toString(testState.expectedArray)
        );
        logger.info(debugMsg);

        switch (testState.expectedState) {
            case ILLEGAL_ARGUMENT_EXCEPTION:
                Assertions.assertThrows(
                        IllegalArgumentException.class,
                        () -> StringUtil.split(testState.str, testState.token, testState.max));
                break;
            case OPERATION:
                Assertions.assertArrayEquals(
                        testState.expectedArray,
                        StringUtil.split(testState.str, testState.token, testState.max)
                );
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + testState.expectedState);
        }
    }

    public enum StrState {
        NULL, LENGTH_EQUAL_AS_ZERO, WITHOUT_TOKEN,
        LAST_TOKEN_IN_BETWEEN_LESS_EQUAL_THAN_MAX_BL,
        LAST_TOKEN_AT_END_LESS_EQUAL_THAN_MAX_EL,
        LAST_TOKEN_IN_BETWEEN_GREATER_THAN_MAX_BG,
        LAST_TOKEN_AT_END_GREATER_THAN_MAX_EG
    }

    public enum TokenState {
        NULL, LENGTH_EQUAL_AS_ZERO, WITH_REGEX_SPECIAL_CHARS, WITHOUT_REGEX_SPECIAL_CHARS
    }

    public enum MaxState {
        LESS_THAN_ZERO, EQUAL_AS_ZERO, GREATER_THAN_ZERO
    }


    public enum ExpectedState {
        ILLEGAL_ARGUMENT_EXCEPTION, OPERATION
    }

    public static class TestState {
        String description;
        StrState strState;
        TokenState tokenState;
        MaxState maxState;
        ExpectedState expectedState;
        boolean successful;

        String str;
        String token;
        int max;

        String[] expectedArray;

        public TestState(String description, StrState strState, TokenState tokenState, MaxState maxState,
                         ExpectedState expectedState, boolean successful) {
            this.description = description;
            this.strState = strState;
            this.tokenState = tokenState;
            this.maxState = maxState;
            this.expectedState = expectedState;
            this.successful = successful;
        }
    }

}
