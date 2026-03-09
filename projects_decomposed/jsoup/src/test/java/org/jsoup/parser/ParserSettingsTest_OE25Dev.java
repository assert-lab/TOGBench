package org.jsoup.parser;

import org.jsoup.MultiLocaleExtension.MultiLocaleTest;
import org.jsoup.nodes.Attributes;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserSettingsTest_OE25Dev {
    @MultiLocaleTest
    public void caseSupport(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);

        assertEquals("IMG", bothOn.normalizeTag("IMG"));
        assertEquals("ID", bothOn.normalizeAttribute("ID"));

        assertEquals("img", bothOff.normalizeTag("IMG"));
        assertEquals("id", bothOff.normalizeAttribute("ID"));

        assertEquals("IMG", tagOn.normalizeTag("IMG"));
        assertEquals("id", tagOn.normalizeAttribute("ID"));

        assertEquals("img", attrOn.normalizeTag("IMG"));
        assertEquals("ID", attrOn.normalizeAttribute("ID"));
    }

    @MultiLocaleTest
    public void attributeCaseNormalization(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings parseSettings = new ParseSettings(false, false);
        String normalizedAttribute = parseSettings.normalizeAttribute("HIDDEN");

        assertEquals("hidden", normalizedAttribute);
    }

    @MultiLocaleTest
    public void attributesCaseNormalization(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings parseSettings = new ParseSettings(false, false);
        Attributes attributes = new Attributes();
        attributes.put("ITEM", "1");

        Attributes normalizedAttributes = parseSettings.normalizeAttributes(attributes);

        assertEquals("item", normalizedAttributes.asList().get(0).getKey());
    }

@MultiLocaleTest
    public void caseSupport_1_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);

        assertEquals("IMG", bothOn.normalizeTag("IMG"));
    }

@MultiLocaleTest
    public void caseSupport_2_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);

        assertEquals("ID", bothOn.normalizeAttribute("ID"));
    }

@MultiLocaleTest
    public void caseSupport_3_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);


        assertEquals("img", bothOff.normalizeTag("IMG"));
    }

@MultiLocaleTest
    public void caseSupport_4_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);


        assertEquals("id", bothOff.normalizeAttribute("ID"));
    }

@MultiLocaleTest
    public void caseSupport_5_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);



        assertEquals("IMG", tagOn.normalizeTag("IMG"));
    }

@MultiLocaleTest
    public void caseSupport_6_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);



        assertEquals("id", tagOn.normalizeAttribute("ID"));
    }

@MultiLocaleTest
    public void caseSupport_7_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);




        assertEquals("img", attrOn.normalizeTag("IMG"));
    }

@MultiLocaleTest
    public void caseSupport_8_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings bothOn = new ParseSettings(true, true);
        ParseSettings bothOff = new ParseSettings(false, false);
        ParseSettings tagOn = new ParseSettings(true, false);
        ParseSettings attrOn = new ParseSettings(false, true);




        assertEquals("ID", attrOn.normalizeAttribute("ID"));
    }

@MultiLocaleTest
    public void attributeCaseNormalization_1_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings parseSettings = new ParseSettings(false, false);
        String normalizedAttribute = parseSettings.normalizeAttribute("HIDDEN");

        assertEquals("hidden", normalizedAttribute);
    }

@MultiLocaleTest
    public void attributesCaseNormalization_1_oe(Locale locale) {
        Locale.setDefault(locale);

        ParseSettings parseSettings = new ParseSettings(false, false);
        Attributes attributes = new Attributes();
        attributes.put("ITEM", "1");

        Attributes normalizedAttributes = parseSettings.normalizeAttributes(attributes);

        assertEquals("item", normalizedAttributes.asList().get(0).getKey());
    }

}
