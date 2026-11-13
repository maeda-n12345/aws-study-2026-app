package jp.co.sfrontier.ss3.app.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.app.user.entity.UserInformation;
import jp.co.sfrontier.ss3.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * 新規ユーザーの登録処理
 */
@Service
@RequiredArgsConstructor
public class RegisterService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	/**
	 * ユーザー名・メールアドレスの重複確認結果。
	 */
	public enum UserCheckResult {
		NOT_FOUND, FOUND
	}

	/**
	 * ユーザIDとメールアドレスの重複チェックを行う
	 */
	public UserCheckResult checkUserExists(String userName, String mailAddress) {
		if (userRepository.existsByUserName(userName)
				|| userRepository.existsByMailAddress(mailAddress)) {
			return UserCheckResult.FOUND;
		}
		return UserCheckResult.NOT_FOUND;
	}

	/**
	 * 新規ユーザを登録する
	 */
	@Transactional
	public void register(String userName, String mailAddress, String password) {

		// 平文のパスワードをハッシュ化する
		String encodedPassword = passwordEncoder.encode(password);

		UserInformation user = new UserInformation();
		user.setUserName(userName);
		user.setMailAddress(mailAddress);
		user.setPassword(encodedPassword);
		user.setStatus(1);

		int insertedRows = userRepository.insert(user);

		if (insertedRows != 1) {
			throw new IllegalStateException("ユーザーの登録件数が不正です: " + insertedRows);
		}
	}
}
