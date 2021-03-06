package com.ourchat.system.login.security;

import com.ourchat.system.login.validatecode.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityBrowserConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Qualifier("myUserDetailService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        super.configure(auth);
    }
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html");
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
//        http.authorizeRequests()
//            //??????swagger-ui.html??????
//
//            .anyRequest().authenticated()
//            .and()
//                .logout().permitAll()
//            .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll();
//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/","/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/#/pages/index/Login").permitAll()
                .antMatchers("/code/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //???UsernamePasswordAuthenticationFilter??????????????????????????????
                .formLogin()
                .loginPage("/#/pages/index/Login")
                .loginProcessingUrl("/authentication/mobile")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/#/pages/index/Login").permitAll()
//                .antMatchers("/code/*").permitAll()
//                .anyRequest()
//                .authenticated()
                .and()
                .csrf().disable()
                //???SmsCodeAuthenticationSecurityConfig???????????????
                .apply(smsCodeAuthenticationSecurityConfig);
    }
    @Bean
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
