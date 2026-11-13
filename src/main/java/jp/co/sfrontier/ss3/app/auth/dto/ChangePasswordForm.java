package jp.co.sfrontier.ss3.app.auth.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * パスワード変更フォームの入力情報
 */
@Getter
@Setter
public class ChangePasswordForm {

	@NotBlank(message = "登録しているユーザ名またはメールアドレスを入力してください。")
	private String userNameOrMailAddress;

	@NotBlank(message = "新しいパスワードを入力してください。")
	@Size(min = 8, max = 32, message = "パスワードは8～32文字で入力してください。")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "パスワードは英大文字・英小文字・数字を含める必要があります。")
	private String newPassword;

	@NotBlank(message = "新しいパスワードを入力してください。")
	@Size(min = 8, max = 32, message = "パスワードは8～32文字で入力してください。")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "パスワードは英大文字・英小文字・数字を含める必要があります。")
	private String confirmPassword;

	/**
	 * 新しいパスワードが確認用パスワードと同じか比較する
	 * 
	 * @return 一致する場合はtrue
	 */
	@AssertTrue(message = "確認用のパスワードが一致しません。")
	public boolean isPasswordMatched() {

		// 新しいパスワードか確認用パスワードが未入力の場合はこのメソッドではエラーにしない
		if (newPassword == null || newPassword.isBlank() || confirmPassword == null || confirmPassword.isBlank()) {
			return true;
		}

		return newPassword.equals(confirmPassword);
	}
}
