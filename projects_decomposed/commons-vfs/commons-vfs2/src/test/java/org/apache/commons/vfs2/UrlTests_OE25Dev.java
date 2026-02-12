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
package org.apache.commons.vfs2;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

/**
 * URL test cases for providers.
 */
public class UrlTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * Returns the capabilities required by the tests of this test case. The tests are not run if the provider being
     * tested does not support all the required capabilities. Return null or an empty array to always run the tests.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] {Capability.URI};
    }

    @Test
    public void testReservedCharacter_Space() throws FileSystemException {
        try (final FileObject fileObject = getReadFolder().resolveFile("file with spaces.txt")) {
            final URL url = fileObject.getURL();
            final String string = url.toString();
            assertTrue(string, string.contains("file%20with%20spaces.txt"));
        }
        try (final FileObject fileObject = getReadFolder().resolveFile("file%20with%20spaces.txt")) {
            final URL url = fileObject.getURL();
            final String string = url.toString();
            assertTrue(string, string.contains("file%20with%20spaces.txt"));
        }
    }

    /**
     * Tests that unknown files have no content.
     */

    /**
     * Tests url.
     */

    /**
     * Tests content.
     */
    @Test
    public void testURLContent() throws Exception {
        testURLContent(getReadFolder());
    }

    private void testURLContent(final FileObject readFolder) throws FileSystemException, IOException, Exception {
        // Test non-empty file
        FileObject file = readFolder.resolveFile("file1.txt");
        assertTrue(file.toString(), file.exists());

        URLConnection urlCon = file.getURL().openConnection();
        assertSameURLContent(FILE1_CONTENT, urlCon);

        // Test empty file
        file = readFolder.resolveFile("empty.txt");
        assertTrue(file.exists());

        urlCon = file.getURL().openConnection();
        assertSameURLContent("", urlCon);
    }

    /**
     * Tests content.
     */


}
