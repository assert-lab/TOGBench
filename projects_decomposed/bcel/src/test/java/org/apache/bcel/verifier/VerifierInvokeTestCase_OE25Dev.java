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
 *
 */

package org.apache.bcel.verifier;


public class VerifierInvokeTestCase_OE25Dev extends AbstractVerifierTestCase {

    public void testLegalInvokeVirtual_1_oe_1_oe() {
                final String classname = "TestLegalInvokeVirtual01";
        final String message = "Verification of invokevirtual on method defined in superclass must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

    public void testLegalInvokeVirtual_2_oe_1_oe() {
        // removed other assertion
                final String classname = "TestLegalInvokeVirtual02";
        final String message = "Verification of invokevirtual on method defined in superinterface must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

    public void testLegalInvokeStatic_1_oe_1_oe() {
                final String classname = "TestLegalInvokeStatic01";
        final String message = "Verification of invokestatic on method defined in superclass must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

    public void testLegalInvokeInterface_1_oe_1_oe() {
                final String classname = "TestLegalInvokeInterface01";
        final String message = "Verification of invokeinterface on method defined in superinterface must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

    public void testLegalInvokeSpecial_1_oe_1_oe() {
                final String classname = "TestLegalInvokeSpecial01";
        final String message = "Verification of invokespecial on method defined in superclass must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

    public void testLegalInvokeSpecial_2_oe_1_oe() {
        // removed other assertion
                final String classname = "TestLegalInvokeSpecial02";
        final String message = "Verification of invokespecial on method defined in superclass must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

}
