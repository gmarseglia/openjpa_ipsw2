package org.apache.openjpa.lib.util;

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

        DeepestInterface intermediateGetDeepest();

        void intermediateSetDeepest(DeepestInterface deepest);
    }

    public static class ObjectWithYYNNType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYYNNType1 deeper;
        private DeepestObjectType1 deepest;

        private ObjectWithYYNNType1() {
        }

        public ObjectWithYYNNType1 getDeeper() {
            return deeper;
        }

        public void setDeeper(ObjectWithYYNNType1 deeper) {
            this.deeper = deeper;
        }

        public DeepestObjectType1 getDeepest() {
            return deepest;
        }

        public void setDeepest(DeepestObjectType1 deepest) {
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
            this.deeper.setDeepest((DeepestObjectType1) deepest);
        }
    }

    public static class ObjectWithYYNYType1 implements IntermediateInterface, AnyDeepInterface {
        private ObjectWithYYNYType1 deeper;
        private DeepestObjectType1 deepest;

        public ObjectWithYYNYType1() {
        }

        public ObjectWithYYNYType1 getDeeper() {
            return deeper;
        }

        public void setDeeper(ObjectWithYYNYType1 deeper) {
            this.deeper = deeper;
        }

        public DeepestObjectType1 getDeepest() {
            return deepest;
        }

        public void setDeepest(DeepestObjectType1 deepest) {
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
            this.deeper.setDeepest((DeepestObjectType1) deepest);
        }
    }

    public static class DeepestObjectType1 implements DeepestInterface, AnyDeepInterface {
        public String StringAttribute2;
        public SpecialClass SpecialClassAttribute2;
        public int PrimitiveAttribute2;
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;
        private int PrimitiveAttribute1;

        public DeepestObjectType1() {
        }

        public void setPrimitiveAttribute1(int primitiveAttribute1) {
            PrimitiveAttribute1 = primitiveAttribute1;
        }

        public void setStringAttribute1(String stringAttribute1) {
            StringAttribute1 = stringAttribute1;
        }

        public void setSpecialClassAttribute1(SpecialClass specialClassAttribute1) {
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
}
