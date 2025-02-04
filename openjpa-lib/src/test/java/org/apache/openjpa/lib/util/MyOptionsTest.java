package org.apache.openjpa.lib.util;

import org.apache.openjpa.lib.util.MyOptionsEnums.*;
import org.apache.openjpa.lib.util.MyOptionsObjects.AnyDeepInterface;
import org.apache.openjpa.lib.util.MyOptionsObjects.DeepestInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.apache.openjpa.lib.util.MyOptionsMethodTracker.verify;

public class MyOptionsTest {

    private static final Logger logger = LoggerFactory.getLogger(MyOptionsTest.class);
    private static final String envFlag = System.getenv("flag");

    private static Stream<Arguments> splitTestArguments() {
        logger.info(String.format("env: %s", envFlag));
        List<TestState> availableTestState = new ArrayList<>();
        List<Arguments> activeArguments = new ArrayList<>();

        availableTestState.add(new TestState(
                "#02: Multiple shallow properties using SUT and defaults",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A2_1_depth.DEPTH_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.PRIMITIVE,
                A2_4_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_SAME_VALUES,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A2_1_depth.DEPTH_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.STRING,
                A2_4_SUT_or_defaults.ONLY_IN_DEFAULTS,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_PUBLIC)
        )));

        availableTestState.add(new TestState(
                "#03: Multiple deep properties, with getter",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                B1_intermediate_getter.WITH_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITH_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NON_NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A2_1_depth.DEPTH_GREATER_THAN_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.PRIMITIVE,
                A2_4_SUT_or_defaults.BOTH_SUT_AND_DEFAULTS,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_SAME_VALUES,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_GETTER, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A2_1_depth.DEPTH_GREATER_THAN_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.SPECIAL_CLASS,
                A2_4_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null, null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_GETTER, ExpectedFlags.FINAL_PUBLIC)
        )));

        availableTestState.add(new TestState(
                "#04: Multiple deep properties, with getter returning null",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                B1_intermediate_getter.WITH_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITH_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A2_1_depth.DEPTH_GREATER_THAN_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.PRIMITIVE,
                A2_4_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_SAME_VALUES,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_SETTER_GETTER, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A2_1_depth.DEPTH_GREATER_THAN_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.PRIMITIVE,
                A2_4_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null, null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_SETTER_GETTER, ExpectedFlags.FINAL_PUBLIC)
        )));

        availableTestState.add(new TestState(
                "#05: Multiple deep properties, w/o getter and with setter",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITH_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NON_NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                false
        ).addProperty(new PropertyState(
                "1",
                A2_1_depth.DEPTH_GREATER_THAN_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.PRIMITIVE,
                A2_4_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_SAME_VALUES,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_SETTER, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A2_1_depth.DEPTH_GREATER_THAN_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.PRIMITIVE,
                A2_4_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null, null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_SETTER, ExpectedFlags.FINAL_PUBLIC)
        )));

        availableTestState.add(new TestState(
                "#06: Multiple deep properties, w/o getter and w/o setter and null attributes",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITHOUT_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITH_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NULL_LAST_INSTANCE,
                false
        ).addProperty(new PropertyState(
                "1",
                A2_1_depth.DEPTH_GREATER_THAN_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.PRIMITIVE,
                A2_4_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_SAME_VALUES,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_PUBLIC, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A2_1_depth.DEPTH_GREATER_THAN_ZERO,
                A2_2_number_of_values.ONE_VALUE,
                A2_3_type_of_values.PRIMITIVE,
                A2_4_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null, null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_PUBLIC, ExpectedFlags.FINAL_PUBLIC)
        )));

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
    public void setIntoTest(TestState testState) {
        logger.info(testState.description);

        MyOptionsConfigurer configurer = new MyOptionsConfigurer();
        configurer.setup(testState);

        logger.info("setup done");

        Options unsetOptions = testState.SUT.setInto(testState.obj);

        logger.info("setInto done");

        List<String> calledMethods = MyOptionsMethodTracker.getMethods();

        logger.info(String.valueOf(calledMethods));


        DeepestInterface deepestObject = ((AnyDeepInterface) testState.obj).intermediateGetDeepest();
        for (PropertyState property : testState.properties) {
            String expectedStr = property.value;
            Object expected;
            Object actual;

            if (property.expectedSet.contains(ExpectedFlags.SET)) {
                String setMethodName;
                switch (property.a23) {
                    case PRIMITIVE:
                        expected = new Integer(expectedStr);
                        actual = deepestObject.deepestPrimitiveAttribute(property.id);
                        setMethodName = "setPrimitiveAttribute" + property.id;
                        break;
                    case STRING:
                        expected = expectedStr;
                        actual = deepestObject.deepestStringAttribute(property.id);
                        setMethodName = "setStringAttribute" + property.id;
                        break;
                    case SPECIAL_CLASS:
                        expected = new SpecialClass(expectedStr);
                        actual = deepestObject.deepestSpecialClassAttribute1(property.id);
                        setMethodName = "setSpecialClassAttribute" + property.id;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + property.a23);
                }

                /* Assert the correct value */
                Assertions.assertEquals(expected, actual, String.format("property.id: %s", property.id));

                /* Assert the correct way is used to proceed towards the deepest object */
                if (property.expectedSet.contains(ExpectedFlags.VIA_GETTER)) {
                    Assertions.assertFalse(verify("new IntermediateInterface"));
                    Assertions.assertTrue(verify("getDeeper"));
                    Assertions.assertFalse(verify("setDeeper"));
                    Assertions.assertTrue(verify("getDeepest"));
                    Assertions.assertFalse(verify("setDeepest"));
                } else if (property.expectedSet.contains(ExpectedFlags.VIA_NEW_SETTER_GETTER)) {
                    Assertions.assertTrue(verify("new IntermediateInterface"));
                    Assertions.assertTrue(verify("getDeeper"));
                    Assertions.assertTrue(verify("setDeeper"));
                    Assertions.assertTrue(verify("getDeepest"));
                    Assertions.assertTrue(verify("setDeepest"));
                } else if (property.expectedSet.contains(ExpectedFlags.VIA_NEW_SETTER)) {
                    Assertions.assertTrue(verify("new IntermediateInterface"));
                    Assertions.assertFalse(verify("getDeeper"));
                    Assertions.assertTrue(verify("setDeeper"));
                    Assertions.assertFalse(verify("getDeepest"));
                    Assertions.assertTrue(verify("setDeepest"));
                } else if (property.expectedSet.contains(ExpectedFlags.VIA_NEW_PUBLIC)) {
                    Assertions.assertTrue(verify("new IntermediateInterface"));
                    Assertions.assertFalse(verify("getDeeper"));
                    Assertions.assertFalse(verify("setDeeper"));
                    Assertions.assertFalse(verify("getDeepest"));
                    Assertions.assertFalse(verify("setDeepest"));
                }

                /* Assert the correct final way has been used to set the attribute */
                if (property.expectedSet.contains(ExpectedFlags.FINAL_SETTER)) {
                    Assertions.assertTrue(verify(setMethodName), String.format("method '%s' was not called", setMethodName));
                } else if (property.expectedSet.contains(ExpectedFlags.FINAL_PUBLIC)) {
                    Assertions.assertFalse(verify(setMethodName), String.format("method '%s' was called", setMethodName));
                }
            }
        }
    }

    public static class PropertyState {
        String id;
        A2_1_depth a21;
        A2_2_number_of_values a22;
        A2_3_type_of_values a23;
        A2_4_SUT_or_defaults a24;
        B5_1_deepest_setter b51;
        B5_2_number_of_parameter_of_deepest_setter b52;
        B5_3_parsable_for_setter b53;
        B5_4_deepest_public_attribute b54;
        B5_5_parsable_for_public_attribute b55;
        Set<ExpectedFlags> expectedSet;

        String key;
        int numberOfValues;
        String typeOfValues;
        String value;

        public PropertyState(
                String id, A2_1_depth a21, A2_2_number_of_values a22, A2_3_type_of_values a23, A2_4_SUT_or_defaults a24,
                B5_1_deepest_setter b51, B5_2_number_of_parameter_of_deepest_setter b52, B5_3_parsable_for_setter b53,
                B5_4_deepest_public_attribute b54, B5_5_parsable_for_public_attribute b55, Set<ExpectedFlags> expectedSet) {
            this.id = id;
            this.a21 = a21;
            this.a22 = a22;
            this.a23 = a23;
            this.a24 = a24;
            this.b51 = b51;
            this.b52 = b52;
            this.b53 = b53;
            this.b54 = b54;
            this.b55 = b55;
            this.expectedSet = expectedSet;
        }
    }

    public static class TestState {
        String description;
        A1_Number_of_properties a1;
        B1_intermediate_getter b1;
        B2_intermediate_setter b2;
        B3_intermediate_public_attributes b3;
        B4_intermediate_javabean_constructor b4;
        C1_intermediate_instances_are_null c1;
        C2_last_instance_is_null c2;
        ArrayList<PropertyState> properties = new ArrayList<>();
        boolean successful;

        Options SUT;
        Object obj;

        public TestState(
                String description, A1_Number_of_properties a1, B1_intermediate_getter b1, B2_intermediate_setter b2,
                B3_intermediate_public_attributes b3, B4_intermediate_javabean_constructor b4,
                C1_intermediate_instances_are_null c1, C2_last_instance_is_null c2, boolean successful) {
            this.description = description;
            this.a1 = a1;
            this.b1 = b1;
            this.b2 = b2;
            this.b3 = b3;
            this.b4 = b4;
            this.c1 = c1;
            this.c2 = c2;
            this.successful = successful;
        }

        public TestState addProperty(PropertyState property) {
            this.properties.add(property);
            return this;
        }
    }
}
