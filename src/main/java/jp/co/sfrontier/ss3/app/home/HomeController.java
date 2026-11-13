package jp.co.sfrontier.ss3.app.home;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインしているユーザーに応じたホーム画面を制御するクラス
 */
@Controller
public class HomeController {

	@GetMapping("/home")
	public String showHome(Authentication authentication) {

		// ログインしたユーザが ROLE_ADMIN を持っているか判定する
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

		if (isAdmin) {
			return "home/adminhome";
		} else {
			return "home/home";
		}
	}
}
