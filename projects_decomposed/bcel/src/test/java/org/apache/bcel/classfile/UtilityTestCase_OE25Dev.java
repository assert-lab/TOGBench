/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.bcel.classfile;

import junit.framework.TestCase;

public class UtilityTestCase_OE25Dev extends TestCase {

    public void testSignatureToStringWithGenerics_1_oe() throws Exception {
    // tests for BCEL-197
        assertEquals("generic signature", "java.util.Map<X, java.util.List<Y>>", Utility.signatureToString("Ljava/util/Map<TX;Ljava/util/List<TY;>;>;"));
    }

    public void testSignatureToStringWithGenerics_2_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        assertEquals("generic signature", "java.util.Set<? extends java.nio.file.OpenOption>" , Utility.signatureToString("Ljava/util/Set<+Ljava/nio/file/OpenOption;>;"));
    }

    public void testSignatureToStringWithGenerics_3_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        assertEquals("generic signature", "java.nio.file.attribute.FileAttribute<?>[]", Utility.signatureToString("[Ljava/nio/file/attribute/FileAttribute<*>;"));
    }

    public void testSignatureToStringWithGenerics_4_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        assertEquals("generic signature", "boofcv.alg.tracker.tld.TldTracker<boofcv.struct.image.ImageGray<boofcv.struct.image.GrayU8>, boofcv.struct.image.GrayI<boofcv.struct.image.GrayU8>>", Utility.signatureToString("Lboofcv/alg/tracker/tld/TldTracker<Lboofcv/struct/image/ImageGray<Lboofcv/struct/image/GrayU8;>;Lboofcv/struct/image/GrayI<Lboofcv/struct/image/GrayU8;>;>;"));
    }

    public void testSignatureToStringWithGenerics_5_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        assertEquals("generic signature", "java.util.Map<?, ?>", Utility.signatureToString("Ljava/util/Map<**>;"));
    }

    public void testSignatureToStringWithGenerics_6_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        // removed other assertion
        assertEquals("generic signature", "com.jme3.util.IntMap<T>.IntMapIterator", Utility.signatureToString("Lcom/jme3/util/IntMap<TT;>.IntMapIterator;"));
    }

    public void testSignatureToStringWithGenerics_7_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-279
        assertEquals("type parameters signature", "<T extends java.lang.Object>(com.google.common.io.ByteProcessor<T>, int)T", Utility.signatureToString("<T:Ljava/lang/Object;>(Lcom/google/common/io/ByteProcessor<TT;>;I)TT;", false));
    }

    public void testSignatureToStringWithGenerics_8_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-279
        // removed other assertion
        assertEquals("type parameters signature", "<T extends Object>(com.google.common.io.ByteProcessor<T>, int)T", Utility.signatureToString("<T:Ljava/lang/Object;>(Lcom/google/common/io/ByteProcessor<TT;>;I)TT;", true));
    }

    public void testSignatureToStringWithGenerics_9_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-279
        // removed other assertion
        // removed other assertion
        assertEquals("type parameters signature", "<M extends java.lang.reflect.AccessibleObject & java.lang.reflect.Member>(M)void", Utility.signatureToString("<M:Ljava/lang/reflect/AccessibleObject;:Ljava/lang/reflect/Member;>(TM;)V"));
    }

    public void testSignatureToStringWithGenerics_10_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-279
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("type parameters signature", "<K1 extends K, V1 extends V>()com.google.common.cache.Weigher<K1, V1>", Utility.signatureToString("<K1:TK;V1:TV;>()Lcom/google/common/cache/Weigher<TK1;TV1;>;"));
    }

    public void testSignatureToStringWithGenerics_11_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-279
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("type parameters signature", "<K1 extends K, V1 extends V>(com.google.common.cache.Weigher<? super K1, ? super V1>)com.google.common.cache.CacheBuilder<K1, V1>", Utility.signatureToString("<K1:TK;V1:TV;>(Lcom/google/common/cache/Weigher<-TK1;-TV1;>;)Lcom/google/common/cache/CacheBuilder<TK1;TV1;>;"));
    }

    public void testSignatureToStringWithGenerics_12_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-279
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("class signature", "<N extends java.lang.Object, E extends java.lang.Object> extends java.lang.Object implements com.google.common.graph.Network<N, E>", Utility.signatureToString("<N:Ljava/lang/Object;E:Ljava/lang/Object;>Ljava/lang/Object;Lcom/google/common/graph/Network<TN;TE;>;", false));
    }

    public void testSignatureToStringWithGenerics_13_oe() throws Exception {
    // tests for BCEL-197
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-286
        // removed other assertion
        // removed other assertion
        // removed other assertion

    // tests for BCEL-279
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("class signature", "<K extends Object, V extends Object> extends Object", Utility.signatureToString("<K:Ljava/lang/Object;V:Ljava/lang/Object;>Ljava/lang/Object;"));
    }

}
