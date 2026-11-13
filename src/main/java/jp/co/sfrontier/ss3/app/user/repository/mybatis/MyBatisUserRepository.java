package jp.co.sfrontier.ss3.app.user.repository.mybatis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sfrontier.ss3.app.user.entity.UserInformation;
import jp.co.sfrontier.ss3.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * MyBatisを使用してユーザー情報を取得するためのクラス
 */
@Repository
@RequiredArgsConstructor
public class MyBatisUserRepository implements UserRepository {

	private final UserMapper mapper;

	/**
	 * ユーザー名またはメールアドレスに一致するユーザーを取得する。
	 *
	 * @param userName 検索するユーザー名
	 * @param mailAddress 検索するメールアドレス
	 * @return 一致するユーザー。存在しない場合はOptional.empty()
	 */
	@Override
	public Optional<UserInformation> findByUserNameOrMailAddress(String userNameOrMailAddress) {

		// SQLの実行、検索結果のエンティティへの変換はMybatisが担う
		return Optional.ofNullable(mapper.findByUserNameOrMailAddress(userNameOrMailAddress));
	}

	/**
	 * 指定したユーザー名が登録済みか確認する
	 *
	 * @param userName 確認するユーザー名
	 * @return 登録済みならtrue
	 */
	@Override
	public boolean existsByUserName(String userName) {
		return mapper.existsByUserName(userName);
	}

	/**
	 * 指定したメールアドレスが登録済みか確認する
	 *
	 * @param mailAddress 確認するメールアドレス
	 * @return 登録済みならtrue
	 */
	@Override
	public boolean existsByMailAddress(String mailAddress) {
		return mapper.existsByMailAddress(mailAddress);
	}

	/**
	 * ユーザーを新規登録する
	 *
	 * ユーザーIDはDBで自動採番する
	 * パスワードには、ハッシュ化済みの値を設定して渡す
	 *
	 * @param user 登録するユーザー情報
	 * @return 登録件数
	 */
	@Override
	public int insert(UserInformation user) {
		return mapper.insert(user);
	}

	/**
	 * ユーザーのパスワードを更新する。
	 *
	 * @param userId 更新対象のユーザーID
	 * @param encodedPassword ハッシュ化済みのパスワード
	 * @return 更新件数
	 */
	@Override
	public int updatePassword(Long userId, String encodedPassword) {
		return mapper.updatePassword(userId, encodedPassword);
	}

}
