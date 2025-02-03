package org.apache.openjpa.lib.util;

import java.util.Objects;

public class SpecialClass {
    private final String value;

    public SpecialClass(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClassFromString{" + "value='" + value + '\'' + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SpecialClass that = (SpecialClass) object;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
