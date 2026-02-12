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
package org.apache.commons.geometry.spherical;

import java.util.List;

import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.oned.AngularInterval;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.geometry.spherical.oned.RegionBSPTree1S;
import org.apache.commons.geometry.spherical.twod.GreatArcPath;
import org.apache.commons.geometry.spherical.twod.GreatCircle;
import org.apache.commons.geometry.spherical.twod.GreatCircles;
import org.apache.commons.geometry.spherical.twod.Point2S;
import org.apache.commons.geometry.spherical.twod.RegionBSPTree2S;
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
    void testGreatCircleIntersectionExample() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // create two great circles
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, precision);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, precision);

        // find the two intersection points of the great circles
        final Point2S ptA = a.intersection(b); //(pi, pi/2)
        final Point2S ptB = ptA.antipodal(); // (0, pi/2)

        // ----------------------
        SphericalTestUtils.assertPointsEq(Point2S.MINUS_I, ptA, TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.PLUS_I, ptB, TEST_EPS);
    }


}
