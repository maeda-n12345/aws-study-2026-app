package jp.co.sfrontier.ss3.app.user.dto;

import lombok.Data;

/**
 * ユーザー検索フォームの入力情報
 */
@Data
public class UserSearchRequest {

	/**
	 * ユーザーID
	 */
	private Long id;

	/**
	 * ユーザー名
	 */
	private String name;
}
