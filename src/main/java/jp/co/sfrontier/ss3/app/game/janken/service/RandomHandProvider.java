package jp.co.sfrontier.ss3.app.game.janken.service;

import java.util.Random;

import org.springframework.stereotype.Component;

import jp.co.sfrontier.ss3.app.game.janken.domain.Hand;

/**
 * ランダムなじゃんけんの手を提供するクラス
 */
@Component
public class RandomHandProvider {

	private final Random random = new Random();

	/**
	 * ランダムにじゃんけんの手を生成する
	 * 
	 * @return じゃんけんの手
	 */
	public Hand generateHand() {
		// values()で定数のを宣言順に並べた配列を返す
		Hand[] hands = Hand.values();

		int index = random.nextInt(hands.length);

		return hands[index];
	}
}
