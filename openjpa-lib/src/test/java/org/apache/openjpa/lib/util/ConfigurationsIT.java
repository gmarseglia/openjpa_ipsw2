package org.apache.openjpa.lib.util;

import org.apache.openjpa.lib.conf.Configurations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class ConfigurationsIT {

    private static final Logger logger = LoggerFactory.getLogger(MyOptionsTest.class);
    private static final String envFlag = System.getenv("flag");

    private static Stream<Arguments> configureInstanceITArguments() {
        logger.info(String.format("env: %s", envFlag));
        List<TestState> availableTestState = new ArrayList<>();
        List<Arguments> activeArguments = new ArrayList<>();

        availableTestState.add(new TestState(
                "#01: Runtime exception thrown",
                A1_EXCEPTION.YES,
                null,
                null,
                null,
                EXPECTED.RUNTIME_EXCEPTION,
                false
        ));

        availableTestState.add(new TestState(
                "#02: Too many unset properties",
                A1_EXCEPTION.NO,
                A2_NUMBER_OF_SETTABLE_PROPERTIES.LESS_THAN_PROPERTIES_SIZE_MINUS_1,
                null,
                null,
                EXPECTED.NO_ACTIVATION,
                false
        ));

        availableTestState.add(new TestState(
                "#03: All properties are set",
                A1_EXCEPTION.NO,
                A2_NUMBER_OF_SETTABLE_PROPERTIES.EQUAL_AS_PROPERTIES_SIZE,
                null,
                null,
                EXPECTED.NO_ACTIVATION,
                false
        ));

        availableTestState.add(new TestState(
                "#04: 1 unset property, but obj is too deep",
                A1_EXCEPTION.NO,
                A2_NUMBER_OF_SETTABLE_PROPERTIES.EQUAL_AS_PROPERTIES_SIZE_MINUS_1,
                A3_OBJ_DEPTH.GREATER_THAN_ZERO,
                null,
                EXPECTED.NO_ACTIVATION,
                false
        ));

        availableTestState.add(new TestState(
                "#05: 1 unset property, but no close setter found",
                A1_EXCEPTION.NO,
                A2_NUMBER_OF_SETTABLE_PROPERTIES.EQUAL_AS_PROPERTIES_SIZE_MINUS_1,
                A3_OBJ_DEPTH.ZERO,
                A4_NEAR_MISS_SETTER_PRESENT.NO,
                EXPECTED.ACTIVATION_NOT_FOUND,
                false
        ));

        availableTestState.add(new TestState(
                "#06: 1 unset property, close setter found",
                A1_EXCEPTION.NO,
                A2_NUMBER_OF_SETTABLE_PROPERTIES.EQUAL_AS_PROPERTIES_SIZE_MINUS_1,
                A3_OBJ_DEPTH.ZERO,
                A4_NEAR_MISS_SETTER_PRESENT.YES,
                EXPECTED.ACTIVATION_FOUND,
                false
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
    @MethodSource("configureInstanceITArguments")
    public void configureInstanceIT(TestState testState) throws Throwable {
        logger.info(testState.description);

        ConfigurationsITConfigurer configurer = new ConfigurationsITConfigurer(testState);
        configurer.configure();

        Executable sutMethod = () -> {
            Configurations.configureInstance(
                    testState.obj,
                    null,
                    testState.properties,
                    "test properties");
        };

        if (testState.expected == EXPECTED.RUNTIME_EXCEPTION) {
            Assertions.assertThrows(RuntimeException.class, sutMethod);
            return;
        }

        sutMethod.execute();
    }


    public enum A1_EXCEPTION {
        NO, YES
    }

    public enum A2_NUMBER_OF_SETTABLE_PROPERTIES {
        LESS_THAN_PROPERTIES_SIZE_MINUS_1, EQUAL_AS_PROPERTIES_SIZE_MINUS_1,
        EQUAL_AS_PROPERTIES_SIZE
    }

    public enum A3_OBJ_DEPTH {
        ZERO, GREATER_THAN_ZERO
    }

    public enum A4_NEAR_MISS_SETTER_PRESENT {
        NO, YES
    }

    public enum EXPECTED {
        RUNTIME_EXCEPTION, NO_ACTIVATION, ACTIVATION_NOT_FOUND, ACTIVATION_FOUND
    }

    public static class TestState {
        String description;
        A1_EXCEPTION a1;
        A2_NUMBER_OF_SETTABLE_PROPERTIES a2;
        A3_OBJ_DEPTH a3;
        A4_NEAR_MISS_SETTER_PRESENT a4;
        EXPECTED expected;
        boolean successful;

        Object obj;
        Properties properties;

        public TestState(String description, A1_EXCEPTION a1, A2_NUMBER_OF_SETTABLE_PROPERTIES a2, A3_OBJ_DEPTH a3,
                         A4_NEAR_MISS_SETTER_PRESENT a4, EXPECTED expected, boolean successful) {
            this.description = description;
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
            this.a4 = a4;
            this.expected = expected;
            this.successful = successful;
        }
    }
}
