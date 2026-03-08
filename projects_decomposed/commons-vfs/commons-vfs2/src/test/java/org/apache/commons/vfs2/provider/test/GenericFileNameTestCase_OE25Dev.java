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
package org.apache.commons.vfs2.provider.test;

import org.apache.commons.vfs2.AbstractVfsTestCase;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.provider.GenericFileName;
import org.apache.commons.vfs2.provider.URLFileNameParser;
import org.junit.Test;

/**
 * Some GenericFileName test cases.
 */
public class GenericFileNameTestCase_OE25Dev extends AbstractVfsTestCase {

    /**
     * Tests error handling in URI parser.
     */
    @Test
    public void testBadlyFormedUri() throws Exception {
        // Does not start with ftp://
        testBadlyFormedUri("ftp:", "vfs.provider/missing-double-slashes.error");
        testBadlyFormedUri("ftp:/", "vfs.provider/missing-double-slashes.error");
        testBadlyFormedUri("ftp:a", "vfs.provider/missing-double-slashes.error");

        // Missing hostname
        testBadlyFormedUri("ftp://", "vfs.provider/missing-hostname.error");
        testBadlyFormedUri("ftp://:21/file", "vfs.provider/missing-hostname.error");
        testBadlyFormedUri("ftp:///file", "vfs.provider/missing-hostname.error");

        // Empty port
        testBadlyFormedUri("ftp://host:", "vfs.provider/missing-port.error");
        testBadlyFormedUri("ftp://host:/file", "vfs.provider/missing-port.error");
        testBadlyFormedUri("ftp://host:port/file", "vfs.provider/missing-port.error");

        // Missing absolute path
        testBadlyFormedUri("ftp://host:90a", "vfs.provider/missing-hostname-path-sep.error");
        testBadlyFormedUri("ftp://host?a", "vfs.provider/missing-hostname-path-sep.error");
    }

    /**
     * Tests that parsing a URI fails with the expected error.
     */
    private void testBadlyFormedUri(final String uri, final String errorMsg) {
        try {
            new URLFileNameParser(80).parseUri(null, null, uri);
            fail();
        } catch (final FileSystemException e) {
            assertSameMessage(errorMsg, uri, e);
        }
    }

