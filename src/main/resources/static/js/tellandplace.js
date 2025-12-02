// 戻るボタン（前のページへ戻る）
document.addEventListener("DOMContentLoaded", () => {
  const backBtn = document.getElementById("back-btn");

  if (backBtn) {
    backBtn.addEventListener("click", () => {
      history.back();
    });
  }
});
