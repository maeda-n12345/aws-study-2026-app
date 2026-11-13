package jp.co.sfrontier.ss3.app.auth.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sfrontier.ss3.app.auth.dto.ChangePasswordForm;
import jp.co.sfrontier.ss3.app.auth.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;

/**
 * パスワード変更画面を制御するクラス
 */
@Controller
@RequiredArgsConstructor
public class ChangePasswordController {

	private final ChangePasswordService service;

	/**
	 * パスワード変更画面を表示する。
	 *
	 * @param model 画面へ渡す情報
	 * @return 変更画面のテンプレート名
	 */
	@GetMapping("/changePassword")
	public String showChangePasswordPage(Model model) {
		model.addAttribute("changePasswordForm", new ChangePasswordForm());
		return "auth/changePassword";
	}

	@PostMapping("/changePassword")
	public String changePassword(@Valid @ModelAttribute("changePasswordForm") ChangePasswordForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		// 入力項目のバリデーションチェックを行う
		if (bindingResult.hasErrors()) {
			return "auth/changePassword";
		}

		// パスワードを変更する
		boolean success = service.changePassword(form.getUserNameOrMailAddress(), form.getNewPassword());

		// ユーザ名やメールアドレスが存在しない場合はエラーメッセージを出す
		if (!success) {
			model.addAttribute("errorMessage", "ユーザ名またはメールアドレスが存在しません");
			return "auth/changePassword";
		}

		// パスワード変更したらログインしホーム画面に遷移する
		try {
			// 既にログイン中なら、現在のセッションを終了する
			if (request.getUserPrincipal() != null) {
				request.logout();
			}

			// 変更したパスワードでログインする
			request.login(
					form.getUserNameOrMailAddress(),
					form.getNewPassword());

		} catch (ServletException exception) {
			// パスワード変更は完了済みなので、手動ログインを案内する
			redirectAttributes.addFlashAttribute(
					"loginMessage",
					"パスワードを変更しました。ログインしてください。");

			return "redirect:/login";
		}

		return "redirect:/home";
	}
}
