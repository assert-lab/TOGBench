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
package org.apache.commons.geometry.euclidean.threed.rotation;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AxisAngleSequenceTest_OE25Dev {

    @Test
    void testConstructor_1_oe() {
        // act
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.RELATIVE, AxisSequence.XYZ, 1, 2, 3);

        // assert
        Assertions.assertEquals(AxisReferenceFrame.RELATIVE, seq.getReferenceFrame());
    }

    @Test
    void testConstructor_2_oe() {
        // act
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.RELATIVE, AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        Assertions.assertEquals(AxisSequence.XYZ, seq.getAxisSequence());
    }

    @Test
    void testConstructor_3_oe() {
        // act
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.RELATIVE, AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, seq.getAngle1(), 0.0);
    }

    @Test
    void testConstructor_4_oe() {
        // act
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.RELATIVE, AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, seq.getAngle2(), 0.0);
    }

    @Test
    void testConstructor_5_oe() {
        // act
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.RELATIVE, AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(3, seq.getAngle3(), 0.0);
    }

    @Test
    void testGetAngles_1_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.RELATIVE, AxisSequence.XYZ, 1, 2, 3);

        // act
        final double[] angles = seq.getAngles();

        // assert
        Assertions.assertArrayEquals(new double[] {1, 2, 3}, angles, 0.0);
    }

    @Test
    void testHashCode_1_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        Assertions.assertNotEquals(seq.hashCode(), new AxisAngleSequence(AxisReferenceFrame.RELATIVE, AxisSequence.XYZ, 1, 2, 3).hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion
        Assertions.assertNotEquals(seq.hashCode(), new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.ZYX, 1, 2, 3).hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(seq.hashCode(), new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 9, 2, 3).hashCode());
    }

    @Test
    void testHashCode_4_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(seq.hashCode(), new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 9, 3).hashCode());
    }

    @Test
    void testHashCode_5_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(seq.hashCode(), new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 9).hashCode());
    }

    @Test
    void testHashCode_6_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(seq.hashCode(), new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3).hashCode());
    }

    @Test
    void testEquals_2_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion

        Assertions.assertNotEquals(seq, new AxisAngleSequence(AxisReferenceFrame.RELATIVE, AxisSequence.XYZ, 1, 2, 3));
    }

    @Test
    void testEquals_3_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(seq, new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.ZYX, 1, 2, 3));
    }

    @Test
    void testEquals_4_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(seq, new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 9, 2, 3));
    }

    @Test
    void testEquals_5_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(seq, new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 9, 3));
    }

    @Test
    void testEquals_6_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(seq, new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 9));
    }

    @Test
    void testEquals_7_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(seq, new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3));
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_1_oe() {
        // arrange
        final AxisAngleSequence a = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence b = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);
        final AxisAngleSequence c = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence d = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);

        // act/assert
        Assertions.assertFalse(a.equals(b));
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_2_oe() {
        // arrange
        final AxisAngleSequence a = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence b = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);
        final AxisAngleSequence c = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence d = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);

        // act/assert
        // removed other assertion
        Assertions.assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_3_oe() {
        // arrange
        final AxisAngleSequence a = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence b = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);
        final AxisAngleSequence c = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence d = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(a.equals(c));
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_4_oe() {
        // arrange
        final AxisAngleSequence a = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence b = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);
        final AxisAngleSequence c = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence d = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(a.hashCode(), c.hashCode());
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_5_oe() {
        // arrange
        final AxisAngleSequence a = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence b = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);
        final AxisAngleSequence c = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence d = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(b.equals(d));
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_6_oe() {
        // arrange
        final AxisAngleSequence a = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence b = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);
        final AxisAngleSequence c = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                0.0, -0.0, 0.0);
        final AxisAngleSequence d = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ,
                -0.0, 0.0, -0.0);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(b.hashCode(), d.hashCode());
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act
        final String str = seq.toString();

        // assert
        Assertions.assertTrue(str.contains("ABSOLUTE"));
    }

    @Test
    void testToString_2_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act
        final String str = seq.toString();

        // assert
        // removed other assertion
        Assertions.assertTrue(str.contains("XYZ"));
    }

    @Test
    void testToString_3_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act
        final String str = seq.toString();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(str.contains("1"));
    }

    @Test
    void testToString_4_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act
        final String str = seq.toString();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(str.contains("2"));
    }

    @Test
    void testToString_5_oe() {
        // arrange
        final AxisAngleSequence seq = new AxisAngleSequence(AxisReferenceFrame.ABSOLUTE, AxisSequence.XYZ, 1, 2, 3);

        // act
        final String str = seq.toString();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(str.contains("3"));
    }

    @Test
    void testCreateRelative_1_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createRelative(AxisSequence.XYZ, 1, 2, 3);

        // assert
        Assertions.assertEquals(AxisReferenceFrame.RELATIVE, seq.getReferenceFrame());
    }

    @Test
    void testCreateRelative_2_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createRelative(AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        Assertions.assertEquals(AxisSequence.XYZ, seq.getAxisSequence());
    }

    @Test
    void testCreateRelative_3_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createRelative(AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, seq.getAngle1(), 0.0);
    }

    @Test
    void testCreateRelative_4_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createRelative(AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, seq.getAngle2(), 0.0);
    }

    @Test
    void testCreateRelative_5_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createRelative(AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(3, seq.getAngle3(), 0.0);
    }

    @Test
    void testCreateAbsolute_1_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createAbsolute(AxisSequence.XYZ, 1, 2, 3);

        // assert
        Assertions.assertEquals(AxisReferenceFrame.ABSOLUTE, seq.getReferenceFrame());
    }

    @Test
    void testCreateAbsolute_2_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createAbsolute(AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        Assertions.assertEquals(AxisSequence.XYZ, seq.getAxisSequence());
    }

    @Test
    void testCreateAbsolute_3_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createAbsolute(AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, seq.getAngle1(), 0.0);
    }

    @Test
    void testCreateAbsolute_4_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createAbsolute(AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, seq.getAngle2(), 0.0);
    }

    @Test
    void testCreateAbsolute_5_oe() {
        // act
        final AxisAngleSequence seq = AxisAngleSequence.createAbsolute(AxisSequence.XYZ, 1, 2, 3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(3, seq.getAngle3(), 0.0);
    }

}
