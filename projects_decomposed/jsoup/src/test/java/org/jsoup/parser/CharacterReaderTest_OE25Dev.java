package org.jsoup.parser;

import org.jsoup.integration.ParseTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for character reader.
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class CharacterReaderTest_OE25Dev {
    public final static int maxBufferLen = CharacterReader.maxBufferLen;

    static String BufferBuster(String content) {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < maxBufferLen)
            builder.append(content);
        return builder.toString();
    }


}
