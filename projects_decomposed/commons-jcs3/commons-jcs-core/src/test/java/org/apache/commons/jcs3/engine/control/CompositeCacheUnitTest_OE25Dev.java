package org.apache.commons.jcs3.engine.control;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.jcs3.auxiliary.MockAuxiliaryCache;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.CompositeCacheAttributes;
import org.apache.commons.jcs3.engine.ElementAttributes;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheType.CacheType;
import org.apache.commons.jcs3.engine.behavior.ICompositeCacheAttributes;
import org.apache.commons.jcs3.engine.behavior.IElementAttributes;
import org.apache.commons.jcs3.engine.memory.MockMemoryCache;

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
 * Tests that directly engage the composite cache.
 * <p>
 * @author Aaron Smuts
 */
public class CompositeCacheUnitTest_OE25Dev
    extends TestCase
{
    /**
     * Verify that the freeMemoryElements method on the memory cache is called on shutdown if there
     * is a disk cache.
     * <p>
     * @throws IOException
     */

    /**
     * Verify that the freeMemoryElements method on the memory cache is NOT called on shutdown if
     * there is NOT a disk cache.
     * <p>
     * @throws IOException
     */

    /**
     * Verify we can get some matching elements..
     * <p>
     * @throws IOException
     */

    /**
     * Verify we try a disk aux on a getMatching call.
     * <p>
     * @throws IOException
     */

    /**
     * Verify we try a remote  aux on a getMatching call.
     * <p>
     * @throws IOException
     */


}
