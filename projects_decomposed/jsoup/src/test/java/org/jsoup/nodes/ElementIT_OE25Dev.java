package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ElementIT_OE25Dev {

    @Test
    public void testFastReparent_1_oe() {
        StringBuilder htmlBuf = new StringBuilder();
        int rows = 300000;
        for (int i = 1; i <= rows; i++) {
            htmlBuf
                .append("<p>El-")
                .append(i)
                .append("</p>");
        }
        String html = htmlBuf.toString();
        Document doc = Jsoup.parse(html);
        long start = System.currentTimeMillis();

        Element wrapper = new Element("div");
        List<Node> childNodes = doc.body().childNodes();
        wrapper.insertChildren(0, childNodes);

        long runtime = System.currentTimeMillis() - start;
        assertEquals(rows, wrapper.childNodes.size());
    }

    @Test
    public void testFastReparent_2_oe() {
        StringBuilder htmlBuf = new StringBuilder();
        int rows = 300000;
        for (int i = 1; i <= rows; i++) {
            htmlBuf
                .append("<p>El-")
                .append(i)
                .append("</p>");
        }
        String html = htmlBuf.toString();
        Document doc = Jsoup.parse(html);
        long start = System.currentTimeMillis();

        Element wrapper = new Element("div");
        List<Node> childNodes = doc.body().childNodes();
        wrapper.insertChildren(0, childNodes);

        long runtime = System.currentTimeMillis() - start;
        // removed other assertion
        assertEquals(rows,childNodes.size());// child nodes is a wrapper,so still there assertEquals(0,doc.body().childNodes().size());// but on a fresh look,all gone doc.body().empty().appendChild(wrapper);
    }

    @Test
    public void testFastReparentExistingContent_1_oe() {
        StringBuilder htmlBuf = new StringBuilder();
        int rows = 300000;
        for (int i = 1; i <= rows; i++) {
            htmlBuf
                .append("<p>El-")
                .append(i)
                .append("</p>");
        }
        String html = htmlBuf.toString();
        Document doc = Jsoup.parse(html);
        long start = System.currentTimeMillis();

        Element wrapper = new Element("div");
        wrapper.append("<p>Prior Content</p>");
        wrapper.append("<p>End Content</p>");
        assertEquals(2, wrapper.childNodes.size());
    }

    @Test
    public void testFastReparentExistingContent_2_oe() {
        StringBuilder htmlBuf = new StringBuilder();
        int rows = 300000;
        for (int i = 1; i <= rows; i++) {
            htmlBuf
                .append("<p>El-")
                .append(i)
                .append("</p>");
        }
        String html = htmlBuf.toString();
        Document doc = Jsoup.parse(html);
        long start = System.currentTimeMillis();

        Element wrapper = new Element("div");
        wrapper.append("<p>Prior Content</p>");
        wrapper.append("<p>End Content</p>");
        // removed other assertion

        List<Node> childNodes = doc.body().childNodes();
        wrapper.insertChildren(1, childNodes);

        long runtime = System.currentTimeMillis() - start;
        assertEquals(rows + 2, wrapper.childNodes.size());
    }

    @Test
    public void testFastReparentExistingContent_3_oe() {
        StringBuilder htmlBuf = new StringBuilder();
        int rows = 300000;
        for (int i = 1; i <= rows; i++) {
            htmlBuf
                .append("<p>El-")
                .append(i)
                .append("</p>");
        }
        String html = htmlBuf.toString();
        Document doc = Jsoup.parse(html);
        long start = System.currentTimeMillis();

        Element wrapper = new Element("div");
        wrapper.append("<p>Prior Content</p>");
        wrapper.append("<p>End Content</p>");
        // removed other assertion

        List<Node> childNodes = doc.body().childNodes();
        wrapper.insertChildren(1, childNodes);

        long runtime = System.currentTimeMillis() - start;
        // removed other assertion
        assertEquals(rows,childNodes.size());// child nodes is a wrapper,so still there assertEquals(0,doc.body().childNodes().size());// but on a fresh look,all gone doc.body().empty().appendChild(wrapper);
    }

}
