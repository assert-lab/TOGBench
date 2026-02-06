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
package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TypeLiteralTest_OE25Dev {

    @Test
    public void testBasic_1_oe() {
        assertTrue(TypeUtils.equals(String.class, new TypeLiteral<String>() {}.value));
    }

    @Test
    public void testBasic_2_oe() {
        // removed other assertion
        assertTrue(TypeUtils.equals(TypeUtils.parameterize(List.class, String.class), new TypeLiteral<List<String>>() {}.value));
    }

    @Test
    public void testTyped_1_oe() {
        final Typed<String> stringType = new TypeLiteral<String>() {};
        assertTrue(TypeUtils.equals(String.class, stringType.getType()));
    }

    @Test
    public void testTyped_2_oe() {
        final Typed<String> stringType = new TypeLiteral<String>() {};
        // removed other assertion
        final Typed<List<String>> listOfStringType = new TypeLiteral<List<String>>() {};
        assertTrue(TypeUtils.equals(TypeUtils.parameterize(List.class, String.class), listOfStringType.getType()));
    }

    @Test
    public void testEquals_1_oe() {
        assertEquals(new TypeLiteral<String>() {}, new TypeLiteral<String>() {});
    }

    @Test
    public void testEquals_2_oe() {
        // removed other assertion
        assertEquals(new TypeLiteral<List<String>>() {}, new TypeLiteral<List<String>>() {});
    }

    @Test
    public void testEquals_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNotEquals(new TypeLiteral<String>() {}, new TypeLiteral<List<String>>() {});
    }

    @Test
    public void testRaw_1_oe() {
        assertThrows(NullPointerException.class, () -> new TypeLiteral() {});
    }

}
