package jp.co.sfrontier.ss3.app.user.dto;

import lombok.Data;

/**
 * ユーザーのステータス更新に使用する入力情報。
 */
@Data
public class UserUpdateRequest {

	/**
	 * ユーザーID
	 */
	private Long userId;

	/**
	 * ユーザー状態（1:有効 / 0:無効）
	 */
	private Integer status;

	/**
	 * ロック用バージョン
	 */
	private Integer version;
}
