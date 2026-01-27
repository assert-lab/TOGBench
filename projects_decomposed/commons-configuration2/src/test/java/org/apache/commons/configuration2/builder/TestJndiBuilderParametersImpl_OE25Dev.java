/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.configuration2.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Map;

import javax.naming.Context;

import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code JndiBuilderParametersImpl}.
 *
 */
public class TestJndiBuilderParametersImpl_OE25Dev {
    /** The parameters object to be tested. */
    private JndiBuilderParametersImpl params;

    @Before
    public void setUp() throws Exception {
        params = new JndiBuilderParametersImpl();
    }

    /**
     * Tests whether the parameters map contains inherited properties, too.
     */

    /**
     * Tests whether properties can be set through BeanUtils.
     */

    /**
     * Tests whether a JNDI context can be set.
     */

    /**
     * Tests whether a prefix can be set.
     */

    @Test
    public void testGetParametersBaseProperties_1_oe() {
        params.setPrefix("somePrefix");
        params.setThrowExceptionOnMissing(true);
        final Map<String, Object> paramsMap = params.getParameters();
        assertEquals("Wrong exception flag", Boolean.TRUE, paramsMap.get("throwExceptionOnMissing"));
    }

    @Test
    public void testSetBeanProperties_1_oe() throws Exception {
        final Context ctx = EasyMock.createMock(Context.class);
        EasyMock.replay(ctx);
        final String prefix = "testJndiPrefix";
        BeanHelper.setProperty(params, "context", ctx);
        BeanHelper.setProperty(params, "prefix", prefix);
        final Map<String, Object> paramsMap = params.getParameters();
        assertSame("Context not in map", ctx, paramsMap.get("context"));
    }

    @Test
    public void testSetBeanProperties_2_oe() throws Exception {
        final Context ctx = EasyMock.createMock(Context.class);
        EasyMock.replay(ctx);
        final String prefix = "testJndiPrefix";
        BeanHelper.setProperty(params, "context", ctx);
        BeanHelper.setProperty(params, "prefix", prefix);
        final Map<String, Object> paramsMap = params.getParameters();
        // removed other assertion
        assertEquals("Prefix not in map", prefix, paramsMap.get("prefix"));
    }

    @Test
    public void testSetContext_1_oe() {
        final Context ctx = EasyMock.createMock(Context.class);
        EasyMock.replay(ctx);
        assertSame("Wrong result", params, params.setContext(ctx));
    }

    @Test
    public void testSetPrefix_1_oe() {
        final String prefix = "testJndiPrefix";
        assertSame("Wrong result", params, params.setPrefix(prefix));
    }

}
