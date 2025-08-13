/**
 * 
 */

const buttons = document.querySelectorAll('.action');

const frm = document.querySelector('#frm');

for (b of buttons) {
	b.addEventListener('click', function () {
		elId = this.getAttribute('id');
		switch (elId) {
		case 'upd':
			frm.setAttribute('action', './update');
			frm.submit();
			break;
		case 'del':
			if (confirm('삭제하시겠습니까?')) {
				frm.setAttribute('action', './delete');
				frm.setAttribute('method', 'post');
				frm.submit();
			}
			break;
			
		case 'cart':
			const productNum = document.getElementById('productNum').value;
			let params = new URLSearchParams();
			params.append('productNum', productNum);
			
			fetch('/member/cartAdd', {
				method: 'POST',
				body: params
			})
			.then(r => r.text())
			.then(r => {
				if (r == 1) {
					if (confirm('장바구니로 이동하시겠습니까?')) {
						location.href = '/member/cartList';
					}
				}
			});
		}
	});
}