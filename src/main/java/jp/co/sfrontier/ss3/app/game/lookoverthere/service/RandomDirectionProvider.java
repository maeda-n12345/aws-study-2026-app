package jp.co.sfrontier.ss3.app.game.lookoverthere.service;

import java.util.Random;

import org.springframework.stereotype.Component;

import jp.co.sfrontier.ss3.app.game.lookoverthere.domain.Direction;

/**
 * ランダムな方向を提供するクラス
 */
@Component
public class RandomDirectionProvider {

	private final Random random = new Random();

	/**
	 * ランダムにあっちむいてほいの方向を生成する
	 * 
	 * @return あっちむいてほいの方向
	 */
	public Direction generateDirection() {
		// values()で定数のを宣言順に並べた配列を返す
		Direction[] directions = Direction.values();

		int index = random.nextInt(directions.length);

		return directions[index];
	}
}
