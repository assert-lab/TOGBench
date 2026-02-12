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
package org.apache.commons.geometry.io.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.partitioning.BoundaryList;
import org.apache.commons.geometry.core.partitioning.test.TestLineSegment;
import org.apache.commons.geometry.core.partitioning.test.TestPoint2D;
import org.apache.commons.geometry.io.core.input.GeometryInput;
import org.apache.commons.geometry.io.core.output.GeometryOutput;
import org.apache.commons.geometry.io.core.test.StubGeometryFormat;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoundaryIOManagerTest_OE25Dev {

    private static final TestLineSegment SEGMENT = new TestLineSegment(TestPoint2D.ZERO, TestPoint2D.PLUS_X);

    private static final TestBoundaryList BOUNDARY_LIST = new TestBoundaryList(Collections.singletonList(SEGMENT));

    private static final GeometryFormat FMT_A = new StubGeometryFormat("testA", Arrays.asList("a", "aext"));

    private static final GeometryFormat FMT_A_ALT = new StubGeometryFormat("TESTa", Collections.singletonList("A"));

    private static final GeometryFormat FMT_B = new StubGeometryFormat("testB", Collections.singletonList("b"));

    private static final GeometryFormat FMT_B_ALT = new StubGeometryFormat("TESTb", Collections.singletonList("B"));

    private static final GeometryFormat FMT_C = new StubGeometryFormat("testC", Collections.singletonList("c"));

    private final TestManager manager = new TestManager();

    @Test
    void testRegisterReadHandler_illegalArgs() {
        // arrange
        final StubReadHandler nullFmt = new StubReadHandler(null);
        final StubReadHandler nullFmtName = new StubReadHandler(new StubGeometryFormat(null));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
                () -> manager.registerReadHandler(null),
                NullPointerException.class, "Handler cannot be null");
        GeometryTestUtils.assertThrowsWithMessage(
                () -> manager.registerReadHandler(nullFmt),
                NullPointerException.class, "Format cannot be null");
        GeometryTestUtils.assertThrowsWithMessage(
                () -> manager.registerReadHandler(nullFmtName),
                NullPointerException.class, "Format name cannot be null");
    }

    @Test
    void testRegisterWriteHandler_illegalArgs() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
                () -> manager.registerWriteHandler(null),
                NullPointerException.class, "Handler cannot be null");
        GeometryTestUtils.assertThrowsWithMessage(
                () -> manager.registerWriteHandler(new StubWriteHandler(null)),
                NullPointerException.class, "Format cannot be null");
        GeometryTestUtils.assertThrowsWithMessage(
                () -> manager.registerWriteHandler(new StubWriteHandler(new StubGeometryFormat(null))),
                NullPointerException.class, "Format name cannot be null");
    }

    private static final class TestManager
        extends BoundaryIOManager<TestLineSegment, TestBoundaryList, StubReadHandler, StubWriteHandler> {
    }

    private static final class TestBoundaryList extends BoundaryList<TestPoint2D, TestLineSegment> {

        TestBoundaryList(final List<? extends TestLineSegment> boundaries) {
            super(boundaries);
        }
    }

    private static final class StubGeometryInput implements GeometryInput {

        private final String fileName;

        StubGeometryInput(final String fileName) {
            this.fileName = fileName;
        }

        /** {@inheritDoc} */
        @Override
        public String getFileName() {
            return fileName;
        }

        /** {@inheritDoc} */
        @Override
        public Charset getCharset() {
            throw new UnsupportedOperationException();
        }

        /** {@inheritDoc} */
        @Override
        public InputStream getInputStream() {
            throw new UnsupportedOperationException();
        }

    }

    private static final class StubGeometryOutput implements GeometryOutput {

        private final String fileName;

        StubGeometryOutput(final String fileName) {
            this.fileName = fileName;
        }

        /** {@inheritDoc} */
        @Override
        public String getFileName() {
            return fileName;
        }

        /** {@inheritDoc} */
        @Override
        public Charset getCharset() {
            throw new UnsupportedOperationException();
        }

        /** {@inheritDoc} */
        @Override
        public OutputStream getOutputStream() {
            throw new UnsupportedOperationException();
        }
    }

    private static final class StubReadHandler implements BoundaryReadHandler<TestLineSegment, TestBoundaryList> {

        private final GeometryFormat fmt;

        private GeometryInput inArg;

        private Precision.DoubleEquivalence precisionArg;

        StubReadHandler(final GeometryFormat fmt) {
            this.fmt = fmt;
        }

        /** {@inheritDoc} */
        @Override
        public GeometryFormat getFormat() {
            return fmt;
        }

        /** {@inheritDoc} */
        @Override
        public TestBoundaryList read(final GeometryInput in, final Precision.DoubleEquivalence precision) {
            this.inArg = in;
            this.precisionArg = precision;

            return BOUNDARY_LIST;
        }

        /** {@inheritDoc} */
        @Override
        public Stream<TestLineSegment> boundaries(final GeometryInput in,
                final Precision.DoubleEquivalence precision) {
            this.inArg = in;
            this.precisionArg = precision;

            return BOUNDARY_LIST.boundaryStream();
        }
    }

    private static final class StubWriteHandler implements BoundaryWriteHandler<TestLineSegment, TestBoundaryList> {

        private GeometryFormat fmt;

        private TestBoundaryList list;

        private GeometryOutput outArg;

        StubWriteHandler(final GeometryFormat fmt) {
            this.fmt = fmt;
        }

        /** {@inheritDoc} */
        @Override
        public GeometryFormat getFormat() {
            return fmt;
        }

        /** {@inheritDoc} */
        @Override
        public void write(final TestBoundaryList boundarySource, final GeometryOutput out) {
            this.list = boundarySource;
            this.outArg = out;
        }
    }

    @Test
    void testRead_handlerNotFound_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput inputA = new StubGeometryInput("file.a");
        final StubGeometryInput inputB = new StubGeometryInput("file.b");
        final StubGeometryInput inputNull = new StubGeometryInput(null);

        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act/assert
        try {
    manager.read(inputA, FMT_B, precision);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testRead_handlerNotFound_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput inputA = new StubGeometryInput("file.a");
        final StubGeometryInput inputB = new StubGeometryInput("file.b");
        final StubGeometryInput inputNull = new StubGeometryInput(null);

        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act/assert
        // removed other assertion
        try {
    manager.read(inputB, null, precision);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testRead_handlerNotFound_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput inputA = new StubGeometryInput("file.a");
        final StubGeometryInput inputB = new StubGeometryInput("file.b");
        final StubGeometryInput inputNull = new StubGeometryInput(null);

        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    manager.read(inputNull, null, precision);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testBoundaries_handlerNotFound_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput inputA = new StubGeometryInput("file.a");
        final StubGeometryInput inputB = new StubGeometryInput("file.b");
        final StubGeometryInput inputNull = new StubGeometryInput(null);

        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act/assert
        try {
    manager.boundaries(inputA, FMT_B, precision);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testBoundaries_handlerNotFound_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput inputA = new StubGeometryInput("file.a");
        final StubGeometryInput inputB = new StubGeometryInput("file.b");
        final StubGeometryInput inputNull = new StubGeometryInput(null);

        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act/assert
        // removed other assertion
        try {
    manager.boundaries(inputB, null, precision);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testBoundaries_handlerNotFound_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput inputA = new StubGeometryInput("file.a");
        final StubGeometryInput inputB = new StubGeometryInput("file.b");
        final StubGeometryInput inputNull = new StubGeometryInput(null);

        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    manager.boundaries(inputNull, null, precision);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWrite_handlerNotFound_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        manager.registerWriteHandler(w1);

        final StubGeometryOutput outputA = new StubGeometryOutput("file.a");
        final StubGeometryOutput outputB = new StubGeometryOutput("file.b");
        final StubGeometryOutput nullOutput = new StubGeometryOutput(null);

        final TestBoundaryList src = BOUNDARY_LIST;

        // act/assert
        try {
    manager.write(src, outputA, FMT_B);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWrite_handlerNotFound_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        manager.registerWriteHandler(w1);

        final StubGeometryOutput outputA = new StubGeometryOutput("file.a");
        final StubGeometryOutput outputB = new StubGeometryOutput("file.b");
        final StubGeometryOutput nullOutput = new StubGeometryOutput(null);

        final TestBoundaryList src = BOUNDARY_LIST;

        // act/assert
        // removed other assertion
        try {
    manager.write(src, outputB, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWrite_handlerNotFound_3_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        manager.registerWriteHandler(w1);

        final StubGeometryOutput outputA = new StubGeometryOutput("file.a");
        final StubGeometryOutput outputB = new StubGeometryOutput("file.b");
        final StubGeometryOutput nullOutput = new StubGeometryOutput(null);

        final TestBoundaryList src = BOUNDARY_LIST;

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    manager.write(src, nullOutput, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
