package jp.co.sfrontier.ss3.app.game.lookoverthere.domain;

import java.util.Objects;

import jp.co.sfrontier.ss3.app.common.GameResult;

/**
 * あっちむいてほいの勝敗を判定するクラス。
 *
 * プレイヤーが指した方向と、相手が向いた方向を比較する。
 */
public class LookOverThereRule {

	/**
	* プレイヤー側の勝敗を返す。
	*
	* 方向が同じなら勝ち、異なれば負けとする。
	* このゲームでは引き分けは発生しない。
	*
	* @param playerDirection プレイヤーが指した方向
	* @param opponentDirection 対戦相手が向いた方向
	* @return プレイヤー側の勝敗
	* @throws NullPointerException いずれかの方向がnullの場合
	*/
	public GameResult judge(Direction playerDirection, Direction opponentDirection) {

		// 未指定の方向で判定されることを防ぐ
		Objects.requireNonNull(playerDirection, "playerDirection は必須です");

		Objects.requireNonNull(opponentDirection, "opponentDirection は必須です");

		// 同じ方向ならプレイヤーの勝ち
		if (playerDirection == opponentDirection) {
			return GameResult.WIN;
		}

		// 異なる方向ならプレイヤーの負け
		return GameResult.LOSE;
	}
}
