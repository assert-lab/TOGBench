package org.apache.commons.jcs3.access;

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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.jcs3.JCS;
import org.apache.commons.jcs3.access.exception.CacheException;
import org.apache.commons.jcs3.access.exception.ObjectExistsException;
import org.apache.commons.jcs3.engine.CompositeCacheAttributes;
import org.apache.commons.jcs3.engine.ElementAttributes;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.ICompositeCacheAttributes;
import org.apache.commons.jcs3.engine.behavior.IElementAttributes;

import junit.framework.TestCase;

/**
 * Tests the methods of the cache access class.
 * <p>
 * @author Aaron Smuts
 */
public class CacheAccessUnitTest_OE25Dev
    extends TestCase
{
    /**
     * Verify that we get an object exists exception if the item is in the cache.
     * @throws Exception
     */

    /**
     * Try to put a null key and verify that we get an exception.
     * @throws Exception
     */

    /**
     * Try to put a null value and verify that we get an exception.
     * @throws Exception
     */

    /**
     * Verify that elements that go in the region after this call take the new attributes.
     * @throws Exception
     */

    /**
     * Verify that getCacheElements returns the elements requested based on the key.
     * @throws Exception
     */

    /**
     * Verify that we can get a region using the define region method.
     * @throws Exception
     */

    /**
     * Verify that we can get a region using the define region method with cache attributes.
     * @throws Exception
     */

    /**
     * Verify that we can get a region using the define region method with cache attributes and
     * element attributes.
     * @throws Exception
     */

    /**
     * Verify we can get some matching elements..
     * <p>
     * @throws Exception
     */

    /**
     * Verify we can get some matching elements..
     * <p>
     * @throws Exception
     */

    public void testPutSafe_1_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );
        assertNotNull( "We should have an access class", access );
    }

    public void testPutSafe_2_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String key = "mykey";
        final String value = "myvalue";

        access.put( key, value );

        final String returnedValue1 = access.get( key );
        assertEquals( "Wrong value returned.", value, returnedValue1 );
    }

    public void testPutSafe_4_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String key = "mykey";
        final String value = "myvalue";

        access.put( key, value );

        final String returnedValue1 = access.get( key );

        try
        {
            access.putSafe( key, "someothervalue" );
        }
        catch ( final CacheException e )
        {
            assertTrue( "Wrong type of exception.", e instanceof ObjectExistsException );
    }
    }

    public void testPutSafe_5_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String key = "mykey";
        final String value = "myvalue";

        access.put( key, value );

        final String returnedValue1 = access.get( key );

        try
        {
            access.putSafe( key, "someothervalue" );
        }
        catch ( final CacheException e )
        {
            assertTrue( "Should have the key in the error message.", e.getMessage().indexOf( "[" + key + "]" ) != -1 );
    }
    }

    public void testPutSafe_6_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String key = "mykey";
        final String value = "myvalue";

        access.put( key, value );

        final String returnedValue1 = access.get( key );

        try
        {
            access.putSafe( key, "someothervalue" );
        }
        catch ( final CacheException e )
        {
        }

        final String returnedValue2 = access.get( key );
        assertEquals( "Wrong value returned.  Should still be the original.", value, returnedValue2 );
    }

    public void testPutNullKey_1_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );
        assertNotNull( "We should have an access class", access );
    }

    public void testPutNullKey_3_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String key = null;
        final String value = "myvalue";

        try
        {
            access.put( key, value );
        }
        catch ( final CacheException e )
        {
            assertTrue( "Should have the word null in the error message.", e.getMessage().indexOf( "null" ) != -1 );
    }
    }

    public void testPutNullValue_1_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );
        assertNotNull( "We should have an access class", access );
    }

    public void testPutNullValue_3_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String key = "myKey";
        final String value = null;

        try
        {
            access.put( key, value );
        }
        catch ( final CacheException e )
        {
            assertTrue( "Should have the word null in the error message.", e.getMessage().indexOf( "null" ) != -1 );
    }
    }

    public void testSetDefaultElementAttributes_1_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );
        assertNotNull( "We should have an access class", access );
    }

    public void testSetDefaultElementAttributes_2_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        access.setDefaultElementAttributes( attr );

        assertEquals("Wrong element attributes.",attr.getMaxLife(),access.getDefaultElementAttributes().getMaxLife());
    }

    public void testSetDefaultElementAttributes_3_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        access.setDefaultElementAttributes( attr );


        final String key = "mykey";
        final String value = "myvalue";

        access.put( key, value );

        final ICacheElement<String, String> element = access.getCacheElement( key );

        assertEquals("Wrong max life. Should have the new value.",maxLife,element.getElementAttributes().getMaxLife());
    }

    public void testGetCacheElements_1_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );
        assertNotNull( "We should have an access class", access );
    }

    public void testGetCacheElements_2_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        assertEquals( "map size", 2, result.size() );
    }

    public void testGetCacheElements_3_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        assertEquals( "value one", keyOne, elementOne.getKey() );
    }

    public void testGetCacheElements_4_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        assertEquals( "value one", valueOne, elementOne.getVal() );
    }

    public void testGetCacheElements_5_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        final ICacheElement<String, String> elementTwo = result.get( keyTwo );
        assertEquals( "value two", keyTwo, elementTwo.getKey() );
    }

    public void testGetCacheElements_6_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        final ICacheElement<String, String> elementTwo = result.get( keyTwo );
        assertEquals( "value two", valueTwo, elementTwo.getVal() );
    }

    public void testGetCacheElements_7_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        final ICacheElement<String, String> elementTwo = result.get( keyTwo );

        assertNull(access.get(keyFour));
    }

    public void testGetCacheElements_8_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        final ICacheElement<String, String> elementTwo = result.get( keyTwo );

        final String suppliedValue1 = access.get(keyFour, () -> valueFour);
        assertNotNull( "value four", suppliedValue1);
    }

    public void testGetCacheElements_9_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        final ICacheElement<String, String> elementTwo = result.get( keyTwo );

        final String suppliedValue1 = access.get(keyFour, () -> valueFour);
        assertEquals( "value four", valueFour, suppliedValue1);
    }

    public void testGetCacheElements_10_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        final ICacheElement<String, String> elementTwo = result.get( keyTwo );

        final String suppliedValue1 = access.get(keyFour, () -> valueFour);
        final String suppliedValue2 = access.get(keyFour);
        assertNotNull( "value four", suppliedValue2);
    }

    public void testGetCacheElements_11_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );

        final String keyOne = "mykeyone";
        final String keyTwo = "mykeytwo";
        final String keyThree = "mykeythree";
        final String keyFour = "mykeyfour";
        final String valueOne = "myvalueone";
        final String valueTwo = "myvaluetwo";
        final String valueThree = "myvaluethree";
        final String valueFour = "myvaluefour";

        access.put( keyOne, valueOne );
        access.put( keyTwo, valueTwo );
        access.put( keyThree, valueThree );

        final Set<String> input = new HashSet<>();
        input.add( keyOne );
        input.add( keyTwo );

        final Map<String, ICacheElement<String, String>> result = access.getCacheElements( input );

        final ICacheElement<String, String> elementOne = result.get( keyOne );
        final ICacheElement<String, String> elementTwo = result.get( keyTwo );

        final String suppliedValue1 = access.get(keyFour, () -> valueFour);
        final String suppliedValue2 = access.get(keyFour);
        assertEquals( "value four", suppliedValue1, suppliedValue2);
    }

    public void testRegionDefiniton_1_oe()
        throws Exception
    {
        final CacheAccess<String, String> access = JCS.getInstance( "test" );
        assertNotNull( "We should have an access class", access );
    }

    public void testRegionDefinitonWithAttributes_1_oe()
        throws Exception
    {
        final ICompositeCacheAttributes ca = new CompositeCacheAttributes();

        final long maxIdleTime = 8765;
        ca.setMaxMemoryIdleTimeSeconds( maxIdleTime );

        final CacheAccess<String, String> access = JCS.getInstance( "testRegionDefinitonWithAttributes", ca );
        assertNotNull( "We should have an access class", access );
    }

    public void testRegionDefinitonWithAttributes_2_oe()
        throws Exception
    {
        final ICompositeCacheAttributes ca = new CompositeCacheAttributes();

        final long maxIdleTime = 8765;
        ca.setMaxMemoryIdleTimeSeconds( maxIdleTime );

        final CacheAccess<String, String> access = JCS.getInstance( "testRegionDefinitonWithAttributes", ca );

        final ICompositeCacheAttributes ca2 = access.getCacheAttributes();
        assertEquals( "Wrong idle time setting.", ca.getMaxMemoryIdleTimeSeconds(), ca2.getMaxMemoryIdleTimeSeconds() );
    }

    public void testRegionDefinitonWithBothAttributes_1_oe()
        throws Exception
    {
        final ICompositeCacheAttributes ca = new CompositeCacheAttributes();

        final long maxIdleTime = 8765;
        ca.setMaxMemoryIdleTimeSeconds( maxIdleTime );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        final CacheAccess<String, String> access = JCS.getInstance( "testRegionDefinitonWithAttributes", ca, attr );
        assertNotNull( "We should have an access class", access );
    }

    public void testRegionDefinitonWithBothAttributes_2_oe()
        throws Exception
    {
        final ICompositeCacheAttributes ca = new CompositeCacheAttributes();

        final long maxIdleTime = 8765;
        ca.setMaxMemoryIdleTimeSeconds( maxIdleTime );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        final CacheAccess<String, String> access = JCS.getInstance( "testRegionDefinitonWithAttributes", ca, attr );

        final ICompositeCacheAttributes ca2 = access.getCacheAttributes();
        assertEquals( "Wrong idle time setting.", ca.getMaxMemoryIdleTimeSeconds(), ca2.getMaxMemoryIdleTimeSeconds() );
    }

    public void testGetMatching_Normal_1_oe()
        throws Exception
    {
        final int maxMemorySize = 1000;
        final String keyprefix1 = "MyPrefix1";
        final String keyprefix2 = "MyPrefix2";
        final String memoryCacheClassName = "org.apache.commons.jcs3.engine.memory.lru.LRUMemoryCache";
        final ICompositeCacheAttributes cattr = new CompositeCacheAttributes();
        cattr.setMemoryCacheName( memoryCacheClassName );
        cattr.setMaxObjects( maxMemorySize );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        final CacheAccess<String, Integer> access = JCS.getInstance( "testGetMatching_Normal", cattr, attr );

        final int numToInsertPrefix1 = 10;
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            access.put( keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final int numToInsertPrefix2 = 50;
        for ( int i = 0; i < numToInsertPrefix2; i++ )
        {
            access.put( keyprefix2 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final Map<String, Integer> result1 = access.getMatching( keyprefix1 + ".+" );
        final Map<String, Integer> result2 = access.getMatching( keyprefix2 + "\\S+" );

        assertEquals( "Wrong number returned 1:", numToInsertPrefix1, result1.size() );
    }

    public void testGetMatching_Normal_2_oe()
        throws Exception
    {
        final int maxMemorySize = 1000;
        final String keyprefix1 = "MyPrefix1";
        final String keyprefix2 = "MyPrefix2";
        final String memoryCacheClassName = "org.apache.commons.jcs3.engine.memory.lru.LRUMemoryCache";
        final ICompositeCacheAttributes cattr = new CompositeCacheAttributes();
        cattr.setMemoryCacheName( memoryCacheClassName );
        cattr.setMaxObjects( maxMemorySize );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        final CacheAccess<String, Integer> access = JCS.getInstance( "testGetMatching_Normal", cattr, attr );

        final int numToInsertPrefix1 = 10;
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            access.put( keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final int numToInsertPrefix2 = 50;
        for ( int i = 0; i < numToInsertPrefix2; i++ )
        {
            access.put( keyprefix2 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final Map<String, Integer> result1 = access.getMatching( keyprefix1 + ".+" );
        final Map<String, Integer> result2 = access.getMatching( keyprefix2 + "\\S+" );

        assertEquals( "Wrong number returned 2:", numToInsertPrefix2, result2.size() );
    }

    public void testGetMatching_Normal_3_oe()
        throws Exception
    {
        final int maxMemorySize = 1000;
        final String keyprefix1 = "MyPrefix1";
        final String keyprefix2 = "MyPrefix2";
        final String memoryCacheClassName = "org.apache.commons.jcs3.engine.memory.lru.LRUMemoryCache";
        final ICompositeCacheAttributes cattr = new CompositeCacheAttributes();
        cattr.setMemoryCacheName( memoryCacheClassName );
        cattr.setMaxObjects( maxMemorySize );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        final CacheAccess<String, Integer> access = JCS.getInstance( "testGetMatching_Normal", cattr, attr );

        final int numToInsertPrefix1 = 10;
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            access.put( keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final int numToInsertPrefix2 = 50;
        for ( int i = 0; i < numToInsertPrefix2; i++ )
        {
            access.put( keyprefix2 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final Map<String, Integer> result1 = access.getMatching( keyprefix1 + ".+" );
        final Map<String, Integer> result2 = access.getMatching( keyprefix2 + "\\S+" );


        for (final Map.Entry<String, Integer> entry : result1.entrySet())
        {
            final Object value = entry.getValue();
            assertFalse( "Should not be a cache element.", value instanceof ICacheElement );
    }
    }

    public void testGetMatchingElements_Normal_1_oe()
        throws Exception
    {
        final int maxMemorySize = 1000;
        final String keyprefix1 = "MyPrefix1";
        final String keyprefix2 = "MyPrefix2";
        final String memoryCacheClassName = "org.apache.commons.jcs3.engine.memory.lru.LRUMemoryCache";
        final ICompositeCacheAttributes cattr = new CompositeCacheAttributes();
        cattr.setMemoryCacheName( memoryCacheClassName );
        cattr.setMaxObjects( maxMemorySize );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        final CacheAccess<String, Integer> access = JCS.getInstance( "testGetMatching_Normal", cattr, attr );

        final int numToInsertPrefix1 = 10;
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            access.put( keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final int numToInsertPrefix2 = 50;
        for ( int i = 0; i < numToInsertPrefix2; i++ )
        {
            access.put( keyprefix2 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final Map<String, ICacheElement<String, Integer>> result1 = access.getMatchingCacheElements( keyprefix1 + "\\S+" );
        final Map<String, ICacheElement<String, Integer>> result2 = access.getMatchingCacheElements( keyprefix2 + ".+" );

        assertEquals( "Wrong number returned 1:", numToInsertPrefix1, result1.size() );
    }

    public void testGetMatchingElements_Normal_2_oe()
        throws Exception
    {
        final int maxMemorySize = 1000;
        final String keyprefix1 = "MyPrefix1";
        final String keyprefix2 = "MyPrefix2";
        final String memoryCacheClassName = "org.apache.commons.jcs3.engine.memory.lru.LRUMemoryCache";
        final ICompositeCacheAttributes cattr = new CompositeCacheAttributes();
        cattr.setMemoryCacheName( memoryCacheClassName );
        cattr.setMaxObjects( maxMemorySize );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        final CacheAccess<String, Integer> access = JCS.getInstance( "testGetMatching_Normal", cattr, attr );

        final int numToInsertPrefix1 = 10;
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            access.put( keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final int numToInsertPrefix2 = 50;
        for ( int i = 0; i < numToInsertPrefix2; i++ )
        {
            access.put( keyprefix2 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final Map<String, ICacheElement<String, Integer>> result1 = access.getMatchingCacheElements( keyprefix1 + "\\S+" );
        final Map<String, ICacheElement<String, Integer>> result2 = access.getMatchingCacheElements( keyprefix2 + ".+" );

        assertEquals( "Wrong number returned 2:", numToInsertPrefix2, result2.size() );
    }

    public void testGetMatchingElements_Normal_3_oe()
        throws Exception
    {
        final int maxMemorySize = 1000;
        final String keyprefix1 = "MyPrefix1";
        final String keyprefix2 = "MyPrefix2";
        final String memoryCacheClassName = "org.apache.commons.jcs3.engine.memory.lru.LRUMemoryCache";
        final ICompositeCacheAttributes cattr = new CompositeCacheAttributes();
        cattr.setMemoryCacheName( memoryCacheClassName );
        cattr.setMaxObjects( maxMemorySize );

        final long maxLife = 9876;
        final IElementAttributes attr = new ElementAttributes();
        attr.setMaxLife(maxLife);

        final CacheAccess<String, Integer> access = JCS.getInstance( "testGetMatching_Normal", cattr, attr );

        final int numToInsertPrefix1 = 10;
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            access.put( keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final int numToInsertPrefix2 = 50;
        for ( int i = 0; i < numToInsertPrefix2; i++ )
        {
            access.put( keyprefix2 + String.valueOf( i ), Integer.valueOf( i ) );
        }

        final Map<String, ICacheElement<String, Integer>> result1 = access.getMatchingCacheElements( keyprefix1 + "\\S+" );
        final Map<String, ICacheElement<String, Integer>> result2 = access.getMatchingCacheElements( keyprefix2 + ".+" );


        for (final Map.Entry<String, ICacheElement<String, Integer>> entry : result1.entrySet())
        {
            final Object value = entry.getValue();
            assertTrue( "Should be a cache element.", value instanceof ICacheElement );
    }
    }

}
