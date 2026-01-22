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
 *
 */

package org.apache.bcel;

import org.apache.bcel.classfile.JavaClass;

public class CounterVisitorTestCase_OE25Dev extends AbstractCounterVisitorTestCase
{
    @Override
    protected JavaClass getTestClass() throws ClassNotFoundException
    {
        return getTestClass(PACKAGE_BASE_NAME+".data.MarkedType");
    }

    public void testAnnotationsCount_1_oe()
    {
        assertEquals("annotationCount", 2, getVisitor().annotationCount);
    }

    public void testAnnotationDefaultCount_1_oe()
    {
        assertEquals("annotationDefaultCount", 0, getVisitor().annotationDefaultCount);
    }

    public void testAnnotationEntryCount_1_oe()
    {
        assertEquals("annotationEntryCount", 2, getVisitor().annotationEntryCount);
    }

    public void testCodeCount_1_oe()
    {
        assertEquals("codeCount", 1, getVisitor().codeCount);
    }

    public void testCodeExceptionCount_1_oe()
    {
        assertEquals("codeExceptionCount", 0, getVisitor().codeExceptionCount);
    }

    public void testConstantClassCount_1_oe()
    {
        assertEquals("constantClassCount", 2, getVisitor().constantClassCount);
    }

    public void testConstantDoubleCount_1_oe()
    {
        assertEquals("constantDoubleCount", 0, getVisitor().constantDoubleCount);
    }

    public void testConstantFieldrefCount_1_oe()
    {
        assertEquals("constantFieldrefCount", 0, getVisitor().constantFieldrefCount);
    }

    public void testConstantFloatCount_1_oe()
    {
        assertEquals("constantFloatCount", 0, getVisitor().constantFloatCount);
    }

    public void testConstantIntegerCount_1_oe()
    {
        assertEquals("constantIntegerCount", 0, getVisitor().constantIntegerCount);
    }

    public void testConstantInterfaceMethodrefCount_1_oe()
    {
        assertEquals("constantInterfaceMethodrefCount", 0, getVisitor().constantInterfaceMethodrefCount);
    }

    public void testConstantLongCount_1_oe()
    {
        assertEquals("constantLongCount", 0, getVisitor().constantLongCount);
    }

    public void testConstantMethodrefCount_1_oe()
    {
        assertEquals("constantMethodrefCount", 1, getVisitor().constantMethodrefCount);
    }

    public void testConstantNameAndTypeCount_1_oe()
    {
        assertEquals("constantNameAndTypeCount", 1, getVisitor().constantNameAndTypeCount);
    }

    public void testConstantPoolCount_1_oe()
    {
        assertEquals("constantPoolCount", 1, getVisitor().constantPoolCount);
    }

    public void testConstantStringCount_1_oe()
    {
        assertEquals("constantStringCount", 0, getVisitor().constantStringCount);
    }

    public void testConstantValueCount_1_oe()
    {
        assertEquals("constantValueCount", 0, getVisitor().constantValueCount);
    }

    public void testDeprecatedCount_1_oe()
    {
        assertEquals("deprecatedCount", 0, getVisitor().deprecatedCount);
    }

    public void testEnclosingMethodCount_1_oe()
    {
        assertEquals("enclosingMethodCount", 0, getVisitor().enclosingMethodCount);
    }

    public void testExceptionTableCount_1_oe()
    {
        assertEquals("exceptionTableCount", 0, getVisitor().exceptionTableCount);
    }

    public void testFieldCount_1_oe()
    {
        assertEquals("fieldCount", 0, getVisitor().fieldCount);
    }

    public void testInnerClassCount_1_oe()
    {
        assertEquals("innerClassCount", 0, getVisitor().innerClassCount);
    }

    public void testInnerClassesCount_1_oe()
    {
        assertEquals("innerClassesCount", 0, getVisitor().innerClassesCount);
    }

    public void testJavaClassCount_1_oe()
    {
        assertEquals("javaClassCount", 1, getVisitor().javaClassCount);
    }

    public void testLineNumberCount_1_oe()
    {
        assertEquals("lineNumberCount", 1, getVisitor().lineNumberCount);
    }

    public void testLineNumberTableCount_1_oe()
    {
        assertEquals("lineNumberTableCount", 1, getVisitor().lineNumberTableCount);
    }

    public void testLocalVariableCount_1_oe()
    {
        assertEquals("localVariableCount", 1, getVisitor().localVariableCount);
    }

    public void testLocalVariableTableCount_1_oe()
    {
        assertEquals("localVariableTableCount", 1, getVisitor().localVariableTableCount);
    }

    public void testLocalVariableTypeTableCount_1_oe()
    {
        assertEquals("localVariableTypeTableCount", 0, getVisitor().localVariableTypeTableCount);
    }

    public void testMethodCount_1_oe()
    {
        assertEquals("methodCount", 1, getVisitor().methodCount);
    }

    public void testParameterAnnotationCount_1_oe()
    {
        assertEquals("parameterAnnotationCount", 0, getVisitor().parameterAnnotationCount);
    }

    public void testSignatureCount_1_oe()
    {
        assertEquals("signatureAnnotationCount", 0, getVisitor().signatureAnnotationCount);
    }

    public void testSourceFileCount_1_oe()
    {
        assertEquals("sourceFileCount", 1, getVisitor().sourceFileCount);
    }

    public void testStackMapCount_1_oe()
    {
        assertEquals("stackMapCount", 0, getVisitor().stackMapCount);
    }

    public void testStackMapEntryCount_1_oe()
    {
        assertEquals("stackMapEntryCount", 0, getVisitor().stackMapEntryCount);
    }

    public void testSyntheticCount_1_oe()
    {
        assertEquals("syntheticCount", 0, getVisitor().syntheticCount);
    }

    public void testUnknownCount_1_oe()
    {
        assertEquals("unknownCount", 0, getVisitor().unknownCount);
    }

}
