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
package org.apache.commons.geometry.io.euclidean.threed.stl;

import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.threed.ConvexPolygon3D;
import org.apache.commons.geometry.euclidean.threed.Planes;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.io.core.test.CloseCountWriter;
import org.apache.commons.geometry.io.euclidean.threed.FacetDefinition;
import org.apache.commons.geometry.io.euclidean.threed.SimpleFacetDefinition;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class TextStlWriterTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private final StringWriter out = new StringWriter();

    @Test
    void testDefaultProperties() {
        // act/assert
        try (TextStlWriter writer = new TextStlWriter(out)) {
            Assertions.assertNotNull(writer.getDoubleFormat());
            Assertions.assertEquals("\n", writer.getLineSeparator());
        }
    }

    @Test
    void testNoContent() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            Assertions.assertEquals(0, countWriter.getCloseCount());
        }

        // assert
        Assertions.assertEquals(1, countWriter.getCloseCount());
        Assertions.assertEquals("", out.toString());
    }

    @Test
    void testStartSolid_alreadyStarted() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            // act/assert
            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.startSolid(),
                    IllegalStateException.class, "Cannot start solid definition: a solid is already being written");
        }
    }

    @Test
    void testEndSolid_notStarted() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            // act/assert
            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.endSolid(),
                    IllegalStateException.class, "Cannot end solid definition: no solid has been started");
        }
    }

    @Test
    void testEmpty_noName() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid();
            writer.endSolid();

            Assertions.assertEquals(0, countWriter.getCloseCount());
        }

        // assert
        Assertions.assertEquals(1, countWriter.getCloseCount());
        Assertions.assertEquals("solid \n" + "endsolid \n",out.toString());
    }

    @Test
    void testEmpty_withName() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid("Name of the solid");
            writer.endSolid();

            Assertions.assertEquals(0, countWriter.getCloseCount());
        }

        // assert
        Assertions.assertEquals(1, countWriter.getCloseCount());
        Assertions.assertEquals("solid Name of the solid\n" + "endsolid Name of the solid\n",out.toString());
    }

    @Test
    void testClose_endsSolid() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid("name");

            Assertions.assertEquals(0, countWriter.getCloseCount());
        }

        // assert
        Assertions.assertEquals(1, countWriter.getCloseCount());
        Assertions.assertEquals("solid name\n" + "endsolid name\n",out.toString());
    }

    @Test
    void testStartSolid_containsNewLine() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            final String err = "Solid name cannot contain new line characters";

            // act/assert
            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.startSolid("Hi\nthere"),
                    IllegalArgumentException.class, err);
            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.startSolid("Hi\r\nthere"),
                    IllegalArgumentException.class, err);
            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.startSolid("Hi\rthere"),
                    IllegalArgumentException.class, err);
        }
    }

    @Test
    void testWriteTriangle_noNormal_computesNormal() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 4, 0);
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 0.5, 10);

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, null);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet -0.9961250701090868 -0.08301042250909056 -0.029053647878181696\n" + "outer loop\n" + "vertex 0.0 4.0 0.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "vertex 0.0 0.5 10.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWriteTriangle_zeroNormal_computesNormal() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 4, 0);
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 0.5, 10);

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, Vector3D.ZERO);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet -0.9961250701090868 -0.08301042250909056 -0.029053647878181696\n" + "outer loop\n" + "vertex 0.0 4.0 0.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "vertex 0.0 0.5 10.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWriteTriangle_noNormal_cannotComputeNormal() {
        // arrange
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.ZERO;

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, null);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 0.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWriteTriangle_withNormal_correctOrientation() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 4, 0);
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 0.5, 10);

        final Vector3D normal = p1.vectorTo(p2).cross(p1.vectorTo(p3)).normalize();

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, normal);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet -0.9961250701090868 -0.08301042250909056 -0.029053647878181696\n" + "outer loop\n" + "vertex 0.0 4.0 0.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "vertex 0.0 0.5 10.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWriteTriangle_withNormal_reversedOrientation() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 4, 0);
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 0.5, 10);

        final Vector3D normal = p1.vectorTo(p2).cross(p1.vectorTo(p3)).normalize();

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, normal.negate());
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.9961250701090868 0.08301042250909056 0.029053647878181696\n" + "outer loop\n" + "vertex 0.0 4.0 0.0\n" + "vertex 0.0 0.5 10.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_verticesAndNormal() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0));
        final Vector3D n1 = Vector3D.of(0, 0, 100);
        final Vector3D n2 = Vector3D.Unit.MINUS_Z;

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(vertices, n1);
            writer.writeTriangles(vertices, n2);
            writer.writeTriangles(vertices, null);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 -1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_verticesAndNormal_moreThanThreeVertices() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 0), Vector3D.of(0, 1, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(vertices, normal);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_verticesAndNormal_fewerThanThreeVertices() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            final List<Vector3D> noElements = Collections.emptyList();
            final List<Vector3D> singleElement = Collections.singletonList(Vector3D.ZERO);
            final List<Vector3D> twoElements = Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 1, 1));

            // act/assert
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> writer.writeTriangles(noElements, null));
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> writer.writeTriangles(singleElement, null));
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> writer.writeTriangles(twoElements, null));
        }
    }

    @Test
    void testWrite_boundary() {
        // arrange
        final ConvexPolygon3D boundary = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 0, 1), Vector3D.of(0, 0, 1)),
                TEST_PRECISION);

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(boundary);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 -1.0 0.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 1.0 0.0 1.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 -1.0 0.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 1.0\n" + "vertex 0.0 0.0 1.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_facetDefinition_noNormal() {
        // arrange
        final FacetDefinition facet = new SimpleFacetDefinition(Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 0), Vector3D.of(0, 1, 0)));

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(facet);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_facetDefinition_withNormal() {
        // arrange
        final Vector3D normal = Vector3D.Unit.PLUS_Z;
        final FacetDefinition facet = new SimpleFacetDefinition(Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 0), Vector3D.of(0, 1, 0)),
                normal);

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(facet);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_noSolidStarted() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        final String msg = "Cannot write triangle: no solid has been started";

        try (TextStlWriter writer = new TextStlWriter(out)) {

            // act/assert
            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.writeTriangle(vertices.get(0), vertices.get(1), vertices.get(2), normal),
                    IllegalStateException.class, msg);

            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.writeTriangles(vertices, normal),
                    IllegalStateException.class, msg);

            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.writeTriangles(new SimpleFacetDefinition(vertices, normal)),
                    IllegalStateException.class, msg);

            GeometryTestUtils.assertThrowsWithMessage(
                    () -> writer.writeTriangles(Planes.convexPolygonFromVertices(vertices, TEST_PRECISION)),
                    IllegalStateException.class, msg);
        }
    }

    @Test
    void testWrite_customFormat() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1.0 / 3.0, 0, 0), Vector3D.of(0, 1.0 / 3.0, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        final DecimalFormat fmt =
                new DecimalFormat("0.0##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

        try (TextStlWriter writer = new TextStlWriter(out)) {

            writer.setDoubleFormat(fmt::format);
            writer.setLineSeparator("\r\n");

            // act
            writer.startSolid();
            writer.writeTriangles(vertices, normal);
        }

        // assert
        Assertions.assertEquals("solid \r\n" + "facet 0.0 0.0 1.0\r\n" + "outer loop\r\n" + "vertex 0.0 0.0 0.0\r\n" + "vertex 0.333 0.0 0.0\r\n" + "vertex 0.0 0.333 0.0\r\n" + "endloop\r\n" + "endfacet\r\n" + "endsolid \r\n",out.toString());
    }

    @Test
    void testWrite_badFacet_withNormal() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.ZERO, Vector3D.ZERO);
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        try (TextStlWriter writer = new TextStlWriter(out)) {
            // act
            writer.startSolid();
            writer.writeTriangles(vertices, normal);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_badFacet_noNormal() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.ZERO, Vector3D.ZERO);

        try (TextStlWriter writer = new TextStlWriter(out)) {
            // act
            writer.startSolid();
            writer.writeTriangles(vertices, null);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 0.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testDefaultProperties_1_oe() {
        // act/assert
        try (TextStlWriter writer = new TextStlWriter(out)) {
            Assertions.assertNotNull(writer.getDoubleFormat());
    }
    }

    @Test
    void testDefaultProperties_2_oe() {
        // act/assert
        try (TextStlWriter writer = new TextStlWriter(out)) {
            // removed other assertion
            Assertions.assertEquals("\n", writer.getLineSeparator());
    }
    }

    @Test
    void testNoContent_1_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            Assertions.assertEquals(0, countWriter.getCloseCount());
    }
    }

    @Test
    void testNoContent_2_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            // removed other assertion
        }

        // assert
        Assertions.assertEquals(1, countWriter.getCloseCount());
    }

    @Test
    void testNoContent_3_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            // removed other assertion
        }

        // assert
        // removed other assertion
        Assertions.assertEquals("", out.toString());
    }

    @Test
    void testEmpty_noName_1_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid();
            writer.endSolid();

            Assertions.assertEquals(0, countWriter.getCloseCount());
    }
    }

    @Test
    void testEmpty_noName_2_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid();
            writer.endSolid();

            // removed other assertion
        }

        // assert
        Assertions.assertEquals(1, countWriter.getCloseCount());
    }

    @Test
    void testEmpty_noName_3_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid();
            writer.endSolid();

            // removed other assertion
        }

        // assert
        // removed other assertion
        Assertions.assertEquals("solid \n" + "endsolid \n",out.toString());
    }

    @Test
    void testEmpty_withName_1_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid("Name of the solid");
            writer.endSolid();

            Assertions.assertEquals(0, countWriter.getCloseCount());
    }
    }

    @Test
    void testEmpty_withName_2_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid("Name of the solid");
            writer.endSolid();

            // removed other assertion
        }

        // assert
        Assertions.assertEquals(1, countWriter.getCloseCount());
    }

    @Test
    void testEmpty_withName_3_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid("Name of the solid");
            writer.endSolid();

            // removed other assertion
        }

        // assert
        // removed other assertion
        Assertions.assertEquals("solid Name of the solid\n" + "endsolid Name of the solid\n",out.toString());
    }

    @Test
    void testClose_endsSolid_1_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid("name");

            Assertions.assertEquals(0, countWriter.getCloseCount());
    }
    }

    @Test
    void testClose_endsSolid_2_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid("name");

            // removed other assertion
        }

        // assert
        Assertions.assertEquals(1, countWriter.getCloseCount());
    }

    @Test
    void testClose_endsSolid_3_oe() {
        // arrange
        final CloseCountWriter countWriter = new CloseCountWriter(out);

        // act
        try (TextStlWriter writer = new TextStlWriter(countWriter)) {
            writer.startSolid("name");

            // removed other assertion
        }

        // assert
        // removed other assertion
        Assertions.assertEquals("solid name\n" + "endsolid name\n",out.toString());
    }

    @Test
    void testWriteTriangle_noNormal_computesNormal_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 4, 0);
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 0.5, 10);

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, null);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet -0.9961250701090868 -0.08301042250909056 -0.029053647878181696\n" + "outer loop\n" + "vertex 0.0 4.0 0.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "vertex 0.0 0.5 10.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWriteTriangle_zeroNormal_computesNormal_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 4, 0);
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 0.5, 10);

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, Vector3D.ZERO);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet -0.9961250701090868 -0.08301042250909056 -0.029053647878181696\n" + "outer loop\n" + "vertex 0.0 4.0 0.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "vertex 0.0 0.5 10.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWriteTriangle_noNormal_cannotComputeNormal_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.ZERO;

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, null);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 0.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWriteTriangle_withNormal_correctOrientation_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 4, 0);
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 0.5, 10);

        final Vector3D normal = p1.vectorTo(p2).cross(p1.vectorTo(p3)).normalize();

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, normal);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet -0.9961250701090868 -0.08301042250909056 -0.029053647878181696\n" + "outer loop\n" + "vertex 0.0 4.0 0.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "vertex 0.0 0.5 10.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWriteTriangle_withNormal_reversedOrientation_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 4, 0);
        final Vector3D p2 = Vector3D.of(1.0 / 3.0, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 0.5, 10);

        final Vector3D normal = p1.vectorTo(p2).cross(p1.vectorTo(p3)).normalize();

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();
            writer.writeTriangle(p1, p2, p3, normal.negate());
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.9961250701090868 0.08301042250909056 0.029053647878181696\n" + "outer loop\n" + "vertex 0.0 4.0 0.0\n" + "vertex 0.0 0.5 10.0\n" + "vertex 0.3333333333333333 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_verticesAndNormal_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0));
        final Vector3D n1 = Vector3D.of(0, 0, 100);
        final Vector3D n2 = Vector3D.Unit.MINUS_Z;

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(vertices, n1);
            writer.writeTriangles(vertices, n2);
            writer.writeTriangles(vertices, null);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 -1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_verticesAndNormal_moreThanThreeVertices_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 0), Vector3D.of(0, 1, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(vertices, normal);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_verticesAndNormal_fewerThanThreeVertices_1_oe() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            final List<Vector3D> noElements = Collections.emptyList();
            final List<Vector3D> singleElement = Collections.singletonList(Vector3D.ZERO);
            final List<Vector3D> twoElements = Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 1, 1));

            // act/assert
            try {
    writer.writeTriangles(noElements, null);
    fail("Expected IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }

    @Test
    void testWrite_verticesAndNormal_fewerThanThreeVertices_2_oe() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            final List<Vector3D> noElements = Collections.emptyList();
            final List<Vector3D> singleElement = Collections.singletonList(Vector3D.ZERO);
            final List<Vector3D> twoElements = Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 1, 1));

            // act/assert
            // removed other assertion
            try {
    writer.writeTriangles(singleElement, null);
    fail("Expected IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }

    @Test
    void testWrite_verticesAndNormal_fewerThanThreeVertices_3_oe() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            final List<Vector3D> noElements = Collections.emptyList();
            final List<Vector3D> singleElement = Collections.singletonList(Vector3D.ZERO);
            final List<Vector3D> twoElements = Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 1, 1));

            // act/assert
            // removed other assertion
            // removed other assertion
            try {
    writer.writeTriangles(twoElements, null);
    fail("Expected IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }

    @Test
    void testWrite_boundary_1_oe() {
        // arrange
        final ConvexPolygon3D boundary = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 0, 1), Vector3D.of(0, 0, 1)),
                TEST_PRECISION);

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(boundary);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 -1.0 0.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 1.0 0.0 1.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 -1.0 0.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 1.0\n" + "vertex 0.0 0.0 1.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_facetDefinition_noNormal_1_oe() {
        // arrange
        final FacetDefinition facet = new SimpleFacetDefinition(Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 0), Vector3D.of(0, 1, 0)));

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(facet);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_facetDefinition_withNormal_1_oe() {
        // arrange
        final Vector3D normal = Vector3D.Unit.PLUS_Z;
        final FacetDefinition facet = new SimpleFacetDefinition(Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 0), Vector3D.of(0, 1, 0)),
                normal);

        // act
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            writer.writeTriangles(facet);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 1.0 1.0 0.0\n" + "vertex 0.0 1.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_customFormat_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1.0 / 3.0, 0, 0), Vector3D.of(0, 1.0 / 3.0, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        final DecimalFormat fmt =
                new DecimalFormat("0.0##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

        try (TextStlWriter writer = new TextStlWriter(out)) {

            writer.setDoubleFormat(fmt::format);
            writer.setLineSeparator("\r\n");

            // act
            writer.startSolid();
            writer.writeTriangles(vertices, normal);
        }

        // assert
        Assertions.assertEquals("solid \r\n" + "facet 0.0 0.0 1.0\r\n" + "outer loop\r\n" + "vertex 0.0 0.0 0.0\r\n" + "vertex 0.333 0.0 0.0\r\n" + "vertex 0.0 0.333 0.0\r\n" + "endloop\r\n" + "endfacet\r\n" + "endsolid \r\n",out.toString());
    }

    @Test
    void testWrite_badFacet_withNormal_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.ZERO, Vector3D.ZERO);
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        try (TextStlWriter writer = new TextStlWriter(out)) {
            // act
            writer.startSolid();
            writer.writeTriangles(vertices, normal);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 1.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

    @Test
    void testWrite_badFacet_noNormal_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.ZERO, Vector3D.ZERO);

        try (TextStlWriter writer = new TextStlWriter(out)) {
            // act
            writer.startSolid();
            writer.writeTriangles(vertices, null);
        }

        // assert
        Assertions.assertEquals("solid \n" + "facet 0.0 0.0 0.0\n" + "outer loop\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "vertex 0.0 0.0 0.0\n" + "endloop\n" + "endfacet\n" + "endsolid \n",out.toString());
    }

