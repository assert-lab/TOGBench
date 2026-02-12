package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Selector Query Parser.
 *
 * @author Jonathan Hedley
 */
public class QueryParserTest_OE25Dev {

    @Test public void exceptionOnUncloseAttribute_1_oe() throws Exception {
        try {
    QueryParser.parse("section > a[href=\"]");
    org.junit.jupiter.api.Assertions.fail("Selector.SelectorParseException");
} catch (Selector.SelectorParseException e) {
}
        }

    @Test public void testParsesSingleQuoteInContains_1_oe() throws Exception {
        try {
    QueryParser.parse("p:contains(One \" One)");
    org.junit.jupiter.api.Assertions.fail("Selector.SelectorParseException");
} catch (Selector.SelectorParseException e) {
}
        }

    @Test public void exceptOnEmptySelector_1_oe() throws Exception {
        try {
    QueryParser.parse("");
    org.junit.jupiter.api.Assertions.fail("Selector.SelectorParseException");
} catch (Selector.SelectorParseException e) {
}
        }

    @Test public void exceptOnNullSelector_1_oe() throws Exception {
        try {
    QueryParser.parse(null);
    org.junit.jupiter.api.Assertions.fail("Selector.SelectorParseException");
} catch (Selector.SelectorParseException e) {
}
        }

}
