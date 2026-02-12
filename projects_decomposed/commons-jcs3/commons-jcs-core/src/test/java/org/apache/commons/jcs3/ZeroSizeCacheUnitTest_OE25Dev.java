package org.apache.commons.jcs3;

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
 *
 * @author Aaron Smuts
 *
 */
public class ZeroSizeCacheUnitTest_OE25Dev
    extends TestCase
{
    /** number to get each loop */
    private static final int items = 20000;

    /**
     * Test setup
     * <p>
     * @throws Exception
     */
    @Override
    public void setUp()
        throws Exception
    {
        JCS.setConfigFilename( "/TestZeroSizeCache.ccf" );
        JCS.getInstance( "testCache1" );
    }

    /**
     * Verify that a 0 size cache does not result in errors. You should be able
     * to disable a region this way.
     * @throws Exception
     *
     */


}
