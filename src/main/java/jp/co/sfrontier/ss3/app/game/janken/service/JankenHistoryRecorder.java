package jp.co.sfrontier.ss3.app.game.janken.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.app.common.GameResult;
import jp.co.sfrontier.ss3.app.common.GameType;
import jp.co.sfrontier.ss3.app.game.janken.domain.Hand;
import jp.co.sfrontier.ss3.app.game.janken.domain.JankenResult;
import jp.co.sfrontier.ss3.app.history.entity.ResultHistory;
import jp.co.sfrontier.ss3.app.history.service.ResultHistoryService;
import lombok.RequiredArgsConstructor;

/**
 * じゃんけんの対戦履歴を提供するクラス
 */
@Service
@RequiredArgsConstructor
public class JankenHistoryRecorder {
	
	private final ResultHistoryService historyService;

	/**
	 * 両者の履歴を作成し、保存する
	 *
	 * @param playerId プレイヤーのユーザーID
	 * @param opponentId 対戦相手のユーザーID
	 * @param result 対戦結果。勝敗はプレイヤー側の視点
	 */
	public void record(Long playerId,Long opponentId,JankenResult result) {

		Objects.requireNonNull(playerId, "playerId は必須です");
		Objects.requireNonNull(opponentId, "opponentId は必須です");
		Objects.requireNonNull(result, "result は必須です");

		LocalDateTime now = LocalDateTime.now();

		ResultHistory playerHistory = createHistory(playerId,opponentId,result.playerHand(),result.gameResult(),now);

		// 相手側の履歴では、自分と相手のIDを入れ替え、手と勝敗も相手側の視点にする
		ResultHistory opponentHistory = createHistory(opponentId,playerId,result.opponentHand(),result.gameResult().reverse(),now);

		historyService.save(playerHistory, opponentHistory);
	}

	/**
	 * 1ユーザー視点の履歴データを作成する。
	 */
	private ResultHistory createHistory(Long userId, Long opponentId, Hand hand, GameResult gameResult,LocalDateTime now) {

		return ResultHistory.builder()
				.userId(userId)
				.opponentUserId(opponentId)
				.gameType(GameType.JANKEN)
				.gameResult(gameResult)
				.userChoice(hand.name())
				.executeDatetime(now)
				.createDatetime(now)
				.updateDatetime(now)
				.version(1)
				.build();
	}
}
