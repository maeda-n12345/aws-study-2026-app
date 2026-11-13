package jp.co.sfrontier.ss3.app.history.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.sfrontier.ss3.app.common.GameType;
import jp.co.sfrontier.ss3.app.history.dto.HistoryRow;
import jp.co.sfrontier.ss3.app.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;

/**
 * MyBatisを使用して対戦履歴を検索するRepository
 */
@Repository
@RequiredArgsConstructor
public class MyBatisHistoryRepository implements HistoryRepository{
	
	private final HistoryMapper mapper;

	/**
	 * 検索結果に一致する履歴の総件数を取得する
	 */
	@Override
	public long countHistoryRows(Long userId, GameType gameTypeId) {
		
		return mapper.countHistoryRows(userId, gameTypeId);
	}

	/**
	 * 1ページに表示する履歴を取得する
	 */
	@Override
	public List<HistoryRow> findPage(Long userId, GameType gameTypeId, int offset, int limit) {
		
		return mapper.findPage(userId,gameTypeId,offset,limit);
	}

}
