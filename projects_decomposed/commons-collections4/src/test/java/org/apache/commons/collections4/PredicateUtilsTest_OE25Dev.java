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
package org.apache.commons.collections4;

import static org.apache.commons.collections4.functors.NullPredicate.*;
import static org.apache.commons.collections4.functors.TruePredicate.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.functors.AbstractPredicateTest;
import org.apache.commons.collections4.functors.AllPredicate;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionPredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.functors.NullPredicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.Test;

/**
 * Tests the PredicateUtils class.
 *
 * @since 3.0
 */
@SuppressWarnings("boxing")
public class PredicateUtilsTest_OE25Dev extends AbstractPredicateTest {

    // exceptionPredicate
    //------------------------------------------------------------------

    // notNullPredicate
    //------------------------------------------------------------------

    // identityPredicate
    //------------------------------------------------------------------

    // truePredicate
    //------------------------------------------------------------------

    // falsePredicate
    //------------------------------------------------------------------

    // notPredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testNotPredicateEx() {
        PredicateUtils.notPredicate(null);
    }

    // andPredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testAndPredicateEx() {
        PredicateUtils.andPredicate(null, null);
    }

    // allPredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testAllPredicateEx1() {
        AllPredicate.allPredicate((Predicate<Object>[]) null);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullPointerException.class)
    public void testAllPredicateEx2() {
        AllPredicate.<Object>allPredicate(new Predicate[] { null });
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullPointerException.class)
    public void testAllPredicateEx3() {
        AllPredicate.allPredicate(new Predicate[] { null, null });
    }

    @Test(expected=NullPointerException.class)
    public void testAllPredicateEx4() {
        AllPredicate.allPredicate((Collection<Predicate<Object>>) null);
    }

    @Test
    public void testAllPredicateEx5() {
        AllPredicate.allPredicate(Collections.<Predicate<Object>>emptyList());
    }

    @Test(expected=NullPointerException.class)
    public void testAllPredicateEx6() {
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(null);
        coll.add(null);
        AllPredicate.allPredicate(coll);
    }

    // orPredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testOrPredicateEx() {
        PredicateUtils.orPredicate(null, null);
    }

    // anyPredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testAnyPredicateEx1() {
        PredicateUtils.anyPredicate((Predicate<Object>[]) null);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullPointerException.class)
    public void testAnyPredicateEx2() {
        PredicateUtils.anyPredicate(new Predicate[] {null});
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullPointerException.class)
    public void testAnyPredicateEx3() {
        PredicateUtils.anyPredicate(new Predicate[] {null, null});
    }

    @Test(expected=NullPointerException.class)
    public void testAnyPredicateEx4() {
        PredicateUtils.anyPredicate((Collection<Predicate<Object>>) null);
    }

    @Test
    public void testAnyPredicateEx5() {
        PredicateUtils.anyPredicate(Collections.<Predicate<Object>>emptyList());
    }

    @Test(expected=NullPointerException.class)
    public void testAnyPredicateEx6() {
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(null);
        coll.add(null);
        PredicateUtils.anyPredicate(coll);
    }

    // eitherPredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testEitherPredicateEx() {
        PredicateUtils.eitherPredicate(null, null);
    }

    // onePredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testOnePredicateEx1() {
        PredicateUtils.onePredicate((Predicate<Object>[]) null);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullPointerException.class)
    public void testOnePredicateEx2() {
        PredicateUtils.onePredicate(new Predicate[] {null});
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullPointerException.class)
    public void testOnePredicateEx3() {
        PredicateUtils.onePredicate(new Predicate[] {null, null});
    }

    @Test(expected=NullPointerException.class)
    public void testOnePredicateEx4() {
        PredicateUtils.onePredicate((Collection<Predicate<Object>>) null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testOnePredicateEx5() {
        PredicateUtils.onePredicate(Collections.EMPTY_LIST);
    }

    @Test(expected=NullPointerException.class)
    public void testOnePredicateEx6() {
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(null);
        coll.add(null);
        PredicateUtils.onePredicate(coll);
    }

    // neitherPredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testNeitherPredicateEx() {
        PredicateUtils.neitherPredicate(null, null);
    }

    // nonePredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testNonePredicateEx1() {
        PredicateUtils.nonePredicate((Predicate<Object>[]) null);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullPointerException.class)
    public void testNonePredicateEx2() {
        PredicateUtils.nonePredicate(new Predicate[] {null});
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullPointerException.class)
    public void testNonePredicateEx3() {
        PredicateUtils.nonePredicate(new Predicate[] {null, null});
    }

    @Test(expected=NullPointerException.class)
    public void testNonePredicateEx4() {
        PredicateUtils.nonePredicate((Collection<Predicate<Object>>) null);
    }

    @Test
    public void testNonePredicateEx5() {
        PredicateUtils.nonePredicate(Collections.<Predicate<Object>>emptyList());
    }

    @Test(expected=NullPointerException.class)
    public void testNonePredicateEx6() {
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(null);
        coll.add(null);
        PredicateUtils.nonePredicate(coll);
    }

    // instanceofPredicate
    //------------------------------------------------------------------

    // uniquePredicate
    //------------------------------------------------------------------

    // asPredicate(Transformer)
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testAsPredicateTransformerEx1() {
        PredicateUtils.asPredicate(null);
    }

    @Test(expected=FunctorException.class)
    public void testAsPredicateTransformerEx2() {
        PredicateUtils.asPredicate(TransformerUtils.<Boolean>nopTransformer()).evaluate(null);
    }

    // invokerPredicate
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testInvokerPredicateEx1() {
        PredicateUtils.invokerPredicate(null);
    }

    @Test(expected=FunctorException.class)
    public void testInvokerPredicateEx2() {
        PredicateUtils.invokerPredicate("isEmpty").evaluate(null);
    }

    @Test(expected=FunctorException.class)
    public void testInvokerPredicateEx3() {
        PredicateUtils.invokerPredicate("noSuchMethod").evaluate(new Object());
    }

    // invokerPredicate2
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testInvokerPredicate2Ex1() {
        PredicateUtils.invokerPredicate(null, null, null);
    }

    @Test(expected=FunctorException.class)
    public void testInvokerPredicate2Ex2() {
        PredicateUtils.invokerPredicate("contains", new Class[] {Object.class}, new Object[] {cString}).evaluate(null);
    }

    @Test(expected=FunctorException.class)
    public void testInvokerPredicate2Ex3() {
        PredicateUtils.invokerPredicate(
                "noSuchMethod", new Class[] {Object.class}, new Object[] {cString}).evaluate(new Object());
    }

    // nullIsException
    //------------------------------------------------------------------

    @Test(expected=FunctorException.class)
    public void testNullIsExceptionPredicate() {
        assertEquals(true, PredicateUtils.nullIsExceptionPredicate(TruePredicate.truePredicate()).evaluate(new Object()));
        PredicateUtils.nullIsExceptionPredicate(TruePredicate.truePredicate()).evaluate(null);
    }

    @Test(expected=NullPointerException.class)
    public void testNullIsExceptionPredicateEx1() {
        PredicateUtils.nullIsExceptionPredicate(null);
    }

    // nullIsTrue
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testNullIsTruePredicateEx1() {
        PredicateUtils.nullIsTruePredicate(null);
    }

    // nullIsFalse
    //------------------------------------------------------------------

    @Test(expected=NullPointerException.class)
    public void testNullIsFalsePredicateEx1() {
        PredicateUtils.nullIsFalsePredicate(null);
    }

    // transformed
    //------------------------------------------------------------------

    // misc tests
    //------------------------------------------------------------------

    /**
     * Test that all Predicate singletones hold singleton pattern in
     * serialization/deserialization process.
     */

    @Override
    protected Predicate<?> generatePredicate() {
        return truePredicate();  //Just return something to satisfy super class.
    }

    @Test
    public void testExceptionPredicate_1_oe() {
        assertNotNull(PredicateUtils.exceptionPredicate());
    }

    @Test
    public void testExceptionPredicate_2_oe() {
        // removed other assertion
        assertSame(PredicateUtils.exceptionPredicate(), PredicateUtils.exceptionPredicate());
    }

    @Test
    public void testExceptionPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        try {
            PredicateUtils.exceptionPredicate().evaluate(null);
        } catch (final FunctorException ex) {
            try {
                PredicateUtils.exceptionPredicate().evaluate(cString);
            } catch (final FunctorException ex2) {
                return;
            }
        }
        fail();
    }

    @Test
    public void testIsNotNullPredicate_1_oe() {
        assertNotNull(PredicateUtils.notNullPredicate());
    }

    @Test
    public void testIsNotNullPredicate_2_oe() {
        // removed other assertion
        assertSame(PredicateUtils.notNullPredicate(), PredicateUtils.notNullPredicate());
    }

    @Test
    public void testIsNotNullPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.notNullPredicate().evaluate(null));
    }

    @Test
    public void testIsNotNullPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.notNullPredicate().evaluate(cObject));
    }

    @Test
    public void testIsNotNullPredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.notNullPredicate().evaluate(cString));
    }

    @Test
    public void testIsNotNullPredicate_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.notNullPredicate().evaluate(cInteger));
    }

    @Test
    public void testIdentityPredicate_1_oe() {
        assertSame(nullPredicate(), PredicateUtils.identityPredicate(null));
    }

    @Test
    public void testIdentityPredicate_2_oe() {
        // removed other assertion
        assertNotNull(PredicateUtils.identityPredicate(Integer.valueOf(6)));
    }

    @Test
    public void testIdentityPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.identityPredicate(Integer.valueOf(6)).evaluate(null));
    }

    @Test
    public void testIdentityPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.<Object>identityPredicate(Integer.valueOf(6)).evaluate(cObject));
    }

    @Test
    public void testIdentityPredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.<Object>identityPredicate(Integer.valueOf(6)).evaluate(cString));
    }

    @Test
    public void testIdentityPredicate_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,PredicateUtils.identityPredicate(new Integer(6)).evaluate(cInteger));// Cannot use valueOf here assertEquals(true,PredicateUtils.identityPredicate(cInteger).evaluate(cInteger));
    }

    @Test
    public void testTruePredicate_1_oe() {
        assertNotNull(TruePredicate.truePredicate());
    }

    @Test
    public void testTruePredicate_2_oe() {
        // removed other assertion
        assertSame(TruePredicate.truePredicate(), TruePredicate.truePredicate());
    }

    @Test
    public void testTruePredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, TruePredicate.truePredicate().evaluate(null));
    }

    @Test
    public void testTruePredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, TruePredicate.truePredicate().evaluate(cObject));
    }

    @Test
    public void testTruePredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, TruePredicate.truePredicate().evaluate(cString));
    }

    @Test
    public void testTruePredicate_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, TruePredicate.truePredicate().evaluate(cInteger));
    }

    @Test
    public void testFalsePredicate_1_oe() {
        assertNotNull(FalsePredicate.falsePredicate());
    }

    @Test
    public void testFalsePredicate_2_oe() {
        // removed other assertion
        assertSame(FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate());
    }

    @Test
    public void testFalsePredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, FalsePredicate.falsePredicate().evaluate(null));
    }

    @Test
    public void testFalsePredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, FalsePredicate.falsePredicate().evaluate(cObject));
    }

    @Test
    public void testFalsePredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, FalsePredicate.falsePredicate().evaluate(cString));
    }

    @Test
    public void testFalsePredicate_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, FalsePredicate.falsePredicate().evaluate(cInteger));
    }

    @Test
    public void testNotPredicate_1_oe() {
        assertNotNull(PredicateUtils.notPredicate(TruePredicate.truePredicate()));
    }

    @Test
    public void testNotPredicate_2_oe() {
        // removed other assertion
        assertEquals(false, PredicateUtils.notPredicate(TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testNotPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.notPredicate(TruePredicate.truePredicate()).evaluate(cObject));
    }

    @Test
    public void testNotPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.notPredicate(TruePredicate.truePredicate()).evaluate(cString));
    }

    @Test
    public void testNotPredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.notPredicate(TruePredicate.truePredicate()).evaluate(cInteger));
    }

    @Test
    public void testAndPredicate_1_oe() {
        assertEquals(true, PredicateUtils.andPredicate(TruePredicate.truePredicate(), TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testAndPredicate_2_oe() {
        // removed other assertion
        assertEquals(false, PredicateUtils.andPredicate(TruePredicate.truePredicate(), FalsePredicate.falsePredicate()).evaluate(null));
    }

    @Test
    public void testAndPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.andPredicate(FalsePredicate.falsePredicate(), TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testAndPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.andPredicate(FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate()).evaluate(null));
    }

    @Test
    public void testAllPredicate_1_oe() {
        assertTrue(AllPredicate.allPredicate(new Predicate[] {}), null);
    }

    @Test
    public void testAllPredicate_2_oe() {
        // removed other assertion
        assertEquals(true, AllPredicate.allPredicate(new Predicate[] { TruePredicate.truePredicate(), TruePredicate.truePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testAllPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, AllPredicate.allPredicate(new Predicate[] { TruePredicate.truePredicate(), FalsePredicate.falsePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testAllPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, AllPredicate.allPredicate(new Predicate[] { FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testAllPredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, AllPredicate.allPredicate(new Predicate[] { FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate()}).evaluate(null));
    }

    @Test
    public void testAllPredicate_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(true, AllPredicate.allPredicate(coll).evaluate(null));
    }

    @Test
    public void testAllPredicate_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(false, AllPredicate.allPredicate(coll).evaluate(null));
    }

    @Test
    public void testAllPredicate_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(false, AllPredicate.allPredicate(coll).evaluate(null));
    }

    @Test
    public void testAllPredicate_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        assertEquals(false, AllPredicate.allPredicate(coll).evaluate(null));
    }

    @Test
    public void testAllPredicate_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        assertFalse(AllPredicate.allPredicate(coll), null);
    }

    @Test
    public void testAllPredicate_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        assertTrue(AllPredicate.allPredicate(coll), null);
    }

    @Test
    public void testAllPredicate_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        assertTrue(AllPredicate.allPredicate(coll), null);
    }

    @Test
    public void testOrPredicate_1_oe() {
        assertEquals(true, PredicateUtils.orPredicate(TruePredicate.truePredicate(), TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testOrPredicate_2_oe() {
        // removed other assertion
        assertEquals(true, PredicateUtils.orPredicate(TruePredicate.truePredicate(), FalsePredicate.falsePredicate()).evaluate(null));
    }

    @Test
    public void testOrPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.orPredicate(FalsePredicate.falsePredicate(), TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testOrPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.orPredicate(FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate()).evaluate(null));
    }

    @Test
    public void testAnyPredicate_1_oe() {
        assertFalse(PredicateUtils.anyPredicate(new Predicate[] {}), null);
    }

    @Test
    public void testAnyPredicate_2_oe() {
        // removed other assertion

        assertEquals(true, PredicateUtils.anyPredicate(new Predicate[] { TruePredicate.truePredicate(), TruePredicate.truePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testAnyPredicate_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals(true, PredicateUtils.anyPredicate(new Predicate[] { TruePredicate.truePredicate(), FalsePredicate.falsePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testAnyPredicate_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.anyPredicate(new Predicate[] { FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testAnyPredicate_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.anyPredicate(new Predicate[] { FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate()}).evaluate(null));
    }

    @Test
    public void testAnyPredicate_6_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(true, PredicateUtils.anyPredicate(coll).evaluate(null));
    }

    @Test
    public void testAnyPredicate_7_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(true, PredicateUtils.anyPredicate(coll).evaluate(null));
    }

    @Test
    public void testAnyPredicate_8_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(true, PredicateUtils.anyPredicate(coll).evaluate(null));
    }

    @Test
    public void testAnyPredicate_9_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        assertEquals(false, PredicateUtils.anyPredicate(coll).evaluate(null));
    }

    @Test
    public void testAnyPredicate_10_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        assertFalse(PredicateUtils.anyPredicate(coll), null);
    }

    @Test
    public void testAnyPredicate_11_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        assertTrue(PredicateUtils.anyPredicate(coll), null);
    }

    @Test
    public void testAnyPredicate_12_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        assertFalse(PredicateUtils.anyPredicate(coll), null);
    }

    @Test
    public void testEitherPredicate_1_oe() {
        assertEquals(false, PredicateUtils.eitherPredicate(TruePredicate.truePredicate(), TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testEitherPredicate_2_oe() {
        // removed other assertion
        assertEquals(true, PredicateUtils.eitherPredicate(TruePredicate.truePredicate(), FalsePredicate.falsePredicate()).evaluate(null));
    }

    @Test
    public void testEitherPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.eitherPredicate(FalsePredicate.falsePredicate(), TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testEitherPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.eitherPredicate(FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate()).evaluate(null));
    }

    @Test
    public void testOnePredicate_1_oe() {
        assertFalse(PredicateUtils.onePredicate((Predicate<Object>[]) new Predicate[] {}), null);
    }

    @Test
    public void testOnePredicate_2_oe() {
        // removed other assertion
        assertEquals(false, PredicateUtils.onePredicate(new Predicate[] { TruePredicate.truePredicate(), TruePredicate.truePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testOnePredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.onePredicate(new Predicate[] { TruePredicate.truePredicate(), FalsePredicate.falsePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testOnePredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.onePredicate(new Predicate[] { TruePredicate.truePredicate(), FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate()}).evaluate(null));
    }

    @Test
    public void testOnePredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.onePredicate(new Predicate[] { FalsePredicate.falsePredicate(), TruePredicate.truePredicate(), FalsePredicate.falsePredicate()}).evaluate(null));
    }

    @Test
    public void testOnePredicate_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.onePredicate(new Predicate[] { FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate(), TruePredicate.truePredicate()}).evaluate(null));
    }

    @Test
    public void testOnePredicate_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.onePredicate(new Predicate[] { FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate()}).evaluate(null));
    }

    @Test
    public void testOnePredicate_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(false, PredicateUtils.onePredicate(coll).evaluate(null));
    }

    @Test
    public void testOnePredicate_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(false, PredicateUtils.onePredicate(coll).evaluate(null));
    }

    @Test
    public void testOnePredicate_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(true, PredicateUtils.onePredicate(coll).evaluate(null));
    }

    @Test
    public void testOnePredicate_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        assertEquals(false, PredicateUtils.onePredicate(coll).evaluate(null));
    }

    @Test
    public void testOnePredicate_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        assertFalse(PredicateUtils.onePredicate(coll), null);
    }

    @Test
    public void testOnePredicate_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        assertTrue(PredicateUtils.onePredicate(coll), null);
    }

    @Test
    public void testOnePredicate_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        assertFalse(PredicateUtils.onePredicate(coll), null);
    }

    @Test
    public void testNeitherPredicate_1_oe() {
        assertEquals(false, PredicateUtils.neitherPredicate(TruePredicate.truePredicate(), TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testNeitherPredicate_2_oe() {
        // removed other assertion
        assertEquals(false, PredicateUtils.neitherPredicate(TruePredicate.truePredicate(), FalsePredicate.falsePredicate()).evaluate(null));
    }

    @Test
    public void testNeitherPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.neitherPredicate(FalsePredicate.falsePredicate(), TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testNeitherPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.neitherPredicate(FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate()).evaluate(null));
    }

    @Test
    public void testNonePredicate_1_oe() {
        assertTrue(PredicateUtils.nonePredicate(new Predicate[] {}), null);
    }

    @Test
    public void testNonePredicate_2_oe() {
        // removed other assertion
        assertEquals(false, PredicateUtils.nonePredicate(new Predicate[] { TruePredicate.truePredicate(), TruePredicate.truePredicate(), TruePredicate.truePredicate() }).evaluate(null));
    }

    @Test
    public void testNonePredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.nonePredicate(new Predicate[] { TruePredicate.truePredicate(), FalsePredicate.falsePredicate(), TruePredicate.truePredicate() }).evaluate(null));
    }

    @Test
    public void testNonePredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.nonePredicate(new Predicate[] { FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate(), TruePredicate.truePredicate() }).evaluate(null));
    }

    @Test
    public void testNonePredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.nonePredicate(new Predicate[] { FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate(), FalsePredicate.falsePredicate() }).evaluate(null));
    }

    @Test
    public void testNonePredicate_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(false, PredicateUtils.nonePredicate(coll).evaluate(null));
    }

    @Test
    public void testNonePredicate_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(false, PredicateUtils.nonePredicate(coll).evaluate(null));
    }

    @Test
    public void testNonePredicate_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        assertEquals(false, PredicateUtils.nonePredicate(coll).evaluate(null));
    }

    @Test
    public void testNonePredicate_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        assertEquals(true, PredicateUtils.nonePredicate(coll).evaluate(null));
    }

    @Test
    public void testNonePredicate_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        assertTrue(PredicateUtils.nonePredicate(coll), null);
    }

    @Test
    public void testNonePredicate_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        assertFalse(PredicateUtils.nonePredicate(coll), null);
    }

    @Test
    public void testNonePredicate_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Collection<Predicate<Object>> coll = new ArrayList<>();
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(FalsePredicate.falsePredicate());
        // removed other assertion
        coll.clear();
        coll.add(TruePredicate.truePredicate());
        // removed other assertion
        coll.clear();
        assertTrue(PredicateUtils.nonePredicate(coll), null);
    }

    @Test
    public void testInstanceOfPredicate_1_oe() {
        assertNotNull(PredicateUtils.instanceofPredicate(String.class));
    }

    @Test
    public void testInstanceOfPredicate_2_oe() {
        // removed other assertion
        assertEquals(false, PredicateUtils.instanceofPredicate(String.class).evaluate(null));
    }

    @Test
    public void testInstanceOfPredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.instanceofPredicate(String.class).evaluate(cObject));
    }

    @Test
    public void testInstanceOfPredicate_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, PredicateUtils.instanceofPredicate(String.class).evaluate(cString));
    }

    @Test
    public void testInstanceOfPredicate_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.instanceofPredicate(String.class).evaluate(cInteger));
    }

    @Test
    public void testUniquePredicate_1_oe() {
        final Predicate<Object> p = PredicateUtils.uniquePredicate();
        assertEquals(true, p.evaluate(new Object()));
    }

    @Test
    public void testUniquePredicate_2_oe() {
        final Predicate<Object> p = PredicateUtils.uniquePredicate();
        // removed other assertion
        assertEquals(true, p.evaluate(new Object()));
    }

    @Test
    public void testUniquePredicate_3_oe() {
        final Predicate<Object> p = PredicateUtils.uniquePredicate();
        // removed other assertion
        // removed other assertion
        assertEquals(true, p.evaluate(new Object()));
    }

    @Test
    public void testUniquePredicate_4_oe() {
        final Predicate<Object> p = PredicateUtils.uniquePredicate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, p.evaluate(cString));
    }

    @Test
    public void testAsPredicateTransformer_1_oe() {
        assertEquals(false, PredicateUtils.asPredicate(TransformerUtils.<Boolean>nopTransformer()).evaluate(false));
    }

    @Test
    public void testAsPredicateTransformer_2_oe() {
        // removed other assertion
        assertEquals(true, PredicateUtils.asPredicate(TransformerUtils.<Boolean>nopTransformer()).evaluate(true));
    }

    @Test
    public void testInvokerPredicate_1_oe() {
        final List<Object> list = new ArrayList<>();
        assertEquals(true, PredicateUtils.invokerPredicate("isEmpty").evaluate(list));
    }

    @Test
    public void testInvokerPredicate_2_oe() {
        final List<Object> list = new ArrayList<>();
        // removed other assertion
        list.add(new Object());
        assertEquals(false, PredicateUtils.invokerPredicate("isEmpty").evaluate(list));
    }

    @Test
    public void testInvokerPredicate2_1_oe() {
        final List<String> list = new ArrayList<>();
        assertEquals(false, PredicateUtils.invokerPredicate( "contains", new Class[] {Object.class}, new Object[] {cString}).evaluate(list));
    }

    @Test
    public void testInvokerPredicate2_2_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add(cString);
        assertEquals(true, PredicateUtils.invokerPredicate( "contains", new Class[] {Object.class}, new Object[] {cString}).evaluate(list));
    }

    @Test
    public void testNullIsTruePredicate_1_oe() {
        assertEquals(true, PredicateUtils.nullIsTruePredicate(TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testNullIsTruePredicate_2_oe() {
        // removed other assertion
        assertEquals(true, PredicateUtils.nullIsTruePredicate(TruePredicate.truePredicate()).evaluate(new Object()));
    }

    @Test
    public void testNullIsTruePredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.nullIsTruePredicate(FalsePredicate.falsePredicate()).evaluate(new Object()));
    }

    @Test
    public void testNullIsFalsePredicate_1_oe() {
        assertEquals(false, PredicateUtils.nullIsFalsePredicate(TruePredicate.truePredicate()).evaluate(null));
    }

    @Test
    public void testNullIsFalsePredicate_2_oe() {
        // removed other assertion
        assertEquals(true, PredicateUtils.nullIsFalsePredicate(TruePredicate.truePredicate()).evaluate(new Object()));
    }

    @Test
    public void testNullIsFalsePredicate_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, PredicateUtils.nullIsFalsePredicate(FalsePredicate.falsePredicate()).evaluate(new Object()));
    }

    @Test
    public void testTransformedPredicate_1_oe() {
        assertEquals(true,PredicateUtils.transformedPredicate(TransformerUtils.nopTransformer(),TruePredicate.truePredicate()).evaluate(new Object()));
    }

    @Test
    public void testTransformedPredicate_2_oe() {
        // removed other assertion

        final Map<Object, Object> map = new HashMap<>();
        map.put(Boolean.TRUE, "Hello");
        final Transformer<Object, Object> t = TransformerUtils.mapTransformer(map);
        final Predicate<Object> p = EqualPredicate.<Object>equalPredicate("Hello");
        assertEquals(false, PredicateUtils.transformedPredicate(t, p).evaluate(null));
    }

    @Test
    public void testTransformedPredicate_3_oe() {
        // removed other assertion

        final Map<Object, Object> map = new HashMap<>();
        map.put(Boolean.TRUE, "Hello");
        final Transformer<Object, Object> t = TransformerUtils.mapTransformer(map);
        final Predicate<Object> p = EqualPredicate.<Object>equalPredicate("Hello");
        // removed other assertion
        assertEquals(true, PredicateUtils.transformedPredicate(t, p).evaluate(Boolean.TRUE));
    }

@Test
    public void testSingletonPatternInSerialization_1_oe() {
        final Object[] singletones = new Object[] {
                ExceptionPredicate.INSTANCE,
                FalsePredicate.INSTANCE,
                NotNullPredicate.INSTANCE,
                NullPredicate.INSTANCE,
                TruePredicate.INSTANCE
        };

        for (final Object original : singletones) {
            TestUtils.assertSameAfterSerialization( "Singletone patern broken for " + original.getClass(), original );
    }
    }

}
