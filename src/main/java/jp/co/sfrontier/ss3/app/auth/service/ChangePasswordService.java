package jp.co.sfrontier.ss3.app.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * パスワード変更処理のサービス
 */
@Service
@RequiredArgsConstructor
public class ChangePasswordService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * ユーザー名またはメールアドレスに対応するパスワードを変更する。
	 *
	 * 入力形式と確認用パスワードの一致は、
	 * ControllerでValidationを実行して確認する。
	 *
	 * @param userNameOrMailAddress ユーザー名またはメールアドレス
	 * @param rawPassword ハッシュ化前の新しいパスワード
	 * @return 変更成功ならtrue、ユーザーが存在しなければfalse
	 */
	@Transactional
	public boolean changePassword(String userNameOrMailAddress, String rawPassword) {

		return userRepository
				// ログイン用と同じ検索処理で、変更対象を取得する
				.findByUserNameOrMailAddress(userNameOrMailAddress)

				// ユーザーが見つかった場合だけ更新する
				.map(user -> {

					// 新しいパスワードをハッシュ化する
					String encodedPassword = passwordEncoder.encode(rawPassword);

					// パスワード・更新日時・バージョンを更新する
					int updatedRows = userRepository.updatePassword(user.getUserId(), encodedPassword);

					// 更新できなかった場合は、成功として扱わない
					if (updatedRows != 1) {
						throw new IllegalStateException("パスワードの更新件数が不正です: " + updatedRows);
					}

					return true;
				})

				// 対象ユーザーが存在しない場合
				.orElse(false);
	}
}
