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
package org.apache.commons.geometry.io.euclidean.threed.obj;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.mesh.TriangleMesh;
import org.apache.commons.geometry.io.core.input.StreamGeometryInput;
import org.apache.commons.geometry.io.core.test.CloseCountInputStream;
import org.apache.commons.geometry.io.euclidean.EuclideanIOTestUtils;
import org.apache.commons.geometry.io.euclidean.threed.FacetDefinition;
import org.apache.commons.geometry.io.euclidean.threed.FacetDefinitionReader;
import org.apache.commons.geometry.io.euclidean.threed.GeometryFormat3D;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBoundaryReadHandler3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private final ObjBoundaryReadHandler3D handler = new ObjBoundaryReadHandler3D();

    @Test
    void testFacetDefinitionReader_close() {
        // arrange
        final CloseCountInputStream in = input("", StandardCharsets.UTF_8);

        // act/assert
        try (FacetDefinitionReader reader = handler.facetDefinitionReader(new StreamGeometryInput(in))) {
            Assertions.assertEquals(0, in.getCloseCount());
        }

        Assertions.assertEquals(1, in.getCloseCount());
    }

    private static CloseCountInputStream input(final String str, final Charset charset) {
        return new CloseCountInputStream(new ByteArrayInputStream(str.getBytes(charset)));
    }


}
