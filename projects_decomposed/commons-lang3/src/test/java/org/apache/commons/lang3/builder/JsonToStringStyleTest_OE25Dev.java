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
package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringStyleTest.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.JsonToStringStyleTest_OE25Dev}.
 */
public class JsonToStringStyleTest_OE25Dev {

    private final Integer base = Integer.valueOf(5);

    @BeforeEach
    public void setUp() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);
    }

    @AfterEach
    public void tearDown() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.DEFAULT_STYLE);
    }

    // ----------------------------------------------------------------

    /**
     * An object with nested object structures used to test {@code ToStringStyle.JsonToStringStyle}.
     */
    static class NestingPerson {
        /**
         * Test String field.
         */
        String pid;

        /**
         * Test nested object field.
         */
        Person person;
    }

    enum Hobby {
        SPORT,
        BOOK,
        MUSIC
    }

    enum EmptyEnum {
    }

    static class Student {
        List<Hobby> hobbies;

        public List<Hobby> getHobbies() {
            return hobbies;
        }

        public void setHobbies(final List<Hobby> hobbies) {
            this.hobbies = hobbies;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    static class Teacher {
        Hobby[] hobbies;

        public Hobby[] getHobbies() {
            return hobbies;
        }

        public void setHobbies(final Hobby[] hobbies) {
            this.hobbies = hobbies;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    static class AcademyClass {
        Teacher teacher;
        List<Student> students;

        public void setTeacher(final Teacher teacher) {
            this.teacher = teacher;
        }

        public void setStudents(final List<Student> students) {
            this.students = students;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public List<Student> getStudents() {
            return students;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    /**
     * An object with a Map field used to test {@code ToStringStyle.JsonToStringStyle}.
     */
    static class InnerMapObject {
        /**
         * Test String field.
         */
        String pid;

        /**
         * Test inner map field.
         */
        Map<String, Object> map;
    }

@Test
    public void testNull_1_oe() {
        assertEquals("null", new ToStringBuilder(null).toString());
    }

@Test
    public void testBlank_1_oe() {
        assertEquals("{}", new ToStringBuilder(base).toString());
    }

@Test
    public void testAppendSuper_1_oe() {
        assertEquals( "{}", new ToStringBuilder(base).appendSuper( "Integer@8888[" + System.lineSeparator() + "]") .toString());
    }

@Test
    public void testAppendSuper_2_oe() {
        // removed other assertion
        assertEquals( "{}", new ToStringBuilder(base).appendSuper( "Integer@8888[" + System.lineSeparator() + "  null" + System.lineSeparator() + "]").toString());
    }

@Test
    public void testAppendSuper_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals( "{\"a\":\"hello\"}", new ToStringBuilder(base) .appendSuper( "Integer@8888[" + System.lineSeparator() + "]").append("a", "hello").toString());
    }

@Test
    public void testAppendSuper_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( "{\"a\":\"hello\"}", new ToStringBuilder(base) .appendSuper( "Integer@8888[" + System.lineSeparator() + "  null" + System.lineSeparator() + "]").append("a", "hello").toString());
    }

@Test
    public void testAppendSuper_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("{\"a\":\"hello\"}", new ToStringBuilder(base) .appendSuper(null).append("a", "hello").toString());
    }

@Test
    public void testAppendSuper_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("{\"a\":\"hello\",\"b\":\"world\"}", new ToStringBuilder(base) .appendSuper("{\"a\":\"hello\"}").append("b", "world").toString());
    }

@Test
    public void testChar_1_oe() {
        assertThrows(UnsupportedOperationException.class, () -> new ToStringBuilder(base).append('A').toString());
    }

@Test
    public void testChar_2_oe() {
        // removed other assertion

        assertEquals("{\"a\":\"A\"}", new ToStringBuilder(base).append("a", 'A') .toString());
    }

@Test
    public void testChar_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals("{\"a\":\"A\",\"b\":\"B\"}", new ToStringBuilder(base).append("a", 'A').append("b", 'B') .toString());
    }

@Test
    public void testDate_1_oe() {
        final Date now = new Date();
        final Date afterNow = new Date(System.currentTimeMillis() + 1);

        assertThrows(UnsupportedOperationException.class, () -> new ToStringBuilder(base).append(now).toString());
    }

@Test
    public void testDate_2_oe() {
        final Date now = new Date();
        final Date afterNow = new Date(System.currentTimeMillis() + 1);

        // removed other assertion

        assertEquals("{\"now\":\"" + now.toString() +"\"}", new ToStringBuilder(base).append("now", now) .toString());
    }

@Test
    public void testDate_3_oe() {
        final Date now = new Date();
        final Date afterNow = new Date(System.currentTimeMillis() + 1);

        // removed other assertion

        // removed other assertion
        assertEquals("{\"now\":\"" + now.toString() +"\",\"after\":\"" + afterNow.toString() + "\"}", new ToStringBuilder(base).append("now", now).append("after", afterNow) .toString());
    }

@Test
    public void testObject_1_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        assertThrows( UnsupportedOperationException.class, () -> new ToStringBuilder(base).append((Object) null).toString());
    }

@Test
    public void testObject_2_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        assertThrows(UnsupportedOperationException.class, () -> new ToStringBuilder(base).append(i3).toString());
    }

