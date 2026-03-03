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

        parser = factory.createFileEntryParser("UNIX");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_3_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");
        assertFalse(((UnixFTPEntryParser)parser).trimLeadingSpaces);
    }

    public void testDefaultParserFactory_4_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_5_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");
        assertTrue(((UnixFTPEntryParser)parser).trimLeadingSpaces);
    }

    public void testDefaultParserFactory_6_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_7_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_8_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");
        assertFalse(parser instanceof EnterpriseUnixFTPEntryParser);
    }

    public void testDefaultParserFactory_9_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");
        assertTrue(parser instanceof UnixFTPEntryParser);
    }

    public void testDefaultParserFactory_11_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
            assertNull(pie.getCause());
    }
    }

    public void testDefaultParserFactory_12_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
            assertTrue(pie.getMessage()+ "should contain 'Unknown parser type:'",pie.getMessage().contains("Unknown parser type:"));
    }
    }

    public void testDefaultParserFactory_13_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");
        assertTrue(parser instanceof CompositeFileEntryParser);
    }

    public void testDefaultParserFactory_14_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");
        assertTrue(parser instanceof VMSFTPEntryParser);
    }

    public void testDefaultParserFactory_15_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");

        parser = factory.createFileEntryParser("OS/2");
        assertTrue(parser instanceof OS2FTPEntryParser);
    }

    public void testDefaultParserFactory_16_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");

        parser = factory.createFileEntryParser("OS/2");

        parser = factory.createFileEntryParser("OS/400");
        assertTrue(parser instanceof CompositeFileEntryParser);
    }

    public void testDefaultParserFactory_17_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");

        parser = factory.createFileEntryParser("OS/2");

        parser = factory.createFileEntryParser("OS/400");

        parser = factory.createFileEntryParser("AS/400");
        assertTrue(parser instanceof CompositeFileEntryParser);
    }

    public void testDefaultParserFactory_19_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");

        parser = factory.createFileEntryParser("OS/2");

        parser = factory.createFileEntryParser("OS/400");

        parser = factory.createFileEntryParser("AS/400");

        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
        } catch (final ParserInitializationException pie) {
            assertNull(pie.getCause());
    }
    }

    public void testDefaultParserFactory_20_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");

        parser = factory.createFileEntryParser("OS/2");

        parser = factory.createFileEntryParser("OS/400");

        parser = factory.createFileEntryParser("AS/400");

        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");
        assertTrue(parser instanceof OS2FTPEntryParser);
    }

    public void testDefaultParserFactory_22_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");

        parser = factory.createFileEntryParser("OS/2");

        parser = factory.createFileEntryParser("OS/400");

        parser = factory.createFileEntryParser("AS/400");

        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");

        try {
            factory.createFileEntryParser(
                "org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory");
        } catch (final ParserInitializationException pie) {
            final Throwable root = pie.getCause();
            assertTrue(root instanceof ClassCastException);
    }
    }

    public void testDefaultParserFactory_24_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");

        parser = factory.createFileEntryParser("OS/2");

        parser = factory.createFileEntryParser("OS/400");

        parser = factory.createFileEntryParser("AS/400");

        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");

        try {
            factory.createFileEntryParser(
                "org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory");
        } catch (final ParserInitializationException pie) {
            final Throwable root = pie.getCause();
        }

        try {
            factory.createFileEntryParser("org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory");
        } catch (final ParserInitializationException pie){
            final Throwable root = pie.getCause();
            assertTrue(root instanceof InstantiationException);
    }
    }

    public void testDefaultParserFactory_26_oe() {
        final DefaultFTPFileEntryParserFactory factory =
            new DefaultFTPFileEntryParserFactory();

        FTPFileEntryParser parser = factory.createFileEntryParser("unix");

        parser = factory.createFileEntryParser("UNIX");

        parser = factory.createFileEntryParser("UNIX_LTRIM");

        parser = factory.createFileEntryParser("Unix");

        parser = factory.createFileEntryParser("EnterpriseUnix");

        parser = factory.createFileEntryParser("UnixFTPEntryParser");

        try {
            parser = factory.createFileEntryParser("NT");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser("WindowsNT");

        parser = factory.createFileEntryParser("ThigaVMSaMaJig");

        parser = factory.createFileEntryParser("OS/2");

        parser = factory.createFileEntryParser("OS/400");

        parser = factory.createFileEntryParser("AS/400");

        parser = factory.createFileEntryParser("UNKNOWN Type: L8");

        try {
            parser = factory.createFileEntryParser("OS2FTPFileEntryParser");
        } catch (final ParserInitializationException pie) {
        }

        parser = factory.createFileEntryParser(
            "org.apache.commons.net.ftp.parser.OS2FTPEntryParser");

        try {
            factory.createFileEntryParser(
                "org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory");
        } catch (final ParserInitializationException pie) {
            final Throwable root = pie.getCause();
        }

        try {
            factory.createFileEntryParser("org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory");
        } catch (final ParserInitializationException pie){
            final Throwable root = pie.getCause();
        }
        try {
            factory.createFileEntryParser("org.apache.commons.net.ftp.FTPFileEntryParserImpl");
        } catch (final ParserInitializationException pie){
            final Throwable root = pie.getCause();
            assertTrue(root instanceof InstantiationException);
    }
    }

}
