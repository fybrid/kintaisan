document.addEventListener("DOMContentLoaded", () => {
  const backBtn = document.getElementById("back-btn");

  if (backBtn) {
    backBtn.addEventListener("click", (e) => {
      e.preventDefault();
      history.back();
    });
  }

  // モーダルの表示
  const modalOverlay = document.getElementById("modalOverlay");
  const modal = document.getElementById("modal");
  const form = document.getElementById("updateForm");
  const selectBox = document.getElementById("workplace");
  const contactInput = document.getElementById("contact");
  const error = document.getElementById("errorMessage");

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    if (selectBox.value === "") {
      error.textContent = "勤務地を選択してください";
      return;
    }
    // 連絡先のチェックをする
    if (contactInput.value.trim() === "") {
      error.textContent = "連絡先を入力してください";
      return;
    }
    if (!/^[0-9\- ]+$/.test(contactInput.value.trim())) {
      error.textContent = "連絡先は数字、ハイフン、スペースのみで入力してください";
      return;
    }
    if (contactInput.value.trim().length < 10 || contactInput.value.trim().length > 11) {
      error.textContent = "連絡先は10桁から11桁で入力してください";
      return;
    }
    if (!contactInput.value.trim().startsWith("0")) {
      error.textContent = "連絡先は0から始まる番号を入力してください";
      return;
    }

    // ▼ モーダル表示
    modalOverlay.style.display = "block";
    modal.style.display = "block";

    setTimeout(() => {
      modalOverlay.style.display = "none";
      modal.style.display = "none";
      form.submit();
    }, 1000);
  });

  // window.showModal = function() {
  //   const modalOverlay = document.getElementById("modalOverlay");
  //   const modal = document.getElementById("modal");

  //   if (modalOverlay && modal) {
  //     modalOverlay.style.display = "block";
  //     modal.style.display = "block";
  //   }
  // };

});


