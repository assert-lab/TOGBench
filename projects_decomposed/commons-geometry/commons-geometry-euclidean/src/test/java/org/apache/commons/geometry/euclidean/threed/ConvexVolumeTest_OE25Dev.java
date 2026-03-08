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

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.line.Lines3D;
import org.apache.commons.geometry.euclidean.twod.ConvexArea;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class ConvexVolumeTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testGetBounds_hasBounds() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // act
        final Bounds3D bounds = vol.getBounds();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0.5, 0, -1), bounds.getMin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1.5, 2, 3), bounds.getMax(), TEST_EPS);
    }

    @Test
    void testLinecast_full() {
        // arrange
        final ConvexVolume volume = ConvexVolume.full();

        // act/assert
        LinecastChecker3D.with(volume)
            .expectNothing()
            .whenGiven(Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION));

        LinecastChecker3D.with(volume)
            .expectNothing()
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_X, TEST_PRECISION));
    }

    @Test
    void testLinecast() {
        // arrange
        final ConvexVolume volume = rect(Vector3D.of(0.5, 0.5, 0.5), 0.5, 0.5, 0.5);

        // act/assert
        LinecastChecker3D.with(volume)
            .expectNothing()
            .whenGiven(Lines3D.fromPoints(Vector3D.of(0, 5, 5), Vector3D.of(1, 5, 5), TEST_PRECISION));

        LinecastChecker3D.with(volume)
            .expect(Vector3D.ZERO, Vector3D.Unit.MINUS_X)
            .and(Vector3D.ZERO, Vector3D.Unit.MINUS_Y)
            .and(Vector3D.ZERO, Vector3D.Unit.MINUS_Z)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X)
            .whenGiven(Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION));

        LinecastChecker3D.with(volume)
            .expect(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X)
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(1, 1, 1), TEST_PRECISION));
    }

    private static ConvexVolume rect(final Vector3D center, final double xDelta, final double yDelta, final double zDelta) {
        final List<Plane> planes = Arrays.asList(
                    Planes.fromPointAndNormal(center.add(Vector3D.of(xDelta, 0, 0)), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(center.add(Vector3D.of(-xDelta, 0, 0)), Vector3D.Unit.MINUS_X, TEST_PRECISION),

                    Planes.fromPointAndNormal(center.add(Vector3D.of(0, yDelta, 0)), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(center.add(Vector3D.of(0, -yDelta, 0)), Vector3D.Unit.MINUS_Y, TEST_PRECISION),

                    Planes.fromPointAndNormal(center.add(Vector3D.of(0, 0, zDelta)), Vector3D.Unit.PLUS_Z, TEST_PRECISION),
                    Planes.fromPointAndNormal(center.add(Vector3D.of(0, 0, -zDelta)), Vector3D.Unit.MINUS_Z, TEST_PRECISION)
                );

        return ConvexVolume.fromBounds(planes);
    }

    @Test
    void testFull_1_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.full();

        // assert
        Assertions.assertTrue(vol.isFull());
    }

    @Test
    void testFull_2_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.full();

        // assert
        // removed other assertion
        Assertions.assertFalse(vol.isEmpty());
    }

    @Test
    void testFull_4_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.full();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(vol.getCentroid());
    }

    @Test
    void testFull_5_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.full();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, vol.getBoundaries().size());
    }

    @Test
    void testFull_6_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.full();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, vol.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBoundaryStream_1_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final ConvexVolume volume = ConvexVolume.fromBounds(plane);

        // act
        final List<PlaneConvexSubset> boundaries = volume.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(1, boundaries.size());
    }

    @Test
    void testBoundaryStream_2_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final ConvexVolume volume = ConvexVolume.fromBounds(plane);

        // act
        final List<PlaneConvexSubset> boundaries = volume.boundaryStream().collect(Collectors.toList());

        // assert
        // removed other assertion

        final PlaneConvexSubset sp = boundaries.get(0);
        Assertions.assertEquals(0, sp.getEmbedded().getSubspaceRegion().getBoundaries().size());
    }

    @Test
    void testBoundaryStream_noBoundaries_1_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.full();

        // act
        final List<PlaneConvexSubset> boundaries = volume.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(0, boundaries.size());
    }

    @Test
    void testTriangleStream_noBoundaries_1_oe() {
        // arrange
        final ConvexVolume full = ConvexVolume.full();

        // act
        final List<Triangle3D> tris = full.triangleStream().collect(Collectors.toList());

        // act/assert
        Assertions.assertEquals(0, tris.size());
    }

    @Test
    void testTriangleStream_finite_1_oe() {
        // arrange
        final Vector3D min = Vector3D.ZERO;
        final Vector3D max = Vector3D.of(1, 1, 1);

        final ConvexVolume box = ConvexVolume.fromBounds(
                    Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Z, TEST_PRECISION),

                    Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Z, TEST_PRECISION)
                );

        // act
        final List<Triangle3D> tris = box.triangleStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(12, tris.size());
    }

    @Test
    void testGetBounds_noBounds_1_oe() {
        // arrange
        final ConvexVolume full = ConvexVolume.full();
        final ConvexVolume halfFull = ConvexVolume.fromBounds(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // act/assert
        Assertions.assertNull(full.getBounds());
    }

    @Test
    void testGetBounds_noBounds_2_oe() {
        // arrange
        final ConvexVolume full = ConvexVolume.full();
        final ConvexVolume halfFull = ConvexVolume.fromBounds(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // act/assert
        // removed other assertion
        Assertions.assertNull(halfFull.getBounds());
    }

    @Test
    void testToList_full_1_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.full();

        // act
        final BoundaryList3D list = volume.toList();

        // assert
        Assertions.assertEquals(0, list.count());
    }

    @Test
    void testToList_1_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.fromBounds(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION),

                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION)
                );

        // act
        final BoundaryList3D list = volume.toList();

        // assert
        Assertions.assertEquals(6, list.count());
    }

    @Test
    void testToList_2_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.fromBounds(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION),

                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION)
                );

        // act
        final BoundaryList3D list = volume.toList();

        // assert
        // removed other assertion
        Assertions.assertEquals(1, list.toTree().getSize(), TEST_EPS);
    }

    @Test
    void testToTree_full_1_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.full();

        // act
        final RegionBSPTree3D tree = volume.toTree();

        // assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testToTree_full_2_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.full();

        // act
        final RegionBSPTree3D tree = volume.toTree();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_1_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.fromBounds(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION),

                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION)
                );

        // act
        final RegionBSPTree3D tree = volume.toTree();

        // assert
        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
    }

    @Test
    void testToTree_2_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.fromBounds(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION),

                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION)
                );

        // act
        final RegionBSPTree3D tree = volume.toTree();

        // assert
        // removed other assertion
        Assertions.assertEquals(6, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_noPlanes_1_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.fromBounds();

        // assert
        Assertions.assertSame(ConvexVolume.full(), vol);
    }

    @Test
    void testFromBounds_halfspace_1_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.fromBounds(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // assert
        Assertions.assertFalse(vol.isFull());
    }

    @Test
    void testFromBounds_halfspace_2_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.fromBounds(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // assert
        // removed other assertion
        Assertions.assertFalse(vol.isEmpty());
    }

    @Test
    void testFromBounds_halfspace_4_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.fromBounds(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(vol.getCentroid());
    }

    @Test
    void testFromBounds_halfspace_5_oe() {
        // act
        final ConvexVolume vol = ConvexVolume.fromBounds(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, vol.getBoundaries().size());
    }

    @Test
    void testFromBounds_cube_1_oe() {
        // act
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // assert
        Assertions.assertFalse(vol.isFull());
    }

    @Test
    void testFromBounds_cube_2_oe() {
        // act
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // assert
        // removed other assertion
        Assertions.assertFalse(vol.isEmpty());
    }

    @Test
    void testFromBounds_cube_3_oe() {
        // act
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(8, vol.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_cube_5_oe() {
        // act
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(6, vol.getBoundaries().size());
    }

    @Test
    void testFromBounds_cube_6_oe() {
        // act
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(28, vol.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTrim_1_oe() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.ZERO, 0.5, 0.5, 0.5);

        final PlaneConvexSubset subplane = Planes.subsetFromConvexArea(
                Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION).getEmbedding(), ConvexArea.full());

        // act
        final PlaneConvexSubset trimmed = vol.trim(subplane);

        // assert
        Assertions.assertEquals(1, trimmed.getSize(), TEST_EPS);
    }

    @Test
    void testTrim_2_oe() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.ZERO, 0.5, 0.5, 0.5);

        final PlaneConvexSubset subplane = Planes.subsetFromConvexArea(
                Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION).getEmbedding(), ConvexArea.full());

        // act
        final PlaneConvexSubset trimmed = vol.trim(subplane);

        // assert
        // removed other assertion

        final List<Vector3D> vertices = trimmed.getVertices();

        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testSplit_1_oe() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.ZERO, 0.5, 0.5, 0.5);

        final Plane splitter = Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<ConvexVolume> split = vol.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_2_oe() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.ZERO, 0.5, 0.5, 0.5);

        final Plane splitter = Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<ConvexVolume> split = vol.split(splitter);

        // assert
        // removed other assertion

        final ConvexVolume minus = split.getMinus();
        Assertions.assertEquals(0.5, minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_4_oe() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.ZERO, 0.5, 0.5, 0.5);

        final Plane splitter = Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<ConvexVolume> split = vol.split(splitter);

        // assert
        // removed other assertion

        final ConvexVolume minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        final ConvexVolume plus = split.getPlus();
        Assertions.assertEquals(0.5, plus.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_1_oe() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.ZERO, 0.5, 0.5, 0.5);

        final Transform<Vector3D> transform = AffineTransformMatrix3D.identity()
                .translate(Vector3D.of(1, 2, 3))
                .scale(Vector3D.of(2, 1, 1));

        // act
        final ConvexVolume transformed = vol.transform(transform);

        // assert
        Assertions.assertEquals(2, transformed.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_2_oe() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.ZERO, 0.5, 0.5, 0.5);

        final Transform<Vector3D> transform = AffineTransformMatrix3D.identity()
                .translate(Vector3D.of(1, 2, 3))
                .scale(Vector3D.of(2, 1, 1));

        // act
        final ConvexVolume transformed = vol.transform(transform);

        // assert
        // removed other assertion
        Assertions.assertEquals(10, transformed.getBoundarySize(), TEST_EPS);
    }

