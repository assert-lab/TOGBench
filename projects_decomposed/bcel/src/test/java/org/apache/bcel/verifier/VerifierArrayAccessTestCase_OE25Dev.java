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

import java.io.IOException;

import org.apache.bcel.verifier.tests.TestArrayAccess02Creator;
import org.apache.bcel.verifier.tests.TestArrayAccess03Creator;
import org.apache.bcel.verifier.tests.TestArrayAccess04Creator;

public class VerifierArrayAccessTestCase_OE25Dev extends AbstractVerifierTestCase {


    public void testInvalidArrayAccess_1_oe_1_oe() throws IOException {
        new TestArrayAccess03Creator().create();
                final String classname = "TestArrayAccess03";
        final String message = "Verification of an arraystore instruction on an object must fail.";
        final String testClassname = TEST_PACKAGE + classname;
                assertFalse(message, doAllPasses(testClassname));
    }

    public void testInvalidArrayAccess_2_oe_1_oe() throws IOException {
        new TestArrayAccess03Creator().create();
        // removed other assertion
        new TestArrayAccess04Creator().create();
                final String classname = "TestArrayAccess04";
        final String message = "Verification of an arraystore instruction of an int on an array of references must fail.";
        final String testClassname = TEST_PACKAGE + classname;
                assertFalse(message, doAllPasses(testClassname));
    }

    public void testValidArrayAccess_1_oe_1_oe() throws IOException {
                final String classname = "TestArrayAccess01";
        final String message = "Verification of an arraystore instruction on an array that is not compatible with the stored element must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

    public void testValidArrayAccess_2_oe_1_oe() throws IOException {
        // removed other assertion
        new TestArrayAccess02Creator().create();
                final String classname = "TestArrayAccess02";
        final String message = "Verification of an arraystore instruction on an array that is not compatible with the stored element must pass.";
        final String testClassname = TEST_PACKAGE + classname;
                assertTrue(message, doAllPasses(testClassname));
    }

}
