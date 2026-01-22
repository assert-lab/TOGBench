package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 Test TextNodes

 @author Jonathan Hedley, jonathan@hedley.net */
public class TextNodeTest_OE25Dev {
    @Test public void testBlank() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }

    @Test public void testTextBean() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());

        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());

        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }

    @Test public void testSplitText() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertSame(tn.parent(), tail.parent());
    }

    @Test public void testSplitAnEmbolden() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }

    @Test public void testWithSupplementaryCharacter(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }

    @Test public void testLeadNodesHaveNoChildren() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words
        assertEquals(whole, tn.getWholeText());
        assertEquals(norm, tn.text());
        assertEquals(norm, tn.outerHtml());
        assertEquals(norm, tn.toString());

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        //tn.setParentNode(el); // set any parent
        assertEquals(whole, tn.getWholeText());
        assertEquals(norm, tn.text());
        assertEquals(norm, tn.outerHtml());
        assertEquals(norm, tn.toString());

        assertEquals("<p>" + norm + "</p>", el.outerHtml());
        assertEquals(norm, el.html());
        assertEquals(whole, el.wholeText());
    }

    @Test
    public void testClone() {
        // https://github.com/jhy/jsoup/issues/1176
        TextNode x = new TextNode("zzz");
        TextNode y = x.clone();

        assertNotSame(x, y);
        assertEquals(x.outerHtml(), y.outerHtml());

        y.text("yyy");
        assertNotEquals(x.outerHtml(), y.outerHtml());
        assertEquals("zzz", x.text());

        x.attributes(); // already cloned so no impact
        y.text("xxx");
        assertEquals("zzz", x.text());
        assertEquals("xxx", y.text());
    }

    @Test public void testBlank() {
        TextNode_1_oe one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        }

    @Test public void testBlank() {
        TextNode_2_oe one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        // removed other assertion
        assertTrue(two.isBlank());
        }

    @Test public void testBlank() {
        TextNode_3_oe one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        // removed other assertion
        // removed other assertion
        assertTrue(three.isBlank());
        }

    @Test public void testBlank() {
        TextNode_4_oe one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(four.isBlank());
        }

    @Test public void testBlank() {
        TextNode_5_oe one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(five.isBlank());
        }

    @Test public void testSplitText() {
        Document doc = Jsoup.parse_1_oe("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        }

    @Test public void testSplitText() {
        Document doc = Jsoup.parse_2_oe("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        // removed other assertion
        assertEquals("there", tail.getWholeText());
        }

    @Test public void testSplitText() {
        Document doc = Jsoup.parse_3_oe("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        // removed other assertion
        // removed other assertion
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        }

    @Test public void testSplitText() {
        Document doc = Jsoup.parse_4_oe("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        // removed other assertion
        // removed other assertion
        tail.text("there!");
        // removed other assertion
        assertSame(tn.parent(), tail.parent());
        }

    @Test public void testWithSupplementaryCharacter(){
        Document doc = Jsoup.parse_1_oe(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_1_oe tn = new TextNode(whole); // there are 2 spaces between the words
        assertEquals(whole, tn.getWholeText());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_2_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        assertEquals(norm, tn.text());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_3_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        assertEquals(norm, tn.outerHtml());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_4_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(norm, tn.toString());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_5_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        //tn.setParentNode(el); // set any parent
        assertEquals(whole, tn.getWholeText());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_6_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        //tn.setParentNode(el); // set any parent
        // removed other assertion
        assertEquals(norm, tn.text());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_7_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        //tn.setParentNode(el); // set any parent
        // removed other assertion
        // removed other assertion
        assertEquals(norm, tn.outerHtml());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_8_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        //tn.setParentNode(el); // set any parent
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(norm, tn.toString());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_9_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        //tn.setParentNode(el); // set any parent
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("<p>" + norm + "</p>", el.outerHtml());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_10_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        //tn.setParentNode(el); // set any parent
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(norm, el.html());
        }

    @Test public void testSpaceNormalise() {
        // https://github.com/jhy/jsoup/issues/1309
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode_11_oe tn = new TextNode(whole); // there are 2 spaces between the words
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        //tn.setParentNode(el); // set any parent
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(whole, el.wholeText());
        }

    @Test
    public void testCloneAfterAttributesHit_1_oe() {
        // https://github.com/jhy/jsoup/issues/1176
        TextNode x = new TextNode("zzz");
        x.attributes(); // moves content from leafnode value to attributes, which were missed in clone
        TextNode y = x.clone();
        y.text("xxx");
        assertEquals("zzz", x.text());
    }

    @Test
    public void testCloneAfterAttributesHit_2_oe() {
        // https://github.com/jhy/jsoup/issues/1176
        TextNode x = new TextNode("zzz");
        x.attributes(); // moves content from leafnode value to attributes, which were missed in clone
        TextNode y = x.clone();
        y.text("xxx");
        // removed other assertion
        assertEquals("xxx", y.text());
    }

    @Test
    public void testHasTextWhenIterating_1_oe() {
        // https://github.com/jhy/jsoup/issues/1170
        Document doc = Jsoup.parse("<div>One <p>Two <p>Three");
        boolean foundFirst = false;
        for (Element el : doc.getAllElements()) {
            for (Node node : el.childNodes()) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    assertFalse(StringUtil.isBlank(textNode.text()));
    }
    }
    }
    }

    @Test
    public void testHasTextWhenIterating_2_oe() {
        // https://github.com/jhy/jsoup/issues/1170
        Document doc = Jsoup.parse("<div>One <p>Two <p>Three");
        boolean foundFirst = false;
        for (Element el : doc.getAllElements()) {
            for (Node node : el.childNodes()) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    // removed other assertion
                    if (!foundFirst) {
                        foundFirst = true;
                        assertEquals("One ", textNode.text());
    }
    }
    }
    }
    }

    @Test
    public void testHasTextWhenIterating_3_oe() {
        // https://github.com/jhy/jsoup/issues/1170
        Document doc = Jsoup.parse("<div>One <p>Two <p>Three");
        boolean foundFirst = false;
        for (Element el : doc.getAllElements()) {
            for (Node node : el.childNodes()) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    // removed other assertion
                    if (!foundFirst) {
                        foundFirst = true;
                        // removed other assertion
                        assertEquals("One ", textNode.getWholeText());
    }
    }
    }
    }
    }

    @Test
    public void testHasTextWhenIterating_4_oe() {
        // https://github.com/jhy/jsoup/issues/1170
        Document doc = Jsoup.parse("<div>One <p>Two <p>Three");
        boolean foundFirst = false;
        for (Element el : doc.getAllElements()) {
            for (Node node : el.childNodes()) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    // removed other assertion
                    if (!foundFirst) {
                        foundFirst = true;
                        // removed other assertion
                        // removed other assertion
                    }
                }
            }
        }
        assertTrue(foundFirst);
    }

}
