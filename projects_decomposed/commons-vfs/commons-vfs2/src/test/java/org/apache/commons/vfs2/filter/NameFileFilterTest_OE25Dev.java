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
 * Test for {@link NameFileFilter}.
 */
// CHECKSTYLE:OFF Test code
public class NameFileFilterTest_OE25Dev extends BaseFilterTest {

    @Test
    public void testAcceptList_1_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(list);

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptList_2_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(list);

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptList_3_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("Test2.txt"))));
    }

    @Test
    public void testAcceptList_4_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_1_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("TEST1.txt"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_2_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_3_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("Test2.txt"))));
    }

    @Test
    public void testAcceptListIOCaseInsensitive_4_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(IOCase.INSENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_1_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(IOCase.SENSITIVE, list);

        // TEST
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("TEST1.txt"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_2_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_3_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("Test2.txt"))));
    }

    @Test
    public void testAcceptListIOCaseSensitive_4_oe() throws FileSystemException {

        // PREPARE
        final List<String> list = new ArrayList<>();
        list.add("test1.txt");
        list.add("test2.txt");
        final NameFileFilter filter = new NameFileFilter(IOCase.SENSITIVE, list);

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptString_1_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter("test1.txt");

        // TEST
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptString_2_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter("test1.txt");

        // TEST
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptString_3_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter("test1.txt");

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("Test2.txt"))));
    }

    @Test
    public void testAcceptString_4_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter("test1.txt");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_1_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter(IOCase.INSENSITIVE, "test2.txt");

        // TEST
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_2_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter(IOCase.INSENSITIVE, "test2.txt");

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_3_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter(IOCase.INSENSITIVE, "test2.txt");

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("Test2.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseInsensitive_4_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter(IOCase.INSENSITIVE, "test2.txt");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_1_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter(IOCase.SENSITIVE, "test2.txt");

        // TEST
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test1.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_2_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter(IOCase.SENSITIVE, "test2.txt");

        // TEST
        // removed other assertion
        Assert.assertTrue(filter.accept(createFileSelectInfo(new File("test2.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_3_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter(IOCase.SENSITIVE, "test2.txt");

        // TEST
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("Test2.txt"))));
    }

    @Test
    public void testAcceptStringIOCaseSensitive_4_oe() throws FileSystemException {

        // PREPARE
        final NameFileFilter filter = new NameFileFilter(IOCase.SENSITIVE, "test2.txt");

        // TEST
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(filter.accept(createFileSelectInfo(new File("test.xxx"))));
    }

}
// CHECKSTYLE:ON
