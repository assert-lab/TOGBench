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
package org.apache.commons.geometry.euclidean.threed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoundarySource3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testToList_1_oe() {
        final BoundarySource3D src = BoundarySource3D.of(
            Planes.convexPolygonFromVertices(
                    Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION)
        );

        final BoundaryList3D list = src.toList();

        Assertions.assertEquals(1, list.count());
    }

    @Test
    void testToList_noBoundaries_1_oe() {
        final BoundarySource3D src = BoundarySource3D.of();

        final BoundaryList3D list = src.toList();

        Assertions.assertEquals(0, list.count());
    }

    @Test
    void testToTree_1_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final BoundarySource3D src = BoundarySource3D.of(a, b);

        final RegionBSPTree3D tree = src.toTree();

        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void testToTree_2_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final BoundarySource3D src = BoundarySource3D.of(a, b);

        final RegionBSPTree3D tree = src.toTree();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_3_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final BoundarySource3D src = BoundarySource3D.of(a, b);

        final RegionBSPTree3D tree = src.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_noBoundaries_1_oe() {
        final BoundarySource3D src = BoundarySource3D.of();

        final RegionBSPTree3D tree = src.toTree();

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testToTree_noBoundaries_2_oe() {
        final BoundarySource3D src = BoundarySource3D.of();

        final RegionBSPTree3D tree = src.toTree();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_noBoundaries_3_oe() {
        final BoundarySource3D src = BoundarySource3D.of();

        final RegionBSPTree3D tree = src.toTree();

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testOf_varargs_empty_1_oe() {
        final BoundarySource3D src = BoundarySource3D.of();

        final List<PlaneConvexSubset> segments = src.boundaryStream().collect(Collectors.toList());
        Assertions.assertEquals(0, segments.size());
    }

    @Test
    void testOf_varargs_1_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final BoundarySource3D src = BoundarySource3D.of(a, b);

        final List<PlaneConvexSubset> boundaries = src.boundaryStream().collect(Collectors.toList());
        Assertions.assertEquals(2, boundaries.size());
    }

    @Test
    void testOf_varargs_2_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final BoundarySource3D src = BoundarySource3D.of(a, b);

        final List<PlaneConvexSubset> boundaries = src.boundaryStream().collect(Collectors.toList());

        Assertions.assertSame(a, boundaries.get(0));
    }

    @Test
    void testOf_varargs_3_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final BoundarySource3D src = BoundarySource3D.of(a, b);

        final List<PlaneConvexSubset> boundaries = src.boundaryStream().collect(Collectors.toList());

        Assertions.assertSame(b, boundaries.get(1));
    }

    @Test
    void testOf_list_empty_1_oe() {
        final List<PlaneConvexSubset> input = new ArrayList<>();

        final BoundarySource3D src = BoundarySource3D.of(input);

        final List<PlaneConvexSubset> segments = src.boundaryStream().collect(Collectors.toList());
        Assertions.assertEquals(0, segments.size());
    }

    @Test
    void testOf_list_1_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final List<PlaneConvexSubset> input = new ArrayList<>();
        input.add(a);
        input.add(b);

        final BoundarySource3D src = BoundarySource3D.of(input);

        final List<PlaneConvexSubset> segments = src.boundaryStream().collect(Collectors.toList());
        Assertions.assertEquals(2, segments.size());
    }

    @Test
    void testOf_list_2_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final List<PlaneConvexSubset> input = new ArrayList<>();
        input.add(a);
        input.add(b);

        final BoundarySource3D src = BoundarySource3D.of(input);

        final List<PlaneConvexSubset> segments = src.boundaryStream().collect(Collectors.toList());

        Assertions.assertSame(a, segments.get(0));
    }

    @Test
    void testOf_list_3_oe() {
        final PlaneConvexSubset a = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION);
        final PlaneConvexSubset b = Planes.convexPolygonFromVertices(
                Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        final List<PlaneConvexSubset> input = new ArrayList<>();
        input.add(a);
        input.add(b);

        final BoundarySource3D src = BoundarySource3D.of(input);

        final List<PlaneConvexSubset> segments = src.boundaryStream().collect(Collectors.toList());

        Assertions.assertSame(b, segments.get(1));
    }

}
