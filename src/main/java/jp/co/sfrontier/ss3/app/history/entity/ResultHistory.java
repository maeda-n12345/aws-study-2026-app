package jp.co.sfrontier.ss3.app.history.entity;

import java.time.LocalDateTime;

import jp.co.sfrontier.ss3.app.common.GameResult;
import jp.co.sfrontier.ss3.app.common.GameType;
import lombok.Builder;
import lombok.Getter;

/**
 * RESULT_HISTORY_TBL 用のEntity<br>
 * <br>
 */
@Getter
@Builder
public class ResultHistory {

	private final Long historyId;

	private final Long userId;

	private final Long opponentUserId;
	
	private final GameType gameType;

	private final GameResult gameResult;

	private final LocalDateTime executeDatetime;

	private final String userChoice;

	private final LocalDateTime createDatetime;

	private final LocalDateTime updateDatetime;

	private final Integer version;
}
