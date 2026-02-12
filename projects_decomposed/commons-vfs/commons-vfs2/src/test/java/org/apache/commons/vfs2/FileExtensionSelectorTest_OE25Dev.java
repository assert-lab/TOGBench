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

import java.util.Arrays;
import java.util.Collection;
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
public class FileExtensionSelectorTest_OE25Dev {
    private static FileObject BaseFolder;

    private static final int FileCount = 9;

    private static final int ExtensionCount = 3;

    private static final int FilesPerExtensionCount = 3;

    /**
     * Creates a RAM FS.
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        BaseFolder = VFS.getManager().resolveFile("ram://" + FileExtensionSelectorTest_OE25Dev.class.getName());
        BaseFolder.deleteAll();
        BaseFolder.createFolder();
        BaseFolder.resolveFile("a.htm").createFile();
        BaseFolder.resolveFile("a.html").createFile();
        BaseFolder.resolveFile("a.xhtml").createFile();
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
     * Tests an empty selector.
     *
     * @throws Exception
     */

    /**
     * Tests many extensions at once.
     *
     * @throws Exception
     */

    /**
     * Tests a null selector.
     *
     * @throws Exception
     */

    /**
     * Tests a null selector.
     *
     * @throws Exception
     */

    /**
     * Tests a one extension selector.
     *
     * @throws Exception
     */


}
