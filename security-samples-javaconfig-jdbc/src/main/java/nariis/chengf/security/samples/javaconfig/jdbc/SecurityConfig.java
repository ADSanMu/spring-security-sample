/**
 * 
 */
package nariis.chengf.security.samples.javaconfig.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author: 作者： chengaofeng
 * @date: 创建时间：2018-01-09 08:31:44
 * @Description: TODO
 * @version V1.0
 */
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("sso");
		dataSource.setName("chengf");
		dataSource.setUrl("jdbc:mysql://localhost:3306/chengf?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setMaxActive(20);
		dataSource.setInitialSize(1);
		dataSource.setMaxWait(60000);
		dataSource.setMinIdle(1);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("select 'x'");
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		return dataSource;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

}
