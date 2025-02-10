package org.apache.openjpa.lib.util;

import org.apache.openjpa.lib.util.ConfigurationsIT.A1_EXCEPTION;
import org.apache.openjpa.lib.util.ConfigurationsIT.A2_NUMBER_OF_SETTABLE_PROPERTIES;
import org.apache.openjpa.lib.util.ConfigurationsIT.A3_OBJ_DEPTH;
import org.apache.openjpa.lib.util.ConfigurationsIT.TestState;
import org.apache.openjpa.lib.util.ConfigurationsITClasses.Deepest1;
import org.apache.openjpa.lib.util.ConfigurationsITClasses.Deepest123;
import org.apache.openjpa.lib.util.ConfigurationsITClasses.Intermediate12;

import java.util.Properties;

public class ConfigurationsITConfigurer {

    TestState testState;

    public ConfigurationsITConfigurer(TestState testState) {
        this.testState = testState;
    }

    public void configure() {
        testState.properties = new Properties();

        /* Configure the case in which the setInto should throw an exception */
        if (testState.a1 == A1_EXCEPTION.YES) {
            testState.obj = new Deepest1();
            testState.properties.setProperty("first", "1,2,3");
            return;
        }

        assert testState.a1 == A1_EXCEPTION.NO;

        if (testState.a3 == A3_OBJ_DEPTH.ZERO || testState.a3 == null) {
            testState.properties.setProperty("first", "1");
            testState.properties.setProperty("second", "2");
            testState.properties.setProperty("third", "3");
        } else if (testState.a3 == A3_OBJ_DEPTH.GREATER_THAN_ZERO) {
            testState.properties.setProperty("deepest.first", "1");
            testState.properties.setProperty("deepest.second", "2");
            testState.properties.setProperty("deepest.third", "3");
        }

        /* Configure the case in which too few properties are set */
        if (testState.a2 == A2_NUMBER_OF_SETTABLE_PROPERTIES.LESS_THAN_PROPERTIES_SIZE_MINUS_1) {
            testState.obj = new Deepest1();
            return;
        } else if (testState.a2 == A2_NUMBER_OF_SETTABLE_PROPERTIES.EQUAL_AS_PROPERTIES_SIZE) {
            testState.obj = new Deepest123();
            return;
        } else if (testState.a2 == A2_NUMBER_OF_SETTABLE_PROPERTIES.EQUAL_AS_PROPERTIES_SIZE_MINUS_1 &&
                testState.a3 == A3_OBJ_DEPTH.GREATER_THAN_ZERO) {
            testState.obj = new Intermediate12();
            return;
        }

        throw new IllegalStateException(String.format(
                "Unexpected combination of A1: %s, A2: %s, A3: %s, A4: %s",
                testState.a1,
                testState.a2,
                testState.a3,
                testState.a4));
    }
}
