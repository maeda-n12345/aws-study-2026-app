package jp.co.sfrontier.ss3.app.auth.controller;

import java.util.DuplicateFormatFlagsException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sfrontier.ss3.app.auth.dto.RegisterForm;
import jp.co.sfrontier.ss3.app.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;

/**
 * 新規ユーザ登録処理を行うコントローラークラス
 */
@Controller
@RequiredArgsConstructor
public class RegisterController {

	private RegisterService service;

	/**
	 * 登録画面を表示する
	 * 
	 * @param model 画面へ渡す情報
	 * @return 登録画面テンプレート
	 */
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "auth/register";
	}

	/**
	 * 新規ユーザーを登録する
	 * 
	 * @param form フォームの入力情報
	 * @param bindingResult Validationの検証結果
	 * @param model 画面に渡す情報
	 * @param request 自動ログインに使用するリクエスト
	 * @return 登録画面、ログイン画面、またはホームへの遷移先
	 */
	@PostMapping("/register")
	public String registerUser(@Validated @ModelAttribute("registerForm") RegisterForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		// 入力項目のバリデーションチェックを行う
		if (bindingResult.hasErrors()) {
			return "auth/register";
		}

		// ユーザIDとメールアドレスの重複チェックを行う
		RegisterService.UserCheckResult result = service.checkUserExists(form.getUserName(), form.getMailAddress());

		if (result == RegisterService.UserCheckResult.FOUND) {
			model.addAttribute("registerError", "ユーザ名またはメールアドレスは既に使用されています。");
			return "auth/register";
		}

		// ユーザの新規登録を行う
		try {
			service.register(form.getUserName(), form.getMailAddress(), form.getPassword());
		} catch (DuplicateFormatFlagsException e) {
			model.addAttribute("registerError", "ユーザ名またはメールアドレスは既に使用されています。");
			return "auth/register";
		}

		// 新規登録したらそのままログインする
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(form.getUserName(),
				form.getPassword());

		try {
			// ユーザー登録後、登録したユーザー名とパスワードで自動ログインする
			request.login(form.getUserName(), form.getPassword());
		} catch (Exception e) {
			// 手動ログインに案内する
			redirectAttributes.addFlashAttribute("loginMessage", "登録が完了しました。ログインしてください。");

			return "redirect:/login";
		}

		// メニュー画面に遷移する
		return "redirect:/home";
	}

}