@Test
    void testStartSolid_alreadyStarted_1_oe() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            writer.startSolid();

            // act/assert
            try {
    writer.startSolid();
    fail("Expected IllegalStateException with message: " + "Cannot start solid definition: a solid is already being written");
} catch (IllegalStateException e) {
}
    }
    }

@Test
    void testEndSolid_notStarted_1_oe() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            // act/assert
            try {
    writer.endSolid();
    fail("Expected IllegalStateException with message: " + "Cannot end solid definition: no solid has been started");
} catch (IllegalStateException e) {
}
    }
    }

@Test
    void testStartSolid_containsNewLine_1_oe() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            final String err = "Solid name cannot contain new line characters";

            // act/assert
            try {
    writer.startSolid("Hi\nthere");
    fail("Expected IllegalArgumentException with message: " + err);
} catch (IllegalArgumentException e) {
}
    }
    }

@Test
    void testStartSolid_containsNewLine_2_oe() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            final String err = "Solid name cannot contain new line characters";

            // act/assert
            // removed other assertion
            try {
    writer.startSolid("Hi\r\nthere");
    fail("Expected IllegalArgumentException with message: " + err);
} catch (IllegalArgumentException e) {
}
    }
    }

@Test
    void testStartSolid_containsNewLine_3_oe() {
        // arrange
        try (TextStlWriter writer = new TextStlWriter(out)) {
            final String err = "Solid name cannot contain new line characters";

            // act/assert
            // removed other assertion
            // removed other assertion
            try {
    writer.startSolid("Hi\rthere");
    fail("Expected IllegalArgumentException with message: " + err);
} catch (IllegalArgumentException e) {
}
    }
    }

