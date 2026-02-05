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
package org.apache.commons.dbutils.handlers.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

public class DatePropertyHandlerTest_OE25Dev {
    private DatePropertyHandler handler;

    @Before
    public void setUp() {
        this.handler = new DatePropertyHandler();
    }

@Test
    public void testMatch_1_oe() {
        assertTrue(handler.match(java.sql.Date.class, new java.util.Date()));
    }

@Test
    public void testMatch_2_oe() {
        // removed other assertion
        assertTrue(handler.match(java.sql.Time.class, new java.util.Date()));
    }

@Test
    public void testMatch_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(handler.match(java.sql.Timestamp.class, new java.util.Date()));
    }

@Test
    public void testMatchNegative_1_oe() {
        assertFalse(handler.match(Float.class, null));
    }

@Test
    public void testMatchNegative_2_oe() {
        // removed other assertion
        assertFalse(handler.match(Float.class, new java.util.Date()));
    }

@Test
    public void testApplyTypeOfDate_1_oe() throws Exception {
        assertEquals(java.sql.Date.class, handler.apply(java.sql.Date.class, new java.util.Date()).getClass());
    }

@Test
    public void testApplyTypeOfTime_1_oe() throws Exception {
        assertEquals(Time.class, handler.apply(java.sql.Time.class, new java.util.Date()).getClass());
    }

@Test
    public void testApplyTypeOfTimestamp_1_oe() throws Exception {
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        assertEquals(Timestamp.class, handler.apply(java.sql.Timestamp.class, ts).getClass());
    }

}
