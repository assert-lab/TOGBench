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
package org.apache.commons.geometry.euclidean.threed.mesh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.AffineTransformMatrix3D;
import org.apache.commons.geometry.euclidean.threed.BoundarySource3D;
import org.apache.commons.geometry.euclidean.threed.Bounds3D;
import org.apache.commons.geometry.euclidean.threed.Planes;
import org.apache.commons.geometry.euclidean.threed.RegionBSPTree3D;
import org.apache.commons.geometry.euclidean.threed.Triangle3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.shape.Parallelepiped;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleTriangleMeshTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testToString() {
        // arrange
        final Triangle3D tri = Planes.triangleFromVertices(Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0),
                TEST_PRECISION);
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(BoundarySource3D.of(tri), TEST_PRECISION);

        // act
        final String str = mesh.toString();

        // assert
        GeometryTestUtils.assertContains("SimpleTriangleMesh[vertexCount= 3, faceCount= 1, bounds= Bounds3D[", str);
    }

    @Test
    void testFaceToString() {
        // arrange
        final Triangle3D tri = Planes.triangleFromVertices(Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0),
                TEST_PRECISION);
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(BoundarySource3D.of(tri), TEST_PRECISION);

        // act
        final String str = mesh.getFace(0).toString();

        // assert
        GeometryTestUtils.assertContains("SimpleTriangleFace[index= 0, vertexIndices= [0, 1, 2], vertices= [(0", str);
    }

    @Test
    void testFrom_verticesAndFaces_1_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        Assertions.assertEquals(4, mesh.getVertexCount());
    }

    @Test
    void testFrom_verticesAndFaces_2_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertEquals(Arrays.asList(vertices), mesh.getVertices());
    }

    @Test
    void testFrom_verticesAndFaces_3_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2, mesh.getFaceCount());
    }

    @Test
    void testFrom_verticesAndFaces_4_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        Assertions.assertEquals(2, faces.size());
    }

    @Test
    void testFrom_verticesAndFaces_5_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        Assertions.assertEquals(0, f1.getIndex());
    }

    @Test
    void testFrom_verticesAndFaces_6_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        Assertions.assertArrayEquals(new int[] {0, 1, 2}, f1.getVertexIndices());
    }

    @Test
    void testFrom_verticesAndFaces_7_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices[0], f1.getPoint1());
    }

    @Test
    void testFrom_verticesAndFaces_8_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices[1], f1.getPoint2());
    }

    @Test
    void testFrom_verticesAndFaces_9_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices[2], f1.getPoint3());
    }

    @Test
    void testFrom_verticesAndFaces_10_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Arrays.asList(vertices[0], vertices[1], vertices[2]), f1.getVertices());
    }

    @Test
    void testFrom_verticesAndFaces_11_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(f1.definesPolygon());
    }

    @Test
    void testFrom_verticesAndFaces_12_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        Assertions.assertEquals(Arrays.asList(vertices[0], vertices[1], vertices[2]), t1.getVertices());
    }

    @Test
    void testFrom_verticesAndFaces_13_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        Assertions.assertEquals(1, f2.getIndex());
    }

    @Test
    void testFrom_verticesAndFaces_14_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        // removed other assertion
        Assertions.assertArrayEquals(new int[] {0, 2, 3}, f2.getVertexIndices());
    }

    @Test
    void testFrom_verticesAndFaces_15_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices[0], f2.getPoint1());
    }

    @Test
    void testFrom_verticesAndFaces_16_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices[2], f2.getPoint2());
    }

    @Test
    void testFrom_verticesAndFaces_17_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices[3], f2.getPoint3());
    }

    @Test
    void testFrom_verticesAndFaces_18_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Arrays.asList(vertices[0], vertices[2], vertices[3]), f2.getVertices());
    }

    @Test
    void testFrom_verticesAndFaces_19_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(f2.definesPolygon());
    }

    @Test
    void testFrom_verticesAndFaces_20_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t2 = f2.getPolygon();
        Assertions.assertEquals(Arrays.asList(vertices[0], vertices[2], vertices[3]), t2.getVertices());
    }

    @Test
    void testFrom_verticesAndFaces_23_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 1, 0),
            Vector3D.of(1, 1, 1),
            Vector3D.of(0, 0, 1)
        };

        final int[][] faceIndices = {{0, 1, 2}, {0, 2, 3}};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        final TriangleMesh.Face f1 = faces.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t1 = f1.getPolygon();
        // removed other assertion

        final TriangleMesh.Face f2 = faces.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t2 = f2.getPolygon();
        // removed other assertion

        final Bounds3D bounds = mesh.getBounds();
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(TEST_PRECISION, mesh.getPrecision());
    }

    @Test
    void testFrom_verticesAndFaces_empty_1_oe() {
        // arrange
        final Vector3D[] vertices = {};

        final int[][] faceIndices = {};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        Assertions.assertEquals(0, mesh.getVertexCount());
    }

    @Test
    void testFrom_verticesAndFaces_empty_2_oe() {
        // arrange
        final Vector3D[] vertices = {};

        final int[][] faceIndices = {};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertEquals(0, mesh.getVertices().size());
    }

    @Test
    void testFrom_verticesAndFaces_empty_3_oe() {
        // arrange
        final Vector3D[] vertices = {};

        final int[][] faceIndices = {};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, mesh.getFaceCount());
    }

    @Test
    void testFrom_verticesAndFaces_empty_4_oe() {
        // arrange
        final Vector3D[] vertices = {};

        final int[][] faceIndices = {};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, mesh.getFaces().size());
    }

    @Test
    void testFrom_verticesAndFaces_empty_5_oe() {
        // arrange
        final Vector3D[] vertices = {};

        final int[][] faceIndices = {};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(mesh.getBounds());
    }

    @Test
    void testFrom_verticesAndFaces_empty_6_oe() {
        // arrange
        final Vector3D[] vertices = {};

        final int[][] faceIndices = {};

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertTrue(mesh.toTree().isEmpty());
    }

    @Test
    void testFrom_boundarySource_1_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        Assertions.assertEquals(8, mesh.getVertexCount());
    }

    @Test
    void testFrom_boundarySource_2_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        Assertions.assertEquals(8, vertices.size());
    }

    @Test
    void testFrom_boundarySource_3_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        Assertions.assertTrue(vertices.contains(p1));
    }

    @Test
    void testFrom_boundarySource_4_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(vertices.contains(p2));
    }

    @Test
    void testFrom_boundarySource_5_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(vertices.contains(p3));
    }

    @Test
    void testFrom_boundarySource_6_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(vertices.contains(p4));
    }

    @Test
    void testFrom_boundarySource_7_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(vertices.contains(p5));
    }

    @Test
    void testFrom_boundarySource_8_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(vertices.contains(p6));
    }

    @Test
    void testFrom_boundarySource_9_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(vertices.contains(p7));
    }

    @Test
    void testFrom_boundarySource_10_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(vertices.contains(p8));
    }

    @Test
    void testFrom_boundarySource_11_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(12, mesh.getFaceCount());
    }

    @Test
    void testFrom_boundarySource_12_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
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

        final RegionBSPTree3D tree = mesh.toTree();

        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
    }

    @Test
    void testFrom_boundarySource_14_oe() {
        // arrange
        final BoundarySource3D src = Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(src, TEST_PRECISION);

        // assert
        // removed other assertion

        final Vector3D p1 = Vector3D.of(0, 0, 0);
        final Vector3D p2 = Vector3D.of(0, 0, 1);
        final Vector3D p3 = Vector3D.of(0, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 1);

        final Vector3D p5 = Vector3D.of(1, 0, 0);
        final Vector3D p6 = Vector3D.of(1, 0, 1);
        final Vector3D p7 = Vector3D.of(1, 1, 0);
        final Vector3D p8 = Vector3D.of(1, 1, 1);

        final List<Vector3D> vertices = mesh.getVertices();
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

        final RegionBSPTree3D tree = mesh.toTree();

        // removed other assertion
        // removed other assertion

        Assertions.assertSame(TEST_PRECISION, mesh.getPrecision());
    }

    @Test
    void testFrom_boundarySource_empty_1_oe() {
        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(BoundarySource3D.of(Collections.emptyList()),
                TEST_PRECISION);

        // assert
        Assertions.assertEquals(0, mesh.getVertexCount());
    }

    @Test
    void testFrom_boundarySource_empty_2_oe() {
        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(BoundarySource3D.of(Collections.emptyList()),
                TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertEquals(0, mesh.getVertices().size());
    }

    @Test
    void testFrom_boundarySource_empty_3_oe() {
        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(BoundarySource3D.of(Collections.emptyList()),
                TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, mesh.getFaceCount());
    }

    @Test
    void testFrom_boundarySource_empty_4_oe() {
        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(BoundarySource3D.of(Collections.emptyList()),
                TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, mesh.getFaces().size());
    }

    @Test
    void testFrom_boundarySource_empty_5_oe() {
        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(BoundarySource3D.of(Collections.emptyList()),
                TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(mesh.getBounds());
    }

    @Test
    void testFrom_boundarySource_empty_6_oe() {
        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(BoundarySource3D.of(Collections.emptyList()),
                TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertTrue(mesh.toTree().isEmpty());
    }

    @Test
    void testVertices_iterable_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0)
        );

        final List<int[]> faceIndices = Collections.singletonList(new int[]{0, 1, 2});

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<Vector3D> result = new ArrayList<>();
        mesh.vertices().forEach(result::add);

        // assert
        Assertions.assertEquals(vertices, result);
    }

    @Test
    void testFaces_iterable_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testFaces_iterable_2_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        Assertions.assertEquals(0, f1.getIndex());
    }

    @Test
    void testFaces_iterable_3_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        Assertions.assertArrayEquals(new int[] {0, 1, 2}, f1.getVertexIndices());
    }

    @Test
    void testFaces_iterable_4_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices.get(0), f1.getPoint1());
    }

    @Test
    void testFaces_iterable_5_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices.get(1), f1.getPoint2());
    }

    @Test
    void testFaces_iterable_6_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices.get(2), f1.getPoint3());
    }

    @Test
    void testFaces_iterable_7_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Arrays.asList(vertices.get(0), vertices.get(1), vertices.get(2)), f1.getVertices());
    }

    @Test
    void testFaces_iterable_8_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(f1.definesPolygon());
    }

    @Test
    void testFaces_iterable_9_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f2 = result.get(1);
        Assertions.assertEquals(1, f2.getIndex());
    }

    @Test
    void testFaces_iterable_10_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f2 = result.get(1);
        // removed other assertion
        Assertions.assertArrayEquals(new int[] {0, 2, 3}, f2.getVertexIndices());
    }

    @Test
    void testFaces_iterable_11_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f2 = result.get(1);
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices.get(0), f2.getPoint1());
    }

    @Test
    void testFaces_iterable_12_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f2 = result.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices.get(2), f2.getPoint2());
    }

    @Test
    void testFaces_iterable_13_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f2 = result.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices.get(3), f2.getPoint3());
    }

    @Test
    void testFaces_iterable_14_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f2 = result.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Arrays.asList(vertices.get(0), vertices.get(2), vertices.get(3)), f2.getVertices());
    }

    @Test
    void testFaces_iterable_15_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<TriangleMesh.Face> result = new ArrayList<>();
        mesh.faces().forEach(result::add);

        // assert
        // removed other assertion

        final TriangleMesh.Face f1 = result.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f2 = result.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(f2.definesPolygon());
    }

    @Test
    void testFaces_iterator_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0)
        );

        final List<int[]> faceIndices = Collections.singletonList(new int[]{0, 1, 2}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act/assert
        final Iterator<TriangleMesh.Face> it = mesh.faces().iterator();

        Assertions.assertTrue(it.hasNext());
    }

    @Test
    void testFaces_iterator_2_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0)
        );

        final List<int[]> faceIndices = Collections.singletonList(new int[]{0, 1, 2}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act/assert
        final Iterator<TriangleMesh.Face> it = mesh.faces().iterator();

        // removed other assertion
        Assertions.assertEquals(0, it.next().getIndex());
    }

    @Test
    void testTriangleStream_1_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<Triangle3D> tris = mesh.triangleStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(2, tris.size());
    }

    @Test
    void testTriangleStream_2_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<Triangle3D> tris = mesh.triangleStream().collect(Collectors.toList());

        // assert
        // removed other assertion

        final Triangle3D t1 = tris.get(0);
        Assertions.assertSame(vertices.get(0), t1.getPoint1());
    }

    @Test
    void testTriangleStream_3_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<Triangle3D> tris = mesh.triangleStream().collect(Collectors.toList());

        // assert
        // removed other assertion

        final Triangle3D t1 = tris.get(0);
        // removed other assertion
        Assertions.assertSame(vertices.get(1), t1.getPoint2());
    }

    @Test
    void testTriangleStream_4_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<Triangle3D> tris = mesh.triangleStream().collect(Collectors.toList());

        // assert
        // removed other assertion

        final Triangle3D t1 = tris.get(0);
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices.get(2), t1.getPoint3());
    }

    @Test
    void testTriangleStream_5_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<Triangle3D> tris = mesh.triangleStream().collect(Collectors.toList());

        // assert
        // removed other assertion

        final Triangle3D t1 = tris.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t2 = tris.get(1);
        Assertions.assertSame(vertices.get(0), t2.getPoint1());
    }

    @Test
    void testTriangleStream_6_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<Triangle3D> tris = mesh.triangleStream().collect(Collectors.toList());

        // assert
        // removed other assertion

        final Triangle3D t1 = tris.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t2 = tris.get(1);
        // removed other assertion
        Assertions.assertSame(vertices.get(2), t2.getPoint2());
    }

    @Test
    void testTriangleStream_7_oe() {
        // arrange
        final List<Vector3D> vertices = Arrays.asList(
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0, 0, 1)
        );

        final List<int[]> faceIndices = Arrays.asList(
            new int[] {0, 1, 2},
            new int[] {0, 2, 3}
        );

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faceIndices, TEST_PRECISION);

        // act
        final List<Triangle3D> tris = mesh.triangleStream().collect(Collectors.toList());

        // assert
        // removed other assertion

        final Triangle3D t1 = tris.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Triangle3D t2 = tris.get(1);
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(vertices.get(3), t2.getPoint3());
    }

    @Test
    void testToTriangleMesh_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision1 = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Precision.DoubleEquivalence precision2 = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), precision1);

        // act/assert
        Assertions.assertSame(mesh, mesh.toTriangleMesh(precision1));
    }

    @Test
    void testToTriangleMesh_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision1 = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Precision.DoubleEquivalence precision2 = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), precision1);

        // act/assert
        // removed other assertion

        final SimpleTriangleMesh other = mesh.toTriangleMesh(precision2);
        Assertions.assertSame(precision2, other.getPrecision());
    }

    @Test
    void testToTriangleMesh_3_oe() {
        // arrange
        final Precision.DoubleEquivalence precision1 = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Precision.DoubleEquivalence precision2 = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), precision1);

        // act/assert
        // removed other assertion

        final SimpleTriangleMesh other = mesh.toTriangleMesh(precision2);
        // removed other assertion
        Assertions.assertEquals(mesh.getVertices(), other.getVertices());
    }

    @Test
    void testToTriangleMesh_4_oe() {
        // arrange
        final Precision.DoubleEquivalence precision1 = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Precision.DoubleEquivalence precision2 = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), precision1);

        // act/assert
        // removed other assertion

        final SimpleTriangleMesh other = mesh.toTriangleMesh(precision2);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(12, other.getFaceCount());
    }

    @Test
    void testToTriangleMesh_5_oe() {
        // arrange
        final Precision.DoubleEquivalence precision1 = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Precision.DoubleEquivalence precision2 = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), precision1);

        // act/assert
        // removed other assertion

        final SimpleTriangleMesh other = mesh.toTriangleMesh(precision2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (int i = 0; i < 12; ++i) {
            Assertions.assertArrayEquals(mesh.getFace(i).getVertexIndices(), other.getFace(i).getVertexIndices());
    }
    }

    @Test
    void testToTriangleMesh_6_oe() {
        // arrange
        final Precision.DoubleEquivalence precision1 = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Precision.DoubleEquivalence precision2 = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), precision1);

        // act/assert
        // removed other assertion

        final SimpleTriangleMesh other = mesh.toTriangleMesh(precision2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (int i = 0; i < 12; ++i) {
            // removed other assertion
        }

        Assertions.assertSame(mesh, mesh.toTriangleMesh(precision1));
    }

    @Test
    void testFace_doesNotDefineTriangle_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(0.01, -0.01, 0.01),
            Vector3D.of(0.01, 0.01, 0.01),
            Vector3D.of(1, 0, 0),
            Vector3D.of(2, 0.01, 0)
        };
        final int[][] faces = {{0, 1, 2}, {0, 3, 4}};
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faces, precision);

        // act/assert
        final Pattern msgPattern = Pattern.compile("^Points do not define a plane: .*");

        Assertions.assertFalse(mesh.getFace(0).definesPolygon());
    }

    @Test
    void testFace_doesNotDefineTriangle_3_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(0.01, -0.01, 0.01),
            Vector3D.of(0.01, 0.01, 0.01),
            Vector3D.of(1, 0, 0),
            Vector3D.of(2, 0.01, 0)
        };
        final int[][] faces = {{0, 1, 2}, {0, 3, 4}};
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faces, precision);

        // act/assert
        final Pattern msgPattern = Pattern.compile("^Points do not define a plane: .*");

        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(mesh.getFace(1).definesPolygon());
    }

    @Test
    void testToTree_smallNumberOfFaces_1_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        // act
        final RegionBSPTree3D tree = mesh.toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_smallNumberOfFaces_2_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        // act
        final RegionBSPTree3D tree = mesh.toTree();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_smallNumberOfFaces_3_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        // act
        final RegionBSPTree3D tree = mesh.toTree();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(tree.isInfinite());
    }

    @Test
    void testToTree_smallNumberOfFaces_4_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        // act
        final RegionBSPTree3D tree = mesh.toTree();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(tree.isFinite());
    }

    @Test
    void testToTree_smallNumberOfFaces_5_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        // act
        final RegionBSPTree3D tree = mesh.toTree();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, tree.getSize(), 1);
    }

    @Test
    void testToTree_smallNumberOfFaces_6_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        // act
        final RegionBSPTree3D tree = mesh.toTree();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(6, tree.getBoundarySize(), 1);
    }

    @Test
    void testToTree_smallNumberOfFaces_7_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        // act
        final RegionBSPTree3D tree = mesh.toTree();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(6, tree.getRoot().height());
    }

    @Test
    void testTransform_1_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        final AffineTransformMatrix3D t = AffineTransformMatrix3D.createScale(1, 2, 3)
                .translate(0.5, 1, 1.5);

        // act
        final SimpleTriangleMesh result = mesh.transform(t);

        // assert
        Assertions.assertNotSame(mesh, result);
    }

    @Test
    void testTransform_2_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        final AffineTransformMatrix3D t = AffineTransformMatrix3D.createScale(1, 2, 3)
                .translate(0.5, 1, 1.5);

        // act
        final SimpleTriangleMesh result = mesh.transform(t);

        // assert
        // removed other assertion

        Assertions.assertEquals(8, result.getVertexCount());
    }

    @Test
    void testTransform_3_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(Parallelepiped.unitCube(TEST_PRECISION), TEST_PRECISION);

        final AffineTransformMatrix3D t = AffineTransformMatrix3D.createScale(1, 2, 3)
                .translate(0.5, 1, 1.5);

        // act
        final SimpleTriangleMesh result = mesh.transform(t);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(12, result.getFaceCount());
    }

    @Test
    void testTransform_empty_1_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.builder(TEST_PRECISION).build();

        final AffineTransformMatrix3D t = AffineTransformMatrix3D.createScale(1, 2, 3);

        // act
        final SimpleTriangleMesh result = mesh.transform(t);

        // assert
        Assertions.assertEquals(0, result.getVertexCount());
    }

    @Test
    void testTransform_empty_2_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.builder(TEST_PRECISION).build();

        final AffineTransformMatrix3D t = AffineTransformMatrix3D.createScale(1, 2, 3);

        // act
        final SimpleTriangleMesh result = mesh.transform(t);

        // assert
        // removed other assertion
        Assertions.assertEquals(0, result.getFaceCount());
    }

    @Test
    void testTransform_empty_3_oe() {
        // arrange
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.builder(TEST_PRECISION).build();

        final AffineTransformMatrix3D t = AffineTransformMatrix3D.createScale(1, 2, 3);

        // act
        final SimpleTriangleMesh result = mesh.transform(t);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(result.getBounds());
    }

    @Test
    void testBuilder_mixedBuildMethods_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(precision);

        // act
        builder.addVertices(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)));
        builder.useVertex(Vector3D.of(0, 0, 1));
        builder.addVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(1, 1, 1));

        builder.addFace(0, 2, 1);
        builder.addFace(new int[] {1, 2, 3});
        builder.addFaceUsingVertices(Vector3D.of(0.5, 0, 0), Vector3D.of(1.01, 0, 0), Vector3D.of(1, 1, 0.95));

        final SimpleTriangleMesh mesh = builder.build();

        // assert
        Assertions.assertEquals(6, mesh.getVertexCount());
    }

    @Test
    void testBuilder_mixedBuildMethods_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(precision);

        // act
        builder.addVertices(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)));
        builder.useVertex(Vector3D.of(0, 0, 1));
        builder.addVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(1, 1, 1));

        builder.addFace(0, 2, 1);
        builder.addFace(new int[] {1, 2, 3});
        builder.addFaceUsingVertices(Vector3D.of(0.5, 0, 0), Vector3D.of(1.01, 0, 0), Vector3D.of(1, 1, 0.95));

        final SimpleTriangleMesh mesh = builder.build();

        // assert
        // removed other assertion
        Assertions.assertEquals(3, mesh.getFaceCount());
    }

    @Test
    void testBuilder_mixedBuildMethods_3_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(precision);

        // act
        builder.addVertices(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)));
        builder.useVertex(Vector3D.of(0, 0, 1));
        builder.addVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(1, 1, 1));

        builder.addFace(0, 2, 1);
        builder.addFace(new int[] {1, 2, 3});
        builder.addFaceUsingVertices(Vector3D.of(0.5, 0, 0), Vector3D.of(1.01, 0, 0), Vector3D.of(1, 1, 0.95));

        final SimpleTriangleMesh mesh = builder.build();

        // assert
        // removed other assertion
        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        Assertions.assertEquals(3, faces.size());
    }

    @Test
    void testBuilder_mixedBuildMethods_4_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(precision);

        // act
        builder.addVertices(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)));
        builder.useVertex(Vector3D.of(0, 0, 1));
        builder.addVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(1, 1, 1));

        builder.addFace(0, 2, 1);
        builder.addFace(new int[] {1, 2, 3});
        builder.addFaceUsingVertices(Vector3D.of(0.5, 0, 0), Vector3D.of(1.01, 0, 0), Vector3D.of(1, 1, 0.95));

        final SimpleTriangleMesh mesh = builder.build();

        // assert
        // removed other assertion
        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        Assertions.assertArrayEquals(new int[] {0, 2, 1},  faces.get(0).getVertexIndices());
    }

    @Test
    void testBuilder_mixedBuildMethods_5_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(precision);

        // act
        builder.addVertices(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)));
        builder.useVertex(Vector3D.of(0, 0, 1));
        builder.addVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(1, 1, 1));

        builder.addFace(0, 2, 1);
        builder.addFace(new int[] {1, 2, 3});
        builder.addFaceUsingVertices(Vector3D.of(0.5, 0, 0), Vector3D.of(1.01, 0, 0), Vector3D.of(1, 1, 0.95));

        final SimpleTriangleMesh mesh = builder.build();

        // assert
        // removed other assertion
        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        // removed other assertion
        Assertions.assertArrayEquals(new int[] {1, 2, 3},  faces.get(1).getVertexIndices());
    }

    @Test
    void testBuilder_mixedBuildMethods_6_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(precision);

        // act
        builder.addVertices(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)));
        builder.useVertex(Vector3D.of(0, 0, 1));
        builder.addVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(1, 1, 1));

        builder.addFace(0, 2, 1);
        builder.addFace(new int[] {1, 2, 3});
        builder.addFaceUsingVertices(Vector3D.of(0.5, 0, 0), Vector3D.of(1.01, 0, 0), Vector3D.of(1, 1, 0.95));

        final SimpleTriangleMesh mesh = builder.build();

        // assert
        // removed other assertion
        // removed other assertion

        final List<TriangleMesh.Face> faces = mesh.getFaces();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertArrayEquals(new int[] {5, 1, 4},  faces.get(2).getVertexIndices());
    }

    @Test
    void testBuilder_addVerticesAndFaces_1_oe() {
        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
                Vector3D.of(0, 0, 1)
            })
            .addFaces(new int[][] {
                {0, 1, 2},
                {0, 2, 3}
            })
            .build();

        // assert
        Assertions.assertEquals(4, mesh.getVertexCount());
    }

    @Test
    void testBuilder_addVerticesAndFaces_2_oe() {
        // act
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
                Vector3D.of(0, 0, 1)
            })
            .addFaces(new int[][] {
                {0, 1, 2},
                {0, 2, 3}
            })
            .build();

        // assert
        // removed other assertion
        Assertions.assertEquals(2, mesh.getFaceCount());
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_1_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        Assertions.assertEquals(6, builder.getVertexCount());
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_2_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        // removed other assertion
        Assertions.assertEquals(3, builder.getFaceCount());
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_3_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(p1, builder.getVertex(0));
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_4_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(p1, builder.getVertex(3));
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_5_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final SimpleTriangleMesh mesh = builder.build();

        Assertions.assertEquals(6, mesh.getVertexCount());
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_6_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final SimpleTriangleMesh mesh = builder.build();

        // removed other assertion
        Assertions.assertEquals(3, mesh.getFaceCount());
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_7_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final SimpleTriangleMesh mesh = builder.build();

        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f1 = mesh.getFace(0);
        Assertions.assertArrayEquals(new int[] {0, 1, 2}, f1.getVertexIndices());
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_8_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final SimpleTriangleMesh mesh = builder.build();

        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f1 = mesh.getFace(0);
        // removed other assertion

        final TriangleMesh.Face f2 = mesh.getFace(1);
        Assertions.assertArrayEquals(new int[] {3, 4, 5}, f2.getVertexIndices());
    }

    @Test
    void testBuilder_addFaceAndVertices_vs_addFaceUsingVertices_9_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        builder.addFaceUsingVertices(p1, p2, p3);
        builder.addFaceAndVertices(p1, p2, p3);
        builder.addFaceUsingVertices(p1, p2, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final SimpleTriangleMesh mesh = builder.build();

        // removed other assertion
        // removed other assertion

        final TriangleMesh.Face f1 = mesh.getFace(0);
        // removed other assertion

        final TriangleMesh.Face f2 = mesh.getFace(1);
        // removed other assertion

        final TriangleMesh.Face f3 = mesh.getFace(2);
        Assertions.assertArrayEquals(new int[] {0, 1, 2}, f3.getVertexIndices());
    }

