/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.net.ftp;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;

public class FTPClientConfigTest_OE25Dev extends TestCase {

    /*
     * Class under test for void FTPClientConfig(String)
     */

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String F = "F";

    /*
     * Class under test for void FTPClientConfig(String, String, String, String, String, String)
     */


    private static final String badDelim = "jan,feb,mar,apr,may,jun,jul,aug.sep,oct,nov,dec";
    private static final String tooLong =  "jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec|jan";
    private static final String tooShort = "jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov";
    private static final String fakeLang = "abc|def|ghi|jkl|mno|pqr|stu|vwx|yza|bcd|efg|hij";

    public void testSetShortMonthNames() {
    }

    public void testGetServerLanguageCode() {
    }

    public void testFTPClientConfigString_1_oe() {
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_VMS);
        assertEquals(FTPClientConfig.SYST_VMS, config.getServerSystemKey());
    }

    public void testFTPClientConfigString_2_oe() {
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_VMS);
        assertNull(config.getDefaultDateFormatStr());
    }

    public void testFTPClientConfigString_3_oe() {
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_VMS);
        assertNull(config.getRecentDateFormatStr());
    }

    public void testFTPClientConfigString_4_oe() {
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_VMS);
        assertNull(config.getShortMonthNames());
    }

    public void testFTPClientConfigString_5_oe() {
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_VMS);
        assertNull(config.getServerTimeZoneId());
    }

    public void testFTPClientConfigString_6_oe() {
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_VMS);
        assertNull(config.getServerLanguageCode());
    }

    public void testFTPClientConfigStringStringStringStringStringString_1_oe() {
        final FTPClientConfig conf = new FTPClientConfig(A,B,C,D,E,F);

        assertEquals("A", conf.getServerSystemKey());
    }

    public void testFTPClientConfigStringStringStringStringStringString_2_oe() {
        final FTPClientConfig conf = new FTPClientConfig(A,B,C,D,E,F);

        assertEquals("B", conf.getDefaultDateFormatStr());
    }

    public void testFTPClientConfigStringStringStringStringStringString_3_oe() {
        final FTPClientConfig conf = new FTPClientConfig(A,B,C,D,E,F);

        assertEquals("C", conf.getRecentDateFormatStr());
    }

    public void testFTPClientConfigStringStringStringStringStringString_4_oe() {
        final FTPClientConfig conf = new FTPClientConfig(A,B,C,D,E,F);

        assertEquals("E", conf.getShortMonthNames());
    }

    public void testFTPClientConfigStringStringStringStringStringString_5_oe() {
        final FTPClientConfig conf = new FTPClientConfig(A,B,C,D,E,F);

        assertEquals("F", conf.getServerTimeZoneId());
    }

    public void testFTPClientConfigStringStringStringStringStringString_6_oe() {
        final FTPClientConfig conf = new FTPClientConfig(A,B,C,D,E,F);

        assertEquals("D", conf.getServerLanguageCode());
    }

    public void testLookupDateFormatSymbols_1_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
            fail("french");
    }
    }

    public void testLookupDateFormatSymbols_2_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
            fail("albanian");
    }
    }

    public void testLookupDateFormatSymbols_3_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs3 = FTPClientConfig.lookupDateFormatSymbols("ru");
        } catch (final IllegalArgumentException e){
            fail("unusupported.default.to.en");
    }
    }

    public void testLookupDateFormatSymbols_4_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs3 = FTPClientConfig.lookupDateFormatSymbols("ru");
        } catch (final IllegalArgumentException e){
        }
        try {
            dfs4 = FTPClientConfig.lookupDateFormatSymbols(fakeLang);
        } catch (final IllegalArgumentException e){
            fail("not.language.code.but.defaults");
    }
    }

    public void testLookupDateFormatSymbols_5_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs3 = FTPClientConfig.lookupDateFormatSymbols("ru");
        } catch (final IllegalArgumentException e){
        }
        try {
            dfs4 = FTPClientConfig.lookupDateFormatSymbols(fakeLang);
        } catch (final IllegalArgumentException e){
        }

        assertEquals(dfs3,dfs4);
    }

    public void testLookupDateFormatSymbols_6_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs3 = FTPClientConfig.lookupDateFormatSymbols("ru");
        } catch (final IllegalArgumentException e){
        }
        try {
            dfs4 = FTPClientConfig.lookupDateFormatSymbols(fakeLang);
        } catch (final IllegalArgumentException e){
        }


        final SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM yyyy", dfs1);
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", dfs2);
        final SimpleDateFormat sdf3 = new SimpleDateFormat("MMM dd, yyyy", dfs3);
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        try {
            d1 = sdf1.parse("31 d\u00e9c 2004");
        } catch (final ParseException px) {
            fail("failed.to.parse.french");
    }
    }

    public void testLookupDateFormatSymbols_7_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs3 = FTPClientConfig.lookupDateFormatSymbols("ru");
        } catch (final IllegalArgumentException e){
        }
        try {
            dfs4 = FTPClientConfig.lookupDateFormatSymbols(fakeLang);
        } catch (final IllegalArgumentException e){
        }


        final SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM yyyy", dfs1);
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", dfs2);
        final SimpleDateFormat sdf3 = new SimpleDateFormat("MMM dd, yyyy", dfs3);
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        try {
            d1 = sdf1.parse("31 d\u00e9c 2004");
        } catch (final ParseException px) {
        }
        try {
            d2 = sdf2.parse("dhj 31, 2004");
        } catch (final ParseException px) {
            fail("failed.to.parse.albanian");
    }
    }

    public void testLookupDateFormatSymbols_8_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs3 = FTPClientConfig.lookupDateFormatSymbols("ru");
        } catch (final IllegalArgumentException e){
        }
        try {
            dfs4 = FTPClientConfig.lookupDateFormatSymbols(fakeLang);
        } catch (final IllegalArgumentException e){
        }


        final SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM yyyy", dfs1);
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", dfs2);
        final SimpleDateFormat sdf3 = new SimpleDateFormat("MMM dd, yyyy", dfs3);
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        try {
            d1 = sdf1.parse("31 d\u00e9c 2004");
        } catch (final ParseException px) {
        }
        try {
            d2 = sdf2.parse("dhj 31, 2004");
        } catch (final ParseException px) {
        }
        try {
            d3 = sdf3.parse("DEC 31, 2004");
        } catch (final ParseException px) {
            fail("failed.to.parse.'russian'");
    }
    }

    public void testLookupDateFormatSymbols_9_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs3 = FTPClientConfig.lookupDateFormatSymbols("ru");
        } catch (final IllegalArgumentException e){
        }
        try {
            dfs4 = FTPClientConfig.lookupDateFormatSymbols(fakeLang);
        } catch (final IllegalArgumentException e){
        }


        final SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM yyyy", dfs1);
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", dfs2);
        final SimpleDateFormat sdf3 = new SimpleDateFormat("MMM dd, yyyy", dfs3);
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        try {
            d1 = sdf1.parse("31 d\u00e9c 2004");
        } catch (final ParseException px) {
        }
        try {
            d2 = sdf2.parse("dhj 31, 2004");
        } catch (final ParseException px) {
        }
        try {
            d3 = sdf3.parse("DEC 31, 2004");
        } catch (final ParseException px) {
        }
        assertEquals("different.parser.same.date", d1, d2);
    }

    public void testLookupDateFormatSymbols_10_oe() {
        DateFormatSymbols dfs1 = null;
        DateFormatSymbols dfs2 = null;
        DateFormatSymbols dfs3 = null;
        DateFormatSymbols dfs4 = null;


        try {
            dfs1 = FTPClientConfig.lookupDateFormatSymbols("fr");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs2 = FTPClientConfig.lookupDateFormatSymbols("sq");
        } catch (final IllegalArgumentException e){
        }

        try {
            dfs3 = FTPClientConfig.lookupDateFormatSymbols("ru");
        } catch (final IllegalArgumentException e){
        }
        try {
            dfs4 = FTPClientConfig.lookupDateFormatSymbols(fakeLang);
        } catch (final IllegalArgumentException e){
        }


        final SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM yyyy", dfs1);
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", dfs2);
        final SimpleDateFormat sdf3 = new SimpleDateFormat("MMM dd, yyyy", dfs3);
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        try {
            d1 = sdf1.parse("31 d\u00e9c 2004");
        } catch (final ParseException px) {
        }
        try {
            d2 = sdf2.parse("dhj 31, 2004");
        } catch (final ParseException px) {
        }
        try {
            d3 = sdf3.parse("DEC 31, 2004");
        } catch (final ParseException px) {
        }
        assertEquals("different.parser.same.date", d1, d3);
    }

    public void testGetDateFormatSymbols_4_oe() {

        try {
            FTPClientConfig.getDateFormatSymbols(badDelim);
        } catch (final IllegalArgumentException e){
        }
        try {
            FTPClientConfig.getDateFormatSymbols(tooLong);
        } catch (final IllegalArgumentException e){
        }
        try {
            FTPClientConfig.getDateFormatSymbols(tooShort);
        } catch (final IllegalArgumentException e){
        }
        DateFormatSymbols dfs2 = null;
        try {
            dfs2 = FTPClientConfig.getDateFormatSymbols(fakeLang);
        } catch (final Exception e){
            fail("rejected valid short month string");
    }
    }

    public void testGetDateFormatSymbols_5_oe() {

        try {
            FTPClientConfig.getDateFormatSymbols(badDelim);
        } catch (final IllegalArgumentException e){
        }
        try {
            FTPClientConfig.getDateFormatSymbols(tooLong);
        } catch (final IllegalArgumentException e){
        }
        try {
            FTPClientConfig.getDateFormatSymbols(tooShort);
        } catch (final IllegalArgumentException e){
        }
        DateFormatSymbols dfs2 = null;
        try {
            dfs2 = FTPClientConfig.getDateFormatSymbols(fakeLang);
        } catch (final Exception e){
        }
        final SimpleDateFormat sdf1 =
            new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", dfs2);

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sdf1.parse("dec 31, 2004");
        } catch (final ParseException px) {
            fail("failed.to.parse.std");
    }
    }

    public void testGetDateFormatSymbols_6_oe() {

        try {
            FTPClientConfig.getDateFormatSymbols(badDelim);
        } catch (final IllegalArgumentException e){
        }
        try {
            FTPClientConfig.getDateFormatSymbols(tooLong);
        } catch (final IllegalArgumentException e){
        }
        try {
            FTPClientConfig.getDateFormatSymbols(tooShort);
        } catch (final IllegalArgumentException e){
        }
        DateFormatSymbols dfs2 = null;
        try {
            dfs2 = FTPClientConfig.getDateFormatSymbols(fakeLang);
        } catch (final Exception e){
        }
        final SimpleDateFormat sdf1 =
            new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", dfs2);

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sdf1.parse("dec 31, 2004");
        } catch (final ParseException px) {
        }
        try {
            d2 = sdf2.parse("hij 31, 2004");
        } catch (final ParseException px) {
            fail("failed.to.parse.weird");
    }
    }

    public void testGetDateFormatSymbols_7_oe() {

        try {
            FTPClientConfig.getDateFormatSymbols(badDelim);
        } catch (final IllegalArgumentException e){
        }
        try {
            FTPClientConfig.getDateFormatSymbols(tooLong);
        } catch (final IllegalArgumentException e){
        }
        try {
            FTPClientConfig.getDateFormatSymbols(tooShort);
        } catch (final IllegalArgumentException e){
        }
        DateFormatSymbols dfs2 = null;
        try {
            dfs2 = FTPClientConfig.getDateFormatSymbols(fakeLang);
        } catch (final Exception e){
        }
        final SimpleDateFormat sdf1 =
            new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", dfs2);

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sdf1.parse("dec 31, 2004");
        } catch (final ParseException px) {
        }
        try {
            d2 = sdf2.parse("hij 31, 2004");
        } catch (final ParseException px) {
        }

        assertEquals("different.parser.same.date",d1, d2);
    }

}
