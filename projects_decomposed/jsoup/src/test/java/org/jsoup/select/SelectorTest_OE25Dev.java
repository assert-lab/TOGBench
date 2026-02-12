package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.MultiLocaleExtension.MultiLocaleTest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests that the selector selects correctly.
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class SelectorTest_OE25Dev {

    // for http://github.com/jhy/jsoup/issues#issue/10

    // for http://github.com/jhy/jsoup/issues#issue/13

    @MultiLocaleTest
    public void containsOwn(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> igor</p>");
        Elements ps = doc.select("p:containsOwn(Hello IGOR)");
        assertEquals(1, ps.size());
        assertEquals("1", ps.first().id());

        assertEquals(0, doc.select("p:containsOwn(there)").size());

        Document doc2 = Jsoup.parse("<p>Hello <b>there</b> IGOR</p>");
        assertEquals(1, doc2.select("p:containsOwn(igor)").size());

    }

    @MultiLocaleTest
    public void containsData(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        assertEquals(2,dataEls1.size());// body and script assertEquals(1,dataEls2.size());
        assertEquals(dataEls1.last(), dataEls2.first());
        assertEquals("<script>FUNCTION</script>", dataEls2.outerHtml());
        assertEquals(1, dataEls3.size());
        assertEquals("span", dataEls3.first().tagName());
        assertEquals(3, dataEls4.size());
        assertEquals("body", dataEls4.first().tagName());
        assertEquals("script", dataEls4.get(1).tagName());
        assertEquals("span", dataEls4.get(2).tagName());
        assertEquals(1, dataEls5.size());
    }

    // https://github.com/jhy/jsoup/issues/1257
    private final String mixedCase =
        "<html xmlns:n=\"urn:ns\"><n:mixedCase>text</n:mixedCase></html>";
    private final String lowercase =
        "<html xmlns:n=\"urn:ns\"><n:lowercase>text</n:lowercase></html>";


}
