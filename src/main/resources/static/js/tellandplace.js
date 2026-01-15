document.addEventListener("DOMContentLoaded", () => {
  const backBtn = document.getElementById("back-btn");

  if (backBtn) {
    backBtn.addEventListener("click", (e) => {
      e.preventDefault();
      history.back();
    });
  }

  window.showModal = function() {
    const modalOverlay = document.getElementById("modalOverlay");
    const modal = document.getElementById("modal");

    if (modalOverlay && modal) {
      modalOverlay.style.display = "block";
      modal.style.display = "block";
    }
  };

});


