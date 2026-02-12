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
package org.apache.commons.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConvexArea2STest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static List<GreatArc> sortArcs(final List<GreatArc> arcs) {
        final List<GreatArc> result = new ArrayList<>(arcs);

        result.sort((a, b) ->
                Point2S.POLAR_AZIMUTH_ASCENDING_ORDER.compare(a.getStartPoint(), b.getStartPoint()));

        return result;
    }

    private static Point2S triangleCentroid(final Point2S p1, final Point2S p2, final Point2S p3) {
        // compute the centroid as the sum of the cross product of each point pair weighted by
        // the angle between the points
        final Vector3D v1 = p1.getVector();
        final Vector3D v2 = p2.getVector();
        final Vector3D v3 = p3.getVector();

        Vector3D sum = Vector3D.ZERO;
        sum = sum.add(v1.cross(v2).withNorm(v1.angle(v2)));
        sum = sum.add(v2.cross(v3).withNorm(v2.angle(v3)));
        sum = sum.add(v3.cross(v1).withNorm(v3.angle(v1)));

        return Point2S.from(sum);
    }

    private static void checkArc(final GreatArc arc, final Point2S start, final Point2S end) {
        SphericalTestUtils.assertPointsEq(start, arc.getStartPoint(), TEST_EPS);
        SphericalTestUtils.assertPointsEq(end, arc.getEndPoint(), TEST_EPS);
    }

    private static void assertPath(final GreatArcPath path, final Point2S... expectedVertices) {
        final List<Point2S> vertices = path.getVertices();

        Assertions.assertEquals(expectedVertices.length, vertices.size());
        for (int i = 0; i < expectedVertices.length; ++i) {

            if (!expectedVertices[i].eq(vertices.get(i), TEST_PRECISION)) {
                final String msg = "Unexpected point in path at index " + i + ". Expected " +
                        Arrays.toString(expectedVertices) + " but received " + vertices;
                Assertions.fail(msg);
            }
        }
    }

    private static void checkCentroidConsistency(final ConvexArea2S area) {
        final Point2S centroid = area.getCentroid();
        final double size = area.getSize();

        SphericalTestUtils.checkClassify(area, RegionLocation.INSIDE, centroid);

        final GreatCircle circle = GreatCircles.fromPole(centroid.getVector(), TEST_PRECISION);
        for (double az = 0; az <= Angle.TWO_PI; az += 0.2) {
            final Point2S pt = circle.toSpace(Point1S.of(az));
            final GreatCircle splitter = GreatCircles.fromPoints(centroid, pt, TEST_PRECISION);

            final Split<ConvexArea2S> split = area.split(splitter);

            Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

            final ConvexArea2S minus = split.getMinus();
            final double minusSize = minus.getSize();

            final ConvexArea2S plus = split.getPlus();
            final double plusSize = plus.getSize();

            final Vector3D minusWeightedCentroid = minus.getWeightedCentroidVector();
            final Vector3D plusWeightedCentroid = plus.getWeightedCentroidVector();

            final Point2S computedCentroid = Point2S.from(minusWeightedCentroid.add(plusWeightedCentroid));

            Assertions.assertEquals(size, minusSize + plusSize, TEST_EPS);
            SphericalTestUtils.assertPointsEq(centroid, computedCentroid, TEST_EPS);
        }
    }

    @Test
    void testFromVertices_invalidArguments_1_oe() {
        // act/assert
        try {
    ConvexArea2S.fromVertices(Collections.singletonList(Point2S.PLUS_I), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testFromVertices_invalidArguments_2_oe() {
        // act/assert
        // removed other assertion
        try {
    ConvexArea2S.fromVertices(Arrays.asList(Point2S.PLUS_I, Point2S.of(1e-16, Angle.PI_OVER_TWO)), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
