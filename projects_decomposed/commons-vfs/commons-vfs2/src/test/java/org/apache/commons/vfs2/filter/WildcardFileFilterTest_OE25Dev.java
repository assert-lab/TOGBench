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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.vfs2.FileSystemException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link WildcardFileFilter}.
 */
// CHECKSTYLE:OFF Test code
public class WildcardFileFilterTest_OE25Dev extends BaseFilterTest {

    @Test
    public void testAcceptList_1_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptList_2_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptList_3_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.a"))));
    }

    @Test
    public void testAcceptList_4_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ab"))));
    }

    @Test
    public void testAcceptList_5_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.abc"))));
    }

    @Test
    public void testAcceptList_6_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ABC"))));
    }

    @Test
    public void testAcceptList_7_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aaa"))));
    }

    @Test
    public void testAcceptList_8_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.Aaa"))));
    }

    @Test
    public void testAcceptList_9_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aAA"))));
    }

    @Test
    public void testAcceptList_10_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.abcd"))));
    }

    @Test
    public void testAcceptList_11_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_1_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_2_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_3_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.a"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_4_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ab"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_5_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.abc"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_6_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.ABC"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_7_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aaa"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_8_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.Aaa"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_9_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aAA"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_10_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.abcd"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_11_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_1_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_2_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_3_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.a"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_4_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ab"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_5_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.abc"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_6_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ABC"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_7_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aaa"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_8_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.Aaa"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_9_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aAA"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_10_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.abcd"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_11_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("*.txt");
        list.add("*.a??");
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptString_1_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptString_2_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptString_3_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.a"))));
    }

    @Test
    public void testAcceptString_4_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ab"))));
    }

    @Test
    public void testAcceptString_5_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.abc"))));
    }

    @Test
    public void testAcceptString_6_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ABC"))));
    }

    @Test
    public void testAcceptString_7_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aaa"))));
    }

    @Test
    public void testAcceptString_8_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.Aaa"))));
    }

    @Test
    public void testAcceptString_9_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aAA"))));
    }

    @Test
    public void testAcceptString_10_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.abcd"))));
    }

    @Test
    public void testAcceptString_11_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter("*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_1_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_2_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_3_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.a"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_4_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ab"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_5_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.abc"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_6_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.ABC"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_7_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aaa"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_8_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.Aaa"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_9_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aAA"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_10_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.abcd"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_11_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.INSENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_1_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_2_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_3_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.a"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_4_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ab"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_5_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.abc"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_6_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.ABC"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_7_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aaa"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_8_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.Aaa"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_9_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test.aAA"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_10_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.abcd"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_11_oe() throws FileSystemException {

        // PREPARE
        final WildcardFileFilter filter = new WildcardFileFilter(IOCase.SENSITIVE, "*.txt", "*.a??");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

}
// CHECKSTYLE:ON
