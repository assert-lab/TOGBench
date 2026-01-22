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
package org.apache.commons.collections4.splitmap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.util.HashMap;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.NOPTransformer;

/**
 * Tests for {@link TransformedSplitMap}
 *
 * @since 4.0
 */
@SuppressWarnings("boxing")
public class TransformedSplitMapTest_OE25Dev extends BulkTest {

    private final Transformer<Integer, String> intToString = new Transformer<Integer, String>() {
        @Override
        public String transform(final Integer input) {
            return String.valueOf(input);
        }
    };

    private final Transformer<Object, Class<?>> objectToClass = new Transformer<Object, Class<?>>() {
        @Override
        public java.lang.Class<?> transform(final Object input) {
            return input == null ? null : input.getClass();
        }
    };

    private final Transformer<String, Integer> stringToInt = new Transformer<String, Integer>() {
        @Override
        public Integer transform(final String input) {
            return Integer.valueOf(input);
        }
    };

    public TransformedSplitMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

//    public void testCreate() throws IOException {
//        TransformedSplitMap<String, String, String, String> map = TransformedSplitMap.transformingMap(
//                new HashMap<String, String>(),
//                NOPTransformer.<String>nopTransformer(),
//                NOPTransformer.<String>nopTransformer() );
//
//        ObjectOutputStream out = new ObjectOutputStream(
//                new FileOutputStream( "src/test/resources/data/test/TransformedSplitMap.emptyCollection.version4.obj" ) );
//        out.writeObject( map );
//
//        map.put( "a", "b" );
//        map.put( "c", "d" );
//        map.put( "e", "f" );
//        map.put( "g", "h" );
//
//        out = new ObjectOutputStream(
//                new FileOutputStream( "src/test/resources/data/test/TransformedSplitMap.fullCollection.version4.obj" ) );
//        out.writeObject( map );
//    }

