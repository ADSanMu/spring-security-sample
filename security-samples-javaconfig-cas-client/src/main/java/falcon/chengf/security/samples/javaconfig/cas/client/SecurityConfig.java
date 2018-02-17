/**
 * 
 */
package falcon.chengf.security.samples.javaconfig.cas.client;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author: 作者： chengaofeng
 * @date: 创建时间：2018-01-05 09:09:21
 * @Description: TODO
 * @version V1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		// cas server默认用户名和密码 casuser::Mellon
		manager.createUser(User.withUsername("casuser").password("Mellon").roles("USER").build());
		return manager;
	}

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		// 路径固定，client端ip:端口/login/cas,是CasAuthenticationFilter默认监听的地址
		serviceProperties.setService("http://localhost:8089/login/cas");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	@Bean
	public AuthenticationEntryPoint casEntryPoint() {
		CasAuthenticationEntryPoint casEntryPoint = new CasAuthenticationEntryPoint();
		// cas-server 对应的login地址
		casEntryPoint.setLoginUrl("http://localhost:8080/cas-server/cas/login");
		casEntryPoint.setServiceProperties(serviceProperties());
		return casEntryPoint;
	}

	@Bean
	public CasAuthenticationFilter casFilter() throws Exception {
		CasAuthenticationFilter casFilter = new CasAuthenticationFilter();
//		casFilter.setFilterProcessesUrl("/**");
		casFilter.setAuthenticationManager(authenticationManager());
		return casFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.anyRequest().hasRole("USER").and()
			.exceptionHandling()
				.authenticationEntryPoint(casEntryPoint()).and()
			.addFilter(casFilter())
			.authenticationProvider(casAuthenticationProvider());
	}

	@Bean
	@Autowired
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(userDetailsByNameServiceWrapper());
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(ticketValidator());
		casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
		return casAuthenticationProvider;
	}

	@Bean
	public Cas20ServiceTicketValidator ticketValidator() {
		// cas server 服务的context path
		Cas20ServiceTicketValidator ticketValidator = new Cas20ServiceTicketValidator("http://localhost:8080/cas-server");
//		ticketValidator.setEncoding("UTF-8");
		return ticketValidator;
	}

	@Bean
	public UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken> userDetailsByNameServiceWrapper() {
		UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken> userDetailsByNameServiceWrapper = new UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken>(
				userDetailsService());
		return userDetailsByNameServiceWrapper;
	}

}
