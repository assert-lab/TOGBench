package org.apache.commons.jcs3.utils.serialization;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static org.junit.Assert.assertThrows;

import java.io.IOException;

import junit.framework.TestCase;

/**
 * Tests the encrypting serializer.
 */
public class EncryptingSerializerUnitTest_OE25Dev
    extends TestCase
{
    private EncryptingSerializer serializer;

    @Override
    protected void setUp() throws Exception
    {
        this.serializer = new EncryptingSerializer();
        this.serializer.setPreSharedKey("my_secret_key");
    }

    /**
     * Verify that we don't get any errors for null input.
     * <p>
     * @throws ClassNotFoundException
     * @throws IOException
     */

    /**
     * Test simple back and forth with a string.
     * <p>
     * ))&lt;=&gt;((
     * <p>
     * @throws Exception on error
     */

    /**
     * Test simple back and forth with a string.
     * <p>
     * ))&lt;=&gt;((
     * <p>
     * @throws Exception on error
     */

    /**
     * Test different key.
     * <p>
     * @throws Exception on error
     */

    /**
     * Test serialization with a null object. Verify that we don't get an error.
     * <p>
     * @throws Exception on error
     */

    public void testDifferentKey_1_oe()
        throws Exception
    {
        // DO WORK
        final String before = "adsfdsafdsafdsafdsafdsafdsafdsagfdsafdsafdsfdsafdsafsa333 31231";
        byte[] serialized = serializer.serialize(before);
        serializer.setPreSharedKey("another_key");

        try {
    serializer.deSerialize(serialized, null);
    org.junit.jupiter.api.Assertions.fail("IOException");
} catch (IOException e) {
}
    }

}
