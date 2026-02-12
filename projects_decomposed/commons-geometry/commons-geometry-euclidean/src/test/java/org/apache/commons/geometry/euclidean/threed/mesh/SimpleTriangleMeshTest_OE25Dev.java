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
    void testBuilder_invalidFaceIndices() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));

        final String msgBase = "Invalid vertex index: ";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(-1, 1, 2);
        }, IllegalArgumentException.class, msgBase + "-1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(0, 3, 2);
        }, IllegalArgumentException.class, msgBase + "3");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(0, 1, 4);
        }, IllegalArgumentException.class, msgBase + "4");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(new int[] {-1, 1, 2});
        }, IllegalArgumentException.class, msgBase + "-1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(new int[] {0, 3, 2});
        }, IllegalArgumentException.class, msgBase + "3");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(new int[] {0, 1, 4});
        }, IllegalArgumentException.class, msgBase + "4");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(new int[][] {{-1, 1, 2}});
        }, IllegalArgumentException.class, msgBase + "-1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(new int[][] {{0, 3, 2}});
        }, IllegalArgumentException.class, msgBase + "3");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(new int[][] {{0, 1, 4}});
        }, IllegalArgumentException.class, msgBase + "4");
    }

    @Test
    void testBuilder_invalidFaceIndexCount() {
        // arrange
        final SimpleTriangleMesh.Builder builder = SimpleTriangleMesh.builder(TEST_PRECISION);
        builder.useVertex(Vector3D.ZERO);
        builder.useVertex(Vector3D.of(1, 0, 0));
        builder.useVertex(Vector3D.of(0, 1, 0));
        builder.useVertex(Vector3D.of(0, 0, 1));

        final String msgBase = "Face must contain 3 vertex indices; found ";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(new int[] {});
        }, IllegalArgumentException.class, msgBase + "0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(new int[] {0});
        }, IllegalArgumentException.class, msgBase + "1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(new int[] {0, 1});
        }, IllegalArgumentException.class, msgBase + "2");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(new int[] {0, 1, 3, 4});
        }, IllegalArgumentException.class, msgBase + "4");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(new int[][] {{}});
        }, IllegalArgumentException.class, msgBase + "0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(new int[][] {{0}});
        }, IllegalArgumentException.class, msgBase + "1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(new int[][] {{0, 1}});
        }, IllegalArgumentException.class, msgBase + "2");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(new int[][] {{0, 1, 2, 3}});
        }, IllegalArgumentException.class, msgBase + "4");
    }

    @Test
    void testBuilder_cannotModifyOnceBuilt() {
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
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.useVertex(Vector3D.ZERO);
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addVertex(Vector3D.ZERO);
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addVertices(Collections.singletonList(Vector3D.ZERO));
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addVertices(new Vector3D[] {Vector3D.ZERO});
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaceUsingVertices(Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0));
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFace(0, 1, 2);
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(Collections.singletonList(new int[]{0, 1, 2}));
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.addFaces(new int[][] {{0, 1, 2}});
        }, IllegalStateException.class, msg);
    }


}
