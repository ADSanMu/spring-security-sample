/**
 * 
 */
package chengf.falcon.security.samples.javaconfig.csrf.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 作者： chengaofeng
 * @date: 创建时间：2018-03-16 16:01:28
 * @Description: TODO
 * @version V1.0
 */
@Controller
public class MyController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/recive")
	public String recive(@ModelAttribute Bean data) {
		System.out.println(data);
		return "hello";
	}

	public static class Bean {}
}
