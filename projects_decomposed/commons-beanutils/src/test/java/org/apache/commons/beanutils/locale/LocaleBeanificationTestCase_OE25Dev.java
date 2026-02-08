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

package org.apache.commons.beanutils.locale;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.BeanUtilsTestCase;
import org.apache.commons.beanutils.ContextClassLoaderLocal;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PrimitiveBean;
import org.apache.commons.beanutils.locale.converters.LongLocaleConverter;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Test Case for changes made during LocaleBeanutils Beanification.
 * This is basically a cut-and-correct version of the beanutils beanifications tests.
 * </p>
 *
 * @version $Id$
 */

public class LocaleBeanificationTestCase_OE25Dev extends TestCase {

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
    public LocaleBeanificationTestCase_OE25Dev(final String name) {
        super(name);
    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() {

        LocaleConvertUtils.deregister();

    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(LocaleBeanificationTestCase_OE25Dev.class));
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
    public void testMemoryTestMethodology() throws Exception {
        // test methodology
        // many thanks to Juozas Baliuka for suggesting this method
        ClassLoader loader = new ClassLoader(this.getClass().getClassLoader()) {};
        final WeakReference<ClassLoader> reference = new  WeakReference<ClassLoader>(loader);
        Class<?> myClass = loader.loadClass("org.apache.commons.beanutils.BetaBean");

        assertNotNull("Weak reference released early", reference.get());

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
            if( reference.get() == null ) {
                break;

            } else {
                // create garbage:
                final byte[] b =  new byte[bytz];
                bytz = bytz * 2;
            }
        }
    }

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

    /**
     * Test registering a locale-aware converter with the standard ConvertUtils.
     */
    public void testLocaleAwareConverterInConvertUtils() throws Exception {
        try {
            // first use the default non-locale-aware converter
            try {
                final Long data = (Long) ConvertUtils.convert("777", Long.class);
                assertEquals("Standard format long converted ok", 777, data.longValue());
            }
            catch(final ConversionException ex) {
                fail("Unable to convert non-locale-aware number 777");
            }

            // now try default converter with special delimiters
            try {
                // This conversion will cause an error. But the default
                // Long converter is set up to return a default value of
                // zero on error.
                final Long data = (Long) ConvertUtils.convert("1.000.000", Long.class);
                assertEquals("Standard format behaved as expected", 0, data.longValue());
            }
            catch(final ConversionException ex) {
                fail("Unexpected exception from standard Long converter.");
            }

            // Now try using a locale-aware converter together with
            // locale-specific input string. Note that in the german locale,
            // large numbers can be split up into groups of three digits
            // using a dot character (and comma is the decimal-point indicator).
            try {

                final Locale germanLocale = Locale.GERMAN;
                final LongLocaleConverter longLocaleConverter = new LongLocaleConverter(germanLocale);
                ConvertUtils.register(longLocaleConverter, Long.class);

                final Long data = (Long) ConvertUtils.convert("1.000.000", Long.class);
                assertEquals("German-format long converted ok", 1000000, data.longValue());
            } catch(final ConversionException ex) {
                fail("Unable to convert german-format number");
            }
        } finally {
            ConvertUtils.deregister();
        }
    }

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
        private LocaleBeanUtilsBean bean;
        private LocaleConvertUtilsBean convertUtils;
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

        public LocaleBeanUtilsBean getBean() {
            return bean;
        }

        public void setBean(final LocaleBeanUtilsBean bean) {
            this.bean = bean;
        }

        public LocaleConvertUtilsBean getConvertUtils() {
            return convertUtils;
        }

        public void setConvertUtils(final LocaleConvertUtilsBean convertUtils) {
            this.convertUtils = convertUtils;
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
        final Map<TestClassLoader, Integer> map = new WeakHashMap<TestClassLoader, Integer>();
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
        final Map<TestClassLoader, Integer> map = new WeakHashMap<TestClassLoader, Integer>();
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
        final Map<TestClassLoader, Integer> map = new WeakHashMap<TestClassLoader, Integer>();
        map.put(loader, test);

        // removed other assertion
        // removed other assertion
        assertNotNull("Weak reference released early (2)", testReference.get());
    }

    public void testMemoryLeak_1_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final WeakReference<TestClassLoader> loaderReference = new  WeakReference<TestClassLoader>(loader);
        LocaleBeanUtilsBean.getLocaleBeanUtilsInstance();

