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
package org.apache.commons.imaging.formats.tiff.fieldtypes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.nio.ByteOrder;

import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for class {@link FieldTypeAscii}.
 * @see FieldTypeAscii
 *
 **/
public class FieldTypeAsciiTest_OE25Dev {

  @Test
  public void testCreatesFieldTypeAsciiAndCallsWriteData_1_oe() throws Exception {
      final FieldTypeAscii fieldTypeAscii = new FieldTypeAscii(0, "1");
      final byte[] byteArray = new byte[1];
      final ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
      final TiffField tiffField = new TiffField(0, 0, fieldTypeAscii, 0L, 0, byteArray, byteOrder, 1);

      try {
    fieldTypeAscii.writeData(tiffField, byteOrder);
    fail("ImageWriteException");
} catch (ImageWriteException e) {
}
  }

  @Test
  public void testCreatesFieldTypeAsciiAndWriteDataUsingByteArray_1_oe() throws ImageWriteException {
      final FieldTypeAscii fieldTypeAscii = new FieldTypeAscii(0, "1");
      final byte[] byteArray = new byte[1];
      final ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
      final byte[] byteArrayTwo = fieldTypeAscii.writeData(byteArray, byteOrder);

      assertArrayEquals(new byte[] {(byte)0, (byte)0}, byteArrayTwo);
  }

    @Test
    public void testCreatesFieldTypeAsciiAndWriteDataUsingString_1_oe() throws ImageWriteException {
        final FieldTypeAscii fieldTypeAscii = new FieldTypeAscii(0, "1");
        final ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        final byte[] byteArrayTwo = fieldTypeAscii.writeData("asdf", byteOrder);

        assertArrayEquals(new byte[] {(byte)97, (byte)115, (byte)100, (byte)102, (byte)0}, byteArrayTwo);
    }

}
