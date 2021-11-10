package com.ourchat.common.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_FROM_MOBILE="email";
    private String mobileParameter=SPRING_SECURITY_FROM_MOBILE;
    private boolean postOnly=true;
    public SmsCodeAuthenticationFilter(){
        super(new AntPathRequestMatcher("/auth/email"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String email = obtainMobile(request);
        if (email == null) {
            email = "";
        }
        email = email.trim();
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(email,obtainPassowrd(request));
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
    /**
     * 获取手机号
     * @param request
     * @return
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }
    protected String obtainPassowrd(HttpServletRequest request){
        return request.getParameter("password");
    }

    protected void setDetails(HttpServletRequest request,
                              SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "email parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getMobileParameter() {
        return mobileParameter;
    }


}
