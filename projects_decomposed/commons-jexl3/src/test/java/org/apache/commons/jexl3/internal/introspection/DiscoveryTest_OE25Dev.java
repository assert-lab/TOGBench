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
package org.apache.commons.jexl3.internal.introspection;

import java.io.Serializable;
import org.apache.commons.jexl3.JexlTestCase;
import org.apache.commons.jexl3.internal.Engine;
import org.apache.commons.jexl3.introspection.JexlPropertyGet;
import org.apache.commons.jexl3.introspection.JexlPropertySet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.jexl3.introspection.JexlMethod;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for checking introspection discovery.
 *
 * @since 2.0
 */
public class DiscoveryTest_OE25Dev extends JexlTestCase {
    public DiscoveryTest_OE25Dev() {
        super("DiscoveryTest_OE25Dev");
    }

    public static class Duck {
        private String value;
        private String eulav;

        public Duck(final String v, final String e) {
            value = v;
            eulav = e;
        }

        public String get(final String prop) {
            if ("value".equals(prop)) {
                return value;
            }
            if ("eulav".equals(prop)) {
                return eulav;
            }
            return "no such property";
        }

        public void set(final String prop, final String v) {
            if ("value".equals(prop)) {
                value = v;
            } else if ("eulav".equals(prop)) {
                eulav = v;
            }
        }
    }

    public static class Bean {
        private String value;
        private String eulav;
        private boolean flag;

        public Bean(final String v, final String e) {
            value = v;
            eulav = e;
            flag = true;
        }

        public String getValue() {
            return value;
        }

        public void setValue(final String v) {
            value = v;
        }

        public String getEulav() {
            return eulav;
        }

        public void setEulav(final String v) {
            eulav = v;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(final boolean f) {
            flag = f;
        }
    }

    public static class Bulgroz {
        public Object list(final int x) {
            return 0;
        }
        public Object list(final String x) {
            return 1;
        }
        public Object list(final Object x) {
            return 2;
        }
        public Object list(final int x, final Object...y) {
            return 3;
        }
        public Object list(final int x, final int y) {
            return 4;
        }
        public Object list(final String x, final Object...y) {
            return 5;
        }
        public Object list(final String x, final String y) {
            return 6;
        }
        public Object list(final Object x, final Object...y) {
            return 7;
        }
        public Object list(final Object x, final Object y) {
            return 8;
        }
        public Object amb(final Serializable x) {
            return -1;
        }
        public Object amb(final Number x) {
            return -2;
        }
    }

    @Test
    public void testBeanIntrospection_1_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        Assert.assertTrue("bean property getter", get instanceof PropertyGetExecutor);
    }

