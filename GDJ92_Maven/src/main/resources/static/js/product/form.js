/**
 * 
 */

const submitBtn = document.querySelector('#submit');
const frm = document.querySelector('#frm');

const productNum = document.getElementById('productNum');

submitBtn.addEventListener('click', function () {
	if (!productNum.value || confirm('저장 하시겠습니까?')) {
		frm.submit();
	}
});