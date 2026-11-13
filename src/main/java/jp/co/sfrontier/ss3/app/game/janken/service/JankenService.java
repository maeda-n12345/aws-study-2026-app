package jp.co.sfrontier.ss3.app.game.janken.service;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.app.common.GameResult;
import jp.co.sfrontier.ss3.app.game.janken.domain.Hand;
import jp.co.sfrontier.ss3.app.game.janken.domain.JankenResult;
import jp.co.sfrontier.ss3.app.game.janken.domain.JankenRule;
import lombok.RequiredArgsConstructor;

/**
 * じゃんけんの対戦処理を行うクラス
 */
@Service
@RequiredArgsConstructor
public class JankenService {
	
	// DBに登録されているCPUのユーザーID
	private static final Long CPU_USER_ID = 0L;

	private final JankenHistoryRecorder historyRecorder;

	private final JankenRule rule = new JankenRule();
	
	private final RandomHandProvider randomHandProvider;
	
	/**
	 * プレイヤーとCPUの対戦を実行する
	 *
	 * @param playerId プレイヤーのユーザーID
	 * @param playerHand プレイヤーが選択した手
	 * @return 両者の手と勝敗
	 */
	public JankenResult play(Long playerId, Hand playerHand) {

	    // 対戦相手の手を生成する
	    Hand opponentHand = randomHandProvider.generateHand();

	    // 既存のメソッドで勝敗判定と履歴保存を行う
	    return play(playerId,CPU_USER_ID,playerHand,opponentHand);
	}

	/**
	 * プレイヤーの手を受け取り、対戦を実行する
	 * <br>対戦後は対戦履歴を保存する
	 * 
	 * @param playerHand プレイヤーが選択した手
	 * @param opponentHand 対戦相手が選択した手
	 * @return 両者の手と勝敗
	 */
	public JankenResult play(Long playerId, Long opponentId, Hand playerHand, Hand opponentHand) {

		// 勝敗を取得する
		GameResult gameResult = rule.judge(playerHand, opponentHand);

		// じゃんけんの対戦結果を取得する
		JankenResult jankenResult = new JankenResult(playerHand, opponentHand, gameResult);

		// じゃんけんの対戦履歴を保存する
		historyRecorder.record(playerId, opponentId, jankenResult);

		// 画面に表示する対戦結果情報を返す
		return jankenResult;
	}
}
