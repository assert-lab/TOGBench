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
package org.apache.commons.beanutils.memoryleaktests;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.MappedPropertyDescriptor;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.commons.beanutils.WrapDynaClass;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.locale.LocaleBeanUtilsBean;
import org.apache.commons.beanutils.locale.LocaleConvertUtils;
import org.apache.commons.beanutils.locale.converters.IntegerLocaleConverter;
import org.junit.Assume;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests BeanUtils memory leaks.
 *
 * See https://issues.apache.org/jira/browse/BEANUTILS-291
 *
 * @version $Id$
 */
public class MemoryLeakTestCase_OE25Dev {

    /**
     * Tests that PropertyUtilsBean's descriptorsCache doesn't cause a memory leak.
     */

    /**
     * Tests that PropertyUtilsBean's mappedDescriptorsCache doesn't cause a memory leak.
     */

    /**
     * Tests that MappedPropertyDescriptor can re-create the Method reference after it
     * has been garbage collected.
     */

    /**
     * Tests that MappedPropertyDescriptor can re-create the Method reference after it
     * has been garbage collected.
     */

    /**
     * Tests that MethodUtils's cache doesn't cause a memory leak.
     */

    /**
     * Tests that WrapDynaClass's dynaClasses doesn't cause a memory leak.
     */

    /**
     * Tests that ConvertUtilsBean's converters doesn't cause a memory leak.
     */

    /**
     * Tests that LocaleConvertUtilsBean's converters doesn't cause a memory leak.
     */

    /**
     * Clears all the BeanUtils Caches manually.
     *
     * This is probably overkill, but since we're dealing with static caches
     * it seems sensible to ensure that all test cases start with a clean sheet.
     */
    private void clearAllBeanUtilsCaches() {

        // Clear BeanUtilsBean's PropertyUtilsBean descriptor caches
        BeanUtilsBean.getInstance().getPropertyUtils().clearDescriptors();

        // Clear LocaleBeanUtilsBean's PropertyUtilsBean descriptor caches
        LocaleBeanUtilsBean.getLocaleBeanUtilsInstance().getPropertyUtils().clearDescriptors();

        // Clear MethodUtils's method cache
        MethodUtils.clearCache();

        // Clear WrapDynaClass cache
        WrapDynaClass.clear();

        // replace the existing BeanUtilsBean instance for the current class loader with a new, clean instance
        BeanUtilsBean.setInstance(new BeanUtilsBean());

        // replace the existing LocaleBeanUtilsBean instance for the current class loader with a new, clean instance
        LocaleBeanUtilsBean.setInstance(new LocaleBeanUtilsBean());
    }

    /**
     * Tries to force the garbage collector to run by filling up memory and calling System.gc().
     */
    private void forceGarbageCollection() throws Exception {
        // Fill up memory
        final SoftReference<Object> ref = new SoftReference<Object>(new Object());
        int count = 0;
        while(ref.get() != null && count++ < 5) {
            java.util.ArrayList<String> list = new java.util.ArrayList<String>();
            try {
                long i = 0;
                while (true && ref.get() != null) {
                    list.add("A Big String A Big String A Big String A Big String A Big String A Big String A Big String A Big String A Big String A Big String " + (i++));
                }
                System.out.println("Count(1) " + count + " : " + getMemoryStats());
            } catch (final OutOfMemoryError ignored) {
                // Cannot do anything here
            }
            // Trying to debug Continuum test fail: try calling GC before releasing the memory
            System.gc();
            list.clear();
            list = null;
            System.gc();
            System.out.println("After GC2: " + getMemoryStats()+ " Count " + count);
            Thread.sleep(1000);
        }

        final boolean isNotNull = ref.get() != null;
        System.out.println("Count " + count+ " " + isNotNull); // debug for Continuum failure
        String message = "Your JVM is not releasing SoftReference, try running the testcase with less memory (-Xmx)";
        Assume.assumeFalse(message, isNotNull);
    }

