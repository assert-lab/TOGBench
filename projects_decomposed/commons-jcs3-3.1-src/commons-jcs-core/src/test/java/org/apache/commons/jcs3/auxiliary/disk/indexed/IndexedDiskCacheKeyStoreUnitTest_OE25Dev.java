package org.apache.commons.jcs3.auxiliary.disk.indexed;

import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.ElementAttributes;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.IElementAttributes;

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
 * Test store and load keys.
 *
 * @author Aaron Smuts
 *
 */
public class IndexedDiskCacheKeyStoreUnitTest_OE25Dev
    extends TestCase
{

    /**
     * Add some keys, store them, load them from disk, then check to see that we
     * can get the items.
     *
     * @throws Exception
     *
     */


    /**
     * Add some elements, remove 1, call optimize, verify that the removed isn't present.
     *
     * We should also compare the data file sizes. . . .
     *
     * @throws Exception
     *
     */

    public void testStoreKeys_1_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testStoreKeys" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            assertNotNull( "presave, Should have received an element.", element );
    }
    }

    public void testStoreKeys_2_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testStoreKeys" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            // removed other assertion
            assertEquals( "presave, element is wrong.", "data:" + i, element.getVal() );
    }
    }

    public void testStoreKeys_3_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testStoreKeys" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            // removed other assertion
            // removed other assertion
        }

        disk.saveKeys();

        disk.loadKeys();

        assertEquals( "The disk is the wrong size.", cnt, disk.getSize() );
    }

    public void testStoreKeys_4_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testStoreKeys" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            // removed other assertion
            // removed other assertion
        }

        disk.saveKeys();

        disk.loadKeys();

        // removed other assertion

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            assertNotNull( "postsave, Should have received an element.", element );
    }
    }

    public void testStoreKeys_5_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testStoreKeys" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            // removed other assertion
            // removed other assertion
        }

        disk.saveKeys();

        disk.loadKeys();

        // removed other assertion

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            // removed other assertion
            assertEquals( "postsave, element is wrong.", "data:" + i, element.getVal() );
    }
    }

    public void testOptiimize_1_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testOptimize" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        final long preAddRemoveSize = disk.getDataFileSize();

        final IElementAttributes eAttr = new ElementAttributes();
        eAttr.setIsSpool( true );
        final ICacheElement<String, String> elementSetup = new CacheElement<>( cattr.getCacheName(), "key:" + "A", "data:" + "A" );
        elementSetup.setElementAttributes( eAttr );
        disk.processUpdate( elementSetup );

        final ICacheElement<String, String> elementRet = disk.processGet( "key:" + "A" );
        assertNotNull( "postsave, Should have received an element.", elementRet );
    }

    public void testOptiimize_2_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testOptimize" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        final long preAddRemoveSize = disk.getDataFileSize();

        final IElementAttributes eAttr = new ElementAttributes();
        eAttr.setIsSpool( true );
        final ICacheElement<String, String> elementSetup = new CacheElement<>( cattr.getCacheName(), "key:" + "A", "data:" + "A" );
        elementSetup.setElementAttributes( eAttr );
        disk.processUpdate( elementSetup );

        final ICacheElement<String, String> elementRet = disk.processGet( "key:" + "A" );
        // removed other assertion
        assertEquals( "postsave, element is wrong.", "data:" + "A", elementRet.getVal() );
    }

    public void testOptiimize_3_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testOptimize" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        final long preAddRemoveSize = disk.getDataFileSize();

        final IElementAttributes eAttr = new ElementAttributes();
        eAttr.setIsSpool( true );
        final ICacheElement<String, String> elementSetup = new CacheElement<>( cattr.getCacheName(), "key:" + "A", "data:" + "A" );
        elementSetup.setElementAttributes( eAttr );
        disk.processUpdate( elementSetup );

        final ICacheElement<String, String> elementRet = disk.processGet( "key:" + "A" );
        // removed other assertion
        // removed other assertion

        disk.remove( "key:" + "A" );

        final long preSize = disk.getDataFileSize();
        // synchronous versoin
        disk.optimizeFile(); //deoptimizeRealTime();
        final long postSize = disk.getDataFileSize();

        assertTrue( "Should be smaller. postsize="+postSize+" preSize="+preSize, postSize < preSize );
    }

    public void testOptiimize_4_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testOptimize" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        final long preAddRemoveSize = disk.getDataFileSize();

        final IElementAttributes eAttr = new ElementAttributes();
        eAttr.setIsSpool( true );
        final ICacheElement<String, String> elementSetup = new CacheElement<>( cattr.getCacheName(), "key:" + "A", "data:" + "A" );
        elementSetup.setElementAttributes( eAttr );
        disk.processUpdate( elementSetup );

        final ICacheElement<String, String> elementRet = disk.processGet( "key:" + "A" );
        // removed other assertion
        // removed other assertion

        disk.remove( "key:" + "A" );

        final long preSize = disk.getDataFileSize();
        // synchronous versoin
        disk.optimizeFile(); //deoptimizeRealTime();
        final long postSize = disk.getDataFileSize();

        // removed other assertion
        assertEquals( "Should be the same size after optimization as before add and remove.", preAddRemoveSize, postSize );
    }

    public void testOptiimize_5_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testOptimize" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        final long preAddRemoveSize = disk.getDataFileSize();

        final IElementAttributes eAttr = new ElementAttributes();
        eAttr.setIsSpool( true );
        final ICacheElement<String, String> elementSetup = new CacheElement<>( cattr.getCacheName(), "key:" + "A", "data:" + "A" );
        elementSetup.setElementAttributes( eAttr );
        disk.processUpdate( elementSetup );

        final ICacheElement<String, String> elementRet = disk.processGet( "key:" + "A" );
        // removed other assertion
        // removed other assertion

        disk.remove( "key:" + "A" );

        final long preSize = disk.getDataFileSize();
        // synchronous versoin
        disk.optimizeFile(); //deoptimizeRealTime();
        final long postSize = disk.getDataFileSize();

        // removed other assertion
        // removed other assertion

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            assertNotNull( "postsave, Should have received an element.", element );
    }
    }

    public void testOptiimize_6_oe()
        throws Exception
    {
        final IndexedDiskCacheAttributes cattr = new IndexedDiskCacheAttributes();
        cattr.setCacheName( "testOptimize" );
        cattr.setMaxKeySize( 100 );
        cattr.setDiskPath( "target/test-sandbox/KeyStoreUnitTest" );
        final IndexedDiskCache<String, String> disk = new IndexedDiskCache<>( cattr );

        disk.processRemoveAll();

        final int cnt = 25;
        for ( int i = 0; i < cnt; i++ )
        {
            final IElementAttributes eAttr = new ElementAttributes();
            eAttr.setIsSpool( true );
            final ICacheElement<String, String> element = new CacheElement<>( cattr.getCacheName(), "key:" + i, "data:" + i );
            element.setElementAttributes( eAttr );
            disk.processUpdate( element );
        }

        final long preAddRemoveSize = disk.getDataFileSize();

        final IElementAttributes eAttr = new ElementAttributes();
        eAttr.setIsSpool( true );
        final ICacheElement<String, String> elementSetup = new CacheElement<>( cattr.getCacheName(), "key:" + "A", "data:" + "A" );
        elementSetup.setElementAttributes( eAttr );
        disk.processUpdate( elementSetup );

        final ICacheElement<String, String> elementRet = disk.processGet( "key:" + "A" );
        // removed other assertion
        // removed other assertion

        disk.remove( "key:" + "A" );

        final long preSize = disk.getDataFileSize();
        // synchronous versoin
        disk.optimizeFile(); //deoptimizeRealTime();
        final long postSize = disk.getDataFileSize();

        // removed other assertion
        // removed other assertion

        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = disk.processGet( "key:" + i );
            // removed other assertion
            assertEquals( "postsave, element is wrong.", "data:" + i, element.getVal() );
    }
    }

}
