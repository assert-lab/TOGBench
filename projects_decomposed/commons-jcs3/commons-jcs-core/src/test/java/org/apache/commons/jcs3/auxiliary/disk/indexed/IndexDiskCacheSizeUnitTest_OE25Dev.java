package org.apache.commons.jcs3.auxiliary.disk.indexed;

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

import java.io.IOException;

import org.apache.commons.jcs3.auxiliary.disk.DiskTestObject;
import org.apache.commons.jcs3.auxiliary.disk.behavior.IDiskCacheAttributes.DiskLimitType;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;

public class IndexDiskCacheSizeUnitTest_OE25Dev extends IndexDiskCacheUnitTestAbstract {

	@Override
	public IndexedDiskCacheAttributes getCacheAttributes() {
		final IndexedDiskCacheAttributes ret = new IndexedDiskCacheAttributes();
		ret.setDiskLimitType(DiskLimitType.SIZE);
		return ret;
	}


}
