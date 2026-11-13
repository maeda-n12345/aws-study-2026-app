package jp.co.sfrontier.ss3.app.user.repository;

import java.util.List;

import jp.co.sfrontier.ss3.app.user.dto.UserInfoResponse;
import jp.co.sfrontier.ss3.app.user.dto.UserSearchRequest;
import jp.co.sfrontier.ss3.app.user.dto.UserUpdateRequest;

/**
 * ユーザー管理画面で使用する検索・更新処理の定義
 */
public interface UserInfoRepository {

	// 全件取得する
	List<UserInfoResponse> findAll();

	// 1件取得する
	UserInfoResponse findById(Long id);

	// 条件検索する
	List<UserInfoResponse> search(UserSearchRequest request);

	// ユーザーの状態を更新する
	int updateStatus(UserUpdateRequest userUpdateRequest);
}
