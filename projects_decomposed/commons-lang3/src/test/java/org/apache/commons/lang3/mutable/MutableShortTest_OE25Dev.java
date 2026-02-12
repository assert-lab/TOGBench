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
 * @see MutableShort
 */
public class MutableShortTest_OE25Dev {

    // ----------------------------------------------------------------

    @Test
    public void testCompareTo_4_oe() throws Exception {
        final MutableShort mutNum = new MutableShort((short) 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    mutNum.compareTo(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructors_6_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        try {
    new MutableShort((Number) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetSet_9_oe() throws Exception {
        final MutableShort mutNum = new MutableShort((short) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((short) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Short.valueOf((short) 2));
        // removed other assertion
        // removed other assertion

        mutNum.setValue(new MutableShort((short) 3));
        // removed other assertion
        // removed other assertion
        try {
    mutNum.setValue(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