@Test
    void testFace_doesNotDefineTriangle_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(0.01, -0.01, 0.01),
            Vector3D.of(0.01, 0.01, 0.01),
            Vector3D.of(1, 0, 0),
            Vector3D.of(2, 0.01, 0)
        };
        final int[][] faces = {{0, 1, 2}, {0, 3, 4}};
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faces, precision);

        // act/assert
        final Pattern msgPattern = Pattern.compile("^Points do not define a plane: .*");

        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(() -> { mesh.getFace(0).getPolygon(); }, IllegalArgumentException.class, msgPattern);
    }

@Test
    void testFace_doesNotDefineTriangle_4_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(0.01, -0.01, 0.01),
            Vector3D.of(0.01, 0.01, 0.01),
            Vector3D.of(1, 0, 0),
            Vector3D.of(2, 0.01, 0)
        };
        final int[][] faces = {{0, 1, 2}, {0, 3, 4}};
        final SimpleTriangleMesh mesh = SimpleTriangleMesh.from(vertices, faces, precision);

        // act/assert
        final Pattern msgPattern = Pattern.compile("^Points do not define a plane: .*");

        // removed other assertion
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(() -> { mesh.getFace(1).getPolygon(); }, IllegalArgumentException.class, msgPattern);
    }

@Test
    void testBuilder_invalidFaceIndices_1_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(-1, 1, 2); }, IllegalArgumentException.class, msgBase + "-1");
    }

