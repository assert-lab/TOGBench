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

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatricesTest_OE25Dev {

    private static final double EPS = 1e-12;

    @Test
    void testCheckDeterminantForInverse_invalid() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Matrices.checkDeterminantForInverse(0);
        }, IllegalStateException.class, "Matrix is not invertible; matrix determinant is 0.0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Matrices.checkDeterminantForInverse(Double.NaN);
        }, IllegalStateException.class, "Matrix is not invertible; matrix determinant is NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Matrices.checkDeterminantForInverse(Double.POSITIVE_INFINITY);
        }, IllegalStateException.class, "Matrix is not invertible; matrix determinant is Infinity");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Matrices.checkDeterminantForInverse(Double.NEGATIVE_INFINITY);
        }, IllegalStateException.class, "Matrix is not invertible; matrix determinant is -Infinity");
    }

    @Test
    void testCheckElementForInverse_invalid() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Matrices.checkElementForInverse(Double.NaN);
        }, IllegalStateException.class, "Matrix is not invertible; invalid matrix element: NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Matrices.checkElementForInverse(Double.POSITIVE_INFINITY);
        }, IllegalStateException.class, "Matrix is not invertible; invalid matrix element: Infinity");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Matrices.checkElementForInverse(Double.NEGATIVE_INFINITY);
        }, IllegalStateException.class, "Matrix is not invertible; invalid matrix element: -Infinity");
    }


}
