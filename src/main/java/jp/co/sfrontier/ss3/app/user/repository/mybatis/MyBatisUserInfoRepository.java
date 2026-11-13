package jp.co.sfrontier.ss3.app.user.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.sfrontier.ss3.app.user.dto.UserInfoResponse;
import jp.co.sfrontier.ss3.app.user.dto.UserSearchRequest;
import jp.co.sfrontier.ss3.app.user.dto.UserUpdateRequest;
import jp.co.sfrontier.ss3.app.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;

/**
 * MyBatisを使用して、ユーザー管理の検索・更新を行うリポジトリ
 */
@Repository
@RequiredArgsConstructor
public class MyBatisUserInfoRepository implements UserInfoRepository{
	
	private final UserInfoMapper mapper;
	
	/**
	 * 全ユーザーのIDを取得する
	 */
	@Override
	public List<UserInfoResponse> findAll() {
		return mapper.findAll();
	}

	/**
	 * ユーザーIDで１件取得する
	 */
	@Override
	public UserInfoResponse findById(Long id) {
		return mapper.findById(id);
	}

	/**
	 * 指定条件に合うユーザーを取得する
	 */
	@Override
	public List<UserInfoResponse> search(UserSearchRequest request) {
		return mapper.search(request);
	}

	/**
	 * IDとバージョンを指定して、ステータスを更新する
	 */
	@Override
	public int updateStatus(UserUpdateRequest request) {
		return mapper.updateStatus(request);
	}

}
