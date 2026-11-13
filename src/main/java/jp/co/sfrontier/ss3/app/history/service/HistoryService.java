package jp.co.sfrontier.ss3.app.history.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.app.auth.service.LoginUserDetails;
import jp.co.sfrontier.ss3.app.common.GameType;
import jp.co.sfrontier.ss3.app.history.dto.HistoryPage;
import jp.co.sfrontier.ss3.app.history.dto.HistoryRow;
import jp.co.sfrontier.ss3.app.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;

/**
 * 対戦履歴に関する業務処理を提供する Service クラス。
 *
 * <p>
 * ログインユーザーの権限（管理者 / 一般ユーザー）を考慮し、
 * 取得対象のユーザーIDを決定した上で、
 * 対戦履歴の検索およびページング処理を行う。
 * </p>
 *
 * <p>
 * ページング処理では、総件数取得（COUNT）と
 * 指定ページ分の履歴取得を組み合わせて結果を返す。
 * </p>
 */
@Service
@RequiredArgsConstructor
public class HistoryService {

	private final HistoryRepository repository;

	/*
	 *  ログインユーザーが参照できる履歴を取得する
	 */
	public HistoryPage findPage(LoginUserDetails loginUser, GameType gameType, int page, int size) {
		// ログインしたユーザが ROLE_ADMIN を持っているか判定する
		boolean isAdmin = loginUser.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

		Long userId;

		// ログインユーザーの権限をチェックする
		if (isAdmin) {
			// 管理者は全ユーザーの履歴を検索する
			userId = null;
		} else {
			// 一般ユーザーは自分の履歴だけを検索する
			userId = Objects.requireNonNull(loginUser.getUser().getUserId());
		}

		// 履歴画面表示に必要な情報
		return gameType == null ? findPageAll(userId, page, size) : findPageByGameType(userId, gameType, page, size);
	}

	/**
	 * 対戦履歴をページングして取得する（全ゲーム種別）。
	 */
	private HistoryPage findPageAll(Long userId, int page, int size) {

		return findPageByCondition(userId, null, page, size);
	}

	/**
	 * 指定したゲーム種別の対戦履歴をページングして取得する。
	 */
	private HistoryPage findPageByGameType(Long userId, GameType gameType, int page, int size) {

		return findPageByCondition(userId, gameType, page, size);
	}

	/**
	 *  指定条件で件数と履歴を取得する<br>
	 *  全ゲーム検索とゲーム別検索で、ページング処理を共有する。
	 * 
	 * @return 検索結果
	 */
	private HistoryPage findPageByCondition(Long userId, GameType gameType, int page, int size) {

		//ログインユーザの履歴の総件数を取得
		long totalCount = repository.countHistoryRows(userId, gameType);

		//page が 1 未満にならないようにする
		int safePage = Math.max(1, page);

		//「何件目から取得するか」を計算する
		int offset = (safePage - 1) * size;

		//条件に合致する 全履歴を取得し、HistoryRow型のリストに格納する
		List<HistoryRow> rows = repository.findPage(userId, gameType, offset, size);

		return new HistoryPage(rows, safePage, size, totalCount);
	}

}
