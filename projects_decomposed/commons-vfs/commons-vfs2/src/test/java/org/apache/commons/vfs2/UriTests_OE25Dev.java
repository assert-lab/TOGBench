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

import java.net.URI;

import org.junit.Test;

/**
 * URI test cases for providers.
 */
public class UriTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * Returns the capabilities required by the tests of this test case. The tests are not run if the provider being
     * tested does not support all the required capabilities. Return null or an empty array to always run the tests.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] {Capability.URI};
    }

    /**
     * Tests resolution of absolute URI.
     */

    @Test
    public void testReservedCharacterSpace() throws FileSystemException {
        try (final FileObject fileObject = getReadFolder().resolveFile("file with spaces.txt")) {
            final URI url = fileObject.getURI();
            final String string = url.toString();
            assertTrue(string, string.contains("file%20with%20spaces.txt"));
        }
        try (final FileObject fileObject = getReadFolder().resolveFile("file%20with%20spaces.txt")) {
            final URI url = fileObject.getURI();
            final String string = url.toString();
            assertTrue(string, string.contains("file%20with%20spaces.txt"));
        }
    }

    /**
     * Tests content.
     */


}
