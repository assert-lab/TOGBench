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
package org.apache.commons.vfs2;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests FileExtensionSelector.
 *
 * @since 2.1
 */
public class PatternFileSelectorTest_OE25Dev {
    private static FileObject BaseFolder;

    /**
     * 9 files and 1 directory = 10
     */
    private static final int EntryCount = 10;

    private static final int ExtensionCount = 3;

    private static final int FilesPerExtensionCount = 3;

    static FileObject getBaseFolder() {
        return BaseFolder;
    }

    /**
     * Creates a RAM FS.
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        BaseFolder = VFS.getManager().resolveFile("ram://" + PatternFileSelectorTest_OE25Dev.class.getName());
        BaseFolder.deleteAll();
        BaseFolder.createFolder();
        BaseFolder.resolveFile("aa.htm").createFile();
        BaseFolder.resolveFile("aa.html").createFile();
        BaseFolder.resolveFile("aa.xhtml").createFile();
        BaseFolder.resolveFile("b.htm").createFile();
        BaseFolder.resolveFile("b.html").createFile();
        BaseFolder.resolveFile("b.xhtml").createFile();
        BaseFolder.resolveFile("c.htm").createFile();
        BaseFolder.resolveFile("c.html").createFile();
        BaseFolder.resolveFile("c.xhtml").createFile();
    }

    /**
     * Deletes RAM FS files.
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        if (BaseFolder != null) {
            BaseFolder.deleteAll();
        }
    }

    /**
     * Tests a one extension selector.
     *
     * @throws Exception
     */

    /**
     * Tests matching all
     *
     * @throws Exception
     */

    /**
     * Tests matching partial file names
     *
     * @throws Exception
     */

    /**
     * Tests matching partial file names with delimiter
     *
     * @throws Exception
     */

    /**
     * Tests a null selector.
     *
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testNullString() throws Exception {
        // Yep, this will blow up.
        new PatternFileSelector((String) null);
    }


}
