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

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.io.core.test.CloseCountReader;
import org.apache.commons.geometry.io.euclidean.EuclideanIOTestUtils;
import org.apache.commons.geometry.io.euclidean.threed.FacetDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjFacetDefinitionReaderTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    @Test
    void testReadFacet_failOnNonPolygon() {
        // arrange
        final ObjFacetDefinitionReader reader = reader(
                "o test\n\n" +
                "v 0 0 0\r\n" +
                "v 1 0 0\n" +
                "v 1 1 0\r" +
                "v 0 1 0\n" +
                "vn 0 0 1\n" +
                "f 1//1 2//1 3//1\n" +
                "curv non-polygon data\n");

        reader.setFailOnNonPolygonKeywords(true);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
                () -> EuclideanIOTestUtils.readAll(reader),
                IllegalStateException.class, Pattern.compile("^Parsing failed.*"));
    }

    private static ObjFacetDefinitionReader reader(final String str) {
        return new ObjFacetDefinitionReader(new StringReader(str));
    }

    @Test
    void testDefaults_1_oe() {
        // arrange
        final ObjFacetDefinitionReader reader = reader("");

        // act/assert
        Assertions.assertFalse(reader.isFailOnNonPolygonKeywords());
    }

    @Test
    void testClose_1_oe() {
        // arrange
        final CloseCountReader closeReader = new CloseCountReader(new StringReader(""));

        // act/assert
        try (ObjFacetDefinitionReader reader = new ObjFacetDefinitionReader(closeReader)) {
            Assertions.assertEquals(0, closeReader.getCloseCount());
    }
    }

    @Test
    void testClose_2_oe() {
        // arrange
        final CloseCountReader closeReader = new CloseCountReader(new StringReader(""));

        // act/assert
        try (ObjFacetDefinitionReader reader = new ObjFacetDefinitionReader(closeReader)) {
            // removed other assertion
        }

        Assertions.assertEquals(1, closeReader.getCloseCount());
    }

    @Test
    void testReadFacet_withNormal_1_oe() {
        // arrange
        final ObjFacetDefinitionReader reader = reader(
                "o test\n\n" +
                "v 0 0 0\r\n" +
                "v 1 0 0\n" +
                "v 1 1 0\r" +
                "v 0 1 0\n" +
                "vn 0 0 -1\n" +
                "f 1//1 2//1 3//1 4//1\n" +
                "curv non-polygon data\n");

        // act
        final List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        Assertions.assertEquals(1, facets.size());
    }

    @Test
    void testReadFacet_withoutNormal_1_oe() {
        // arrange
        final ObjFacetDefinitionReader reader = reader(
                "o test\n\n" +
                "v 0 0 0\r\n" +
                "v 1 0 0\n" +
                "v 1 1 0\r" +
                "f 1 2 3\n");

        // act
        final List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        Assertions.assertEquals(1, facets.size());
    }

    @Test
    void testReadFacet_withoutNormal_2_oe() {
        // arrange
        final ObjFacetDefinitionReader reader = reader(
                "o test\n\n" +
                "v 0 0 0\r\n" +
                "v 1 0 0\n" +
                "v 1 1 0\r" +
                "f 1 2 3\n");

        // act
        final List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        // removed other assertion
        EuclideanIOTestUtils.assertFacetVertices(facets.get(0), Arrays.asList(
                    Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 0)
                ), TEST_EPS);
        Assertions.assertNull(facets.get(0).getNormal());
    }

}