@Test
    void testBuilder_invalidFaceIndices_2_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(0, 3, 2); }, IllegalArgumentException.class, msgBase + "3");
    }

@Test
    void testBuilder_invalidFaceIndices_3_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(0, 1, 4); }, IllegalArgumentException.class, msgBase + "4");
    }

@Test
    void testBuilder_invalidFaceIndices_4_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(new int[] {-1, 1, 2}); }, IllegalArgumentException.class, msgBase + "-1");
    }

@Test
    void testBuilder_invalidFaceIndices_5_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(new int[] {0, 3, 2}); }, IllegalArgumentException.class, msgBase + "3");
    }

@Test
    void testBuilder_invalidFaceIndices_6_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(new int[] {0, 1, 4}); }, IllegalArgumentException.class, msgBase + "4");
    }

@Test
    void testBuilder_invalidFaceIndices_7_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(new int[][] {{-1, 1, 2}}); }, IllegalArgumentException.class, msgBase + "-1");
    }

@Test
    void testBuilder_invalidFaceIndices_8_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(new int[][] {{0, 3, 2}}); }, IllegalArgumentException.class, msgBase + "3");
    }

@Test
    void testBuilder_invalidFaceIndices_9_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(new int[][] {{0, 1, 4}}); }, IllegalArgumentException.class, msgBase + "4");
    }

