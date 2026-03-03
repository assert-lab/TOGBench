/*
 *  Copyright 2001-2014 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.format;

import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Period;

/**
 * This class is a Junit unit test for PeriodFormat.
 *
 * @author Stephen Colebourne
 */
public class TestPeriodFormat_OE25Dev extends TestCase {

    private static final Locale EN = new Locale("en");
    private static final Locale FR = new Locale("fr");
    private static final Locale PT = new Locale("pt");
    private static final Locale ES = new Locale("es");
    private static final Locale DE = new Locale("de");
    private static final Locale NL = new Locale("nl");
    private static final Locale DA = new Locale("da");
    private static final Locale JA = new Locale("ja");
    private static final Locale PL = new Locale("pl");
    private static final Locale BG = new Locale("bg");
    private static final Locale CS = new Locale("cs");
    private static final Locale RU = new Locale("ru");

    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestPeriodFormat_OE25Dev.class);
    }

    public TestPeriodFormat_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        originalLocale = Locale.getDefault();
        Locale.setDefault(DE);
    }

    @Override
    protected void tearDown() throws Exception {
        Locale.setDefault(originalLocale);
        originalLocale = null;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // getDefault()
    //-----------------------------------------------------------------------
    public void test_getDefault_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6 ,7, 8);
        assertEquals("1 day,5 hours,6 minutes,7 seconds and 8 milliseconds",PeriodFormat.getDefault().print(p));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // wordBased() - default locale (de)
    //-----------------------------------------------------------------------
    public void test_wordBased_default() {
        Period p = new Period(0, 0, 0, 1, 5, 6 ,7, 8);
        assertEquals("1 Tag,5 Stunden,6 Minuten,7 Sekunden und 8 Millisekunden",PeriodFormat.wordBased().print(p));
    }

    //-----------------------------------------------------------------------
    // wordBased(Locale.FRENCH)
    //-----------------------------------------------------------------------
    public void test_wordBased_fr_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6 ,7, 8);
        assertEquals("1 jour,5 heures,6 minutes,7 secondes et 8 millisecondes",PeriodFormat.wordBased(FR).print(p));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // wordBased(Locale pt)
    //-----------------------------------------------------------------------
    public void test_wordBased_pt_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6 ,7, 8);
        assertEquals("1 dia,5 horas,6 minutos,7 segundos e 8 milissegundos",PeriodFormat.wordBased(PT).print(p));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // wordBased(Locale es)
    //-----------------------------------------------------------------------
    public void test_wordBased_es_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6 ,7, 8);
        assertEquals("1 d\u00EDa,5 horas,6 minutos,7 segundos y 8 milisegundos",PeriodFormat.wordBased(ES).print(p));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // wordBased(Locale de)
    //-----------------------------------------------------------------------
    public void test_wordBased_de_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6 ,7, 8);
        assertEquals("1 Tag,5 Stunden,6 Minuten,7 Sekunden und 8 Millisekunden",PeriodFormat.wordBased(DE).print(p));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // wordBased(Locale nl)
    //-----------------------------------------------------------------------
    public void test_wordBased_nl_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6 ,7, 8);
        assertEquals("1 dag,5 uur,6 minuten,7 seconden en 8 milliseconden",PeriodFormat.wordBased(NL).print(p));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // wordBased(Locale da)
    //-----------------------------------------------------------------------
    public void test_wordBased_da_formatMultiple() {
        Period p = new Period(2, 3, 4, 2, 5, 6 ,7, 8);
        assertEquals("2 \u00E5r,3 m\u00E5neder,4 uger,2 dage,5 timer,6 minutter,7 sekunder og 8 millisekunder",PeriodFormat.wordBased(DA).print(p));
    }

    //-----------------------------------------------------------------------
    public void test_wordBased_da_formatSinglular() {
        Period p = new Period(1, 1, 1, 1, 1, 1, 1, 1);
        assertEquals("1 \u00E5r,1 m\u00E5ned,1 uge,1 dag,1 time,1 minut,1 sekund og 1 millisekund",PeriodFormat.wordBased(DA).print(p));
    }
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    // wordBased(Locale ja)
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    // -----------------------------------------------------------------------
    // wordBased(new Locale("pl")
    // -----------------------------------------------------------------------
    public void test_wordBased_pl_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 dzie\u0144,5 godzin,6 minut,7 sekund i 8 milisekund",PeriodFormat.wordBased(PL).print(p));
    }

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------
    
    // -----------------------------------------------------------------------
    
    // -----------------------------------------------------------------------
    // wordBased(new Locale("bg")
    // -----------------------------------------------------------------------
    public void test_wordBased_bg_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 ден,5 часа,6 минути,7 секунди и 8 милисекунди",PeriodFormat.wordBased(BG).print(p));
    }

    // -----------------------------------------------------------------------
    // wordBased(new Locale("cs")
    // -----------------------------------------------------------------------
    public void test_wordBased_cs_formatStandard() {
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 den,5 hodin,6 minut,7 sekund a 8 milisekund",PeriodFormat.wordBased(CS).print(p));
    }

    // -----------------------------------------------------------------------
    // wordBased(new Locale("ru")
    // -----------------------------------------------------------------------
    public void test_wordBased_ru_formatStandard() {
        Period p = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals("1 год,2 месяца,3 недели,4 дня,5 часов,6 минут,7 секунд и 8 миллисекунд",PeriodFormat.wordBased(RU).print(p));
    }

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // Cross check languages
    //-----------------------------------------------------------------------
    public void test_wordBased_fr_from_de() {
        Locale.setDefault(DE);
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 jour,5 heures,6 minutes,7 secondes et 8 millisecondes",PeriodFormat.wordBased(FR).print(p));
    }

    public void test_wordBased_fr_from_nl() {
        Locale.setDefault(NL);
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 jour,5 heures,6 minutes,7 secondes et 8 millisecondes",PeriodFormat.wordBased(FR).print(p));
    }

    public void test_wordBased_en_from_de() {
        Locale.setDefault(DE);
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 day,5 hours,6 minutes,7 seconds and 8 milliseconds",PeriodFormat.wordBased(EN).print(p));
    }

    public void test_wordBased_en_from_nl() {
        Locale.setDefault(NL);
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 day,5 hours,6 minutes,7 seconds and 8 milliseconds",PeriodFormat.wordBased(EN).print(p));
    }

    public void test_wordBased_en_from_pl() {
        Locale.setDefault(PL);
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 day,5 hours,6 minutes,7 seconds and 8 milliseconds",PeriodFormat.wordBased(EN).print(p));
    }

    public void test_wordBased_pl_from_fr() {
        Locale.setDefault(FR);
        Period p = new Period(0, 0, 0, 1, 5, 6, 7, 8);
        assertEquals("1 dzie\u0144,5 godzin,6 minut,7 sekund i 8 milisekund",PeriodFormat.wordBased(PL).print(p));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testSubclassableConstructor_1_oe() {
        PeriodFormat f = new PeriodFormat() {
        };
        assertNotNull(f);
    }

    public void test_getDefault_FormatOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals("2 days",PeriodFormat.getDefault().print(p));
    }

    public void test_getDefault_formatTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals("2 days and 5 hours",PeriodFormat.getDefault().print(p));
    }

    public void test_getDefault_parseOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals(p,PeriodFormat.getDefault().parsePeriod("2 days"));
    }

    public void test_getDefault_parseTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals(p,PeriodFormat.getDefault().parsePeriod("2 days and 5 hours"));
    }

    public void test_getDefault_checkRedundantSeparator_2_oe() {
        try {
            PeriodFormat.getDefault().parsePeriod("2 days and 5 hours ");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class,e.getClass());
    }
    }

    public void test_getDefault_cached_1_oe() {
        assertSame(PeriodFormat.getDefault(),PeriodFormat.getDefault());
    }

    public void test_wordBased_fr_FormatOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals("2 jours",PeriodFormat.wordBased(FR).print(p));
    }

    public void test_wordBased_fr_formatTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals("2 jours et 5 heures",PeriodFormat.wordBased(FR).print(p));
    }

    public void test_wordBased_fr_parseOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals(p,PeriodFormat.wordBased(FR).parsePeriod("2 jours"));
    }

    public void test_wordBased_fr_parseTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals(p,PeriodFormat.wordBased(FR).parsePeriod("2 jours et 5 heures"));
    }

    public void test_wordBased_fr_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(FR),PeriodFormat.wordBased(FR));
    }

    public void test_wordBased_pt_FormatOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals("2 dias",PeriodFormat.wordBased(PT).print(p));
    }

    public void test_wordBased_pt_formatTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals("2 dias e 5 horas",PeriodFormat.wordBased(PT).print(p));
    }

    public void test_wordBased_pt_parseOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals(p,PeriodFormat.wordBased(PT).parsePeriod("2 dias"));
    }

    public void test_wordBased_pt_parseTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals(p,PeriodFormat.wordBased(PT).parsePeriod("2 dias e 5 horas"));
    }

    public void test_wordBased_pt_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(PT),PeriodFormat.wordBased(PT));
    }

    public void test_wordBased_es_FormatOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals("2 d\u00EDas",PeriodFormat.wordBased(ES).print(p));
    }

    public void test_wordBased_es_formatTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals("2 d\u00EDas y 5 horas",PeriodFormat.wordBased(ES).print(p));
    }

    public void test_wordBased_es_parseOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals(p,PeriodFormat.wordBased(ES).parsePeriod("2 d\u00EDas"));
    }

    public void test_wordBased_es_parseTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals(p,PeriodFormat.wordBased(ES).parsePeriod("2 d\u00EDas y 5 horas"));
    }

    public void test_wordBased_es_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(ES),PeriodFormat.wordBased(ES));
    }

    public void test_wordBased_de_FormatOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals("2 Tage",PeriodFormat.wordBased(DE).print(p));
    }

    public void test_wordBased_de_formatTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals("2 Tage und 5 Stunden",PeriodFormat.wordBased(DE).print(p));
    }

    public void test_wordBased_de_parseOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals(p,PeriodFormat.wordBased(DE).parsePeriod("2 Tage"));
    }

    public void test_wordBased_de_parseTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals(p,PeriodFormat.wordBased(DE).parsePeriod("2 Tage und 5 Stunden"));
    }

    public void test_wordBased_de_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(DE),PeriodFormat.wordBased(DE));
    }

    public void test_wordBased_nl_FormatOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals("2 dagen",PeriodFormat.wordBased(NL).print(p));
    }

    public void test_wordBased_nl_formatTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals("2 dagen en 5 uur",PeriodFormat.wordBased(NL).print(p));
    }

    public void test_wordBased_nl_parseOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals(p,PeriodFormat.wordBased(NL).parsePeriod("2 dagen"));
    }

    public void test_wordBased_nl_parseTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals(p,PeriodFormat.wordBased(NL).parsePeriod("2 dagen en 5 uur"));
    }

    public void test_wordBased_nl_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(NL),PeriodFormat.wordBased(NL));
    }

    public void test_wordBased_da_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(DA),PeriodFormat.wordBased(DA));
    }

    public void test_wordBased_ja_formatMultiple_1_oe() {
        Period p = new Period(2, 3, 4, 2, 5, 6 ,7, 8);
        assertEquals("2\u5E743\u304B\u67084\u9031\u95932\u65E55\u6642\u95936\u52067\u79D28\u30DF\u30EA\u79D2",PeriodFormat.wordBased(JA).print(p));
    }

    public void test_wordBased_ja_formatSingular_1_oe() {
        Period p = new Period(1, 1, 1, 1, 1, 1, 1, 1);
        assertEquals("1\u5E741\u304B\u67081\u9031\u95931\u65E51\u6642\u95931\u52061\u79D21\u30DF\u30EA\u79D2",PeriodFormat.wordBased(JA).print(p));
    }

    public void test_wordBased_ja_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(JA),PeriodFormat.wordBased(JA));
    }

    public void test_wordBased_ja_parseOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals(p,PeriodFormat.wordBased(JA).parsePeriod("2\u65E5"));
    }

    public void test_wordBased_ja_parseTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals(p,PeriodFormat.wordBased(JA).parsePeriod("2\u65E55\u6642\u9593"));
    }

    public void test_wordBased_ja_checkRedundantSeparator_2_oe() {
        try {
            PeriodFormat.wordBased(JA).parsePeriod("2\u65E5 ");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class,e.getClass());
    }
    }

    public void test_wordBased_pl_FormatOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals("2 dni",PeriodFormat.wordBased(PL).print(p));
    }

    public void test_wordBased_pl_formatTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals("2 dni i 5 godzin",PeriodFormat.wordBased(PL).print(p));
    }

    public void test_wordBased_pl_parseOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals(p,PeriodFormat.wordBased(PL).parsePeriod("2 dni"));
    }

    public void test_wordBased_pl_parseTwoFields_1_oe() {
        Period p = Period.days(2).withHours(5);
        assertEquals(p,PeriodFormat.wordBased(PL).parsePeriod("2 dni i 5 godzin"));
    }

    public void test_wordBased_pl_checkRedundantSeparator_2_oe() {
        try {
            PeriodFormat.wordBased(PL).parsePeriod("2 dni and 5 godzin ");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class,e.getClass());
    }
    }

    public void test_wordBased_pl_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(PL),PeriodFormat.wordBased(PL));
    }

    public void test_wordBased_pl_regEx_1_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("1 rok",pf.print(Period.years(1)));
    }

    public void test_wordBased_pl_regEx_2_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2 lata",pf.print(Period.years(2)));
    }

    public void test_wordBased_pl_regEx_3_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("5 lat",pf.print(Period.years(5)));
    }

    public void test_wordBased_pl_regEx_4_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("12 lat",pf.print(Period.years(12)));
    }

    public void test_wordBased_pl_regEx_5_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("15 lat",pf.print(Period.years(15)));
    }

    public void test_wordBased_pl_regEx_6_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("1112 lat",pf.print(Period.years(1112)));
    }

    public void test_wordBased_pl_regEx_7_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("1115 lat",pf.print(Period.years(1115)));
    }

    public void test_wordBased_pl_regEx_8_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2112 lat",pf.print(Period.years(2112)));
    }

    public void test_wordBased_pl_regEx_9_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2115 lat",pf.print(Period.years(2115)));
    }

    public void test_wordBased_pl_regEx_10_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2212 lat",pf.print(Period.years(2212)));
    }

    public void test_wordBased_pl_regEx_11_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2215 lat",pf.print(Period.years(2215)));
    }

    public void test_wordBased_pl_regEx_12_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("22 lata",pf.print(Period.years(22)));
    }

    public void test_wordBased_pl_regEx_13_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("25 lat",pf.print(Period.years(25)));
    }

    public void test_wordBased_pl_regEx_14_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("1122 lata",pf.print(Period.years(1122)));
    }

    public void test_wordBased_pl_regEx_15_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("1125 lat",pf.print(Period.years(1125)));
    }

    public void test_wordBased_pl_regEx_16_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2122 lata",pf.print(Period.years(2122)));
    }

    public void test_wordBased_pl_regEx_17_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2125 lat",pf.print(Period.years(2125)));
    }

    public void test_wordBased_pl_regEx_18_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2222 lata",pf.print(Period.years(2222)));
    }

    public void test_wordBased_pl_regEx_19_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        assertEquals("2225 lat",pf.print(Period.years(2225)));
    }

    public void test_wordBased_pl_regEx_20_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("1 miesi\u0105c",pf.print(Period.months(1)));
    }

    public void test_wordBased_pl_regEx_21_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2 miesi\u0105ce",pf.print(Period.months(2)));
    }

    public void test_wordBased_pl_regEx_22_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("5 miesi\u0119cy",pf.print(Period.months(5)));
    }

    public void test_wordBased_pl_regEx_23_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("12 miesi\u0119cy",pf.print(Period.months(12)));
    }

    public void test_wordBased_pl_regEx_24_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("15 miesi\u0119cy",pf.print(Period.months(15)));
    }

    public void test_wordBased_pl_regEx_25_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("1112 miesi\u0119cy",pf.print(Period.months(1112)));
    }

    public void test_wordBased_pl_regEx_26_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("1115 miesi\u0119cy",pf.print(Period.months(1115)));
    }

    public void test_wordBased_pl_regEx_27_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2112 miesi\u0119cy",pf.print(Period.months(2112)));
    }

    public void test_wordBased_pl_regEx_28_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2115 miesi\u0119cy",pf.print(Period.months(2115)));
    }

    public void test_wordBased_pl_regEx_29_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2212 miesi\u0119cy",pf.print(Period.months(2212)));
    }

    public void test_wordBased_pl_regEx_30_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2215 miesi\u0119cy",pf.print(Period.months(2215)));
    }

    public void test_wordBased_pl_regEx_31_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("22 miesi\u0105ce",pf.print(Period.months(22)));
    }

    public void test_wordBased_pl_regEx_32_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("25 miesi\u0119cy",pf.print(Period.months(25)));
    }

    public void test_wordBased_pl_regEx_33_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("1122 miesi\u0105ce",pf.print(Period.months(1122)));
    }

    public void test_wordBased_pl_regEx_34_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("1125 miesi\u0119cy",pf.print(Period.months(1125)));
    }

    public void test_wordBased_pl_regEx_35_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2122 miesi\u0105ce",pf.print(Period.months(2122)));
    }

    public void test_wordBased_pl_regEx_36_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2125 miesi\u0119cy",pf.print(Period.months(2125)));
    }

    public void test_wordBased_pl_regEx_37_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2222 miesi\u0105ce",pf.print(Period.months(2222)));
    }

    public void test_wordBased_pl_regEx_38_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        
        assertEquals("2225 miesi\u0119cy",pf.print(Period.months(2225)));
    }

    public void test_wordBased_pl_regEx_39_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("1 tydzie\u0144",pf.print(Period.weeks(1)));
    }

    public void test_wordBased_pl_regEx_40_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2 tygodnie",pf.print(Period.weeks(2)));
    }

    public void test_wordBased_pl_regEx_41_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("5 tygodni",pf.print(Period.weeks(5)));
    }

    public void test_wordBased_pl_regEx_42_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("12 tygodni",pf.print(Period.weeks(12)));
    }

    public void test_wordBased_pl_regEx_43_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("15 tygodni",pf.print(Period.weeks(15)));
    }

    public void test_wordBased_pl_regEx_44_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("1112 tygodni",pf.print(Period.weeks(1112)));
    }

    public void test_wordBased_pl_regEx_45_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("1115 tygodni",pf.print(Period.weeks(1115)));
    }

    public void test_wordBased_pl_regEx_46_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2112 tygodni",pf.print(Period.weeks(2112)));
    }

    public void test_wordBased_pl_regEx_47_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2115 tygodni",pf.print(Period.weeks(2115)));
    }

    public void test_wordBased_pl_regEx_48_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2212 tygodni",pf.print(Period.weeks(2212)));
    }

    public void test_wordBased_pl_regEx_49_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2215 tygodni",pf.print(Period.weeks(2215)));
    }

    public void test_wordBased_pl_regEx_50_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("22 tygodnie",pf.print(Period.weeks(22)));
    }

    public void test_wordBased_pl_regEx_51_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("25 tygodni",pf.print(Period.weeks(25)));
    }

    public void test_wordBased_pl_regEx_52_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("1122 tygodnie",pf.print(Period.weeks(1122)));
    }

    public void test_wordBased_pl_regEx_53_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("1125 tygodni",pf.print(Period.weeks(1125)));
    }

    public void test_wordBased_pl_regEx_54_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2122 tygodnie",pf.print(Period.weeks(2122)));
    }

    public void test_wordBased_pl_regEx_55_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2125 tygodni",pf.print(Period.weeks(2125)));
    }

    public void test_wordBased_pl_regEx_56_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2222 tygodnie",pf.print(Period.weeks(2222)));
    }

    public void test_wordBased_pl_regEx_57_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        

        assertEquals("2225 tygodni",pf.print(Period.weeks(2225)));
    }

    public void test_wordBased_pl_regEx_58_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        


        assertEquals("1 dzie\u0144",pf.print(Period.days(1)));
    }

    public void test_wordBased_pl_regEx_59_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        


        assertEquals("2 dni",pf.print(Period.days(2)));
    }

    public void test_wordBased_pl_regEx_60_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        


        assertEquals("5 dni",pf.print(Period.days(5)));
    }

    public void test_wordBased_pl_regEx_61_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        


        assertEquals("12 dni",pf.print(Period.days(12)));
    }

    public void test_wordBased_pl_regEx_62_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        


        assertEquals("15 dni",pf.print(Period.days(15)));
    }

    public void test_wordBased_pl_regEx_63_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        


        assertEquals("22 dni",pf.print(Period.days(22)));
    }

    public void test_wordBased_pl_regEx_64_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        


        assertEquals("25 dni",pf.print(Period.days(25)));
    }

    public void test_wordBased_pl_regEx_65_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("1 godzina",pf.print(Period.hours(1)));
    }

    public void test_wordBased_pl_regEx_66_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2 godziny",pf.print(Period.hours(2)));
    }

    public void test_wordBased_pl_regEx_67_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("5 godzin",pf.print(Period.hours(5)));
    }

    public void test_wordBased_pl_regEx_68_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("12 godzin",pf.print(Period.hours(12)));
    }

    public void test_wordBased_pl_regEx_69_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("15 godzin",pf.print(Period.hours(15)));
    }

    public void test_wordBased_pl_regEx_70_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("1112 godzin",pf.print(Period.hours(1112)));
    }

    public void test_wordBased_pl_regEx_71_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("1115 godzin",pf.print(Period.hours(1115)));
    }

    public void test_wordBased_pl_regEx_72_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2112 godzin",pf.print(Period.hours(2112)));
    }

    public void test_wordBased_pl_regEx_73_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2115 godzin",pf.print(Period.hours(2115)));
    }

    public void test_wordBased_pl_regEx_74_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2212 godzin",pf.print(Period.hours(2212)));
    }

    public void test_wordBased_pl_regEx_75_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2215 godzin",pf.print(Period.hours(2215)));
    }

    public void test_wordBased_pl_regEx_76_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("22 godziny",pf.print(Period.hours(22)));
    }

    public void test_wordBased_pl_regEx_77_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("25 godzin",pf.print(Period.hours(25)));
    }

    public void test_wordBased_pl_regEx_78_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("1122 godziny",pf.print(Period.hours(1122)));
    }

    public void test_wordBased_pl_regEx_79_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("1125 godzin",pf.print(Period.hours(1125)));
    }

    public void test_wordBased_pl_regEx_80_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2122 godziny",pf.print(Period.hours(2122)));
    }

    public void test_wordBased_pl_regEx_81_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2125 godzin",pf.print(Period.hours(2125)));
    }

    public void test_wordBased_pl_regEx_82_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2222 godziny",pf.print(Period.hours(2222)));
    }

    public void test_wordBased_pl_regEx_83_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        



        assertEquals("2225 godzin",pf.print(Period.hours(2225)));
    }

    public void test_wordBased_pl_regEx_84_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("1 minuta",pf.print(Period.minutes(1)));
    }

    public void test_wordBased_pl_regEx_85_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2 minuty",pf.print(Period.minutes(2)));
    }

    public void test_wordBased_pl_regEx_86_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("5 minut",pf.print(Period.minutes(5)));
    }

    public void test_wordBased_pl_regEx_87_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("12 minut",pf.print(Period.minutes(12)));
    }

    public void test_wordBased_pl_regEx_88_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("15 minut",pf.print(Period.minutes(15)));
    }

    public void test_wordBased_pl_regEx_89_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("1112 minut",pf.print(Period.minutes(1112)));
    }

    public void test_wordBased_pl_regEx_90_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("1115 minut",pf.print(Period.minutes(1115)));
    }

    public void test_wordBased_pl_regEx_91_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2112 minut",pf.print(Period.minutes(2112)));
    }

    public void test_wordBased_pl_regEx_92_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2115 minut",pf.print(Period.minutes(2115)));
    }

    public void test_wordBased_pl_regEx_93_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2212 minut",pf.print(Period.minutes(2212)));
    }

    public void test_wordBased_pl_regEx_94_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2215 minut",pf.print(Period.minutes(2215)));
    }

    public void test_wordBased_pl_regEx_95_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("22 minuty",pf.print(Period.minutes(22)));
    }

    public void test_wordBased_pl_regEx_96_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("25 minut",pf.print(Period.minutes(25)));
    }

    public void test_wordBased_pl_regEx_97_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("1122 minuty",pf.print(Period.minutes(1122)));
    }

    public void test_wordBased_pl_regEx_98_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("1125 minut",pf.print(Period.minutes(1125)));
    }

    public void test_wordBased_pl_regEx_99_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2122 minuty",pf.print(Period.minutes(2122)));
    }

    public void test_wordBased_pl_regEx_100_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2125 minut",pf.print(Period.minutes(2125)));
    }

    public void test_wordBased_pl_regEx_101_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2222 minuty",pf.print(Period.minutes(2222)));
    }

    public void test_wordBased_pl_regEx_102_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        




        assertEquals("2225 minut",pf.print(Period.minutes(2225)));
    }

    public void test_wordBased_pl_regEx_103_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("1 sekunda",pf.print(Period.seconds(1)));
    }

    public void test_wordBased_pl_regEx_104_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2 sekundy",pf.print(Period.seconds(2)));
    }

    public void test_wordBased_pl_regEx_105_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("5 sekund",pf.print(Period.seconds(5)));
    }

    public void test_wordBased_pl_regEx_106_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("12 sekund",pf.print(Period.seconds(12)));
    }

    public void test_wordBased_pl_regEx_107_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("15 sekund",pf.print(Period.seconds(15)));
    }

    public void test_wordBased_pl_regEx_108_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("1112 sekund",pf.print(Period.seconds(1112)));
    }

    public void test_wordBased_pl_regEx_109_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("1115 sekund",pf.print(Period.seconds(1115)));
    }

    public void test_wordBased_pl_regEx_110_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2112 sekund",pf.print(Period.seconds(2112)));
    }

    public void test_wordBased_pl_regEx_111_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2115 sekund",pf.print(Period.seconds(2115)));
    }

    public void test_wordBased_pl_regEx_112_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2212 sekund",pf.print(Period.seconds(2212)));
    }

    public void test_wordBased_pl_regEx_113_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2215 sekund",pf.print(Period.seconds(2215)));
    }

    public void test_wordBased_pl_regEx_114_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("22 sekundy",pf.print(Period.seconds(22)));
    }

    public void test_wordBased_pl_regEx_115_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("25 sekund",pf.print(Period.seconds(25)));
    }

    public void test_wordBased_pl_regEx_116_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("1122 sekundy",pf.print(Period.seconds(1122)));
    }

    public void test_wordBased_pl_regEx_117_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("1125 sekund",pf.print(Period.seconds(1125)));
    }

    public void test_wordBased_pl_regEx_118_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2122 sekundy",pf.print(Period.seconds(2122)));
    }

    public void test_wordBased_pl_regEx_119_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2125 sekund",pf.print(Period.seconds(2125)));
    }

    public void test_wordBased_pl_regEx_120_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2222 sekundy",pf.print(Period.seconds(2222)));
    }

    public void test_wordBased_pl_regEx_121_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        





        assertEquals("2225 sekund",pf.print(Period.seconds(2225)));
    }

    public void test_wordBased_pl_regEx_122_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("1 milisekunda",pf.print(Period.millis(1)));
    }

    public void test_wordBased_pl_regEx_123_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2 milisekundy",pf.print(Period.millis(2)));
    }

    public void test_wordBased_pl_regEx_124_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("5 milisekund",pf.print(Period.millis(5)));
    }

    public void test_wordBased_pl_regEx_125_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("12 milisekund",pf.print(Period.millis(12)));
    }

    public void test_wordBased_pl_regEx_126_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("15 milisekund",pf.print(Period.millis(15)));
    }

    public void test_wordBased_pl_regEx_127_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("1112 milisekund",pf.print(Period.millis(1112)));
    }

    public void test_wordBased_pl_regEx_128_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("1115 milisekund",pf.print(Period.millis(1115)));
    }

    public void test_wordBased_pl_regEx_129_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2112 milisekund",pf.print(Period.millis(2112)));
    }

    public void test_wordBased_pl_regEx_130_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2115 milisekund",pf.print(Period.millis(2115)));
    }

    public void test_wordBased_pl_regEx_131_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2212 milisekund",pf.print(Period.millis(2212)));
    }

    public void test_wordBased_pl_regEx_132_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2215 milisekund",pf.print(Period.millis(2215)));
    }

    public void test_wordBased_pl_regEx_133_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("22 milisekundy",pf.print(Period.millis(22)));
    }

    public void test_wordBased_pl_regEx_134_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("25 milisekund",pf.print(Period.millis(25)));
    }

    public void test_wordBased_pl_regEx_135_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("1122 milisekundy",pf.print(Period.millis(1122)));
    }

    public void test_wordBased_pl_regEx_136_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("1125 milisekund",pf.print(Period.millis(1125)));
    }

    public void test_wordBased_pl_regEx_137_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2122 milisekundy",pf.print(Period.millis(2122)));
    }

    public void test_wordBased_pl_regEx_138_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2125 milisekund",pf.print(Period.millis(2125)));
    }

    public void test_wordBased_pl_regEx_139_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2222 milisekundy",pf.print(Period.millis(2222)));
    }

    public void test_wordBased_pl_regEx_140_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(PL);
        






        assertEquals("2225 milisekund",pf.print(Period.millis(2225)));
    }

    public void test_wordBased_ru_FormatOneField_1_oe() {
        Period p = Period.days(2);
        assertEquals("2 дня",PeriodFormat.wordBased(RU).print(p));
    }

    public void test_wordBased_ru_formatTwoFields_1_oe() {
        Period p = Period.years(1).withMonths(2);
        assertEquals("1 год и 2 месяца",PeriodFormat.wordBased(RU).print(p));
    }

    public void test_wordBased_ru_parseOneField_1_oe() {
        Period p = Period.years(1);
        assertEquals(p,PeriodFormat.wordBased(RU).parsePeriod("1 год"));
    }

    public void test_wordBased_ru_parseTwoFields_1_oe() {
        Period p = Period.hours(1).withMillis(5);
        assertEquals(p,PeriodFormat.wordBased(RU).parsePeriod("1 час и 5 миллисекунд"));
    }

    public void test_wordBased_ru_checkRedundantSeparator_2_oe() {
        try {
            PeriodFormat.wordBased(RU).parsePeriod("2 дня and 5 минут");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class,e.getClass());
    }
    }

    public void test_wordBased_ru_cached_1_oe() {
        assertSame(PeriodFormat.wordBased(RU),PeriodFormat.wordBased(RU));
    }

    public void test_wordBased_ru_regEx_1_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("1 год",pf.print(Period.years(1)));
    }

    public void test_wordBased_ru_regEx_2_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("11 лет",pf.print(Period.years(11)));
    }

    public void test_wordBased_ru_regEx_3_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("21 год",pf.print(Period.years(21)));
    }

    public void test_wordBased_ru_regEx_4_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("101 год",pf.print(Period.years(101)));
    }

    public void test_wordBased_ru_regEx_5_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("111 лет",pf.print(Period.years(111)));
    }

    public void test_wordBased_ru_regEx_6_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("121 год",pf.print(Period.years(121)));
    }

    public void test_wordBased_ru_regEx_7_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("2001 год",pf.print(Period.years(2001)));
    }

    public void test_wordBased_ru_regEx_8_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("2 года",pf.print(Period.years(2)));
    }

    public void test_wordBased_ru_regEx_9_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("3 года",pf.print(Period.years(3)));
    }

    public void test_wordBased_ru_regEx_10_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("4 года",pf.print(Period.years(4)));
    }

    public void test_wordBased_ru_regEx_11_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("12 лет",pf.print(Period.years(12)));
    }

    public void test_wordBased_ru_regEx_12_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("13 лет",pf.print(Period.years(13)));
    }

    public void test_wordBased_ru_regEx_13_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("14 лет",pf.print(Period.years(14)));
    }

    public void test_wordBased_ru_regEx_14_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("22 года",pf.print(Period.years(22)));
    }

    public void test_wordBased_ru_regEx_15_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("23 года",pf.print(Period.years(23)));
    }

    public void test_wordBased_ru_regEx_16_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("24 года",pf.print(Period.years(24)));
    }

    public void test_wordBased_ru_regEx_17_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("102 года",pf.print(Period.years(102)));
    }

    public void test_wordBased_ru_regEx_18_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("112 лет",pf.print(Period.years(112)));
    }

    public void test_wordBased_ru_regEx_19_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("124 года",pf.print(Period.years(124)));
    }

    public void test_wordBased_ru_regEx_20_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("5 лет",pf.print(Period.years(5)));
    }

    public void test_wordBased_ru_regEx_21_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("15 лет",pf.print(Period.years(15)));
    }

    public void test_wordBased_ru_regEx_22_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("25 лет",pf.print(Period.years(25)));
    }

    public void test_wordBased_ru_regEx_23_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("105 лет",pf.print(Period.years(105)));
    }

    public void test_wordBased_ru_regEx_24_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);
        assertEquals("1005 лет",pf.print(Period.years(1005)));
    }

    public void test_wordBased_ru_regEx_25_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("1 месяц",pf.print(Period.months(1)));
    }

    public void test_wordBased_ru_regEx_26_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("11 месяцев",pf.print(Period.months(11)));
    }

    public void test_wordBased_ru_regEx_27_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("21 месяц",pf.print(Period.months(21)));
    }

    public void test_wordBased_ru_regEx_28_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("101 месяц",pf.print(Period.months(101)));
    }

    public void test_wordBased_ru_regEx_29_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("111 месяцев",pf.print(Period.months(111)));
    }

    public void test_wordBased_ru_regEx_30_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("121 месяц",pf.print(Period.months(121)));
    }

    public void test_wordBased_ru_regEx_31_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("2001 месяц",pf.print(Period.months(2001)));
    }

    public void test_wordBased_ru_regEx_32_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("2 месяца",pf.print(Period.months(2)));
    }

    public void test_wordBased_ru_regEx_33_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("3 месяца",pf.print(Period.months(3)));
    }

    public void test_wordBased_ru_regEx_34_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("4 месяца",pf.print(Period.months(4)));
    }

    public void test_wordBased_ru_regEx_35_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("12 месяцев",pf.print(Period.months(12)));
    }

    public void test_wordBased_ru_regEx_36_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("13 месяцев",pf.print(Period.months(13)));
    }

    public void test_wordBased_ru_regEx_37_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("14 месяцев",pf.print(Period.months(14)));
    }

    public void test_wordBased_ru_regEx_38_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("22 месяца",pf.print(Period.months(22)));
    }

    public void test_wordBased_ru_regEx_39_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("23 месяца",pf.print(Period.months(23)));
    }

    public void test_wordBased_ru_regEx_40_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("24 месяца",pf.print(Period.months(24)));
    }

    public void test_wordBased_ru_regEx_41_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("102 месяца",pf.print(Period.months(102)));
    }

    public void test_wordBased_ru_regEx_42_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("112 месяцев",pf.print(Period.months(112)));
    }

    public void test_wordBased_ru_regEx_43_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("124 месяца",pf.print(Period.months(124)));
    }

    public void test_wordBased_ru_regEx_44_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("5 месяцев",pf.print(Period.months(5)));
    }

    public void test_wordBased_ru_regEx_45_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("15 месяцев",pf.print(Period.months(15)));
    }

    public void test_wordBased_ru_regEx_46_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("25 месяцев",pf.print(Period.months(25)));
    }

    public void test_wordBased_ru_regEx_47_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("105 месяцев",pf.print(Period.months(105)));
    }

    public void test_wordBased_ru_regEx_48_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);

        assertEquals("1005 месяцев",pf.print(Period.months(1005)));
    }

    public void test_wordBased_ru_regEx_49_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("1 неделя",pf.print(Period.weeks(1)));
    }

    public void test_wordBased_ru_regEx_50_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("11 недель",pf.print(Period.weeks(11)));
    }

    public void test_wordBased_ru_regEx_51_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("21 неделя",pf.print(Period.weeks(21)));
    }

    public void test_wordBased_ru_regEx_52_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("101 неделя",pf.print(Period.weeks(101)));
    }

    public void test_wordBased_ru_regEx_53_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("111 недель",pf.print(Period.weeks(111)));
    }

    public void test_wordBased_ru_regEx_54_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("121 неделя",pf.print(Period.weeks(121)));
    }

    public void test_wordBased_ru_regEx_55_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("2001 неделя",pf.print(Period.weeks(2001)));
    }

    public void test_wordBased_ru_regEx_56_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("2 недели",pf.print(Period.weeks(2)));
    }

    public void test_wordBased_ru_regEx_57_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("3 недели",pf.print(Period.weeks(3)));
    }

    public void test_wordBased_ru_regEx_58_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("4 недели",pf.print(Period.weeks(4)));
    }

    public void test_wordBased_ru_regEx_59_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("12 недель",pf.print(Period.weeks(12)));
    }

    public void test_wordBased_ru_regEx_60_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("13 недель",pf.print(Period.weeks(13)));
    }

    public void test_wordBased_ru_regEx_61_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("14 недель",pf.print(Period.weeks(14)));
    }

    public void test_wordBased_ru_regEx_62_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("22 недели",pf.print(Period.weeks(22)));
    }

    public void test_wordBased_ru_regEx_63_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("23 недели",pf.print(Period.weeks(23)));
    }

    public void test_wordBased_ru_regEx_64_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("24 недели",pf.print(Period.weeks(24)));
    }

    public void test_wordBased_ru_regEx_65_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("102 недели",pf.print(Period.weeks(102)));
    }

    public void test_wordBased_ru_regEx_66_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("112 недель",pf.print(Period.weeks(112)));
    }

    public void test_wordBased_ru_regEx_67_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("124 недели",pf.print(Period.weeks(124)));
    }

    public void test_wordBased_ru_regEx_68_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("5 недель",pf.print(Period.weeks(5)));
    }

    public void test_wordBased_ru_regEx_69_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("15 недель",pf.print(Period.weeks(15)));
    }

    public void test_wordBased_ru_regEx_70_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("25 недель",pf.print(Period.weeks(25)));
    }

    public void test_wordBased_ru_regEx_71_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("105 недель",pf.print(Period.weeks(105)));
    }

    public void test_wordBased_ru_regEx_72_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);


        assertEquals("1005 недель",pf.print(Period.weeks(1005)));
    }

    public void test_wordBased_ru_regEx_73_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("1 день",pf.print(Period.days(1)));
    }

    public void test_wordBased_ru_regEx_74_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("11 дней",pf.print(Period.days(11)));
    }

    public void test_wordBased_ru_regEx_75_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("21 день",pf.print(Period.days(21)));
    }

    public void test_wordBased_ru_regEx_76_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("101 день",pf.print(Period.days(101)));
    }

    public void test_wordBased_ru_regEx_77_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("111 дней",pf.print(Period.days(111)));
    }

    public void test_wordBased_ru_regEx_78_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("121 день",pf.print(Period.days(121)));
    }

    public void test_wordBased_ru_regEx_79_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("2001 день",pf.print(Period.days(2001)));
    }

    public void test_wordBased_ru_regEx_80_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("2 дня",pf.print(Period.days(2)));
    }

    public void test_wordBased_ru_regEx_81_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("3 дня",pf.print(Period.days(3)));
    }

    public void test_wordBased_ru_regEx_82_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("4 дня",pf.print(Period.days(4)));
    }

    public void test_wordBased_ru_regEx_83_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("12 дней",pf.print(Period.days(12)));
    }

    public void test_wordBased_ru_regEx_84_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("13 дней",pf.print(Period.days(13)));
    }

    public void test_wordBased_ru_regEx_85_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("14 дней",pf.print(Period.days(14)));
    }

    public void test_wordBased_ru_regEx_86_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("22 дня",pf.print(Period.days(22)));
    }

    public void test_wordBased_ru_regEx_87_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("23 дня",pf.print(Period.days(23)));
    }

    public void test_wordBased_ru_regEx_88_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("24 дня",pf.print(Period.days(24)));
    }

    public void test_wordBased_ru_regEx_89_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("102 дня",pf.print(Period.days(102)));
    }

    public void test_wordBased_ru_regEx_90_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("112 дней",pf.print(Period.days(112)));
    }

    public void test_wordBased_ru_regEx_91_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("124 дня",pf.print(Period.days(124)));
    }

    public void test_wordBased_ru_regEx_92_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("5 дней",pf.print(Period.days(5)));
    }

    public void test_wordBased_ru_regEx_93_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("15 дней",pf.print(Period.days(15)));
    }

    public void test_wordBased_ru_regEx_94_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("25 дней",pf.print(Period.days(25)));
    }

    public void test_wordBased_ru_regEx_95_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("105 дней",pf.print(Period.days(105)));
    }

    public void test_wordBased_ru_regEx_96_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);



        assertEquals("1005 дней",pf.print(Period.days(1005)));
    }

    public void test_wordBased_ru_regEx_97_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("1 час",pf.print(Period.hours(1)));
    }

    public void test_wordBased_ru_regEx_98_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("11 часов",pf.print(Period.hours(11)));
    }

    public void test_wordBased_ru_regEx_99_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("21 час",pf.print(Period.hours(21)));
    }

    public void test_wordBased_ru_regEx_100_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("101 час",pf.print(Period.hours(101)));
    }

    public void test_wordBased_ru_regEx_101_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("111 часов",pf.print(Period.hours(111)));
    }

    public void test_wordBased_ru_regEx_102_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("121 час",pf.print(Period.hours(121)));
    }

    public void test_wordBased_ru_regEx_103_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("2001 час",pf.print(Period.hours(2001)));
    }

    public void test_wordBased_ru_regEx_104_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("2 часа",pf.print(Period.hours(2)));
    }

    public void test_wordBased_ru_regEx_105_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("3 часа",pf.print(Period.hours(3)));
    }

    public void test_wordBased_ru_regEx_106_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("4 часа",pf.print(Period.hours(4)));
    }

    public void test_wordBased_ru_regEx_107_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("12 часов",pf.print(Period.hours(12)));
    }

    public void test_wordBased_ru_regEx_108_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("13 часов",pf.print(Period.hours(13)));
    }

    public void test_wordBased_ru_regEx_109_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("14 часов",pf.print(Period.hours(14)));
    }

    public void test_wordBased_ru_regEx_110_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("22 часа",pf.print(Period.hours(22)));
    }

    public void test_wordBased_ru_regEx_111_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("23 часа",pf.print(Period.hours(23)));
    }

    public void test_wordBased_ru_regEx_112_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("24 часа",pf.print(Period.hours(24)));
    }

    public void test_wordBased_ru_regEx_113_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("102 часа",pf.print(Period.hours(102)));
    }

    public void test_wordBased_ru_regEx_114_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("112 часов",pf.print(Period.hours(112)));
    }

    public void test_wordBased_ru_regEx_115_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("124 часа",pf.print(Period.hours(124)));
    }

    public void test_wordBased_ru_regEx_116_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("5 часов",pf.print(Period.hours(5)));
    }

    public void test_wordBased_ru_regEx_117_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("15 часов",pf.print(Period.hours(15)));
    }

    public void test_wordBased_ru_regEx_118_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("25 часов",pf.print(Period.hours(25)));
    }

    public void test_wordBased_ru_regEx_119_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("105 часов",pf.print(Period.hours(105)));
    }

    public void test_wordBased_ru_regEx_120_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);




        assertEquals("1005 часов",pf.print(Period.hours(1005)));
    }

    public void test_wordBased_ru_regEx_121_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("1 минута",pf.print(Period.minutes(1)));
    }

    public void test_wordBased_ru_regEx_122_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("11 минут",pf.print(Period.minutes(11)));
    }

    public void test_wordBased_ru_regEx_123_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("21 минута",pf.print(Period.minutes(21)));
    }

    public void test_wordBased_ru_regEx_124_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("101 минута",pf.print(Period.minutes(101)));
    }

    public void test_wordBased_ru_regEx_125_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("111 минут",pf.print(Period.minutes(111)));
    }

    public void test_wordBased_ru_regEx_126_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("121 минута",pf.print(Period.minutes(121)));
    }

    public void test_wordBased_ru_regEx_127_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("2001 минута",pf.print(Period.minutes(2001)));
    }

    public void test_wordBased_ru_regEx_128_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("2 минуты",pf.print(Period.minutes(2)));
    }

    public void test_wordBased_ru_regEx_129_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("3 минуты",pf.print(Period.minutes(3)));
    }

    public void test_wordBased_ru_regEx_130_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("4 минуты",pf.print(Period.minutes(4)));
    }

    public void test_wordBased_ru_regEx_131_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("12 минут",pf.print(Period.minutes(12)));
    }

    public void test_wordBased_ru_regEx_132_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("13 минут",pf.print(Period.minutes(13)));
    }

    public void test_wordBased_ru_regEx_133_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("14 минут",pf.print(Period.minutes(14)));
    }

    public void test_wordBased_ru_regEx_134_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("22 минуты",pf.print(Period.minutes(22)));
    }

    public void test_wordBased_ru_regEx_135_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("23 минуты",pf.print(Period.minutes(23)));
    }

    public void test_wordBased_ru_regEx_136_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("24 минуты",pf.print(Period.minutes(24)));
    }

    public void test_wordBased_ru_regEx_137_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("102 минуты",pf.print(Period.minutes(102)));
    }

    public void test_wordBased_ru_regEx_138_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("112 минут",pf.print(Period.minutes(112)));
    }

    public void test_wordBased_ru_regEx_139_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("124 минуты",pf.print(Period.minutes(124)));
    }

    public void test_wordBased_ru_regEx_140_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("5 минут",pf.print(Period.minutes(5)));
    }

    public void test_wordBased_ru_regEx_141_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("15 минут",pf.print(Period.minutes(15)));
    }

    public void test_wordBased_ru_regEx_142_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("25 минут",pf.print(Period.minutes(25)));
    }

    public void test_wordBased_ru_regEx_143_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("105 минут",pf.print(Period.minutes(105)));
    }

    public void test_wordBased_ru_regEx_144_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);





        assertEquals("1005 минут",pf.print(Period.minutes(1005)));
    }

    public void test_wordBased_ru_regEx_145_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("1 секунда",pf.print(Period.seconds(1)));
    }

    public void test_wordBased_ru_regEx_146_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("11 секунд",pf.print(Period.seconds(11)));
    }

    public void test_wordBased_ru_regEx_147_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("21 секунда",pf.print(Period.seconds(21)));
    }

    public void test_wordBased_ru_regEx_148_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("101 секунда",pf.print(Period.seconds(101)));
    }

    public void test_wordBased_ru_regEx_149_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("111 секунд",pf.print(Period.seconds(111)));
    }

    public void test_wordBased_ru_regEx_150_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("121 секунда",pf.print(Period.seconds(121)));
    }

    public void test_wordBased_ru_regEx_151_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("2001 секунда",pf.print(Period.seconds(2001)));
    }

    public void test_wordBased_ru_regEx_152_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("2 секунды",pf.print(Period.seconds(2)));
    }

    public void test_wordBased_ru_regEx_153_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("3 секунды",pf.print(Period.seconds(3)));
    }

    public void test_wordBased_ru_regEx_154_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("4 секунды",pf.print(Period.seconds(4)));
    }

    public void test_wordBased_ru_regEx_155_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("12 секунд",pf.print(Period.seconds(12)));
    }

    public void test_wordBased_ru_regEx_156_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("13 секунд",pf.print(Period.seconds(13)));
    }

    public void test_wordBased_ru_regEx_157_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("14 секунд",pf.print(Period.seconds(14)));
    }

    public void test_wordBased_ru_regEx_158_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("22 секунды",pf.print(Period.seconds(22)));
    }

    public void test_wordBased_ru_regEx_159_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("23 секунды",pf.print(Period.seconds(23)));
    }

    public void test_wordBased_ru_regEx_160_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("24 секунды",pf.print(Period.seconds(24)));
    }

    public void test_wordBased_ru_regEx_161_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("102 секунды",pf.print(Period.seconds(102)));
    }

    public void test_wordBased_ru_regEx_162_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("112 секунд",pf.print(Period.seconds(112)));
    }

    public void test_wordBased_ru_regEx_163_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("124 секунды",pf.print(Period.seconds(124)));
    }

    public void test_wordBased_ru_regEx_164_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("5 секунд",pf.print(Period.seconds(5)));
    }

    public void test_wordBased_ru_regEx_165_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("15 секунд",pf.print(Period.seconds(15)));
    }

    public void test_wordBased_ru_regEx_166_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("25 секунд",pf.print(Period.seconds(25)));
    }

    public void test_wordBased_ru_regEx_167_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("105 секунд",pf.print(Period.seconds(105)));
    }

    public void test_wordBased_ru_regEx_168_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);






        assertEquals("1005 секунд",pf.print(Period.seconds(1005)));
    }

    public void test_wordBased_ru_regEx_169_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("1 миллисекунда",pf.print(Period.millis(1)));
    }

    public void test_wordBased_ru_regEx_170_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("11 миллисекунд",pf.print(Period.millis(11)));
    }

    public void test_wordBased_ru_regEx_171_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("21 миллисекунда",pf.print(Period.millis(21)));
    }

    public void test_wordBased_ru_regEx_172_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("101 миллисекунда",pf.print(Period.millis(101)));
    }

    public void test_wordBased_ru_regEx_173_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("111 миллисекунд",pf.print(Period.millis(111)));
    }

    public void test_wordBased_ru_regEx_174_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("121 миллисекунда",pf.print(Period.millis(121)));
    }

    public void test_wordBased_ru_regEx_175_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("2001 миллисекунда",pf.print(Period.millis(2001)));
    }

    public void test_wordBased_ru_regEx_176_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("2 миллисекунды",pf.print(Period.millis(2)));
    }

    public void test_wordBased_ru_regEx_177_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("3 миллисекунды",pf.print(Period.millis(3)));
    }

    public void test_wordBased_ru_regEx_178_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("4 миллисекунды",pf.print(Period.millis(4)));
    }

    public void test_wordBased_ru_regEx_179_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("12 миллисекунд",pf.print(Period.millis(12)));
    }

    public void test_wordBased_ru_regEx_180_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("13 миллисекунд",pf.print(Period.millis(13)));
    }

    public void test_wordBased_ru_regEx_181_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("14 миллисекунд",pf.print(Period.millis(14)));
    }

    public void test_wordBased_ru_regEx_182_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("22 миллисекунды",pf.print(Period.millis(22)));
    }

    public void test_wordBased_ru_regEx_183_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("23 миллисекунды",pf.print(Period.millis(23)));
    }

    public void test_wordBased_ru_regEx_184_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("24 миллисекунды",pf.print(Period.millis(24)));
    }

    public void test_wordBased_ru_regEx_185_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("102 миллисекунды",pf.print(Period.millis(102)));
    }

    public void test_wordBased_ru_regEx_186_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("112 миллисекунд",pf.print(Period.millis(112)));
    }

    public void test_wordBased_ru_regEx_187_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("124 миллисекунды",pf.print(Period.millis(124)));
    }

    public void test_wordBased_ru_regEx_188_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("5 миллисекунд",pf.print(Period.millis(5)));
    }

    public void test_wordBased_ru_regEx_189_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("15 миллисекунд",pf.print(Period.millis(15)));
    }

    public void test_wordBased_ru_regEx_190_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("25 миллисекунд",pf.print(Period.millis(25)));
    }

    public void test_wordBased_ru_regEx_191_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("105 миллисекунд",pf.print(Period.millis(105)));
    }

    public void test_wordBased_ru_regEx_192_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(RU);







        assertEquals("1005 миллисекунд",pf.print(Period.millis(1005)));
    }

    public void test_getDefault_localeValue_1_oe() {
        PeriodFormatter pf = PeriodFormat.getDefault();
        assertEquals(Locale.ENGLISH,pf.getLocale());
    }

    public void test_wordBased_localeValue_1_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased();
        assertEquals(DE,pf.getLocale());
    }

    public void test_wordBasedWithLocale_localeValue_1_oe() {
        PeriodFormatter pf = PeriodFormat.wordBased(FR);
        assertEquals(FR,pf.getLocale());
    }

    public void test_wordBased_en_withLocale_pt_1_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        assertEquals("2 days and 5 hours",format1.print(p));
    }

    public void test_wordBased_en_withLocale_pt_2_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        assertEquals(p,format1.parsePeriod("2 days and 5 hours"));
    }

    public void test_wordBased_en_withLocale_pt_3_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        assertEquals(EN,format1.getLocale());
    }

    public void test_wordBased_en_withLocale_pt_4_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        assertEquals("2 dias e 5 horas",format2.print(p));
    }

    public void test_wordBased_en_withLocale_pt_5_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        assertEquals(p,format2.parsePeriod("2 dias e 5 horas"));
    }

    public void test_wordBased_en_withLocale_pt_6_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        assertEquals(PT,format2.getLocale());
    }

    public void test_wordBased_en_withLocale_pt_7_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        
        PeriodFormatter format3 = format1.withLocale(DE);
        assertEquals("2 Tage und 5 Stunden",format3.print(p));
    }

    public void test_wordBased_en_withLocale_pt_8_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        
        PeriodFormatter format3 = format1.withLocale(DE);
        assertEquals(p,format3.parsePeriod("2 Tage und 5 Stunden"));
    }

    public void test_wordBased_en_withLocale_pt_9_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        
        PeriodFormatter format3 = format1.withLocale(DE);
        assertEquals(DE,format3.getLocale());
    }

    public void test_wordBased_en_withLocale_pt_10_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        
        PeriodFormatter format3 = format1.withLocale(DE);
        
        PeriodFormatter format4 = format1.withLocale(null);
        assertEquals("2 days and 5 hours",format4.print(p));
    }

    public void test_wordBased_en_withLocale_pt_11_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        
        PeriodFormatter format3 = format1.withLocale(DE);
        
        PeriodFormatter format4 = format1.withLocale(null);
        assertEquals(p,format4.parsePeriod("2 days and 5 hours"));
    }

    public void test_wordBased_en_withLocale_pt_12_oe() {
        Period p = Period.days(2).withHours(5);
        PeriodFormatter format1 = PeriodFormat.wordBased(EN);
        
        PeriodFormatter format2 = format1.withLocale(PT);
        
        PeriodFormatter format3 = format1.withLocale(DE);
        
        PeriodFormatter format4 = format1.withLocale(null);
        assertEquals(null,format4.getLocale());
    }

}
