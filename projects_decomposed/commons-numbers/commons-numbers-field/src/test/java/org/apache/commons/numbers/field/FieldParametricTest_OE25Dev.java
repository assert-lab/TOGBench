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
package org.apache.commons.numbers.field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import org.apache.commons.numbers.core.Addition;
import org.apache.commons.numbers.core.Multiplication;

/**
 * Tests for fields.
 */
class FieldParametricTest_OE25Dev {

    private static Stream<FieldTestData<?>> getList() {
        return FieldsList.list().stream();
    }

    /**
     * @param a Instance.
     * @param b Instance.
     */
    private static void assertEquals(Object a,
                                     Object b) {
        Assertions.assertEquals(a, b, a + " != " + b);
    }


}
