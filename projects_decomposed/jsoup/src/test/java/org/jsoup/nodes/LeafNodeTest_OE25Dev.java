package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeafNodeTest_OE25Dev {

    private boolean hasAnyAttributes(Node node) {
        final boolean[] found = new boolean[1];
        node.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                if (node.hasAttributes()) {
                    found[0] = true;
                    return FilterResult.STOP;
                } else {
                    return FilterResult.CONTINUE;
                }
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        });
        return found[0];
    }


}
