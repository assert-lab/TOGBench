package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttributeTest_OE25Dev {

    @Test public void validatesKeysNotEmpty_1_oe() throws Exception {
        try {
    new Attribute(" ", "Check");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void validatesKeysNotEmptyViaSet_1_oe() throws Exception {
        try {
     Attribute attr = new Attribute("One", "Check"); attr.setKey(" ");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

}
