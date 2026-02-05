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
package org.apache.commons.vfs2.provider.local;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests conversion from VFS to File.
 * <p>
 * VFS-443 Need an easy way to convert from a FileObject to a File.
 */
public class ConversionTestCase_OE25Dev {

@Test
    @Ignore
    public void testFileNameWithCharacters_1_oe() throws URISyntaxException, IOException {
        final File file = new File("target", "+# %&.txt");
        final String fileURL = file.toURI().toURL().toExternalForm();
        assertEquals(file.getAbsoluteFile(), new File(file.toURI().getPath()));
    }

@Test
    @Ignore
    public void testFileNameWithCharacters_2_oe() throws URISyntaxException, IOException {
        final File file = new File("target", "+# %&.txt");
        final String fileURL = file.toURI().toURL().toExternalForm();
        // removed other assertion
        assertEquals(file.getAbsoluteFile(), new File(new URL(fileURL).toURI().getPath()));
    }

@Test
    @Ignore
    public void testFileNameWithSpaces_1_oe() throws URISyntaxException, IOException {
        final File file = new File("target", "a name.txt");
        final String fileURL = file.toURI().toURL().toExternalForm();
        assertEquals(file.getAbsoluteFile(), new File(file.toURI().getPath()));
    }

@Test
    @Ignore
    public void testFileNameWithSpaces_2_oe() throws URISyntaxException, IOException {
        final File file = new File("target", "a name.txt");
        final String fileURL = file.toURI().toURL().toExternalForm();
        // removed other assertion
        assertEquals(file.getAbsoluteFile(), new File(new URL(fileURL).toURI().getPath()));
    }

@Test
    @Ignore
    public void testFileNameWithSpaces_3_oe() throws URISyntaxException, IOException {
        final File file = new File("target", "a name.txt");
        final String fileURL = file.toURI().toURL().toExternalForm();
        // removed other assertion
        // removed other assertion

        final FileSystemManager manager = VFS.getManager();
        final FileObject fo = manager.resolveFile(fileURL);
        assertEquals(file.getAbsoluteFile(), new File(new URL(fo.getURL().toExternalForm()).toURI().getPath()));
    }

}