    @Test
    public void testBeanIntrospection_2_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        Assert.assertTrue("bean property setter", set instanceof PropertySetExecutor);
    }

    @Test
    public void testBeanIntrospection_3_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        Assert.assertEquals(get, uber.getPropertyGet(bean, "value"));
    }

    @Test
    public void testBeanIntrospection_4_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        Assert.assertEquals(set, uber.getPropertySet(bean, "value", "foo"));
    }

    @Test
    public void testBeanIntrospection_5_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        Assert.assertNotEquals(get, uber.getPropertyGet(bean, "eulav"));
    }

    @Test
    public void testBeanIntrospection_6_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        Assert.assertNotEquals(set, uber.getPropertySet(bean, "eulav", "foo"));
    }

    @Test
    public void testBeanIntrospection_7_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(bean, "bar");
        Assert.assertEquals("bar", bar);
    }

    @Test
    public void testBeanIntrospection_8_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(bean, "bar");
        // removed other assertion
        // getter should return last value
        Assert.assertEquals("bar", get.invoke(bean));
    }

    @Test
    public void testBeanIntrospection_9_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(bean, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property
        final Object quux = set.tryInvoke(bean, "value", "quux");
        Assert.assertEquals("quux", quux);
    }

    @Test
    public void testBeanIntrospection_10_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(bean, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property
        final Object quux = set.tryInvoke(bean, "value", "quux");
        // removed other assertion
        Assert.assertEquals("quux", get.invoke(bean));
    }

    @Test
    public void testBeanIntrospection_11_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Bean bean = new Bean("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(bean, "value");
        final JexlPropertySet set = uber.getPropertySet(bean, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(bean, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property
        final Object quux = set.tryInvoke(bean, "value", "quux");
        // removed other assertion
        // removed other assertion
        // tryExecute should fail on different property
        Assert.assertEquals(AbstractExecutor.TRY_FAILED, set.tryInvoke(bean, "eulav", "nope"));
    }

    @Test
    public void testDuckIntrospection_1_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        Assert.assertTrue("duck property getter", get instanceof DuckGetExecutor);
    }

    @Test
    public void testDuckIntrospection_2_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        Assert.assertTrue("duck property setter", set instanceof DuckSetExecutor);
    }

    @Test
    public void testDuckIntrospection_3_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        Assert.assertEquals(get, uber.getPropertyGet(duck, "value"));
    }

    @Test
    public void testDuckIntrospection_4_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        Assert.assertEquals(set, uber.getPropertySet(duck, "value", "foo"));
    }

    @Test
    public void testDuckIntrospection_5_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        Assert.assertNotEquals(get, uber.getPropertyGet(duck, "eulav"));
    }

    @Test
    public void testDuckIntrospection_6_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        Assert.assertNotEquals(set, uber.getPropertySet(duck, "eulav", "foo"));
    }

    @Test
    public void testDuckIntrospection_7_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(duck, "bar");
        Assert.assertEquals("bar", bar);
    }

    @Test
    public void testDuckIntrospection_8_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(duck, "bar");
        // removed other assertion
        // getter should return last value
        Assert.assertEquals("bar", get.invoke(duck));
    }

    @Test
    public void testDuckIntrospection_9_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(duck, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property
        final Object quux = set.tryInvoke(duck, "value", "quux");
        Assert.assertEquals("quux", quux);
    }

    @Test
    public void testDuckIntrospection_10_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(duck, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property
        final Object quux = set.tryInvoke(duck, "value", "quux");
        // removed other assertion
        Assert.assertEquals("quux", get.invoke(duck));
    }

    @Test
    public void testDuckIntrospection_11_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Duck duck = new Duck("JEXL", "LXEJ");

        final JexlPropertyGet get = uber.getPropertyGet(duck, "value");
        final JexlPropertySet set = uber.getPropertySet(duck, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(duck, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property
        final Object quux = set.tryInvoke(duck, "value", "quux");
        // removed other assertion
        // removed other assertion
        // tryExecute should fail on different property
        Assert.assertEquals(AbstractExecutor.TRY_FAILED, set.tryInvoke(duck, "eulav", "nope"));
    }

    @Test
    public void testListIntrospection_1_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        Assert.assertTrue("list property getter", get instanceof ListGetExecutor);
    }

    @Test
    public void testListIntrospection_2_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        Assert.assertTrue("list property setter", set instanceof ListSetExecutor);
    }

    @Test
    public void testListIntrospection_3_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        Assert.assertEquals(get, uber.getPropertyGet(list, 1));
    }

    @Test
    public void testListIntrospection_4_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        Assert.assertEquals(set, uber.getPropertySet(list, 1, "foo"));
    }

    @Test
    public void testListIntrospection_5_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        Assert.assertNotEquals(get, uber.getPropertyGet(list, 0));
    }

    @Test
    public void testListIntrospection_6_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        Assert.assertNotEquals(get, uber.getPropertySet(list, 0, "foo"));
    }

    @Test
    public void testListIntrospection_7_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(list, "bar");
        Assert.assertEquals("bar", bar);
    }

    @Test
    public void testListIntrospection_8_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(list, "bar");
        // removed other assertion
        // getter should return last value
        Assert.assertEquals("bar", get.invoke(list));
    }

    @Test
    public void testListIntrospection_9_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(list, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on integer property
        final Object quux = set.tryInvoke(list, 1, "quux");
        Assert.assertEquals("quux", quux);
    }

    @Test
    public void testListIntrospection_10_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(list, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on integer property
        final Object quux = set.tryInvoke(list, 1, "quux");
        // removed other assertion
        // getter should return last value
        Assert.assertEquals("quux", get.invoke(list));
    }

    @Test
    public void testListIntrospection_11_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final List<Object> list = new ArrayList<Object>();
        list.add("LIST");
        list.add("TSIL");

        final JexlPropertyGet get = uber.getPropertyGet(list, 1);
        final JexlPropertySet set = uber.getPropertySet(list, 1, "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(list, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on integer property
        final Object quux = set.tryInvoke(list, 1, "quux");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should fail on non-integer property class
        Assert.assertEquals(AbstractExecutor.TRY_FAILED, set.tryInvoke(list, "eulav", "nope"));
    }

    @Test
    public void testMapIntrospection_1_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        Assert.assertTrue("map property getter", get instanceof MapGetExecutor);
    }

    @Test
    public void testMapIntrospection_2_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        Assert.assertTrue("map property setter", set instanceof MapSetExecutor);
    }

    @Test
    public void testMapIntrospection_3_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        Assert.assertEquals(get, uber.getPropertyGet(map, "value"));
    }

    @Test
    public void testMapIntrospection_4_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        Assert.assertEquals(set, uber.getPropertySet(map, "value", "foo"));
    }

    @Test
    public void testMapIntrospection_5_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        Assert.assertNotEquals(get, uber.getPropertyGet(map, "eulav"));
    }

    @Test
    public void testMapIntrospection_6_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        Assert.assertNotEquals(get, uber.getPropertySet(map, "eulav", "foo"));
    }

    @Test
    public void testMapIntrospection_7_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(map, "bar");
        Assert.assertEquals("bar", bar);
    }

    @Test
    public void testMapIntrospection_8_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(map, "bar");
        // removed other assertion
        // getter should return last value
        Assert.assertEquals("bar", get.invoke(map));
    }

    @Test
    public void testMapIntrospection_9_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(map, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property class
        final Object quux = set.tryInvoke(map, "value", "quux");
        Assert.assertEquals("quux", quux);
    }

    @Test
    public void testMapIntrospection_10_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(map, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property class
        final Object quux = set.tryInvoke(map, "value", "quux");
        // removed other assertion
        // getter should return last value
        Assert.assertEquals("quux", get.invoke(map));
    }

    @Test
    public void testMapIntrospection_11_oe() throws Exception {
        final Uberspect uber = Engine.getUberspect(null, null);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", "MAP");
        map.put("eulav", "PAM");

        final JexlPropertyGet get = uber.getPropertyGet(map, "value");
        final JexlPropertySet set = uber.getPropertySet(map, "value", "foo");
        // removed other assertion
        // removed other assertion
        // introspector and uberspect should return same result
        // removed other assertion
        // removed other assertion
        // different property should return different setter/getter
        // removed other assertion
        // removed other assertion
        // setter returns argument
        final Object bar = set.invoke(map, "bar");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should succeed on same property class
        final Object quux = set.tryInvoke(map, "value", "quux");
        // removed other assertion
        // getter should return last value
        // removed other assertion
        // tryExecute should fail on different property class
        Assert.assertEquals(AbstractExecutor.TRY_FAILED, set.tryInvoke(map, 1, "nope"));
    }

    @Test
    public void testMethodIntrospection_1_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testMethodIntrospection_2_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        Assert.assertEquals(1, result);
    }

    @Test
    public void testMethodIntrospection_3_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        Assert.assertEquals(2, result);
    }

    @Test
    public void testMethodIntrospection_4_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testMethodIntrospection_5_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testMethodIntrospection_6_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        Assert.assertEquals(4, result);
    }

    @Test
    public void testMethodIntrospection_7_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        Assert.assertEquals(5, result);
    }

    @Test
    public void testMethodIntrospection_8_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        Assert.assertEquals(6, result);
    }

    @Test
    public void testMethodIntrospection_9_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, bulgroz);
        Assert.assertEquals(8, result);
    }

    @Test
    public void testMethodIntrospection_10_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, 1, bulgroz);
        Assert.assertEquals(7, result);
    }

    @Test
    public void testMethodIntrospection_11_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, "1");
        result = jmethod.invoke(bulgroz, bulgroz, 1, "1");
        Assert.assertEquals(7, result);
    }

    @Test
    public void testMethodIntrospection_12_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, "1");
        result = jmethod.invoke(bulgroz, bulgroz, 1, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", (Object) null);
        result = jmethod.invoke(bulgroz,  (Object) null);
        Assert.assertEquals(2, result);
    }

    @Test
    public void testMethodIntrospection_13_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, "1");
        result = jmethod.invoke(bulgroz, bulgroz, 1, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", (Object) null);
        result = jmethod.invoke(bulgroz,  (Object) null);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, (Object) null);
        result = jmethod.invoke(bulgroz, bulgroz, (Object) null);
        Assert.assertEquals(8, result);
    }

    @Test
    public void testMethodIntrospection_14_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, "1");
        result = jmethod.invoke(bulgroz, bulgroz, 1, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", (Object) null);
        result = jmethod.invoke(bulgroz,  (Object) null);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, (Object) null);
        result = jmethod.invoke(bulgroz, bulgroz, (Object) null);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", null, "1");
        result = jmethod.invoke(bulgroz, null, "1");
        Assert.assertEquals(8, result);
    }

    @Test
    public void testMethodIntrospection_15_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, "1");
        result = jmethod.invoke(bulgroz, bulgroz, 1, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", (Object) null);
        result = jmethod.invoke(bulgroz,  (Object) null);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, (Object) null);
        result = jmethod.invoke(bulgroz, bulgroz, (Object) null);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", null, "1");
        result = jmethod.invoke(bulgroz, null, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, null, null);
        result = jmethod.invoke(bulgroz, bulgroz, null, null);
        Assert.assertEquals(7, result);
    }

    @Test
    public void testMethodIntrospection_16_oe() throws Exception {
        final Uberspect uber = new Uberspect(null, null);
        final Bulgroz bulgroz = new Bulgroz();
        JexlMethod jmethod;
        Object result;
        jmethod = uber.getMethod(bulgroz, "list", 0);
        result = jmethod.invoke(bulgroz, 0);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1");
        result = jmethod.invoke(bulgroz, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, 1, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", 1, 2);
        result = jmethod.invoke(bulgroz, 1, 2);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", bulgroz);
        result = jmethod.invoke(bulgroz, "1", bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", "1", "2");
        result = jmethod.invoke(bulgroz, "1", "2");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, bulgroz);
        result = jmethod.invoke(bulgroz, bulgroz, 1, bulgroz);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, 1, "1");
        result = jmethod.invoke(bulgroz, bulgroz, 1, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", (Object) null);
        result = jmethod.invoke(bulgroz,  (Object) null);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, (Object) null);
        result = jmethod.invoke(bulgroz, bulgroz, (Object) null);
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", null, "1");
        result = jmethod.invoke(bulgroz, null, "1");
        // removed other assertion
        jmethod = uber.getMethod(bulgroz, "list", bulgroz, null, null);
        result = jmethod.invoke(bulgroz, bulgroz, null, null);
        // removed other assertion

        jmethod = uber.getMethod(bulgroz, "amb", 3d);
        Assert.assertNotNull(null, jmethod);
    }

}
