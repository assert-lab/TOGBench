package com.github.scribejava.core.extractors;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.github.scribejava.core.ObjectMother;
import com.github.scribejava.core.exceptions.OAuthParametersMissingException;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import static org.junit.Assert.assertThrows;
import org.junit.function.ThrowingRunnable;

public class BaseStringExtractorTest_OE25Dev {

    private BaseStringExtractorImpl extractor;
    private OAuthRequest request;
    private OAuthRequest requestPort80;
    private OAuthRequest requestPort80v2;
    private OAuthRequest requestPort8080;
    private OAuthRequest requestPort443;
    private OAuthRequest requestPort443v2;

    @Before
    public void setUp() {
        request = ObjectMother.createSampleOAuthRequest();
        requestPort80 = ObjectMother.createSampleOAuthRequestPort80();
        requestPort80v2 = ObjectMother.createSampleOAuthRequestPort80v2();
        requestPort8080 = ObjectMother.createSampleOAuthRequestPort8080();
        requestPort443 = ObjectMother.createSampleOAuthRequestPort443();
        requestPort443v2 = ObjectMother.createSampleOAuthRequestPort443v2();
        extractor = new BaseStringExtractorImpl();
    }

    public void shouldThrowExceptionIfRquestIsNull() {
        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                extractor.extract(null);
            }
        });
    }

    public void shouldThrowExceptionIfRquestHasNoOAuthParameters() {
        final OAuthRequest request = new OAuthRequest(Verb.GET, "http://example.com");
        assertThrows(OAuthParametersMissingException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                extractor.extract(request);
            }
        });
    }


}
