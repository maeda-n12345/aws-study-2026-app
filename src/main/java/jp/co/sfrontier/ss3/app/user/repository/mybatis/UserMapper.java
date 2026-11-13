package jp.co.sfrontier.ss3.app.user.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sfrontier.ss3.app.user.entity.UserInformation;

/**
 * ユーザー情報のSQLを実行するMapper
 */
@Mapper
public interface UserMapper {

	/**
	 * ユーザ名またはメールアドレスで検索する
	 *
	 * @param userNameOrMailAddress ユーザー名またはメールアドレス
	 * @return 一致するユーザー
	 */
	UserInformation findByUserNameOrMailAddress(@Param("userNameOrMailAddress") String userNameOrMailAddress);

	/**
	 * 指定したユーザー名が登録済みか確認する
	 *
	 * @param userName 確認するユーザー名
	 * @return 登録済みならtrue
	 */
	boolean existsByUserName(@Param("userName") String userName);

	/**
	 * 指定したメールアドレスが登録済みか確認する
	 *
	 * @param mailAddress 確認するメールアドレス
	 * @return 登録済みならtrue
	 */
	boolean existsByMailAddress(@Param("mailAddress") String mailAddress);

	/**
	 * ユーザーを新規登録する
	 *
	 * ユーザーIDはDBで自動採番する
	 * パスワードには、ハッシュ化済みの値を設定して渡す
	 *
	 * @param user 登録するユーザー情報
	 * @return 登録件数
	 */
	int insert(@Param("user") UserInformation user);

	/**
	 * 指定したユーザーのパスワードを更新する。
	 *
	 * @param userId 更新対象のユーザーID
	 * @param encodedPassword ハッシュ化済みのパスワード
	 * @return 更新件数
	 */
	int updatePassword(@Param("userId") Long userId, @Param("encodedPassword") String encodedPassword);
}
