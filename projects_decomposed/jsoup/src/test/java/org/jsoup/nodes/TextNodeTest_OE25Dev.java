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

    @Test public void testBlank_1_oe() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        }

    @Test public void testBlank_2_oe() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(two.isBlank());
        }

    @Test public void testBlank_3_oe() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(three.isBlank());
        }

    @Test public void testBlank_4_oe() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertFalse(four.isBlank());
        }

    @Test public void testBlank_5_oe() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertFalse(five.isBlank());
        }

    @Test public void testTextBean_1_oe() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        }

    @Test public void testTextBean_2_oe() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        }

    @Test public void testTextBean_3_oe() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        TextNode spanText = (TextNode) span.childNode(0);

        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        }

    @Test public void testTextBean_4_oe() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        TextNode spanText = (TextNode) span.childNode(0);

        TextNode tn = (TextNode) p.childNode(2);

        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));
        }

    @Test public void testTextBean_5_oe() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        TextNode spanText = (TextNode) span.childNode(0);

        TextNode tn = (TextNode) p.childNode(2);

        tn.text(" POW!");

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        }

    @Test public void testTextBean_6_oe() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        TextNode spanText = (TextNode) span.childNode(0);

        TextNode tn = (TextNode) p.childNode(2);

        tn.text(" POW!");

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
        }

    @Test public void testSplitText_1_oe() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        }

    @Test public void testSplitText_2_oe() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("there", tail.getWholeText());
        }

    @Test public void testSplitText_3_oe() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        }

    @Test public void testSplitText_4_oe() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.text("there!");
        assertSame(tn.parent(), tail.parent());
        }

    @Test public void testSplitAnEmbolden_1_oe() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct;
        }

    @Test public void testWithSupplementaryCharacter_1_oe(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        }

    @Test public void testLeadNodesHaveNoChildren_1_oe() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
        }

    @Test public void testSpaceNormalise_1_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words
        assertEquals(whole, tn.getWholeText());
        }

    @Test public void testSpaceNormalise_2_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words
        assertEquals(norm, tn.text());
        }

    @Test public void testSpaceNormalise_3_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words
        assertEquals(norm, tn.outerHtml());
        }

    @Test public void testSpaceNormalise_4_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words
        assertEquals(norm, tn.toString());
        }

    @Test public void testSpaceNormalise_5_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        assertEquals(whole, tn.getWholeText());
        }

    @Test public void testSpaceNormalise_6_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        assertEquals(norm, tn.text());
        }

    @Test public void testSpaceNormalise_7_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        assertEquals(norm, tn.outerHtml());
        }

    @Test public void testSpaceNormalise_8_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context
        assertEquals(norm, tn.toString());
        }

    @Test public void testSpaceNormalise_9_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context

        assertEquals("<p>" + norm + "</p>", el.outerHtml());
        }

    @Test public void testSpaceNormalise_10_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context

        assertEquals(norm, el.html());
        }

    @Test public void testSpaceNormalise_11_oe() {
        String whole = "Two  spaces";
        String norm = "Two spaces";
        TextNode tn = new TextNode(whole); // there are 2 spaces between the words

        Element el = new Element("p");
        el.appendChild(tn); // this used to change the context

        assertEquals(whole, el.wholeText());
        }

    @Test
    public void testClone_1_oe() {
        TextNode x = new TextNode("zzz");
        TextNode y = x.clone();

        assertNotSame(x, y);
    }

    @Test
    public void testClone_2_oe() {
        TextNode x = new TextNode("zzz");
        TextNode y = x.clone();

        assertEquals(x.outerHtml(), y.outerHtml());
    }

    @Test
    public void testClone_3_oe() {
        TextNode x = new TextNode("zzz");
        TextNode y = x.clone();


        y.text("yyy");
        assertNotEquals(x.outerHtml(), y.outerHtml());
    }

    @Test
    public void testClone_4_oe() {
        TextNode x = new TextNode("zzz");
        TextNode y = x.clone();


        y.text("yyy");
        assertEquals("zzz", x.text());
    }

    @Test
    public void testClone_5_oe() {
        TextNode x = new TextNode("zzz");
        TextNode y = x.clone();


        y.text("yyy");

        x.attributes(); // already cloned so no impact
        y.text("xxx");
        assertEquals("zzz", x.text());
    }

    @Test
    public void testClone_6_oe() {
        TextNode x = new TextNode("zzz");
        TextNode y = x.clone();


        y.text("yyy");

        x.attributes(); // already cloned so no impact
        y.text("xxx");
        assertEquals("xxx", y.text());
    }

    @Test
    public void testCloneAfterAttributesHit_1_oe() {
        TextNode x = new TextNode("zzz");
        x.attributes(); // moves content from leafnode value to attributes, which were missed in clone
        TextNode y = x.clone();
        y.text("xxx");
        assertEquals("zzz", x.text());
    }

    @Test
    public void testCloneAfterAttributesHit_2_oe() {
        TextNode x = new TextNode("zzz");
        x.attributes(); // moves content from leafnode value to attributes, which were missed in clone
        TextNode y = x.clone();
        y.text("xxx");
        assertEquals("xxx", y.text());
    }

    @Test
    public void testHasTextWhenIterating_1_oe() {
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
        Document doc = Jsoup.parse("<div>One <p>Two <p>Three");
        boolean foundFirst = false;
        for (Element el : doc.getAllElements()) {
            for (Node node : el.childNodes()) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
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
        Document doc = Jsoup.parse("<div>One <p>Two <p>Three");
        boolean foundFirst = false;
        for (Element el : doc.getAllElements()) {
            for (Node node : el.childNodes()) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    if (!foundFirst) {
                        foundFirst = true;
                        assertEquals("One ", textNode.getWholeText());
    }
    }
    }
    }
    }

    @Test
    public void testHasTextWhenIterating_4_oe() {
        Document doc = Jsoup.parse("<div>One <p>Two <p>Three");
        boolean foundFirst = false;
        for (Element el : doc.getAllElements()) {
            for (Node node : el.childNodes()) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    if (!foundFirst) {
                        foundFirst = true;
                    }
                }
            }
        }
        assertTrue(foundFirst);
    }

}
