package org.apache.commons.jcs3.engine.memory.shrinking;

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

import org.apache.commons.jcs3.engine.ElementAttributesUtils;
import org.apache.commons.jcs3.engine.control.event.ElementEventHandlerMockImpl;
import org.apache.commons.jcs3.engine.memory.MockMemoryCache;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.CompositeCacheAttributes;
import org.apache.commons.jcs3.engine.ElementAttributes;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.control.CompositeCache;
import org.apache.commons.jcs3.engine.control.event.behavior.ElementEventType;
import java.io.IOException;

/**
 * This tests the functionality of the shrinker thread.
 * <p>
 * @author Aaron Smuts
 */
public class ShrinkerThreadUnitTest_OE25Dev
    extends TestCase
{
    /** verify the check for removal
     * <p>
     * @throws IOException */

    /** verify the check for removal
     * <p>
     * @throws IOException */

    /** verify the check for removal
     * <p>
     * @throws IOException */

    /** verify the check for removal
     * <p>
     * @throws IOException */

    /**
     * Setup cache attributes in mock. Create the shrinker with the mock. Add some elements into the
     * mock memory cache see that they get spooled.
     * <p>
     * @throws Exception
     */

    /**
     * Add 10 to the memory cache. Set the spool per run limit to 3.
     * <p>
     * @throws Exception
     */

    /**
     * Add a mock event handler to the items. Verify that it gets called.
     * <p>
     * This is only testing the spooled background event
     * <p>
     * @throws Exception
     */

    public void testCheckForRemoval_Expired_1_oe() throws IOException
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 10 );
        cacheAttr.setMaxSpoolPerRun( 10 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());

        final String key = "key";
        final String value = "value";

        final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );
        final ElementAttributes elementAttr = new ElementAttributes();
        elementAttr.setIsEternal( false );
        element.setElementAttributes( elementAttr );
        element.getElementAttributes().setMaxLife(1);

        long now = System.currentTimeMillis();
        now += 2000;

        final boolean result = cache.isExpired( element, now,
                ElementEventType.EXCEEDED_MAXLIFE_BACKGROUND,
                ElementEventType.EXCEEDED_IDLETIME_BACKGROUND );

        assertTrue( "Item should have expired.", result );
    }

    public void testCheckForRemoval_NotExpired_1_oe() throws IOException
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 10 );
        cacheAttr.setMaxSpoolPerRun( 10 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());

        final String key = "key";
        final String value = "value";

        final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );
        final ElementAttributes elementAttr = new ElementAttributes();
        elementAttr.setIsEternal( false );
        element.setElementAttributes( elementAttr );
        element.getElementAttributes().setMaxLife(1);

        long now = System.currentTimeMillis();
        now -= 2000;

        final boolean result = cache.isExpired( element, now,
                ElementEventType.EXCEEDED_MAXLIFE_BACKGROUND,
                ElementEventType.EXCEEDED_IDLETIME_BACKGROUND );

        assertFalse( "Item should not have expired.", result );
    }

    public void testCheckForRemoval_IdleTooLong_1_oe() throws IOException
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 10 );
        cacheAttr.setMaxSpoolPerRun( 10 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());

        final String key = "key";
        final String value = "value";

        final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );
        final ElementAttributes elementAttr = new ElementAttributes();
        elementAttr.setIsEternal( false );
        element.setElementAttributes( elementAttr );
        element.getElementAttributes().setMaxLife(100);
        element.getElementAttributes().setIdleTime( 1 );

        long now = System.currentTimeMillis();
        now += 2000;

        final boolean result = cache.isExpired( element, now,
                ElementEventType.EXCEEDED_MAXLIFE_BACKGROUND,
                ElementEventType.EXCEEDED_IDLETIME_BACKGROUND );

        assertTrue( "Item should have expired.", result );
    }

    public void testCheckForRemoval_NotIdleTooLong_1_oe() throws IOException
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 10 );
        cacheAttr.setMaxSpoolPerRun( 10 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());

        final String key = "key";
        final String value = "value";

        final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );
        final ElementAttributes elementAttr = new ElementAttributes();
        elementAttr.setIsEternal( false );
        element.setElementAttributes( elementAttr );
        element.getElementAttributes().setMaxLife(100);
        element.getElementAttributes().setIdleTime( 1 );

        long now = System.currentTimeMillis();
        now -= 2000;

        final boolean result = cache.isExpired( element, now,
                ElementEventType.EXCEEDED_MAXLIFE_BACKGROUND,
                ElementEventType.EXCEEDED_IDLETIME_BACKGROUND );

        assertFalse( "Item should not have expired.", result );
    }

    public void testSimpleShrink_1_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 10 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        final String key = "key";
        final String value = "value";

        final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

        final ElementAttributes elementAttr = new ElementAttributes();
        elementAttr.setIsEternal( false );
        element.setElementAttributes( elementAttr );
        element.getElementAttributes().setMaxLife(1);
        memory.update( element );

        final ICacheElement<String, String> returnedElement1 = memory.get( key );
        assertNotNull( "We should have received an element", returnedElement1 );
    }

    public void testSimpleShrink_2_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 10 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        final String key = "key";
        final String value = "value";

        final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

        final ElementAttributes elementAttr = new ElementAttributes();
        elementAttr.setIsEternal( false );
        element.setElementAttributes( elementAttr );
        element.getElementAttributes().setMaxLife(1);
        memory.update( element );

        final ICacheElement<String, String> returnedElement1 = memory.get( key );

        ElementAttributesUtils.setLastAccessTime( elementAttr,  System.currentTimeMillis() - 2000 );

        final ShrinkerThread<String, String> shrinker = new ShrinkerThread<>( cache );
        shrinker.run();

        Thread.sleep( 500 );

        final ICacheElement<String, String> returnedElement2 = memory.get( key );
        assertTrue( "Waterfall should have been called.", memory.waterfallCallCount > 0 );
    }

    public void testSimpleShrink_3_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 10 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        final String key = "key";
        final String value = "value";

        final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

        final ElementAttributes elementAttr = new ElementAttributes();
        elementAttr.setIsEternal( false );
        element.setElementAttributes( elementAttr );
        element.getElementAttributes().setMaxLife(1);
        memory.update( element );

        final ICacheElement<String, String> returnedElement1 = memory.get( key );

        ElementAttributesUtils.setLastAccessTime( elementAttr,  System.currentTimeMillis() - 2000 );

        final ShrinkerThread<String, String> shrinker = new ShrinkerThread<>( cache );
        shrinker.run();

        Thread.sleep( 500 );

        final ICacheElement<String, String> returnedElement2 = memory.get( key );
        assertNull( "We not should have received an element.  It should have been spooled.", returnedElement2 );
    }

    public void testSimpleShrinkMultiple_1_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 3 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        for ( int i = 0; i < 10; i++ )
        {
            final String key = "key" + i;
            final String value = "value";

            final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

            final ElementAttributes elementAttr = new ElementAttributes();
            elementAttr.setIsEternal( false );
            element.setElementAttributes( elementAttr );
            element.getElementAttributes().setMaxLife(1);
            memory.update( element );

            final ICacheElement<String, String> returnedElement1 = memory.get( key );
            assertNotNull( "We should have received an element", returnedElement1 );
    }
    }

    public void testSimpleShrinkMultiple_2_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 3 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        for ( int i = 0; i < 10; i++ )
        {
            final String key = "key" + i;
            final String value = "value";

            final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

            final ElementAttributes elementAttr = new ElementAttributes();
            elementAttr.setIsEternal( false );
            element.setElementAttributes( elementAttr );
            element.getElementAttributes().setMaxLife(1);
            memory.update( element );

            final ICacheElement<String, String> returnedElement1 = memory.get( key );

            ElementAttributesUtils.setLastAccessTime( elementAttr,  System.currentTimeMillis() - 2000 );
        }

        final ShrinkerThread<String, String> shrinker = new ShrinkerThread<>( cache );
        shrinker.run();

        Thread.sleep( 500 );
        assertEquals( "Waterfall called the wrong number of times.", 3, memory.waterfallCallCount );
    }

    public void testSimpleShrinkMultiple_3_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 3 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        for ( int i = 0; i < 10; i++ )
        {
            final String key = "key" + i;
            final String value = "value";

            final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

            final ElementAttributes elementAttr = new ElementAttributes();
            elementAttr.setIsEternal( false );
            element.setElementAttributes( elementAttr );
            element.getElementAttributes().setMaxLife(1);
            memory.update( element );

            final ICacheElement<String, String> returnedElement1 = memory.get( key );

            ElementAttributesUtils.setLastAccessTime( elementAttr,  System.currentTimeMillis() - 2000 );
        }

        final ShrinkerThread<String, String> shrinker = new ShrinkerThread<>( cache );
        shrinker.run();

        Thread.sleep( 500 );
        assertEquals( "Wrong number of elements remain.", 7, memory.getSize() );
    }

    public void testSimpleShrinkMultipleWithEventHandler_1_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 3 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        final ElementEventHandlerMockImpl handler = new ElementEventHandlerMockImpl();

        for ( int i = 0; i < 10; i++ )
        {
            final String key = "key" + i;
            final String value = "value";

            final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

            final ElementAttributes elementAttr = new ElementAttributes();
            elementAttr.addElementEventHandler( handler );
            elementAttr.setIsEternal( false );
            element.setElementAttributes( elementAttr );
            element.getElementAttributes().setMaxLife(1);
            memory.update( element );

            final ICacheElement<String, String> returnedElement1 = memory.get( key );
            assertNotNull( "We should have received an element", returnedElement1 );
    }
    }

    public void testSimpleShrinkMultipleWithEventHandler_2_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 3 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        final ElementEventHandlerMockImpl handler = new ElementEventHandlerMockImpl();

        for ( int i = 0; i < 10; i++ )
        {
            final String key = "key" + i;
            final String value = "value";

            final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

            final ElementAttributes elementAttr = new ElementAttributes();
            elementAttr.addElementEventHandler( handler );
            elementAttr.setIsEternal( false );
            element.setElementAttributes( elementAttr );
            element.getElementAttributes().setMaxLife(1);
            memory.update( element );

            final ICacheElement<String, String> returnedElement1 = memory.get( key );

            ElementAttributesUtils.setLastAccessTime( elementAttr,  System.currentTimeMillis() - 2000 );
        }

        final ShrinkerThread<String, String> shrinker = new ShrinkerThread<>( cache );
        shrinker.run();

        Thread.sleep( 500 );
        assertEquals( "Waterfall called the wrong number of times.", 3, memory.waterfallCallCount );
    }

    public void testSimpleShrinkMultipleWithEventHandler_3_oe()
        throws Exception
    {
        final CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
        cacheAttr.setCacheName("testRegion");
        cacheAttr.setMemoryCacheName("org.apache.commons.jcs3.engine.memory.MockMemoryCache");
        cacheAttr.setMaxMemoryIdleTimeSeconds( 1 );
        cacheAttr.setMaxSpoolPerRun( 3 );

        final CompositeCache<String, String> cache = new CompositeCache<>(cacheAttr, new ElementAttributes());
        final MockMemoryCache<String, String> memory = (MockMemoryCache<String, String>)cache.getMemoryCache();

        final ElementEventHandlerMockImpl handler = new ElementEventHandlerMockImpl();

        for ( int i = 0; i < 10; i++ )
        {
            final String key = "key" + i;
            final String value = "value";

            final ICacheElement<String, String> element = new CacheElement<>( "testRegion", key, value );

            final ElementAttributes elementAttr = new ElementAttributes();
            elementAttr.addElementEventHandler( handler );
            elementAttr.setIsEternal( false );
            element.setElementAttributes( elementAttr );
            element.getElementAttributes().setMaxLife(1);
            memory.update( element );

            final ICacheElement<String, String> returnedElement1 = memory.get( key );

            ElementAttributesUtils.setLastAccessTime( elementAttr,  System.currentTimeMillis() - 2000 );
        }

        final ShrinkerThread<String, String> shrinker = new ShrinkerThread<>( cache );
        shrinker.run();

        Thread.sleep( 500 );
        assertEquals( "Wrong number of elements remain.", 7, memory.getSize() );
    }

}
