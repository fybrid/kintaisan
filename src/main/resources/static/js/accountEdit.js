const passBtn = document.getElementById('passbtn');
passBtn.addEventListener('click', function() {
    if (!document.getElementById('oldpassword')) {
        const oldPassInput = document.createElement('input');
        oldPassInput.type = 'password';
        oldPassInput.name = 'oldpassword';
        oldPassInput.id = 'oldpassword';
        oldPassInput.placeholder = '古いパスワード';

        const newPassInput = document.createElement('input');
        newPassInput.type = 'password';
        newPassInput.name = 'newpassword';
        newPassInput.id = 'newpassword';
        newPassInput.placeholder = '新しいパスワード';

        const checkPassInput = document.createElement('input');
        checkPassInput.type = 'password';
        checkPassInput.name = 'checkpassword';
        checkPassInput.id = 'checkpassword';
        checkPassInput.placeholder = '新しいパスワード（確認用）';

        passBtn.insertAdjacentElement('afterend', checkPassInput);
        passBtn.insertAdjacentElement('afterend', newPassInput);
        passBtn.insertAdjacentElement('afterend', oldPassInput);
    }
});