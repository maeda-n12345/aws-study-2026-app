package jp.co.sfrontier.ss3.app.game.janken.domain;

/**
 * じゃんけんの手の定義
 */
public enum Hand {
	
	ROCK(1,"グー"),
    SCISSORS(2,"チョキ"),
    PAPER(3,"パー");
	
	private final int value;
	private final String label;
	
	private Hand(int value,String label) {
		this.value=value;
		this.label=label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}
