package jp.co.sfrontier.ss3.app.common;

/**
 * ゲームの勝敗を表すenum
 */
public enum GameResult {

	WIN(0), LOSE(1), DRAW(2);

	private final int id;

	private GameResult(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	/**
	 * 判定結果を反転する<br>
	 * <br>
	 * 
	 * @return 対戦相手の勝敗
	 */
	public GameResult reverse() {

		return switch (this) {

		case WIN -> LOSE;

		case LOSE -> WIN;

		case DRAW -> DRAW;

		};
	}
}
