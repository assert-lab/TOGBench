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


}
