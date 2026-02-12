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

import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.impl.VirtualFileSystem;
import org.apache.commons.vfs2.provider.ram.RamFileObject;
import org.apache.commons.vfs2.util.FileObjectUtils;
import org.junit.Test;

/**
 * Test the cache stragey
 */
public class ProviderCacheStrategyTests_OE25Dev extends AbstractProviderTestCase {
    public void assertContains(final FileObject[] fos, final String string) {
        for (final FileObject fo : fos) {
            if (string.equals(fo.getName().getBaseName())) {
                return;
            }
        }

        fail(string + " should be seen");
    }

    public void assertContainsNot(final FileObject[] fos, final String string) {
        for (final FileObject fo : fos) {
            if (string.equals(fo.getName().getBaseName())) {
                fail(string + " should not be seen");
            }
        }
    }

    /**
     * Returns the capabilities required by the tests of this test case.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] { Capability.CREATE, Capability.GET_TYPE, Capability.LIST_CHILDREN, };
    }

    /**
     * Test the manual cache strategy
     */

    /**
     * Test the on_call strategy
     */

    /**
     * Test the on_resolve strategy
     */


}
