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
package org.apache.commons.vfs2.provider;

import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileType;
import org.junit.Assert;
import org.junit.Test;

public class AbstractFileNameTest_OE25Dev {

@Test
    public void testHashSignEncoded_1_oe() {
        final AbstractFileName fileName = new AbstractFileName("file", "/foo/bar/file#name.txt", FileType.FILE) {
            @Override
            protected void appendRootUri(final StringBuilder buffer, final boolean addPassword) {
                if (addPassword) {
                    buffer.append("pass");
                }
            }

            @Override
            public FileName createName(final String absolutePath, final FileType fileType) {
                return null;
            }
        };

        Assert.assertEquals("pass/foo/bar/file%23name.txt", fileName.getURI());
    }

@Test
    public void testHashSignEncoded_2_oe() {
        final AbstractFileName fileName = new AbstractFileName("file", "/foo/bar/file#name.txt", FileType.FILE) {
            @Override
            protected void appendRootUri(final StringBuilder buffer, final boolean addPassword) {
                if (addPassword) {
                    buffer.append("pass");
                }
            }

            @Override
            public FileName createName(final String absolutePath, final FileType fileType) {
                return null;
            }
        };

        // removed other assertion
        Assert.assertEquals("/foo/bar/file%23name.txt", fileName.getFriendlyURI());
    }

}
