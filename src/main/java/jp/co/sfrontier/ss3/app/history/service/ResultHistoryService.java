package jp.co.sfrontier.ss3.app.history.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sfrontier.ss3.app.history.entity.ResultHistory;
import jp.co.sfrontier.ss3.app.history.repository.ResultHistoryRepository;
import lombok.RequiredArgsConstructor;

/**
 * 対戦履歴の保存を行うサービスクラス
 */
@Service
@RequiredArgsConstructor
public class ResultHistoryService {

	private final ResultHistoryRepository repository;

	/**
	 * 対戦者の全員分の履歴をまとめて保存する
	 * 
	 * @param playerHistory プレイヤー側の履歴
	 * @param opponentHistory  対戦相手側の履歴
	 */
	@Transactional
	public void save(ResultHistory playerHistory, ResultHistory opponentHistory) {
		Objects.requireNonNull(playerHistory, "playerHistory は必須です");
		Objects.requireNonNull(opponentHistory, "opponentHistory は必須です");

		repository.save(playerHistory);
		repository.save(opponentHistory);
	}
}
