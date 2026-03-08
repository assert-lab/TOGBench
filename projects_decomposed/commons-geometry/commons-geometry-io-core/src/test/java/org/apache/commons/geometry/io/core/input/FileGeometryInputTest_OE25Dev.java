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
package org.apache.commons.geometry.io.core.input;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileGeometryInputTest_OE25Dev {

    @TempDir
    Path tempDir;

    @Test
    void testCtor_fileOnly_1_oe() {
        // arrange
        final Path file = Paths.get("some/path/test.txt");

        // act
        final FileGeometryInput in = new FileGeometryInput(file);

        // assert
        Assertions.assertEquals(file, in.getFile());
    }

    @Test
    void testCtor_fileOnly_2_oe() {
        // arrange
        final Path file = Paths.get("some/path/test.txt");

        // act
        final FileGeometryInput in = new FileGeometryInput(file);

        // assert
        // removed other assertion
        Assertions.assertEquals("test.txt", in.getFileName());
    }

    @Test
    void testCtor_fileOnly_3_oe() {
        // arrange
        final Path file = Paths.get("some/path/test.txt");

        // act
        final FileGeometryInput in = new FileGeometryInput(file);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(in.getCharset());
    }

    @Test
    void testCtor_fileAndCharset_1_oe() {
        // arrange
        final Path file = Paths.get("TEST");
        final Charset charset = StandardCharsets.UTF_8;

        // act
        final FileGeometryInput in = new FileGeometryInput(file, charset);

        // assert
        Assertions.assertEquals(file, in.getFile());
    }

    @Test
    void testCtor_fileAndCharset_2_oe() {
        // arrange
        final Path file = Paths.get("TEST");
        final Charset charset = StandardCharsets.UTF_8;

        // act
        final FileGeometryInput in = new FileGeometryInput(file, charset);

        // assert
        // removed other assertion
        Assertions.assertEquals("TEST", in.getFileName());
    }

    @Test
    void testCtor_fileAndCharset_3_oe() {
        // arrange
        final Path file = Paths.get("TEST");
        final Charset charset = StandardCharsets.UTF_8;

        // act
        final FileGeometryInput in = new FileGeometryInput(file, charset);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(charset, in.getCharset());
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final FileGeometryInput in = new FileGeometryInput(Paths.get("some/path/test.txt"));

        // act
        final String result = in.toString();

        // assert
        Assertions.assertEquals("FileGeometryInput[file= some/path/test.txt]",result.replaceAll("\\\\","/"));
    }

    @Test
    void testGetInputStream_1_oe() throws IOException {
        // arrange
        final Path file = tempDir.resolve("test");
        final byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);
        Files.write(file, bytes);

        final FileGeometryInput input = new FileGeometryInput(file);

        // act/assert
        try (InputStream in = input.getInputStream()) {
            Assertions.assertEquals(BufferedInputStream.class, in.getClass());
    }
    }

    @Test
    void testGetInputStream_2_oe() throws IOException {
        // arrange
        final Path file = tempDir.resolve("test");
        final byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);
        Files.write(file, bytes);

        final FileGeometryInput input = new FileGeometryInput(file);

        // act/assert
        try (InputStream in = input.getInputStream()) {
            // removed other assertion

            final byte[] readBytes = new byte[3];
            in.read(readBytes);

            Assertions.assertArrayEquals(bytes, readBytes);
    }
    }

}
