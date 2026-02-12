package com.github.scribejava.apis.odnoklassniki;

import com.github.scribejava.apis.OdnoklassnikiApi;
import com.github.scribejava.core.builder.ServiceBuilder;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.ParameterList;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Parameter;
import com.github.scribejava.core.oauth.OAuth20Service;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class OdnoklassnikiServiceTest_OE25Dev {

    private static final String URL = "https://api.ok.ru/fb.do?method=friends.get&fields=uid%2C"
            + "first_name%2Clast_name%2Cpic_2&application_key=AAAAAAAAAAAAAAAA&format=json";

    private final OAuth20Service service = new ServiceBuilder("0000000000")
            .apiSecret("CCCCCCCCCCCCCCCCCCCCCCCC")
            .defaultScope("VALUABLE_ACCESS")
            .callback("http://your.site.com/callback")
            .build(OdnoklassnikiApi.instance());

    private static String findParam(ParameterList list, String key) {
        for (Parameter param : list.getParams()) {
            if (param.getKey().equals(key)) {
                return param.getValue();
            }
        }
        return null;
    }


}
