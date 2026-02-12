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
package org.apache.commons.imaging.formats.jpeg.iptc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.GenericImageMetadata.GenericImageMetadataItem;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.jpeg.JpegImagingParameters;
import org.apache.commons.imaging.formats.jpeg.JpegPhotoshopMetadata;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {#link {@link IptcParser} class.
 * @since 1.0-alpha2
 */
public class IptcParserTest_OE25Dev {

    /**
     * Some block types (or Image Resource Blocks in Photoshop specification) have a recommendation
     * to not be interpreted by parsers, as they are handled by Photoshop in a special way,
     * that varies by platform (e.g. Mac, Windows, etc).
     *
     * IMAGING-246 provided a test image with APP13 segments, with some of the block types,
     * such as 1084. When the IptcParser is reading this block, the 4-bytes length record
     * may give a value that is larger than the block size.
     *
     * When such a case happens, the IptcParser fails with an exception, to avoid a memory
     * error. To prevent that, the parser must be able to skip these blocks that the specification
     * says "It is recommended that you do not interpret or use this data".
     *
     * @throws IOException when reading input
     * @throws ImageReadException when parsing file
     * @see <a href="https://www.adobe.com/devnet-apps/photoshop/fileformatashtml/">Adobe Photoshop File Formats Specification</a>
     */

    /**
     * Tests for IptcParser encoding support. See IMAGING-168 and pull request #124 for more.
     * @throws IOException when reading input
     * @throws ImageReadException when parsing file
     */


}
