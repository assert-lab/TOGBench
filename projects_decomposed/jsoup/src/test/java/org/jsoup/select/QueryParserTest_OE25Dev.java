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

    @Test public void testConsumeSubQuery_1_oe() {
        Document doc = Jsoup.parse("<html><head>h</head><body>" +
                "<li><strong>l1</strong></li>" +
                "<a><li><strong>l2</strong></li></a>" +
                "<p><strong>yes</strong></p>" +
                "</body></html>");
        assertEquals("l1 l2 yes", doc.body().select(">p>strong,>*>li>strong").text());
        }

    @Test public void testConsumeSubQuery_2_oe() {
        Document doc = Jsoup.parse("<html><head>h</head><body>" +
                "<li><strong>l1</strong></li>" +
                "<a><li><strong>l2</strong></li></a>" +
                "<p><strong>yes</strong></p>" +
                "</body></html>");
        assertEquals("l2 yes", doc.select("body>p>strong,body>*>li>strong").text());
        }

    @Test public void testConsumeSubQuery_3_oe() {
        Document doc = Jsoup.parse("<html><head>h</head><body>" +
                "<li><strong>l1</strong></li>" +
                "<a><li><strong>l2</strong></li></a>" +
                "<p><strong>yes</strong></p>" +
                "</body></html>");
        assertEquals("yes", doc.select(">body>*>li>strong,>body>p>strong").text());
        }

    @Test public void testConsumeSubQuery_4_oe() {
        Document doc = Jsoup.parse("<html><head>h</head><body>" +
                "<li><strong>l1</strong></li>" +
                "<a><li><strong>l2</strong></li></a>" +
                "<p><strong>yes</strong></p>" +
                "</body></html>");
        assertEquals("l2", doc.select(">body>p>strong,>body>*>li>strong").text());
        }

    @Test public void testOrGetsCorrectPrecedence_1_oe() {
        Evaluator eval = QueryParser.parse("a b, c d, e f");
        assertTrue(eval instanceof CombiningEvaluator.Or);
        }

    @Test public void testOrGetsCorrectPrecedence_2_oe() {
        Evaluator eval = QueryParser.parse("a b, c d, e f");
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;
        assertEquals(3, or.evaluators.size());
        }

    @Test public void testOrGetsCorrectPrecedence_3_oe() {
        Evaluator eval = QueryParser.parse("a b, c d, e f");
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;
        for (Evaluator innerEval: or.evaluators) {
            assertTrue(innerEval instanceof CombiningEvaluator.And);
        }
        }

    @Test public void testOrGetsCorrectPrecedence_4_oe() {
        Evaluator eval = QueryParser.parse("a b, c d, e f");
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;
        for (Evaluator innerEval: or.evaluators) {
            CombiningEvaluator.And and = (CombiningEvaluator.And) innerEval;
            assertEquals(2, and.evaluators.size());
        }
        }

    @Test public void testOrGetsCorrectPrecedence_5_oe() {
        Evaluator eval = QueryParser.parse("a b, c d, e f");
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;
        for (Evaluator innerEval: or.evaluators) {
            CombiningEvaluator.And and = (CombiningEvaluator.And) innerEval;
            assertTrue(and.evaluators.get(0) instanceof StructuralEvaluator.Parent);
        }
        }

    @Test public void testOrGetsCorrectPrecedence_6_oe() {
        Evaluator eval = QueryParser.parse("a b, c d, e f");
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;
        for (Evaluator innerEval: or.evaluators) {
            CombiningEvaluator.And and = (CombiningEvaluator.And) innerEval;
            assertTrue(and.evaluators.get(1) instanceof Evaluator.Tag);
        }
        }

    @Test public void testParsesMultiCorrectly_1_oe() {
        String query = ".foo > ol, ol > li + li";
        Evaluator eval = QueryParser.parse(query);
        assertTrue(eval instanceof CombiningEvaluator.Or);
        }

    @Test public void testParsesMultiCorrectly_2_oe() {
        String query = ".foo > ol, ol > li + li";
        Evaluator eval = QueryParser.parse(query);
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;
        assertEquals(2, or.evaluators.size());
        }

    @Test public void testParsesMultiCorrectly_3_oe() {
        String query = ".foo > ol, ol > li + li";
        Evaluator eval = QueryParser.parse(query);
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;

        CombiningEvaluator.And andLeft = (CombiningEvaluator.And) or.evaluators.get(0);
        CombiningEvaluator.And andRight = (CombiningEvaluator.And) or.evaluators.get(1);

        assertEquals(".foo > ol", andLeft.toString());
        }

    @Test public void testParsesMultiCorrectly_4_oe() {
        String query = ".foo > ol, ol > li + li";
        Evaluator eval = QueryParser.parse(query);
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;

        CombiningEvaluator.And andLeft = (CombiningEvaluator.And) or.evaluators.get(0);
        CombiningEvaluator.And andRight = (CombiningEvaluator.And) or.evaluators.get(1);

        assertEquals(2, andLeft.evaluators.size());
        }

    @Test public void testParsesMultiCorrectly_5_oe() {
        String query = ".foo > ol, ol > li + li";
        Evaluator eval = QueryParser.parse(query);
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;

        CombiningEvaluator.And andLeft = (CombiningEvaluator.And) or.evaluators.get(0);
        CombiningEvaluator.And andRight = (CombiningEvaluator.And) or.evaluators.get(1);

        assertEquals("ol > li + li", andRight.toString());
        }

    @Test public void testParsesMultiCorrectly_6_oe() {
        String query = ".foo > ol, ol > li + li";
        Evaluator eval = QueryParser.parse(query);
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;

        CombiningEvaluator.And andLeft = (CombiningEvaluator.And) or.evaluators.get(0);
        CombiningEvaluator.And andRight = (CombiningEvaluator.And) or.evaluators.get(1);

        assertEquals(2, andRight.evaluators.size());
        }

    @Test public void testParsesMultiCorrectly_7_oe() {
        String query = ".foo > ol, ol > li + li";
        Evaluator eval = QueryParser.parse(query);
        CombiningEvaluator.Or or = (CombiningEvaluator.Or) eval;

        CombiningEvaluator.And andLeft = (CombiningEvaluator.And) or.evaluators.get(0);
        CombiningEvaluator.And andRight = (CombiningEvaluator.And) or.evaluators.get(1);

        assertEquals(query, eval.toString());
        }

    @Test public void exceptionOnUncloseAttribute_1_oe() throws Exception {
        try {
    QueryParser.parse("section > a[href=\"]");
    fail("Selector.SelectorParseException");
} catch (Selector.SelectorParseException e) {
}
        }

    @Test public void testParsesSingleQuoteInContains_1_oe() throws Exception {
        try {
    QueryParser.parse("p:contains(One \" One)");
    fail("Selector.SelectorParseException");
} catch (Selector.SelectorParseException e) {
}
        }

    @Test public void exceptOnEmptySelector_1_oe() throws Exception {
        try {
    QueryParser.parse("");
    fail("Selector.SelectorParseException");
} catch (Selector.SelectorParseException e) {
}
        }

    @Test public void exceptOnNullSelector_1_oe() throws Exception {
        try {
    QueryParser.parse(null);
    fail("Selector.SelectorParseException");
} catch (Selector.SelectorParseException e) {
}
        }

    @Test public void okOnSpacesForeAndAft_1_oe() {
        Evaluator parse = QueryParser.parse(" span div  ");
        assertEquals("span div", parse.toString());
        }

    @Test public void structuralEvaluatorsToString_1_oe() {
        String q = "a:not(:has(span.foo)) b d > e + f ~ g";
        Evaluator parse = QueryParser.parse(q);
        assertEquals(q, parse.toString());
        }

}
