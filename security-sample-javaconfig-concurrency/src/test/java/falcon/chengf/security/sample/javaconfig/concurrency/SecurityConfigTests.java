/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package falcon.chengf.security.sample.javaconfig.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.core.ResolvableType;

/**
 * @author Rob Winch
 *
 */
public class SecurityConfigTests {
	HashMap<Integer, List<String>> myMap = new HashMap<>();
	List<String> aa = new ArrayList<String>();
	@Test
	public void securityConfigurationLoads() throws NoSuchFieldException, SecurityException {
		

		ResolvableType t = ResolvableType.forField(getClass().getDeclaredField("myMap"));
		System.out.println(t.getSuperType()); // AbstractMap<Integer, List<String>>
		System.out.println(t.asMap()); // Map<Integer, List<String>>
		System.out.println(t.getGeneric(0).resolve()); // Integer
		System.out.println(t.getGeneric(1).resolve()); // List
		System.out.println(t.getGeneric(1)); // List<String>
		System.out.println(t.resolveGeneric(1, 0)); // String

		
		ResolvableType resolvableType = ResolvableType.forField(getClass().getDeclaredField("aa"));
		System.out.println(resolvableType.getGeneric(0).resolve());
	}
}