@Test
    public void testObject_3_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        assertEquals("{\"a\":null}", new ToStringBuilder(base).append("a", (Object) null).toString());
    }

@Test
    public void testObject_4_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals("{\"a\":3}", new ToStringBuilder(base).append("a", i3) .toString());
    }

@Test
    public void testObject_5_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("{\"a\":3,\"b\":4}", new ToStringBuilder(base).append("a", i3).append("b", i4) .toString());
    }

@Test
    public void testObject_6_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> new ToStringBuilder(base).append("a", i3, false).toString());
    }

@Test
    public void testObject_7_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> new ToStringBuilder(base).append("a", new ArrayList<>(), false).toString());
    }

@Test
    public void testObject_8_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( "{\"a\":[]}", new ToStringBuilder(base).append("a", new ArrayList<>(), true).toString());
    }

@Test
    public void testObject_9_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> new ToStringBuilder(base).append("a", new HashMap<>(), false).toString());
    }

@Test
    public void testObject_10_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( "{\"a\":{}}", new ToStringBuilder(base).append("a", new HashMap<>(), true).toString());
    }

@Test
    public void testObject_11_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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

        assertThrows( UnsupportedOperationException.class, () -> new ToStringBuilder(base).append("a", (Object) new String[0], false).toString());
    }

@Test
    public void testObject_12_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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

        assertEquals( "{\"a\":[]}", new ToStringBuilder(base).append("a", (Object) new String[0], true).toString());
    }

@Test
    public void testObject_13_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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

        assertThrows( UnsupportedOperationException.class, () -> new ToStringBuilder(base).append("a", (Object) new int[]{1, 2, 3}, false).toString());
    }

@Test
    public void testObject_14_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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

        assertEquals( "{\"a\":[1,2,3]}", new ToStringBuilder(base).append("a", (Object) new int[]{1, 2, 3}, true).toString());
    }

@Test
    public void testObject_15_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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

        assertThrows( UnsupportedOperationException.class, () -> new ToStringBuilder(base).append("a", (Object) new String[]{"v", "x", "y", "z"}, false).toString());
    }

@Test
    public void testObject_16_oe() {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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

        assertEquals( "{\"a\":[\"v\",\"x\",\"y\",\"z\"]}", new ToStringBuilder(base).append("a", (Object) new String[]{"v", "x", "y", "z"}, true) .toString());
    }

@Test
    public void testList_1_oe() {
        final Student student = new Student();
        final ArrayList<Hobby> objects = new ArrayList<>();

        objects.add(Hobby.BOOK);
        objects.add(Hobby.SPORT);
        objects.add(Hobby.MUSIC);

        student.setHobbies(objects);

        assertEquals(student.toString(), "{\"hobbies\":[\"BOOK\",\"SPORT\",\"MUSIC\"]}");
    }

@Test
    public void testList_2_oe() {
        final Student student = new Student();
        final ArrayList<Hobby> objects = new ArrayList<>();

        objects.add(Hobby.BOOK);
        objects.add(Hobby.SPORT);
        objects.add(Hobby.MUSIC);

        student.setHobbies(objects);

        // removed other assertion
        student.setHobbies(new ArrayList<>());
        assertEquals(student.toString(), "{\"hobbies\":[]}");
    }

