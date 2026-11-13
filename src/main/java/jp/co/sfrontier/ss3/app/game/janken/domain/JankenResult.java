package jp.co.sfrontier.ss3.app.game.janken.domain;

import java.util.Objects;

import jp.co.sfrontier.ss3.app.common.GameResult;

/**
 * 両者の手と一回のじゃんけんの勝敗
 */
public record JankenResult(Hand playerHand, Hand opponentHand, GameResult gameResult) {

	public JankenResult {
		Objects.requireNonNull(playerHand,"playerHand は必須です");
		Objects.requireNonNull(opponentHand,"opponentHand は必須です");
		Objects.requireNonNull(gameResult,"gameResult は必須です");
	}
}
