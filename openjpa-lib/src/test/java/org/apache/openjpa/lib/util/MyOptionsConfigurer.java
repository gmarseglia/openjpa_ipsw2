package org.apache.openjpa.lib.util;

import org.apache.openjpa.lib.util.MyOptionsEnums.*;
import org.apache.openjpa.lib.util.MyOptionsObjects.*;
import org.apache.openjpa.lib.util.MyOptionsTest.PropertyState;
import org.apache.openjpa.lib.util.MyOptionsTest.TestState;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class MyOptionsConfigurer {

    public static final String PRIMITIVE_VALUE = "1";
    public static final String STRING_VALUE = "one";

    TestState testState;
    int maxDepth = 0;

    public void setup(TestState testState) {
        this.testState = testState;

        /* Clear the method tracker */
        MyOptionsMethodTracker.clearMethods();

        /* Determine maxDepth */
        findMaxDepth();

        /* Create Object */
        /* Use B1, B2, B3, B4, C1, C2 and B5.1, B5.2, B5.3, B5.4, B5.5 for each property */
        createObj();   // this also create the obj

        /* Setup A1 */
        setupA1();

        /* Set up A2.1, A2.2, A2.3 for each property */
        for (PropertyState property : testState.properties) {
            setupPropertyA21to23(property);
        }

        /* Create the SUT, using A2.4 for each property */
        createSUT();

        /* Set up  */
        for (PropertyState property : testState.properties) {
            setupExpected(property);
        }

    }

    private void findMaxDepth() {
        this.maxDepth = 0;
        if (testState.a2 != null) {
            switch (testState.a2) {
                case DEPTH_ZERO:
                    this.maxDepth = 0;
                    break;
                case DEPTH_GREATER_THAN_ZERO:
                    this.maxDepth = 2;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + testState.a2);
            }
        }
    }

    private void createSUT() {
        Options defaults = new Options();
        for (PropertyState property : testState.properties) {
            if (property.a33 == A3_3_SUT_or_defaults.BOTH_SUT_AND_DEFAULTS || property.a33 == A3_3_SUT_or_defaults.ONLY_IN_DEFAULTS) {
                defaults.setProperty(property.key, property.value);
            }
        }
        Options SUT = new Options(defaults);
        for (PropertyState property : testState.properties) {
            if (property.a33 == A3_3_SUT_or_defaults.BOTH_SUT_AND_DEFAULTS || property.a33 == A3_3_SUT_or_defaults.ONLY_IN_SUT) {
                SUT.setProperty(property.key, property.value);
            }
        }
        testState.SUT = SUT;
    }

    private void setupA1() {
        /* Check the A1 characteristics */
        switch (testState.a1) {
            case ZERO_PROPERTIES:
                assert testState.properties.isEmpty();
                break;
            case ONE_PROPERTY:
                assert testState.properties.size() == 1;
                break;
            case MULTIPLE_PROPERTIES:
                assert testState.properties.size() > 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + testState.a1);
        }
    }

    private void createObj() {
        /* Choose the class for the deepest object */
        Class deepestClass = getDeepestClass();
        DeepestInterface deepest;
        try {
            deepest = (DeepestInterface) deepestClass.getConstructor(boolean.class).newInstance(false);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        /* If the maxDepth is 0, then skip choosing intermediate class */
        if (this.maxDepth == 0) {
            if (testState.c2 == C2_last_instance_is_null.NON_NULL_LAST_INSTANCE)
                testState.obj = deepest;
            return;
        }

        /* Set up B1 characteristics */
        if (testState.b1 == null)
            testState.b1 = B1_intermediate_getter.WITH_INTERMEDIATE_GETTER;
        if (testState.b2 == null)
            testState.b2 = B2_intermediate_setter.WITH_INTERMEDIATE_SETTER;
        if (testState.b3 == null)
            testState.b3 = B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES;
        if (testState.b4 == null)
            testState.b4 = B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR;

        /* Choose the class for the intermediate object */
        Class intermediateClass = getIntermediateClass();

        if (intermediateClass == null)
            throw new IllegalStateException("#TODO: not implemented combination of B1, B2, B3, B4");

        IntermediateInterface top;
        IntermediateInterface middle;
        try {
            top = (IntermediateInterface) intermediateClass.getConstructor(boolean.class).newInstance(false);
            middle = (IntermediateInterface) intermediateClass.getConstructor(boolean.class).newInstance(false);
            testState.obj = top;
            if (testState.c1 == C1_intermediate_instances_are_null.NON_NULL_INTERMEDIATE_INSTANCES) {
                top.intermediateSetDeeper(middle);
                if (testState.c2 == C2_last_instance_is_null.NON_NULL_LAST_INSTANCE)
                    middle.intermediateSetDeepest(deepest);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Class getDeepestClass() {
        if (testState.properties.isEmpty()) {
            return DeepestObjectEmpty.class;
        } else if (testState.properties.size() == 1) {
            PropertyState p1 = testState.properties.get(0);

            if (p1.b51 == B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER &&
                    p1.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE
            ) {
                return DeepestObjectNo1.class;
            } else if (p1.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
                    p1.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER &&
                    p1.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
                    p1.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE
            ) {
                return DeepestObjectSetter1Public2.class;
            }
            // else if (p1.a32 == A3_2_type_of_values.PRIMITIVE &&
            //         p1.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
            //         p1.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_MORE_VALUES &&
            //         p1.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
            //         p1.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE
            // ) {
            //     return DeepestObjectType3MoreValues.class;
            // }

        } else if (testState.properties.size() == 2) {
            PropertyState p1 = testState.properties.get(0);
            PropertyState p2 = testState.properties.get(1);

            if (p1.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
                    p1.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER &&
                    p1.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
                    p1.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE &&
                    p2.b51 == B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER &&
                    p2.b54 == B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE &&
                    p2.b55 == B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE
            ) {
                return DeepestObjectSetter1Public2.class;
            } else if (p1.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
                    p1.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER &&
                    p1.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
                    p1.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE &&
                    p2.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
                    p2.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_ONE_PARAMETER &&
                    p2.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
                    p2.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE
            ) {
                return DeepestObjectSetter1Setter2.class;
            }


            // else if (p1.a31 == A3_1_number_of_values.MULTIPLE_VALUES &&
            //         p1.a32 == A3_2_type_of_values.STRING &&
            //         p1.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
            //         p1.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_MORE_VALUES &&
            //         p1.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
            //         p1.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE &&
            //         p2.a31 == A3_1_number_of_values.MULTIPLE_VALUES &&
            //         p2.a32 == A3_2_type_of_values.STRING &&
            //         p2.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
            //         p2.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_LESS_VALUES &&
            //         p2.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
            //         p2.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE
            // ) {
            //     return DeepestObjectType5.class;
            // }

        } else if (testState.properties.size() == 3) {
            PropertyState p1 = testState.properties.get(0);
            PropertyState p2 = testState.properties.get(1);
            PropertyState p3 = testState.properties.get(2);

            if (p1.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
                    p1.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS &&
                    p1.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
                    p1.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE &&
                    p2.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
                    p2.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS &&
                    p2.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
                    p2.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE &&
                    p3.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
                    p3.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_WITH_MULTIPLE_PARAMETERS &&
                    p3.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
                    p3.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE
            ) {
                return DeepestObjectMultiple1Multiple2Multiple3.class;
            }
        }

        throw new IllegalStateException("#TODO: not implemented combination of B5.1, B5.2, B5.3, B5.4, B5.5");
    }

    private Class getIntermediateClass() {
        /* This is the case YYNN */
        if (getDeepestClass() == DeepestObjectSetter1Public2.class &&
                testState.b1 == B1_intermediate_getter.WITH_INTERMEDIATE_GETTER &&
                testState.b2 == B2_intermediate_setter.WITH_INTERMEDIATE_SETTER &&
                testState.b3 == B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES &&
                testState.b4 == B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR
        ) {
            return ObjectWithYYNNType1.class;
        } else if (getDeepestClass() == DeepestObjectMultiple1Multiple2Multiple3.class &&
                testState.b1 == B1_intermediate_getter.WITH_INTERMEDIATE_GETTER &&
                testState.b2 == B2_intermediate_setter.WITH_INTERMEDIATE_SETTER &&
                testState.b3 == B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES &&
                testState.b4 == B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR
        ) {
            return ObjectWithYYNNType3.class;
        } else if (getDeepestClass() == DeepestObjectSetter1Public2.class &&
                testState.b1 == B1_intermediate_getter.WITH_INTERMEDIATE_GETTER &&
                testState.b2 == B2_intermediate_setter.WITH_INTERMEDIATE_SETTER &&
                testState.b3 == B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES &&
                testState.b4 == B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR
        ) {
            return ObjectWithYYNYType1.class;
        } else if (getDeepestClass() == DeepestObjectSetter1Public2.class &&
                testState.b1 == B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER &&
                testState.b2 == B2_intermediate_setter.WITH_INTERMEDIATE_SETTER &&
                testState.b3 == B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES &&
                testState.b4 == B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR) {
            return ObjectWithNYNYType1.class;
        } else if (getDeepestClass() == DeepestObjectSetter1Public2.class &&
                testState.b1 == B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER &&
                testState.b2 == B2_intermediate_setter.WITHOUT_INTERMEDIATE_SETTER &&
                testState.b3 == B3_intermediate_public_attributes.WITH_INTERMEDIATE_PUBLIC_ATTRIBUTES &&
                testState.b4 == B4_intermediate_javabean_constructor.WITH_JAVABEAN_CONSTRUCTOR) {
            return ObjectWithNNYYType1.class;
        } else if (getDeepestClass() == DeepestObjectSetter1Public2.class &&
                testState.b1 == B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER &&
                testState.b2 == B2_intermediate_setter.WITHOUT_INTERMEDIATE_SETTER &&
                testState.b3 == B3_intermediate_public_attributes.WITH_INTERMEDIATE_PUBLIC_ATTRIBUTES &&
                testState.b4 == B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR) {
            return ObjectWithNNYNType1.class;
        } else if (getDeepestClass() == DeepestObjectSetter1Public2.class &&
                testState.b1 == B1_intermediate_getter.WITHOUT_INTERMEDIATE_GETTER &&
                testState.b2 == B2_intermediate_setter.WITHOUT_INTERMEDIATE_SETTER &&
                testState.b3 == B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES &&
                testState.b4 == B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR) {
            return ObjectWithNNNNType1.class;
        }
        return null;
    }

    private void setupPropertyA21to23(PropertyState property) {
        /* Setup A2.1 */
        String prefixKey;
        switch (testState.a2) {
            case DEPTH_ZERO:
                prefixKey = "";
                break;
            case DEPTH_GREATER_THAN_ZERO:
                prefixKey = "deeper.deepest.";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + testState.a2);
        }

        /* Setup A2.2 */
        int numberOfValues;
        Integer numberOfParametersForSetter = null;
        if (property.b52 != null) {
            switch (property.b52) {
                case SETTER_WITH_ONE_PARAMETER:
                    numberOfParametersForSetter = 1;
                    break;
                case SETTER_WITH_MULTIPLE_PARAMETERS:
                    numberOfParametersForSetter = 3;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + property.b52);
            }
        }
        switch (property.a31) {
            case ONE_VALUE:
                numberOfValues = 1;
                break;
            case MULTIPLE_VALUES:
                numberOfValues = 3;
                break;
            case VALUES_LESS_THAN_SETTER_PARAMETERS:
                assert (numberOfParametersForSetter != null);
                numberOfValues = numberOfParametersForSetter - 1;
                break;
            case VALUES_EQUAL_AS_SETTER_PARAMETERS:
                assert (numberOfParametersForSetter != null);
                numberOfValues = numberOfParametersForSetter;
                break;
            case VALUES_GREATER_THAN_SETTER_PARAMETERS:
                assert (numberOfParametersForSetter != null);
                numberOfValues = numberOfParametersForSetter + 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + property.a31);
        }
        assert (numberOfValues > 0);
        property.numberOfValues = numberOfValues;

        /* Setup A2.3 */
        String singleValue;
        switch (property.a32) {
            case PRIMITIVE:
                singleValue = PRIMITIVE_VALUE;
                property.typeOfValues = "Primitive";
                break;
            case STRING:
                singleValue = STRING_VALUE;
                property.typeOfValues = "String";
                break;
            case SPECIAL_CLASS:
                singleValue = STRING_VALUE;
                property.typeOfValues = "SpecialClass";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + property.a32);
        }

        /* Finally set up the key of the property */
        property.key = prefixKey + property.typeOfValues + "Attribute" + property.id;

        /* Finally set up the value of the property */
        StringBuilder valueBuilder = new StringBuilder();
        for (int i = 0; i < numberOfValues; i++) {
            valueBuilder.append(singleValue);
            if (i < numberOfValues - 1) {
                valueBuilder.append(",");
            }
        }
        property.value = valueBuilder.toString();
    }

    private void setupExpected(PropertyState property) {
        Set<ExpectedFlags> flags = property.expectedSet;
        if (flags.contains(ExpectedFlags.SET)) {
            assert !flags.contains(ExpectedFlags.NOT_SET);
            assert !flags.contains(ExpectedFlags.THROWS_RUNTIME_EXCEPTION);
        }
    }
}