@Test
    public void testList_3_oe() {
        final Student student = new Student();
        final ArrayList<Hobby> objects = new ArrayList<>();

        objects.add(Hobby.BOOK);
        objects.add(Hobby.SPORT);
        objects.add(Hobby.MUSIC);

        student.setHobbies(objects);

        // removed other assertion
        student.setHobbies(new ArrayList<>());
        // removed other assertion
        student.setHobbies(null);
        assertEquals(student.toString(), "{\"hobbies\":null}");
    }

@Test
    public void testArrayEnum_1_oe() {
        final Teacher teacher = new Teacher();
        final Hobby[] hobbies = new Hobby[3];
        hobbies[0] = Hobby.BOOK;
        hobbies[1] = Hobby.SPORT;
        hobbies[2] = Hobby.MUSIC;

        teacher.setHobbies(hobbies);

        assertEquals(teacher.toString(), "{\"hobbies\":[\"BOOK\",\"SPORT\",\"MUSIC\"]}");
    }

@Test
    public void testArrayEnum_2_oe() {
        final Teacher teacher = new Teacher();
        final Hobby[] hobbies = new Hobby[3];
        hobbies[0] = Hobby.BOOK;
        hobbies[1] = Hobby.SPORT;
        hobbies[2] = Hobby.MUSIC;

        teacher.setHobbies(hobbies);

        // removed other assertion
        teacher.setHobbies(new Hobby[0]);
        assertEquals(teacher.toString(), "{\"hobbies\":[]}");
    }

@Test
    public void testArrayEnum_3_oe() {
        final Teacher teacher = new Teacher();
        final Hobby[] hobbies = new Hobby[3];
        hobbies[0] = Hobby.BOOK;
        hobbies[1] = Hobby.SPORT;
        hobbies[2] = Hobby.MUSIC;

        teacher.setHobbies(hobbies);

        // removed other assertion
        teacher.setHobbies(new Hobby[0]);
        // removed other assertion
        teacher.setHobbies(null);
        assertEquals(teacher.toString(), "{\"hobbies\":null}");
    }

@Test
    public void testCombineListAndEnum_1_oe() {
        final Teacher teacher = new Teacher();

        final Hobby[] teacherHobbies = new Hobby[3];
        teacherHobbies[0] = Hobby.BOOK;
        teacherHobbies[1] = Hobby.SPORT;
        teacherHobbies[2] = Hobby.MUSIC;

        teacher.setHobbies(teacherHobbies);

        final Student john = new Student();
        john.setHobbies(Arrays.asList(Hobby.BOOK, Hobby.MUSIC));

        final Student alice = new Student();
        alice.setHobbies(new ArrayList<>());

        final Student bob = new Student();
        bob.setHobbies(Collections.singletonList(Hobby.BOOK));

        final ArrayList<Student> students = new ArrayList<>();
        students.add(john);
        students.add(alice);
        students.add(bob);

        final AcademyClass academyClass = new AcademyClass();
        academyClass.setStudents(students);
        academyClass.setTeacher(teacher);

        assertEquals(academyClass.toString(), "{\"students\":[{\"hobbies\":[\"BOOK\",\"MUSIC\"]},{\"hobbies\":[]},{\"hobbies\":[\"BOOK\"]}],\"teacher\":{\"hobbies\":[\"BOOK\",\"SPORT\",\"MUSIC\"]}}");
    }

@Test
    public void testPerson_1_oe() {
        final Person p = new Person();
        p.name = "Jane Doe";
        p.age = 25;
        p.smoker = true;

        assertEquals( "{\"name\":\"Jane Doe\",\"age\":25,\"smoker\":true}", new ToStringBuilder(p).append("name", p.name) .append("age", p.age).append("smoker", p.smoker) .toString());
    }

@Test
    public void testNestingPerson_1_oe() {
        final Person p = new Person() {
            @Override
            public String toString() {
                return new ToStringBuilder(this).append("name", this.name)
                    .append("age", this.age).append("smoker", this.smoker)
                    .toString();
            }
        };
        p.name = "Jane Doe";
        p.age = 25;
        p.smoker = true;

        final NestingPerson nestP = new NestingPerson();
        nestP.pid="#1@Jane";
        nestP.person = p;

        assertEquals( "{\"pid\":\"#1@Jane\",\"person\":{\"name\":\"Jane Doe\",\"age\":25,\"smoker\":true}}", new ToStringBuilder(nestP).append("pid", nestP.pid) .append("person", nestP.person) .toString());
    }

