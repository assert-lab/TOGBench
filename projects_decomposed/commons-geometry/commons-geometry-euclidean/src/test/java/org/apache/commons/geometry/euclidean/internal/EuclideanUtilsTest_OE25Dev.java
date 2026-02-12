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
    void testConvexPolygonToTriangleFan_notEnoughVertices() {
        // arrange
        final String baseMsg = "Cannot create triangle fan: 3 or more vertices are required but found only ";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            EuclideanUtils.convexPolygonToTriangleFan(Collections.emptyList(), Function.identity());
        }, IllegalArgumentException.class, baseMsg + "0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            EuclideanUtils.convexPolygonToTriangleFan(Collections.singletonList(Vector3D.ZERO), Function.identity());
        }, IllegalArgumentException.class, baseMsg + "1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            EuclideanUtils.convexPolygonToTriangleFan(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)), Function.identity());
        }, IllegalArgumentException.class, baseMsg + "2");
    }


}
