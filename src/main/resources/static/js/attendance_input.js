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
  // const workplaceSelect = document.getElementById("workplace");

  form.addEventListener("submit", (e) => {
    e.preventDefault(); // ページリロードを防ぐ

    // モーダル表示
    modalOverlay.style.display = "block";
    modal.style.display = "block";

    // モーダル3秒後に非表示
    setTimeout(() => {
      modalOverlay.style.display = "none";
      modal.style.display = "none";
    
    // //遷移先ページ
    //   window.location.href = `/stamp?workplace=${encodeURIComponent(workplaceId)}`;
      form.submit();
  }, 1000);
  });
});
