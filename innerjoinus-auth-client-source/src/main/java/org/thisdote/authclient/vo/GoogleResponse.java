package org.thisdote.authclient.vo;

import java.util.Map;

import static org.thisdote.authclient.common.Constant.*;

public class GoogleResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return OAUTH_GOOGLE;
    }

    @Override
    public String getProviderId() {
        return attribute.get(OAUTH_ATTR_SUB).toString();
    }

    @Override
    public String getEmail() {
        return attribute.get(OAUTH_ATTR_EMAIL).toString();
    }

    @Override
    public String getName() {
        return attribute.get(OAUTH_ATTR_NAME).toString();
    }
}
