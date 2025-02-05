package org.apache.openjpa.lib.util;

public class MyOptionsEnums {

    public enum A1_Number_of_properties {
        ZERO_PROPERTIES, ONE_PROPERTY, MULTIPLE_PROPERTIES
    }

    public enum A2_depth {
        DEPTH_ZERO, DEPTH_GREATER_THAN_ZERO
    }

    public enum A3_1_number_of_values {
        ONE_VALUE, MULTIPLE_VALUES,
        VALUES_LESS_THAN_SETTER_PARAMETERS, VALUES_EQUAL_AS_SETTER_PARAMETERS, VALUES_GREATER_THAN_SETTER_PARAMETERS
    }

    public enum A3_2_type_of_values {
        PRIMITIVE, STRING, SPECIAL_CLASS
    }

    public enum A3_3_SUT_or_defaults {
        ONLY_IN_SUT, ONLY_IN_DEFAULTS, BOTH_SUT_AND_DEFAULTS
    }

    public enum B1_intermediate_getter {
        WITH_INTERMEDIATE_GETTER, WITHOUT_INTERMEDIATE_GETTER
    }

    public enum B2_intermediate_setter {
        WITH_INTERMEDIATE_SETTER, WITHOUT_INTERMEDIATE_SETTER
    }

    public enum B3_intermediate_public_attributes {
        WITH_INTERMEDIATE_PUBLIC_ATTRIBUTES, WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES
    }

    public enum B4_intermediate_javabean_constructor {
        WITH_JAVABEAN_CONSTRUCTOR, WITHOUT_JAVABEAN_CONSTRUCTOR
    }

    public enum B5_1_deepest_setter {
        WITH_DEEPEST_SETTER, WITHOUT_DEEPEST_SETTER
    }

    public enum B5_2_number_of_parameter_of_deepest_setter {
        SETTER_WITH_ONE_PARAMETER, SETTER_WITH_MULTIPLE_PARAMETERS
    }

    public enum B5_3_parsable_for_setter {
        PARSABLE_FOR_SETTER, NOT_PARSABLE_FOR_SETTER
    }

    public enum B5_4_deepest_public_attribute {
        WITH_DEEPEST_PUBLIC_ATTRIBUTE, WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE
    }

    public enum B5_5_parsable_for_public_attribute {
        PARSABLE_FOR_PUBLIC_ATTRIBUTE, NOT_PARSABLE_FOR_PUBLIC_ATTRIBUTE
    }

    public enum C1_intermediate_instances_are_null {
        NULL_INTERMEDIATE_INSTANCES, NON_NULL_INTERMEDIATE_INSTANCES
    }

    public enum C2_last_instance_is_null {
        NULL_LAST_INSTANCE, NON_NULL_LAST_INSTANCE
    }

    public enum ExpectedFlags {
        SET,
        FINAL_SETTER, FINAL_PUBLIC,
        VIA_GETTER, VIA_NEW_SETTER_GETTER, VIA_NEW_SETTER, VIA_NEW_PUBLIC, VIA_PUBLIC,
        USE_ALL, REPEAT_LAST, USE_DEFAULT_IF_MISSING, NOT_SPLIT_STRINGS,
        OVERWRITTEN,
        NOT_SET,
        THROWS_RUNTIME_EXCEPTION
    }
}
