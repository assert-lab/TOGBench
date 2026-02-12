package org.apache.commons.jcs3.auxiliary.lateral;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.jcs3.auxiliary.lateral.socket.tcp.TCPLateralCacheAttributes;
import org.apache.commons.jcs3.engine.ZombieCacheServiceNonLocal;

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
 * Tests for LateralCacheNoWaitFacade.
 */
public class LateralCacheNoWaitFacadeUnitTest_OE25Dev
    extends TestCase
{
    private LateralCacheNoWaitFacade<String, String> facade;
    private LateralCache<String, String> cache;

    @Override
    protected void setUp() throws Exception
    {
        // SETUP
        List<LateralCacheNoWait<String, String>> noWaits = new ArrayList<>();
        TCPLateralCacheAttributes cattr = new TCPLateralCacheAttributes();
        cattr.setCacheName( "testCache1" );
        cattr.setTcpServer("localhost:7890");


        facade = new LateralCacheNoWaitFacade<>( null, noWaits, cattr );
        cache = new LateralCache<>(cattr, new ZombieCacheServiceNonLocal<>(), null);
    }

    /**
     * Verify that we can remove an item.
     */

    /**
     * Verify that we can remove an item.
     */

    /**
     * Verify that we can remove an item.
     */

    /**
     * Verify that we can remove an item.
     */


}
