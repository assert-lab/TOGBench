/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.bcel.verifier;

import java.io.IOException;

import org.apache.bcel.verifier.tests.TestReturn01Creator;
import org.apache.bcel.verifier.tests.TestReturn03Creator;

public class VerifierReturnTestCase_OE25Dev extends AbstractVerifierTestCase {

    public void testInvalidReturn() throws IOException {
        new TestReturn01Creator().create();
        assertVerifyRejected("TestReturn01", "Verification of a void method that returns an object must fail.");
        new TestReturn03Creator().create();
        assertVerifyRejected("TestReturn03", "Verification of an int method that returns null must fail.");
    }

    public void testValidReturn() {
        assertVerifyOK("TestReturn02", "Verification of a method that returns a newly created object must pass.");
        assertVerifyOK("TestArray01", "Verification of a method that returns an array must pass.");
    }

    public void testInvalidReturn_1_oe_1_oe() throws IOException {
        new TestReturn01Creator().create();
                final String classname0 = "TestReturn01";
        final String message0 = "Verification of a void method that returns an object must fail.";
        final String testClassname0 = TEST_PACKAGE + classname0;
                assertFalse(message0, doAllPasses(testClassname0));
    }

    public void testInvalidReturn_2_oe_1_oe() throws IOException {
        new TestReturn01Creator().create();
        // removed other assertion
        new TestReturn03Creator().create();
                final String classname0 = "TestReturn03";
        final String message0 = "Verification of an int method that returns null must fail.";
        final String testClassname0 = TEST_PACKAGE + classname0;
                assertFalse(message0, doAllPasses(testClassname0));
    }

    public void testValidReturn_1_oe_1_oe() {
                final String classname0 = "TestReturn02";
        final String message0 = "Verification of a method that returns a newly created object must pass.";
        final String testClassname0 = TEST_PACKAGE + classname0;
                assertTrue(message0, doAllPasses(testClassname0));
    }

    public void testValidReturn_2_oe_1_oe() {
        // removed other assertion
                final String classname0 = "TestArray01";
        final String message0 = "Verification of a method that returns an array must pass.";
        final String testClassname0 = TEST_PACKAGE + classname0;
                assertTrue(message0, doAllPasses(testClassname0));
    }

}
