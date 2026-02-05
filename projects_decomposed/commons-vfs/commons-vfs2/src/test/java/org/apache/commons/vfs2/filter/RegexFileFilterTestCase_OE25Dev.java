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
package org.apache.commons.vfs2.filter;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.commons.vfs2.FileFilter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link RegexFileFilter}.
 */
// CHECKSTYLE:OFF Test code
public class RegexFileFilterTestCase_OE25Dev extends BaseFilterTest {

@Test
    public void testPatternNullArgConstruction_2_oe() {
        try {
            new RegexFileFilter((Pattern) null);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals(RegexFileFilter.PATTERN_IS_MISSING, ex.getMessage());
    }
    }

@Test
    public void testRegex_1_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("Test.java"))));
    }

@Test
    public void testRegex_2_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test-10.java"))));
    }

@Test
    public void testRegex_3_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test-.java"))));
    }

@Test
    public void testRegex_4_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("Test.java"))));
    }

@Test
    public void testRegex_5_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.java"))));
    }

@Test
    public void testRegex_6_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("tEST.java"))));
    }

@Test
    public void testRegex_7_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("Test.java"))));
    }

@Test
    public void testRegex_8_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.java"))));
    }

@Test
    public void testRegex_9_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("tEST.java"))));
    }

@Test
    public void testRegex_10_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", Pattern.CASE_INSENSITIVE);
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("Test.java"))));
    }

@Test
    public void testRegex_11_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", Pattern.CASE_INSENSITIVE);
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.java"))));
    }

@Test
    public void testRegex_12_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", Pattern.CASE_INSENSITIVE);
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("tEST.java"))));
    }

@Test
    public void testRegex_13_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", Pattern.CASE_INSENSITIVE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", IOCase.INSENSITIVE);
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("Test.java"))));
    }

@Test
    public void testRegex_14_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", Pattern.CASE_INSENSITIVE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", IOCase.INSENSITIVE);
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.java"))));
    }

@Test
    public void testRegex_15_oe() throws Exception {

        FileFilter filter;

        filter = new RegexFileFilter("^.*[tT]est(-\\d+)?\\.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^[Tt]est.java$");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter(Pattern.compile("^test.java$", Pattern.CASE_INSENSITIVE));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", Pattern.CASE_INSENSITIVE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        filter = new RegexFileFilter("^test.java$", IOCase.INSENSITIVE);
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("tEST.java"))));
    }

@Test
    public void testStringIOCaseNullArgConstruction_2_oe() {
        try {
            new RegexFileFilter((String) null, IOCase.INSENSITIVE);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals(RegexFileFilter.PATTERN_IS_MISSING, ex.getMessage());
    }
    }

@Test
    public void testStringNullArgConstruction_2_oe() {
        try {
            new RegexFileFilter((String) null);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals(RegexFileFilter.PATTERN_IS_MISSING, ex.getMessage());
    }
    }

@Test
    public void testStringPatternNullArgConstruction_2_oe() {
        try {
            new RegexFileFilter((String) null, Pattern.CASE_INSENSITIVE);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals(RegexFileFilter.PATTERN_IS_MISSING, ex.getMessage());
    }
    }

}
// CHECKSTYLE:ON
