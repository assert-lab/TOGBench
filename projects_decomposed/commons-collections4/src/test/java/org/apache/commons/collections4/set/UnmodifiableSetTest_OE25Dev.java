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
package org.apache.commons.collections4.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.Unmodifiable;

/**
 * Extension of {@link AbstractSetTest} for exercising the
 * {@link UnmodifiableSet} implementation.
 *
 * @since 3.0
 */
public class UnmodifiableSetTest_OE25Dev<E> extends AbstractSetTest<E> {

    public UnmodifiableSetTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return new junit.framework.TestSuite(UnmodifiableSetTest_OE25Dev.class);
    }

    //-------------------------------------------------------------------
    @Override
    public Set<E> makeObject() {
        return UnmodifiableSet.unmodifiableSet(new HashSet<E>());
    }

    @Override
    public Set<E> makeFullCollection() {
        final HashSet<E> set = new HashSet<>();
        set.addAll(Arrays.asList(getFullElements()));
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public boolean isAddSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/UnmodifiableSet.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/UnmodifiableSet.fullCollection.version4.obj");
//    }

public void testUnmodifiable_1_oe() {
        assertTrue(makeObject() instanceof Unmodifiable);
    }

public void testUnmodifiable_2_oe() {
        // removed other assertion
        assertTrue(makeFullCollection() instanceof Unmodifiable);
    }

public void testDecorateFactory_1_oe() {
        final Set<E> set = makeFullCollection();
        assertSame(set, UnmodifiableSet.unmodifiableSet(set));
    }

}
