package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

@Configuration
//配置认证服务器
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String CLIEN_ID = "asiainfo-client";
	static final String CLIENT_SECRET = "asiainfo-secret";
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 12*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 24*60*60;
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Resource(name = "userService")
	private UserDetailsService userDetailsService;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		//内存中配置clientid
		/*configurer
				.inMemory()
				.withClient(CLIEN_ID)
				.secret(passwordEncoder.encode(CLIENT_SECRET))
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
				refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);*/

		//JDBC中获取clientid等信息
		configurer.withClientDetails(clientDetailsService);
	}

	//设置client_id可以使用form表单式提交
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		//enable client to get the authenticated when using the /oauth/token to get a access token
		//there is a 401 authentication is required if it doesn't allow form authentication for clients when access /oauth/token
		oauthServer
				.checkTokenAccess("isAuthenticated()") //allow check token 403错误解决方案
				.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//设置token的保存方式
		endpoints.tokenStore(tokenStore)
				//设置认证管理
				.authenticationManager(authenticationManager)
				//用于refretoken
				.userDetailsService(userDetailsService)
				//JDBC中获取clientid等信息
				.setClientDetailsService(clientDetailsService);
	}

}