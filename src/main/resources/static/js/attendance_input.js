document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("checkInForm");
  const modalOverlay = document.getElementById("modalOverlay");
  const modal = document.getElementById("modal");

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
