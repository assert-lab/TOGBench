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
package org.apache.commons.lang3.mutable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * JUnit tests.
 *
 * @see MutableInt
 */
public class MutableIntTest_OE25Dev {

    // ----------------------------------------------------------------

    @Test
    public void testEquals() {
        this.testEquals(new MutableInt(0), new MutableInt(0), new MutableInt(1));
        // Should Numbers be supported? GaryG July-21-2005.
        //this.testEquals(mutNumA, Integer.valueOf(0), mutNumC);
    }

    /**
     * @param numA must not be a 0 Integer; must not equal numC.
     * @param numB must equal numA; must not equal numC.
     * @param numC must not equal numA; must not equal numC.
     */
    void testEquals(final Number numA, final Number numB, final Number numC) {
        assertEquals(numA, numA);
        assertEquals(numA, numB);
        assertEquals(numB, numA);
        assertEquals(numB, numB);
        assertNotEquals(numA, numC);
        assertNotEquals(numB, numC);
        assertEquals(numC, numC);
        assertNotEquals(null, numA);
        assertNotEquals(numA, Integer.valueOf(0));
        assertNotEquals("0", numA);
    }

    @Test
    public void testCompareToNull_1_oe() throws Exception {
        final MutableInt mutNum = new MutableInt(0);
        try {
    mutNum.compareTo(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructorNull_1_oe() throws Exception {
        try {
    new MutableInt((Number) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSetNull_1_oe() throws Exception {
        final MutableInt mutNum = new MutableInt(0);
        try {
    mutNum.setValue(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
