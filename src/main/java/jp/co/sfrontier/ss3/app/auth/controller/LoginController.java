package jp.co.sfrontier.ss3.app.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログイン処理を行うコントローラークラス
 */
@Controller
public class LoginController {

	@GetMapping("/login")
	public String showLoginPage() { 

		return "auth/login";
	}
}
