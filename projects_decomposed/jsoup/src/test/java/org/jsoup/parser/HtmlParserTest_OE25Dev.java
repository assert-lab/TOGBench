package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.integration.ParseTest;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.*;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.jsoup.parser.ParseSettings.preserveCase;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Parser
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class HtmlParserTest_OE25Dev {

    @Test public void handlesQuotesInCommentsInScripts() {
        String html = "<script>\n" +
            "  <!--\n" +
            "    document.write('</scr' + 'ipt>');\n" +
            "  // -->\n" +
            "</script>";
        Document node = Jsoup.parseBodyFragment(html);
        assertEquals("<script>\n" + " <!--\n" + " document.write('</scr' + 'ipt>');\n" + " // -->\n" + "</script>",node.body().html());
    }

    // form tests

    private boolean didAddElements(String input) {
        // two passes, one as XML and one as HTML. XML does not vivify missing/optional tags
        Document html = Jsoup.parse(input);
        Document xml = Jsoup.parse(input, "", Parser.xmlParser());

        int htmlElementCount = html.getAllElements().size();
        int xmlElementCount = xml.getAllElements().size();
        return htmlElementCount > xmlElementCount;
    }


}