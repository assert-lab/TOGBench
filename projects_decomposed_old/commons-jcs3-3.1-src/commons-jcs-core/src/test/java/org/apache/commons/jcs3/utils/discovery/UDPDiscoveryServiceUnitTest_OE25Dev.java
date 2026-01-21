package org.apache.commons.jcs3.utils.discovery;

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

import java.util.ArrayList;

import junit.framework.TestCase;

/** Unit tests for the service. */
public class UDPDiscoveryServiceUnitTest_OE25Dev
    extends TestCase
{
    /** Verify that the list is updated. */

    /** Verify that the list is updated. */

    /** Verify that the list is updated. */

    /** Verify that the list is updated. */

    public void testAddOrUpdateService_NotInList_1_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        // DO WORK
        service.addOrUpdateService( discoveredService );

        // VERIFY
        assertTrue( "Service should be in the service list.", service.getDiscoveredServices() .contains( discoveredService ) );
    }

    public void testAddOrUpdateService_NotInList_2_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        // DO WORK
        service.addOrUpdateService( discoveredService );

        // VERIFY
        // removed other assertion
        assertTrue( "Service should be in the listener list.", discoveryListener.discoveredServices .contains( discoveredService ) );
    }

    public void testAddOrUpdateService_InList_NamesDoNotChange_1_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        ArrayList<String> sametCacheNames = new ArrayList<>();
        sametCacheNames.add( "name1" );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( sametCacheNames );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );


        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( sametCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        assertEquals( "Should only be one in the set.", 1, service.getDiscoveredServices().size() );
    }

    public void testAddOrUpdateService_InList_NamesDoNotChange_2_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        ArrayList<String> sametCacheNames = new ArrayList<>();
        sametCacheNames.add( "name1" );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( sametCacheNames );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );


        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( sametCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        assertTrue( "Service should be in the service list.", service.getDiscoveredServices() .contains( discoveredService ) );
    }

    public void testAddOrUpdateService_InList_NamesDoNotChange_3_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        ArrayList<String> sametCacheNames = new ArrayList<>();
        sametCacheNames.add( "name1" );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( sametCacheNames );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );


        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( sametCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        // removed other assertion
        assertTrue( "Service should be in the listener list.", discoveryListener.discoveredServices .contains( discoveredService ) );
    }

    public void testAddOrUpdateService_InList_NamesDoNotChange_4_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        ArrayList<String> sametCacheNames = new ArrayList<>();
        sametCacheNames.add( "name1" );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( sametCacheNames );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );


        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( sametCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // need to update the time this sucks. add has no effect convert to a map
        for (DiscoveredService service1 : service.getDiscoveredServices())
        {
            if ( discoveredService.equals( service1 ) )
            {
                assertEquals( "The match should have the new last heard from time.", service1.getLastHearFromTime(), discoveredService2.getLastHearFromTime() );
    }
    }
    }

    public void testAddOrUpdateService_InList_NamesDoNotChange_5_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        ArrayList<String> sametCacheNames = new ArrayList<>();
        sametCacheNames.add( "name1" );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( sametCacheNames );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );


        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( sametCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // need to update the time this sucks. add has no effect convert to a map
        for (DiscoveredService service1 : service.getDiscoveredServices())
        {
            if ( discoveredService.equals( service1 ) )
            {
                // removed other assertion
            }
        }
        // the mock has a list from all add calls.
        // it should have been called when the list changed.
        //assertEquals( "Mock should have been called once.", 1, discoveryListener.discoveredServices.size() );
        // logic changed.  it's called every time.
        assertEquals( "Mock should have been called twice.", 2, discoveryListener.discoveredServices.size() );
    }

    public void testAddOrUpdateService_InList_NamesChange_1_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        ArrayList<String> differentCacheNames = new ArrayList<>();
        differentCacheNames.add( "name1" );
        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( differentCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        assertEquals( "Should only be one in the set.", 1, service.getDiscoveredServices().size() );
    }

    public void testAddOrUpdateService_InList_NamesChange_2_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        ArrayList<String> differentCacheNames = new ArrayList<>();
        differentCacheNames.add( "name1" );
        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( differentCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        assertTrue( "Service should be in the service list.", service.getDiscoveredServices() .contains( discoveredService ) );
    }

    public void testAddOrUpdateService_InList_NamesChange_3_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        ArrayList<String> differentCacheNames = new ArrayList<>();
        differentCacheNames.add( "name1" );
        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( differentCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        // removed other assertion
        assertTrue( "Service should be in the listener list.", discoveryListener.discoveredServices .contains( discoveredService ) );
    }

    public void testAddOrUpdateService_InList_NamesChange_4_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        ArrayList<String> differentCacheNames = new ArrayList<>();
        differentCacheNames.add( "name1" );
        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( differentCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // need to update the time this sucks. add has no effect convert to a map
        for (DiscoveredService service1 : service.getDiscoveredServices())
        {
            if ( discoveredService.equals( service1 ) )
            {
                assertEquals( "The match should have the new last heard from time.", service1.getLastHearFromTime(), discoveredService2.getLastHearFromTime() );
    }
    }
    }

    public void testAddOrUpdateService_InList_NamesChange_5_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        ArrayList<String> differentCacheNames = new ArrayList<>();
        differentCacheNames.add( "name1" );
        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( differentCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // need to update the time this sucks. add has no effect convert to a map
        for (DiscoveredService service1 : service.getDiscoveredServices())
        {
            if ( discoveredService.equals( service1 ) )
            {
                // removed other assertion
                assertEquals( "The names should be updated.", service1.getCacheNames() + "", differentCacheNames + "" );
    }
    }
    }

    public void testAddOrUpdateService_InList_NamesChange_6_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        ArrayList<String> differentCacheNames = new ArrayList<>();
        differentCacheNames.add( "name1" );
        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( differentCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // need to update the time this sucks. add has no effect convert to a map
        for (DiscoveredService service1 : service.getDiscoveredServices())
        {
            if ( discoveredService.equals( service1 ) )
            {
                // removed other assertion
                // removed other assertion
            }
        }
        // the mock has a list from all add calls.
        // it should have been called when the list changed.
        assertEquals( "Mock should have been called twice.", 2, discoveryListener.discoveredServices.size() );
    }

    public void testAddOrUpdateService_InList_NamesChange_7_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        ArrayList<String> differentCacheNames = new ArrayList<>();
        differentCacheNames.add( "name1" );
        DiscoveredService discoveredService2 = new DiscoveredService();
        discoveredService2.setServiceAddress( host );
        discoveredService2.setCacheNames( differentCacheNames );
        discoveredService2.setServicePort( 1000 );
        discoveredService2.setLastHearFromTime( 500 );

        // DO WORK
        service.addOrUpdateService( discoveredService );
        // again
        service.addOrUpdateService( discoveredService2 );

        // VERIFY
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // need to update the time this sucks. add has no effect convert to a map
        for (DiscoveredService service1 : service.getDiscoveredServices())
        {
            if ( discoveredService.equals( service1 ) )
            {
                // removed other assertion
                // removed other assertion
            }
        }
        // the mock has a list from all add calls.
        // it should have been called when the list changed.
        // removed other assertion
        assertEquals( "The second mock listener add should be discoveredService2", discoveredService2, discoveryListener.discoveredServices.get( 1 ) );
    }

    public void testRemoveDiscoveredService_1_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        service.addOrUpdateService( discoveredService );

        // DO WORK
        service.removeDiscoveredService( discoveredService );

        // VERIFY
        assertFalse( "Service should not be in the service list.", service.getDiscoveredServices() .contains( discoveredService ) );
    }

    public void testRemoveDiscoveredService_2_oe()
    {
        // SETUP
        String host = "228.5.6.7";
        int port = 6789;
        UDPDiscoveryAttributes attributes = new UDPDiscoveryAttributes();
        attributes.setUdpDiscoveryAddr( host );
        attributes.setUdpDiscoveryPort( port );
        attributes.setServicePort( 1000 );

        // create the service
        UDPDiscoveryService service = new UDPDiscoveryService( attributes );
        service.startup();
        service.addParticipatingCacheName( "testCache1" );

        MockDiscoveryListener discoveryListener = new MockDiscoveryListener();
        service.addDiscoveryListener( discoveryListener );

        DiscoveredService discoveredService = new DiscoveredService();
        discoveredService.setServiceAddress( host );
        discoveredService.setCacheNames( new ArrayList<>() );
        discoveredService.setServicePort( 1000 );
        discoveredService.setLastHearFromTime( 100 );

        service.addOrUpdateService( discoveredService );

        // DO WORK
        service.removeDiscoveredService( discoveredService );

        // VERIFY
        // removed other assertion
        assertFalse( "Service should not be in the listener list.", discoveryListener.discoveredServices .contains( discoveredService ) );
    }

}
