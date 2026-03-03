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

package org.apache.commons.jexl3.junit;

import org.apache.commons.jexl3.Foo;
import org.apache.commons.jexl3.JexlTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Simple testcases
 *
 *  @since 1.0
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class AsserterTest_OE25Dev extends JexlTestCase {
    public AsserterTest_OE25Dev() {
        super("AsserterTest_OE25Dev");
    }

@Test
    public void testThis_1_oe() throws Exception {
        final Asserter asserter = new Asserter(JEXL);
        asserter.setVariable("this", new Foo());
        asserter.assertExpression("this.repeat('abc')", "Repeat : abc");
    }

@Test
    public void testVariable_1_oe() throws Exception {
        final Asserter asserter = new Asserter(JEXL);
        asserter.setSilent(true);
        asserter.setVariable("foo", new Foo());
        asserter.setVariable("person", "James");

        asserter.assertExpression("person", "James");
    }

@Test
    public void testVariable_2_oe() throws Exception {
        final Asserter asserter = new Asserter(JEXL);
        asserter.setSilent(true);
        asserter.setVariable("foo", new Foo());
        asserter.setVariable("person", "James");

        asserter.assertExpression("size(person)", new Integer(5));
    }

@Test
    public void testVariable_3_oe() throws Exception {
        final Asserter asserter = new Asserter(JEXL);
        asserter.setSilent(true);
        asserter.setVariable("foo", new Foo());
        asserter.setVariable("person", "James");


        asserter.assertExpression("foo.getCount()", new Integer(5));
    }

@Test
    public void testVariable_4_oe() throws Exception {
        final Asserter asserter = new Asserter(JEXL);
        asserter.setSilent(true);
        asserter.setVariable("foo", new Foo());
        asserter.setVariable("person", "James");


        asserter.assertExpression("foo.count", new Integer(5));
    }

}