    /**
     * Creates a new class loader instance.
     */
    private static URLClassLoader newClassLoader() throws MalformedURLException {

        final String dataFilePath = MemoryLeakTestCase_OE25Dev.class.getResource("pojotests").getFile();
        //System.out.println("dataFilePath: " + dataFilePath);
        final String location = "file://" + dataFilePath.substring(0,dataFilePath.length()-"org.apache.commons.beanutils.memoryleaktests.pojotests".length());
        //System.out.println("location: " + location);

        final StringBuilder newString = new StringBuilder();
        for (int i=0;i<location.length();i++) {
            if (location.charAt(i)=='\\') {
                newString.append("/");
            } else {
                newString.append(location.charAt(i));
            }
        }
        final String classLocation = newString.toString();
        //System.out.println("classlocation: " + classLocation);

        final URLClassLoader theLoader = URLClassLoader.newInstance(new URL[]{new URL(classLocation)},null);
        return theLoader;
    }

    /**
     * Produces a profiler report about where the leaks are.
     *
     * This requires JBoss's profiler be installed, see:
     *     http://labs.jboss.com/jbossprofiler/
     *
     * @param className The name of the class to profile
     */
    private void profilerLeakReport(final String test, final String className) {
       /*
        * If you want a report about where the leaks are... uncomment this,
        * add jboss-profiler.jvmti.jar and jboss-commons.jar (for org.jboss.loggin).
        * You will then have a report for where the references are.
        System.out.println(" ----------------" + test + " START ----------------");
        org.jboss.profiler.jvmti.JVMTIInterface jvmti = new org.jboss.profiler.jvmti.JVMTIInterface();
        System.out.println(jvmti.exploreClassReferences(className, 8, true, true, true, false, false));
        System.out.println(" ----------------" + test + " END ------------------");
        */
    }

    /**
     * Gets the total, free, used memory stats.
     * @return the total, free, used memory stats
     */
    private String getMemoryStats() {
        final java.text.DecimalFormat fmt = new java.text.DecimalFormat("#,##0");
        final Runtime runtime = Runtime.getRuntime();
        final long free = runtime.freeMemory() / 1024;
        final long total = runtime.totalMemory() / 1024;
        final long used = total - free;
        return "MEMORY - Total: " + fmt.format(total) + "k " + "Used: "
                + fmt.format(used) + "k " + "Free: "
                + fmt.format(free) + "k";
    }

