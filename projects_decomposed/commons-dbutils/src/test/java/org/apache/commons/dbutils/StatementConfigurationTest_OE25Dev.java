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
package org.apache.commons.dbutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StatementConfigurationTest_OE25Dev {
    /**
     * Test that an empty builder yields null values for all configuration settings.
     */
    @Test
    public void testEmptyBuilder() {
        StatementConfiguration config = new StatementConfiguration.Builder().build();

        assertFalse(config.isFetchDirectionSet());
        assertFalse(config.isFetchSizeSet());
        assertFalse(config.isMaxFieldSizeSet());
        assertFalse(config.isMaxRowsSet());
        assertFalse(config.isQueryTimeoutSet());
    }

    /**
     * Test that a builder with all values set yields like values in the constructed configuration.
     */
    @Test
    public void testBuilder() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();

        assertTrue(config.isFetchDirectionSet());
        assertEquals(Integer.valueOf(1), config.getFetchDirection());

        assertTrue(config.isFetchSizeSet());
        assertEquals(Integer.valueOf(2), config.getFetchSize());

        assertTrue(config.isMaxFieldSizeSet());
        assertEquals(Integer.valueOf(3), config.getMaxFieldSize());

        assertTrue(config.isMaxRowsSet());
        assertEquals(Integer.valueOf(4), config.getMaxRows());

        assertTrue(config.isQueryTimeoutSet());
        assertEquals(Integer.valueOf(5), config.getQueryTimeout());
    }

    /**
     * Test that the constructor of <code>StatementConfiguration</code> correctly sets all values.
     */
    @Test
    public void testConstructor() {
        StatementConfiguration config = new StatementConfiguration(1, 2, 3, 4, 5);

        assertEquals(Integer.valueOf(1), config.getFetchDirection());
        assertEquals(Integer.valueOf(2), config.getFetchSize());
        assertEquals(Integer.valueOf(3), config.getMaxFieldSize());
        assertEquals(Integer.valueOf(4), config.getMaxRows());
        assertEquals(Integer.valueOf(5), config.getQueryTimeout());
    }

    @Test
    public void testEmptyBuilder_1_oe() {
        StatementConfiguration config = new StatementConfiguration.Builder().build();

        assertFalse(config.isFetchDirectionSet());
    }

    @Test
    public void testEmptyBuilder_2_oe() {
        StatementConfiguration config = new StatementConfiguration.Builder().build();

        assertFalse(config.isFetchSizeSet());
    }

    @Test
    public void testEmptyBuilder_3_oe() {
        StatementConfiguration config = new StatementConfiguration.Builder().build();

        assertFalse(config.isMaxFieldSizeSet());
    }

    @Test
    public void testEmptyBuilder_4_oe() {
        StatementConfiguration config = new StatementConfiguration.Builder().build();

        assertFalse(config.isMaxRowsSet());
    }

    @Test
    public void testEmptyBuilder_5_oe() {
        StatementConfiguration config = new StatementConfiguration.Builder().build();

        assertFalse(config.isQueryTimeoutSet());
    }

    @Test
    public void testBuilder_1_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();

        assertTrue(config.isFetchDirectionSet());
    }

    @Test
    public void testBuilder_2_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();

        assertEquals(Integer.valueOf(1), config.getFetchDirection());
    }

    @Test
    public void testBuilder_3_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();


        assertTrue(config.isFetchSizeSet());
    }

    @Test
    public void testBuilder_4_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();


        assertEquals(Integer.valueOf(2), config.getFetchSize());
    }

    @Test
    public void testBuilder_5_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();



        assertTrue(config.isMaxFieldSizeSet());
    }

    @Test
    public void testBuilder_6_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();



        assertEquals(Integer.valueOf(3), config.getMaxFieldSize());
    }

    @Test
    public void testBuilder_7_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();




        assertTrue(config.isMaxRowsSet());
    }

    @Test
    public void testBuilder_8_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();




        assertEquals(Integer.valueOf(4), config.getMaxRows());
    }

    @Test
    public void testBuilder_9_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();





        assertTrue(config.isQueryTimeoutSet());
    }

    @Test
    public void testBuilder_10_oe() {
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder()
                .fetchDirection(1)
                .fetchSize(2)
                .maxFieldSize(3)
                .maxRows(4)
                .queryTimeout(5);
        StatementConfiguration config = builder.build();





        assertEquals(Integer.valueOf(5), config.getQueryTimeout());
    }

    @Test
    public void testConstructor_1_oe() {
        StatementConfiguration config = new StatementConfiguration(1, 2, 3, 4, 5);

        assertEquals(Integer.valueOf(1), config.getFetchDirection());
    }

    @Test
    public void testConstructor_2_oe() {
        StatementConfiguration config = new StatementConfiguration(1, 2, 3, 4, 5);

        assertEquals(Integer.valueOf(2), config.getFetchSize());
    }

    @Test
    public void testConstructor_3_oe() {
        StatementConfiguration config = new StatementConfiguration(1, 2, 3, 4, 5);

        assertEquals(Integer.valueOf(3), config.getMaxFieldSize());
    }

    @Test
    public void testConstructor_4_oe() {
        StatementConfiguration config = new StatementConfiguration(1, 2, 3, 4, 5);

        assertEquals(Integer.valueOf(4), config.getMaxRows());
    }

    @Test
    public void testConstructor_5_oe() {
        StatementConfiguration config = new StatementConfiguration(1, 2, 3, 4, 5);

        assertEquals(Integer.valueOf(5), config.getQueryTimeout());
    }

}
