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
package org.apache.commons.geometry.io.core.internal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.io.core.input.GeometryInput;
import org.apache.commons.geometry.io.core.input.StreamGeometryInput;
import org.apache.commons.geometry.io.core.output.GeometryOutput;
import org.apache.commons.geometry.io.core.output.StreamGeometryOutput;
import org.apache.commons.geometry.io.core.test.CloseCountInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeometryIOUtilsTest_OE25Dev {

    @Test
    void testGetUnchecked_failure() {
        // arrange
        final IOSupplier<String> supplier = () -> {
            throw new IOException("test");
        };

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
                () -> GeometryIOUtils.getUnchecked(supplier),
                UncheckedIOException.class,
                "IOException: test");
    }

    @Test
    void testAcceptUnchecked_failure() {
        // arrange
        final IOConsumer<String> consumer = str -> {
            throw new IOException(str);
        };

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
                () -> GeometryIOUtils.acceptUnchecked(consumer, "arg"),
                UncheckedIOException.class,
                "IOException: arg");
    }

    @Test
    void testApplyAsIntUnchecked_failure() {
        // arrange
        final IOToIntFunction<String> consumer = str -> {
            throw new IOException(str);
        };

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
                () -> GeometryIOUtils.applyAsIntUnchecked(consumer, "arg"),
                UncheckedIOException.class,
                "IOException: arg");
    }

    @Test
    void testTryApplyCloseable_supplierThrows_ioException() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            GeometryIOUtils.tryApplyCloseable(i -> {
                throw new IOException("fn");
            }, () -> {
                throw new IOException("supplier");
            });
        }, UncheckedIOException.class, "IOException: supplier");
    }

    @Test
    void testTryApplyCloseable_supplierThrows_runtimeException() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            GeometryIOUtils.tryApplyCloseable(i -> {
                throw new IOException("fn");
            }, () -> {
                throw new RuntimeException("supplier");
            });
        }, RuntimeException.class, "supplier");
    }


    @Test
    void testTryApplyCloseable_functionThrows() {
        // arrange
        final CloseCountInputStream in = new CloseCountInputStream(new ByteArrayInputStream(new byte[0]));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            GeometryIOUtils.tryApplyCloseable(i -> {
                throw new IOException("fn");
            }, () -> in);
        }, UncheckedIOException.class, "IOException: fn");

        Assertions.assertEquals(1, in.getCloseCount());
    }

    @Test
    void testCreateCloseableStream() {
        // arrange
        final CloseCountInputStream in = new CloseCountInputStream(new ByteArrayInputStream(new byte[0]));

        // act/assert
        try (Stream<String> stream = GeometryIOUtils.createCloseableStream(i -> Stream.of("a"), () -> in)) {
            Assertions.assertEquals(Arrays.asList("a"), stream.collect(Collectors.toList()));
            Assertions.assertEquals(0, in.getCloseCount());
        }

        Assertions.assertEquals(1, in.getCloseCount());
    }

    @Test
    void testCreateCloseableStream_closeThrows() {
        // arrange
        final CloseCountInputStream in = new CloseCountInputStream(new CloseFailByteArrayInputStream(new byte[0]));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
                () -> GeometryIOUtils.createCloseableStream(i -> Stream.of("a"), () -> in).close(),
                UncheckedIOException.class, "IOException: close");
    }

    private static final class CloseFailByteArrayInputStream extends ByteArrayInputStream {

        CloseFailByteArrayInputStream(final byte[] buf) {
            super(buf);
        }

        @Override
        public void close() throws IOException {
            throw new IOException("close");
        }
    }

    @Test
    void testTryApplyCloseable_functionThrows_inputCloseThrows_1_oe() {
        // arrange
        final CloseCountInputStream in = new CloseCountInputStream(new CloseFailByteArrayInputStream(new byte[0]));

        // act/assert
        final Throwable thr = Assertions.assertThrows(UncheckedIOException.class, () -> { GeometryIOUtils.tryApplyCloseable(i -> { throw new IOException("fn"); }, () -> in); });
    }

}
