package org.apache.openjpa.lib.util;

class SpecialClass {
    private final String value;

    public SpecialClass(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClassFromString{" + "value='" + value + '\'' + '}';
    }
}