package jp.co.sfrontier.ss3.app.history.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sfrontier.ss3.app.history.entity.ResultHistory;

/**
 * 対戦履歴のSQLを実行するMapper
 */
@Mapper
public interface ResultHistoryMapper {

    /**
     * 対戦履歴を1件登録する
     *
     * @param history 登録する対戦履歴
     * @return 登録件数（正常に1件登録された場合は1）
     */
    int insert(@Param("history") ResultHistory history);
}
