package jp.co.sfrontier.ss3.app.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザ管理テーブルのエンティティ
 */
@Getter
@Setter
public class UserInformation implements Serializable{

	 private static final long serialVersionUID = 1L;

	    /** ユーザーID */
	    private Long userId;

	    /** ユーザー名 */
	    private String userName;

	    /** メールアドレス */
	    private String mailAddress;

	    /** DBに保存されたパスワードのハッシュ値 */
	    private String password;

	    /** ユーザーの状態 */
	    private int status;

	    /** 作成日時 */
	    private LocalDateTime createDatetime;

	    /** 更新日時 */
	    private LocalDateTime updateDatetime;

	    /** 更新管理用のバージョン */
	    private Integer version;
}
