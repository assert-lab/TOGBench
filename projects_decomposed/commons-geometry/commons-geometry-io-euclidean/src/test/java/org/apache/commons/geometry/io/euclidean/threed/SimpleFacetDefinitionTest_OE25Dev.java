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
package org.apache.commons.geometry.io.euclidean.threed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleFacetDefinitionTest_OE25Dev {

    private static final List<Vector3D> FACET_PTS = Arrays.asList(
            Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 0), Vector3D.of(0, 1, 0));

    @Test
    void testCtor_invalidArgs() {
        // arrange
        final Vector3D normal = Vector3D.ZERO;
        final List<Vector3D> invalid = Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X);

        final String verticesNull = "Facet vertex list cannot be null";
        final String vertexCountMsg = "Facet vertex list must contain at least 3 points; found 2";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
                () -> new SimpleFacetDefinition(null),
                NullPointerException.class, verticesNull);

        GeometryTestUtils.assertThrowsWithMessage(
                () -> new SimpleFacetDefinition(null, normal),
                NullPointerException.class, verticesNull);

        GeometryTestUtils.assertThrowsWithMessage(
                () -> new SimpleFacetDefinition(invalid),
                IllegalArgumentException.class, vertexCountMsg);

        GeometryTestUtils.assertThrowsWithMessage(
                () -> new SimpleFacetDefinition(invalid, normal),
                IllegalArgumentException.class, vertexCountMsg);
    }

    @Test
    void testToString() {
        // arrange
        final SimpleFacetDefinition f = new SimpleFacetDefinition(FACET_PTS, Vector3D.Unit.PLUS_Z);

        // act
        final String str = f.toString();

        // assert
        GeometryTestUtils.assertContains("SimpleFacetDefinition[vertices= [(0", str);
        GeometryTestUtils.assertContains(", normal= (0", str);
    }

@Test
    void testProperties_verticesOnly_1_oe() {
        // act
        final SimpleFacetDefinition f = new SimpleFacetDefinition(new ArrayList<>(FACET_PTS));

        // assert
        Assertions.assertEquals(FACET_PTS, f.getVertices());
    }

@Test
    void testProperties_verticesOnly_2_oe() {
        // act
        final SimpleFacetDefinition f = new SimpleFacetDefinition(new ArrayList<>(FACET_PTS));

        // assert
        // removed other assertion
        Assertions.assertNotSame(FACET_PTS, f.getVertices());
    }

@Test
    void testProperties_verticesOnly_3_oe() {
        // act
        final SimpleFacetDefinition f = new SimpleFacetDefinition(new ArrayList<>(FACET_PTS));

        // assert
        // removed other assertion
        // removed other assertion

        final List<Vector3D> vertices = f.getVertices();
        final Vector3D toAdd = FACET_PTS.get(0);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> vertices.add(toAdd));
    }

@Test
    void testProperties_verticesOnly_4_oe() {
        // act
        final SimpleFacetDefinition f = new SimpleFacetDefinition(new ArrayList<>(FACET_PTS));

        // assert
        // removed other assertion
        // removed other assertion

        final List<Vector3D> vertices = f.getVertices();
        final Vector3D toAdd = FACET_PTS.get(0);
        // removed other assertion

        Assertions.assertNull(f.getNormal());
    }

@Test
    void testProperties_verticesAndNormal_1_oe() {
        // arrange
        final Vector3D normal = Vector3D.ZERO; // invalid normal is accepted

        // act
        final SimpleFacetDefinition f = new SimpleFacetDefinition(new ArrayList<>(FACET_PTS), normal);

        // assert
        Assertions.assertEquals(FACET_PTS, f.getVertices());
    }

@Test
    void testProperties_verticesAndNormal_2_oe() {
        // arrange
        final Vector3D normal = Vector3D.ZERO; // invalid normal is accepted

        // act
        final SimpleFacetDefinition f = new SimpleFacetDefinition(new ArrayList<>(FACET_PTS), normal);

        // assert
        // removed other assertion
        Assertions.assertNotSame(FACET_PTS, f.getVertices());
    }

@Test
    void testProperties_verticesAndNormal_3_oe() {
        // arrange
        final Vector3D normal = Vector3D.ZERO; // invalid normal is accepted

        // act
        final SimpleFacetDefinition f = new SimpleFacetDefinition(new ArrayList<>(FACET_PTS), normal);

        // assert
        // removed other assertion
        // removed other assertion

        final List<Vector3D> vertices = f.getVertices();
        final Vector3D toAdd = FACET_PTS.get(0);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> vertices.add(toAdd));
    }

@Test
    void testProperties_verticesAndNormal_4_oe() {
        // arrange
        final Vector3D normal = Vector3D.ZERO; // invalid normal is accepted

        // act
        final SimpleFacetDefinition f = new SimpleFacetDefinition(new ArrayList<>(FACET_PTS), normal);

        // assert
        // removed other assertion
        // removed other assertion

        final List<Vector3D> vertices = f.getVertices();
        final Vector3D toAdd = FACET_PTS.get(0);
        // removed other assertion

        Assertions.assertSame(normal, f.getNormal());
    }

}