    public void testTransformedMap_1_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        assertEquals(0, map.size());
    }

    public void testTransformedMap_2_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            assertEquals(i + 1, map.size());
    }
    }

    public void testTransformedMap_3_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            assertTrue(map.containsKey(intToString.transform(k[i])));
    }
    }

    public void testTransformedMap_4_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            assertFalse(map.containsKey(k[i]));
    }
    }

    public void testTransformedMap_5_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertTrue(map.containsValue(objectToClass.transform(v[i])));
    }
    }

    public void testTransformedMap_6_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertTrue(objectToClass.transform(v[i]) != v[i] ^ map.containsValue(v[i]));
    }
    }

    public void testTransformedMap_7_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(objectToClass.transform(v[i]), map.get(intToString.transform(k[i])));
    }
    }

    public void testTransformedMap_8_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        assertEquals(null, map.remove(k[0]));
    }

    public void testTransformedMap_9_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        assertEquals(sz, map.size());
    }

    public void testTransformedMap_10_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        // removed other assertion
        assertEquals(objectToClass.transform(v[0]), map.remove(intToString.transform(k[0])));
    }

    public void testTransformedMap_12_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TransformedSplitMap<String, String, String, Integer> map2 = TransformedSplitMap.transformingMap(
                new HashMap<String, Integer>(), NOPTransformer.<String> nopTransformer(), stringToInt);
        assertEquals(0, map2.size());
    }

    public void testTransformedMap_13_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TransformedSplitMap<String, String, String, Integer> map2 = TransformedSplitMap.transformingMap(
                new HashMap<String, Integer>(), NOPTransformer.<String> nopTransformer(), stringToInt);
        // removed other assertion
        for (int i = 0; i < 6; i++) {
            map2.put(String.valueOf(i), String.valueOf(i));
            assertEquals(i + 1, map2.size());
    }
    }

    public void testTransformedMap_14_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TransformedSplitMap<String, String, String, Integer> map2 = TransformedSplitMap.transformingMap(
                new HashMap<String, Integer>(), NOPTransformer.<String> nopTransformer(), stringToInt);
        // removed other assertion
        for (int i = 0; i < 6; i++) {
            map2.put(String.valueOf(i), String.valueOf(i));
            // removed other assertion
            assertTrue(map2.containsValue(i));
    }
    }

    public void testTransformedMap_15_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TransformedSplitMap<String, String, String, Integer> map2 = TransformedSplitMap.transformingMap(
                new HashMap<String, Integer>(), NOPTransformer.<String> nopTransformer(), stringToInt);
        // removed other assertion
        for (int i = 0; i < 6; i++) {
            map2.put(String.valueOf(i), String.valueOf(i));
            // removed other assertion
            // removed other assertion
            assertFalse(map2.containsValue(String.valueOf(i)));
    }
    }

    public void testTransformedMap_16_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TransformedSplitMap<String, String, String, Integer> map2 = TransformedSplitMap.transformingMap(
                new HashMap<String, Integer>(), NOPTransformer.<String> nopTransformer(), stringToInt);
        // removed other assertion
        for (int i = 0; i < 6; i++) {
            map2.put(String.valueOf(i), String.valueOf(i));
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertTrue(map2.containsKey(String.valueOf(i)));
    }
    }

    public void testTransformedMap_17_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TransformedSplitMap<String, String, String, Integer> map2 = TransformedSplitMap.transformingMap(
                new HashMap<String, Integer>(), NOPTransformer.<String> nopTransformer(), stringToInt);
        // removed other assertion
        for (int i = 0; i < 6; i++) {
            map2.put(String.valueOf(i), String.valueOf(i));
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(i, map2.get(String.valueOf(i)).intValue());
    }
    }

    public void testTransformedMap_18_oe() {
        final TransformedSplitMap<Integer, String, Object, Class<?>> map = TransformedSplitMap.transformingMap(
                new HashMap<String, Class<?>>(), intToString, objectToClass);

        final Integer[] k = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        final Object[] v = new Object[] { "", new Object(), new HashMap<>(), 0, BigInteger.TEN, null,
                new Object[0] };

        // removed other assertion
        for (int i = 0; i < k.length; i++) {
            map.put(k[i], v[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz = map.size();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TransformedSplitMap<String, String, String, Integer> map2 = TransformedSplitMap.transformingMap(
                new HashMap<String, Integer>(), NOPTransformer.<String> nopTransformer(), stringToInt);
        // removed other assertion
        for (int i = 0; i < 6; i++) {
            map2.put(String.valueOf(i), String.valueOf(i));
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        int sz2 = map2.size();
        assertEquals(Integer.valueOf(0), map2.remove("0"));
    }

    public void testMapIterator_1_oe() {
        final TransformedSplitMap<String, String, String, Integer> map =
                TransformedSplitMap.transformingMap(new HashMap<String, Integer>(),
                                                    NOPTransformer.<String> nopTransformer(), stringToInt);
        assertEquals(0, map.size());
    }

    public void testMapIterator_2_oe() {
        final TransformedSplitMap<String, String, String, Integer> map =
                TransformedSplitMap.transformingMap(new HashMap<String, Integer>(),
                                                    NOPTransformer.<String> nopTransformer(), stringToInt);
        // removed other assertion
        for (int i = 0; i < 6; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }

        for (final MapIterator<String, Integer> it = map.mapIterator(); it.hasNext();) {
            final String k = it.next();
            assertEquals(k, it.getKey());
    }
    }

    public void testMapIterator_3_oe() {
        final TransformedSplitMap<String, String, String, Integer> map =
                TransformedSplitMap.transformingMap(new HashMap<String, Integer>(),
                                                    NOPTransformer.<String> nopTransformer(), stringToInt);
        // removed other assertion
        for (int i = 0; i < 6; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }

        for (final MapIterator<String, Integer> it = map.mapIterator(); it.hasNext();) {
            final String k = it.next();
            // removed other assertion
            assertEquals(map.get(k), it.getValue());
    }
    }

    public void testEmptyMap_1_oe() throws IOException, ClassNotFoundException {
        final TransformedSplitMap<String, String, String, String> map =
                TransformedSplitMap.transformingMap(new HashMap<String, String>(),
                                                    NOPTransformer.<String>nopTransformer(),
                                                    NOPTransformer.<String>nopTransformer() );

        final ObjectInputStream in =
                new ObjectInputStream( new FileInputStream( TEST_DATA_PATH+"/TransformedSplitMap.emptyCollection.version4.obj" ) );
        final Object readObject = in.readObject();
        in.close();

        final TransformedSplitMap<?, ?, ?, ?> readMap = (TransformedSplitMap<?, ?, ?, ?>) readObject;
        assertTrue( "Map should be empty", readMap.size() == 0 );
    }

    public void testEmptyMap_2_oe() throws IOException, ClassNotFoundException {
        final TransformedSplitMap<String, String, String, String> map =
                TransformedSplitMap.transformingMap(new HashMap<String, String>(),
                                                    NOPTransformer.<String>nopTransformer(),
                                                    NOPTransformer.<String>nopTransformer() );

        final ObjectInputStream in =
                new ObjectInputStream( new FileInputStream( TEST_DATA_PATH+"/TransformedSplitMap.emptyCollection.version4.obj" ) );
        final Object readObject = in.readObject();
        in.close();

        final TransformedSplitMap<?, ?, ?, ?> readMap = (TransformedSplitMap<?, ?, ?, ?>) readObject;
        // removed other assertion
        assertEquals( map.entrySet(), readMap.entrySet() );
    }

    public void testFullMap_1_oe() throws IOException, ClassNotFoundException {
        final TransformedSplitMap<String, String, String, String> map = TransformedSplitMap.transformingMap(
                new HashMap<String, String>(),
                NOPTransformer.<String>nopTransformer(),
                NOPTransformer.<String>nopTransformer() );
        map.put( "a", "b" );
        map.put( "c", "d" );
        map.put( "e", "f" );
        map.put( "g", "h" );

        final ObjectInputStream in =
                new ObjectInputStream( new FileInputStream( TEST_DATA_PATH+"TransformedSplitMap.fullCollection.version4.obj" ) );
        final Object readObject = in.readObject();
        in.close();

        final TransformedSplitMap<?, ?, ?, ?> readMap = (TransformedSplitMap<?, ?, ?, ?>) readObject;
        assertFalse( "Map should not be empty", readMap.size() == 0 );
    }

    public void testFullMap_2_oe() throws IOException, ClassNotFoundException {
        final TransformedSplitMap<String, String, String, String> map = TransformedSplitMap.transformingMap(
                new HashMap<String, String>(),
                NOPTransformer.<String>nopTransformer(),
                NOPTransformer.<String>nopTransformer() );
        map.put( "a", "b" );
        map.put( "c", "d" );
        map.put( "e", "f" );
        map.put( "g", "h" );

        final ObjectInputStream in =
                new ObjectInputStream( new FileInputStream( TEST_DATA_PATH+"TransformedSplitMap.fullCollection.version4.obj" ) );
        final Object readObject = in.readObject();
        in.close();

        final TransformedSplitMap<?, ?, ?, ?> readMap = (TransformedSplitMap<?, ?, ?, ?>) readObject;
        // removed other assertion
        assertEquals( map.entrySet(), readMap.entrySet() );
    }

}
