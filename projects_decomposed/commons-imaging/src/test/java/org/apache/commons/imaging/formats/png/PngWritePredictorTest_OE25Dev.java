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



package org.apache.commons.imaging.formats.png;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Provides a test for the PngWriter using predictors
 */
public class PngWritePredictorTest_OE25Dev {

  public PngWritePredictorTest_OE25Dev() {
  }

  @BeforeAll
  public static void setUpClass() {
  }

  @BeforeEach
  public void setUp() {
  }

  /**
   * Populate an integer pixel array for a 256-by-256 image
   * with varied colors across the image area and a white and
   * black line down the main diagonal.
   * @return a valid array of integers.
   */
  private int[] populateARGB() {
    //populate array with a blend of color components
    int[] argb = new int[256 * 256];
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        int red = i;
        int green = (255 - i);
        int blue = j;
        argb[i * 256 + j] = ((((0xff00 | red) << 8) | green) << 8) | blue;
      }
    }

    // also draw a black and white strip down main diagonal
    for (int i = 0; i < 256; i++) {
      argb[i * 256 + i] = 0xff000000;
      if (i < 255) {
        argb[i * 256 + i + 1] = 0xffffffff;
      }
    }
    return argb;
  }


}
