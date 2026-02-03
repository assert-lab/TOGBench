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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.NotImplementedException}.
 */
public class NotImplementedExceptionTest_OE25Dev {

    private void assertCorrect(final String assertMessage, final NotImplementedException nie, final String message, final Throwable nested, final String code) {
        assertNotNull(nie, assertMessage + ": target is null");
        assertEquals(message, nie.getMessage(), assertMessage + ": Message not equal");
        assertEquals(nested, nie.getCause(), assertMessage + ": Nested throwable not equal");
        assertEquals(code, nie.getCode(), assertMessage + ": Code not equal");
    }

    @Test
    public void testConstructors_1_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
                final String assertMessage = "Issue in (String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = null;
        final String code1 = null;
        assertNotNull(nie1, assertMessage + ": target is null");
    }

    @Test
    public void testConstructors_1_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
                final String assertMessage = "Issue in (String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = null;
        final String code1 = null;
        // removed other assertion
                assertEquals(message1, nie1.getMessage(), assertMessage + ": Message not equal");
    }

    @Test
    public void testConstructors_1_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
                final String assertMessage = "Issue in (String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = null;
        final String code1 = null;
        // removed other assertion
                // removed other assertion
                assertEquals(nested1, nie1.getCause(), assertMessage + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_1_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
                final String assertMessage = "Issue in (String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = null;
        final String code1 = null;
        // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(code1, nie1.getCode(), assertMessage + ": Code not equal");
    }

    @Test
    public void testConstructors_2_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
                final String assertMessage = "Issue in (Throwable)";
        final NotImplementedException nie1 = nie;
        final String message1 = nested.toString();
        final Throwable nested1 = nested;
        final String code1 = null;
        assertNotNull(nie1, assertMessage + ": target is null");
    }

    @Test
    public void testConstructors_2_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
                final String assertMessage = "Issue in (Throwable)";
        final NotImplementedException nie1 = nie;
        final String message1 = nested.toString();
        final Throwable nested1 = nested;
        final String code1 = null;
        // removed other assertion
                assertEquals(message1, nie1.getMessage(), assertMessage + ": Message not equal");
    }

    @Test
    public void testConstructors_2_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
                final String assertMessage = "Issue in (Throwable)";
        final NotImplementedException nie1 = nie;
        final String message1 = nested.toString();
        final Throwable nested1 = nested;
        final String code1 = null;
        // removed other assertion
                // removed other assertion
                assertEquals(nested1, nie1.getCause(), assertMessage + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_2_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
                final String assertMessage = "Issue in (Throwable)";
        final NotImplementedException nie1 = nie;
        final String message1 = nested.toString();
        final Throwable nested1 = nested;
        final String code1 = null;
        // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(code1, nie1.getCode(), assertMessage + ": Code not equal");
    }

    @Test
    public void testConstructors_3_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
                final String assertMessage = "Issue in (String, Throwable)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = nested;
        final String code1 = null;
        assertNotNull(nie1, assertMessage + ": target is null");
    }

    @Test
    public void testConstructors_3_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
                final String assertMessage = "Issue in (String, Throwable)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = nested;
        final String code1 = null;
        // removed other assertion
                assertEquals(message1, nie1.getMessage(), assertMessage + ": Message not equal");
    }

    @Test
    public void testConstructors_3_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
                final String assertMessage = "Issue in (String, Throwable)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = nested;
        final String code1 = null;
        // removed other assertion
                // removed other assertion
                assertEquals(nested1, nie1.getCause(), assertMessage + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_3_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
                final String assertMessage = "Issue in (String, Throwable)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = nested;
        final String code1 = null;
        // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(code1, nie1.getCode(), assertMessage + ": Code not equal");
    }

    @Test
    public void testConstructors_4_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
                final String assertMessage = "Issue in (String, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = null;
        final String code1 = code;
        assertNotNull(nie1, assertMessage + ": target is null");
    }

    @Test
    public void testConstructors_4_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
                final String assertMessage = "Issue in (String, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = null;
        final String code1 = code;
        // removed other assertion
                assertEquals(message1, nie1.getMessage(), assertMessage + ": Message not equal");
    }

    @Test
    public void testConstructors_4_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
                final String assertMessage = "Issue in (String, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = null;
        final String code1 = code;
        // removed other assertion
                // removed other assertion
                assertEquals(nested1, nie1.getCause(), assertMessage + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_4_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
                final String assertMessage = "Issue in (String, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = null;
        final String code1 = code;
        // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(code1, nie1.getCode(), assertMessage + ": Code not equal");
    }

    @Test
    public void testConstructors_5_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
        // removed other assertion
        nie = new NotImplementedException(nested, code);
                final String assertMessage = "Issue in (Throwable, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = nested.toString();
        final Throwable nested1 = nested;
        final String code1 = code;
        assertNotNull(nie1, assertMessage + ": target is null");
    }

    @Test
    public void testConstructors_5_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
        // removed other assertion
        nie = new NotImplementedException(nested, code);
                final String assertMessage = "Issue in (Throwable, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = nested.toString();
        final Throwable nested1 = nested;
        final String code1 = code;
        // removed other assertion
                assertEquals(message1, nie1.getMessage(), assertMessage + ": Message not equal");
    }

    @Test
    public void testConstructors_5_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
        // removed other assertion
        nie = new NotImplementedException(nested, code);
                final String assertMessage = "Issue in (Throwable, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = nested.toString();
        final Throwable nested1 = nested;
        final String code1 = code;
        // removed other assertion
                // removed other assertion
                assertEquals(nested1, nie1.getCause(), assertMessage + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_5_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
        // removed other assertion
        nie = new NotImplementedException(nested, code);
                final String assertMessage = "Issue in (Throwable, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = nested.toString();
        final Throwable nested1 = nested;
        final String code1 = code;
        // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(code1, nie1.getCode(), assertMessage + ": Code not equal");
    }

    @Test
    public void testConstructors_6_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
        // removed other assertion
        nie = new NotImplementedException(nested, code);
        // removed other assertion
        nie = new NotImplementedException(message, nested, code);
                final String assertMessage = "Issue in (String, Throwable, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = nested;
        final String code1 = code;
        assertNotNull(nie1, assertMessage + ": target is null");
    }

    @Test
    public void testConstructors_6_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
        // removed other assertion
        nie = new NotImplementedException(nested, code);
        // removed other assertion
        nie = new NotImplementedException(message, nested, code);
                final String assertMessage = "Issue in (String, Throwable, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = nested;
        final String code1 = code;
        // removed other assertion
                assertEquals(message1, nie1.getMessage(), assertMessage + ": Message not equal");
    }

    @Test
    public void testConstructors_6_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
        // removed other assertion
        nie = new NotImplementedException(nested, code);
        // removed other assertion
        nie = new NotImplementedException(message, nested, code);
                final String assertMessage = "Issue in (String, Throwable, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = nested;
        final String code1 = code;
        // removed other assertion
                // removed other assertion
                assertEquals(nested1, nie1.getCause(), assertMessage + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_6_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        // removed other assertion
        nie = new NotImplementedException(nested);
        // removed other assertion
        nie = new NotImplementedException(message, nested);
        // removed other assertion
        nie = new NotImplementedException(message, code);
        // removed other assertion
        nie = new NotImplementedException(nested, code);
        // removed other assertion
        nie = new NotImplementedException(message, nested, code);
                final String assertMessage = "Issue in (String, Throwable, String)";
        final NotImplementedException nie1 = nie;
        final String message1 = message;
        final Throwable nested1 = nested;
        final String code1 = code;
        // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(code1, nie1.getCode(), assertMessage + ": Code not equal");
    }

}
