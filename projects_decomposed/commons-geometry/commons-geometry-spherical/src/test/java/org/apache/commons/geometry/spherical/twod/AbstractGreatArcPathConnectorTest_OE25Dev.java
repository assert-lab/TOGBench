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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.geometry.spherical.twod.AbstractGreatArcConnector.ConnectableGreatArc;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractGreatArcPathConnectorTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final GreatCircle XY_PLANE = GreatCircles.fromPoleAndU(
            Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

    private static final GreatCircle XZ_PLANE = GreatCircles.fromPoleAndU(
            Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_X, TEST_PRECISION);

    private final TestConnector connector = new TestConnector();

    private static void assertPathPoints(final GreatArcPath path, final Point2S... points) {
        final List<Point2S> expectedPoints = Arrays.asList(points);
        final List<Point2S> actualPoints = path.getVertices();

        final String msg = "Expected path points to equal " + expectedPoints + " but was " + actualPoints;
        Assertions.assertEquals(expectedPoints.size(), actualPoints.size(), msg);

        for (int i = 0; i < expectedPoints.size(); ++i) {
            SphericalTestUtils.assertPointsEq(expectedPoints.get(i), actualPoints.get(i), TEST_EPS);
        }
    }

    private static class TestConnector extends AbstractGreatArcConnector {

        @Override
        protected ConnectableGreatArc selectConnection(final ConnectableGreatArc incoming,
                                                       final List<ConnectableGreatArc> outgoing) {

            // just choose the first element
            return outgoing.get(0);
        }
    }


}
