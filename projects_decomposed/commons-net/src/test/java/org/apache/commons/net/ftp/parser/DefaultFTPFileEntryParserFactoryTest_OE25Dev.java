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
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFileEntryParser;

import junit.framework.TestCase;


public class DefaultFTPFileEntryParserFactoryTest_OE25Dev extends TestCase
{
    public void testDefaultParserFactory() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        assertTrue(parser instanceof UnixFTPEntryParser);

        parser = factory.createFileEntryParser("UNIX");
        assertTrue(parser instanceof UnixFTPEntryParser);
        assertFalse(((UnixFTPEntryParser)parser).trimLeadingSpaces);

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        assertTrue(parser instanceof UnixFTPEntryParser);
        assertTrue(((UnixFTPEntryParser)parser).trimLeadingSpaces);

        parser = factory.createFileEntryParser("Unix");
        assertTrue(parser instanceof UnixFTPEntryParser);

        parser = factory.createFileEntryParser("EnterpriseUnix");
        assertTrue(parser instanceof UnixFTPEntryParser);
        assertFalse(parser instanceof EnterpriseUnixFTPEntryParser);

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        assertTrue(parser instanceof UnixFTPEntryParser);

        try {
            parser = factory.createFileEntryParser("NT");
            fail("Exception should have been thrown. \"NT\" is not a recognized key");
        } catch (final ParserInitializationException pie) {
            assertNull(pie.getCause());
            assertTrue(pie.getMessage()+ "should contain 'Unknown parser type:'",pie.getMessage().contains("Unknown parser type:"));
        }

        parser = factory.createFileEntryParser("WindowsNT");
        assertTrue(parser instanceof CompositeFileEntryParser);

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        assertTrue(parser instanceof VMSFTPEntryParser);

        parser = factory.createFileEntryParser("OS/2");
        assertTrue(parser instanceof OS2FTPEntryParser);

        parser = factory.createFileEntryParser("OS/400");
        assertTrue(parser instanceof CompositeFileEntryParser);

        parser = factory.createFileEntryParser("AS/400");
        assertTrue(parser instanceof CompositeFileEntryParser);

