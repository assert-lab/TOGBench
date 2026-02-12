package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Longer running Parser tests.
 */

public class ParserIT_OE25Dev {
    @Test
    @Disabled // disabled by default now, as there more specific unconsume tests
    public void testIssue1251() {
        // https://github.com/jhy/jsoup/issues/1251
        StringBuilder str = new StringBuilder("<a href=\"\"ca");
        for (int countSpaces = 0; countSpaces < 100000; countSpaces++) {
            try {
                Parser.htmlParser().setTrackErrors(1).parseInput(str.toString(), "");
            } catch (Exception e) {
                throw new AssertionError("failed at length " + str.length(), e);
            }
            str.insert(countSpaces, ' ');
        }
    }


}
