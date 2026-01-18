const passwordToggle = document.getElementById('password-toggle');
const passwordFields = document.getElementById('password-fields');
const passwordInput = document.getElementById('password');
const checkInput = document.getElementById('check');

const showPasswordFields = () => {
    passwordFields.classList.remove('is-hidden');
    passwordToggle.setAttribute('aria-expanded', 'true');
    passwordToggle.textContent = 'キャンセル';
    if (passwordInput) {
        passwordInput.setAttribute('required', 'required');
    }
    if (checkInput) {
        checkInput.setAttribute('required', 'required');
    }
    if (passwordInput) {
        passwordInput.focus();
    }
};

const hidePasswordFields = () => {
    passwordFields.classList.add('is-hidden');
    passwordToggle.setAttribute('aria-expanded', 'false');
    passwordToggle.textContent = '変更';
    if (passwordInput) {
        passwordInput.removeAttribute('required');
        passwordInput.value = '';
    }
    if (checkInput) {
        checkInput.removeAttribute('required');
        checkInput.value = '';
    }
};

const togglePasswordFields = () => {
    if (passwordFields.classList.contains('is-hidden')) {
        showPasswordFields();
    } else {
        hidePasswordFields();
    }
};

if (passwordToggle && passwordFields) {
    passwordToggle.addEventListener('click', togglePasswordFields);
}
