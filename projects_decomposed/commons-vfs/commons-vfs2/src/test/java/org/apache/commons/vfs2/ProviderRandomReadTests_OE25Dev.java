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

import org.apache.commons.vfs2.util.RandomAccessMode;
import org.junit.Test;

/**
 * Random read-only test case for file providers.
 *
 */
public class ProviderRandomReadTests_OE25Dev extends AbstractProviderTestCase {

    private static final String TEST_DATA = "This is a test file.";

    /**
     * Returns the capabilities required by the tests of this test case.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] { Capability.GET_TYPE, Capability.RANDOM_ACCESS_READ };
    }

    /**
     * Read a file
     */

    @Test
    public void testRandomRead_1_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            assertEquals(TEST_DATA.charAt(0), c);
    }
    }

    @Test
    public void testRandomRead_2_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            assertEquals("fp", 1, ra.getFilePointer());
    }
    }

    @Test
    public void testRandomRead_3_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            assertEquals(TEST_DATA.charAt(3), c);
    }
    }

    @Test
    public void testRandomRead_4_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            assertEquals("fp", 4, ra.getFilePointer());
    }
    }

    @Test
    public void testRandomRead_5_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            assertEquals(TEST_DATA.charAt(4), c);
    }
    }

    @Test
    public void testRandomRead_6_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            assertEquals("fp", 5, ra.getFilePointer());
    }
    }

    @Test
    public void testRandomRead_7_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // restart at pos 4
            ra.seek(3);
            c = ra.readByte();
            assertEquals(TEST_DATA.charAt(3), c);
    }
    }

    @Test
    public void testRandomRead_8_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // restart at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            assertEquals("fp", 4, ra.getFilePointer());
    }
    }

    @Test
    public void testRandomRead_9_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // restart at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            assertEquals(TEST_DATA.charAt(4), c);
    }
    }

    @Test
    public void testRandomRead_10_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // restart at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            assertEquals("fp", 5, ra.getFilePointer());
    }
    }

    @Test
    public void testRandomRead_11_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // restart at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // advance to pos 11
            ra.seek(10);
            c = ra.readByte();
            assertEquals(TEST_DATA.charAt(10), c);
    }
    }

    @Test
    public void testRandomRead_12_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // restart at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // advance to pos 11
            ra.seek(10);
            c = ra.readByte();
            // removed other assertion
            assertEquals("fp", 11, ra.getFilePointer());
    }
    }

    @Test
    public void testRandomRead_13_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // restart at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // advance to pos 11
            ra.seek(10);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            assertEquals(TEST_DATA.charAt(11), c);
    }
    }

    @Test
    public void testRandomRead_14_oe() throws Exception {
        try (FileObject file = getReadFolder().resolveFile("file1.txt")) {
            final RandomAccessContent ra = file.getContent().getRandomAccessContent(RandomAccessMode.READ);

            // read first byte
            byte c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // start at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // restart at pos 4
            ra.seek(3);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            // advance to pos 11
            ra.seek(10);
            c = ra.readByte();
            // removed other assertion
            // removed other assertion

            c = ra.readByte();
            // removed other assertion
            assertEquals("fp", 12, ra.getFilePointer());
    }
    }

}
