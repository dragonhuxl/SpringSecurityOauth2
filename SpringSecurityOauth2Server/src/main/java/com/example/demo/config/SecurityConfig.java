package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
//安全管理
@EnableWebSecurity
//开启权限控制
//配合controller中的@PreAuthorize("hasRole('USER')")使用
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        //配置用户信息获取方式，密码使用hash加密
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Primary
    @Bean
    public ClientDetailsService clientDetailsService() {
        //配置clientID等授权信息 保存在数据库中
        //此处使用的Oauth2默认的JdbcClientDetailsService 需要初始化对应的表结构
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁止跨站请求伪造
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/noOauth/**")
                //无需认证
                .permitAll()
                .antMatchers("/oauth/**")
                .permitAll()
                //除了匹配的URL剩下的请求
                .anyRequest()
                //认证
                .authenticated()
                .and()
                //认证失败的返回消息
                .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);
    }
    @Bean
    public TokenStore tokenStore() {
        //配置access_token等授权信息 保存在数据库中 应用重启后token依旧有效
        //此处使用的Oauth2默认的JdbcTokenStore 需要初始化对应的表结构
        return new JdbcTokenStore(dataSource);
//        //配置access_token保存在内存中，应用重启后token失效
//        return new InMemoryTokenStore();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        //初始化加密方式 Spring Security提供
        return new BCryptPasswordEncoder();
    }

    //跨域过滤器
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
