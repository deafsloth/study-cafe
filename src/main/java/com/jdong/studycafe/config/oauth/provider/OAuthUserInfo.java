package com.jdong.studycafe.config.oauth.provider;

public interface OAuthUserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
