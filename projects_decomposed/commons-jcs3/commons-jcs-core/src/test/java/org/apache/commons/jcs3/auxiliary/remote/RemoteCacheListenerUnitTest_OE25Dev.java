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

import junit.framework.TestCase;

import org.apache.commons.jcs3.engine.control.MockCompositeCacheManager;
import org.apache.commons.jcs3.auxiliary.remote.behavior.IRemoteCacheAttributes;
import org.apache.commons.jcs3.engine.CacheElementSerialized;
import org.apache.commons.jcs3.engine.ElementAttributes;
import org.apache.commons.jcs3.engine.behavior.ICache;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheElementSerialized;
import org.apache.commons.jcs3.engine.behavior.ICompositeCacheManager;
import org.apache.commons.jcs3.engine.behavior.IElementAttributes;
import org.apache.commons.jcs3.engine.behavior.IElementSerializer;
import org.apache.commons.jcs3.utils.serialization.StandardSerializer;

/**
 * Tests for the remote cache listener.
 * <p>
 * @author Aaron Smuts
 */
public class RemoteCacheListenerUnitTest_OE25Dev
    extends TestCase
{
    /**
     * Create a RemoteCacheListener with a mock cache manager.  Set remove on put to false.
     * Create a serialized element.  Call put on the listener.
     * Verify that the deserialized element is in the cache.
     * <p>
     * @throws Exception
     */

    /**
     * Create a RemoteCacheListener with a mock cache manager.  Set remove on put to true.
     * Create a serialized element.  Call put on the listener.
     * Verify that the deserialized element is not in the cache.
     * <p>
     * @throws Exception
     */


}
