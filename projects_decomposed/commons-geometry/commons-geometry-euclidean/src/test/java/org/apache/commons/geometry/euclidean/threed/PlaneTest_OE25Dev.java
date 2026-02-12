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
import java.util.Collections;
import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.line.Line3D;
import org.apache.commons.geometry.euclidean.threed.line.Lines3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlaneTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromNormal() {
        // act/assert
        checkPlane(Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION), Vector3D.ZERO, Vector3D.Unit.PLUS_X);
        checkPlane(Planes.fromNormal(Vector3D.of(7, 0, 0), TEST_PRECISION), Vector3D.ZERO, Vector3D.Unit.PLUS_X);

        checkPlane(Planes.fromNormal(Vector3D.Unit.PLUS_Y, TEST_PRECISION), Vector3D.ZERO, Vector3D.Unit.PLUS_Y);
        checkPlane(Planes.fromNormal(Vector3D.of(0, 5, 0), TEST_PRECISION), Vector3D.ZERO, Vector3D.Unit.PLUS_Y);

        checkPlane(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION), Vector3D.ZERO, Vector3D.Unit.PLUS_Z);
        checkPlane(Planes.fromNormal(Vector3D.of(0, 0, 0.01), TEST_PRECISION), Vector3D.ZERO, Vector3D.Unit.PLUS_Z);
    }

    @Test
    void testFromPointAndNormal() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 2, 3);

        // act/assert
        checkPlane(Planes.fromPointAndNormal(pt, Vector3D.of(0.1, 0, 0), TEST_PRECISION),
                Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X);
        checkPlane(Planes.fromPointAndNormal(pt, Vector3D.of(0, 2, 0), TEST_PRECISION),
                Vector3D.of(0, 2, 0), Vector3D.Unit.PLUS_Y);
        checkPlane(Planes.fromPointAndNormal(pt, Vector3D.of(0, 0, 5), TEST_PRECISION),
                Vector3D.of(0, 0, 3), Vector3D.Unit.PLUS_Z);
    }

    @Test
    void testFromPoints() {
        // arrange
        final Vector3D a = Vector3D.of(1, 1, 1);
        final Vector3D b = Vector3D.of(1, 1, 4.3);
        final Vector3D c = Vector3D.of(2.5, 1, 1);

        // act/assert
        checkPlane(Planes.fromPoints(a, b, c, TEST_PRECISION),
                Vector3D.of(0, 1, 0), Vector3D.Unit.PLUS_Y);

        checkPlane(Planes.fromPoints(a, c, b, TEST_PRECISION),
                Vector3D.of(0, 1, 0), Vector3D.Unit.MINUS_Y);
    }

    @Test
    void testFromPoints_collection_concaveWithCollinearAndDuplicatePoints() {
        // arrange
        final List<Vector3D> pts = Arrays.asList(
                    Vector3D.of(1, 0, 1),
                    Vector3D.of(1, 0, 0.5),

                    Vector3D.of(1, 0, 0),
                    Vector3D.of(1, 1, -1),
                    Vector3D.of(1, 2, 0),
                    Vector3D.of(1, 2, 1e-15),
                    Vector3D.of(1, 1, -0.5),
                    Vector3D.of(1, 1 + 1e-15, -0.5),
                    Vector3D.of(1 - 1e-15, 1, -0.5),
                    Vector3D.of(1, 0, 0),

                    Vector3D.of(1, 0, 0.5),
                    Vector3D.of(1, 0, 1)
                );

        final Vector3D origin = Vector3D.of(1, 0, 0);

        // act
        checkPlane(Planes.fromPoints(pts, TEST_PRECISION), origin, Vector3D.Unit.PLUS_X);

        for (int i = 1; i < 12; ++i) {
            checkPlane(Planes.fromPoints(rotate(pts, i), TEST_PRECISION), origin, Vector3D.Unit.PLUS_X);
        }
    }

    @Test
    void testFromPoints_collection_choosesBestOrientation() {
        // act/assert
        checkPlane(Planes.fromPoints(Arrays.asList(
                Vector3D.of(1, 0, 2),
                Vector3D.of(2, 0, 2),
                Vector3D.of(3, 0, 2),
                Vector3D.of(3.5, 1, 2)
            ), TEST_PRECISION), Vector3D.of(0, 0, 2), Vector3D.Unit.PLUS_Z);

        checkPlane(Planes.fromPoints(Arrays.asList(
                Vector3D.of(1, 0, 2),
                Vector3D.of(2, 0, 2),
                Vector3D.of(3, 0, 2),
                Vector3D.of(3.5, -1, 2)
            ), TEST_PRECISION), Vector3D.of(0, 0, 2), Vector3D.Unit.MINUS_Z);

        checkPlane(Planes.fromPoints(Arrays.asList(
                Vector3D.of(1, 0, 2),
                Vector3D.of(2, 0, 2),
                Vector3D.of(3, 0, 2),
                Vector3D.of(3.5, -1, 2),
                Vector3D.of(4, 0, 2)
            ), TEST_PRECISION), Vector3D.of(0, 0, 2), Vector3D.Unit.PLUS_Z);

        checkPlane(Planes.fromPoints(Arrays.asList(
                Vector3D.of(1, 0, 2),
                Vector3D.of(2, 0, 2),
                Vector3D.of(3, 0, 2),
                Vector3D.of(3.5, 1, 2),
                Vector3D.of(4, -1, 2)
            ), TEST_PRECISION), Vector3D.of(0, 0, 2), Vector3D.Unit.MINUS_Z);

        checkPlane(Planes.fromPoints(Arrays.asList(
                Vector3D.of(0, 0, 2),
                Vector3D.of(1, 0, 2),
                Vector3D.of(1, 1, 2),
                Vector3D.of(0, 1, 2),
                Vector3D.of(0, 0, 2)
            ), TEST_PRECISION), Vector3D.of(0, 0, 2), Vector3D.Unit.PLUS_Z);

        checkPlane(Planes.fromPoints(Arrays.asList(
                Vector3D.of(0, 0, 2),
                Vector3D.of(0, 1, 2),
                Vector3D.of(1, 1, 2),
                Vector3D.of(1, 0, 2),
                Vector3D.of(0, 0, 2)
            ), TEST_PRECISION), Vector3D.of(0, 0, 2), Vector3D.Unit.MINUS_Z);

        checkPlane(Planes.fromPoints(Arrays.asList(
                Vector3D.of(0, 0, 2),
                Vector3D.of(1, 0, 2),
                Vector3D.of(2, 1, 2),
                Vector3D.of(3, 0, 2),
                Vector3D.of(2, 4, 2),
                Vector3D.of(0, 0, 2)
            ), TEST_PRECISION), Vector3D.of(0, 0, 2), Vector3D.Unit.PLUS_Z);

        checkPlane(Planes.fromPoints(Arrays.asList(
                Vector3D.of(0, 0, 2),
                Vector3D.of(0, 1, 2),
                Vector3D.of(2, 4, 2),
                Vector3D.of(3, 0, 2),
                Vector3D.of(2, 1, 2),
                Vector3D.of(0, 0, 2)
            ), TEST_PRECISION), Vector3D.of(0, 0, 2), Vector3D.Unit.MINUS_Z);
    }

    @Test
    void testGetEmbedding() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 2, 3);
        EuclideanTestUtils.permuteSkipZero(-4, 4, 1, (x, y, z) -> {

            final Plane plane = Planes.fromPointAndNormal(pt, Vector3D.of(x, y, z), TEST_PRECISION);

            // act
            final EmbeddingPlane embeddingPlane = plane.getEmbedding();
            final EmbeddingPlane nextEmbeddingPlane = plane.getEmbedding();

            // assert
            Assertions.assertSame(plane.getNormal(), embeddingPlane.getNormal());
            Assertions.assertSame(plane.getNormal(), embeddingPlane.getW());
            Assertions.assertEquals(plane.getOriginOffset(), embeddingPlane.getOriginOffset(), TEST_EPS);
            Assertions.assertSame(plane.getPrecision(), embeddingPlane.getPrecision());

            final Vector3D.Unit u = embeddingPlane.getU();
            final Vector3D.Unit v = embeddingPlane.getV();
            final Vector3D.Unit w = embeddingPlane.getW();

            Assertions.assertEquals(0, u.dot(v), TEST_EPS);
            Assertions.assertEquals(0, u.dot(w), TEST_EPS);
            Assertions.assertEquals(0, v.dot(w), TEST_EPS);

            Assertions.assertNotSame(embeddingPlane, nextEmbeddingPlane);
            Assertions.assertEquals(embeddingPlane, nextEmbeddingPlane);
        });
    }

    @Test
    void testContains_point() {
        // arrange
        final Plane plane = Planes.fromPointAndNormal(Vector3D.of(0, 0, 1), Vector3D.of(0, 0, 1), TEST_PRECISION);
        final double halfEps = 0.5 * TEST_EPS;

        // act/assert
        EuclideanTestUtils.permute(-100, 100, 5, (x, y) -> {

            Assertions.assertTrue(plane.contains(Vector3D.of(x, y, 1)));
            Assertions.assertTrue(plane.contains(Vector3D.of(x, y, 1 + halfEps)));
            Assertions.assertTrue(plane.contains(Vector3D.of(x, y, 1 - halfEps)));

            Assertions.assertFalse(plane.contains(Vector3D.of(x, y, 0.5)));
            Assertions.assertFalse(plane.contains(Vector3D.of(x, y, 1.5)));
        });
    }

    @Test
    void testProject_point() {
        // arrange
        final Vector3D pt = Vector3D.of(-3, -2, -1);
        EuclideanTestUtils.permuteSkipZero(-4, 4, 1, (nx, ny, nz) -> {

            final Plane plane = Planes.fromPointAndNormal(pt, Vector3D.of(nx, ny, nz), TEST_PRECISION);
            EuclideanTestUtils.permute(-4, 4, 1, (x, y, z) -> {

                final Vector3D p = Vector3D.of(x, y, z);

                // act
                final Vector3D proj = plane.project(p);

                // assert
                Assertions.assertTrue(plane.contains(proj));
                Assertions.assertEquals(0, plane.getOrigin().vectorTo(proj).dot(plane.getNormal()), TEST_EPS);
            });
        });
    }

    @Test
    void testTransform_rotationAroundPoint() {
        // arrange
        final Vector3D pt = Vector3D.of(0, 0, 1);
        final Plane plane = Planes.fromPointAndNormal(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final AffineTransformMatrix3D mat = AffineTransformMatrix3D.createRotation(pt,
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO));

        // act
        final Plane result = plane.transform(mat);

        // assert
        checkPlane(result, Vector3D.ZERO, Vector3D.Unit.PLUS_X);
    }

    @Test
    void testTransform_negateTwoComponents() {
        // arrange
        final Vector3D pt = Vector3D.of(0, 0, 1);
        final Plane plane = Planes.fromPointAndNormal(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.from(v -> Vector3D.of(-v.getX(), -v.getY(), v.getZ()));

        // act
        final Plane result = plane.transform(transform);

        // assert
        checkPlane(result, Vector3D.of(0, 0, 1), Vector3D.Unit.PLUS_Z);
    }

    @Test
    void testTransform_negateAllComponents() {
        // arrange
        final Vector3D pt = Vector3D.of(0, 0, 1);
        final Plane plane = Planes.fromPointAndNormal(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.from(Vector3D::negate);

        // act
        final Plane result = plane.transform(transform);

        // assert
        checkPlane(result, Vector3D.of(0, 0, -1), Vector3D.Unit.PLUS_Z);
    }

    @Test
    void testTransform_consistency() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 2, 3);
        final Vector3D normal = Vector3D.Unit.of(1, 1, 1);

        final Plane plane = Planes.fromPointAndNormal(pt, normal, TEST_PRECISION);

        final Vector3D p1 = plane.project(Vector3D.of(4, 5, 6));
        final Vector3D p2 = plane.project(Vector3D.of(-7, -8, -9));
        final Vector3D p3 = plane.project(Vector3D.of(10, -11, 12));

        final Vector3D notOnPlane1 = plane.getOrigin().add(plane.getNormal());
        final Vector3D notOnPlane2 = plane.getOrigin().subtract(plane.getNormal());

        EuclideanTestUtils.permuteSkipZero(-4, 4, 1, (a, b, c) -> {
            final AffineTransformMatrix3D t = AffineTransformMatrix3D.identity()
                    .rotate(Vector3D.of(-1, 2, 3),
                            QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.3 * a))
                    .scale(Math.max(a, 1), Math.max(b, 1), Math.max(c, 1))
                    .translate(c, b, a);

            // act
            final Plane result = plane.transform(t);

            // assert
            Vector3D expectedNormal = t.normalTransform().apply(plane.getNormal()).normalize();
            if (!t.preservesOrientation()) {
                expectedNormal = expectedNormal.negate();
            }

            EuclideanTestUtils.assertCoordinatesEqual(expectedNormal, result.getNormal(), TEST_EPS);

            Assertions.assertTrue(result.contains(t.apply(p1)));
            Assertions.assertTrue(result.contains(t.apply(p2)));
            Assertions.assertTrue(result.contains(t.apply(p3)));

            Assertions.assertFalse(result.contains(t.apply(notOnPlane1)));
            Assertions.assertFalse(result.contains(t.apply(notOnPlane2)));
        });
    }

    @Test
    void testIntersection_threePlanes() {
        // arrange
        final Vector3D pt = Vector3D.of(1.2, 3.4, -5.8);
        final Plane a = Planes.fromPointAndNormal(pt, Vector3D.of(1, 3, 3), TEST_PRECISION);
        final Plane b = Planes.fromPointAndNormal(pt, Vector3D.of(-2, 4, 0), TEST_PRECISION);
        final Plane c = Planes.fromPointAndNormal(pt, Vector3D.of(7, 0, -4), TEST_PRECISION);

        // act
        final Vector3D result = Plane.intersection(a, b, c);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(pt, result, TEST_EPS);
    }

    private static void checkPlane(final Plane plane, final Vector3D origin, final Vector3D normal) {
        EuclideanTestUtils.assertCoordinatesEqual(origin, plane.getOrigin(), TEST_EPS);
        Assertions.assertTrue(plane.contains(origin));

        EuclideanTestUtils.assertCoordinatesEqual(normal, plane.getNormal(), TEST_EPS);
        Assertions.assertEquals(1.0, plane.getNormal().norm(), TEST_EPS);

        final double offset = plane.getOriginOffset();
        Assertions.assertEquals(Vector3D.ZERO.distance(plane.getOrigin()), Math.abs(offset), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(origin, plane.getNormal().multiply(-offset), TEST_EPS);
    }

    private static <T> List<T> rotate(final List<T> list, final int shift) {
        final int size = list.size();

        final List<T> result = new ArrayList<>(size);

        for (int i = 0; i < size; ++i) {
            result.add(list.get((i + shift) % size));
        }

        return result;
    }

    @Test
    void testFromNormal_illegalArguments_1_oe() {
        // act/assert
        try {
    Planes.fromNormal(Vector3D.ZERO, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPointAndNormal_illegalArguments_1_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 2, 3);

        // act/assert
        try {
    Planes.fromPointAndNormal(pt, Vector3D.ZERO, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_illegalArguments_1_oe() {
        // arrange
        final Vector3D a = Vector3D.of(1, 0, 0);
        final Vector3D b = Vector3D.of(0, 1, 0);

        // act/assert

        try {
    Planes.fromPoints(a, a, a, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_illegalArguments_2_oe() {
        // arrange
        final Vector3D a = Vector3D.of(1, 0, 0);
        final Vector3D b = Vector3D.of(0, 1, 0);

        // act/assert

        // removed other assertion
        try {
    Planes.fromPoints(a, a, b, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_illegalArguments_3_oe() {
        // arrange
        final Vector3D a = Vector3D.of(1, 0, 0);
        final Vector3D b = Vector3D.of(0, 1, 0);

        // act/assert

        // removed other assertion
        // removed other assertion
        try {
    Planes.fromPoints(a, b, a, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_illegalArguments_4_oe() {
        // arrange
        final Vector3D a = Vector3D.of(1, 0, 0);
        final Vector3D b = Vector3D.of(0, 1, 0);

        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Planes.fromPoints(b, a, a, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_collection_illegalArguments_1_oe() {
        // arrange
        final Vector3D a = Vector3D.ZERO;
        final Vector3D b = Vector3D.Unit.PLUS_X;

        // act/assert

        try {
    Planes.fromPoints(Collections.emptyList(), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_collection_illegalArguments_2_oe() {
        // arrange
        final Vector3D a = Vector3D.ZERO;
        final Vector3D b = Vector3D.Unit.PLUS_X;

        // act/assert

        // removed other assertion
        try {
    Planes.fromPoints(Collections.singletonList(a), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_collection_illegalArguments_3_oe() {
        // arrange
        final Vector3D a = Vector3D.ZERO;
        final Vector3D b = Vector3D.Unit.PLUS_X;

        // act/assert

        // removed other assertion
        // removed other assertion
        try {
    Planes.fromPoints(Arrays.asList(a, b), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_collection_allPointsCollinear_1_oe() {
        // act/assert
        try {
    Planes.fromPoints(Arrays.asList( Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.of(2, 0, 0) ), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_collection_allPointsCollinear_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Planes.fromPoints(Arrays.asList( Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.of(2, 0, 0), Vector3D.of(3, 0, 0) ), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_collection_notEnoughUniquePoints_1_oe() {
        // act/assert
        try {
    Planes.fromPoints(Arrays.asList( Vector3D.ZERO, Vector3D.ZERO, Vector3D.of(1e-12, 1e-12, 0), Vector3D.Unit.PLUS_X ), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_collection_notEnoughUniquePoints_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Planes.fromPoints(Arrays.asList( Vector3D.ZERO, Vector3D.of(1e-12, 0, 0), Vector3D.ZERO ), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_collection_pointsNotOnSamePlane_1_oe() {
        // act/assert
        try {
    Planes.fromPoints(Arrays.asList( Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_Z ), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