    /**
     * Tests parsing a URI into its parts.
     */
    @Test
    public void testParseUri() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        assertEquals("ftp", name.getScheme());
        assertNull(name.getUserName());
        assertNull(name.getPassword());
        assertEquals("hostname", name.getHostName());
        assertEquals(21, name.getPort());
        assertEquals(name.getDefaultPort(), name.getPort());
        assertEquals("/file", name.getPath());
        assertEquals("ftp://hostname/", name.getRootURI());
        assertEquals("ftp://hostname/file", name.getURI());

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        assertEquals("ftp", name.getScheme());
        assertNull(name.getUserName());
        assertNull(name.getPassword());
        assertEquals("hostname", name.getHostName());
        assertEquals(9090, name.getPort());
        assertEquals("/file", name.getPath());
        assertEquals("ftp://hostname:9090/", name.getRootURI());
        assertEquals("ftp://hostname:9090/file", name.getURI());

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        assertEquals("ftp", name.getScheme());
        assertNull(name.getUserName());
        assertNull(name.getPassword());
        assertEquals("hostname", name.getHostName());
        assertEquals(21, name.getPort());
        assertEquals("/", name.getPath());
        assertEquals("ftp://hostname/", name.getRootURI());
        assertEquals("ftp://hostname/", name.getURI());

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        assertEquals("ftp", name.getScheme());
        assertEquals("user", name.getUserName());
        assertNull(name.getPassword());
        assertEquals("hostname", name.getHostName());
        assertEquals(21, name.getPort());
        assertEquals("/file", name.getPath());
        assertEquals("ftp://user@hostname/", name.getRootURI());
        assertEquals("ftp://user@hostname/file", name.getURI());

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        assertEquals("ftp", name.getScheme());
        assertEquals("user", name.getUserName());
        assertEquals("password", name.getPassword());
        assertEquals("hostname", name.getHostName());
        assertEquals(21, name.getPort());
        assertEquals("/file", name.getPath());
        assertEquals("ftp://user:password@hostname/", name.getRootURI());
        assertEquals("ftp://user:password@hostname/file", name.getURI());

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        assertEquals("ftp", name.getScheme());
        assertEquals("user:", name.getUserName());
        assertEquals("@", name.getPassword());
        assertEquals("hostname", name.getHostName());
        assertEquals(21, name.getPort());
        assertEquals("/", name.getPath());
        assertEquals("ftp://user%3a:%40@hostname/", name.getRootURI());
        assertEquals("ftp://user%3a:%40@hostname/", name.getURI());
    }

    @Test
    public void testParseUri_1_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        assertEquals("ftp", name.getScheme());
    }

    @Test
    public void testParseUri_2_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        assertNull(name.getUserName());
    }

    @Test
    public void testParseUri_3_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        assertNull(name.getPassword());
    }

    @Test
    public void testParseUri_4_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hostname", name.getHostName());
    }

    @Test
    public void testParseUri_5_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(21, name.getPort());
    }

    @Test
    public void testParseUri_6_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(name.getDefaultPort(), name.getPort());
    }

    @Test
    public void testParseUri_7_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("/file", name.getPath());
    }

    @Test
    public void testParseUri_8_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://hostname/", name.getRootURI());
    }

    @Test
    public void testParseUri_9_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://hostname/file", name.getURI());
    }

    @Test
    public void testParseUri_10_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        assertEquals("ftp", name.getScheme());
    }

    @Test
    public void testParseUri_11_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        assertNull(name.getUserName());
    }

    @Test
    public void testParseUri_12_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        assertNull(name.getPassword());
    }

    @Test
    public void testParseUri_13_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hostname", name.getHostName());
    }

    @Test
    public void testParseUri_14_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9090, name.getPort());
    }

    @Test
    public void testParseUri_15_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("/file", name.getPath());
    }

    @Test
    public void testParseUri_16_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://hostname:9090/", name.getRootURI());
    }

    @Test
    public void testParseUri_17_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://hostname:9090/file", name.getURI());
    }

    @Test
    public void testParseUri_18_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        assertEquals("ftp", name.getScheme());
    }

    @Test
    public void testParseUri_19_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        assertNull(name.getUserName());
    }

    @Test
    public void testParseUri_20_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        assertNull(name.getPassword());
    }

    @Test
    public void testParseUri_21_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hostname", name.getHostName());
    }

    @Test
    public void testParseUri_22_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(21, name.getPort());
    }

    @Test
    public void testParseUri_23_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("/", name.getPath());
    }

    @Test
    public void testParseUri_24_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://hostname/", name.getRootURI());
    }

    @Test
    public void testParseUri_25_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://hostname/", name.getURI());
    }

    @Test
    public void testParseUri_26_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        assertEquals("ftp", name.getScheme());
    }

    @Test
    public void testParseUri_27_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        assertEquals("user", name.getUserName());
    }

    @Test
    public void testParseUri_28_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        assertNull(name.getPassword());
    }

    @Test
    public void testParseUri_29_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hostname", name.getHostName());
    }

    @Test
    public void testParseUri_30_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(21, name.getPort());
    }

    @Test
    public void testParseUri_31_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("/file", name.getPath());
    }

    @Test
    public void testParseUri_32_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://user@hostname/", name.getRootURI());
    }

    @Test
    public void testParseUri_33_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://user@hostname/file", name.getURI());
    }

    @Test
    public void testParseUri_34_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        assertEquals("ftp", name.getScheme());
    }

    @Test
    public void testParseUri_35_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        assertEquals("user", name.getUserName());
    }

    @Test
    public void testParseUri_36_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        assertEquals("password", name.getPassword());
    }

    @Test
    public void testParseUri_37_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hostname", name.getHostName());
    }

    @Test
    public void testParseUri_38_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(21, name.getPort());
    }

    @Test
    public void testParseUri_39_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("/file", name.getPath());
    }

    @Test
    public void testParseUri_40_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://user:password@hostname/", name.getRootURI());
    }

    @Test
    public void testParseUri_41_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://user:password@hostname/file", name.getURI());
    }

    @Test
    public void testParseUri_42_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        assertEquals("ftp", name.getScheme());
    }

    @Test
    public void testParseUri_43_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        // removed other assertion
        assertEquals("user:", name.getUserName());
    }

    @Test
    public void testParseUri_44_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        // removed other assertion
        // removed other assertion
        assertEquals("@", name.getPassword());
    }

    @Test
    public void testParseUri_45_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hostname", name.getHostName());
    }

    @Test
    public void testParseUri_46_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(21, name.getPort());
    }

    @Test
    public void testParseUri_47_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("/", name.getPath());
    }

    @Test
    public void testParseUri_48_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://user%3a:%40@hostname/", name.getRootURI());
    }

    @Test
    public void testParseUri_49_oe() throws Exception {
        final URLFileNameParser urlParser = new URLFileNameParser(21);
        // Simple name
        GenericFileName name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with port
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname:9090/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with no path
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Name with username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://user:password@hostname/file");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Encoded username and password
        name = (GenericFileName) urlParser.parseUri(null, null, "ftp://%75ser%3A:%40@hostname");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ftp://user%3a:%40@hostname/", name.getURI());
    }

}
