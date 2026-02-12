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
package org.apache.commons.lang3.text;

import static java.util.FormattableFlags.LEFT_JUSTIFY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Formatter;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link FormattableUtils}.
 */
@Deprecated
public class FormattableUtilsTest_OE25Dev {

    @Test
    public void testIllegalEllipsis_1_oe() throws Exception {
        try {
    FormattableUtils.append("foo", new Formatter(), 0, -1, 1, "xx");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
