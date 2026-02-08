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

    @Test
    public void testGetResourcesJARs_1_oe() throws Exception {
        final FileSystemManager manager = getManager();
        try {
            // hasProvider("file") cannot be used as it triggers default provider URL
            manager.toFileObject(new File("."));
        } catch (final FileSystemException e) {
            System.out.println("VfsClassLoaderTests no local file provider, skipping.");
            return;
        }

        // build search path without using #getBaseFolder()
        // because NestedJarTestCase redefines it
        final File baseDir = AbstractVfsTestCase.getTestDirectoryFile();
        final FileObject nestedJar = manager.resolveFile(baseDir, "nested.jar");
        final FileObject testJar = manager.resolveFile(baseDir, "test.jar");

        // test setup needs to know about .jar extension - i.e. NestedJarTestCase
        if (!manager.canCreateFileSystem(nestedJar)) {
            System.out.println("VfsClassLoaderTests no layered .jar provider, skipping.");
            return;
        }

        // verify test setup
        assertSame("nested.jar is required for testing", nestedJar.getType(), FileType.FILE);
    }

    @Test
    public void testGetResourcesJARs_2_oe() throws Exception {
        final FileSystemManager manager = getManager();
        try {
            // hasProvider("file") cannot be used as it triggers default provider URL
            manager.toFileObject(new File("."));
        } catch (final FileSystemException e) {
            System.out.println("VfsClassLoaderTests no local file provider, skipping.");
            return;
        }

        // build search path without using #getBaseFolder()
        // because NestedJarTestCase redefines it
        final File baseDir = AbstractVfsTestCase.getTestDirectoryFile();
        final FileObject nestedJar = manager.resolveFile(baseDir, "nested.jar");
        final FileObject testJar = manager.resolveFile(baseDir, "test.jar");

        // test setup needs to know about .jar extension - i.e. NestedJarTestCase
        if (!manager.canCreateFileSystem(nestedJar)) {
            System.out.println("VfsClassLoaderTests no layered .jar provider, skipping.");
            return;
        }

        // verify test setup
        // removed other assertion
        assertSame("test.jar is required for testing", testJar.getType(), FileType.FILE);
    }

    @Test
    public void testGetResourcesJARs_3_oe() throws Exception {
        final FileSystemManager manager = getManager();
        try {
            // hasProvider("file") cannot be used as it triggers default provider URL
            manager.toFileObject(new File("."));
        } catch (final FileSystemException e) {
            System.out.println("VfsClassLoaderTests no local file provider, skipping.");
            return;
        }

        // build search path without using #getBaseFolder()
        // because NestedJarTestCase redefines it
        final File baseDir = AbstractVfsTestCase.getTestDirectoryFile();
        final FileObject nestedJar = manager.resolveFile(baseDir, "nested.jar");
        final FileObject testJar = manager.resolveFile(baseDir, "test.jar");

        // test setup needs to know about .jar extension - i.e. NestedJarTestCase
        if (!manager.canCreateFileSystem(nestedJar)) {
            System.out.println("VfsClassLoaderTests no layered .jar provider, skipping.");
            return;
        }

        // verify test setup
        // removed other assertion
        // removed other assertion

        // System class loader (null) might be unpredictable in regards
        // to returning resources for META-INF/MANIFEST.MF (see VFS-500)
        // so we use our own which is guaranteed to not return any hit
        final ClassLoader mockClassloader = new MockClassloader();
        final FileObject[] search = new FileObject[] { nestedJar, testJar };
        final VFSClassLoader loader = new VFSClassLoader(search, getManager(), mockClassloader);

        final Enumeration<URL> urls = loader.getResources("META-INF/MANIFEST.MF");
        final URL url1 = urls.nextElement();
        final URL url2 = urls.nextElement();

        assertTrue("First resource must refer to nested.jar but was " + url1,url1.toString().endsWith("nested.jar!/META-INF/MANIFEST.MF"));
    }

    @Test
    public void testGetResourcesJARs_4_oe() throws Exception {
        final FileSystemManager manager = getManager();
        try {
            // hasProvider("file") cannot be used as it triggers default provider URL
            manager.toFileObject(new File("."));
        } catch (final FileSystemException e) {
            System.out.println("VfsClassLoaderTests no local file provider, skipping.");
            return;
        }

        // build search path without using #getBaseFolder()
        // because NestedJarTestCase redefines it
        final File baseDir = AbstractVfsTestCase.getTestDirectoryFile();
        final FileObject nestedJar = manager.resolveFile(baseDir, "nested.jar");
        final FileObject testJar = manager.resolveFile(baseDir, "test.jar");

        // test setup needs to know about .jar extension - i.e. NestedJarTestCase
        if (!manager.canCreateFileSystem(nestedJar)) {
            System.out.println("VfsClassLoaderTests no layered .jar provider, skipping.");
            return;
        }

        // verify test setup
        // removed other assertion
        // removed other assertion

        // System class loader (null) might be unpredictable in regards
        // to returning resources for META-INF/MANIFEST.MF (see VFS-500)
        // so we use our own which is guaranteed to not return any hit
        final ClassLoader mockClassloader = new MockClassloader();
        final FileObject[] search = new FileObject[] { nestedJar, testJar };
        final VFSClassLoader loader = new VFSClassLoader(search, getManager(), mockClassloader);

        final Enumeration<URL> urls = loader.getResources("META-INF/MANIFEST.MF");
        final URL url1 = urls.nextElement();
        final URL url2 = urls.nextElement();

        // removed other assertion
        assertTrue("Second resource must refer to test.jar but was " + url2,url2.toString().endsWith("test.jar!/META-INF/MANIFEST.MF"));
    }

    @Test
    public void testGetResourcesNoLayerLocal_1_oe() throws Exception {
        final FileSystemManager manager = getManager();
        try {
            // hasProvider("file") cannot be used as it triggers default provider URL
            manager.toFileObject(new File("."));
        } catch (final FileSystemException e) {
            System.out.println("VfsClassLoaderTests no local file provider, skipping.");
            return;
        }
        final File baseDir = AbstractVfsTestCase.getTestDirectoryFile();

        // setup test folder
        final FileObject dir = manager.resolveFile(baseDir, "read-tests/dir1/subdir4.jar");
        assertSame("subdir4.jar/ is required for testing " + dir, dir.getType(), FileType.FOLDER);
    }

    @Test
    public void testGetResourcesNoLayerLocal_2_oe() throws Exception {
        final FileSystemManager manager = getManager();
        try {
            // hasProvider("file") cannot be used as it triggers default provider URL
            manager.toFileObject(new File("."));
        } catch (final FileSystemException e) {
            System.out.println("VfsClassLoaderTests no local file provider, skipping.");
            return;
        }
        final File baseDir = AbstractVfsTestCase.getTestDirectoryFile();

        // setup test folder
        final FileObject dir = manager.resolveFile(baseDir, "read-tests/dir1/subdir4.jar");
        // removed other assertion
        assertFalse(manager.canCreateFileSystem(dir));
    }

    @Test
    public void testGetResourcesNoLayerLocal_3_oe() throws Exception {
        final FileSystemManager manager = getManager();
        try {
            // hasProvider("file") cannot be used as it triggers default provider URL
            manager.toFileObject(new File("."));
        } catch (final FileSystemException e) {
            System.out.println("VfsClassLoaderTests no local file provider, skipping.");
            return;
        }
        final File baseDir = AbstractVfsTestCase.getTestDirectoryFile();

        // setup test folder
        final FileObject dir = manager.resolveFile(baseDir, "read-tests/dir1/subdir4.jar");
        // removed other assertion
        // removed other assertion

        // prepare classloader
        final FileObject[] search = new FileObject[] { dir };
        final ClassLoader mockClassloader = new MockClassloader();
        final VFSClassLoader loader = new VFSClassLoader(search, getManager(), mockClassloader);

        // verify resource loading
        final Enumeration<URL> urls = loader.getResources("file1.txt");
        final URL url1 = urls.nextElement();
        assertFalse("Only one hit expected", urls.hasMoreElements());
    }

    @Test
    public void testGetResourcesNoLayerLocal_4_oe() throws Exception {
        final FileSystemManager manager = getManager();
        try {
            // hasProvider("file") cannot be used as it triggers default provider URL
            manager.toFileObject(new File("."));
        } catch (final FileSystemException e) {
            System.out.println("VfsClassLoaderTests no local file provider, skipping.");
            return;
        }
        final File baseDir = AbstractVfsTestCase.getTestDirectoryFile();

        // setup test folder
        final FileObject dir = manager.resolveFile(baseDir, "read-tests/dir1/subdir4.jar");
        // removed other assertion
        // removed other assertion

        // prepare classloader
        final FileObject[] search = new FileObject[] { dir };
        final ClassLoader mockClassloader = new MockClassloader();
        final VFSClassLoader loader = new VFSClassLoader(search, getManager(), mockClassloader);

        // verify resource loading
        final Enumeration<URL> urls = loader.getResources("file1.txt");
        final URL url1 = urls.nextElement();
        // removed other assertion
        assertTrue("not pointing to resource " + url1, url1.toString().endsWith("subdir4.jar/file1.txt"));
    }

    @Test
    public void testLoadClass_1_oe() throws Exception {
        final VFSClassLoader loader = createClassLoader();

        final Class<?> testClass = loader.loadClass("code.ClassToLoad");
        final Package pack = testClass.getPackage();
        assertEquals("code", pack.getName());
    }

    @Test
    public void testLoadClass_2_oe() throws Exception {
        final VFSClassLoader loader = createClassLoader();

        final Class<?> testClass = loader.loadClass("code.ClassToLoad");
        final Package pack = testClass.getPackage();
        // removed other assertion
        verifyPackage(pack, false);

        final Object testObject = testClass.newInstance();
        assertEquals("**PRIVATE**", testObject.toString());
    }

    @Test
    public void testLoadResource_1_oe() throws Exception {
        final VFSClassLoader loader = createClassLoader();

        final URL resource = loader.getResource("read-tests/file1.txt");

        assertNotNull(resource);
    }

    @Test
    public void testSealing_1_oe() throws Exception {
        final VFSClassLoader loader = createClassLoader();
        final Class<?> testClass = loader.loadClass("code.sealed.AnotherClass");
        final Package pack = testClass.getPackage();
        assertEquals("code.sealed", pack.getName());
    }

    @Test
    public void testLoadResource_2_oe_1_oe() throws Exception {
        final VFSClassLoader loader = createClassLoader();

        final URL resource = loader.getResource("read-tests/file1.txt");

        // removed other assertion
        final URLConnection urlCon = resource.openConnection();
                final String expected0 = FILE1_CONTENT;
        final URLConnection connection0 = urlCon;
        // Get file content as a binary stream
                final byte[] expectedBin0 = expected0.getBytes(StandardCharsets.UTF_8);
        
                // Check lengths
                assertEquals("same content length", expectedBin0.length, connection0.getContentLength());
    }

    @Test
    public void testLoadResource_2_oe_2_oe() throws Exception {
        final VFSClassLoader loader = createClassLoader();

        final URL resource = loader.getResource("read-tests/file1.txt");

        // removed other assertion
        final URLConnection urlCon = resource.openConnection();
                final String expected0 = FILE1_CONTENT;
        final URLConnection connection0 = urlCon;
        // Get file content as a binary stream
                final byte[] expectedBin0 = expected0.getBytes(StandardCharsets.UTF_8);
        
                // Check lengths
                // removed other assertion
        
                // Read content into byte array
                final InputStream instr0 = connection0.getInputStream();
                final ByteArrayOutputStream outstr0;
                try {
                    outstr0 = new ByteArrayOutputStream();
                    final byte[] buffer0 = new byte[256];
                    int nread0 = 0;
                    while (nread0 >= 0) {
                        outstr0.write(buffer0, 0, nread0);
                        nread0 = instr0.read(buffer0);
                    }
                } finally {
                    instr0.close();
                }
        
                // Compare
                assertArrayEquals("same binary content", expectedBin0, outstr0.toByteArray());
    }

}
