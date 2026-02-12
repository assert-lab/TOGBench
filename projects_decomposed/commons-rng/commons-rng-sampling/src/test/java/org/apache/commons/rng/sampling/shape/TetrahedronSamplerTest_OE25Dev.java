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
package org.apache.commons.rng.sampling.shape;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.RandomAssert;
import org.apache.commons.rng.simple.RandomSource;

/**
 * Test for {@link TetrahedronSampler}.
 */
class TetrahedronSamplerTest_OE25Dev {
    /**
     * Test invalid vertex dimensions (i.e. not 3D coordinates).
     */

    /**
     * Test non-finite vertices.
     */

    /**
     * Test a tetrahedron with coordinates that are separated by more than
     * {@link Double#MAX_VALUE}.
     */

    /**
     * Test the distribution of points in three dimensions. 6 tetrahedra are used to create
     * a box. The distribution should be uniform inside the box.
     */

    /**
     * Adds the observation. Coordinates are mapped using the offsets, scaled and
     * then cast to an integer bin.
     *
     * <pre>
     * binx = (int) ((x - lx) * sx)
     * </pre>
     *
     * @param v the sample (3D coordinate xyz)
     * @param observed the observations
     * @param binsX the numbers of bins in the x dimension
     * @param binsXy the numbers of bins in the combined x and y dimensions
     * @param lx the lower limit to convert the x coordinate to the x bin
     * @param ly the lower limit to convert the y coordinate to the y bin
     * @param lz the lower limit to convert the z coordinate to the z bin
     * @param sx the scale to convert the x coordinate to the x bin
     * @param sy the scale to convert the y coordinate to the y bin
     * @param sz the scale to convert the z coordinate to the z bin
     * @param tetrahedron the tetrahedron the sample should be within
     */
    // CHECKSTYLE: stop ParameterNumberCheck
    private static void addObservation(double[] v, long[] observed,
                                       int binsX, int binsXy,
                                       double lx, double ly, double lz,
                                       double sx, double sy, double sz,
                                       Tetrahedron tetrahedron) {
        Assertions.assertEquals(3, v.length);
        // Test the point is inside the correct tetrahedron
        Assertions.assertTrue(tetrahedron.contains(v), "Not inside the tetrahedron");
        final double x = v[0];
        final double y = v[1];
        final double z = v[2];
        // Add to the correct bin after using the offset
        final int binx = (int) ((x - lx) * sx);
        final int biny = (int) ((y - ly) * sy);
        final int binz = (int) ((z - lz) * sz);
        observed[binz * binsXy + biny * binsX + binx]++;
    }
    // CHECKSTYLE: resume ParameterNumberCheck

