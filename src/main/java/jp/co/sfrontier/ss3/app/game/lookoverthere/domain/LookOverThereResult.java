package jp.co.sfrontier.ss3.app.game.lookoverthere.domain;

import java.util.Objects;

import jp.co.sfrontier.ss3.app.common.GameResult;

/**
 * 両者の方向とあっちむいてほいの勝敗
 */
public record LookOverThereResult(Direction playerDirection,Direction opponentDirection,GameResult gameResult) {

	public LookOverThereResult {
		Objects.requireNonNull(playerDirection,"playerDirection は必須です");
		Objects.requireNonNull(opponentDirection,"opponentDirection は必須です");
		Objects.requireNonNull(gameResult,"gameResult は必須です");
	}
}
