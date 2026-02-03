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

    public void testInvalidReturn_1_oe_1_oe() throws IOException {
        new TestReturn01Creator().create();
                final String classname = "TestReturn01";
        final String message = "Verification of a void method that returns an object must fail.";
        final String testClassname = TEST_PACKAGE + classname;
                assertFalse(message, doAllPasses(testClassname));
    }

    public void testInvalidReturn_2_oe_1_oe() throws IOException {
        new TestReturn01Creator().create();
        // removed other assertion
        new TestReturn03Creator().create();
                final String classname = "TestReturn03";
        final String message = "Verification of an int method that returns null must fail.";
        final String testClassname = TEST_PACKAGE + classname;
                assertFalse(message, doAllPasses(testClassname));
    }

    public void testValidReturn_1_oe_1_oe() {
                final String classname = "TestReturn02";
        final String message = "Verification of a method that returns a newly created object must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

    public void testValidReturn_2_oe_1_oe() {
        // removed other assertion
                final String classname = "TestArray01";
        final String message = "Verification of a method that returns an array must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

}
