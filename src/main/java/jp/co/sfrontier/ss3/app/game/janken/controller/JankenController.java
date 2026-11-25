package jp.co.sfrontier.ss3.app.game.janken.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sfrontier.ss3.app.auth.service.LoginUserDetails;
import jp.co.sfrontier.ss3.app.game.janken.domain.Hand;
import jp.co.sfrontier.ss3.app.game.janken.domain.JankenResult;
import jp.co.sfrontier.ss3.app.game.janken.service.JankenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * じゃんけんの画面を制御するクラス
 */
@Slf4j
@Controller
@RequestMapping("/janken")
@RequiredArgsConstructor
public class JankenController {

	private final JankenService service;

	/**
	 * 対戦画面を表示する
	 * @return 画面テンプレート名
	 */
	@GetMapping("/play")
	public String show(Model model) {

		log.debug("Game Start");

		model.addAttribute("activeMenu", "janken");

		return "janken/play";
	}

	/**
	 * プレイヤーの手を受け取り、結果をJSONで返す
	 * 
	 * @param playerHand プレイヤーが選択した手
	 * @param loginUser ログイン中のユーザー情報
	 * @return 両者の手と勝敗
	 */
	@PostMapping("/play")
	@ResponseBody
	public JankenResult play(@RequestParam("hand") Hand playerHand,
			@AuthenticationPrincipal LoginUserDetails loginUser) {

		// ログイン情報からプレイヤーのIDを取得する
		Long playerId = loginUser.getUser().getUserId();

		return service.play(playerId, playerHand);
	}
}
