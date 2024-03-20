package org.thisdote.authclient.vo;

public interface OAuth2Response {

    String getProvider();           // google, github, kakao,,,
    String getProviderId();
    String getEmail();
    String getName();
}
