const deleteForms = document.querySelectorAll('.delete-form');
deleteForms.forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        const userId = form.querySelector('input[name="userId"]')?.value || '';
        Swal.fire({
            title: '本当に削除しますか？',
            text: `ユーザーID: ${userId} を削除すると復元できません。`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '削除する',
            cancelButtonText: 'キャンセル'
        }).then((result) => {
            if (result.isConfirmed) {
                form.submit();
            }
        });
    });
});
        
