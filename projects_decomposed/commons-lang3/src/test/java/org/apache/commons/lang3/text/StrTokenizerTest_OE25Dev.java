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

package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * Unit test for Tokenizer.
 */
@Deprecated
public class StrTokenizerTest_OE25Dev {

    private static final String CSV_SIMPLE_FIXTURE = "A,b,c";

    private static final String TSV_SIMPLE_FIXTURE = "A\tb\tc";

    private void checkClone(final StrTokenizer tokenizer) {
        assertNotSame(StrTokenizer.getCSVInstance(), tokenizer);
        assertNotSame(StrTokenizer.getTSVInstance(), tokenizer);
    }

    // -----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    private void testCSV(final String data) {
        this.testXSVAbc(StrTokenizer.getCSVInstance(data));
        this.testXSVAbc(StrTokenizer.getCSVInstance(data.toCharArray()));
    }

    @Test
    public void testCSVEmpty() {
        this.testEmpty(StrTokenizer.getCSVInstance());
        this.testEmpty(StrTokenizer.getCSVInstance(""));
    }

    @Test
    public void testCSVSimple() {
        this.testCSV(CSV_SIMPLE_FIXTURE);
    }

    @Test
    public void testCSVSimpleNeedsTrim() {
        this.testCSV("   " + CSV_SIMPLE_FIXTURE);
        this.testCSV("   \n\t  " + CSV_SIMPLE_FIXTURE);
        this.testCSV("   \n  " + CSV_SIMPLE_FIXTURE + "\n\n\r");
    }

    void testEmpty(final StrTokenizer tokenizer) {
        this.checkClone(tokenizer);
        assertFalse(tokenizer.hasNext());
        assertFalse(tokenizer.hasPrevious());
        assertNull(tokenizer.nextToken());
        assertEquals(0, tokenizer.size());
        assertThrows(NoSuchElementException.class, tokenizer::next);
    }

    //-----------------------------------------------------------------------

    /**
     * Tests that the {@link StrTokenizer#clone()} clone method catches {@link CloneNotSupportedException} and returns
     * {@code null}.
     */

    // -----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    @Test
    public void testTSV() {
        this.testXSVAbc(StrTokenizer.getTSVInstance(TSV_SIMPLE_FIXTURE));
        this.testXSVAbc(StrTokenizer.getTSVInstance(TSV_SIMPLE_FIXTURE.toCharArray()));
    }

    @Test
    public void testTSVEmpty() {
        this.testEmpty(StrTokenizer.getTSVInstance());
        this.testEmpty(StrTokenizer.getTSVInstance(""));
    }

    void testXSVAbc(final StrTokenizer tokenizer) {
        this.checkClone(tokenizer);
        assertEquals(-1, tokenizer.previousIndex());
        assertEquals(0, tokenizer.nextIndex());
        assertNull(tokenizer.previousToken());
        assertEquals("A", tokenizer.nextToken());
        assertEquals(1, tokenizer.nextIndex());
        assertEquals("b", tokenizer.nextToken());
        assertEquals(2, tokenizer.nextIndex());
        assertEquals("c", tokenizer.nextToken());
        assertEquals(3, tokenizer.nextIndex());
        assertNull(tokenizer.nextToken());
        assertEquals(3, tokenizer.nextIndex());
        assertEquals("c", tokenizer.previousToken());
        assertEquals(2, tokenizer.nextIndex());
        assertEquals("b", tokenizer.previousToken());
        assertEquals(1, tokenizer.nextIndex());
        assertEquals("A", tokenizer.previousToken());
        assertEquals(0, tokenizer.nextIndex());
        assertNull(tokenizer.previousToken());
        assertEquals(0, tokenizer.nextIndex());
        assertEquals(-1, tokenizer.previousIndex());
        assertEquals(3, tokenizer.size());
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Test
    public void testIteration_2_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        assertThrows(NoSuchElementException.class, tkn::previous);
    }

    @Test
    public void testIteration_5_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertThrows(UnsupportedOperationException.class, tkn::remove);
    }

    @Test
    public void testIteration_6_oe() throws Exception {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    tkn.set("x");
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testIteration_7_oe() throws Exception {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    tkn.add("y");
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

}