@Test
    void testBuilder_invalidFaceIndexCount_1_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(new int[] {}); }, IllegalArgumentException.class, msgBase + "0");
    }

@Test
    void testBuilder_invalidFaceIndexCount_2_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(new int[] {0}); }, IllegalArgumentException.class, msgBase + "1");
    }

@Test
    void testBuilder_invalidFaceIndexCount_3_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(new int[] {0, 1}); }, IllegalArgumentException.class, msgBase + "2");
    }

@Test
    void testBuilder_invalidFaceIndexCount_4_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(new int[] {0, 1, 3, 4}); }, IllegalArgumentException.class, msgBase + "4");
    }

@Test
    void testBuilder_invalidFaceIndexCount_5_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(new int[][] {{}}); }, IllegalArgumentException.class, msgBase + "0");
    }

@Test
    void testBuilder_invalidFaceIndexCount_6_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(new int[][] {{0}}); }, IllegalArgumentException.class, msgBase + "1");
    }

@Test
    void testBuilder_invalidFaceIndexCount_7_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(new int[][] {{0, 1}}); }, IllegalArgumentException.class, msgBase + "2");
    }

@Test
    void testBuilder_invalidFaceIndexCount_8_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(new int[][] {{0, 1, 2, 3}}); }, IllegalArgumentException.class, msgBase + "4");
    }

