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
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for methods of {@link org.apache.commons.lang3.RegExUtils} which been moved to their own test classes.
 */
public class RegExUtilsTest_OE25Dev {

    @Test
    public void testRemoveAll_StringString_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    RegExUtils.removeAll("any", "{badRegexSyntax}");
    org.junit.jupiter.api.Assertions.fail("PatternSyntaxException: RegExUtils.removeAll expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testRemoveFirst_StringString_11_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    RegExUtils.removeFirst("any", "{badRegexSyntax}");
    org.junit.jupiter.api.Assertions.fail("PatternSyntaxException: RegExUtils.removeFirst expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testReplaceAll_StringStringString_14_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    RegExUtils.replaceAll("any", "{badRegexSyntax}", "");
    org.junit.jupiter.api.Assertions.fail("PatternSyntaxException: RegExUtils.replaceAll expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testReplaceFirst_StringStringString_14_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    RegExUtils.replaceFirst("any", "{badRegexSyntax}", "");
    org.junit.jupiter.api.Assertions.fail("PatternSyntaxException: RegExUtils.replaceFirst expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

}
