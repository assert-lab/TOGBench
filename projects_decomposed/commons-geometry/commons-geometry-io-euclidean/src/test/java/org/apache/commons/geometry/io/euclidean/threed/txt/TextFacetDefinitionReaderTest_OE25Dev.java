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

    private static TextFacetDefinitionReader facetReader(final String content) {
        return new TextFacetDefinitionReader(new StringReader(content));
    }

    @Test
    void testPropertyDefaults_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("");

        // act/assert
        Assertions.assertEquals("#", reader.getCommentToken());
    }

    @Test
    void testReadFacet_empty_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("");

        // act
        List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        Assertions.assertEquals(0, facets.size());
    }

    @Test
    void testReadFacet_singleFacet_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader(
                "1.0 2.0 3.0 40 50 60 7.0e-2 8e-2 9E-02 1.01e+1 -11.02 +12");

        // act
        List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        Assertions.assertEquals(1, facets.size());
    }

    @Test
    void testReadFacet_multipleFacets_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader(
                "1,2,3    4,5,6 7,8,9    10,11,12\r" +
                "1 1 1;2 2 2;3 3 3;4 4 4;5 5 5\r\n" +
                "6 6 6 6 6 6 6 6 6");

        // act
        List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        Assertions.assertEquals(3, facets.size());
    }

    @Test
    void testReadFacet_blankLinesAndComments_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader(
                "# some ignored numbers: 1 2 3 4 5 6\n" +
                "\n" +
                " \n" +
                "1 2 3 4 5 6 7 8 9 # end of line comment\n" +
                "1 1 1 2 2 2 3 3 3\n" +
                "\t\n" +
                "#line comment\n" +
                "5 5 5 5 5 5 5 5 5\n\n  \n");

        // act
        List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        Assertions.assertEquals(3, facets.size());
    }

    @Test
    void testReadFacet_nonDefaultCommentToken_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader(
                "5$ some ignored numbers: 1 2 3 4 5 6\n" +
                "\n" +
                " \n" +
                "1 2 3 4 5 6 7 8 9 5$ end of line comment\n" +
                "1 1 1 2 2 2 3 3 3\n" +
                "\t\n" +
                "5$line comment\n" +
                "5 5 5 5 5 5 5 5 5\n");

        reader.setCommentToken("5$");

        // act
        List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        Assertions.assertEquals(3, facets.size());
    }

    @Test
    void testReadFacet_longCommentToken_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader(
                "this_is-a-comment some ignored numbers: 1 2 3 4 5 6\n" +
                "\n" +
                " \n" +
                "1 2 3 4 5 6 7 8 9 this_is-a-comment end of line comment\n" +
                "1 1 1 2 2 2 3 3 3\n" +
                "\t\n" +
                "this_is-a-commentline comment\n" +
                "5 5 5 5 5 5 5 5 5\n");

        reader.setCommentToken("this_is-a-comment");

        // act
        List<FacetDefinition> facets = EuclideanIOTestUtils.readAll(reader);

        // assert
        Assertions.assertEquals(3, facets.size());
    }

    @Test
    void testClose_1_oe() {
        // arrange
        final CloseCountReader countReader = new CloseCountReader(new StringReader(""));
        final TextFacetDefinitionReader reader = new TextFacetDefinitionReader(countReader);

        // act
        reader.close();

        // assert
        Assertions.assertEquals(1, countReader.getCloseCount());
    }

@Test
    void testSetCommentToken_invalidArgs_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("");
        String baseMsg = "Comment token cannot contain whitespace; was [";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> { reader.setCommentToken(" "); }, IllegalArgumentException.class, baseMsg + " ]");
    }

@Test
    void testSetCommentToken_invalidArgs_2_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("");
        String baseMsg = "Comment token cannot contain whitespace; was [";

        // act/assert
        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> { reader.setCommentToken("a\tb"); }, IllegalArgumentException.class, baseMsg + "a\tb]");
    }

@Test
    void testReadFacet_emptyCommentToken_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("# line comment\n");
        reader.setCommentToken("");

        // act
        GeometryTestUtils.assertThrowsWithMessage(() -> { reader.readFacet(); }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected double but found empty token followed by [#]");
    }

@Test
    void testReadFacet_nullCommentToken_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("# line comment\n");
        reader.setCommentToken(null);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> { reader.readFacet(); }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected double but found empty token followed by [#]");
    }

@Test
    void testReadFacet_invalidTokens_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader("1 abc 3 ; 4 5 6 ; 7 8 9");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> { reader.readFacet(); }, IllegalStateException.class, "Parsing failed at line 1, column 3: expected double but found [abc]");
    }

@Test
    void testReadFacet_notEnoughVectors_1_oe() {
        // arrange
        TextFacetDefinitionReader reader = facetReader(
                "1\n" +
                "1 2\n" +
                "1 2 3\n" +
                "1 2 3 ; 4 5 6;\n");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> { reader.readFacet(); }, IllegalStateException.class, "Parsing failed at line 1, column 2: expected double but found end of line");
    }

}
