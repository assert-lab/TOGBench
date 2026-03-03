package org.apache.commons.jcs3.auxiliary.remote.http.server;

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

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.commons.jcs3.auxiliary.remote.MockRemoteCacheService;
import org.apache.commons.jcs3.auxiliary.remote.util.RemoteCacheRequestFactory;
import org.apache.commons.jcs3.auxiliary.remote.value.RemoteCacheRequest;
import org.apache.commons.jcs3.auxiliary.remote.value.RemoteCacheResponse;
import org.apache.commons.jcs3.engine.CacheElement;

/** Unit tests for the servlet. */
public class RemoteHttpCacheServletUnitTest_OE25Dev
    extends TestCase
{
    private RemoteHttpCacheServlet servlet;
    private MockRemoteCacheService<Serializable, Serializable> remoteHttpCacheService;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        servlet = new RemoteHttpCacheServlet();
        servlet.init(null);

        remoteHttpCacheService = new MockRemoteCacheService<>();
        servlet.setRemoteCacheService( remoteHttpCacheService );
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception
    {
        servlet.destroy();
        super.tearDown();
    }

    /** Verify that we balk and return an error. */

    /** Verify that the service is called. */

    /** Verify that the service is called. */

    /** Verify that the service is called. */

    /** Verify that the service is called. */

    /** Verify that the service is called. */

    /** Verify that the service is called. */

    public void testProcessRequest_null_1_oe()
    {
        final RemoteCacheRequest<Serializable, Serializable> request = null;

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertNotNull( "Should have a result.", result );
    }

    public void testProcessRequest_null_2_oe()
    {
        final RemoteCacheRequest<Serializable, Serializable> request = null;

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertTrue( "Should have 'The request is null' in the errorMessage", result.getErrorMessage().indexOf( "The request is null" ) != -1 );
    }

    public void testProcessRequest_null_3_oe()
    {
        final RemoteCacheRequest<Serializable, Serializable> request = null;

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertTrue( "Should have 'The request is null' in the toString", result.toString().indexOf( "The request is null" ) != -1 );
    }

    public void testProcessRequest_Get_1_oe()
    {
        final String cacheName = "test";
        final Serializable key = "key";
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createGetRequest( cacheName, key, requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertNotNull( "Should have a result.", result );
    }

    public void testProcessRequest_Get_2_oe()
    {
        final String cacheName = "test";
        final Serializable key = "key";
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createGetRequest( cacheName, key, requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertEquals( "Wrong key.", key, remoteHttpCacheService.lastGetKey );
    }

    public void testProcessRequest_GetMatching_1_oe()
    {
        final String cacheName = "test";
        final String pattern = "pattern";
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createGetMatchingRequest( cacheName, pattern,
                                                                                                  requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertNotNull( "Should have a result.", result );
    }

    public void testProcessRequest_GetMatching_2_oe()
    {
        final String cacheName = "test";
        final String pattern = "pattern";
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createGetMatchingRequest( cacheName, pattern,
                                                                                                  requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertEquals( "Wrong pattern.", pattern, remoteHttpCacheService.lastGetMatchingPattern );
    }

    public void testProcessRequest_GetMultiple_1_oe()
    {
        final String cacheName = "test";
        final Set<Serializable> keys = Collections.emptySet();
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createGetMultipleRequest( cacheName, keys,
                                                                                                  requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertNotNull( "Should have a result.", result );
    }

    public void testProcessRequest_GetMultiple_2_oe()
    {
        final String cacheName = "test";
        final Set<Serializable> keys = Collections.emptySet();
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createGetMultipleRequest( cacheName, keys,
                                                                                                  requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertEquals( "Wrong keys.", keys, remoteHttpCacheService.lastGetMultipleKeys );
    }

    public void testProcessRequest_Update_1_oe()
    {
        final String cacheName = "test";
        final String key = "key";
        final long requesterId = 2;
        final CacheElement<Serializable, Serializable> element = new CacheElement<>( cacheName, key, null );
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createUpdateRequest( element, requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertNotNull( "Should have a result.", result );
    }

    public void testProcessRequest_Update_2_oe()
    {
        final String cacheName = "test";
        final String key = "key";
        final long requesterId = 2;
        final CacheElement<Serializable, Serializable> element = new CacheElement<>( cacheName, key, null );
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createUpdateRequest( element, requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertEquals( "Wrong object.", element, remoteHttpCacheService.lastUpdate );
    }

    public void testProcessRequest_Remove_1_oe()
    {
        final String cacheName = "test";
        final Serializable key = "key";
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createRemoveRequest( cacheName, key, requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertNotNull( "Should have a result.", result );
    }

    public void testProcessRequest_Remove_2_oe()
    {
        final String cacheName = "test";
        final Serializable key = "key";
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createRemoveRequest( cacheName, key, requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertEquals( "Wrong key.", key, remoteHttpCacheService.lastRemoveKey );
    }

    public void testProcessRequest_RemoveAll_1_oe()
    {
        final String cacheName = "testRemoveALl";
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createRemoveAllRequest( cacheName, requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertNotNull( "Should have a result.", result );
    }

    public void testProcessRequest_RemoveAll_2_oe()
    {
        final String cacheName = "testRemoveALl";
        final long requesterId = 2;
        final RemoteCacheRequest<Serializable, Serializable> request = RemoteCacheRequestFactory.createRemoveAllRequest( cacheName, requesterId );

        final RemoteCacheResponse<Object> result = servlet.processRequest( request );

        assertEquals( "Wrong cacheName.", cacheName, remoteHttpCacheService.lastRemoveAllCacheName );
    }

}
