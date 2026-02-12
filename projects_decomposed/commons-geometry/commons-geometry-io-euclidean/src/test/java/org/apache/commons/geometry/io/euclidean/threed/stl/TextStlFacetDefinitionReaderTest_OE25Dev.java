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
package org.apache.commons.geometry.io.euclidean.threed.stl;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.io.core.test.CloseCountReader;
import org.apache.commons.geometry.io.euclidean.EuclideanIOTestUtils;
import org.apache.commons.geometry.io.euclidean.threed.FacetDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextStlFacetDefinitionReaderTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static TextStlFacetDefinitionReader facetReader(final String content) {
        return new TextStlFacetDefinitionReader(new StringReader(content));
    }

    private static void assertParseError(final String content) {
        GeometryTestUtils.assertThrowsWithMessage(
                () -> EuclideanIOTestUtils.readAll(facetReader(content)),
                IllegalStateException.class,
                Pattern.compile("^Parsing failed.*"));
    }

    @Test
    void testParseErrors_1_oe_1_oe() {
        // act/assert
                final String content0 = "soli test\n" + "facet normal 1 2 3 " + "outer loop " + "vertex 4 5 6 " + "vertex 7 8 9 " + "vertex 10 11 12 " + "endloop " + "endfacet " + "endsolid test";
        GeometryTestUtils.assertThrowsWithMessage( () -> EuclideanIOTestUtils.readAll(facetReader(content0)), IllegalStateException.class, Pattern.compile("^Parsing failed.*"));
    }

    @Test
    void testParseErrors_2_oe_1_oe() {
        // act/assert
        // removed other assertion
                final String content0 = "solid test\n" + "facet normal 1 2 3 " + "outer loop " + "vertex abc 5 6 " + "vertex 7 8 9 " + "vertex 10 11 12 " + "endloop " + "endfacet " + "endsolid test";
        GeometryTestUtils.assertThrowsWithMessage( () -> EuclideanIOTestUtils.readAll(facetReader(content0)), IllegalStateException.class, Pattern.compile("^Parsing failed.*"));
    }

}
