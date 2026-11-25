package jp.co.sfrontier.ss3.app.game.lookoverthere.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sfrontier.ss3.app.auth.service.LoginUserDetails;
import jp.co.sfrontier.ss3.app.game.lookoverthere.domain.Direction;
import jp.co.sfrontier.ss3.app.game.lookoverthere.domain.LookOverThereResult;
import jp.co.sfrontier.ss3.app.game.lookoverthere.service.LookOverThereService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * あっちむいてほいの画面を制御するクラス
 */
@Slf4j
@Controller
@RequestMapping("/lookoverthere")
@RequiredArgsConstructor
public class LookOverThereController {

	private final LookOverThereService service;
	
	/**
	 * 対戦画面を表示する
	 * @return 画面テンプレート名
	 */
	@GetMapping("/play")
	public String show(Model model) {

		log.debug("Game Start");
		
		model.addAttribute("activeMenu", "lookoverthere");

		return "lookoverthere/play";
	}
	
	/**
	 * プレイヤーの方向を受け取り、結果をJSONで返す
	 *
	 * @param playerDirection プレイヤーが選択した方向
	 * @param loginUser ログイン中のユーザー情報
	 * @return 両者の方向と勝敗
	 */
	@PostMapping("/play")
	@ResponseBody
	public LookOverThereResult play(@RequestParam("direction") Direction playerDirection, @AuthenticationPrincipal LoginUserDetails loginUser) {

		
		// ログイン情報からプレイヤーのIDを取得する
	    Long playerId = loginUser.getUser().getUserId();

		return service.play(playerId,playerDirection);
	}
}
