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
package org.apache.commons.vfs2.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 */
public class EncryptDecryptTest_OE25Dev {

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidDecrypt() throws Exception {
    	// provider.HostFileNameParser.extractToPath(String, StringBuilder) catches `Exception`
    	final String broken = "91458";
        final Cryptor cryptor = CryptorFactory.getCryptor();
        /* ignored */ cryptor.decrypt(broken);
    }


}
