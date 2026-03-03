package org.apache.commons.jcs3.engine.memory.mru;

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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.jcs3.JCS;
import org.apache.commons.jcs3.access.CacheAccess;
import org.apache.commons.jcs3.access.exception.CacheException;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.control.CompositeCache;
import org.apache.commons.jcs3.engine.control.CompositeCacheManager;

/**
 * Tests for the test MRU implementation.
 * <p>
 * @author Aaron Smuts
 */
public class MRUMemoryCacheUnitTest_OE25Dev
    extends TestCase
{
    /** Test setup */
    @Override
    public void setUp()
    {
        JCS.setConfigFilename( "/TestMRUCache.ccf" );
    }

    /**
     * Verify that the mru gets used by a non-defined region when it is set as the default in the
     * default region.
     * <p>
     * @throws CacheException
     */

    /**
     * put twice as many as the max.  verify that the second half is in the cache.
     * <p>
     * @throws CacheException
     */

    /**
     * Put twice as many as the max, twice. verify that the second half is in the cache.
     * <p>
     * @throws CacheException
     */

    /**
     * put the max and remove each. verify that they are all null.
     * <p>
     * @throws CacheException
     */

    /**
     * put the max and clear. verify that no elements remain.
     * <p>
     * @throws CacheException
     */

    /**
     * Get stats.
     * <p>
     * @throws CacheException
     */

    /**
     * Put half the max and clear. get the key array and verify that it has the correct number of
     * items.
     * <p>
     * @throws Exception
     */

    /**
     * Add a few keys with the delimiter. Remove them.
     * <p>
     * @throws CacheException
     */

    public void testLoadFromCCF_1_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGet" );
        final String memoryCacheName = cache.getCacheAttributes().getMemoryCacheName();
        assertTrue( "Cache name should have MRU in it.", memoryCacheName.indexOf( "MRUMemoryCache" ) != -1 );
    }

    public void testPutGetThroughHub_1_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = max -1; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
            assertNull( "Should not have value for key [" + i + ":key" + "] in the cache." + cache.getStats(), value );
    }
    }

    public void testPutGetThroughHub_2_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = max -1; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
        }

        for ( int i = max + 2; i < items; i++ )
        {
            final String value = cache.get( i + ":key" );
            assertEquals( "myregion" + " data " + i, value );
    }
    }

    public void testPutGetThroughHub_3_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = max -1; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
        }

        for ( int i = max + 2; i < items; i++ )
        {
            final String value = cache.get( i + ":key" );
        }

        final Set<String> keys = new HashSet<>();
        for ( int i = 0; i < items; i++ )
        {
            keys.add( i + ":key" );
        }

        final Map<String, ICacheElement<String, String>> elements = cache.getCacheElements( keys );
        for ( int i = max-1; i >= 0; i-- )
        {
            assertNull( "Should not have value for key [" + i + ":key" + "] in the cache." + cache.getStats(), elements.get( i + ":key" ) );
    }
    }

    public void testPutGetThroughHub_4_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = max -1; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
        }

        for ( int i = max + 2; i < items; i++ )
        {
            final String value = cache.get( i + ":key" );
        }

        final Set<String> keys = new HashSet<>();
        for ( int i = 0; i < items; i++ )
        {
            keys.add( i + ":key" );
        }

        final Map<String, ICacheElement<String, String>> elements = cache.getCacheElements( keys );
        for ( int i = max-1; i >= 0; i-- )
        {
        }
        for ( int i = max + 2; i < items; i++ )
        {
            final ICacheElement<String, String> element = elements.get( i + ":key" );
            assertNotNull( "element " + i + ":key is missing", element );
    }
    }

    public void testPutGetThroughHub_5_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = max -1; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
        }

        for ( int i = max + 2; i < items; i++ )
        {
            final String value = cache.get( i + ":key" );
        }

        final Set<String> keys = new HashSet<>();
        for ( int i = 0; i < items; i++ )
        {
            keys.add( i + ":key" );
        }

        final Map<String, ICacheElement<String, String>> elements = cache.getCacheElements( keys );
        for ( int i = max-1; i >= 0; i-- )
        {
        }
        for ( int i = max + 2; i < items; i++ )
        {
            final ICacheElement<String, String> element = elements.get( i + ":key" );
            assertEquals( "value " + i + ":key", "myregion" + " data " + i, element.getVal() );
    }
    }

    public void testPutGetThroughHubTwice_1_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = max-1; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
            assertNull( "Should not have value for key [" + i + ":key" + "] in the cache.", value );
    }
    }

    public void testPutGetThroughHubTwice_2_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = max-1; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
        }

        for ( int i = max + 2; i < items; i++ )
        {
            final String value = cache.get( i + ":key" );
            assertEquals( "myregion" + " data " + i, value );
    }
    }

    public void testPutRemoveThroughHub_1_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        for ( int i = 0; i < items; i++ )
        {
            cache.remove( i + ":key" );
        }

        for ( int i = max; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
            assertNull( "Should not have value for key [" + i + ":key" + "] in the cache.", value );
    }
    }

    public void testClearThroughHub_1_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testPutGetThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        cache.clear();

        for ( int i = max; i >= 0; i-- )
        {
            final String value = cache.get( i + ":key" );
            assertNull( "Should not have value for key [" + i + ":key" + "] in the cache.", value );
    }
    }

    public void testGetStatsThroughHub_1_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testGetStatsThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max * 2;

        for ( int i = 0; i < items; i++ )
        {
            cache.put( i + ":key", "myregion" + " data " + i );
        }

        final String stats = cache.getStats();


        assertTrue( "Should have 200 puts", stats.indexOf( "2000" ) != -1 );
    }

    public void testGetKeyArray_1_oe()
        throws Exception
    {
        final CompositeCacheManager cacheMgr = CompositeCacheManager.getUnconfiguredInstance();
        cacheMgr.configure( "/TestMRUCache.ccf" );
        final CompositeCache<String, String> cache = cacheMgr.getCache( "testGetKeyArray" );

        final MRUMemoryCache<String, String> mru = new MRUMemoryCache<>();
        mru.initialize( cache );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max / 2;

        for ( int i = 0; i < items; i++ )
        {
            final ICacheElement<String, String> ice = new CacheElement<>( cache.getCacheName(), i + ":key", cache.getCacheName() + " data " + i );
            ice.setElementAttributes( cache.getElementAttributes() );
            mru.update( ice );
        }

        final Set<String> keys = mru.getKeySet();

        assertEquals( "Wrong number of keys.", items, keys.size() );
    }

    public void testRemovePartialThroughHub_1_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testGetStatsThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max / 2;

        cache.put( "test", "data" );

        final String root = "myroot";

        for ( int i = 0; i < items; i++ )
        {
            cache.put( root + ":" + i + ":key", "myregion" + " data " + i );
        }

        for ( int i = 0; i < items; i++ )
        {
            final String value = cache.get( root + ":" + i + ":key" );
            assertEquals( "myregion" + " data " + i, value );
    }
    }

    public void testRemovePartialThroughHub_2_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testGetStatsThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max / 2;

        cache.put( "test", "data" );

        final String root = "myroot";

        for ( int i = 0; i < items; i++ )
        {
            cache.put( root + ":" + i + ":key", "myregion" + " data " + i );
        }

        for ( int i = 0; i < items; i++ )
        {
            final String value = cache.get( root + ":" + i + ":key" );
        }

        cache.remove( root + ":" );

        for ( int i = 0; i < items; i++ )
        {
            assertNull( "Should have been removed by partial loop.", cache.get( root + ":" + i + ":key" ) );
    }
    }

    public void testRemovePartialThroughHub_3_oe()
        throws CacheException
    {
        final CacheAccess<String, String> cache = JCS.getInstance( "testGetStatsThroughHub" );

        final int max = cache.getCacheAttributes().getMaxObjects();
        final int items = max / 2;

        cache.put( "test", "data" );

        final String root = "myroot";

        for ( int i = 0; i < items; i++ )
        {
            cache.put( root + ":" + i + ":key", "myregion" + " data " + i );
        }

        for ( int i = 0; i < items; i++ )
        {
            final String value = cache.get( root + ":" + i + ":key" );
        }

        cache.remove( root + ":" );

        for ( int i = 0; i < items; i++ )
        {
        }

        assertNotNull( "Other item should be in the cache.", cache.get( "test" ) );
    }

}