    /**
     * Test the SharedStateSampler implementation.
     */
    @Test
    void testSharedStateSampler() {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final double[] c1 = createCoordinate(-1);
        final double[] c2 = createCoordinate(2);
        final double[] c3 = createCoordinate(-3);
        final double[] c4 = createCoordinate(4);
        final TetrahedronSampler sampler1 = TetrahedronSampler.of(rng1, c1, c2, c3, c4);
        final TetrahedronSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Test the input vectors are copied and not used by reference.
     */

    /**
     * Test the tetrahedron contains predicate.
     */

    /**
     * Creates the coordinate of length 3 filled with
     * the given value and the dimension index: x + i.
     *
     * @param x the value for index 0
     * @return the coordinate
     */
    private static double[] createCoordinate(double x) {
        final double[] coord = new double[3];
        for (int i = 0; i < 3; i++) {
            coord[0] = x + i;
        }
        return coord;
    }

    /**
     * Class to test if a point is inside the tetrahedron.
     *
     * <p>Computes the outer pointing face normals for the tetrahedron. A point is inside
     * if the point lies below each of the face planes of the shape.
     *
     * @see <a href="https://mathworld.wolfram.com/Point-PlaneDistance.html">Point-Plane distance</a>
     */
    private static class Tetrahedron {
        /** The face normals. */
        private final double[][] n;
        /** The distance of each face from the origin. */
        private final double[] d;

        /**
         * Create an instance.
         *
         * @param v1 The first vertex.
         * @param v2 The second vertex.
         * @param v3 The third vertex.
         * @param v4 The fourth vertex.
         */
        Tetrahedron(double[] v1, double[] v2, double[] v3, double[] v4) {
            // Compute the centre of each face
            final double[][] x = new double[][] {
                centre(v1, v2, v3),
                centre(v2, v3, v4),
                centre(v3, v4, v1),
                centre(v4, v1, v2)
            };

            // Compute the normal for each face
            n = new double[][] {
                normal(v1, v2, v3),
                normal(v2, v3, v4),
                normal(v3, v4, v1),
                normal(v4, v1, v2)
            };

            // Given the plane:
            // 0 = ax + by + cz + d
            // Where abc is the face normal and d is the distance of the plane from the origin.
            // Compute d:
            // d = -(ax + by + cz)
            d = new double[] {
                -dot(n[0], x[0]),
                -dot(n[1], x[1]),
                -dot(n[2], x[2]),
                -dot(n[3], x[3]),
            };

            // Compute the distance of the other vertex from each face plane.
            // When below the distance should be negative. Orient each normal so this is true.
            //
            // This distance D of a point xyz to the plane is:
            // D = ax + by + cz + d
            // Above plane:
            // ax + by + cz + d > 0
            // ax + by + cz > -d
            final double[][] other = {v4, v1, v2, v3};
            for (int i = 0; i < 4; i++) {
                if (dot(n[i], other[i]) > -d[i]) {
                    // Swap orientation
                    n[i][0] = -n[i][0];
                    n[i][1] = -n[i][1];
                    n[i][2] = -n[i][2];
                    d[i] = -d[i];
                }
            }
        }

        /**
         * Compute the centre of the triangle face.
         *
         * @param a The first vertex.
         * @param b The second vertex.
         * @param c The third vertex.
         * @return the centre
         */
        private static double[] centre(double[] a, double[] b, double[] c) {
            return new double[] {
                (a[0] + b[0] + c[0]) / 3,
                (a[1] + b[1] + c[1]) / 3,
                (a[2] + b[2] + c[2]) / 3
            };
        }

        /**
         * Compute the normal of the triangle face.
         *
         * @param a The first vertex.
         * @param b The second vertex.
         * @param c The third vertex.
         * @return the normal
         */
        private static double[] normal(double[] a, double[] b, double[] c) {
            final double[] v1 = subtract(b, a);
            final double[] v2 = subtract(c, a);
            // Cross product
            final double[] normal = {
                v1[1] * v2[2] - v1[2] * v2[1],
                v1[2] * v2[0] - v1[0] * v2[2],
                v1[0] * v2[1] - v1[1] * v2[0]
            };
            // Normalise
            final double scale = 1.0 / Math.sqrt(dot(normal, normal));
            normal[0] *= scale;
            normal[1] *= scale;
            normal[2] *= scale;
            return normal;
        }

        /**
         * Compute the dot product of vector {@code a} and {@code b}.
         *
         * <pre>
         * a.b = a.x * b.x + a.y * b.y + a.z * b.z
         * </pre>
         *
         * @param a the first vector
         * @param b the second vector
         * @return the dot product
         */
        private static double dot(double[] a, double[] b) {
            return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
        }

        /**
         * Subtract the second term from the first: {@code a - b}.
         *
         * @param a The first term.
         * @param b The second term.
         * @return the vector {@code a - b}
         */
        private static double[] subtract(double[] a, double[] b) {
            return new double[] {
                a[0] - b[0],
                a[1] - b[1],
                a[2] - b[2]
            };
        }

        /**
         * Check whether or not the tetrahedron contains the given point.
         *
         * @param x the coordinate
         * @return true if inside the tetrahedron
         */
        boolean contains(double[] x) {
            // Must be below all the face planes
            for (int i = 0; i < 4; i++) {
                // This distance D of a point xyz to the plane is:
                // D = ax + by + cz + d
                // Above plane:
                // ax + by + cz + d > 0
                // ax + by + cz > -d
                if (dot(n[i], x) > -d[i]) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Check whether or not the tetrahedron contains the given point
         * within the given absolute epsilon.
         *
         * @param x the coordinate
         * @param epsilon the epsilon
         * @return true if inside the tetrahedron
         */
        boolean contains(double[] x, double epsilon) {
            for (int i = 0; i < 4; i++) {
                // As above but with an epsilon above zero
                if (dot(n[i], x) > epsilon - d[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    void testInvalidDimensionThrows_1_oe() {
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
         double[] ok = new double[3];
         double[] bad = new double[2];
         double[][] c = {ok, ok, ok, ok};
        for (int i = 0; i < c.length; i++) {
             int ii = i;
            c[i] = bad;
            try {
    TetrahedronSampler.of(rng, c[0], c[1], c[2], c[3]);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: () -> String.format(\"Did not detect invalid dimension for vertex: %d\", ii)");
} catch (IllegalArgumentException e) {
}
    }
    }

    @Test
    void testNonFiniteVertexCoordinates_2_oe() {
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
        // A valid tetrahedron
         double[][] c = new double[][] {
            {1, 1, 1}, {1, -1, 1}, {-1, 1, 1}, {1, 1, -1}
        };
        // removed other assertion
         double[] bad = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN};
        for (int i = 0; i < c.length; i++) {
             int ii = i;
            for (int j = 0; j < c[0].length; j++) {
                 int jj = j;
                for ( double d : bad) {
                     double value = c[i][j];
                    c[i][j] = d;
                    try {
    TetrahedronSampler.of(rng, c[0], c[1], c[2], c[3]);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: () -> String.format(\"Did not detect non-finite coordinate: %d,%d = %s\", ii, jj, d)");
} catch (IllegalArgumentException e) {
}
    }
    }
    }
    }

}
