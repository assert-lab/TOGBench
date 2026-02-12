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
package org.apache.commons.vfs2;

import static org.junit.Assert.assertNotEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for getting and setting file last modified time.
 */
public class LastModifiedTests_OE25Dev extends AbstractProviderTestCase {

    protected static final Duration ONE_DAY = Duration.ofDays(1);

    protected void assertDeltaMillis(final String message, final long expected, final long actual, final long delta) {
        if (expected == actual) {
            return;
        }
        // getLastModTimeAccuracy() is not accurate
        final long actualDelta = Math.abs(expected - actual);
        if (actualDelta > Math.max(delta, 1000)) {
            Assert.fail(String.format("%s expected=%,d(%s),actual=%,d(%s),expected delta=%,d,actual delta=%,d",message,Long.valueOf(expected),new Date(expected).toString(),Long.valueOf(actual),new Date(actual).toString(),Long.valueOf(delta),Long.valueOf(actualDelta)));
        }
    }

    protected void assertEqualMillis(final String message, final long expected, final long actual) {
        if (expected != actual) {
            final long delta = Math.abs(expected - actual);
            Assert
                .fail(String.format("%s expected=%,d (%s), actual=%,d (%s), delta=%,d", message, Long.valueOf(expected),
                    new Date(expected).toString(), Long.valueOf(actual), new Date(actual).toString(), delta));
        }
    }

    /**
     * Returns the capabilities required by the tests of this test case.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] {Capability.GET_LAST_MODIFIED};
    }

    /**
     * Tests FileSystem#getLastModTimeAccuracy for sane values.
     *
     * @throws FileSystemException if error occurred
     */

    /**
     * Tests getting the last modified time of a file.
     *
     * @throws FileSystemException if error occurred
     */

    /**
     * Tests getting the last modified time of a folder.
     *
     * @throws FileSystemException if error occurred
     */

    /**
     * Tests setting the last modified time of file.
     *
     * @throws FileSystemException if error occurred
     */

    /**
     * Tests setting the last modified time of a folder.
     *
     * @throws FileSystemException if error occurred
     */


}
