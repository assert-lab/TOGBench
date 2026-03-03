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
package org.apache.commons.geometry.euclidean.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EuclideanUtilsTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testConvexPolygonToTriangleFan_threeVertices_1_oe() {
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        final List<List<Vector3D>> tris = new ArrayList<>();

        EuclideanUtils.convexPolygonToTriangleFan(Arrays.asList(p1, p2, p3), tris::add);

        Assertions.assertEquals(1, tris.size());
    }

    @Test
    void testConvexPolygonToTriangleFan_fourVertices_1_oe() {
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(1, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 0);

        final List<List<Vector3D>> tris = new ArrayList<>();

        EuclideanUtils.convexPolygonToTriangleFan(Arrays.asList(p1, p2, p3, p4), tris::add);

        Assertions.assertEquals(2, tris.size());
    }

    @Test
    void testConvexPolygonToTriangleFan_fourVertices_chooseLargestInteriorAngleForBase_1_oe() {
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(2, 1, 0);
        final Vector3D p4 = Vector3D.of(1.5, 1, 0);

        final List<List<Vector3D>> tris = new ArrayList<>();

        EuclideanUtils.convexPolygonToTriangleFan(Arrays.asList(p1, p2, p3, p4), tris::add);

        Assertions.assertEquals(2, tris.size());
    }

    @Test
    void testConvexPolygonToTriangleFan_fourVertices_distancesLessThanPrecision_1_oe() {

        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1e-20, 0, 0);
        final Vector3D p3 = Vector3D.of(1e-20, 1e-20, 0);
        final Vector3D p4 = Vector3D.of(0, 1e-20, 0);

        final List<List<Vector3D>> tris = new ArrayList<>();

        EuclideanUtils.convexPolygonToTriangleFan(Arrays.asList(p1, p2, p3, p4), tris::add);

        Assertions.assertEquals(2, tris.size());
    }

    @Test
    void testConvexPolygonToTriangleFan_sixVertices_1_oe() {
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, -1, 0);
        final Vector3D p3 = Vector3D.of(1.5, -1, 0);
        final Vector3D p4 = Vector3D.of(5, 0, 0);
        final Vector3D p5 = Vector3D.of(3, 1, 0);
        final Vector3D p6 = Vector3D.of(0.5, 1, 0);

        final List<List<Vector3D>> tris = new ArrayList<>();

        EuclideanUtils.convexPolygonToTriangleFan(Arrays.asList(p1, p2, p3, p4, p5, p6), tris::add);

        Assertions.assertEquals(4, tris.size());
    }

@Test
    void testConvexPolygonToTriangleFan_notEnoughVertices_1_oe() {
        final String baseMsg = "Cannot create triangle fan: 3 or more vertices are required but found only ";

        GeometryTestUtils.assertThrowsWithMessage(() -> { EuclideanUtils.convexPolygonToTriangleFan(Collections.emptyList(), Function.identity()); }, IllegalArgumentException.class, baseMsg + "0");
    }

@Test
    void testConvexPolygonToTriangleFan_notEnoughVertices_2_oe() {
        final String baseMsg = "Cannot create triangle fan: 3 or more vertices are required but found only ";


        GeometryTestUtils.assertThrowsWithMessage(() -> { EuclideanUtils.convexPolygonToTriangleFan(Collections.singletonList(Vector3D.ZERO), Function.identity()); }, IllegalArgumentException.class, baseMsg + "1");
    }

@Test
    void testConvexPolygonToTriangleFan_notEnoughVertices_3_oe() {
        final String baseMsg = "Cannot create triangle fan: 3 or more vertices are required but found only ";



        GeometryTestUtils.assertThrowsWithMessage(() -> { EuclideanUtils.convexPolygonToTriangleFan(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)), Function.identity()); }, IllegalArgumentException.class, baseMsg + "2");
    }

}
