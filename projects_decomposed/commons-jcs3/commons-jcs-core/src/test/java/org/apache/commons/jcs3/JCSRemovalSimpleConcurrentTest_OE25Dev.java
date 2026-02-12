package org.apache.commons.jcs3;

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
 * Verify that basic removal functionality works.
 */
public class JCSRemovalSimpleConcurrentTest_OE25Dev
    extends TestCase
{
    private CacheAccess<String, String> jcs;

    /**
     * Test setup
     * <p>
     * @throws Exception
     */
    @Override
    public void setUp()
        throws Exception
    {
        JCS.setConfigFilename( "/TestRemoval.ccf" );
        jcs = JCS.getInstance( "testCache1" );
    }

    /**
     * Verify that 2 level deep hierchical removal works.
     * <p>
     * @throws Exception
     */

    /**
     * Verify that 1 level deep hierchical removal works.
     *
     * @throws Exception
     */

    /**
     * Verify that clear removes everyting as it should.
     * <p>
     * @throws Exception
     */

    /**
     * Verify that we can clear repeatedly without error.
     *
     * @throws Exception
     */


}
