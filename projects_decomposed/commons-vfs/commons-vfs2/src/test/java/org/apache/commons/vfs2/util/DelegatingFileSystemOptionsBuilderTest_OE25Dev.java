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
package org.apache.commons.vfs2.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.http.HttpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.TrustEveryoneUserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Some tests for the DelegatingFileSystemOptionsBuilder
 */
public class DelegatingFileSystemOptionsBuilderTest_OE25Dev {
    private static final String[] schemes = new String[] { "http", "ftp", "file", "zip", "tar", "tgz", "bz2", "gz",
            "jar", "tmp", "ram" };

    private StandardFileSystemManager fsm;

    @Before
    public void setUp() throws Exception {

        // get a full blown, fully functional manager
        fsm = new StandardFileSystemManager();
        fsm.init();
    }

    @After
    public void tearDown() throws Exception {
        if (fsm != null) {
            fsm.close();
        }
    }


}
