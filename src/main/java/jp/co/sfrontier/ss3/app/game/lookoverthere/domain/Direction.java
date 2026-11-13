package jp.co.sfrontier.ss3.app.game.lookoverthere.domain;

/**
 * 「あっち向いてほい」の方向定義<br>
 * <br>
 */
public enum Direction {
	UP(1, "↑"),

	DOWN(2, "↓"),

	LEFT(3, "←"),

	RIGHT(4, "→"),
	;

	/** 方向に対応する数値 */
	private final int value;

	/** 画面表示用のラベル */
	private final String label;
	

	private Direction(int value, String label) {
		this.value = value;
		this.label = label;
	}

	/**
	 * 方向を表す値を int として取り出す。<br>
	 * <br>
	 * @return 方向を表す値
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 指定された値に該当する方向を取り出す。<br>
	 * <br>
	 * @return 方向を表す矢印
	 */
	public String getLabel() {
		return label;
	}
}
