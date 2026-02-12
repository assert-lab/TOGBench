package com.github.scribejava.core.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;
import com.github.scribejava.core.exceptions.OAuthParametersMissingException;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.ObjectMother;
import org.junit.function.ThrowingRunnable;

public class HeaderExtractorTest_OE25Dev {

    private HeaderExtractorImpl extractor;
    private OAuthRequest request;

    @Before
    public void setUp() {
        request = ObjectMother.createSampleOAuthRequest();
        extractor = new HeaderExtractorImpl();
    }

    public void shouldExceptionIfRequestIsNull() {
        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                extractor.extract(null);
            }
        });
    }

    public void shouldExceptionIfRequestHasNoOAuthParams() {
        final OAuthRequest emptyRequest = new OAuthRequest(Verb.GET, "http://example.com");
        assertThrows(OAuthParametersMissingException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                extractor.extract(emptyRequest);
            }
        });
    }


}
