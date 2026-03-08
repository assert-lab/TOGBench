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
package org.apache.commons.geometry.io.core.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.DoubleFunction;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.io.core.test.CloseCountWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class AbstractTextFormatWriterTest_OE25Dev {

    private StringWriter out = new StringWriter();

    private static final class TestWriter extends AbstractTextFormatWriter {

        protected TestWriter(final Writer writer) {
            super(writer);
        }
    }

    @Test
    void testDefaults_1_oe() {
        // act
        try (TestWriter writer = new TestWriter(out)) {
            // assert
            Assertions.assertEquals("\n", writer.getLineSeparator());
    }
    }

    @Test
    void testDefaults_2_oe() {
        // act
        try (TestWriter writer = new TestWriter(out)) {
            // assert
            // removed other assertion
            Assertions.assertNotNull(writer.getDoubleFormat());
    }
    }

    @Test
    void testDefaults_3_oe() {
        // act
        try (TestWriter writer = new TestWriter(out)) {
            // assert
            // removed other assertion
            // removed other assertion
            Assertions.assertSame(out, writer.getWriter());
    }
    }

    @Test
    void testWrite_defaultConfig_1_oe() {
        // arrange
        final double n = 20000.0 / 3.0;
        final CloseCountWriter closeCountWriter = new CloseCountWriter(out);
        try (TestWriter writer = new TestWriter(closeCountWriter)) {
            // act
            writer.write('a');
            writer.write("bc");
            writer.writeNewLine();
            writer.write(n);
            writer.writeNewLine();
            writer.write(Double.POSITIVE_INFINITY);
            writer.writeNewLine();
            writer.write(5);

            // assert
            Assertions.assertEquals("abc\n" + n + "\nInfinity\n5", out.toString());
    }
    }

    @Test
    void testWrite_defaultConfig_2_oe() {
        // arrange
        final double n = 20000.0 / 3.0;
        final CloseCountWriter closeCountWriter = new CloseCountWriter(out);
        try (TestWriter writer = new TestWriter(closeCountWriter)) {
            // act
            writer.write('a');
            writer.write("bc");
            writer.writeNewLine();
            writer.write(n);
            writer.writeNewLine();
            writer.write(Double.POSITIVE_INFINITY);
            writer.writeNewLine();
            writer.write(5);

            // assert
            // removed other assertion
        }

        Assertions.assertEquals(1, closeCountWriter.getCloseCount());
    }

    @Test
    void testWrite_customConfig_1_oe() {
        // arrange
        final CloseCountWriter closeCountWriter = new CloseCountWriter(out);
        try (TestWriter writer = new TestWriter(closeCountWriter)) {

            writer.setLineSeparator("\r\n");

            final DecimalFormat fmt = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

            final DoubleFunction<String> df = fmt::format;
            writer.setDoubleFormat(df);

            // act
            writer.write('a');
            writer.write("bc");
            writer.writeNewLine();
            writer.write(20000.0 / 3.0);
            writer.writeNewLine();
            writer.write(5);

            // assert
            Assertions.assertEquals("abc\r\n6666.67\r\n5", out.toString());
    }
    }

    @Test
    void testWrite_customConfig_2_oe() {
        // arrange
        final CloseCountWriter closeCountWriter = new CloseCountWriter(out);
        try (TestWriter writer = new TestWriter(closeCountWriter)) {

            writer.setLineSeparator("\r\n");

            final DecimalFormat fmt = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

            final DoubleFunction<String> df = fmt::format;
            writer.setDoubleFormat(df);

            // act
            writer.write('a');
            writer.write("bc");
            writer.writeNewLine();
            writer.write(20000.0 / 3.0);
            writer.writeNewLine();
            writer.write(5);

            // assert
            // removed other assertion
        }

        Assertions.assertEquals(1, closeCountWriter.getCloseCount());
    }

@Test
    void testWrite_failure_1_oe() {
        // arrange
        final Writer failWriter = new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("test");
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() {
            }
        };

        // act/assert
        try (TestWriter writer = new TestWriter(failWriter)) {
            try {
    writer.write('a');
    fail("Expected UncheckedIOException with message: " + "IOException: test");
} catch (UncheckedIOException e) {
}
    }
    }

@Test
    void testWrite_failure_2_oe() {
        // arrange
        final Writer failWriter = new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("test");
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() {
            }
        };

        // act/assert
        try (TestWriter writer = new TestWriter(failWriter)) {
            // removed other assertion

            try {
    writer.write("abc");
    fail("Expected UncheckedIOException with message: " + "IOException: test");
} catch (UncheckedIOException e) {
}
    }
    }

}
