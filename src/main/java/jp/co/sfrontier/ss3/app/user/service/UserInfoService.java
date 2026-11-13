package jp.co.sfrontier.ss3.app.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.app.user.dto.UserInfoResponse;
import jp.co.sfrontier.ss3.app.user.dto.UserSearchRequest;
import jp.co.sfrontier.ss3.app.user.dto.UserUpdateRequest;
import jp.co.sfrontier.ss3.app.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー管理画面における検索とステータス更新処理を提供するクラス
 */
@Service
@RequiredArgsConstructor
public class UserInfoService {

	private final UserInfoRepository repository;
	
	/**
	 * ユーザー情報全件検索
	 */
	public List<UserInfoResponse> findAll(){
		return repository.findAll();
	}

	/**
	 * ユーザー情報主キー検索
	 */
	public UserInfoResponse findById(Long id) {
		return repository.findById(id);
	}

	/**
	 * ユーザー情報検索
	 */
	public List<UserInfoResponse> search(UserSearchRequest request){
		return repository.search(request);
	}

	/**
	 * ユーザー状態更新(statusのみ)
	 * @return 更新成功:true / 失敗;false
	 */
	public boolean updateStatus(UserUpdateRequest request) {
		int count = repository.updateStatus(request);
		return count == 1;
	}
}
