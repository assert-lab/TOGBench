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
package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Abstract test of an ExceptionContext implementation.
 */
public abstract class AbstractExceptionContextTest_OE25Dev<T extends ExceptionContext & Serializable> {

    protected static final String TEST_MESSAGE_2 = "This is monotonous";
    protected static final String TEST_MESSAGE = "Test Message";
    protected T exceptionContext;

    protected static class ObjectWithFaultyToString {
        @Override
        public String toString() {
            throw new RuntimeException("Crap");
        }
    }


    @BeforeEach
    public void setUp() throws Exception {
        exceptionContext
            .addContextValue("test1", null)
            .addContextValue("test2", "some value")
            .addContextValue("test Date", new Date())
            .addContextValue("test Nbr", Integer.valueOf(5))
            .addContextValue("test Poorly written obj", new ObjectWithFaultyToString());
    }

    @Test
    public void testAddContextValue_1_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        assertTrue(message.contains(TEST_MESSAGE));
    }

    @Test
    public void testAddContextValue_2_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        assertTrue(message.contains("test1"));
    }

    @Test
    public void testAddContextValue_3_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        assertTrue(message.contains("test2"));
    }

    @Test
    public void testAddContextValue_4_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(message.contains("test Date"));
    }

    @Test
    public void testAddContextValue_5_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(message.contains("test Nbr"));
    }

    @Test
    public void testAddContextValue_6_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(message.contains("some value"));
    }

    @Test
    public void testAddContextValue_7_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(message.contains("5"));
    }

    @Test
    public void testAddContextValue_8_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull(exceptionContext.getFirstContextValue("test1"));
    }

    @Test
    public void testAddContextValue_9_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("some value", exceptionContext.getFirstContextValue("test2"));
    }

    @Test
    public void testAddContextValue_10_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(5, exceptionContext.getContextLabels().size());
    }

    @Test
    public void testAddContextValue_11_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(exceptionContext.getContextLabels().contains("test1"));
    }

    @Test
    public void testAddContextValue_12_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(exceptionContext.getContextLabels().contains("test2"));
    }

    @Test
    public void testAddContextValue_13_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(exceptionContext.getContextLabels().contains("test Date"));
    }

    @Test
    public void testAddContextValue_14_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(exceptionContext.getContextLabels().contains("test Nbr"));
    }

    @Test
    public void testAddContextValue_15_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");
        assertEquals(5, exceptionContext.getContextLabels().size());
    }

    @Test
    public void testAddContextValue_16_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");
        // removed other assertion
        assertTrue(exceptionContext.getContextLabels().contains("test2"));
    }

    @Test
    public void testAddContextValue_17_oe() {
        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");
        // removed other assertion
        // removed other assertion

        final String contextMessage = exceptionContext.getFormattedExceptionMessage(null);
        assertFalse(contextMessage.contains(TEST_MESSAGE));
    }

    @Test
    public void testSetContextValue_1_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        assertTrue(message.contains(TEST_MESSAGE));
    }

    @Test
    public void testSetContextValue_2_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        assertTrue(message.contains("test Poorly written obj"));
    }

    @Test
    public void testSetContextValue_3_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        assertTrue(message.contains("Crap"));
    }

    @Test
    public void testSetContextValue_4_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull(exceptionContext.getFirstContextValue("crap"));
    }

    @Test
    public void testSetContextValue_5_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(exceptionContext.getFirstContextValue("test Poorly written obj") instanceof ObjectWithFaultyToString);
    }

    @Test
    public void testSetContextValue_6_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(7, exceptionContext.getContextEntries().size());
    }

    @Test
    public void testSetContextValue_7_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(6, exceptionContext.getContextLabels().size());
    }

    @Test
    public void testSetContextValue_8_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(exceptionContext.getContextLabels().contains("test Poorly written obj"));
    }

    @Test
    public void testSetContextValue_9_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(exceptionContext.getContextLabels().contains("crap"));
    }

    @Test
    public void testSetContextValue_10_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test Poorly written obj", "replacement");

        assertEquals(7, exceptionContext.getContextEntries().size());
    }

    @Test
    public void testSetContextValue_11_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test Poorly written obj", "replacement");

        // removed other assertion
        assertEquals(6, exceptionContext.getContextLabels().size());
    }

    @Test
    public void testSetContextValue_12_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test Poorly written obj", "replacement");

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test2", "another");

        assertEquals(6, exceptionContext.getContextEntries().size());
    }

    @Test
    public void testSetContextValue_13_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test Poorly written obj", "replacement");

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test2", "another");

        // removed other assertion
        assertEquals(6, exceptionContext.getContextLabels().size());
    }

    @Test
    public void testSetContextValue_14_oe() {
        exceptionContext.addContextValue("test2", "different value");
        exceptionContext.setContextValue("test3", "3");

        final String message = exceptionContext.getFormattedExceptionMessage(TEST_MESSAGE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test Poorly written obj", "replacement");

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test2", "another");

        // removed other assertion
        // removed other assertion

        final String contextMessage = exceptionContext.getFormattedExceptionMessage(null);
        assertFalse(contextMessage.contains(TEST_MESSAGE));
    }

    @Test
    public void testGetFirstContextValue_1_oe() {
        exceptionContext.addContextValue("test2", "different value");

        assertNull(exceptionContext.getFirstContextValue("test1"));
    }

    @Test
    public void testGetFirstContextValue_2_oe() {
        exceptionContext.addContextValue("test2", "different value");

        // removed other assertion
        assertEquals("some value", exceptionContext.getFirstContextValue("test2"));
    }

    @Test
    public void testGetFirstContextValue_3_oe() {
        exceptionContext.addContextValue("test2", "different value");

        // removed other assertion
        // removed other assertion
        assertNull(exceptionContext.getFirstContextValue("crap"));
    }

    @Test
    public void testGetFirstContextValue_4_oe() {
        exceptionContext.addContextValue("test2", "different value");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test2", "another");

        assertEquals("another", exceptionContext.getFirstContextValue("test2"));
    }

    @Test
    public void testGetContextValues_1_oe() {
        exceptionContext.addContextValue("test2", "different value");

        assertEquals(exceptionContext.getContextValues("test1"), Collections.singletonList(null));
    }

    @Test
    public void testGetContextValues_2_oe() {
        exceptionContext.addContextValue("test2", "different value");

        // removed other assertion
        assertEquals(exceptionContext.getContextValues("test2"), Arrays.asList("some value", "different value"));
    }

    @Test
    public void testGetContextValues_3_oe() {
        exceptionContext.addContextValue("test2", "different value");

        // removed other assertion
        // removed other assertion

        exceptionContext.setContextValue("test2", "another");

        assertEquals("another", exceptionContext.getFirstContextValue("test2"));
    }

    @Test
    public void testGetContextLabels_1_oe() {
        assertEquals(5, exceptionContext.getContextEntries().size());
    }

    @Test
    public void testGetContextLabels_2_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final Set<String> labels = exceptionContext.getContextLabels();
        assertEquals(6, exceptionContext.getContextEntries().size());
    }

    @Test
    public void testGetContextLabels_3_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final Set<String> labels = exceptionContext.getContextLabels();
        // removed other assertion
        assertEquals(5, labels.size());
    }

    @Test
    public void testGetContextLabels_4_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final Set<String> labels = exceptionContext.getContextLabels();
        // removed other assertion
        // removed other assertion
        assertTrue(labels.contains("test1"));
    }

    @Test
    public void testGetContextLabels_5_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final Set<String> labels = exceptionContext.getContextLabels();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(labels.contains("test2"));
    }

    @Test
    public void testGetContextLabels_6_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final Set<String> labels = exceptionContext.getContextLabels();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(labels.contains("test Date"));
    }

    @Test
    public void testGetContextLabels_7_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final Set<String> labels = exceptionContext.getContextLabels();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(labels.contains("test Nbr"));
    }

    @Test
    public void testGetContextEntries_1_oe() {
        assertEquals(5, exceptionContext.getContextEntries().size());
    }

    @Test
    public void testGetContextEntries_2_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final List<Pair<String, Object>> entries = exceptionContext.getContextEntries();
        assertEquals(6, entries.size());
    }

    @Test
    public void testGetContextEntries_3_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final List<Pair<String, Object>> entries = exceptionContext.getContextEntries();
        // removed other assertion
        assertEquals("test1", entries.get(0).getKey());
    }

    @Test
    public void testGetContextEntries_4_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final List<Pair<String, Object>> entries = exceptionContext.getContextEntries();
        // removed other assertion
        // removed other assertion
        assertEquals("test2", entries.get(1).getKey());
    }

    @Test
    public void testGetContextEntries_5_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final List<Pair<String, Object>> entries = exceptionContext.getContextEntries();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("test Date", entries.get(2).getKey());
    }

    @Test
    public void testGetContextEntries_6_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final List<Pair<String, Object>> entries = exceptionContext.getContextEntries();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("test Nbr", entries.get(3).getKey());
    }

    @Test
    public void testGetContextEntries_7_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final List<Pair<String, Object>> entries = exceptionContext.getContextEntries();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("test Poorly written obj", entries.get(4).getKey());
    }

    @Test
    public void testGetContextEntries_8_oe() {
        // removed other assertion

        exceptionContext.addContextValue("test2", "different value");

        final List<Pair<String, Object>> entries = exceptionContext.getContextEntries();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("test2", entries.get(5).getKey());
    }

    @Test
    public void testJavaSerialization_1_oe() {
        exceptionContext.setContextValue("test Poorly written obj", "serializable replacement");

        final T clone = SerializationUtils.deserialize(SerializationUtils.serialize(exceptionContext));

        assertEquals(exceptionContext.getFormattedExceptionMessage(null), clone.getFormattedExceptionMessage(null));
    }

}
