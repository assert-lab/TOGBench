/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.configuration2.convert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.lang.annotation.ElementType;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.apache.commons.configuration2.ex.ConversionException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test class for PropertyConverter.
 *
 */
public class TestPropertyConverter_OE25Dev {
    /** Constant for an enumeration class used by some tests. */
    private static final Class<ElementType> ENUM_CLASS = ElementType.class;

    /**
     * See CONFIGURATION-766.
     */

    /**
     * See CONFIGURATION-766.
     */

    /**
     * Tests a failed conversion to character.
     */
    @Test(expected = ConversionException.class)
    public void testToCharFailed() {
        PropertyConverter.to(Character.TYPE, "FF", new DefaultConversionHandler());
    }

    /**
     * Tests whether a conversion to character is possible.
     */

    /**
     * Tests whether other objects implementing a toString() method can be converted to character.
     */

    @Test(expected = ConversionException.class)
    public void testToEnumFromInvalidNumber() {
        PropertyConverter.toEnum(Integer.valueOf(-1), ENUM_CLASS);
    }

    @Test(expected = ConversionException.class)
    public void testToEnumFromInvalidString() {
        PropertyConverter.toEnum("FOO", ENUM_CLASS);
    }

    /**
     * Tests conversion to files when the passed in objects are already files.
     */

    /**
     * Tests conversion to file when the passed in objects are paths.
     */

    /**
     * Tests conversion to file when the passed in objects have a compatible string representation.
     */

    /**
     * Tests a trivial conversion: the value has already the desired type.
     */

    /**
     * Tests conversion to numbers when the passed in objects are already numbers.
     */

    /**
     * Tests conversion to numbers when the passed in objects are strings with prefixes for special radices.
     */

    /**
     * Tests conversion to numbers when the passed in objects are strings with prefixes for special radices.
     */

    /**
     * Tests conversion to numbers when an invalid binary value is passed in. This should cause an exception.
     */
    @Test(expected = ConversionException.class)
    public void testToNumberFromInvalidBinaryString() {
        PropertyConverter.toNumber("0bNotABinValue", Integer.class);
    }

    /**
     * Tests conversion to numbers when an invalid Hex value is passed in. This should cause an exception.
     */
    @Test(expected = ConversionException.class)
    public void testToNumberFromInvalidHexString() {
        PropertyConverter.toNumber("0xNotAHexValue", Integer.class);
    }

    /**
     * Tests conversion to numbers when the passed in objects have no numeric String representation. This should cause an
     * exception.
     */
    @Test(expected = ConversionException.class)
    public void testToNumberFromInvalidString() {
        PropertyConverter.toNumber("Not a number", Byte.class);
    }

    /**
     * Tests conversion to numbers when the passed in objects have a compatible string representation.
     */

    /**
     * Tests conversion to numbers when the passed in target class is invalid. This should cause an exception.
     */
    @Test(expected = ConversionException.class)
    public void testToNumberWithInvalidClass() {
        PropertyConverter.toNumber("42", Object.class);
    }

    /**
     * Tests conversion to paths when the passed in objects are already paths.
     */

    /**
     * Tests conversion to path when the passed in objects are files.
     */

    /**
     * Tests conversion to file when the passed in objects have a compatible string representation.
     */

    /**
     * Tests conversion to patterns when the passed in objects are already patterns.
     */

    /**
     * Tests conversion to patterns when the passed in objects have a compatible string representation.
     */

    /**
     * Tests a conversion to a string.
     */

    @Test
    public void testToBigDecimalDoubleConstructor_1_oe() {
        // If the conversion uses new BigDecimal(0.1) the result is not exact due to round off.
        // The result is 0.1000000000000000055511151231257827021181583404541015625.
        // See Sonar rule: https://rules.sonarsource.com/java/type/Bug/RSPEC-2111
        final double d = 0.1;
        assertEquals("Incorrect BigDecimal value", new BigDecimal(d), PropertyConverter.toBigDecimal(d));
    }

    @Test
    @Ignore
    public void testToBigDecimalStringConstructor_1_oe() {
        // If the conversion uses new BigDecimal(0.1) the result is not exact due to round off.
        // The result is 0.1000000000000000055511151231257827021181583404541015625.
        // See Sonar rule: https://rules.sonarsource.com/java/type/Bug/RSPEC-2111
        final double d = 0.1;
        assertEquals("Incorrect BigDecimal value", new BigDecimal(Double.toString(d)), PropertyConverter.toBigDecimal(d));
    }

