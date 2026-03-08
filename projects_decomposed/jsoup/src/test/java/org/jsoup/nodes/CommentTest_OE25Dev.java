package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest_OE25Dev {
    private Comment comment = new Comment(" This is one heck of a comment! ");
    private Comment decl = new Comment("?xml encoding='ISO-8859-1'?");

    @Test
    public void nodeName() {
        assertEquals("#comment", comment.nodeName());
    }

    @Test
    public void getData() {
        assertEquals(" This is one heck of a comment! ", comment.getData());
    }

    @Test
    public void testToString() {
        assertEquals("<!-- This is one heck of a comment! -->", comment.toString());

        Document doc = Jsoup.parse("<div><!-- comment--></div>");
        assertEquals("<div>\n <!-- comment-->\n</div>", doc.body().html());

        doc = Jsoup.parse("<p>One<!-- comment -->Two</p>");
        assertEquals("<p>One<!-- comment -->Two</p>", doc.body().html());
        assertEquals("OneTwo", doc.text());
    }

    @Test
    public void testHtmlNoPretty() {
        Document doc = Jsoup.parse("<!-- a simple comment -->");
        doc.outputSettings().prettyPrint(false);
        assertEquals("<!-- a simple comment --><html><head></head><body></body></html>", doc.html());
        Node node = doc.childNode(0);
        Comment c1 = (Comment) node;
        assertEquals("<!-- a simple comment -->", c1.outerHtml());
    }

    @Test
    public void testClone() {
        Comment c1 = comment.clone();
        assertNotSame(comment, c1);
        assertEquals(comment.getData(), comment.getData());
        c1.setData("New");
        assertEquals("New", c1.getData());
        assertNotEquals(c1.getData(), comment.getData());
    }

    @Test
    public void isXmlDeclaration() {
        assertFalse(comment.isXmlDeclaration());
        assertTrue(decl.isXmlDeclaration());
    }

    @Test
    public void asXmlDeclaration() {
        XmlDeclaration xmlDeclaration = decl.asXmlDeclaration();
        assertNotNull(xmlDeclaration);
    }

    @Test
    public void nodeName_1_oe() {
        assertEquals("#comment", comment.nodeName());
    }

    @Test
    public void getData_1_oe() {
        assertEquals(" This is one heck of a comment! ", comment.getData());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("<!-- This is one heck of a comment! -->", comment.toString());
    }

    @Test
    public void testToString_2_oe() {
        // removed other assertion

        Document doc = Jsoup.parse("<div><!-- comment--></div>");
        assertEquals("<div>\n <!-- comment-->\n</div>", doc.body().html());
    }

    @Test
    public void testToString_3_oe() {
        // removed other assertion

        Document doc = Jsoup.parse("<div><!-- comment--></div>");
        // removed other assertion

        doc = Jsoup.parse("<p>One<!-- comment -->Two</p>");
        assertEquals("<p>One<!-- comment -->Two</p>", doc.body().html());
    }

    @Test
    public void testToString_4_oe() {
        // removed other assertion

        Document doc = Jsoup.parse("<div><!-- comment--></div>");
        // removed other assertion

        doc = Jsoup.parse("<p>One<!-- comment -->Two</p>");
        // removed other assertion
        assertEquals("OneTwo", doc.text());
    }

    @Test
    public void testHtmlNoPretty_1_oe() {
        Document doc = Jsoup.parse("<!-- a simple comment -->");
        doc.outputSettings().prettyPrint(false);
        assertEquals("<!-- a simple comment --><html><head></head><body></body></html>", doc.html());
    }

    @Test
    public void testHtmlNoPretty_2_oe() {
        Document doc = Jsoup.parse("<!-- a simple comment -->");
        doc.outputSettings().prettyPrint(false);
        // removed other assertion
        Node node = doc.childNode(0);
        Comment c1 = (Comment) node;
        assertEquals("<!-- a simple comment -->", c1.outerHtml());
    }

    @Test
    public void testClone_1_oe() {
        Comment c1 = comment.clone();
        assertNotSame(comment, c1);
    }

    @Test
    public void testClone_2_oe() {
        Comment c1 = comment.clone();
        // removed other assertion
        assertEquals(comment.getData(), comment.getData());
    }

    @Test
    public void testClone_3_oe() {
        Comment c1 = comment.clone();
        // removed other assertion
        // removed other assertion
        c1.setData("New");
        assertEquals("New", c1.getData());
    }

    @Test
    public void testClone_4_oe() {
        Comment c1 = comment.clone();
        // removed other assertion
        // removed other assertion
        c1.setData("New");
        // removed other assertion
        assertNotEquals(c1.getData(), comment.getData());
    }

    @Test
    public void isXmlDeclaration_1_oe() {
        assertFalse(comment.isXmlDeclaration());
    }

    @Test
    public void isXmlDeclaration_2_oe() {
        // removed other assertion
        assertTrue(decl.isXmlDeclaration());
    }

    @Test
    public void asXmlDeclaration_1_oe() {
        XmlDeclaration xmlDeclaration = decl.asXmlDeclaration();
        assertNotNull(xmlDeclaration);
    }

}