@Test
    public void testLong_1_oe() {
        assertThrows(UnsupportedOperationException.class, () -> new ToStringBuilder(base).append(3L).toString());
    }

@Test
    public void testLong_2_oe() {
        // removed other assertion

        assertEquals("{\"a\":3}", new ToStringBuilder(base).append("a", 3L) .toString());
    }

@Test
    public void testLong_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals("{\"a\":3,\"b\":4}", new ToStringBuilder(base).append("a", 3L).append("b", 4L) .toString());
    }

@Test
    public void testObjectArray_1_oe() {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testObjectArray_2_oe() {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"objectArray\":[null,5,[3,6]]}", toStringBuilder.append("objectArray", array) .toString());
    }

@Test
    public void testObjectArray_3_oe() {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testObjectArray_4_oe() {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object[]) null).toString());
    }

@Test
    public void testObjectArray_5_oe() {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testLongArray_1_oe() {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testLongArray_2_oe() {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"longArray\":[1,2,-3,4]}", toStringBuilder.append("longArray", array) .toString());
    }

@Test
    public void testLongArray_3_oe() {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testLongArray_4_oe() {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[]) null).toString());
    }

@Test
    public void testLongArray_5_oe() {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testIntArray_1_oe() {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testIntArray_2_oe() {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"intArray\":[1,2,-3,4]}", toStringBuilder.append("intArray", array) .toString());
    }

@Test
    public void testIntArray_3_oe() {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testIntArray_4_oe() {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[]) null).toString());
    }

@Test
    public void testIntArray_5_oe() {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testByteArray_1_oe() {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testByteArray_2_oe() {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"byteArray\":[1,2,-3,4]}", toStringBuilder.append("byteArray", array) .toString());
    }

@Test
    public void testByteArray_3_oe() {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testByteArray_4_oe() {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[]) null).toString());
    }

@Test
    public void testByteArray_5_oe() {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testShortArray_1_oe() {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testShortArray_2_oe() {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"shortArray\":[1,2,-3,4]}", toStringBuilder.append("shortArray", array) .toString());
    }

@Test
    public void testShortArray_3_oe() {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testShortArray_4_oe() {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[]) null).toString());
    }

@Test
    public void testShortArray_5_oe() {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testDoubleArray_1_oe() {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testDoubleArray_2_oe() {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"doubleArray\":[1.0,2.0,-3.0,4.0]}", toStringBuilder.append("doubleArray", array) .toString());
    }

@Test
    public void testDoubleArray_3_oe() {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testDoubleArray_4_oe() {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[]) null).toString());
    }

@Test
    public void testDoubleArray_5_oe() {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testFloatArray_1_oe() {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testFloatArray_2_oe() {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"floatArray\":[1.0,2.0,-3.0,4.0]}", toStringBuilder.append("floatArray", array) .toString());
    }

@Test
    public void testFloatArray_3_oe() {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testFloatArray_4_oe() {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[]) null).toString());
    }

@Test
    public void testFloatArray_5_oe() {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testCharArray_1_oe() {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testCharArray_2_oe() {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"charArray\":[\"1\",\"2\",\"3\",\"4\"]}", toStringBuilder.append("charArray", array) .toString());
    }

@Test
    public void testCharArray_3_oe() {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testCharArray_4_oe() {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[]) null).toString());
    }

@Test
    public void testCharArray_5_oe() {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testBooleanArray_1_oe() {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testBooleanArray_2_oe() {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertEquals("{\"booleanArray\":[true,false]}", toStringBuilder.append("booleanArray", array) .toString());
    }

@Test
    public void testBooleanArray_3_oe() {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testBooleanArray_4_oe() {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[]) null).toString());
    }

