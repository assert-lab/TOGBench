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
package org.apache.commons.numbers.gamma;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Digamma}.
 */
class DigammaTest_OE25Dev {

    @Test
    void testDigammaSmallArgs() {
        // values for negative powers of 10 from 1 to 30 as computed by webMathematica with 20 digits.
        // For example, to compute trigamma($i) = Polygamma(1, $i), use
        //
        // http://functions.wolfram.com/webMathematica/Evaluated.jsp?name=PolyGamma2&plottype=0&vars={%221%22,%22$i%22}&digits=20
        double[] expected = {-10.423754940411076795, -100.56088545786867450, -1000.5755719318103005,
            -10000.577051183514335, -100000.57719921568107, -1.0000005772140199687e6, -1.0000000577215500408e7,
            -1.0000000057721564845e8, -1.0000000005772156633e9, -1.0000000000577215665e10, -1.0000000000057721566e11,
            -1.0000000000005772157e12, -1.0000000000000577216e13, -1.0000000000000057722e14, -1.0000000000000005772e15, -1e+16,
            -1e+17, -1e+18, -1e+19, -1e+20, -1e+21, -1e+22, -1e+23, -1e+24, -1e+25, -1e+26,
            -1e+27, -1e+28, -1e+29, -1e+30};
        for (double n = 1; n < 30; n++) {
            checkRelativeError(String.format("Test %.0f: ", n), expected[(int) (n - 1)], Digamma.value(Math.pow(10.0, -n)), 1e-8);
        }
    }

    private void checkRelativeError(String msg,
                                    double expected,
                                    double actual,
                                    double tolerance) {
        Assertions.assertEquals(expected, actual, Math.abs(tolerance * actual), msg);
    }


}

