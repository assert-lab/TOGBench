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


}
