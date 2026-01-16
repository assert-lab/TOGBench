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
package org.apache.commons.net.ftp.parser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

import junit.framework.TestCase;

/**
 * This is a simple TestCase that tests entry parsing using the new FTPClientConfig
 * mechanism. The normal FTPClient cannot handle the different date formats in these
 * entries, however using a configurable format, we can handle it easily.
 *
 * The original system presenting this issue was an AIX system - see bug #27437 for details.
 *
 */
public class FTPConfigEntryParserTest_OE25Dev extends TestCase {

    private final SimpleDateFormat df = new SimpleDateFormat();

    /**
     * This is a new format reported on the mailing lists. Parsing this kind of
     * entry necessitated changing the regex in the parser.
     *
     */

    public void testParseFieldsOnAIX_1_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        assertNotNull("Could not parse entry.", f);
    }

    public void testParseFieldsOnAIX_2_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        assertFalse("Is not a directory.", f.isDirectory());
    }

    public void testParseFieldsOnAIX_3_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        assertTrue("Should have user read permission.", f.hasPermission( FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    public void testParseFieldsOnAIX_4_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("Should have user write permission.", f.hasPermission( FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    public void testParseFieldsOnAIX_5_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Should NOT have user execute permission.", f .hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    public void testParseFieldsOnAIX_6_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Should have group read permission.", f.hasPermission( FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
    }

    public void testParseFieldsOnAIX_7_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Should NOT have group write permission.", f .hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    public void testParseFieldsOnAIX_8_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Should NOT have group execute permission.", f.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    public void testParseFieldsOnAIX_9_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Should NOT have world read permission.", f.hasPermission( FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION));
    }

    public void testParseFieldsOnAIX_10_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Should NOT have world write permission.", f .hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    public void testParseFieldsOnAIX_11_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Should NOT have world execute permission.", f.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    public void testParseFieldsOnAIX_12_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(1, f.getHardLinkCount());
    }

    public void testParseFieldsOnAIX_13_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertEquals("ravensm", f.getUser());
    }

    public void testParseFieldsOnAIX_14_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals("sca", f.getGroup());
    }

    public void testParseFieldsOnAIX_15_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("ZMIR2.m", f.getName());
    }

    public void testParseFieldsOnAIX_16_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(814, f.getSize());
    }

    public void testParseFieldsOnAIX_17_oe() {

        // Set a date format for this server type
        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("dd MMM HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("-rw-r-----   1 ravensm  sca          814 02 Mar 16:27 ZMIR2.m");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final Calendar cal = Calendar.getInstance();

        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 2);
        cal.set(Calendar.HOUR_OF_DAY, 16);
        cal.set(Calendar.MINUTE, 27);
        cal.set(Calendar.SECOND, 0);

        // With no year specified, it defaults to 1970
        // TODO this is probably a bug - it should default to the current year
        cal.set(Calendar.YEAR, 1970);

        assertEquals(df.format(cal.getTime()), df.format(f.getTimestamp() .getTime()));
    }

    public void testParseEntryWithSymlink_1_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        assertNotNull("Could not parse entry.", f);
    }

    public void testParseEntryWithSymlink_2_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        assertFalse("Is not a directory.", f.isDirectory());
    }

    public void testParseEntryWithSymlink_3_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        assertTrue("Is a symbolic link", f.isSymbolicLink());
    }

    public void testParseEntryWithSymlink_4_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("Should have user read permission.", f.hasPermission( FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
    }

    public void testParseEntryWithSymlink_5_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("Should have user write permission.", f.hasPermission( FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    public void testParseEntryWithSymlink_6_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("Should have user execute permission.", f .hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    public void testParseEntryWithSymlink_7_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Should have group read permission.", f.hasPermission( FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION));
    }

    public void testParseEntryWithSymlink_8_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Should have group write permission.", f .hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    public void testParseEntryWithSymlink_9_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Should have group execute permission.", f.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    public void testParseEntryWithSymlink_10_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Should have world read permission.", f.hasPermission( FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION));
    }

    public void testParseEntryWithSymlink_11_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Should have world write permission.", f .hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION));
    }

    public void testParseEntryWithSymlink_12_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Should have world execute permission.", f.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION));
    }

    public void testParseEntryWithSymlink_13_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(1, f.getHardLinkCount());
    }

    public void testParseEntryWithSymlink_14_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertEquals("neeme", f.getUser());
    }

    public void testParseEntryWithSymlink_15_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals("neeme", f.getGroup());
    }

    public void testParseEntryWithSymlink_16_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("macros", f.getName());
    }

    public void testParseEntryWithSymlink_17_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(23, f.getSize());
    }

    public void testParseEntryWithSymlink_18_oe() {

        final FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        config.setDefaultDateFormatStr("yyyy-MM-dd HH:mm");

        final UnixFTPEntryParser parser = new UnixFTPEntryParser();
        parser.configure(config);

        final FTPFile f = parser.parseFTPEntry("lrwxrwxrwx   1 neeme neeme    23 2005-03-02 18:06 macros");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final Calendar cal = Calendar.getInstance();

        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 2);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 06);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.YEAR, 2005);

        assertEquals(df.format(cal.getTime()), df.format(f.getTimestamp() .getTime()));
    }

}
