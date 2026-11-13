package jp.co.sfrontier.ss3.app.user.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sfrontier.ss3.app.user.dto.UserInfoResponse;
import jp.co.sfrontier.ss3.app.user.dto.UserSearchRequest;
import jp.co.sfrontier.ss3.app.user.dto.UserUpdateRequest;

@Mapper
public interface UserInfoMapper {

	// 全件取得する
	List<UserInfoResponse> findAll();

	// 1件取得する
	UserInfoResponse findById(@Param("id") Long id);

	// 条件検索する
	List<UserInfoResponse> search(@Param("request")UserSearchRequest request);

	// ユーザーの状態を更新する
	int updateStatus(@Param("request")UserUpdateRequest request);
}
