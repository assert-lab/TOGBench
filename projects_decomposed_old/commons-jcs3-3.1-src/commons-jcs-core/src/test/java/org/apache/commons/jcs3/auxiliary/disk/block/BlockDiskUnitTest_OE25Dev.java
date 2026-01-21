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
        String rootDirName = "target/test-sandbox/block";
        this.rafDir = new File( rootDirName );
        this.rafDir.mkdirs();
    }

    private void setUpBlockDisk(String fileName) throws IOException
    {
        File file = new File(rafDir, fileName + ".data");
        file.delete();
        this.disk = new BlockDisk(file, new StandardSerializer());
    }

    private void setUpBlockDisk(String fileName, int blockSize) throws IOException
    {
        File file = new File(rafDir, fileName + ".data");
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
    private int getBytesForBlocksOfByteArrays( int blockSize, int numBlocks )
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

    public void testWrite_NullBlockElement_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_NullBlockElement");

        // DO WORK
        int[] blocks = disk.write( null );

        // VERIFY
        assertEquals( "Wrong number of blocks recorded.", 1, disk.getNumberOfBlocks() );
    }

    public void testWrite_NullBlockElement_2_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_NullBlockElement");

        // DO WORK
        int[] blocks = disk.write( null );

        // VERIFY
        // removed other assertion
        assertEquals( "Wrong number of blocks returned.", 1, blocks.length );
    }

    public void testWrite_NullBlockElement_3_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_NullBlockElement");

        // DO WORK
        int[] blocks = disk.write( null );

        // VERIFY
        // removed other assertion
        // removed other assertion
        assertEquals( "Wrong block returned.", 0, blocks[0] );
    }

    public void testWrite_SingleBlockElement_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_SingleBlockElement");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        assertEquals( "Wrong number of blocks recorded.", 1, disk.getNumberOfBlocks() );
    }

    public void testWrite_SingleBlockElement_2_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_SingleBlockElement");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        assertEquals( "Wrong number of blocks returned.", 1, blocks.length );
    }

    public void testWrite_SingleBlockElement_3_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_SingleBlockElement");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        // removed other assertion
        assertEquals( "Wrong block returned.", 0, blocks[0] );
    }

    public void testWriteAndRead_SingleBlockElement_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndRead_SingleBlockElement");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks = disk.write( new byte[bytes] );

        byte[] result = (byte[]) disk.read( blocks );

        // VERIFY
        assertEquals( "Wrong item retured.", new byte[bytes].length, result.length );
    }

    public void testWrite_TwoSingleBlockElements_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_TwoSingleBlockElements");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks1 = disk.write( new byte[bytes] );
        int[] blocks2 = disk.write( new byte[bytes] );

        // VERIFY
        assertEquals( "Wrong number of blocks recorded.", 2, disk.getNumberOfBlocks() );
    }

    public void testWrite_TwoSingleBlockElements_2_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_TwoSingleBlockElements");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks1 = disk.write( new byte[bytes] );
        int[] blocks2 = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        assertEquals( "Wrong number of blocks returned.", 1, blocks1.length );
    }

    public void testWrite_TwoSingleBlockElements_3_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_TwoSingleBlockElements");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks1 = disk.write( new byte[bytes] );
        int[] blocks2 = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        // removed other assertion
        assertEquals( "Wrong block returned.", 0, blocks1[0] );
    }

    public void testWrite_TwoSingleBlockElements_4_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_TwoSingleBlockElements");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks1 = disk.write( new byte[bytes] );
        int[] blocks2 = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( "Wrong number of blocks returned.", 1, blocks2.length );
    }

    public void testWrite_TwoSingleBlockElements_5_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWrite_TwoSingleBlockElements");

        // DO WORK
        int bytes = 1 * 1024;
        int[] blocks1 = disk.write( new byte[bytes] );
        int[] blocks2 = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( "Wrong block returned.", 1, blocks2[0] );
    }

    public void testCalculateBlocksNeededDouble_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testCalculateBlocksNeededDouble");

        // DO WORK
        int result = disk.calculateTheNumberOfBlocksNeeded( new byte[disk.getBlockSizeBytes() * 2
            - ( 2 * BlockDisk.HEADER_SIZE_BYTES )] );

        // Verify
        assertEquals( "Wrong number of blocks", 2, result );
    }

    public void testWrite_DoubleBlockElement_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteDoubleBlockElement");

        // DO WORK
        // byte arrays encur 27 bytes of serialization overhead.
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), 2 );
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        assertEquals( "Wrong number of blocks recorded.", 2, disk.getNumberOfBlocks() );
    }

    public void testWrite_DoubleBlockElement_2_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteDoubleBlockElement");

        // DO WORK
        // byte arrays encur 27 bytes of serialization overhead.
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), 2 );
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        assertEquals( "Wrong number of blocks returned.", 2, blocks.length );
    }

    public void testWrite_DoubleBlockElement_3_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteDoubleBlockElement");

        // DO WORK
        // byte arrays encur 27 bytes of serialization overhead.
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), 2 );
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        // removed other assertion
        assertEquals( "Wrong block returned.", 0, blocks[0] );
    }

    public void testWrite_128BlockElement_1_oe()
        throws Exception
    {
        // SETUP
        int numBlocks = 128;

        setUpBlockDisk("testWrite_128BlockElement");

        // DO WORK
        // byte arrays encur 27 bytes of serialization overhead.
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocks );
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        assertEquals( "Wrong number of blocks recorded.", numBlocks, disk.getNumberOfBlocks() );
    }

    public void testWrite_128BlockElement_2_oe()
        throws Exception
    {
        // SETUP
        int numBlocks = 128;

        setUpBlockDisk("testWrite_128BlockElement");

        // DO WORK
        // byte arrays encur 27 bytes of serialization overhead.
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocks );
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        assertEquals( "Wrong number of blocks returned.", numBlocks, blocks.length );
    }

    public void testWrite_128BlockElement_3_oe()
        throws Exception
    {
        // SETUP
        int numBlocks = 128;

        setUpBlockDisk("testWrite_128BlockElement");

        // DO WORK
        // byte arrays encur 27 bytes of serialization overhead.
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocks );
        int[] blocks = disk.write( new byte[bytes] );

        // VERIFY
        // removed other assertion
        // removed other assertion
        assertEquals( "Wrong block returned.", 0, blocks[0] );
    }

    public void testWriteAndReadMultipleMultiBlockElement_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndReadSingleBlockElement");

        // DO WORK
        int numBlocksPerElement = 4;
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocksPerElement );

        int numElements = 100;
        for ( int i = 0; i < numElements; i++ )
        {
            int[] blocks = disk.write( new byte[bytes] );
            byte[] result = (byte[]) disk.read( blocks );

            // VERIFY
            assertEquals( "Wrong item retured.", new byte[bytes].length, result.length );
    }
    }

    public void testWriteAndReadMultipleMultiBlockElement_2_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndReadSingleBlockElement");

        // DO WORK
        int numBlocksPerElement = 4;
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocksPerElement );

        int numElements = 100;
        for ( int i = 0; i < numElements; i++ )
        {
            int[] blocks = disk.write( new byte[bytes] );
            byte[] result = (byte[]) disk.read( blocks );

            // VERIFY
            // removed other assertion
            assertEquals( "Wrong number of blocks returned.", numBlocksPerElement, blocks.length );
    }
    }

    public void testWriteAndReadMultipleMultiBlockElement_setSize_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndReadSingleBlockElement", 1024);

        // DO WORK
        int numBlocksPerElement = 4;
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocksPerElement );

        int numElements = 100;
        Random r = new Random(System.currentTimeMillis());
        final byte[] src = new byte[bytes];
        for ( int i = 0; i < numElements; i++ )
        {
            r.nextBytes(src);  // Ensure we don't just write zeros out
            int[] blocks = disk.write( src );
            byte[] result = (byte[]) disk.read( blocks );

            // VERIFY
            assertEquals( "Wrong item length retured.", src.length, result.length );
    }
    }

    public void testWriteAndReadMultipleMultiBlockElement_setSize_2_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndReadSingleBlockElement", 1024);

        // DO WORK
        int numBlocksPerElement = 4;
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocksPerElement );

        int numElements = 100;
        Random r = new Random(System.currentTimeMillis());
        final byte[] src = new byte[bytes];
        for ( int i = 0; i < numElements; i++ )
        {
            r.nextBytes(src);  // Ensure we don't just write zeros out
            int[] blocks = disk.write( src );
            byte[] result = (byte[]) disk.read( blocks );

            // VERIFY
            // removed other assertion
            assertEquals( "Wrong number of blocks returned.", numBlocksPerElement, blocks.length );
    }
    }

    public void testWriteAndReadMultipleMultiBlockElement_setSize_3_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndReadSingleBlockElement", 1024);

        // DO WORK
        int numBlocksPerElement = 4;
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocksPerElement );

        int numElements = 100;
        Random r = new Random(System.currentTimeMillis());
        final byte[] src = new byte[bytes];
        for ( int i = 0; i < numElements; i++ )
        {
            r.nextBytes(src);  // Ensure we don't just write zeros out
            int[] blocks = disk.write( src );
            byte[] result = (byte[]) disk.read( blocks );

            // VERIFY
            // removed other assertion
            // removed other assertion

            // We check the array contents, too, to ensure we read back what we wrote out
            for (int j = 0 ; j < src.length ; j++) {
                assertEquals( "Mismatch at offset " + j + " in attempt # " + (i + 1), src[j], result[j] );
    }
    }
    }

    public void testWriteAndReadMultipleMultiBlockElement_setSize_4_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndReadSingleBlockElement", 1024);

        // DO WORK
        int numBlocksPerElement = 4;
        int bytes = getBytesForBlocksOfByteArrays( disk.getBlockSizeBytes(), numBlocksPerElement );

        int numElements = 100;
        Random r = new Random(System.currentTimeMillis());
        final byte[] src = new byte[bytes];
        for ( int i = 0; i < numElements; i++ )
        {
            r.nextBytes(src);  // Ensure we don't just write zeros out
            int[] blocks = disk.write( src );
            byte[] result = (byte[]) disk.read( blocks );

            // VERIFY
            // removed other assertion
            // removed other assertion

            // We check the array contents, too, to ensure we read back what we wrote out
            for (int j = 0 ; j < src.length ; j++) {
                // removed other assertion
            }
        }
        assertEquals( "Wrong number of elements. "+disk, numBlocksPerElement * numElements, disk.getNumberOfBlocks() );
    }

    public void testWriteAndRead_BigString_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndRead_BigString", 4096); //1024

        String string = "This is my big string ABCDEFGH";
        StringBuilder sb = new StringBuilder();
        sb.append( string );
        for ( int i = 0; i < 8; i++ )
        {
            sb.append( " " + i + sb.toString() ); // big string
        }
        string = sb.toString();

        // DO WORK
        int[] blocks = disk.write( string );
        String result = (String) disk.read( blocks );

        // VERIFY
//        System.out.println( string );
//        System.out.println( result );
//        System.out.println( disk );
        assertEquals( "Wrong item retured.", string, result );
    }

    public void testWriteAndRead_BigString2_1_oe()
        throws Exception
    {
        // SETUP
        setUpBlockDisk("testWriteAndRead_BigString", 47); //4096;//1024

        String string = "abcdefghijklmnopqrstuvwxyz1234567890";
        string += string;
        string += string;

        // DO WORK
        int[] blocks = disk.write( string );
        String result = (String) disk.read( blocks );

        // VERIFY
        assertEquals( "Wrong item retured.", string, result );
    }

    public void testJCS156_1_oe() throws Exception
    {
        // SETUP
        setUpBlockDisk("testJCS156", 4096);
        long offset = disk.calculateByteOffsetForBlockAsLong(Integer.MAX_VALUE);
        assertTrue("Must not wrap round", offset > 0);
    }

    public void testJCS156_2_oe() throws Exception
    {
        // SETUP
        setUpBlockDisk("testJCS156", 4096);
        long offset = disk.calculateByteOffsetForBlockAsLong(Integer.MAX_VALUE);
        // removed other assertion
        assertEquals(Integer.MAX_VALUE*4096L,offset);
    }

}
