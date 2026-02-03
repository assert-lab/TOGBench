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
package org.apache.commons.geometry.io.euclidean.threed.obj;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PolygonObjParserTest_OE25Dev {

    private static final double EPS = 1e-10;

    @Test
    void testInitialState() {
        // act
        final PolygonObjParser p = parser("");

        // assert
        Assertions.assertNull(p.getCurrentKeyword());
        Assertions.assertEquals(0, p.getVertexCount());
        Assertions.assertEquals(0, p.getVertexNormalCount());
        Assertions.assertEquals(0, p.getTextureCoordinateCount());
        Assertions.assertFalse(p.isFailOnNonPolygonKeywords());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_invalid() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "",
                "curv2 abc"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.nextKeyword();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 1: expected keyword to be one of " +
                "[f, g, mtllib, o, s, usemtl, v, vn, vt] but was [curv2]");
    }

    @Test
    void testNextKeyword_unexpectedContent() {
        // arrange
        final PolygonObjParser p = parser(lines(
                    " f",
                    "-- bad comment attempt"
                ));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.nextKeyword();
        }, IllegalStateException.class, "Parsing failed at line 1, column 2: " +
            "non-blank lines must begin with an OBJ keyword or comment character");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.nextKeyword();
        }, IllegalStateException.class, "Parsing failed at line 2, column 1: " +
            "expected OBJ keyword but found empty token followed by [-]");
    }

    @Test
    void testReadDataLine() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "  line\t",
                "",
                " \\",
                "a \\",
                "b\\",
                "cd\\",
                ".\\"
        ));

        // act/assert
        Assertions.assertEquals("  line\t", p.readDataLine());
        Assertions.assertEquals("", p.readDataLine());
        Assertions.assertEquals(" a bcd.", p.readDataLine());
        Assertions.assertNull(p.readDataLine());
    }

    @Test
    void testDiscardDataLine() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "  line\t",
                "",
                " \\",
                "a \\",
                "b\\",
                "cd\\",
                ".\\"
        ));

        // act/assert
        p.discardDataLine();
        Assertions.assertEquals(2, p.getTextParser().getLineNumber());
        Assertions.assertEquals(1, p.getTextParser().getColumnNumber());

        p.discardDataLine();
        Assertions.assertEquals(3, p.getTextParser().getLineNumber());
        Assertions.assertEquals(1, p.getTextParser().getColumnNumber());

        p.discardDataLine();
        Assertions.assertEquals(8, p.getTextParser().getLineNumber());
        Assertions.assertEquals(1, p.getTextParser().getColumnNumber());

        p.discardDataLine();
        Assertions.assertEquals(8, p.getTextParser().getLineNumber());
        Assertions.assertEquals(1, p.getTextParser().getColumnNumber());
    }

    @Test
    void testReadVector() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "1.01 3e-02 123.999 extra"
        ));

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1.01, 0.03, 123.999), p.readVector(), EPS);
    }

    @Test
    void testReadVector_parseFailures() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "0.1 0.2 a",
                "1",
                ""
        ));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readVector();
        }, IllegalStateException.class, "Parsing failed at line 1, column 9: expected double but found [a]");

        p.readDataLine();

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readVector();
        }, IllegalStateException.class, "Parsing failed at line 2, column 2: expected double but found end of line");
    }

    @Test
    void testReadDoubles() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "0.1 0.2 3e2 4e2 500.01",
                "  12.001  ",
                "  ",
                ""
        ));

        // act/assert
        Assertions.assertArrayEquals(new double[] {
            0.1, 0.2, 3e2, 4e2, 500.01
        }, p.readDoubles(), EPS);
        Assertions.assertArrayEquals(new double[0], p.readDoubles(), EPS);

        p.readDataLine();

        Assertions.assertArrayEquals(new double[] {12.001}, p.readDoubles(), EPS);

        p.readDataLine();

        Assertions.assertArrayEquals(new double[0], p.readDoubles(), EPS);

        p.readDataLine();

        Assertions.assertArrayEquals(new double[0], p.readDoubles(), EPS);
    }

    @Test
    void testReadDoubles_parseFailures() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "0.1 0.2 a",
                "b"
        ));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readDoubles();
        }, IllegalStateException.class, "Parsing failed at line 1, column 9: expected double but found [a]");

        p.readDataLine();

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readDoubles();
        }, IllegalStateException.class, "Parsing failed at line 2, column 1: expected double but found [b]");
    }

    @Test
    void testReadFace() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "v 0 0 0",
                "v 1 0 0",
                "v 1 1 0",
                "v 0 1 0",
                "vt 1 2",
                "vt 3 4",
                "vt 5 6",
                "vt 7 8",
                "vt 9 10",
                "vn 0 0 1",
                "vn 0 0 -1",

                "f 1 2 3 4",
                "f -4// -3// -2// -1//",

                "f 1//1 2//2 3//1 4//2",
                "f -4//-2 -3//-1 -2//-2 -1//-1",

                "f 1/4/1 2/3/2 3/2/1 4/1/2",
                "f -4/-1/-2 -3/-2/-1 -2/-3/-2 -1/-4/-1",

                "f 1/4 2/3 3/2 4/1",
                "f -4/-1 -3/-2 -2/-3 -1/-4"
        ));

        nextFace(p);

        // act/assert
        assertFace(new int[][] {
            {0, -1, -1},
            {1, -1, -1},
            {2, -1, -1},
            {3, -1, -1},
        }, p.readFace());

        nextFace(p);

        assertFace(new int[][] {
            {0, -1, -1},
            {1, -1, -1},
            {2, -1, -1},
            {3, -1, -1},
        }, p.readFace());

        nextFace(p);

        assertFace(new int[][] {
            {0, -1, 0},
            {1, -1, 1},
            {2, -1, 0},
            {3, -1, 1},
        }, p.readFace());

        nextFace(p);

        assertFace(new int[][] {
            {0, -1, 0},
            {1, -1, 1},
            {2, -1, 0},
            {3, -1, 1},
        }, p.readFace());

        nextFace(p);

        assertFace(new int[][] {
            {0, 3, 0},
            {1, 2, 1},
            {2, 1, 0},
            {3, 0, 1},
        }, p.readFace());

        nextFace(p);

        assertFace(new int[][] {
            {0, 4, 0},
            {1, 3, 1},
            {2, 2, 0},
            {3, 1, 1},
        }, p.readFace());

        nextFace(p);

        assertFace(new int[][] {
            {0, 3, -1},
            {1, 2, -1},
            {2, 1, -1},
            {3, 0, -1},
        }, p.readFace());

        nextFace(p);

        assertFace(new int[][] {
            {0, 4, -1},
            {1, 3, -1},
            {2, 2, -1},
            {3, 1, -1},
        }, p.readFace());
    }

    @Test
    void testReadFace_notEnoughVertices() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "v 0 0 0",
                "v 1 0 0",
                "v 1 1 0",
                "f 1 2"
        ));

        // act/assert
        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 5, column 6: " +
            "face must contain at least 3 vertices but found only 2");
    }

    @Test
    void testReadFace_invalidVertexIndex() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "f 1 2 3",
                "v 0 0 0",
                "v 1 0 0",
                "v 1 1 0",
                "f 1 2 -4",
                "f 1 0 3",
                "f 4 2 3"
        ));

        // act/assert
        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 2, column 3: " +
            "vertex index cannot be used because no values of that type have been defined");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 6, column 7: " +
            "vertex index must evaluate to be within the range [1, 3] but was -4");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 7, column 5: " +
            "vertex index must evaluate to be within the range [1, 3] but was 0");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 8, column 3: " +
            "vertex index must evaluate to be within the range [1, 3] but was 4");
    }

    @Test
    void testReadFace_invalidTextureIndex() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "v 0 0 0",
                "v 1 0 0",
                "v 1 1 0",
                "f 1/1 2/2 3/3",
                "vt 1 2",
                "vt 3 4",
                "vt 5 6",
                "f 1/1 2/2 3/-4",
                "f 1/1 1/0 3/3",
                "f 1/4 2/2 3/3"
        ));

        // act/assert
        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 5, column 5: " +
            "texture index cannot be used because no values of that type have been defined");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 9, column 13: " +
            "texture index must evaluate to be within the range [1, 3] but was -4");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 10, column 9: " +
            "texture index must evaluate to be within the range [1, 3] but was 0");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 11, column 5: " +
            "texture index must evaluate to be within the range [1, 3] but was 4");
    }

    @Test
    void testReadFace_invalidNormalIndex() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "v 0 0 0",
                "v 1 0 0",
                "v 1 1 0",
                "f 1//1 2//2 3//3",
                "vn 1 0 0",
                "vn 0 1 0",
                "vn 0 0 1",
                "f 1//1 2//2 3//-4",
                "f 1//1 1//0 3//3",
                "f 1//4 2//2 3//3"
        ));

        // act/assert
        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 5, column 6: " +
            "normal index cannot be used because no values of that type have been defined");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 9, column 16: " +
            "normal index must evaluate to be within the range [1, 3] but was -4");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 10, column 11: " +
            "normal index must evaluate to be within the range [1, 3] but was 0");

        nextFace(p);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.readFace();
        }, IllegalStateException.class, "Parsing failed at line 11, column 6: " +
            "normal index must evaluate to be within the range [1, 3] but was 4");
    }

    @Test
    void testFace_getDefinedCompositeNormal() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v 0 0 0",
                "v 1 0 0",
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vn 0 0 1",
                "vn 0 0 -1",
                "vn 2 2 2",
                "vn -2 2 2",
                "",
                "f 1 2 3 4",
                "f 1//1 2 3",
                "f 1//1 2//1 3//1 4//1",
                "f 1//1 2//2 3//1 4//2",
                "f 1//-2 2//-1 3//3 4//4"
        ));

        final List<Vector3D> normals = Arrays.asList(
                Vector3D.Unit.PLUS_Z,
                Vector3D.Unit.MINUS_Z,
                Vector3D.of(1, 1, 1),
                Vector3D.of(-1, 1, 1));
        final IntFunction<Vector3D> normalFn = normals::get;

        // act/assert
        nextMatchingKeyword("f", p);
        Assertions.assertNull(p.readFace().getDefinedCompositeNormal(normalFn));

        nextMatchingKeyword("f", p);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Z,
                p.readFace().getDefinedCompositeNormal(normalFn), EPS);

        nextMatchingKeyword("f", p);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Z,
                p.readFace().getDefinedCompositeNormal(normalFn), EPS);

        nextMatchingKeyword("f", p);
        Assertions.assertNull(p.readFace().getDefinedCompositeNormal(normalFn));

        nextMatchingKeyword("f", p);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, 1).normalize(),
                p.readFace().getDefinedCompositeNormal(normalFn), EPS);
    }

    @Test
    void testFace_computeNormalFromVertices() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v 0 0 0",
                "v 1 0 0",
                "v 2 0 0",
                "v 0 1 0",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1//1 2//1 3//1"
        ));

        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO,
                Vector3D.Unit.PLUS_X,
                Vector3D.of(2, 0, 0),
                Vector3D.of(0, 1, 0));
        final IntFunction<Vector3D> vertexFn = vertices::get;

        // act/assert
        nextMatchingKeyword("f", p);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Z,
                p.readFace().computeNormalFromVertices(vertexFn), EPS);

        nextMatchingKeyword("f", p);
        Assertions.assertNull(p.readFace().computeNormalFromVertices(vertexFn));
    }

    @Test
    void testFace_getVertexAttributesCounterClockwise() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v 0 0 0",
                "v 1 0 0",
                "v 0 1 0",
                "f 1 2 3"
        ));

        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO,
                Vector3D.Unit.PLUS_X,
                Vector3D.Unit.PLUS_Y,
                Vector3D.of(2, 0, 0));
        final IntFunction<Vector3D> vertexFn = vertices::get;

        nextMatchingKeyword("f", p);
        final PolygonObjParser.Face f = p.readFace();

        final List<PolygonObjParser.VertexAttributes> attrs = f.getVertexAttributes();

        final List<PolygonObjParser.VertexAttributes> reverseAttrs = new ArrayList<>(attrs);
        Collections.reverse(reverseAttrs);

        // act/assert
        Assertions.assertEquals(attrs, f.getVertexAttributesCounterClockwise(null, vertexFn));

        Assertions.assertEquals(attrs, f.getVertexAttributesCounterClockwise(Vector3D.Unit.PLUS_Z, vertexFn));
        Assertions.assertEquals(attrs, f.getVertexAttributesCounterClockwise(Vector3D.of(1, 0, 0.1), vertexFn));
        Assertions.assertEquals(attrs, f.getVertexAttributesCounterClockwise(Vector3D.Unit.PLUS_X, vertexFn));

        Assertions.assertEquals(reverseAttrs, f.getVertexAttributesCounterClockwise(Vector3D.Unit.MINUS_Z, vertexFn));
        Assertions.assertEquals(reverseAttrs, f.getVertexAttributesCounterClockwise(Vector3D.of(1, 0, -0.1), vertexFn));
    }

    @Test
    void testFace_getVertices() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v 0 0 0",
                "v 1 0 0",
                "v 1 1 0",
                "v 0 1 0",
                "v 0 0 1",
                "v 0 0 -1",
                "",
                "f 2 3 4"
        ));

        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO,
                Vector3D.Unit.PLUS_X,
                Vector3D.of(1, 1, 0),
                Vector3D.Unit.PLUS_Y,
                Vector3D.of(0, 0, 1),
                Vector3D.of(0, 0, -1));
        final IntFunction<Vector3D> vertexFn = vertices::get;

        // act/assert
        nextMatchingKeyword("f", p);
        Assertions.assertEquals(vertices.subList(1, 4), p.readFace().getVertices(vertexFn));
    }

    @Test
    void testFace_getVerticesCounterClockwise() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v 0 0 0",
                "v 1 0 0",
                "v 0 1 0",
                "v 0 0 -1",
                "f 1 2 3"
        ));

        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO,
                Vector3D.Unit.PLUS_X,
                Vector3D.Unit.PLUS_Y,
                Vector3D.of(0, 0, -1));
        final IntFunction<Vector3D> vertexFn = vertices::get;

        final List<Vector3D> faceVertices = vertices.subList(0, 3);
        final List<Vector3D> reverseFaceVertices = new ArrayList<>(faceVertices);
        Collections.reverse(reverseFaceVertices);

        nextMatchingKeyword("f", p);
        final PolygonObjParser.Face f = p.readFace();

        // act/assert
        Assertions.assertEquals(faceVertices, f.getVerticesCounterClockwise(null, vertexFn));

        Assertions.assertEquals(faceVertices, f.getVerticesCounterClockwise(Vector3D.Unit.PLUS_Z, vertexFn));
        Assertions.assertEquals(faceVertices, f.getVerticesCounterClockwise(Vector3D.of(1, 0, 0.1), vertexFn));
        Assertions.assertEquals(faceVertices, f.getVerticesCounterClockwise(Vector3D.Unit.PLUS_X, vertexFn));

        Assertions.assertEquals(reverseFaceVertices, f.getVerticesCounterClockwise(Vector3D.Unit.MINUS_Z, vertexFn));
        Assertions.assertEquals(reverseFaceVertices, f.getVerticesCounterClockwise(Vector3D.of(1, 0, -0.1), vertexFn));
    }

    private static PolygonObjParser parser(final String content) {
        return new PolygonObjParser(new StringReader(content));
    }

    private static String lines(final String... lines) {
        final String[] newlineOptions = {"\n", "\r", "\r\n"};

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; ++i) {
            sb.append(lines[i])
                .append(newlineOptions[i % newlineOptions.length]);
        }

        return sb.toString();
    }

    private static void nextFace(final PolygonObjParser parser) {
        nextMatchingKeyword(ObjConstants.FACE_KEYWORD, parser);
    }

    private static void nextMatchingKeyword(final String keyword, final PolygonObjParser parser) {
        while (parser.nextKeyword()) {
            if (keyword.equals(parser.getCurrentKeyword())) {
                return;
            }
        }
    }

    private static void assertNextKeyword(final String expected, final PolygonObjParser parser) {
        Assertions.assertEquals(expected != null, parser.nextKeyword());
        Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    private static void assertFace(final int[][] vertexAttributes, final PolygonObjParser.Face face) {
        Assertions.assertEquals(vertexAttributes.length, face.getVertexAttributes().size());

        final int[] expectedVertexIndices = new int[vertexAttributes.length];
        final int[] expectedTextureIndices = new int[vertexAttributes.length];
        final int[] expectedNormalIndices = new int[vertexAttributes.length];

        // check the indices directly on the vertex attributes
        PolygonObjParser.VertexAttributes attrs;
        String msg;
        for (int i = 0; i < vertexAttributes.length; ++i) {
            attrs = face.getVertexAttributes().get(i);

            msg = "Unexpected face vertex attributes at index " + i;
            Assertions.assertArrayEquals(vertexAttributes[i], new int[] {
                    attrs.getVertexIndex(),
                    attrs.getTextureIndex(),
                    attrs.getNormalIndex()
            }, msg);

            expectedVertexIndices[i] = attrs.getVertexIndex();
            expectedTextureIndices[i] = attrs.getTextureIndex();
            expectedNormalIndices[i] = attrs.getNormalIndex();
        }

        // check the individual index arrays from the face
        Assertions.assertArrayEquals(expectedVertexIndices, face.getVertexIndices());
        Assertions.assertArrayEquals(expectedTextureIndices, face.getTextureIndices());
        Assertions.assertArrayEquals(expectedNormalIndices, face.getNormalIndices());
    }

    @Test
    void testNextKeyword_1_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
                final String expected = "o";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_1_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
                final String expected = "o";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_2_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
                final String expected = "v";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_2_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
                final String expected = "v";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_3_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
                final String expected = "v";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_3_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
                final String expected = "v";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_4_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "v";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_4_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "v";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_5_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "g";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_5_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "g";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_6_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "f";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_6_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "f";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_7_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "curv2";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_7_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "curv2";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_8_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final String expected = null;
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_8_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "#comment",
                "",
                "  \t",
                "o test",
                "v",
                "v 1 0 0 1",
                "v 0 1 0",
                "# comment",
                " ",
                "g triangle-\\",
                "group",
                "f 1 2 3",
                "",
                "curv2",
                "# end"
        ));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final String expected = null;
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_1_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
                final String expected = "v";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_1_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
                final String expected = "v";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_2_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
                final String expected = "vn";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_2_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
                final String expected = "vn";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_3_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
                final String expected = "vt";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_3_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
                final String expected = "vt";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_4_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "f";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_4_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "f";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_5_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "o";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_5_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "o";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_6_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "s";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_6_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "s";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_7_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "g";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_7_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "g";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_8_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "mtllib";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_8_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "mtllib";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_9_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "usemtl";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_9_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String expected = "usemtl";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_10_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final String expected = null;
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_polygonKeywordsOnly_valid_10_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "v",
                "vn",
                "vt",
                "f",
                "o",
                "s",
                "g",
                "mtllib",
                "usemtl"
        ));
        p.setFailOnNonPolygonKeywords(true);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final String expected = null;
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testNextKeyword_emptyContent_1_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser("");

        // act/assert
                final String expected = null;
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testNextKeyword_emptyContent_1_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser("");

        // act/assert
                final String expected = null;
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_1_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
                final String expected = "o";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_1_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
                final String expected = "o";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_3_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

                final String expected = "g";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_3_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

                final String expected = "g";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_5_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "s";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_5_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "s";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_7_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "mtllib";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_7_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "mtllib";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_9_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "usemtl";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_9_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "usemtl";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_11_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "v";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_11_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "v";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_13_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "v";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_13_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "v";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_15_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "v";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_15_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "v";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_17_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "v";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_17_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "v";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_19_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "vt";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_19_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "vt";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_21_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "vt";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_21_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "vt";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_23_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "vt";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_23_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "vt";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_25_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "vn";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_25_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "vn";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_27_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "f";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_27_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "f";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

    @Test
    void testParse_29_oe_1_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "f";
        final PolygonObjParser parser = p;
        Assertions.assertEquals(expected != null, parser.nextKeyword());
    }

    @Test
    void testParse_29_oe_2_oe() {
        // arrange
        final PolygonObjParser p = parser(lines(
                "# test content",
                "o test",
                "g test",
                "s test",
                "mtllib mylib.mtl",
                "usemtl mymaterial",
                "",
                "\\", // line continuation
                " \\", // line continuation
                "",
                "v 0 0 0",
                "v 1\\", ".0 0 0", // line continuation
                "v 1 1 0",
                "v 0 1 0",
                "",
                "vt 0 0",
                "vt 1 0",
                "vt 1 1",
                "",
                "vn 0 0 1",
                "",
                "f 1 2 4",
                "f 1/1/1 2/2/1 3\\", "/3/1" // line continuation
        ));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final String expected = "f";
        final PolygonObjParser parser = p;
        // removed other assertion
                Assertions.assertEquals(expected, parser.getCurrentKeyword());
    }

}
