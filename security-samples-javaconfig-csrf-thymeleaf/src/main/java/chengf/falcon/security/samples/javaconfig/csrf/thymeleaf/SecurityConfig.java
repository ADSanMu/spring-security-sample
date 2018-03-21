/**
 * 
 */
package chengf.falcon.security.samples.javaconfig.csrf.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: 作者： chengaofeng
 * @date: 创建时间：2018-01-16 19:32:47
 * @Description: TODO
 * @version V1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void auth(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").authorities("ROLE_USER");
//		auth.eraseCredentials(false);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login.html")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/index")
			.permitAll()
			.and()
		.logout()
			.logoutSuccessUrl("/login.html");
	}
}
