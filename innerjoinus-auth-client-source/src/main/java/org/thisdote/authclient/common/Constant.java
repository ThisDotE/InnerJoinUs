package org.thisdote.authclient.common;

public class Constant {

    /* OAuth provider */
    public static final String OAUTH_GOOGLE = "GOOGLE";
    public static final String OAUTH_GITHUB = "GITHUB";
    public static final String OAUTH_KAKAO = "KAKAO";

    /* OAuth attribute name */
    public static final String OAUTH_ATTR_SUB = "sub";
    public static final String OAUTH_ATTR_EMAIL = "email";
    public static final String OAUTH_ATTR_NAME = "name";

    /* return OAuth result */
    /* TODO
     *  front 작업 후 redirect uri 변경 필요... (OAUTH_RESULT_REDIRECT_URI)
    * */
    public static final String OAUTH_RESULT_TOKEN_KEY = "Authorization";
    public static final String OAUTH_RESULT_REDIRECT_URI = "http://localhost:5500/oauth/index.html";

    /* JWT attribute */
    public static final String JWT_ATTR_LOGIN_CODE = "loginCode";
    public static final String JWT_ATTR_ROLE = "role";

    /* CORS Configuration */
    public static final String CORS_ALLOWED_ORIGIN = "http://localhost:5500";
    public static final String CORS_EXPOSED_HEADER = "Set-Cookie";
}