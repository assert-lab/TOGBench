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

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public abstract class BaseTestProxiedObjectPool_OE25Dev {

    protected interface TestObject {
        String getData();
        void setData(String data);
    }
    private static class TestObjectFactory extends
            BasePooledObjectFactory<TestObject> {

        @Override
        public TestObject create() throws Exception {
            return new TestObjectImpl();
        }
        @Override
        public PooledObject<TestObject> wrap(final TestObject value) {
            return new DefaultPooledObject<>(value);
        }
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
    private static final String DATA1 = "data1";

    private static final Duration ABANDONED_TIMEOUT_SECS = Duration.ofSeconds(3);


    private ObjectPool<TestObject> pool;

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

        final GenericObjectPoolConfig<TestObject> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(3);

        final PooledObjectFactory<TestObject> factory = new TestObjectFactory();

        @SuppressWarnings("resource")
        final ObjectPool<TestObject> innerPool =
                new GenericObjectPool<>(factory, config, abandonedConfig);

        pool = new ProxiedObjectPool<>(innerPool, getproxySource());
    }

    @Test
    public void testAccessAfterInvalidate_4_oe() throws Exception {
        final TestObject obj = pool.borrowObject();
        // removed other assertion

        // Make sure proxied methods are working
        obj.setData(DATA1);
        // removed other assertion

        pool.invalidateObject(obj);

        // removed other assertion

        assertThrows(IllegalStateException.class, obj::getData);
    }

    @Test
    public void testAccessAfterReturn_4_oe() throws Exception {
        final TestObject obj = pool.borrowObject();
        // removed other assertion

        // Make sure proxied methods are working
        obj.setData(DATA1);
        // removed other assertion

        pool.returnObject(obj);

        // removed other assertion

        assertThrows(IllegalStateException.class, obj::getData);
    }

    @Test
    public void testPassThroughMethods02_1_oe() throws Exception {
        pool.close();

        try {
    pool.addObject();
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
