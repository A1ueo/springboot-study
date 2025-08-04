/**
 * 
 */

const actions1 = document.getElementsByClassName('action');
const actions2 = document.querySelectorAll('.action');

const frm = document.getElementById('frm');

for (a of actions1) {
	a.addEventListener("click", (e) => {
		const k = e.target;
		const kind = k.getAttribute("data-kind")
		
		switch (kind) {
		case 'u':
			frm.setAttribute('action', './update');
			frm.submit();
			break;
		case 'd':
			if (confirm('삭제하시겠습니까?')) {
				frm.setAttribute('action', './delete');
				frm.setAttribute('method', 'post');
				frm.submit();
			}
			break;
		}
	});
}

