package org.apache.commons.jcs3.auxiliary.remote;

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

import java.rmi.registry.Registry;

import junit.framework.TestCase;

/**
 * Simple tests for remote utils. It is difficult to verify most of the things is does.
 *<p>
 * @author Aaron Smuts
 */
public class RemoteUtilsUnitTest_OE25Dev
    extends TestCase
{
    /**
     * Call create registry.
     * <p>
     * The exception is in the security manager setting.
     */
    public void testCreateRegistry()
    {
        final Registry registry = RemoteUtils.createRegistry( 1102 );
        assertNotNull("Registry should not be null", registry);
    }

    public void testGetNamingURL_1_oe()
    {
        assertEquals("//host:1/servicename", RemoteUtils.getNamingURL("host",1,"servicename"));
    }

    public void testGetNamingURL_2_oe()
    {
        assertEquals("//127.0.0.1:2/servicename", RemoteUtils.getNamingURL("127.0.0.1",2,"servicename"));
    }

    public void testGetNamingURL_3_oe()
    {
        assertEquals("//[0:0:0:0:0:0:0:1%251]:3/servicename", RemoteUtils.getNamingURL("0:0:0:0:0:0:0:1%1",3,"servicename"));
    }

    public void testParseServerAndPort_1_oe()
    {
        RemoteLocation loc = RemoteLocation.parseServerAndPort("server1:1234");
        assertEquals("server1", loc.getHost());
    }

    public void testParseServerAndPort_2_oe()
    {
        RemoteLocation loc = RemoteLocation.parseServerAndPort("server1:1234");
        assertEquals(1234, loc.getPort());
    }

    public void testParseServerAndPort_3_oe()
    {
        RemoteLocation loc = RemoteLocation.parseServerAndPort("server1:1234");

        loc = RemoteLocation.parseServerAndPort("  server2  :  4567  ");
        assertEquals("server2", loc.getHost());
    }

    public void testParseServerAndPort_4_oe()
    {
        RemoteLocation loc = RemoteLocation.parseServerAndPort("server1:1234");

        loc = RemoteLocation.parseServerAndPort("  server2  :  4567  ");
        assertEquals(4567, loc.getPort());
    }

}
