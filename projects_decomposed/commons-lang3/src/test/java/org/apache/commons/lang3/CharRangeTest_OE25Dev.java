/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.CharRange}.
 */
public class CharRangeTest_OE25Dev  {

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Test
    public void testContainsNullArg_1_oe() {
        final CharRange range = CharRange.is('a');
        final NullPointerException e = assertThrows(NullPointerException.class, () -> range.contains(null));
    }

    @Test
    public void testIterator_17_oe() {
        final CharRange a = CharRange.is('a');
        final CharRange ad = CharRange.isIn('a', 'd');
        final CharRange nota = CharRange.isNot('a');
        final CharRange emptySet = CharRange.isNotIn((char) 0, Character.MAX_VALUE);
        final CharRange notFirst = CharRange.isNotIn((char) 1, Character.MAX_VALUE);
        final CharRange notLast = CharRange.isNotIn((char) 0, (char) (Character.MAX_VALUE - 1));

        final Iterator<Character> aIt = a.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Iterator<Character> adIt = ad.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Iterator<Character> notaIt = nota.iterator();
        // removed other assertion
        // removed other assertion
        while (notaIt.hasNext()) {
            final Character c = notaIt.next();
            // removed other assertion
        }

        final Iterator<Character> emptySetIt = emptySet.iterator();
        // removed other assertion
        // removed other assertion
        assertThrows(NoSuchElementException.class, emptySetIt::next);
    }

    @Test
    public void testIteratorRemove_1_oe() {
        final CharRange a = CharRange.is('a');
        final Iterator<Character> aIt = a.iterator();
        assertThrows(UnsupportedOperationException.class, aIt::remove);
    }

}
