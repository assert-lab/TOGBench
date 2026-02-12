package org.apache.commons.jcs3.auxiliary.disk.block;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.jcs3.utils.serialization.StandardSerializer;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import junit.framework.TestCase;

/**
 * Test for the disk access layer of the Block Disk Cache.
 * <p>
 * @author Aaron Smuts
 */
public class BlockDiskUnitTest_OE25Dev
    extends TestCase
{
    /** data file. */
    private File rafDir;
    private BlockDisk disk;

    /**
     * @see junit.framework.TestCase#setUp()
     * Creates the base directory
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        final String rootDirName = "target/test-sandbox/block";
        this.rafDir = new File( rootDirName );
        this.rafDir.mkdirs();
    }

    private void setUpBlockDisk(final String fileName) throws IOException
    {
        final File file = new File(rafDir, fileName + ".data");
        file.delete();
        this.disk = new BlockDisk(file, new StandardSerializer());
    }

    private void setUpBlockDisk(final String fileName, final int blockSize) throws IOException
    {
        final File file = new File(rafDir, fileName + ".data");
        file.delete();
        this.disk = new BlockDisk(file, blockSize, new StandardSerializer());
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception
    {
        disk.close();
        super.tearDown();
    }

    /**
     * Test writing a null object within a single block size.
     * <p>
     * @throws Exception
     */

    /**
     * Test writing an element within a single block size.
     * <p>
     * @throws Exception
     */

    /**
     * Test writing and reading an element within a single block size.
     * <p>
     * @throws Exception
     */

    /**
     * Test writing two elements that each fit within a single block size.
     * <p>
     * @throws Exception
     */

    /**
     * Verify that it says we need two blocks if the total size will fit.
     * <p>
     * @throws Exception
     */

    /**
     * Test writing an element that takes two blocks.
     * <p>
     * @throws Exception
     */

    /**
     * Test writing an element that takes 128 blocks.  There was a byte in a for loop that limited the number to 127.  I fixed this.
     * <p>
     * @throws Exception
     */

    /**
     * Test writing and reading elements that do not fit within a single block.
     * <p>
     * @throws Exception
     */

    /**
     * Test writing and reading elements that do not fit within a single block.
     * <p>
     * @throws Exception
     */

    /**
     * Used to get the size for byte arrays that will take up the number of blocks specified.
     * <p>
     * @param blockSize
     * @param numBlocks
     * @return num bytes.
     */
    private int getBytesForBlocksOfByteArrays( final int blockSize, final int numBlocks )
    {
        // byte arrays encur some bytes of serialization overhead.
        return blockSize * numBlocks - ( numBlocks * BlockDisk.HEADER_SIZE_BYTES ) - ( numBlocks * 14 );
    }

    /**
     * Verify that the block disk can handle a big string.
     * <p>
     * @throws Exception
     */

    /**
     * Verify that the block disk can handle a big string.
     * <p>
     * @throws Exception
     */


}
