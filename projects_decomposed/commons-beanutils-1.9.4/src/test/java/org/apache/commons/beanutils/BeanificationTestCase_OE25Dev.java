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

package org.apache.commons.beanutils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Test Case for changes made during Beanutils Beanification
 * </p>
 *
 * @version $Id$
 */

public class BeanificationTestCase_OE25Dev extends TestCase {

    // ---------------------------------------------------- Constants

    /** Maximum number of iterations before our test fails */
    public static final int MAX_GC_ITERATIONS = 50;

    // ---------------------------------------------------- Instance Variables


    // ---------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public BeanificationTestCase_OE25Dev(final String name) {
        super(name);
    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() {

        ConvertUtils.deregister();

    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(BeanificationTestCase.class));
    }


    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {
        // No action required
    }


    // ------------------------------------------------ Individual Test Methods

    /** Test of the methodology we'll use for some of the later tests */

    /** Tests whether classloaders and beans are released from memory by the map used by beanutils */

    /** Tests whether classloaders and beans are released from memory */

    /**
     * Tests whether difference instances are loaded by different
     * context classloaders.
     */


    /**
     * Tests whether difference instances are loaded by different
     * context classloaders.
     */

    /** Tests whether calls are independent for different classloaders */

    /** Tests whether different threads can set beanutils instances correctly */

    /** Tests whether the unset method works*/

    // ---- Auxillary classes

    class TestClassLoader extends ClassLoader {
        @Override
        public String toString() {
            return "TestClassLoader";
        }
    }

    class Signal {
        private Exception e;
        private int signal = 0;
        private BeanUtilsBean bean;
        private PropertyUtilsBean propertyUtils;
        private ConvertUtilsBean convertUtils;
        private Object marker;

        public Exception getException() {
            return e;
        }

        public void setException(final Exception e) {
            this.e = e;
        }

        public int getSignal() {
            return signal;
        }

        public void setSignal(final int signal) {
            this.signal = signal;
        }

        public Object getMarkerObject() {
            return marker;
        }

        public void setMarkerObject(final Object marker) {
            this.marker = marker;
        }

        public BeanUtilsBean getBean() {
            return bean;
        }

        public void setBean(final BeanUtilsBean bean) {
            this.bean = bean;
        }

        public PropertyUtilsBean getPropertyUtils() {
            return propertyUtils;
        }

        public void setPropertyUtils(final PropertyUtilsBean propertyUtils) {
            this.propertyUtils = propertyUtils;
        }

        public ConvertUtilsBean getConvertUtils() {
            return convertUtils;
        }

        public void setConvertUtils(final ConvertUtilsBean convertUtils) {
            this.convertUtils = convertUtils;
        }
    }

    public void testMemoryTestMethodology_1_oe() throws Exception {
        // test metho;
        final WeakReference<ClassLoader> reference = new  WeakReference<ClassLoader>(loader);
        @SuppressWarnings("unused")
        Class<?> myClass = loader.loadClass("org.apache.commons.beanutils.BetaBean");

        assertNotNull("Weak reference released early", reference.get());
    }

    public void testMemoryTestMethodology_2_oe() throws Exception {
        // test metho;
        final WeakReference<ClassLoader> reference = new  WeakReference<ClassLoader>(loader);
        @SuppressWarnings("unused")
        Class<?> myClass = loader.loadClass("org.apache.commons.beanutils.BetaBean");

        // removed other assertion

        // dereference class loader and class:
        loader = null;
        myClass = null;

        int iterations = 0;
        int bytz = 2;
        while(true) {
            System.gc();
            if(iterations++ > MAX_GC_ITERATIONS){
                fail("Max iterations reached before resource released.");
    }
    }
    }

    public void testMemoryLeak2_1_oe() throws Exception {
        // tests when the map used by beanutils has the right behaviour

        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        final WeakReference<ClassLoader> loaderReference = new WeakReference<ClassLoader>(loader, queue);
        Integer test = new Integer(1);

        final WeakReference<Integer> testReference = new WeakReference<Integer>(test, queue);
        //Map map = new ReferenceMap(ReferenceMap.WEAK, ReferenceMap.HARD, true);
        final Map<Object, Object> map = new WeakHashMap<Object, Object>();
        map.put(loader, test);

        assertEquals("In map", test, map.get(loader));
    }

    public void testMemoryLeak2_2_oe() throws Exception {
        // tests when the map used by beanutils has the right behaviour

        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        final WeakReference<ClassLoader> loaderReference = new WeakReference<ClassLoader>(loader, queue);
        Integer test = new Integer(1);

        final WeakReference<Integer> testReference = new WeakReference<Integer>(test, queue);
        //Map map = new ReferenceMap(ReferenceMap.WEAK, ReferenceMap.HARD, true);
        final Map<Object, Object> map = new WeakHashMap<Object, Object>();
        map.put(loader, test);

        // removed other assertion
        assertNotNull("Weak reference released early (1)", loaderReference.get());
    }

    public void testMemoryLeak2_3_oe() throws Exception {
        // tests when the map used by beanutils has the right behaviour

        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        final WeakReference<ClassLoader> loaderReference = new WeakReference<ClassLoader>(loader, queue);
        Integer test = new Integer(1);

        final WeakReference<Integer> testReference = new WeakReference<Integer>(test, queue);
        //Map map = new ReferenceMap(ReferenceMap.WEAK, ReferenceMap.HARD, true);
        final Map<Object, Object> map = new WeakHashMap<Object, Object>();
        map.put(loader, test);

        // removed other assertion
        // removed other assertion
        assertNotNull("Weak reference released early (2)", testReference.get());
    }

    public void testMemoryLeak2_4_oe() throws Exception {
        // tests when the map used by beanutils has the right behaviour

        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        final WeakReference<ClassLoader> loaderReference = new WeakReference<ClassLoader>(loader, queue);
        Integer test = new Integer(1);

        final WeakReference<Integer> testReference = new WeakReference<Integer>(test, queue);
        //Map map = new ReferenceMap(ReferenceMap.WEAK, ReferenceMap.HARD, true);
        final Map<Object, Object> map = new WeakHashMap<Object, Object>();
        map.put(loader, test);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // dereference strong references
        loader = null;
        test = null;

        int iterations = 0;
        int bytz = 2;
        while(true) {
            System.gc();
            if(iterations++ > MAX_GC_ITERATIONS){
                fail("Max iterations reached before resource released.");
    }
    }
    }

    public void testMemoryLeak_1_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final WeakReference<ClassLoader> loaderReference = new  WeakReference<ClassLoader>(loader);
        BeanUtilsBean.getInstance();

        class GetBeanUtilsBeanThread extends Thread {

            BeanUtilsBean beanUtils;
            ConvertUtilsBean convertUtils;
            PropertyUtilsBean propertyUtils;

            GetBeanUtilsBeanThread() {}

            @Override
            public void run() {
                beanUtils = BeanUtilsBean.getInstance();
                convertUtils = ConvertUtilsBean.getInstance();
                propertyUtils = PropertyUtilsBean.getInstance();
                // XXX Log keeps a reference around!
                LogFactory.releaseAll();
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }


        GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread();
        @SuppressWarnings("unused")
        final
        WeakReference<Thread> threadWeakReference = new WeakReference<Thread>(thread);
        thread.setContextClassLoader(loader);

        thread.start();
        thread.join();

        final WeakReference<BeanUtilsBean> beanUtilsReference = new WeakReference<BeanUtilsBean>(thread.beanUtils);
        final WeakReference<PropertyUtilsBean> propertyUtilsReference =  new WeakReference<PropertyUtilsBean>(thread.propertyUtils);
        final WeakReference<ConvertUtilsBean> convertUtilsReference = new WeakReference<ConvertUtilsBean>(thread.convertUtils);

        assertNotNull("Weak reference released early (1)", loaderReference.get());
    }

    public void testMemoryLeak_2_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final WeakReference<ClassLoader> loaderReference = new  WeakReference<ClassLoader>(loader);
        BeanUtilsBean.getInstance();

        class GetBeanUtilsBeanThread extends Thread {

            BeanUtilsBean beanUtils;
            ConvertUtilsBean convertUtils;
            PropertyUtilsBean propertyUtils;

            GetBeanUtilsBeanThread() {}

            @Override
            public void run() {
                beanUtils = BeanUtilsBean.getInstance();
                convertUtils = ConvertUtilsBean.getInstance();
                propertyUtils = PropertyUtilsBean.getInstance();
                // XXX Log keeps a reference around!
                LogFactory.releaseAll();
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }


        GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread();
        @SuppressWarnings("unused")
        final
        WeakReference<Thread> threadWeakReference = new WeakReference<Thread>(thread);
        thread.setContextClassLoader(loader);

        thread.start();
        thread.join();

        final WeakReference<BeanUtilsBean> beanUtilsReference = new WeakReference<BeanUtilsBean>(thread.beanUtils);
        final WeakReference<PropertyUtilsBean> propertyUtilsReference =  new WeakReference<PropertyUtilsBean>(thread.propertyUtils);
        final WeakReference<ConvertUtilsBean> convertUtilsReference = new WeakReference<ConvertUtilsBean>(thread.convertUtils);

        // removed other assertion
        assertNotNull("Weak reference released early (2)", beanUtilsReference.get());
    }

    public void testMemoryLeak_3_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final WeakReference<ClassLoader> loaderReference = new  WeakReference<ClassLoader>(loader);
        BeanUtilsBean.getInstance();

        class GetBeanUtilsBeanThread extends Thread {

            BeanUtilsBean beanUtils;
            ConvertUtilsBean convertUtils;
            PropertyUtilsBean propertyUtils;

            GetBeanUtilsBeanThread() {}

            @Override
            public void run() {
                beanUtils = BeanUtilsBean.getInstance();
                convertUtils = ConvertUtilsBean.getInstance();
                propertyUtils = PropertyUtilsBean.getInstance();
                // XXX Log keeps a reference around!
                LogFactory.releaseAll();
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }


        GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread();
        @SuppressWarnings("unused")
        final
        WeakReference<Thread> threadWeakReference = new WeakReference<Thread>(thread);
        thread.setContextClassLoader(loader);

        thread.start();
        thread.join();

        final WeakReference<BeanUtilsBean> beanUtilsReference = new WeakReference<BeanUtilsBean>(thread.beanUtils);
        final WeakReference<PropertyUtilsBean> propertyUtilsReference =  new WeakReference<PropertyUtilsBean>(thread.propertyUtils);
        final WeakReference<ConvertUtilsBean> convertUtilsReference = new WeakReference<ConvertUtilsBean>(thread.convertUtils);

        // removed other assertion
        // removed other assertion
        assertNotNull("Weak reference released early (3)", propertyUtilsReference.get());
    }

    public void testMemoryLeak_4_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final WeakReference<ClassLoader> loaderReference = new  WeakReference<ClassLoader>(loader);
        BeanUtilsBean.getInstance();

        class GetBeanUtilsBeanThread extends Thread {

            BeanUtilsBean beanUtils;
            ConvertUtilsBean convertUtils;
            PropertyUtilsBean propertyUtils;

            GetBeanUtilsBeanThread() {}

            @Override
            public void run() {
                beanUtils = BeanUtilsBean.getInstance();
                convertUtils = ConvertUtilsBean.getInstance();
                propertyUtils = PropertyUtilsBean.getInstance();
                // XXX Log keeps a reference around!
                LogFactory.releaseAll();
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }


        GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread();
        @SuppressWarnings("unused")
        final
        WeakReference<Thread> threadWeakReference = new WeakReference<Thread>(thread);
        thread.setContextClassLoader(loader);

        thread.start();
        thread.join();

        final WeakReference<BeanUtilsBean> beanUtilsReference = new WeakReference<BeanUtilsBean>(thread.beanUtils);
        final WeakReference<PropertyUtilsBean> propertyUtilsReference =  new WeakReference<PropertyUtilsBean>(thread.propertyUtils);
        final WeakReference<ConvertUtilsBean> convertUtilsReference = new WeakReference<ConvertUtilsBean>(thread.convertUtils);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull("Weak reference released early (4)", convertUtilsReference.get());
    }

    public void testMemoryLeak_5_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final WeakReference<ClassLoader> loaderReference = new  WeakReference<ClassLoader>(loader);
        BeanUtilsBean.getInstance();

        class GetBeanUtilsBeanThread extends Thread {

            BeanUtilsBean beanUtils;
            ConvertUtilsBean convertUtils;
            PropertyUtilsBean propertyUtils;

            GetBeanUtilsBeanThread() {}

            @Override
            public void run() {
                beanUtils = BeanUtilsBean.getInstance();
                convertUtils = ConvertUtilsBean.getInstance();
                propertyUtils = PropertyUtilsBean.getInstance();
                // XXX Log keeps a reference around!
                LogFactory.releaseAll();
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }


        GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread();
        @SuppressWarnings("unused")
        final
        WeakReference<Thread> threadWeakReference = new WeakReference<Thread>(thread);
        thread.setContextClassLoader(loader);

        thread.start();
        thread.join();

        final WeakReference<BeanUtilsBean> beanUtilsReference = new WeakReference<BeanUtilsBean>(thread.beanUtils);
        final WeakReference<PropertyUtilsBean> propertyUtilsReference =  new WeakReference<PropertyUtilsBean>(thread.propertyUtils);
        final WeakReference<ConvertUtilsBean> convertUtilsReference = new WeakReference<ConvertUtilsBean>(thread.convertUtils);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // dereference strong references
        loader = null;
        thread.setContextClassLoader(null);
        thread = null;

        int iterations = 0;
        int bytz = 2;
        while(true) {
            BeanUtilsBean.getInstance();
            System.gc();
            if(iterations++ > MAX_GC_ITERATIONS){
                fail("Max iterations reached before resource released.");
    }
    }
    }

    public void testGetByContextClassLoader_1_oe() throws Exception {

        class GetBeanUtilsBeanThread extends Thread {

            private final Signal signal;

            GetBeanUtilsBeanThread(final Signal signal) {
                this.signal = signal;
            }

            @Override
            public void run() {
                signal.setSignal(2);
                signal.setBean(BeanUtilsBean.getInstance());
                signal.setConvertUtils(ConvertUtilsBean.getInstance());
                signal.setPropertyUtils(PropertyUtilsBean.getInstance());
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread(signal);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        assertEquals("Signal not set by test thread", 2, signal.getSignal());
    }

    public void testGetByContextClassLoader_2_oe() throws Exception {

        class GetBeanUtilsBeanThread extends Thread {

            private final Signal signal;

            GetBeanUtilsBeanThread(final Signal signal) {
                this.signal = signal;
            }

            @Override
            public void run() {
                signal.setSignal(2);
                signal.setBean(BeanUtilsBean.getInstance());
                signal.setConvertUtils(ConvertUtilsBean.getInstance());
                signal.setPropertyUtils(PropertyUtilsBean.getInstance());
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread(signal);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        // removed other assertion
        assertTrue( "Different BeanUtilsBean instances per context classloader", BeanUtilsBean.getInstance() != signal.getBean());
    }

    public void testGetByContextClassLoader_3_oe() throws Exception {

        class GetBeanUtilsBeanThread extends Thread {

            private final Signal signal;

            GetBeanUtilsBeanThread(final Signal signal) {
                this.signal = signal;
            }

            @Override
            public void run() {
                signal.setSignal(2);
                signal.setBean(BeanUtilsBean.getInstance());
                signal.setConvertUtils(ConvertUtilsBean.getInstance());
                signal.setPropertyUtils(PropertyUtilsBean.getInstance());
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread(signal);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        // removed other assertion
        // removed other assertion
        assertTrue( "Different ConvertUtilsBean instances per context classloader", ConvertUtilsBean.getInstance() != signal.getConvertUtils());
    }

    public void testGetByContextClassLoader_4_oe() throws Exception {

        class GetBeanUtilsBeanThread extends Thread {

            private final Signal signal;

            GetBeanUtilsBeanThread(final Signal signal) {
                this.signal = signal;
            }

            @Override
            public void run() {
                signal.setSignal(2);
                signal.setBean(BeanUtilsBean.getInstance());
                signal.setConvertUtils(ConvertUtilsBean.getInstance());
                signal.setPropertyUtils(PropertyUtilsBean.getInstance());
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread(signal);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "Different PropertyUtilsBean instances per context classloader", PropertyUtilsBean.getInstance() != signal.getPropertyUtils());
    }

    public void testContextClassLoaderLocal_1_oe() throws Exception {

        class CCLLTesterThread extends Thread {

            private final Signal signal;
            private final ContextClassLoaderLocal<Integer> ccll;

            CCLLTesterThread(final Signal signal, final ContextClassLoaderLocal<Integer> ccll) {
                this.signal = signal;
                this.ccll = ccll;
            }

            @Override
            public void run() {
                ccll.set(new Integer(1789));
                signal.setSignal(2);
                signal.setMarkerObject(ccll.get());
            }

            @Override
            public String toString() {
                return "CCLLTesterThread";
            }
        }

        final ContextClassLoaderLocal<Integer> ccll = new ContextClassLoaderLocal<Integer>();
        ccll.set(new Integer(1776));
        assertEquals("Start thread sets value", new Integer(1776), ccll.get());
    }

    public void testContextClassLoaderLocal_2_oe() throws Exception {

        class CCLLTesterThread extends Thread {

            private final Signal signal;
            private final ContextClassLoaderLocal<Integer> ccll;

            CCLLTesterThread(final Signal signal, final ContextClassLoaderLocal<Integer> ccll) {
                this.signal = signal;
                this.ccll = ccll;
            }

            @Override
            public void run() {
                ccll.set(new Integer(1789));
                signal.setSignal(2);
                signal.setMarkerObject(ccll.get());
            }

            @Override
            public String toString() {
                return "CCLLTesterThread";
            }
        }

        final ContextClassLoaderLocal<Integer> ccll = new ContextClassLoaderLocal<Integer>();
        ccll.set(new Integer(1776));
        // removed other assertion

        final Signal signal = new Signal();
        signal.setSignal(1);

        final CCLLTesterThread thread = new CCLLTesterThread(signal, ccll);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        assertEquals("Signal not set by test thread", 2, signal.getSignal());
    }

    public void testContextClassLoaderLocal_3_oe() throws Exception {

        class CCLLTesterThread extends Thread {

            private final Signal signal;
            private final ContextClassLoaderLocal<Integer> ccll;

            CCLLTesterThread(final Signal signal, final ContextClassLoaderLocal<Integer> ccll) {
                this.signal = signal;
                this.ccll = ccll;
            }

            @Override
            public void run() {
                ccll.set(new Integer(1789));
                signal.setSignal(2);
                signal.setMarkerObject(ccll.get());
            }

            @Override
            public String toString() {
                return "CCLLTesterThread";
            }
        }

        final ContextClassLoaderLocal<Integer> ccll = new ContextClassLoaderLocal<Integer>();
        ccll.set(new Integer(1776));
        // removed other assertion

        final Signal signal = new Signal();
        signal.setSignal(1);

        final CCLLTesterThread thread = new CCLLTesterThread(signal, ccll);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        // removed other assertion
        assertEquals("Second thread preserves value", new Integer(1776), ccll.get());
    }

    public void testContextClassLoaderLocal_4_oe() throws Exception {

        class CCLLTesterThread extends Thread {

            private final Signal signal;
            private final ContextClassLoaderLocal<Integer> ccll;

            CCLLTesterThread(final Signal signal, final ContextClassLoaderLocal<Integer> ccll) {
                this.signal = signal;
                this.ccll = ccll;
            }

            @Override
            public void run() {
                ccll.set(new Integer(1789));
                signal.setSignal(2);
                signal.setMarkerObject(ccll.get());
            }

            @Override
            public String toString() {
                return "CCLLTesterThread";
            }
        }

        final ContextClassLoaderLocal<Integer> ccll = new ContextClassLoaderLocal<Integer>();
        ccll.set(new Integer(1776));
        // removed other assertion

        final Signal signal = new Signal();
        signal.setSignal(1);

        final CCLLTesterThread thread = new CCLLTesterThread(signal, ccll);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        // removed other assertion
        // removed other assertion
        assertEquals("Second thread gets value it set", new Integer(1789), signal.getMarkerObject());
    }

    public void testContextClassloaderIndependence_1_oe() throws Exception {

        class TestIndependenceThread extends Thread {
            private final Signal signal;
            private final PrimitiveBean bean;

            TestIndependenceThread(final Signal signal, final PrimitiveBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                try {
                    signal.setSignal(3);
                    ConvertUtils.register(new Converter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(new Integer(9));
                                            }
                                                }, Integer.TYPE);
                    BeanUtils.setProperty(bean, "int", new Integer(1));
                } catch (final Exception e) {
                    e.printStackTrace();
                    signal.setException(e);
                }
            }

            @Override
            public String toString() {
                return "TestIndependenceThread";
            }
        }

        final PrimitiveBean bean = new PrimitiveBean();
        BeanUtils.setProperty(bean, "int", new Integer(1));
        assertEquals("Wrong property value (1)", 1, bean.getInt());
    }

    public void testContextClassloaderIndependence_2_oe() throws Exception {

        class TestIndependenceThread extends Thread {
            private final Signal signal;
            private final PrimitiveBean bean;

            TestIndependenceThread(final Signal signal, final PrimitiveBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                try {
                    signal.setSignal(3);
                    ConvertUtils.register(new Converter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(new Integer(9));
                                            }
                                                }, Integer.TYPE);
                    BeanUtils.setProperty(bean, "int", new Integer(1));
                } catch (final Exception e) {
                    e.printStackTrace();
                    signal.setException(e);
                }
            }

            @Override
            public String toString() {
                return "TestIndependenceThread";
            }
        }

        final PrimitiveBean bean = new PrimitiveBean();
        BeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        ConvertUtils.register(new Converter() {
                                public <T> T convert(final Class<T> type, final Object value) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(new Integer(5));
                                }
                                    }, Integer.TYPE);
        BeanUtils.setProperty(bean, "int", new Integer(1));
        assertEquals("Wrong property value(2)", 5, bean.getInt());
    }

    public void testContextClassloaderIndependence_3_oe() throws Exception {

        class TestIndependenceThread extends Thread {
            private final Signal signal;
            private final PrimitiveBean bean;

            TestIndependenceThread(final Signal signal, final PrimitiveBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                try {
                    signal.setSignal(3);
                    ConvertUtils.register(new Converter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(new Integer(9));
                                            }
                                                }, Integer.TYPE);
                    BeanUtils.setProperty(bean, "int", new Integer(1));
                } catch (final Exception e) {
                    e.printStackTrace();
                    signal.setException(e);
                }
            }

            @Override
            public String toString() {
                return "TestIndependenceThread";
            }
        }

        final PrimitiveBean bean = new PrimitiveBean();
        BeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        ConvertUtils.register(new Converter() {
                                public <T> T convert(final Class<T> type, final Object value) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(new Integer(5));
                                }
                                    }, Integer.TYPE);
        BeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        final Signal signal = new Signal();
        signal.setSignal(1);
        final TestIndependenceThread thread = new TestIndependenceThread(signal, bean);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        assertNull("Exception thrown by test thread:" + signal.getException(), signal.getException());
    }

    public void testContextClassloaderIndependence_4_oe() throws Exception {

        class TestIndependenceThread extends Thread {
            private final Signal signal;
            private final PrimitiveBean bean;

            TestIndependenceThread(final Signal signal, final PrimitiveBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                try {
                    signal.setSignal(3);
                    ConvertUtils.register(new Converter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(new Integer(9));
                                            }
                                                }, Integer.TYPE);
                    BeanUtils.setProperty(bean, "int", new Integer(1));
                } catch (final Exception e) {
                    e.printStackTrace();
                    signal.setException(e);
                }
            }

            @Override
            public String toString() {
                return "TestIndependenceThread";
            }
        }

        final PrimitiveBean bean = new PrimitiveBean();
        BeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        ConvertUtils.register(new Converter() {
                                public <T> T convert(final Class<T> type, final Object value) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(new Integer(5));
                                }
                                    }, Integer.TYPE);
        BeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        final Signal signal = new Signal();
        signal.setSignal(1);
        final TestIndependenceThread thread = new TestIndependenceThread(signal, bean);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        // removed other assertion
        assertEquals("Signal not set by test thread", 3, signal.getSignal());
    }

    public void testContextClassloaderIndependence_5_oe() throws Exception {

        class TestIndependenceThread extends Thread {
            private final Signal signal;
            private final PrimitiveBean bean;

            TestIndependenceThread(final Signal signal, final PrimitiveBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                try {
                    signal.setSignal(3);
                    ConvertUtils.register(new Converter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(new Integer(9));
                                            }
                                                }, Integer.TYPE);
                    BeanUtils.setProperty(bean, "int", new Integer(1));
                } catch (final Exception e) {
                    e.printStackTrace();
                    signal.setException(e);
                }
            }

            @Override
            public String toString() {
                return "TestIndependenceThread";
            }
        }

        final PrimitiveBean bean = new PrimitiveBean();
        BeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        ConvertUtils.register(new Converter() {
                                public <T> T convert(final Class<T> type, final Object value) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(new Integer(5));
                                }
                                    }, Integer.TYPE);
        BeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        final Signal signal = new Signal();
        signal.setSignal(1);
        final TestIndependenceThread thread = new TestIndependenceThread(signal, bean);
        thread.setContextClassLoader(new TestClassLoader());

        thread.start();
        thread.join();

        // removed other assertion
        // removed other assertion
        assertEquals("Wrong property value(3)", 9, bean.getInt());
    }

    public void testBeanUtilsBeanSetInstance_1_oe() throws Exception {

        class SetInstanceTesterThread extends Thread {

            private final Signal signal;
            private final BeanUtilsBean bean;

            SetInstanceTesterThread(final Signal signal, final BeanUtilsBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                BeanUtilsBean.setInstance(bean);
                signal.setSignal(21);
                signal.setBean(BeanUtilsBean.getInstance());
            }

            @Override
            public String toString() {
                return "SetInstanceTesterThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final BeanUtilsBean beanOne = new BeanUtilsBean();
        final BeanUtilsBean beanTwo = new BeanUtilsBean();

        final SetInstanceTesterThread thread = new SetInstanceTesterThread(signal, beanTwo);
        thread.setContextClassLoader(new TestClassLoader());

        BeanUtilsBean.setInstance(beanOne);
        assertEquals("Start thread gets right instance", beanOne, BeanUtilsBean.getInstance());
    }

    public void testBeanUtilsBeanSetInstance_2_oe() throws Exception {

        class SetInstanceTesterThread extends Thread {

            private final Signal signal;
            private final BeanUtilsBean bean;

            SetInstanceTesterThread(final Signal signal, final BeanUtilsBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                BeanUtilsBean.setInstance(bean);
                signal.setSignal(21);
                signal.setBean(BeanUtilsBean.getInstance());
            }

            @Override
            public String toString() {
                return "SetInstanceTesterThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final BeanUtilsBean beanOne = new BeanUtilsBean();
        final BeanUtilsBean beanTwo = new BeanUtilsBean();

        final SetInstanceTesterThread thread = new SetInstanceTesterThread(signal, beanTwo);
        thread.setContextClassLoader(new TestClassLoader());

        BeanUtilsBean.setInstance(beanOne);
        // removed other assertion

        thread.start();
        thread.join();

        assertEquals("Signal not set by test thread", 21, signal.getSignal());
    }

    public void testBeanUtilsBeanSetInstance_3_oe() throws Exception {

        class SetInstanceTesterThread extends Thread {

            private final Signal signal;
            private final BeanUtilsBean bean;

            SetInstanceTesterThread(final Signal signal, final BeanUtilsBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                BeanUtilsBean.setInstance(bean);
                signal.setSignal(21);
                signal.setBean(BeanUtilsBean.getInstance());
            }

            @Override
            public String toString() {
                return "SetInstanceTesterThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final BeanUtilsBean beanOne = new BeanUtilsBean();
        final BeanUtilsBean beanTwo = new BeanUtilsBean();

        final SetInstanceTesterThread thread = new SetInstanceTesterThread(signal, beanTwo);
        thread.setContextClassLoader(new TestClassLoader());

        BeanUtilsBean.setInstance(beanOne);
        // removed other assertion

        thread.start();
        thread.join();

        // removed other assertion
        assertEquals("Second thread preserves value", beanOne, BeanUtilsBean.getInstance());
    }

    public void testBeanUtilsBeanSetInstance_4_oe() throws Exception {

        class SetInstanceTesterThread extends Thread {

            private final Signal signal;
            private final BeanUtilsBean bean;

            SetInstanceTesterThread(final Signal signal, final BeanUtilsBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                BeanUtilsBean.setInstance(bean);
                signal.setSignal(21);
                signal.setBean(BeanUtilsBean.getInstance());
            }

            @Override
            public String toString() {
                return "SetInstanceTesterThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final BeanUtilsBean beanOne = new BeanUtilsBean();
        final BeanUtilsBean beanTwo = new BeanUtilsBean();

        final SetInstanceTesterThread thread = new SetInstanceTesterThread(signal, beanTwo);
        thread.setContextClassLoader(new TestClassLoader());

        BeanUtilsBean.setInstance(beanOne);
        // removed other assertion

        thread.start();
        thread.join();

        // removed other assertion
        // removed other assertion
        assertEquals("Second thread gets value it set", beanTwo, signal.getBean());
    }

    public void testContextClassLoaderUnset_1_oe() throws Exception {
        final BeanUtilsBean beanOne = new BeanUtilsBean();
        final ContextClassLoaderLocal<BeanUtilsBean> ccll = new ContextClassLoaderLocal<BeanUtilsBean>();
        ccll.set(beanOne);
        assertEquals("Start thread gets right instance", beanOne, ccll.get());
    }

    public void testContextClassLoaderUnset_2_oe() throws Exception {
        final BeanUtilsBean beanOne = new BeanUtilsBean();
        final ContextClassLoaderLocal<BeanUtilsBean> ccll = new ContextClassLoaderLocal<BeanUtilsBean>();
        ccll.set(beanOne);
        // removed other assertion
        ccll.unset();
        assertTrue("Unset works", !beanOne.equals(ccll.get()));
    }

}

