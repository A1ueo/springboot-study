/**
 * 
 */

const submitBtn = document.querySelector('#submit');
const frm = document.getElementById('frm');

const boardNum = document.querySelector('#boardNum');

submitBtn.addEventListener('click', function () {
	if (!boardNum.value || confirm('저장 하시겠습니까?')) {
		frm.submit();
	}
})