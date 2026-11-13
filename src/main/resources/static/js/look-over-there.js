const playForm = document.getElementById("play-form");
const playScreen = document.getElementById("play-screen");
const resultScreen = document.getElementById("result-screen");
const battleButton = playForm.querySelector('button[type="submit"]');

const opponentImage = document.getElementById("result-opponent-img");

// 画像の配置フォルダーを、Thymeleafが生成したURLから取得する
const imageBaseUrl = new URL(".", opponentImage.src);

// enum名と、昨年度の表示ラベル・画像を対応付ける
const directions = {
    UP: { label: "↑", image: "face_up.png" },
    DOWN: { label: "↓", image: "face_down.png" },
    LEFT: { label: "←", image: "face_left.png" },
    RIGHT: { label: "→", image: "face_right.png" }
};

let isPlaying = false;

// 方向を矢印キーで選択し、Enterキーで送信する
document.addEventListener("keydown", function(event) {

    // 結果画面では、ボタン本来のキーボード操作を使用する
    if (playScreen.style.display === "none" || isPlaying) {
        return;
    }

    const directionIds = {
        ArrowUp: "dir-up",
        ArrowDown: "dir-down",
        ArrowLeft: "dir-left",
        ArrowRight: "dir-right"
    };

    const id = directionIds[event.key];

    if (id) {
        event.preventDefault();

        if (!event.repeat) {
            selectDirection(id);
        }
        return;
    }

    if (event.key === "Enter") {
        event.preventDefault();

        if (!event.repeat) {
            submitIfSelected();
        }
    }
});

// 指定した方向のラジオボタンを選択する
function selectDirection(id) {
    const radio = document.getElementById(id);

    if (radio) {
        radio.checked = true;
        radio.focus();
    }
}

// 方向を選択済みの場合だけ、フォームの送信処理を開始する
function submitIfSelected() {
    const selected = playForm.querySelector(
        'input[name="direction"]:checked'
    );

    if (selected) {
        playForm.requestSubmit();
    }
}

// 通常のページ遷移を止め、JSONで対戦結果を取得する
playForm.addEventListener("submit", async function(event) {
    event.preventDefault();

    if (isPlaying) {
        return;
    }

    const selected = playForm.querySelector(
        'input[name="direction"]:checked'
    );

    if (!selected) {
        alert("方向を選択してください");
        return;
    }

    isPlaying = true;
    battleButton.disabled = true;

    try {
        // 方向と、hidden項目のCSRFトークンをまとめて送信する
        const response = await fetch(playForm.action, {
            method: "POST",
            body: new URLSearchParams(new FormData(playForm))
        });

        if (!response.ok) {
            throw new Error(
                "対戦リクエストに失敗しました: " + response.status
            );
        }

        const result = await response.json();

        showResult(result);

    } catch (error) {
        console.error(error);
        alert("対戦処理に失敗しました");

    } finally {
        isPlaying = false;
        battleButton.disabled = false;
    }
});

// JSONの内容を、昨年度と同じ結果画面の構造へ反映する
function showResult(result) {
    const player = directions[result.playerDirection];
    const opponent = directions[result.opponentDirection];

    if (!player || !opponent ||
        !["WIN", "LOSE"].includes(result.gameResult)) {
        throw new Error("対戦結果が不正です");
    }

    opponentImage.src = new URL(
        opponent.image, imageBaseUrl
    ).href;

    document.getElementById("opponent-direction").textContent =
        opponent.label;

    document.getElementById("player-direction").textContent =
        player.label;

    const isWin = result.gameResult === "WIN";
    const resultText = document.getElementById("result-text");

    // 昨年度と同じCSSクラスで、勝敗に応じた装飾を適用する
    resultText.classList.remove("win", "lose", "draw");
    resultText.classList.add(isWin ? "win" : "lose");

    document.getElementById("result-label").textContent =
        isWin ? "勝ち！" : "負け！";

    // 結果を設定してから画面を切り替える
    playScreen.style.display = "none";
    resultScreen.style.display = "";
    document.title = "対戦結果";
}

// 「続ける」では通信せず、方向選択を初期化して対戦画面へ戻る
document.getElementById("retry-form")
    .addEventListener("submit", function(event) {
        event.preventDefault();

        playForm.reset();

        resultScreen.style.display = "none";
        playScreen.style.display = "";
        document.title = "あっちむいてほい";
    });