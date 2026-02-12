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
package org.apache.commons.geometry.euclidean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.bsp.RegionCutRule;
import org.apache.commons.geometry.euclidean.oned.Interval;
import org.apache.commons.geometry.euclidean.oned.RegionBSPTree1D;
import org.apache.commons.geometry.euclidean.oned.Vector1D;
import org.apache.commons.geometry.euclidean.threed.AffineTransformMatrix3D;
import org.apache.commons.geometry.euclidean.threed.ConvexPolygon3D;
import org.apache.commons.geometry.euclidean.threed.Plane;
import org.apache.commons.geometry.euclidean.threed.PlaneConvexSubset;
import org.apache.commons.geometry.euclidean.threed.Planes;
import org.apache.commons.geometry.euclidean.threed.RegionBSPTree3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.line.Line3D;
import org.apache.commons.geometry.euclidean.threed.line.LinecastPoint3D;
import org.apache.commons.geometry.euclidean.threed.line.Lines3D;
import org.apache.commons.geometry.euclidean.threed.line.Ray3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.geometry.euclidean.threed.shape.Parallelepiped;
import org.apache.commons.geometry.euclidean.twod.AffineTransformMatrix2D;
import org.apache.commons.geometry.euclidean.twod.Line;
import org.apache.commons.geometry.euclidean.twod.LinecastPoint2D;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Ray;
import org.apache.commons.geometry.euclidean.twod.RegionBSPTree2D;
import org.apache.commons.geometry.euclidean.twod.Segment;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.path.LinePath;
import org.apache.commons.geometry.euclidean.twod.shape.Parallelogram;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** This class contains code listed as examples in the user guide and other documentation.
 * If any portion of this code changes, the corresponding examples in the documentation <em>must</em> be updated.
 */
class DocumentationExamplesTest_OE25Dev {

    private static final double TEST_EPS = 1e-12;

    @Test
    void testLinecast2DExample() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-6);

        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), precision);

        final LinecastPoint2D pt = box.linecastFirst(
                Lines.segmentFromPoints(Vector2D.of(1, 0.5), Vector2D.of(4, 0.5), precision));

        final Vector2D intersection = pt.getPoint(); // (2.0, 0.5)
        final Vector2D normal = pt.getNormal(); // (1.0, 0.0)

        // ----------------
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 0.5), intersection, TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 0), normal, TEST_EPS);
    }

    @Test
    void testPlaneIntersectionExample() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // create two planes
        final Plane a = Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z, precision);
        final Plane b = Planes.fromPointAndPlaneVectors(Vector3D.of(1, 1, 1),
                Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_Y, precision);

        // compute the intersection
        final Line3D line = a.intersection(b);

        final Vector3D dir = line.getDirection(); // (0, 1, 0)

        // ----------------------
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Y, dir, TEST_EPS);
    }

    @Test
    void testTransform3DExample() {
        final List<Vector3D> inputPts = Arrays.asList(
                Vector3D.ZERO,
                Vector3D.Unit.PLUS_X,
                Vector3D.Unit.PLUS_Y,
                Vector3D.Unit.PLUS_Z);

        // create a 4x4 transform matrix and quaternion rotation
        final AffineTransformMatrix3D mat = AffineTransformMatrix3D.createScale(2)
                .translate(Vector3D.of(1, 2, 3));

        final QuaternionRotation rot = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z,
                Angle.PI_OVER_TWO);

        // transform the input points
        final List<Vector3D> matOutput = inputPts.stream()
                .map(mat)
                .collect(Collectors.toList()); // [(1, 2, 3), (3, 2, 3), (1, 4, 3), (1, 2, 5)]

        final List<Vector3D> rotOutput = inputPts.stream()
                .map(rot)
                .collect(Collectors.toList()); // [(0, 0, 0), (0, 1, 0), (-1, 0, 0), (0, 0, 1)]

        // ----------------
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 3), matOutput.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 2, 3), matOutput.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 4, 3), matOutput.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 5), matOutput.get(3), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 0, 0), rotOutput.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, 0), rotOutput.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 0, 0), rotOutput.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 0, 1), rotOutput.get(3), TEST_EPS);
    }


}