        // Added test to make sure it handles the Unix systems that were
        // compiled with OS as "UNKNOWN". This test validates that the
        // check is case-insensitive.
        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
            fail("Exception should have been thrown. \"OS2FTPFileEntryParser\" is not a recognized key");
        } catch (final ParserInitializationException pie) {
            assertNull(pie.getCause());
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");
        assertTrue(parser instanceof OS2FTPEntryParser);

        try {
            factory.createFileEntryParser(
                "org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory");
            fail("Exception should have been thrown. \"DefaultFTPFileEntryParserFactory\" does not implement FTPFileEntryParser");
        } catch (final ParserInitializationException pie) {
            final Throwable root = pie.getCause();
            assertTrue(root instanceof ClassCastException);
        }

        try {
            // Class exists, but is an interface
            factory.createFileEntryParser("org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory");
            fail("ParserInitializationException should have been thrown.");
        } catch (final ParserInitializationException pie){
            final Throwable root = pie.getCause();
            assertTrue(root instanceof InstantiationException);
        }
        try {
            // Class exists, but is abstract
            factory.createFileEntryParser("org.apache.commons.net.ftp.FTPFileEntryParserImpl");
            fail("ParserInitializationException should have been thrown.");
        } catch (final ParserInitializationException pie){
            final Throwable root = pie.getCause();
            assertTrue(root instanceof InstantiationException);
        }
    }

    private void checkParserClass(final FTPFileEntryParserFactory fact, final String key, final Class<?> expected){
        final FTPClientConfig config = key == null ? new FTPClientConfig() : new FTPClientConfig(key);
        final FTPFileEntryParser parser = fact.createFileEntryParser(config);
        assertNotNull(parser);
        assertTrue("Expected "+expected.getCanonicalName()+" got "+parser.getClass().getCanonicalName(),expected.isInstance(parser));
    }
    public void testDefaultParserFactoryConfig() throws Exception {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        try {
            factory.createFileEntryParser((FTPClientConfig)null);
            fail("Expected NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
        checkParserClass(factory, null, UnixFTPEntryParser.class);

        checkParserClass(factory, FTPClientConfig.SYST_OS400, OS400FTPEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_AS400, CompositeFileEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_L8, UnixFTPEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_MVS, MVSFTPEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_NETWARE, NetwareFTPEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_NT, NTFTPEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_OS2, OS2FTPEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_UNIX, UnixFTPEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_VMS, VMSFTPEntryParser.class);
        checkParserClass(factory, FTPClientConfig.SYST_MACOS_PETER, MacOsPeterFTPEntryParser.class);

        checkParserClass(factory, "WINDOWS", NTFTPEntryParser.class); // Same as SYST_NT
        // This is the way it works at present; config matching is exact
        checkParserClass(factory, "Windows", CompositeFileEntryParser.class);

        checkParserClass(factory, "OS/400", OS400FTPEntryParser.class); // Same as SYST_OS400
        // This is the way it works at present; config matching is exact
        checkParserClass(factory, "OS/400 v1", CompositeFileEntryParser.class);

        // Note: exact matching via config is the only way to generate NTFTPEntryParser and OS400FTPEntryParser
        // using DefaultFTPFileEntryParserFactory
    }

    public void testDefaultParserFactory_1_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_2_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_3_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        assertFalse(((UnixFTPEntryParser)parser).trimLeadingSpaces);
    }

    public void testDefaultParserFactory_4_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_5_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        assertTrue(((UnixFTPEntryParser)parser).trimLeadingSpaces);
    }

    public void testDefaultParserFactory_6_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_7_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_8_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        assertFalse(parser instanceof EnterpriseUnixFTPEntryParser);
    }

    public void testDefaultParserFactory_9_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_11_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            assertNull(pie.getCause());
    }
    }

    public void testDefaultParserFactory_12_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            assertTrue(pie.getMessage()+ "should contain 'Unknown parser type:'",pie.getMessage().contains("Unknown parser type:"));
    }
    }

    public void testDefaultParserFactory_13_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        assertTrue(parser instanceof CompositeFileEntryParser);
    }

    public void testDefaultParserFactory_14_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        assertTrue(parser instanceof VMSFTPEntryParser);
    }

    public void testDefaultParserFactory_15_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/2");
        assertTrue(parser instanceof OS2FTPEntryParser);
    }

    public void testDefaultParserFactory_16_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/2");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/400");
        assertTrue(parser instanceof CompositeFileEntryParser);
    }

    public void testDefaultParserFactory_17_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/2");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/400");
        // removed other assertion

        parser = factory.createFileEntryParser("AS/400");
        assertTrue(parser instanceof CompositeFileEntryParser);
    }

    public void testDefaultParserFactory_19_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/2");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/400");
        // removed other assertion

        parser = factory.createFileEntryParser("AS/400");
        // removed other assertion

        // Added test to make sure it handles the Unix systems that were
        // compiled with OS as "UNKNOWN". This test validates that the
        // check is case-insensitive.
        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            assertNull(pie.getCause());
    }
    }

    public void testDefaultParserFactory_20_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/2");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/400");
        // removed other assertion

        parser = factory.createFileEntryParser("AS/400");
        // removed other assertion

        // Added test to make sure it handles the Unix systems that were
        // compiled with OS as "UNKNOWN". This test validates that the
        // check is case-insensitive.
        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");
        assertTrue(parser instanceof OS2FTPEntryParser);
    }

    public void testDefaultParserFactory_22_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/2");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/400");
        // removed other assertion

        parser = factory.createFileEntryParser("AS/400");
        // removed other assertion

        // Added test to make sure it handles the Unix systems that were
        // compiled with OS as "UNKNOWN". This test validates that the
        // check is case-insensitive.
        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");
        // removed other assertion

        try {
            factory.createFileEntryParser(
                "org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            final Throwable root = pie.getCause();
            assertTrue(root instanceof ClassCastException);
    }
    }

    public void testDefaultParserFactory_24_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/2");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/400");
        // removed other assertion

        parser = factory.createFileEntryParser("AS/400");
        // removed other assertion

        // Added test to make sure it handles the Unix systems that were
        // compiled with OS as "UNKNOWN". This test validates that the
        // check is case-insensitive.
        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");
        // removed other assertion

        try {
            factory.createFileEntryParser(
                "org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            final Throwable root = pie.getCause();
            // removed other assertion
        }

        try {
            // Class exists, but is an interface
            factory.createFileEntryParser("org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory");
            // removed other assertion
        } catch (final ParserInitializationException pie){
            final Throwable root = pie.getCause();
            assertTrue(root instanceof InstantiationException);
    }
    }

    public void testDefaultParserFactory_26_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        // removed other assertion
        // removed other assertion

        parser = factory.createFileEntryParser("Unix");
        // removed other assertion

        parser = factory.createFileEntryParser("EnterpriseUnix");
        // removed other assertion
        // removed other assertion

        // works because contains the expression "Unix"
        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        // removed other assertion

        try {
            parser = factory.createFileEntryParser("NT");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
            // removed other assertion
        }

        parser = factory.createFileEntryParser("WindowsNT");
        // removed other assertion

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/2");
        // removed other assertion

        parser = factory.createFileEntryParser("OS/400");
        // removed other assertion

        parser = factory.createFileEntryParser("AS/400");
        // removed other assertion

        // Added test to make sure it handles the Unix systems that were
        // compiled with OS as "UNKNOWN". This test validates that the
        // check is case-insensitive.
        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            // removed other assertion
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");
        // removed other assertion

        try {
            factory.createFileEntryParser(
                "org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory");
            // removed other assertion
        } catch (final ParserInitializationException pie) {
            final Throwable root = pie.getCause();
            // removed other assertion
        }

        try {
            // Class exists, but is an interface
            factory.createFileEntryParser("org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory");
            // removed other assertion
        } catch (final ParserInitializationException pie){
            final Throwable root = pie.getCause();
            // removed other assertion
        }
        try {
            // Class exists, but is abstract
            factory.createFileEntryParser("org.apache.commons.net.ftp.FTPFileEntryParserImpl");
            // removed other assertion
        } catch (final ParserInitializationException pie){
            final Throwable root = pie.getCause();
            assertTrue(root instanceof InstantiationException);
    }
    }

}
