const deleteButtons = document.querySelectorAll('.delete');
deleteButtons.forEach(button => {
    button.addEventListener('click', function() {
        const userId = this.parentElement.nextElementSibling.textContent;
        Swal.fire({
            title: '本当に削除しますか？',
            text: `ユーザーID: ${userId} を削除すると復元できません。`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '削除する',
            cancelButtonText: 'キャンセル'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire(
                    `${userId} を削除しました。`,
                    '',
                    'success'
                );
                // SpringBootのルートマッピングにDELETEリクエストを送るコード
                fetch(`/api/admin/users/delete&userid=${userId}`, {
                    
                });
            }
        });
    });
});
        