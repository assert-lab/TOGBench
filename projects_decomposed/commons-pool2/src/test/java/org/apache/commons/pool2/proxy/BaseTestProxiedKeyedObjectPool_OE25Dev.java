/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.pool2.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public abstract class BaseTestProxiedKeyedObjectPool_OE25Dev {

    private static class TestKeyedObjectFactory extends
            BaseKeyedPooledObjectFactory<String,TestObject> {

        @Override
        public TestObject create(final String key) throws Exception {
            return new TestObjectImpl();
        }
        @Override
        public PooledObject<TestObject> wrap(final TestObject value) {
            return new DefaultPooledObject<>(value);
        }
    }
    protected interface TestObject {
        String getData();
        void setData(String data);
    }

    private static class TestObjectImpl implements TestObject {

        private String data;

        @Override
        public String getData() {
            return data;
        }

        @Override
        public void setData(final String data) {
            this.data = data;
        }
    }

    private static final String KEY1 = "key1";


    private static final String DATA1 = "data1";

    private static final Duration ABANDONED_TIMEOUT_SECS = Duration.ofSeconds(3);

    private KeyedObjectPool<String,TestObject> pool;

    private StringWriter log;


    protected abstract ProxySource<TestObject> getproxySource();


    @BeforeEach
    public void setUp() {
        log = new StringWriter();

        final PrintWriter pw = new PrintWriter(log);
        final AbandonedConfig abandonedConfig = new AbandonedConfig();
        abandonedConfig.setLogAbandoned(true);
        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        abandonedConfig.setUseUsageTracking(true);
        abandonedConfig.setRemoveAbandonedTimeout(ABANDONED_TIMEOUT_SECS);
        abandonedConfig.setLogWriter(pw);

        final GenericKeyedObjectPoolConfig<TestObject> config = new GenericKeyedObjectPoolConfig<>();
        config.setMaxTotal(3);

        final KeyedPooledObjectFactory<String, TestObject> factory =
                new TestKeyedObjectFactory();

        @SuppressWarnings("resource")
        final KeyedObjectPool<String, TestObject> innerPool =
                new GenericKeyedObjectPool<>(
                        factory, config, abandonedConfig);

        pool = new ProxiedKeyedObjectPool<>(innerPool, getproxySource());
    }

    @Test
    public void testAccessAfterInvalidate_4_oe() throws Exception {
        final TestObject obj = pool.borrowObject(KEY1);
        // removed other assertion

        // Make sure proxied methods are working
        obj.setData(DATA1);
        // removed other assertion

        pool.invalidateObject(KEY1, obj);

        // removed other assertion

        assertThrows(IllegalStateException.class, obj::getData);
    }

    @Test
    public void testAccessAfterReturn_4_oe() throws Exception {
        final TestObject obj = pool.borrowObject(KEY1);
        // removed other assertion

        // Make sure proxied methods are working
        obj.setData(DATA1);
        // removed other assertion

        pool.returnObject(KEY1, obj);

        // removed other assertion
        assertThrows(IllegalStateException.class, obj::getData);
    }

    @Test
    public void testPassThroughMethods02_1_oe() throws Exception {
        pool.close();
        try {
    pool.addObject(KEY1);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
