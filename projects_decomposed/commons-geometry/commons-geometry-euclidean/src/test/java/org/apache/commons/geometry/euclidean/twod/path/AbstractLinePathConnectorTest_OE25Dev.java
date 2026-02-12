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
package org.apache.commons.geometry.euclidean.twod.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.twod.Line;
import org.apache.commons.geometry.euclidean.twod.LineConvexSubset;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Segment;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.path.AbstractLinePathConnector.ConnectableLineSubset;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractLinePathConnectorTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Line Y_AXIS = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO,
            TEST_PRECISION);

    private final TestConnector connector = new TestConnector();

    private static List<LineConvexSubset> shuffle(final List<LineConvexSubset> segments) {
        return shuffle(segments, 1);
    }

    private static List<LineConvexSubset> shuffle(final List<LineConvexSubset> segments, final int seed) {
        Collections.shuffle(segments, new Random(seed));

        return segments;
    }

    private static void assertFinitePath(final LinePath path, final Vector2D... vertices) {
        Assertions.assertFalse(path.isInfinite());
        Assertions.assertTrue(path.isFinite());

        assertPathVertices(path, vertices);
    }

    private static void assertPathVertices(final LinePath path, final Vector2D... vertices) {
        final List<Vector2D> expectedVertices = Arrays.asList(vertices);
        final List<Vector2D> actualVertices = path.getVertexSequence();

        final String msg = "Expected path vertices to equal " + expectedVertices + " but was " + actualVertices;
        Assertions.assertEquals(expectedVertices.size(), actualVertices.size(), msg);

        for (int i = 0; i < expectedVertices.size(); ++i) {
            EuclideanTestUtils.assertCoordinatesEqual(expectedVertices.get(i), actualVertices.get(i), TEST_EPS);
        }
    }

    private static class TestConnector extends AbstractLinePathConnector {

        @Override
        protected ConnectableLineSubset selectConnection(final ConnectableLineSubset incoming, final List<ConnectableLineSubset> outgoing) {
            // just choose the first element
            return outgoing.get(0);
        }
    }


}
