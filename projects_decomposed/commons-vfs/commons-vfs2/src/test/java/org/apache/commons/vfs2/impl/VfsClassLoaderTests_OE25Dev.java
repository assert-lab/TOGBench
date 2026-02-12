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
package org.apache.commons.vfs2.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;

import org.apache.commons.vfs2.AbstractProviderTestCase;
import org.apache.commons.vfs2.AbstractVfsTestCase;
import org.apache.commons.vfs2.Capability;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.junit.Test;

/**
 * VfsClassLoader test cases.
 */
public class VfsClassLoaderTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * Non-Delegating Class Loader.
     */
    public static class MockClassloader extends ClassLoader {
        MockClassloader() {
            super(null);
        }

        @Override
        protected Class<?> findClass(final String name) throws ClassNotFoundException {
            fail("Not intended to be used for class loading.");
            return null;
        }

        /**
         * This method will not return any hit to VFSClassLoader#testGetResourcesJARs.
         */
        @Override
        public Enumeration<URL> getResources(final String name) throws IOException {
            return Collections.enumeration(Collections.<URL>emptyList());
        }
    }

    /**
     * Creates the classloader to use when testing.
     */
    private VFSClassLoader createClassLoader() throws FileSystemException {
        return new VFSClassLoader(getBaseFolder(), getManager());
    }

    /**
     * Returns the capabilities required by the tests of this test case.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] { Capability.READ_CONTENT, Capability.URI };
    }

    /**
     * Tests retrieving resources (from JAR searchpath).
     * <p>
     * This is run for all providers, but only when a local provider is present and jar extension is registered it will
     * actually carry out all tests.
     * </p>
     */

    /**
     * Tests retrieving resources (from local directory with .jar extension).
     * <p>
     * This test is repeated with various provider configurations but works on local files, only.
     * </p>
     */

    /**
     * Tests loading a class.
     */

    /**
     * Tests loading a resource.
     */

    /**
     * Tests package sealing.
     */

    /**
     * Verify the package loaded with class loader.
     */
    private void verifyPackage(final Package pack, final boolean sealed) {
        if (getBaseFolder().getFileSystem().hasCapability(Capability.MANIFEST_ATTRIBUTES)) {
            assertEquals("ImplTitle", pack.getImplementationTitle());
            assertEquals("ImplVendor", pack.getImplementationVendor());
            assertEquals("1.1", pack.getImplementationVersion());
            assertEquals("SpecTitle", pack.getSpecificationTitle());
            assertEquals("SpecVendor", pack.getSpecificationVendor());
            assertEquals("1.0", pack.getSpecificationVersion());
            assertEquals(sealed, pack.isSealed());
        } else {
            assertNull(pack.getImplementationTitle());
            assertNull(pack.getImplementationVendor());
            assertNull(pack.getImplementationVersion());
            assertNull(pack.getSpecificationTitle());
            assertNull(pack.getSpecificationVendor());
            assertNull(pack.getSpecificationVersion());
            assertFalse(pack.isSealed());
        }
    }


}