        class GetBeanUtilsBeanThread extends Thread {

            LocaleBeanUtilsBean beanUtils;
            LocaleConvertUtilsBean convertUtils;

            GetBeanUtilsBeanThread() {}

            @Override
            public void run() {
                beanUtils = LocaleBeanUtilsBean.getLocaleBeanUtilsInstance();
                convertUtils = LocaleConvertUtilsBean.getInstance();
                // XXX Log keeps a reference around!
                LogFactory.releaseAll();
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }


        GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread();
        final WeakReference<GetBeanUtilsBeanThread> threadWeakReference = new WeakReference<GetBeanUtilsBeanThread>(thread);
        thread.setContextClassLoader(loader);

        thread.start();
        thread.join();

        final WeakReference<LocaleBeanUtilsBean> beanUtilsReference = new WeakReference<LocaleBeanUtilsBean>(thread.beanUtils);
        final WeakReference<LocaleConvertUtilsBean> convertUtilsReference = new WeakReference<LocaleConvertUtilsBean>(thread.convertUtils);

        assertNotNull("Weak reference released early (1)", loaderReference.get());
    }

    public void testMemoryLeak_2_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            System.out.println("WARNING: CANNOT TEST MEMORY LEAK ON PRE1.4 JVM");
            return;
        }

        // many thanks to Juozas Baliuka for suggesting this methodology
        TestClassLoader loader = new TestClassLoader();
        final WeakReference<TestClassLoader> loaderReference = new  WeakReference<TestClassLoader>(loader);
        LocaleBeanUtilsBean.getLocaleBeanUtilsInstance();

        class GetBeanUtilsBeanThread extends Thread {

            LocaleBeanUtilsBean beanUtils;
            LocaleConvertUtilsBean convertUtils;

            GetBeanUtilsBeanThread() {}

            @Override
            public void run() {
                beanUtils = LocaleBeanUtilsBean.getLocaleBeanUtilsInstance();
                convertUtils = LocaleConvertUtilsBean.getInstance();
                // XXX Log keeps a reference around!
                LogFactory.releaseAll();
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }


        GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread();
        final WeakReference<GetBeanUtilsBeanThread> threadWeakReference = new WeakReference<GetBeanUtilsBeanThread>(thread);
        thread.setContextClassLoader(loader);

        thread.start();
        thread.join();

        final WeakReference<LocaleBeanUtilsBean> beanUtilsReference = new WeakReference<LocaleBeanUtilsBean>(thread.beanUtils);
        final WeakReference<LocaleConvertUtilsBean> convertUtilsReference = new WeakReference<LocaleConvertUtilsBean>(thread.convertUtils);

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
        final WeakReference<TestClassLoader> loaderReference = new  WeakReference<TestClassLoader>(loader);
        LocaleBeanUtilsBean.getLocaleBeanUtilsInstance();

        class GetBeanUtilsBeanThread extends Thread {

            LocaleBeanUtilsBean beanUtils;
            LocaleConvertUtilsBean convertUtils;

            GetBeanUtilsBeanThread() {}

            @Override
            public void run() {
                beanUtils = LocaleBeanUtilsBean.getLocaleBeanUtilsInstance();
                convertUtils = LocaleConvertUtilsBean.getInstance();
                // XXX Log keeps a reference around!
                LogFactory.releaseAll();
            }

            @Override
            public String toString() {
                return "GetBeanUtilsBeanThread";
            }
        }


        GetBeanUtilsBeanThread thread = new GetBeanUtilsBeanThread();
        final WeakReference<GetBeanUtilsBeanThread> threadWeakReference = new WeakReference<GetBeanUtilsBeanThread>(thread);
        thread.setContextClassLoader(loader);

        thread.start();
        thread.join();

        final WeakReference<LocaleBeanUtilsBean> beanUtilsReference = new WeakReference<LocaleBeanUtilsBean>(thread.beanUtils);
        final WeakReference<LocaleConvertUtilsBean> convertUtilsReference = new WeakReference<LocaleConvertUtilsBean>(thread.convertUtils);

        // removed other assertion
        // removed other assertion
        assertNotNull("Weak reference released early (4)", convertUtilsReference.get());
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
                signal.setBean(LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
                signal.setConvertUtils(LocaleConvertUtilsBean.getInstance());
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
                signal.setBean(LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
                signal.setConvertUtils(LocaleConvertUtilsBean.getInstance());
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
        assertTrue("Different LocaleBeanUtilsBean instances per context classloader",LocaleBeanUtilsBean.getInstance()!= signal.getBean());
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
                signal.setBean(LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
                signal.setConvertUtils(LocaleConvertUtilsBean.getInstance());
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
        assertTrue("Different LocaleConvertUtilsBean instances per context classloader",LocaleConvertUtilsBean.getInstance()!= signal.getConvertUtils());
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
        ccll.set(1776);
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
        ccll.set(1776);
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
        ccll.set(1776);
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
        ccll.set(1776);
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
                    LocaleConvertUtils.register(new LocaleConverter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                            public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                                }, Integer.TYPE, Locale.getDefault());
                    LocaleBeanUtils.setProperty(bean, "int", "1");
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
        LocaleBeanUtils.setProperty(bean, "int", new Integer(1));
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
                    LocaleConvertUtils.register(new LocaleConverter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                            public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                                }, Integer.TYPE, Locale.getDefault());
                    LocaleBeanUtils.setProperty(bean, "int", "1");
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
        LocaleBeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        LocaleConvertUtils.register(new LocaleConverter() {
                                public <T> T convert(final Class<T> type, final Object value) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(5);
                                }
                                public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(5);
                                }
                                    }, Integer.TYPE, Locale.getDefault());
        LocaleBeanUtils.setProperty(bean, "int", "1");
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
                    LocaleConvertUtils.register(new LocaleConverter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                            public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                                }, Integer.TYPE, Locale.getDefault());
                    LocaleBeanUtils.setProperty(bean, "int", "1");
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
        LocaleBeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        LocaleConvertUtils.register(new LocaleConverter() {
                                public <T> T convert(final Class<T> type, final Object value) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(5);
                                }
                                public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(5);
                                }
                                    }, Integer.TYPE, Locale.getDefault());
        LocaleBeanUtils.setProperty(bean, "int", "1");
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
                    LocaleConvertUtils.register(new LocaleConverter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                            public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                                }, Integer.TYPE, Locale.getDefault());
                    LocaleBeanUtils.setProperty(bean, "int", "1");
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
        LocaleBeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        LocaleConvertUtils.register(new LocaleConverter() {
                                public <T> T convert(final Class<T> type, final Object value) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(5);
                                }
                                public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(5);
                                }
                                    }, Integer.TYPE, Locale.getDefault());
        LocaleBeanUtils.setProperty(bean, "int", "1");
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
                    LocaleConvertUtils.register(new LocaleConverter() {
                                            public <T> T convert(final Class<T> type, final Object value) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                            public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                                return ConvertUtils.primitiveToWrapper(type).cast(9);
                                            }
                                                }, Integer.TYPE, Locale.getDefault());
                    LocaleBeanUtils.setProperty(bean, "int", "1");
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
        LocaleBeanUtils.setProperty(bean, "int", new Integer(1));
        // removed other assertion

        LocaleConvertUtils.register(new LocaleConverter() {
                                public <T> T convert(final Class<T> type, final Object value) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(5);
                                }
                                public <T> T convert(final Class<T> type, final Object value, final String pattern) {
                                    return ConvertUtils.primitiveToWrapper(type).cast(5);
                                }
                                    }, Integer.TYPE, Locale.getDefault());
        LocaleBeanUtils.setProperty(bean, "int", "1");
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
            private final LocaleBeanUtilsBean bean;

            SetInstanceTesterThread(final Signal signal, final LocaleBeanUtilsBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                LocaleBeanUtilsBean.setInstance(bean);
                signal.setSignal(21);
                signal.setBean(LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
            }

            @Override
            public String toString() {
                return "SetInstanceTesterThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final LocaleBeanUtilsBean beanOne = new LocaleBeanUtilsBean();
        final LocaleBeanUtilsBean beanTwo = new LocaleBeanUtilsBean();

        final SetInstanceTesterThread thread = new SetInstanceTesterThread(signal, beanTwo);
        thread.setContextClassLoader(new TestClassLoader());

        LocaleBeanUtilsBean.setInstance(beanOne);
        assertEquals("Start thread gets right instance", beanOne, LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
    }

    public void testBeanUtilsBeanSetInstance_2_oe() throws Exception {

        class SetInstanceTesterThread extends Thread {

            private final Signal signal;
            private final LocaleBeanUtilsBean bean;

            SetInstanceTesterThread(final Signal signal, final LocaleBeanUtilsBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                LocaleBeanUtilsBean.setInstance(bean);
                signal.setSignal(21);
                signal.setBean(LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
            }

            @Override
            public String toString() {
                return "SetInstanceTesterThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final LocaleBeanUtilsBean beanOne = new LocaleBeanUtilsBean();
        final LocaleBeanUtilsBean beanTwo = new LocaleBeanUtilsBean();

        final SetInstanceTesterThread thread = new SetInstanceTesterThread(signal, beanTwo);
        thread.setContextClassLoader(new TestClassLoader());

        LocaleBeanUtilsBean.setInstance(beanOne);
        // removed other assertion

        thread.start();
        thread.join();

        assertEquals("Signal not set by test thread", 21, signal.getSignal());
    }

    public void testBeanUtilsBeanSetInstance_3_oe() throws Exception {

        class SetInstanceTesterThread extends Thread {

            private final Signal signal;
            private final LocaleBeanUtilsBean bean;

            SetInstanceTesterThread(final Signal signal, final LocaleBeanUtilsBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                LocaleBeanUtilsBean.setInstance(bean);
                signal.setSignal(21);
                signal.setBean(LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
            }

            @Override
            public String toString() {
                return "SetInstanceTesterThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final LocaleBeanUtilsBean beanOne = new LocaleBeanUtilsBean();
        final LocaleBeanUtilsBean beanTwo = new LocaleBeanUtilsBean();

        final SetInstanceTesterThread thread = new SetInstanceTesterThread(signal, beanTwo);
        thread.setContextClassLoader(new TestClassLoader());

        LocaleBeanUtilsBean.setInstance(beanOne);
        // removed other assertion

        thread.start();
        thread.join();

        // removed other assertion
        assertEquals("Second thread preserves value", beanOne, LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
    }

    public void testBeanUtilsBeanSetInstance_4_oe() throws Exception {

        class SetInstanceTesterThread extends Thread {

            private final Signal signal;
            private final LocaleBeanUtilsBean bean;

            SetInstanceTesterThread(final Signal signal, final LocaleBeanUtilsBean bean) {
                this.signal = signal;
                this.bean = bean;
            }

            @Override
            public void run() {
                LocaleBeanUtilsBean.setInstance(bean);
                signal.setSignal(21);
                signal.setBean(LocaleBeanUtilsBean.getLocaleBeanUtilsInstance());
            }

            @Override
            public String toString() {
                return "SetInstanceTesterThread";
            }
        }

        final Signal signal = new Signal();
        signal.setSignal(1);

        final LocaleBeanUtilsBean beanOne = new LocaleBeanUtilsBean();
        final LocaleBeanUtilsBean beanTwo = new LocaleBeanUtilsBean();

        final SetInstanceTesterThread thread = new SetInstanceTesterThread(signal, beanTwo);
        thread.setContextClassLoader(new TestClassLoader());

        LocaleBeanUtilsBean.setInstance(beanOne);
        // removed other assertion

        thread.start();
        thread.join();

        // removed other assertion
        // removed other assertion
        assertEquals("Second thread gets value it set", beanTwo, signal.getBean());
    }

    public void testContextClassLoaderUnset_1_oe() throws Exception {
        final LocaleBeanUtilsBean beanOne = new LocaleBeanUtilsBean();
        final ContextClassLoaderLocal<LocaleBeanUtilsBean> ccll = new ContextClassLoaderLocal<LocaleBeanUtilsBean>();
        ccll.set(beanOne);
        assertEquals("Start thread gets right instance", beanOne, ccll.get());
    }

    public void testContextClassLoaderUnset_2_oe() throws Exception {
        final LocaleBeanUtilsBean beanOne = new LocaleBeanUtilsBean();
        final ContextClassLoaderLocal<LocaleBeanUtilsBean> ccll = new ContextClassLoaderLocal<LocaleBeanUtilsBean>();
        ccll.set(beanOne);
        // removed other assertion
        ccll.unset();
        assertTrue("Unset works", !beanOne.equals(ccll.get()));
    }

}

