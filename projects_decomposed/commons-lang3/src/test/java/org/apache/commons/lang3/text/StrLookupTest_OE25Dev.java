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

package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;

/**
 * Test class for StrLookup.
 */
@Deprecated
public class StrLookupTest_OE25Dev  {

    //-----------------------------------------------------------------------

    /**
     * Tests that a lookup object for system properties can deal with a full
     * replacement of the system properties object. This test is related to
     * LANG-1055.
     */
    @Test
    public void testSystemPropertiesLookupReplacedProperties() {
        final Properties oldProperties = System.getProperties();
        final String osName = "os.name";
        final String newOsName = oldProperties.getProperty(osName) + "_changed";

        final StrLookup<String> sysLookup = StrLookup.systemPropertiesLookup();
        final Properties newProps = new Properties();
        newProps.setProperty(osName, newOsName);
        System.setProperties(newProps);
        try {
            assertEquals(newOsName, sysLookup.lookup(osName), "Changed properties not detected");
        } finally {
            System.setProperties(oldProperties);
        }
    }

    /**
     * Tests that a lookup object for system properties sees changes on system
     * properties. This test is related to LANG-1141.
     */
    @Test
    public void testSystemPropertiesLookupUpdatedProperty() {
        final String osName = "os.name";
        final String oldOs = System.getProperty(osName);
        final String newOsName = oldOs + "_changed";

        final StrLookup<String> sysLookup = StrLookup.systemPropertiesLookup();
        System.setProperty(osName, newOsName);
        try {
            assertEquals(newOsName, sysLookup.lookup(osName), "Changed properties not detected");
        } finally {
            System.setProperty(osName, oldOs);
        }
    }

    @Test
    public void testSystemPropertiesLookup_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    StrLookup.systemPropertiesLookup().lookup(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
