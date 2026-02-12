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
package org.apache.commons.vfs2.provider.hdfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.util.RandomAccessMode;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DFSConfigKeys;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This test class uses the Hadoop MiniDFSCluster class to create an embedded Hadoop cluster.
 * <P>
 * This will only work on systems that Hadoop supports.
 */
@SuppressWarnings("resource")
public class HdfsFileProviderTest_OE25Dev {

    // Turn off the MiniDFSCluster logging
    static {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
    }

    private static final int PORT = 8620;
    private static final String HDFS_URI = "hdfs://localhost:" + PORT;
    private static final String TEST_DIR1 = HDFS_URI + "/test-dir";
    private static final Path DIR1_PATH = new Path("/test-dir");
    private static final String TEST_FILE1 = TEST_DIR1 + "/accumulo-test-1.jar";
    private static final Path FILE1_PATH = new Path(DIR1_PATH, "accumulo-test-1.jar");

    private static DefaultFileSystemManager manager;
    private static FileSystem hdfs;

    protected static Configuration conf;
    protected static MiniDFSCluster cluster;

    /**
     * Add {@code dfs.datanode.data.dir.perm} setting if OS needs it.
     * <P>
     * MiniDFSCluster will check the permissions on the data directories, but does not do a good job of setting them
     * properly. We need to get the users umask and set the appropriate Hadoop property so that the data directories
     * will be created with the correct permissions.
     * <P>
     * Will do nothing on Windows.
     */
    public static void setUmask(final Configuration conf2) {
        if (SystemUtils.IS_OS_WINDOWS) {
            return;
        }

        try {
            final Process p = Runtime.getRuntime().exec("/bin/sh -c umask");
            final BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
            final String line = bri.readLine();
            p.waitFor();
            final Short umask = Short.parseShort(line.trim(), 8);
            // Need to set permission to 777 xor umask
            // leading zero makes java interpret as base 8
            final int newPermission = 0777 ^ umask;
            conf2.set("dfs.datanode.data.dir.perm", String.format("%03o", newPermission));
        } catch (final Exception e) {
            throw new RuntimeException("Error getting umask from O/S", e);
        }
    }

    @BeforeClass
    public static void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.ERROR);

        // Put the MiniDFSCluster directory in the target directory
        final File data = new File("target/test/hdfstestdata").getAbsoluteFile();
        data.mkdirs();
        System.setProperty("test.build.data", data.toString());
        FileUtils.cleanDirectory(data);

        // Setup HDFS
        conf = new Configuration();
        conf.set(FileSystem.FS_DEFAULT_NAME_KEY, HDFS_URI);
        conf.set("hadoop.security.token.service.use_ip", "true");
        conf.setLong(DFSConfigKeys.DFS_BLOCK_SIZE_KEY, 1024 * 1024); // 1M blocksize

        setUmask(conf);

        cluster = new MiniDFSCluster(PORT, conf, 1, true, true, true, null, null, null, null);
        cluster.waitActive();

        // Set up the VFS
        manager = new DefaultFileSystemManager();
        manager.addProvider("hdfs", new HdfsFileProvider());
        manager.init();
        hdfs = cluster.getFileSystem();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (null != hdfs) {
            hdfs.close();
        }
        manager.close();
    }

    @After
    public void after() throws Exception {
        if (null != hdfs) {
            hdfs.delete(DIR1_PATH, true);
        }
    }

    private FileObject createTestFile(final FileSystem hdfs) throws IOException {
        // Create the directory
        hdfs.mkdirs(DIR1_PATH);
        final FileObject dir = manager.resolveFile(TEST_DIR1);
        Assert.assertNotNull(dir);
        Assert.assertTrue(dir.exists());
        Assert.assertEquals(dir.getType(), FileType.FOLDER);

        // Create the file in the directory
        hdfs.create(FILE1_PATH).close();
        final FileObject f = manager.resolveFile(TEST_FILE1);
        Assert.assertNotNull(f);
        Assert.assertTrue(f.exists());
        Assert.assertEquals(f.getType(), FileType.FILE);
        return f;
    }


}
