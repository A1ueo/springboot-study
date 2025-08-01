/**
 * 
 */

const submitBtn = document.querySelector('#submit');
const frm = document.getElementById('frm');

submitBtn.addEventListener('click', function () {
	if (confirm('저장 하시겠습니까?')) {
		frm.submit();
	}
})