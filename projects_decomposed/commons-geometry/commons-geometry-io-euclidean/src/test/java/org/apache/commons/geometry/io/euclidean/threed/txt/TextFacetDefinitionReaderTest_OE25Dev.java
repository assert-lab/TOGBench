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
package org.apache.commons.geometry.io.euclidean.threed.txt;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.io.core.test.CloseCountReader;
import org.apache.commons.geometry.io.euclidean.EuclideanIOTestUtils;
import org.apache.commons.geometry.io.euclidean.threed.FacetDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextFacetDefinitionReaderTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    @Test
    void testSetCommentToken_invalidArgs() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("");
        String baseMsg = "Comment token cannot contain whitespace; was [";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.setCommentToken(" ");
        }, IllegalArgumentException.class, baseMsg + " ]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.setCommentToken("a\tb");
        }, IllegalArgumentException.class, baseMsg + "a\tb]");
    }

    @Test
    void testReadFacet_emptyCommentToken() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("# line comment\n");
        reader.setCommentToken("");

        // act
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.readFacet();
        }, IllegalStateException.class,
                "Parsing failed at line 1, column 1: expected double but found empty token followed by [#]");
    }

    @Test
    void testReadFacet_nullCommentToken() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("# line comment\n");
        reader.setCommentToken(null);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.readFacet();
        }, IllegalStateException.class,
                "Parsing failed at line 1, column 1: expected double but found empty token followed by [#]");
    }

    @Test
    void testReadFacet_invalidTokens() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("1 abc 3 ; 4 5 6 ; 7 8 9");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.readFacet();
        }, IllegalStateException.class,
                "Parsing failed at line 1, column 3: expected double but found [abc]");
    }

    @Test
    void testReadFacet_notEnoughVectors() {
        // arrange
        TextFacetDefinitionReader reader = facetReader(
                "1\n" +
                "1 2\n" +
                "1 2 3\n" +
                "1 2 3 ; 4 5 6;\n");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.readFacet();
        }, IllegalStateException.class,
                "Parsing failed at line 1, column 2: expected double but found end of line");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.readFacet();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 4: expected double but found end of line");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.readFacet();
        }, IllegalStateException.class,
                "Parsing failed at line 3, column 6: expected double but found end of line");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            reader.readFacet();
        }, IllegalStateException.class,
                "Parsing failed at line 4, column 15: expected double but found end of line");
    }

    private static TextFacetDefinitionReader facetReader(final String content) {
        return new TextFacetDefinitionReader(new StringReader(content));
    }


}
