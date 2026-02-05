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
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;

import static org.apache.commons.collections4.functors.AllPredicate.allPredicate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

/**
 * Tests the org.apache.commons.collections.functors.AllPredicate class.
 *
 * @since 3.0
 */
@SuppressWarnings("boxing")
public class AllPredicateTest_OE25Dev extends AbstractAnyAllOnePredicateTest<Integer> {

    /**
     * Creates a new <code>TestAllPredicate</code>.
     */
    public AllPredicateTest_OE25Dev() {
        super(42);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Predicate<Integer> getPredicateInstance(final Predicate<? super Integer> ... predicates) {
        return AllPredicate.allPredicate(predicates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Predicate<Integer> getPredicateInstance(final Collection<Predicate<Integer>> predicates) {
        return AllPredicate.allPredicate(predicates);
    }

    /**
     * Verifies that providing an empty predicate array evaluates to true.
     */

    /**
     * Verifies that providing an empty predicate collection evaluates to true.
     */

    /**
     * Tests whether a single true predicate evaluates to true.
     */

    /**
     * Tests whether a single false predicate evaluates to true.
     */

    /**
     * Tests whether multiple true predicates evaluates to true.
     */

    /**
     * Tests whether combining some true and one false evalutes to false.  Also verifies that only the first
     * false predicate is actually evaluated
     */

@Test
    public void emptyArrayToGetInstance_1_oe() {
        assertTrue("empty array not true", getPredicateInstance(new Predicate[] {}).evaluate(null));
    }

@Test
    public void emptyCollectionToGetInstance_1_oe() {
        final Predicate<Integer> allPredicate = getPredicateInstance(
                Collections.<Predicate<Integer>>emptyList());
        assertTrue("empty collection not true", allPredicate.evaluate(getTestValue()));
    }

@Test
    public void oneTruePredicate_1_oe() {
        // use the constructor directly, as getInstance() returns the original predicate when passed
        // an array of size one.
        final Predicate<Integer> predicate = createMockPredicate(true);

        assertTrue("single true predicate evaluated to false",allPredicate(predicate).evaluate(getTestValue()));
    }

@Test
    public void oneFalsePredicate_1_oe() {
        // use the constructor directly, as getInstance() returns the original predicate when passed
        // an array of size one.
        final Predicate<Integer> predicate = createMockPredicate(false);
        assertFalse("single false predicate evaluated to true",allPredicate(predicate).evaluate(getTestValue()));
    }

@Test
    public void allTrue_1_oe() {
        assertTrue("multiple true predicates evaluated to false",getPredicateInstance(true,true).evaluate(getTestValue()));
    }

@Test
    public void allTrue_2_oe() {
        // removed other assertion
        assertTrue("multiple true predicates evaluated to false",getPredicateInstance(true,true,true).evaluate(getTestValue()));
    }

@Test
    public void trueAndFalseCombined_1_oe() {
        assertFalse("false predicate evaluated to true",getPredicateInstance(false,null).evaluate(getTestValue()));
    }

@Test
    public void trueAndFalseCombined_2_oe() {
        // removed other assertion
        assertFalse("false predicate evaluated to true",getPredicateInstance(false,null,null).evaluate(getTestValue()));
    }

@Test
    public void trueAndFalseCombined_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse("false predicate evaluated to true",getPredicateInstance(true,false,null).evaluate(getTestValue()));
    }

@Test
    public void trueAndFalseCombined_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("false predicate evaluated to true",getPredicateInstance(true,true,false).evaluate(getTestValue()));
    }

@Test
    public void trueAndFalseCombined_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("false predicate evaluated to true",getPredicateInstance(true,true,false,null).evaluate(getTestValue()));
    }

}
