package org.jsoup.integration;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 Check that we can extend Safelist methods
 */
public class SafelistExtensionTest_OE25Dev {

    // passes tags and attributes starting with "open"
    private static class OpenSafelist extends Safelist {
        public OpenSafelist(Safelist safelist) {
            super(safelist);
        }

        @Override
        protected boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
            if (attr.getKey().startsWith("open"))
                return true;
            return super.isSafeAttribute(tagName, el, attr);
        }

        @Override
        protected boolean isSafeTag(String tag) {
            if (tag.startsWith("open"))
                return true;
            return super.isSafeTag(tag);
        }
    }


}
