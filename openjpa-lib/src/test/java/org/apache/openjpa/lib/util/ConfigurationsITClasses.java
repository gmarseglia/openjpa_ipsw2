package org.apache.openjpa.lib.util;

public class ConfigurationsITClasses {

    public static final String[] AVAILABLE_ATTRIBUTES = {"first", "second", "third"};

    public interface DeepestInterface {
        public Integer generalGetter(String attributeName);
    }

    public static class DeepestBase implements DeepestInterface {
        protected Integer first;
        protected Integer second;
        protected Integer third;

        public Integer generalGetter(String attributeName) {
            switch (attributeName) {
                case "First":
                case "first":
                    return this.first;
                case "Second":
                case "second":
                    return this.second;
                case "Third":
                case "third":
                    return this.third;
                default:
                    throw new IllegalStateException("Unexpected value: " + attributeName);
            }
        }
    }

    public static class Deepest1 extends DeepestBase {
        public void setFirst(int first) {
            this.first = first;
        }
    }

    public static class Deepest123 extends DeepestBase {
        public void setFirst(int first) {
            this.first = first;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public void setThird(int third) {
            this.third = third;
        }
    }

    public static class Deepest12 extends DeepestBase {
        public void setFirst(int first) {
            this.first = first;
        }

        public void setSecond(int second) {
            this.second = second;
        }
    }


    public static class Intermediate12 {
        DeepestInterface deepest;

        public Intermediate12() {
            deepest = new Deepest12();
        }

        public DeepestInterface getDeepest() {
            return deepest;
        }
    }
}
