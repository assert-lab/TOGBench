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
package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.compare.ObjectToStringComparator;
import org.apache.commons.lang3.reflect.testbed.Ambig;
import org.apache.commons.lang3.reflect.testbed.Annotated;
import org.apache.commons.lang3.reflect.testbed.Foo;
import org.apache.commons.lang3.reflect.testbed.PrivatelyShadowedChild;
import org.apache.commons.lang3.reflect.testbed.PublicChild;
import org.apache.commons.lang3.reflect.testbed.PubliclyShadowedChild;
import org.apache.commons.lang3.reflect.testbed.StaticContainer;
import org.apache.commons.lang3.reflect.testbed.StaticContainerChild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests FieldUtils
 */
public class FieldUtilsTest_OE25Dev {

    private static final String JACOCO_DATA_FIELD_NAME = "$jacocoData";
    static final Integer I0 = Integer.valueOf(0);
    static final Integer I1 = Integer.valueOf(1);
    static final Double D0 = Double.valueOf(0.0);
    static final Double D1 = Double.valueOf(1.0);

    @Annotated
    private PublicChild publicChild;
    private PubliclyShadowedChild publiclyShadowedChild;
    @Annotated
    private PrivatelyShadowedChild privatelyShadowedChild;
    private final Class<? super PublicChild> parentClass = PublicChild.class.getSuperclass();

    @BeforeEach
    public void setUp() {
        StaticContainer.reset();
        publicChild = new PublicChild();
        publiclyShadowedChild = new PubliclyShadowedChild();
        privatelyShadowedChild = new PrivatelyShadowedChild();
    }

    private Field[] sort(final Field[] fields) {
        // Field does not implement Comparable, so we use a KISS solution here.
        return ArraySorter.sort(fields, ObjectToStringComparator.INSTANCE);
    }

