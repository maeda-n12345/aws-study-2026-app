package jp.co.sfrontier.ss3.app.user.repository;

import java.util.Optional;

import jp.co.sfrontier.ss3.app.user.entity.UserInformation;

/**
 * ユーザー情報の取得処理を定義する
 */
public interface UserRepository {

	/**
	 * ユーザー名またはメールアドレスと一致するユーザー情報を取得する
	 * 
	 * @param userName ユーザー名
	 * @param mailAddress メールアドレス
	 * @return ユーザー情報
	 */
	Optional<UserInformation> findByUserNameOrMailAddress(String userNameOrMailAddress);

	/**
	 * 指定したユーザー名が登録済みか確認する
	 *
	 * @param userName 確認するユーザー名
	 * @return 登録済みならtrue
	 */
	boolean existsByUserName(String userName);

	/**
	 * 指定したメールアドレスが登録済みか確認する
	 *
	 * @param mailAddress 確認するメールアドレス
	 * @return 登録済みならtrue
	 */
	boolean existsByMailAddress(String mailAddress);

	/**
	 * ユーザーを新規登録する
	 *
	 * ユーザーIDはDBで自動採番する
	 * パスワードには、ハッシュ化済みの値を設定して渡す
	 *
	 * @param user 登録するユーザー情報
	 * @return 登録件数
	 */
	int insert(UserInformation user);
	
	/**
	 * ユーザーのパスワードを更新する。
	 *
	 * @param userId 更新対象のユーザーID
	 * @param encodedPassword ハッシュ化済みのパスワード
	 * @return 更新件数
	 */
	int updatePassword(Long userId, String encodedPassword);
}
