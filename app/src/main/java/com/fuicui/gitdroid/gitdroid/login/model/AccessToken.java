package com.fuicui.gitdroid.gitdroid.login.model;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：yuanchao on 2016/8/26 0026 12:09
 * 邮箱：yuanchao@feicuiedu.com
 */
public class AccessToken {
    /**
     {
     "access_token":"e72e16c7e42f292c6912e7710c838347ae178b4a",
     "scope":"repo,gist",
     "token_type":"bearer"
     }
     */

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }
}
