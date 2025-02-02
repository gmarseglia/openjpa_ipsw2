package org.apache.openjpa.lib.util;

import org.apache.openjpa.lib.util.MyOptionsEnums.*;
import org.apache.openjpa.lib.util.MyOptionsTest.PropertyState;
import org.apache.openjpa.lib.util.MyOptionsTest.TestState;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class MyOptionsConfigurer {

    public static final String PRIMITIVE_VALUE = "1";
    public static final String STRING_VALUE = "one";

    TestState testState;
    int maxDepth = 0;

    public void setup(TestState testState) {
        this.testState = testState;

        /* Determine maxDepth */
        findMaxDepth();

        /* Create Object */
        /* Use B1, B2, B3, B4 and B5.1, B5.2, B5.3, B5.4, B5.5, C2.1 for each property */
        setupB();   // this also create the obj

        /* Setup A1, C1 */
        setupA1();
        setupC1();

        /* Set up A2.1, A2.2, A2.3 for each property */
        for (PropertyState property : testState.properties) {
            setupPropertyA21to23(property);
        }

        /* Create the SUT, using A2.4 for each property */
        createSUT();

        /* Set up  */
        for (PropertyState property : testState.properties) {
            setupPropertyC21(property);
            setupExpected(property);
        }

    }

    private void findMaxDepth() {
        this.maxDepth = 0;
        for (PropertyState property : testState.properties) {
            if (property.a21 == A2_1_depth.DEPTH_GREATER_THAN_ZERO) {
                this.maxDepth = 2;
                break;
            }
        }
    }

    private void createSUT() {
        Options defaults = new Options();
        for (PropertyState property : testState.properties) {
            if (property.a24 == A2_4_SUT_or_defaults.BOTH_SUT_AND_DEFAULTS || property.a24 == A2_4_SUT_or_defaults.ONLY_IN_DEFAULTS) {
                defaults.setProperty(property.key, property.value);
            }
        }
        Options SUT = new Options(defaults);
        for (PropertyState property : testState.properties) {
            if (property.a24 == A2_4_SUT_or_defaults.BOTH_SUT_AND_DEFAULTS || property.a24 == A2_4_SUT_or_defaults.ONLY_IN_SUT) {
                SUT.setProperty(property.key, property.value);
            }
        }
        testState.SUT = SUT;
    }

    private void setupA1() {
        /* Check the A1 characteristics */
        switch (testState.a1) {
            case ONE_PROPERTY:
            case ZERO_PROPERTIES:
                throw new IllegalStateException("#TODO implement: " + testState.a1);
            case MULTIPLE_PROPERTIES:
                assert testState.properties.size() > 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + testState.a1);
        }
    }

    private void setupB() {
        /* Set up B1 characteristics */
        if (testState.b1 == null)
            testState.b1 = B1_intermediate_getter.WITH_INTERMEDIATE_GETTER;

        if (testState.b2 == null)
            testState.b2 = B2_intermediate_setter.WITH_INTERMEDIATE_SETTER;

        if (testState.b3 == null)
            testState.b3 = B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES;

        if (testState.b4 == null)
            testState.b4 = B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR;

        /* This is the case YYNN */
        if (testState.b1 == B1_intermediate_getter.WITH_INTERMEDIATE_GETTER &&
                testState.b2 == B2_intermediate_setter.WITH_INTERMEDIATE_SETTER &&
                testState.b3 == B3_intermediate_public_attributes.WITHOUT_INTERMEDIATE_PUBLIC_ATTRIBUTES &&
                testState.b4 == B4_intermediate_javabean_constructor.WITHOUT_JAVABEAN_CONSTRUCTOR
        ) {
            Object deepestObject = getDeepestObject();
            testState.deepestObject = deepestObject;
            if (this.maxDepth == 0) {
                testState.obj = deepestObject;
            } else {
                MyOptionsObjects.ObjectWithYYNN obj = new MyOptionsObjects.ObjectWithYYNN();
                obj.setDeeper(new MyOptionsObjects.ObjectWithYYNN());
                obj.getDeeper().setDeepest((MyOptionsObjects.DeepestObject) deepestObject);
            }
            return;
        }

        throw new IllegalStateException("#TODO: not implemented combination of B1, B2, B3, B4");
    }

    private Object getDeepestObject() {
        if (testState.properties.size() == 2) {
            PropertyState p1 = testState.properties.get(0);
            PropertyState p2 = testState.properties.get(1);

            if (p1.b51 == B5_1_deepest_setter.WITH_DEEPEST_SETTER &&
                    p1.b52 == B5_2_number_of_parameter_of_deepest_setter.SETTER_NEEDS_SAME_VALUES &&
                    p1.b53 == B5_3_parsable_for_setter.PARSABLE_FOR_SETTER &&
                    p1.b54 == B5_4_deepest_public_attribute.WITHOUT_DEEPEST_PUBLIC_ATTRIBUTE &&
                    p2.b51 == B5_1_deepest_setter.WITHOUT_DEEPEST_SETTER &&
                    p2.b54 == B5_4_deepest_public_attribute.WITH_DEEPEST_PUBLIC_ATTRIBUTE &&
                    p2.b55 == B5_5_parsable_for_public_attribute.PARSABLE_FOR_PUBLIC_ATTRIBUTE
            ) {
                return new MyOptionsObjects.DeepestObject();
            }
        }

        throw new IllegalStateException("#TODO: not implemented combination of B5.1, B5.2, B5.3, B5.4, B5.5");
    }

    private void setupC1() {
        if (testState.c1 != null) {
            switch (testState.c1) {
                case NULL_INTERMEDIATE_INSTANCES:
                case NON_NULL_INTERMEDIATE_INSTANCES:
                    throw new IllegalStateException("#TODO implement: " + testState.c1);
                default:
                    throw new IllegalStateException("Unexpected value: " + testState.c1);
            }
        }
    }

    private void setupPropertyA21to23(PropertyState property) {
        /* Setup A2.1 */
        String prefixKey;
        switch (property.a21) {
            case DEPTH_ZERO:
                prefixKey = "";
                break;
            case DEPTH_GREATER_THAN_ZERO:
                throw new IllegalStateException("#TODO implement: " + property.a21);
            default:
                throw new IllegalStateException("Unexpected value: " + property.a21);
        }

        /* Setup A2.2 */
        int numberOfValues;
        switch (property.a22) {
            case ONE_VALUE:
                numberOfValues = 1;
                break;
            case MULTIPLE_VALUES:
                numberOfValues = 3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + property.a22);
        }
        property.numberOfValues = numberOfValues;

        /* Setup A2.3 */
        String singleValue;
        switch (property.a23) {
            case PRIMITIVE:
                singleValue = PRIMITIVE_VALUE;
                property.typeOfValues = "Primitive";
                break;
            case STRING:
                singleValue = STRING_VALUE;
                property.typeOfValues = "String";
                break;
            case CLASS_WITH_ADEQUATE_CONSTRUCTOR:
                throw new IllegalStateException("#TODO implement: " + property.a23);
            default:
                throw new IllegalStateException("Unexpected value: " + property.a23);
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

    private boolean isSetterForProperty(Method method, String propertyId) {
        return (method.getName().contains("set") && method.getName().contains("Attribute" + propertyId));
    }

    private boolean isFieldForProperty(Field field, String propertyId) {
        return (field.getName().contains(propertyId));
    }

    private void setupPropertyC21(PropertyState property) {
        switch (property.c21) {
            case NULL_LAST_INSTANCE:
                throw new IllegalStateException("#TODO implement: " + property.c21);
            case NON_NULL_LAST_INSTANCE:
                // No need to do anything
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + property.c21);
        }
    }

    private void setupExpected(PropertyState property) {
        Set<ExpectedFlags> flags = property.expectedSet;
        if (flags.contains(ExpectedFlags.SET)) {
            assert !flags.contains(ExpectedFlags.NOT_SET);
            assert !flags.contains(ExpectedFlags.THROWS_RUNTIME_EXCEPTION);
        }
    }
}
