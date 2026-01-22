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
    void testRegisterReadHandler_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);
        final StubReadHandler r3 = new StubReadHandler(FMT_A_ALT);

        // act
        manager.registerReadHandler(r1); // will be replaced by r3
        manager.registerReadHandler(r2);
        manager.registerReadHandler(r2); // register 2x
        manager.registerReadHandler(r3);

        // assert
        Assertions.assertSame(r3, manager.getReadHandlerForFormat(FMT_A));
    }

    @Test
    void testRegisterReadHandler_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);
        final StubReadHandler r3 = new StubReadHandler(FMT_A_ALT);

        // act
        manager.registerReadHandler(r1); // will be replaced by r3
        manager.registerReadHandler(r2);
        manager.registerReadHandler(r2); // register 2x
        manager.registerReadHandler(r3);

        // assert
        // removed other assertion
        Assertions.assertSame(r2, manager.getReadHandlerForFormat(FMT_B));
    }

    @Test
    void testRegisterReadHandler_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);
        final StubReadHandler r3 = new StubReadHandler(FMT_A_ALT);

        // act
        manager.registerReadHandler(r1); // will be replaced by r3
        manager.registerReadHandler(r2);
        manager.registerReadHandler(r2); // register 2x
        manager.registerReadHandler(r3);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(r3, manager.getReadHandlerForFileExtension("a"));
    }

    @Test
    void testRegisterReadHandler_4_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);
        final StubReadHandler r3 = new StubReadHandler(FMT_A_ALT);

        // act
        manager.registerReadHandler(r1); // will be replaced by r3
        manager.registerReadHandler(r2);
        manager.registerReadHandler(r2); // register 2x
        manager.registerReadHandler(r3);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(manager.getReadHandlerForFileExtension("aext"));
    }

    @Test
    void testRegisterReadHandler_5_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);
        final StubReadHandler r3 = new StubReadHandler(FMT_A_ALT);

        // act
        manager.registerReadHandler(r1); // will be replaced by r3
        manager.registerReadHandler(r2);
        manager.registerReadHandler(r2); // register 2x
        manager.registerReadHandler(r3);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(r2, manager.getReadHandlerForFileExtension("b"));
    }

    @Test
    void testRegisterReadHandler_6_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);
        final StubReadHandler r3 = new StubReadHandler(FMT_A_ALT);

        // act
        manager.registerReadHandler(r1); // will be replaced by r3
        manager.registerReadHandler(r2);
        manager.registerReadHandler(r2); // register 2x
        manager.registerReadHandler(r3);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Arrays.asList(r2, r3), manager.getReadHandlers());
    }

    @Test
    void testRegisterReadHandler_multipleFileExtensions_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);

        // act
        manager.registerReadHandler(r1);

        // assert
        Assertions.assertSame(r1, manager.getReadHandlerForFormat(FMT_A_ALT));
    }

    @Test
    void testRegisterReadHandler_multipleFileExtensions_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);

        // act
        manager.registerReadHandler(r1);

        // assert
        // removed other assertion

        Assertions.assertSame(r1, manager.getReadHandlerForFileExtension("A"));
    }

    @Test
    void testRegisterReadHandler_multipleFileExtensions_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);

        // act
        manager.registerReadHandler(r1);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(r1, manager.getReadHandlerForFileExtension("AEXT"));
    }

    @Test
    void testRegisterReadHandler_nullAndMissingFileExt_1_oe() {
        // arrange
        final StubGeometryFormat noExts = new StubGeometryFormat("a", null);
        final StubGeometryFormat nullExts = new StubGeometryFormat("b", Arrays.asList("bext", null, null));

        final StubReadHandler r1 = new StubReadHandler(noExts);
        final StubReadHandler r2 = new StubReadHandler(nullExts);

        // act
        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // assert
        Assertions.assertSame(r1, manager.getReadHandlerForFormat(noExts));
    }

    @Test
    void testRegisterReadHandler_nullAndMissingFileExt_2_oe() {
        // arrange
        final StubGeometryFormat noExts = new StubGeometryFormat("a", null);
        final StubGeometryFormat nullExts = new StubGeometryFormat("b", Arrays.asList("bext", null, null));

        final StubReadHandler r1 = new StubReadHandler(noExts);
        final StubReadHandler r2 = new StubReadHandler(nullExts);

        // act
        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // assert
        // removed other assertion
        Assertions.assertNull(manager.getReadHandlerForFileExtension("a"));
    }

    @Test
    void testRegisterReadHandler_nullAndMissingFileExt_3_oe() {
        // arrange
        final StubGeometryFormat noExts = new StubGeometryFormat("a", null);
        final StubGeometryFormat nullExts = new StubGeometryFormat("b", Arrays.asList("bext", null, null));

        final StubReadHandler r1 = new StubReadHandler(noExts);
        final StubReadHandler r2 = new StubReadHandler(nullExts);

        // act
        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(r2, manager.getReadHandlerForFormat(nullExts));
    }

    @Test
    void testRegisterReadHandler_nullAndMissingFileExt_4_oe() {
        // arrange
        final StubGeometryFormat noExts = new StubGeometryFormat("a", null);
        final StubGeometryFormat nullExts = new StubGeometryFormat("b", Arrays.asList("bext", null, null));

        final StubReadHandler r1 = new StubReadHandler(noExts);
        final StubReadHandler r2 = new StubReadHandler(nullExts);

        // act
        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(r2, manager.getReadHandlerForFileExtension("bext"));
    }

    @Test
    void testUnregisterReadHandler_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act
        manager.unregisterReadHandler(r1);

        // assert
        Assertions.assertNull(manager.getReadHandlerForFormat(FMT_A));
    }

    @Test
    void testUnregisterReadHandler_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act
        manager.unregisterReadHandler(r1);

        // assert
        // removed other assertion
        Assertions.assertSame(r2, manager.getReadHandlerForFormat(FMT_B));
    }

    @Test
    void testUnregisterReadHandler_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act
        manager.unregisterReadHandler(r1);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Arrays.asList(r2), manager.getReadHandlers());
    }

    @Test
    void testUnregisterReadHandler_argsNotRegistered_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);

        // act
        manager.unregisterReadHandler(null);
        manager.unregisterReadHandler(r2);

        // assert
        Assertions.assertEquals(Arrays.asList(r1), manager.getReadHandlers());
    }

    @Test
    void testGetReadHandlerForFormat_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        Assertions.assertSame(r1, manager.getReadHandlerForFormat(FMT_A));
    }

    @Test
    void testGetReadHandlerForFormat_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        Assertions.assertSame(r1, manager.getReadHandlerForFormat(FMT_A_ALT));
    }

    @Test
    void testGetReadHandlerForFormat_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(r2, manager.getReadHandlerForFormat(FMT_B));
    }

    @Test
    void testGetReadHandlerForFormat_4_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(r2, manager.getReadHandlerForFormat(FMT_B_ALT));
    }

    @Test
    void testGetReadHandlerForFormat_5_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(manager.getReadHandlerForFormat(null));
    }

    @Test
    void testGetReadHandlerForFormat_6_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(manager.getReadHandlerForFormat(FMT_C));
    }

    @Test
    void testGetReadHandlerForFileExtension_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        Assertions.assertSame(r1, manager.getReadHandlerForFileExtension("a"));
    }

    @Test
    void testGetReadHandlerForFileExtension_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        Assertions.assertSame(r1, manager.getReadHandlerForFileExtension("A"));
    }

    @Test
    void testGetReadHandlerForFileExtension_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(r1, manager.getReadHandlerForFileExtension("aext"));
    }

    @Test
    void testGetReadHandlerForFileExtension_4_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(r1, manager.getReadHandlerForFileExtension("AeXt"));
    }

    @Test
    void testGetReadHandlerForFileExtension_5_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(r2, manager.getReadHandlerForFileExtension("b"));
    }

    @Test
    void testGetReadHandlerForFileExtension_6_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(r2, manager.getReadHandlerForFileExtension("B"));
    }

    @Test
    void testGetReadHandlerForFileExtension_7_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(manager.getReadHandlerForFileExtension(null));
    }

    @Test
    void testGetReadHandlerForFileExtension_8_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(manager.getReadHandlerForFileExtension(""));
    }

    @Test
    void testGetReadHandlerForFileExtension_9_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(manager.getReadHandlerForFileExtension("c"));
    }

    @Test
    void testRequireReadHandler_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final GeometryInput aInput = new StubGeometryInput("/some/path/to/a/file.AEXT");
        final GeometryInput bInput = new StubGeometryInput("/some/path/to/a/file.b");
        final GeometryInput noFileExt = new StubGeometryInput("/some/path/to/a/file");
        final GeometryInput nullFileName = new StubGeometryInput(null);

        manager.registerReadHandler(r1);

        // act/assert
        Assertions.assertSame(r1, manager.requireReadHandler(bInput, FMT_A));
    }

    @Test
    void testRequireReadHandler_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final GeometryInput aInput = new StubGeometryInput("/some/path/to/a/file.AEXT");
        final GeometryInput bInput = new StubGeometryInput("/some/path/to/a/file.b");
        final GeometryInput noFileExt = new StubGeometryInput("/some/path/to/a/file");
        final GeometryInput nullFileName = new StubGeometryInput(null);

        manager.registerReadHandler(r1);

        // act/assert
        // removed other assertion
        Assertions.assertSame(r1, manager.requireReadHandler(noFileExt, FMT_A));
    }

    @Test
    void testRequireReadHandler_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final GeometryInput aInput = new StubGeometryInput("/some/path/to/a/file.AEXT");
        final GeometryInput bInput = new StubGeometryInput("/some/path/to/a/file.b");
        final GeometryInput noFileExt = new StubGeometryInput("/some/path/to/a/file");
        final GeometryInput nullFileName = new StubGeometryInput(null);

        manager.registerReadHandler(r1);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(r1, manager.requireReadHandler(aInput, null));
    }

    @Test
    void testGetReadFormats_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);
        final StubReadHandler r3 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);
        manager.registerReadHandler(r3);

        // act
        final List<GeometryFormat> formats = manager.getReadFormats();

        // assert
        Assertions.assertEquals(2, formats.size());
    }

    @Test
    void testGetReadFormats_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        final StubReadHandler r2 = new StubReadHandler(FMT_B);
        final StubReadHandler r3 = new StubReadHandler(FMT_B);

        manager.registerReadHandler(r1);
        manager.registerReadHandler(r2);
        manager.registerReadHandler(r3);

        // act
        final List<GeometryFormat> formats = manager.getReadFormats();

        // assert
        // removed other assertion
        Assertions.assertEquals(Arrays.asList(FMT_A, FMT_B), formats);
    }

    @Test
    void testGetReadFormats_empty_1_oe() {
        // act/assert
        Assertions.assertEquals(0, manager.getReadFormats().size());
    }

    @Test
    void testRegisterWriteHandler_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_A_ALT);

        // act
        manager.registerWriteHandler(w1); // will be replaced by w3
        manager.registerWriteHandler(w2);
        manager.registerWriteHandler(w2); // register 2x
        manager.registerWriteHandler(w3);

        // assert
        Assertions.assertSame(w3, manager.getWriteHandlerForFormat(FMT_A));
    }

    @Test
    void testRegisterWriteHandler_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_A_ALT);

        // act
        manager.registerWriteHandler(w1); // will be replaced by w3
        manager.registerWriteHandler(w2);
        manager.registerWriteHandler(w2); // register 2x
        manager.registerWriteHandler(w3);

        // assert
        // removed other assertion
        Assertions.assertSame(w2, manager.getWriteHandlerForFormat(FMT_B));
    }

    @Test
    void testRegisterWriteHandler_3_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_A_ALT);

        // act
        manager.registerWriteHandler(w1); // will be replaced by w3
        manager.registerWriteHandler(w2);
        manager.registerWriteHandler(w2); // register 2x
        manager.registerWriteHandler(w3);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(w3, manager.getWriteHandlerForFileExtension("a"));
    }

    @Test
    void testRegisterWriteHandler_4_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_A_ALT);

        // act
        manager.registerWriteHandler(w1); // will be replaced by w3
        manager.registerWriteHandler(w2);
        manager.registerWriteHandler(w2); // register 2x
        manager.registerWriteHandler(w3);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(manager.getWriteHandlerForFileExtension("aext"));
    }

    @Test
    void testRegisterWriteHandler_5_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_A_ALT);

        // act
        manager.registerWriteHandler(w1); // will be replaced by w3
        manager.registerWriteHandler(w2);
        manager.registerWriteHandler(w2); // register 2x
        manager.registerWriteHandler(w3);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(w2, manager.getWriteHandlerForFileExtension("b"));
    }

    @Test
    void testRegisterWriteHandler_6_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_A_ALT);

        // act
        manager.registerWriteHandler(w1); // will be replaced by w3
        manager.registerWriteHandler(w2);
        manager.registerWriteHandler(w2); // register 2x
        manager.registerWriteHandler(w3);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Arrays.asList(w2, w3), manager.getWriteHandlers());
    }

    @Test
    void testRegisterWriteHandler_multipleFileExtensions_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);

        // act
        manager.registerWriteHandler(w1);

        // assert
        Assertions.assertSame(w1, manager.getWriteHandlerForFormat(FMT_A_ALT));
    }

    @Test
    void testRegisterWriteHandler_multipleFileExtensions_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);

        // act
        manager.registerWriteHandler(w1);

        // assert
        // removed other assertion

        Assertions.assertSame(w1, manager.getWriteHandlerForFileExtension("A"));
    }

    @Test
    void testRegisterWriteHandler_multipleFileExtensions_3_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);

        // act
        manager.registerWriteHandler(w1);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(w1, manager.getWriteHandlerForFileExtension("AEXT"));
    }

    @Test
    void testRegisterWriteHandler_nullAndMissingFileExt_1_oe() {
        // arrange
        final StubGeometryFormat noExts = new StubGeometryFormat("a", null);
        final StubGeometryFormat nullExts = new StubGeometryFormat("b", Arrays.asList("bext", null, null));

        final StubWriteHandler w1 = new StubWriteHandler(noExts);
        final StubWriteHandler w2 = new StubWriteHandler(nullExts);

        // act
        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // assert
        Assertions.assertSame(w1, manager.getWriteHandlerForFormat(noExts));
    }

    @Test
    void testRegisterWriteHandler_nullAndMissingFileExt_2_oe() {
        // arrange
        final StubGeometryFormat noExts = new StubGeometryFormat("a", null);
        final StubGeometryFormat nullExts = new StubGeometryFormat("b", Arrays.asList("bext", null, null));

        final StubWriteHandler w1 = new StubWriteHandler(noExts);
        final StubWriteHandler w2 = new StubWriteHandler(nullExts);

        // act
        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // assert
        // removed other assertion
        Assertions.assertNull(manager.getWriteHandlerForFileExtension("a"));
    }

    @Test
    void testRegisterWriteHandler_nullAndMissingFileExt_3_oe() {
        // arrange
        final StubGeometryFormat noExts = new StubGeometryFormat("a", null);
        final StubGeometryFormat nullExts = new StubGeometryFormat("b", Arrays.asList("bext", null, null));

        final StubWriteHandler w1 = new StubWriteHandler(noExts);
        final StubWriteHandler w2 = new StubWriteHandler(nullExts);

        // act
        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(w2, manager.getWriteHandlerForFormat(nullExts));
    }

    @Test
    void testRegisterWriteHandler_nullAndMissingFileExt_4_oe() {
        // arrange
        final StubGeometryFormat noExts = new StubGeometryFormat("a", null);
        final StubGeometryFormat nullExts = new StubGeometryFormat("b", Arrays.asList("bext", null, null));

        final StubWriteHandler w1 = new StubWriteHandler(noExts);
        final StubWriteHandler w2 = new StubWriteHandler(nullExts);

        // act
        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(w2, manager.getWriteHandlerForFileExtension("bext"));
    }

    @Test
    void testUnregisterWriteHandler_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act
        manager.unregisterWriteHandler(w1);

        // assert
        Assertions.assertNull(manager.getWriteHandlerForFormat(FMT_A));
    }

    @Test
    void testUnregisterWriteHandler_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act
        manager.unregisterWriteHandler(w1);

        // assert
        // removed other assertion
        Assertions.assertSame(w2, manager.getWriteHandlerForFormat(FMT_B));
    }

    @Test
    void testUnregisterWriteHandler_3_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act
        manager.unregisterWriteHandler(w1);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Arrays.asList(w2), manager.getWriteHandlers());
    }

    @Test
    void testUnregisterWriteHandler_argsNotRegistered_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_C);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act
        manager.unregisterWriteHandler(null);
        manager.unregisterWriteHandler(w3);

        // assert
        Assertions.assertEquals(Arrays.asList(w1, w2), manager.getWriteHandlers());
    }

    @Test
    void testGetWriteFormats_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);
        manager.registerWriteHandler(w3);

        // act
        final List<GeometryFormat> formats = manager.getWriteFormats();

        // assert
        Assertions.assertEquals(2, formats.size());
    }

    @Test
    void testGetWriteFormats_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);
        final StubWriteHandler w3 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);
        manager.registerWriteHandler(w3);

        // act
        final List<GeometryFormat> formats = manager.getWriteFormats();

        // assert
        // removed other assertion
        Assertions.assertEquals(Arrays.asList(FMT_A, FMT_B), formats);
    }

    @Test
    void testGetWriteFormats_empty_1_oe() {
        // act/assert
        Assertions.assertEquals(0, manager.getWriteFormats().size());
    }

    @Test
    void testGetWriteHandlerForFormat_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        Assertions.assertSame(w1, manager.getWriteHandlerForFormat(FMT_A));
    }

    @Test
    void testGetWriteHandlerForFormat_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        Assertions.assertSame(w1, manager.getWriteHandlerForFormat(FMT_A_ALT));
    }

    @Test
    void testGetWriteHandlerForFormat_3_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(w2, manager.getWriteHandlerForFormat(FMT_B));
    }

    @Test
    void testGetWriteHandlerForFormat_4_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(w2, manager.getWriteHandlerForFormat(FMT_B_ALT));
    }

    @Test
    void testGetWriteHandlerForFormat_5_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(manager.getWriteHandlerForFormat(null));
    }

    @Test
    void testGetWriteHandlerForFormat_6_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(manager.getWriteHandlerForFormat(FMT_C));
    }

    @Test
    void testGetWriteHandlerForFileExtension_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        Assertions.assertSame(w1, manager.getWriteHandlerForFileExtension("a"));
    }

    @Test
    void testGetWriteHandlerForFileExtension_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        Assertions.assertSame(w1, manager.getWriteHandlerForFileExtension("A"));
    }

    @Test
    void testGetWriteHandlerForFileExtension_3_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(w1, manager.getWriteHandlerForFileExtension("aext"));
    }

    @Test
    void testGetWriteHandlerForFileExtension_4_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(w1, manager.getWriteHandlerForFileExtension("AeXt"));
    }

    @Test
    void testGetWriteHandlerForFileExtension_5_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(w2, manager.getWriteHandlerForFileExtension("b"));
    }

    @Test
    void testGetWriteHandlerForFileExtension_6_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(w2, manager.getWriteHandlerForFileExtension("B"));
    }

    @Test
    void testGetWriteHandlerForFileExtension_7_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(manager.getWriteHandlerForFileExtension(null));
    }

    @Test
    void testGetWriteHandlerForFileExtension_8_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(manager.getWriteHandlerForFileExtension(""));
    }

    @Test
    void testGetWriteHandlerForFileExtension_9_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final StubWriteHandler w2 = new StubWriteHandler(FMT_B);

        manager.registerWriteHandler(w1);
        manager.registerWriteHandler(w2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(manager.getWriteHandlerForFileExtension("c"));
    }

    @Test
    void testRequireWriteHandler_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final GeometryOutput aInput = new StubGeometryOutput("/some/path/to/a/file.AEXT");
        final GeometryOutput bInput = new StubGeometryOutput("/some/path/to/a/file.b");
        final GeometryOutput noFileExt = new StubGeometryOutput("/some/path/to/a/file");
        final GeometryOutput nullFileName = new StubGeometryOutput(null);

        manager.registerWriteHandler(w1);

        // act/assert
        Assertions.assertSame(w1, manager.requireWriteHandler(bInput, FMT_A));
    }

    @Test
    void testRequireWriteHandler_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final GeometryOutput aInput = new StubGeometryOutput("/some/path/to/a/file.AEXT");
        final GeometryOutput bInput = new StubGeometryOutput("/some/path/to/a/file.b");
        final GeometryOutput noFileExt = new StubGeometryOutput("/some/path/to/a/file");
        final GeometryOutput nullFileName = new StubGeometryOutput(null);

        manager.registerWriteHandler(w1);

        // act/assert
        // removed other assertion
        Assertions.assertSame(w1, manager.requireWriteHandler(noFileExt, FMT_A));
    }

    @Test
    void testRequireWriteHandler_3_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        final GeometryOutput aInput = new StubGeometryOutput("/some/path/to/a/file.AEXT");
        final GeometryOutput bInput = new StubGeometryOutput("/some/path/to/a/file.b");
        final GeometryOutput noFileExt = new StubGeometryOutput("/some/path/to/a/file");
        final GeometryOutput nullFileName = new StubGeometryOutput(null);

        manager.registerWriteHandler(w1);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(w1, manager.requireWriteHandler(aInput, null));
    }

    @Test
    void testRead_formatGiven_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput(null);
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final TestBoundaryList result = manager.read(in, FMT_A_ALT, precision);

        // assert
        Assertions.assertSame(BOUNDARY_LIST, result);
    }

    @Test
    void testRead_formatGiven_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput(null);
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final TestBoundaryList result = manager.read(in, FMT_A_ALT, precision);

        // assert
        // removed other assertion
        Assertions.assertSame(in, r1.inArg);
    }

    @Test
    void testRead_formatGiven_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput(null);
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final TestBoundaryList result = manager.read(in, FMT_A_ALT, precision);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(precision, r1.precisionArg);
    }

    @Test
    void testRead_noFormatGiven_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput("file.aeXT");
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final TestBoundaryList result = manager.read(in, null, precision);

        // assert
        Assertions.assertSame(BOUNDARY_LIST, result);
    }

    @Test
    void testRead_noFormatGiven_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput("file.aeXT");
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final TestBoundaryList result = manager.read(in, null, precision);

        // assert
        // removed other assertion
        Assertions.assertSame(in, r1.inArg);
    }

    @Test
    void testRead_noFormatGiven_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput("file.aeXT");
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final TestBoundaryList result = manager.read(in, null, precision);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(precision, r1.precisionArg);
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.read(inputA, FMT_B, precision));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.read(inputB, null, precision));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.read(inputNull, null, precision));
    }

    @Test
    void testBoundaries_formatGiven_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput(null);
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final Stream<TestLineSegment> result = manager.boundaries(in, FMT_A_ALT, precision);

        // assert
        Assertions.assertEquals(BOUNDARY_LIST.getBoundaries(), result.collect(Collectors.toList()));
    }

    @Test
    void testBoundaries_formatGiven_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput(null);
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final Stream<TestLineSegment> result = manager.boundaries(in, FMT_A_ALT, precision);

        // assert
        // removed other assertion
        Assertions.assertSame(in, r1.inArg);
    }

    @Test
    void testBoundaries_formatGiven_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput(null);
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final Stream<TestLineSegment> result = manager.boundaries(in, FMT_A_ALT, precision);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(precision, r1.precisionArg);
    }

    @Test
    void testBoundaries_noFormatGiven_1_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput("file.aeXT");
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final Stream<TestLineSegment> result = manager.boundaries(in, null, precision);

        // assert
        Assertions.assertEquals(BOUNDARY_LIST.getBoundaries(), result.collect(Collectors.toList()));
    }

    @Test
    void testBoundaries_noFormatGiven_2_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput("file.aeXT");
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final Stream<TestLineSegment> result = manager.boundaries(in, null, precision);

        // assert
        // removed other assertion
        Assertions.assertSame(in, r1.inArg);
    }

    @Test
    void testBoundaries_noFormatGiven_3_oe() {
        // arrange
        final StubReadHandler r1 = new StubReadHandler(FMT_A);
        manager.registerReadHandler(r1);

        final StubGeometryInput in = new StubGeometryInput("file.aeXT");
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-4);

        // act
        final Stream<TestLineSegment> result = manager.boundaries(in, null, precision);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(precision, r1.precisionArg);
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.boundaries(inputA, FMT_B, precision));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.boundaries(inputB, null, precision));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.boundaries(inputNull, null, precision));
    }

    @Test
    void testWrite_formatGiven_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        manager.registerWriteHandler(w1);

        final TestBoundaryList src = BOUNDARY_LIST;
        final StubGeometryOutput out = new StubGeometryOutput(null);

        // act
        manager.write(BOUNDARY_LIST, out, FMT_A_ALT);

        // assert
        Assertions.assertSame(src, w1.list);
    }

    @Test
    void testWrite_formatGiven_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        manager.registerWriteHandler(w1);

        final TestBoundaryList src = BOUNDARY_LIST;
        final StubGeometryOutput out = new StubGeometryOutput(null);

        // act
        manager.write(BOUNDARY_LIST, out, FMT_A_ALT);

        // assert
        // removed other assertion
        Assertions.assertSame(out, w1.outArg);
    }

    @Test
    void testWrite_noFormatGiven_1_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        manager.registerWriteHandler(w1);

        final TestBoundaryList src = BOUNDARY_LIST;
        final StubGeometryOutput out = new StubGeometryOutput("file.aeXT");

        // act
        manager.write(src, out, null);

        // assert
        Assertions.assertSame(src, w1.list);
    }

    @Test
    void testWrite_noFormatGiven_2_oe() {
        // arrange
        final StubWriteHandler w1 = new StubWriteHandler(FMT_A);
        manager.registerWriteHandler(w1);

        final TestBoundaryList src = BOUNDARY_LIST;
        final StubGeometryOutput out = new StubGeometryOutput("file.aeXT");

        // act
        manager.write(src, out, null);

        // assert
        // removed other assertion
        Assertions.assertSame(out, w1.outArg);
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.write(src, outputA, FMT_B));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.write(src, outputB, null));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.write(src, nullOutput, null));
    }

}
