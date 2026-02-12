package org.apache.commons.jcs3.admin;

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

import java.util.List;

import org.apache.commons.jcs3.JCS;
import org.apache.commons.jcs3.access.CacheAccess;


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
 * Test the admin bean that is used by the JCSAdmin.jsp
 *
 * @author Aaron Smuts
 *
 */
public class AdminBeanUnitTest_OE25Dev
    extends TestCase
{

    /**
     * Create a test region and then verify that we get it from the list.
     *
     * @throws Exception
     *
     */

    /**
     * Put a value in a region and verify that it shows up.
     *
     * @throws Exception
     */

    /**
     * Remove an item via the remove method.
     *
     * @throws Exception
     */

    /**
     * Add an item to a region. Call clear all and verify that it doesn't exist.
     *
     * @throws Exception
     */

    public void testGetRegionInfo_1_oe()
        throws Exception
    {
        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        cache.put( "key", "value" );

        final JCSAdminBean admin = new JCSAdminBean();

        final List<CacheRegionInfo> regions = admin.buildCacheInfo();

        boolean foundRegion = false;

        for (final CacheRegionInfo info : regions)
        {

            if ( info.getCacheName().equals( regionName ) )
            {
                foundRegion = true;

                assertTrue( "Byte count should be greater than 5.", info.getByteCount() > 5 );
    }
    }
    }

    public void testGetRegionInfo_2_oe()
        throws Exception
    {
        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        cache.put( "key", "value" );

        final JCSAdminBean admin = new JCSAdminBean();

        final List<CacheRegionInfo> regions = admin.buildCacheInfo();

        boolean foundRegion = false;

        for (final CacheRegionInfo info : regions)
        {

            if ( info.getCacheName().equals( regionName ) )
            {
                foundRegion = true;

                // removed other assertion

                assertNotNull( "Should have stats.", info.getCacheStatistics() );
    }
    }
    }

    public void testGetRegionInfo_3_oe()
        throws Exception
    {
        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        cache.put( "key", "value" );

        final JCSAdminBean admin = new JCSAdminBean();

        final List<CacheRegionInfo> regions = admin.buildCacheInfo();

        boolean foundRegion = false;

        for (final CacheRegionInfo info : regions)
        {

            if ( info.getCacheName().equals( regionName ) )
            {
                foundRegion = true;

                // removed other assertion

                // removed other assertion
            }
        }

        assertTrue( "Should have found the region we just created.", foundRegion );
    }

    public void testGetElementForRegionInfo_1_oe()
        throws Exception
    {
        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        // clear the region
        cache.clear();

        final String key = "myKey";
        cache.put( key, "value" );

        final JCSAdminBean admin = new JCSAdminBean();

        final List<CacheElementInfo> elements = admin.buildElementInfo( regionName );
        assertEquals( "Wrong number of elements in the region.", 1, elements.size() );
    }

    public void testGetElementForRegionInfo_2_oe()
        throws Exception
    {
        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        // clear the region
        cache.clear();

        final String key = "myKey";
        cache.put( key, "value" );

        final JCSAdminBean admin = new JCSAdminBean();

        final List<CacheElementInfo> elements = admin.buildElementInfo( regionName );
        // removed other assertion

        final CacheElementInfo elementInfo = elements.get(0);
        assertEquals( "Wrong key." + elementInfo, key, elementInfo.getKey() );
    }

    public void testRemove_1_oe()
        throws Exception
    {
        final JCSAdminBean admin = new JCSAdminBean();

        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        // clear the region
        cache.clear();
        admin.clearRegion( regionName );

        final String key = "myKey";
        cache.put( key, "value" );

        final List<CacheElementInfo> elements = admin.buildElementInfo( regionName );
        assertEquals( "Wrong number of elements in the region.", 1, elements.size() );
    }

    public void testRemove_2_oe()
        throws Exception
    {
        final JCSAdminBean admin = new JCSAdminBean();

        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        // clear the region
        cache.clear();
        admin.clearRegion( regionName );

        final String key = "myKey";
        cache.put( key, "value" );

        final List<CacheElementInfo> elements = admin.buildElementInfo( regionName );
        // removed other assertion

        final CacheElementInfo elementInfo = elements.get(0);
        assertEquals( "Wrong key.", key, elementInfo.getKey() );
    }

    public void testRemove_3_oe()
        throws Exception
    {
        final JCSAdminBean admin = new JCSAdminBean();

        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        // clear the region
        cache.clear();
        admin.clearRegion( regionName );

        final String key = "myKey";
        cache.put( key, "value" );

        final List<CacheElementInfo> elements = admin.buildElementInfo( regionName );
        // removed other assertion

        final CacheElementInfo elementInfo = elements.get(0);
        // removed other assertion

        admin.removeItem( regionName, key );

        final List<CacheElementInfo> elements2 = admin.buildElementInfo( regionName );
        assertEquals( "Wrong number of elements in the region after remove.", 0, elements2.size() );
    }

    public void testClearAll_1_oe()
        throws Exception
    {
        final JCSAdminBean admin = new JCSAdminBean();

        final String regionName = "myRegion";
        final CacheAccess<String, String> cache = JCS.getInstance( regionName );

        final String key = "myKey";
        cache.put( key, "value" );

        admin.clearAllRegions();

        final List<CacheElementInfo> elements2 = admin.buildElementInfo( regionName );
        assertEquals( "Wrong number of elements in the region after remove.", 0, elements2.size() );
    }

}
