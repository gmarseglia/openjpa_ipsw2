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
        private DeepestObjectType1 deepest;

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

        public DeepestObjectType1 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        public void setDeepest(DeepestObjectType1 deepest) {
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
            this.deepest = (DeepestObjectType1) deepest;
        }
    }

    public static class ObjectWithYYNYType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYYNYType1 deeper;
        private DeepestObjectType1 deepest;

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

        public DeepestObjectType1 getDeepest() {
            addMethod("getDeepest");
            return deepest;
        }

        public void setDeepest(DeepestObjectType1 deepest) {
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
            this.deepest = (DeepestObjectType1) deepest;
        }
    }

    public static class ObjectWithNYNYType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithNYNYType1 deeper;
        private DeepestObjectType1 deepest;

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

        public void setDeepest(DeepestObjectType1 deepest) {
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
            this.deepest = (DeepestObjectType1) deepest;
        }
    }

    public static class ObjectWithNNYYType1 implements IntermediateInterface, AnyDeepInterface {
        public ObjectWithNNYYType1 deeper;
        public DeepestObjectType1 deepest;

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
            this.deepest = (DeepestObjectType1) deepest;
        }
    }

    public static class ObjectWithNNYNType1 implements IntermediateInterface, AnyDeepInterface {
        public ObjectWithNNYNType1 deeper;
        public DeepestObjectType1 deepest;

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
            this.deepest = (DeepestObjectType1) deepest;
        }
    }

    /* Deepest ojects */
    public static class DeepestObjectType1 implements DeepestInterface, AnyDeepInterface {
        public String StringAttribute2;
        public SpecialClass SpecialClassAttribute2;
        public int PrimitiveAttribute2;
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;
        private int PrimitiveAttribute1;

        public DeepestObjectType1() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectType1(boolean track) {
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

    public static class DeepestObjectType2 implements DeepestInterface, AnyDeepInterface {
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;
        private int PrimitiveAttribute1;

        public DeepestObjectType2() {
            addMethod("new DeepestInterface");
        }

        public DeepestObjectType2(boolean track) {
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
}