    @Test
    public void testToCharSuccess_1_oe() {
        assertEquals("Wrong conversion result", Character.valueOf('t'), PropertyConverter.to(Character.class, "t", new DefaultConversionHandler()));
    }

    @Test
    public void testToCharViaToString_1_oe() {
        final Object value = new Object() {
            @Override
            public String toString() {
                return "X";
            }
        };
        assertEquals("Wrong conversion result", Character.valueOf('X'), PropertyConverter.to(Character.TYPE, value, new DefaultConversionHandler()));
    }

    @Test
    public void testToEnumFromEnum_1_oe() {
        assertEquals(ElementType.METHOD, PropertyConverter.toEnum(ElementType.METHOD, ENUM_CLASS));
    }

    @Test
    public void testToEnumFromNumber_1_oe() {
        assertEquals(ElementType.METHOD, PropertyConverter.toEnum(Integer.valueOf(ElementType.METHOD.ordinal()), ENUM_CLASS));
    }

    @Test
    public void testToEnumFromString_1_oe() {
        assertEquals(ElementType.METHOD, PropertyConverter.toEnum("METHOD", ENUM_CLASS));
    }

    @Test
    public void testToFileDirect_1_oe() {
        final File f = new File("dir", "file");
        assertSame("Wrong file", f, PropertyConverter.toFile(f));
    }

    @Test
    public void testToFileFromPath_1_oe() {
        final Path p = Paths.get("dir", "file");
        assertEquals("Wrong conversion result", new File("dir", "file"), PropertyConverter.toFile(p));
    }

    @Test
    public void testToFileFromString_1_oe() {
        assertEquals("Wrong conversion result", new File("dir", "file"), PropertyConverter.toFile("dir/file"));
    }

    @Test
    public void testToNoConversionNeeded_1_oe() {
        final String value = "testValue";
        assertEquals("Wrong conversion result", value, PropertyConverter.to(String.class, value, new DefaultConversionHandler()));
    }

    @Test
    public void testToNumberDirect_1_oe() {
        final Integer i = Integer.valueOf(42);
        assertSame("Wrong integer", i, PropertyConverter.toNumber(i, Integer.class));
    }

    @Test
    public void testToNumberDirect_2_oe() {
        final Integer i = Integer.valueOf(42);
        // removed other assertion
        final BigDecimal d = new BigDecimal("3.1415");
        assertSame("Wrong BigDecimal", d, PropertyConverter.toNumber(d, Integer.class));
    }

    @Test
    public void testToNumberFromBinaryString_1_oe() {
        final Number n = PropertyConverter.toNumber("0b1111", Integer.class);
        assertEquals("Incorrect Integer value", 15, n.intValue());
    }

    @Test
    public void testToNumberFromHexString_1_oe() {
        final Number n = PropertyConverter.toNumber("0x10", Integer.class);
        assertEquals("Incorrect Integer value", 16, n.intValue());
    }

    @Test
    public void testToNumberFromString_1_oe() {
        assertEquals("Incorrect Integer value", Integer.valueOf(42), PropertyConverter.toNumber("42", Integer.class));
    }

    @Test
    public void testToNumberFromString_2_oe() {
        // removed other assertion
        assertEquals("Incorrect Short value", Short.valueOf((short) 10), PropertyConverter.toNumber(new StringBuffer("10"), Short.class));
    }

    @Test
    public void testToPathDirect_1_oe() {
        final Path p = Paths.get("dir", "file");
        assertSame("Wrong path", p, PropertyConverter.toPath(p));
    }

    @Test
    public void testToPathFromFile_1_oe() {
        final File f = new File("dir", "file");
        assertEquals("Wrong conversion result", Paths.get("dir", "file"), PropertyConverter.toPath(f));
    }

    @Test
    public void testToPathFromString_1_oe() {
        assertEquals("Wrong conversion result", Paths.get("dir", "file"), PropertyConverter.toPath("dir/file"));
    }

    @Test
    public void testToPatternDirect_1_oe() {
        final Pattern p = Pattern.compile(".+");
        assertSame("Wrong pattern", p, PropertyConverter.toPattern(p));
    }

    @Test
    public void testToPatternFromString_1_oe() {
        final Pattern p = Pattern.compile(".+");
        assertEquals("Wrong conversion result", p.pattern(), PropertyConverter.toPattern(".+").pattern());
    }

    @Test
    public void testToStringConversion_1_oe() {
        final Integer src = 42;
        final Object result = PropertyConverter.to(String.class, src, new DefaultConversionHandler());
        assertEquals("Wrong resulting string", "42", result);
    }

}
