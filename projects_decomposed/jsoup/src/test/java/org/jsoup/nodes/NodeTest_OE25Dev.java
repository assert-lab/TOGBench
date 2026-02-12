package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeVisitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests Nodes

 @author Jonathan Hedley, jonathan@hedley.net */
public class NodeTest_OE25Dev {

    /*
    Test for an issue with Java's abs URL handler.
     */

    private Attributes getAttributesCaseInsensitive(Element element) {
        Attributes matches = new Attributes();
        for (Attribute attribute : element.attributes()) {
            if (attribute.getKey().equalsIgnoreCase("value")) {
                matches.put(attribute);
            }
        }
        return matches;
    }

    private Attributes singletonAttributes() {
        Attributes attributes = new Attributes();
        attributes.put("value", "bar");
        return attributes;
    }


}
