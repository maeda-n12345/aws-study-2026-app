package jp.co.sfrontier.ss3.app.game.lookoverthere.service;

import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.app.common.GameResult;
import jp.co.sfrontier.ss3.app.game.lookoverthere.domain.Direction;
import jp.co.sfrontier.ss3.app.game.lookoverthere.domain.LookOverThereResult;
import jp.co.sfrontier.ss3.app.game.lookoverthere.domain.LookOverThereRule;
import lombok.RequiredArgsConstructor;

/**
 * あっちむいてほいの対戦処理を行うクラス
 */
@Service
@RequiredArgsConstructor
public class LookOverThereService {
	
	// DBに登録されているCPUのユーザーID
	private static final Long CPU_USER_ID = 0L;
	
	private final LookOverThereHistoryRecorder historyRecorder;
	
	private final LookOverThereRule rule = new LookOverThereRule();
	
	private final RandomDirectionProvider directionProvider;
	

	
	/**
	 * プレイヤーとCPUの対戦を実行する
	 *
	 * @param playerId プレイヤーのユーザーID
	 * @param playerHand プレイヤーが選択した方向
	 * @return 両者の方向と勝敗
	 */
	public LookOverThereResult play(Long playerId, Direction playerDirectiond) {

	    // 対戦相手の方向を生成する
		Direction opponentDirection = directionProvider.generateDirection();

	    // 既存のメソッドで勝敗判定と履歴保存を行う
	    return play(playerId,CPU_USER_ID,playerDirectiond,opponentDirection);
	}

	/**
	 * プレイヤーの方向を受け取り、対戦を実行する
	 * <br>対戦後は対戦履歴を保存する
	 * 
	 * @param playerHand プレイヤーが選択した方向
	 * @param opponentHand 対戦相手が選択した方向
	 * @return 両者の方向と勝敗
	 */
	public LookOverThereResult play(Long playerId, Long opponentId, Direction playerDirectiond,Direction opponentDirectiondd) {

		// 勝敗を取得する
		GameResult gameResult = rule.judge(playerDirectiond, opponentDirectiondd);

		// あっちむいてほいの対戦結果を取得する
		LookOverThereResult LookOverThereResult = new LookOverThereResult(playerDirectiond, opponentDirectiondd, gameResult);

		// あっちむいてほいの対戦履歴を保存する
		historyRecorder.record(playerId, opponentId, LookOverThereResult);

		// 画面に表示する対戦結果情報を返す
		return LookOverThereResult;
	}
}