@Test
    void testBuilder_cannotModifyOnceBuilt_1_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
            })
            .addFaces(new int[][] {
                {0, 1, 2}
            });
        builder.build();

        final String msg = "Builder instance cannot be modified: mesh construction is complete";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.useVertex(Vector3D.ZERO); }, IllegalStateException.class, msg);
    }

@Test
    void testBuilder_cannotModifyOnceBuilt_2_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
            })
            .addFaces(new int[][] {
                {0, 1, 2}
            });
        builder.build();

        final String msg = "Builder instance cannot be modified: mesh construction is complete";

        // act/assert
        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addVertex(Vector3D.ZERO); }, IllegalStateException.class, msg);
    }

@Test
    void testBuilder_cannotModifyOnceBuilt_3_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
            })
            .addFaces(new int[][] {
                {0, 1, 2}
            });
        builder.build();

        final String msg = "Builder instance cannot be modified: mesh construction is complete";

        // act/assert
        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addVertices(Collections.singletonList(Vector3D.ZERO)); }, IllegalStateException.class, msg);
    }

@Test
    void testBuilder_cannotModifyOnceBuilt_4_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
            })
            .addFaces(new int[][] {
                {0, 1, 2}
            });
        builder.build();

        final String msg = "Builder instance cannot be modified: mesh construction is complete";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addVertices(new Vector3D[] {Vector3D.ZERO}); }, IllegalStateException.class, msg);
    }

