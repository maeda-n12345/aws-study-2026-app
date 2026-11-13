package jp.co.sfrontier.ss3.app.game.janken.domain;

import java.util.Objects;

import jp.co.sfrontier.ss3.app.common.GameResult;

/**
 * じゃんけんゲーム固有の処理を提供するクラス<br>
 * <br>
 */
public class JankenRule {

	/**
	 * プレイヤー側の勝敗を返す
	 * 
	 * @param playerHand プレイヤーが選択した手
	 * @param opponentHand 対戦相手が選択した手
	 * @return 勝敗
	 */
	public GameResult judge(Hand playerHand, Hand opponentHand) {

		Objects.requireNonNull(playerHand, "playerHand は必須です");
		Objects.requireNonNull(opponentHand, "opponentHand は必須です");

		// 引き分け
		if (playerHand == opponentHand) {
			return GameResult.DRAW;
		}

		// player の勝ち
		if (playerHand == Hand.ROCK && opponentHand == Hand.SCISSORS) {
			return GameResult.WIN;
		}

		if (playerHand == Hand.SCISSORS && opponentHand == Hand.PAPER) {
			return GameResult.WIN;
		}

		if (playerHand == Hand.PAPER && opponentHand == Hand.ROCK) {
			return GameResult.WIN;
		}

		// 引き分けでも勝ちでもなければ負け
		return GameResult.LOSE;
	}
}
