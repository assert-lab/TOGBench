package org.apache.commons.jcs3.utils.config;

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

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * Test property settings
 *
 * @author Thomas Vandahl
 *
 */
public class PropertySetterUnitTest_OE25Dev
{
    enum EnumTest { ONE, TWO, THREE }

    @Test
    public void testConvertArg()
    {
        final PropertySetter ps = new PropertySetter(this);
        final Object s = ps.convertArg("test", String.class);
        assertEquals("Should be a string", "test", s);

        final Object i = ps.convertArg("1", Integer.TYPE);
        assertEquals("Should be an integer", Integer.valueOf(1), i);

        final Object l = ps.convertArg("1", Long.TYPE);
        assertEquals("Should be a long", Long.valueOf(1), l);

        final Object b = ps.convertArg("true", Boolean.TYPE);
        assertEquals("Should be a boolean", Boolean.TRUE, b);

        final Object e = ps.convertArg("TWO", EnumTest.class);
        assertEquals("Should be an enum", EnumTest.TWO, e);

        final Object f = ps.convertArg("test.conf", File.class);
        assertTrue("Should be a file", f instanceof File);
    }

    @Test
    public void testConvertArg_1_oe()
    {
        final PropertySetter ps = new PropertySetter(this);
        final Object s = ps.convertArg("test", String.class);
        assertEquals("Should be a string", "test", s);
    }

    @Test
    public void testConvertArg_2_oe()
    {
        final PropertySetter ps = new PropertySetter(this);
        final Object s = ps.convertArg("test", String.class);

        final Object i = ps.convertArg("1", Integer.TYPE);
        assertEquals("Should be an integer", Integer.valueOf(1), i);
    }

    @Test
    public void testConvertArg_3_oe()
    {
        final PropertySetter ps = new PropertySetter(this);
        final Object s = ps.convertArg("test", String.class);

        final Object i = ps.convertArg("1", Integer.TYPE);

        final Object l = ps.convertArg("1", Long.TYPE);
        assertEquals("Should be a long", Long.valueOf(1), l);
    }

    @Test
    public void testConvertArg_4_oe()
    {
        final PropertySetter ps = new PropertySetter(this);
        final Object s = ps.convertArg("test", String.class);

        final Object i = ps.convertArg("1", Integer.TYPE);

        final Object l = ps.convertArg("1", Long.TYPE);

        final Object b = ps.convertArg("true", Boolean.TYPE);
        assertEquals("Should be a boolean", Boolean.TRUE, b);
    }

    @Test
    public void testConvertArg_5_oe()
    {
        final PropertySetter ps = new PropertySetter(this);
        final Object s = ps.convertArg("test", String.class);

        final Object i = ps.convertArg("1", Integer.TYPE);

        final Object l = ps.convertArg("1", Long.TYPE);

        final Object b = ps.convertArg("true", Boolean.TYPE);

        final Object e = ps.convertArg("TWO", EnumTest.class);
        assertEquals("Should be an enum", EnumTest.TWO, e);
    }

    @Test
    public void testConvertArg_6_oe()
    {
        final PropertySetter ps = new PropertySetter(this);
        final Object s = ps.convertArg("test", String.class);

        final Object i = ps.convertArg("1", Integer.TYPE);

        final Object l = ps.convertArg("1", Long.TYPE);

        final Object b = ps.convertArg("true", Boolean.TYPE);

        final Object e = ps.convertArg("TWO", EnumTest.class);

        final Object f = ps.convertArg("test.conf", File.class);
        assertTrue("Should be a file", f instanceof File);
    }

}
