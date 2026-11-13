let winStreak = 0;

function handleSelection(hand) {
  const buttons = document.querySelectorAll('input[name="hand"]');

  buttons.forEach(button => button.classList.remove('selected'));
  document.getElementById(hand).classList.add('selected');
}

function playGame() {
  const selectedHand =
    document.querySelector('input[name="hand"]:checked');

  if (!selectedHand) {
    alert('手を選択してください');
    return;
  }

  const submitButton = document.getElementById('submit-button');

  submitButton.disabled = true;
  submitButton.style.display = 'none';

  const headers = {
    'Content-Type': 'application/x-www-form-urlencoded'
  };

  // メタタグが存在する場合だけCSRFヘッダーを追加する
  const csrfToken =
    document.querySelector('meta[name="_csrf"]')?.content;

  const csrfHeader =
    document.querySelector('meta[name="_csrf_header"]')?.content;

  if (csrfToken && csrfHeader) {
    headers[csrfHeader] = csrfToken;
  }

  fetch('/janken/play', {
    method: 'POST',
    headers,
    body: new URLSearchParams({
      hand: selectedHand.value
    })
  })
    .then(response => {
      // HTTPステータスが成功か確認する
      if (!response.ok) {
        throw new Error(`HTTPエラー: ${response.status}`);
      }

      return response.json();
    })
    .then(data => {
      // 想定した対戦結果か確認する
      if (
        !['WIN', 'LOSE', 'DRAW'].includes(data.gameResult) ||
        !['ROCK', 'SCISSORS', 'PAPER'].includes(data.opponentHand)
      ) {
        throw new Error('対戦結果の形式が正しくありません');
      }

      const resultText = document.getElementById('result');
      const winStreakText = document.getElementById('win-streak');
      const opponentHandImg =
        document.getElementById('opponent-hand-img');
      const opponentHandContainer =
        document.getElementById('opponent-hand');

      // 対戦相手の手画像を表示する
      opponentHandImg.src =
        '/images/' + data.opponentHand + '.png';

      opponentHandContainer.style.display = 'block';

      if (data.gameResult === 'WIN') {
        resultText.innerText = 'あなたの勝ちです';
        winStreak++;
      } else if (data.gameResult === 'LOSE') {
        resultText.innerText = 'あなたの負けです';
        winStreak = 0;
      } else if (data.gameResult === 'DRAW') {
        resultText.innerText = '引き分けです';
        winStreak = 0;
      }

      winStreakText.innerText =
        winStreak > 0 ? winStreak + '連勝中！' : '';

      document.getElementById('retry-button').style.display =
        'inline-block';

      document.getElementById('exit-button').style.display =
        'inline-block';

      document.getElementById('game-sentence').style.display =
        'none';
    })
    .catch(error => {
      console.error(error);
      alert('通信エラー');

      // エラー時はもう一度操作できるように戻す
      submitButton.disabled = false;
      submitButton.style.display = 'inline-block';
    });
}

function retryGame() {
  const submitButton = document.getElementById('submit-button');

  submitButton.style.display = 'inline-block';
  submitButton.disabled = false;

  document.getElementById('result').innerText = '';
  document.getElementById('win-streak').innerText = '';
  document.getElementById('retry-button').style.display = 'none';
  document.getElementById('opponent-hand').style.display = 'none';
  document.getElementById('exit-button').style.display = 'none';
  document.getElementById('game-sentence').style.display =
    'inline-block';

  const buttons = document.querySelectorAll('input[name="hand"]');

  buttons.forEach(button => {
    button.checked = false;
    button.classList.remove('selected');
  });
}