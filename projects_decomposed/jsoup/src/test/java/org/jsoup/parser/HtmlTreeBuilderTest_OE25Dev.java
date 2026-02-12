package org.jsoup.parser;


import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HtmlTreeBuilderTest_OE25Dev {

    @Test
    public void nonnull() {
        assertThrows(IllegalArgumentException.class, () -> {
                HtmlTreeBuilder treeBuilder = new HtmlTreeBuilder();
                treeBuilder.parse(null, null, null); // not sure how to test that these visual warnings actually appear! - test below checks for method annotation
            }
        ); // I'm not convinced that this lambda is easier to read than the old Junit 4 @Test(expected=IEA.class)...
    }


}
