package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.helper.ValidationException;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests for Element (DOM stuff mostly).

 @author Jonathan Hedley */
public class ElementTest_OE25Dev {
    private String reference = "<div id=div1><p>Hello</p><p>Another <b>element</b></p><div id=div2><img src=foo.png></div></div>";

    private static void validateScriptContents(String src, Element el) {
        assertEquals("",el.text());// it's not text assertEquals("",el.ownText());
        assertEquals("", el.wholeText());
        assertEquals(src, el.html());
        assertEquals(src, el.data());
    }

    private static void validateXmlScriptContents(Element el) {
        assertEquals("var foo = 5 < 2; var bar = 1 && 2;", el.text());
        assertEquals("var foo = 5 < 2; var bar = 1 && 2;", el.ownText());
        assertEquals("var foo = 5 < 2;\nvar bar = 1 && 2;", el.wholeText());
        assertEquals("var foo = 5 &lt; 2;\nvar bar = 1 &amp;&amp; 2;", el.html());
        assertEquals("", el.data());
    }

    @Test
    public void testBasicFormats() {
        String html = "<span>0</span>.<div><span>1</span>-<span>2</span><p><span>3</span>-<span>4</span><div>5</div>";
        Document doc = Jsoup.parse(html);
        assertEquals("<span>0</span>.\n" + "<div>\n" + " <span>1</span>-<span>2</span>\n" + " <p><span>3</span>-<span>4</span></p>\n" + " <div>\n" + " 5\n" + " </div>\n" + "</div>",doc.body().html());
    }

    @Test
    public void insertChildrenArgumentValidation() {
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);
        List<Node> children = div1.childNodes();

        try {
            div2.insertChildren(6, children);
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            div2.insertChildren(-5, children);
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            div2.insertChildren(0, (Collection<? extends Node>) null);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    private static Stream<Document.OutputSettings> testOutputSettings() {
        return Stream.of(
            new Document.OutputSettings().prettyPrint(true).indentAmount(4),
            new Document.OutputSettings().prettyPrint(true).indentAmount(1),
            new Document.OutputSettings().prettyPrint(true).indentAmount(4).outline(true),
            new Document.OutputSettings().prettyPrint(false)
        );
    }

    @Test
    public void testThrowsOnAddNullText_1_oe() throws Exception {
        try {
     Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>"); Element div = doc.getElementById("1"); div.appendText(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testThrowsOnPrependNullText_1_oe() throws Exception {
        try {
     Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>"); Element div = doc.getElementById("1"); div.prependText(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}