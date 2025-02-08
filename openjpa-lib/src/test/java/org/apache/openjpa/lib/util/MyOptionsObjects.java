package org.apache.openjpa.lib.util;

import static org.apache.openjpa.lib.util.MyOptionsMethodTracker.addMethod;

public class MyOptionsObjects {

    public interface AnyDeepInterface {
        DeepestInterface intermediateGetDeepest();
    }

    public interface DeepestInterface {
        int deepestPrimitiveAttribute(String id);

        String deepestStringAttribute(String id);

        SpecialClass deepestSpecialClassAttribute1(String id);
    }

    public interface IntermediateInterface {
        IntermediateInterface intermediateGetDeeper();

        void intermediateSetDeeper(IntermediateInterface lower);

        void intermediateSetDeepest(DeepestInterface deepest);
    }

    /* Intermediate objects */
    public static class ObjectWithYYNNType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYYNNType1 deeper;
        private DeepestObjectSetter1Public2 deepest;

        private ObjectWithYYNNType1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithYYNNType1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        public ObjectWithYYNNType1 getDeeper() {
            addMethod("getDeeper");
            return deeper;
        }

        public void setDeeper(ObjectWithYYNNType1 deeper) {
            addMethod("setDeeper");
            this.deeper = deeper;
        }

        public DeepestObjectSetter1Public2 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        public void setDeepest(DeepestObjectSetter1Public2 deepest) {
            addMethod("setDeepest");
            this.deepest = deepest;
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithYYNNType1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }

    public static class ObjectWithYNNNType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYNNNType1 deeper;
        private DeepestObjectSetter1Public2 deepest;

        private ObjectWithYNNNType1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithYNNNType1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        public ObjectWithYNNNType1 getDeeper() {
            addMethod("getDeeper");
            return deeper;
        }

        public DeepestObjectSetter1Public2 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        public void setDeepest(DeepestObjectSetter1Public2 deepest) {
            addMethod("setDeepest");
            this.deepest = deepest;
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithYNNNType1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }

    public static class ObjectWithYNNNNo1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYNNNNo1 deeper;
        private DeepestObjectNo1 deepest;

        private ObjectWithYNNNNo1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithYNNNNo1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        public ObjectWithYNNNNo1 getDeeper() {
            addMethod("getDeeper");
            return deeper;
        }

