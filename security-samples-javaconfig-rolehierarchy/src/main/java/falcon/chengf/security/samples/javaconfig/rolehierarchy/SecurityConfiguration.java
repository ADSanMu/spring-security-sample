/**
 * 
 */
package falcon.chengf.security.samples.javaconfig.rolehierarchy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

/**
 * @author: 作者： chengaofeng
 * @date: 创建时间：2018-01-27 17:34:10
 * @Description: TODO
 * @version V1.0
 */
@Configuration
public class SecurityConfiguration {

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return roleHierarchy;
	}
}
