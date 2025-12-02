// ポップアップ処理
const names = document.querySelectorAll(".name");
const popup = document.getElementById("popup");
const overlay = document.getElementById("overlay");
const popupName = document.getElementById("popup-name");
const popupPhone = document.getElementById("popup-phone");
const popupEmail = document.getElementById("popup-email");
const closeBtn = document.getElementById("close-btn");

names.forEach(name => {
  name.addEventListener("click", () => {
    popupName.textContent = "名前: " + name.textContent;
    popupPhone.innerHTML = "電話: <a href='tel:" + name.dataset.phone + "'>" + name.dataset.phone + "</a>";
    popupEmail.textContent = "メール: " + name.dataset.email;
    popup.style.display = "block";
    overlay.style.display = "block";
  });
});

closeBtn.addEventListener("click", () => {
  popup.style.display = "none";
  overlay.style.display = "none";
});

overlay.addEventListener("click", () => {
  popup.style.display = "none";
  overlay.style.display = "none";
});

