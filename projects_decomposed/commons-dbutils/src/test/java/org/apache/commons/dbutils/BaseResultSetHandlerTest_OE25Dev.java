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
package org.apache.commons.dbutils;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class BaseResultSetHandlerTest_OE25Dev extends BaseTestCase {

    private static final class ToMapCollectionHandler
        extends BaseResultSetHandler<Collection<Map<String, Object>>> {

        @Override
        protected Collection<Map<String, Object>> handle() throws SQLException {
            Collection<Map<String, Object>> result = new LinkedList<Map<String, Object>>();

            while (next()) {
                Map<String, Object> current = new HashMap<String, Object>();

                for (int i = 1; i <= getMetaData().getColumnCount(); i++) {
                    current.put(getMetaData().getColumnName(i), getObject(i));
                }

                result.add(current);
            }

            return result;
        }

    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_1_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());

        assertFalse(result.isEmpty());
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_2_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("one"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_3_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("two"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_4_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("three"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_5_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("notInBean"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_6_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("intTest"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_7_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("integerTest"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_8_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("nullObjectTest"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_9_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("nullPrimitiveTest"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_10_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("notDate"));
    }
    }

    @Test
    public void handleWithoutExplicitResultSetInvocation_11_oe() throws Exception {
        Collection<Map<String, Object>> result = new ToMapCollectionHandler().handle(createMockResultSet());


        for (Map<String, Object> current : result) {
            assertTrue(current.containsKey("columnProcessorDoubleTest"));
    }
    }

}
