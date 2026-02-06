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
package org.apache.commons.beanutils.expression;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Junit Test for BasicResolver.
 *
 * @version $Id$
 */
public class DefaultResolverTestCase_OE25Dev extends TestCase {

    private final DefaultResolver resolver = new DefaultResolver();

    // Simple Properties Test Data
    private final String[] validProperties = new String[] {null, "", "a", "bc", "def", "g.h", "ij.k", "lm.no", "pqr.stu"};
    private final String[] validNames      = new String[] {null, "", "a", "bc", "def", "g",   "ij",   "lm",    "pqr"};

    // Indexed Properties Test Data
    private final String[] validIndexProperties = new String[] {"a[1]", "b[12]", "cd[3]", "ef[45]", "ghi[6]", "jkl[789]", };
    private final String[] validIndexNames      = new String[] {"a",    "b",     "cd",    "ef",     "ghi",    "jkl"};
    private final int[]    validIndexValues     = new int[]    {1,      12,      3,       45,       6,        789};

    // Mapped Properties Test Data
    private final String[] validMapProperties = new String[] {"a(b)", "c(de)", "fg(h)", "ij(kl)", "mno(pqr.s)", "tuv(wx).yz[1]"};
    private final String[] validMapNames      = new String[] {"a",    "c",     "fg",    "ij",     "mno",        "tuv"};
    private final String[] validMapKeys       = new String[] {"b",    "de",    "h",     "kl",     "pqr.s",      "wx"};

    private final String[] nextExpressions   = new String[] {"a", "bc", "d.e", "fg.h", "ij.kl", "m(12)", "no(3.4)", "pq(r).s", "t[12]", "uv[34].wx"};
    private final String[] nextProperties    = new String[] {"a", "bc", "d",   "fg",   "ij",    "m(12)", "no(3.4)", "pq(r)",   "t[12]", "uv[34]"};
    private final String[] removeProperties  = new String[] {null, null, "e",  "h",    "kl",    null,    null,      "s",       null,    "wx"};

    /**
     * Construct a DefaultResolver Test Case.
     * @param name The name of the test
     */
    public DefaultResolverTestCase_OE25Dev(final String name) {
        super(name);
    }

    // ------------------------------------------------------------------------

    /**
     * Create Test Suite
     * @return test suite
     */
    public static TestSuite suite() {
        return new TestSuite(DefaultResolverTestCase_OE25Dev.class);
    }

    /**
     * Set Up
     */
    @Override
    protected void setUp() {
    }

    /**
     * Tear Down
     */
    @Override
    protected void tearDown() {
    }

    // ------------------------------------------------------------------------

    /**
     * Test getIndex() method.
     */

    /**
     * Test getMapKey() method.
     */

    /**
     * Test isIndexed() method.
     */

    /**
     * Test isMapped() method.
     */

    /**
     * Test getName() method.
     */

    /**
     * Test next() method.
     */

    /**
     * Test remove() method.
     */

    private String label(final String expression, final int i) {
        return "Expression[" + i + "]=\"" + expression + "\"";
    }

    public void testGetIndex_2_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetIndex_4_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetIndex_6_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect -1)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetIndex_8_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect -1)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Missing Index Value
        label = "Missing Index";
        try {
            final int index  = resolver.getIndex("foo[]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            assertEquals(label + " Error Message", "No Index Value", e.getMessage());
    }
    }

    public void testGetIndex_9_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect -1)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Missing Index Value
        label = "Missing Index";
        try {
            final int index  = resolver.getIndex("foo[]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            fail(label + " expected IllegalArgumentException: " + t);
    }
    }

    public void testGetIndex_11_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect -1)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Missing Index Value
        label = "Missing Index";
        try {
            final int index  = resolver.getIndex("foo[]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Malformed
        label = "Malformed";
        try {
            final int index  = resolver.getIndex("foo[12");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            assertEquals(label + " Error Message", "Missing End Delimiter", e.getMessage());
    }
    }

    public void testGetIndex_12_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect -1)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Missing Index Value
        label = "Missing Index";
        try {
            final int index  = resolver.getIndex("foo[]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Malformed
        label = "Malformed";
        try {
            final int index  = resolver.getIndex("foo[12");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            fail(label + " expected IllegalArgumentException: " + t);
    }
    }

    public void testGetIndex_14_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect -1)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Missing Index Value
        label = "Missing Index";
        try {
            final int index  = resolver.getIndex("foo[]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Malformed
        label = "Malformed";
        try {
            final int index  = resolver.getIndex("foo[12");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Non-numeric
        label = "Malformed";
        try {
            final int index  = resolver.getIndex("foo[BAR]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            assertEquals(label + " Error Message", "Invalid index value 'BAR'", e.getMessage());
    }
    }

    public void testGetIndex_15_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect -1)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Missing Index Value
        label = "Missing Index";
        try {
            final int index  = resolver.getIndex("foo[]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Malformed
        label = "Malformed";
        try {
            final int index  = resolver.getIndex("foo[12");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Non-numeric
        label = "Malformed";
        try {
            final int index  = resolver.getIndex("foo[BAR]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            fail(label + " expected IllegalArgumentException: " + t);
    }
    }

    public void testGetMapKey_2_oe() {
        String label = null;

        // Simple Properties (expect null)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetMapKey_4_oe() {
        String label = null;

        // Simple Properties (expect null)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect null)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetMapKey_6_oe() {
        String label = null;

        // Simple Properties (expect null)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect null)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect correct map key)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetMapKey_8_oe() {
        String label = null;

        // Simple Properties (expect null)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect null)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect correct map key)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Malformed
        label = "Malformed";
        try {
            final String key  = resolver.getKey("foo(bar");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            assertEquals(label + " Error Message", "Missing End Delimiter", e.getMessage());
    }
    }

    public void testGetMapKey_9_oe() {
        String label = null;

        // Simple Properties (expect null)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect null)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect correct map key)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Malformed
        label = "Malformed";
        try {
            final String key  = resolver.getKey("foo(bar");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final Throwable t) {
            fail(label + " expected IllegalArgumentException: " + t);
    }
    }

    public void testIsIndexed_2_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testIsIndexed_4_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testIsIndexed_6_oe() {
        String label = null;

        // Simple Properties (expect -1)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect correct index value)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect -1)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testIsMapped_2_oe() {
        String label = null;

        // Simple Properties (expect null)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testIsMapped_4_oe() {
        String label = null;

        // Simple Properties (expect null)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect null)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testIsMapped_6_oe() {
        String label = null;

        // Simple Properties (expect null)
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties (expect null)
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties (expect correct map key)
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetName_2_oe() {
        String label = null;

        // Simple Properties
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetName_4_oe() {
        String label = null;

        // Simple Properties
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testGetName_6_oe() {
        String label = null;

        // Simple Properties
        for (int i = 0; i < validProperties.length; i++) {
            try {
                label = "Simple " + label(validProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Indexed Properties
        for (int i = 0; i < validIndexProperties.length; i++) {
            try {
                label = "Indexed " + label(validIndexProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }
        }

        // Mapped Properties
        for (int i = 0; i < validMapProperties.length; i++) {
            try {
                label = "Mapped " + label(validMapProperties[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testNext_2_oe() {
        String label = null;
        for (int i = 0; i < nextExpressions.length; i++) {
            try {
                label = label(nextExpressions[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

    public void testRemove_2_oe() {
        String label = null;
        for (int i = 0; i < nextExpressions.length; i++) {
            try {
                label = label(nextExpressions[i], i);
                // removed other assertion
            } catch (final Throwable t) {
                fail(label + " threw " + t);
    }
    }
    }

}
