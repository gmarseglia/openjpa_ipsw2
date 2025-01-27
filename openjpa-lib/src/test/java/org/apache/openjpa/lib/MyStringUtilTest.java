package org.apache.openjpa.lib;

import org.apache.openjpa.lib.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MyStringUtilTest {

    @Test
    public void prova() {
        String[] expected = {"ok", "yes"};
        String string = "ok.yes";
        String[] actual = StringUtil.split(string, ".", 10);
        Assertions.assertArrayEquals(expected, actual);
    }

}
