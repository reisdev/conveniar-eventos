package com.mthrsj.conveniareventos.models;

public class Auth {
    protected String AccessToken;
    protected long Expires;

    public Auth(String accessToken, long expires) {
        AccessToken = accessToken;
        Expires = expires;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public long getExpires() {
        return Expires;
    }

    public void setExpires(long expires) {
        Expires = expires;
    }
}
