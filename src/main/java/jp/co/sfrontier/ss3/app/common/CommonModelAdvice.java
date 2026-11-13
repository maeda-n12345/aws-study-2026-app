package jp.co.sfrontier.ss3.app.common;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sfrontier.ss3.app.auth.service.LoginUserDetails;
import jp.co.sfrontier.ss3.app.user.entity.UserInformation;

/**
 * 各画面で使用する共通情報を Model に設定するクラス
 */
@ControllerAdvice
public class CommonModelAdvice {

	/**
	 * ログイン中のユーザー情報を各画面に渡す
	 * 
	 * @param loginUserDetails Spring Security が管理するログインユーザー
	 * @return
	 */
	@ModelAttribute("loginUser")
	public UserInformation loginUser(@AuthenticationPrincipal LoginUserDetails loginUser) {

		// 未ログインの場合は、ユーザー情報を取得せずに終了する
		if (loginUser == null) {
			return null;
		}

		return loginUser.getUser();
	}

}
