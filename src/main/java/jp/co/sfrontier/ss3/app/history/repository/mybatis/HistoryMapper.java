package jp.co.sfrontier.ss3.app.history.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sfrontier.ss3.app.common.GameType;
import jp.co.sfrontier.ss3.app.history.dto.HistoryRow;

/**
 * 対戦履歴の検索SQLを実行するMapper
 */
@Mapper
public interface HistoryMapper {

	/**
	 * 指定条件に該当する対戦履歴の総件数を取得する。
	 *
	 * @param userId 対象ユーザーID（管理者の場合は null）
	 * @param gameTypeId ゲーム種別ID（全件の場合は null）
	 * @return 条件に一致する履歴の総件数
	 */
	long countHistoryRows(@Param("userId") Long userId,
			@Param("gameTypeId") GameType gameTypeId);
	
	/**
	 * 指定条件に該当する対戦履歴をページングして取得する。
	 *
	 * <p>
	 * 新しい履歴から順に並び替え、
	 * offset と limit を用いて指定ページ分のデータを取得する。
	 * </p>
	 *
	 * @param userId 対象ユーザーID（管理者の場合は null）
	 * @param gameTypeId ゲーム種別ID（全件の場合は null）
	 * @param offset 取得開始位置
	 * @param limit 取得件数
	 * @return 対戦履歴一覧（ページ分）
	 */
	List<HistoryRow> findPage(
			@Param("userId") Long userId,
			@Param("gameTypeId") GameType gameTypeId,
			@Param("offset") int offset,
			@Param("limit") int limit);
}
