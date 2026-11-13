package jp.co.sfrontier.ss3.app.history.dto;

import java.util.List;

import lombok.Getter;

/**
 * 対戦履歴１ページ分の情報
 */
@Getter
public class HistoryPage {

	/**
	 * 条件に合致する 全履歴
	 */
	private List<HistoryRow> rows;

	/**
	 * 現在のページ番号
	 */
	private int page;

	/**
	 * 1ページあたりの表示件数（20）
	 */
	private int size;

	/**
	 * 条件に合致する 全履歴件数
	 */
	private long totalCount;

	/**
	 * 総件数と1ページあたりの表示件数から、
	 * 全ページ数を計算して返す。
	 *
	 * @return 全ページ数
	 */
	public int getTotalPages() {
		return (int) ((totalCount + size - 1) / size);
	}

	public HistoryPage(List<HistoryRow> rows, int page, int size, long totalCount) {
		super();
		this.rows = rows;
		this.page = page;
		this.size = size;
		this.totalCount = totalCount;
	}
}