@Test
    void testWrite_noSolidStarted_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        final String msg = "Cannot write triangle: no solid has been started";

        try (TextStlWriter writer = new TextStlWriter(out)) {

            // act/assert
            try {
    writer.writeTriangle(vertices.get(0), vertices.get(1), vertices.get(2), normal);
    fail("Expected IllegalStateException with message: " + msg);
} catch (IllegalStateException e) {
}
    }
    }

@Test
    void testWrite_noSolidStarted_2_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        final String msg = "Cannot write triangle: no solid has been started";

        try (TextStlWriter writer = new TextStlWriter(out)) {

            // act/assert
            // removed other assertion

            try {
    writer.writeTriangles(vertices, normal);
    fail("Expected IllegalStateException with message: " + msg);
} catch (IllegalStateException e) {
}
    }
    }

@Test
    void testWrite_noSolidStarted_3_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        final String msg = "Cannot write triangle: no solid has been started";

        try (TextStlWriter writer = new TextStlWriter(out)) {

            // act/assert
            // removed other assertion

            // removed other assertion

            try {
    writer.writeTriangles(new SimpleFacetDefinition(vertices, normal));
    fail("Expected IllegalStateException with message: " + msg);
} catch (IllegalStateException e) {
}
    }
    }

@Test
    void testWrite_noSolidStarted_4_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0));
        final Vector3D normal = Vector3D.Unit.PLUS_Z;

        final String msg = "Cannot write triangle: no solid has been started";

        try (TextStlWriter writer = new TextStlWriter(out)) {

            // act/assert
            // removed other assertion

            // removed other assertion

            // removed other assertion

            try {
    writer.writeTriangles(Planes.convexPolygonFromVertices(vertices, TEST_PRECISION));
    fail("Expected IllegalStateException with message: " + msg);
} catch (IllegalStateException e) {
}
    }
    }

}
