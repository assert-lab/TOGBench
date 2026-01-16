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
public class ArchUtilsTest {

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
    public void testArch() {
        Processor processor = ArchUtils.getProcessor(X86);
        assertNotNull(Processor.Type.X86);

        assertNotNull(processor);

        assertEquals(Processor.Type.X86, processor.getType());assertTrue(processor.isX86());
        assertNotNull(Processor.Type.PPC);

        assertNotNull(processor);

        assertNotEquals(Processor.Type.PPC, processor.getType());assertFalse(processor.isPPC());

        processor = ArchUtils.getProcessor(X86_64);
        assertNotNull(Processor.Type.X86);

        assertNotNull(processor);

        assertEquals(Processor.Type.X86, processor.getType());assertTrue(processor.isX86());

        processor = ArchUtils.getProcessor(IA64_32);
        assertNotNull(Processor.Type.IA_64);

        assertNotNull(processor);

        assertEquals(Processor.Type.IA_64, processor.getType());assertTrue(processor.isIA64());

        processor = ArchUtils.getProcessor(IA64);
        assertNotNull(Processor.Type.IA_64);

        assertNotNull(processor);

        assertEquals(Processor.Type.IA_64, processor.getType());assertTrue(processor.isIA64());
        assertNotNull(Processor.Type.X86);

        assertNotNull(processor);

        assertNotEquals(Processor.Type.X86, processor.getType());assertFalse(processor.isX86());

        processor = ArchUtils.getProcessor(PPC);
        assertNotNull(Processor.Type.PPC);

        assertNotNull(processor);

        assertEquals(Processor.Type.PPC, processor.getType());assertTrue(processor.isPPC());
        assertNotNull(Processor.Type.IA_64);

        assertNotNull(processor);

        assertNotEquals(Processor.Type.IA_64, processor.getType());assertFalse(processor.isIA64());

        processor = ArchUtils.getProcessor(PPC64);
        assertNotNull(Processor.Type.PPC);

        assertNotNull(processor);

        assertEquals(Processor.Type.PPC, processor.getType());assertTrue(processor.isPPC());
    }

    @Test
    public void testArchLabels() {
        for (final Arch arch : Arch.values()) {
            // Only test label presence.
            assertFalse(arch.getLabel().isEmpty());
        }
    }

    @Test
    public void testGetProcessor() {
        assertNotNull(ArchUtils.getProcessor(X86));
        assertNull(ArchUtils.getProcessor("NA"));
    }

    @Test
    public void testIs32BitJVM() {
        Processor processor = ArchUtils.getProcessor(X86);
        assertNotNull(Processor.Arch.BIT_32);

        assertNotNull(processor);

        assertEquals(Processor.Arch.BIT_32, processor.getArch());assertTrue(processor.is32Bit());

        processor = ArchUtils.getProcessor(IA64_32);
        assertNotNull(Processor.Arch.BIT_32);

        assertNotNull(processor);

        assertEquals(Processor.Arch.BIT_32, processor.getArch());assertTrue(processor.is32Bit());

        processor = ArchUtils.getProcessor(PPC);
        assertNotNull(Processor.Arch.BIT_32);

        assertNotNull(processor);

        assertEquals(Processor.Arch.BIT_32, processor.getArch());processor.is32Bit();

        processor = ArchUtils.getProcessor(X86_64);
        assertNotNull(Processor.Arch.BIT_32);

        assertNotNull(processor);

        assertNotEquals(Processor.Arch.BIT_32, processor.getArch());assertFalse(processor.is32Bit());

        processor = ArchUtils.getProcessor(PPC64);
        assertNotNull(Processor.Arch.BIT_32);

        assertNotNull(processor);

        assertNotEquals(Processor.Arch.BIT_32, processor.getArch());assertFalse(processor.is32Bit());

        processor = ArchUtils.getProcessor(IA64);
        assertNotNull(Processor.Arch.BIT_32);

        assertNotNull(processor);

        assertNotEquals(Processor.Arch.BIT_32, processor.getArch());assertFalse(processor.is32Bit());
    }

    @Test
    public void testIs64BitJVM() {
        Processor processor = ArchUtils.getProcessor(X86_64);
        assertNotNull(Processor.Arch.BIT_64);

        assertNotNull(processor);

        assertEquals(Processor.Arch.BIT_64, processor.getArch());assertTrue(processor.is64Bit());

        processor = ArchUtils.getProcessor(PPC64);
        assertNotNull(Processor.Arch.BIT_64);

        assertNotNull(processor);

        assertEquals(Processor.Arch.BIT_64, processor.getArch());assertTrue(processor.is64Bit());

        processor = ArchUtils.getProcessor(IA64);
        assertNotNull(Processor.Arch.BIT_64);

        assertNotNull(processor);

        assertEquals(Processor.Arch.BIT_64, processor.getArch());assertTrue(processor.is64Bit());

        processor = ArchUtils.getProcessor(X86);
        assertNotNull(Processor.Arch.BIT_64);

        assertNotNull(processor);

        assertNotEquals(Processor.Arch.BIT_64, processor.getArch());assertFalse(processor.is64Bit());

        processor = ArchUtils.getProcessor(PPC);
        assertNotNull(Processor.Arch.BIT_64);

        assertNotNull(processor);

        assertNotEquals(Processor.Arch.BIT_64, processor.getArch());assertFalse(processor.is64Bit());

        processor = ArchUtils.getProcessor(IA64_32);
        assertNotNull(Processor.Arch.BIT_64);

        assertNotNull(processor);

        assertNotEquals(Processor.Arch.BIT_64, processor.getArch());assertFalse(processor.is64Bit());
    }

}
