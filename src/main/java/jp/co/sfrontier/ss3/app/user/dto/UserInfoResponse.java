package jp.co.sfrontier.ss3.app.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー管理画面に表示するユーザー情報
 */
@Data
public class UserInfoResponse {

	private Long userId;
	private String userName;
	private String mailAddress;
	private Integer status;
	private LocalDateTime createDatetime;
	private LocalDateTime updateDatetime;
	private Integer version;
}
