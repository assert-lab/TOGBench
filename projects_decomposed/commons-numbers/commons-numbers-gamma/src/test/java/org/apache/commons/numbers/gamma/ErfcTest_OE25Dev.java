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
 * Tests for {@link Erfc}.
 */
class ErfcTest_OE25Dev {
    /**
     * Compare erfc against reference values computed using GCC 4.2.1
     * (Apple OSX packaged version) erfcl (extended precision erfc).
     */
    @Test
    void testErfcGnu() {
        final double tol = 1e-15;
        final double[] gnuValues = new double[] {
            2,  2,  2,  2,  2,
            2,  2,  2, 1.9999999999999999785,
            1.9999999999999926422, 1.9999999999984625402, 1.9999999998033839558, 1.9999999845827420998,
            1.9999992569016276586, 1.9999779095030014146, 1.9995930479825550411, 1.9953222650189527342,
            1.9661051464753107271, 1.8427007929497148695, 1.5204998778130465381,  1,
            0.47950012218695346194, 0.15729920705028513051, 0.033894853524689272893, 0.0046777349810472658333,
            0.00040695201744495893941, 2.2090496998585441366E-05, 7.4309837234141274516E-07, 1.5417257900280018858E-08,
            1.966160441542887477E-10, 1.5374597944280348501E-12, 7.3578479179743980661E-15, 2.1519736712498913103E-17,
            3.8421483271206474691E-20, 4.1838256077794144006E-23, 2.7766493860305691016E-26, 1.1224297172982927079E-29,
            2.7623240713337714448E-33, 4.1370317465138102353E-37, 3.7692144856548799402E-41, 2.0884875837625447567E-45
        };

        double x = -10;
        for (int i = 0; i < 41; i++) {
            Assertions.assertEquals(gnuValues[i], Erfc.value(x), tol);
            x += 0.5d;
        }
    }

    /**
     * Tests erfc against reference data computed using Maple reported in Marsaglia, G,,
     * "Evaluating the Normal Distribution," Journal of Statistical Software, July, 2004.
     * http//www.jstatsoft.org/v11/a05/paper
     */


}
