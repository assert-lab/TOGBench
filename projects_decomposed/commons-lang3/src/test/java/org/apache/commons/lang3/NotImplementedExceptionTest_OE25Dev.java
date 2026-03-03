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
                final String assertMessage0 = "Issue in (String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = null;
        final String code0 = null;
        assertNotNull(nie0, assertMessage0 + ": target is null");
    }

    @Test
    public void testConstructors_1_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
                final String assertMessage0 = "Issue in (String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = null;
        final String code0 = null;
                assertEquals(message0, nie0.getMessage(), assertMessage0 + ": Message not equal");
    }

    @Test
    public void testConstructors_1_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
                final String assertMessage0 = "Issue in (String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = null;
        final String code0 = null;
                assertEquals(nested0, nie0.getCause(), assertMessage0 + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_1_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
                final String assertMessage0 = "Issue in (String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = null;
        final String code0 = null;
                assertEquals(code0, nie0.getCode(), assertMessage0 + ": Code not equal");
    }

    @Test
    public void testConstructors_2_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
                final String assertMessage0 = "Issue in (Throwable)";
        final NotImplementedException nie0 = nie;
        final String message0 = nested.toString();
        final Throwable nested0 = nested;
        final String code0 = null;
        assertNotNull(nie0, assertMessage0 + ": target is null");
    }

    @Test
    public void testConstructors_2_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
                final String assertMessage0 = "Issue in (Throwable)";
        final NotImplementedException nie0 = nie;
        final String message0 = nested.toString();
        final Throwable nested0 = nested;
        final String code0 = null;
                assertEquals(message0, nie0.getMessage(), assertMessage0 + ": Message not equal");
    }

    @Test
    public void testConstructors_2_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
                final String assertMessage0 = "Issue in (Throwable)";
        final NotImplementedException nie0 = nie;
        final String message0 = nested.toString();
        final Throwable nested0 = nested;
        final String code0 = null;
                assertEquals(nested0, nie0.getCause(), assertMessage0 + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_2_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
                final String assertMessage0 = "Issue in (Throwable)";
        final NotImplementedException nie0 = nie;
        final String message0 = nested.toString();
        final Throwable nested0 = nested;
        final String code0 = null;
                assertEquals(code0, nie0.getCode(), assertMessage0 + ": Code not equal");
    }

    @Test
    public void testConstructors_3_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
                final String assertMessage0 = "Issue in (String, Throwable)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = nested;
        final String code0 = null;
        assertNotNull(nie0, assertMessage0 + ": target is null");
    }

    @Test
    public void testConstructors_3_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
                final String assertMessage0 = "Issue in (String, Throwable)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = nested;
        final String code0 = null;
                assertEquals(message0, nie0.getMessage(), assertMessage0 + ": Message not equal");
    }

    @Test
    public void testConstructors_3_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
                final String assertMessage0 = "Issue in (String, Throwable)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = nested;
        final String code0 = null;
                assertEquals(nested0, nie0.getCause(), assertMessage0 + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_3_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
                final String assertMessage0 = "Issue in (String, Throwable)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = nested;
        final String code0 = null;
                assertEquals(code0, nie0.getCode(), assertMessage0 + ": Code not equal");
    }

    @Test
    public void testConstructors_4_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
                final String assertMessage0 = "Issue in (String, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = null;
        final String code0 = code;
        assertNotNull(nie0, assertMessage0 + ": target is null");
    }

    @Test
    public void testConstructors_4_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
                final String assertMessage0 = "Issue in (String, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = null;
        final String code0 = code;
                assertEquals(message0, nie0.getMessage(), assertMessage0 + ": Message not equal");
    }

    @Test
    public void testConstructors_4_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
                final String assertMessage0 = "Issue in (String, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = null;
        final String code0 = code;
                assertEquals(nested0, nie0.getCause(), assertMessage0 + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_4_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
                final String assertMessage0 = "Issue in (String, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = null;
        final String code0 = code;
                assertEquals(code0, nie0.getCode(), assertMessage0 + ": Code not equal");
    }

    @Test
    public void testConstructors_5_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
        nie = new NotImplementedException(nested, code);
                final String assertMessage0 = "Issue in (Throwable, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = nested.toString();
        final Throwable nested0 = nested;
        final String code0 = code;
        assertNotNull(nie0, assertMessage0 + ": target is null");
    }

    @Test
    public void testConstructors_5_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
        nie = new NotImplementedException(nested, code);
                final String assertMessage0 = "Issue in (Throwable, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = nested.toString();
        final Throwable nested0 = nested;
        final String code0 = code;
                assertEquals(message0, nie0.getMessage(), assertMessage0 + ": Message not equal");
    }

    @Test
    public void testConstructors_5_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
        nie = new NotImplementedException(nested, code);
                final String assertMessage0 = "Issue in (Throwable, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = nested.toString();
        final Throwable nested0 = nested;
        final String code0 = code;
                assertEquals(nested0, nie0.getCause(), assertMessage0 + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_5_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
        nie = new NotImplementedException(nested, code);
                final String assertMessage0 = "Issue in (Throwable, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = nested.toString();
        final Throwable nested0 = nested;
        final String code0 = code;
                assertEquals(code0, nie0.getCode(), assertMessage0 + ": Code not equal");
    }

    @Test
    public void testConstructors_6_oe_1_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
        nie = new NotImplementedException(nested, code);
        nie = new NotImplementedException(message, nested, code);
                final String assertMessage0 = "Issue in (String, Throwable, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = nested;
        final String code0 = code;
        assertNotNull(nie0, assertMessage0 + ": target is null");
    }

    @Test
    public void testConstructors_6_oe_2_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
        nie = new NotImplementedException(nested, code);
        nie = new NotImplementedException(message, nested, code);
                final String assertMessage0 = "Issue in (String, Throwable, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = nested;
        final String code0 = code;
                assertEquals(message0, nie0.getMessage(), assertMessage0 + ": Message not equal");
    }

    @Test
    public void testConstructors_6_oe_3_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
        nie = new NotImplementedException(nested, code);
        nie = new NotImplementedException(message, nested, code);
                final String assertMessage0 = "Issue in (String, Throwable, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = nested;
        final String code0 = code;
                assertEquals(nested0, nie0.getCause(), assertMessage0 + ": Nested throwable not equal");
    }

    @Test
    public void testConstructors_6_oe_4_oe() {
        final Throwable nested = new RuntimeException();
        final String message = "Not Implemented";
        final String code = "CODE";

        NotImplementedException nie = new NotImplementedException(message);
        nie = new NotImplementedException(nested);
        nie = new NotImplementedException(message, nested);
        nie = new NotImplementedException(message, code);
        nie = new NotImplementedException(nested, code);
        nie = new NotImplementedException(message, nested, code);
                final String assertMessage0 = "Issue in (String, Throwable, String)";
        final NotImplementedException nie0 = nie;
        final String message0 = message;
        final Throwable nested0 = nested;
        final String code0 = code;
                assertEquals(code0, nie0.getCode(), assertMessage0 + ": Code not equal");
    }

}
