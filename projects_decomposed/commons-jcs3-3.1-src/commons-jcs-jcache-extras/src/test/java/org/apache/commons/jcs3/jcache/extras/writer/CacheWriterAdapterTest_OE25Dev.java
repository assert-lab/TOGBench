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
package org.apache.commons.jcs3.jcache.extras.writer;

import org.apache.commons.jcs3.jcache.extras.InternalCacheRule;
import org.apache.commons.jcs3.jcache.extras.writer.CacheWriterAdapter;
import org.junit.Rule;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.integration.CacheWriterException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CacheWriterAdapterTest_OE25Dev
{
    @Rule
    public final InternalCacheRule rule = new InternalCacheRule(this);

    private final Map<String, String> copy = new HashMap<String, String>();
    private final Configuration<?, ?> config = new MutableConfiguration<String, String>()
            .setStoreByValue(false).setReadThrough(true)
            .setCacheWriterFactory(new CacheWriterAdapter<String, String>()
            {
                @Override
                public void write(final Cache.Entry<? extends String, ? extends String> entry) throws CacheWriterException
                {
                    copy.put(entry.getKey(), entry.getValue());
                }

                @Override
                public void delete(final Object key) throws CacheWriterException
                {
                    copy.remove(key);
                }
            });
    private Cache<String, String> cache;

    @Test
    public void checkWriteAllAndDeleteAll_1_oe()
    {
        assertTrue(copy.isEmpty());
    }

    @Test
    public void checkWriteAllAndDeleteAll_2_oe()
    {
        // removed other assertion
        assertFalse(cache.iterator().hasNext());
    }

    @Test
    public void checkWriteAllAndDeleteAll_3_oe()
    {
        // removed other assertion
        // removed other assertion
        cache.put("foo", "bar");
        assertEquals(1, copy.size());
    }

    @Test
    public void checkWriteAllAndDeleteAll_4_oe()
    {
        // removed other assertion
        // removed other assertion
        cache.put("foo", "bar");
        // removed other assertion
        cache.remove("foo");
        assertTrue(copy.isEmpty());
    }

    @Test
    public void checkWriteAllAndDeleteAll_5_oe()
    {
        // removed other assertion
        // removed other assertion
        cache.put("foo", "bar");
        // removed other assertion
        cache.remove("foo");
        // removed other assertion

        cache.putAll(new HashMap<String, String>() {{
            put("a", "b");
            put("b", "c");
        }});
        assertEquals(2, copy.size());
    }

    @Test
    public void checkWriteAllAndDeleteAll_6_oe()
    {
        // removed other assertion
        // removed other assertion
        cache.put("foo", "bar");
        // removed other assertion
        cache.remove("foo");
        // removed other assertion

        cache.putAll(new HashMap<String, String>() {{
            put("a", "b");
            put("b", "c");
        }});
        // removed other assertion
        cache.removeAll(new HashSet<String>(asList("a", "b")));
        assertTrue(copy.isEmpty());
    }

}