@Test
    void testTriangleStream_infinite_1_oe() {
        // arrange
        final Pattern pattern = Pattern.compile("^Cannot convert infinite plane subset to triangles: .*");

        final ConvexVolume half = ConvexVolume.fromBounds(
                Planes.fromNormal(Vector3D.Unit.MINUS_X, TEST_PRECISION)
            );

        final ConvexVolume quadrant = ConvexVolume.fromBounds(
                    Planes.fromNormal(Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromNormal(Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromNormal(Vector3D.Unit.MINUS_Z, TEST_PRECISION)
                );

        // act/assert
        try {
     half.triangleStream().collect(Collectors.toList());
    fail("Expected IllegalStateException with message: " + pattern);
} catch (IllegalStateException e) {
}
    }

@Test
    void testTriangleStream_infinite_2_oe() {
        // arrange
        final Pattern pattern = Pattern.compile("^Cannot convert infinite plane subset to triangles: .*");

        final ConvexVolume half = ConvexVolume.fromBounds(
                Planes.fromNormal(Vector3D.Unit.MINUS_X, TEST_PRECISION)
            );

        final ConvexVolume quadrant = ConvexVolume.fromBounds(
                    Planes.fromNormal(Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromNormal(Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromNormal(Vector3D.Unit.MINUS_Z, TEST_PRECISION)
                );

        // act/assert
        // removed other assertion

        try {
     quadrant.triangleStream().collect(Collectors.toList());
    fail("Expected IllegalStateException with message: " + pattern);
} catch (IllegalStateException e) {
}
    }

@Test
    void testToTree_4_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.fromBounds(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION),

                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION)
                );

        // act
        final RegionBSPTree3D tree = volume.toTree();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertRegionLocation(tree, RegionLocation.OUTSIDE, Vector3D.of(-1, 0.5, 0.5), Vector3D.of(2, 0.5, 0.5), Vector3D.of(0.5, -1, 0.5), Vector3D.of(0.5, 2, 0.5), Vector3D.of(0.5, 0.5, -1), Vector3D.of(0.5, 0.5, 2));
    }

@Test
    void testFromBounds_cube_8_oe() {
        // act
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        EuclideanTestUtils.assertRegionLocation(vol, RegionLocation.BOUNDARY, Vector3D.of(0.5, 0, -1), Vector3D.of(1.5, 2, 3));
    }

@Test
    void testFromBounds_cube_9_oe() {
        // act
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        EuclideanTestUtils.assertRegionLocation(vol, RegionLocation.OUTSIDE, Vector3D.of(0, 1, 1), Vector3D.of(2, 1, 1), Vector3D.of(1, -1, 1), Vector3D.of(1, 3, 1), Vector3D.of(1, 1, -2), Vector3D.of(1, 1, 4));
    }

}