@Test
    public void testBooleanArray_5_oe() {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testLongArrayArray_1_oe() {
        final long[][] array = new long[][]{{1, 2}, null, {5}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        assertThrows(UnsupportedOperationException.class, () -> toStringBuilder.append(array).toString());
    }

@Test
    public void testLongArrayArray_2_oe() {
        final long[][] array = new long[][]{{1, 2}, null, {5}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testLongArrayArray_3_oe() {
        final long[][] array = new long[][]{{1, 2}, null, {5}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((long[][]) null).toString());
    }

@Test
    public void testLongArrayArray_4_oe() {
        final long[][] array = new long[][]{{1, 2}, null, {5}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( UnsupportedOperationException.class, () -> toStringBuilder.append((Object) array).toString());
    }

@Test
    public void testArray_1_oe() {
        final Person p = new Person();
        p.name = "Jane Doe";
        p.age = 25;
        p.smoker = true;

        assertEquals( "{\"name\":\"Jane Doe\",\"age\":25,\"smoker\":true,\"groups\":['admin', 'manager', 'user']}", new ToStringBuilder(p).append("name", p.name) .append("age", p.age).append("smoker", p.smoker) .append("groups", new Object() { @Override public String toString() { return "['admin', 'manager', 'user']"; } }) .toString());
    }

@Test
    public void testLANG1395_1_oe() {
        assertEquals("{\"name\":\"value\"}", new ToStringBuilder(base).append("name", "value").toString());
    }

@Test
    public void testLANG1395_2_oe() {
        // removed other assertion
        assertEquals("{\"name\":\"\"}", new ToStringBuilder(base).append("name", "").toString());
    }

@Test
    public void testLANG1395_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("{\"name\":\"\\\"\"}", new ToStringBuilder(base).append("name", '"').toString());
    }

@Test
    public void testLANG1395_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("{\"name\":\"\\\\\"}", new ToStringBuilder(base).append("name", '\\').toString());
    }

@Test
    public void testLANG1395_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("{\"name\":\"Let's \\\"quote\\\" this\"}", new ToStringBuilder(base).append("name", "Let's \"quote\" this").toString());
    }

@Test
    public void testLANG1396_1_oe() {
        assertEquals("{\"Let's \\\"quote\\\" this\":\"value\"}", new ToStringBuilder(base).append("Let's \"quote\" this", "value").toString());
    }

@Test
    public void testRootMap_1_oe() {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("k1", "v1");
        map.put("k2", 2);

        assertEquals("{\"map\":{\"k1\":\"v1\",\"k2\":2}}", new ToStringBuilder(base).append("map", map).toString());
    }

@Test
    public void testObjectWithInnerMap_1_oe() {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("k1", "value1");
        map.put("k2", 2);

        final InnerMapObject object = new InnerMapObject(){
            @Override
            public String toString() {
                return new ToStringBuilder(this).append("pid", this.pid)
                        .append("map", this.map).toString();
            }
        };
        object.pid = "dummy-text";
        object.map = map;

        assertEquals("{\"object\":{\"pid\":\"dummy-text\",\"map\":{\"k1\":\"value1\",\"k2\":2}}}", new ToStringBuilder(base).append("object", object).toString());
    }

@Test
    public void testNestedMaps_1_oe() {
        final Map<String, Object> innerMap = new LinkedHashMap<>();
        innerMap.put("k2.1", "v2.1");
        innerMap.put("k2.2", "v2.2");
        final Map<String, Object> baseMap = new LinkedHashMap<>();
        baseMap.put("k1", "v1");
        baseMap.put("k2", innerMap);

        final InnerMapObject object = new InnerMapObject(){
            @Override
            public String toString() {
                return new ToStringBuilder(this).append("pid", this.pid)
                        .append("map", this.map).toString();
            }
        };
        object.pid = "dummy-text";
        object.map = baseMap;

        assertEquals("{\"object\":{\"pid\":\"dummy-text\",\"map\":{\"k1\":\"v1\"," + "\"k2\":{\"k2.1\":\"v2.1\",\"k2.2\":\"v2.2\"}}}}", new ToStringBuilder(base).append("object", object).toString());
    }

@Test
    public void testMapSkipNullKey_1_oe() {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("k1", "v1");
        map.put(null, "v2");

        assertEquals("{\"map\":{\"k1\":\"v1\"}}", new ToStringBuilder(base).append("map", map).toString());
    }

}
