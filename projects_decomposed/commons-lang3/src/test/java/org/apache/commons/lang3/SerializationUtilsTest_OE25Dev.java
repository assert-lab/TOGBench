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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.SerializationUtils}.
 */
public class SerializationUtilsTest_OE25Dev {

  static final String CLASS_NOT_FOUND_MESSAGE = "ClassNotFoundSerialization.readObject fake exception";
    protected static final String SERIALIZE_IO_EXCEPTION_MESSAGE = "Anonymous OutputStream I/O exception";

    private String iString;
    private Integer iInteger;
    private HashMap<Object, Object> iMap;

    @BeforeEach
    public void setUp() {
        iString = "foo";
        iInteger = Integer.valueOf(7);
        iMap = new HashMap<>();
        iMap.put("FOO", iString);
        iMap.put("BAR", iInteger);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Test
    public void testSerializeStreamUnserializable_1_oe() throws Exception {
        final ByteArrayOutputStream streamTest = new ByteArrayOutputStream();
        iMap.put(new Object(), new Object());
        try {
    SerializationUtils.serialize(iMap, streamTest);
    org.junit.jupiter.api.Assertions.fail("SerializationException");
} catch (SerializationException e) {
}
    }

    @Test
    public void testSerializeStreamObjNull_1_oe() throws Exception {
        try {
    SerializationUtils.serialize(iMap, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSerializeStreamNullNull_1_oe() throws Exception {
        try {
    SerializationUtils.serialize(null, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSerializeIOException_1_oe() {
        // forces an IOException when the ObjectOutputStream is created, to test not closing the stream
        // in the finally block
        final OutputStream streamTest = new OutputStream() {
            @Override
            public void write(final int arg0) throws IOException {
                throw new IOException(SERIALIZE_IO_EXCEPTION_MESSAGE);
            }
        };
        final SerializationException e =
                assertThrows(SerializationException.class, () -> SerializationUtils.serialize(iMap, streamTest));
    }

    @Test
    public void testDeserializeStreamNull_1_oe() throws Exception {
        try {
    SerializationUtils.deserialize((InputStream) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testDeserializeStreamBadStream_1_oe() throws Exception {
        try {
    SerializationUtils.deserialize(new ByteArrayInputStream(new byte[0]));
    org.junit.jupiter.api.Assertions.fail("SerializationException");
} catch (SerializationException e) {
}
    }

    @Test
    public void testDeserializeStreamClassNotFound_1_oe() throws Exception {
        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(new ClassNotFoundSerialization());
        oos.flush();
        oos.close();

        final ByteArrayInputStream inTest = new ByteArrayInputStream(streamReal.toByteArray());
        final SerializationException se =
                assertThrows(SerializationException.class, () -> SerializationUtils.deserialize(inTest));
    }

    @Test
    public void testSerializeBytesUnserializable_1_oe() throws Exception {
        iMap.put(new Object(), new Object());
        try {
    SerializationUtils.serialize(iMap);
    org.junit.jupiter.api.Assertions.fail("SerializationException");
} catch (SerializationException e) {
}
    }

    @Test
    public void testDeserializeBytesNull_1_oe() throws Exception {
        try {
    SerializationUtils.deserialize((byte[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testDeserializeBytesBadStream_1_oe() throws Exception {
        try {
    SerializationUtils.deserialize(new byte[0]);
    org.junit.jupiter.api.Assertions.fail("SerializationException");
} catch (SerializationException e) {
}
    }

    @Test
    public void testCloneUnserializable_1_oe() throws Exception {
        iMap.put(new Object(), new Object());
        try {
    SerializationUtils.clone(iMap);
    org.junit.jupiter.api.Assertions.fail("SerializationException");
} catch (SerializationException e) {
}
    }

}

