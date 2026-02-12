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
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.arch.Processor;
import org.apache.commons.lang3.arch.Processor.Arch;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link ArchUtils}.
 */
public class ArchUtilsTest_OE25Dev {

    private static final String IA64 = "ia64";
    private static final String IA64_32 = "ia64_32";
    private static final String PPC = "ppc";
    private static final String PPC64 = "ppc64";
    private static final String X86 = "x86";
    private static final String X86_64 = "x86_64";

    private void assertEqualsArchNotNull(final Processor.Arch arch, final Processor processor) {
        assertNotNull(arch);
        assertNotNull(processor);
        assertEquals(arch, processor.getArch());
    }

    private void assertEqualsTypeNotNull(final Processor.Type type, final Processor processor) {
        assertNotNull(type);
        assertNotNull(processor);
        assertEquals(type, processor.getType());
    }

    private void assertNotEqualsArchNotNull(final Processor.Arch arch, final Processor processor) {
        assertNotNull(arch);
        assertNotNull(processor);
        assertNotEquals(arch, processor.getArch());
    }

    private void assertNotEqualsTypeNotNull(final Processor.Type type, final Processor processor) {
        assertNotNull(type);
        assertNotNull(processor);
        assertNotEquals(type, processor.getType());
    }

    @Test
    public void testArch_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        assertEqualsTypeNotNull(Processor.Type.X86, processor);
    }

    @Test
    public void testArch_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        assertTrue(processor.isX86());
    }

    @Test
    public void testArch_4_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(processor.isPPC());
    }

    @Test
    public void testArch_5_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        assertEqualsTypeNotNull(Processor.Type.X86, processor);
    }

    @Test
    public void testArch_6_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        assertTrue(processor.isX86());
    }

    @Test
    public void testArch_7_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        assertEqualsTypeNotNull(Processor.Type.IA_64, processor);
    }

    @Test
    public void testArch_8_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        assertTrue(processor.isIA64());
    }

    @Test
    public void testArch_9_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        assertEqualsTypeNotNull(Processor.Type.IA_64, processor);
    }

    @Test
    public void testArch_10_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        assertTrue(processor.isIA64());
    }

    @Test
    public void testArch_12_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(processor.isX86());
    }

    @Test
    public void testArch_13_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        assertEqualsTypeNotNull(Processor.Type.PPC, processor);
    }

    @Test
    public void testArch_14_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        assertTrue(processor.isPPC());
    }

    @Test
    public void testArch_16_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(processor.isIA64());
    }

    @Test
    public void testArch_17_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        assertEqualsTypeNotNull(Processor.Type.PPC, processor);
    }

    @Test
    public void testArch_18_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        assertTrue(processor.isPPC());
    }

    @Test
    public void testArchLabels_1_oe() {
        for (final Arch arch : Arch.values()) {
            // Only test label presence.
            assertFalse(arch.getLabel().isEmpty());
    }
    }

    @Test
    public void testGetProcessor_1_oe() {
        assertNotNull(ArchUtils.getProcessor(X86));
    }

    @Test
    public void testGetProcessor_2_oe() {
        // removed other assertion
        assertNull(ArchUtils.getProcessor("NA"));
    }

    @Test
    public void testIs32BitJVM_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        assertTrue(processor.is32Bit());
    }

    @Test
    public void testIs32BitJVM_4_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        assertTrue(processor.is32Bit());
    }

    @Test
    public void testIs32BitJVM_7_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        assertFalse(processor.is32Bit());
    }

    @Test
    public void testIs32BitJVM_9_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        assertFalse(processor.is32Bit());
    }

    @Test
    public void testIs32BitJVM_11_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        assertFalse(processor.is32Bit());
    }

    @Test
    public void testIs64BitJVM_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        assertTrue(processor.is64Bit());
    }

    @Test
    public void testIs64BitJVM_4_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        assertTrue(processor.is64Bit());
    }

    @Test
    public void testIs64BitJVM_6_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        assertTrue(processor.is64Bit());
    }

    @Test
    public void testIs64BitJVM_8_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        assertFalse(processor.is64Bit());
    }

    @Test
    public void testIs64BitJVM_10_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        assertFalse(processor.is64Bit());
    }

    @Test
    public void testIs64BitJVM_12_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        assertFalse(processor.is64Bit());
    }

    @Test
    public void testArch_3_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.PPC;
        final Processor processor0 = processor;
        assertNotNull(type0);
    }

    @Test
    public void testArch_3_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.PPC;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testArch_3_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.PPC;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(type0, processor0.getType());
    }

    @Test
    public void testArch_11_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.X86;
        final Processor processor0 = processor;
        assertNotNull(type0);
    }

    @Test
    public void testArch_11_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.X86;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testArch_11_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.X86;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(type0, processor0.getType());
    }

    @Test
    public void testArch_15_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.IA_64;
        final Processor processor0 = processor;
        assertNotNull(type0);
    }

    @Test
    public void testArch_15_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.IA_64;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testArch_15_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion
                final Processor.Type type0 = Processor.Type.IA_64;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(type0, processor0.getType());
    }

    @Test
    public void testIs32BitJVM_1_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs32BitJVM_1_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs32BitJVM_1_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs32BitJVM_3_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs32BitJVM_3_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs32BitJVM_3_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs32BitJVM_5_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs32BitJVM_5_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs32BitJVM_5_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs32BitJVM_6_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs32BitJVM_6_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs32BitJVM_6_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs32BitJVM_8_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs32BitJVM_8_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs32BitJVM_8_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs32BitJVM_10_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs32BitJVM_10_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs32BitJVM_10_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
                final Processor.Arch arch0 = Processor.Arch.BIT_32;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs64BitJVM_1_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs64BitJVM_1_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs64BitJVM_1_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs64BitJVM_3_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs64BitJVM_3_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs64BitJVM_3_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs64BitJVM_5_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs64BitJVM_5_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs64BitJVM_5_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs64BitJVM_7_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs64BitJVM_7_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs64BitJVM_7_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs64BitJVM_9_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs64BitJVM_9_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs64BitJVM_9_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(arch0, processor0.getArch());
    }

    @Test
    public void testIs64BitJVM_11_oe_1_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        assertNotNull(arch0);
    }

    @Test
    public void testIs64BitJVM_11_oe_2_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                assertNotNull(processor0);
    }

    @Test
    public void testIs64BitJVM_11_oe_3_oe() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(X86);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(PPC);
        // removed other assertion
        // removed other assertion

        processor = ArchUtils.getProcessor(IA64_32);
                final Processor.Arch arch0 = Processor.Arch.BIT_64;
        final Processor processor0 = processor;
        // removed other assertion
                // removed other assertion
                assertNotEquals(arch0, processor0.getArch());
    }

}
