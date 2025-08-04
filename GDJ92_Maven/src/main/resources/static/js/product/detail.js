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
		}
	});
}