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

import static org.apache.openjpa.lib.util.MyOptionsMethodTracker.containsMethod;

public class MyOptionsTest {

    private static final Logger logger = LoggerFactory.getLogger(MyOptionsTest.class);
    private static final String envFlag = System.getenv("flag");

    private static Stream<Arguments> splitTestArguments() {
        logger.info(String.format("env: %s", envFlag));
        List<TestState> availableTestState = new ArrayList<>();
        List<Arguments> activeArguments = new ArrayList<>();

        availableTestState.add(new TestState(
                "#01: No properties",
                A1_Number_of_properties.ZERO_PROPERTIES,
                null,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ));

        availableTestState.add(new TestState(
                "#02: Multiple shallow properties using SUT and defaults",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.STRING,
                A3_3_SUT_or_defaults.ONLY_IN_DEFAULTS,
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
                A2_depth.DEPTH_GREATER_THAN_ZERO,
                B1_intermediate_getter.WITH_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITH_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NON_NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.BOTH_SUT_AND_DEFAULTS,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_GETTER, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.SPECIAL_CLASS,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_GETTER, ExpectedFlags.FINAL_PUBLIC)
        )));

        availableTestState.add(new TestState(
                "#04: Multiple deep properties, with getter returning null",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_GREATER_THAN_ZERO,
                B1_intermediate_getter.WITH_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITH_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_SETTER_GETTER, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_SETTER_GETTER, ExpectedFlags.FINAL_PUBLIC)
        )));

        availableTestState.add(new TestState(
                "#05: Multiple deep properties, w/o getter and with setter",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_GREATER_THAN_ZERO,
                B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITH_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NON_NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                false
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_SETTER, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_SETTER, ExpectedFlags.FINAL_PUBLIC, ExpectedFlags.OVERWRITTEN)
        )));

        availableTestState.add(new TestState(
                "#06: Multiple deep properties, w/o getter and w/o setter and null attributes",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_GREATER_THAN_ZERO,
                B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITHOUT_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITH_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_PUBLIC, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_NEW_PUBLIC, ExpectedFlags.FINAL_PUBLIC)
        )));

        availableTestState.add(new TestState(
                "#07: Multiple deep properties, w/o getter and w/o setter and non null attributes",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_GREATER_THAN_ZERO,
                B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITHOUT_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITH_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NON_NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_PUBLIC, ExpectedFlags.FINAL_SETTER)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.VIA_PUBLIC, ExpectedFlags.FINAL_PUBLIC)
        )));

        availableTestState.add(new TestState(
                "#08_a: Setter with one parameters, property with multiple parameter, primitive type",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_GREATER_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.THROWS_RUNTIME_EXCEPTION)
        )));

        availableTestState.add(new TestState(
                "#08_b: Setter with one parameters, property with multiple parameter, String and Class with constructor types",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_GREATER_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.STRING,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.NOT_SPLIT_STRINGS)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.VALUES_GREATER_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.SPECIAL_CLASS,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.NOT_SPLIT_STRINGS)
        )));

        availableTestState.add(new TestState(
                "#09_a: Without setters, property with multiple parameter, primitive type",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.MULTIPLE_VALUES,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.THROWS_RUNTIME_EXCEPTION)
        )));

        availableTestState.add(new TestState(
                "#09_b: Without setters, property with multiple parameter, String and Class with constructor type",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.MULTIPLE_VALUES,
                A3_2_type_of_values.STRING,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_PUBLIC, ExpectedFlags.NOT_SPLIT_STRINGS)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.MULTIPLE_VALUES,
                A3_2_type_of_values.SPECIAL_CLASS,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_PUBLIC, ExpectedFlags.NOT_SPLIT_STRINGS)
        )));

        availableTestState.add(new TestState(
                "#10: Setters with multiple values, properties has same values\n",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.USE_ALL)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.STRING,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.USE_ALL)
        )).addProperty(new PropertyState(
                "3",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.SPECIAL_CLASS,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.USE_ALL)
        )));

        availableTestState.add(new TestState(
                "#11_a: Setters with multiple values, properties has less values",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_LESS_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.USE_DEFAULT_IF_MISSING)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.VALUES_LESS_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.STRING,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.USE_DEFAULT_IF_MISSING)
        )).addProperty(new PropertyState(
                "3",
                A3_1_number_of_values.VALUES_LESS_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.SPECIAL_CLASS,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.USE_DEFAULT_IF_MISSING)
        )));

        availableTestState.add(new TestState(
                "#11_b: Setters with multiple values, properties has less values",
                A1_Number_of_properties.MULTIPLE_PROPERTIES,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                false
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.REPEAT_LAST)
        )).addProperty(new PropertyState(
                "2",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.STRING,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.REPEAT_LAST)
        )).addProperty(new PropertyState(
                "3",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.SPECIAL_CLASS,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.SET, ExpectedFlags.FINAL_SETTER, ExpectedFlags.REPEAT_LAST)
        )));

        availableTestState.add(new TestState(
                "#12: Single shallow property, not set",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.NOT_SET)
        )));

        availableTestState.add(new TestState(
                "#13: Single deep property, not set",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_GREATER_THAN_ZERO,
                B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER,
                B2_intermediate_setter.WITHOUT_INTERMEDIATE_SETTER,
                B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES,
                B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR,
                C1_intermediate_instances_are_null.NON_NULL_INTERMEDIATE_INSTANCES,
                C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.NOT_SET)
        )));

        availableTestState.add(new TestState(
                "#14_a: Setters with multiple values, properties has more values, primitive type",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_GREATER_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.THROWS_RUNTIME_EXCEPTION)
        )));

        availableTestState.add(new TestState(
                "#14_b: Setters with multiple values, properties has more values, String type",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_GREATER_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.STRING,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.THROWS_RUNTIME_EXCEPTION)
        )));

        availableTestState.add(new TestState(
                "#14_c: Setters with multiple values, properties has more values, Class with constructor type",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_GREATER_THAN_SETTER_PARAMETERS,
                A3_2_type_of_values.SPECIAL_CLASS,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS,
                B5_3_parsable_for_setter.PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.THROWS_RUNTIME_EXCEPTION)
        )));

        availableTestState.add(new TestState(
                "#15: Not parsable for setter",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.VALUES_EQUAL_AS_SETTER_PARAMETERS,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITH_DEEPEST_SETTER,
                B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER,
                B5_3_parsable_for_setter.NOT_PARSABLE_FOR_SETTER,
                B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE,
                null,
                EnumSet.of(ExpectedFlags.THROWS_RUNTIME_EXCEPTION)
        )));

        availableTestState.add(new TestState(
                "#16: Not parsable for public attribute",
                A1_Number_of_properties.ONE_PROPERTY,
                A2_depth.DEPTH_ZERO,
                null, null, null, null,
                null, C2_last_instance_is_null.NON_NULL_LAST_INSTANCE,
                true
        ).addProperty(new PropertyState(
                "1",
                A3_1_number_of_values.ONE_VALUE,
                A3_2_type_of_values.PRIMITIVE,
                A3_3_SUT_or_defaults.ONLY_IN_SUT,
                B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER,
                null,
                null,
                B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE,
                B5_5_parsable_for_public_attribute.NOT_PARSABLE_FOR_PUBLIC_ATTRIBUTE,
                EnumSet.of(ExpectedFlags.THROWS_RUNTIME_EXCEPTION)
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

        /* Assert the exception throw, if that's expected */
        if (testState.properties.size() == 1 &&
                testState.properties.get(0).expectedSet.contains(ExpectedFlags.THROWS_RUNTIME_EXCEPTION)) {
            Assertions.assertThrows(RuntimeException.class, () -> testState.SUT.setInto(testState.obj), "RuntimeException expected, but not thrown");
            return;
        }

        /* Otherwise call the setInto method */
        Options unsetOptions = testState.SUT.setInto(testState.obj);

        logger.info(String.format("setInto done, unsetOptions: %s", unsetOptions));

        List<String> calledMethods = MyOptionsMethodTracker.getMethods();

        logger.info(String.valueOf(calledMethods));

        /* If no properties, then simply assert that no options are present in unsetOptions */
        if (testState.properties.isEmpty()) {
            Assertions.assertEquals(0, unsetOptions.size());
            return;
        }

        DeepestInterface deepestObject = ((AnyDeepInterface) testState.obj).intermediateGetDeepest();
        for (PropertyState property : testState.properties) {
            String expectedStr = property.value;
            Object expected;
            Object actual;

            if (property.expectedSet.contains(ExpectedFlags.SET)) {
                String setMethodName;
                switch (property.a32) {
                    case PRIMITIVE:
                        actual = deepestObject.deepestPrimitiveAttribute(property.id);
                        setMethodName = "setPrimitiveAttribute" + property.id;
                        if (property.numberOfValues == 1 &&
                                (property.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER ||
                                        property.b54 == B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE)) {
                            expected = new Integer(expectedStr);
                            break;
                        } else {
                            if (property.expectedSet.contains(ExpectedFlags.USE_ALL) ||
                                    property.expectedSet.contains(ExpectedFlags.USE_DEFAULT_IF_MISSING)) {
                                int counter = 0;
                                for (String s : property.value.split(",")) {
                                    counter += new Integer(s);
                                }
                                expected = counter;
                                break;
                            } else if (property.expectedSet.contains(ExpectedFlags.REPEAT_LAST)) {
                                int counter = 0;
                                int last = 0;
                                for (String s : property.value.split(",")) {
                                    last = new Integer(s);
                                    counter += last;
                                }
                                for (int i = property.numberOfValues; i < property.numberOfParametersForSetter; i++) {
                                    counter += last;
                                }
                                expected = counter;
                                break;
                            }
                        }
                        throw new IllegalStateException("Unexpected MULTIPLE_VALUE option");
                    case STRING:
                        actual = deepestObject.deepestStringAttribute(property.id);
                        setMethodName = "setStringAttribute" + property.id;

                        if (property.numberOfValues == 1 &&
                                (property.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER ||
                                        property.b54 == B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE)) {
                            expected = expectedStr;
                            break;
                        } else {
                            if (property.expectedSet.contains(ExpectedFlags.NOT_SPLIT_STRINGS)) {
                                expected = expectedStr;
                                break;
                            } else if (property.expectedSet.contains(ExpectedFlags.USE_ALL)) {
                                expected = expectedStr.replace(",", "");
                                break;
                            } else if (property.expectedSet.contains(ExpectedFlags.USE_DEFAULT_IF_MISSING)) {
                                expected = expectedStr.replace(",", "");
                                for (int i = property.numberOfValues; i < property.numberOfParametersForSetter; i++) {
                                    expected += "null";
                                }
                                break;
                            } else if (property.expectedSet.contains(ExpectedFlags.REPEAT_LAST)) {
                                String last = expectedStr.split(",")[property.numberOfValues - 1];
                                expected = expectedStr.replace(",", "");
                                for (int i = property.numberOfValues; i < property.numberOfParametersForSetter; i++) {
                                    expected += last;
                                }
                                break;
                            }
                        }
                        throw new IllegalStateException("Unexpected MULTIPLE_VALUE option");
                    case SPECIAL_CLASS:
                        actual = deepestObject.deepestSpecialClassAttribute1(property.id);
                        setMethodName = "setSpecialClassAttribute" + property.id;
                        if (property.numberOfValues == 1 &&
                                (property.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER ||
                                        property.b54 == B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE)) {
                            expected = new SpecialClass(expectedStr);
                            break;
                        } else {
                            if (property.expectedSet.contains(ExpectedFlags.NOT_SPLIT_STRINGS)) {
                                expected = new SpecialClass(expectedStr);
                                break;
                            } else if (property.expectedSet.contains(ExpectedFlags.USE_ALL)) {
                                expected = new SpecialClass(expectedStr.replace(",", ""));
                                break;
                            } else if (property.expectedSet.contains(ExpectedFlags.USE_DEFAULT_IF_MISSING)) {
                                String baseStr = expectedStr.replace(",", "");
                                for (int i = property.numberOfValues; i < property.numberOfParametersForSetter; i++) {
                                    baseStr += "null";
                                }
                                expected = new SpecialClass(baseStr);
                                break;
                            } else if (property.expectedSet.contains(ExpectedFlags.REPEAT_LAST)) {
                                String last = expectedStr.split(",")[property.numberOfValues - 1];
                                String baseStr = expectedStr.replace(",", "");
                                for (int i = property.numberOfValues; i < property.numberOfParametersForSetter; i++) {
                                    baseStr += last;
                                }
                                expected = new SpecialClass(baseStr);
                                break;
                            }
                        }
                        throw new IllegalStateException("Unexpected MULTIPLE_VALUE option");
                    default:
                        throw new IllegalStateException("Unexpected value: " + property.a32);
                }

                /* Assert the correct value */
                if (!property.expectedSet.contains(ExpectedFlags.OVERWRITTEN))
                    Assertions.assertEquals(expected, actual, String.format("property.id: %s", property.id));

                /* Assert the correct way is used to proceed towards the deepest object */
                if (property.expectedSet.contains(ExpectedFlags.VIA_GETTER)) {
                    Assertions.assertFalse(containsMethod("new IntermediateInterface"), "new IntermediateInterface");
                    Assertions.assertTrue(containsMethod("getDeeper"), "getDeeper expected");
                    Assertions.assertFalse(containsMethod("setDeeper"), "setDeeper expected");
                    Assertions.assertTrue(containsMethod("getDeepest"), "getDeepest expected");
                    Assertions.assertFalse(containsMethod("setDeepest"), "setDeepest");
                } else if (property.expectedSet.contains(ExpectedFlags.VIA_NEW_SETTER_GETTER)) {
                    Assertions.assertTrue(containsMethod("new IntermediateInterface"), "new IntermediateInterface");
                    Assertions.assertTrue(containsMethod("getDeeper"), "getDeeper expected");
                    Assertions.assertTrue(containsMethod("setDeeper"), "setDeeper expected");
                    Assertions.assertTrue(containsMethod("getDeepest"), "getDeepest expected");
                    Assertions.assertTrue(containsMethod("setDeepest"), "setDeepest");
                } else if (property.expectedSet.contains(ExpectedFlags.VIA_NEW_SETTER)) {
                    Assertions.assertTrue(containsMethod("new IntermediateInterface"), "new IntermediateInterface");
                    Assertions.assertFalse(containsMethod("getDeeper"), "getDeeper expected");
                    Assertions.assertTrue(containsMethod("setDeeper"), "setDeeper expected");
                    Assertions.assertFalse(containsMethod("getDeepest"), "getDeepest expected");
                    Assertions.assertTrue(containsMethod("setDeepest"), "setDeepest");
                } else if (property.expectedSet.contains(ExpectedFlags.VIA_NEW_PUBLIC)) {
                    Assertions.assertTrue(containsMethod("new IntermediateInterface"), "new IntermediateInterface");
                    Assertions.assertFalse(containsMethod("getDeeper"), "getDeeper expected");
                    Assertions.assertFalse(containsMethod("setDeeper"), "setDeeper expected");
                    Assertions.assertFalse(containsMethod("getDeepest"), "getDeepest expected");
                    Assertions.assertFalse(containsMethod("setDeepest"), "setDeepest");
                } else if (property.expectedSet.contains(ExpectedFlags.VIA_PUBLIC)) {
                    Assertions.assertFalse(containsMethod("new IntermediateInterface"), "new IntermediateInterface");
                    Assertions.assertFalse(containsMethod("getDeeper"), "getDeeper expected");
                    Assertions.assertFalse(containsMethod("setDeeper"), "setDeeper expected");
                    Assertions.assertFalse(containsMethod("getDeepest"), "getDeepest expected");
                    Assertions.assertFalse(containsMethod("setDeepest"), "setDeepest");
                }

                /* Assert the correct final way has been used to set the attribute */
                if (property.expectedSet.contains(ExpectedFlags.FINAL_SETTER)) {
                    Assertions.assertTrue(containsMethod(setMethodName), String.format("method '%s' was not called", setMethodName));
                } else if (property.expectedSet.contains(ExpectedFlags.FINAL_PUBLIC)) {
                    Assertions.assertFalse(containsMethod(setMethodName), String.format("method '%s' was called", setMethodName));
                }
            } else if (property.expectedSet.contains(ExpectedFlags.NOT_SET)) {
                DeepestInterface deepest = ((AnyDeepInterface) testState.obj).intermediateGetDeepest();

                /* Assert the property is the unsetOptions returned object */
                Assertions.assertTrue(unsetOptions.containsKey(property.key));

                /* Assert the attribute of the deepest object has not been modified */
                Assertions.assertEquals(0, deepest.deepestPrimitiveAttribute(property.id));
                Assertions.assertNull(deepest.deepestStringAttribute(property.id));
                Assertions.assertNull(deepest.deepestSpecialClassAttribute1(property.id));
            }
        }
    }

    public static class PropertyState {
        String id;
        A3_1_number_of_values a31;
        A3_2_type_of_values a32;
        A3_3_SUT_or_defaults a33;
        B5_1_deepest_setter b51;
        B5_2_number_of_parameter_of_deepest_setter b52;
        B5_3_parsable_for_setter b53;
        B5_4_deepest_public_attribute b54;
        B5_5_parsable_for_public_attribute b55;
        Set<ExpectedFlags> expectedSet;

        String key;
        int numberOfValues;
        Integer numberOfParametersForSetter;
        String typeOfValues;
        String value;

        public PropertyState(
                String id, A3_1_number_of_values a31, A3_2_type_of_values a32, A3_3_SUT_or_defaults a33,
                B5_1_deepest_setter b51, B5_2_number_of_parameter_of_deepest_setter b52, B5_3_parsable_for_setter b53,
                B5_4_deepest_public_attribute b54, B5_5_parsable_for_public_attribute b55, Set<ExpectedFlags> expectedSet) {
            this.id = id;
            this.a31 = a31;
            this.a32 = a32;
            this.a33 = a33;
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
        A2_depth a2;
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
                String description, A1_Number_of_properties a1, A2_depth a2, B1_intermediate_getter b1,
                B2_intermediate_setter b2, B3_intermediate_public_attributes b3,
                B4_intermediate_javabean_constructor b4, C1_intermediate_instances_are_null c1,
                C2_last_instance_is_null c2, boolean successful) {
            this.description = description;
            this.a1 = a1;
            this.a2 = a2;
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
