document.addEventListener("DOMContentLoaded", () => {
  // 日付表示 
  // 2025年12月31日（木）のように表示したい
  const date = new Date();
  const formatter = new Intl.DateTimeFormat("ja-JP", {
    year: "numeric",
    month: "long",
    day: "numeric",
    weekday: "short" // "木"みたいに1文字にしてくれてうれしい
  }).format(date).replace("(", "（").replace(")", "）");
  document.getElementById("date").textContent = formatter;

  const form = document.getElementById("checkInForm");
  const modalOverlay = document.getElementById("modalOverlay");
  const modal = document.getElementById("modal");
  const selectBox = document.getElementById("workplaceId");
  const alertAreaSelect = document.getElementById("alertAreaSelect");

  // ▼ 日付更新
  function updateDateTime() {
    const now = new Date();
    const weekdays = ["日","月","火","水","木","金","土"];
    const dateStr = `${now.getFullYear()}年${String(now.getMonth()+1).padStart(2,'0')}月${String(now.getDate()).padStart(2,'0')}日（${weekdays[now.getDay()]}）`;
    document.getElementById("date").textContent = dateStr;
  }

  // ▼ 時刻更新
  function updateClock() {
    const now = new Date();
    const h = String(now.getHours()).padStart(2, "0");
    const m = String(now.getMinutes()).padStart(2, "0");
    const s = String(now.getSeconds()).padStart(2, "0");

    document.getElementById("clock-hours").textContent = `${h}:${m}`;
    document.getElementById("clock-seconds").textContent = `:${s}`;
  }

  setInterval(updateDateTime, 1000);
  updateDateTime();
  setInterval(updateClock, 1000);
  updateClock();

  // ▼ フォーム送信時のバリデーション
  form.addEventListener("submit", (e) => {
    if (selectBox.value === "") {
      e.preventDefault();
      alertAreaSelect.textContent = "勤務地を選択してください";
      return;
    }

    // ▼ モーダル表示
    e.preventDefault();
    modalOverlay.style.display = "block";
    modal.style.display = "block";

    setTimeout(() => {
      modalOverlay.style.display = "none";
      modal.style.display = "none";
      // window.location.href = "/attendance_status";
      form.submit();
    }, 1000);
  });

  // ▼ セレクト変更時にエラー解除
  // その前にifでselectBoxがあるか確認
  if (selectBox)
  selectBox.addEventListener("change", () => {
    if (selectBox.value !== "") {
      alertAreaSelect.textContent = "";
    }
  });

  // ▼ ページ表示時（初回 + 戻る時）に5秒後エラー表示
window.addEventListener("pageshow", () => {
  const selectBoxNow = document.getElementById("workplace");
  const alertAreaNow = document.getElementById("alertAreaSelect");

  // ▼ 選択状態を強制リセット
  // selectBoxNow.value = "";

  // ▼ 5秒後にエラー表示
  setTimeout(() => {
    console.log("戻ってきたときの値:", selectBoxNow.value);
    if (selectBoxNow.value === "") {
      alertAreaNow.textContent = "勤務地を選択してください";
      console.log("エラー表示しました");
    } else {
      console.log("選択済みなのでエラー出しません");
    }
  }, 5000);
});
});