@Test
    void testBuilder_cannotModifyOnceBuilt_5_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
            })
            .addFaces(new int[][] {
                {0, 1, 2}
            });
        builder.build();

        final String msg = "Builder instance cannot be modified: mesh construction is complete";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaceUsingVertices(Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0)); }, IllegalStateException.class, msg);
    }

@Test
    void testBuilder_cannotModifyOnceBuilt_6_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
            })
            .addFaces(new int[][] {
                {0, 1, 2}
            });
        builder.build();

        final String msg = "Builder instance cannot be modified: mesh construction is complete";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFace(0, 1, 2); }, IllegalStateException.class, msg);
    }

@Test
    void testBuilder_cannotModifyOnceBuilt_7_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
            })
            .addFaces(new int[][] {
                {0, 1, 2}
            });
        builder.build();

        final String msg = "Builder instance cannot be modified: mesh construction is complete";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(Collections.singletonList(new int[]{0, 1, 2})); }, IllegalStateException.class, msg);
    }

@Test
    void testBuilder_cannotModifyOnceBuilt_8_oe() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION)
            .addVertices(new Vector3D[] {
                Vector3D.ZERO,
                Vector3D.of(1, 1, 0),
                Vector3D.of(1, 1, 1),
            })
            .addFaces(new int[][] {
                {0, 1, 2}
            });
        builder.build();

        final String msg = "Builder instance cannot be modified: mesh construction is complete";

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.addFaces(new int[][] {{0, 1, 2}}); }, IllegalStateException.class, msg);
    }

}