    @Test
    public void testPropertyUtilsBean_descriptorsCache_memoryLeak_1_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        assertNotNull("ClassLoader is null", loader);
    }

    @Test
    public void testPropertyUtilsBean_descriptorsCache_memoryLeak_2_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        assertNotNull("BeanClass is null", beanClass);
    }

    @Test
    public void testPropertyUtilsBean_descriptorsCache_memoryLeak_3_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        assertNotSame("ClassLoaders should be different..", getClass().getClassLoader(), beanClass.getClassLoader());
    }

    @Test
    public void testPropertyUtilsBean_descriptorsCache_memoryLeak_4_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("BeanClass ClassLoader incorrect", beanClass.getClassLoader(), loader);
    }

    @Test
    public void testPropertyUtilsBean_descriptorsCache_memoryLeak_5_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following line, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and PropertyUtils is holding a reference
        assertEquals("initialValue", PropertyUtils.getProperty(bean, "name"));
    }

    @Test
    public void testPropertyUtilsBean_descriptorsCache_memoryLeak_6_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following line, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and PropertyUtils is holding a reference
        // removed other assertion

        // this should make the reference go away.
        loader    = null;
        beanClass = null;
        bean      = null;

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        if (someRef.get() != null) {
            profilerLeakReport("PropertyUtilsBean descriptorsCache", className);
        }

        // if everything is fine, this will be null
        assertNull("PropertyUtilsBean is holding a reference to the classLoader", someRef.get());
    }

    @Test
    public void testPropertyUtilsBean_mappedDescriptorsCache_memoryLeak_1_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        assertNotNull("ClassLoader is null", loader);
    }

    @Test
    public void testPropertyUtilsBean_mappedDescriptorsCache_memoryLeak_2_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        assertNotNull("BeanClass is null", beanClass);
    }

    @Test
    public void testPropertyUtilsBean_mappedDescriptorsCache_memoryLeak_3_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        assertNotSame("ClassLoaders should be different..", getClass().getClassLoader(), beanClass.getClassLoader());
    }

    @Test
    public void testPropertyUtilsBean_mappedDescriptorsCache_memoryLeak_4_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("BeanClass ClassLoader incorrect", beanClass.getClassLoader(), loader);
    }

    @Test
    public void testPropertyUtilsBean_mappedDescriptorsCache_memoryLeak_5_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following three lines, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and PropertyUtils is holding a reference
        assertEquals("Second Value", PropertyUtils.getProperty(bean, "mappedProperty(Second Key)"));
    }

    @Test
    public void testPropertyUtilsBean_mappedDescriptorsCache_memoryLeak_6_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following three lines, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and PropertyUtils is holding a reference
        // removed other assertion
        PropertyUtils.setProperty(bean, "mappedProperty(Second Key)", "New Second Value");
        assertEquals("New Second Value", PropertyUtils.getProperty(bean, "mappedProperty(Second Key)"));
    }

    @Test
    public void testPropertyUtilsBean_mappedDescriptorsCache_memoryLeak_7_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following three lines, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and PropertyUtils is holding a reference
        // removed other assertion
        PropertyUtils.setProperty(bean, "mappedProperty(Second Key)", "New Second Value");
        // removed other assertion

        // this should make the reference go away.
        loader = null;
        beanClass = null;
        bean = null;

        // PropertyUtilsBean uses the MethodUtils's method cache for mapped properties.
        // Uncomment the following line to check this is not just a repeat of that memory leak.
        // MethodUtils.clearCache();

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        if (someRef.get() != null) {
            profilerLeakReport("PropertyUtilsBean mappedDescriptorsCache", className);
        }

        // if everything is fine, this will be null
        assertNull("PropertyUtilsBean is holding a reference to the classLoader", someRef.get());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_1_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        assertNotNull("ClassLoader is null", loader);
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_2_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        assertNotNull("BeanClass is null", beanClass);
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_3_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        assertNotNull("Bean is null", bean);
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_4_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame("ClassLoaders should be different..", getClass().getClassLoader(), beanClass.getClassLoader());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_5_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("BeanClass ClassLoader incorrect", beanClass.getClassLoader(), loader);
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_6_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        assertNotNull("1-Read Method null", descriptor.getMappedReadMethod());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_7_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        assertNotNull("1-Write Method null", descriptor.getMappedWriteMethod());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_8_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        assertEquals("1-Read Method name", "getMappedProperty", descriptor.getMappedReadMethod().getName());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_9_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1-Read Write name", "setMappedProperty", descriptor.getMappedWriteMethod().getName());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_10_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        // The aim of this test is to check the functinality in MappedPropertyDescriptor which
        // re-creates the Method references after they have been garbage collected. However theres no
        // way of knowing the method references were garbage collected and that code was run, except by
        // un-commeting the System.out statement in MappedPropertyDescriptor's MappedMethodReference's
        // get() method.

        assertNotNull("1-Read Method null", descriptor.getMappedReadMethod());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_11_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        // The aim of this test is to check the functinality in MappedPropertyDescriptor which
        // re-creates the Method references after they have been garbage collected. However theres no
        // way of knowing the method references were garbage collected and that code was run, except by
        // un-commeting the System.out statement in MappedPropertyDescriptor's MappedMethodReference's
        // get() method.

        // removed other assertion
        assertNotNull("1-Write Method null", descriptor.getMappedWriteMethod());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_12_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        // The aim of this test is to check the functinality in MappedPropertyDescriptor which
        // re-creates the Method references after they have been garbage collected. However theres no
        // way of knowing the method references were garbage collected and that code was run, except by
        // un-commeting the System.out statement in MappedPropertyDescriptor's MappedMethodReference's
        // get() method.

        // removed other assertion
        // removed other assertion
        assertEquals("1-Read Method name", "getMappedProperty", descriptor.getMappedReadMethod().getName());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference1_13_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        final ClassLoader loader = newClassLoader();
        final Class<?> beanClass    = loader.loadClass(className);
        final Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        // The aim of this test is to check the functinality in MappedPropertyDescriptor which
        // re-creates the Method references after they have been garbage collected. However theres no
        // way of knowing the method references were garbage collected and that code was run, except by
        // un-commeting the System.out statement in MappedPropertyDescriptor's MappedMethodReference's
        // get() method.

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1-Read Write name", "setMappedProperty", descriptor.getMappedWriteMethod().getName());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_1_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        assertNotNull("ClassLoader is null", loader);
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_2_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        assertNotNull("BeanClass is null", beanClass);
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_3_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        assertNotNull("Bean is null", bean);
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_4_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame("ClassLoaders should be different..", getClass().getClassLoader(), beanClass.getClassLoader());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_5_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("BeanClass ClassLoader incorrect", beanClass.getClassLoader(), loader);
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_6_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        assertNotNull("1-Read Method null", descriptor.getMappedReadMethod());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_7_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        assertNotNull("1-Write Method null", descriptor.getMappedWriteMethod());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_8_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        assertEquals("1-Read Method name", "getMappedProperty", descriptor.getMappedReadMethod().getName());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_9_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1-Read Write name", "setMappedProperty", descriptor.getMappedWriteMethod().getName());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_10_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this should make the reference go away.
        loader = null;
        beanClass = null;
        bean = null;

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        // The aim of this test is to check the functinality in MappedPropertyDescriptor which
        // re-creates the Method references after they have been garbage collected. However theres no
        // way of knowing the method references were garbage collected and that code was run, except by
        // un-commeting the System.out statement in MappedPropertyDescriptor's MappedMethodReference's
        // get() method.

        assertNotNull("1-Read Method null", descriptor.getMappedReadMethod());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_11_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this should make the reference go away.
        loader = null;
        beanClass = null;
        bean = null;

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        // The aim of this test is to check the functinality in MappedPropertyDescriptor which
        // re-creates the Method references after they have been garbage collected. However theres no
        // way of knowing the method references were garbage collected and that code was run, except by
        // un-commeting the System.out statement in MappedPropertyDescriptor's MappedMethodReference's
        // get() method.

        // removed other assertion
        assertNotNull("1-Write Method null", descriptor.getMappedWriteMethod());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_12_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this should make the reference go away.
        loader = null;
        beanClass = null;
        bean = null;

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        // The aim of this test is to check the functinality in MappedPropertyDescriptor which
        // re-creates the Method references after they have been garbage collected. However theres no
        // way of knowing the method references were garbage collected and that code was run, except by
        // un-commeting the System.out statement in MappedPropertyDescriptor's MappedMethodReference's
        // get() method.

        // removed other assertion
        // removed other assertion
        assertEquals("1-Read Method name", "getMappedProperty", descriptor.getMappedReadMethod().getName());
    }

    @Test
    public void testMappedPropertyDescriptor_MappedMethodReference2_13_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomeMappedPojo";
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MappedPropertyDescriptor descriptor = new MappedPropertyDescriptor("mappedProperty", beanClass);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this should make the reference go away.
        loader = null;
        beanClass = null;
        bean = null;

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        // The aim of this test is to check the functinality in MappedPropertyDescriptor which
        // re-creates the Method references after they have been garbage collected. However theres no
        // way of knowing the method references were garbage collected and that code was run, except by
        // un-commeting the System.out statement in MappedPropertyDescriptor's MappedMethodReference's
        // get() method.

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1-Read Write name", "setMappedProperty", descriptor.getMappedWriteMethod().getName());
    }

    @Test
    public void testMethodUtils_cache_memoryLeak_1_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        assertNotNull("ClassLoader is null", loader);
    }

    @Test
    public void testMethodUtils_cache_memoryLeak_2_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        assertNotNull("BeanClass is null", beanClass);
    }

    @Test
    public void testMethodUtils_cache_memoryLeak_3_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        assertNotSame("ClassLoaders should be different..", getClass().getClassLoader(), beanClass.getClassLoader());
    }

    @Test
    public void testMethodUtils_cache_memoryLeak_4_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("BeanClass ClassLoader incorrect", beanClass.getClassLoader(), loader);
    }

    @Test
    public void testMethodUtils_cache_memoryLeak_5_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following line, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and MethodUtils is holding a reference
        assertEquals("initialValue", MethodUtils.invokeExactMethod(bean, "getName", new Object[0]));
    }

    @Test
    public void testMethodUtils_cache_memoryLeak_6_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following line, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and MethodUtils is holding a reference
        // removed other assertion

        // this should make the reference go away.
        loader    = null;
        beanClass = null;
        bean      = null;

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        if (someRef.get() != null) {
            profilerLeakReport("MethodUtils cache", className);
        }

        // if everything is fine, this will be null
        assertNull("MethodUtils is holding a reference to the classLoader", someRef.get());
    }

    @Test
    public void testWrapDynaClass_dynaClasses_memoryLeak_1_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        WrapDynaBean wrapDynaBean = new WrapDynaBean(bean);
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        assertNotNull("ClassLoader is null", loader);
    }

    @Test
    public void testWrapDynaClass_dynaClasses_memoryLeak_2_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        WrapDynaBean wrapDynaBean = new WrapDynaBean(bean);
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        assertNotNull("BeanClass is null", beanClass);
    }

    @Test
    public void testWrapDynaClass_dynaClasses_memoryLeak_3_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        WrapDynaBean wrapDynaBean = new WrapDynaBean(bean);
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        assertNotSame("ClassLoaders should be different..", getClass().getClassLoader(), beanClass.getClassLoader());
    }

    @Test
    public void testWrapDynaClass_dynaClasses_memoryLeak_4_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        WrapDynaBean wrapDynaBean = new WrapDynaBean(bean);
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("BeanClass ClassLoader incorrect", beanClass.getClassLoader(), loader);
    }

    @Test
    public void testWrapDynaClass_dynaClasses_memoryLeak_5_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        WrapDynaBean wrapDynaBean = new WrapDynaBean(bean);
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following line, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and WrapDynaClass is holding a reference
        assertEquals("initialValue", wrapDynaBean.get("name"));
    }

    @Test
    public void testWrapDynaClass_dynaClasses_memoryLeak_6_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.SomePojo";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        WrapDynaBean wrapDynaBean = new WrapDynaBean(bean);
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following line, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and WrapDynaClass is holding a reference
        // removed other assertion

        // this should make the reference go away.
        loader       = null;
        beanClass    = null;
        bean         = null;
        wrapDynaBean = null;

        // Wrap Dyna Class uses the PropertyUtilsBean's decriptor caches.
        // Uncomment the following line to check this is not just a repeat of that memory leak.
        // BeanUtilsBean.getInstance().getPropertyUtils().clearDescriptors();

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        if (someRef.get() != null) {
            profilerLeakReport("WrapDynaClass dynaClasses", className);
        }

        // if everything is fine, this will be null
        assertNull("WrapDynaClass is holding a reference to the classLoader", someRef.get());
    }

    @Test
    public void testConvertUtilsBean_converters_memoryLeak_1_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        assertNotNull("ClassLoader is null", loader);
    }

    @Test
    public void testConvertUtilsBean_converters_memoryLeak_2_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        assertNotNull("BeanClass is null", beanClass);
    }

    @Test
    public void testConvertUtilsBean_converters_memoryLeak_3_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        assertNotSame("ClassLoaders should be different..", getClass().getClassLoader(), beanClass.getClassLoader());
    }

    @Test
    public void testConvertUtilsBean_converters_memoryLeak_4_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("BeanClass ClassLoader incorrect", beanClass.getClassLoader(), loader);
    }

    @Test
    public void testConvertUtilsBean_converters_memoryLeak_5_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following two lines, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and ConvertUtilsBean is holding a reference
        ConvertUtils.register(new IntegerConverter(), beanClass);
        assertEquals("12345", ConvertUtils.convert(bean, String.class));
    }

    @Test
    public void testConvertUtilsBean_converters_memoryLeak_6_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following two lines, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and ConvertUtilsBean is holding a reference
        ConvertUtils.register(new IntegerConverter(), beanClass);
        // removed other assertion

        // this should make the reference go away.
        loader    = null;
        beanClass = null;
        bean      = null;

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        if (someRef.get() != null) {
            profilerLeakReport("ConvertUtilsBean converters", className);
        }

        // if everything is fine, this will be null
        assertNull("ConvertUtilsBean is holding a reference to the classLoader", someRef.get());
    }

    @Test
    public void testLocaleConvertUtilsBean_converters_memoryLeak_1_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        assertNotNull("ClassLoader is null", loader);
    }

    @Test
    public void testLocaleConvertUtilsBean_converters_memoryLeak_2_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        assertNotNull("BeanClass is null", beanClass);
    }

    @Test
    public void testLocaleConvertUtilsBean_converters_memoryLeak_3_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        assertNotSame("ClassLoaders should be different..", getClass().getClassLoader(), beanClass.getClassLoader());
    }

    @Test
    public void testLocaleConvertUtilsBean_converters_memoryLeak_4_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("BeanClass ClassLoader incorrect", beanClass.getClassLoader(), loader);
    }

    @Test
    public void testLocaleConvertUtilsBean_converters_memoryLeak_5_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following two lines, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and LocaleConvertUtilsBean is holding a reference
        LocaleConvertUtils.register(new IntegerLocaleConverter(Locale.US, false), beanClass, Locale.US);
        assertEquals(new Integer(12345), LocaleConvertUtils.convert(bean.toString(), Integer.class, Locale.US, "#,###"));
    }

    @Test
    public void testLocaleConvertUtilsBean_converters_memoryLeak_6_oe() throws Exception {

        // Clear All BeanUtils caches before the test
        clearAllBeanUtilsCaches();

        final String className = "org.apache.commons.beanutils.memoryleaktests.pojotests.CustomInteger";

        // The classLoader will go away only when these following variables are released
        ClassLoader loader = newClassLoader();
        Class<?> beanClass    = loader.loadClass(className);
        Object bean        = beanClass.newInstance();
        // -----------------------------------------------------------------------------

        final WeakReference<ClassLoader> someRef = new WeakReference<ClassLoader>(loader);

        // Sanity checks only
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // if you comment the following two lines, the testcase will work, and the ClassLoader will be released.
        // That proves that nothing is wrong with the test, and LocaleConvertUtilsBean is holding a reference
        LocaleConvertUtils.register(new IntegerLocaleConverter(Locale.US, false), beanClass, Locale.US);
        // removed other assertion

        // this should make the reference go away.
        loader    = null;
        beanClass = null;
        bean      = null;

        forceGarbageCollection(); /* Try to force the garbage collector to run by filling up memory */

        if (someRef.get() != null) {
            profilerLeakReport("LocaleConvertUtilsBean converters", className);
        }

        // if everything is fine, this will be null
        assertNull("LocaleConvertUtilsBean is holding a reference to the classLoader", someRef.get());
    }

}
