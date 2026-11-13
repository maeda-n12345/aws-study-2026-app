package jp.co.sfrontier.ss3.app.history.repository;

import jp.co.sfrontier.ss3.app.history.entity.ResultHistory;

/**
 * 対戦履歴用のリポジトリ共通処理を定義するインタフェース<br>
 * <br>
 */
public interface ResultHistoryRepository {

	/**
	 * 対戦履歴を保存する
	 */
	void save(ResultHistory history);
}
