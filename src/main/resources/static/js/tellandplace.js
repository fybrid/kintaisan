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

  const errorFunc = (errorMessage) => {
    error.textContent = errorMessage;
    // errorの要素をscale(1.2)にしてから0.3sで元に戻すアニメーション
    error.style.transform = "scale(1.2)";
    error.style.transition = "transform 0.3s";
    setTimeout(() => {
      error.style.transform = "scale(1)";
    }, 300);
  };

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    if (selectBox.value === "") {
      errorFunc("勤務地を選択してください");
      return;
    }
    // 連絡先のチェックをする
    if (contactInput.value.trim() === "") {
      errorFunc("連絡先を入力してください");
      return;
    }
    if (!/^[0-9\- ]+$/.test(contactInput.value.trim())) {
      errorFunc("連絡先は数字、ハイフン、スペースのみで入力してください");
      return;
    }
    if (contactInput.value.trim().length < 10 || contactInput.value.trim().length > 11) {
      errorFunc("連絡先は10桁から11桁で入力してください");
      return;
    }
    if (!contactInput.value.trim().startsWith("0")) {
      errorFunc("連絡先は0から始まる番号を入力してください");
      return;
    }

    // ▼ モーダル表示
    modalOverlay.style.display = "block";
    modal.style.display = "block";
    // アニメーションを追加、少し大きくなる
    modal.style.transition = "transform 0.2s";
    setTimeout(() => {
      
    }, 100);
    setTimeout(() => {
      requestAnimationFrame(() => {
        modal.classList.add("active");
      });
    }, 115);
    setTimeout(() => {
      requestAnimationFrame(() => {
        modal.classList.remove("active");
        modal.classList.add("active2");
      });
    }, 250);
    

    setTimeout(() => {
      modal.classList.remove("active");
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


