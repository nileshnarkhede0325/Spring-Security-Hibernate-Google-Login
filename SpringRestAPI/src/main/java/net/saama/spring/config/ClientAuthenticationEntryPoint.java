package net.saama.spring.config;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*
 * This custom bean has added to autowire AuthenticationEntry bean
*/

@Component
public class ClientAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	private static String loginFormUrl = "/googleLogin";
	public ClientAuthenticationEntryPoint() {
		super(loginFormUrl);
	}

}
