document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("checkInForm");
  const modalOverlay = document.getElementById("modalOverlay");
  const modal = document.getElementById("modal");

  function updateDateTime() {
  const now = new Date();
  const weekdays = ["日","月","火","水","木","金","土"];
  const dateStr = `${now.getFullYear()}年${String(now.getMonth()+1).padStart(2,'0')}月${String(now.getDate()).padStart(2,'0')}日（${weekdays[now.getDay()]}）`;
  const timeStr = `${String(now.getHours()).padStart(2,'0')}:${String(now.getMinutes()).padStart(2,'0')}.${String(now.getSeconds()).padStart(2,'0')}`;

  document.getElementById("date").textContent = dateStr;
  document.getElementById("clock").textContent = timeStr;
}

setInterval(updateDateTime, 1000);
updateDateTime();

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
