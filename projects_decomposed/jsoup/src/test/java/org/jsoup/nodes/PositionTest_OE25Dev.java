package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.integration.servlets.FileServlet;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 Functional tests for the Position tracking behavior (across nodes, treebuilder, etc.)
 */
class PositionTest_OE25Dev {
    static Parser TrackingParser = Parser.htmlParser().setTrackPosition(true);


}