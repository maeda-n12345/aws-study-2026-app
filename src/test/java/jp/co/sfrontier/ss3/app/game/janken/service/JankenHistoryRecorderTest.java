package jp.co.sfrontier.ss3.app.game.janken.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import jp.co.sfrontier.ss3.app.common.GameResult;
import jp.co.sfrontier.ss3.app.common.GameType;
import jp.co.sfrontier.ss3.app.game.janken.domain.Hand;
import jp.co.sfrontier.ss3.app.game.janken.domain.JankenResult;
import jp.co.sfrontier.ss3.app.history.entity.ResultHistory;
import jp.co.sfrontier.ss3.app.history.service.ResultHistoryService;

/**
 * じゃんけん対戦履歴の保存処理をテストするクラス
 */
public class JankenHistoryRecorderTest {

	/**
	* 両者のID・手・勝敗を、それぞれの視点で履歴に設定する。
	*/
	@Test
	void shouldCreateHistoriesForBothPlayers() {
		// 準備：実際のDB保存を行わないように、保存Serviceをモックにする。
		ResultHistoryService historyService = mock(ResultHistoryService.class);

		JankenHistoryRecorder recorder = new JankenHistoryRecorder(historyService);

		JankenResult result = new JankenResult(Hand.ROCK, Hand.SCISSORS, GameResult.WIN);

		// 実行：ユーザー5が、対戦相手0に勝った状況を渡す。
		// モックを使用するため、実DBにこのIDが存在する必要はない。
		recorder.record(5L, 0L, result);

		// 保存Serviceへ渡された2つの履歴を取り出すための入れ物。
		ArgumentCaptor<ResultHistory> playerCaptor = ArgumentCaptor.forClass(ResultHistory.class);

		ArgumentCaptor<ResultHistory> opponentCaptor = ArgumentCaptor.forClass(ResultHistory.class);

		// save()が1回呼ばれたことを確認し、両方の引数を取得する。
		verify(historyService).save(playerCaptor.capture(), opponentCaptor.capture());

		ResultHistory playerHistory = playerCaptor.getValue();
		ResultHistory opponentHistory = opponentCaptor.getValue();

		// プレイヤー側：ユーザー5が、グーで勝った履歴。
		assertEquals(Long.valueOf(5L), playerHistory.getUserId());
		assertEquals(Long.valueOf(0L), playerHistory.getOpponentUserId());
		assertEquals(GameType.JANKEN, playerHistory.getGameType());
		assertEquals("ROCK", playerHistory.getUserChoice());
		assertEquals(GameResult.WIN, playerHistory.getGameResult());

		// 相手側：IDを入れ替え、チョキで負けた履歴。
		assertEquals(Long.valueOf(0L), opponentHistory.getUserId());
		assertEquals(Long.valueOf(5L), opponentHistory.getOpponentUserId());
		assertEquals(GameType.JANKEN, opponentHistory.getGameType());
		assertEquals("SCISSORS", opponentHistory.getUserChoice());
		assertEquals(GameResult.LOSE, opponentHistory.getGameResult());

		// 同じ対戦なので、両者に同じ日時が設定されること。
		assertNotNull(playerHistory.getExecuteDatetime());
		assertEquals(playerHistory.getExecuteDatetime(), opponentHistory.getExecuteDatetime());

		// 両者の作成・更新日時と、初期バージョンを確認する。
		for (ResultHistory history : new ResultHistory[] { playerHistory, opponentHistory }) {

			assertEquals(history.getExecuteDatetime(), history.getCreateDatetime());

			assertEquals(history.getExecuteDatetime(), history.getUpdateDatetime());

			assertEquals(1, history.getVersion());
		}
	}
}