    /**
     * Read the {@code @deprecated} notice on
     * {@link FieldUtils#removeFinalModifier(Field, boolean)}.
     *
     * @param field {@link Field} to be curried into
     *              {@link FieldUtils#removeFinalModifier(Field, boolean)}.
     * @param forceAccess {@link Boolean} to be curried into
     *              {@link FieldUtils#removeFinalModifier(Field, boolean)}.
     */
    private void callRemoveFinalModifierCheckForException(final Field field, final Boolean forceAccess) {
        try {
            FieldUtils.removeFinalModifier(field, forceAccess);
        } catch (final UnsupportedOperationException exception) {
            if (SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_12)) {
                assertTrue(exception.getCause() instanceof NoSuchFieldException);
            } else {
                fail("No exception should be thrown for java prior to 12.0");
            }
        }
    }

    @Test
    public void testGetFieldIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getField(null, "none");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, "");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldIllegalArgumentException4_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, " ");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getField(null, "none", true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, null, true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, "", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException4_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, " ", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsWithAnnotation(FieldUtilsTest.class, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsWithAnnotation(null, Annotated.class);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsWithAnnotation(null, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsListWithAnnotation(null, Annotated.class);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsListWithAnnotation(null, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(null, "none");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, "");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException4_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, " ");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(null, "none", true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, null, true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, "", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException4_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, " ", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadStaticFieldIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.readStaticField(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadStaticFieldIllegalArgumentException2_2_oe() throws Exception {
        // removed other assertion
        final Field nonStaticField = FieldUtils.getField(PublicChild.class, "s");
        assumeTrue(nonStaticField != null);
        try {
    FieldUtils.readStaticField(nonStaticField);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadStaticFieldForceAccessIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.readStaticField(null, true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadStaticFieldForceAccessIllegalArgumentException2_1_oe() throws Exception {
        final Field nonStaticField = FieldUtils.getField(PublicChild.class, "s", true);
        assumeTrue(nonStaticField != null);
        try {
    FieldUtils.readStaticField(nonStaticField);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticField_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    FieldUtils.readStaticField(null, "none");
    org.junit.jupiter.api.Assertions.fail("NullPointerException: null class should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedStaticField_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readStaticField(Foo.class, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticField_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readStaticField(Foo.class, "");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticField_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readStaticField(Foo.class, " ");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticField_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readStaticField(Foo.class, "does_not_exist");
    org.junit.jupiter.api.Assertions.fail("NullPointerException: a field that doesn't exist should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedStaticField_10_oe() throws Exception {
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
    FieldUtils.readStaticField(PublicChild.class, "s");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: non-static field should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    FieldUtils.readStaticField(null, "none", true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: null class should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readStaticField(Foo.class, null, true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readStaticField(Foo.class, "", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readStaticField(Foo.class, " ", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readStaticField(Foo.class, "does_not_exist", true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: a field that doesn't exist should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_10_oe() throws Exception {
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
    FieldUtils.readStaticField(PublicChild.class, "s", false);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: non-static field should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticField_2_oe() throws Exception {
        // removed other assertion
        try {
    FieldUtils.readDeclaredStaticField(PublicChild.class, "VALUE");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticField_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readDeclaredStaticField(PubliclyShadowedChild.class, "VALUE");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticField_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readDeclaredStaticField(PrivatelyShadowedChild.class, "VALUE");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticFieldForceAccess_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readDeclaredStaticField(PubliclyShadowedChild.class, "VALUE", true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticFieldForceAccess_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readDeclaredStaticField(PrivatelyShadowedChild.class, "VALUE", true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadField_13_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    FieldUtils.readField(null, publicChild);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: a null field should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadFieldForceAccess_13_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    FieldUtils.readField(null, publicChild, true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: a null field should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedField_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    FieldUtils.readField(publicChild, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: a null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readField(publicChild, "");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: an empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readField(publicChild, " ");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: a blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readField((Object) null, "none");
    org.junit.jupiter.api.Assertions.fail("NullPointerException: a null target should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedField_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readField(publicChild, "b");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_10_oe() throws Exception {
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
    FieldUtils.readField(privatelyShadowedChild, "b");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_11_oe() throws Exception {
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
    FieldUtils.readField(publicChild, "i");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_13_oe() throws Exception {
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
    FieldUtils.readField(privatelyShadowedChild, "i");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_14_oe() throws Exception {
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
    FieldUtils.readField(publicChild, "d");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_16_oe() throws Exception {
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
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readField(privatelyShadowedChild, "d");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedFieldForceAccess_13_oe() throws Exception {
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
    FieldUtils.readField(publicChild, null, true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: a null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedFieldForceAccess_14_oe() throws Exception {
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
    FieldUtils.readField(publicChild, "", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: an empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedFieldForceAccess_15_oe() throws Exception {
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

        // removed other assertion

        try {
    FieldUtils.readField(publicChild, " ", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: a blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedFieldForceAccess_16_oe() throws Exception {
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

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readField((Object) null, "none", true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: a null target should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_1_oe() throws Exception {
        try {
    FieldUtils.readDeclaredField(publicChild, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: a null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_2_oe() throws Exception {
        // removed other assertion

        try {
    FieldUtils.readDeclaredField(publicChild, "");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: an empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readDeclaredField(publicChild, " ");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: a blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_4_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readDeclaredField(null, "none");
    org.junit.jupiter.api.Assertions.fail("NullPointerException: a null target should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_5_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readDeclaredField(publicChild, "s");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_7_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readDeclaredField(privatelyShadowedChild, "s");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_8_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readDeclaredField(publicChild, "b");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_10_oe() throws Exception {
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
    FieldUtils.readDeclaredField(privatelyShadowedChild, "b");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_11_oe() throws Exception {
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
    FieldUtils.readDeclaredField(publicChild, "i");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_13_oe() throws Exception {
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
    FieldUtils.readDeclaredField(privatelyShadowedChild, "i");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_14_oe() throws Exception {
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
    FieldUtils.readDeclaredField(publicChild, "d");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_16_oe() throws Exception {
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
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readDeclaredField(privatelyShadowedChild, "d");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_1_oe() throws Exception {
        try {
    FieldUtils.readDeclaredField(publicChild, null, true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: a null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_2_oe() throws Exception {
        // removed other assertion

        try {
    FieldUtils.readDeclaredField(publicChild, "", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: an empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readDeclaredField(publicChild, " ", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: a blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_4_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readDeclaredField(null, "none", true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: a null target should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_5_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    FieldUtils.readDeclaredField(publicChild, "s", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_8_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.readDeclaredField(publicChild, "b", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_11_oe() throws Exception {
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
    FieldUtils.readDeclaredField(publicChild, "i", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_14_oe() throws Exception {
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
    FieldUtils.readDeclaredField(publicChild, "d", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteStaticField_2_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutableProtected"), "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutablePackage"), "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutablePrivate"), "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_5_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PUBLIC"), "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_6_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PROTECTED"), "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_7_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE"), "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_8_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE"), "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticFieldForceAccess_5_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PUBLIC"), "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticFieldForceAccess_6_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PROTECTED"), "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticFieldForceAccess_7_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE"), "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticFieldForceAccess_8_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE"), "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_2_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_3_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_4_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PUBLIC", "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PROTECTED", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PACKAGE", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PRIVATE", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PUBLIC", "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PROTECTED", "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PACKAGE", "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PRIVATE", "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_2_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_3_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_4_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PUBLIC", "new");
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PROTECTED", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PACKAGE", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PRIVATE", "new");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_5_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PUBLIC", "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_6_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PROTECTED", "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_7_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PACKAGE", "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_8_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PRIVATE", "new", true);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteField_2_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        // removed other assertion
        try {
    FieldUtils.writeField(parentClass.getDeclaredField("b"), publicChild, Boolean.TRUE);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteField_3_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeField(parentClass.getDeclaredField("i"), publicChild, Integer.valueOf(Integer.MAX_VALUE));
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteField_4_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeField(parentClass.getDeclaredField("d"), publicChild, Double.valueOf(Double.MAX_VALUE));
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedField_2_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        try {
    FieldUtils.writeField(publicChild, "b", Boolean.TRUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_3_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeField(publicChild, "i", Integer.valueOf(1));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_4_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_10_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        // removed other assertion
        try {
    FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.TRUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_11_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(1));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_12_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeField(privatelyShadowedChild, "d", Double.valueOf(1.0));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_1_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "s", "S");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_2_oe() throws Exception {
        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(publicChild, "b", Boolean.TRUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(publicChild, "i", Integer.valueOf(1));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(publicChild, "d", Double.valueOf(1.0));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        try {
    FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.TRUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_11_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(1));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_12_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(privatelyShadowedChild, "d", Double.valueOf(1.0));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_1_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "s", "S", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_2_oe() throws Exception {
        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(publicChild, "b", Boolean.TRUE, true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(publicChild, "i", Integer.valueOf(1), true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    FieldUtils.writeDeclaredField(publicChild, "d", Double.valueOf(1.0), true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAmbig_1_oe() throws Exception {
        try {
    FieldUtils.getField(Ambig.class, "VALUE");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
