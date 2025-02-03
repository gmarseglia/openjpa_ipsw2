package org.apache.openjpa.lib.util;

public class MyOptionsObjects {

    public interface DeepestInterface {
        int deepestPrimitiveAttribute(String id);

        String deepestStringAttribute(String id);

        SpecialClass deepestSpecialClassAttribute1(String id);
    }

    public interface DeepestSetterForProperty1 {
        void setPrimitiveAttribute1(int primitiveAttribute1);

        void setStringAttribute1(String stringAttribute1);

        void setSpecialClassAttribute1(SpecialClass specialClassAttribute1);
    }

    public interface DeepestSetterForProperty2 {
        void setPrimitiveAttribute2(int primitiveAttribute1);

        void setStringAttribute2(String stringAttribute1);

        void setSpecialClassAttribute2(SpecialClass specialClassAttribute1);
    }

    public static class ObjectWithYYNN {
        private ObjectWithYYNN deeper;
        private DeepestObjectType1 deepest;

        public ObjectWithYYNN getDeeper() {
            return deeper;
        }

        public void setDeeper(ObjectWithYYNN deeper) {
            this.deeper = deeper;
        }

        public DeepestObjectType1 getDeepest() {
            return deepest;
        }

        public void setDeepest(DeepestObjectType1 deepest) {
            this.deepest = deepest;
        }
    }

    public static class DeepestObjectType1 implements DeepestInterface, DeepestSetterForProperty1 {
        public String StringAttribute2;
        public SpecialClass SpecialClassAttribute2;
        public int PrimitiveAttribute2;
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;
        private int PrimitiveAttribute1;

        public DeepestObjectType1() {
        }

        @Override
        public void setPrimitiveAttribute1(int primitiveAttribute1) {
            PrimitiveAttribute1 = primitiveAttribute1;
        }

        @Override
        public void setStringAttribute1(String stringAttribute1) {
            StringAttribute1 = stringAttribute1;
        }

        @Override
        public void setSpecialClassAttribute1(SpecialClass specialClassAttribute1) {
            SpecialClassAttribute1 = specialClassAttribute1;
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
