package jp.co.sfrontier.ss3.app.history.repository.mybatis;

import org.springframework.stereotype.Repository;

import jp.co.sfrontier.ss3.app.history.entity.ResultHistory;
import jp.co.sfrontier.ss3.app.history.repository.ResultHistoryRepository;
import lombok.RequiredArgsConstructor;

/**
 * MyBatisを使用して対戦履歴を保存するRepository
 */
@Repository
@RequiredArgsConstructor
public class MyBatisResultHistoryRepository implements ResultHistoryRepository {

	private final ResultHistoryMapper mapper;

	/**
	 * 対戦履歴を1件保存する。
	 *
	 * トランザクションは呼び出し元のResultHistoryServiceで管理し、
	 * プレイヤーと対戦相手の履歴をまとめて確定する。
	 *
	 * @param history 保存する対戦履歴
	 */
	@Override
	public void save(ResultHistory history) {

		// Mapper XMLに定義したINSERT文を実行する
		int insertedRows = mapper.insert(history);

		// 想定どおり1件登録されなかった場合は、処理を失敗させる
		if (insertedRows != 1) {
			throw new IllegalStateException(
					"対戦履歴の登録件数が不正です: " + insertedRows);
		}
	}
}