        public DeepestObjectNo1 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithYNNNNo1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectNo1) deepest;
        }
    }

    public static class ObjectWithYYNNType3 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYYNNType3 deeper;
        private DeepestObjectMultiple1Multiple2Multiple3 deepest;

        private ObjectWithYYNNType3() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithYYNNType3(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        public ObjectWithYYNNType3 getDeeper() {
            addMethod("getDeeper");
            return deeper;
        }

        public void setDeeper(ObjectWithYYNNType3 deeper) {
            addMethod("setDeeper");
            this.deeper = deeper;
        }

        public DeepestObjectMultiple1Multiple2Multiple3 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        public void setDeepest(DeepestObjectMultiple1Multiple2Multiple3 deepest) {
            addMethod("setDeepest");
            this.deepest = deepest;
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithYYNNType3) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectMultiple1Multiple2Multiple3) deepest;
        }
    }

    public static class ObjectWithYYNYType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYYNYType1 deeper;
        private DeepestObjectSetter1Public2 deepest;

        public ObjectWithYYNYType1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithYYNYType1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        public ObjectWithYYNYType1 getDeeper() {
            addMethod("getDeeper");
            return deeper;
        }

        public void setDeeper(ObjectWithYYNYType1 deeper) {
            addMethod("setDeeper");
            this.deeper = deeper;
        }

        public DeepestObjectSetter1Public2 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        public void setDeepest(DeepestObjectSetter1Public2 deepest) {
            addMethod("setDeepest");
            this.deepest = deepest;
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithYYNYType1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }

    public static class ObjectWithYNNYType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYNNYType1 deeper;
        private DeepestObjectSetter1Public2 deepest;

        public ObjectWithYNNYType1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithYNNYType1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        public ObjectWithYNNYType1 getDeeper() {
            addMethod("getDeeper");
            return deeper;
        }

        public DeepestObjectSetter1Public2 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithYNNYType1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }

    public static class ObjectWithNYNYType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithNYNYType1 deeper;
        private DeepestObjectSetter1Public2 deepest;

        public ObjectWithNYNYType1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithNYNYType1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        public void setDeeper(ObjectWithNYNYType1 deeper) {
            addMethod("setDeeper");
            this.deeper = deeper;
        }

        public void setDeepest(DeepestObjectSetter1Public2 deepest) {
            addMethod("setDeepest");
            this.deepest = deepest;
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithNYNYType1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }

    public static class ObjectWithNNYYType1 implements IntermediateInterface, AnyDeepInterface {
        public ObjectWithNNYYType1 deeper;
        public DeepestObjectSetter1Public2 deepest;

        public ObjectWithNNYYType1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithNNYYType1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithNNYYType1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }

    public static class ObjectWithNNYNType1 implements IntermediateInterface, AnyDeepInterface {
        public ObjectWithNNYNType1 deeper;
        public DeepestObjectSetter1Public2 deepest;

        private ObjectWithNNYNType1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithNNYNType1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithNNYNType1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }

    public static class ObjectWithNNNNType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithNNNNType1 deeper;
        private DeepestObjectSetter1Public2 deepest;

        private ObjectWithNNNNType1() {
            addMethod("new IntermediateInterface");
        }

        public ObjectWithNNNNType1(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithNNNNType1) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }

    public static class ObjectWithExceptionInConstructor implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithExceptionInConstructor deeper;
        private DeepestObjectSetter1Public2 deepest;

        private ObjectWithExceptionInConstructor() throws Exception {
            addMethod("new IntermediateInterface");
            addMethod("throw new Exception");
            throw new Exception("from ObjectWithExceptionInConstructor");
        }

        public ObjectWithExceptionInConstructor(boolean track) {
            if (track)
                addMethod("new IntermediateInterface");
        }

        public ObjectWithExceptionInConstructor getDeeper() {
            addMethod("getDeeper");
            return deeper;
        }

        public void setDeeper(ObjectWithExceptionInConstructor deeper) {
            addMethod("setDeeper");
            this.deeper = deeper;
        }

        public DeepestObjectSetter1Public2 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        public void setDeepest(DeepestObjectSetter1Public2 deepest) {
            addMethod("setDeepest");
            this.deepest = deepest;
        }

        @Override
        public IntermediateInterface intermediateGetDeeper() {
            return this.deeper;
        }

        @Override
        public void intermediateSetDeeper(IntermediateInterface lower) {
            this.deeper = (ObjectWithExceptionInConstructor) lower;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this.deeper.deepest;
        }

        @Override
        public void intermediateSetDeepest(DeepestInterface deepest) {
            this.deepest = (DeepestObjectSetter1Public2) deepest;
        }
    }


    /* Deepest objects */
    public static class DeepestObjectSetter1Public2 implements DeepestInterface, AnyDeepInterface {
        public String StringAttribute2;
        public SpecialClass SpecialClassAttribute2;
        public int PrimitiveAttribute2;
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;
        private int PrimitiveAttribute1;

        public DeepestObjectSetter1Public2() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectSetter1Public2(boolean track) {
            if (track)
                addMethod("new DeepestInterface");
        }

        public void setPrimitiveAttribute1(int primitiveAttribute1) {
            addMethod("setPrimitiveAttribute1");
            PrimitiveAttribute1 = primitiveAttribute1;
        }

        public void setPrimitiveAttribute1() {
            addMethod("setPrimitiveAttribute1");
            PrimitiveAttribute1 = -1;
        }

        public void setPrimitiveAttribute1(int[] primitiveAttributes) {
            addMethod("setPrimitiveAttribute1");
            PrimitiveAttribute1 = -1;
        }

        public void setStringAttribute1(String stringAttribute1) {
            addMethod("setStringAttribute1");
            StringAttribute1 = stringAttribute1;
        }

        public void setSpecialClassAttribute1(SpecialClass specialClassAttribute1) {
            addMethod("setSpecialClassAttribute1");
            SpecialClassAttribute1 = specialClassAttribute1;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this;
        }

        @Override
        public int deepestPrimitiveAttribute(String id) {
            if (id.equals("1")) {
                return this.PrimitiveAttribute1;
            } else if (id.equals("2")) {
                return this.PrimitiveAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public String deepestStringAttribute(String id) {
            if (id.equals("1")) {
                return this.StringAttribute1;
            } else if (id.equals("2")) {
                return this.StringAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public SpecialClass deepestSpecialClassAttribute1(String id) {
            if (id.equals("1")) {
                return this.SpecialClassAttribute1;
            } else if (id.equals("2")) {
                return this.SpecialClassAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }
    }

    public static class DeepestObjectSetter1Setter2 implements DeepestInterface, AnyDeepInterface {
        private String StringAttribute2;
        private SpecialClass SpecialClassAttribute2;
        private int PrimitiveAttribute2;
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;
        private int PrimitiveAttribute1;

        public DeepestObjectSetter1Setter2() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectSetter1Setter2(boolean track) {
            if (track)
                addMethod("new DeepestInterface");
        }

        public void setPrimitiveAttribute1(int primitiveAttribute1) {
            addMethod("setPrimitiveAttribute1");
            PrimitiveAttribute1 = primitiveAttribute1;
        }

        public void setStringAttribute1(String stringAttribute1) {
            addMethod("setStringAttribute1");
            StringAttribute1 = stringAttribute1;
        }

        public void setStringAttribute1(String stringAttribute1, String second) {
            addMethod("setStringAttribute1");
            StringAttribute1 = stringAttribute1 + "second" + second;
        }

        public void setStringAttribute1(int first, int second) {
            addMethod("setStringAttribute1");
            StringAttribute1 = String.valueOf(first + second + 1000);
        }

        public void setSpecialClassAttribute1(SpecialClass specialClassAttribute1) {
            addMethod("setSpecialClassAttribute1");
            SpecialClassAttribute1 = specialClassAttribute1;
        }

        public void setPrimitiveAttribute2(int primitiveAttribute2) {
            addMethod("setPrimitiveAttribute2");
            PrimitiveAttribute2 = primitiveAttribute2;
        }

        public void setStringAttribute2(String stringAttribute2) {
            addMethod("setStringAttribute2");
            StringAttribute2 = stringAttribute2;
        }

        public void setSpecialClassAttribute2(SpecialClass specialClassAttribute2) {
            addMethod("setSpecialClassAttribute2");
            SpecialClassAttribute2 = specialClassAttribute2;
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this;
        }

        @Override
        public int deepestPrimitiveAttribute(String id) {
            if (id.equals("1")) {
                return this.PrimitiveAttribute1;
            } else if (id.equals("2")) {
                return this.PrimitiveAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public String deepestStringAttribute(String id) {
            if (id.equals("1")) {
                return this.StringAttribute1;
            } else if (id.equals("2")) {
                return this.StringAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public SpecialClass deepestSpecialClassAttribute1(String id) {
            if (id.equals("1")) {
                return this.SpecialClassAttribute1;
            } else if (id.equals("2")) {
                return this.SpecialClassAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }
    }

    public static class DeepestObjectPublic1Public2 implements DeepestInterface, AnyDeepInterface {
        public String StringAttribute2;
        public SpecialClass SpecialClassAttribute2;
        public int PrimitiveAttribute2;
        public String StringAttribute1;
        public SpecialClass SpecialClassAttribute1;
        public int PrimitiveAttribute1;

        public DeepestObjectPublic1Public2() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectPublic1Public2(boolean track) {
            if (track)
                addMethod("new DeepestInterface");
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this;
        }

        @Override
        public int deepestPrimitiveAttribute(String id) {
            if (id.equals("1")) {
                return this.PrimitiveAttribute1;
            } else if (id.equals("2")) {
                return this.PrimitiveAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public String deepestStringAttribute(String id) {
            if (id.equals("1")) {
                return this.StringAttribute1;
            } else if (id.equals("2")) {
                return this.StringAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public SpecialClass deepestSpecialClassAttribute1(String id) {
            if (id.equals("1")) {
                return this.SpecialClassAttribute1;
            } else if (id.equals("2")) {
                return this.SpecialClassAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }
    }

    public static class DeepestObjectNo1 implements DeepestInterface, AnyDeepInterface {
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;
        private int PrimitiveAttribute1;

        public DeepestObjectNo1() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectNo1(boolean track) {
            if (track)
                addMethod("new DeepestInterface");
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this;
        }

        @Override
        public int deepestPrimitiveAttribute(String id) {
            if (id.equals("1")) {
                return this.PrimitiveAttribute1;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public String deepestStringAttribute(String id) {
            if (id.equals("1")) {
                return this.StringAttribute1;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public SpecialClass deepestSpecialClassAttribute1(String id) {
            if (id.equals("1")) {
                return this.SpecialClassAttribute1;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }
    }

    public static class DeepestObjectMultiple1Multiple2Multiple3 implements DeepestInterface, AnyDeepInterface {
        private int PrimitiveAttribute1;
        private String StringAttribute2;
        private SpecialClass SpecialClassAttribute3;

        public DeepestObjectMultiple1Multiple2Multiple3() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectMultiple1Multiple2Multiple3(boolean track) {
            if (track)
                addMethod("new DeepestInterface");
        }

        public void setPrimitiveAttribute1(int first, int second, int third) {
            addMethod("setPrimitiveAttribute1");
            PrimitiveAttribute1 = first + second + third;
        }

        public void setStringAttribute2(String first, String second, String third) {
            addMethod("setStringAttribute2");
            String firstValue = first == null ? "null" : first;
            String secondValue = second == null ? "null" : second;
            String thirdValue = third == null ? "null" : third;
            StringAttribute2 = first + second + third;
        }

        public void setSpecialClassAttribute3(SpecialClass first, SpecialClass second, SpecialClass third) {
            addMethod("setSpecialClassAttribute3");
            String firstValue = first == null ? "null" : first.getValue();
            String secondValue = second == null ? "null" : second.getValue();
            String thirdValue = third == null ? "null" : third.getValue();

            SpecialClassAttribute3 = new SpecialClass(firstValue + secondValue + thirdValue);
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this;
        }

        @Override
        public int deepestPrimitiveAttribute(String id) {
            if (id.equals("1")) {
                return this.PrimitiveAttribute1;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public String deepestStringAttribute(String id) {
            if (id.equals("2")) {
                return this.StringAttribute2;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public SpecialClass deepestSpecialClassAttribute1(String id) {
            if (id.equals("3")) {
                return this.SpecialClassAttribute3;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }
    }

    public static class DeepestObjectMultiple1 implements DeepestInterface, AnyDeepInterface {
        private int PrimitiveAttribute1;
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;

        public DeepestObjectMultiple1() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectMultiple1(boolean track) {
            if (track)
                addMethod("new DeepestInterface");
        }

        public void setPrimitiveAttribute1(int first, int second, int third) {
            addMethod("setPrimitiveAttribute1");
            PrimitiveAttribute1 = first + second + third;
        }

        public void setStringAttribute1(String first, String second, String third) {
            addMethod("setStringAttribute1");
            String firstValue = first == null ? "null" : first;
            String secondValue = second == null ? "null" : second;
            String thirdValue = third == null ? "null" : third;
            StringAttribute1 = first + second + third;
        }

        public void setSpecialClassAttribute1(SpecialClass first, SpecialClass second, SpecialClass third) {
            addMethod("setSpecialClassAttribute1");
            String firstValue = first == null ? "null" : first.getValue();
            String secondValue = second == null ? "null" : second.getValue();
            String thirdValue = third == null ? "null" : third.getValue();

            SpecialClassAttribute1 = new SpecialClass(firstValue + secondValue + thirdValue);
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this;
        }

        @Override
        public int deepestPrimitiveAttribute(String id) {
            if (id.equals("1")) {
                return this.PrimitiveAttribute1;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public String deepestStringAttribute(String id) {
            if (id.equals("2")) {
                return this.StringAttribute1;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public SpecialClass deepestSpecialClassAttribute1(String id) {
            if (id.equals("3")) {
                return this.SpecialClassAttribute1;
            }
            throw new IllegalStateException("Unexpected id: " + id);
        }
    }

    public static class DeepestObjectEmpty implements DeepestInterface, AnyDeepInterface {
        public DeepestObjectEmpty() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectEmpty(boolean track) {
            if (track)
                addMethod("new DeepestInterface");
        }

        @Override
        public DeepestInterface intermediateGetDeepest() {
            return this;
        }

        @Override
        public int deepestPrimitiveAttribute(String id) {
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public String deepestStringAttribute(String id) {
            throw new IllegalStateException("Unexpected id: " + id);
        }

        @Override
        public SpecialClass deepestSpecialClassAttribute1(String id) {
            throw new IllegalStateException("Unexpected id: " + id);
        }
    }
}
