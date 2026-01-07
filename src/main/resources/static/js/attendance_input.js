document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("checkInForm");
  const modalOverlay = document.getElementById("modalOverlay");
  const modal = document.getElementById("modal");

// 日付だけ更新
function updateDateTime() {
  const now = new Date();
  const weekdays = ["日","月","火","水","木","金","土"];
  const dateStr = `${now.getFullYear()}年${String(now.getMonth()+1).padStart(2,'0')}月${String(now.getDate()).padStart(2,'0')}日（${weekdays[now.getDay()]}）`;

  document.getElementById("date").textContent = dateStr;
}

// 時刻（HH:MM と 秒）を更新
function updateClock() {
    const now = new Date();
    const h = String(now.getHours()).padStart(2, "0");
    const m = String(now.getMinutes()).padStart(2, "0");
    const s = String(now.getSeconds()).padStart(2, "0");

    document.getElementById("clock-hours").textContent = `${h}:${m}`;
    document.getElementById("clock-seconds").textContent = `:${s}`;
}

// 実行
setInterval(updateDateTime, 1000);
updateDateTime();

setInterval(updateClock, 1000);
updateClock();

  form.addEventListener("submit", (e) => {
    e.preventDefault(); // ページリロードを防ぐ

    // モーダル表示
    modalOverlay.style.display = "block";
    modal.style.display = "block";

    // モーダル3秒後に非表示
    setTimeout(() => {
      modalOverlay.style.display = "none";
      modal.style.display = "none";
    
    //遷移先ページ
      window.location.href = "/attendance_status.html";
    }, 3000);
  });
});