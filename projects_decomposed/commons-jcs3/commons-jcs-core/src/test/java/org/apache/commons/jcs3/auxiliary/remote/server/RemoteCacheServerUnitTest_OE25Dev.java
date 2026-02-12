package org.apache.commons.jcs3.auxiliary.remote.server;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.jcs3.auxiliary.MockCacheEventLogger;
import org.apache.commons.jcs3.auxiliary.remote.MockRemoteCacheListener;
import org.apache.commons.jcs3.utils.timing.SleepUtil;
import org.apache.commons.jcs3.auxiliary.remote.RemoteUtils;
import org.apache.commons.jcs3.auxiliary.remote.server.behavior.IRemoteCacheServerAttributes;
import org.apache.commons.jcs3.auxiliary.remote.server.behavior.RemoteType;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;

import junit.framework.TestCase;

/**
 * Since the server does not know that it is a server, it is easy to unit test. The factory does all
 * the rmi work.
 * <p>
 * @author Aaron Smuts
 */
public class RemoteCacheServerUnitTest_OE25Dev
    extends TestCase
{
    private static final String expectedIp1 = "adfasdf";
    private static final String expectedIp2 = "adsfadsafaf";

    private RemoteCacheServer<String, String> server;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        final IRemoteCacheServerAttributes rcsa = new RemoteCacheServerAttributes();
        rcsa.setConfigFileName( "/TestRemoteCacheServer.ccf" );
        final Properties config = RemoteUtils.loadProps(rcsa.getConfigFileName());
        this.server = new RemoteCacheServer<>( rcsa, config );
    }

    @Override
    protected void tearDown() throws Exception
    {
        this.server.shutdown();

        super.tearDown();
    }

    /**
     * Add a listener. Pass the id of 0, verify that the server sets a new listener id. Do another
     * and verify that the second gets an id of 2.
     * <p>
     * @throws Exception
     */

    /**
     * Add a listener. Pass the id of 0, verify that the server sets a new listener id. Do another
     * and verify that the second gets an id of 2.
     * <p>
     * @throws Exception
     */

    // TODO: This test only works if preconfigured remote caches exist. Need to fix.
//    /**
//     * Add a listener. Pass the id of 0, verify that the server sets a new listener id. Do another
//     * and verify that the second gets an id of 2.
//     * <p>
//     * @throws Exception
//     */
//    public void testAddListener_ToAll()
//        throws Exception
//    {
//        MockRemoteCacheListener<String, String> mockListener1 = new MockRemoteCacheListener<>();
//        mockListener1.localAddress = expectedIp1;
//        MockRemoteCacheListener<String, String> mockListener2 = new MockRemoteCacheListener<>();
//        mockListener2.localAddress = expectedIp2;
//
//        // DO WORK
//        // don't specify the cache name
//        server.addCacheListener( mockListener1 );
//        server.addCacheListener( mockListener2 );
//
//        // VERIFY
//        assertEquals( "Wrong listener id.", 1, mockListener1.getListenerId() );
//        assertEquals( "Wrong listener id.", 2, mockListener2.getListenerId() );
//        assertEquals( "Wrong ip.", expectedIp1, server.getExtraInfoForRequesterId( 1 ) );
//        assertEquals( "Wrong ip.", expectedIp2, server.getExtraInfoForRequesterId( 2 ) );
//    }

    /**
     * Add a listener. Pass the id of 0, verify that the server sets a new listener id. Do another
     * and verify that the second gets an id of 2. Call remove Listener and verify that it is
     * removed.
     * <p>
     * @throws Exception
     */

    /**
     * Add a listener. Pass the id of 0, verify that the server sets a new listener id. Do another
     * and verify that the second gets an id of 2. Call remove Listener and verify that it is
     * removed.
     * <p>
     * @throws Exception
     */

    /**
     * Register a listener and then verify that it is called when we put using a different listener
     * id.
     * @throws Exception
     */

    /**
     * Register a listener and then verify that it is called when we put using a different listener
     * id. The updates should come from a cluster listener and local cluster consistency should be
     * true.
     * <p>
     * @throws Exception
     */

    /**
     * Register a listener and then verify that it is called when we put using a different listener
     * id.
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */


}
