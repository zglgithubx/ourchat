package com.ourchat.common.validatecode;

import com.ourchat.common.redis.GetBean;
import com.ourchat.common.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 手机验证码过滤器，拦截的请求为/xx/xx(登录接口)请求方式为POST请求，做验证码检验。包括是否正确与过期等。
 */

public class ValidateCodeFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil= (JwtUtil) GetBean.getBean("jwtUtil");

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        Map<String,String> map = new HashMap<>();
        if(StringUtils.equals("/authentication/email", httpServletRequest.getRequestURI()) && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")){
            try {
                validateSmsCode(httpServletRequest,httpServletRequest.getSession());
            }catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
            //验证码验证成功，放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else{
            if(!StringUtils.equals("/code/sms",httpServletRequest.getRequestURI())){
                System.out.println(httpServletRequest.getRequestURI());
                //其他请求验证token
                if(StringUtils.equals("/sign-up",httpServletRequest.getRequestURI())){
                    filterChain.doFilter(httpServletRequest,httpServletResponse);
                    return;
                }
                if(StringUtils.equals("/src/main/",httpServletRequest.getRequestURI())){
                    filterChain.doFilter(httpServletRequest,httpServletResponse);
                    return;
                }
                filterChain.doFilter(httpServletRequest,httpServletResponse);
//                String token=httpServletRequest.getHeader("token");
//                if(StringUtils.isNotBlank(token)){
//                    //token验证结果
//                    int verify= jwtUtil.verify(token);
//                    if(verify!=1){
//                        //验证失败
//                        if(verify==2){
//                            map.put("errorMsg","token已过期");
//                        }else{
//                            map.put("errorMsg","用户信息验证失败");
//                        }
//                    }else{
//                        filterChain.doFilter(httpServletRequest,httpServletResponse);
//                        return;
//                    }
//                }else{
//                    map.put("errorMsg","未携带token信息");
//                }
//                JSONObject jsonObject=new JSONObject(map);
//                httpServletResponse.setContentType("application/json");
//                httpServletResponse.setCharacterEncoding("utf-8");
//                PrintWriter printWriter=httpServletResponse.getWriter();
//                printWriter.write(jsonObject.toString());
//                printWriter.flush();
//                printWriter.close();
            }else{
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }
        }
    }

    //校验手机验证码
    private void validateSmsCode(HttpServletRequest request, HttpSession session) {
        //请求里的手机号和验证码
        String mobileInRequest = request.getParameter("email");
        String codeInRequest = request.getParameter("smsCode");

        ValidateCode codeInSession = (ValidateCode) session.getAttribute(mobileInRequest);

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if(codeInSession == null){
            throw new ValidateCodeException("该手机号未发送验证码");
        }

        if(codeInSession.isExpried()){
            session.removeAttribute(mobileInRequest);
            throw new ValidateCodeException("验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        session.removeAttribute(mobileInRequest);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

}
