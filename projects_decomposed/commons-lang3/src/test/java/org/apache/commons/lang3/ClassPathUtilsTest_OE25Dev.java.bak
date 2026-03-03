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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 */
public class ClassPathUtilsTest_OE25Dev {

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new ClassPathUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        // removed other assertion
        final Constructor<?>[] cons = ClassPathUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        // removed other assertion
        final Constructor<?>[] cons = ClassPathUtils.class.getDeclaredConstructors();
        // removed other assertion
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        // removed other assertion
        final Constructor<?>[] cons = ClassPathUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        assertTrue(Modifier.isPublic(ClassPathUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        // removed other assertion
        final Constructor<?>[] cons = ClassPathUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Modifier.isFinal(ClassPathUtils.class.getModifiers()));
    }

    @Test
    public void testToFullyQualifiedNameNullClassString_1_oe() throws Exception {
        try {
    ClassPathUtils.toFullyQualifiedName((Class<?>) null, "Test.properties");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToFullyQualifiedNameClassNull_1_oe() throws Exception {
        try {
    ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class, null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToFullyQualifiedNameClassString_1_oe() {
        final String expected = "org.apache.commons.lang3.Test.properties";
        final String actual = ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class, "Test.properties");

        assertEquals(expected, actual);
    }

    @Test
    public void testToFullyQualifiedNameNullPackageString_1_oe() throws Exception {
        try {
    ClassPathUtils.toFullyQualifiedName((Package) null, "Test.properties");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToFullyQualifiedNamePackageNull_1_oe() throws Exception {
        try {
    ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class.getPackage(), null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToFullyQualifiedNamePackageString_1_oe() {
        final String expected = "org.apache.commons.lang3.Test.properties";
        final String actual = ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class.getPackage(), "Test.properties");

        assertEquals(expected, actual);
    }

    @Test
    public void testToFullyQualifiedPathClassNullString_1_oe() throws Exception {
        try {
    ClassPathUtils.toFullyQualifiedPath((Class<?>) null, "Test.properties");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToFullyQualifiedPathClassNull_1_oe() throws Exception {
        try {
    ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class, null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToFullyQualifiedPathClass_1_oe() {
        final String expected = "org/apache/commons/lang3/Test.properties";
        final String actual = ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class, "Test.properties");

        assertEquals(expected, actual);
    }

    @Test
    public void testToFullyQualifiedPathPackageNullString_1_oe() throws Exception {
        try {
    ClassPathUtils.toFullyQualifiedPath((Package) null, "Test.properties");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToFullyQualifiedPathPackageNull_1_oe() throws Exception {
        try {
    ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class.getPackage(), null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToFullyQualifiedPathPackage_1_oe() {
        final String expected = "org/apache/commons/lang3/Test.properties";
        final String actual = ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class.getPackage(), "Test.properties");

        assertEquals(expected, actual);
    }

}
