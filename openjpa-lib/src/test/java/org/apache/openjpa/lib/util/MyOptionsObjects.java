package org.apache.openjpa.lib.util;

public class MyOptionsObjects {

    public interface DeepestInterface {
        int deepestPrimitiveAttribute(String id);

        String deepestStringAttribute(String id);

        SpecialClass deepestSpecialClassAttribute1(String id);
    }

    public static class ObjectWithYYNN {
        private ObjectWithYYNN deeper;
        private DeepestObject deepest;

        public ObjectWithYYNN getDeeper() {
            return deeper;
        }

        public void setDeeper(ObjectWithYYNN deeper) {
            this.deeper = deeper;
        }

        public DeepestObject getDeepest() {
            return deepest;
        }

        public void setDeepest(DeepestObject deepest) {
            this.deepest = deepest;
        }
    }

    public static class DeepestObject implements DeepestInterface {
        public String StringAttribute2;
        public SpecialClass SpecialClassAttribute2;
        public int PrimitiveAttribute2;
        private String StringAttribute1;
        private SpecialClass SpecialClassAttribute1;
        private int PrimitiveAttribute1;

        public DeepestObject() {
        }

        public void setStringAttribute1(String stringAttribute1) {
            StringAttribute1 = stringAttribute1;
        }

        public void setSpecialClassAttribute1(SpecialClass specialClassAttribute1) {
            SpecialClassAttribute1 = specialClassAttribute1;
        }

        public void setPrimitiveAttribute1(int primitiveAttribute1) {
            PrimitiveAttribute1 = primitiveAttribute1;
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
