package jp.co.sfrontier.ss3.app.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.app.user.entity.UserInformation;
import jp.co.sfrontier.ss3.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * Spring Secutrity で使用するユーザー情報を取得するサービス
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
	 * ユーザー情報リポジトリ
	 */
	private final UserRepository userRepository;

	/**
	 * ユーザー名またはメールアドレスからユーザー情報を取得する
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserInformation user = userRepository.findByUserNameOrMailAddress(username)
				.orElseThrow(() -> new UsernameNotFoundException("ユーザが見つかりません"));

		// user(UserInformationTbl)を、Spring Security用の形(UserDetails)に変換して返す
		return new LoginUserDetails(user);
	}
}
